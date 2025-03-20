package com.example.control1.service.schedule.main;

import com.example.control1.dto.common.CreateResponseDTO;
import com.example.control1.dto.schedule.main.ScheduleCreateDTO;
import com.example.control1.dto.schedule.main.ScheduleDTO;
import org.springframework.data.domain.Page;

/**
 * Service interface for managing schedules.
 */
public interface ScheduleService {

    /**
     * Creates a new schedule.
     *
     * @param scheduleCreateDTO data transfer object containing schedule details
     * @return response containing the ID of the created schedule
     */
    CreateResponseDTO createSchedule(ScheduleCreateDTO scheduleCreateDTO);

    /**
     * Retrieves a schedule by its ID or name.
     *
     * @param request ID or name of the schedule
     * @return schedule data transfer object
     */
    ScheduleDTO getSchedule(String request);

    /**
     * Retrieves all schedules with pagination.
     *
     * @param page page number to retrieve
     * @param size number of schedules per page
     * @return a paginated list of schedule data transfer objects
     */
    Page<ScheduleDTO> getAllSchedules(Integer page, Integer size);
}
