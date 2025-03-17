package com.example.control1.dto.schedule.template;

import java.time.Instant;

public record ScheduleTemplateDTO(
        String id,
        Instant creationDate,
        String templateType
) {
}
