package com.example.project.service;

import com.example.project.dto.PayrollRequest;
import com.example.project.dto.PayrollResponse;
import com.example.project.mapper.PayrollMapper;
import com.example.project.model.Payroll;
import com.example.project.repository.PayrollRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PayrollService {

    private final PayrollRepository payrollRepository;
    private final PayrollMapper payrollMapper;

    public PayrollResponse createPayroll(PayrollRequest payrollRequest){
        Payroll payroll = new Payroll();
        Payroll savedPayroll = payrollRepository.save(payrollMapper.payrollRequestMap(payrollRequest, payroll));
        return payrollMapper.payrollResponseMap(savedPayroll);
    }

    public PayrollResponse readPayroll(Long id){
        Payroll readPayroll = payrollRepository.findById(id).orElseThrow(() -> new RuntimeException("Project not found."));
        return payrollMapper.payrollResponseMap(readPayroll);
    }

    public List<PayrollResponse> readAllPayroll(){
        List<Payroll> readAllPayroll = payrollRepository.findAll();
        return readAllPayroll.stream().map(payroll -> payrollMapper.payrollResponseMap(payroll)).toList();
    }

    public PayrollResponse updatePayroll(Long id, PayrollRequest payrollRequest){
        Payroll updatePayroll = payrollRepository.findById(id).orElseThrow(() -> new RuntimeException("Payrole not found."));
        Payroll updatedPayroll = payrollRepository.save(payrollMapper.payrollRequestMap(payrollRequest, updatePayroll));
        return payrollMapper.payrollResponseMap(updatedPayroll);
    }
}
