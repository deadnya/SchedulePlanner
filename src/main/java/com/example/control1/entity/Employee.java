package com.example.control1.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "employees")
public class Employee {

    public enum Status {
        WORKING,
        TRIAL,
        TIME_OFF,
        DISMISSED
    }

    public enum Position {
        MANAGER,
        EMPLOYEE,
        UNDEFINED,
        TECH
    }

    @Id
    @Column(length = 32)
    private String id;

    @Column(name = "employee_name", nullable = false)
    private String employeeName;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "status", nullable = false, length = 20)
    private Status status;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "position", nullable = false, length = 20)
    private Position position;
}
