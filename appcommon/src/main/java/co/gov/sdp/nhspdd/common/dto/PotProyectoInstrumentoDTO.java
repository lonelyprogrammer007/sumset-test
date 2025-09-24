package co.gov.sdp.nhspdd.common.dto;

import lombok.Data;

/**
 * The persistent class for the POT_PROYECTO_INSTRUMENTO database table.
 *
 */
@Data
public class PotProyectoInstrumentoDTO extends RespuestaDTO {

	private Long idProyectoInstrumento;

	private Integer estado;

	private Long idPotInstrumento;

	private Long idPotProyecto;

	private String nombreInstrumento;

	private String nombrePotProyecto;
	
	private String stringEstado;

}
