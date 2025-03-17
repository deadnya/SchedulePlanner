package com.example.control1.dto.schedule.period;

import com.example.control1.entity.SchedulePeriod.SlotType;
import jakarta.validation.constraints.NotNull;

public record SchedulePeriodCreateDTO(

        @NotNull(message = "Slot id must be present")
        String slotId,

        @NotNull(message = "Schedule id must be present")
        String scheduleId,

        @NotNull(message = "Slot type must be present")
        SlotType slotType,

        @NotNull(message = "Administrator id must be present")
        String administratorId,

        String executorId
) {
}
