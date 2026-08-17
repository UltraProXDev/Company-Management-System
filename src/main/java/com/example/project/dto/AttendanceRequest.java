package com.example.project.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttendanceRequest {
    @NotNull(message = "Employee ID is required.")
    @Positive(message = "Employee ID cannot be negative.")
    private Long employeeId;

    @NotNull(message = "Date is required.")
    @PastOrPresent(message = "Enter the todat's date.")
    @FutureOrPresent(message = "Enter the todat's date.")
    private LocalDate date;

    @NotNull(message = "Time is required.")
    private LocalTime checkIn;

    @NotNull(message = "Time is required.")
    private LocalTime checkOut;

    @NotBlank(message = "Status is required.")
    private String status;
}
