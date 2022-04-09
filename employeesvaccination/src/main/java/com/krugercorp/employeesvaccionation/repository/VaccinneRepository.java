package com.krugercorp.employeesvaccionation.repository;

import com.krugercorp.employeesvaccionation.entity.Vaccine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VaccinneRepository extends JpaRepository<Vaccine, Integer> {
}
