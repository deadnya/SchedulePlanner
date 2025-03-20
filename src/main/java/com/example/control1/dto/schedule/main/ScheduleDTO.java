package com.example.control1.dto.schedule.main;

import com.example.control1.dto.schedule.period.SchedulePeriodDTO;

import java.time.Instant;
import java.util.List;

/**
 * Data transfer object for a schedule.
 *
 * @param id ID of the schedule
 * @param scheduleName name of the schedule
 * @param creationDate creation date of the schedule
 * @param updateDate last update date of the schedule
 * @param schedulePeriods list of schedule periods associated with the schedule
 */
public record ScheduleDTO(
        String id,
        String scheduleName,
        Instant creationDate,
        Instant updateDate,
        List<SchedulePeriodDTO> schedulePeriods
) {
}
