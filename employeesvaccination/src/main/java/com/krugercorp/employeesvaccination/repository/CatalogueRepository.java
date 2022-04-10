package com.krugercorp.employeesvaccination.repository;

import com.krugercorp.employeesvaccination.entity.Catalogue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatalogueRepository extends JpaRepository<Catalogue, Integer> {

}
