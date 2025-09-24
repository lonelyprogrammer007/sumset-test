package co.gov.sdp.spdd.core.ip.service.ipplandistrital;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;

import co.gov.sdp.nhspdd.common.dto.ArgumentoListaSimpleDTO;
import co.gov.sdp.nhspdd.common.dto.CampoValidacionDTO;
import co.gov.sdp.nhspdd.common.dto.EntidadDTO;
import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.dto.PddDTO;
import co.gov.sdp.nhspdd.common.dto.PddNivelAtributoDTO;
import co.gov.sdp.nhspdd.common.dto.RespuestaDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddMetaProductoDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddMpIndicadorEntidadDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddRangoPonderacionDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PdlDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.nhspdd.common.util.NHSPDDMensajes;
import co.gov.sdp.nhspdd.common.util.PaqueteMensajeEnum;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.spdd.core.ip.iservice.ipplandistrital.IIPPlanDistritalModificarService;
import co.gov.sdp.spdd.data.sesionfacade.interfaces.ISessionFacadeAFS;
import co.gov.sdp.spdd.data.sesionfacade.interfaces.ISessionFacadeIP;
import co.gov.sdp.spdd.util.AuditoriaUtil;

/**
 * Clase que permite la comunicacion entre el appdata y appcore del modulo IP
 * Capítulos 10, 11, 14, 15, 16 y 17. Permite que el controlador pueda utilizar
 * los servicios de modificar
 * 
 * @author DANIEL
 * @version 1.0 02/03/2020
 */
@Service
public class IPPlanDistritalModificarService implements IIPPlanDistritalModificarService {

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
	public PddDTO modificarPdd(PddDTO peticion) throws SpddExceptions, JsonProcessingException {
		PddDTO respuesta = new PddDTO();
		PddDTO pddDTO = sessionFacadeIP.consultarPddPorID(peticion.getIdPlanDesarrollo());
		if (pddDTO != null && pddDTO.getIdPlanDesarrollo() > 0) {
			if (peticion.getIdPlanDesarrollo().equals(pddDTO.getIdPlanDesarrollo())) {
				ArgumentoListaSimpleDTO argumento = sessionFacadeAFS
						.consultarArgumentoListaSimplePorResultadoYNombreListaSimple(NHSPDDConstantes.PDD_ESTADO_FORMULACION, NHSPDDConstantes.LS_ESTADOS_PDD);
				Long idEstadoFormulacion = argumento.getIdArgumento() != null ? argumento.getIdArgumento() : 0;
				argumento = sessionFacadeAFS
						.consultarArgumentoListaSimplePorResultadoYNombreListaSimple(NHSPDDConstantes.PDD_ESTADO_VIGENTE, NHSPDDConstantes.LS_ESTADOS_PDD);
				Long idEstadoVigente = argumento.getIdArgumento() != null ? argumento.getIdArgumento() : 0;
				argumento = sessionFacadeAFS
						.consultarArgumentoListaSimplePorResultadoYNombreListaSimple(NHSPDDConstantes.PDD_ESTADO_FINALIZADO, NHSPDDConstantes.LS_ESTADOS_PDD);
				Long idEstadoFinalizdo = argumento.getIdArgumento() != null ? argumento.getIdArgumento() : 0;

				if (pddDTO.getIdLsEstadoPlan().equals(idEstadoFinalizdo)) {
					respuesta = new PddDTO();
					mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
							NHSPDDConstantes.MENSAJE_PDD_ESTADO_FINALIZADO, PaqueteMensajeEnum.MENSAJES,
							peticion.getLenguaje(), null);
				} else if (pddDTO.getIdLsEstadoPlan().equals(idEstadoVigente)
						&& peticion.getIdLsEstadoPlan().equals(idEstadoFormulacion)) {
					respuesta = new PddDTO();
					mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
							NHSPDDConstantes.MENSAJE_PDD_DEVOLVER_ESTADO, PaqueteMensajeEnum.MENSAJES,
							peticion.getLenguaje(), null);
				} else {
					if (pddDTO.getIdLsEstadoPlan().equals(idEstadoFormulacion)
							&& (peticion.getIdLsEstadoPlan().equals(idEstadoVigente)
									|| peticion.getIdLsEstadoPlan().equals(idEstadoFinalizdo))) {
						// Valido que no haya otro PDD en estado vigente
						if (peticion.getIdLsEstadoPlan().equals(idEstadoVigente)) {
							GenericoDTO respuestaPddVigente = sessionFacadeIP.consultarPddsPorEstado(idEstadoVigente);
							if (!respuestaPddVigente.getLstObjectsDTO().isEmpty()) {
								respuesta = new PddDTO();
								mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
										NHSPDDConstantes.MENSAJE_PDD_ESTADO_VIGENTE, PaqueteMensajeEnum.MENSAJES,
										peticion.getLenguaje(), null);
								return respuesta;
							}
						}

						GenericoDTO genericoPddNiveles = sessionFacadeIP
								.consultarPddNivelPorIdPlanDesarrollo(peticion.getIdPlanDesarrollo());
						if (genericoPddNiveles.getLstObjectsDTO().isEmpty()) {
							respuesta = new PddDTO();
							mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
									NHSPDDConstantes.MENSAJE_PDD_MODIFICAR_SIN_NIVELES, PaqueteMensajeEnum.MENSAJES,
									peticion.getLenguaje(), null);
							return respuesta;
						}
					}

