package com.krugercorp.employeesvaccination.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.krugercorp.employeesvaccination.commons.util.Constants;

import lombok.Data;

@Data
@Entity
@Table(name = "authorities", schema = Constants.Entities.SCHEMA)
public class Role implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "gen_role_seq", sequenceName = "authorities_id_seq", schema = Constants.Entities.SCHEMA, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen_role_seq")
    @Column(unique = true, nullable = false, precision = 131089, scale = 0)
	private Long id;

	private String authority;
	
	@ManyToOne
	@JsonBackReference
	private Users users;

}
