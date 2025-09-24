package co.gov.sdp.spdd.core.ip.service.ipplandistrital;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import co.gov.sdp.nhspdd.common.dto.ArgumentoListaSimpleDTO;
import co.gov.sdp.nhspdd.common.dto.CampoValidacionDTO;
import co.gov.sdp.nhspdd.common.dto.EntidadDTO;
import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.dto.PddDTO;
import co.gov.sdp.nhspdd.common.dto.PddNivelAtributoDTO;
import co.gov.sdp.nhspdd.common.dto.PddNivelDTO;
import co.gov.sdp.nhspdd.common.dto.RespuestaDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddIndicadorDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddMRIndicadorDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddMetaProductoDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddMpIndicadorDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddMpIndicadorEntidadDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddRangoPonderacionDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PdlDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PdlNivelAtributoDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PdlNivelDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.nhspdd.common.util.NHSPDDMensajes;
import co.gov.sdp.nhspdd.common.util.PaqueteMensajeEnum;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.spdd.core.ip.iservice.ipplandistrital.IIPPlanDistritalRegistrarService;
import co.gov.sdp.spdd.data.sesionfacade.interfaces.ISessionFacadeAFS;
import co.gov.sdp.spdd.data.sesionfacade.interfaces.ISessionFacadeIP;
import co.gov.sdp.spdd.util.AuditoriaUtil;

/**
 * Clase que permite la comunicacion entre el appdata y appcore del modulo IP
 * Capítulos 10, 11, 14, 15, 16 y 17. Permite que el controlador pueda utilizar
 * los servicios de registrar
 * 
 * @author DANIEL
 * @version 1.0 02/03/2020
 */
@Service
public class IPPlanDistritalRegistrarService implements IIPPlanDistritalRegistrarService {

	/**
	 * Inyeccion del SessionFacade del modulo IP
	 */
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
	public PddDTO registrarPdd(PddDTO peticion) throws SpddExceptions, JsonProcessingException {
		// Aseguro que tenga dos niveles por defecto
		if (peticion.getIdLsNiveles() == null) {
			ArgumentoListaSimpleDTO argumentoLsNiveles = sessionFacadeAFS.consultarArgumentoListaSimplePorResultadoYNombreListaSimple(
					NHSPDDConstantes.PDD_NUMERO_NIVELES_2, NHSPDDConstantes.LS_NIVELES_PDD);
			if (argumentoLsNiveles.getIdArgumento() != null) {
				peticion.setIdLsNiveles(argumentoLsNiveles.getIdArgumento());
			}
		}

		PddDTO respuesta = sessionFacadeIP.guardarPdd(peticion);
		if (respuesta.getIdPlanDesarrollo() != null) {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_CREAR_PDD_CORRECTO,
					PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
			// TODO: Habilitar Auditoria
			// auditoria.crearAuditoria(peticion.toString(), peticion.toString(),
			// "MODIFICAR_IP", "MODIFICAR");
		} else {
			respuesta = new PddDTO();
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_PDD_ESTADO_FORMULACION,
					PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
		}
		return respuesta;
	}

