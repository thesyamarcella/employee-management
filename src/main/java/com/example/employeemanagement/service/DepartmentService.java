package com.example.employeemanagement.service;

import com.example.employeemanagement.dto.DepartmentDTO;
import com.example.employeemanagement.dto.EmployeeDTO;
import com.example.employeemanagement.model.Department;
import com.example.employeemanagement.repository.DepartmentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentService {

    private static final Logger logger = LoggerFactory.getLogger(DepartmentService.class);

    private final DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public List<DepartmentDTO> getAllDepartments() {
        logger.info("Fetching all departments");
        List<Department> departments = departmentRepository.findAll();
        logger.info("Found {} departments", departments.size());
        return departments.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public DepartmentDTO saveDepartment(DepartmentDTO departmentDTO) {
        logger.info("Saving new department: {}", departmentDTO.getName());
        Department department = new Department();
        department.setName(departmentDTO.getName());
        department = departmentRepository.save(department);
        logger.info("Department saved successfully with ID: {}", department.getId());
        return convertToDTO(department);
    }

    private DepartmentDTO convertToDTO(Department department) {
        List<EmployeeDTO> employeeDTOs = department.getEmployees().stream()
                .map(employee -> new EmployeeDTO(employee.getId(), employee.getName(), employee.getEmail()))
                .collect(Collectors.toList());
        return new DepartmentDTO(department.getId(), department.getName(), employeeDTOs);
    }
}
