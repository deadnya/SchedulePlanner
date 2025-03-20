package com.example.control1.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * Entity class representing a schedule.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "schedules")
public class Schedule {

    /**
     * ID of the schedule.
     */
    @Id
    @Column(length = 32)
    private String id;

    /**
     * Name of the schedule.
     */
    @Column(name = "schedule_name")
    private String scheduleName;

    /**
     * Date and time when the schedule was created.
     */
    @CreationTimestamp
    @Column(name = "creation_date", nullable = false)
    private Instant creationDate;

    /**
     * Date and time when the schedule was last updated.
     */
    @UpdateTimestamp
    @Column(name = "update_date", nullable = false)
    private Instant updateDate;

    /**
     * List of schedule periods associated with the schedule.
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "schedule", orphanRemoval = true, fetch = FetchType.LAZY)
    private List<SchedulePeriod> schedulePeriods = new ArrayList<>();
}