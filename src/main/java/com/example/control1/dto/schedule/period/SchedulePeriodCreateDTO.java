package com.example.control1.dto.schedule.period;

import com.example.control1.entity.SchedulePeriod.SlotType;
import jakarta.validation.constraints.NotNull;

public record SchedulePeriodCreateDTO(

        @NotNull(message = "Slot id must be present")
        String slotId,

        @NotNull(message = "Schedule id must be present")
        String scheduleId,

        SlotType slotType,

        String executorId
) {
}
