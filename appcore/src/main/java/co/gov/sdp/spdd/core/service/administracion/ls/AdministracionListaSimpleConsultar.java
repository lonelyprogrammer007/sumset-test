package co.gov.sdp.spdd.core.service.administracion.ls;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.dto.ListaSimpleDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.nhspdd.common.util.NHSPDDMensajes;
import co.gov.sdp.nhspdd.common.util.PaqueteMensajeEnum;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.spdd.core.iservice.administracion.ls.IAdministracionListaSimpleConsultar;
import co.gov.sdp.spdd.data.dao.interfaces.afs.IListaSimpleServiceRepo;
import co.gov.sdp.spdd.data.irepositorio.afs.IListaSimpleRepo;
import co.gov.sdp.spdd.data.mapper.ListaSimpleMapper;
import co.gov.sdp.spdd.data.model.afs.ListaSimple;
import co.gov.sdp.spdd.data.sesionfacade.interfaces.ISessionFacadeAFS;

/**
 * Clase que implementa los servicios de consulta asociados a
 * argumentosListaSimple
 *
 * @author BryanMunoz
 *
 */
@Service
public class AdministracionListaSimpleConsultar implements IAdministracionListaSimpleConsultar {

	/**
	 * Objeto que me permite mapear la entidad a dto o viceversa
	 */
	@Autowired
	ListaSimpleMapper listaSimpleMapper;
	
	/**
	 * Objeto que me permite traer las operaciones basicas de bd
	 */
	@Autowired
	IListaSimpleServiceRepo listaSimpleServiceRepo;
	
	/**
	 * Objeto que tiene las consultas especificas de la entidad
	 */
	@Autowired
	IListaSimpleRepo listaSimpleRepo;
	
	/**
	 * 
	 */
	@Autowired
	ISessionFacadeAFS sessionFacadeAfs;

	/*
	 * 
	 */
	@Autowired
	SPDDLogger spddLogger;

	/**
	 * 
	 */
	@Override
	public GenericoDTO obtenerTodos() throws SpddExceptions {
		GenericoDTO respuesta = new GenericoDTO();
		List<ListaSimple> listaSimple = listaSimpleServiceRepo.obtenerTodos();
		List<ListaSimpleDTO> listaRespuesta = listaSimpleMapper.listaSimpleEntitiesToDTO(listaSimple);
		respuesta.setLstObjectsDTO(new ArrayList<>(listaRespuesta));
		return respuesta;
	}

	/**
	 * 
	 */
	@Override
	public ListaSimpleDTO obtenerPorId(Long id) throws SpddExceptions {
		ListaSimple entidad = listaSimpleServiceRepo.obtener(id);
		ListaSimpleDTO respuesta = new ListaSimpleDTO();
		if (entidad != null) {
			respuesta = listaSimpleMapper.listaSimpleEntityToDTO(entidad);
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(
					NHSPDDConstantes.MENSAJE_OBTENER_POR_ID_LISTA_SIMPLE, PaqueteMensajeEnum.MENSAJES, null));
		} else {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_TIEMPO_EXCEDIDO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_NO_SE_ENCUENTRA_LA_ENTIDAD,
					PaqueteMensajeEnum.MENSAJES, null));
		}
		return respuesta;
	}

	/**
	 * 
	 */
	@Override
	public GenericoDTO obtenerPaginado(ListaSimpleDTO peticion) throws SpddExceptions {
		GenericoDTO respuesta = sessionFacadeAfs.consultarListaSimplePorFiltro(peticion);
		respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);

		return respuesta;
	}
}
