package com.example.control1.dto.schedule.main;

/**
 * Data transfer object for a short representation of a schedule.
 *
 * @param id ID of the schedule
 * @param scheduleName name of the schedule
 */
public record ScheduleShortDTO(
        String id,
        String scheduleName
) { }
