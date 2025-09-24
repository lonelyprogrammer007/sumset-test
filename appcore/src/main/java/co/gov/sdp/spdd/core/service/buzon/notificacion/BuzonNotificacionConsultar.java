package co.gov.sdp.spdd.core.service.buzon.notificacion;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.gov.sdp.nhspdd.common.dto.BuzonNotificacionesDTO;
import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.nhspdd.common.util.NHSPDDMensajes;
import co.gov.sdp.nhspdd.common.util.PaqueteMensajeEnum;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.spdd.core.iservice.buzon.notificacion.IBuzonNotificacionConsultar;
import co.gov.sdp.spdd.data.dao.interfaces.afs.IBuzonNotificacioneServiceRepo;
import co.gov.sdp.spdd.data.mapper.BuzonNotificacionesMapper;
import co.gov.sdp.spdd.data.model.afs.BuzonNotificaciones;
import co.gov.sdp.spdd.data.sesionfacade.interfaces.ISessionFacadeAFS;

/**
 * Clase que implementa los metodos de consulta de la clase buzonNotificaciones
 *
 * @author Bryan Munoz
 *
 */
@Service
public class BuzonNotificacionConsultar implements IBuzonNotificacionConsultar {

	// Servicio que permite convertir un dto a una entidad y viceversa
	@Autowired
	BuzonNotificacionesMapper mapper;

	// Servicio que permite traer las operaciones basicas de la bd
	@Autowired
	IBuzonNotificacioneServiceRepo buzonNotificacionesServiceRepo;

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
	 * 
	 */
	@Override
	public GenericoDTO obtenerTodosAdmin() throws SpddExceptions {
		GenericoDTO respuesta = new GenericoDTO();
		List<BuzonNotificaciones> entidad = buzonNotificacionesServiceRepo.obtenerTodos();
		List<BuzonNotificacionesDTO> listaRespuesta = mapper.buzonNotificacionesEntitiesToDTO(entidad);
		respuesta.setLstObjectsDTO(new ArrayList<>(listaRespuesta));
		respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
		respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(
				NHSPDDConstantes.MENSAJE_OBTENER_TODOS_ADMIN_BUZON_NOTIFICACION, PaqueteMensajeEnum.MENSAJES, null));
		return respuesta;

	}

	/**
	 * 
	 */
	@Transactional
	@Override
	public GenericoDTO obtenerTodosPorUsuario(String usuario) throws SpddExceptions {
		GenericoDTO genericoDTO = sessionFacadeAFS.obtenerBuzonPorUsuario(usuario);
		genericoDTO.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
		genericoDTO.setMsgRespuesta(
				NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_OBTENER_TODOS_POR_USUARIO_BUZON_NOTIFICACION,
						PaqueteMensajeEnum.MENSAJES, null));

		return genericoDTO;
	}

	/**
	 * 
	 */
	@Transactional
	@Override
	public Long notificacionesPorUsuario(BuzonNotificacionesDTO buzonNotificacionesDTO) throws SpddExceptions {
		return sessionFacadeAFS.notificacionesPorUsuario(buzonNotificacionesDTO);
	}
	
	/**
	 * 
	 */
	@Override
	public GenericoDTO obtenerPaginado(BuzonNotificacionesDTO peticion) throws SpddExceptions {
		GenericoDTO respuesta = sessionFacadeAFS.consultarBuzonNotificacionesPorFiltro(peticion);
		respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
		respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(
				NHSPDDConstantes.MENSAJE_OBTENER_TODOS_CONSECUTIVO_CORRECTO, PaqueteMensajeEnum.MENSAJES, null));

		return respuesta;
	}
	
}
