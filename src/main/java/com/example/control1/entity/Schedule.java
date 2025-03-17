package com.example.control1.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

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
}
