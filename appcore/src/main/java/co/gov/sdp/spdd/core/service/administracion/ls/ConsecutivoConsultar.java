package co.gov.sdp.spdd.core.service.administracion.ls;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import co.gov.sdp.nhspdd.common.dto.ConsecutivoDTO;
import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.dto.PddDTO;
import co.gov.sdp.nhspdd.common.dto.RespuestaApiDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PotObraEntidadDTO;
import co.gov.sdp.nhspdd.common.dto.seguridad.EntidadSeguridadDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.nhspdd.common.util.NHSPDDMensajes;
import co.gov.sdp.nhspdd.common.util.PaqueteMensajeEnum;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.spdd.core.iservice.administracion.ls.IConsecutivoConsultar;
import co.gov.sdp.spdd.data.dao.interfaces.afs.IConsecutivoServiceRepo;
import co.gov.sdp.spdd.data.mapper.ConsecutivoMapper;
import co.gov.sdp.spdd.data.model.afs.Consecutivo;
import co.gov.sdp.spdd.data.sesionfacade.interfaces.ISessionFacadeAFS;
import co.gov.sdp.spdd.data.sesionfacade.interfaces.ISessionFacadeIP;
import co.gov.sdp.spdd.util.MetodosRest;

/**
 * Clase que implementa los metodos de consultar asociados a consecutivo
 *
 * @author Bryan Munoz
 *
 */
@Service
public class ConsecutivoConsultar implements IConsecutivoConsultar {

	// Servicio que permite utilizar las operaciones basicas de la bd
	@Autowired
	IConsecutivoServiceRepo consecutivoServiceRepo;

	// Mapper que permite convertir un dto a entidad y viceversa
	@Autowired
	ConsecutivoMapper consecutivoMapper;

	@Autowired
	ISessionFacadeAFS sessionFacadeAFS;
	
	@Autowired
	ISessionFacadeIP sessionFacadeIP;

	@Autowired
	SPDDLogger spddLogger;
	
	@Autowired
	MetodosRest<RespuestaApiDTO<EntidadSeguridadDTO>> entidad;
	
	@Autowired
	HttpServletRequest request;
	
	@Autowired
	ObjectMapper objectMapper;

	/**
	 * 
	 */
	@Override
	public GenericoDTO obtenerTodos() throws SpddExceptions {
		GenericoDTO respuesta = new GenericoDTO();
		List<Consecutivo> entidad = consecutivoServiceRepo.obtenerTodos();
		List<ConsecutivoDTO> listaRespuesta = consecutivoMapper.consecutivoEntitiesToDTO(entidad);
		respuesta.setLstObjectsDTO(new ArrayList<>(listaRespuesta));
		respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
		respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(
				NHSPDDConstantes.MENSAJE_OBTENER_TODOS_CONSECUTIVO_CORRECTO, PaqueteMensajeEnum.MENSAJES, null));
		return respuesta;
	}

	/**
	 * 
	 */
	@Override
	public GenericoDTO obtenerPaginado(ConsecutivoDTO peticion) throws SpddExceptions {
		GenericoDTO respuesta = sessionFacadeAFS.consultarConsecutivoPorFiltro(peticion);
		
		TypeReference<List<ConsecutivoDTO>> typeListaConsecutivoDTO = new TypeReference<List<ConsecutivoDTO>>() {
		};
		List<ConsecutivoDTO> listaConsecutivoDTO = objectMapper
				.convertValue(respuesta.getLstObjectsDTO(), typeListaConsecutivoDTO);
		
		String token = request.getHeader("Authorization");
		entidad.agregarToken(token.substring(6, token.length()));
		
		listaConsecutivoDTO.stream().forEach(ConsecutivoDTOAux -> {
			String stringEntidadAux = "";
			
			if(ConsecutivoDTOAux.getCodigoEntidad() != null)
			{
				RespuestaApiDTO<EntidadSeguridadDTO> entidades = entidad.get(
						NHSPDDConstantes.CONTEXTO_SEGURIDAD_API + "/api/entidad/consultarentidad/"
								+ ConsecutivoDTOAux.getCodigoEntidad(),
						new ParameterizedTypeReference<RespuestaApiDTO<EntidadSeguridadDTO>>() {
						});
				stringEntidadAux = entidades.getObjetos().get(0).getCodigoEntidad();
			}
			else
			{
				stringEntidadAux = NHSPDDConstantes.STRING_DE_ENTIDAD_NO_APLICA;
			}
			
			
			
			ConsecutivoDTOAux.setStringEntidad(stringEntidadAux);			
		});
		
		
		respuesta.setLstObjectsDTO(new ArrayList<>(listaConsecutivoDTO));
		respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
		respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(
				NHSPDDConstantes.MENSAJE_OBTENER_TODOS_CONSECUTIVO_CORRECTO, PaqueteMensajeEnum.MENSAJES, null));

		return respuesta;
	}

	@Override
	public ConsecutivoDTO obtenerConsecutivoPorPlanYEntidad(ConsecutivoDTO peticion) throws SpddExceptions {
		PddDTO pddVigente = sessionFacadeIP.consultarPddVigente();
		
		ConsecutivoDTO respuesta = sessionFacadeAFS.obtenerConsecutivosPorPlanYEntidad(peticion.getIdPlanDesarrollo(),
				peticion.getCodigoEntidad(), peticion.getNombre());
		respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
		respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(
				NHSPDDConstantes.MENSAJE_OBTENER_TODOS_CONSECUTIVO_CORRECTO, PaqueteMensajeEnum.MENSAJES, null));

		return respuesta;
	}
}
