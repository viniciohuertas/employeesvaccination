package com.krugercorp.employeesvaccination.repository;

import com.krugercorp.employeesvaccination.entity.Vaccine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VaccinneRepository extends JpaRepository<Vaccine, Integer> {
}
