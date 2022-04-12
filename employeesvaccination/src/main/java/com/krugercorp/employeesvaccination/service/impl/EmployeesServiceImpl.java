package com.krugercorp.employeesvaccination.service.impl;

import com.krugercorp.employeesvaccination.commons.bo.EmployeesBO;
import com.krugercorp.employeesvaccination.commons.enumerations.EnumResponse;
import com.krugercorp.employeesvaccination.commons.exception.CustomValidationException;
import com.krugercorp.employeesvaccination.commons.request.EmployeePatchReq;
import com.krugercorp.employeesvaccination.commons.request.EmployeePostReq;
import com.krugercorp.employeesvaccination.commons.request.EmployeePutReq;
import com.krugercorp.employeesvaccination.entity.Employee;
import com.krugercorp.employeesvaccination.entity.Role;
import com.krugercorp.employeesvaccination.entity.Users;
import com.krugercorp.employeesvaccination.repository.EmployeeRepository;
import com.krugercorp.employeesvaccination.repository.EmployeeRepositoryCustom;
import com.krugercorp.employeesvaccination.repository.RoleRepository;
import com.krugercorp.employeesvaccination.repository.UserRepository;
import com.krugercorp.employeesvaccination.service.EmployeesService;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Class: EmployeesServiceImpl.java 
 * <br>
 * Date Creation: 10/04/2022 <br>
 * 
 * @author Vinicio Huertas
 * @version 1.0.0
 * @since jdk 11
 **/

@Service
public class EmployeesServiceImpl implements EmployeesService {

    public static final Log LOG = LogFactory.getLog(EmployeesServiceImpl.class);

    private EmployeesBO employeesBO;
    private EmployeeRepository employeeRepository;
    private EmployeeRepositoryCustom employeeRepositoryCustom;
    private UserRepository userRepository;
    private RoleRepository roleRepository;

    public EmployeesServiceImpl() {
    }

    @Autowired
    public EmployeesServiceImpl(EmployeesBO employeesBO, EmployeeRepository employeeRepository, EmployeeRepositoryCustom employeeRepositoryCustom, 
    		UserRepository userRepository, RoleRepository roleRepository) {
        this.employeesBO = employeesBO;
        this.employeeRepository = employeeRepository;
        this.employeeRepositoryCustom = employeeRepositoryCustom;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    @Transactional
    public String postEmployees(EmployeePostReq empReq) {
    	Employee employee = new Employee();
    	Users user = new Users();
    	StringBuilder sb = new StringBuilder();
    	user = this.employeesBO.createUser(empReq);
    	user = this.userRepository.save(user);
    	sb.append("Se creo el usuario: ");
    	sb.append(user.getUsername());
    	sb.append(", con password: ");
    	sb.append(user.getUsername());
    	Role role = this.employeesBO.createRole(user);
    	role = this.roleRepository.save(role);
    	sb.append(", tiene rol de: ");
    	sb.append(role.getAuthority());
    	employee = this.employeesBO.employeePostReqToEmployee(employee, empReq);        
    	employee = this.employeeRepository.save(employee);
    	return sb.toString();
    }

    @Override
    public Employee getEmployee(Integer id) throws CustomValidationException {
    	Employee employee = this.employeeRepository.findByIdEmployee(id);
    	if (employee == null)
			throw new CustomValidationException(EnumResponse.NO_EXIST);
    	
    	return employee;
    }

	@Override
	public List<Employee> getEmployees() {
		List<Employee> employee = this.employeeRepository.findAll();
        LOG.info(employee.toString());
        return employee;
	}

	@Override
	public Employee patchEmployee(Integer id, EmployeePatchReq employeePatchReq) throws CustomValidationException {
		Employee employee = this.employeeRepository.findByIdEmployee(id);
		if (employee == null)
			throw new CustomValidationException(EnumResponse.NO_EXIST);
			
		employee = this.employeesBO.patchEmployee(employee, employeePatchReq);
		return this.employeeRepository.save(employee);
	}

	@Override
	public void deleteEmployee(Integer id) throws CustomValidationException {
		Employee employee = this.employeeRepository.findByIdEmployee(id);
		if (employee == null)
			throw new CustomValidationException(EnumResponse.NO_EXIST);
		
		this.employeeRepository.deleteById(id);	
	}

	@Override
	public Employee putEmployee(Integer id, EmployeePutReq employeePutReq) throws CustomValidationException {
		Employee employee = this.employeeRepository.findByIdEmployee(id);
		if (employee == null)
			throw new CustomValidationException(EnumResponse.NO_EXIST);
		
		employee = this.employeesBO.putEmployee(employee, employeePutReq);
		return this.employeeRepository.save(employee);
	}

	@Override
	public List<Employee> getEmployeesFilter(Boolean vaccinationStatus, String typeVaccine, LocalDate initialDate, LocalDate finalDate) {
		return this.employeeRepositoryCustom.findFilter(vaccinationStatus, typeVaccine, initialDate, finalDate);
	}
}
