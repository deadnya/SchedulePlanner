package com.example.control1.controller.schedule;

import com.example.control1.dto.common.CreateResponseDTO;
import com.example.control1.dto.schedule.slot.ScheduleSlotCreateDTO;
import com.example.control1.dto.schedule.slot.ScheduleSlotDTO;
import com.example.control1.service.schedule.slot.ScheduleSlotService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for managing schedule slots.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/schedule/slot")
public class ScheduleSlotController {

    private final ScheduleSlotService scheduleSlotService;

    /**
     * Creates a new schedule slot.
     *
     * @param scheduleSlotCreateDTO Schedule slot creation data transfer object
     * @return response entity containing the creation response data transfer object
     */
    @PostMapping("/create")
    public ResponseEntity<CreateResponseDTO> createScheduleSlot(
            @Valid @RequestBody ScheduleSlotCreateDTO scheduleSlotCreateDTO
    ) {
        return ResponseEntity.ok(scheduleSlotService.createScheduleSlot(scheduleSlotCreateDTO));
    }

    /**
     * Retrieves a schedule slot by ID.
     *
     * @param id ID of the schedule slot
     * @return response entity containing the schedule slot data transfer object
     */
    @GetMapping("/get/{id}")
    public ResponseEntity<ScheduleSlotDTO> getScheduleSlot(
            @PathVariable String id
    ) {
        return ResponseEntity.ok(scheduleSlotService.getScheduleSlot(id));
    }

    /**
     * Retrieves all schedule slots with pagination.
     *
     * @param page page number to retrieve
     * @param size number of schedule slots per page
     * @return response entity containing a page of schedule slot data transfer objects
     */
    @GetMapping("/get")
    public ResponseEntity<Page<ScheduleSlotDTO>> getAllScheduleSlots(
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "size", defaultValue = "5") Integer size
    ) {
        return ResponseEntity.ok(scheduleSlotService.getAllScheduleSlots(page, size));
    }
}
