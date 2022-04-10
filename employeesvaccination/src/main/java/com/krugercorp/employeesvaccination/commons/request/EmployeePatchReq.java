package com.krugercorp.employeesvaccination.commons.request;

import jdk.jfr.BooleanFlag;
import lombok.Data;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;

@Data
public class EmployeePatchReq implements Serializable {

    private static final long serialVersionUID = 1L;

    private LocalDate birthdate;
    @Size(min=1, max = 250, message = "es obligatorio, se permite solo letras mayúsculas o minúsculas, no se permite caracteres especiales y el tamaño máximo es 250 caracteres")
    @Pattern(regexp = "^[a-zñáéíóúA-ZÑÁÉÍÓÚ\\s]*$", message = "es obligatorio, se permite solo letras mayúsculas o minúsculas, no se permite caracteres especiales y el tamaño máximo es 250 caracteres")
    private String address;
    @Size(max = 20, message = "se permite 20 dígitos entre 0 y 9")
    @Pattern(regexp = "^[0-9\\t]*$", message = "se permite 20 dígitos entre 0 y 9")
    private String cellphone;
    @BooleanFlag
    private Boolean vaccinationStatus;
}
