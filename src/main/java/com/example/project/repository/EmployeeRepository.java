package com.example.project.repository;

import com.example.project.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findByIdAndIsDeletedFalse(Long id);
    Optional<Employee> findByIdAndIsDeletedTrue(Long id);
    List<Employee> findByIsDeletedFalse();
    long countByIsDeletedFalse();
    Optional<Employee> findByUsernameAndIsDeletedFalse(String username);
}
