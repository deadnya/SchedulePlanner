package com.example.control1.service.schedule.period.impl;

import com.example.control1.dto.common.CreateResponseDTO;
import com.example.control1.dto.schedule.period.SchedulePeriodOutputSettings;
import com.example.control1.dto.schedule.period.SchedulePeriodCreateDTO;
import com.example.control1.dto.schedule.period.SchedulePeriodDTO;
import com.example.control1.entity.Employee;
import com.example.control1.entity.Schedule;
import com.example.control1.entity.SchedulePeriod;
import com.example.control1.entity.ScheduleSlot;
import com.example.control1.mapper.ScheduleMapper;
import com.example.control1.repository.EmployeeRepository;
import com.example.control1.repository.SchedulePeriodRepository;
import com.example.control1.repository.ScheduleRepository;
import com.example.control1.repository.ScheduleSlotRepository;
import com.example.control1.service.schedule.period.SchedulePeriodService;
import com.example.control1.service.schedule.period.filter.SchedulePeriodFilterProvider;
import com.example.control1.service.uuid.UUIDService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 * Implementation of the SchedulePeriodService interface.
 * Provides functionality to manage schedule periods.
 */
@Service
@RequiredArgsConstructor
public class SchedulePeriodServiceImpl implements SchedulePeriodService {

    private final SchedulePeriodRepository schedulePeriodRepository;
    private final ScheduleSlotRepository scheduleSlotRepository;
    private final ScheduleRepository scheduleRepository;
    private final EmployeeRepository employeeRepository;
    private final ScheduleMapper scheduleMapper;
    private final UUIDService uuidService;
    private final SchedulePeriodFilterProvider schedulePeriodFilterProvider;

    /**
     * Creates a new schedule period.
     *
     * @param schedulePeriodCreateDTO the data transfer object containing schedule period details
     * @param currentUserId the ID of the current user creating the schedule period
     * @return the response containing the ID of the created schedule period
     * @throws ResponseStatusException if the schedule slot, schedule, or employee is not found,
     * or if the new schedule period intersects with existing ones in the schedule
     */
    @Override
    public CreateResponseDTO createSchedulePeriod(SchedulePeriodCreateDTO schedulePeriodCreateDTO, String currentUserId) {

        SchedulePeriod schedulePeriod = scheduleMapper.toSchedulePeriod(schedulePeriodCreateDTO);
        schedulePeriod.setId(uuidService.getRandomUUID());

        if (schedulePeriod.getSlotType() == null) {
            schedulePeriod.setSlotType(SchedulePeriod.SlotType.UNDEFINED);
        }

        ScheduleSlot scheduleSlot = scheduleSlotRepository.findById(
                schedulePeriodCreateDTO.slotId()
        ).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format("Schedule Slot with id: %s not found", schedulePeriodCreateDTO.slotId())
                )
        );

        Schedule schedule = scheduleRepository.findById(
                schedulePeriodCreateDTO.scheduleId()
        ).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format("Schedule with id: %s not found", schedulePeriodCreateDTO.scheduleId())
                )
        );

        Employee administrator = employeeRepository.findById(
                currentUserId
        ).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format("Employee with id: %s not found", currentUserId)
                )
        );

        Employee executor = null;

        if (!currentUserId.equals(schedulePeriodCreateDTO.executorId())) {

            executor = employeeRepository.findById(
                    schedulePeriodCreateDTO.executorId()
            ).orElseThrow(
                    () -> new ResponseStatusException(
                            HttpStatus.NOT_FOUND,
                            String.format("Employee with id: %s not found", schedulePeriodCreateDTO.executorId())
                    )
            );
        }

        for (var oldPeriod : schedule.getSchedulePeriods()) {

            boolean startIntersects =
                    oldPeriod.getSlot().getBeginTime().isBefore(scheduleSlot.getBeginTime()) &&
                            oldPeriod.getSlot().getEndTime().isAfter(scheduleSlot.getBeginTime());

            boolean endIntersects =
                    oldPeriod.getSlot().getBeginTime().isBefore(scheduleSlot.getEndTime()) &&
                            oldPeriod.getSlot().getEndTime().isAfter(scheduleSlot.getEndTime());

            if (oldPeriod.getSlot().getBeginTime().isAfter(oldPeriod.getSlot().getEndTime())) {

                startIntersects =
                        oldPeriod.getSlot().getBeginTime().isBefore(scheduleSlot.getBeginTime()) ||
                                oldPeriod.getSlot().getEndTime().isAfter(scheduleSlot.getBeginTime());

                endIntersects =
                        oldPeriod.getSlot().getBeginTime().isBefore(scheduleSlot.getEndTime()) ||
                                oldPeriod.getSlot().getEndTime().isAfter(scheduleSlot.getEndTime());
            }

            if (startIntersects || endIntersects) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This Schedule Period intersects with existing ones");
            }
        }

        schedulePeriod.setSlot(scheduleSlot);
        schedulePeriod.setSchedule(schedule);
        schedulePeriod.setAdministrator(administrator);
        schedulePeriod.setExecutor(executor);

        schedulePeriodRepository.save(schedulePeriod);

        return scheduleMapper.toCreateResponseDTO(schedulePeriod);
    }

    /**
     * Retrieves a schedule period by its ID.
     *
     * @param id the ID of the schedule period
     * @return the schedule period data transfer object
     * @throws ResponseStatusException if the schedule period is not found
     */
    @Override
    public SchedulePeriodDTO getSchedulePeriod(String id) {

        SchedulePeriod schedulePeriod = schedulePeriodRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Schedule Period with id: %s not found",id))
        );

        return scheduleMapper.toDTO(schedulePeriod);
    }

    /**
     * Retrieves all schedule periods with pagination.
     *
     * @param outputSettings the settings containing filter and sort parameters
     * @return a paginated list of schedule period data transfer objects
     */
    @Override
    public Page<SchedulePeriodDTO> getAllSchedulePeriods(SchedulePeriodOutputSettings outputSettings) {

        var specification = schedulePeriodFilterProvider.getSpecForAllSchedulePeriods(outputSettings);

        Sort sort = schedulePeriodFilterProvider.getSortForAllSchedulePeriods(outputSettings);
        Pageable pageable = PageRequest.of(outputSettings.page(), 5, sort != null ? sort : Sort.unsorted());

        return schedulePeriodRepository.findAll(specification, pageable).map(scheduleMapper::toDTO);
    }
}
