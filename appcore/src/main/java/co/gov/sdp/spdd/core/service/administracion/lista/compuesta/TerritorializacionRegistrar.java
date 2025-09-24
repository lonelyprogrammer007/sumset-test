package co.gov.sdp.spdd.core.service.administracion.lista.compuesta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;

import co.gov.sdp.nhspdd.common.dto.TerritorializacionDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.nhspdd.common.util.NHSPDDMensajes;
import co.gov.sdp.nhspdd.common.util.PaqueteMensajeEnum;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.spdd.core.iservice.administracion.lista.compuesta.ITerritorializacionRegistrar;
import co.gov.sdp.spdd.data.sesionfacade.interfaces.ISessionFacadeAFS;
import co.gov.sdp.spdd.util.AuditoriaUtil;

/**
 * Clase que implementa los metodos asociados con registrar
 *
 * @author Bryan Munoz
 *
 */
@Service
public class TerritorializacionRegistrar implements ITerritorializacionRegistrar {

	/**
	 * Logger
	 */
	@Autowired
	SPDDLogger spddLogger;

	/**
	 * 
	 */
	@Autowired
	ISessionFacadeAFS sessionFacadeAFS;
	
	/**
	 * Auditoria
	 */
	@Autowired
	AuditoriaUtil auditoria;

	/**
	 * Implementacion del metodo registrarTerritorializacion
	 * @throws JsonProcessingException 
	 *
	 * @see
	 */
	@Override
	public TerritorializacionDTO registrarTerritorializacion(TerritorializacionDTO territorializacionDTO)
			throws SpddExceptions, JsonProcessingException {
		TerritorializacionDTO respuesta = sessionFacadeAFS.guardarTerritorializacion(territorializacionDTO);
		if (respuesta.getIdTerritorializacion() != null) {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
			respuesta.setMsgRespuesta(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_CREAR_LISTA_COMPUESTA_EXITOSO,
							PaqueteMensajeEnum.MENSAJES, territorializacionDTO.getLenguaje()));
			auditoria.crearAuditoria(territorializacionDTO.toString(), territorializacionDTO.toString(), "CREAR_AFS", "AGREGAR_TERRITORIALIZACION");
		} else {
			respuesta = new TerritorializacionDTO();
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ASOCIACION_EXISTENTE,
					PaqueteMensajeEnum.MENSAJES, territorializacionDTO.getLenguaje()));
		}
		return respuesta;
	}
}
