package co.gov.sdp.spdd.core.service.administracion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.nhspdd.common.util.NHSPDDMensajes;
import co.gov.sdp.nhspdd.common.util.PaqueteMensajeEnum;
import co.gov.sdp.spdd.core.iservice.administracion.IPddNivelAtributoConsultar;
import co.gov.sdp.spdd.data.sesionfacade.interfaces.ISessionFacadeAFS;

@Service
public class PddNivelAtributoConsultar implements IPddNivelAtributoConsultar {

	/**
	 * 
	 */
	@Autowired
	ISessionFacadeAFS sessionFacadeAfs;

	/**
	 * 
	 */
	@Override
	public GenericoDTO pddNivelAtributoObtenerLibres() throws SpddExceptions {
		GenericoDTO respuesta = sessionFacadeAfs.pddNivelAtributoObtenerLibres();
		respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
		respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(
				NHSPDDConstantes.MENSAJE_OBTENER_TODOS_COMPONENTES_GESTION_USUARIO_CORRECTO,
				PaqueteMensajeEnum.MENSAJES, null));
		return respuesta;
	}
}
