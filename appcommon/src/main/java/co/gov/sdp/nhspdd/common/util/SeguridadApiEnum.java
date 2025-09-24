/**
 * 
 */
package co.gov.sdp.nhspdd.common.util;

/**
 * @author Sumset
 *
 */
public enum SeguridadApiEnum {
	
	CODIGO_OK(0),
	CODIGO_USUARIO_CONTRASENIA_EN_BLANCO(-1),
	CODIGO_USUARIO_CONTRASENIA_INVALIDAS(-2),
	CODIGO_TIPO_USUARIO_INVALIDO(-3),
	CODIGO_USUARIO_ERROR_NO_EXISTE(-4),
	CODIGO_LDAP_ERROR(-5),
	CODIGO_ERROR_SERVICIO_NO_SE_ENCUENTRA(-6),
	CODIGO_TOKEN_INVALIDO(-7),
	CODIGO_USUARIO_USUARIO_INHABILITADO(-8),
	CODIGO_USUARIO_CAMBIAR_CONTRASENIA(-9),
	CODIGO_ROL_NO_EXISTE(-10),
	CODIGO_USUARIO_NO_TIENE_PERMISO_APLICACION(-11),
	CODIGO_ERROR(-99),
	CODIGO_ADVERTENCIA(2);
	
	private int codigoRespuesta;
	
	SeguridadApiEnum(int codigoRespuesta) {
		this.codigoRespuesta = codigoRespuesta;
	}

	public int getCodigoRespuesta() {
		return codigoRespuesta;
	}
}
