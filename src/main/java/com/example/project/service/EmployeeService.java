package com.example.project.service;


import com.example.project.dto.EmployeeRequest;
import com.example.project.dto.EmployeeResponse;
import com.example.project.mapper.EmployeeMapper;
import com.example.project.model.Employee;
import com.example.project.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    public EmployeeResponse createEmployee(EmployeeRequest employeeRequest){
        Employee employee = new Employee();
        Employee savedEmployee = employeeRepository.save(employeeMapper.employeeRequestMap(employeeRequest, employee));
        return employeeMapper.employeeResponseMap(savedEmployee);
    }

    public EmployeeResponse readEmployee(Long id){
        Employee readEmployee = employeeRepository.findByIdAndIsDeletedFalse(id).orElseThrow(() -> new RuntimeException("Employee not found."));
        return employeeMapper.employeeResponseMap(readEmployee);
    }

    public List<EmployeeResponse> readAllEmployee() {
        List<Employee> readAllEmployee = employeeRepository.findByIsDeletedFalse();
        return readAllEmployee.stream().map(employee -> employeeMapper.employeeResponseMap(employee)).toList();
    }

    public EmployeeResponse updateEmployee(Long id, EmployeeRequest employeeRequest){
        Employee updateEmployee = employeeRepository .findByIdAndIsDeletedFalse(id) .orElseThrow(() -> new RuntimeException("Employee not found."));
        Employee updatedEmployee = employeeRepository.save(employeeMapper.employeeRequestMap(employeeRequest, updateEmployee));
        return employeeMapper.employeeResponseMap(updatedEmployee);
    }

    public String deleteEmployee(Long id){
        if(employeeRepository.existsById(id)){
            employeeRepository.deleteById(id);
            return "Employee Deleted Successfully.";
        }
        return "Employee Not Found.";
    }

    public EmployeeResponse softDeleteEmployee(Long id){
        Employee softDeleteEmployee = employeeRepository.findByIdAndIsDeletedFalse(id).orElseThrow(() -> new RuntimeException("Employee not found or already deleted."));
        softDeleteEmployee.setIsDeleted(true);
        Employee softDeletedEmployee = employeeRepository.save(softDeleteEmployee);
        return employeeMapper.employeeResponseMap(softDeletedEmployee);
    }

    public EmployeeResponse recoveryEmployee(Long id){
        Employee recoveryEmployee = employeeRepository.findByIdAndIsDeletedTrue(id).orElseThrow(() -> new RuntimeException("Employee not created or not deleted."));
        recoveryEmployee.setIsDeleted(false);
        Employee recoveredEmployee = employeeRepository.save(recoveryEmployee);
        return employeeMapper.employeeResponseMap(recoveredEmployee);
    }
}
