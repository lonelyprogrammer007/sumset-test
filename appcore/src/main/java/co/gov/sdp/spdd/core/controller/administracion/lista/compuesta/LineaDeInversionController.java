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
import co.gov.sdp.nhspdd.common.dto.LineaDeInversionDTO;
import co.gov.sdp.nhspdd.common.dto.RespuestaDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.nhspdd.common.util.NHSPDDMensajes;
import co.gov.sdp.nhspdd.common.util.PaqueteMensajeEnum;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.nhspdd.common.validador.NHSPDDCamposValidar;
import co.gov.sdp.nhspdd.common.validador.NHSPDDReglasValidacion;
import co.gov.sdp.nhspdd.common.validador.NHSPDDResultadoValidacion;
import co.gov.sdp.spdd.core.icontroller.administracion.lista.compuesta.ILineaDeInversionController;
import co.gov.sdp.spdd.core.iservice.administracion.lista.compuesta.ILineaDeInversionConsultar;
import co.gov.sdp.spdd.core.iservice.administracion.lista.compuesta.ILineaDeInversionModificar;
import co.gov.sdp.spdd.core.iservice.administracion.lista.compuesta.ILineaDeInversionRegistrar;

/**
 * Esta clase implementa los metodos que se manejaran en el controlador
 *
 * @author Bryan Munoz
 *
 */
@RestController
public class LineaDeInversionController implements ILineaDeInversionController {

	// Objeto que permite realizar consultas de lineas de inversion
	@Autowired
	ILineaDeInversionConsultar consultar;

	// Objeto que permite realizar registros de lineas de inversion
	@Autowired
	ILineaDeInversionRegistrar registrar;

	// Objeto que permite realizar modificaciones de lineas de inversion
	@Autowired
	ILineaDeInversionModificar modificar;

	/**
	 * Logger
	 */
	@Autowired
	SPDDLogger spddLogger;

