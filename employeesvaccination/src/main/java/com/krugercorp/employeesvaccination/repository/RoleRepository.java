package com.krugercorp.employeesvaccination.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.krugercorp.employeesvaccination.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
