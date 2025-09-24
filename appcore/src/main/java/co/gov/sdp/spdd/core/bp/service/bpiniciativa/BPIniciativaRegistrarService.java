package co.gov.sdp.spdd.core.bp.service.bpiniciativa;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import co.gov.sdp.nhspdd.common.dto.ConsecutivoDTO;
import co.gov.sdp.nhspdd.common.dto.TerritorializacionDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpIniciativaCiudadanaDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpIniciativaCondicionDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpIniciativaEtarioDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpIniciativaUbicacionDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.nhspdd.common.util.NHSPDDMensajes;
import co.gov.sdp.nhspdd.common.util.PaqueteMensajeEnum;
import co.gov.sdp.spdd.core.bp.iservice.bpiniciativa.IBPIniciativaRegistrarService;
import co.gov.sdp.spdd.data.sesionfacade.interfaces.ISessionFacadeAFS;
import co.gov.sdp.spdd.data.sesionfacade.interfaces.ISessionFacadeBP;

/**
 * 
 * @author Bryan Muñoz
 *
 */
@Service
public class BPIniciativaRegistrarService implements IBPIniciativaRegistrarService {

	@Autowired
	ISessionFacadeBP sessionFacadeBP;

	@Autowired
	ISessionFacadeAFS sessionFacadeAFS;

	@Autowired
	ObjectMapper mapper;

	@Override
	public BpIniciativaCiudadanaDTO guardarIniciativaCiudadana(BpIniciativaCiudadanaDTO peticion)
			throws SpddExceptions {
		ConsecutivoDTO consecutivo = sessionFacadeAFS.obtenerConsecutivosPorPlanYEntidad(peticion.getIdPlanDesarrollo(),
				peticion.getCodigoEntidad(), "Iniciativa Ciudadana");
		peticion.setCodigoIn(consecutivo.getSecuencia());
		consecutivo.setSecuencia(peticion.getCodigoIn() + 1);
		sessionFacadeAFS.guardarConsecutivo(consecutivo);
		SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
		peticion.setFechaCodigo(date.format(new Date()));
		BpIniciativaCiudadanaDTO respuesta = sessionFacadeBP.guardarIniciativaCiudadana(peticion);
		String[] split = peticion.getGruposEtarios().split(";");
		List<String> gruposEtarios = Arrays.asList(split);
		gruposEtarios.stream().forEach(g -> {
			if (!g.equals("")) {
				BpIniciativaEtarioDTO etarios = new BpIniciativaEtarioDTO();
				etarios.setIdLsEtario(Long.parseLong(g));
				etarios.setIdIniciativa(respuesta.getIdIniciativa());
				try {
					sessionFacadeBP.guardarGruposEtarios(etarios);
				} catch (SpddExceptions e) {
					respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
				}
			}
		});
		String[] splitCondiciones = peticion.getCodicionesPoblacionales().split(";");
		List<String> condicionesPoblacionales = Arrays.asList(splitCondiciones);
		condicionesPoblacionales.stream().forEach(c -> {
			if (!c.equals("")) {
				BpIniciativaCondicionDTO condicion = new BpIniciativaCondicionDTO();
				condicion.setIdLsCondicion(Long.parseLong(c));
				condicion.setIdIniciativa(respuesta.getIdIniciativa());
				try {
					sessionFacadeBP.guardarIniciativaCondicion(condicion);
				} catch (SpddExceptions e) {
					respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
				}
			}
		});
		TypeReference<List<TerritorializacionDTO>> type = new TypeReference<List<TerritorializacionDTO>>() {
		};
		List<TerritorializacionDTO> lista = mapper.convertValue(
				sessionFacadeAFS.buscarPorLocalidadTerritorializacion().getLstObjectsDTO(),
				type);
		
		if (peticion.getIdLsUpr() == -2) {
			List<TerritorializacionDTO> upr = lista.stream().filter(x -> x.getIdLsUpr() != -1)
					.collect(Collectors.toList());
			upr.stream().forEach(up -> {
				if (up.getIdTerritorializacion() != null) {
					BpIniciativaUbicacionDTO ubicacion = new BpIniciativaUbicacionDTO();
					ubicacion.setIdIniciativa(respuesta.getIdIniciativa());
					ubicacion.setIdTerritorializacion(up.getIdTerritorializacion());
					try {
						sessionFacadeBP.guardarUbicacionIniciativaCiudadana(ubicacion);
					} catch (SpddExceptions e) {
						respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
					}

				}
			});
		} else {
			BpIniciativaUbicacionDTO ubicacion = new BpIniciativaUbicacionDTO();
			TerritorializacionDTO territorializacionDTO = new TerritorializacionDTO(lista.get(0).getIdLsLocalidad(),
					peticion.getIdLsBarrio(), peticion.getIdLsUpz(), peticion.getIdLsUpr(), peticion.getIdLsVereda());
			TerritorializacionDTO aux = sessionFacadeAFS
					.consultarTerritorializacionPorLsBarrioYLsUpzYLsLocalidad(territorializacionDTO);
			ubicacion.setIdTerritorializacion(aux.getIdTerritorializacion());
			ubicacion.setIdIniciativa(respuesta.getIdIniciativa());
			sessionFacadeBP.guardarUbicacionIniciativaCiudadana(ubicacion);
		}

		if (peticion.getIdLsUpz() == -2) {
			List<TerritorializacionDTO> upz = lista.stream().filter(x -> x.getIdLsUpz() != -1)
					.collect(Collectors.toList());
			upz.stream().forEach(up -> {
				if (up.getIdTerritorializacion() != null) {
					BpIniciativaUbicacionDTO ubicacion = new BpIniciativaUbicacionDTO();
					ubicacion.setIdIniciativa(respuesta.getIdIniciativa());
					ubicacion.setIdTerritorializacion(up.getIdTerritorializacion());
					try {
						sessionFacadeBP.guardarUbicacionIniciativaCiudadana(ubicacion);
					} catch (SpddExceptions e) {
						respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
					}
				}
			});
		} else {
			BpIniciativaUbicacionDTO ubicacion = new BpIniciativaUbicacionDTO();
			TerritorializacionDTO territorializacionDTO = new TerritorializacionDTO(lista.get(0).getIdLsLocalidad(),
					peticion.getIdLsBarrio(), peticion.getIdLsUpz(), peticion.getIdLsUpr(), peticion.getIdLsVereda());
			System.out.println(territorializacionDTO.toString());
			TerritorializacionDTO aux = sessionFacadeAFS
					.consultarTerritorializacionPorLsVeredaYLsUpr(territorializacionDTO);
			ubicacion.setIdTerritorializacion(aux.getIdTerritorializacion());
			ubicacion.setIdIniciativa(respuesta.getIdIniciativa());
			System.out.println(aux.toString());
			sessionFacadeBP.guardarUbicacionIniciativaCiudadana(ubicacion);

		}
		respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
		respuesta.setMsgRespuesta(
				NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_CREAR_BP_ASIGNAR_INICIATIVA_CIUDADANA_CORRECTO,
						PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje())
						+ " " + respuesta.getCodigoIn() + " "
						+ NHSPDDMensajes.obtenerMensaje(
								NHSPDDConstantes.MENSAJE_REGISTRAR_BP_INCIATIVA_CIUDADANA_CORRECTO,
								PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
		return respuesta;
	}
}
