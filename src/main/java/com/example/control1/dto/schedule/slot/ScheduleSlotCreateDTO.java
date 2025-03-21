package com.example.control1.dto.schedule.slot;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

import java.time.OffsetTime;

/**
 * Data transfer object for creating a schedule slot.
 *
 * @param scheduleTemplateId ID of the schedule template
 * @param beginTime begin time of the slot
 * @param endTime end time of the slot
 */
public record ScheduleSlotCreateDTO(

        @NotNull(message = "Schedule template id must be present")
        String scheduleTemplateId,

        @NotNull(message = "Begin time must be present")
        @JsonFormat(pattern = "HH:mm:ssZ")
        @Schema(type = "string", format = "time", example = "14:30:00-0800")
        OffsetTime beginTime,

        @NotNull(message = "End time must be present")
        @JsonFormat(pattern = "HH:mm:ssZ")
        @Schema(type = "string", format = "time", example = "14:30:00-0800")
        OffsetTime endTime
) {
}
