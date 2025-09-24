package co.gov.sdp.spdd.core.controller.administracion.lista.compuesta;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

import co.gov.sdp.nhspdd.common.dto.CampoValidacionDTO;
import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.dto.PotProyectoInstrumentoDTO;
import co.gov.sdp.nhspdd.common.dto.RespuestaDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.nhspdd.common.util.NHSPDDMensajes;
import co.gov.sdp.nhspdd.common.util.PaqueteMensajeEnum;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.nhspdd.common.validador.NHSPDDCamposValidar;
import co.gov.sdp.nhspdd.common.validador.NHSPDDReglasValidacion;
import co.gov.sdp.nhspdd.common.validador.NHSPDDResultadoValidacion;
import co.gov.sdp.spdd.core.icontroller.administracion.lista.compuesta.IPotProyectoInstrumentoController;
import co.gov.sdp.spdd.core.iservice.administracion.lista.compuesta.IPotInstrumentoConsultar;
import co.gov.sdp.spdd.core.iservice.administracion.lista.compuesta.IPotObraConsultar;
import co.gov.sdp.spdd.core.iservice.administracion.lista.compuesta.IPotProyectoInstrumentoConsultar;
import co.gov.sdp.spdd.core.iservice.administracion.lista.compuesta.IPotProyectoInstrumentoModificar;
import co.gov.sdp.spdd.core.iservice.administracion.lista.compuesta.IPotProyectoInstrumentoRegistrar;
/**
 * Clase que implementa todos los metodos que maneja el controlador de
 * potProyetoInstrumento
 *
 * @author Bryan Munoz
 *
 */
@RestController
public class PotProyectoInstrumentoController implements IPotProyectoInstrumentoController {

	/**
	 * Objeto que tiene los metodos de consultas
	 */
	@Autowired
	IPotProyectoInstrumentoConsultar consultar;

	/**
	 * 
	 */
	@Autowired
	IPotObraConsultar potObraConsultar;

	/**
	 * 
	 */
	@Autowired
	IPotInstrumentoConsultar potInstrumentoConsultar;

	/**
	 * Objeto que tiene los metodos de registrar
	 */
	@Autowired
	IPotProyectoInstrumentoRegistrar registrar;

	/**
	 * Objeto que tiene los metodos de modificar
	 */
	@Autowired
	IPotProyectoInstrumentoModificar modificar;

	/**
	 * Logger
	 */
	@Autowired
	SPDDLogger spddLogger;

