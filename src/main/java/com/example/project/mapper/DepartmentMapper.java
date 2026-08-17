package com.example.project.mapper;

import com.example.project.dto.DepartmentRequest;
import com.example.project.dto.DepartmentResponse;
import com.example.project.model.Department;
import org.springframework.stereotype.Component;

@Component
public class DepartmentMapper {
    public Department departmentRequestMap(DepartmentRequest departmentRequest, Department department){
        department.setName(departmentRequest.getName());
        department.setDescription(departmentRequest.getDescription());
        department.setManagerName(departmentRequest.getManagerName());
        department.setLocation(departmentRequest.getLocation());
        return department;
    }

    public DepartmentResponse departmentResponseMap(Department department){
        return new DepartmentResponse(
                department.getId(),
                department.getName(),
                department.getDescription(),
                department.getManagerName(),
                department.getLocation(),
                department.getCreatedAt()
        );
    }
}
