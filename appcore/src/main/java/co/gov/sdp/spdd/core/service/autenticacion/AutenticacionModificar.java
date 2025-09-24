package co.gov.sdp.spdd.core.service.autenticacion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import co.gov.sdp.nhspdd.common.dto.RespuestaApiDTO;
import co.gov.sdp.nhspdd.common.dto.UsuariosDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.nhspdd.common.util.NHSPDDMensajes;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.spdd.core.iservice.autenticacion.IAutenticacionModificar;
import co.gov.sdp.spdd.util.MetodosRest;
import co.gov.sdp.spdd.util.ValidacionMensajesError;

/**
 * Implementacion de las funcionalidades de modificacion para el modulo de
 * autenticacion
 *
 * @author Raul Londono Murillo
 *
 */
@Service
public class AutenticacionModificar implements IAutenticacionModificar {

	/**
	 * 
	 */
	@Autowired
	MetodosRest<RespuestaApiDTO<UsuariosDTO>> metodosRest;

	/**
	 * 
	 */
	@Autowired
	ValidacionMensajesError<UsuariosDTO> validacionMensajesError;

	/**
	 * 
	 */
	@Autowired
	SPDDLogger spddLogger;
	
	/**
	 * 
	 */
	@Autowired
	ObjectMapper mapper;

	/**
	 * Implementacion del metodo iniciarSesion
	 * 
	 * @throws JsonProcessingException
	 *
	 * @see co.gov.sdp.spdd.core.iservice.autenticacion.IAutenticacionModificar.cambiarClave
	 */
	@Override
	public UsuariosDTO cambiarClave(UsuariosDTO peticion) throws SpddExceptions, JsonProcessingException {
		String parametro = mapper.writeValueAsString(peticion);
		UsuariosDTO respuesta = new UsuariosDTO();
		peticion.setAplicacion("SPDD");
		RespuestaApiDTO<UsuariosDTO> respuestaApi = metodosRest.post(parametro,
				new ParameterizedTypeReference<RespuestaApiDTO<UsuariosDTO>>() {
				},
				NHSPDDConstantes.CONTEXTO_SEGURIDAD_API + NHSPDDConstantes.PATH_SEG_API
						+ NHSPDDConstantes.PATH_SEG_API_REST_AUT
						+ NHSPDDConstantes.PATH_SEG_API_REST_AUT_CAMBIAR_CONTRASENIA);
		if (Boolean.TRUE.equals(respuestaApi.getExito())) {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_CAMBIAR_CLAVE_CORRECTO,
					peticion.getLenguaje()));

		} else {
			respuesta.setCodigo(respuestaApi.getCodigo());
			respuesta = validacionMensajesError.validarMensajesError(respuesta);
			respuesta.setMsgRespuesta(respuestaApi.getMensaje());
			spddLogger.mensajeLogger(respuesta.getMsgRespuesta(), NHSPDDConstantes.SEVERIDAD_ERROR,
					AutenticacionModificar.class);
		}
		return respuesta;
	}

	/**
	 * Implementacion del metodo iniciarSesion
	 * 
	 * @throws JsonProcessingException
	 *
	 * @see co.gov.sdp.spdd.core.iservice.autenticacion.IAutenticacionModificar.reestablecerContrasenia
	 */
	@Override
	public UsuariosDTO reestablecerContrasenia(UsuariosDTO peticion) throws SpddExceptions, JsonProcessingException {

		UsuariosDTO respuesta = new UsuariosDTO();
		peticion.setAplicacion("SPDD");
		String parametro = mapper.writeValueAsString(peticion);
		RespuestaApiDTO<UsuariosDTO> respuestaApi = metodosRest.post(parametro,
				new ParameterizedTypeReference<RespuestaApiDTO<UsuariosDTO>>() {
				},
				NHSPDDConstantes.CONTEXTO_SEGURIDAD_API + NHSPDDConstantes.PATH_SEG_API
						+ NHSPDDConstantes.PATH_SEG_API_REST_AUT
						+ NHSPDDConstantes.PATH_SEG_API_REST_AUT_RECORDAR_CONTRASENIA);

		if (Boolean.TRUE.equals(respuestaApi.getExito())) {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_RESTABLACER_CLAVE_CORRECTO,
					peticion.getLenguaje()));
			spddLogger.mensajeLogger(respuesta.getMsgRespuesta(), NHSPDDConstantes.SEVERIDAD_INFO,
					AutenticacionModificar.class);

		} else {
			respuesta = validacionMensajesError.validarMensajesError(respuesta);
			spddLogger.mensajeLogger(respuesta.getMsgRespuesta(), NHSPDDConstantes.SEVERIDAD_ERROR,
					AutenticacionModificar.class);
		}
		return respuesta;
	}
}
