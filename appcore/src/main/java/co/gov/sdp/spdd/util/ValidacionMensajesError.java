package co.gov.sdp.spdd.util;

import org.springframework.stereotype.Component;

import co.gov.sdp.nhspdd.common.dto.RespuestaDTO;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.nhspdd.common.util.NHSPDDMensajes;

/**
 * @author Sumset
 * @param <T>
 *
 */
@Component
public class ValidacionMensajesError<T> {

	@SuppressWarnings("unchecked")
	public T validarMensajesError(RespuestaDTO respuesta) {

		switch (respuesta.getCodigo()) {
		case NHSPDDConstantes.SEGURIDADAPI_CODIGO_ADVERTENCIA:
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_SEGURIDADAPI_ADVERTENCIA,
					respuesta.getLenguaje()));
			break;
		case NHSPDDConstantes.SEGURIDADAPI_CODIGO_USUARIO_CONTRASENIA_EN_BLANCO:
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(
					NHSPDDConstantes.MENSAJE_SEGURIDADAPI_USUARIO_CONTRASENIA_EN_BLANCO, respuesta.getLenguaje()));
			break;
		case NHSPDDConstantes.SEGURIDADAPI_CODIGO_USUARIO_CONTRASENIA_INVALIDAS:
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(
					NHSPDDConstantes.MENSAJE_SEGURIDADAPI_USUARIO_CONTRASENIA_INVALIDAS, respuesta.getLenguaje()));
			break;
		case NHSPDDConstantes.SEGURIDADAPI_CODIGO_TIPO_USUARIO_INVALIDO:
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(
					NHSPDDConstantes.MENSAJE_SEGURIDADAPI_TIPO_USUARIO_INVALIDA, respuesta.getLenguaje()));
			break;
		case NHSPDDConstantes.SEGURIDADAPI_CODIGO_USUARIO_ERROR_NO_EXISTE:
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(
					NHSPDDConstantes.MENSAJE_SEGURIDADAPI_USUARIO_ERROR_NO_EXISTE, respuesta.getLenguaje()));
			break;
		case NHSPDDConstantes.SEGURIDADAPI_CODIGO_LDAP_ERROR:
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_SEGURIDADAPI_LDAP_ERROR,
					respuesta.getLenguaje()));
			break;
		case NHSPDDConstantes.SEGURIDADAPI_CODIGO_ERROR_SERVICIO_NO_SE_ENCUENTRA:
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(
					NHSPDDConstantes.MENSAJE_SEGURIDADAPI_ERROR_SERVICIO_NO_SE_ENCUENTRA, respuesta.getLenguaje()));
			break;
		case NHSPDDConstantes.SEGURIDADAPI_CODIGO_TOKEN_INVALIDO:
			respuesta.setMsgRespuesta(NHSPDDMensajes
					.obtenerMensaje(NHSPDDConstantes.MENSAJE_SEGURIDADAPI_TOKEN_INVALIDO, respuesta.getLenguaje()));
			break;
		case NHSPDDConstantes.SEGURIDADAPI_CODIGO_USUARIO_USUARIO_INHABILITADO:
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(
					NHSPDDConstantes.MENSAJE_SEGURIDADAPI_USUARIO_USUARIO_INHABILITADO, respuesta.getLenguaje()));
			break;
		case NHSPDDConstantes.SEGURIDADAPI_CODIGO_USUARIO_CAMBIAR_CONTRASENIA:
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(
					NHSPDDConstantes.MENSAJE_SEGURIDADAPI_USUARIO_CAMBIAR_CONTRASENIA, respuesta.getLenguaje()));
			break;
		case NHSPDDConstantes.SEGURIDADAPI_CODIGO_ROL_NO_EXISTE:
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_SEGURIDADAPI_ROL_NO_EXISTE,
					respuesta.getLenguaje()));
			break;
		case NHSPDDConstantes.SEGURIDADAPI_CODIGO_USUARIO_NO_TIENE_PERMISO_APLICACION:
			respuesta.setMsgRespuesta(NHSPDDConstantes.MENSAJE_SEGURIDADAPI_USUARIO_NO_TIENE_PERMISO_APLICACION);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(
					NHSPDDConstantes.MENSAJE_SEGURIDADAPI_USUARIO_NO_TIENE_PERMISO_APLICACION,
					respuesta.getLenguaje()));
			break;
		case NHSPDDConstantes.SEGURIDADAPI_CODIGO_ERROR:
			respuesta.setMsgRespuesta(NHSPDDConstantes.MENSAJE_SEGURIDADAPI_MENSAJE_CRITERIOS_CONTRASENA);

		default:
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_SEGURIDADAPI_ERROR,
					respuesta.getLenguaje()));
			break;
		}
		return (T) respuesta;
	}
}
