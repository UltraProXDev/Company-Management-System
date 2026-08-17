package com.example.project.repository;

import com.example.project.model.Payroll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PayrollRepository extends JpaRepository<Payroll, Long> {
//    Optional<Payroll> findByIdAndDeletedIsFalse(Long id);
//    List<Payroll> findByDeletedIsFalse();
    @Query("""
        SELECT COALESCE(
            SUM(p.basicSalary + p.allowance - p.deduction), 
            0
        )
        FROM Payroll p
    """)
    Double getTotalPayrolls();
}
