package co.gov.sdp.spdd.core.controller.administracion;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

import co.gov.sdp.nhspdd.common.dto.CampoValidacionDTO;
import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.dto.ProyectoInversionDTO;
import co.gov.sdp.nhspdd.common.dto.ProyectosInversionUsuarioDTO;
import co.gov.sdp.nhspdd.common.dto.RespuestaDTO;
import co.gov.sdp.nhspdd.common.dto.UsuariosDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.nhspdd.common.util.NHSPDDMensajes;
import co.gov.sdp.nhspdd.common.util.PaqueteMensajeEnum;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.nhspdd.common.validador.NHSPDDCamposValidar;
import co.gov.sdp.nhspdd.common.validador.NHSPDDReglasValidacion;
import co.gov.sdp.nhspdd.common.validador.NHSPDDResultadoValidacion;
import co.gov.sdp.spdd.core.icontroller.administracion.IProyectoInversionAdministracionController;
import co.gov.sdp.spdd.core.iservice.administracion.IProyectoInversionAdministracionRegistrar;
import co.gov.sdp.spdd.core.iservice.administracion.IProyectoInversionAdmnistracionConsultar;
import co.gov.sdp.spdd.core.iservice.administracion.IProyectoInversionAdmnistracionEliminar;

/**
 * Clase para manejar las peticiones relacionadas con proyectos de inversion
 * para el modulo administracion
 *
 * @author Raul Londono Murillo
 *
 */
@RestController
public class ProyectoInversionAdministracionController implements IProyectoInversionAdministracionController {

	// Servicio de consulta para proyecto de inversion
	@Autowired
	IProyectoInversionAdmnistracionConsultar consultar;

	// Servicio de eliminacion para proyecto de inversion
	@Autowired
	IProyectoInversionAdmnistracionEliminar eliminar;

	// Servicio de registro para proyecto de inversion
	@Autowired
	IProyectoInversionAdministracionRegistrar registrar;

	/**
	 * 
	 */
	@Autowired
	SPDDLogger spddLogger;

