package com.krugercorp.employeesvaccination.service.impl;

import com.krugercorp.employeesvaccination.commons.bo.EmployeesBO;
import com.krugercorp.employeesvaccination.commons.enumerations.EnumResponse;
import com.krugercorp.employeesvaccination.commons.exception.CustomValidationException;
import com.krugercorp.employeesvaccination.commons.request.EmployeePatchReq;
import com.krugercorp.employeesvaccination.commons.request.EmployeePostReq;
import com.krugercorp.employeesvaccination.commons.request.EmployeePutReq;
import com.krugercorp.employeesvaccination.entity.Employee;
import com.krugercorp.employeesvaccination.repository.EmployeeRepository;
import com.krugercorp.employeesvaccination.repository.EmployeeRepositoryCustom;
import com.krugercorp.employeesvaccination.service.EmployeesService;

import java.time.LocalDate;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeesServiceImpl implements EmployeesService {

    public static final Log LOG = LogFactory.getLog(EmployeesServiceImpl.class);

    private EmployeesBO employeesBO;
    private EmployeeRepository employeeRepository;
    private EmployeeRepositoryCustom employeeRepositoryCustom;

    public EmployeesServiceImpl() {
    }

    @Autowired
    public EmployeesServiceImpl(EmployeesBO employeesBO, EmployeeRepository employeeRepository, EmployeeRepositoryCustom employeeRepositoryCustom) {
        this.employeesBO = employeesBO;
        this.employeeRepository = employeeRepository;
        this.employeeRepositoryCustom = employeeRepositoryCustom;    
    }

    @Override
    public Employee postEmployees(EmployeePostReq empReq) {
    	Employee employee = new Employee();
        employee = this.employeesBO.employeePostReqToEmployee(employee, empReq);
        return this.employeeRepository.save(employee);
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
