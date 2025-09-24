package co.gov.sdp.spdd.core.ip.service.ipformulacion;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;

import co.gov.sdp.nhspdd.common.dto.CampoValidacionDTO;
import co.gov.sdp.nhspdd.common.dto.RespuestaDTO;
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
import co.gov.sdp.nhspdd.common.dto.ip.PddPrbValoracionDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddProblematicaDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.nhspdd.common.util.NHSPDDMensajes;
import co.gov.sdp.nhspdd.common.util.PaqueteMensajeEnum;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.spdd.core.ip.iservice.ipformulacion.IIPFormulacionModificarService;
import co.gov.sdp.spdd.data.sesionfacade.interfaces.ISessionFacadeIP;
import co.gov.sdp.spdd.util.AuditoriaUtil;

/**
 * Clase que permite la comunicacion entre el appdata y appcore del modulo IP.
 * Permite que el controlador pueda utilizar los servicios de modificar
 * 
 * @author DANIEL
 * @version 1.0 27/02/2020
 */
@Service
public class IPFormulacionModificarService implements IIPFormulacionModificarService {

	/**
	 * Inyeccion del SessionFacade del modulo IP
	 */
	@Autowired
	ISessionFacadeIP sessionFacadeIP;

	/**
	 * Logger
	 */
	@Autowired
	SPDDLogger spddLogger;
	
	@Autowired
	AuditoriaUtil auditoria;

	@Override
	public CompromisoEstrategicoDTO modificarCompromisoEstrategico(CompromisoEstrategicoDTO peticion)
			throws SpddExceptions {
		CompromisoEstrategicoDTO respuesta = new CompromisoEstrategicoDTO();
		// valido el registro exista en BD para poder ser modificada
		CompromisoEstrategicoDTO compromiso = sessionFacadeIP
				.consultarCompromisoEstrategicoPorID(peticion.getIdCompromiso());
		// Busco el registro que exista con la combinacion de llaves foraneas de la
		// peticion en BD
		CompromisoEstrategicoDTO compromisoAux = sessionFacadeIP
				.consultarCompromisoEstrategicoPorIdTematicaYIdCompromisoEstrategico(peticion.getIdTematica(),
						peticion.getIdCompromisoEstrategico());
		if (compromiso != null && compromiso.getIdCompromiso() > 0) {
			if (compromisoAux != null && peticion.getIdCompromiso() == compromisoAux.getIdCompromiso()) {
				// Existe para la misma entidad. Modificar.
				respuesta = sessionFacadeIP.modificarCompromisoEstrategico(peticion);
				respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
				respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(
						NHSPDDConstantes.MENSAJE_MODIFICAR_COMPROMISO_ESTRATEGICO_CORRECTO, PaqueteMensajeEnum.MENSAJES,
						peticion.getLenguaje()));
			} else {
				// Existe la llave para otra entidad. Mensaje de error donde se indica que la
				// pareja ya existe para otra entidad.
				respuesta = sessionFacadeIP.guardarCompromisoEstrategico(peticion);
				if (respuesta != null && respuesta.getIdCompromiso() != null) {
					respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
					respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(
							NHSPDDConstantes.MENSAJE_MODIFICAR_COMPROMISO_ESTRATEGICO_CORRECTO,
							PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
				} else {
					respuesta = new CompromisoEstrategicoDTO();
					respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
					respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_REGISTRO_REPETIDO,
							PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
				}
			}
		} else {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ENTIDAD_NO_ENCONTRADO,
					PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
		}
		return respuesta;
	}
	
