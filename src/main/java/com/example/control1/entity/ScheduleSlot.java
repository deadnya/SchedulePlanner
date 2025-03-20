package com.example.control1.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetTime;

/**
 * Entity class representing a schedule slot.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "schedule_slots")
public class ScheduleSlot {

    /**
     * ID of the schedule slot.
     */
    @Id
    @Column(length = 32)
    private String id;

    /**
     * Schedule template associated with the schedule slot.
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "schedule_template_id", nullable = false)
    private ScheduleTemplate scheduleTemplate;

    /**
     * Beginning time of the schedule slot.
     */
    @Column(name = "begin_time", nullable = false, columnDefinition = "TIMETZ")
    @JsonFormat(pattern = "HH:mm:ssZ")
    private OffsetTime beginTime;

    /**
     * Ending time of the schedule slot.
     */
    @Column(name = "end_time", nullable = false, columnDefinition = "TIMETZ")
    @JsonFormat(pattern = "HH:mm:ssZ")
    private OffsetTime endTime;
}
