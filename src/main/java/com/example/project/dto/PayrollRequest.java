package com.example.project.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PayrollRequest {
    @NotNull(message = "Employee ID is required.")
    @Positive(message = "Employee ID should be positive.")
    private Long employeeId;

    @NotBlank(message = "Month is required.")
    @Size(min = 3, max = 9)
    private String month;

    @NotNull(message = "Basic Salary is required.")
    @Positive(message = "Salary should be positive.")
    private double basicSalary;

    @NotNull(message = "Allowance is required.")
    @Positive(message = "Allowance should be positive.")
    private double allowance;

    @NotNull(message = "Deduction is required.")
    @Positive(message = "Deduction should be positive.")
    private double deduction;
}
