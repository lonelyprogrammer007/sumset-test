package co.gov.sdp.spdd.core.controller.administracion;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

import co.gov.sdp.nhspdd.common.dto.EntidadDTO;
import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.dto.UsuarioEntidadDTO;
import co.gov.sdp.nhspdd.common.dto.UsuariosDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.nhspdd.common.util.NHSPDDMensajes;
import co.gov.sdp.nhspdd.common.util.PaqueteMensajeEnum;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.nhspdd.common.validador.NHSPDDCamposValidar;
import co.gov.sdp.nhspdd.common.validador.NHSPDDReglasValidacion;
import co.gov.sdp.nhspdd.common.validador.NHSPDDResultadoValidacion;
import co.gov.sdp.spdd.core.icontroller.administracion.IEntidadAdministracionController;
import co.gov.sdp.spdd.core.iservice.administracion.IBancoDeProyectoConsultar;
import co.gov.sdp.spdd.core.iservice.administracion.IEntidadAdministracionConsultar;
import co.gov.sdp.spdd.core.iservice.administracion.IEntidadAdministracionEliminar;
import co.gov.sdp.spdd.core.iservice.administracion.IEntidadAdministracionRegistrar;

/**
 * Clase para manejar las peticiones relacionadas con entidades o alcaldias para
 * el modulo administracion
 *
 * @author Raul Londono Murillo
 *
 */
@RestController
public class EntidadAdministracionController implements IEntidadAdministracionController {

	// Servicio de consulta para entidad
	@Autowired
	IEntidadAdministracionConsultar consultar;

	@Autowired
	IBancoDeProyectoConsultar consultarBanco;
	// Servicio de eliminacion para entidad
	@Autowired
	IEntidadAdministracionEliminar eliminar;

	// Servicio de registro para entidad
	@Autowired
	IEntidadAdministracionRegistrar registrar;

	@Autowired
	SPDDLogger spddLogger;

	/**
	 * Implementacion del metodo asignarEntidad
	 * 
	 * @throws SpddExceptions
	 * @throws JsonProcessingException 
	 *
	 * @see co.gov.sdp.spdd.core.icontroller.administracion.IEntidadAdministracionController.asignarEntidad
	 */
	@PostMapping(NHSPDDConstantes.CORE_CONTROLLER_ADMINISTRACION_POST_ASIGNAR_ENTIDAD)
	@Override
	public UsuarioEntidadDTO asignarEntidad(@RequestBody UsuarioEntidadDTO peticion) throws SpddExceptions, JsonProcessingException {
		return registrar.asignarUsuarioEntidad(peticion);
	}

