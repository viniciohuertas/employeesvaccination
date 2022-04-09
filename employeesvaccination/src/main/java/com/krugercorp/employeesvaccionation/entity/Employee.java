package com.krugercorp.employeesvaccionation.entity;

import com.krugercorp.employeesvaccionation.commons.util.Constants;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "employee", schema = Constants.Entitys.SCHEMA)
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name = "gen_employee_seq", sequenceName = "employee_seq", schema = Constants.Entitys.SCHEMA, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen_employee_seq")
    @Column(name = "id_employee", unique = true, nullable = false, precision = 131089, scale = 0)
    private int idEmployee;
    @Column(length = 10)
    private String identification;
    @Column(length = 250)
    private String firstname;
    @Column(length = 250)
    private String lastname;
    @Column(length = 250)
    private String email;
    private LocalDate birthdate;
    @Column(length = 250)
    private String address;
    @Column(length = 20)
    private String cellphone;
    @Column(columnDefinition = "boolean default false")
    private Boolean vaccinationStatus;
    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Vaccine> vaccines = new ArrayList<>();
}
