package com.example.employeemanagement.repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.example.employeemanagement.model.Employee;
import java.util.List;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {

    List<Employee> findAll();

    @Query("SELECT e FROM Employee e JOIN FETCH e.department WHERE e.department.id = :departmentId")
    List<Employee> findByDepartment(Long departmentId);
}
