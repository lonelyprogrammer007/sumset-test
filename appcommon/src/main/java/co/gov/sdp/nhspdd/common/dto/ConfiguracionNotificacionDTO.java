package co.gov.sdp.nhspdd.common.dto;

import lombok.Data;

/**
 * The persistent class for the CONFIGURACION_NOTIFICACION database table.
 *
 */
@Data
public class ConfiguracionNotificacionDTO extends RespuestaDTO {

	

	private Long idConfigNotificacion;

	private String mensaje;
	
	private String asunto;
	
	private Long requiereAccion;
	
	private String operacionOrigen;
	
	private Long enviarCorreo;

	private String moduloOrigen;
	
	private Long estado;

}
