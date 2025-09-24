package co.gov.sdp.spdd.core.ip.service.ipformulacion;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;

import co.gov.sdp.nhspdd.common.dto.ArgumentoListaSimpleDTO;
import co.gov.sdp.nhspdd.common.dto.ip.CompromisoEstrategicoDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddCompetenciaAsociadaDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddCompromisoDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddCompromisoEspecificoDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddIndicadorDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddMetaDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddMetaResultadoDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddObraConcretaDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddPrbIndicadorDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddPrbPoblacionDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddProblematicaDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddPrbValoracionDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.nhspdd.common.util.NHSPDDMensajes;
import co.gov.sdp.nhspdd.common.util.PaqueteMensajeEnum;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.spdd.core.ip.iservice.ipformulacion.IIPFormulacionRegistrarService;
import co.gov.sdp.spdd.data.sesionfacade.interfaces.ISessionFacadeAFS;
import co.gov.sdp.spdd.data.sesionfacade.interfaces.ISessionFacadeIP;
import co.gov.sdp.spdd.util.AuditoriaUtil;

/**
 * Clase que permite la comunicacion entre el appdata y appcore del modulo IP.
 * Permite que el controlador pueda utilizar los servicios de registrar
 * 
 * @author DANIEL
 * @version 1.0 24/02/2020
 */
@Service
public class IPFormulacionRegistrarService implements IIPFormulacionRegistrarService {

	/**
	 * Inyeccion del SessionFacade del modulo IP
	 */
	@Autowired
	ISessionFacadeIP sessionFacadeIP;

	@Autowired
	ISessionFacadeAFS sessionFacadeAFS;

	@Autowired
	AuditoriaUtil auditoria;

	/**
	 * Logger
	 */
	@Autowired
	SPDDLogger spddLogger;

