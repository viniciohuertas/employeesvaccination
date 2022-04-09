package com.krugercorp.employeesvaccionation.commons.enumerations;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum ResponseMessages {
    //2XX
    CORRECTO("000","Ok"),


    AUTH_CORRECTA("000", "Autenticación correcta."),

    //4XX
    AUTH_ERROR("401","Error de autenticación: username o password incorrecto."),
    INCORRECTO("450", "Error"),
    GENERAL_ERROR("451","Error general"),
    TOKEN_ERROR("452","El token enviado no es válido o está caducado, por favor genere un nuevo token y vuelva a intentar."),
    TOKEN_ENTIDAD_ERROR("453","El token enviado no corresponde a su entidad, por favor genere un nuevo token y vuelva a intentar."),
    TRANSACCION_PEDIDO_ERROR("454", "Se debe ingresar numeroTransaccion o codigoPedido."),
    REGISTRO_TRANSACCION_ERROR("455","La transacción no se registró.");

    @Getter
    private final String codigo;
    @Getter
    private final String mensaje;

}
