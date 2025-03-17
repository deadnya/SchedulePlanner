package com.example.control1.dto.schedule.period;

import com.example.control1.entity.Employee;
import com.example.control1.entity.Schedule;
import com.example.control1.entity.SchedulePeriod.SlotType;
import com.example.control1.entity.ScheduleSlot;

public record SchedulePeriodDTO(
        String id,
        ScheduleSlot slot,
        Schedule schedule,
        SlotType slotType,
        Employee administrator,
        Employee executor
) {
}
