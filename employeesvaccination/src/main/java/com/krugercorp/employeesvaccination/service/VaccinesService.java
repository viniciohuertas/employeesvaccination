package com.krugercorp.employeesvaccination.service;

import org.springframework.stereotype.Service;

import com.krugercorp.employeesvaccination.commons.exception.CustomValidationException;
import com.krugercorp.employeesvaccination.commons.request.VaccinePostReq;
import com.krugercorp.employeesvaccination.entity.Vaccine;

/**
 * Interface: VaccinesService.java 
 * <br>
 * Date Creation: 10/04/2022 <br>
 * 
 * @author Vinicio Huertas
 * @version 1.0.0
 * @since jdk 11
 **/

@Service
public interface VaccinesService {

	Vaccine postVaccine(Integer id, VaccinePostReq vaccinePostReq) throws CustomValidationException;
}
