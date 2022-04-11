package com.krugercorp.employeesvaccination.rest;

import lombok.extern.apachecommons.CommonsLog;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.krugercorp.employeesvaccination.commons.bo.CommonBO;
import com.krugercorp.employeesvaccination.commons.bo.ValidationsBO;
import com.krugercorp.employeesvaccination.commons.enumerations.EnumResponse;
import com.krugercorp.employeesvaccination.commons.exception.CustomValidationException;
import com.krugercorp.employeesvaccination.commons.request.EmployeePostReq;
import com.krugercorp.employeesvaccination.commons.request.VaccinePostReq;
import com.krugercorp.employeesvaccination.commons.response.InfoResponse;
import com.krugercorp.employeesvaccination.commons.util.Constants;
import com.krugercorp.employeesvaccination.entity.Vaccine;
import com.krugercorp.employeesvaccination.repository.EmployeeRepository;
import com.krugercorp.employeesvaccination.service.VaccinesService;

@CommonsLog(topic = "vaccinesRestController")
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/")
public class VaccinesRestController {
	
	private Map<String, Object> response = null;
    private InfoResponse infoResponse;
    private Vaccine vaccine;
    private String info;
    
    private CommonBO commonBO;
    private final VaccinesService vaccinessService;    
    
    @Autowired
	public VaccinesRestController(CommonBO commonBO, VaccinesService vaccinessService) {
		this.vaccinessService = vaccinessService;
		this.commonBO = commonBO;
	}

	@PostMapping("employees/{id}/vaccines")
    public ResponseEntity<?> postEmployees(@PathVariable Integer id, @Valid @RequestBody VaccinePostReq vaccinePostReq, BindingResult result, @RequestHeader Map<String, String> headers) {
        this.response = new HashMap<>();
        this.infoResponse = new InfoResponse();
        if (result.hasErrors()) {
            this.infoResponse = this.commonBO.fillInfo(result);
            this.response.put(Constants.Messages.INFO_RESPONSE, infoResponse);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        try {
            this.vaccine = this.vaccinessService.postVaccine(id, vaccinePostReq);
            this.info = Constants.Messages.REGISTER_OK;
            response.put(Constants.Messages.VACCINE, this.info);
        } catch (DataAccessException e) {
            infoResponse = this.commonBO.fillInfo(EnumResponse.ERROR_DB);
            response.put(Constants.Messages.INFO_RESPONSE, infoResponse);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (CustomValidationException cvex) {
            infoResponse = this.commonBO.fillInfo(cvex.getCode(), cvex.getMessage());
            response.put(Constants.Messages.INFO_RESPONSE, infoResponse);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } catch (Exception ex) {
            infoResponse = this.commonBO.fillInfo(EnumResponse.ERROR_DB);
            response.put(Constants.Messages.INFO_RESPONSE, infoResponse);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
