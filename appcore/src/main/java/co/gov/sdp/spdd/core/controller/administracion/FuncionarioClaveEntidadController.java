package co.gov.sdp.spdd.core.controller.administracion;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

import co.gov.sdp.nhspdd.common.dto.CampoValidacionDTO;
import co.gov.sdp.nhspdd.common.dto.FuncionarioClaveEntidadDTO;
import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.dto.RespuestaDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.nhspdd.common.util.NHSPDDMensajes;
import co.gov.sdp.nhspdd.common.util.PaqueteMensajeEnum;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.nhspdd.common.validador.NHSPDDCamposValidar;
import co.gov.sdp.nhspdd.common.validador.NHSPDDReglasValidacion;
import co.gov.sdp.nhspdd.common.validador.NHSPDDResultadoValidacion;
import co.gov.sdp.spdd.core.icontroller.administracion.IFuncionarioClaveEntidadController;
import co.gov.sdp.spdd.core.iservice.administracion.IFuncionarioClaveEntidadConsultar;
import co.gov.sdp.spdd.core.iservice.administracion.IFuncionarioClaveEntidadModificar;
import co.gov.sdp.spdd.core.iservice.administracion.IFuncionarioClaveEntidadRegistrar;

/**
 * Clase para manejar las peticiones relacionadas con funcionario clave entidad
 * para el modulo administracion
 *
 * @author Johan Sebastian Giraldo
 *
 */
@RestController
public class FuncionarioClaveEntidadController implements IFuncionarioClaveEntidadController {

	// Servicio de consulta
	@Autowired
	IFuncionarioClaveEntidadConsultar consultar;

	// Servicio de registro
	@Autowired
	IFuncionarioClaveEntidadRegistrar registrar;

	// Servicio de registro
	@Autowired
	IFuncionarioClaveEntidadModificar modificar;

	/**
	 * Logger
	 */
	@Autowired
	SPDDLogger spddLogger;

	/**
	 * 
	 */
	@PostMapping(NHSPDDConstantes.CORE_CONTROLLER_ADMINISTRACION_GET_OBTENER_POR_ID_FUNCIONARIO_CLAVE_ENTIDAD)
	@Override
	public GenericoDTO obtenerFuncionarioClaveEntidad(@RequestBody FuncionarioClaveEntidadDTO peticion) {
		GenericoDTO respuesta = new GenericoDTO();
		try {
			respuesta = consultar.obtenerPaginado(peticion);
		} catch (SpddExceptions e) {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ERROR_VALIDACION,
					PaqueteMensajeEnum.ERRORES, respuesta.getLenguaje()));
			spddLogger.mensajeLogger(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, respuesta.getLenguaje()),
					NHSPDDConstantes.SEVERIDAD_FATAL, FuncionarioClaveEntidadController.class);
		}
		return respuesta;
	}

	/**
	 * @throws JsonProcessingException 
	 * 
	 */
	@PostMapping(NHSPDDConstantes.CORE_CONTROLLER_ADMINISTRACION_POST_REGISTRAR_FUNCIONARIO_CLAVE_ENTIDAD)
	@Override
	public FuncionarioClaveEntidadDTO crearFuncionarioClaveEntidad(@RequestBody FuncionarioClaveEntidadDTO peticion)
			throws SpddExceptions, JsonProcessingException {
		FuncionarioClaveEntidadDTO respuesta = new FuncionarioClaveEntidadDTO();
		Map<String, Boolean> camposAValidar = NHSPDDCamposValidar.crearFuncionarioClaveEntidad();
		NHSPDDResultadoValidacion resultadoValidacion = NHSPDDReglasValidacion
				.validarCrearFuncionarioClaveEntidad(peticion, camposAValidar);
		try {
			if (resultadoValidacion.esValido()) {
				respuesta = registrar.crearFuncionarioClaveEntidad(peticion);
			} else {
				mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO, NHSPDDConstantes.MENSAJE_ERROR_VALIDACION, PaqueteMensajeEnum.ERRORES, peticion.getLenguaje(), resultadoValidacion.getMsgCampoValidacion());
				mensajeLogger(respuesta.getMsgRespuesta(), NHSPDDConstantes.SEVERIDAD_ERROR, FuncionarioClaveEntidadController.class, respuesta.getLenguaje());

			}
		} catch (SpddExceptions e) {
			mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO, NHSPDDConstantes.MENSAJE_ERROR_VALIDACION, PaqueteMensajeEnum.ERRORES, null, null);
			mensajeLogger(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, NHSPDDConstantes.SEVERIDAD_FATAL, FuncionarioClaveEntidadController.class, respuesta.getLenguaje());
		}
		return respuesta;
	}

	/**
	 * 
	 */
	@PostMapping(NHSPDDConstantes.CORE_CONTROLLER_ADMINISTRACION_POST_MODIFICAR_FUNCIONARIO_CLAVE_ENTIDAD)
	@Override
	public FuncionarioClaveEntidadDTO modificarFuncionarioClaveEntidad(@RequestBody FuncionarioClaveEntidadDTO peticion)
			throws SpddExceptions {
		FuncionarioClaveEntidadDTO respuesta = new FuncionarioClaveEntidadDTO();
		Map<String, Boolean> camposAValidar = NHSPDDCamposValidar.modificarFuncionarioClaveEntidad();
		NHSPDDResultadoValidacion resultadoValidacion = NHSPDDReglasValidacion
				.validarCrearFuncionarioClaveEntidad(peticion, camposAValidar);
		try {
			if (resultadoValidacion.esValido()) {
				respuesta = modificar.modificarFuncionarioClaveEntidad(peticion);
			} else {
				mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO, NHSPDDConstantes.MENSAJE_ERROR_VALIDACION, PaqueteMensajeEnum.ERRORES, peticion.getLenguaje(), resultadoValidacion.getMsgCampoValidacion());
				mensajeLogger(respuesta.getMsgRespuesta(), NHSPDDConstantes.SEVERIDAD_ERROR, FuncionarioClaveEntidadController.class, respuesta.getLenguaje());

			}
		} catch (SpddExceptions e) {
			mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO, NHSPDDConstantes.MENSAJE_ERROR_VALIDACION, PaqueteMensajeEnum.ERRORES, null, null);
			mensajeLogger(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, NHSPDDConstantes.SEVERIDAD_FATAL, FuncionarioClaveEntidadController.class, respuesta.getLenguaje());
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
