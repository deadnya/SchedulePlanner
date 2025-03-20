package com.example.control1.dto.schedule.period;

import com.example.control1.entity.SchedulePeriod.SlotType;

/**
 * Data transfer object for the filter settings of a schedule period.
 *
 * @param id ID of the schedule period
 * @param slotId ID of the slot
 * @param scheduleId ID of the schedule
 * @param slotType type of the slot
 * @param administratorId ID of the administrator
 * @param executorId ID of the executor
 */
public record SchedulePeriodFilter(
        String id,
        String slotId,
        String scheduleId,
        SlotType slotType,
        String administratorId,
        String executorId
) {
}
