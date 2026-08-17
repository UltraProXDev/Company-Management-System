package com.example.project.controller;

import com.example.project.dto.PayrollRequest;
import com.example.project.dto.PayrollResponse;
import com.example.project.service.PayrollService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payroll")
@RequiredArgsConstructor
public class PayrollController {

    private final PayrollService payrollService;

    @PostMapping("/create")
    public ResponseEntity<PayrollResponse> createPayroll(@Valid @RequestBody PayrollRequest payrollRequest){
        return ResponseEntity.ok(payrollService.createPayroll(payrollRequest));
    }

    @GetMapping("/read")
    public ResponseEntity<PayrollResponse> readPayroll(@RequestParam Long id){
        return ResponseEntity.ok(payrollService.readPayroll(id));
    }

    @GetMapping("/read/all")
    public ResponseEntity<List<PayrollResponse>> readAllPayroll(){
        List<PayrollResponse> readAllPayroll = payrollService.readAllPayroll();
        return ResponseEntity.ok(readAllPayroll);
    }

    @PutMapping("/update")
    public ResponseEntity<PayrollResponse> updatePayroll(@RequestParam Long id, @Valid @RequestBody PayrollRequest payrollRequest){
        return ResponseEntity.ok(payrollService.updatePayroll(id, payrollRequest));
    }
}
