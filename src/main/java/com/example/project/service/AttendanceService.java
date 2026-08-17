package com.example.project.service;


import com.example.project.dto.AttendanceRequest;
import com.example.project.dto.AttendanceResponse;
import com.example.project.mapper.AttendanceMapper;
import com.example.project.model.Attendance;
import com.example.project.repository.AttendanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final AttendanceMapper attendanceMapper;

    public AttendanceResponse createAttendance(AttendanceRequest attendanceRequest){
        Attendance attendance =new Attendance();
        Attendance savedAttendance = attendanceRepository.save(attendanceMapper.attendanceRequestMap(attendanceRequest, attendance));
        return attendanceMapper.attendanceResponseMap(savedAttendance);
    }

    public AttendanceResponse readAttendance(Long id){
        Attendance readAttendance = attendanceRepository.findById(id).orElseThrow(() -> new RuntimeException("Attendance not found."));
        return attendanceMapper.attendanceResponseMap(readAttendance);
    }

    public List<AttendanceResponse> readAllAttendance(){
        List<Attendance> readAllAttendance = attendanceRepository.findAll();
        return readAllAttendance.stream().map(attendance -> attendanceMapper.attendanceResponseMap(attendance)).toList();
    }

    public AttendanceResponse updateAttendance(Long id, AttendanceRequest attendanceRequest){
        Attendance updateAttendance = attendanceRepository.findById(id).orElseThrow(() -> new RuntimeException("Attendance not found."));
        Attendance updatedAttendance = attendanceRepository.save(attendanceMapper.attendanceRequestMap(attendanceRequest, updateAttendance));
        return attendanceMapper.attendanceResponseMap(updatedAttendance);
    }
}