	/**
	 * Implementacion del metodo registrarLineaDeInversion
	 * @throws JsonProcessingException 
	 *
	 * @see co.gov.sdp.spdd.core.icontroller.administracion.lista.compuesta.ILineaDeInversionController.registrarLineaDeInversion
	 */
	@PostMapping(NHSPDDConstantes.CORE_CONTROLLER_ADMINISTRACION_LISTA_COMPUESTA_REGISTRAR_LINEA_INVERSION)
	@Override
	public LineaDeInversionDTO registrarLineaDeInversion(@RequestBody LineaDeInversionDTO lineaDeInversionDTO) throws JsonProcessingException {
		lineaDeInversionDTO.setEstado(NHSPDDConstantes.ESTADO_ACTIVO);
		lineaDeInversionDTO.setIdLineaInversion(null);
		LineaDeInversionDTO respuesta = new LineaDeInversionDTO();
		Map<String, Boolean> camposAValidar = NHSPDDCamposValidar.registrarLineaInverision();
		NHSPDDResultadoValidacion resultadoValidacion = NHSPDDReglasValidacion
				.validarLineaInversion(lineaDeInversionDTO, camposAValidar);
		try {
			if (resultadoValidacion.esValido()) {
				lineaDeInversionDTO.setIdLineaInversion(null);
				respuesta = registrar.registrarLineaDeInversion(lineaDeInversionDTO);
			} else {
				mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
						NHSPDDConstantes.MENSAJE_ERROR_VALIDACION, PaqueteMensajeEnum.ERRORES,
						lineaDeInversionDTO.getLenguaje(), resultadoValidacion.getMsgCampoValidacion());
				mensajeLogger(respuesta.getMsgRespuesta(), NHSPDDConstantes.SEVERIDAD_ERROR,
						LineaDeInversionController.class, respuesta.getLenguaje());
			}
		} catch (SpddExceptions e) {
			mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDConstantes.MENSAJE_ERROR_VALIDACION, PaqueteMensajeEnum.ERRORES, null, null);
			mensajeLogger(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, NHSPDDConstantes.SEVERIDAD_FATAL,
					LineaDeInversionController.class, respuesta.getLenguaje());
		}
		return respuesta;
	}

	/**
	 * Implementacion del metodo obtenerTodos
	 * @throws JsonProcessingException 
	 *
	 * @see co.gov.sdp.spdd.core.icontroller.administracion.lista.compuesta.ILineaDeInversionController.obtenerTodos
	 */
	@PostMapping(NHSPDDConstantes.CORE_CONTROLLER_ADMINISTRACION_LISTA_COMPUESTA_OBTENER_TODOS_LINEA_INVERSION)
	@Override
	public GenericoDTO obtenerTodos(@RequestBody LineaDeInversionDTO peticion) throws JsonProcessingException {
		GenericoDTO respuesta = new GenericoDTO();
		try {
			respuesta = consultar.obtenerPaginado(peticion);
		} catch (SpddExceptions e) {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ERROR_VALIDACION,
					PaqueteMensajeEnum.ERRORES, null));

			spddLogger.mensajeLogger(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, respuesta.getLenguaje()),
					NHSPDDConstantes.SEVERIDAD_FATAL, LineaDeInversionController.class);
		}
		return respuesta;
	}

	/**
	 * Implementacion del metodo modificarLineaInversion
	 *
	 * @see co.gov.sdp.spdd.core.icontroller.administracion.lista.compuesta.ILineaDeInversionController.modificarLineaDeInversion
	 */
	@PutMapping(NHSPDDConstantes.CORE_CONTROLLER_ADMINISTRACION_LISTA_COMPUESTA_MODIFICAR_LINEA_INVERSION)
	@Override
	public LineaDeInversionDTO modificarLineaDeInversion(@RequestBody LineaDeInversionDTO lineaDeInversionDTO) {
		LineaDeInversionDTO respuesta = new LineaDeInversionDTO();
		Map<String, Boolean> camposAValidar = NHSPDDCamposValidar.modificarLineaInversion();
		NHSPDDResultadoValidacion resultadoValidacion = NHSPDDReglasValidacion
				.validarLineaInversion(lineaDeInversionDTO, camposAValidar);
		try {
			if (resultadoValidacion.esValido()) {
				respuesta = modificar.modificarLineaInversion(lineaDeInversionDTO);
			} else {
				mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
						NHSPDDConstantes.MENSAJE_ERROR_VALIDACION, PaqueteMensajeEnum.ERRORES,
						lineaDeInversionDTO.getLenguaje(), resultadoValidacion.getMsgCampoValidacion());
				mensajeLogger(respuesta.getMsgRespuesta(), NHSPDDConstantes.SEVERIDAD_ERROR,
						LineaDeInversionController.class, respuesta.getLenguaje());
			}
		} catch (SpddExceptions e) {
			mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDConstantes.MENSAJE_ERROR_VALIDACION, PaqueteMensajeEnum.ERRORES, null, null);
			mensajeLogger(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, NHSPDDConstantes.SEVERIDAD_FATAL,
					LineaDeInversionController.class, respuesta.getLenguaje());
		}
		return respuesta;
	}

	/**
	 * Implementacion del metodo modificarEstadoLineaInversion
	 *
	 * @see co.gov.sdp.spdd.core.icontroller.administracion.lista.compuesta.ILineaDeInversionController.modificarEstadoLineaDeInversion
	 */
	@PutMapping(NHSPDDConstantes.CORE_CONTROLLER_ADMINISTRACION_LISTA_COMPUESTA_MODIFICAR_LINEA_INVERSION_ESTADO)
	@Override
	public LineaDeInversionDTO modificarEstadoLineaDeInversion(@PathVariable("id") Long id) {
		LineaDeInversionDTO respuesta = new LineaDeInversionDTO();
		try {
			respuesta = modificar.modificarEstadoLineaInversion(id);
		} catch (SpddExceptions e) {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ERROR_VALIDACION,
					PaqueteMensajeEnum.ERRORES, null));

			spddLogger.mensajeLogger(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, respuesta.getLenguaje()),
					NHSPDDConstantes.SEVERIDAD_FATAL, LineaDeInversionController.class);
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

	@GetMapping(NHSPDDConstantes.CORE_CONTROLLER_ADMINISTRACION_LISTA_COMPUESTA_OBTENER_LINEA_POR_SECTOR)
	@Override
	public GenericoDTO obtenerLineaPorSector(@PathVariable("sector") String sector) throws SpddExceptions {
		GenericoDTO respuesta = new GenericoDTO();
		try {
			respuesta = consultar.obtenerPorSector(sector);
		} catch (SpddExceptions e) {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ERROR_VALIDACION,
					PaqueteMensajeEnum.ERRORES, null));

			spddLogger.mensajeLogger(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, respuesta.getLenguaje()),
					NHSPDDConstantes.SEVERIDAD_FATAL, LineaDeInversionController.class);
		}
		return respuesta;
	}
}
