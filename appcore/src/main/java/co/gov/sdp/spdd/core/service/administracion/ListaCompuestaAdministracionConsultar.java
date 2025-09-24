package co.gov.sdp.spdd.core.service.administracion;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.dto.ListaCompuestaDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.nhspdd.common.util.NHSPDDMensajes;
import co.gov.sdp.nhspdd.common.util.PaqueteMensajeEnum;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.spdd.core.iservice.administracion.lista.compuesta.IListaCompuestaAdministracionConsultar;
import co.gov.sdp.spdd.data.dao.interfaces.afs.IListaCompuestaServiceRepo;
import co.gov.sdp.spdd.data.irepositorio.afs.IListaCompuestaRepo;
import co.gov.sdp.spdd.data.mapper.ListaCompuestaMapper;
import co.gov.sdp.spdd.data.model.afs.ListaCompuesta;
import co.gov.sdp.spdd.data.sesionfacade.interfaces.ISessionFacadeAFS;

/**
 * Implementacion de las funcionalidades de consulta para el modulo de
 * administracion
 *
 * @author Raul Londono Murillo
 *
 */
@Service
public class ListaCompuestaAdministracionConsultar implements IListaCompuestaAdministracionConsultar {

	// Servicio repositorio de lista compuesta
	@Autowired
	IListaCompuestaServiceRepo listaCompuestaServiceRepo;

	// Repositorio de lista compuesta
	@Autowired
	IListaCompuestaRepo listaCompuestaRepo;

	// Mapper de ListaCompuesta
	@Autowired
	ListaCompuestaMapper listaCompuestaMapper;

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
	 * Implementacion del metodo obtenerListaCOmpuestaTodos
	 *
	 * @see co.gov.sdp.spdd.core.iservice.administracion.lista.compuesta.IListaCompuestaAdministracionConsultar.obtenerListaCompuestaTodos
	 */
	@Override
	public GenericoDTO obtenerListaCompuestaTodos() throws SpddExceptions {
		GenericoDTO respuesta = new GenericoDTO();
		List<ListaCompuesta> lista = listaCompuestaServiceRepo.obtenerTodos();
		List<ListaCompuestaDTO> listaRespuesta = listaCompuestaMapper.listaCompuestaEntitiesToDTO(lista);
		respuesta.setLstObjectsDTO(new ArrayList<>(listaRespuesta));
		respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
		respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(
				NHSPDDConstantes.MENSAJE_OBTENER_LISTA_COMPUESTA_CORRECTO, PaqueteMensajeEnum.MENSAJES, null));
		return respuesta;
	}

	/**
	 * Implementacion del metodo obtenerLista
	 *
	 * @see co.gov.sdp.spdd.core.iservice.administracion.lista.compuesta.IListaCompuestaAdministracionConsultar.obtenerLista
	 */
	@Override
	public ListaCompuestaDTO obtenerLista(Long idListaCompuesta) throws SpddExceptions {
		ListaCompuesta entidad = listaCompuestaServiceRepo.obtener(idListaCompuesta);
		ListaCompuestaDTO respuesta = new ListaCompuestaDTO();
		if (entidad != null) {
			respuesta = listaCompuestaMapper.listaCompuestaEntityToDTO(entidad);
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(
					NHSPDDConstantes.MENSAJE_OBTENER_POR_ID_LISTA_COMPUESTA, PaqueteMensajeEnum.MENSAJES, null));
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
	public GenericoDTO obtenerPaginado(ListaCompuestaDTO peticion) throws SpddExceptions {
		GenericoDTO respuesta = sessionFacadeAFS.consultarListaCompuestaPorFiltro(peticion);
		respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
		respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(
				NHSPDDConstantes.MENSAJE_OBTENER_TODOS_CONSECUTIVO_CORRECTO, PaqueteMensajeEnum.MENSAJES, null));

		return respuesta;
	}
}