	@Override
	public PddCompetenciaAsociadaDTO modificarPddCompetenciaAsociada(PddCompetenciaAsociadaDTO peticion) throws SpddExceptions {
		PddCompetenciaAsociadaDTO respuesta = new PddCompetenciaAsociadaDTO();
		// valido el registro exista en BD para poder ser modificada
		PddCompetenciaAsociadaDTO competenciaAsociadaDTO = sessionFacadeIP.consultarPddCompetenciaAsociadaPorId(peticion.getIdCompetencia());
		
		// Busco el registro que exista con la combinacion de llaves foraneas de la peticion en BD
		PddCompetenciaAsociadaDTO competenciaAsociadaDTOAux = sessionFacadeIP.consultarPddCompetenciaAsociadaPorIdSectorYIdCompetencia(peticion.getIdLsSector(), peticion.getIdLsCompetencia());
		if (competenciaAsociadaDTO != null && competenciaAsociadaDTO.getIdCompetencia() > 0) {
			if (competenciaAsociadaDTOAux != null && peticion.getIdCompetencia() == competenciaAsociadaDTOAux.getIdCompetencia()) {
				// Existe para la misma entidad. Modificar.
				respuesta = sessionFacadeIP.modificarPddCompetenciaAsociada(peticion);				
				respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
				respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(
						NHSPDDConstantes.MENSAJE_MODIFICAR_PDD_COMPETENCIA_ASOCIADA_CORRECTO,
						PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
			} else {
				// Existe la llave para otra entidad. Mensaje de error donde se indica que la
				// pareja ya existe para otra entidad.
				respuesta = sessionFacadeIP.guardarPddCompetenciaAsociada(peticion);
				if (respuesta != null && respuesta.getIdCompetencia() != null) {
					respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
					respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(
							NHSPDDConstantes.MENSAJE_MODIFICAR_PDD_COMPETENCIA_ASOCIADA_CORRECTO,
							PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
				} else {
					respuesta = new PddCompetenciaAsociadaDTO();
					respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
					respuesta.setMsgRespuesta(
							NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ASOCIACION_EXISTENTE,
									PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
				}
			}
		} else {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ENTIDAD_NO_ENCONTRADO,
					PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
		}
		return respuesta;
	}

	@Override
	public PddCompromisoEspecificoDTO modificarPddCompromisoEspecifico(PddCompromisoEspecificoDTO peticion)
			throws SpddExceptions {
		PddCompromisoEspecificoDTO respuesta = new PddCompromisoEspecificoDTO();
		// valido el registro exista en BD para poder ser modificada
		PddCompromisoEspecificoDTO compromisoEspecifico = sessionFacadeIP
				.consultarPddCompromisoEspecificoPorID(peticion.getIdEspecifico());
		// Busco el registro que exista con la combinacion de llaves foraneas de la
		// peticion en BD
		PddCompromisoEspecificoDTO compromisoAux = sessionFacadeIP
				.consultarPddCompromisoEspecificoPorIdCompromisoYDescripcion(peticion.getIdCompromiso(),
						peticion.getDescripcion());
		if (compromisoEspecifico != null && compromisoEspecifico.getIdEspecifico() > 0) {
			if (compromisoAux != null && peticion.getIdEspecifico() == compromisoAux.getIdEspecifico()) {
				// Existe para la misma entidad. Modificar.
				respuesta = sessionFacadeIP.modificarPddCompromisoEspecifico(peticion);

				if (respuesta != null && respuesta.getIdEspecifico() != null) {
					respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
					respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(
							NHSPDDConstantes.MENSAJE_MODIFICAR_PDD_COMPROMISO_ESPECIFICO_CORRECTO,
							PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
				} else {
					respuesta = new PddCompromisoEspecificoDTO();
					respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
					respuesta.setMsgRespuesta(
							NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ASOCIACION_EXISTENTE,
									PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
				}

			} else {
				// Existe la llave para otra entidad. Mensaje de error donde se indica que la
				// pareja ya existe para otra entidad.
				respuesta = sessionFacadeIP.guardarPddCompromisoEspecifico(peticion);
				if (respuesta != null && respuesta.getIdEspecifico() != null) {
					respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
					respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(
							NHSPDDConstantes.MENSAJE_MODIFICAR_PDD_COMPROMISO_ESPECIFICO_CORRECTO,
							PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
				} else {
					respuesta = new PddCompromisoEspecificoDTO();
					respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
					respuesta.setMsgRespuesta(
							NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ASOCIACION_EXISTENTE,
									PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
				}
			}
		} else {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ENTIDAD_NO_ENCONTRADO,
					PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
		}
		return respuesta;
	}

	@Override
	public PddMetaDTO modificarPddMeta(PddMetaDTO peticion) throws SpddExceptions {
		PddMetaDTO respuesta = new PddMetaDTO();
		PddMetaDTO meta = sessionFacadeIP.consultarPddMetaPorId(peticion.getIdMeta());
		PddMetaDTO auxiliar = sessionFacadeIP.validarMeta(peticion);
		if (meta != null) {
			if (auxiliar != null && meta.getIdMeta() == auxiliar.getIdMeta()) {
				respuesta = sessionFacadeIP.modificarMetaDeCompromiso(peticion);
				respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
				respuesta.setMsgRespuesta(
						NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_MODIFICAR_PDD_META_CORRECTO,
								PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
			} else {
				respuesta = sessionFacadeIP.guardarPddMeta(peticion);
				if (respuesta.getIdMeta() != null) {
					respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
					respuesta.setMsgRespuesta(
							NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_MODIFICAR_PDD_META_CORRECTO,
									PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
				} else {

					respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
					respuesta.setMsgRespuesta(
							NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_MODIFICAR_PDD_META_INCORRECTO,
									PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
				}
			}
		} else {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ENTIDAD_NO_ENCONTRADO,
					PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
		}
		return respuesta;
	}
	
	@Override
	public PddPrbValoracionDTO modificarPddPrbValoracion(PddPrbValoracionDTO peticion) throws SpddExceptions {
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
		
		PddPrbValoracionDTO respuesta = new PddPrbValoracionDTO();
		// valido el registro exista en BD para poder ser modificada
		PddPrbValoracionDTO pddPrbValoracionDTO = sessionFacadeIP.consultarPddPrbValoracionPorId(peticion.getIdValoracion());
		// Busco el registro que exista con la combinacion de llaves foraneas de la
		// peticion en BD
		PddPrbValoracionDTO  pddPrbValoracionDTOAux= sessionFacadeIP.consultarPddPrbValoracionPorIdProblematicaYMomento(peticion.getIdProblematica(), peticion.getMomento());
		if (pddPrbValoracionDTO != null && pddPrbValoracionDTO.getIdValoracion() > 0) {
			if (pddPrbValoracionDTOAux != null && peticion.getIdValoracion() == pddPrbValoracionDTOAux.getIdValoracion()) {
				// Existe para la misma entidad. Modificar.
				respuesta = sessionFacadeIP.modificarPddPrbValoracion(peticion);				
				respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
				respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(
						NHSPDDConstantes.MENSAJE_MODIFICAR_PDD_PRB_VALORACION_CORRECTO,
						PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
				

			} else {
				// Existe la llave para otra entidad. Mensaje de error donde se indica que la
				// pareja ya existe para otra entidad.
				respuesta = sessionFacadeIP.guardarPddPrbValoracion(peticion);
				if (respuesta != null && respuesta.getIdValoracion() != null) {
					respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
					respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(
							NHSPDDConstantes.MENSAJE_MODIFICAR_PDD_PRB_VALORACION_CORRECTO,
							PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
				} else {
					respuesta = new PddPrbValoracionDTO();
					respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
					respuesta.setMsgRespuesta(
							NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ASOCIACION_EXISTENTE,
									PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
				}
			}
		} else {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ENTIDAD_NO_ENCONTRADO,
					PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
		}
		return respuesta;
	}

	@Override
	public PddObraConcretaDTO modificarObraConcreta(PddObraConcretaDTO peticion) throws SpddExceptions {
		PddObraConcretaDTO respuesta = new PddObraConcretaDTO();
		PddObraConcretaDTO obra = sessionFacadeIP.consultarObraConcretaPorId(peticion.getIdConcreta());
		PddObraConcretaDTO auxiliar = sessionFacadeIP.validarPddObraConcreta(peticion);
		if (obra != null) {
			if (auxiliar != null && obra.getIdConcreta() == auxiliar.getIdConcreta()) {
				respuesta = sessionFacadeIP.modificarObraConcreta(peticion);
				respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
				respuesta.setMsgRespuesta(
						NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_MODIFICAR_PDD_OBRA_CONCRETA_CORRECTO,
								PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
			} else {
				respuesta = sessionFacadeIP.guardarPddObraConcreta(peticion);
				if (respuesta.getIdConcreta() != null) {
					respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
					respuesta.setMsgRespuesta(
							NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_MODIFICAR_PDD_OBRA_CONCRETA_CORRECTO,
									PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
				} else {
					respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
					respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(
							NHSPDDConstantes.MENSAJE_MODIFICAR_PDD_OBRA_CONCRETA_IN69CORRECTO,
							PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
				}
			}
		} else {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ENTIDAD_NO_ENCONTRADO,
					PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
		}
		return respuesta;
	}

	@Override
	public PddPrbPoblacionDTO modificarPrbPoblacion(PddPrbPoblacionDTO peticion) throws SpddExceptions {
		PddPrbPoblacionDTO respuesta = new PddPrbPoblacionDTO();
		PddPrbPoblacionDTO poblacion = sessionFacadeIP.consultarPrbPoblacionPorId(peticion.getIdPoblacion());
		PddPrbPoblacionDTO auxiliar = sessionFacadeIP.validarPrbPoblacion(peticion);
		if (poblacion != null) {
			if (auxiliar != null && auxiliar.getIdPoblacion() == poblacion.getIdPoblacion()) {
				respuesta = sessionFacadeIP.modificarPrbPoblacion(peticion);
				respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
				respuesta.setMsgRespuesta(
						NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_MODIFICAR_PDD_PBR_POBLACION_CORRECTO,
								PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
			} else {
				respuesta = sessionFacadeIP.guardarPrbPoblacion(peticion);
				if (respuesta.getIdPoblacion() != null) {
					respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
					respuesta.setMsgRespuesta(
							NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_MODIFICAR_PDD_PBR_POBLACION_CORRECTO,
									PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
				} else {
					respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
					respuesta.setMsgRespuesta(
							NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_REGISTRO_REPETIDO_PDD_PBR_POBLACION,
									PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
				}
			}

		} else {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ENTIDAD_NO_ENCONTRADO,
					PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
		}

		return respuesta;
	}

	@Override
	public PddPrbIndicadorDTO modificarPrbIndicador(PddPrbIndicadorDTO peticion) throws SpddExceptions {
		PddPrbIndicadorDTO respuesta = new PddPrbIndicadorDTO();
		PddPrbIndicadorDTO indicador = sessionFacadeIP.consultarPrbIndicadorPorId(peticion.getIdProbInd());
		PddPrbIndicadorDTO auxiliar = sessionFacadeIP.validarPddPrbIndicador(peticion.getIdIndicador(),
				peticion.getIdProblematica());

		if (indicador != null) {
			if (auxiliar != null && auxiliar.getIdProbInd() == indicador.getIdProbInd()) {
				respuesta = sessionFacadeIP.modificarPrbIndicador(peticion);
				respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
				respuesta.setMsgRespuesta(
						NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_MODIFICAR_PDD_PRB_INDICADOR_CORRECTO,
								PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
			} else {
				respuesta = sessionFacadeIP.guardarPrbIndicador(peticion);
				if (respuesta.getIdProbInd() != null) {
					respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
					respuesta.setMsgRespuesta(
							NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_MODIFICAR_PDD_PRB_INDICADOR_CORRECTO,
									PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
				} else {
					respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
					respuesta.setMsgRespuesta(
							NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_REGISTRO_REPETIDO_PDD_PRB_INDICADOR,
									PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
				}
			}
		} else {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ENTIDAD_NO_ENCONTRADO,
					PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
		}
		return respuesta;

	}

	@Override
	public PddMetaResultadoDTO modificarPddMetaResultado(PddMetaResultadoDTO peticion) throws SpddExceptions {
		PddMetaResultadoDTO respuesta = new PddMetaResultadoDTO();
		// valido el registro exista en BD para poder ser modificada
		PddMetaResultadoDTO pddMetaResultadoDTO = sessionFacadeIP.consultarPddMetaResultadoPorId(peticion.getIdMetaResultado());
		if (pddMetaResultadoDTO != null && pddMetaResultadoDTO.getIdMetaResultado() > 0) {
			respuesta = sessionFacadeIP.guardarPddMetaResultado(peticion);
			if (respuesta != null && respuesta.getIdMetaResultado() != null) {
				respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
				respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(
						NHSPDDConstantes.MENSAJE_MODIFICAR_PDD_META_RESULTADO_CORRECTO,
						PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
			} else {
				respuesta = new PddMetaResultadoDTO();
				respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
				respuesta.setMsgRespuesta(
						NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_MODIFICAR_PDD_META_RESULTADO_INCORRECTO,
								PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
			}			
		} else {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ENTIDAD_NO_ENCONTRADO,
					PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
		}
		return respuesta;
	}
	
	@Override
	public PddIndicadorDTO modificarPddIndicador(PddIndicadorDTO peticion) throws SpddExceptions, JsonProcessingException {
		PddIndicadorDTO respuesta = new PddIndicadorDTO();
		// valido el registro exista en BD para poder ser modificada
		PddIndicadorDTO pddIndicadorDTO = sessionFacadeIP.consultarPddIndicadorPorId(peticion.getIdIndicador());
		if (pddIndicadorDTO != null && pddIndicadorDTO.getIdIndicador() > 0) {
			respuesta = sessionFacadeIP.guardarPddIndicador(peticion);
			if (respuesta != null && respuesta.getIdIndicador() != null) {
				respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
				respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_MODIFICAR_PDD_INDICADOR_CORRECTO,
						PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
				// TODO: Habilitar Auditoria
				//auditoria.crearAuditoria(peticion.toString(), peticion.toString(), "MODIFICAR_IP", "MODIFICAR");

			} else {
				respuesta = new PddIndicadorDTO();
				respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
				respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_MODIFICAR_PDD_INDICADOR_INCORRECTO,
								PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
			}			
		} else {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ENTIDAD_NO_ENCONTRADO,
					PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
		}
		return respuesta;
	}

	@Override
	public PddCompromisoDTO modificarPddCompromiso(PddCompromisoDTO peticion) throws SpddExceptions {
		
		CompromisoEstrategicoDTO respuestaCompromisoEstrategicoDTO;
		PddCompromisoDTO respuestaPddCompromisoDTO;
		
		PddCompromisoDTO pddCompromisoDTO = sessionFacadeIP.consultarCompromisoPorId(peticion.getIdCompromiso());
		
		if(pddCompromisoDTO != null && pddCompromisoDTO.getIdCompromiso() > 0)
		{
			respuestaCompromisoEstrategicoDTO = sessionFacadeIP.consultarCompromisoEstrategicoPorIdTematicaYIdCompromisoEstrategico(peticion.getIdTematica(), peticion.getIdLsEstrategico());
			
			if(respuestaCompromisoEstrategicoDTO.getIdCompromiso() == null)
			{
				respuestaCompromisoEstrategicoDTO = sessionFacadeIP.guardarCompromisoEstrategico(new CompromisoEstrategicoDTO(null, peticion.getIdTematica(),peticion.getIdLsEstrategico(), NHSPDDConstantes.ESTADO_ACTIVO));
				respuestaPddCompromisoDTO = sessionFacadeIP.modificarPddCompromiso(new PddCompromisoDTO(peticion.getIdCompromiso(),	respuestaCompromisoEstrategicoDTO.getIdCompromiso(), peticion.getIdPlanDesarrollo()));
			}
			else
			{
				PddCompromisoDTO pddCompromisoDTOAux = sessionFacadeIP.consultarPddCompromisoPorIdEstrategicoYIdPlanDesarrollo(respuestaCompromisoEstrategicoDTO.getIdCompromiso(), peticion.getIdPlanDesarrollo());
				
				if(pddCompromisoDTOAux.getIdCompromiso() == null || pddCompromisoDTOAux.getIdCompromiso() == peticion.getIdCompromiso())
				{
					respuestaPddCompromisoDTO = sessionFacadeIP.modificarPddCompromiso(new PddCompromisoDTO(peticion.getIdCompromiso(),	respuestaCompromisoEstrategicoDTO.getIdCompromiso(), peticion.getIdPlanDesarrollo()));
				}
				else
				{
					respuestaPddCompromisoDTO = new PddCompromisoDTO();
					respuestaPddCompromisoDTO.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
					respuestaPddCompromisoDTO.setMsgRespuesta(
							NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ASOCIACION_EXISTENTE,
									PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
					return respuestaPddCompromisoDTO;
				}
			}
		}
		else
		{
			respuestaPddCompromisoDTO = new PddCompromisoDTO();
			mensajeRespuesta(respuestaPddCompromisoDTO, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO, NHSPDDConstantes.MENSAJE_ENTIDAD_NO_ENCONTRADO, PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje(), null);
			return respuestaPddCompromisoDTO;
		}
		respuestaPddCompromisoDTO.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
		respuestaPddCompromisoDTO
				.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_MODIFICAR_PDD_COMPROMISO_CORRECTO,
						PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
		return respuestaPddCompromisoDTO;
	}
	
	/**
	 * Metodo privado que permite setiar los valores necesarios para mostrar el mensaje correspondiente en cada 
	 * uno de los casos para la respuesta de las peticiones
	 * @param respuesta objeto al cual se le va a setiar el mensaje
	 * @param codigoRespuesta objeto de tipo Integer que representa el codigo de la respuesta
	 * @param msgRespuesta String que representa el mensaje de respuesta que se va a retornar
	 * @param paqueteMensaje objeto de tipo PaqueteMensajeEnum que representa el paquete donde se encuentra el mensaje
	 * @param lenguaje 
	 * @param getMsgCampoValidacion lista de mensaje de las validaciones
	 */
	private void mensajeRespuesta(RespuestaDTO respuesta, Integer codigoRespuesta, String msgRespuesta, PaqueteMensajeEnum paqueteMensaje, String lenguaje,List<CampoValidacionDTO> getMsgCampoValidacion)
	{
		respuesta.setCodigoRespuesta(codigoRespuesta);
		respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(msgRespuesta,
				paqueteMensaje, lenguaje));
		
		respuesta.setLstCampoValidacion(getMsgCampoValidacion);

				
	}

	@Override
	public PddProblematicaDTO modificarPddProblematica(PddProblematicaDTO peticion) throws SpddExceptions {
		PddProblematicaDTO respuesta = sessionFacadeIP.consultarPddProblematicaPorId(peticion.getIdProblematica());
		if(respuesta !=null) {
			respuesta = sessionFacadeIP.modificarPddProblematica(peticion);
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
			respuesta
					.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_MODIFICAR_PDD_PROBLEMATICA_CORRECTO,
							PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
		}else {
			respuesta = new PddProblematicaDTO();
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ENTIDAD_NO_ENCONTRADO,
					PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
		}
		return respuesta;
	}
}
