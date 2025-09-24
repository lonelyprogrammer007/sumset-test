package co.gov.sdp.spdd.core.controller.administracion.ls;

import java.util.List;
import java.util.Map;

import org.hibernate.exception.GenericJDBCException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

import co.gov.sdp.nhspdd.common.dto.ArgumentoListaSimpleDTO;
import co.gov.sdp.nhspdd.common.dto.CampoValidacionDTO;
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
import co.gov.sdp.spdd.core.icontroller.administracion.ls.IArgumentoListaSimpleController;
import co.gov.sdp.spdd.core.iservice.administracion.ls.IArgumentoListaSimpleConsultar;
import co.gov.sdp.spdd.core.iservice.administracion.ls.IArgumentoListaSimpleModificar;
import co.gov.sdp.spdd.core.iservice.administracion.ls.IArgumentoListaSimpleRegistrar;

/**
 * Clase implementa los metodos que maneja el controlador de argumento lista
 * simple
 *
 * @author Bryan Munoz
 *
 */
@RestController
public class ArgumentoListaSimpleController implements IArgumentoListaSimpleController {

	/**
	 * Objeto que me permite traer los metodos de registrar
	 */
	@Autowired
	IArgumentoListaSimpleRegistrar registrar;

	/**
	 * Objeto que me permite traer los metodos de consultas
	 */
	@Autowired
	IArgumentoListaSimpleConsultar consultar;

	/**
	 * Objeto que me permite traer los metodos de modificar
	 */
	@Autowired
	IArgumentoListaSimpleModificar modificar;

	/**
	 * Logger
	 */
	@Autowired
	SPDDLogger spddLogger;

