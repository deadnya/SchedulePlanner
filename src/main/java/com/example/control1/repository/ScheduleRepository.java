package com.example.control1.repository;

import com.example.control1.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for managing SchedulePeriod entities.
 */
@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, String> {

    /**
     * Finds a schedule by its ID and sorts its periods by begin time.
     *
     * @param id ID of the schedule
     * @return an optional containing the schedule if found, or empty if not found
     */
    @Query("SELECT s FROM Schedule s " +
            "LEFT JOIN FETCH s.schedulePeriods sp " +
            "LEFT JOIN FETCH sp.slot sl " +
            "WHERE s.id = :id ORDER BY sl.beginTime")
    Optional<Schedule> findByIdSortPeriodsByBeginTime(@Param("id") String id);

    /**
     * Finds a schedule by its name and sorts its periods by begin time.
     *
     * @param scheduleName name of the schedule
     * @return an optional containing the schedule if found, or empty if not found
     */
    @Query("SELECT s FROM Schedule s " +
            "LEFT JOIN FETCH s.schedulePeriods sp " +
            "LEFT JOIN FETCH sp.slot sl " +
            "WHERE s.scheduleName = :scheduleName ORDER BY sl.beginTime")
    Optional<Schedule> findByScheduleNameSortPeriodsByBeginTime(@Param("scheduleName") String scheduleName);
}
