package com.krugercorp.employeesvaccination.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.krugercorp.employeesvaccination.entity.CatalogueDetail;

@Service
public interface CataloguesService {

	List<CatalogueDetail> getCatalogues(String abbreviation);
}
