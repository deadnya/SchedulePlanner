package com.example.control1.service.employee.impl;

import com.example.control1.dto.common.CreateResponseDTO;
import com.example.control1.dto.employee.EmployeeCreateDTO;
import com.example.control1.dto.employee.EmployeeDTO;
import com.example.control1.entity.Employee;
import com.example.control1.mapper.EmployeeMapper;
import com.example.control1.repository.EmployeeRepository;
import com.example.control1.service.employee.EmployeeService;
import com.example.control1.service.uuid.UUIDService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 * Implementation of the EmployeeService interface.
 * Provides functionality to manage employees.
 */
@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;
    private final UUIDService uuidService;

    /**
     * Creates a new employee.
     *
     * @param employeeCreateDTO data transfer object containing employee details
     * @return response containing the ID of the created employee
     */
    @Override
    public CreateResponseDTO createEmployee(EmployeeCreateDTO employeeCreateDTO) {

        Employee employee = employeeMapper.toEmployee(employeeCreateDTO);
        employee.setId(uuidService.getRandomUUID());

        if (employee.getPosition() == null) {
            employee.setPosition(Employee.Position.UNDEFINED);
        }

        employeeRepository.save(employee);

        return employeeMapper.toCreateResponseDTO(employee);
    }

    /**
     * Retrieves an employee by its ID.
     *
     * @param id ID of the employee
     * @return employee data transfer object
     */
    @Override
    public EmployeeDTO getEmployee(String id) {

        Employee employee = employeeRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Employee with id: %s not found", id)));

        return employeeMapper.toDTO(employee);
    }

    /**
     * Retrieves all employees with pagination.
     *
     * @param page page number to retrieve
     * @param size number of employees per page
     * @return a paginated list of employee data transfer objects
     */
    @Override
    public Page<EmployeeDTO> getAllEmployees(Integer page, Integer size) {

        Pageable pageable = PageRequest.of(page, size);

        return employeeRepository.findAll(pageable).map(employeeMapper::toDTO);
    }
}
