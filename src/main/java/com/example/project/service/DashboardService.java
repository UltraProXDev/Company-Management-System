package com.example.project.service;

import com.example.project.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final AttendanceRepository attendanceRepository;
    private final DepartmentRepository departmentRepository;
    private final EmployeeRepository employeeRepository;
    private final PayrollRepository payrollRepository;
    private final ProjectRepository projectRepository;

    public Map<String, Object> getDashboard() {
        LocalDate today = LocalDate.now();
        long employees = employeeRepository.countByIsDeletedFalse();
        long departments = departmentRepository.countByIsDeletedFalse();
        long projects = projectRepository.countByIsDeletedFalse();
        Double payrolls = payrollRepository.getTotalPayrolls();
        long present = attendanceRepository.countPresent(today);
        long absent = attendanceRepository.countAbsent(today);

        Map<String, Object> dashboard = new HashMap<>();
        dashboard.put("Employees", employees);
        dashboard.put("Departments", departments);
        dashboard.put("Projects", projects);
        dashboard.put("Payrolls", payrolls);
        dashboard.put("Present", present);
        dashboard.put("Absent", absent);
        return dashboard;
    }
}
