package com.krugercorp.employeesvaccionation.entity;

import com.krugercorp.employeesvaccionation.commons.util.Constants;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "vaccine", schema = Constants.Entitys.SCHEMA)
public class Vaccine implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name = "gen_vaccine_seq", sequenceName = "vaccine_seq", schema = Constants.Entitys.SCHEMA, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen_vaccine_seq")
    @Column(name = "id_vaccine", unique = true, nullable = false, precision = 131089, scale = 0)
    private int idVaccine;
    @Column(name = "type_vaccine", length = 20)
    private String typeVaccine;
    @Column(name = "vaccination_date")
    private LocalDate vaccinationDate;
    @Column(name = "number_doses")
    private Integer numberDoses;
    @ManyToOne
    private Employee employee;
}
