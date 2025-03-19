package com.example.control1.dto.schedule.period;

import com.example.control1.entity.SchedulePeriod.SlotType;

public record SchedulePeriodFilter(
        String id,
        String slotId,
        String scheduleId,
        SlotType slotType,
        String administratorId,
        String executorId
) {
}
