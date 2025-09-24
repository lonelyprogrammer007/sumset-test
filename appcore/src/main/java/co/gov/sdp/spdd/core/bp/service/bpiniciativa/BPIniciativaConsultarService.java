package co.gov.sdp.spdd.core.bp.service.bpiniciativa;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.dto.HistorialSectorialDTO;
import co.gov.sdp.nhspdd.common.dto.PotObraDTO;
import co.gov.sdp.nhspdd.common.dto.RespuestaApiDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpIniciativaCiudadanaDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpIniciativaCondicionDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpIniciativaEtarioDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpIniciativaUbicacionDTO;
import co.gov.sdp.nhspdd.common.dto.seguridad.EntidadSeguridadDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.nhspdd.common.util.NHSPDDMensajes;
import co.gov.sdp.nhspdd.common.util.PaqueteMensajeEnum;
import co.gov.sdp.spdd.core.bp.iservice.bpiniciativa.IBPIniciativaConsultarService;
import co.gov.sdp.spdd.data.sesionfacade.interfaces.ISessionFacadeAFS;
import co.gov.sdp.spdd.data.sesionfacade.interfaces.ISessionFacadeBP;
import co.gov.sdp.spdd.util.MetodosRest;

@Service
public class BPIniciativaConsultarService implements IBPIniciativaConsultarService {

	@Autowired
	ISessionFacadeBP sessionFacadeBP;

	@Autowired
	ISessionFacadeAFS sessionFacadeAFS;
	
	@Autowired
	MetodosRest<RespuestaApiDTO<EntidadSeguridadDTO>> entidad;
	
	@Autowired
	HttpServletRequest request;
	
	@Autowired
	ObjectMapper objectMapper;

