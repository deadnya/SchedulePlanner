package com.example.control1.service.schedule.period;

import com.example.control1.dto.common.CreateResponseDTO;
import com.example.control1.dto.schedule.period.SchedulePeriodOutputSettings;
import com.example.control1.dto.schedule.period.SchedulePeriodCreateDTO;
import com.example.control1.dto.schedule.period.SchedulePeriodDTO;
import org.springframework.data.domain.Page;

/**
 * Service interface for managing schedule periods.
 */
public interface SchedulePeriodService {

    /**
     * Creates a new schedule period.
     *
     * @param schedulePeriodCreateDTO data transfer object containing schedule period details
     * @return response containing the ID of the created schedule period
     */
    CreateResponseDTO createSchedulePeriod(SchedulePeriodCreateDTO schedulePeriodCreateDTO, String currentUserId);

    /**
     * Retrieves a schedule period by its ID.
     *
     * @param id ID of the schedule period
     * @return schedule period data transfer object
     */
    SchedulePeriodDTO getSchedulePeriod(String id);

    /**
     * Retrieves all schedule periods with pagination.
     *
     * @param outputSettings settings containing filter and sort parameters
     * @return a paginated list of schedule period data transfer objects
     */
    Page<SchedulePeriodDTO> getAllSchedulePeriods(SchedulePeriodOutputSettings outputSettings);
}
