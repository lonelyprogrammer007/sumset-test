package co.gov.sdp.spdd.core.service.administracion.ls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;

import co.gov.sdp.nhspdd.common.dto.ArgumentoListaSimpleDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.nhspdd.common.util.NHSPDDMensajes;
import co.gov.sdp.nhspdd.common.util.PaqueteMensajeEnum;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.spdd.core.iservice.administracion.ls.IArgumentoListaSimpleRegistrar;
import co.gov.sdp.spdd.data.dao.interfaces.afs.IArgumentoListaSimpleServiceRepo;
import co.gov.sdp.spdd.data.mapper.ArgumentoListaSimpleMapper;
import co.gov.sdp.spdd.data.sesionfacade.interfaces.ISessionFacadeAFS;
import co.gov.sdp.spdd.util.AuditoriaUtil;

/**
 * Clase que implementa todos los metodos asociados con registrar de un
 * argumento lista simple
 *
 * @author Bryan Munoz
 *
 */
@Service
public class ArgumentoListaSimpleRegistrar implements IArgumentoListaSimpleRegistrar {

	/**
	 * 
	 */
	@Autowired
	ISessionFacadeAFS sessionFacadeAFS;

	/**
	 * 
	 */
	@Autowired
	ArgumentoListaSimpleMapper argumentoListaSimpleMapper;

	/**
	 * 
	 */
	@Autowired
	IArgumentoListaSimpleServiceRepo argumentoListaSimpleServiceRepo;

	/**
	 * 
	 */
	@Autowired
	SPDDLogger spddLogger;
	
	/**
	 * Auditoria
	 */
	@Autowired
	AuditoriaUtil auditoria;

	/**
	 * @throws JsonProcessingException 
	 * 
	 */
	@Override
	public ArgumentoListaSimpleDTO registrarArgumentoListaSimple(ArgumentoListaSimpleDTO argumentoListaSimpleDTO)
			throws SpddExceptions, JsonProcessingException {
		ArgumentoListaSimpleDTO respuesta = sessionFacadeAFS.buscarIdListaSimpleArgumento(argumentoListaSimpleDTO);
		if (respuesta.getIdArgumento() != null) {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_REGISTRO_REPETIDO,
					PaqueteMensajeEnum.MENSAJES, argumentoListaSimpleDTO.getLenguaje()));
		} else {
			respuesta = sessionFacadeAFS.guardarArgumentoListaSimple(argumentoListaSimpleDTO);
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
			respuesta.setMsgRespuesta(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_REGISTRAR_ARGUMENTO_LISTA_SIMPLE,
							PaqueteMensajeEnum.MENSAJES, argumentoListaSimpleDTO.getLenguaje()));
			auditoria.crearAuditoria(argumentoListaSimpleDTO.toString(), argumentoListaSimpleDTO.toString(), "CREAR_AFS", "ADMIN_CONTENIDO_LS");
		}
		return respuesta;
	}
}
