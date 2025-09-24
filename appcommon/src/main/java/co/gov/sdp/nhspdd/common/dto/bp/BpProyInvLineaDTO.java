package co.gov.sdp.nhspdd.common.dto.bp;

import co.gov.sdp.nhspdd.common.dto.RespuestaDTO;
import lombok.Data;

/**
 * Clase que representa el DTO de la entidad BpProyInvLinea
 * @author DANIEL
 * @version 1.0 31/03/2020
 */
@Data
public class BpProyInvLineaDTO extends RespuestaDTO {

	private Long idProyLinea;
	
	private Long idProyInversion;
	
	private String stringProyInversion;
	
	private Long idLineaInversion;
	
	private String stringLineaInversion;
	
	private Long idLsSectorLineaInversion;
	
	private String stringLsSectorLineaInversion;
	
	private String conceptoLineaInversion;
	
}
