package com.krugercorp.employeesvaccination.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.krugercorp.employeesvaccination.entity.Employee;

@Repository
public interface EmployeeRepositoryCustom {

	List<Employee> findFilter(Boolean vaccinationStatus, String typeVaccine, LocalDate initialDate, LocalDate finalDate);

}