	@Override
	public CompromisoEstrategicoDTO registrarCompromisoEstrategico(CompromisoEstrategicoDTO peticion)
			throws SpddExceptions {
		CompromisoEstrategicoDTO respuesta = sessionFacadeIP.guardarCompromisoEstrategico(peticion);
		if (respuesta.getIdCompromiso() != null) {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
			respuesta.setMsgRespuesta(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_CREAR_COMPROMISO_ESTRATEGICO_CORRECTO,
							PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
		} else {
			respuesta = new CompromisoEstrategicoDTO();
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_REGISTRO_REPETIDO,
					PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
		}
		return respuesta;
	}

	@Override
	public PddCompetenciaAsociadaDTO registrarPddCompetenciaAsociada(PddCompetenciaAsociadaDTO peticion)
			throws SpddExceptions {
		PddCompetenciaAsociadaDTO respuesta = sessionFacadeIP.guardarPddCompetenciaAsociada(peticion);
		if (respuesta != null && respuesta.getIdCompetencia() != null) {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
			respuesta.setMsgRespuesta(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_CREAR_PDD_COMPETENCIA_ASOCIADA_CORRECTO,
							PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
		} else {
			respuesta = new PddCompetenciaAsociadaDTO();
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ASOCIACION_EXISTENTE,
					PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
		}
		return respuesta;
	}

	@Override
	public PddCompromisoEspecificoDTO registrarPddCompromisoEspecifico(PddCompromisoEspecificoDTO peticion)
			throws SpddExceptions {
		PddCompromisoEspecificoDTO respuesta = sessionFacadeIP.guardarPddCompromisoEspecifico(peticion);
		if (respuesta != null && respuesta.getIdCompromiso() != null) {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
			respuesta.setMsgRespuesta(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_CREAR_PDD_COMPROMISO_ESPECIFICO_CORRECTO,
							PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
		} else {
			respuesta = new PddCompromisoEspecificoDTO();
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ASOCIACION_EXISTENTE,
					PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
		}
		return respuesta;

	}

	@Override
	public PddCompromisoDTO registrarPddCompromiso(PddCompromisoDTO peticion) throws SpddExceptions {
		CompromisoEstrategicoDTO respuestaCompromisoEstrategicoDTO;
		PddCompromisoDTO respuestaPddCompromisoDTO;
		respuestaCompromisoEstrategicoDTO = sessionFacadeIP
				.consultarCompromisoEstrategicoPorIdTematicaYIdCompromisoEstrategico(peticion.getIdTematica(),
						peticion.getIdLsEstrategico());
		if (respuestaCompromisoEstrategicoDTO.getIdCompromiso() == null) {
			respuestaCompromisoEstrategicoDTO = sessionFacadeIP
					.guardarCompromisoEstrategico(new CompromisoEstrategicoDTO(null, peticion.getIdTematica(),
							peticion.getIdLsEstrategico(), NHSPDDConstantes.ESTADO_ACTIVO));
			respuestaPddCompromisoDTO = sessionFacadeIP.guardarPddCompromiso(new PddCompromisoDTO(null,
					respuestaCompromisoEstrategicoDTO.getIdCompromiso(), peticion.getIdPlanDesarrollo()));
		} else {
			respuestaPddCompromisoDTO = sessionFacadeIP.consultarPddCompromisoPorIdEstrategicoYIdPlanDesarrollo(
					respuestaCompromisoEstrategicoDTO.getIdCompromiso(), peticion.getIdPlanDesarrollo());
			if (respuestaPddCompromisoDTO.getIdCompromiso() == null) {
				respuestaPddCompromisoDTO = sessionFacadeIP.guardarPddCompromiso(new PddCompromisoDTO(null,
						respuestaCompromisoEstrategicoDTO.getIdCompromiso(), peticion.getIdPlanDesarrollo()));
			} else {
				respuestaPddCompromisoDTO = new PddCompromisoDTO();
				respuestaPddCompromisoDTO.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
				respuestaPddCompromisoDTO
						.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ASOCIACION_EXISTENTE,
								PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
				return respuestaPddCompromisoDTO;
			}
		}
		respuestaPddCompromisoDTO.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
		respuestaPddCompromisoDTO
				.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_CREAR_PDD_COMPROMISO_CORRECTO,
						PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
		return respuestaPddCompromisoDTO;
	}

	@Override
	public PddPrbValoracionDTO registrarPddPrbValoracion(PddPrbValoracionDTO peticion) throws SpddExceptions {
		if (peticion.getMomento().equals(1L))
		{
			peticion.setAgrava(0L);
			peticion.setContrarresta(0L);
		}
		else if (peticion.getMomento().equals(2L))
		{
			peticion.setGravedad(0L);
			peticion.setDuracion(0L);
			peticion.setImpacto(0L);
			peticion.setDebilidad(0L);
		}
		else
		{
			peticion.setGravedad(0L);
			peticion.setAgrava(0L);
			peticion.setContrarresta(0L);
		}
		
		PddPrbValoracionDTO respuesta = sessionFacadeIP.guardarPddPrbValoracion(peticion);
		if (respuesta != null && respuesta.getIdValoracion() != null) {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
			respuesta.setMsgRespuesta(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_CREAR_PDD_PRB_VALORACION_CORRECTO,
							PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
		} else {
			respuesta = new PddPrbValoracionDTO();
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ASOCIACION_EXISTENTE,
					PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
		}
		return respuesta;
	}

	@Override
	public PddMetaDTO registrarMetaPorCompromiso(PddMetaDTO peticion) throws SpddExceptions, JsonProcessingException {
		PddMetaDTO respuesta = sessionFacadeIP.guardarPddMeta(peticion);
		if (respuesta.getIdEspecifico() != null) {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_CREAR_PDD_META_CORRECTO,
					PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
			auditoria.crearAuditoria(peticion.toString(), peticion.toString(), "CREAR_IP", "CREAR");
		} else {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_CREAR_PDD_META_INCORRECTO,
					PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
		}

		return respuesta;
	}

	@Override
	public PddObraConcretaDTO registrarObraConcretaPorMeta(PddObraConcretaDTO peticion) throws SpddExceptions {

		PddObraConcretaDTO respuesta = sessionFacadeIP.guardarPddObraConcreta(peticion);
		if (respuesta.getIdConcreta() != null) {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
			respuesta.setMsgRespuesta(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_CREAR_PDD_OBRA_CONCRETA_CORRECTO,
							PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
		} else {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
			respuesta.setMsgRespuesta(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_CREAR_PDD_OBRA_CONCRETA_INCORRECTA,
							PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
		}
		return respuesta;
	}

	@Override
	public PddProblematicaDTO registrarProblematica(PddProblematicaDTO peticion) throws SpddExceptions {
		// Consultar si existen las llaves foraneas

		PddProblematicaDTO respuesta = new PddProblematicaDTO();
//		Boolean condicion = false;

//		PddCompromisoDTO pddCompromisoDTO = sessionFacadeIP.consultarCompromisoPorId(peticion.getIdCompromiso());
//
//		ArgumentoListaSimpleDTO idLocalizacion = sessionFacadeAFS
//				.consultarArgumentoListaSimplePorId(peticion.getIdLsLocalizacion());
//
//		ArgumentoListaSimpleDTO idSubLocalizacion = sessionFacadeAFS
//				.consultarArgumentoListaSimplePorId(peticion.getIdLsSublocalizacion());
//
//		ArgumentoListaSimpleDTO idUpzUpr = sessionFacadeAFS
//				.consultarArgumentoListaSimplePorId(peticion.getIdLzUpzUpr());

//		condicion = pddCompromisoDTO != null && idLocalizacion != null && idSubLocalizacion != null && idUpzUpr != null;

			
			respuesta = sessionFacadeIP.guardarPddProblematica(peticion);

			if (respuesta.getIdProblematica() != null) {
				respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
				respuesta.setMsgRespuesta(
						NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_REGISTRAR_PDD_PROBLEMATICA_CORRECTO,
								PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
			} else {
				respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
				respuesta.setMsgRespuesta(
						NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_REGISTRAR_PDD_PROBLEMATICA_REPETIDO,
								PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
			}
		
		return respuesta;
	}

	@Override
	public PddPrbPoblacionDTO registrarPrbPoblacion(PddPrbPoblacionDTO peticion) throws SpddExceptions {
		PddPrbPoblacionDTO respuesta = sessionFacadeIP.guardarPrbPoblacion(peticion);
		if (respuesta.getIdPoblacion() != null) {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
			respuesta.setMsgRespuesta(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_REGISTRAR_PDD_PBR_POBLACION_CORRECTO,
							PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
		} else {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
			respuesta.setMsgRespuesta(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_REGISTRO_REPETIDO_PDD_PBR_POBLACION,
							PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
		}
		return respuesta;
	}

	@Override
	public PddPrbIndicadorDTO registrarPrbIndicador(PddPrbIndicadorDTO peticion)
			throws SpddExceptions, JsonProcessingException {
		PddPrbIndicadorDTO respuesta = sessionFacadeIP.guardarPrbIndicador(peticion);
		if (respuesta.getIdProbInd() != null) {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
			respuesta.setMsgRespuesta(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_REGISTRAR_PDD_PRB_INDICADOR_CORRECTO,
							PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
			auditoria.crearAuditoria(peticion.toString(), peticion.toString(), "CREAR_IP", "CREAR");
		} else {

			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
			respuesta.setMsgRespuesta(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_REGISTRO_REPETIDO_PDD_PRB_INDICADOR,
							PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
		}
		return respuesta;
	}

	@Override
	public PddMetaResultadoDTO registrarPddMetaResultado(PddMetaResultadoDTO peticion) throws SpddExceptions {
		SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
		peticion.setFechaCreacion(date.format(new Date()));
		PddMetaResultadoDTO respuesta = sessionFacadeIP.guardarPddMetaResultado(peticion);
		if (respuesta != null && respuesta.getIdMetaResultado() != null) {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
			respuesta.setMsgRespuesta(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_REGISTRAR_PDD_META_RESULTADO_CORRECTO,
							PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
		} else {
			respuesta = new PddMetaResultadoDTO();
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_REGISTRAR_PDD_META_RESULTADO_INCORRECTO,
							PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
		}
		return respuesta;
	}

	@Override
	public PddPrbIndicadorDTO registrarPddIndicadorYPddPrbIndicador(PddPrbIndicadorDTO peticion)
			throws SpddExceptions, JsonProcessingException {

		PddPrbIndicadorDTO respuesta = new PddPrbIndicadorDTO();
		PddIndicadorDTO pddIndicadorDTO = sessionFacadeIP
				.guardarPddIndicador(new PddIndicadorDTO(null, peticion.getNombre(), peticion.getTipo(),
						peticion.getLineaBaseDesc(), peticion.getFuente(), peticion.getYearLineaBase(),
						peticion.getInformacionSoporte(), peticion.getLineaBase(), null, null, null, null, null, null));

		if (pddIndicadorDTO != null && pddIndicadorDTO.getIdIndicador() != null) {
			respuesta.setIdIndicador(pddIndicadorDTO.getIdIndicador());
			respuesta = sessionFacadeIP.guardarPrbIndicador(peticion);

			if (respuesta != null && respuesta.getIdProbInd() != null) {
				respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
				respuesta.setMsgRespuesta(
						NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_REGISTRAR_PDD_PRB_INDICADOR_CORRECTO,
								PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
				auditoria.crearAuditoria(peticion.toString(), peticion.toString(), "CREAR_IP", "CREAR");

			} else {
				respuesta = new PddPrbIndicadorDTO();
				respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
				respuesta.setMsgRespuesta(
						NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_REGISTRAR_PDD_PRB_INDICADOR_INCORRECTO,
								PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
			}

		} else {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_REGISTRAR_PDD_INDICADOR_INCORRECTO,
							PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
		}
		return respuesta;
	}
}
