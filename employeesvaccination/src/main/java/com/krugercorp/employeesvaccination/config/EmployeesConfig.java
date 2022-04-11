package com.krugercorp.employeesvaccination.config;

import com.krugercorp.employeesvaccination.repository.EmployeeRepositoryCustom;
import com.krugercorp.employeesvaccination.repository.impl.EmployeeRepositoryCustomImpl;
import com.krugercorp.employeesvaccination.service.EmployeesService;
import com.krugercorp.employeesvaccination.service.impl.EmployeesServiceImpl;
import org.springframework.context.annotation.Bean;

public class EmployeesConfig {
	
    @Bean
    public EmployeesService employeesService() {
        return new EmployeesServiceImpl();
    }
    
    @Bean
    public EmployeeRepositoryCustom employeeRepositoryCustom() {
    	return new EmployeeRepositoryCustomImpl();
    }
}
