package com.example.control1.repository;

import com.example.control1.entity.SchedulePeriod;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing SchedulePeriod entities.
 */
@Repository
public interface SchedulePeriodRepository extends JpaRepository<SchedulePeriod, String> {

    /**
     * Finds all schedule periods matching the given specification with pagination.
     *
     * @param specification specification to filter schedule periods
     * @param pageable pagination information
     * @return a paginated list of schedule periods matching the specification
     */
    Page<SchedulePeriod> findAll(Specification<SchedulePeriod> specification, Pageable pageable);
}
