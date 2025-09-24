package co.gov.sdp.spdd.core.service.administracion.lista.compuesta;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;

import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.dto.TerritorializacionDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.nhspdd.common.util.NHSPDDMensajes;
import co.gov.sdp.nhspdd.common.util.PaqueteMensajeEnum;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.spdd.core.iservice.administracion.lista.compuesta.ITerritorializacionConsultar;
import co.gov.sdp.spdd.data.dao.interfaces.afs.ITerritorializacionServiceRepo;
import co.gov.sdp.spdd.data.mapper.TerritorializacionMapper;
import co.gov.sdp.spdd.data.model.afs.Territorializacion;
import co.gov.sdp.spdd.data.sesionfacade.interfaces.ISessionFacadeAFS;
import co.gov.sdp.spdd.util.AuditoriaUtil;

/**
 * Clase que implementa todos los metodos asociados a consultar
 *
 * @author Bryan Munoz
 *
 */
@Service
public class TerritorializacionConsultar implements ITerritorializacionConsultar {

	/**
	 * 
	 */
	@Autowired
	TerritorializacionMapper mapper;

	/**
	 * 
	 */
	@Autowired
	ITerritorializacionServiceRepo territorializacionServiceRepo;

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
	 * Implementacion del metodo obtenerTodos
	 *
	 * @see
	 */
	@Override
	public GenericoDTO obtenerTodos() throws SpddExceptions {
		GenericoDTO respuesta = new GenericoDTO();
		List<Territorializacion> entidad = territorializacionServiceRepo.obtenerTodos();
		List<TerritorializacionDTO> listaRespuesta = mapper.territorializacionEntitiesToDTO(entidad);
		respuesta.setLstObjectsDTO(new ArrayList<>(listaRespuesta));
		respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
		respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(
				NHSPDDConstantes.MENSAJE_OBTENER_TODOS_TERRITORIALIZACION_CORRECTO, PaqueteMensajeEnum.MENSAJES, null));
		return respuesta;
	}

	/**
	 * @throws JsonProcessingException 
	 * 
	 */
	@Override
	public GenericoDTO obtenerPaginado(TerritorializacionDTO peticion) throws SpddExceptions, JsonProcessingException {
		GenericoDTO respuesta = sessionFacadeAFS.consultarTerritorializacionPorFiltro(peticion);
		respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
		respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(
				NHSPDDConstantes.MENSAJE_OBTENER_TODOS_TERRITORIALIZACION_CORRECTO, PaqueteMensajeEnum.MENSAJES, null));
		auditoria.crearAuditoria(peticion.toString(), peticion.toString(), "CONSULTAR_AFS", "CONSULTAR_TERRITORIALIZACION");
		return respuesta;
	}

	@Override
	public GenericoDTO obtenerTerPorLocalidad() throws SpddExceptions {
		GenericoDTO respuesta = sessionFacadeAFS.buscarPorLocalidadTerritorializacion();
		respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
		respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(
				NHSPDDConstantes.MENSAJE_OBTENER_TODOS_TERRITORIALIZACION_CORRECTO, PaqueteMensajeEnum.MENSAJES, null));

		return respuesta;
	}

	@Override
	public GenericoDTO obtenerTerritoriosPorIdLocalidad(Long idLocalidad) throws SpddExceptions {
		GenericoDTO respuesta = sessionFacadeAFS.obtenerTerritoriosPorIdLocalidad(idLocalidad);
		respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
		respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(
				NHSPDDConstantes.MENSAJE_OBTENER_TODOS_TERRITORIALIZACION_CORRECTO, PaqueteMensajeEnum.MENSAJES, null));

		return respuesta;
	}
}
