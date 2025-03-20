package com.example.control1.controller.schedule;

import com.example.control1.dto.common.CreateResponseDTO;
import com.example.control1.dto.schedule.period.SchedulePeriodOutputSettings;
import com.example.control1.dto.schedule.period.SchedulePeriodCreateDTO;
import com.example.control1.dto.schedule.period.SchedulePeriodDTO;
import com.example.control1.service.schedule.period.SchedulePeriodService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for managing schedule periods.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/schedule/period")
public class SchedulePeriodController {

    private final SchedulePeriodService schedulePeriodService;

    /**
     * Creates a new schedule period.
     *
     * @param currentUserId ID of the current user
     * @param schedulePeriodCreateDTO schedule period creation data transfer object
     * @return response entity containing the creation response data transfer object
     */
    @PostMapping("/create")
    public ResponseEntity<CreateResponseDTO> createSchedulePeriod(
            @RequestHeader("x-current-user") String currentUserId,
            @Valid @RequestBody SchedulePeriodCreateDTO schedulePeriodCreateDTO
    ) {
        return ResponseEntity.ok(schedulePeriodService.createSchedulePeriod(schedulePeriodCreateDTO, currentUserId));
    }

    /**
     * Retrieves a schedule period by ID.
     *
     * @param id ID of the schedule period
     * @return response entity containing the schedule period data transfer object
     */
    @GetMapping("/get/{id}")
    public ResponseEntity<SchedulePeriodDTO> getSchedulePeriod(
            @PathVariable String id
    ) {
        return ResponseEntity.ok(schedulePeriodService.getSchedulePeriod(id));
    }

    /**
     * Retrieves all schedule periods based on output settings.
     *
     * @param outputSettings output settings for retrieving schedule periods
     * @return response entity containing a page of schedule period data transfer objects
     */
    @PostMapping("/get")
    public ResponseEntity<Page<SchedulePeriodDTO>> getAllSchedulePeriods(
            @RequestBody SchedulePeriodOutputSettings outputSettings
    ) {
        return ResponseEntity.ok(schedulePeriodService.getAllSchedulePeriods(outputSettings));
    }
}
