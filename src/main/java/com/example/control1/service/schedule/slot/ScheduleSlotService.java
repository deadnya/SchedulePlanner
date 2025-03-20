package com.example.control1.service.schedule.slot;

import com.example.control1.dto.common.CreateResponseDTO;
import com.example.control1.dto.schedule.slot.ScheduleSlotCreateDTO;
import com.example.control1.dto.schedule.slot.ScheduleSlotDTO;
import org.springframework.data.domain.Page;

/**
 * Service interface for managing schedule slots.
 */
public interface ScheduleSlotService {

    /**
     * Creates a new schedule slot.
     *
     * @param scheduleSlotCreateDTO data transfer object containing schedule slot details
     * @return response containing the ID of the created schedule slot
     */
    CreateResponseDTO createScheduleSlot(ScheduleSlotCreateDTO scheduleSlotCreateDTO);

    /**
     * Retrieves a schedule slot by its ID.
     *
     * @param id ID of the schedule slot
     * @return schedule slot data transfer object
     */
    ScheduleSlotDTO getScheduleSlot(String id);

    /**
     * Retrieves all schedule slots with pagination.
     *
     * @param page page number to retrieve
     * @param size number of schedule slots per page
     * @return a paginated list of schedule slot data transfer objects
     */
    Page<ScheduleSlotDTO> getAllScheduleSlots(Integer page, Integer size);
}
