package co.gov.sdp.spdd.core.bp.service.bpproyinv;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import co.gov.sdp.nhspdd.common.dto.ArgumentoListaSimpleDTO;
import co.gov.sdp.nhspdd.common.dto.CampoValidacionDTO;
import co.gov.sdp.nhspdd.common.dto.ConsecutivoDTO;
import co.gov.sdp.nhspdd.common.dto.EntidadDTO;
import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.dto.PddDTO;
import co.gov.sdp.nhspdd.common.dto.RespuestaDTO;
import co.gov.sdp.nhspdd.common.dto.TerritorializacionDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpAporteCiudadanoDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvActividadDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvAnualizaDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvAporteDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvEspecifDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvEtnicoDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvFinanciaDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvIniciativaDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvLineaDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvLocalizaDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvLocalizaYTerritorizacionDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvMetaPlanDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvPmrDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvPoblacionDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvPoliticaDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvProductoDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvTipoDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyectoInversionDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddMetaProductoDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PdlDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.nhspdd.common.util.NHSPDDMensajes;
import co.gov.sdp.nhspdd.common.util.PaqueteMensajeEnum;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.spdd.core.bp.iservice.bpproyinv.IBPProyInvRegistrarService;
import co.gov.sdp.spdd.data.sesionfacade.interfaces.ISessionFacadeAFS;
import co.gov.sdp.spdd.data.sesionfacade.interfaces.ISessionFacadeBP;
import co.gov.sdp.spdd.data.sesionfacade.interfaces.ISessionFacadeIP;
import co.gov.sdp.spdd.util.AuditoriaUtil;

@Service
public class BPProyInvRegistrarService implements IBPProyInvRegistrarService {

	/**
	 * Inyeccion del SessionFacade del modulo BP
	 */
	@Autowired
	ISessionFacadeBP sessionFacadeBP;

	@Autowired
	ISessionFacadeIP sessionFacadeIP;

	@Autowired
	ISessionFacadeAFS sessionFacadeAFS;

	@Autowired
	ObjectMapper objectMapper;

	@Autowired
	AuditoriaUtil auditoria;

	/**
	 * Logger
	 */
	@Autowired
	SPDDLogger spddLogger;

	@Override
	public BpProyectoInversionDTO registrarProyectoInversionIndentificacionProyecto(BpProyectoInversionDTO peticion)
			throws SpddExceptions, JsonProcessingException {

		BpProyectoInversionDTO respuesta = new BpProyectoInversionDTO();
		PddDTO pddVigenteDTO = sessionFacadeIP.consultarPddVigente();
		

		if (pddVigenteDTO.getIdPlanDesarrollo() != null) {
			
			ArgumentoListaSimpleDTO argumentoEntidadClasificacionAlcaldia = sessionFacadeAFS.consultarArgumentoListaSimplePorResultadoYNombreListaSimple(NHSPDDConstantes.ENTIDAD_ALCALDIA,NHSPDDConstantes.LS_CLASIFICACION_ENTIDADES);
			EntidadDTO entidadDTO = sessionFacadeAFS.consultarEntidad(peticion.getCodigoEntidad());
			
			if(entidadDTO.getIdLsClasificacion().equals(argumentoEntidadClasificacionAlcaldia.getIdArgumento()))
			{
				PdlDTO pdlVigenteDTO = sessionFacadeIP.consultarPdlVigente(peticion.getCodigoEntidad());
				
				if(pdlVigenteDTO.getIdPlanLocal() != null)
				{
					peticion.setIdPlanDesarrolloLocal(pdlVigenteDTO.getIdPlanLocal());
				}
				else
				{
					respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
					respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_PDL_VIGENTE_INEXISTENTE,
							PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
					
					return respuesta;
				}
			}
			
			// Se actualiza el consectivo
			ConsecutivoDTO consecutivo = sessionFacadeAFS.obtenerConsecutivosPorPlanYEntidad(
					pddVigenteDTO.getIdPlanDesarrollo(), peticion.getCodigoEntidad(),
					NHSPDDConstantes.CORE_CONSECUTIVO_TABLA_BP_PROYECTO_INVERSION);
			peticion.setCodigoProyecto(consecutivo.getSecuencia());
			consecutivo.setSecuencia(peticion.getCodigoProyecto() + 1);
			sessionFacadeAFS.guardarConsecutivo(consecutivo);

			ArgumentoListaSimpleDTO argumetoEstadoProyectoSinInscripcion = sessionFacadeAFS
					.consultarArgumentoListaSimplePorResultadoYNombreListaSimple(NHSPDDConstantes.BP_PROYECTO_INV_ESTADO_SIN_INSCRIPCION,
							NHSPDDConstantes.LS_ESTADOS_PROYECTO_INVERSION);
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			peticion.setIdLsEstado(argumetoEstadoProyectoSinInscripcion.getIdArgumento());
			peticion.setVersion(1L);
			peticion.setFechaVersion(formatter.format(new Date()));

			peticion.setIdPlanDesarrolloDistrital(pddVigenteDTO.getIdPlanDesarrollo());

			respuesta = sessionFacadeBP.guardarProyectoInversionIndentificacionProyecto(peticion);

			if (respuesta != null && respuesta.getIdProyInversion() != null) {

				// auditoria.crearAuditoria(peticion.toString(), peticion.toString(),
				// "CREAR_IP", "CREAR");

				sessionFacadeBP.eliminarBpProyInvTiposDeIdProyectoInversion(respuesta.getIdProyInversion());

				String[] listaIdsTipos = peticion.getIdsTipoProy().split(";");
				for (String idTipoAux : listaIdsTipos) {
					BpProyInvTipoDTO bpProyInvTipoDTO = sessionFacadeBP.guardarProyInvTipo(new BpProyInvTipoDTO(null,
							Long.parseLong(idTipoAux.trim()), respuesta.getIdProyInversion()));

					if (bpProyInvTipoDTO != null && bpProyInvTipoDTO.getIdTipo() != null) {
						// auditoria.crearAuditoria(peticion.toString(), peticion.toString(),
						// "CREAR_IP", "CREAR");
					}

				}

				respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
				respuesta.setMsgRespuesta(
						NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_CREAR_BP_PROYECTO_INVERSION_CORRECTO,
								PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
			} else {
				respuesta = new BpProyectoInversionDTO();
				respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
				respuesta.setMsgRespuesta(
						NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_CREAR_BP_PROYECTO_INVERSION_INCORRECTO,
								PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
			}
		} else {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_PDD_VIGENTE_INEXISTENTE,
					PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));

		}

		return respuesta;
	}

