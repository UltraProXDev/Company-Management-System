package com.example.project.repository;

import com.example.project.model.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
//    Optional<Attendance> findByIdAndDeletedIsFalse(Long id);
//    List<Attendance> findByDeletedIsFalse();
    @Query(""" 
            SELECT COUNT(a)
            FROM Attendance a
            WHERE a.date = :date
            AND a.status = 'PRESENT'
            """)
    long countPresent(@Param("date") LocalDate date);

    @Query("""
           SELECT COUNT(a)
           FROM Attendance a
           WHERE a.date = :date
           AND a.status = 'ABSENT'
           """)
    long countAbsent(@Param("date") LocalDate date);
}
