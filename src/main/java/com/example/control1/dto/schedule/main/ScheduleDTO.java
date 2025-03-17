package com.example.control1.dto.schedule.main;

import com.example.control1.entity.SchedulePeriod;

import java.time.Instant;
import java.util.List;

public record ScheduleDTO(
        String id,
        String scheduleName,
        Instant creationDate,
        Instant updateDate,
        List<SchedulePeriod> schedulePeriods
) {
}
