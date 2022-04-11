package com.krugercorp.employeesvaccination.commons.request;

import lombok.Data;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.sun.istack.NotNull;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class VaccinePostReq implements Serializable {

    private static final long serialVersionUID = 1L;

    @Size(min=1, max = 20, message = "es obligatorio, se permite solo letras minúsculas, se debe ingresar la abreviación del catálogo de vacunas")
    @Pattern(regexp = "^[a-z]*$", message = "es obligatorio, se permite solo letras minúsculas, se debe ingresar la abreviación del catálogo de vacunas")
    private String vaccine;
    private LocalDate vaccination_date;
    @NotNull
    @Min(1)
    @Max(9)
    private Integer number_doses;
}
