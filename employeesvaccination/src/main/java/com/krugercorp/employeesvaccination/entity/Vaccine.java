package com.krugercorp.employeesvaccination.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.krugercorp.employeesvaccination.commons.util.Constants;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

//@Data
@Entity
@Table(name = "vaccine", schema = Constants.Entities.SCHEMA)
public class Vaccine implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "gen_vaccine_seq", sequenceName = "vaccine_seq", schema = Constants.Entities.SCHEMA, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen_vaccine_seq")
    @Column(name = "id_vaccine", unique = true, nullable = false, precision = 131089, scale = 0)
    private int idVaccine;
    @Getter
    @Setter
    @Column(name = "type_vaccine", length = 20)
    private String typeVaccine;
    @Getter
    @Setter
    @Column(name = "vaccination_date")
    private LocalDate vaccinationDate;
    @Getter
    @Setter
    @Column(name = "number_doses")
    private Integer numberDoses;
    @Getter
    @Setter
    @ManyToOne
    @JsonBackReference
    private Employee employee;
}
