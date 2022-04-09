package com.krugercorp.employeesvaccionation.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@Table(name = "catalogue")
public class Catalogue  implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id

}
