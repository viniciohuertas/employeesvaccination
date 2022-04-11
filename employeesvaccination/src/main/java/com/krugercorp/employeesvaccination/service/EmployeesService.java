package com.krugercorp.employeesvaccination.service;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import com.krugercorp.employeesvaccination.commons.exception.CustomValidationException;
import com.krugercorp.employeesvaccination.commons.request.EmployeePatchReq;
import com.krugercorp.employeesvaccination.commons.request.EmployeePostReq;
import com.krugercorp.employeesvaccination.commons.request.EmployeePutReq;
import com.krugercorp.employeesvaccination.entity.Employee;

public interface EmployeesService {
    Employee postEmployees(EmployeePostReq employeePostReq);

    Employee getEmployee(Integer id) throws CustomValidationException;

	List<Employee> getEmployees();

	Employee patchEmployee(Integer id, EmployeePatchReq employeePatchReq) throws CustomValidationException;

	void deleteEmployee(Integer id) throws CustomValidationException;

	Employee putEmployee(Integer id, EmployeePutReq employeePutReq) throws CustomValidationException;

	List<Employee> getEmployeesFilter(Boolean vaccinationStatus, String typeVaccine, LocalDate initialDate, LocalDate finalDate);
}
