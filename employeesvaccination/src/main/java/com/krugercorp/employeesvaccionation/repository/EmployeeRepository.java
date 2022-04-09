package com.krugercorp.employeesvaccionation.repository;

import com.krugercorp.employeesvaccionation.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
