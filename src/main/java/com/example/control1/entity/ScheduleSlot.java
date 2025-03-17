package com.example.control1.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "schedule_template_id", nullable = false)
    private ScheduleTemplate scheduleTemplate;

    @Column(name = "begin_time", nullable = false, columnDefinition = "TIMETZ")
    @JsonFormat(pattern = "HH:mm:ssZ")
    private OffsetTime beginTime;

    @Column(name = "end_time", nullable = false, columnDefinition = "TIMETZ")
    @JsonFormat(pattern = "HH:mm:ssZ")
    private OffsetTime endTime;
}