	/**
	 * Implementacion del metodo obtenerTodos
	 *
	 * @see co.gov.sdp.spdd.core.icontroller.administracion.lista.compuesta.IPotProyectoInstrumentoController.obtenerTodos
	 */
	@PostMapping(NHSPDDConstantes.CORE_CONTROLLER_ADMINISTRACION_LISTA_COMPUESTA_OBTENER_TODOS_POT_PROYECTO_INSTRUMENTO)
	@Override
	public GenericoDTO obtenerTodos(@RequestBody PotProyectoInstrumentoDTO peticion) {
		GenericoDTO respuesta = new GenericoDTO();
		try {
			respuesta = consultar.obtenerPaginado(peticion);
		} catch (SpddExceptions e) {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ERROR_VALIDACION,
					PaqueteMensajeEnum.ERRORES, null));
			spddLogger.mensajeLogger(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, respuesta.getLenguaje()),
					NHSPDDConstantes.SEVERIDAD_FATAL, PotProyectoInstrumentoController.class);
		}
		return respuesta;
	}

	/**
	 * 
	 */
	@GetMapping(NHSPDDConstantes.CORE_CONTROLLER_ADMINISTRACION_LISTA_COMPUESTA_OBTENER_TODOS_POT_OBRA)
	@Override
	public GenericoDTO obtenerPotObra() {
		GenericoDTO respuesta = new GenericoDTO();
		try {
			respuesta = potObraConsultar.obtenerPotObra();
		} catch (SpddExceptions e) {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ERROR_VALIDACION,
					PaqueteMensajeEnum.ERRORES, null));
			spddLogger.mensajeLogger(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, respuesta.getLenguaje()),
					NHSPDDConstantes.SEVERIDAD_FATAL, PotProyectoInstrumentoController.class);
		}
		return respuesta;
	}

	/**
	 * 
	 */
	@GetMapping(NHSPDDConstantes.CORE_CONTROLLER_ADMINISTRACION_LISTA_COMPUESTA_OBTENER_TODOS_POT_INSTRUMENTO)
	@Override
	public GenericoDTO obtenerPotInstrumento() {
		GenericoDTO respuesta = new GenericoDTO();
		try {
			respuesta = potInstrumentoConsultar.obtenerPotInstrumento();
		} catch (SpddExceptions e) {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ERROR_VALIDACION,
					PaqueteMensajeEnum.ERRORES, null));
			spddLogger.mensajeLogger(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, respuesta.getLenguaje()),
					NHSPDDConstantes.SEVERIDAD_FATAL, PotProyectoInstrumentoController.class);
		}

		return respuesta;
	}

	/**
	 * Implementacion del metodo registrarProyectoInstrumento
	 * @throws JsonProcessingException 
	 *
	 * @see co.gov.sdp.spdd.core.icontroller.administracion.lista.compuesta.IPotProyectoInstrumentoController.registrarProyectoInstrumento
	 */
	@PostMapping(NHSPDDConstantes.CORE_CONTROLLER_ADMINISTRACION_LISTA_COMPUESTA_REGISTRAR_POT_PROYECTO_INSTRUMENTO)
	@Override
	public PotProyectoInstrumentoDTO registrarProyectoInstrumento(
			@RequestBody PotProyectoInstrumentoDTO potProyectoInstrumentoDTO) throws JsonProcessingException {
		potProyectoInstrumentoDTO.setEstado(1);
		potProyectoInstrumentoDTO.setIdProyectoInstrumento(null);
		PotProyectoInstrumentoDTO respuesta = new PotProyectoInstrumentoDTO();
		Map<String, Boolean> camposAValidar = NHSPDDCamposValidar.registrarPotProyectoInstrumento();
		NHSPDDResultadoValidacion resultadoValidacion = NHSPDDReglasValidacion
				.validarPotProyectoInstrumento(potProyectoInstrumentoDTO, camposAValidar);
		try {
			if (resultadoValidacion.esValido()) {
				respuesta = registrar.registrarProyectoInstrumento(potProyectoInstrumentoDTO);
			} else {
				mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO, NHSPDDConstantes.MENSAJE_ERROR_VALIDACION, PaqueteMensajeEnum.ERRORES, respuesta.getLenguaje(), resultadoValidacion.getMsgCampoValidacion());
				mensajeLogger(respuesta.getMsgRespuesta(), NHSPDDConstantes.SEVERIDAD_ERROR, PotProyectoInstrumentoController.class, respuesta.getLenguaje());
			}
		} catch (SpddExceptions e) {
			mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO, NHSPDDConstantes.MENSAJE_ERROR_VALIDACION, PaqueteMensajeEnum.ERRORES, null, null);
			mensajeLogger(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, NHSPDDConstantes.SEVERIDAD_FATAL, PotProyectoInstrumentoController.class, respuesta.getLenguaje());
		}

		return respuesta;
	}

	/**
	 * Implementacion del metodo modificarProyectoInstrumento
	 *
	 * @see co.gov.sdp.spdd.core.icontroller.administracion.lista.compuesta.IPotProyectoInstrumentoController.ModificarProyectoInstrumento
	 */
	@PutMapping(NHSPDDConstantes.CORE_CONTROLLER_ADMINISTRACION_LISTA_COMPUESTA_MODIFICAR_POT_PROYECTO_INSTRUMENTO)
	@Override
	public PotProyectoInstrumentoDTO modificarProyectoInstrumento(
			@RequestBody PotProyectoInstrumentoDTO potProyectoInstrumentoDTO) {
		PotProyectoInstrumentoDTO respuesta = new PotProyectoInstrumentoDTO();
		Map<String, Boolean> camposAValidar = NHSPDDCamposValidar.modificarPotProyectoInstrumento();
		NHSPDDResultadoValidacion resultadoValidacion = NHSPDDReglasValidacion
				.validarPotProyectoInstrumento(potProyectoInstrumentoDTO, camposAValidar);
		try {
			if (resultadoValidacion.esValido()) {
				respuesta = modificar.modificarProyectoInstrumento(potProyectoInstrumentoDTO);
			} else {
				mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO, NHSPDDConstantes.MENSAJE_ERROR_VALIDACION, PaqueteMensajeEnum.ERRORES, respuesta.getLenguaje(), resultadoValidacion.getMsgCampoValidacion());
				mensajeLogger(respuesta.getMsgRespuesta(), NHSPDDConstantes.SEVERIDAD_ERROR, PotProyectoInstrumentoController.class, respuesta.getLenguaje());
			}
		} catch (SpddExceptions e) {
			mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO, NHSPDDConstantes.MENSAJE_ERROR_VALIDACION, PaqueteMensajeEnum.ERRORES, null, null);
			mensajeLogger(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, NHSPDDConstantes.SEVERIDAD_FATAL, PotProyectoInstrumentoController.class, respuesta.getLenguaje());
		}


		return respuesta;
	}

	/**
	
	
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
