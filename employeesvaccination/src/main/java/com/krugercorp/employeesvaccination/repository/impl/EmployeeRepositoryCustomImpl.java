package com.krugercorp.employeesvaccination.repository.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import com.krugercorp.employeesvaccination.entity.Employee;
import com.krugercorp.employeesvaccination.repository.EmployeeRepositoryCustom;

@Repository
public class EmployeeRepositoryCustomImpl implements EmployeeRepositoryCustom {
	
	private static final Log LOG = LogFactory.getLog(EmployeeRepositoryCustomImpl.class);
	
	@PersistenceContext
    private EntityManager entityManager;

	@Override
	public List<Employee> findFilter(Boolean vaccinationStatus, String typeVaccine, LocalDate initialDate, LocalDate finalDate) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Employee> query = cb.createQuery(Employee.class);
        Root<Employee> emp = query.from(Employee.class);
        
        List<Predicate> predicates = new ArrayList<>();
        
        if (vaccinationStatus != null) {   
        	Path<Boolean> vaccinationStatusPath = emp.get("vaccinationStatus");
        	predicates.add(cb.equal(vaccinationStatusPath, vaccinationStatus));
        }
        if (typeVaccine != null && !typeVaccine.isEmpty()) {
        	Path<String> typeVaccinePath = emp.join("vaccines").get("typeVaccine");
        	predicates.add(cb.like(typeVaccinePath, typeVaccine));
        }
        if (initialDate != null && !initialDate.toString().isEmpty() && finalDate != null && !finalDate.toString().isEmpty()) {
        	Path<LocalDate> vaccinationDatePath = emp.join("vaccines").get("vaccinationDate");
        	predicates.add(cb.between(vaccinationDatePath, initialDate, finalDate));
        }
        
        query.select(emp)
        .where(cb.and(predicates.toArray(new Predicate[predicates.size()])))
        .groupBy(emp);

	    return entityManager.createQuery(query).
	        getResultList();
        
		
	}

}
