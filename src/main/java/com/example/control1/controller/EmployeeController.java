package com.example.control1.controller;

import com.example.control1.dto.employee.EmployeeCreateDTO;
import com.example.control1.dto.employee.EmployeeCreateResponseDTO;
import com.example.control1.dto.employee.EmployeeDTO;
import com.example.control1.service.employee.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employee")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping("/create")
    public ResponseEntity<EmployeeCreateResponseDTO> createEmployee(
            @Valid @RequestBody EmployeeCreateDTO employeeCreateDTO
    ) {
        return ResponseEntity.ok(employeeService.createEmployee(employeeCreateDTO));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<EmployeeDTO> getEmployee(
            @PathVariable String id
    ) {
        return ResponseEntity.ok(employeeService.getEmployee(id));
    }

    @GetMapping("/get")
    public ResponseEntity<Page<EmployeeDTO>> getAllEmployees(
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "size", defaultValue = "5") Integer size
    ) {
        return ResponseEntity.ok(employeeService.getAllEmployees(page, size));
    }
}
