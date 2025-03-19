package com.example.control1.dto.schedule.period;

import com.example.control1.dto.employee.EmployeeDTO;
import com.example.control1.dto.schedule.main.ScheduleShortDTO;
import com.example.control1.dto.schedule.slot.ScheduleSlotDTO;
import com.example.control1.entity.SchedulePeriod.SlotType;

public record SchedulePeriodDTO(
        String id,
        ScheduleSlotDTO slot,
        ScheduleShortDTO schedule,
        SlotType slotType,
        EmployeeDTO administrator,
        EmployeeDTO executor
) {
}
