package com.krugercorp.employeesvaccination.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.krugercorp.employeesvaccination.entity.CatalogueDetail;
import com.krugercorp.employeesvaccination.repository.CatalogueDetailRepository;
import com.krugercorp.employeesvaccination.service.CataloguesService;

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
