package com.krugercorp.employeesvaccination.repository;

import org.springframework.data.repository.CrudRepository;

import com.krugercorp.employeesvaccination.entity.Users;


public interface UserRepository extends CrudRepository<Users, Long>{

	public Users findByUsername(String username);
	
	
}
