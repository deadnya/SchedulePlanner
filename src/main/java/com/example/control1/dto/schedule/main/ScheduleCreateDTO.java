package com.example.control1.dto.schedule.main;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * Data transfer object for creating a schedule.
 *
 * @param scheduleName name of the schedule
 */
public record ScheduleCreateDTO(
        @Size(max = 255, message = "Maximum name size: 255")
        @NotNull(message = "Schedule name must be present")
        String scheduleName
) {
}
