package com.krugercorp.employeesvaccination.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.krugercorp.employeesvaccination.commons.util.Constants;

import lombok.Data;

@Data
@Entity
@Table(name = "authorities", schema = Constants.Entities.SCHEMA, uniqueConstraints = {
		@UniqueConstraint(columnNames = { "user_id", "authority" }) })
public class Role implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private Long id;

	private String authority;

//	public Long getId() {
//		return id;
//	}
//
//	public void setId(Long id) {
//		this.id = id;
//	}
//
//	public String getAuthority() {
//		return authority;
//	}
//
//	public void setAuthority(String authority) {
//		this.authority = authority;
//	}
	
	@ManyToOne
	@JsonBackReference
	private Users user;

}
