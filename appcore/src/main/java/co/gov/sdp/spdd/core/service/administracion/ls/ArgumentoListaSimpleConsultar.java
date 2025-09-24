package co.gov.sdp.spdd.core.service.administracion.ls;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.gov.sdp.nhspdd.common.dto.ArgumentoListaSimpleDTO;
import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.nhspdd.common.util.NHSPDDMensajes;
import co.gov.sdp.nhspdd.common.util.PaqueteMensajeEnum;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.spdd.core.iservice.administracion.ls.IArgumentoListaSimpleConsultar;
import co.gov.sdp.spdd.data.irepositorio.afs.IArgumentoListaSimpleRepo;
import co.gov.sdp.spdd.data.mapper.ArgumentoListaSimpleMapper;
import co.gov.sdp.spdd.data.model.afs.ArgumentoListaSimple;
import co.gov.sdp.spdd.data.sesionfacade.interfaces.ISessionFacadeAFS;

/**
 * Clase que implementa todos los metodos asociados con consultar un argumento
 * lista simple
 *
 * @author Bryan Munoz
 *
 */
@Service
public class ArgumentoListaSimpleConsultar implements IArgumentoListaSimpleConsultar {

	/**
	 * Objeto que permite traer consultas especificas de argumento lista simple
	 */
	@Autowired
	IArgumentoListaSimpleRepo argumentoListaSimpleRepo;
	/**
	 * Objeto que permite mapear un dto a una entidad y viceversa
	 */
	@Autowired
	ArgumentoListaSimpleMapper argumentoListaSimpleMapper;

	@Autowired
	SPDDLogger spddLogger;

	@Autowired
	ISessionFacadeAFS sessionFacadeAfs;

	/**
	 * Implementacion del metodo obtener todos
	 *
	 * @see co.gov.sdp.spdd.core.iservice.administracion.ls.IArgumentoListaSimpleConsultar.obtenerTodos
	 */
	@Override
	public GenericoDTO obtenerTodos(Long id) throws SpddExceptions {
		GenericoDTO respuesta = new GenericoDTO();
		List<ArgumentoListaSimple> argumentoListaSimple = argumentoListaSimpleRepo.obtenerArgumentoPorLista(id);
		List<ArgumentoListaSimpleDTO> listaRespuesta = argumentoListaSimpleMapper
				.argumentoListaSimpleEntitiesToDTO(argumentoListaSimple);
		respuesta.setLstObjectsDTO(new ArrayList<>(listaRespuesta));
		respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
		respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(
				NHSPDDConstantes.MENSAJE_OBTENER_TODOS_ARGUMENTO_LISTA_SIMPLE, PaqueteMensajeEnum.MENSAJES, null));

		return respuesta;
	}

	/**
	 * Implementacion del metodo obtener todos
	 * 
	 * @throws SpddExceptions
	 *
	 * @see co.gov.sdp.spdd.core.iservice.administracion.ls.IArgumentoListaSimpleConsultar.obtenerPorNombre
	 */
	@Override
	public GenericoDTO obtenerPorNombre(String nombre) throws SpddExceptions {
		GenericoDTO respuesta = new GenericoDTO();
		List<ArgumentoListaSimple> argumentoListaSimple = argumentoListaSimpleRepo.obtenerArgumentoPorNombre(nombre);
		List<ArgumentoListaSimpleDTO> listaRespuesta = argumentoListaSimpleMapper
				.argumentoListaSimpleEntitiesToDTO(argumentoListaSimple);
		respuesta.setLstObjectsDTO(new ArrayList<>(listaRespuesta));
		respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
		respuesta.setMsgRespuesta(
				NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_OBTENER_TODOS_ARGUMENTO_LISTA_SIMPLE_POR_NOMBRE,
						PaqueteMensajeEnum.MENSAJES, null));
		return respuesta;
	}

	/**
	 * Implementacion del metodo obtenerPorFiltro
	 * 
	 * @throws SpddExceptions
	 * @see
	 */
	@Override
	public GenericoDTO obtenerPorFiltro(ArgumentoListaSimpleDTO peticion) throws SpddExceptions {
		GenericoDTO respuesta = sessionFacadeAfs.consultarArgumentoListaSimplePorFiltro(peticion);
		respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
		respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_OBTENER_TODOS_LISTA_SIMPLE,
				PaqueteMensajeEnum.MENSAJES, null));
		return respuesta;
	}
	
	@Override
	public GenericoDTO consultarArgumentoPorIdPlanDesarrollo(Long idPlan) throws SpddExceptions {		
		GenericoDTO respuesta = sessionFacadeAfs.consultarArgumentoPorIdPdd(idPlan);
		respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
		respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(
				NHSPDDConstantes.MENSAJE_OBTENER_TODOS_ARGUMENTO_LISTA_SIMPLE, PaqueteMensajeEnum.MENSAJES, null));
		return respuesta;
	}

}