	@Override
	public BpProyInvAporteDTO registrarBpProyInvAporte(BpProyInvAporteDTO peticion)
			throws SpddExceptions, JsonProcessingException {
		BpProyInvAporteDTO respuesta = new BpProyInvAporteDTO();

		BpProyInvAporteDTO respuestaAux;

		if (peticion.getIdsAportes() != null && !"".equals(peticion.getIdsAportes())) {
			String[] listaIdsAportes = peticion.getIdsAportes().split(";");

			// eliminar todas los aportes ciudados cargados de un archivo por
			// idPoryInversion
			sessionFacadeBP.eliminarTodosBpProyInvAporteCargadosArchivoPorIdProyecto(peticion.getIdProyInversion());

			List<CampoValidacionDTO> listaCampoValidacion = new ArrayList<>();

			boolean validar = true;

			boolean validarAux = false;

			for (String idAporteAux : listaIdsAportes) {
				respuestaAux = sessionFacadeBP.guardarBpProyInvAporte(new BpProyInvAporteDTO(null,
						Long.parseLong(idAporteAux.trim()), peticion.getIdProyInversion()));

				if (respuestaAux.getIdProyAporte() != null && !validarAux) {
					validarAux = true;
				}

				if (respuestaAux.getIdProyAporte() == null) {
					listaCampoValidacion.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
							String.format(NHSPDDMensajes.obtenerMensaje(
									NHSPDDConstantes.MENSAJE_ERROR_BP_APORTE_CIUDADANO_RELACIONADO_BP_PROYECTO_INVERSION,
									PaqueteMensajeEnum.ERRORES, peticion.getLenguaje()), idAporteAux.trim())));
				} else {
					// Auditoria de agregar una relacion de un aporte ciudada con el proyecto de
					// inversion
					// auditoria.crearAuditoria(peticion.toString(), peticion.toString(),
					// "CREAR_IP", "CREAR");
				}

				validar = validar && (respuestaAux.getIdProyAporte() != null);
			}

			respuesta.setLstCampoValidacion(listaCampoValidacion);

