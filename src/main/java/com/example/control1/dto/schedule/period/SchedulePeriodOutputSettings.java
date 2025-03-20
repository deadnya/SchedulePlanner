package com.example.control1.dto.schedule.period;

/**
 * Data transfer object for schedule period output settings.
 *
 * @param filter filter settings for the schedule period
 * @param sort sort settings for the schedule period
 * @param page page number for pagination
 */
public record SchedulePeriodOutputSettings(
        SchedulePeriodFilter filter,
        SchedulePeriodSort sort,
        Integer page
) {
}
