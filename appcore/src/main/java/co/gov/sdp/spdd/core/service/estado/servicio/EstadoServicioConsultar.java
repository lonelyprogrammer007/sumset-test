package co.gov.sdp.spdd.core.service.estado.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.gov.sdp.nhspdd.common.dto.EstadoServicioDTO;
import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.nhspdd.common.util.NHSPDDMensajes;
import co.gov.sdp.nhspdd.common.util.PaqueteMensajeEnum;
import co.gov.sdp.spdd.core.iservice.estado.servicio.IEstadoServicioConsultar;
import co.gov.sdp.spdd.data.sesionfacade.interfaces.ISessionFacadeAFS;

/**
 * Clase que implementa los metodos asociados con estado servicio
 *
 * @author Bryan Munoz
 *
 */
@Service
public class EstadoServicioConsultar implements IEstadoServicioConsultar {
	
	/**
	 * 
	 */
	@Autowired
	ISessionFacadeAFS sessionFacadeAFS;


	/**
	 * Implementacion del metodo obtenerTodos
	 *
	 * @see
	 */
	@Override
	public GenericoDTO obtenerTodos(EstadoServicioDTO peticion) throws SpddExceptions {
		GenericoDTO respuesta = sessionFacadeAFS.consultarEstadoServicioPorFiltro(peticion);
		respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
		respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(
				NHSPDDConstantes.MENSAJE_OBTENER_ARCHIVOS_PLANOS_CARGADOS_CORRECTO, PaqueteMensajeEnum.MENSAJES, null));
		return respuesta;

	}

}
