package co.gov.sdp.spdd.web.socket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import co.gov.sdp.nhspdd.common.dto.BuzonNotificacionesDTO;
import co.gov.sdp.nhspdd.common.dto.MensajesDTO;
import co.gov.sdp.nhspdd.common.dto.UsuariosDTO;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.nhspdd.common.util.NHSPDDMensajes;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.spdd.web.util.MetodosRest;

/**
 * Controlador del socket para las notificaciones de la aplicacion
 *
 * @author Bryan Munoz
 *
 */
@Controller
public class WebSocketController {

	// Servicio que permite mandar los mensajes a diferentes usuarios
	private SimpMessagingTemplate template;

	@Autowired
	MetodosRest<Long> notificacionesRest;

	@Autowired
	ObjectMapper mapper;

	/**
	 * Logger
	 */
	@Autowired
	SPDDLogger spddLogger;

	@Autowired
	public WebSocketController(SimpMessagingTemplate template) {
		this.template = template;
	}

	/**
	 * Metodo que permite distinguir usuarios a los que se le envia el mensaje
	 *
	 * @param name     nombre del usuario que esta logueado
	 * @param usuarios objeto dto que trasnporta informacion
	 * @return un mensaje de respuesta al usuario logueado
	 */
	@MessageMapping("/mensaje/{name}")
	public MensajesDTO notificacion(@DestinationVariable String name, UsuariosDTO usuarios) {

		MensajesDTO wInfo = new MensajesDTO();
		BuzonNotificacionesDTO buzonNotificacionesDTO = new BuzonNotificacionesDTO();
		buzonNotificacionesDTO.setCodigoUsuarioDestino(name);
		buzonNotificacionesDTO.setEstado(NHSPDDConstantes.ESTADO_MENSAJE_NO_LEIDO);
		String parametro = "";

		try {
			parametro = mapper.writeValueAsString(buzonNotificacionesDTO);
		} catch (JsonProcessingException e) {
			spddLogger.mensajeLogger(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, ""),
					NHSPDDConstantes.SEVERIDAD_ERROR, WebSocketController.class);
		}
		Long notificaciones = notificacionesRest.post(parametro, new ParameterizedTypeReference<Long>() {
		}, NHSPDDConstantes.CORE_CONTROLLER_BUZON_NOTIFICACION_OBTENER_NOTIFICACIONES, NHSPDDConstantes.TIPO_CORE);

		wInfo.setUsuario(name);
		wInfo.setNotificaciones(notificaciones);

		this.template.convertAndSend("/topic/notificacion/" + name, wInfo);

		return wInfo;
	}
}
