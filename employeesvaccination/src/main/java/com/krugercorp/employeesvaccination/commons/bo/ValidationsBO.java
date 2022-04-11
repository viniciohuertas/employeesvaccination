package com.krugercorp.employeesvaccination.commons.bo;

import com.krugercorp.employeesvaccination.commons.enumerations.EnumResponse;
import com.krugercorp.employeesvaccination.commons.exception.CustomValidationException;
import com.krugercorp.employeesvaccination.commons.request.EmployeePostReq;
import com.krugercorp.employeesvaccination.commons.util.Validator;
import com.krugercorp.employeesvaccination.entity.Employee;
import com.krugercorp.employeesvaccination.repository.EmployeeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ValidationsBO {	
	
	private EmployeeRepository employeeRepository;
		
	@Autowired
    public ValidationsBO(EmployeeRepository employeeRepository) {
		super();
		this.employeeRepository = employeeRepository;
	}

	public void employeePostValidation(String identification) throws CustomValidationException {
        if (!Validator.validatorIdentification(identification))
            throw new CustomValidationException(EnumResponse.IDENTIFICATION_ERROR);
    }

}