	/**
	 * Implementacion del metodo registrarArgumentoListaSimple
	 * @throws JsonProcessingException 
	 *
	 * @see co.gov.sdp.spdd.core.icontroller.administracion.ls.IArgumentoListaSimpleController.registrarArgumentoListaSimple
	 */
	@PostMapping(NHSPDDConstantes.CORE_CONTROLLER_ADMINISTRACION_LISTA_SIMPLE_REGISTRAR_ARGUMENTO_LISTA_SIMPLE)
	@Override
	public ArgumentoListaSimpleDTO registrarArgumentoListaSimple(
			@RequestBody ArgumentoListaSimpleDTO argumentoListaSimpleDTO) throws JsonProcessingException {
		argumentoListaSimpleDTO.setEstado(1);
		ArgumentoListaSimpleDTO respuesta = new ArgumentoListaSimpleDTO();
		Map<String, Boolean> camposAValidar = NHSPDDCamposValidar.registrarArgumentoListaSimple();
		NHSPDDResultadoValidacion resultadoValidacion = NHSPDDReglasValidacion
				.validarArgumentoListaSimple(argumentoListaSimpleDTO, camposAValidar);
		try {
			if (resultadoValidacion.esValido()) {
				respuesta = registrar.registrarArgumentoListaSimple(argumentoListaSimpleDTO);
			} else {
				mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO, NHSPDDConstantes.MENSAJE_ERROR_VALIDACION, PaqueteMensajeEnum.ERRORES, respuesta.getLenguaje(), resultadoValidacion.getMsgCampoValidacion());
				mensajeLogger(respuesta.getMsgRespuesta(), NHSPDDConstantes.SEVERIDAD_ERROR, ArgumentoListaSimpleController.class, respuesta.getLenguaje());
			}
		} catch ( GenericJDBCException | SpddExceptions e) {
			e.printStackTrace();
			mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO, NHSPDDConstantes.MENSAJE_ERROR_VALIDACION, PaqueteMensajeEnum.ERRORES, null, null);
			mensajeLogger(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, NHSPDDConstantes.SEVERIDAD_FATAL, ArgumentoListaSimpleController.class, respuesta.getLenguaje());
			respuesta.setMsgRespuesta("Ha ocurrido un error");
		}

		return respuesta;
	}

	/**
	 * Implementacion del metodo modificarArgumentoListaSimple
	 * @throws JsonProcessingException 
	 *
	 * @see co.gov.sdp.spdd.core.icontroller.administracion.ls.IArgumentoListaSimpleController.modificarArgumentoListaSimple
	 */
	@PutMapping(NHSPDDConstantes.CORE_CONTROLLER_ADMINISTRACION_LISTA_SIMPLE_MODIFICAR_ARGUMENTO_LISTA_SIMPLE)
	@Override
	public ArgumentoListaSimpleDTO modificarArgumentoListaSimple(
			@RequestBody ArgumentoListaSimpleDTO argumentoListaSimpleDTO) throws JsonProcessingException {
		ArgumentoListaSimpleDTO respuesta = new ArgumentoListaSimpleDTO();
		Map<String, Boolean> camposAValidar = NHSPDDCamposValidar.modificarArgumentoListaSimple();
		NHSPDDResultadoValidacion resultadoValidacion = NHSPDDReglasValidacion
				.validarArgumentoListaSimple(argumentoListaSimpleDTO, camposAValidar);
		try {
			if (resultadoValidacion.esValido()) {
				respuesta = modificar.modificarArgumento(argumentoListaSimpleDTO);
			} else {
				mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO, NHSPDDConstantes.MENSAJE_ERROR_VALIDACION, PaqueteMensajeEnum.ERRORES, respuesta.getLenguaje(), resultadoValidacion.getMsgCampoValidacion());
				mensajeLogger(respuesta.getMsgRespuesta(), NHSPDDConstantes.SEVERIDAD_ERROR, ArgumentoListaSimpleController.class, respuesta.getLenguaje());
			}
		} catch (SpddExceptions | GenericJDBCException e) {
			mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO, NHSPDDConstantes.MENSAJE_ERROR_VALIDACION, PaqueteMensajeEnum.ERRORES, null, null);
			mensajeLogger(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, NHSPDDConstantes.SEVERIDAD_FATAL, ArgumentoListaSimpleController.class, respuesta.getLenguaje());
			
		}
		return respuesta;
	}

	/**
	 * Implementacion del metodo obtener argumento por nombre
	 *
	 * @see co.gov.sdp.spdd.core.icontroller.administracion.ls.IArgumentoListaSimpleController.obtenerArgumentoPorNombre
	 */
	@GetMapping(NHSPDDConstantes.CORE_CONTROLLER_ADMINISTRACION_GET_ARGUMENTO_LISTA_SIMPLE_POR_NOMBRE)
	@Override
	public GenericoDTO obtenerArgumentoPorNombre(@PathVariable(name = "nombre") String nombre) {
		GenericoDTO respuesta = new GenericoDTO();
		try {
			respuesta = consultar.obtenerPorNombre(nombre);
		} catch (SpddExceptions e) {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ERROR_VALIDACION,
					PaqueteMensajeEnum.ERRORES, null));
			spddLogger.mensajeLogger(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, respuesta.getLenguaje()),
					NHSPDDConstantes.SEVERIDAD_FATAL, ArgumentoListaSimpleController.class);
		}
		return respuesta;
	}



	/**
	 * 
	 */
	@PostMapping(NHSPDDConstantes.CORE_CONTROLLER_ADMINISTRACION_LISTA_SIMPLE_OBTENER_ARGUMENTO_LISTA_SIMPLE)
	@Override
	public GenericoDTO obtenerTodosPorFiltro(@RequestBody ArgumentoListaSimpleDTO peticion) throws SpddExceptions {
		GenericoDTO respuesta = new GenericoDTO();
		try {
			respuesta = consultar.obtenerPorFiltro(peticion);
		} catch (SpddExceptions e) {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ERROR_VALIDACION,
					PaqueteMensajeEnum.ERRORES, null));
			spddLogger.mensajeLogger(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, respuesta.getLenguaje()),
					NHSPDDConstantes.SEVERIDAD_FATAL, ArgumentoListaSimpleController.class);
		}
		return respuesta;
	}
	
	@GetMapping(NHSPDDConstantes.CORE_CONTROLLER_ADMINISTRACION_OBTENER_ARGUMENTO_LISTA_SIMPLE_POR_ID_PLAN)
	@Override
	public GenericoDTO consultarArgumentoPorIdPlanDesarrollo(@PathVariable("id") Long idPlan) throws SpddExceptions {
		GenericoDTO respuesta = new GenericoDTO();
		try {
			respuesta = consultar.consultarArgumentoPorIdPlanDesarrollo(idPlan);
		} catch (SpddExceptions e) {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ERROR_VALIDACION,
					PaqueteMensajeEnum.ERRORES, null));
			spddLogger.mensajeLogger(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, respuesta.getLenguaje()),
					NHSPDDConstantes.SEVERIDAD_FATAL, ArgumentoListaSimpleController.class);
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
