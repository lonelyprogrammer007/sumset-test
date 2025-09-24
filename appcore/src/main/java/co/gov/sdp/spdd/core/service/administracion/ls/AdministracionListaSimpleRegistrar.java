package co.gov.sdp.spdd.core.service.administracion.ls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.gov.sdp.nhspdd.common.dto.ListaSimpleDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.nhspdd.common.util.NHSPDDMensajes;
import co.gov.sdp.nhspdd.common.util.PaqueteMensajeEnum;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.spdd.core.iservice.administracion.ls.IAdministracionListaSimpleRegistrar;
import co.gov.sdp.spdd.data.dao.interfaces.afs.IListaSimpleServiceRepo;
import co.gov.sdp.spdd.data.mapper.ListaSimpleMapper;
import co.gov.sdp.spdd.data.model.afs.ListaSimple;

/**
 * Esta clase implementa los metodos de IAdministracionListaSimpleRegistrar que
 * son encargados de guardar en la bd
 *
 * @author Bryan Munoz
 *
 */
@Service
public class AdministracionListaSimpleRegistrar implements IAdministracionListaSimpleRegistrar {

	/**
	 * Objeto que permite manejar las operaciones basicas de bd
	 */
	@Autowired
	IListaSimpleServiceRepo listaSimpleServiceRepo;
	/**
	 * Objeto que permite mapear de dto a entidad y viceversa
	 */
	@Autowired
	ListaSimpleMapper listaSimpleMapper;

	@Autowired
	SPDDLogger spddLogger;

	/**
	 * Implementacion del metodo guardar lista simple
	 *
	 * @see co.gov.sdp.spdd.core.iservice.administracion.ls.IArgumentoListaSimpleRegistrar.guardarListaSimple
	 *
	 */
	@Override
	public ListaSimpleDTO guardarListaSimple(ListaSimpleDTO listaSimpleDTO) throws SpddExceptions {
		ListaSimple entidad = listaSimpleMapper.listaSimpleDTOToEntity(listaSimpleDTO);
		listaSimpleServiceRepo.guardar(entidad);
		ListaSimpleDTO respuesta = listaSimpleMapper.listaSimpleEntityToDTO(entidad);
		respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
		respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_REGISTRAR_LISTA_SIMPLE,
				PaqueteMensajeEnum.MENSAJES, listaSimpleDTO.getLenguaje()));
		return respuesta;
	}
}
