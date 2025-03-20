package com.example.control1.dto.employee;

import com.example.control1.entity.Employee.*;

/**
 * Data transfer object for Employee entities.
 *
 * @param id the ID of the employee
 * @param employeeName the name of the employee
 * @param status the status of the employee
 * @param position the position of the employee
 */
public record EmployeeDTO(
        String id,
        String employeeName,
        Status status,
        Position position
) {
}
