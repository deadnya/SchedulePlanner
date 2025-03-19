package com.example.control1.repository;

import com.example.control1.entity.SchedulePeriod;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SchedulePeriodRepository extends JpaRepository<SchedulePeriod, String> {
    Page<SchedulePeriod> findAll(Specification<SchedulePeriod> specification, Pageable pageable);
}
