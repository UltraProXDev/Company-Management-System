package com.example.project.service;

import com.example.project.dto.AdminRequest;
import com.example.project.dto.AdminResponse;
import com.example.project.mapper.AdminMapper;
import com.example.project.model.Admin;
import com.example.project.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class AdminService {

    private final AdminRepository adminRepository;
    private final AdminMapper adminMapper;

    public AdminResponse createAdmin(AdminRequest adminRequest){
        Admin admin = new Admin();
        Admin savedAdmin = adminRepository.save(adminMapper.adminRequestMap(adminRequest, admin));
        return adminMapper.adminResponseMap(savedAdmin);
    }

    public AdminResponse readAdmin(Long id) {
        Admin readAdmin = adminRepository.findByIdAndIsDeletedFalse(id).orElseThrow(() -> new RuntimeException("Admin not found."));
        return adminMapper.adminResponseMap(readAdmin);
    }

    public List<AdminResponse> readAllAdmin(){
        List<Admin> readAllAdmin = adminRepository.findByIsDeletedFalse();
        return readAllAdmin.stream().map(admin -> adminMapper.adminResponseMap(admin)).toList();
    }

    public AdminResponse updateAdmin(Long id, AdminRequest adminRequest){
        Admin updateAdmin = adminRepository.findByIdAndIsDeletedFalse(id).orElseThrow(() -> new RuntimeException("Admin not found."));
        Admin updatedAdmin = adminRepository.save(adminMapper.adminRequestMap(adminRequest, updateAdmin));
        return adminMapper.adminResponseMap(updatedAdmin);
    }

    public String deleteAdmin(Long id){
        if(adminRepository.existsById(id)){
            adminRepository.deleteById(id);
            return "Admin Deleted Successfully";
        }
        return "Admin Not Found";
    }

    public AdminResponse softDeleteAdmin(Long id){
        Admin softDeleteAdmin = adminRepository.findByIdAndIsDeletedFalse(id).orElseThrow(() -> new RuntimeException("Admin not found or already deleted."));
        softDeleteAdmin.setIsDeleted(true);
        Admin softDeletedAdmin = adminRepository.save(softDeleteAdmin);
        return adminMapper.adminResponseMap(softDeletedAdmin);
    }

    public AdminResponse recoveryAdmin(Long id){
        Admin recoveryAdmin = adminRepository.findByIdAndIsDeletedTrue(id).orElseThrow(() -> new RuntimeException("Admin not existed or not deleted."));
        recoveryAdmin.setIsDeleted(false);
        Admin recoveredAdmin = adminRepository.save(recoveryAdmin);
        return adminMapper.adminResponseMap(recoveredAdmin);
    }
}
