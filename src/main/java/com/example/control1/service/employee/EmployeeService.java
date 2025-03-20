package com.example.control1.service.employee;

import com.example.control1.dto.common.CreateResponseDTO;
import com.example.control1.dto.employee.EmployeeCreateDTO;
import com.example.control1.dto.employee.EmployeeDTO;
import org.springframework.data.domain.Page;

/**
 * Service interface for managing employees.
 */
public interface EmployeeService {

    /**
     * Creates a new employee.
     *
     * @param employeeCreateDTO data transfer object containing employee details
     * @return response containing the ID of the created employee
     */
    CreateResponseDTO createEmployee(EmployeeCreateDTO employeeCreateDTO);

    /**
     * Retrieves an employee by its ID.
     *
     * @param id ID of the employee
     * @return employee data transfer object
     */
    EmployeeDTO getEmployee(String id);

    /**
     * Retrieves all employees with pagination.
     *
     * @param page page number to retrieve
     * @param size number of employees per page
     * @return a paginated list of employee data transfer objects
     */
    Page<EmployeeDTO> getAllEmployees(Integer page, Integer size);
}
