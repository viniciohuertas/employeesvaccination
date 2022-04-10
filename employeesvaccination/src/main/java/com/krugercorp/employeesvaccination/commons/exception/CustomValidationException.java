package com.krugercorp.employeesvaccination.commons.exception;

import com.krugercorp.employeesvaccination.commons.enumerations.EnumResponse;
import lombok.Getter;

import java.io.Serializable;

public class CustomValidationException extends Exception implements Serializable {
    private static final long serialVersionUID = 1L;

    @Getter
    private String code;
    @Getter
    private String message;

    public CustomValidationException(String code, String message) {
        this.code = code;
        this.message = message;
    }

    /*public ValidationException(String mesagge) {
        this.identifier = EnumRespuestas.VALIDACIONES_CORREGIR.getCodigo();
        this.mesagge = EnumRespuestas.VALIDACIONES_CORREGIR.getMensaje() + " " + mesagge;
    }*/

    public CustomValidationException(EnumResponse enumResponse) {
        this.code = enumResponse.getCode();
        this.message = enumResponse.getMessage();
    }

}
