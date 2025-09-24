package co.gov.sdp.spdd.core.service.administracion.lista.compuesta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;

import co.gov.sdp.nhspdd.common.dto.PotProyectoInstrumentoDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.nhspdd.common.util.NHSPDDMensajes;
import co.gov.sdp.nhspdd.common.util.PaqueteMensajeEnum;
import co.gov.sdp.spdd.core.iservice.administracion.lista.compuesta.IPotProyectoInstrumentoRegistrar;
import co.gov.sdp.spdd.data.sesionfacade.interfaces.ISessionFacadeAFS;
import co.gov.sdp.spdd.util.AuditoriaUtil;

/**
 * Clase que implementa los metodos de registrar de potProyectoInstrumento
 *
 * @author Bryan Munoz
 *
 */
@Service
public class PotProyectoInstrumentoRegistrar implements IPotProyectoInstrumentoRegistrar {

	/**
	 * 
	 */
	@Autowired
	ISessionFacadeAFS sessionFacadeAFS;
	
	/**
	 * 
	 */
	@Autowired
	AuditoriaUtil auditoria;

	/**
	 * Implementacion del metodo registrarProyecto
	 * @throws JsonProcessingException 
	 *
	 * @see
	 */
	@Override
	public PotProyectoInstrumentoDTO registrarProyectoInstrumento(PotProyectoInstrumentoDTO potProyectoInstrumentoDTO)
			throws SpddExceptions, JsonProcessingException {
		PotProyectoInstrumentoDTO respuesta = sessionFacadeAFS.buscarPorIdLsPotObraYIdLsPotInstrumento(potProyectoInstrumentoDTO);
		if (respuesta.getIdProyectoInstrumento() != null) {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ASOCIACION_EXISTENTE,
					PaqueteMensajeEnum.MENSAJES, potProyectoInstrumentoDTO.getLenguaje()));
		} else {
			respuesta = sessionFacadeAFS.guardarPotProyectoInstrumento(potProyectoInstrumentoDTO);
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
			respuesta.setMsgRespuesta(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_CREAR_LISTA_COMPUESTA_EXITOSO,
							PaqueteMensajeEnum.MENSAJES, potProyectoInstrumentoDTO.getLenguaje()));
			auditoria.crearAuditoria(potProyectoInstrumentoDTO.toString(), potProyectoInstrumentoDTO.toString(), "CREAR_AFS", "AGREGAR_POT_INSTRUMENTO");
		}
		return respuesta;
	}
}
