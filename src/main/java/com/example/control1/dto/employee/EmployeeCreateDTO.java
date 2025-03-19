package com.example.control1.dto.employee;

import com.example.control1.entity.Employee.Status;
import com.example.control1.entity.Employee.Position;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record EmployeeCreateDTO(

        @NotNull(message = "Name must be present")
        @Size(max = 255)
        String employeeName,

        @NotNull(message = "Status must be present")
        Status status,

        Position position
) {
}
