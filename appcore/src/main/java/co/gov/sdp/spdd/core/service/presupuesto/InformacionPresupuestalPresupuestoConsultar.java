package co.gov.sdp.spdd.core.service.presupuesto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.dto.InformacionPresupuestalDTO;
import co.gov.sdp.nhspdd.common.dto.PddNivelAtributoDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.nhspdd.common.util.NHSPDDMensajes;
import co.gov.sdp.nhspdd.common.util.PaqueteMensajeEnum;
import co.gov.sdp.spdd.core.iservice.presupuesto.IInformacionPresupuestalPresupuestoConsultar;
import co.gov.sdp.spdd.data.dao.interfaces.afs.IInformacionPresupuestalServiceRepo;
import co.gov.sdp.spdd.data.mapper.InformacionPresupuestalMapper;
import co.gov.sdp.spdd.data.model.afs.InformacionPresupuestal;
import co.gov.sdp.spdd.data.sesionfacade.interfaces.ISessionFacadeAFS;
import co.gov.sdp.spdd.data.sesionfacade.interfaces.ISessionFacadeIP;
import co.gov.sdp.spdd.util.AuditoriaUtil;

/**
 * Implementacion de las funcionalidades de consulta para el modulo de
 * presupuesto
 *
 * @author Raul Londono Murillo
 *
 */
@Service
public class InformacionPresupuestalPresupuestoConsultar implements IInformacionPresupuestalPresupuestoConsultar {

	// Servicio Repositorio de informacion presupuestal
	@Autowired
	IInformacionPresupuestalServiceRepo informacionPresupuestalServiceRepo;

	// Mapper de informacion presupuestal
	@Autowired
	InformacionPresupuestalMapper informacionPresupuestalMapper;
	
	@Autowired
	ObjectMapper objectMapper;
	
	@Autowired
	ISessionFacadeIP sessionFacadeIP;
	/**
	 * 
	 */
	@Autowired
	ISessionFacadeAFS sessionFacadeAFS;
	
	/**
	 * Auditoria
	 */
	@Autowired
	AuditoriaUtil auditoria;

	/**
	 * Implementacion del metodo obtenerInformacionPresupuestalTodos
	 *
	 * @see co.gov.sdp.nhspdd.core..iservice.administracion.presupuesto.IInformacionPresupuestalPresupuestoConsultar.obtenerInformacionPresupuestalTodos
	 */
	@Override
	public GenericoDTO obtenerInformacionPresupuestalTodos() throws SpddExceptions {
		GenericoDTO respuesta = new GenericoDTO();
		List<InformacionPresupuestal> lista = informacionPresupuestalServiceRepo.obtenerTodos();
		List<InformacionPresupuestalDTO> listaRespuesta = informacionPresupuestalMapper
				.informacionPresupuestalEntitiesToDTO(lista);
		respuesta.setLstObjectsDTO(new ArrayList<>(listaRespuesta));
		respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
		respuesta.setMsgRespuesta(
				NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_OBTENER_INFORMACION_PRESUPUESTAL_TODOS_CORRECTO,
						PaqueteMensajeEnum.MENSAJES, null));

