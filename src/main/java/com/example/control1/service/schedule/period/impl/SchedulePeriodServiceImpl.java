package com.example.control1.service.schedule.period.impl;

import com.example.control1.dto.common.CreateResponseDTO;
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
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Comparator;

import static com.example.control1.common.util.Utility.generateRandomUUID;

@Service
@RequiredArgsConstructor
public class SchedulePeriodServiceImpl implements SchedulePeriodService {

    private final SchedulePeriodRepository schedulePeriodRepository;
    private final ScheduleSlotRepository scheduleSlotRepository;
    private final ScheduleRepository scheduleRepository;
    private final EmployeeRepository employeeRepository;
    private final ScheduleMapper scheduleMapper;

    @Override
    public CreateResponseDTO createSchedulePeriod(SchedulePeriodCreateDTO schedulePeriodCreateDTO) {

        SchedulePeriod schedulePeriod = scheduleMapper.toSchedulePeriod(schedulePeriodCreateDTO);
        schedulePeriod.setId(generateRandomUUID());

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
                schedulePeriodCreateDTO.administratorId()
        ).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format("Employee with id: %s not found", schedulePeriodCreateDTO.administratorId())
                )
        );

        Employee executor = null;

        if (!schedulePeriodCreateDTO.administratorId().equals(schedulePeriodCreateDTO.executorId())) {

            executor = employeeRepository.findById(
                    schedulePeriodCreateDTO.executorId()
            ).orElseThrow(
                    () -> new ResponseStatusException(
                            HttpStatus.NOT_FOUND,
                            String.format("Employee with id: %s not found", schedulePeriodCreateDTO.executorId())
                    )
            );
        }

        //schedule.getSchedulePeriods().sort(Comparator.comparing(s -> s.getScheduleSlot().getBeginTime()));

        for (var oldPeriod : schedule.getSchedulePeriods()) {

            boolean startIntersects =
                    oldPeriod.getScheduleSlot().getBeginTime().isBefore(scheduleSlot.getBeginTime()) &&
                            oldPeriod.getScheduleSlot().getEndTime().isAfter(scheduleSlot.getBeginTime());

            boolean endIntersects =
                    oldPeriod.getScheduleSlot().getBeginTime().isBefore(scheduleSlot.getEndTime()) &&
                            oldPeriod.getScheduleSlot().getEndTime().isAfter(scheduleSlot.getEndTime());

            if (oldPeriod.getScheduleSlot().getBeginTime().isAfter(oldPeriod.getScheduleSlot().getEndTime())) {

                startIntersects =
                        oldPeriod.getScheduleSlot().getBeginTime().isBefore(scheduleSlot.getBeginTime()) ||
                                oldPeriod.getScheduleSlot().getEndTime().isAfter(scheduleSlot.getBeginTime());

                endIntersects =
                        oldPeriod.getScheduleSlot().getBeginTime().isBefore(scheduleSlot.getEndTime()) ||
                                oldPeriod.getScheduleSlot().getEndTime().isAfter(scheduleSlot.getEndTime());
            }

            if (startIntersects || endIntersects) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This Schedule Period intersects with existing ones");
            }
        }

        schedulePeriod.setScheduleSlot(scheduleSlot);
        schedulePeriod.setSchedule(schedule);
        schedulePeriod.setAdministrator(administrator);
        schedulePeriod.setExecutor(executor);

        schedulePeriodRepository.save(schedulePeriod);

        return scheduleMapper.toCreateResponseDTO(schedulePeriod);
    }

    @Override
    public SchedulePeriodDTO getSchedulePeriod(String id) {

        SchedulePeriod schedulePeriod = schedulePeriodRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Schedule Period with id: %s not found",id))
        );

        return scheduleMapper.toDTO(schedulePeriod);
    }

    @Override
    public Page<SchedulePeriodDTO> getAllSchedulePeriods(Integer page, Integer size) {

        Pageable pageable = PageRequest.of(page, size);

        return schedulePeriodRepository.findAll(pageable).map(scheduleMapper::toDTO);
    }
}
