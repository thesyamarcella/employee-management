package com.example.employeemanagement.dto;

import java.util.List;

public class DepartmentDTO {

    private Long id;
    private String name;
    private List<EmployeeDTO> employees;

    // Constructor
    public DepartmentDTO(Long id, String name, List<EmployeeDTO> employees) {
        this.id = id;
        this.name = name;
        this.employees = employees;
    }

    // Getter dan Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<EmployeeDTO> getEmployees() {
        return employees;
    }

    public void setEmployees(List<EmployeeDTO> employees) {
        this.employees = employees;
    }
}
