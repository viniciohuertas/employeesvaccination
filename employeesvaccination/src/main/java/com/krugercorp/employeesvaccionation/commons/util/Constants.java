/**
 * Nombre Clase: Constantes.java
 * <br> 
 * El contenido de este archivo est&aacute; protegido
 * por los Derechos de autor de la DIRECCI&Oacute;N GENERAL DE REGISTRO CIVIL,
 * IDENTIFICACI&Oacute;N Y CEDULACI&Oacute;N no se permite su reproducci&oacute;N o
 * distribuci&oacute;n sin una autorizaci&oacute;n de la INSTITUCI&Oacute;N 
 * y ser&aacute; penado por la ley seg&uacute;n se infrinja
 *  <br>
 * Fecha Creaci&oacute;n: 12/10/2021 <br>
 * Fecha Modificaci&oacute;n: <br>
 *  
 * <br>
 * Cambios
 * Importantes: <br>
 * 
 * @author Santiago Simba&ntilde;a
 * @version 1.0.0
 * @since jdk 11
 **/

package com.krugercorp.employeesvaccionation.commons.util;

public class Constants {

	public static final String FORMATO_FECHA = "dd/MM/yyyy";

	public static final class Autenticacion {
		public static final String SECRET = "Mav54321JmAv12345";
	}

	public static final class ObtencionCuenta {
		public static final String CHARSET = "UTF-8";
		public static final String ALGORITMO_AES = "PBEWithMD5AndDES";
	}

	public static final class Entitys {
		public static final String SCHEMA = "employeevaccine";
	}

	public static final class Mensajes {
		public static final String INFO_RESPONSE = "infoResponse";
	}

}
