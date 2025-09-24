package co.gov.sdp.spdd.core.controller.buzon.notificacion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

import co.gov.sdp.nhspdd.common.dto.BuzonNotificacionesDTO;
import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.nhspdd.common.util.NHSPDDMensajes;
import co.gov.sdp.nhspdd.common.util.PaqueteMensajeEnum;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.spdd.core.icontroller.buzon.notificacion.IBuzonNotificacionController;
import co.gov.sdp.spdd.core.iservice.buzon.notificacion.IBuzonNotificacionConsultar;
import co.gov.sdp.spdd.core.iservice.buzon.notificacion.IBuzonNotificacionModificar;
import co.gov.sdp.spdd.core.iservice.buzon.notificacion.IBuzonNotificacionRegistrar;
import co.gov.sdp.spdd.util.AuditoriaUtil;

/**
 * Clase que implementa los metodos de buzon notificacion
 *
 * @author Bryan Munoz
 *
 */
@RestController
public class BuzonNotificacionController implements IBuzonNotificacionController {

	// Servicio que permite realizar consultas de buzonNotificaciones
	@Autowired
	IBuzonNotificacionConsultar consultar;

	// Servicio que permite realizar modificaciones de buzonNotificaciones
	@Autowired
	IBuzonNotificacionModificar modificar;

	// Servicio que permite registar buzonNotificaciones
	@Autowired
	IBuzonNotificacionRegistrar registrar;

	/**
	 * Logger
	 */
	@Autowired
	SPDDLogger spddLogger;

	/**
	 * 
	 */
	@Autowired
	AuditoriaUtil auditoria;

	/**
	 * Implementacion de obtenerTodosAdmin
	 *
	 * @see co.gov.sdp.spdd.core.icontroller.buzon.notificacion.IBuzonNotificacionController.obtenerTodosAdmin
	 */
	@PostMapping(NHSPDDConstantes.CORE_CONTROLLER_BUZON_NOTIFICACION_OBTENER_ADMIN)
	@Override
	public GenericoDTO obtenerTodosAdmin(@RequestBody BuzonNotificacionesDTO peticion) {
		GenericoDTO respuesta = new GenericoDTO();
		try {
			respuesta = consultar.obtenerPaginado(peticion);
		} catch (SpddExceptions e) {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ERROR_VALIDACION,
					PaqueteMensajeEnum.ERRORES, null));
			spddLogger.mensajeLogger(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, respuesta.getLenguaje()),
					NHSPDDConstantes.SEVERIDAD_FATAL, BuzonNotificacionController.class);
		}
		return respuesta;
	}

	/**
	 * Implementacion de obtenerPorUsuario
	 *
	 * @see co.gov.sdp.spdd.core.icontroller.buzon.notificacion.IBuzonNotificacionController.obtenerPorUsuario
	 */
	@GetMapping(NHSPDDConstantes.CORE_CONTROLLER_BUZON_NOTIFIACION_OBTENER_TODOS_USUARIO)
	@Override
	public GenericoDTO obtenerPorUsuario(@PathVariable("usuario") String usuario) {
		GenericoDTO respuesta = new GenericoDTO();
		try {
			respuesta = consultar.obtenerTodosPorUsuario(usuario);
		} catch (Exception e) {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ERROR_VALIDACION,
					PaqueteMensajeEnum.ERRORES, null));
			spddLogger.mensajeLogger(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, respuesta.getLenguaje()),
					NHSPDDConstantes.SEVERIDAD_FATAL, BuzonNotificacionController.class);
		}
		return respuesta;
	}

	/**
	 * Implementacion de darRespuesta
	 *
	 * @see co.gov.sdp.spdd.core.icontroller.buzon.notificacion.IBuzonNotificacionController.darRespuesta
	 */
	@PutMapping(NHSPDDConstantes.CORE_CONTROLLER_BUZON_NOTIFICACION_DAR_RESPUESTA)
	@Override
	public BuzonNotificacionesDTO darRespuesta(@RequestBody BuzonNotificacionesDTO peticion) {
		BuzonNotificacionesDTO respuesta = new BuzonNotificacionesDTO();
		try {
			respuesta = modificar.darRespuesta(peticion);
		} catch (SpddExceptions e) {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ERROR_VALIDACION,
					PaqueteMensajeEnum.ERRORES, null));

			spddLogger.mensajeLogger(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, respuesta.getLenguaje()),
					NHSPDDConstantes.SEVERIDAD_FATAL, BuzonNotificacionController.class);
		}
		return respuesta;
	}

	@PostMapping(NHSPDDConstantes.CORE_CONTROLLER_BUZON_NOTIFICACION_CREAR_BUZON)
	@Override
	public BuzonNotificacionesDTO registrarBuzonNotificacion(@RequestBody BuzonNotificacionesDTO peticion) {
		peticion.setIdNotificacion(null);
		try {
			registrar.registarBuzonNotificacion(peticion);
			auditoria.crearAuditoria(peticion.toString(), peticion.toString(), "CREAR_AFS", "CREACION_MENSAJE");
		} catch (JsonProcessingException e) {

			peticion.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXCEPCION);
			peticion.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, ""));
			spddLogger.mensajeLogger(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, ""),
					NHSPDDConstantes.SEVERIDAD_ERROR, BuzonNotificacionController.class);
		} catch (SpddExceptions e) {
			peticion.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXCEPCION);
			peticion.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, ""));
			spddLogger.mensajeLogger(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, ""),
					NHSPDDConstantes.SEVERIDAD_ERROR, BuzonNotificacionController.class);
		}
		return peticion;
	}

	@GetMapping(NHSPDDConstantes.CORE_CONTROLLER_BUZON_NOTIFICACION_LEER_INFORMATIVO)
	@Override
	public BuzonNotificacionesDTO leerInformativo(@PathVariable("usuario") String usuario) {

		BuzonNotificacionesDTO peticion = new BuzonNotificacionesDTO();
		try {
			modificar.leerInformativos(usuario);
		} catch (SpddExceptions e) {
			peticion.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXCEPCION);
			peticion.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, ""));
			spddLogger.mensajeLogger(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, ""),
					NHSPDDConstantes.SEVERIDAD_ERROR, BuzonNotificacionController.class);
		}
		return peticion;

	}

	@PostMapping(NHSPDDConstantes.CORE_CONTROLLER_BUZON_NOTIFICACION_OBTENER_NOTIFICACIONES)
	@Override
	public Long leerNotificacioenes(@RequestBody BuzonNotificacionesDTO peticion) {
		Long notificaciones = 0L;
		try {
			notificaciones = consultar.notificacionesPorUsuario(peticion);
		} catch (SpddExceptions e) {
			peticion.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXCEPCION);
			peticion.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, ""));
			spddLogger.mensajeLogger(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, ""),
					NHSPDDConstantes.SEVERIDAD_ERROR, BuzonNotificacionController.class);
		}
		return notificaciones;
	}
}
