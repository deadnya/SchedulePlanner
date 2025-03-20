package com.example.control1.dto.schedule.slot;

import com.example.control1.dto.schedule.template.ScheduleTemplateDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.OffsetTime;

/**
 * Data transfer object for a schedule slot.
 *
 * @param id ID of the schedule slot
 * @param scheduleTemplate template details of the schedule slot
 * @param beginTime begin time of the slot
 * @param endTime end time of the slot
 */
public record ScheduleSlotDTO(

        String id,

        ScheduleTemplateDTO scheduleTemplate,

        @JsonFormat(pattern = "HH:mm:ssZ")
        @Schema(type = "string", format = "time", example = "14:30:00-0800")
        OffsetTime beginTime,

        @JsonFormat(pattern = "HH:mm:ssZ")
        @Schema(type = "string", format = "time", example = "14:30:00-0800")
        OffsetTime endTime
) {
}