		return respuesta;
	}

	/**
	 * @throws JsonProcessingException 
	 * 
	 */
	@Override
	public GenericoDTO obtenerPaginado(InformacionPresupuestalDTO peticion) throws SpddExceptions, JsonProcessingException {
		GenericoDTO respuesta = sessionFacadeAFS.consultarInformacionPresupuestalPorFiltro(peticion);
		
		TypeReference<List<InformacionPresupuestalDTO>> type = new TypeReference<List<InformacionPresupuestalDTO>>() {};
		List<InformacionPresupuestalDTO> listaInformacionPresupuestalDTO = objectMapper.convertValue(respuesta.getLstObjectsDTO(), type);
		
		for(InformacionPresupuestalDTO infoAux : listaInformacionPresupuestalDTO)
		{	
			if(infoAux.getCodigoN3() != null)
			{
				PddNivelAtributoDTO nivel3 = sessionFacadeIP.consultarPddNivelAtributoPorId(infoAux.getCodigoN3());
				PddNivelAtributoDTO nivel2 = sessionFacadeIP.consultarPddNivelAtributoPorId(nivel3.getIdAtributoPadre());
				PddNivelAtributoDTO nivel1 = sessionFacadeIP.consultarPddNivelAtributoPorId(nivel2.getIdAtributoPadre());
				
				infoAux.setCodigoN1Aux(completarCantidadCaracteresConCero(nivel1.getNumero(), 2));
				infoAux.setIdCodigoN1Aux(nivel1.getIdAtributo());
				
				infoAux.setCodigoN2Aux(completarCantidadCaracteresConCero(nivel2.getNumero(), 2));
				infoAux.setIdCodigoN2Aux(nivel2.getIdAtributo());
				
				infoAux.setCodigoN3Aux(completarCantidadCaracteresConCero(nivel3.getNumero(), 4));
				infoAux.setIdCodigoN3Aux(nivel3.getIdAtributo());
			}
			
		}
		
		respuesta.setLstObjectsDTO(new ArrayList<>(listaInformacionPresupuestalDTO));
		respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
		respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(
				NHSPDDConstantes.MENSAJE_OBTENER_INFORMACION_PRESUPUESTAL_TODOS_CORRECTO, PaqueteMensajeEnum.MENSAJES, null));
		auditoria.crearAuditoria(peticion.toString(), peticion.toString(), "CONSULTAR_AFS", "CONSULTAR_INFO_PRESUPUESTAL");
		return respuesta;
	}

	/**
	 * 
	 */
	@Override
	public GenericoDTO obtenerInformacionPresupuestalPorPlanDesarrollo(Long id) throws SpddExceptions {
		GenericoDTO respuesta = new GenericoDTO();
		List<InformacionPresupuestal> lista = informacionPresupuestalServiceRepo
				.obtenerInformacionPresupuestalPorPlanDesarrollo(id);
		List<InformacionPresupuestalDTO> listaRespuesta = informacionPresupuestalMapper
				.informacionPresupuestalEntitiesToDTO(lista);
		respuesta.setLstObjectsDTO(new ArrayList<>(listaRespuesta));
		respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
		respuesta.setMsgRespuesta(
				NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_OBTENER_INFORMACION_PRESUPUESTAL_PLAN_DESARROLLO,
						PaqueteMensajeEnum.MENSAJES, null));
		return respuesta;
	}

	/**
	 * 
	 */
	@Override
	public GenericoDTO obtenerInformacionPresupuestalPorEntidad(Long id) throws SpddExceptions {
		GenericoDTO respuesta = new GenericoDTO();
		List<InformacionPresupuestal> lista = informacionPresupuestalServiceRepo
				.obtenerInformacionPresupuestalPorEntidad(id);
		List<InformacionPresupuestalDTO> listaRespuesta = informacionPresupuestalMapper
				.informacionPresupuestalEntitiesToDTO(lista);
		respuesta.setLstObjectsDTO(new ArrayList<>(listaRespuesta));
		respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
		respuesta.setMsgRespuesta(
				NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_OBTENER_INFORMACION_PRESUPUESTAL_PLAN_ENTIDAD,
						PaqueteMensajeEnum.MENSAJES, null));
		return respuesta;
	}

	@Override
	public GenericoDTO obtenerPorArchivoInfo(Long id) {
		GenericoDTO respuesta = sessionFacadeAFS.obtenerArchivoInfoPresupuestal(id);
		respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
		respuesta.setMsgRespuesta(
				NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_OBTENER_INFORMACION_PRESUPUESTAL_TODOS_CORRECTO,
						PaqueteMensajeEnum.MENSAJES, null));
		return respuesta;
	}
	
	private String completarCantidadCaracteresConCero(Long numero, int cantidadCaracteres)
	{
		String numeroString = String.valueOf(numero);
		String numeroStringAux = "";
		
		if(numeroString.length() < cantidadCaracteres)
		{
			for(int i = 0 ; i < cantidadCaracteres - numeroString.length() ; i++)
			{
				numeroStringAux = numeroStringAux + "0";
			}			
		}		
		
		return String.format("%s%s", numeroStringAux,numeroString);
		
	}
}
