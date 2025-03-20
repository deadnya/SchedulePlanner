package com.example.control1.dto.schedule.template;

import java.time.Instant;

/**
 * Data transfer object for a schedule template.
 *
 * @param id ID of the schedule template
 * @param creationDate creation date of the schedule template
 * @param templateType type of the template
 */
public record ScheduleTemplateDTO(
        String id,
        Instant creationDate,
        String templateType
) {
}
