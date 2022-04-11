package com.krugercorp.employeesvaccination.commons.bo;

import org.springframework.stereotype.Service;

import com.krugercorp.employeesvaccination.commons.request.VaccinePostReq;
import com.krugercorp.employeesvaccination.entity.Employee;
import com.krugercorp.employeesvaccination.entity.Vaccine;

@Service
public class VaccinesBO {
	
	 public Vaccine vaccinePostReqToVaccine(Employee employee, Vaccine vaccine, VaccinePostReq vaccinePostReq) {
		 vaccine.setEmployee(employee);
		 vaccine.setTypeVaccine(vaccinePostReq.getVaccine().toLowerCase());
		 vaccine.setVaccinationDate(vaccinePostReq.getVaccination_date());
		 vaccine.setNumberDoses(vaccinePostReq.getNumber_doses());
		 return vaccine;
    }

}
