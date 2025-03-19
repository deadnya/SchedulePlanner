package com.example.control1.service.schedule.period.filter;

import com.example.control1.dto.schedule.period.OutputSettings;
import com.example.control1.entity.SchedulePeriod;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

public interface SchedulePeriodFilterProvider {
    Specification<SchedulePeriod> getSpecForAllSchedulePeriods(OutputSettings outputSettings);
    Sort getSortForAllSchedulePeriods(OutputSettings outputSettings);
}
