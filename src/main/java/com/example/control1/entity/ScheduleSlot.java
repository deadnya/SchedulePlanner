package com.example.control1.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "schedule_slots")
public class ScheduleSlot {

    @Id
    @Column(length = 32)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "schedule_template_id", nullable = false)
    private ScheduleTemplate scheduleTemplate;

    @Column(name = "begin_time", nullable = false)
    private OffsetTime beginTime;

    @Column(name = "end_time", nullable = false)
    private OffsetTime endTime;
}
