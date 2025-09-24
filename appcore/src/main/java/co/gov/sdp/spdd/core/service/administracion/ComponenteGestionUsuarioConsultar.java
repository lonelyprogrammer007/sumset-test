package co.gov.sdp.spdd.core.service.administracion;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.gov.sdp.nhspdd.common.dto.ComponenteGestionUsuarioDTO;
import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.dto.UsuariosDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.nhspdd.common.util.NHSPDDMensajes;
import co.gov.sdp.nhspdd.common.util.PaqueteMensajeEnum;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.spdd.core.iservice.administracion.IComponenteGestionUsuarioConsultar;
import co.gov.sdp.spdd.data.dao.interfaces.afs.IComponenteGestionUsuarioServiceRepo;
import co.gov.sdp.spdd.data.irepositorio.afs.IComponenteGestionUsuarioRepo;
import co.gov.sdp.spdd.data.mapper.ComponenteGestionUsuarioMapper;
import co.gov.sdp.spdd.data.model.afs.ComponenteGestionUsuario;
import co.gov.sdp.spdd.data.sesionfacade.interfaces.ISessionFacadeAFS;

/**
 * Esta clase implementa todos los metodos de consulta de
 * componenteGestionUsuario
 *
 * @author Bryan Munoz
 *
 */
@Service
public class ComponenteGestionUsuarioConsultar implements IComponenteGestionUsuarioConsultar {

	/**
	 * Objeto que mapea las entidades a dto y viceversa
	 */
	@Autowired
	ComponenteGestionUsuarioMapper componenteGestionUsuarioMapper;

	/**
	 * Objeto que tiene los servicios de componenteGestionUsuario
	 */
	@Autowired
	IComponenteGestionUsuarioServiceRepo componenteGestionUsuarioServiceRepo;

	/**
	 * Objeto que contiene metodos de consultas especificas de
	 * componenteGestionUsuario
	 */
	@Autowired
	IComponenteGestionUsuarioRepo componenteGestionUsuarioRepo;

	/**
	 * 
	 */
	@Autowired
	ISessionFacadeAFS sessionFacadeAfs;

	/**
	 * 
	 */
	@Autowired
	SPDDLogger spddLogger;

	/**
	 * 
	 */
	@Override
	public GenericoDTO obtenerPorUsuario(UsuariosDTO usuario) throws SpddExceptions {
		GenericoDTO respuesta = new GenericoDTO();
		List<ComponenteGestionUsuario> lista = componenteGestionUsuarioRepo.findByUsuario(usuario.getNombreUsuario());
		List<ComponenteGestionUsuarioDTO> listaRespuesta = componenteGestionUsuarioMapper
				.componenteGestionUsuarioEntitiesToDTO(lista);
		respuesta.setLstObjectsDTO(new ArrayList<>(listaRespuesta));
		respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
		respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(
				NHSPDDConstantes.MENSAJE_OBTENER_TODOS_COMPONENTES_GESTION_USUARIO_CORRECTO,
				PaqueteMensajeEnum.MENSAJES, null));
		return respuesta;
	}

	/**
	 * 
	 */
	@Override
	public GenericoDTO obtenerTodos() throws SpddExceptions {
		GenericoDTO respuesta = sessionFacadeAfs.consultarComponenteGestionUsuarioTodos();
		respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
		respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(
				NHSPDDConstantes.MENSAJE_OBTENER_TODOS_COMPONENTES_GESTION_USUARIO_CORRECTO,
				PaqueteMensajeEnum.MENSAJES, null));
		return respuesta;
	}
}
