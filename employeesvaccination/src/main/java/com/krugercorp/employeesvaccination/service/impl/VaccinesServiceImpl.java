package com.krugercorp.employeesvaccination.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.krugercorp.employeesvaccination.commons.bo.VaccinesBO;
import com.krugercorp.employeesvaccination.commons.enumerations.EnumResponse;
import com.krugercorp.employeesvaccination.commons.exception.CustomValidationException;
import com.krugercorp.employeesvaccination.commons.request.VaccinePostReq;
import com.krugercorp.employeesvaccination.entity.CatalogueDetail;
import com.krugercorp.employeesvaccination.entity.Employee;
import com.krugercorp.employeesvaccination.entity.Vaccine;
import com.krugercorp.employeesvaccination.repository.CatalogueDetailRepository;
import com.krugercorp.employeesvaccination.repository.EmployeeRepository;
import com.krugercorp.employeesvaccination.repository.VaccinneRepository;
import com.krugercorp.employeesvaccination.service.VaccinesService;

/**
 * Class: VaccinesServiceImpl.java 
 * <br>
 * Date Creation: 10/04/2022 <br>
 * 
 * @author Vinicio Huertas
 * @version 1.0.0
 * @since jdk 11
 **/

@Service
public class VaccinesServiceImpl implements VaccinesService {
	
	private VaccinesBO vaccinesBO;
	private VaccinneRepository vaccineRepository;
	private EmployeeRepository employeeRepository;
	private CatalogueDetailRepository catDetRepository;
	
	public VaccinesServiceImpl() {
	}

	@Autowired
	public VaccinesServiceImpl(VaccinesBO vaccinesBO, VaccinneRepository vaccineRepository, EmployeeRepository employeeRepository, CatalogueDetailRepository catDetRepository) {
		this.vaccinesBO = vaccinesBO;
		this.vaccineRepository = vaccineRepository;
		this.employeeRepository = employeeRepository;
		this.catDetRepository = catDetRepository;
	}

	@Override
	public Vaccine postVaccine(Integer id, VaccinePostReq vaccinePostReq) throws CustomValidationException {
		Vaccine vaccine = new Vaccine();
		Employee employee = this.employeeRepository.findByIdEmployee(id);
		if (employee == null)
			throw new CustomValidationException(EnumResponse.NO_EXIST);
		
		if (!employee.getVaccinationStatus())
			throw new CustomValidationException(EnumResponse.NO_REGISTER_VACCINES);
		
		List<CatalogueDetail> catalogueDetails = this.catDetRepository.findByAbbreviationDetail("vaccines");
		if (catalogueDetails.stream().filter(cd -> cd.getAbbreviationDetail().equalsIgnoreCase(vaccinePostReq.getVaccine())).count() != 1)
			throw new CustomValidationException(EnumResponse.INCORRECT_VACCINE);
    	
		vaccine = this.vaccinesBO.vaccinePostReqToVaccine(employee, vaccine, vaccinePostReq);
        return this.vaccineRepository.save(vaccine);
	}
}
