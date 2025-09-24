package co.gov.sdp.spdd.core.service.administracion.lista.compuesta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.dto.PotProyectoInstrumentoDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.nhspdd.common.util.NHSPDDMensajes;
import co.gov.sdp.nhspdd.common.util.PaqueteMensajeEnum;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.spdd.core.iservice.administracion.lista.compuesta.IPotProyectoInstrumentoConsultar;
import co.gov.sdp.spdd.data.sesionfacade.interfaces.ISessionFacadeAFS;

/**
 * Clase que implementa los metodos de consultar asociados a un proyecto
 * instrumento
 *
 * @author Bryan Munoz
 *
 */
@Service
public class PotProyectoInstrumentoConsultar implements IPotProyectoInstrumentoConsultar {
	
    /**
	 * 
	 */
	@Autowired
	ISessionFacadeAFS sessionFacadeAFS;
	
	/**
	 * Logger
	 */
	@Autowired
	SPDDLogger spddLogger;

	/**
	 * Implementacion del metodo obtenerTodosProyectoInstrumento
	 *
	 * @see
	 */
	@Override
	public GenericoDTO obtenerTodosProyectoInstrumento() throws SpddExceptions {
		GenericoDTO respuesta = sessionFacadeAFS.consultarPotProyectoInstrumentoTodos();
		respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
		respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(
				NHSPDDConstantes.MENSAJE_OBTENER_PROYECTO_INVERSION_TODOS_CORRECTO, PaqueteMensajeEnum.MENSAJES, null));
		return respuesta;
	}
	
	/**
	 * 
	 */
	@Override
	public GenericoDTO obtenerPaginado(PotProyectoInstrumentoDTO peticion) throws SpddExceptions {
		GenericoDTO respuesta = sessionFacadeAFS.consultarPotProyectoInstrumentoPorFiltro(peticion);
		respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
		respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(
				NHSPDDConstantes.MENSAJE_OBTENER_TODOS_POT_PROYECTO_INSTRUMENTO, PaqueteMensajeEnum.MENSAJES, null));
		return respuesta;
	}

}