package com.example.project.repository;

import com.example.project.dto.AdminRequest;
import com.example.project.dto.AdminResponse;
import com.example.project.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
    Optional<Admin> findByIdAndIsDeletedFalse(Long id);
    Optional<Admin> findByIdAndIsDeletedTrue(Long id);
    List<Admin> findByIsDeletedFalse();
    Optional<Admin> findByUsernameAndIsDeletedFalse(String username);
}
