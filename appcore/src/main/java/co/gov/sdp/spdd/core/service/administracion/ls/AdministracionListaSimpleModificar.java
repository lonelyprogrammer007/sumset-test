package co.gov.sdp.spdd.core.service.administracion.ls;

import org.hibernate.exception.GenericJDBCException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.gov.sdp.nhspdd.common.dto.ListaSimpleDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.nhspdd.common.util.NHSPDDMensajes;
import co.gov.sdp.nhspdd.common.util.PaqueteMensajeEnum;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.spdd.core.iservice.administracion.ls.IAdministracionListaSimpleModificar;
import co.gov.sdp.spdd.data.dao.interfaces.afs.IListaSimpleServiceRepo;
import co.gov.sdp.spdd.data.mapper.ListaSimpleMapper;
import co.gov.sdp.spdd.data.model.afs.ListaSimple;

/**
 * Clase que implementa todos los metodos asociados a la modificacion de listas
 *
 * @author Bryan Munoz
 *
 */
@Service
public class AdministracionListaSimpleModificar implements IAdministracionListaSimpleModificar {

	/**
	 * Objeto que mapea la entidad a dto o viceversa
	 */
	@Autowired
	ListaSimpleMapper listaSimpleMapper;

	/**
	 * Objeto que trae las operaciones basicas de la bd relacionado con listas
	 * simples
	 */
	@Autowired
	IListaSimpleServiceRepo listaSimpleServiceRepo;

	/*
	 * 
	 */
	@Autowired
	SPDDLogger spddLogger;

	/**
	 * Implementacion del metodo modificar lista simple
	 *
	 * @see co.gov.sdp.spdd.core.iservice.administracion.ls.IArgumentoListaSimpleModificar.modificarListaSimple
	 *
	 */
	@Override
	public ListaSimpleDTO modificarListaSimple(ListaSimpleDTO peticion) throws SpddExceptions,GenericJDBCException {
		ListaSimple entidad = listaSimpleServiceRepo.obtener(peticion.getIdListaSimple());
		ListaSimpleDTO respuesta = new ListaSimpleDTO();
		if (entidad != null) {
			entidad = listaSimpleMapper.listaSimpleDTOToEntity(peticion);

			listaSimpleServiceRepo.guardar(entidad);
			respuesta = listaSimpleMapper.listaSimpleEntityToDTO(entidad);
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_MODIFICAR_LISTA_SIMPLE,
					PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
		} else {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_TIEMPO_EXCEDIDO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_NO_SE_ENCUENTRA_LA_ENTIDAD,
					PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));

		}
		return respuesta;
	}
}
