package com.example.control1.mapper;

import com.example.control1.dto.employee.EmployeeCreateDTO;
import com.example.control1.dto.employee.EmployeeCreateResponseDTO;
import com.example.control1.dto.employee.EmployeeDTO;
import com.example.control1.entity.Employee;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    Employee toEmployee(EmployeeCreateDTO employeeCreateDTO);
    EmployeeCreateResponseDTO toCreateResponseDTO(Employee employee);
    EmployeeDTO toDTO(Employee employee);
}
