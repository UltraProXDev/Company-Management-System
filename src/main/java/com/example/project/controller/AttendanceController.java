package com.example.project.controller;


import com.example.project.dto.AttendanceRequest;
import com.example.project.dto.AttendanceResponse;
import com.example.project.service.AttendanceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/attendance")
@RequiredArgsConstructor
public class AttendanceController {

    private final AttendanceService attendanceService;

    @PostMapping("/create")
    public ResponseEntity<AttendanceResponse> createAttendance(@Valid @RequestBody AttendanceRequest attendanceRequest){
        return ResponseEntity.ok(attendanceService.createAttendance(attendanceRequest));
    }

    @GetMapping("/read")
    public ResponseEntity<AttendanceResponse> readAttendance(@RequestParam Long id){
        return ResponseEntity.ok(attendanceService.readAttendance(id));
    }

    @GetMapping("/read/all")
    public ResponseEntity<List<AttendanceResponse>> readAllAttendance(){
        List<AttendanceResponse>  readAllAttendance= attendanceService.readAllAttendance();
        return ResponseEntity.ok(readAllAttendance);
    }

    @PutMapping("/update")
    public ResponseEntity<AttendanceResponse> updateAttendance(@RequestParam Long id, @Valid @RequestBody AttendanceRequest attendanceRequest){
        return ResponseEntity.ok(attendanceService.updateAttendance(id, attendanceRequest));
    }
}
