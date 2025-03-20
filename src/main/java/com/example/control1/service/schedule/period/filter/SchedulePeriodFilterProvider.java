package com.example.control1.service.schedule.period.filter;

import com.example.control1.dto.schedule.period.SchedulePeriodOutputSettings;
import com.example.control1.entity.SchedulePeriod;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

/**
 * Interface for providing filtering and sorting specifications for schedule periods.
 */
public interface SchedulePeriodFilterProvider {

    /**
     * Generates a specification for filtering schedule periods based on the provided output settings.
     *
     * @param outputSettings settings containing filter parameters
     * @return specification for filtering schedule periods
     */
    Specification<SchedulePeriod> getSpecForAllSchedulePeriods(SchedulePeriodOutputSettings outputSettings);

    /**
     * Generates a sort object for sorting schedule periods based on the provided output settings.
     *
     * @param outputSettings settings containing sort parameters
     * @return sort object for sorting schedule periods
     */
    Sort getSortForAllSchedulePeriods(SchedulePeriodOutputSettings outputSettings);
}
