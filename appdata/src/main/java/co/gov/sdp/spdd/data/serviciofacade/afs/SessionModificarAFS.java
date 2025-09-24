package co.gov.sdp.spdd.data.serviciofacade.afs;

import java.io.Serializable;

import org.springframework.stereotype.Component;

import co.gov.sdp.nhspdd.common.dto.PotProyectoInstrumentoDTO;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.nhspdd.common.util.NHSPDDMensajes;
import co.gov.sdp.nhspdd.common.util.PaqueteMensajeEnum;
import co.gov.sdp.spdd.data.model.ip.PotProyectoInstrumento;

@Component
public class SessionModificarAFS extends SessionAFS implements Serializable {

	
	/**
	 * Atributo de serialización.
	 */
	private static final long serialVersionUID = 1L;
		
	/**
	 * 
	 * @param peticion
	 * @return
	 */
	public PotProyectoInstrumentoDTO modificarProyectoInstrumento(PotProyectoInstrumentoDTO peticion) {
		PotProyectoInstrumento entidad = potProyectoInstrumentoServiceRepo.obtener(peticion.getIdProyectoInstrumento());
		PotProyectoInstrumentoDTO respuesta = new PotProyectoInstrumentoDTO();
		if (entidad != null) {
			entidad = potProyectoInstrumentoMapper.potProyectoInstrumentoDTOToEntity(peticion);
			potProyectoInstrumentoServiceRepo.guardar(entidad);
			respuesta = potProyectoInstrumentoMapper.potProyectoInstrumentoEntityToDTO(entidad);
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
			respuesta.setMsgRespuesta(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_MODIFICAR_POT_PROYECTO_INSTRUMENTO,
							PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
		} else {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_TIEMPO_EXCEDIDO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_NO_SE_ENCUENTRA_LA_ENTIDAD,
					PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
		}
		return respuesta;
	}

}
