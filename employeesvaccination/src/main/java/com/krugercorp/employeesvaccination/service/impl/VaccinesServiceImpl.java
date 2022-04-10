package com.krugercorp.employeesvaccination.service.impl;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.krugercorp.employeesvaccination.commons.bo.VaccinesBO;
import com.krugercorp.employeesvaccination.commons.enumerations.EnumResponse;
import com.krugercorp.employeesvaccination.commons.exception.CustomValidationException;
import com.krugercorp.employeesvaccination.commons.request.VaccinePostReq;
import com.krugercorp.employeesvaccination.entity.Employee;
import com.krugercorp.employeesvaccination.entity.Vaccine;
import com.krugercorp.employeesvaccination.repository.EmployeeRepository;
import com.krugercorp.employeesvaccination.repository.VaccinneRepository;
import com.krugercorp.employeesvaccination.service.VaccinesService;

@Service
public class VaccinesServiceImpl implements VaccinesService {
	
	private VaccinesBO vaccinesBO;
	private VaccinneRepository vaccineRepository;
	private EmployeeRepository employeeRepository;
	
	public VaccinesServiceImpl() {
	}

	@Autowired
	public VaccinesServiceImpl(VaccinesBO vaccinesBO, VaccinneRepository vaccineRepository, EmployeeRepository employeeRepository) {
		this.vaccinesBO = vaccinesBO;
		this.vaccineRepository = vaccineRepository;
		this.employeeRepository = employeeRepository;
	}

	@Override
	public Vaccine postVaccine(Integer id, VaccinePostReq vaccinePostReq) throws CustomValidationException {
		Vaccine vaccine = new Vaccine();
		Employee employee = this.employeeRepository.findByIdEmployee(id);
    	if (employee == null)
			throw new CustomValidationException(EnumResponse.NO_EXIST);
    	
		vaccine = this.vaccinesBO.vaccinePostReqToVaccine(employee, vaccine, vaccinePostReq);
        return this.vaccineRepository.save(vaccine);
	}
}