			if (validar) {
				respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
				respuesta.setMsgRespuesta(
						NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_RELACIONAR_BP_APORTE_CIUDADANO_CORRECTO,
								PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
			} else if (validarAux && !validar) {
				respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
				respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(
						NHSPDDConstantes.MENSAJE_RELACIONAR_BP_APORTE_CIUDADANO_CORRECTO_INCORRECTO,
						PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
			} else {
				respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
				respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(
						NHSPDDConstantes.MENSAJE_RELACIONAR_BP_APORTE_CIUDADANO_INCORRECTO, PaqueteMensajeEnum.MENSAJES,
						peticion.getLenguaje()));

			}
		} else {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(
					NHSPDDConstantes.MENSAJE_VALIDACION_BP_PROY_INV_APORTE_IDS_APORTES_VACIO,
					PaqueteMensajeEnum.ERRORES, peticion.getLenguaje()));
		}

		return respuesta;
	}

	@Override
	public BpAporteCiudadanoDTO registrarBPAporteCiudadano(BpAporteCiudadanoDTO peticion)
			throws SpddExceptions, JsonProcessingException {
		if (peticion.getIdNivelAtributoPddOpcion3() != null) {
			peticion.setIdNivelAtributoPdd(peticion.getIdNivelAtributoPddOpcion3());
		} else {
			peticion.setIdNivelAtributoPdd(peticion.getIdNivelAtributoPddOpcion2());
		}

		BpAporteCiudadanoDTO respuesta = sessionFacadeBP.guardarBPAporteCiudadano(peticion);
		if (respuesta != null && respuesta.getIdAporte() != null) {
			if (peticion.getIdProyInversion() != null) {
				sessionFacadeBP.guardarBpProyInvAporte(
						new BpProyInvAporteDTO(null, respuesta.getIdAporte(), peticion.getIdProyInversion()));
			}

			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
			respuesta.setMsgRespuesta(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_CREAR_BP_APORTE_CIUDADANO_CORRECTO,
							PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
			// auditoria.crearAuditoria(peticion.toString(), peticion.toString(),
			// "CREAR_IP", "CREAR");

		} else {
			respuesta = new BpAporteCiudadanoDTO();
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ASOCIACION_EXISTENTE,
					PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
		}
		return respuesta;
	}

	/**
	 * Metodo privado que permite setiar los valores necesarios para mostrar el
	 * mensaje correspondiente en cada uno de los casos para la respuesta de las
	 * peticiones
	 * 
	 * @param respuesta             objeto al cual se le va a setiar el mensaje
	 * @param codigoRespuesta       objeto de tipo Integer que representa el codigo
	 *                              de la respuesta
	 * @param msgRespuesta          String que representa el mensaje de respuesta
	 *                              que se va a retornar
	 * @param paqueteMensaje        objeto de tipo PaqueteMensajeEnum que representa
	 *                              el paquete donde se encuentra el mensaje
	 * @param lenguaje
	 * @param getMsgCampoValidacion lista de mensaje de las validaciones
	 */
	private void mensajeRespuesta(RespuestaDTO respuesta, Integer codigoRespuesta, String msgRespuesta,
			PaqueteMensajeEnum paqueteMensaje, String lenguaje, List<CampoValidacionDTO> getMsgCampoValidacion) {
		respuesta.setCodigoRespuesta(codigoRespuesta);
		respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(msgRespuesta, paqueteMensaje, lenguaje));
		respuesta.setLstCampoValidacion(getMsgCampoValidacion);
	}

	@Override
	public BpProyInvIniciativaDTO registrarBpProyInvIniciativa(BpProyInvIniciativaDTO peticion)
			throws SpddExceptions, JsonProcessingException {
		BpProyInvIniciativaDTO respuesta = new BpProyInvIniciativaDTO();

		BpProyInvIniciativaDTO respuestaAux;

		if (peticion.getIdsIniciativasConcatenadas() != null && !"".equals(peticion.getIdsIniciativasConcatenadas())) {
			String[] listaIdsInciativas = peticion.getIdsIniciativasConcatenadas().split(";");

			// eliminar todas los aportes ciudados cargados de un archivo por
			// idPoryInversion
			sessionFacadeBP.eliminarTodosBpProyInvIniciativaPorIdProyInversion(peticion.getIdProyInversion());

			List<CampoValidacionDTO> listaCampoValidacion = new ArrayList<>();

			boolean validar = true;

			boolean validarAux = false;
			
			SimpleDateFormat formateador = new SimpleDateFormat("dd-MM-yyyy");

			for (String idIniciativaAux : listaIdsInciativas) {
				respuestaAux = sessionFacadeBP.guardarBpProyInvIniciativa(new BpProyInvIniciativaDTO(null,
						Long.parseLong(idIniciativaAux.trim()), peticion.getIdProyInversion(), formateador.format(new Date())));

				if (respuestaAux.getIdIniciativaInv() != null && !validarAux) {
					validarAux = true;
				}

				if (respuestaAux.getIdIniciativaInv() == null) {
					listaCampoValidacion.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
							String.format(NHSPDDMensajes.obtenerMensaje(
									NHSPDDConstantes.MENSAJE_ERROR_BP_INICIATIVA_CIUDADANA_RELACIONADA_BP_PROYECTO_INVERSION,
									PaqueteMensajeEnum.ERRORES, peticion.getLenguaje()), idIniciativaAux.trim())));
				} else {
					// Auditoria de agregar una relacion de un aporte ciudada con el proyecto de
					// inversion
					// auditoria.crearAuditoria(peticion.toString(), peticion.toString(),
					// "CREAR_IP", "CREAR");
				}

				validar = validar && (respuestaAux.getIdIniciativaInv() != null);
			}

			respuesta.setLstCampoValidacion(listaCampoValidacion);

			if (validar) {
				respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
				respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(
						NHSPDDConstantes.MENSAJE_RELACIONAR_BP_INICIATIVA_CIUDADANA_CORRECTA,
						PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
			} else if (validarAux && !validar) {
				respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
				respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(
						NHSPDDConstantes.MENSAJE_RELACIONAR_BP_INICIATIVA_CIUDADANA_CORRECTA_INCORRECTA,
						PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
			} else {
				respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
				respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(
						NHSPDDConstantes.MENSAJE_RELACIONAR_BP_INICIATIVA_CIUDADANA_INCORRECTA,
						PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));

			}
		} else {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(
					NHSPDDConstantes.MENSAJE_VALIDACION_BP_PROY_INV_INICIATIVA_IDS_INICIATIVAS_CONCATENADAS_VACIO,
					PaqueteMensajeEnum.ERRORES, peticion.getLenguaje()));
		}

		return respuesta;
	}

	@Override
	public BpProyectoInversionDTO registrarBPProyectoInvLocaliza(
			BpProyInvLocalizaYTerritorizacionDTO bpProyInvLocalizaYTerritorizacionDTO) throws SpddExceptions {

		TerritorializacionDTO auxTerritorializacionDTO = new TerritorializacionDTO();
		TerritorializacionDTO territorializacionDTO = new TerritorializacionDTO();
		territorializacionDTO.setIdTerritorializacion(bpProyInvLocalizaYTerritorizacionDTO.getIdTerritorializacion());
		territorializacionDTO.setIdLsBarrio(bpProyInvLocalizaYTerritorizacionDTO.getIdLsBarrio());
		territorializacionDTO.setIdLsLocalidad(bpProyInvLocalizaYTerritorizacionDTO.getIdLsLocalidad());
		territorializacionDTO.setIdLsUpr(bpProyInvLocalizaYTerritorizacionDTO.getIdLsUpr());
		territorializacionDTO.setIdLsUpz(bpProyInvLocalizaYTerritorizacionDTO.getIdLsUpz());
		territorializacionDTO.setIdLsVereda(bpProyInvLocalizaYTerritorizacionDTO.getIdLsVereda());

		BpProyectoInversionDTO respuesta = new BpProyectoInversionDTO();

		BpProyectoInversionDTO auxBpProyectoInversionDTO = sessionFacadeBP
				.consultarProyInvPorId(bpProyInvLocalizaYTerritorizacionDTO.getIdProyInversion());

		if (auxBpProyectoInversionDTO != null && auxBpProyectoInversionDTO.getIdProyInversion() > 0) {

			auxBpProyectoInversionDTO.setAntecedente(bpProyInvLocalizaYTerritorizacionDTO.getAntecedente());
			auxBpProyectoInversionDTO.setSituacion(bpProyInvLocalizaYTerritorizacionDTO.getSituacion());
			auxBpProyectoInversionDTO
					.setDescripcionUniverso(bpProyInvLocalizaYTerritorizacionDTO.getDescripcionUniverso());
			auxBpProyectoInversionDTO.setCuantificacion(bpProyInvLocalizaYTerritorizacionDTO.getCuantificacion());
			auxBpProyectoInversionDTO.setIdLsUnidad(bpProyInvLocalizaYTerritorizacionDTO.getIdLsUnidad());

			respuesta = sessionFacadeBP.modificarProyectoInversionProyecto(auxBpProyectoInversionDTO);

			if (respuesta.getIdProyInversion() != null) {

				if (bpProyInvLocalizaYTerritorizacionDTO.getIdLsUpr() != null) {

					auxTerritorializacionDTO = sessionFacadeAFS
							.consultarTerritorializacionPorLsVeredaYLsUpr(territorializacionDTO);

				} else {

					auxTerritorializacionDTO = sessionFacadeAFS
							.consultarTerritorializacionPorLsBarrioYLsUpzYLsLocalidad(territorializacionDTO);
				}

				if (auxTerritorializacionDTO.getIdTerritorializacion() != null) {

					// eliminar todas las territorializaciones por idPoryInversion
					sessionFacadeBP.eliminarTodosBpProyInvLocalizaPorIdProyecto(
							bpProyInvLocalizaYTerritorizacionDTO.getIdProyInversion());

					sessionFacadeBP.guardarBPProyectoInvLocaliza(new BpProyInvLocalizaDTO(null,
							respuesta.getIdProyInversion(), auxTerritorializacionDTO.getIdTerritorializacion()));

					// Auditoria de agregar una relacion de un proy inv localiza con el proyecto de
					// inversion
					// auditoria.crearAuditoria(peticion.toString(), peticion.toString(),
					// "CREAR_IP", "CREAR");

					mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_EXITOSA,
							NHSPDDConstantes.MENSAJE_CREAR_BP_PROYECTO_INVERSION_LOCALIZA_CORRECTO,
							PaqueteMensajeEnum.MENSAJES, respuesta.getLenguaje(), null);
				} else {
					respuesta = new BpProyectoInversionDTO();
					mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
							NHSPDDConstantes.MENSAJE_ENTIDAD_NO_ENCONTRADO, PaqueteMensajeEnum.MENSAJES,
							respuesta.getLenguaje(), null);
				}
			} else {
				respuesta = new BpProyectoInversionDTO();
				mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
						NHSPDDConstantes.MENSAJE_CREAR_BP_PROYECTO_INVERSION_LOCALIZA_INCORRECTO,
						PaqueteMensajeEnum.MENSAJES, respuesta.getLenguaje(), null);
			}
		} else {
			mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDConstantes.MENSAJE_ENTIDAD_NO_ENCONTRADO, PaqueteMensajeEnum.MENSAJES,
					respuesta.getLenguaje(), null);
		}
		return respuesta;
	}

	@Override
	public BpProyInvEspecifDTO registrarBpProyInvEspecif(BpProyInvEspecifDTO peticion) throws SpddExceptions {
		BpProyInvEspecifDTO respuesta = new BpProyInvEspecifDTO();
		BpProyectoInversionDTO auxBpProyectoInversionDTO = sessionFacadeBP
				.consultarProyInvPorId(peticion.getIdProyInversion());
		if (auxBpProyectoInversionDTO != null && auxBpProyectoInversionDTO.getIdProyInversion() > 0) {
			respuesta = sessionFacadeBP.guardarBpProyInvEspecif(peticion);
			if (respuesta.getIdProyObjEspecif() != null) {
				mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_EXITOSA,
						NHSPDDConstantes.MENSAJE_CREAR_BP_PROYECTO_INVERSION_ESPECIF_CORRECTO,
						PaqueteMensajeEnum.MENSAJES, respuesta.getLenguaje(), null);
				// Auditoria de agregar una relacion de un proy inv localiza con el proyecto de
				// inversion
				// auditoria.crearAuditoria(peticion.toString(), peticion.toString(),
				// "CREAR_IP", "CREAR");
			} else {
				respuesta = new BpProyInvEspecifDTO();
				mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
						NHSPDDConstantes.MENSAJE_CREAR_BP_PROYECTO_INVERSION_ESPECIF_INCORRECTO,
						PaqueteMensajeEnum.MENSAJES, respuesta.getLenguaje(), null);
			}
		} else {
			mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDConstantes.MENSAJE_ENTIDAD_NO_ENCONTRADO, PaqueteMensajeEnum.MENSAJES,
					respuesta.getLenguaje(), null);
		}
		return respuesta;
	}

	@Override
	public BpProyInvMetaPlanDTO registrarBpProyInvMetaPlan(BpProyInvMetaPlanDTO peticion) throws SpddExceptions {
		BpProyInvMetaPlanDTO respuesta = new BpProyInvMetaPlanDTO();
		BpProyInvEspecifDTO proyInvEspecifDTO = sessionFacadeBP
				.consultarBpProyInvEspecifPorId(peticion.getIdProyObjEspecif());
		PddMetaProductoDTO metaProductoDTO = new PddMetaProductoDTO(); // Consultar meta producto por id
		if (proyInvEspecifDTO.getIdProyObjEspecif() != null && metaProductoDTO.getIdMetaProducto() != null) {
			BpProyInvMetaPlanDTO auxBpProyInvMetaPlan = sessionFacadeBP
					.consultarBpProyInvMetaPlanPorIdProyInvEspecif(peticion.getIdProyObjEspecif());
			if (auxBpProyInvMetaPlan.getIdProyMetaPlan() == null) {
				respuesta = sessionFacadeBP.guardarBpProyInvMetaPlan(peticion);
				if (respuesta.getIdProyObjEspecif() != null) {
					mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_EXITOSA,
							NHSPDDConstantes.MENSAJE_CREAR_BP_PROYECTO_INVERSION_META_PLAN_CORRECTO,
							PaqueteMensajeEnum.MENSAJES, respuesta.getLenguaje(), null);
					// Auditoria de agregar una relacion de un proy inv localiza con el proyecto de
					// inversion
					// auditoria.crearAuditoria(peticion.toString(), peticion.toString(),"CREAR_IP",
					// "CREAR");
				} else {
					mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
							NHSPDDConstantes.MENSAJE_CREAR_BP_PROYECTO_INVERSION_META_PLAN_INCORRECTO,
							PaqueteMensajeEnum.MENSAJES, respuesta.getLenguaje(), null);
				}
			} else {
				mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
						NHSPDDConstantes.MENSAJE_CREAR_BP_PROYECTO_INVERSION_META_PLAN_ESPECIF,
						PaqueteMensajeEnum.MENSAJES, respuesta.getLenguaje(), null);
			}
		} else {
			mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDConstantes.MENSAJE_ENTIDAD_NO_ENCONTRADO, PaqueteMensajeEnum.MENSAJES,
					respuesta.getLenguaje(), null);
		}
		return respuesta;
	}

	@Override
	public BpProyInvProductoDTO registrarBpProyInvProducto(BpProyInvProductoDTO peticion) throws SpddExceptions {
		BpProyInvProductoDTO respuesta = new BpProyInvProductoDTO();
		BpProyInvProductoDTO auxProyInvProductoDTO = sessionFacadeBP
				.consultarBpProyInvProductoPorIdProyInvMetaPlan(peticion.getIdProyMetaPlan());
		if (auxProyInvProductoDTO.getIdProducto() == null) {
			respuesta = sessionFacadeBP.guardarBpProyInvProducto(peticion);
			if (respuesta.getIdProducto() != null) {
				mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_EXITOSA,
						NHSPDDConstantes.MENSAJE_CREAR_BP_PROYECTO_INVERSION_PRODUCTO_CORRECTO,
						PaqueteMensajeEnum.MENSAJES, respuesta.getLenguaje(), null);
				// Auditoria de agregar una relacion de un proy inv localiza con el proyecto de
				// inversion
				// auditoria.crearAuditoria(peticion.toString(), peticion.toString(),"CREAR_IP",
				// "CREAR");
			} else {
				mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
						NHSPDDConstantes.MENSAJE_CREAR_BP_PROYECTO_INVERSION_PRODUCTO_INCORRECTO,
						PaqueteMensajeEnum.MENSAJES, respuesta.getLenguaje(), null);
			}
		} else {
			mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDConstantes.MENSAJE_CREAR_BP_PROYECTO_INVERSION_PRODUCTO_OBJETIVO, PaqueteMensajeEnum.MENSAJES,
					respuesta.getLenguaje(), null);
		}
		return respuesta;
	}

	@Override
	public BpProyInvActividadDTO registrarBpProyInvActividad(BpProyInvActividadDTO peticion) throws SpddExceptions {
		BpProyInvActividadDTO respuesta = new BpProyInvActividadDTO();
		BpProyInvActividadDTO auxBpProyInvActividadDTO = sessionFacadeBP
				.consultarBpProyInvActividadPorId(peticion.getIdProducto());
		if (auxBpProyInvActividadDTO == null) {
			respuesta = sessionFacadeBP.guardarBpProyInvActividad(peticion);
			if (respuesta.getIdProducto() != null) {
				mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_EXITOSA,
						NHSPDDConstantes.MENSAJE_CREAR_BP_PROYECTO_INVERSION_ACTIVIDAD_CORRECTO,
						PaqueteMensajeEnum.MENSAJES, respuesta.getLenguaje(), null);
				// Auditoria de agregar una relacion de un proy inv localiza con el proyecto de
				// inversion
				// auditoria.crearAuditoria(peticion.toString(), peticion.toString(),"CREAR_IP",
				// "CREAR");
			} else {
				mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
						NHSPDDConstantes.MENSAJE_CREAR_BP_PROYECTO_INVERSION_ACTIVIDAD_INCORRECTO,
						PaqueteMensajeEnum.MENSAJES, respuesta.getLenguaje(), null);
			}
		} else {
			mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDConstantes.MENSAJE_CREAR_BP_PROYECTO_INVERSION_ACTIVIDAD_PRODUCTO,
					PaqueteMensajeEnum.MENSAJES, respuesta.getLenguaje(), null);
		}
		return peticion;
	}

	@Override
	public BpProyInvFinanciaDTO registrarBpProyInvFinancia(BpProyInvFinanciaDTO peticion) throws SpddExceptions {

		BpProyInvFinanciaDTO respuesta = new BpProyInvFinanciaDTO();
		BpProyectoInversionDTO auxBpProyectoInversionDTO = sessionFacadeBP
				.consultarProyInvPorId(peticion.getIdProyInversion());

		if (auxBpProyectoInversionDTO != null) {
			if (auxBpProyectoInversionDTO.getIdProyInversion() != null) {
				
				String[] idsLsFuentes = peticion.getIdsLsfuentes().split(";");
				List<String> listaidsLsFuentes = Arrays.asList(idsLsFuentes);
				Boolean bandera = true;

				for (String idLsFuente : listaidsLsFuentes) {

					BpProyInvFinanciaDTO auxBpProyInvFinanciaDTO = sessionFacadeBP
							.consultarProyInvFinanciaPorIdLSFuYIdProy(Long.parseLong(idLsFuente),
									peticion.getIdProyInversion());
					if (auxBpProyInvFinanciaDTO.getIdFuente() != null) {

						bandera = false;
						break;

					}

				}

				if (bandera) {
					listaidsLsFuentes.stream().forEach(idFuente -> {
						peticion.setIdLsFuente(Long.parseLong(idFuente));
						try {
							BpProyInvFinanciaDTO respuesta2 = sessionFacadeBP.registrarBpProyInvFinancia(peticion);
							if (respuesta2.getIdFuente() != null) {

								BpProyInvAnualizaDTO auxBpProyInvAnualizaDTO = new BpProyInvAnualizaDTO();

								auxBpProyInvAnualizaDTO.setIdFuente(respuesta2.getIdFuente());

								// registrar los Proy_Inv_Anualiza

								if (peticion.getMontoAnio1() != null && peticion.getVigencia1() != null) {

									auxBpProyInvAnualizaDTO.setMonto(peticion.getMontoAnio1());
									auxBpProyInvAnualizaDTO.setVigencia(peticion.getVigencia1());
									sessionFacadeBP.registrarBpProyInvAnualiza(auxBpProyInvAnualizaDTO);

								}
								if (peticion.getMontoAnio2() != null && peticion.getVigencia2() != null) {

									auxBpProyInvAnualizaDTO.setMonto(peticion.getMontoAnio2());
									auxBpProyInvAnualizaDTO.setVigencia(peticion.getVigencia2());
									sessionFacadeBP.registrarBpProyInvAnualiza(auxBpProyInvAnualizaDTO);

								}
								if (peticion.getMontoAnio3() != null && peticion.getVigencia3() != null) {

									auxBpProyInvAnualizaDTO.setMonto(peticion.getMontoAnio3());
									auxBpProyInvAnualizaDTO.setVigencia(peticion.getVigencia3());
									sessionFacadeBP.registrarBpProyInvAnualiza(auxBpProyInvAnualizaDTO);

								}
								if (peticion.getMontoAnio4() != null && peticion.getVigencia4() != null) {

									auxBpProyInvAnualizaDTO.setMonto(peticion.getMontoAnio4());
									auxBpProyInvAnualizaDTO.setVigencia(peticion.getVigencia4());
									sessionFacadeBP.registrarBpProyInvAnualiza(auxBpProyInvAnualizaDTO);

								}
								if (peticion.getMontoAnio5() != null && peticion.getVigencia5() != null) {

									auxBpProyInvAnualizaDTO.setMonto(peticion.getMontoAnio5());
									auxBpProyInvAnualizaDTO.setVigencia(peticion.getVigencia5());
									sessionFacadeBP.registrarBpProyInvAnualiza(auxBpProyInvAnualizaDTO);

								}

								mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_EXITOSA,
										NHSPDDConstantes.MENSAJE_CREAR_BP_PROYECTO_INVERSION_FINANCIA_CORRECTO,
										PaqueteMensajeEnum.MENSAJES, respuesta.getLenguaje(), null);

							} else {
								// si no se registra el el pro de financiacion por claves
								mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
										NHSPDDConstantes.MENSAJE_CREAR_BP_PROYECTO_INVERSION_FINANCIA_INCORRECTO,
										PaqueteMensajeEnum.MENSAJES, respuesta.getLenguaje(), null);
							}

						} catch (SpddExceptions e) {
							// si no se registra el el pro de financiacion por claves
							mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
									NHSPDDConstantes.MENSAJE_CREAR_BP_PROYECTO_INVERSION_FINANCIA_INCORRECTO,
									PaqueteMensajeEnum.MENSAJES, respuesta.getLenguaje(), null);
						}

					});

				} else {

					// si el proyecto no existe o el argumento no existe
					mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
							NHSPDDConstantes.MENSAJE_CREAR_BP_PROYECTO_INVERSION_FINANCIA_INCORRECTO,
							PaqueteMensajeEnum.MENSAJES, respuesta.getLenguaje(), null);

				}

			} else {
				// si el proyecto no existe o el argumento no existe
				mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
						NHSPDDConstantes.MENSAJE_CREAR_BP_PROYECTO_INVERSION_FINANCIA_PROYECTO,
						PaqueteMensajeEnum.MENSAJES, respuesta.getLenguaje(), null);
			}
		} else {

			// si no se envian los idsLsFuentes
			mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDConstantes.MENSAJE_CREAR_BP_PROYECTO_INVERSION_FINANCIA_IDS_FUENTES,
					PaqueteMensajeEnum.MENSAJES, respuesta.getLenguaje(), null);
		}
		return respuesta;
	}

	public BpProyInvPoblacionDTO registrarBpProyInvPoblacionAsociadoaProyInv(BpProyInvPoblacionDTO peticion)
			throws SpddExceptions, JsonProcessingException {
<<<<<<< HEAD
=======

>>>>>>> developer
		BpProyInvPoblacionDTO respuesta = new BpProyInvPoblacionDTO();

		BpProyInvPoblacionDTO respuestaAuxUnicidad = new BpProyInvPoblacionDTO();
<<<<<<< HEAD
		
		//Validacion existencia proyecto de inversion
		BpProyectoInversionDTO dtoAuxExistenciaProyInv = new BpProyectoInversionDTO();
		dtoAuxExistenciaProyInv.setIdProyInversion(peticion.getIdProyInversion());
		BpProyectoInversionDTO respuestaExistenciaProyInv = sessionFacadeBP.consultarBpProyectoInversion(dtoAuxExistenciaProyInv);
		
		if(respuestaExistenciaProyInv.getIdProyInversion() == null) {
			mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDConstantes.MENSAJE_PROY_INV_NO_EXISTE, PaqueteMensajeEnum.MENSAJES, respuesta.getLenguaje(),null);
			return respuesta;
		}
		
		//Validacion de unicidad antes de agregar		
		respuestaAuxUnicidad = new BpProyInvPoblacionDTO();
		respuestaAuxUnicidad.setIdLsEtario(peticion.getIdLsEtario());
		respuestaAuxUnicidad.setIdProyInversion(peticion.getIdProyInversion());
				
		respuestaAuxUnicidad = sessionFacadeBP.consultarBpProyInvPoblacionUnicidad(respuestaAuxUnicidad);
				
		if(!respuestaAuxUnicidad.getValidacionUnicidad()) {
			mensajeRespuesta(respuestaAuxUnicidad, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
							NHSPDDConstantes.MENSAJE_ASOCIACION_EXISTENTE, PaqueteMensajeEnum.MENSAJES, respuestaAuxUnicidad.getLenguaje(),null);
			return respuestaAuxUnicidad;
		}

				
		respuesta = sessionFacadeBP.guardarBpProyInvPoblacion(peticion);
				
		if(respuesta.getIdPoblacion() != null) {
			mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_EXITOSA,
					NHSPDDConstantes.MENSAJE_CREACION_GRUPO_ETARIO_EXITOSO, PaqueteMensajeEnum.MENSAJES, respuesta.getLenguaje(),null);
			
		}else {
		mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
						NHSPDDConstantes.MENSAJE_ASOCIACION_EXISTENTE, PaqueteMensajeEnum.MENSAJES, respuesta.getLenguaje(),null);
		}
		
