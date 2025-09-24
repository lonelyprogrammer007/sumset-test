package co.gov.sdp.spdd.core.controller.presupuesto;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

import co.gov.sdp.nhspdd.common.dto.CampoValidacionDTO;
import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.dto.InformacionPresupuestalDTO;
import co.gov.sdp.nhspdd.common.dto.RespuestaDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.nhspdd.common.util.NHSPDDMensajes;
import co.gov.sdp.nhspdd.common.util.PaqueteMensajeEnum;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.nhspdd.common.validador.NHSPDDCamposValidar;
import co.gov.sdp.nhspdd.common.validador.NHSPDDReglasValidacion;
import co.gov.sdp.nhspdd.common.validador.NHSPDDResultadoValidacion;
import co.gov.sdp.spdd.core.controller.estado.servicio.EstadoServicioController;
import co.gov.sdp.spdd.core.icontroller.presupuesto.IInformacionPresupuestalPresupuestoController;
import co.gov.sdp.spdd.core.iservice.presupuesto.IInformacionPresupuestalPresupuestoConsultar;
import co.gov.sdp.spdd.core.iservice.presupuesto.IInformacionPresupuestalPresupuestoEliminar;
import co.gov.sdp.spdd.core.iservice.presupuesto.IInformacionPresupuestalPresupuestoModificar;
import co.gov.sdp.spdd.core.iservice.presupuesto.IInformacionPresupuestalPresupuestoRegistrar;

/**
 * Clase para manejar las peticiones relacionadas con equipamiento para el
 * modulo administracion
 *
 * @author Raul Londono Murillo
 *
 */
@RestController
public class InformacionPresupuestalPresupuestoController implements IInformacionPresupuestalPresupuestoController {

	// Servicio de consulta para informacion presupuestal
	@Autowired
	IInformacionPresupuestalPresupuestoConsultar consultar;

	// Servicio de eliminacion para informacion presupuestal
	@Autowired
	IInformacionPresupuestalPresupuestoEliminar eliminar;

	// Servicio de modificacion para informacion presupuestal
	@Autowired
	IInformacionPresupuestalPresupuestoModificar modificar;

	// Servicio de registro para informacion presupuestal
	@Autowired
	IInformacionPresupuestalPresupuestoRegistrar registrar;

	/**
	 * Logger
	 */
	@Autowired
	SPDDLogger spddLogger;

	/**
	 * Implementacion del metodo crearInformacionPresupuestal
	 * @throws JsonProcessingException 
	 *
	 * @see co.gov.sdp.nhspdd.core.icontroller.administracion.lista.compuesta.ArchivoProcesadoCargaController.crearEquipamiento
	 */
	@PostMapping(NHSPDDConstantes.CORE_CONTROLLER_PRESUPUESTO_POST_CREAR_INFORMACION_PRESUPUESTAL)
	@Override
	public InformacionPresupuestalDTO crearInformacionPresupuestal(@RequestBody InformacionPresupuestalDTO peticion) throws JsonProcessingException {
		InformacionPresupuestalDTO respuesta = new InformacionPresupuestalDTO();
		Map<String, Boolean> camposAValidar = NHSPDDCamposValidar.crearInformacionPresupuestal();
		NHSPDDResultadoValidacion resultadoValidacion = NHSPDDReglasValidacion.validarInformacionPresupuestal(peticion,
				camposAValidar);
		try {
			if (resultadoValidacion.esValido()) {
				respuesta = registrar.crearInformacionPresupuestal(peticion);
			} else {
				mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO, NHSPDDConstantes.MENSAJE_ERROR_VALIDACION, PaqueteMensajeEnum.ERRORES, respuesta.getLenguaje(), resultadoValidacion.getMsgCampoValidacion());
				mensajeLogger(respuesta.getMsgRespuesta(), NHSPDDConstantes.SEVERIDAD_ERROR, InformacionPresupuestalPresupuestoController.class, respuesta.getLenguaje());
			}
		} catch (SpddExceptions e) {
			mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO, NHSPDDConstantes.MENSAJE_ERROR_VALIDACION, PaqueteMensajeEnum.ERRORES, null, null);
			mensajeLogger(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, NHSPDDConstantes.SEVERIDAD_FATAL, InformacionPresupuestalPresupuestoController.class, respuesta.getLenguaje());
		}

		return respuesta;
	}

	/**
	 * Implementacion del metodo modificarInformacionPresupuestal
	 *
	 * @see co.gov.sdp.nhspdd.core.icontroller.administracion.lista.compuesta.ArchivoProcesadoCargaController.modificarEquipamiento
	 */
	@PutMapping(NHSPDDConstantes.CORE_CONTROLLER_PRESUPUESTO_PUT_MODIFICAR_INFORMACION_PRESUPUESTAL)
	@Override
	public InformacionPresupuestalDTO modificarInformacionPresupuestal(
			@RequestBody InformacionPresupuestalDTO peticion) {
		InformacionPresupuestalDTO respuesta = new InformacionPresupuestalDTO();
		Map<String, Boolean> camposAValidar = NHSPDDCamposValidar.modificarInformacionPresupuestal();
		NHSPDDResultadoValidacion resultadoValidacion = NHSPDDReglasValidacion.validarInformacionPresupuestal(peticion,
				camposAValidar);
		try {
			if (resultadoValidacion.esValido()) {
				respuesta = modificar.modificarInformacionPresupuestal(peticion);
			} else {
				mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO, NHSPDDConstantes.MENSAJE_ERROR_VALIDACION, PaqueteMensajeEnum.ERRORES, respuesta.getLenguaje(), resultadoValidacion.getMsgCampoValidacion());
				mensajeLogger(respuesta.getMsgRespuesta(), NHSPDDConstantes.SEVERIDAD_ERROR, InformacionPresupuestalPresupuestoController.class, respuesta.getLenguaje());
			}
		} catch (SpddExceptions e) {
			mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO, NHSPDDConstantes.MENSAJE_ERROR_VALIDACION, PaqueteMensajeEnum.ERRORES, null, null);
			mensajeLogger(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, NHSPDDConstantes.SEVERIDAD_FATAL, InformacionPresupuestalPresupuestoController.class, respuesta.getLenguaje());
		}
		return respuesta;
	}

	/**
	 * Implementacion del metodo obtenerInformacionPresupuestalTodos
	 *
	 * @see co.gov.sdp.nhspdd.core.icontroller.administracion.lista.compuesta.ArchivoProcesadoCargaController.obtenerEquipamientoTodos
	 */
