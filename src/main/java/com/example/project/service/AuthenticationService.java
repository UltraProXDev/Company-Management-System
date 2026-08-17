package com.example.project.service;

import com.example.project.dto.AuthenticationRequest;
import com.example.project.model.Admin;
import com.example.project.model.Employee;
import com.example.project.repository.AdminRepository;
import com.example.project.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final AdminRepository adminRepository;

    private final EmployeeRepository employeeRepository;

    public boolean adminLogin(AuthenticationRequest authenticationRequest) {
        Optional<Admin> existingAdmin = adminRepository.findByUsernameAndIsDeletedFalse(authenticationRequest.getUsername());
        if (existingAdmin.isPresent()) {
            return existingAdmin.get().getPassword().equals(authenticationRequest.getPassword());
        }
        return false;
    }

    public boolean employeeLogin(AuthenticationRequest authenticationRequest) {
        Optional<Employee> existingEmployee = employeeRepository.findByUsernameAndIsDeletedFalse(authenticationRequest.getUsername());
        if (existingEmployee.isPresent()) {
            return existingEmployee.get().getPassword().equals(authenticationRequest.getPassword());
        }
        return false;
    }
}
