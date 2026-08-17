package com.example.project.mapper;

import com.example.project.dto.AttendanceRequest;
import com.example.project.dto.AttendanceResponse;
import com.example.project.model.Attendance;
import org.springframework.stereotype.Component;

@Component
public class AttendanceMapper {
    public Attendance attendanceRequestMap(AttendanceRequest attendanceRequest, Attendance attendance){
        attendance.setEmployeeId(attendanceRequest.getEmployeeId());
        attendance.setDate(attendanceRequest.getDate());
        attendance.setCheckIn(attendanceRequest.getCheckIn());
        attendance.setCheckOut(attendanceRequest.getCheckOut());
        attendance.setStatus(attendanceRequest.getStatus());
        return attendance;
    }

    public AttendanceResponse attendanceResponseMap(Attendance attendance){
        return new AttendanceResponse(
                attendance.getId(),
                attendance.getEmployeeId(),
                attendance.getDate(),
                attendance.getCheckIn(),
                attendance.getCheckOut(),
                attendance.getStatus(),
                attendance.getCreatedAt()
        );
    }
}
