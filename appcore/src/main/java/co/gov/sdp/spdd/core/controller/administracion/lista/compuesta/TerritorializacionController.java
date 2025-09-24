package co.gov.sdp.spdd.core.controller.administracion.lista.compuesta;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

import co.gov.sdp.nhspdd.common.dto.CampoValidacionDTO;
import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.dto.RespuestaDTO;
import co.gov.sdp.nhspdd.common.dto.TerritorializacionDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.nhspdd.common.util.NHSPDDMensajes;
import co.gov.sdp.nhspdd.common.util.PaqueteMensajeEnum;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.nhspdd.common.validador.NHSPDDCamposValidar;
import co.gov.sdp.nhspdd.common.validador.NHSPDDReglasValidacion;
import co.gov.sdp.nhspdd.common.validador.NHSPDDResultadoValidacion;
import co.gov.sdp.spdd.core.icontroller.administracion.lista.compuesta.ITerritorializacionController;
import co.gov.sdp.spdd.core.iservice.administracion.lista.compuesta.ITerritorializacionConsultar;
import co.gov.sdp.spdd.core.iservice.administracion.lista.compuesta.ITerritorializacionModificar;
import co.gov.sdp.spdd.core.iservice.administracion.lista.compuesta.ITerritorializacionRegistrar;

/**
 * Clase que implementa metodos y direccionamientos de territorializacion
 *
 * @author Bryan Munoz
 *
 */
@RestController
public class TerritorializacionController implements ITerritorializacionController {

	// Servicio para realizar consultas
	@Autowired
	ITerritorializacionConsultar consultar;

	// Servicio para realizar modificaciones
	@Autowired
	ITerritorializacionModificar modificar;

	// Servicio para realizar registros
	@Autowired
	ITerritorializacionRegistrar registrar;

	/**
	 * Logger
	 */
	@Autowired
	SPDDLogger spddLogger;

