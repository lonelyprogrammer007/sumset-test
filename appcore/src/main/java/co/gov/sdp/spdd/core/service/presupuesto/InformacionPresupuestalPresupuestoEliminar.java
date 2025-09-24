package co.gov.sdp.spdd.core.service.presupuesto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.gov.sdp.nhspdd.common.dto.InformacionPresupuestalDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.nhspdd.common.util.NHSPDDMensajes;
import co.gov.sdp.nhspdd.common.util.PaqueteMensajeEnum;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.spdd.core.iservice.presupuesto.IInformacionPresupuestalPresupuestoEliminar;
import co.gov.sdp.spdd.data.sesionfacade.interfaces.ISessionFacadeAFS;

/**
 * Implementacion de las funcionalidades de consulta para el modulo de
 * presupuesto
 *
 * @author Raul Londono Murillo
 *
 */
@Service
public class InformacionPresupuestalPresupuestoEliminar implements IInformacionPresupuestalPresupuestoEliminar {

	/**
	 * Inyeccion del SessionFacade del modulo IP
	 */
	@Autowired
	ISessionFacadeAFS sessionFacadeAFS;

	/**
	 * Logger
	 */
	@Autowired
	SPDDLogger spddLogger;
	
	@Override
	public InformacionPresupuestalDTO eliminarInformacionPresupuestal(Long idInformacionPresupuestal) throws SpddExceptions {
		InformacionPresupuestalDTO respuesta = sessionFacadeAFS.consultarInformacionPresupuestalPorId(idInformacionPresupuestal);
		if (respuesta != null) {

			sessionFacadeAFS.eliminarInformacionPresupuestal(idInformacionPresupuestal);
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(
					NHSPDDConstantes.MENSAJE_ELIMINAR_INFORMACION_PRESUPUESTAL_CORRECTO, PaqueteMensajeEnum.MENSAJES, null));

		} else {
			respuesta = new InformacionPresupuestalDTO();
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ENTIDAD_NO_ENCONTRADO,
					PaqueteMensajeEnum.MENSAJES, null));
		}

		return respuesta;
	}
	
	

}
