package com.krugercorp.employeesvaccination.service;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import com.krugercorp.employeesvaccination.commons.exception.CustomValidationException;
import com.krugercorp.employeesvaccination.commons.request.VaccinePostReq;
import com.krugercorp.employeesvaccination.entity.Vaccine;

@Service
public interface VaccinesService {

	Vaccine postVaccine(Integer id, VaccinePostReq vaccinePostReq) throws CustomValidationException;
}
