package co.gov.sdp.spdd.core.ip.service.ippot;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;

import co.gov.sdp.nhspdd.common.dto.CampoValidacionDTO;
import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.dto.PotInstrumentoDTO;
import co.gov.sdp.nhspdd.common.dto.PotObraDTO;
import co.gov.sdp.nhspdd.common.dto.RespuestaDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PotNivelDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PotRamaDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.nhspdd.common.util.NHSPDDMensajes;
import co.gov.sdp.nhspdd.common.util.PaqueteMensajeEnum;
import co.gov.sdp.spdd.core.ip.iservice.ippot.IIPPotIEliminarService;
import co.gov.sdp.spdd.data.sesionfacade.interfaces.ISessionFacadeIP;
import co.gov.sdp.spdd.util.AuditoriaUtil;

/**
 * 
 * @author nicolas zuluaga
 *
 */
@Service
public class IPPotEliminarService implements IIPPotIEliminarService {

	/**
	 * inyección del sessionFacade de ip
	 */
	@Autowired
	ISessionFacadeIP sessionFacadeIP;

	@Autowired
	AuditoriaUtil auditoria;

	@Override
	public PotRamaDTO eliminarPotRama(Long idRamaPot) throws SpddExceptions {
		PotRamaDTO respuesta = sessionFacadeIP.obtenerRamaPotPorid(idRamaPot);
		GenericoDTO lista = sessionFacadeIP.obtenerTodosNivelPotPorIdRamaPotDesc(idRamaPot);
		if (respuesta != null) {
			if (lista.getLstObjectsDTO().isEmpty()) {
				sessionFacadeIP.eliminarRamaPot(idRamaPot);
				respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
				respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(
						NHSPDDConstantes.MENSAJE_ELIMINAR_RAMA_POT_CORRECTO, PaqueteMensajeEnum.MENSAJES, null));
			} else {
				respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
				respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(
						NHSPDDConstantes.MENSAJE_ELIMINAR_RAMA_POT_INCORRECTO, PaqueteMensajeEnum.MENSAJES, null));
			}
		} else {
			respuesta = new PotRamaDTO();
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ENTIDAD_NO_ENCONTRADO,
					PaqueteMensajeEnum.MENSAJES, null));
		}
		return respuesta;
	}

	@Override
	public PotNivelDTO eliminarNivelDTO(Long id) throws SpddExceptions {
		GenericoDTO lista = sessionFacadeIP.obtenerTodosNivelPotPorIdNivel(id);
		PotNivelDTO respuesta = sessionFacadeIP.obtenerNivelPotPorId(id);
		PotObraDTO peticion = new PotObraDTO();
		peticion.setIdNivelPot(id);
		if (lista.getLstObjectsDTO().isEmpty()) {
			if (respuesta != null) {
				GenericoDTO potObra = sessionFacadeIP.consultarTodosPotObraPorIdNivelPotPaginado(peticion);
				if (potObra.getLstObjectsDTO().isEmpty()) {
					sessionFacadeIP.eliminarNivelPot(id);
					respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
					respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(
							NHSPDDConstantes.MENSAJE_ELIMINAR_NIVEL_POT_CORRECTO, PaqueteMensajeEnum.MENSAJES, null));
				} else {
					respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
					respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(
							NHSPDDConstantes.MENSAJE_ELIMINAR_NIVEL_POT_INCORRECTO, PaqueteMensajeEnum.MENSAJES, null));
				}
			} else {
				respuesta = new PotNivelDTO();
				respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
				respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ENTIDAD_NO_ENCONTRADO,
						PaqueteMensajeEnum.MENSAJES, null));
			}

		} else {
			respuesta = new PotNivelDTO();
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(
					NHSPDDConstantes.MENSAJE_ELIMINAR_NIVEL_POT_INCORRECTO, PaqueteMensajeEnum.MENSAJES, null));
		}
		return respuesta;
	}

	@Override
	public PotObraDTO eliminarPotObra(Long idPotObra) throws SpddExceptions, JsonProcessingException {
		PotObraDTO respuesta = sessionFacadeIP.consultarPotObraPorId(idPotObra);

		if (respuesta != null && respuesta.getIdObraProyPot() != null) {
			sessionFacadeIP.eliminarTodosPotObraEntidadPorIdPotObra(idPotObra);
			sessionFacadeIP.eliminarPotObra(idPotObra);
			mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_EXITOSA,
					NHSPDDConstantes.MENSAJE_ELIMINAR_POT_OBRA_EXITOSO, PaqueteMensajeEnum.MENSAJES, null, null);
			// TODO: Habilitar Auditoria
			// auditoria.crearAuditoria(respuesta.toString(), respuesta.toString(),
			// "MODIFICAR_IP", "MODIFICAR");
		} else {
			respuesta = new PotObraDTO();
			mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDConstantes.MENSAJE_ENTIDAD_NO_ENCONTRADO, PaqueteMensajeEnum.MENSAJES, null, null);
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
	public PotInstrumentoDTO eliminarPotInstrumento(Long idPotInstrumento)
			throws SpddExceptions, JsonProcessingException {
		PotInstrumentoDTO respuesta = sessionFacadeIP.consultarPotInstrumentoPorId(idPotInstrumento);

		if (respuesta != null && respuesta.getIdInstrumentoPot() != null) {
			sessionFacadeIP.eliminarPotInstrumento(idPotInstrumento);
			mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_EXITOSA,
					NHSPDDConstantes.MENSAJE_ELIMINAR_POT_INSTRUMENTO_EXITOSO, PaqueteMensajeEnum.MENSAJES, null, null);
			// TODO: Habilitar Auditoria
			// auditoria.crearAuditoria(respuesta.toString(), respuesta.toString(),
			// "MODIFICAR_IP", "MODIFICAR");
		} else {
			respuesta = new PotInstrumentoDTO();
			mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDConstantes.MENSAJE_ENTIDAD_NO_ENCONTRADO, PaqueteMensajeEnum.MENSAJES, null, null);
		}

		return respuesta;
	}

}
