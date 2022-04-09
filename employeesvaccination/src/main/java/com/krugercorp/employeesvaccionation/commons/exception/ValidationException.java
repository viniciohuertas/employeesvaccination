package com.krugercorp.employeesvaccionation.commons.exception;

import lombok.Getter;

import java.io.Serializable;

public class ValidationException extends Exception implements Serializable {
    private static final long serialVersionUID = 1L;

    @Getter
    private String identifier;
    @Getter
    private String mesagge;

    public ValidationException(String identifier, String mesagge) {
        this.identifier = identifier;
        this.mesagge = mesagge;
    }

    /*public ValidationException(String mesagge) {
        this.identifier = EnumRespuestas.VALIDACIONES_CORREGIR.getCodigo();
        this.mesagge = EnumRespuestas.VALIDACIONES_CORREGIR.getMensaje() + " " + mesagge;
    }

    public ValidationException(EnumRespuestas enumRespuestas) {
        this.identifier = enumRespuestas.getCodigo();
        this.mesagge = enumRespuestas.getMensaje();
    }*/

}
