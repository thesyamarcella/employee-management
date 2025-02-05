package com.example.employeemanagement.service;

import com.example.employeemanagement.dto.EmployeeDTO;
import com.example.employeemanagement.exception.ResourceNotFoundException;
import com.example.employeemanagement.model.Department;
import com.example.employeemanagement.model.Employee;
import com.example.employeemanagement.repository.DepartmentRepository;
import com.example.employeemanagement.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    public EmployeeService(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
    }

    // Get all employees by department ID
    public List<EmployeeDTO> getEmployeesByDepartment(Long departmentId) {
        // Cek apakah department ada
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found with ID: " + departmentId));

        // Jika department ditemukan, cari employee berdasarkan department
        List<Employee> employees = employeeRepository.findByDepartment(departmentId);
        return employees.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Get employee by ID
    public EmployeeDTO getEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with ID: " + id));
        return convertToDTO(employee);
    }

    // Create new employee
    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
        Department department = departmentRepository.findByName(employeeDTO.getDepartmentName())
                .orElseThrow(() -> new ResourceNotFoundException("Department not found: " + employeeDTO.getDepartmentName()));

        // Buat objek Employee
        Employee employee = new Employee();
        employee.setName(employeeDTO.getName());
        employee.setEmail(employeeDTO.getEmail());
        employee.setDepartment(department);

        // Simpan employee ke database
        Employee savedEmployee = employeeRepository.save(employee);
        return convertToDTO(savedEmployee);
    }

    // Helper method to convert Employee entity to EmployeeDTO
    private EmployeeDTO convertToDTO(Employee employee) {
        return new EmployeeDTO(
                employee.getId(),
                employee.getName(),
                employee.getEmail(),
                employee.getDepartment().getName() // Assuming the employee has a reference to Department
        );
    }
}
