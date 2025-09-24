package co.gov.sdp.nhspdd.common.dto.bp;

import co.gov.sdp.nhspdd.common.dto.RespuestaDTO;
import lombok.Data;

/**
 * Clase que representa el DTO de la entidad BpProyInvIniciativa
 * @author DANIEL
 * @version 1.0 14/05/2020
 */
@Data
public class BpProyInvIniciativaDTO extends RespuestaDTO{
	
	private Long idIniciativaInv;
    
    private Long idIniciativa;
    
    private String stringIniciativa;
    
    private Long idProyInversion;
    
    private String stringProyInversion;
    
    private String fecha;
    
    private String codigoProyInversion;
    
    private String idLsEstadoProyInversion;
    
    private String stringLsEstadoProyInversion;
    
    private String fechaVersionProyInversion;
    
    private String idsIniciativasConcatenadas;

	public BpProyInvIniciativaDTO(Long idIniciativaInv, Long idIniciativa, Long idProyInversion,String fecha) {
		super();
		this.idIniciativaInv = idIniciativaInv;
		this.idIniciativa = idIniciativa;
		this.idProyInversion = idProyInversion;
		this.fecha = fecha;
	}
    
	public BpProyInvIniciativaDTO() {}
}
