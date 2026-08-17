package com.example.project.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminRequest {
    @NotBlank(message = "Name is required.")
    private String name;

    @NotBlank(message = "Phone number is required.")
    @Size(min = 10, max = 10, message = "Phone number should be 10 digits.")
    private String phone;

    @NotBlank(message = "Username is required.")
    @Size(min = 8, message = "Username cannot be under 8 characters.")
    private String username;

    @NotBlank(message = "Email is required.")
    @Email(message = "Enter the valid Email.")
    private String email;

    @NotBlank(message = "Password is required.")
    @Size(min = 8, message = "Password cannot be under 8 characters.")
    private String password;

}
