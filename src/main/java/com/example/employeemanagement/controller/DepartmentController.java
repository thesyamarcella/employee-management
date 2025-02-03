package com.example.employeemanagement.controller;

import com.example.employeemanagement.dto.DepartmentDTO;
import com.example.employeemanagement.service.DepartmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
@Tag(name = "Department Controller", description = "Manage Department Data")
public class DepartmentController {

    private static final Logger logger = LoggerFactory.getLogger(DepartmentController.class);

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping
    @Operation(summary = "Create new department")
    public DepartmentDTO createDepartment(@RequestBody DepartmentDTO departmentDTO) {
        logger.info("Received request to create department: {}", departmentDTO.getName());
        DepartmentDTO savedDepartment = departmentService.saveDepartment(departmentDTO);
        logger.info("Department created with ID: {}", savedDepartment.getId());
        return savedDepartment;
    }

    @GetMapping
    @Operation(summary = "Get all departments")
    public List<DepartmentDTO> getAllDepartments() {
        logger.info("Received request to get all departments");
        List<DepartmentDTO> departments = departmentService.getAllDepartments();
        logger.info("Returning {} departments", departments.size());
        return departments;
    }
}
