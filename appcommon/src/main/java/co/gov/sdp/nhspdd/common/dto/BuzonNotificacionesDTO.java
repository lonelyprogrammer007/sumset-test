package co.gov.sdp.nhspdd.common.dto;

import lombok.Data;

/**
 * The persistent class for the BUZON_NOTIFICACIONES database table.
 *
 */
@Data
public class BuzonNotificacionesDTO extends RespuestaDTO {

	private String codigoUsuarioDestino;

	private String codigoUsuarioOrigina;

	private Integer estado;

	private String fechaEscritura;

	private String fechaLectura;

	private String fechaRespuesta;

	private Long idConfigNotificacion;

	private Long idNotificacion;

	private String mensaje;

	private String nombreOperacionOrigen;

	private String colorEstado;
	
	private String respuesta;

	private String roles;

	private String stringEstado;

	private Long tipoMensaje;

	private String asunto;

	private String nombreEntidad;

	private String accionRequerida;

	private String accionesTomadas;
	
	private Long admin;

}
