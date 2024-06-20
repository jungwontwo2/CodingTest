package com.gonet.codingtest.service;

import com.gonet.codingtest.domain.entity.Department;
import com.gonet.codingtest.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentService {
    private final DepartmentRepository departmentRepository;

    public List<Department> getAllDepartments(){
        return departmentRepository.findAll();
    }

    public Department findDepartmentById(Long departmentId) {
        return departmentRepository.findById(departmentId).orElse(null);
    }
}
