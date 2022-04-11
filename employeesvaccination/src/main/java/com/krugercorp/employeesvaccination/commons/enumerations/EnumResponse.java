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
    VALIDACIONES_CORREGIR("452","Los datos ingresados no cumplen la validación, Revisar:"),
    GENERAL_ERROR("453","Error general"),
    TOKEN_ERROR("454","El token enviado no es válido o está caducado, por favor genere un nuevo token y vuelva a intentar."),
    TOKEN_ENTIDAD_ERROR("455","El token enviado no corresponde a su entidad, por favor genere un nuevo token y vuelva a intentar."),
    TRANSACCION_PEDIDO_ERROR("456", "Se debe ingresar numeroTransaccion o codigoPedido."),
    ERROR_DB("457", "Se presentó un inconveniente con el registro o consulta de datos."),
    NO_EXIST("458", "El registro al que se refiere no existe en la base de datos."),
	NO_REGISTER_VACCINES("459", "No puede registra vacunas porque declaró que no está vacunado."),
	INCORRECT_VACCINE("460", "La abreviación de la vacuna es incorrecta, por favor revisar el catálogo de vacunas, en el método: catalogues, debe colocar la abreviación: vaccines");

    @Getter
    private final String code;
    @Getter
    private final String message;

}
