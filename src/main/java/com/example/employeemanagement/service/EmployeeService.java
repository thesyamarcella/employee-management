package com.example.employeemanagement.service;

import com.example.employeemanagement.model.Employee;
import com.example.employeemanagement.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeService.class);
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getEmployeesByDepartment(Long departmentId) {
        logger.info("Fetching employees for department ID: {}", departmentId);
        return employeeRepository.findByDepartment(departmentId);
    }

    public Optional<Employee> getEmployeeById(Long id) {
        logger.info("Fetching employee by ID: {}", id);
        return employeeRepository.findById(id);
    }

    public Employee createEmployee(Employee employee) {
        logger.info("Creating employee: {}", employee);
        return employeeRepository.save(employee);
    }
}
