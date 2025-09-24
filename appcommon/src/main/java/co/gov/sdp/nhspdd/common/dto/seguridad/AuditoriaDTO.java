package co.gov.sdp.nhspdd.common.dto.seguridad;

import lombok.Data;

@Data
public class AuditoriaDTO {

	private String nombreEvento;

	private String nombreUsuario;

	private String aplicacion;

	private String nombreTransaccion;

	private String valorModificado;

	private String valorOriginal;
	
}
