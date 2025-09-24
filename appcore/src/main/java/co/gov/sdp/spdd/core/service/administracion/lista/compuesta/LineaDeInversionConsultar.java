package co.gov.sdp.spdd.core.service.administracion.lista.compuesta;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;

import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.dto.LineaDeInversionDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.nhspdd.common.util.NHSPDDMensajes;
import co.gov.sdp.nhspdd.common.util.PaqueteMensajeEnum;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.spdd.core.iservice.administracion.lista.compuesta.ILineaDeInversionConsultar;
import co.gov.sdp.spdd.data.dao.interfaces.afs.ILineaDeInversionServiceRepo;
import co.gov.sdp.spdd.data.mapper.LineaDeInversionMapper;
import co.gov.sdp.spdd.data.model.afs.LineaDeInversion;
import co.gov.sdp.spdd.data.sesionfacade.interfaces.ISessionFacadeAFS;
import co.gov.sdp.spdd.util.AuditoriaUtil;

/**
 * Esta clase implementa los metodos asociados a una consulta
 *
 * @author Bryan Munoz
 *
 */
@Service
public class LineaDeInversionConsultar implements ILineaDeInversionConsultar {

    /**
     * Objeto que permite realizar las operaciones basicas de bd
     */
    @Autowired
    ILineaDeInversionServiceRepo lineaDeInversionServiceRepo;
    /**
     * Objeto que permite realizar el mapeo de dto a entidad y viceversa
     */
    @Autowired
    LineaDeInversionMapper lineaDeInversionMapper;
    
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
     * Implementacion del metodo obtener todos
     *
     * @see
     * co.gov.sdp.nhspdd.core.iservice.administracion.lista.compuesta.IlineaDeInversionConsultar.obtenerTodos
     */
    @Override
    public GenericoDTO obtenerTodos() throws SpddExceptions {
        GenericoDTO respuesta = new GenericoDTO();
        List<LineaDeInversion> entidad = lineaDeInversionServiceRepo.obtenerTodos();
        List<LineaDeInversionDTO> listRespuesta = lineaDeInversionMapper.lineaDeInversionEntitiesTODTO(entidad);
        respuesta.setLstObjectsDTO(new ArrayList<>(listRespuesta));
        respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
        respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(
                NHSPDDConstantes.MENSAJE_OBTENER_TODOS_LINEA_INVERSION_CORRECTO, PaqueteMensajeEnum.MENSAJES, null));
        return respuesta;
    }
    
	/**
	 * @throws JsonProcessingException 
	 * 
	 */
	@Override
	public GenericoDTO obtenerPaginado(LineaDeInversionDTO peticion) throws SpddExceptions, JsonProcessingException {
		GenericoDTO respuesta = sessionFacadeAFS.consultarLineaDeInversionPorFiltro(peticion);
		respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
		respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(
				NHSPDDConstantes.MENSAJE_OBTENER_TODOS_LINEA_INVERSION_CORRECTO, PaqueteMensajeEnum.MENSAJES, null));
		auditoria.crearAuditoria(peticion.toString(), peticion.toString(), "CONSULTAR_AFS", "CONSULTAR_LINEA_INVERSION");
		return respuesta;
	}

	@Override
	public GenericoDTO obtenerPorSector(String sector) throws SpddExceptions {
		GenericoDTO respuesta = sessionFacadeAFS.buscarPorSectorLinea(sector);
		respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
		respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(
				NHSPDDConstantes.MENSAJE_OBTENER_TODOS_LINEA_INVERSION_CORRECTO, PaqueteMensajeEnum.MENSAJES, null));
		return respuesta;
	}

}
