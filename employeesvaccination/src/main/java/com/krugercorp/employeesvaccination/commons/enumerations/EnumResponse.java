package com.krugercorp.employeesvaccination.commons.enumerations;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum EnumResponse {
    //2XX
    CORRECTO("000","Ok"),


    AUTH_CORRECTA("000", "Autenticación correcta."),

    //4XX
    AUTH_ERROR("401","Error de autenticación: username o password incorrecto."),
    INCORRECTO("450", "Error"),
    IDENTIFICATION_ERROR("451", "La Cédula ingresada es Incorrecta"),
    VALIDACIONES_CORREGIR("463","Los datos ingresados no cumplen la validación, Revisar:"),
    GENERAL_ERROR("451","Error general"),
    TOKEN_ERROR("452","El token enviado no es válido o está caducado, por favor genere un nuevo token y vuelva a intentar."),
    TOKEN_ENTIDAD_ERROR("453","El token enviado no corresponde a su entidad, por favor genere un nuevo token y vuelva a intentar."),
    TRANSACCION_PEDIDO_ERROR("454", "Se debe ingresar numeroTransaccion o codigoPedido."),
    ERROR_DB("455", "Se presentó un inconveniente con el registro o consulta de datos."),
    NO_EXIST("456", "El registro al que se refiere no existe en la base de datos.");

    @Getter
    private final String code;
    @Getter
    private final String message;

}
