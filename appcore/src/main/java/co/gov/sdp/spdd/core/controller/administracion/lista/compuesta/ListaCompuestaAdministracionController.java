package co.gov.sdp.spdd.core.controller.administracion.lista.compuesta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.dto.ListaCompuestaDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.nhspdd.common.util.NHSPDDMensajes;
import co.gov.sdp.nhspdd.common.util.PaqueteMensajeEnum;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.spdd.core.icontroller.administracion.lista.compuesta.IListaCompuestaAdministracionController;
import co.gov.sdp.spdd.core.iservice.administracion.lista.compuesta.IListaCompuestaAdministracionConsultar;

/**
 * Clase para manejar las peticiones relacionadas con listas compuestas para el
 * modulo administracion
 *
 * @author Raul Londono Murillo
 *
 */
@RestController
public class ListaCompuestaAdministracionController implements IListaCompuestaAdministracionController {

	// Servicio de consulta para proyecto de inversion
	@Autowired
	IListaCompuestaAdministracionConsultar consultar;

	/**
	 * Logger
	 */
	@Autowired
	SPDDLogger spddLogger;

	/**
	 * Implementacion del metodo obtenerListaCompuestaTodos
	 *
	 * @see co.gov.sdp.spdd.core.icontroller.administracion.lista.compuesta.IListaCompuestaAdministracionController.obtenerListaCompuestaTodos
	 */
	@PostMapping(NHSPDDConstantes.CORE_CONTROLLER_ADMINISTRACION_GET_OBTENER_LISTAS_COMPUESTAS)
	@Override
	public GenericoDTO obtenerListaCompuestaTodos(@RequestBody ListaCompuestaDTO peticion) {
		GenericoDTO respuesta = new GenericoDTO();
		try {
			respuesta = consultar.obtenerPaginado(peticion);
		} catch (SpddExceptions e) {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ERROR_VALIDACION,
					PaqueteMensajeEnum.ERRORES, null));
			spddLogger.mensajeLogger(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, respuesta.getLenguaje()),
					NHSPDDConstantes.SEVERIDAD_FATAL, ListaCompuestaAdministracionController.class);
		}
		return respuesta;
	}

	/**
	 * Implementacion del metodo obtenerPorId
	 *
	 * @see co.gov.sdp.spdd.core.icontroller.administracion.lista.compuesta.IListaCompuestaAdministracionController.obtenerPorId
	 */
	@GetMapping(NHSPDDConstantes.CORE_CONTROLLER_ADMINISTRACION_GET_OBTENER_POR_ID_LISTA_COMPUESTA)
	@Override
	public ListaCompuestaDTO obtenerPorId(@PathVariable("id") Long id) {
		ListaCompuestaDTO respuesta = new ListaCompuestaDTO();
		try {
			respuesta = consultar.obtenerLista(id);
		} catch (SpddExceptions e) {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ERROR_VALIDACION,
					PaqueteMensajeEnum.ERRORES, null));
			spddLogger.mensajeLogger(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, respuesta.getLenguaje()),
					NHSPDDConstantes.SEVERIDAD_FATAL, ListaCompuestaAdministracionController.class);
		}
		return respuesta;
	}
}