	/**
	 * Implementacion del metodo crearProyectoInversion
	 * @throws JsonProcessingException 
	 *
	 * @see co.gov.sdp.nhspdd.core.icontroller.administracion.IProyectosInversionAdministracionController.crearProyectoInversion
	 */
	@PostMapping(NHSPDDConstantes.CORE_CONTROLLER_ADMINISTRACION_POST_CREAR_PROYECTO_INVERSION)
	@Override
	public ProyectoInversionDTO crearProyectoInversion(@RequestBody ProyectoInversionDTO peticion) throws JsonProcessingException {
		peticion.setIdProyectoInversion(null);
		ProyectoInversionDTO respuesta = new ProyectoInversionDTO();
		Map<String, Boolean> camposAValidar = NHSPDDCamposValidar.crearProyectoInversionCamposValidacion();
		NHSPDDResultadoValidacion resultadoValidacion = NHSPDDReglasValidacion.validarProyectoInversion(peticion,
				camposAValidar);
		try {
			if (resultadoValidacion.esValido()) {
				respuesta = registrar.crearProyectoInversion(peticion);
			} else {
				mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO, NHSPDDConstantes.MENSAJE_ERROR_VALIDACION, PaqueteMensajeEnum.ERRORES, peticion.getLenguaje(), resultadoValidacion.getMsgCampoValidacion());
				mensajeLogger(respuesta.getMsgRespuesta(), NHSPDDConstantes.SEVERIDAD_ERROR, ProyectoInversionAdministracionController.class, respuesta.getLenguaje());
			}
		} catch (SpddExceptions e) {
			mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO, NHSPDDConstantes.MENSAJE_ERROR_VALIDACION, PaqueteMensajeEnum.ERRORES, null, null);
			mensajeLogger(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, NHSPDDConstantes.SEVERIDAD_FATAL, FuncionarioClaveEntidadController.class, respuesta.getLenguaje());
		}

		return respuesta;
	}

	/**
	 * Implementacion del metodo asignarProyectoInversion
	 *
	 * @see co.gov.sdp.nhspdd.core.icontroller.administracion.IProyectosInversionAdministracionController.asignarProyectoInversion
	 */
	@PostMapping(NHSPDDConstantes.CORE_CONTROLLER_ADMINISTRACION_POST_ASIGNAR_PROYECTO_INVERSION)
	@Override
	public ProyectosInversionUsuarioDTO asignarProyectoInversion(@RequestBody ProyectosInversionUsuarioDTO peticion) {
		ProyectosInversionUsuarioDTO respuesta = new ProyectosInversionUsuarioDTO();
		try {
			respuesta = registrar.asignarProyectosInversionUsuario(peticion);
		} catch (SpddExceptions e) {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ERROR_VALIDACION,
					PaqueteMensajeEnum.ERRORES, peticion.getLenguaje()));
			spddLogger.mensajeLogger(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, respuesta.getLenguaje()),
					NHSPDDConstantes.SEVERIDAD_FATAL, ProyectoInversionAdministracionController.class);
		}
		return respuesta;
	}

	/**
	 * Implementacion del metodo removerAsignacionProyectoInversion
	 *
	 * @see co.gov.sdp.nhspdd.core.icontroller.administracion.IProyectosInversionAdministracionController.removerAsignacionProyectoInversion
	 */
	@PostMapping(NHSPDDConstantes.CORE_CONTROLLER_ADMINISTRACION_DELETE_REMOVER_PROYECTO_INVERSION)
	@Override
	public ProyectosInversionUsuarioDTO removerAsignacionProyectoInversion(
			@RequestBody ProyectosInversionUsuarioDTO peticion) {
		ProyectosInversionUsuarioDTO respuesta = new ProyectosInversionUsuarioDTO();
		Map<String, Boolean> camposAValidar = NHSPDDCamposValidar.removerProyectoInversionCamposValidacion();
		NHSPDDResultadoValidacion resultadoValidacion = NHSPDDReglasValidacion
				.validarProyectosInversionUsuario(peticion, camposAValidar);
		try {
			if (resultadoValidacion.esValido()) {
				respuesta = eliminar.removerProyectoInversionUsuario(peticion);
			} else {
				mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO, NHSPDDConstantes.MENSAJE_ERROR_VALIDACION, PaqueteMensajeEnum.ERRORES, peticion.getLenguaje(), resultadoValidacion.getMsgCampoValidacion());
				mensajeLogger(respuesta.getMsgRespuesta(), NHSPDDConstantes.SEVERIDAD_ERROR, ProyectoInversionAdministracionController.class, respuesta.getLenguaje());
			}
		} catch (SpddExceptions e) {
			mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO, NHSPDDConstantes.MENSAJE_ERROR_VALIDACION, PaqueteMensajeEnum.ERRORES, null, null);
			mensajeLogger(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, NHSPDDConstantes.SEVERIDAD_FATAL, FuncionarioClaveEntidadController.class, respuesta.getLenguaje());
		}
		return respuesta;
	}

	/**
	 * Implementacion del metodo obtenerProyectosInversionAsignados
	 *
	 * @see co.gov.sdp.nhspdd.core.icontroller.administracion.IProyectosInversionAdministracionController.obtenerProyectosInversionAsignados
	 */
	@PostMapping(NHSPDDConstantes.CORE_CONTROLLER_ADMINISTRACION_GET_OBTENER_PROYECTOS_INVERSION_ASIGNADOS)
	@Override
	public GenericoDTO obtenerProyectosInversionAsignados(@RequestBody UsuariosDTO peticion) {
		GenericoDTO respuesta = new GenericoDTO();
		try {
			respuesta = consultar.obtenerProyectoInversionAsignados(peticion);
		} catch (SpddExceptions e) {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ERROR_VALIDACION,
					PaqueteMensajeEnum.ERRORES, peticion.getLenguaje()));

			spddLogger.mensajeLogger(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, respuesta.getLenguaje()),
					NHSPDDConstantes.SEVERIDAD_FATAL, ProyectoInversionAdministracionController.class);
		}
		return respuesta;
	}

	/**
	 * Implementacion del metodo obtenerProyectosInversionAsignados
	 *
	 * @see co.gov.sdp.nhspdd.core.icontroller.administracion.IProyectosInversionAdministracionController.obtenerProyectosInversionDisponibles
	 */
	@GetMapping(NHSPDDConstantes.CORE_CONTROLLER_ADMINISTRACION_GET_OBTENER_PROYECTOS_INVERSION_DISPONIBLES)
	@Override
	public GenericoDTO obtenerProyectosInversionDisponibles() {
		GenericoDTO respuesta = new GenericoDTO();
		try {
			respuesta = consultar.obtenerProyectoInversionDisponibles();
		} catch (SpddExceptions e) {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ERROR_VALIDACION,
					PaqueteMensajeEnum.ERRORES, respuesta.getLenguaje()));

			spddLogger.mensajeLogger(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, respuesta.getLenguaje()),
					NHSPDDConstantes.SEVERIDAD_FATAL, ProyectoInversionAdministracionController.class);
		}
		return respuesta;
	}

	/**
	 * Implementacion del metodo obtenerProyectosInversionAsignados
	 *
	 * @see co.gov.sdp.nhspdd.core.icontroller.administracion.IProyectosInversionAdministracionController.obtenerProyectosInversionTodos
	 */
	@GetMapping(NHSPDDConstantes.CORE_CONTROLLER_ADMINISTRACION_GET_OBTENER_PROYECTOS_INVERSION_TODOS)
	@Override
	public GenericoDTO obtenerProyectosInversionTodos() {
		GenericoDTO respuesta = new GenericoDTO();
		try {
			respuesta = consultar.obtenerProyectoInversionTodos();
		} catch (SpddExceptions e) {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ERROR_VALIDACION,
					PaqueteMensajeEnum.ERRORES, respuesta.getLenguaje()));
			spddLogger.mensajeLogger(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, respuesta.getLenguaje()),
					NHSPDDConstantes.SEVERIDAD_FATAL, ProyectoInversionAdministracionController.class);
		}
		return respuesta;
	}
	
	/**
	 * Metodo privado que permite setiar los valores necesarios para mostrar el mensaje correspondiente en cada 
	 * uno de los casos para la respuesta de las peticiones
	 * @param respuesta objeto al cual se le va a setiar el mensaje
	 * @param codigoRespuesta objeto de tipo Integer que representa el codigo de la respuesta
	 * @param msgRespuesta String que representa el mensaje de respuesta que se va a retornar
	 * @param paqueteMensaje objeto de tipo PaqueteMensajeEnum que representa el paquete donde se encuentra el mensaje
	 * @param lenguaje 
	 * @param getMsgCampoValidacion lista de mensaje de las validaciones
	 */
	private void mensajeRespuesta(RespuestaDTO respuesta, Integer codigoRespuesta, String msgRespuesta, PaqueteMensajeEnum paqueteMensaje, String lenguaje,List<CampoValidacionDTO> getMsgCampoValidacion)
	{
		respuesta.setCodigoRespuesta(codigoRespuesta);
		respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(msgRespuesta,
				paqueteMensaje, lenguaje));
		
		respuesta.setLstCampoValidacion(getMsgCampoValidacion);

				
	}
	
	/**
	 * Metodo privado que permite setiar los valores necesarios para el log.
	 * @param msgLogger string que represente el mensaje que tendra el log
	 * @param severidadLoger String que representa la severidad del log
	 * @param nombreClase String que representa el nombre de la clase donde se presenta el log
	 * @param lenguaje
	 */
	private void mensajeLogger(String msgLogger, String severidadLoger, Class<?> nombreClase, String lenguaje)
	{
		spddLogger.mensajeLogger(
				NHSPDDMensajes.obtenerMensaje(msgLogger, lenguaje),
				severidadLoger, nombreClase);
	}
}
