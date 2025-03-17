package com.example.control1.service.employee;

import com.example.control1.dto.employee.EmployeeCreateDTO;
import com.example.control1.dto.employee.EmployeeCreateResponseDTO;
import com.example.control1.dto.employee.EmployeeDTO;
import org.springframework.data.domain.Page;

public interface EmployeeService {
    EmployeeCreateResponseDTO createEmployee(EmployeeCreateDTO employeeCreateDTO);
    EmployeeDTO getEmployee(String id);
    Page<EmployeeDTO> getAllEmployees(Integer page, Integer size);
}
