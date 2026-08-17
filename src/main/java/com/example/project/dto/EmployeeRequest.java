package com.example.project.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeRequest {
    @NotBlank(message = "Name is required.")
    private String name;

    @NotBlank(message = "Phone number is required.")
    @Size(min = 10, max = 10, message = "Phone number should be 10 digits.")
    private String phone;

    @NotBlank(message = "Email is required.")
    @Email(message = "Enter the valid Email.")
    private String email;

    @NotBlank(message = "Username is required.")
    @Size(min = 8, message = "Username cannot be under 8 characters.")
    private String username;

    @NotBlank(message = "Password is required.")
    @Size(min = 8, message = "Password cannot be under 8 characters.")
    private String password;

    @NotNull(message = "Salary is required.")
    @Positive(message = "Salary should be positive.")
    private double salary;

    @NotNull(message = "Department ID is required.")
    @Positive(message = "Department ID should be positive.")
    private Long departmentId;
}
