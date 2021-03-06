package com.krugercorp.employeesvaccination.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.krugercorp.employeesvaccination.commons.util.Constants;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Class: CatalogueDetail.java 
 * <br>
 * Date Creation: 10/04/2022 <br>
 * 
 * @author Vinicio Huertas
 * @version 1.0.0
 * @since jdk 11
 **/

@Data
@Entity
@Table(name = "catalogue_detail", schema = Constants.Entities.SCHEMA)
public class CatalogueDetail implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name = "gen_catalogue_detail_seq", sequenceName = "catalogue_detail_seq", schema = Constants.Entities.SCHEMA, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen_catalogue_detail_seq")
    @Column(name = "id_catalogue_detail", unique = true, nullable = false, precision = 131089, scale = 0)
    private int idCatalogueDetail;
    @Column(name = "abbreviation_detail", length = 20)
    private String abbreviationDetail;
    @Column(name = "name_detail", length = 250)
    private String nameDetail;
    @Column(name = "description_detail", length = 250)
    private String descriptionDetail;
    @Column(name = "status_detail", columnDefinition = "boolean default true")
    private Boolean statusDetail;
    @ManyToOne
    @JsonBackReference
    private Catalogue catalogue;
}