=======
		// Boolean validacionUnicidadGeneral = true;

		if (peticion.getConcatenadosIdsLsEtario() != null && !peticion.getConcatenadosIdsLsEtario().equals("")) {

			String[] listaIdsLsEtario = peticion.getConcatenadosIdsLsEtario().replace(" ", "").split(";");

			// validacion de unicidad por cada id
			for (String id : listaIdsLsEtario) {

				if (id.equals(""))
					continue;

				respuestaAuxUnicidad = new BpProyInvPoblacionDTO();
				respuestaAuxUnicidad.setIdLsEtario(Long.parseLong(id));
				respuestaAuxUnicidad.setIdProyInversion(peticion.getIdProyInversion());

				respuestaAuxUnicidad = sessionFacadeBP.consultarBpProyInvPoblacionUnicidad(respuestaAuxUnicidad);

				if (!respuestaAuxUnicidad.getValidacionUnicidad()) {
					// validacionUnicidadGeneral = false;
					mensajeRespuesta(respuestaAuxUnicidad, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
							NHSPDDConstantes.MENSAJE_ASOCIACION_EXISTENTE, PaqueteMensajeEnum.MENSAJES,
							respuestaAuxUnicidad.getLenguaje(), null);
					return respuestaAuxUnicidad;
				}
			}

			// unicidades validas -> proceso de guardar los registros

			for (String id : listaIdsLsEtario) {

				if (id.equals(""))
					continue;

				peticion.setIdLsEtario(Long.parseLong(id));

				respuesta = sessionFacadeBP.guardarBpProyInvPoblacion(peticion);

				if (respuesta.getIdPoblacion() != null) {
					// guardado correctamente
				} else {
					mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
							NHSPDDConstantes.MENSAJE_ASOCIACION_EXISTENTE, PaqueteMensajeEnum.MENSAJES,
							respuesta.getLenguaje(), null);
				}

			}

		} else {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(
					NHSPDDConstantes.MENSAJE_VALIDACION_BP_PROY_INV_POBLACION_IDS_LS_ETARIO_CONCATENADOS_VACIO,
					PaqueteMensajeEnum.ERRORES, peticion.getLenguaje()));
		}

