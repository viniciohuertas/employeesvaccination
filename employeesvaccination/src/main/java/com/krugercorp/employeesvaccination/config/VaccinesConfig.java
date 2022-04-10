package com.krugercorp.employeesvaccination.config;

import org.springframework.context.annotation.Bean;

import com.krugercorp.employeesvaccination.service.VaccinesService;
import com.krugercorp.employeesvaccination.service.impl.VaccinesServiceImpl;

public class VaccinesConfig {
	
	@Bean
	public VaccinesService vaccinesService() {
		return new VaccinesServiceImpl();
	}

}
