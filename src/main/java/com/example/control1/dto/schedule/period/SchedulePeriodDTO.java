package com.example.control1.dto.schedule.period;

import com.example.control1.dto.employee.EmployeeDTO;
import com.example.control1.dto.schedule.main.ScheduleShortDTO;
import com.example.control1.dto.schedule.slot.ScheduleSlotDTO;
import com.example.control1.entity.SchedulePeriod.SlotType;

/**
 * Data transfer object for a schedule period.
 *
 * @param id ID of the schedule period
 * @param slot slot details of the schedule period
 * @param schedule short representation of the schedule
 * @param slotType type of the slot
 * @param administrator administrator of the schedule period
 * @param executor executor of the schedule period
 */
public record SchedulePeriodDTO(
        String id,
        ScheduleSlotDTO slot,
        ScheduleShortDTO schedule,
        SlotType slotType,
        EmployeeDTO administrator,
        EmployeeDTO executor
) {
}
