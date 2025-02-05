package com.example.employeemanagement.controller;

import com.example.employeemanagement.dto.DepartmentDTO;
import com.example.employeemanagement.exception.ResourceNotFoundException;
import com.example.employeemanagement.service.DepartmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
@Tag(name = "Department Controller", description = "Manajemen Department Data")
public class DepartmentController {

    private static final Logger logger = LogManager.getLogger(DepartmentController.class);
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping
    @Operation(summary = "Create a new department")
    public DepartmentDTO createDepartment(@RequestBody DepartmentDTO departmentDTO) {
        logger.info("Membuat department baru dengan nama: {}", departmentDTO.getName());
        DepartmentDTO createdDepartment = departmentService.createDepartment(departmentDTO);
        logger.info("Department berhasil dibuat dengan ID: {}", createdDepartment.getId());
        return createdDepartment;
    }

    @GetMapping
    @Operation(summary = "Get all departments")
    public List<DepartmentDTO> getAllDepartments() {
        logger.info("Mengambil semua department...");
        List<DepartmentDTO> departments = departmentService.getAllDepartments();
        logger.info("Berhasil mengambil {} department.", departments.size());
        return departments;
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get department by ID")
    public DepartmentDTO getDepartmentById(@PathVariable Long id) {
        logger.info("Mencari department dengan ID: {}", id);
        return departmentService.getDepartmentById(id);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleDepartmentNotFound(ResourceNotFoundException ex) {
        logger.error("Error: {}", ex.getMessage());
        return ResponseEntity.status(404).body(ex.getMessage());
    }
}
