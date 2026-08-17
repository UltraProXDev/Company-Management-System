package com.example.project.controller;

import com.example.project.dto.EmployeeRequest;
import com.example.project.dto.EmployeeResponse;
import com.example.project.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping("/create")
    public ResponseEntity<EmployeeResponse> createEmployee(@Valid @RequestBody EmployeeRequest employeeRequest){
        return ResponseEntity.ok(employeeService.createEmployee(employeeRequest));
    }

    @GetMapping("/read")
    public ResponseEntity<EmployeeResponse> readEmployee(@RequestParam Long id){
        return ResponseEntity.ok(employeeService.readEmployee(id));
    }

    @GetMapping("/read/all")
    public ResponseEntity<List<EmployeeResponse>> readAllEmployee(){
        List<EmployeeResponse> readEmployee = employeeService.readAllEmployee();
        return ResponseEntity.ok(readEmployee);
    }

    @PutMapping("/update")
    public ResponseEntity<EmployeeResponse> updateEmployee(@RequestParam Long id, @Valid @RequestBody EmployeeRequest employeeRequest) {
        return ResponseEntity.ok(employeeService.updateEmployee(id, employeeRequest));
    }

    @DeleteMapping("/delete")
    public String deleteEmployee(@RequestParam Long id){
        return employeeService.deleteEmployee(id);
    }

    @PatchMapping("/soft/delete")
    public ResponseEntity<EmployeeResponse> softDeteteEmployee(@RequestParam Long id){
        return ResponseEntity.ok(employeeService.softDeleteEmployee(id));
    }

    @PatchMapping("/recovery")
    public ResponseEntity<EmployeeResponse> recoveryEmployee(@RequestParam Long id){
        return ResponseEntity.ok(employeeService.recoveryEmployee(id));
    }

}
