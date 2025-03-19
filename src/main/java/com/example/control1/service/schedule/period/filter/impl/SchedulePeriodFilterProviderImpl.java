package com.example.control1.service.schedule.period.filter.impl;

import com.example.control1.dto.schedule.period.OutputSettings;
import com.example.control1.dto.schedule.period.SchedulePeriodSort;
import com.example.control1.entity.SchedulePeriod;
import com.example.control1.service.schedule.period.filter.SchedulePeriodFilterProvider;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

import static com.example.control1.dto.schedule.period.SchedulePeriodSort.Field.getDatabasePath;

@Service
public class SchedulePeriodFilterProviderImpl implements SchedulePeriodFilterProvider {

    @Override
    public Specification<SchedulePeriod> getSpecForAllSchedulePeriods(OutputSettings outputSettings) {

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

    @Override
    public Sort getSortForAllSchedulePeriods(OutputSettings outputSettings) {

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