	@Override
	public GenericoDTO registrarPddNivel(PddNivelDTO peticion) throws SpddExceptions, JsonProcessingException {
		GenericoDTO respuesta = new GenericoDTO();
		PddDTO pddDTOAuxiliar = sessionFacadeIP.consultarPddPorID(peticion.getIdPlanDesarrollo());

		ArgumentoListaSimpleDTO argumento = sessionFacadeAFS.consultarArgumentoListaSimplePorResultadoYNombreListaSimple(
				NHSPDDConstantes.PDD_ESTADO_VIGENTE, NHSPDDConstantes.LS_ESTADOS_PDD);
		Long idEstadoVigente = argumento.getIdArgumento() != null ? argumento.getIdArgumento() : 0;
		argumento = sessionFacadeAFS.consultarArgumentoListaSimplePorResultadoYNombreListaSimple(NHSPDDConstantes.PDD_ESTADO_FINALIZADO,
				NHSPDDConstantes.LS_ESTADOS_PDD);
		Long idEstadoFinalizdo = argumento.getIdArgumento() != null ? argumento.getIdArgumento() : 0;

		if (pddDTOAuxiliar != null && pddDTOAuxiliar.getIdLsEstadoPlan().equals(idEstadoFinalizdo)) {
			mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDConstantes.MENSAJE_PDD_NIVEL_ESTADO_FINALIZADO, PaqueteMensajeEnum.MENSAJES,
					peticion.getLenguaje(), null);
		} else if (pddDTOAuxiliar != null && pddDTOAuxiliar.getIdLsEstadoPlan().equals(idEstadoVigente)
				&& (peticion.getIdPddNivelConcatenados() == null || peticion.getIdPddNivelConcatenados().equals(""))) {
			mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDConstantes.MENSAJE_REGISTRAR_PDD_NIVEL_ESTADO_VIGENTE, PaqueteMensajeEnum.MENSAJES,
					peticion.getLenguaje(), null);
		} else {

			String[] identificadoresNiveles = null;
			if (peticion.getIdPddNivelConcatenados() != null && !peticion.getIdPddNivelConcatenados().equals("")) {
				identificadoresNiveles = peticion.getIdPddNivelConcatenados().split(";");
			}

			String[] codigosNiveles = peticion.getCodNivelConcatenados().split(";");
			String[] descripciones = peticion.getDescripcionConcatenados().split(";");
			String[] obligatorios = peticion.getObligatorioPdlConcatenados().split(";");
			boolean correcto = true;

			for (int i = 0; i < codigosNiveles.length; i++) {
				PddNivelDTO pddNivelDTOauxiliar;
				if (identificadoresNiveles != null) {
					PddNivelDTO pddNivelDTO = sessionFacadeIP
							.consultarPddNivelPorID(Long.parseLong(identificadoresNiveles[i].trim()));

					if (pddNivelDTO != null && pddNivelDTO.getIdPlanDesarrollo() > 0) {

						pddNivelDTOauxiliar = sessionFacadeIP
								.modificarPddNivel(new PddNivelDTO(Long.parseLong(identificadoresNiveles[i].trim()),
										Long.parseLong(codigosNiveles[i].trim()), descripciones[i],
										Long.parseLong(obligatorios[i].trim()), peticion.getIdPlanDesarrollo()));

						if (pddNivelDTOauxiliar != null && pddNivelDTOauxiliar.getIdPddNivel() != null) {
							mensajeRespuesta(pddNivelDTOauxiliar, NHSPDDConstantes.RESPUESTA_EXITOSA,
									NHSPDDConstantes.MENSAJE_MODIFICAR_PDD_NIVEL_CORRECTO, PaqueteMensajeEnum.MENSAJES,
									peticion.getLenguaje(), null);
							// TODO: Habilitar Auditoria
							// auditoria.crearAuditoria(peticion.toString(), peticion.toString(),
							// "MODIFICAR_IP", "MODIFICAR");
						} else {
							pddNivelDTOauxiliar = new PddNivelDTO();
							mensajeRespuesta(pddNivelDTOauxiliar, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
									NHSPDDConstantes.MENSAJE_ASOCIACION_EXISTENTE, PaqueteMensajeEnum.MENSAJES,
									peticion.getLenguaje(), null);
						}
					} else {
						pddNivelDTOauxiliar = new PddNivelDTO();
						mensajeRespuesta(pddNivelDTOauxiliar, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
								NHSPDDConstantes.MENSAJE_ENTIDAD_NO_ENCONTRADO, PaqueteMensajeEnum.MENSAJES,
								peticion.getLenguaje(), null);
					}

				} else {
					pddNivelDTOauxiliar = sessionFacadeIP.guardarPddNivel(
							new PddNivelDTO(null, Long.parseLong(codigosNiveles[i].trim()), descripciones[i],
									Long.parseLong(obligatorios[i].trim()), peticion.getIdPlanDesarrollo()));

					if (pddNivelDTOauxiliar != null && pddNivelDTOauxiliar.getIdPddNivel() != null) {
						mensajeRespuesta(pddNivelDTOauxiliar, NHSPDDConstantes.RESPUESTA_EXITOSA,
								NHSPDDConstantes.MENSAJE_CREAR_PDD_NIVEL_CORRECTO, PaqueteMensajeEnum.MENSAJES,
								peticion.getLenguaje(), null);
						// TODO: Habilitar Auditoria
						// auditoria.crearAuditoria(peticion.toString(), peticion.toString(),
						// "MODIFICAR_IP", "MODIFICAR");
					} else {
						pddNivelDTOauxiliar = new PddNivelDTO();
						mensajeRespuesta(pddNivelDTOauxiliar, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
								NHSPDDConstantes.MENSAJE_ASOCIACION_EXISTENTE, PaqueteMensajeEnum.MENSAJES,
								peticion.getLenguaje(), null);
					}
				}
				respuesta.getLstObjectsDTO().add(pddNivelDTOauxiliar);
				correcto = correcto && pddNivelDTOauxiliar.getCodigoRespuesta() == NHSPDDConstantes.RESPUESTA_EXITOSA;
			}

			if (correcto) {
				if (identificadoresNiveles == null) {
					mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_EXITOSA,
							NHSPDDConstantes.MENSAJE_CREAR_PDD_NIVELES_CORRECTO, PaqueteMensajeEnum.MENSAJES,
							respuesta.getLenguaje(), null);
				} else {
					mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_EXITOSA,
							NHSPDDConstantes.MENSAJE_MODIFICAR_PDD_NIVELES_CORRECTO, PaqueteMensajeEnum.MENSAJES,
							respuesta.getLenguaje(), null);
				}
			} else {
				respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
				if (respuesta.getLstObjectsDTO() != null) {
					String msgError = ((PddNivelDTO) (respuesta.getLstObjectsDTO().get(0))).getMsgRespuesta();
					String msgRef = NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_PDD_NIVEL_ESTADO_FORMULACION,
							PaqueteMensajeEnum.MENSAJES, respuesta.getLenguaje());
					if (msgError.equalsIgnoreCase(msgRef)) {
						mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
								NHSPDDConstantes.MENSAJE_PDD_NIVEL_ESTADO_FORMULACION, PaqueteMensajeEnum.ERRORES,
								respuesta.getLenguaje(), null);
					} else {
						if (identificadoresNiveles == null) {
							mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
									NHSPDDConstantes.MENSAJE_CREAR_PDD_NIVELES_INCORRECTO, PaqueteMensajeEnum.MENSAJES,
									respuesta.getLenguaje(), null);
						} else {
							mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
									NHSPDDConstantes.MENSAJE_MODIFICAR_PDD_NIVELES_INCORRECTO,
									PaqueteMensajeEnum.MENSAJES, respuesta.getLenguaje(), null);
						}
					}
				}
			}
		}
		return respuesta;
	}

	@Override
	public PddNivelAtributoDTO registrarPddNivelAtributo(PddNivelAtributoDTO peticion)
			throws SpddExceptions, JsonProcessingException {
		PddNivelAtributoDTO pddNivelAtributoDTOAux = sessionFacadeIP
				.consultarPddNivelAtributoPorDenominacionYIdPddNivel(peticion.getDenominacion(),
						peticion.getIdPddNivel());
		PddNivelAtributoDTO respuesta;
		if (pddNivelAtributoDTOAux.getIdAtributo() == null) {
			respuesta = sessionFacadeIP.guardarPddNivelAtributo(peticion);
			if (respuesta.getIdAtributo() != null) {
				respuesta.setStringIdPddNivel(peticion.getStringIdPddNivel());
				respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
				respuesta.setMsgRespuesta(
						NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_CREAR_PDD_NIVEL_ATRIBUTO_CORRECTO,
								PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
				// TODO: Habilitar Auditoria
				// auditoria.crearAuditoria(peticion.toString(), peticion.toString(),
				// "MODIFICAR_IP", "MODIFICAR");

			} else {
				respuesta = new PddNivelAtributoDTO();
				respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
				respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ASOCIACION_EXISTENTE,
						PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
			}
			return respuesta;
		} else {
			respuesta = new PddNivelAtributoDTO();
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_REGISTRO_YA_EXISTENTE,
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
	public PdlDTO registrarPdl(PdlDTO peticion) throws SpddExceptions {
		PdlDTO respuesta;
		ArgumentoListaSimpleDTO argumentoListaSimpleDTO = sessionFacadeAFS.consultarArgumentoListaSimplePorResultadoYNombreListaSimple(
				NHSPDDConstantes.PDD_ESTADO_VIGENTE, NHSPDDConstantes.LS_ESTADOS_PDD);
		
		EntidadDTO entidadDTO = sessionFacadeAFS.consultarEntidad(peticion.getCodigoEntidad());
		
		GenericoDTO auxPdd = sessionFacadeIP.consultarPddsPorEstado(argumentoListaSimpleDTO.getIdArgumento());
		
		if(entidadDTO != null && entidadDTO.getCodigoEntidad() != null)
		{
			if (auxPdd != null && !(auxPdd.getLstObjectsDTO().isEmpty())) {
				PddDTO pddDTO = (PddDTO) auxPdd.getLstObjectsDTO().get(0);
				peticion.setIdLsEstadoPlan(argumentoListaSimpleDTO.getIdArgumento());
				peticion.setIdPlanDesarrollo(pddDTO.getIdPlanDesarrollo());
				respuesta = sessionFacadeIP.guardarPdl(peticion);
				if (respuesta.getIdPlanDesarrollo() != null) {
					respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
					respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_CREAR_PDL_CORRECTO,
							PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
					// TODO: Habilitar Auditoria
					// auditoria.crearAuditoria(peticion.toString(), peticion.toString(),
					// "MODIFICAR_IP", "MODIFICAR");
				} else {
					respuesta = new PdlDTO();
					respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
					respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_CREAR_PDL_INCORRECTO,
							PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
				}
			} else {
				respuesta = new PdlDTO();
				respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
				respuesta.setMsgRespuesta(
						NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_CREAR_PDL_SIN_PDD_ESTADO_VIGENTE,
								PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
			}
		}
		else
		{
			respuesta = new PdlDTO();
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ENTIDAD_NO_EXISTENTE_BD_SEGPLAN,
							PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
		}
		return respuesta;
	}

	@Override
	public PddMRIndicadorDTO registrarIndicadorMetaResultado(PddIndicadorDTO peticion) throws SpddExceptions {
		PddMRIndicadorDTO respuesta;
		PddMRIndicadorDTO auxiliar = new PddMRIndicadorDTO();
		PddIndicadorDTO indicador = sessionFacadeIP.obtenerPddIndicadorMetaResultado(peticion);
		if (indicador.getIdIndicador() != null) {
			auxiliar.setIdMetaResultado(peticion.getIdMetaResultado());
			auxiliar.setIdIndicador(indicador.getIdIndicador());
			respuesta = sessionFacadeIP.validarIndicadorMetaResultado(auxiliar);
			if (respuesta != null) {
				respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
				respuesta.setMsgRespuesta(
						NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_CREAR_PDDMR_INDICADOR_INCORRECTO,
								PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
			} else {
				respuesta = sessionFacadeIP.guardarMetaIndicador(auxiliar);
				respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
				respuesta.setMsgRespuesta(
						NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_CREAR_PDDMR_INDICADOR_CORRECTO,
								PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
			}

		} else {
			peticion.setIdArchivo(-1L);
			SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
			peticion.setFechaCreacion(date.format(new Date()));
			indicador = sessionFacadeIP.guardarPddIndicador(peticion);
			auxiliar.setIdMetaResultado(peticion.getIdMetaResultado());
			auxiliar.setIdIndicador(indicador.getIdIndicador());
			respuesta = sessionFacadeIP.guardarMetaIndicador(auxiliar);
			respuesta.setCodigo(NHSPDDConstantes.RESPUESTA_EXITOSA);
			respuesta.setMsgRespuesta(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_CREAR_PDDMR_INDICADOR_CORRECTO,
							PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
		}
		return respuesta;
	}

	@Override
	public PddMetaProductoDTO registrarMetaProducto(PddMetaProductoDTO peticion) throws SpddExceptions {
		SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
		peticion.setFechaCreacion(date.format(new Date()));
		PddMetaProductoDTO respuesta = sessionFacadeIP.guardarMetaProducto(peticion);
		respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
		respuesta.setMsgRespuesta(
				NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_REGISTRAR_PDD_META_PRODUCTO_CORRECTO,
						PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
		return respuesta;
	}

	@Override
	public PddMpIndicadorDTO registrarMetaProductoIndicador(PddIndicadorDTO peticion) throws SpddExceptions {
		PddMpIndicadorDTO respuesta;
		PddIndicadorDTO indicador = sessionFacadeIP.obtenerPddIndicadorMetaResultado(peticion);
		PddMpIndicadorDTO auxiliar = new PddMpIndicadorDTO();
		auxiliar.setLongFuenteExterna(peticion.getFuenteExterna());
		auxiliar.setLongLineaBase(peticion.getLineaBase());
		auxiliar.setEsPdd(0L);
		auxiliar.setSumPond(0L);
		if (indicador.getIdIndicador() != null) {
			auxiliar.setIdIndicador(indicador.getIdIndicador());
			auxiliar.setIdMetaProducto(peticion.getIdMetaProducto());
			respuesta = sessionFacadeIP.validarMetaProductoIndicador(auxiliar);
			if (respuesta != null) {
				respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
				respuesta.setMsgRespuesta(
						NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_CREAR_PDDMP_INDICADOR_INCORRECTO,
								PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
			} else {
				respuesta = sessionFacadeIP.guardarIndicadorMetaProducto(auxiliar);
				respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
				respuesta.setMsgRespuesta(
						NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_CREAR_PDDMP_INDICADOR_CORRECTO,
								PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
			}
		} else {
			peticion.setIdArchivo(-1L);
			SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
			peticion.setFechaCreacion(date.format(new Date()));
			indicador = sessionFacadeIP.guardarPddIndicador(peticion);
			auxiliar.setIdIndicador(indicador.getIdIndicador());
			auxiliar.setIdMetaProducto(peticion.getIdMetaProducto());
			respuesta = sessionFacadeIP.guardarIndicadorMetaProducto(auxiliar);
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
			respuesta.setMsgRespuesta(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_CREAR_PDDMP_INDICADOR_CORRECTO,
							PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));

		}
		return respuesta;
	}

	@Override
	public GenericoDTO copiarEstructuraPddToPdl(PdlDTO peticion) throws SpddExceptions, JsonProcessingException {
		GenericoDTO respuesta = new GenericoDTO();
		GenericoDTO genericoNivelesPdd = sessionFacadeIP
				.consultarPddNivelPorIdPlanDesarrollo(peticion.getIdPlanDesarrollo());
		TypeReference<List<PddNivelDTO>> type = new TypeReference<List<PddNivelDTO>>() {
		};
		List<PddNivelDTO> pddNivelesDTO = objectMapper.convertValue(genericoNivelesPdd.getLstObjectsDTO(), type);

		ArgumentoListaSimpleDTO argumentoClasificacionAlcaldiaEntidad = sessionFacadeAFS
				.consultarArgumentoListaSimplePorResultadoYNombreListaSimple(NHSPDDConstantes.ENTIDAD_ALCALDIA,
						NHSPDDConstantes.LS_CLASIFICACION_ENTIDADES);
		EntidadDTO entidadDTO = sessionFacadeAFS.consultarEntidad(peticion.getCodigoEntidad());
		PdlDTO pdlDTOVigente = new PdlDTO();
		GenericoDTO genericoPdlDTOVigentes = sessionFacadeIP.consultarPdlPorEntidad(NHSPDDConstantes.PDD_ESTADO_VIGENTE,
				peticion.getCodigoEntidad());
		boolean correcto = true;

		if (!genericoPdlDTOVigentes.getLstObjectsDTO().isEmpty()) {
			TypeReference<PdlDTO> typePdlDTO = new TypeReference<PdlDTO>() {
			};
			pdlDTOVigente = objectMapper.convertValue(genericoPdlDTOVigentes.getLstObjectsDTO().get(0), typePdlDTO);
		}

		if (entidadDTO.getIdLsClasificacion() != null
				&& entidadDTO.getIdLsClasificacion().equals(argumentoClasificacionAlcaldiaEntidad.getIdArgumento())) {
			if (pdlDTOVigente.getIdPlanLocal() != null) {
				for (PddNivelDTO pddNivelDTOTemp : pddNivelesDTO) {
					PdlNivelDTO pdlNivelDTOAux;
					try {
						pdlNivelDTOAux = sessionFacadeIP
								.guardarPdlNivel(new PdlNivelDTO(null, pddNivelDTOTemp.getCodNivel(),
										pddNivelDTOTemp.getDescripcion(), pdlDTOVigente.getIdPlanLocal()));

						if (pdlNivelDTOAux != null && pdlNivelDTOAux.getIdPdlNivel() != null) {
							mensajeRespuesta(pdlNivelDTOAux, NHSPDDConstantes.RESPUESTA_EXITOSA,
									NHSPDDConstantes.MENSAJE_CREAR_PDL_NIVEL_CORRECTO, PaqueteMensajeEnum.MENSAJES,
									pdlDTOVigente.getLenguaje(), null);
							// TODO: Habilitar Auditoria
							// auditoria.crearAuditoria(peticion.toString(), peticion.toString(),
							// "MODIFICAR_IP", "MODIFICAR");
						} else {
							pdlNivelDTOAux = new PdlNivelDTO();
							mensajeRespuesta(pdlNivelDTOAux, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
									NHSPDDConstantes.MENSAJE_ASOCIACION_EXISTENTE, PaqueteMensajeEnum.MENSAJES,
									pdlDTOVigente.getLenguaje(), null);
						}
					} catch (SpddExceptions e) {
						pdlNivelDTOAux = new PdlNivelDTO();
						mensajeRespuesta(pdlNivelDTOAux, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
								NHSPDDConstantes.MENSAJE_ASOCIACION_EXISTENTE, PaqueteMensajeEnum.MENSAJES,
								pdlDTOVigente.getLenguaje(), null);
					}
					respuesta.getLstObjectsDTO().add(pdlNivelDTOAux);
					correcto = correcto && pdlNivelDTOAux.getCodigoRespuesta() == NHSPDDConstantes.RESPUESTA_EXITOSA;
				}

				if (correcto) {
					mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_EXITOSA,
							NHSPDDConstantes.MENSAJE_CREAR_PDL_NIVELES_CORRECTO, PaqueteMensajeEnum.MENSAJES,
							respuesta.getLenguaje(), null);
				} else {
					mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
							NHSPDDConstantes.MENSAJE_CREAR_PDL_NIVELES_INCORRECTO, PaqueteMensajeEnum.MENSAJES,
							respuesta.getLenguaje(), null);
				}
			} else {
				mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
						NHSPDDConstantes.MENSAJE_PDL_VIGENTE_INEXISTENTE, PaqueteMensajeEnum.MENSAJES,
						respuesta.getLenguaje(), null);
			}
		} else {
			mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDConstantes.MENSAJE_PDL_ENTIDAD_NO_ALCALDIA, PaqueteMensajeEnum.MENSAJES,
					respuesta.getLenguaje(), null);
		}
		return respuesta;
	}

	@Override
	public PdlNivelAtributoDTO registrarPdlNivelAtributo(PdlNivelAtributoDTO peticion) throws SpddExceptions {
		PdlNivelAtributoDTO pdlNivelAtributoDTOAux = sessionFacadeIP
				.consultarPdlNivelAtributoPorDenominacionYIdPdlNivel(peticion.getDenominacion(),
						peticion.getIdPdlNivel());
		PdlNivelAtributoDTO respuesta;
		if (pdlNivelAtributoDTOAux == null) {
			respuesta = sessionFacadeIP.guardarPdlNivelAtributo(peticion);
			if (respuesta.getIdAtributo() != null) {
				respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
				respuesta.setMsgRespuesta(
						NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_CREAR_PDL_NIVEL_ATRIBUTO_CORRECTO,
								PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
				// TODO: Habilitar Auditoria
				// auditoria.crearAuditoria(peticion.toString(), peticion.toString(),
				// "MODIFICAR_IP", "MODIFICAR");

			} else {
				respuesta = new PdlNivelAtributoDTO();
				respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
				respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ASOCIACION_EXISTENTE,
						PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
			}
			return respuesta;
		} else {
			respuesta = new PdlNivelAtributoDTO();
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_REGISTRO_YA_EXISTENTE,
					PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
		}
		return respuesta;
	}

	@Override
	public PddMpIndicadorEntidadDTO registrarMpEntidad(PddMpIndicadorEntidadDTO peticion) throws SpddExceptions {
		PddMpIndicadorEntidadDTO respuesta = new PddMpIndicadorEntidadDTO();
		PddMpIndicadorEntidadDTO auxiliar = sessionFacadeIP.validarMpIndicadorEntidad(peticion.getCodigoEntidad(),
				peticion.getIdMetaProdInd());
		if (auxiliar != null) {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_REGISTRO_YA_EXISTENTE,
					PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
		} else {
			respuesta = sessionFacadeIP.registrarMpIndicadorEntidad(peticion);
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
			respuesta.setMsgRespuesta(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_REGISTRAR_MP_INDICADOR_ENTIDAD_CORRECTO,
							PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
		}
		return respuesta;
	}

	@Override
	public PddRangoPonderacionDTO registrarPddRangoPonderacion(Long idRango, MultipartFile logo, String rango,
			String descripcion, Long idPlanDesarrollo) throws SpddExceptions, IOException {
		PddRangoPonderacionDTO peticion = new PddRangoPonderacionDTO();
		peticion.setLogo(Base64.getEncoder().encodeToString(logo.getBytes()));
		peticion.setRango(rango);
		peticion.setDescripcion(descripcion);
		peticion.setIdPlanDesarrollo(idPlanDesarrollo);
		PddRangoPonderacionDTO respuesta = new PddRangoPonderacionDTO();
		if (peticion.getIdPlanDesarrollo() != null) {
			GenericoDTO auxiliar = sessionFacadeIP.obtenerPddRangoPonderacionPorIdPdd(idPlanDesarrollo);
			if (auxiliar.getLstObjectsDTO().isEmpty()) {
				respuesta = sessionFacadeIP.guardarPddRangoPonderacion(peticion);
				respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
				respuesta.setMsgRespuesta(
						NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_REGISTRAR_PDD_RANGO_PONDERACION_CORRECTO,
								PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
				// TODO: Habilitar Auditoria
				// auditoria.crearAuditoria(peticion.toString(), peticion.toString(),
				// "MODIFICAR_IP", "MODIFICAR");
			} else {
				if (auxiliar.getLstObjectsDTO().size() >= 5) {
					respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
					respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(
							NHSPDDConstantes.MENSAJE_REGISTRAR_PDD_RANGO_PONDERACION_INCORRECTO,
							PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
				} else {
					PddRangoPonderacionDTO rangoBD = (PddRangoPonderacionDTO) auxiliar.getLstObjectsDTO().get(0);
					if (esRangoTraslapado(rangoBD.getRango(), peticion.getRango())) {
						respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
						respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(
								NHSPDDConstantes.MENSAJE_REGISTRAR_PDD_RANGO_PONDERACION_INCORRECTO,
								PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
					} else {
						respuesta = sessionFacadeIP.guardarPddRangoPonderacion(peticion);
						respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
						respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(
								NHSPDDConstantes.MENSAJE_REGISTRAR_PDD_RANGO_PONDERACION_CORRECTO,
								PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
					}
				}
			}
		} else {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_REGISTRAR_PDD_RANGO_PONDERACION_INCORRECTO,
							PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
		}
		return respuesta;
	}

	private Integer valorDelRango(String rango) throws SpddExceptions {
		char[] rango_div = rango.toCharArray();
		String n = "";
		Integer entero = 0;

		for (int i = 0; i < rango_div.length; i++) {
			if (Character.isDigit(rango_div[i])) {
				n += rango_div[i];
			}
			if (rango_div[i] == 'y') {
				n += rango_div[i];
			}
		}

		if (n.length() <= 3) {
			entero = Integer.parseInt(n);
		} else {
			String[] parts = n.split("y");
			String part = parts[1];
			entero = Integer.parseInt(part);
		}
		return entero;
	}

	private boolean esRangoTraslapado(String rangoBD, String RangoPeticion) throws SpddExceptions {
		Integer baseD = valorDelRango(rangoBD);
		Integer peticion = valorDelRango(RangoPeticion);
		if (peticion > baseD) {
			return false;
		}
		return true;
	}

}
