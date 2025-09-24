package co.gov.sdp.spdd.core.service.buzon.notificacion;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import co.gov.sdp.nhspdd.common.dto.BuzonNotificacionesDTO;
import co.gov.sdp.nhspdd.common.dto.RespuestaApiDTO;
import co.gov.sdp.nhspdd.common.dto.UsuariosDTO;
import co.gov.sdp.nhspdd.common.dto.seguridad.EntidadSeguridadDTO;
import co.gov.sdp.nhspdd.common.dto.seguridad.UsuariosSeguridadDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.nhspdd.common.util.NHSPDDMensajes;
import co.gov.sdp.nhspdd.common.util.PaqueteMensajeEnum;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.spdd.core.iservice.buzon.notificacion.IBuzonNotificacionRegistrar;
import co.gov.sdp.spdd.data.sesionfacade.interfaces.ISessionFacadeAFS;
import co.gov.sdp.spdd.util.MetodosRest;

/**
 * @author Sumset
 *
 */
@Service
public class BuzonNotificacionRegistrar implements IBuzonNotificacionRegistrar {

	/**
	 * 
	 */
	@Autowired
	MetodosRest<RespuestaApiDTO<UsuariosSeguridadDTO>> metodoRest;

	@Autowired
	MetodosRest<RespuestaApiDTO<EntidadSeguridadDTO>> entidadRest;

	/**
	 * 
	 */
	@Autowired
	ObjectMapper objectMapper;

	/**
	 * 
	 */
	@Autowired
	ISessionFacadeAFS sessionFacadeAFS;

	/**
	 * 
	 */
	@Autowired
	SPDDLogger spddLogger;

	/*
	 * 
	 */
	@Override
	public BuzonNotificacionesDTO registarBuzonNotificacion(BuzonNotificacionesDTO peticion)
			throws SpddExceptions, JsonProcessingException {

		BuzonNotificacionesDTO respuesta = new BuzonNotificacionesDTO();
		UsuariosDTO usuariosDTO = new UsuariosDTO();
		usuariosDTO.setAplicacion("SPDD");
		usuariosDTO.setEntidad("");

		usuariosDTO.setNombreRoles(peticion.getRoles());
		if (!peticion.getRoles().equals("")) {
			metodoRest.agregarToken(peticion.getToken());
			entidadRest.agregarToken(peticion.getToken());
			String parametro = objectMapper.writeValueAsString(usuariosDTO);
			List<UsuariosSeguridadDTO> list = new ArrayList<>();
			RespuestaApiDTO<UsuariosSeguridadDTO> usuariosRoles = metodoRest.post(parametro,
					new ParameterizedTypeReference<RespuestaApiDTO<UsuariosSeguridadDTO>>() {
					},
					NHSPDDConstantes.CONTEXTO_SEGURIDAD_API + NHSPDDConstantes.PATH_SEG_API
							+ NHSPDDConstantes.PATH_SEG_API_REST_USUARIOS
							+ NHSPDDConstantes.PATH_SEG_API_REST_USUARIOS_CONSULTARPORROL);

			list.addAll(usuariosRoles.getObjetos());
			RespuestaApiDTO<EntidadSeguridadDTO> ent = entidadRest.get(
					NHSPDDConstantes.CONTEXTO_SEGURIDAD_API + "/api/entidad/consultartodos",
					new ParameterizedTypeReference<RespuestaApiDTO<EntidadSeguridadDTO>>() {
					});
			for (EntidadSeguridadDTO entidad : ent.getObjetos()) {
				usuariosDTO.setEntidad(entidad.getCodigoEntidad());
				parametro = objectMapper.writeValueAsString(usuariosDTO);
				usuariosRoles = metodoRest.post(parametro,
						new ParameterizedTypeReference<RespuestaApiDTO<UsuariosSeguridadDTO>>() {
						},
						NHSPDDConstantes.CONTEXTO_SEGURIDAD_API + NHSPDDConstantes.PATH_SEG_API
								+ NHSPDDConstantes.PATH_SEG_API_REST_USUARIOS
								+ NHSPDDConstantes.PATH_SEG_API_REST_USUARIOS_CONSULTARPORROL);

				list.addAll(usuariosRoles.getObjetos());
			}

			for (UsuariosSeguridadDTO usuarioSeguridadDTO : list) {
				respuesta.setTipoMensaje(0L);
				respuesta.setAsunto(peticion.getAsunto());
				respuesta.setMensaje(peticion.getMensaje());
				SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");
				respuesta.setFechaEscritura(date.format(new Date()));
				respuesta.setCodigoUsuarioDestino(usuarioSeguridadDTO.getUsuario());
				respuesta.setCodigoUsuarioOrigina(peticion.getCodigoUsuarioOrigina());
				respuesta.setIdConfigNotificacion(-1L);
				respuesta.setEstado(0);
				sessionFacadeAFS.guardarBuzonNotificaciones(respuesta);
			}
			respuesta.setCodigo(NHSPDDConstantes.RESPUESTA_EXITOSA);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_CREAR_BUZON_EXITOSO,
					PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
			spddLogger.mensajeLogger(NHSPDDConstantes.MENSAJE_CREAR_BUZON_EXITOSO, NHSPDDConstantes.SEVERIDAD_INFO,
					BuzonNotificacionRegistrar.class);
		} else {
			respuesta.setCodigo(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ROLES_VACIO,
					PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
			spddLogger.mensajeLogger(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, "", BuzonNotificacionRegistrar.class);
		}
		return respuesta;
	}

}
