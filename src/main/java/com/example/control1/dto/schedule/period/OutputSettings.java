package com.example.control1.dto.schedule.period;

public record OutputSettings(
        SchedulePeriodFilter filter,
        SchedulePeriodSort sort,
        Integer page
) {
}
