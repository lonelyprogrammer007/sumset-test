package co.gov.sdp.spdd.core.service.administracion.lista.compuesta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;

import co.gov.sdp.nhspdd.common.dto.ArgumentoListaSimpleDTO;
import co.gov.sdp.nhspdd.common.dto.EstructuraMetaDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.nhspdd.common.util.NHSPDDMensajes;
import co.gov.sdp.nhspdd.common.util.PaqueteMensajeEnum;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.spdd.core.iservice.administracion.lista.compuesta.IEstructuraMetaAdministracionRegistrar;
import co.gov.sdp.spdd.data.dao.interfaces.afs.IArgumentoListaSimpleServiceRepo;
import co.gov.sdp.spdd.data.dao.interfaces.afs.IEstructuraMetaServiceRepo;
import co.gov.sdp.spdd.data.mapper.EstructuraMetaMapper;
import co.gov.sdp.spdd.data.sesionfacade.interfaces.ISessionFacadeAFS;
import co.gov.sdp.spdd.util.AuditoriaUtil;

/**
 * Implementacion de las funcionalidades de registro para el modulo de
 * administracion
 *
 * @author Raul Londono Murillo
 *
 */
@Service
public class EstructuraMetaAdministracionRegistrar implements IEstructuraMetaAdministracionRegistrar {

	// Servicio Repositorio de estructura meta
	@Autowired
	IEstructuraMetaServiceRepo estructuraMetaServiceRepo;

	// Servicio Repositorio de estructura meta
	@Autowired
	IArgumentoListaSimpleServiceRepo argumentoListaSimpleServiceRepo;

	// Mapper de estructura meta
	@Autowired
	EstructuraMetaMapper estructuraMetaMapper;

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
	 * Auditoria
	 */
	@Autowired
	AuditoriaUtil auditoria;

	/**
	 * Implementacion del metodo crearComponenteGasto
	 * @throws JsonProcessingException 
	 *
	 * @see co.gov.sdp.nhspdd.core.iservice.administracion.listacompuesta.IEstructuraMetaAdministracionRegistrar.crearEstructuraMeta
	 */
	@Override
	public EstructuraMetaDTO crearEstructuraMeta(EstructuraMetaDTO peticion) throws SpddExceptions, JsonProcessingException {
		ArgumentoListaSimpleDTO idLsUnidadMedida = sessionFacadeAFS.consultarArgumentoListaSimplePorId(peticion.getIdLsUnidadMedida());
		ArgumentoListaSimpleDTO idLsVerbo = sessionFacadeAFS.consultarArgumentoListaSimplePorId(peticion.getIdLsVerbo());
		EstructuraMetaDTO respuesta = new EstructuraMetaDTO();
		if (idLsUnidadMedida == null || idLsUnidadMedida.getIdArgumento() == null) {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_LISTA_UNIDAD_MEDIDA_NO_ENCONTRADO,
							PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
			return respuesta;
		}
		if (idLsVerbo == null || idLsVerbo.getIdArgumento() == null) {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_LISTA_VERBO_NO_ENCONTRADO,
					PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
			return respuesta;
		}
		respuesta = sessionFacadeAFS.guardarEstructuraMeta(peticion);
		if(respuesta.getIdEstructuraMetas()!=null) {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_CREAR_LISTA_COMPUESTA_EXITOSO,
					PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
			auditoria.crearAuditoria(peticion.toString(), peticion.toString(), "CREAR_AFS", "AGREGAR_ESTRUCTURA_META");
		}else {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ASOCIACION_EXISTENTE,
					PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
		}
		
		return respuesta;
	}
}