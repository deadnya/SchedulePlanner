package com.example.control1.dto.schedule.slot;

import com.example.control1.entity.ScheduleTemplate;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.OffsetTime;

public record ScheduleSlotDTO(

        String id,

        ScheduleTemplate scheduleTemplate,

        @JsonFormat(pattern = "HH:mm:ssZ")
        @Schema(type = "string", format = "time", example = "14:30:00-0800")
        OffsetTime beginTime,

        @JsonFormat(pattern = "HH:mm:ssZ")
        @Schema(type = "string", format = "time", example = "14:30:00-0800")
        OffsetTime endTime
) {
}
