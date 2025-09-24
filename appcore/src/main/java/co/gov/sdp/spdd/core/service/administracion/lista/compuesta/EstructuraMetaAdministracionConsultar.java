package co.gov.sdp.spdd.core.service.administracion.lista.compuesta;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;

import co.gov.sdp.nhspdd.common.dto.EstructuraMetaDTO;
import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.nhspdd.common.util.NHSPDDMensajes;
import co.gov.sdp.nhspdd.common.util.PaqueteMensajeEnum;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.spdd.core.iservice.administracion.lista.compuesta.IEstructuraMetaAdministracionConsultar;
import co.gov.sdp.spdd.data.dao.interfaces.afs.IEstructuraMetaServiceRepo;
import co.gov.sdp.spdd.data.mapper.EstructuraMetaMapper;
import co.gov.sdp.spdd.data.model.afs.EstructuraMeta;
import co.gov.sdp.spdd.data.sesionfacade.interfaces.ISessionFacadeAFS;
import co.gov.sdp.spdd.util.AuditoriaUtil;

/**
 * Implementacion de las funcionalidades de consulta para el modulo de
 * administracion
 *
 * @author Raul Londono Murillo
 *
 */
@Service
public class EstructuraMetaAdministracionConsultar implements IEstructuraMetaAdministracionConsultar {

	// Servicio Repositorio de estructura meta
	@Autowired
	IEstructuraMetaServiceRepo estructuraMetaServiceRepo;

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
	 * Implementacion del metodo obtenerEstructuraMetaTodos
	 *
	 * @see co.gov.sdp.nhspdd.core..iservice.administracion.lista.compuesta.IEstructuraMetaAdmnistracionConsultar.obtenerEstructuraMetaTodos
	 */
	@Override
	public GenericoDTO obtenerEstructuraMetaTodos() throws SpddExceptions {
		GenericoDTO respuesta = new GenericoDTO();
		List<EstructuraMeta> lista = estructuraMetaServiceRepo.obtenerTodos();
		List<EstructuraMetaDTO> listaRespuesta = estructuraMetaMapper.estructuraMetaEntitiesToDTO(lista);
		respuesta.setLstObjectsDTO(new ArrayList<>(listaRespuesta));
		respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
		respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(
				NHSPDDConstantes.MENSAJE_OBTENER_ESTRUCTURA_METAS_TODOS_CORRECTO, PaqueteMensajeEnum.MENSAJES, null));
		return respuesta;
	}

	/**
	 * @throws JsonProcessingException 
	 * 
	 */
	@Override
	public GenericoDTO obtenerPaginado(EstructuraMetaDTO peticion) throws SpddExceptions, JsonProcessingException {
		GenericoDTO respuesta = sessionFacadeAFS.consultarEstructuraMetaPorFiltro(peticion);
		respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
		respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(
				NHSPDDConstantes.MENSAJE_OBTENER_ESTRUCTURA_METAS_TODOS_CORRECTO, PaqueteMensajeEnum.MENSAJES, null));
		auditoria.crearAuditoria(peticion.toString(), peticion.toString(), "CONSULTAR_AFS", "CONSULTAR_ESTRUCTURA_META");
		return respuesta;
	}

}