					// Aumento la version cada vez que la modifico
					peticion.setVersion(String.valueOf(Integer.parseInt(pddDTO.getVersion()) + 1));
					respuesta = sessionFacadeIP.modificarPdd(peticion);
					mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_EXITOSA,
							NHSPDDConstantes.MENSAJE_MODIFICAR_PDD_CORRECTO, PaqueteMensajeEnum.MENSAJES,
							peticion.getLenguaje(), null);
					// TODO: Habilitar Auditoria
					// auditoria.crearAuditoria(peticion.toString(), peticion.toString(),
					// "MODIFICAR_IP", "MODIFICAR");
				}
			} else {
				respuesta = sessionFacadeIP.guardarPdd(peticion);
				if (respuesta.getIdPlanDesarrollo() != null) {
					mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_EXITOSA,
							NHSPDDConstantes.MENSAJE_CREAR_PDD_CORRECTO, PaqueteMensajeEnum.MENSAJES,
							peticion.getLenguaje(), null);
					// TODO: Habilitar Auditoria
					// auditoria.crearAuditoria(peticion.toString(), peticion.toString(),
					// "MODIFICAR_IP", "MODIFICAR");

				} else {
					respuesta = new PddDTO();
					mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
							NHSPDDConstantes.MENSAJE_PDD_ESTADO_FORMULACION, PaqueteMensajeEnum.MENSAJES,
							peticion.getLenguaje(), null);
				}
			}
		} else {
			mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDConstantes.MENSAJE_ENTIDAD_NO_ENCONTRADO, PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje(),
					null);
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
	public PdlDTO modificarPdl(PdlDTO peticion) throws SpddExceptions {
		PdlDTO respuesta = new PdlDTO();
		EntidadDTO entidadDTO = sessionFacadeAFS.consultarEntidad(peticion.getCodigoEntidad());
		PdlDTO auxiliar = sessionFacadeIP.consultarPlanDesarrolloLocalPorId(peticion.getIdPlanLocal());
		
		if(entidadDTO != null && entidadDTO.getCodigoEntidad() != null)
		{
			if (auxiliar != null && auxiliar.getIdPlanLocal() > 0) {
				if (auxiliar.getIdPlanLocal() == peticion.getIdPlanLocal()) {
					respuesta = sessionFacadeIP.modificarPdl(peticion);
					if (respuesta.getIdPlanLocal() != null) {
						mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_EXITOSA,
								NHSPDDConstantes.MENSAJE_MODIFICAR_PDL_NIVEL_CORRECTO, PaqueteMensajeEnum.MENSAJES,
								peticion.getLenguaje(), null);
					} else {
						respuesta = new PdlDTO();
						mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
								NHSPDDConstantes.MENSAJE_ASOCIACION_EXISTENTE, PaqueteMensajeEnum.MENSAJES,
								peticion.getLenguaje(), null);
					}
				} else {
					respuesta = sessionFacadeIP.guardarPdl(peticion);
					if (respuesta.getIdPlanLocal() != null) {
						mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_EXITOSA,
								NHSPDDConstantes.MENSAJE_CREAR_PDL_NIVEL_CORRECTO, PaqueteMensajeEnum.MENSAJES,
								peticion.getLenguaje(), null);
					} else {
						respuesta = new PdlDTO();
						mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
								NHSPDDConstantes.MENSAJE_ASOCIACION_EXISTENTE, PaqueteMensajeEnum.MENSAJES,
								peticion.getLenguaje(), null);
					}
				}
			} else {
				mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
						NHSPDDConstantes.MENSAJE_ENTIDAD_NO_ENCONTRADO, PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje(),
						null);
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
	public PddMetaProductoDTO modificarMetaProducto(PddMetaProductoDTO peticion) throws SpddExceptions {
		PddMetaProductoDTO respuesta = new PddMetaProductoDTO();
		PddMetaProductoDTO auxiliar = sessionFacadeIP.obtenerMetaProductoPorId(peticion.getIdMetaProducto());
		if (auxiliar != null) {
			respuesta = sessionFacadeIP.guardarMetaProducto(peticion);
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
			respuesta.setMsgRespuesta(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_MODIFICAR_PDD_META_PRODUCTO_CORRECTO,
							PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
		} else {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ENTIDAD_NO_ENCONTRADO,
					PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
		}

		return respuesta;
	}

	@Override
	public PddNivelAtributoDTO modificarPddNivelAtributo(PddNivelAtributoDTO peticion) throws SpddExceptions {
		PddNivelAtributoDTO respuesta = new PddNivelAtributoDTO();
		PddNivelAtributoDTO pddNivelAtributoDTO = sessionFacadeIP
				.consultarPddNivelAtributoPorId(peticion.getIdAtributo());
		PddNivelAtributoDTO pddNivelAtributoDTOAux = sessionFacadeIP.consultarPddNivelAtributoPorNumeroYIdPddNivel(
				peticion.getNumero(), peticion.getIdPddNivel(), peticion.getIdAtributoPadre());

		if (pddNivelAtributoDTO != null && pddNivelAtributoDTO.getIdAtributo() > 0) {
			if (pddNivelAtributoDTOAux.getIdAtributo() != null
					&& pddNivelAtributoDTOAux.getIdAtributo().equals(peticion.getIdAtributo())) {
				respuesta = sessionFacadeIP.modificarPddNivelAtributo(peticion);
				mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_EXITOSA,
						NHSPDDConstantes.MENSAJE_MODIFICAR_PDD_NIVEL_ATRIBUTO_CORRECTO, PaqueteMensajeEnum.MENSAJES,
						peticion.getLenguaje(), null);
				// TODO: Habilitar Auditoria
				// auditoria.crearAuditoria(peticion.toString(), peticion.toString(),
				// "MODIFICAR_IP", "MODIFICAR");
			} else {
				respuesta = sessionFacadeIP.guardarPddNivelAtributo(peticion);
				if (respuesta != null && respuesta.getIdAtributo() != null) {
					mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_EXITOSA,
							NHSPDDConstantes.MENSAJE_MODIFICAR_PDD_NIVEL_ATRIBUTO_CORRECTO, PaqueteMensajeEnum.MENSAJES,
							peticion.getLenguaje(), null);
					// TODO: Habilitar Auditoria
					// auditoria.crearAuditoria(peticion.toString(), peticion.toString(),
					// "MODIFICAR_IP", "MODIFICAR");
				} else {
					respuesta = new PddNivelAtributoDTO();
					mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
							NHSPDDConstantes.MENSAJE_ASOCIACION_EXISTENTE, PaqueteMensajeEnum.MENSAJES,
							peticion.getLenguaje(), null);
				}
			}
		} else {
			mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDConstantes.MENSAJE_ENTIDAD_NO_ENCONTRADO, PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje(),
					null);
		}
		return respuesta;
	}

	@Override
	public PddMpIndicadorEntidadDTO modificarMpEntidad(PddMpIndicadorEntidadDTO peticion) throws SpddExceptions {

		PddMpIndicadorEntidadDTO respuesta = sessionFacadeIP
				.consultarIndicadorEntidadPorId(peticion.getIdMpIndEntidad());
		PddMpIndicadorEntidadDTO auxiliar = sessionFacadeIP.validarMpIndicadorEntidad(peticion.getCodigoEntidad(),
				peticion.getIdMetaProdInd());
		if (respuesta != null) {
			if (auxiliar != null) {
				if (respuesta.getIdMpIndEntidad() == auxiliar.getIdMpIndEntidad()) {
					respuesta = sessionFacadeIP.registrarMpIndicadorEntidad(peticion);
					respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
					respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(
							NHSPDDConstantes.MENSAJE_MODIFICAR_MP_INDICADOR_ENTIDAD_CORRECTO,
							PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
				} else {
					respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
					respuesta.setMsgRespuesta(
							NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_REGISTRO_YA_EXISTENTE,
									PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
				}

			} else {
				respuesta = sessionFacadeIP.registrarMpIndicadorEntidad(peticion);
				respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
				respuesta.setMsgRespuesta(
						NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_MODIFICAR_MP_INDICADOR_ENTIDAD_CORRECTO,
								PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
			}

		} else {
			respuesta = new PddMpIndicadorEntidadDTO();
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ENTIDAD_NO_ENCONTRADO,
					PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
		}
		return respuesta;
	}

	@Override
	public PddRangoPonderacionDTO modificarPddRangoPonderacion(Long idRango, MultipartFile logo, String rango, String descripcion, Long idPlanDesarrollo) throws SpddExceptions, IOException {
	
		PddRangoPonderacionDTO respuesta = new PddRangoPonderacionDTO();
		PddRangoPonderacionDTO auxiliar = sessionFacadeIP.obtenerPddRangoPonderacionPorId(idRango);
		
		if (auxiliar != null) {
			auxiliar.setLogo(Base64.getEncoder().encodeToString(logo.getBytes()));
			auxiliar.setRango(rango);
			auxiliar.setDescripcion(descripcion);
			auxiliar.setIdPlanDesarrollo(idPlanDesarrollo);
			respuesta = sessionFacadeIP.modificarPddRangoPonderacion(auxiliar);
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
			respuesta.setMsgRespuesta(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_MODIFICAR_PDD_RANGO_PONDERACION_CORRECTO,
							PaqueteMensajeEnum.MENSAJES, respuesta.getLenguaje()));
			// TODO: Habilitar Auditoria
			// auditoria.crearAuditoria(peticion.toString(), peticion.toString(),
			// "MODIFICAR_IP", "MODIFICAR");
		} else {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ENTIDAD_NO_ENCONTRADO,
					PaqueteMensajeEnum.MENSAJES, respuesta.getLenguaje()));
		}

		return respuesta;
	}
}
