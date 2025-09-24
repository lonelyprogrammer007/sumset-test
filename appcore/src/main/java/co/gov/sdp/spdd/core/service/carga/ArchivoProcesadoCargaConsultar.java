package co.gov.sdp.spdd.core.service.carga;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.gov.sdp.nhspdd.common.dto.ArchivoProcesadoDTO;
import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.nhspdd.common.util.NHSPDDMensajes;
import co.gov.sdp.nhspdd.common.util.PaqueteMensajeEnum;
import co.gov.sdp.spdd.core.iservice.carga.IArchivoProcesadoCargaConsultar;
import co.gov.sdp.spdd.data.dao.interfaces.afs.IArchivoProcesadoServiceRepo;
import co.gov.sdp.spdd.data.dao.interfaces.afs.IEntidadServiceRepo;
import co.gov.sdp.spdd.data.mapper.ArchivoProcesadoMapper;
import co.gov.sdp.spdd.data.model.afs.ArchivoProcesado;
import co.gov.sdp.spdd.data.sesionfacade.interfaces.ISessionFacadeAFS;

/**
 * Esta clase implementa todos los metodos de consulta de archivoProcesado
 *
 * @author Raul Londono Murillo
 *
 */
@Service
public class ArchivoProcesadoCargaConsultar implements IArchivoProcesadoCargaConsultar {

	/**
	 * Objeto que me trae consultar especificas de la entidad
	 */
	@Autowired
	IArchivoProcesadoServiceRepo archivoProcesadoServiceRepo;

	/**
	 * Objeto que permite el mapeo de componente de gestion
	 */
	@Autowired
	ArchivoProcesadoMapper archivoProcesadoMapper;

	/**
	 * Objeto que contiene metodos de consultas especificas de entidadServiceRepo
	 */
	@Autowired
	IEntidadServiceRepo entidadServiceRepo;

	/**
	 * 
	 */
	@Autowired
	ISessionFacadeAFS sessionFacadeAFS;

	/**
	 * 
	 */
	@Override
	public GenericoDTO obtenerArchivosProcesadosTodos() throws SpddExceptions {

		GenericoDTO respuesta = new GenericoDTO();

		List<ArchivoProcesado> lista = archivoProcesadoServiceRepo.obtenerTodos();

		List<ArchivoProcesadoDTO> listaRespuesta = archivoProcesadoMapper.ArchivoProcesadoEntitiesToDTO(lista);

		respuesta.setLstObjectsDTO(new ArrayList<>(listaRespuesta));
		respuesta.setCodigoRespuesta(200);
		respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(
				NHSPDDConstantes.MENSAJE_OBTENER_ARCHIVOS_PLANOS_CARGADOS_CORRECTO, PaqueteMensajeEnum.MENSAJES, null));

		return respuesta;
	}

	/**
	 * 
	 */
	@Override
	public GenericoDTO obtenerPaginado(ArchivoProcesadoDTO peticion) throws SpddExceptions {
		GenericoDTO respuesta = sessionFacadeAFS.consultarArchivoProcesadoPorFiltro(peticion);
		respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
		respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(
				NHSPDDConstantes.MENSAJE_OBTENER_ARCHIVOS_PLANOS_CARGADOS_CORRECTO, PaqueteMensajeEnum.MENSAJES, null));

		return respuesta;
	}

}