//	@GetMapping(NHSPDDConstantes.CORE_CONTROLLER_PRESUPUESTO_GET_OBTENER_INFORMACION_PRESUPUESTAL_TODOS)
//	@Override
//	public GenericoDTO obtenerInformacionPresupuestalTodos() {
//		GenericoDTO respuesta = new GenericoDTO();
//		try {
//			respuesta = consultar.obtenerInformacionPresupuestalTodos();
//		} catch (SpddExceptions e) {
//			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
//			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ERROR_VALIDACION,
//					PaqueteMensajeEnum.ERRORES, null));
//			spddLogger.mensajeLogger(
//					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, respuesta.getLenguaje()),
//					NHSPDDConstantes.SEVERIDAD_FATAL, InformacionPresupuestalPresupuestoController.class);
//		}
//		return respuesta;
//	}

	/**
	 * @throws JsonProcessingException 
	 * 
	 */
	@PostMapping(NHSPDDConstantes.CORE_CONTROLLER_PRESUPUESTO_GET_OBTENER_INFORMACION_PRESUPUESTAL_TODOS)
	@Override
	public GenericoDTO obtenerTodos(@RequestBody InformacionPresupuestalDTO peticion) throws JsonProcessingException {
		GenericoDTO respuesta = new GenericoDTO();
		try {
			respuesta = consultar.obtenerPaginado(peticion);
		} catch (SpddExceptions e) {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ERROR_VALIDACION,
					PaqueteMensajeEnum.ERRORES, null));
			spddLogger.mensajeLogger(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, respuesta.getLenguaje()),
					NHSPDDConstantes.SEVERIDAD_FATAL, EstadoServicioController.class);
		}
		return respuesta;
	}

	/**
	 * 
	 */
	@GetMapping(NHSPDDConstantes.CORE_CONTROLLER_PRESUPUESTO_GET_OBTENER_INFORMACION_PRESUPUESTAL_PLAN_DESAROLLO)
	@Override
	public GenericoDTO obtenerInformacionPresupuestalPorPlanDesarrollo(@PathVariable("codigo") Long codigo)
			throws SpddExceptions {
		GenericoDTO respuesta = new GenericoDTO();
		try {
			respuesta = consultar.obtenerInformacionPresupuestalPorPlanDesarrollo(codigo);
		} catch (SpddExceptions e) {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ERROR_VALIDACION,
					PaqueteMensajeEnum.ERRORES, null));
			spddLogger.mensajeLogger(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, respuesta.getLenguaje()),
					NHSPDDConstantes.SEVERIDAD_FATAL, EstadoServicioController.class);
		}
		return respuesta;
	}

	/**
	 * 
	 */
	@GetMapping(NHSPDDConstantes.CORE_CONTROLLER_PRESUPUESTO_GET_OBTENER_INFORMACION_PRESUPUESTAL_ENTIDAD)
	@Override
	public GenericoDTO obtenerInformacionPresupuestalPorEntidad(@PathVariable("codigo") Long codigo)
			throws SpddExceptions {
		GenericoDTO respuesta = new GenericoDTO();
		try {
			respuesta = consultar.obtenerInformacionPresupuestalPorEntidad(codigo);
		} catch (SpddExceptions e) {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ERROR_VALIDACION,
					PaqueteMensajeEnum.ERRORES, null));
			spddLogger.mensajeLogger(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, respuesta.getLenguaje()),
					NHSPDDConstantes.SEVERIDAD_FATAL, EstadoServicioController.class);
		}
		return respuesta;
	}

	@GetMapping("/obtener_por_archivo/{id}")
	@Override
	public GenericoDTO obtenerPorArchivoInfo(@PathVariable("id") Long id) {

		return consultar.obtenerPorArchivoInfo(id);
	}

	@DeleteMapping(NHSPDDConstantes.CORE_CONTROLLER_PRESUPUESTO_DELETE_ELIMINAR_INFORMACION_PRESUPUESTAL)
	@Override
	public InformacionPresupuestalDTO eliminarInformacionPresupuestal(@PathVariable("id") Long idInformacionPresupuestal) {
		InformacionPresupuestalDTO respuesta = new InformacionPresupuestalDTO();
		try {
			respuesta = eliminar.eliminarInformacionPresupuestal(idInformacionPresupuestal);
		} catch (SpddExceptions e) {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ERROR_SERVICIO_INTERNO,
					PaqueteMensajeEnum.ERRORES, null));
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
