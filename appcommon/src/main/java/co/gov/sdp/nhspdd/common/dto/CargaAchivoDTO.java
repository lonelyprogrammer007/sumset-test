package co.gov.sdp.nhspdd.common.dto;

import lombok.Data;


@Data
public class CargaAchivoDTO extends RespuestaDTO {

	private Long  modulo;
	private String nombreModulo;
	private Long tema;
	private String nombreTema;
	private String detalle;
	private String usuario;
	private String nombreArchivo;
}
