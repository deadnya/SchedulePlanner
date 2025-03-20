package com.example.control1.dto.schedule.template;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

/**
 * Data transfer object for creating a schedule template.
 *
 * @param templateType type of the template, consisting of 2 capital letters, e.g. "AA"
 */
public record ScheduleTemplateCreateDTO(
        @NotNull(message = "Template type must be present")
        @Pattern(regexp = "[A-Z]{2}", message = "Type must consist of 2 capital letters")
        String templateType
) {
}
