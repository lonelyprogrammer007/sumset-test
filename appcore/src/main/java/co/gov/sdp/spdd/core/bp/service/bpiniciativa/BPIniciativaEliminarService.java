package co.gov.sdp.spdd.core.bp.service.bpiniciativa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.gov.sdp.nhspdd.common.dto.bp.BpIniciativaCiudadanaDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.nhspdd.common.util.NHSPDDMensajes;
import co.gov.sdp.nhspdd.common.util.PaqueteMensajeEnum;
import co.gov.sdp.spdd.core.bp.iservice.bpiniciativa.IBPIniciativaEliminarService;
import co.gov.sdp.spdd.data.sesionfacade.interfaces.ISessionFacadeBP;

@Service
public class BPIniciativaEliminarService implements IBPIniciativaEliminarService {

	@Autowired
	ISessionFacadeBP sessionFacadeBP;

	@Override
	public BpIniciativaCiudadanaDTO eliminarIniciativaCiudadana(Long idIniciativa) throws SpddExceptions {
		BpIniciativaCiudadanaDTO respuesta = sessionFacadeBP.consultarBpIniciativaCiudadanaPorId(idIniciativa);
		if (respuesta != null) {

			sessionFacadeBP.eliminarGruposEtarios(idIniciativa);
			sessionFacadeBP.eliminarUbicaciones(idIniciativa);
			sessionFacadeBP.eliminarTodasCondicionIniciativa(idIniciativa);
			sessionFacadeBP.eliminarIniciativaCiudadana(idIniciativa);
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ELIMINAR_INICIATIVA_CIUDADANA_CORRECTO,
					PaqueteMensajeEnum.MENSAJES, null));

		} else {
			respuesta = new BpIniciativaCiudadanaDTO();
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ENTIDAD_NO_ENCONTRADO,
					PaqueteMensajeEnum.MENSAJES, null));
		}
		return respuesta;
	}
}
