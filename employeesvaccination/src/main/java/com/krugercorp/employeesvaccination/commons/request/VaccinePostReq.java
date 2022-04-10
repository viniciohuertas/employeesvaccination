package com.krugercorp.employeesvaccination.commons.request;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;

@Data
public class VaccinePostReq implements Serializable {

    private static final long serialVersionUID = 1L;

    @Size(min=1, max = 20, message = "es obligatorio, se permite solo letras minúsculas, se debe ingresar la abreviación del catálogo de vacunas")
    @Pattern(regexp = "^[a-z]*$", message = "es obligatorio, se permite solo letras minúsculas, se debe ingresar la abreviación del catálogo de vacunas")
    private String typeVaccine;
    private LocalDate vaccination_date;
    private Integer number_doses;
}
