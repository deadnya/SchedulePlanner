package com.example.control1.repository;

import com.example.control1.entity.ScheduleSlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing ScheduleSlot entities.
 */
@Repository
public interface ScheduleSlotRepository extends JpaRepository<ScheduleSlot, String> {
}
