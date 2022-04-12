package com.krugercorp.employeesvaccination.rest;

import lombok.extern.apachecommons.CommonsLog;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.krugercorp.employeesvaccination.commons.bo.CommonBO;
import com.krugercorp.employeesvaccination.commons.enumerations.EnumResponse;
import com.krugercorp.employeesvaccination.commons.exception.CustomValidationException;
import com.krugercorp.employeesvaccination.commons.response.InfoResponse;
import com.krugercorp.employeesvaccination.commons.util.Constants;
import com.krugercorp.employeesvaccination.entity.CatalogueDetail;
import com.krugercorp.employeesvaccination.entity.Employee;
import com.krugercorp.employeesvaccination.service.CataloguesService;

/**
 * Class: CataloguesRestController.java 
 * <br>
 * Date Creation: 10/04/2022 <br>
 * 
 * @author Vinicio Huertas
 * @version 1.0.0
 * @since jdk 11
 **/

@CommonsLog(topic = "cataloguesRestController")
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("catalogues")
public class CataloguesRestController {
	
	private Map<String, Object> response = null;
    private InfoResponse infoResponse;
    
    private List<CatalogueDetail> catalogues;
    private CataloguesService cataloguesService;
    private CommonBO commonBO;
    
    
	@Autowired
	public CataloguesRestController(CataloguesService cataloguesService, CommonBO commonBO) {
		this.cataloguesService = cataloguesService;
		this.commonBO = commonBO;
	}

	@GetMapping("{abbreviation}")
	@PreAuthorize("hasAnyAuthority('EMPLOYEE', 'ADMIN')")
    public ResponseEntity<?> getCataloguesByAbr(@PathVariable String abbreviation) {
        this.response = new HashMap<>();
        this.infoResponse = new InfoResponse();
        try {
            this.catalogues = this.cataloguesService.getCatalogues(abbreviation);
            response.put(Constants.Messages.CATALOGUES, this.catalogues);
        } catch (DataAccessException e) {
            infoResponse = this.commonBO.fillInfo(EnumResponse.ERROR_DB);
            response.put(Constants.Messages.INFO_RESPONSE, infoResponse);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception ex) {
            infoResponse = this.commonBO.fillInfo(EnumResponse.ERROR_DB);
            response.put(Constants.Messages.INFO_RESPONSE, infoResponse);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
