package co.gov.sdp.spdd.core.controller.administracion.ls;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import co.gov.sdp.nhspdd.common.dto.CampoValidacionDTO;
import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.dto.ListaSimpleDTO;
import co.gov.sdp.nhspdd.common.dto.RespuestaDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.nhspdd.common.util.NHSPDDMensajes;
import co.gov.sdp.nhspdd.common.util.PaqueteMensajeEnum;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.nhspdd.common.validador.NHSPDDCamposValidar;
import co.gov.sdp.nhspdd.common.validador.NHSPDDReglasValidacion;
import co.gov.sdp.nhspdd.common.validador.NHSPDDResultadoValidacion;
import co.gov.sdp.spdd.core.icontroller.administracion.ls.IListaSimpleController;
import co.gov.sdp.spdd.core.iservice.administracion.ls.IAdministracionListaSimpleConsultar;
import co.gov.sdp.spdd.core.iservice.administracion.ls.IAdministracionListaSimpleEliminar;
import co.gov.sdp.spdd.core.iservice.administracion.ls.IAdministracionListaSimpleModificar;
import co.gov.sdp.spdd.core.iservice.administracion.ls.IAdministracionListaSimpleRegistrar;

/**
 * Clase que implementa los de la interface IListaSimpleController y controla
 * los direccionamientos para peticiones al back end
 *
 * @author Bryan Munoz
 *
 */
@RestController
public class ListaSimpleController implements IListaSimpleController {

	// Servicio que permite consultar una lista simple
	@Autowired
	IAdministracionListaSimpleConsultar consultar;

	// Servicio que permite consultar una lista simple
	@Autowired
	IAdministracionListaSimpleEliminar eliminar;

	// Servicio que permite consultar una lista simple
	@Autowired
	IAdministracionListaSimpleRegistrar registrar;

	// Servicio que permite consultar una lista simple
	@Autowired
	IAdministracionListaSimpleModificar modificar;

	/**
	 * Logger
	 */
	@Autowired
	SPDDLogger spddLogger;

	/**
	 * Implementacion del metodo crearListaSimple
	 *
	 * @see co.gov.sdp.spdd.core.icontroller.administracion.ls.IListaSimpleController.crearListaSimple
	 */
	@PostMapping(NHSPDDConstantes.CORE_CONTROLLER_ADMINISTRACION_LISTA_SIMPLE_REGISTRAR_LISTA)
	@Override
	public ListaSimpleDTO crearListSimple(@RequestBody ListaSimpleDTO listaSimpleDTO) {
		ListaSimpleDTO respuesta = new ListaSimpleDTO();
		Map<String, Boolean> camposAValidar = NHSPDDCamposValidar.crearListaSimple();
		NHSPDDResultadoValidacion resultadoValidacion = NHSPDDReglasValidacion.validarListaSimple(listaSimpleDTO,
				camposAValidar);
		try {
			if (resultadoValidacion.esValido()) {
				respuesta = registrar.guardarListaSimple(listaSimpleDTO);
			} else {
				mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO, NHSPDDConstantes.MENSAJE_ERROR_VALIDACION, PaqueteMensajeEnum.ERRORES, respuesta.getLenguaje(), resultadoValidacion.getMsgCampoValidacion());
				mensajeLogger(respuesta.getMsgRespuesta(), NHSPDDConstantes.SEVERIDAD_ERROR, ListaSimpleController.class, respuesta.getLenguaje());
			}
		} catch (SpddExceptions e) {
			mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO, NHSPDDConstantes.MENSAJE_ERROR_VALIDACION, PaqueteMensajeEnum.ERRORES, null, null);
			mensajeLogger(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, NHSPDDConstantes.SEVERIDAD_FATAL, ListaSimpleController.class, respuesta.getLenguaje());
		}
		return respuesta;
	}

	/**
	 * Implementacion del metodo modificarListaSimple
	 * co.gov.sdp.nhspdd.core.icontroller.administracion.ls.IListaSimpleController.modificarListaSimple
	 */
	@PutMapping(NHSPDDConstantes.CORE_CONTROLLER_ADMINISTRACION_LISTA_SIMPLE_MODIFICAR_LISTA)
	@Override
	public ListaSimpleDTO modificarListaSimple(@RequestBody ListaSimpleDTO listaSimpleDTO) {
		ListaSimpleDTO respuesta = new ListaSimpleDTO();
		Map<String, Boolean> camposAValidar = NHSPDDCamposValidar.modificarListaSimple();
		NHSPDDResultadoValidacion resultadoValidacion = NHSPDDReglasValidacion.validarListaSimple(listaSimpleDTO,
				camposAValidar);
		try {
			if (resultadoValidacion.esValido()) {
				respuesta = modificar.modificarListaSimple(listaSimpleDTO);
			} else {
				mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO, NHSPDDConstantes.MENSAJE_ERROR_VALIDACION, PaqueteMensajeEnum.ERRORES, respuesta.getLenguaje(), resultadoValidacion.getMsgCampoValidacion());
				mensajeLogger(respuesta.getMsgRespuesta(), NHSPDDConstantes.SEVERIDAD_ERROR, ListaSimpleController.class, respuesta.getLenguaje());
			}
		} catch (SpddExceptions e) {
			mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO, NHSPDDConstantes.MENSAJE_ERROR_VALIDACION, PaqueteMensajeEnum.ERRORES, null, null);
			mensajeLogger(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, NHSPDDConstantes.SEVERIDAD_FATAL, ListaSimpleController.class, respuesta.getLenguaje());
		}

		return respuesta;
	}

	/**
	 * Implementacion del metodo obtenerPorId
	 *
	 * @see co.gov.sdp.spdd.core.icontroller.administracion.ls.IListaSimpleController.obtenerPorId
	 */
	@GetMapping(NHSPDDConstantes.CORE_CONTROLLER_ADMINISTRACION_GET_OBTENER_POR_ID_LISTA_SIMPLE)
	@Override
	public ListaSimpleDTO obtenerPorId(@PathVariable("id") Long id) {
		ListaSimpleDTO respuesta = new ListaSimpleDTO();
		try {
			respuesta = consultar.obtenerPorId(id);
		} catch (SpddExceptions e) {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ERROR_VALIDACION,
					PaqueteMensajeEnum.ERRORES, null));
			spddLogger.mensajeLogger(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, respuesta.getLenguaje()),
					NHSPDDConstantes.SEVERIDAD_FATAL, ListaSimpleController.class);
		}
		return respuesta;
	}

	/**
	 * Implementacion del metodo obtenerTodos
	 *
	 * @see co.gov.sdp.spdd.core.icontroller.administracion.ls.IListaSimpleController.obtenerPaginado
	 */
	@PostMapping(NHSPDDConstantes.CORE_CONTROLLER_ADMINISTRACION_LISTA_SIMPLE_OBTENER_TODOS)
	@Override
	public GenericoDTO paginado(@RequestBody ListaSimpleDTO peticion) {
		GenericoDTO respuesta = new GenericoDTO();
		try {
			respuesta = consultar.obtenerPaginado(peticion);
		} catch (SpddExceptions e) {
			spddLogger.mensajeLogger(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, respuesta.getLenguaje()),
					NHSPDDConstantes.SEVERIDAD_FATAL, ListaSimpleController.class);

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