	/**
	 * Implementacion del metodo registrarTerritorializacion
	 * @throws JsonProcessingException 
	 *
	 * @see co.gov.sdp.spdd.core.icontroller.administracion.lista.compuesta.ITerritorializacionController.registrarTerritorializacion
	 */
	@PostMapping(NHSPDDConstantes.CORE_CONTROLLER_ADMINISTRACION_LISTA_COMPUESTA_REGISTRAR_TERRITORIALIZACION)
	@Override
	public TerritorializacionDTO registrarTerritorializacion(@RequestBody TerritorializacionDTO territorializacionDTO) throws SpddExceptions, JsonProcessingException {
		territorializacionDTO.setEstado(1);
		territorializacionDTO.setIdTerritorializacion(null);
		TerritorializacionDTO respuesta = new TerritorializacionDTO();
		Map<String, Boolean> camposAValidar = NHSPDDCamposValidar.registrarTerritorializacion();
		NHSPDDResultadoValidacion resultadoValidacion = NHSPDDReglasValidacion
				.validarTerritorializacion(territorializacionDTO, camposAValidar);
		try {
			if (resultadoValidacion.esValido()) {
				respuesta = registrar.registrarTerritorializacion(territorializacionDTO);
			} else {
				mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
						NHSPDDConstantes.MENSAJE_ERROR_VALIDACION, PaqueteMensajeEnum.ERRORES, respuesta.getLenguaje(),
						resultadoValidacion.getMsgCampoValidacion());
				mensajeLogger(respuesta.getMsgRespuesta(), NHSPDDConstantes.SEVERIDAD_ERROR,
						TerritorializacionController.class, respuesta.getLenguaje());
			}
		} catch (SpddExceptions e) {
			mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDConstantes.MENSAJE_ERROR_VALIDACION, PaqueteMensajeEnum.ERRORES, null, null);
			mensajeLogger(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, NHSPDDConstantes.SEVERIDAD_FATAL,
					TerritorializacionController.class, respuesta.getLenguaje());
		}

		return respuesta;
	}

	/**
	 * Implementacion del metodo modificarTerritorializacion
	 *
	 * @see co.gov.sdp.spdd.core.icontroller.administracion.lista.compuesta.ITerritorializacionController.modificarTerritorializacion
	 */
	@PutMapping(NHSPDDConstantes.CORE_CONTROLLER_ADMINISTRACION_LISTA_COMPUESTA_MODIFICAR_TERRITORIALIZACION)
	@Override
	public TerritorializacionDTO modificarTerritorializacion(@RequestBody TerritorializacionDTO territorializacionDTO) {
		TerritorializacionDTO respuesta = new TerritorializacionDTO();
		Map<String, Boolean> camposAValidar = NHSPDDCamposValidar.modificarTerritorializacion();
		NHSPDDResultadoValidacion resultadoValidacion = NHSPDDReglasValidacion
				.validarTerritorializacion(territorializacionDTO, camposAValidar);
		try {
			if (resultadoValidacion.esValido()) {
				respuesta = modificar.modificarTerritorializacion(territorializacionDTO);
			} else {
				mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
						NHSPDDConstantes.MENSAJE_ERROR_VALIDACION, PaqueteMensajeEnum.ERRORES, respuesta.getLenguaje(),
						resultadoValidacion.getMsgCampoValidacion());
				mensajeLogger(respuesta.getMsgRespuesta(), NHSPDDConstantes.SEVERIDAD_ERROR,
						TerritorializacionController.class, respuesta.getLenguaje());
			}
		} catch (SpddExceptions e) {
			mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDConstantes.MENSAJE_ERROR_VALIDACION, PaqueteMensajeEnum.ERRORES, null, null);
			mensajeLogger(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, NHSPDDConstantes.SEVERIDAD_FATAL,
					TerritorializacionController.class, respuesta.getLenguaje());
		}
		return respuesta;
	}

	/**
	 * Implementacion del metodo modificarEstadoTerritorializacion
	 *
	 * @see co.gov.sdp.spdd.core.icontroller.administracion.lista.compuesta.ITerritorializacionController.modificarEstadoTerritorializacion
	 */
	@PutMapping(NHSPDDConstantes.CORE_CONTROLLER_ADMINISTRACION_LISTA_COMPUESTA_MODIFICAR_ESTADO_TERRITORIALIZACION)
	@Override
	public TerritorializacionDTO modificarEstadoTerritorializacion(@PathVariable("id") Long id) {
		TerritorializacionDTO respuesta = new TerritorializacionDTO();
		try {
			respuesta = modificar.modificarEstadoTerritorializacion(id);
		} catch (SpddExceptions e) {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ERROR_VALIDACION,
					PaqueteMensajeEnum.ERRORES, null));
			spddLogger.mensajeLogger(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, respuesta.getLenguaje()),
					NHSPDDConstantes.SEVERIDAD_FATAL, TerritorializacionController.class);
		}
		return respuesta;
	}

	/**
	 * Implementacion del metodo obtenerTodos
	 * @throws JsonProcessingException 
	 *
	 * @see co.gov.sdp.spdd.core.icontroller.administracion.lista.compuesta.ITerritorializacionController.obtenerTodos
	 */
	@PostMapping(NHSPDDConstantes.CORE_CONTROLLER_ADMINISTRACION_LISTA_COMPUESTA_OBTENER_TODOS_TERRITORIALIZACION)
	@Override
	public GenericoDTO obtenerTodos(@RequestBody TerritorializacionDTO peticion) throws JsonProcessingException {
		GenericoDTO respuesta = new GenericoDTO();
		try {
			respuesta = consultar.obtenerPaginado(peticion);
		} catch (SpddExceptions e) {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ERROR_VALIDACION,
					PaqueteMensajeEnum.ERRORES, null));
			spddLogger.mensajeLogger(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, respuesta.getLenguaje()),
					NHSPDDConstantes.SEVERIDAD_FATAL, TerritorializacionController.class);
		}
		return respuesta;
	}

	@GetMapping(NHSPDDConstantes.CORE_CONTROLLER_ADMINISTRACION_LISTA_COMPUESTA_OBTENER_TODOS_TERRITORIALIZACION_V2)
	@Override
	public GenericoDTO obtenerTerritorializacionTodos() throws SpddExceptions {
		GenericoDTO respuesta = new GenericoDTO();
		try {
			respuesta = consultar.obtenerTodos();
		} catch (SpddExceptions e) {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ERROR_VALIDACION,
					PaqueteMensajeEnum.ERRORES, null));
			spddLogger.mensajeLogger(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, respuesta.getLenguaje()),
					NHSPDDConstantes.SEVERIDAD_FATAL, TerritorializacionController.class);
		}
		return respuesta;
	}

	/**
	 * Metodo privado que permite setiar los valores necesarios para mostrar el
	 * mensaje correspondiente en cada uno de los casos para la respuesta de las
	 * peticiones
	 * 
	 * @param respuesta             objeto al cual se le va a setiar el mensaje
	 * @param codigoRespuesta       objeto de tipo Integer que representa el codigo
	 *                              de la respuesta
	 * @param msgRespuesta          String que representa el mensaje de respuesta
	 *                              que se va a retornar
	 * @param paqueteMensaje        objeto de tipo PaqueteMensajeEnum que representa
	 *                              el paquete donde se encuentra el mensaje
	 * @param lenguaje
	 * @param getMsgCampoValidacion lista de mensaje de las validaciones
	 */
	private void mensajeRespuesta(RespuestaDTO respuesta, Integer codigoRespuesta, String msgRespuesta,
			PaqueteMensajeEnum paqueteMensaje, String lenguaje, List<CampoValidacionDTO> getMsgCampoValidacion) {
		respuesta.setCodigoRespuesta(codigoRespuesta);
		respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(msgRespuesta, paqueteMensaje, lenguaje));

		respuesta.setLstCampoValidacion(getMsgCampoValidacion);

	}

	/**
	 * Metodo privado que permite setiar los valores necesarios para el log.
	 * 
	 * @param msgLogger      string que represente el mensaje que tendra el log
	 * @param severidadLoger String que representa la severidad del log
	 * @param nombreClase    String que representa el nombre de la clase donde se
	 *                       presenta el log
	 * @param lenguaje
	 */
	private void mensajeLogger(String msgLogger, String severidadLoger, Class<?> nombreClase, String lenguaje) {
		spddLogger.mensajeLogger(NHSPDDMensajes.obtenerMensaje(msgLogger, lenguaje), severidadLoger, nombreClase);
	}

	@GetMapping(NHSPDDConstantes.CORE_CONTROLLER_ADMINISTRACION_LISTA_COMPUESTA_OBTENER_POR_LOCALIDAD_TERR)
	@Override
	public GenericoDTO obtenerTerritorializacionPorLocalidad()
			throws SpddExceptions {
		GenericoDTO respuesta = new GenericoDTO();
		try {
			respuesta = consultar.obtenerTerPorLocalidad();
		} catch (SpddExceptions e) {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ERROR_VALIDACION,
					PaqueteMensajeEnum.ERRORES, null));
			spddLogger.mensajeLogger(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, respuesta.getLenguaje()),
					NHSPDDConstantes.SEVERIDAD_FATAL, TerritorializacionController.class);
		}
		return respuesta;
	}
	@GetMapping(NHSPDDConstantes.CORE_CONTROLLER_ADMINISTRACION_LISTA_COMPUESTA_OBTENER_POR_ID_LOCALIDAD_TERR)
	@Override
	public GenericoDTO obtenerTerritoritoriosPorIdLocalidad(@PathVariable("id")Long idLocalidad) {
		
		GenericoDTO respuesta = new GenericoDTO();
		try {
			respuesta = consultar.obtenerTerritoriosPorIdLocalidad(idLocalidad);
		} catch (SpddExceptions e) {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ERROR_VALIDACION,
					PaqueteMensajeEnum.ERRORES, null));
			spddLogger.mensajeLogger(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, respuesta.getLenguaje()),
					NHSPDDConstantes.SEVERIDAD_FATAL, TerritorializacionController.class);
		}
		return respuesta;
	}

}
