package com.example.employeemanagement.service;

import com.example.employeemanagement.model.Employee;
import com.example.employeemanagement.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    // Mendapatkan employees berdasarkan departmentId
    public List<Employee> getEmployeesByDepartment(Long departmentId) {
        return employeeRepository.findByDepartment(departmentId);
    }

    // Mendapatkan employee berdasarkan ID
    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id).orElse(null);
    }

    // Menambahkan employee baru
    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }
}
