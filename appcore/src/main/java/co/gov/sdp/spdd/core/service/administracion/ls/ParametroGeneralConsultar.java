package co.gov.sdp.spdd.core.service.administracion.ls;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.dto.ParametroGeneralDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.nhspdd.common.util.NHSPDDMensajes;
import co.gov.sdp.nhspdd.common.util.PaqueteMensajeEnum;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.spdd.core.iservice.administracion.ls.IParanetroGeneralConsultar;
import co.gov.sdp.spdd.data.dao.interfaces.afs.IParametroGeneralServiceRepo;
import co.gov.sdp.spdd.data.mapper.ParametroGeneralMapper;
import co.gov.sdp.spdd.data.model.afs.ParametroGeneral;
import co.gov.sdp.spdd.data.sesionfacade.interfaces.ISessionFacadeAFS;

/**
 * Clase que implementa los metodos asociados con consultar asociados a
 * parametro general
 *
 * @author SumSet 2019
 *
 */
@Service
public class ParametroGeneralConsultar implements IParanetroGeneralConsultar {

	// Servicio que proporciona metodos de bd
	@Autowired
	IParametroGeneralServiceRepo parametroGeneralServiceRepo;

	// Servicio que permite convertir un dto a entidad y viceversa
	@Autowired
	ParametroGeneralMapper mapper;
	
    /**
	 * 
	 */
	@Autowired
	ISessionFacadeAFS sessionFacadeAFS;

	/**
	 * 
	 */
	@Autowired
	SPDDLogger spddLogger;

	/**
	 * Implementacion del metodo obtenerTodos
	 *
	 * @see
	 */
	@Override
	public GenericoDTO obtenerTodos() throws SpddExceptions {
		GenericoDTO respuesta = new GenericoDTO();
		List<ParametroGeneral> entidad = parametroGeneralServiceRepo.obtenerTodos();
		List<ParametroGeneralDTO> listaRespuesta = mapper.parametroGeneralEntitiesToDTO(entidad);
		respuesta.setLstObjectsDTO(new ArrayList<>(listaRespuesta));
		respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
		respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(
				NHSPDDConstantes.MENSAJE_OBTENER_TODOS_PARAMETRO_GENERAL_CORRECTO, PaqueteMensajeEnum.MENSAJES, null));
		return respuesta;
	}
	
	/**
	 * 
	 */
	@Override
	public GenericoDTO obtenerPaginado(ParametroGeneralDTO peticion) throws SpddExceptions {
		GenericoDTO respuesta = sessionFacadeAFS.consultarParametroGeneralPorFiltro(peticion);
		respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
		respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(
				NHSPDDConstantes.MENSAJE_OBTENER_TODOS_PARAMETRO_GENERAL_CORRECTO, PaqueteMensajeEnum.MENSAJES, null));

		return respuesta;
	}

}
