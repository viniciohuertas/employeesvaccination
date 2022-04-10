package com.krugercorp.employeesvaccination.repository;

import com.krugercorp.employeesvaccination.entity.CatalogueDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatalogueDetailRepository extends JpaRepository<CatalogueDetail, Integer> {

}
