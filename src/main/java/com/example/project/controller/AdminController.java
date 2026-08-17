package com.example.project.controller;

import com.example.project.dto.AdminRequest;
import com.example.project.dto.AdminResponse;
import com.example.project.service.AdminService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @PostMapping("/create")
    public ResponseEntity<AdminResponse> createAdmin(@Valid @RequestBody AdminRequest adminRequest){
        return ResponseEntity.ok(adminService.createAdmin(adminRequest));
    }

    @GetMapping("/read")
    public ResponseEntity<AdminResponse> readAdmin(@RequestParam Long id){
        return ResponseEntity.ok(adminService.readAdmin(id));
    }

    @GetMapping("/read/all")
    public ResponseEntity<List<AdminResponse>> readAllAdmin(){
        List<AdminResponse> readAllAdmin = adminService.readAllAdmin();
        return ResponseEntity.ok(readAllAdmin);
    }

    @PutMapping("/update")
    public ResponseEntity<AdminResponse> updateAdmin(@RequestParam Long id, @Valid @RequestBody AdminRequest adminRequest){
        return ResponseEntity.ok(adminService.updateAdmin(id, adminRequest));
    }

    @DeleteMapping("/delete")
    public String deleteAdmin(@RequestParam Long id){
        return adminService.deleteAdmin(id);
    }

    @PatchMapping("/soft/delete")
    public ResponseEntity<AdminResponse> softDeteteAdmin(@RequestParam Long id){
        return ResponseEntity.ok(adminService.softDeleteAdmin(id));
    }

    @PatchMapping("/recovery")
    public ResponseEntity<AdminResponse> recoveryAdmin(@RequestParam Long id){
        return ResponseEntity.ok(adminService.recoveryAdmin(id));
    }
}
