package com.krugercorp.employeesvaccination.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.krugercorp.employeesvaccination.entity.CatalogueDetail;

/**
 * Interface: CataloguesService.java 
 * <br>
 * Date Creation: 10/04/2022 <br>
 * 
 * @author Vinicio Huertas
 * @version 1.0.0
 * @since jdk 11
 **/

@Service
public interface CataloguesService {

	List<CatalogueDetail> getCatalogues(String abbreviation);
}
