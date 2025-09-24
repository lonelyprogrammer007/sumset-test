package co.gov.sdp.spdd.core.controller.administracion.ls;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

import co.gov.sdp.nhspdd.common.dto.ConsecutivoDTO;
import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.nhspdd.common.util.NHSPDDMensajes;
import co.gov.sdp.nhspdd.common.util.PaqueteMensajeEnum;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.nhspdd.common.validador.NHSPDDCamposValidar;
import co.gov.sdp.nhspdd.common.validador.NHSPDDReglasValidacion;
import co.gov.sdp.nhspdd.common.validador.NHSPDDResultadoValidacion;
import co.gov.sdp.spdd.core.icontroller.administracion.ls.IConsecutivoController;
import co.gov.sdp.spdd.core.iservice.administracion.ls.IConsecutivoConsultar;
import co.gov.sdp.spdd.core.iservice.administracion.ls.IConsecutivoModificar;

/**
 * Clase que implementa los metodos asociados con el controlador de consecutivo
 *
 * @author Bryan Munoz
 *
 */
@RestController
public class ConsecutivoController implements IConsecutivoController {

	// Servicio que permite consultar uno o una lista de consecutivos
	@Autowired
	IConsecutivoConsultar consultar;

	// Servicio que permite modificar un consecutivo
	@Autowired
	IConsecutivoModificar modificar;

	/**
	 * Logger
	 */
	@Autowired
	SPDDLogger spddLogger;

	/**
	 * Implementacion del metodo editarConsecutivo
	 * 
	 * @throws JsonProcessingException
	 *
	 * @see co.gov.sdp.spdd.core.icontroller.administracion.ls.IConsecutivoController.editarConsecutivo
	 */
	@PutMapping(NHSPDDConstantes.CORE_CONTROLLER_ADMINISTRACION_LISTA_SIMPLE_MODIFICAR_CONSECUTIVO)
	@Override
	public ConsecutivoDTO editatConsecutivo(@RequestBody ConsecutivoDTO peticion) throws JsonProcessingException {
		ConsecutivoDTO respuesta = new ConsecutivoDTO();
		Map<String, Boolean> camposAValidar = NHSPDDCamposValidar.modificarConsecutivo();
		NHSPDDResultadoValidacion resultadoValidacion = NHSPDDReglasValidacion.validarConsecutivo(peticion,
				camposAValidar);
		try {
			if (resultadoValidacion.esValido()) {
				respuesta = modificar.modificarConsecutivo(peticion);
			} else {
				respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
				respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ERROR_VALIDACION,
						PaqueteMensajeEnum.ERRORES, peticion.getLenguaje()));
				respuesta.setLstCampoValidacion(resultadoValidacion.getMsgCampoValidacion());
				spddLogger.mensajeLogger(
						NHSPDDMensajes.obtenerMensaje(respuesta.getMsgRespuesta(), respuesta.getLenguaje()),
						NHSPDDConstantes.SEVERIDAD_ERROR, ConsecutivoController.class);
			}
		} catch (SpddExceptions e) {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ERROR_VALIDACION,
					PaqueteMensajeEnum.ERRORES, null));
			spddLogger.mensajeLogger(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, respuesta.getLenguaje()),
					NHSPDDConstantes.SEVERIDAD_FATAL, ConsecutivoController.class);
		}
		return respuesta;
	}

	/**
	 * Implementacion del metodo obtenerTodos
	 *
	 * @see co.gov.sdp.spdd.core.icontroller.administracion.ls.IConsecutivoController.obtenerTodos
	 */
	@PostMapping(NHSPDDConstantes.CORE_CONTROLLER_ADMINISTRACION_LISTA_SIMPLE_OBTENER_TODOS_CONSECUTIVO)
	@Override
	public GenericoDTO obtenerTodos(@RequestBody ConsecutivoDTO peticion) {
		GenericoDTO respuesta = new GenericoDTO();
		try {
			respuesta = consultar.obtenerPaginado(peticion);
		} catch (SpddExceptions e) {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ERROR_VALIDACION,
					PaqueteMensajeEnum.ERRORES, null));
			spddLogger.mensajeLogger(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, respuesta.getLenguaje()),
					NHSPDDConstantes.SEVERIDAD_FATAL, ConsecutivoController.class);
		}
		return respuesta;
	}

	@PostMapping(NHSPDDConstantes.CORE_CONTROLLER_ADMINISTRACION_LISTA_SIMPLE_OBTENER_CONSECUTIVO_POR_PLAN)
	@Override
	public ConsecutivoDTO obtenerConsecutivoPorPlanYEntidad(@RequestBody ConsecutivoDTO consecutivo) {
		ConsecutivoDTO respuesta = new ConsecutivoDTO();
		try {
			respuesta = consultar.obtenerConsecutivoPorPlanYEntidad(consecutivo);
		} catch (SpddExceptions e) {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ERROR_VALIDACION,
					PaqueteMensajeEnum.ERRORES, null));
			spddLogger.mensajeLogger(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, respuesta.getLenguaje()),
					NHSPDDConstantes.SEVERIDAD_FATAL, ConsecutivoController.class);
		}
		return respuesta;
	}
}
