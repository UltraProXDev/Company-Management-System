package com.example.project.mapper;

import com.example.project.dto.PayrollRequest;
import com.example.project.dto.PayrollResponse;
import com.example.project.model.Payroll;
import org.springframework.stereotype.Component;

@Component
public class PayrollMapper {
    public Payroll payrollRequestMap(PayrollRequest payrollRequest, Payroll payroll){
        payroll.setEmployeeId(payrollRequest.getEmployeeId());
        payroll.setMonth(payrollRequest.getMonth());
        payroll.setBasicSalary(payrollRequest.getBasicSalary());
        payroll.setAllowance(payrollRequest.getAllowance());
        payroll.setDeduction(payrollRequest.getDeduction());
        return payroll;
    }

    public PayrollResponse payrollResponseMap(Payroll payroll){
        return new PayrollResponse(
                payroll.getId(),
                payroll.getEmployeeId(),
                payroll.getMonth(),
                payroll.getBasicSalary(),
                payroll.getAllowance(),
                payroll.getDeduction(),
                payroll.getCreatedAt()
        );
    }
}
