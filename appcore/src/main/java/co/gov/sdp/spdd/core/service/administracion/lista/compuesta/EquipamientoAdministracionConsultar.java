package co.gov.sdp.spdd.core.service.administracion.lista.compuesta;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;

import co.gov.sdp.nhspdd.common.dto.EquipamientoDTO;
import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.nhspdd.common.util.NHSPDDMensajes;
import co.gov.sdp.nhspdd.common.util.PaqueteMensajeEnum;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.spdd.core.iservice.administracion.lista.compuesta.IEquipamientoAdministracionConsultar;
import co.gov.sdp.spdd.data.dao.interfaces.afs.IEquipamientoServiceRepo;
import co.gov.sdp.spdd.data.irepositorio.afs.IEquipamentoRepo;
import co.gov.sdp.spdd.data.mapper.EquipamientoMapper;
import co.gov.sdp.spdd.data.model.afs.Equipamento;
import co.gov.sdp.spdd.data.sesionfacade.interfaces.ISessionFacadeAFS;
import co.gov.sdp.spdd.util.AuditoriaUtil;

/**
 * Implementacion de las funcionalidades de consulta para el modulo de
 * administracion
 *
 * @author Raul Londono Murillo
 *
 */
@Service
public class EquipamientoAdministracionConsultar implements IEquipamientoAdministracionConsultar {

    // Servicio Repositorio de equipamiento
    @Autowired
    IEquipamientoServiceRepo equipamientoServiceRepo;

    // Servicio Repositorio de equipamiento
    @Autowired
    IEquipamentoRepo equipamentoRepo;

    // Mapper de equipamiento
    @Autowired
    EquipamientoMapper equipamientoMapper;
    
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
     * Implementacion del metodo obtenerEstructuraMetaTodos
     *
     * @see
     * co.gov.sdp.nhspdd.core..iservice.administracion.lista.compuesta.IEstructuraMetaAdmnistracionConsultar.obtenerEstructuraMetaTodos
     */
    @Override
    public GenericoDTO obtenerEquipamientoTodos() throws SpddExceptions {
        GenericoDTO respuesta = new GenericoDTO();
        List<Equipamento> lista = equipamientoServiceRepo.obtenerTodos();
        List<EquipamientoDTO> listaRespuesta = equipamientoMapper.equipamientoEntitiesToDTO(lista);
        respuesta.setLstObjectsDTO(new ArrayList<>(listaRespuesta));
        respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
        respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(
                NHSPDDConstantes.MENSAJE_OBTENER_EQUIPAMIENTO_TODOS_CORRECTO, PaqueteMensajeEnum.MENSAJES, null));
        return respuesta;
    }
    
	/**
	 * @throws JsonProcessingException 
	 * 
	 */
	@Override
	public GenericoDTO obtenerPaginado(EquipamientoDTO peticion) throws SpddExceptions, JsonProcessingException {
		GenericoDTO respuesta = sessionFacadeAFS.consultarEquipamientoPorFiltro(peticion);
		respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
		respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(
				NHSPDDConstantes.MENSAJE_OBTENER_EQUIPAMIENTO_TODOS_CORRECTO, PaqueteMensajeEnum.MENSAJES, null));
		auditoria.crearAuditoria(peticion.toString(), peticion.toString(), "CONSULTAR_AFS", "CONSULTAR_EQUIPAMIENTO");
		return respuesta;
	}

}
