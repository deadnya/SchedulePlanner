package com.example.control1.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;

/**
 * Entity class representing a schedule template.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "schedule_templates")
public class ScheduleTemplate {

    /**
     * ID of the schedule template.
     */
    @Id
    @Column(length = 32)
    private String id;

    /**
     * Date and time when the schedule template was created.
     */
    @CreationTimestamp
    @Column(name = "creation_date", nullable = false)
    private Instant creationDate;

    /**
     * Template type of the schedule.
     * Must consist of two uppercase letters, e.g. "AB".
     */
    @Column(name = "template_type", nullable = false, length = 2)
    @Pattern(regexp = "[A-Z]{2}")
    private String templateType;
}
