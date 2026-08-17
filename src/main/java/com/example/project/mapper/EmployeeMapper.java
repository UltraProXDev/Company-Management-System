package com.example.project.mapper;

import com.example.project.dto.EmployeeRequest;
import com.example.project.dto.EmployeeResponse;
import com.example.project.model.Employee;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {
    public Employee employeeRequestMap(EmployeeRequest employeeRequest, Employee employee){
        employee.setName(employeeRequest.getName());
        employee.setPhone(employeeRequest.getPhone());
        employee.setEmail(employeeRequest.getEmail());
        employee.setUsername(employeeRequest.getUsername());
        employee.setPassword(employeeRequest.getPassword());
        employee.setSalary(employeeRequest.getSalary());
        employee.setDepartmentId(employeeRequest.getDepartmentId());
        return employee;
    }

    public EmployeeResponse employeeResponseMap(Employee employee){
        return new EmployeeResponse(
                employee.getId(),
                employee.getName(),
                employee.getPhone(),
                employee.getEmail(),
                employee.getUsername(),
                employee.getSalary(),
                employee.getDepartmentId(),
                employee.getCreatedAt()
        );
    }
}
