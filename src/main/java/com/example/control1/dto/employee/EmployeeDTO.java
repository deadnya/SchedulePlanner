package com.example.control1.dto.employee;

import com.example.control1.entity.Employee.*;

public record EmployeeDTO(
        String id,
        String employeeName,
        Status status,
        Position position
) {
}