	/**
	 * Implementacion del metodo crearEntidad
	 *
	 * @see co.gov.sdp.spdd.core.icontroller.administracion.IEntidadAdministracionController.crearEntidad
	 */
	@PostMapping(NHSPDDConstantes.CORE_CONTROLLER_ADMINISTRACION_POST_CREAR_ENTIDAD)
	public EntidadDTO crearEntidad(@RequestBody EntidadDTO peticion) {
		EntidadDTO respuesta = new EntidadDTO();
		Map<String, Boolean> camposAValidar = NHSPDDCamposValidar.crearEntidadCamposValidacion();
		NHSPDDResultadoValidacion resultadoValidacion = NHSPDDReglasValidacion.validarEntidad(peticion, camposAValidar);
		try {
			if (resultadoValidacion.esValido()) {
				respuesta = registrar.crearEntidad(peticion);
			} else {
				respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
				respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ERROR_VALIDACION,
						PaqueteMensajeEnum.ERRORES, peticion.getLenguaje()));
				respuesta.setLstCampoValidacion(resultadoValidacion.getMsgCampoValidacion());
				spddLogger.mensajeLogger(
						NHSPDDMensajes.obtenerMensaje(respuesta.getMsgRespuesta(), respuesta.getLenguaje()),
						NHSPDDConstantes.SEVERIDAD_ERROR, ComponenteGestionUsuarioController.class);
			}
		} catch (SpddExceptions e) {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ERROR_VALIDACION,
					PaqueteMensajeEnum.ERRORES, respuesta.getLenguaje()));
			spddLogger.mensajeLogger(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, respuesta.getLenguaje()),
					NHSPDDConstantes.SEVERIDAD_FATAL, ComponenteGestionUsuarioController.class);
		}
		return respuesta;
	}

	/**
	 * Implementacion del metodo removerAsignacionEntidad
	 * @throws JsonProcessingException 
	 *
	 * @see co.gov.sdp.spdd.core.icontroller.administracion.IEntidadAdministracionController.removerAsignacionEntidad
	 */
	@DeleteMapping(NHSPDDConstantes.CORE_CONTROLLER_ADMINISTRACION_DELETE_REMOVER_ENTIDAD)
	public UsuarioEntidadDTO removerAsignacionEntidad(@RequestBody UsuarioEntidadDTO peticion) throws JsonProcessingException {
		UsuarioEntidadDTO respuesta = new UsuarioEntidadDTO();
		Map<String, Boolean> camposAValidar = NHSPDDCamposValidar.removerUsuarioEntidadCamposValidacion();
		NHSPDDResultadoValidacion resultadoValidacion = NHSPDDReglasValidacion.validarUsuarioEntidad(peticion,
				camposAValidar);
		try {
			if (resultadoValidacion.esValido()) {
				respuesta = eliminar.removerUsuarioEntidad(peticion);
			} else {
				respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
				respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ERROR_VALIDACION,
						PaqueteMensajeEnum.ERRORES, peticion.getLenguaje()));
				respuesta.setLstCampoValidacion(resultadoValidacion.getMsgCampoValidacion());
				spddLogger.mensajeLogger(
						NHSPDDMensajes.obtenerMensaje(respuesta.getMsgRespuesta(), respuesta.getLenguaje()),
						NHSPDDConstantes.SEVERIDAD_ERROR, EntidadAdministracionController.class);
			}
		} catch (SpddExceptions e) {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ERROR_VALIDACION,
					PaqueteMensajeEnum.ERRORES, respuesta.getLenguaje()));
			spddLogger.mensajeLogger(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, respuesta.getLenguaje()),
					NHSPDDConstantes.SEVERIDAD_FATAL, EntidadAdministracionController.class);
		}
		return respuesta;
	}

	/**
	 * Implementacion del metodo obtenerEntidadesAsignados
	 *
	 * @see co.gov.sdp.spdd.core.icontroller.administracion.IEntidadAdministracionController.obtenerEntidadesAsignados
	 */
	@PostMapping(NHSPDDConstantes.CORE_CONTROLLER_ADMINISTRACION_GET_OBTENER_ENTIDADES_ASIGNADOS)
	public GenericoDTO obtenerEntidadesAsignados(@RequestBody UsuariosDTO peticion) {
		GenericoDTO respuesta = new GenericoDTO();
		try {
			respuesta = consultar.obtenerEntidadAsignadas(peticion);
		} catch (SpddExceptions e) {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ERROR_VALIDACION,
					PaqueteMensajeEnum.ERRORES, respuesta.getLenguaje()));
			spddLogger.mensajeLogger(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, respuesta.getLenguaje()),
					NHSPDDConstantes.SEVERIDAD_FATAL, EntidadAdministracionController.class);

		}
		return respuesta;
	}

	/**
	 * Implementacion del metodo obtenerEntidadesDisponibles
	 *
	 * @see co.gov.sdp.spdd.core.icontroller.administracion.IEntidadAdministracionController.obtenerEntidadesDisponibles
	 */
	@GetMapping(NHSPDDConstantes.CORE_CONTROLLER_ADMINISTRACION_GET_OBTENER_ENTIDADES_DISPONIBLES)
	public GenericoDTO obtenerEntidadesDisponibles() {
		GenericoDTO respuesta = new GenericoDTO();
		try {
			respuesta = consultar.obtenerEntidadTodos();
		} catch (SpddExceptions e) {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ERROR_VALIDACION,
					PaqueteMensajeEnum.ERRORES, respuesta.getLenguaje()));
			spddLogger.mensajeLogger(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, respuesta.getLenguaje()),
					NHSPDDConstantes.SEVERIDAD_FATAL, EntidadAdministracionController.class);
		}
		return respuesta;
	}

	/**
	 * Implementacion del metodo obtenerEntidadesTodas
	 *
	 * @see co.gov.sdp.spdd.core.icontroller.administracion.IEntidadAdministracionController.obtenerEntidadesTodas
	 */
	@GetMapping(NHSPDDConstantes.CORE_CONTROLLER_ADMINISTRACION_GET_OBTENER_ENTIDADES_TODOS)
	public GenericoDTO obtenerEntidadesTodas() {
		GenericoDTO respuesta = new GenericoDTO();
		try {
			respuesta = consultar.obtenerEntidadTodos();
		} catch (SpddExceptions e) {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ERROR_VALIDACION,
					PaqueteMensajeEnum.ERRORES, respuesta.getLenguaje()));

			spddLogger.mensajeLogger(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, respuesta.getLenguaje()),
					NHSPDDConstantes.SEVERIDAD_FATAL, EntidadAdministracionController.class);
		}
		return respuesta;
	}

	/**
	 * 
	 */
	@GetMapping("consultar_banco")
	@Override
	public GenericoDTO obtenerTodosBancoProyectos() {
		GenericoDTO respuesta = new GenericoDTO();
		try {
			respuesta = consultarBanco.obtenerTodos();
		} catch (SpddExceptions e) {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ERROR_VALIDACION,
					PaqueteMensajeEnum.ERRORES, respuesta.getLenguaje()));
			spddLogger.mensajeLogger(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, respuesta.getLenguaje()),
					NHSPDDConstantes.SEVERIDAD_FATAL, EntidadAdministracionController.class);
		}
		return respuesta;
	}

	@GetMapping("/administracion/obtener_por_id/{codigo}")
	@Override
	public EntidadDTO obtenerPorId(@PathVariable("codigo") String codigo) {
		EntidadDTO respuesta = new EntidadDTO();
		try {
			respuesta = consultar.obtenerPorId(codigo);
		} catch (SpddExceptions e) {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ERROR_VALIDACION,
					PaqueteMensajeEnum.ERRORES, respuesta.getLenguaje()));
			spddLogger.mensajeLogger(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, respuesta.getLenguaje()),
					NHSPDDConstantes.SEVERIDAD_FATAL, EntidadAdministracionController.class);
		}
		return respuesta;
	}
	
	@GetMapping("/administracion/obtener_por_nombre/{nombre}")
	@Override
	public EntidadDTO obtenerPorNombreEntidad(@PathVariable("nombre")String nombre) {
		EntidadDTO respuesta = new EntidadDTO();
		try {
			respuesta = consultar.obtenerPorNombre(nombre);
		} catch (SpddExceptions e) {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ERROR_VALIDACION,
					PaqueteMensajeEnum.ERRORES, respuesta.getLenguaje()));
			spddLogger.mensajeLogger(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, respuesta.getLenguaje()),
					NHSPDDConstantes.SEVERIDAD_FATAL, EntidadAdministracionController.class);
		}
		return respuesta;
	}

	
	@GetMapping(NHSPDDConstantes.CORE_CONTROLLER_ADMINISTRACION_VALIDAR_Y_REGISTRAR_ENTIDAD)
	@Override
	public EntidadDTO validarYRegistrarEntidadNoExistente(@PathVariable("codigo") String codigo) {
		EntidadDTO respuesta = new EntidadDTO();
		try {
			respuesta = consultar.validarYRegistrarEntidadNoExistente(codigo);
		} catch (SpddExceptions e) {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ERROR_VALIDACION,
					PaqueteMensajeEnum.ERRORES, respuesta.getLenguaje()));
			spddLogger.mensajeLogger(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, respuesta.getLenguaje()),
					NHSPDDConstantes.SEVERIDAD_FATAL, EntidadAdministracionController.class);
		}
		return respuesta;
	}
}
