package co.gov.sdp.nhspdd.common.dto.bp;

import co.gov.sdp.nhspdd.common.dto.RespuestaDTO;
import lombok.Data;

/**
 * Clase que representa el DTO de la entidad BpProyInvTipoDTO
 * @author DANIEL
 * @version 1.0 31/03/2020
 */
@Data
public class BpProyInvTipoDTO extends RespuestaDTO {

	private Long idTipo;
    
    private Long idLsTipo;
    
    private String stringLsTipo;
    
    private Long idProyInversion;

	public BpProyInvTipoDTO(Long idTipo, Long idLsTipo, Long idProyInversion) {
		super();
		this.idTipo = idTipo;
		this.idLsTipo = idLsTipo;
		this.idProyInversion = idProyInversion;
	}

	public BpProyInvTipoDTO() {
		super();
	}
	
	
    
    
}
