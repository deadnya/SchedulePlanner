package com.example.control1.dto.schedule.period;

import com.example.control1.entity.SchedulePeriod.SlotType;
import jakarta.validation.constraints.NotNull;

/**
 * Data transfer object for creating a schedule period.
 *
 * @param slotId ID of the slot
 * @param scheduleId ID of the schedule
 * @param slotType type of the slot
 * @param executorId ID of the executor
 */
public record SchedulePeriodCreateDTO(

        @NotNull(message = "Slot id must be present")
        String slotId,

        @NotNull(message = "Schedule id must be present")
        String scheduleId,

        SlotType slotType,

        String executorId
) {
}
