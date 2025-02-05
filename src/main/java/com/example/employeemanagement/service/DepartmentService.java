package com.example.employeemanagement.service;

import com.example.employeemanagement.dto.DepartmentDTO;
import com.example.employeemanagement.exception.ResourceNotFoundException;
import com.example.employeemanagement.model.Department;
import com.example.employeemanagement.repository.DepartmentRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentService {

    private static final Logger logger = LogManager.getLogger(DepartmentService.class);
    private final DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public List<DepartmentDTO> getAllDepartments() {
        logger.info("Mengambil semua department dari database...");
        List<Department> departments = departmentRepository.findAll();
        logger.info("Ditemukan {} department di database.", departments.size());
        return departments.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public DepartmentDTO getDepartmentById(Long id) {
        logger.info("Mencari department dengan ID: {}", id);
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Department dengan ID {} tidak ditemukan!", id);
                    return new ResourceNotFoundException("Department not found with ID: " + id);
                });
        logger.info("Department dengan ID {} ditemukan: {}", id, department.getName());
        return convertToDTO(department);
    }

    public DepartmentDTO createDepartment(DepartmentDTO departmentDTO) {
        logger.info("Membuat department baru: {}", departmentDTO.getName());
        Department department = new Department();
        department.setName(departmentDTO.getName());
        department = departmentRepository.save(department);
        logger.info("Department baru berhasil disimpan dengan ID: {}", department.getId());
        return convertToDTO(department);
    }

    private DepartmentDTO convertToDTO(Department department) {
        return new DepartmentDTO(department.getId(), department.getName(), List.of());
    }
}
