package com.example.project.controller;

import com.example.project.dto.AuthenticationRequest;
import com.example.project.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authentication")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/admin/login")
    public boolean adminLogin(@Valid @RequestBody AuthenticationRequest authenticationRequest) {
        return authenticationService.adminLogin(authenticationRequest);
    }

    @PostMapping("/employee/login")
    public boolean employeeLogin(@Valid @RequestBody AuthenticationRequest authenticationRequest){
        return authenticationService.employeeLogin(authenticationRequest);
    }
}
