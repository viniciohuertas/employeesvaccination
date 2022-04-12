package com.krugercorp.employeesvaccination.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.krugercorp.employeesvaccination.commons.util.Constants;

import lombok.Data;

@Data
@Entity
@Table(name = "users", schema = Constants.Entities.SCHEMA)
public class Users implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@SequenceGenerator(name = "gen_users_seq", sequenceName = "users_id_seq", schema = Constants.Entities.SCHEMA, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen_users_seq")
    @Column(unique = true, nullable = false, precision = 131089, scale = 0)
	private Long id;

	@Column(length = 30, unique = true)
	private String username;

	@Column(length = 60)
	private String password;

	private Boolean enabled;

	@OneToMany(mappedBy = "users", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonManagedReference
	private List<Role> roles = new ArrayList<>();

}
