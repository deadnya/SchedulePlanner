package com.example.control1.service.schedule.period.filter.impl;

import com.example.control1.dto.schedule.period.SchedulePeriodOutputSettings;
import com.example.control1.dto.schedule.period.SchedulePeriodSort;
import com.example.control1.entity.SchedulePeriod;
import com.example.control1.service.schedule.period.filter.SchedulePeriodFilterProvider;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

import static com.example.control1.dto.schedule.period.SchedulePeriodSort.Field.getDatabasePath;

/**
 * Implementation of the SchedulePeriodFilterProvider interface.
 * Provides functionality to generate filtering and sorting specifications for schedule periods.
 */
@Service
public class SchedulePeriodFilterProviderImpl implements SchedulePeriodFilterProvider {

    /**
     * Generates a specification for filtering schedule periods based on the provided output settings.
     *
     * @param outputSettings settings containing filter parameters
     * @return specification for filtering schedule periods
     */
    @Override
    public Specification<SchedulePeriod> getSpecForAllSchedulePeriods(SchedulePeriodOutputSettings outputSettings) {

        var filterParams = outputSettings.filter();

        var specificationPredicates = new ArrayList<Specification<SchedulePeriod>>();

        if (filterParams != null) {
            if (filterParams.id() != null) {
                specificationPredicates.add((root, query, criteriaBuilder) ->
                        criteriaBuilder.equal(root.get("id"), filterParams.id()));
            }

            if (filterParams.slotId() != null) {
                specificationPredicates.add((root, query, criteriaBuilder) ->
                        criteriaBuilder.equal(root.get("scheduleSlot").get("id"), filterParams.slotId()));
            }

            if (filterParams.scheduleId() != null) {
                specificationPredicates.add((root, query, criteriaBuilder) ->
                        criteriaBuilder.equal(root.get("schedule").get("id"), filterParams.scheduleId()));
            }

            if (filterParams.slotType() != null) {
                specificationPredicates.add((root, query, criteriaBuilder) ->
                        criteriaBuilder.equal(root.get("slotType"), filterParams.slotType()));
            }

            if (filterParams.administratorId() != null) {
                specificationPredicates.add((root, query, criteriaBuilder) ->
                        criteriaBuilder.equal(root.get("administrator").get("id"), filterParams.administratorId()));
            }

            if (filterParams.executorId() != null) {
                specificationPredicates.add((root, query, criteriaBuilder) ->
                        criteriaBuilder.equal(root.get("executor").get("id"), filterParams.executorId()));
            }
        }

        return Specification.allOf(specificationPredicates);
    }

    /**
     * Generates a sort object for sorting schedule periods based on the provided output settings.
     *
     * @param outputSettings settings containing sort parameters
     * @return sort object for sorting schedule periods
     */
    @Override
    public Sort getSortForAllSchedulePeriods(SchedulePeriodOutputSettings outputSettings) {

        Sort sort = null;

        if (outputSettings.sort() != null && outputSettings.sort().field() != null) {

            sort = Sort.by(getDatabasePath(outputSettings.sort().field()));

            if (outputSettings.sort().direction() == SchedulePeriodSort.Direction.DESC) {
                sort = sort.descending();
            }
        }

        return sort;
    }
}
