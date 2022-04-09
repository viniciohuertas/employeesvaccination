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
@Table(name = "catalogue", schema = Constants.Entitys.SCHEMA)
public class Catalogue implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name = "gen_catalogue_seq", sequenceName = "catalogue_seq", schema = Constants.Entitys.SCHEMA, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen_catalogue_seq")
    @Column(name = "id_catalogue", unique = true, nullable = false, precision = 131089, scale = 0)
    private int idCatalogue;
    @Column(length = 20)
    private String abbreviation;
    @Column(length = 250)
    private String name;
    @Column(length = 250)
    private String description;
    @Column(columnDefinition = "boolean default true")
    private Boolean status;
    @OneToMany(mappedBy = "catalogue", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<CatalogueDetail> catalogueDetails = new ArrayList<>();
}
