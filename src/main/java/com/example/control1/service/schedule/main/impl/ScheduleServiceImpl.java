package com.example.control1.service.schedule.main.impl;

import com.example.control1.dto.common.CreateResponseDTO;
import com.example.control1.dto.schedule.main.ScheduleCreateDTO;
import com.example.control1.dto.schedule.main.ScheduleDTO;
import com.example.control1.entity.Schedule;
import com.example.control1.mapper.ScheduleMapper;
import com.example.control1.repository.ScheduleRepository;
import com.example.control1.service.schedule.main.ScheduleService;
import com.example.control1.service.uuid.UUIDService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

/**
 * Implementation of the ScheduleService interface.
 * Provides functionality to manage schedules.
 */
@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final ScheduleMapper scheduleMapper;
    private final UUIDService uuidService;

    /**
     * Creates a new schedule.
     *
     * @param scheduleCreateDTO Data transfer object containing schedule details
     * @return Response containing the ID of the created schedule
     * @throws ResponseStatusException if the schedule cannot be created
     */
    @Override
    public CreateResponseDTO createSchedule(ScheduleCreateDTO scheduleCreateDTO) {

        Schedule schedule = scheduleMapper.toSchedule(scheduleCreateDTO);
        schedule.setId(uuidService.getRandomUUID());

        scheduleRepository.save(schedule);

        return scheduleMapper.toResponseCreateDTO(schedule);
    }

    /**
     * Retrieves a schedule by its ID or name.
     *
     * @param request ID or name of the schedule
     * @return schedule data transfer object. All periods are sorted by begin time.
     * @throws ResponseStatusException if the schedule is not found
     */
    @Override
    public ScheduleDTO getSchedule(String request) {

        Optional<Schedule> schedule = scheduleRepository.findByIdSortPeriodsByBeginTime(request);

        if (schedule.isEmpty()) {
            schedule = scheduleRepository.findByScheduleNameSortPeriodsByBeginTime(request);
        }

        if (schedule.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    String.format("Could not find schedule by request: %s", request)
            );
        }

        return scheduleMapper.toDTO(schedule.get());
    }

    /**
     * Retrieves all schedules with pagination.
     *
     * @param page page number to retrieve
     * @param size number of schedules per page
     * @return a paginated list of schedule data transfer objects
     */
    @Override
    public Page<ScheduleDTO> getAllSchedules(Integer page, Integer size) {

        Pageable pageable = PageRequest.of(page, size);

        return scheduleRepository.findAll(pageable).map(scheduleMapper::toDTO);
    }
}