>>>>>>> developer
		return respuesta;
	}


	@Override
	public BpProyInvEtnicoDTO registrarBpProyInvEtnico(BpProyInvEtnicoDTO peticion)
			throws SpddExceptions, JsonProcessingException {
		
		 		BpProyInvEtnicoDTO respuesta = new BpProyInvEtnicoDTO();
		
		 		BpProyInvEtnicoDTO respuestaAuxUnicidad = new BpProyInvEtnicoDTO();
		 		respuestaAuxUnicidad.setIdLsEtnico(peticion.getIdLsEtnico());
				respuestaAuxUnicidad.setIdPoblacion(peticion.getIdPoblacion());	
		 		
				
				//validacion de unicidad
				respuestaAuxUnicidad = sessionFacadeBP.consultarBpProyInvEtnicoUnicidad(respuestaAuxUnicidad);
				
				if(!respuestaAuxUnicidad.getValidacionUnicidad()) {
					
					mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
							NHSPDDConstantes.MENSAJE_ASOCIACION_EXISTENTE, PaqueteMensajeEnum.MENSAJES, respuesta.getLenguaje(),null);
					return respuesta;
				}else {
					BpProyInvPoblacionDTO auxConsultaProyInvPoblacion= new BpProyInvPoblacionDTO();
					auxConsultaProyInvPoblacion.setIdPoblacion(peticion.getIdPoblacion());
					
					auxConsultaProyInvPoblacion = sessionFacadeBP.consultarBpProyInvPoblacion(auxConsultaProyInvPoblacion);
					
					if(auxConsultaProyInvPoblacion.getIdPoblacion() == null) {
						//El registro asociado padre (BpProyInvPoblacion) no existe
						
						mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
								NHSPDDConstantes.MENSAJE_CREACION_GRUPO_ETNICO_PROY_INV_POBLACION_NO_EXISTE, PaqueteMensajeEnum.MENSAJES, respuesta.getLenguaje(),null);
						return respuesta;
					}
					
					if(peticion.getNumero() <= auxConsultaProyInvPoblacion.getNumero()) {
						//No incumple la regla de negocio
						respuesta = sessionFacadeBP.guardarBpProyInvEtnico(peticion);
						
						mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_EXITOSA,
								NHSPDDConstantes.MENSAJE_CREACION_GRUPO_ETNICO_EXITOSO, PaqueteMensajeEnum.MENSAJES, respuesta.getLenguaje(),null);
						return respuesta;
						
					}else {
						//Se viola la regla de negocio
						mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
								NHSPDDConstantes.MENSAJE_CREACION_GRUPO_ETNICO_PROY_INV_POBLACION_NUMERO_NO_VALIDO, PaqueteMensajeEnum.MENSAJES, respuesta.getLenguaje(),null);
						
					}

				}
				
		
		return respuesta;
	}

	@Override
	public BpProyInvPoliticaDTO registrarBpProyInvPolitica(BpProyInvPoliticaDTO peticion) throws SpddExceptions , JsonProcessingException {
		BpProyInvPoliticaDTO respuesta = sessionFacadeBP.guardarBpProyInvPolitica(peticion);
		if (respuesta.getIdProyPolitica() != null) {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
			respuesta.setMsgRespuesta(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_CREAR_RELACION_PROYECTOINVERSION_CON_POLITICAPUBLICA_CORRECTO,
							PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
			// auditoria.crearAuditoria(peticion.toString(), peticion.toString(),
			// "CREAR_IP", "CREAR");
		} else {
			respuesta = new BpProyInvPoliticaDTO();
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_REGISTRO_REPETIDO,
					PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
		}
		return respuesta;
	}

	@Override
	public BpProyInvLineaDTO registrarBpProyInvLinea(BpProyInvLineaDTO peticion) throws SpddExceptions, JsonProcessingException {
		BpProyInvLineaDTO respuesta = sessionFacadeBP.guardarBpProyInvLinea(peticion);
		if (respuesta.getIdProyLinea() != null) {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
			respuesta.setMsgRespuesta(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_CREAR_RELACION_PROYECTOINVERSION_CON_LINEAINVERSION_CORRECTO,
							PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
			// auditoria.crearAuditoria(peticion.toString(), peticion.toString(),
			// "CREAR_IP", "CREAR");
		} else {
			respuesta = new BpProyInvLineaDTO();
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_REGISTRO_REPETIDO,
					PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
		}
		return respuesta;
	}

	@Override
	public BpProyInvPmrDTO registrarBpProyInvPmr(BpProyInvPmrDTO peticion) throws SpddExceptions, JsonProcessingException {
		BpProyInvPmrDTO respuesta = sessionFacadeBP.guardarBpProyInvPmr(peticion);
		if (respuesta.getIdProyPmr() != null) {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
			respuesta.setMsgRespuesta(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_CREAR_RELACION_PROYECTOINVERSION_CON_PDDMRINDICADOR_CORRECTO,
							PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
			// auditoria.crearAuditoria(peticion.toString(), peticion.toString(),
			// "CREAR_IP", "CREAR");
		} else {
			respuesta = new BpProyInvPmrDTO();
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_REGISTRO_REPETIDO,
					PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
		}
		return respuesta;
	}
	
	
}
