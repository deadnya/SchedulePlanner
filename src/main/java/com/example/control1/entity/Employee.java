package com.example.control1.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entity class representing an employee.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "employees")
public class Employee {

    /**
     * Enumeration of possible employee statuses.
     */
    public enum Status {
        WORKING,
        TRIAL,
        TIME_OFF,
        DISMISSED
    }

    /**
     * Enumeration of possible employee positions.
     */
    public enum Position {
        MANAGER,
        EMPLOYEE,
        UNDEFINED,
        TECH
    }

    /**
     * ID of the employee.
     */
    @Id
    @Column(length = 32)
    private String id;

    /**
     * Name of the employee.
     */
    @Column(name = "employee_name", nullable = false)
    private String employeeName;

    /**
     * Status of the employee.
     */
    @Enumerated(value = EnumType.STRING)
    @Column(name = "status", nullable = false, length = 20)
    private Status status;

    /**
     * Position of the employee.
     */
    @Enumerated(value = EnumType.STRING)
    @Column(name = "position", nullable = false, length = 20)
    private Position position;
}
