package co.gov.sdp.spdd.core.ip.service.ippot;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;

import co.gov.sdp.nhspdd.common.dto.CampoValidacionDTO;
import co.gov.sdp.nhspdd.common.dto.PotInstrumentoDTO;
import co.gov.sdp.nhspdd.common.dto.PotObraDTO;
import co.gov.sdp.nhspdd.common.dto.RespuestaDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PotDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PotNivelDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PotObraEntidadDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PotRamaDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.nhspdd.common.util.NHSPDDMensajes;
import co.gov.sdp.nhspdd.common.util.PaqueteMensajeEnum;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.spdd.core.ip.iservice.ippot.IIPPotIModificarService;
import co.gov.sdp.spdd.data.sesionfacade.interfaces.ISessionFacadeIP;
import co.gov.sdp.spdd.util.AuditoriaUtil;

@Service
public class IPPotModificarService implements IIPPotIModificarService {

	/**
	 * Inyeccion del SessionFacade del modulo IP
	 */
	@Autowired
	ISessionFacadeIP sessionFacadeIP;

	@Autowired
	AuditoriaUtil auditoria;

	/**
	 * Logger
	 */
	@Autowired
	SPDDLogger spddLogger;

	@Override
	public PotDTO modificarPot(PotDTO peticion) throws SpddExceptions {
		PotDTO respuesta = new PotDTO();
		PotDTO entidad = sessionFacadeIP.obtenerPotPorId(peticion.getIdPot());
		PotDTO auxiliar = sessionFacadeIP.obtenerPotCodigo(peticion.getCodigoPot());

		if (entidad != null && entidad.getIdPot() > 0) {
			if (auxiliar != null) {
				if (entidad.getCodigoPot().equals(auxiliar.getCodigoPot())) {
					respuesta = sessionFacadeIP.guardarPot(peticion);
					respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
					respuesta.setMsgRespuesta(
							NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_MODIFICAR_POT_CORRECTO,
									PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
				} else {
					respuesta = sessionFacadeIP.guardarPot(peticion);
					if (respuesta.getIdPot() != null) {
						respuesta = sessionFacadeIP.guardarPot(peticion);
						respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
						respuesta.setMsgRespuesta(
								NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_MODIFICAR_POT_CORRECTO,
										PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
					} else {
						respuesta = new PotDTO();
						respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
						respuesta.setMsgRespuesta(
								NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_CREAR_POT_INCORRECTO,
										PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
					}
				}
			} else {
				respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
				respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ENTIDAD_NO_ENCONTRADO,
						PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
			}
		} else {
			respuesta = sessionFacadeIP.guardarPot(peticion);
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_MODIFICAR_POT_CORRECTO,
					PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
		}
		return respuesta;
	}

	@Override
	public PotObraDTO modificarPotObra(PotObraDTO peticion) throws SpddExceptions, JsonProcessingException {
		PotObraDTO respuesta = new PotObraDTO();
		PotObraDTO potObraDTO = sessionFacadeIP.consultarPotObraPorId(peticion.getIdObraProyPot());
		PotObraDTO potObraDTOAux = sessionFacadeIP.consultarPotObraPorCodigoYIdNivelPot(peticion.getCodigoPotObra(),
				peticion.getIdNivelPot());
		if (potObraDTO != null && potObraDTO.getIdObraProyPot() > 0) {
			if (potObraDTOAux != null && peticion.getIdObraProyPot().equals(potObraDTOAux.getIdObraProyPot())) {
				sessionFacadeIP.eliminarTodosPotObraEntidadPorIdPotObra(peticion.getIdObraProyPot());
				respuesta = sessionFacadeIP.modificarPotObra(peticion);

				String[] codigosEntidades = peticion.getCodigoEntidadConcatenados().split(";");
				for (String codigoEntidadAux : codigosEntidades) {
					PotObraEntidadDTO potObraEntidadDTO = sessionFacadeIP.guardarPotObraEntidad(
							new PotObraEntidadDTO(null, codigoEntidadAux.trim(), respuesta.getIdObraProyPot()));
					if (potObraEntidadDTO.getIdObraEntidad() == null) {
						mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_EXITOSA,
								NHSPDDConstantes.MENSAJE_CREAR_POT_OBRA_EXITOSO_SIN_ENTIDADES,
								PaqueteMensajeEnum.MENSAJES, respuesta.getLenguaje(), null);
					}
				}
				if (respuesta.getCodigoRespuesta() != null
						&& respuesta.getCodigoRespuesta().equals(NHSPDDConstantes.RESPUESTA_EXITOSA)) {
					return respuesta;
				}
				mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_EXITOSA,
						NHSPDDConstantes.MENSAJE_MODIFICAR_POT_OBRA_EXITOSO, PaqueteMensajeEnum.MENSAJES,
						peticion.getLenguaje(), null);
				// TODO: Habilitar Auditoria
				// auditoria.crearAuditoria(peticion.toString(), peticion.toString(),
				// "MODIFICAR_IP", "MODIFICAR");

			} else {
				respuesta = sessionFacadeIP.guardarPotObra(peticion);
				if (respuesta != null && respuesta.getIdObraProyPot() != null) {
					sessionFacadeIP.eliminarTodosPotObraEntidadPorIdPotObra(peticion.getIdObraProyPot());

					String[] codigosEntidades = peticion.getCodigoEntidadConcatenados().split(";");
					for (String codigoEntidadAux : codigosEntidades) {
						PotObraEntidadDTO potObraEntidadDTO = sessionFacadeIP.guardarPotObraEntidad(
								new PotObraEntidadDTO(null, codigoEntidadAux.trim(), respuesta.getIdObraProyPot()));
						if (potObraEntidadDTO.getIdObraEntidad() == null) {
							mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_EXITOSA,
									NHSPDDConstantes.MENSAJE_CREAR_POT_OBRA_EXITOSO_SIN_ENTIDADES,
									PaqueteMensajeEnum.MENSAJES, respuesta.getLenguaje(), null);
						}
					}
					if (respuesta.getCodigoRespuesta() != null
							&& respuesta.getCodigoRespuesta().equals(NHSPDDConstantes.RESPUESTA_EXITOSA)) {
						return respuesta;
					}
					mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_EXITOSA,
							NHSPDDConstantes.MENSAJE_MODIFICAR_POT_OBRA_EXITOSO, PaqueteMensajeEnum.MENSAJES,
							peticion.getLenguaje(), null);
					// TODO: Habilitar Auditoria
					// auditoria.crearAuditoria(peticion.toString(), peticion.toString(),
					// "MODIFICAR_IP", "MODIFICAR");
				} else {
					respuesta = new PotObraDTO();
					mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
							NHSPDDConstantes.MENSAJE_ASOCIACION_EXISTENTE, PaqueteMensajeEnum.MENSAJES,
							respuesta.getLenguaje(), null);
				}
			}
		} else {
			mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDConstantes.MENSAJE_ENTIDAD_NO_ENCONTRADO, PaqueteMensajeEnum.MENSAJES,
					respuesta.getLenguaje(), null);
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
	public PotInstrumentoDTO modificarPotInstrumento(PotInstrumentoDTO peticion)
			throws SpddExceptions, JsonProcessingException {
		PotInstrumentoDTO respuesta = new PotInstrumentoDTO();
		PotInstrumentoDTO potInstrumentoDTO = sessionFacadeIP
				.consultarPotInstrumentoPorId(peticion.getIdInstrumentoPot());
		PotInstrumentoDTO potInstrumentoDTOAux = sessionFacadeIP
				.consultarPotInstrumentoPorCodigoYIdPot(peticion.getCodigoPotInstrumento(), peticion.getIdPot());
		if (potInstrumentoDTO != null && potInstrumentoDTO.getIdInstrumentoPot() > 0) {
			if (potInstrumentoDTOAux != null
					&& peticion.getIdInstrumentoPot().equals(potInstrumentoDTOAux.getIdInstrumentoPot())) {
				respuesta = sessionFacadeIP.modificarPotInstrumento(peticion);
				mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_EXITOSA,
						NHSPDDConstantes.MENSAJE_MODIFICAR_POT_INSTRUMENTO_EXITOSO, PaqueteMensajeEnum.MENSAJES,
						peticion.getLenguaje(), null);
				// TODO: Habilitar Auditoria
				// auditoria.crearAuditoria(peticion.toString(), peticion.toString(),
				// "MODIFICAR_IP", "MODIFICAR");

			} else {
				respuesta = sessionFacadeIP.guardarPotInstrumento(peticion);
				if (respuesta != null && respuesta.getIdInstrumentoPot() != null) {
					mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_EXITOSA,
							NHSPDDConstantes.MENSAJE_MODIFICAR_POT_INSTRUMENTO_EXITOSO, PaqueteMensajeEnum.MENSAJES,
							peticion.getLenguaje(), null);
					// TODO: Habilitar Auditoria
					// auditoria.crearAuditoria(peticion.toString(), peticion.toString(),
					// "MODIFICAR_IP", "MODIFICAR");
				} else {
					respuesta = new PotInstrumentoDTO();
					mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
							NHSPDDConstantes.MENSAJE_ASOCIACION_EXISTENTE, PaqueteMensajeEnum.MENSAJES,
							respuesta.getLenguaje(), null);
				}
			}
		} else {
			mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDConstantes.MENSAJE_ENTIDAD_NO_ENCONTRADO, PaqueteMensajeEnum.MENSAJES,
					respuesta.getLenguaje(), null);
		}
		return respuesta;
	}

	@Override
	public PotNivelDTO modificarPotNivel(PotNivelDTO peticion) throws SpddExceptions {
		PotNivelDTO respuesta = sessionFacadeIP.obtenerNivelPotPorId(peticion.getIdNivelPot());
		if (respuesta != null) {
			respuesta = sessionFacadeIP.guardarNivelPot(peticion);
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_CREAR_POT_NIVEL_CORRECTO,
					PaqueteMensajeEnum.MENSAJES, respuesta.getLenguaje()));
		} else {
			respuesta = new PotNivelDTO();
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ENTIDAD_NO_ENCONTRADO,
					PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
		}
		return respuesta;
	}

	@Override
	public PotNivelDTO cerrarNIvel(Long idNivel, Long cerrado) throws SpddExceptions {
		PotNivelDTO respuesta = sessionFacadeIP.obtenerNivelPotPorId(idNivel);
		if (respuesta != null) {
			respuesta.setCerrado(cerrado);
			respuesta = sessionFacadeIP.guardarNivelPot(respuesta);
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_CREAR_POT_NIVEL_CORRECTO,
					PaqueteMensajeEnum.MENSAJES, respuesta.getLenguaje()));
		} else {
			respuesta = new PotNivelDTO();
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ENTIDAD_NO_ENCONTRADO,
					PaqueteMensajeEnum.MENSAJES, respuesta.getLenguaje()));
		}
		return respuesta;
	}

	@Override
	public PotRamaDTO cerrarRama(Long idRama, Long cerrado) throws SpddExceptions {
		PotRamaDTO respuesta = sessionFacadeIP.obtenerRamaPotPorid(idRama);
		if (respuesta != null) {
			respuesta.setCerrada(cerrado);
			respuesta = sessionFacadeIP.guardarRamaPot(respuesta);
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
			respuesta
					.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_MODIFICAR_POT_RAMA_CORRECTO,
							PaqueteMensajeEnum.MENSAJES, respuesta.getLenguaje()));
		} else {
			respuesta = new PotRamaDTO();
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ENTIDAD_NO_ENCONTRADO,
					PaqueteMensajeEnum.MENSAJES, respuesta.getLenguaje()));
		}
		return respuesta;
	}

}
