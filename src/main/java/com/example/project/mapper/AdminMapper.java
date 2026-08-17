package com.example.project.mapper;

import com.example.project.dto.AdminRequest;
import com.example.project.dto.AdminResponse;
import com.example.project.model.Admin;
import org.springframework.stereotype.Component;

@Component
public class AdminMapper {
    public Admin adminRequestMap(AdminRequest adminRequest, Admin admin){
        admin.setName(adminRequest.getName());
        admin.setPhone(adminRequest.getPhone());
        admin.setUsername(adminRequest.getUsername());
        admin.setEmail(adminRequest.getEmail());
        admin.setPassword(adminRequest.getPassword());
        return admin;
    }

    public AdminResponse adminResponseMap(Admin admin){
        return new AdminResponse(
                admin.getId(),
                admin.getName(),
                admin.getPhone(),
                admin.getUsername(),
                admin.getEmail(),
                admin.getCreatedAt()
        );
    }
}
