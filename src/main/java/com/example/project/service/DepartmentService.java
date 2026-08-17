package com.example.project.service;

import com.example.project.dto.DepartmentRequest;
import com.example.project.dto.DepartmentResponse;
import com.example.project.mapper.DepartmentMapper;
import com.example.project.model.Department;
import com.example.project.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final DepartmentMapper departmentMapper;

    public DepartmentResponse createDepartment(DepartmentRequest departmentRequest){
        Department department = new Department();
        Department savedDepartment = departmentRepository.save(departmentMapper.departmentRequestMap(departmentRequest, department));
        return departmentMapper.departmentResponseMap(savedDepartment);
    }

    public DepartmentResponse readDepartment(Long id){
        Department readDepartment = departmentRepository.findByIdAndIsDeletedFalse(id).orElseThrow(() -> new RuntimeException("Department not found."));
        return departmentMapper.departmentResponseMap(readDepartment);
    }

    public List<DepartmentResponse> readAllDepartment(){
        List<Department> readAllDepartment = departmentRepository.findByIsDeletedFalse();
        return readAllDepartment.stream().map(department -> departmentMapper.departmentResponseMap(department)).toList();
    }

    public DepartmentResponse updateDepartment(Long id, DepartmentRequest departmentRequest){
        Department updateDepartment = departmentRepository.findByIdAndIsDeletedFalse(id).orElseThrow(() -> new RuntimeException("Department not found."));
        Department updatedDepartment = departmentRepository.save(departmentMapper.departmentRequestMap(departmentRequest, updateDepartment));
        return departmentMapper.departmentResponseMap(updatedDepartment);
    }

    public String deleteDepartment(Long id){
        if(departmentRepository.existsById(id)){
            departmentRepository.deleteById(id);
            return "Department Deleted Successfully.";
        }
        return "Department Not Found.";
    }

    public DepartmentResponse softDeleteDepartment(Long id) {
        Department softDeleteDepartment = departmentRepository.findByIdAndIsDeletedFalse(id).orElseThrow(() -> new RuntimeException("Department not found or already deleted."));
        softDeleteDepartment.setIsDeleted(true);
        Department softDeletedDepartment = departmentRepository.save(softDeleteDepartment);
        return departmentMapper.departmentResponseMap(softDeletedDepartment);
    }

    public DepartmentResponse recoveryDepartment(Long id){
        Department recoveryDepartment = departmentRepository.findByIdAndIsDeletedTrue(id).orElseThrow(() -> new RuntimeException("Department not exist or not deleted."));
        recoveryDepartment.setIsDeleted(false);
        Department recoveredDepartment = departmentRepository.save(recoveryDepartment);
        return departmentMapper.departmentResponseMap(recoveredDepartment);
    }
}
