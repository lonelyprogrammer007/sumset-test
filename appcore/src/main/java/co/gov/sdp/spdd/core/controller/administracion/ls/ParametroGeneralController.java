package co.gov.sdp.spdd.core.controller.administracion.ls;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.dto.ParametroGeneralDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.nhspdd.common.util.NHSPDDMensajes;
import co.gov.sdp.nhspdd.common.util.PaqueteMensajeEnum;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.nhspdd.common.validador.NHSPDDCamposValidar;
import co.gov.sdp.nhspdd.common.validador.NHSPDDReglasValidacion;
import co.gov.sdp.nhspdd.common.validador.NHSPDDResultadoValidacion;
import co.gov.sdp.spdd.core.icontroller.administracion.ls.IParametroGeneralController;
import co.gov.sdp.spdd.core.iservice.administracion.ls.IParametroGeneralModificar;
import co.gov.sdp.spdd.core.iservice.administracion.ls.IParanetroGeneralConsultar;

/**
 * Clase que implementa todos los metodos del controlador parametro general
 *
 * @author Bryan Munoz
 *
 */
@RestController
public class ParametroGeneralController implements IParametroGeneralController {

	// Servicio que permite realizar una consulta en la bd
	@Autowired
	IParanetroGeneralConsultar consultar;

	// Servicio que permite realizar una modificacion en la bd
	@Autowired
	IParametroGeneralModificar modificar;

	/**
	 * Logger
	 */
	@Autowired
	SPDDLogger spddLogger;

	/**
	 * Implementacion del metodo editarParametro
	 * @throws JsonProcessingException 
	 *
	 * @see co.gov.sdp.nhspdd.core.icontroller.administracion.ls.IparametroController.editarParametro
	 */
	@PutMapping(NHSPDDConstantes.CORE_CONTROLLER_ADMINISTRACION_LISTA_SIMPLE_MODIFICAR_PARAMETRO_GENERAL)
	@Override
	public ParametroGeneralDTO editarParametro(@RequestBody ParametroGeneralDTO peticion) throws JsonProcessingException {
		ParametroGeneralDTO respuesta = new ParametroGeneralDTO();
		Map<String, Boolean> camposAValidar = NHSPDDCamposValidar.modificarParametroGeneral();
		NHSPDDResultadoValidacion resultadoValidacion = NHSPDDReglasValidacion.validarParametroGeneral(peticion,
				camposAValidar);
		try {
			if (resultadoValidacion.esValido()) {
				respuesta = modificar.editarParametro(peticion);
			} else {
				respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
				respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ERROR_VALIDACION,
						PaqueteMensajeEnum.ERRORES, peticion.getLenguaje()));
				respuesta.setLstCampoValidacion(resultadoValidacion.getMsgCampoValidacion());
				spddLogger.mensajeLogger(
						NHSPDDMensajes.obtenerMensaje(respuesta.getMsgRespuesta(), respuesta.getLenguaje()),
						NHSPDDConstantes.SEVERIDAD_ERROR, ParametroGeneralController.class);
			}
		} catch (SpddExceptions e) {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ERROR_VALIDACION,
					PaqueteMensajeEnum.ERRORES, null));
			spddLogger.mensajeLogger(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, respuesta.getLenguaje()),
					NHSPDDConstantes.SEVERIDAD_FATAL, ParametroGeneralController.class);
		}
		return respuesta;
	}

	/**
	 * Implementacion del metodo obtenerTodos
	 *
	 * @see co.gov.sdp.nhspdd.core.icontroller.administracion.ls.IparametroController.obtenerTodos
	 */
	@PostMapping(NHSPDDConstantes.CORE_CONTROLLER_ADMINISTRACION_LISTA_SIMPLE_OBTENER_TODO_PARAMETRO_GENERAL)
	@Override
	public GenericoDTO obtenerTodos(@RequestBody ParametroGeneralDTO peticion) {
		GenericoDTO respuesta = new GenericoDTO();
		try {
			respuesta = consultar.obtenerPaginado(peticion);
		} catch (SpddExceptions e) {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ERROR_VALIDACION,
					PaqueteMensajeEnum.ERRORES, null));
			spddLogger.mensajeLogger(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, respuesta.getLenguaje()),
					NHSPDDConstantes.SEVERIDAD_FATAL, ParametroGeneralController.class);
		}
		return respuesta;
	}
}