	@Override
	public GenericoDTO obtenerTodasLaIniciativasCiudadanas(BpIniciativaCiudadanaDTO peticion) throws SpddExceptions {
		GenericoDTO respuesta = sessionFacadeBP.filtrarIniciativaCiudadana(peticion);
		
		String token = request.getHeader("Authorization");
		entidad.agregarToken(token.substring(6, token.length()));
		
		TypeReference<List<BpIniciativaCiudadanaDTO>> typeBpIniciativaCiudadanaDTO = new TypeReference<List<BpIniciativaCiudadanaDTO>>() { };
		List<BpIniciativaCiudadanaDTO> listaBpIniciativaCiudadanaDTO = objectMapper.convertValue(respuesta.getLstObjectsDTO(), typeBpIniciativaCiudadanaDTO);
		
		listaBpIniciativaCiudadanaDTO.stream().forEach(bpIniciativaCiudadanaDTOAux -> {
			
			RespuestaApiDTO<EntidadSeguridadDTO> entidades = entidad.get(
					NHSPDDConstantes.CONTEXTO_SEGURIDAD_API + "/api/entidad/consultarentidad/"
							+ bpIniciativaCiudadanaDTOAux.getCodigoEntidad(),
					new ParameterizedTypeReference<RespuestaApiDTO<EntidadSeguridadDTO>>() {
					});
			
			bpIniciativaCiudadanaDTOAux.setNombreEntidad(entidades.getObjetos().get(0).getCodigoEntidad());
		} );		
		
		respuesta.setLstObjectsDTO(new ArrayList<>(listaBpIniciativaCiudadanaDTO));
		respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
		respuesta.setMsgRespuesta(
				NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_OBTENER_TODOS_PRESENTACION_INICIATIVA_CIUDADANA,
						PaqueteMensajeEnum.MENSAJES, null));
		return respuesta;
	}

	@Override
	public BpIniciativaCiudadanaDTO consultaDetallaIniciativaCiudadana(Long idIniciativa) throws SpddExceptions {

		BpIniciativaCiudadanaDTO respuesta = sessionFacadeBP.consultarBpIniciativaCiudadanaPorId(idIniciativa);
		List<BpIniciativaEtarioDTO> gruposEtarios = sessionFacadeBP.consultarGruposEtariosPorIniciativa(idIniciativa);
		String concat = "";
		String nombres = "";
		String split = "";
		String splitComa = "";
		for (int i = 0; i < gruposEtarios.size(); i++) {

			concat += split + gruposEtarios.get(i).getIdLsEtario();
			nombres += splitComa + gruposEtarios.get(i).getNombreEtario();
			split = ";";
			splitComa = ",";
		}
		respuesta.setGruposEtarios(concat);
		respuesta.setNombreGruposEtarios(nombres);
		List<BpIniciativaUbicacionDTO> ubicaciones = sessionFacadeBP
				.consultarUbicacionesGruposEtariosPorIniciativa(idIniciativa);
		List<BpIniciativaUbicacionDTO> upz = new ArrayList<>();
		List<BpIniciativaUbicacionDTO> upr = new ArrayList<>();
		ubicaciones.stream().forEach(u -> {
			if (u.getNombreUpr().equalsIgnoreCase("NA")) {
				upz.add(u);
			} else {
				upr.add(u);
			}
		});

		if (upz.size() == 1 ) {
			respuesta.setIdLcTerritorializacionUpz(upz.get(0).getIdTerritorializacion());
			respuesta.setNombreUpz(upz.get(0).getNombreUpz());
			respuesta.setNombreBarrio(upz.get(0).getNombreBarrio());
			respuesta.setIdLsBarrio(upz.get(0).getIdLsBarrio());
			respuesta.setIdLsUpz(upz.get(0).getIdLsUpz());
		} else {
			respuesta.setIdLcTerritorializacionUpz(-2L);
			respuesta.setNombreUpz("Todas las Upz");
		}
		if (upr.size() == 1) {
			respuesta.setIdLcTerritorializacionUpr(upr.get(0).getIdTerritorializacion());
			respuesta.setNombreUpr(upr.get(0).getNombreUpr());
			respuesta.setNombreVereda(upr.get(0).getNombreVereda());
			respuesta.setIdLsVereda(upr.get(0).getIdLsVereda());
			respuesta.setIdLsUpr(upr.get(0).getIdLsUpr());
		} else {
			respuesta.setIdLcTerritorializacionUpr(-2L);
			respuesta.setNombreUpr("Todas las Upr");
		}
		List<BpIniciativaCondicionDTO> condicion = sessionFacadeBP.obtenerCondicionesPorIniciativa(idIniciativa);
		concat = "";
		nombres = "";
		split = "";
		splitComa = "";
		for (int i = 0; i < condicion.size(); i++) {

			concat += split + condicion.get(i).getIdLsCondicion();
			nombres += splitComa + condicion.get(i).getNombreCondicion();
			split = ";";
			splitComa = ",";
		}
		respuesta.setCodicionesPoblacionales(concat);
		respuesta.setNombrePoblaciones(nombres);
		
		String token = request.getHeader("Authorization");
		entidad.agregarToken(token.substring(6, token.length()));
		
		RespuestaApiDTO<EntidadSeguridadDTO> entidades = entidad.get(
				NHSPDDConstantes.CONTEXTO_SEGURIDAD_API + "/api/entidad/consultarentidad/"
						+ respuesta.getCodigoEntidad(),
				new ParameterizedTypeReference<RespuestaApiDTO<EntidadSeguridadDTO>>() {
				});
		
		respuesta.setNombreEntidad(entidades.getObjetos().get(0).getCodigoEntidad());	

		return respuesta;
	}

	@Override
	public GenericoDTO consultarTodosBpProyInvIniciativaPorIdIniciativa(Long idIniciativa) throws SpddExceptions {
		GenericoDTO respuesta = sessionFacadeBP.consultarTodosBpProyInvIniciativaPorIdIniciativa(idIniciativa);
		respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
		respuesta.setMsgRespuesta(
				NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_OBTENER_TODOS_BP_PROYECTO_INVERSION_CORRECTO,
						PaqueteMensajeEnum.MENSAJES, null));
		return respuesta;
	}
	
	

}
