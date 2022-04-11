package com.krugercorp.employeesvaccination.repository;

import com.krugercorp.employeesvaccination.entity.CatalogueDetail;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CatalogueDetailRepository extends JpaRepository<CatalogueDetail, Integer> {

	@Query("select cd from CatalogueDetail cd"
			+ " join Catalogue c on c.idCatalogue = cd.catalogue"
			+ " where c.abbreviation = ?1")
	List<CatalogueDetail> findByAbbreviationDetail(String abbreviation);

}
