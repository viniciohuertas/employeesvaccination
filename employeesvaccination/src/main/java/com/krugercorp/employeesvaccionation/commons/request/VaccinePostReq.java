package com.krugercorp.employeesvaccionation.commons.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;

public class VaccinePostReq implements Serializable {

    private static final long serialVersionUID = 1L;

    @Size(min=1, max = 20, message = "es obligatorio, se permite solo letras minúsculas, se debe ingresar la abreviación del catálogo de vacunas")
    @Pattern(regexp = "^[a-z]*$", message = "es obligatorio, se permite solo letras minúsculas, se debe ingresar la abreviación del catálogo de vacunas")
    private String typeVaccine;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate vaccination_date;
    @Size(min=1, max = 20, message = "es obligatorio, se permite un solo número")
    @Digits(integer = 1, fraction = 0, message = "es obligatorio, se permite un solo número")
    private Integer number_doses;
}
