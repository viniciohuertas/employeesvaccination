package com.krugercorp.employeesvaccination.commons.bo;

import com.krugercorp.employeesvaccination.commons.enumerations.EnumResponse;
import com.krugercorp.employeesvaccination.commons.exception.CustomValidationException;
import com.krugercorp.employeesvaccination.commons.request.EmployeePostReq;
import com.krugercorp.employeesvaccination.commons.util.Validator;
import org.springframework.stereotype.Service;

@Service
public class ValidationsBO {	

    public void employeePostValidation(String identification) throws CustomValidationException {
        if (!Validator.validatorIdentification(identification))
            throw new CustomValidationException(EnumResponse.IDENTIFICATION_ERROR);
    }
    
    /*public void employeePostValidation(EmployeePostReq employeePostReq) throws CustomValidationException {
        if (!Validator.validatorIdentification(employeePostReq.getIdentification()))
            throw new CustomValidationException(EnumResponse.IDENTIFICATION_ERROR);
    }*/

}
