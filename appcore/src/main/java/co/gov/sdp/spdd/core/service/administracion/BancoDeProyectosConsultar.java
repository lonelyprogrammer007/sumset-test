package co.gov.sdp.spdd.core.service.administracion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.nhspdd.common.util.NHSPDDMensajes;
import co.gov.sdp.nhspdd.common.util.PaqueteMensajeEnum;
import co.gov.sdp.spdd.core.iservice.administracion.IBancoDeProyectoConsultar;
import co.gov.sdp.spdd.data.sesionfacade.interfaces.ISessionFacadeAFS;

/**
 * 
 * @author sumset
 *
 */
@Service
public class BancoDeProyectosConsultar implements IBancoDeProyectoConsultar {

	/**
	 * 
	 */
	@Autowired
	ISessionFacadeAFS sessionFacade;

	/**
	 * 
	 */
	@Override
	public GenericoDTO obtenerTodos() throws SpddExceptions {
		GenericoDTO genericoDTO = sessionFacade.consultarBancoDeProyectosTodos();
		genericoDTO.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
		genericoDTO.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_OBTENER_TODOS_LISTA_SIMPLE,
				PaqueteMensajeEnum.MENSAJES, null));
		return genericoDTO;
	}

}
