package com.example.control1.controller.employee;

import com.example.control1.dto.common.CreateResponseDTO;
import com.example.control1.dto.employee.EmployeeCreateDTO;
import com.example.control1.dto.employee.EmployeeDTO;
import com.example.control1.service.employee.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for managing employees.
 */
@RestController
@RequestMapping("/api/employee")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    /**
     * Creates a new employee.
     *
     * @param employeeCreateDTO employee creation data transfer object
     * @return response entity containing the creation response data transfer object
     */
    @PostMapping("/create")
    public ResponseEntity<CreateResponseDTO> createEmployee(
            @Valid @RequestBody EmployeeCreateDTO employeeCreateDTO
    ) {
        return ResponseEntity.ok(employeeService.createEmployee(employeeCreateDTO));
    }

    /**
     * Retrieves an employee by ID.
     *
     * @param id ID of the employee
     * @return response entity containing the employee data transfer object
     */
    @GetMapping("/get/{id}")
    public ResponseEntity<EmployeeDTO> getEmployee(
            @PathVariable String id
    ) {
        return ResponseEntity.ok(employeeService.getEmployee(id));
    }

    /**
     * Retrieves all employees with pagination.
     *
     * @param page page number to retrieve
     * @param size number of employees per page
     * @return response entity containing a page of employee data transfer objects
     */
    @GetMapping("/get")
    public ResponseEntity<Page<EmployeeDTO>> getAllEmployees(
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "size", defaultValue = "5") Integer size
    ) {
        return ResponseEntity.ok(employeeService.getAllEmployees(page, size));
    }
}
