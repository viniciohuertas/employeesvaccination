package com.krugercorp.employeesvaccination.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.krugercorp.employeesvaccination.entity.CatalogueDetail;
import com.krugercorp.employeesvaccination.repository.CatalogueDetailRepository;
import com.krugercorp.employeesvaccination.service.CataloguesService;

/**
 * Class: CataloguesServiceImpl.java 
 * <br>
 * Date Creation: 10/04/2022 <br>
 * 
 * @author Vinicio Huertas
 * @version 1.0.0
 * @since jdk 11
 **/

@Service
public class CataloguesServiceImpl implements CataloguesService {
	
	private CatalogueDetailRepository catalogueDetailRepository;
	
	public CataloguesServiceImpl() {
	}

	@Autowired
	public CataloguesServiceImpl(CatalogueDetailRepository catalogueDetailRepository) {
		this.catalogueDetailRepository = catalogueDetailRepository;
	}

	@Override
	public List<CatalogueDetail> getCatalogues(String abbreviation) {
		return this.catalogueDetailRepository.findByAbbreviationDetail(abbreviation);
	}
}
