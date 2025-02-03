package com.example.employeemanagement.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.example.employeemanagement.model.Employee;
import java.util.List;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {

    @Query("SELECT e FROM Employee e JOIN FETCH e.department WHERE e.id = :id")
    Employee findByIdWithDepartment(Long id);

    @Query("SELECT e FROM Employee e JOIN FETCH e.department")
    List<Employee> findAllWithDepartments();

    // Menambahkan query untuk mendapatkan semua employee berdasarkan department
    @Query("SELECT e FROM Employee e JOIN FETCH e.department d WHERE d.id = :departmentId")
    List<Employee> findByDepartment(Long departmentId);
}
