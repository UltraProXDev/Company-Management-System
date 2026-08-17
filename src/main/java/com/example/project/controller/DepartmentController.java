package com.example.project.controller;

import com.example.project.dto.DepartmentRequest;
import com.example.project.dto.DepartmentResponse;
import com.example.project.service.DepartmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/department")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;

    @PostMapping("/create")
    public ResponseEntity<DepartmentResponse> createDepartment(@Valid @RequestBody DepartmentRequest departmentRequest){
        return ResponseEntity.ok(departmentService.createDepartment(departmentRequest));
    }

    @GetMapping("/read")
    public ResponseEntity<DepartmentResponse> readDepartment(@RequestParam Long id){
        return ResponseEntity.ok(departmentService.readDepartment(id));
    }

    @GetMapping("/read/all")
    public ResponseEntity<List<DepartmentResponse>> readAllDepartment(){
        List<DepartmentResponse> readAllDepartment = departmentService.readAllDepartment();
        return ResponseEntity.ok(readAllDepartment);
    }

    @PutMapping("/update")
    public ResponseEntity<DepartmentResponse> updateDepartment(@RequestParam Long id, @Valid @RequestBody DepartmentRequest departmentRequest){
        return ResponseEntity.ok(departmentService.updateDepartment(id, departmentRequest));
    }

    @DeleteMapping("/delete")
    public String deleteDepartment(@RequestParam Long id){
        return  departmentService.deleteDepartment(id);
    }

    @PatchMapping("/soft/delete")
    public ResponseEntity<DepartmentResponse> softDeleteDepartment(@RequestParam Long id){
        return ResponseEntity.ok(departmentService.softDeleteDepartment(id));
    }

    @PatchMapping("/recovery")
    public ResponseEntity<DepartmentResponse> recoveryDepartment(@RequestParam Long id){
        return ResponseEntity.ok(departmentService.recoveryDepartment(id));
    }
}
