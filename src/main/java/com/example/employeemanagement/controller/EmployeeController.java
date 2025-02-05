package com.example.employeemanagement.controller;

import com.example.employeemanagement.dto.EmployeeDTO;
import com.example.employeemanagement.exception.ResourceNotFoundException;
import com.example.employeemanagement.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
@Tag(name = "Employee Controller", description = "Manajemen Employee Data")
public class EmployeeController {

    private static final Logger logger = LogManager.getLogger(EmployeeController.class); // Logger

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    @Operation(summary = "Create a new employee")
    public EmployeeDTO createEmployee(@RequestBody EmployeeDTO employeeDTO) {
        logger.info("Membuat employee baru dengan nama: {}", employeeDTO.getName());
        EmployeeDTO createdEmployee = employeeService.createEmployee(employeeDTO);
        logger.info("Employee berhasil dibuat dengan ID: {}", createdEmployee.getId());
        return createdEmployee;
    }

    @GetMapping("/department/{departmentId}")
    @Operation(summary = "Get all employees by department")
    public List<EmployeeDTO> getEmployeesByDepartment(@PathVariable Long departmentId) {
        logger.info("Mengambil employees dari department dengan ID: {}", departmentId);
        List<EmployeeDTO> employees = employeeService.getEmployeesByDepartment(departmentId);
        logger.info("Berhasil mengambil {} employees dari department ID: {}", employees.size(), departmentId);
        return employees;
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get employee by ID")
    public EmployeeDTO getEmployeeById(@PathVariable Long id) {
        logger.info("Mencari employee dengan ID: {}", id);
        EmployeeDTO employee = employeeService.getEmployeeById(id);
        logger.info("Employee ditemukan: {}", employee.getName());
        return employee;
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleDepartmentNotFound(ResourceNotFoundException ex) {
        logger.error("Error: {}", ex.getMessage());
        return ResponseEntity.status(404).body(ex.getMessage());
    }
}
