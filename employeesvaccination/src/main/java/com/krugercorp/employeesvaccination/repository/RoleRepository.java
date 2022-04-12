package com.krugercorp.employeesvaccination.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.krugercorp.employeesvaccination.entity.Role;
import com.krugercorp.employeesvaccination.entity.Users;

public interface RoleRepository extends JpaRepository<Role, Long> {

	Role findByUsers(Users user);

}
