package com.example.control1.dto.schedule.template;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record ScheduleTemplateCreateDTO(
        @NotNull(message = "Template type must be present")
        @Pattern(regexp = "[A-Z]{2}", message = "Type must consist of 2 capital letters")
        String templateType
) {
}
