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

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "schedules")
public class Schedule {

    @Id
    @Column(length = 32)
    private String id;

    @Column(name = "schedule_name")
    private String scheduleName;

    @CreationTimestamp
    @Column(name = "creation_date", nullable = false)
    private Instant creationDate;

    @UpdateTimestamp
    @Column(name = "update_date", nullable = false)
    private Instant updateDate;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "schedule", orphanRemoval = true, fetch = FetchType.LAZY)
    private List<SchedulePeriod> schedulePeriods = new ArrayList<>();

    public void addPeriod(SchedulePeriod schedulePeriod) {
        schedulePeriods.add(schedulePeriod);
        schedulePeriod.setSchedule(this);
    }

    public void removePeriod(SchedulePeriod schedulePeriod) {
        schedulePeriods.remove(schedulePeriod);
        schedulePeriod.setSchedule(null);
    }
}
