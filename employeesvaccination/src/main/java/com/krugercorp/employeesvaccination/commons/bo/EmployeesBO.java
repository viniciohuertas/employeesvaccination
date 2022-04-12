package com.krugercorp.employeesvaccination.commons.bo;

import com.krugercorp.employeesvaccination.commons.request.EmployeePatchReq;
import com.krugercorp.employeesvaccination.commons.request.EmployeePostReq;
import com.krugercorp.employeesvaccination.commons.request.EmployeePutReq;
import com.krugercorp.employeesvaccination.commons.response.EmployeePostRes;
import com.krugercorp.employeesvaccination.entity.Employee;
import com.krugercorp.employeesvaccination.entity.Role;
import com.krugercorp.employeesvaccination.entity.Users;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class EmployeesBO {
	
	private final PasswordEncoder passwordEncoder;
		
	@Autowired
    public EmployeesBO(PasswordEncoder passwordEncoder) {
		super();
		this.passwordEncoder = passwordEncoder;
	}

	public Employee employeePostReqToEmployee(Employee employee, EmployeePostReq employeePostReq) {
        employee.setIdentification(employeePostReq.getIdentification());
        employee.setFirstname(employeePostReq.getFirstname());
        employee.setLastname(employeePostReq.getLastname());
        employee.setEmail(employeePostReq.getEmail());
        employee.setBirthdate(null);
        employee.setAddress("");
        employee.setCellphone("");
        employee.setVaccinationStatus(false);
        return employee;
    }

    public EmployeePostRes employeeToEmployePostRes(Employee employee) {
        EmployeePostRes empPostRes = new EmployeePostRes();
        empPostRes.setEmployee(employee);
        return empPostRes;
    }

	public Employee patchEmployee(Employee employee, EmployeePatchReq empPatchReq) {
		employee.setBirthdate(empPatchReq.getBirthdate());
		employee.setAddress(empPatchReq.getAddress());
		employee.setCellphone(empPatchReq.getCellphone());
		employee.setVaccinationStatus(empPatchReq.getVaccinationStatus());
		return employee;
	}

	public Employee putEmployee(Employee employee, EmployeePutReq employeePutReq) {
		employee.setIdentification(employeePutReq.getIdentification());
        employee.setFirstname(employeePutReq.getFirstname());
        employee.setLastname(employeePutReq.getLastname());
        employee.setEmail(employeePutReq.getEmail());
        employee.setBirthdate(employeePutReq.getBirthdate());
		employee.setAddress(employeePutReq.getAddress());
		employee.setCellphone(employeePutReq.getCellphone());
		employee.setVaccinationStatus(employeePutReq.getVaccinationStatus());
		return employee;
	}
	
	public Users createUser(EmployeePostReq empReq) {
		Users user = new Users();		
		user.setUsername(empReq.getIdentification());
		user.setPassword(this.passwordEncoder.encode(empReq.getIdentification()));
		user.setEnabled(true);
    	return user;
	}
	
	public Role createRole(Users user) {
		Role rol = new Role();
		rol.setAuthority("EMPLOYEE");
    	rol.setUsers(user);
    	return rol;
	}
}
