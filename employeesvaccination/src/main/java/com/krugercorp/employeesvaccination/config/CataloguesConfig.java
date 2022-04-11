package com.krugercorp.employeesvaccination.config;

import org.springframework.context.annotation.Bean;

import com.krugercorp.employeesvaccination.service.CataloguesService;
import com.krugercorp.employeesvaccination.service.impl.CataloguesServiceImpl;

public class CataloguesConfig {
	
	@Bean
	public CataloguesService cataloguesService() {
		return new CataloguesServiceImpl();
	}

}
