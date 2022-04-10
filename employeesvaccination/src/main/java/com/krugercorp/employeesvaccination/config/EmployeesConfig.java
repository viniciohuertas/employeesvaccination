package com.krugercorp.employeesvaccination.config;

import com.krugercorp.employeesvaccination.service.EmployeesService;
import com.krugercorp.employeesvaccination.service.impl.EmployeesServiceImpl;
import org.springframework.context.annotation.Bean;

public class EmployeesConfig {
	
    @Bean
    public EmployeesService employeesService() {
        return new EmployeesServiceImpl();
    }
}
