package co.gov.sdp.nhspdd.common.dto.bp;

import java.io.Serializable;

import co.gov.sdp.nhspdd.common.dto.RespuestaDTO;
import lombok.Data;

@Data
public class BpProyInvAporteDTO extends RespuestaDTO implements Serializable {
	
	private Long idProyAporte;
    
    private Long idAporte;
    
    private String stringAporte;
    
    private Long idProyInversion;
    
    private String stringProyInversion;
    
    /*
     * String que tendra concatenados los ids de los aportes que se quieren relacionar con el proyecto de inversion
     */
    private String idsAportes;

	public BpProyInvAporteDTO(Long idProyAporte, Long idAporte, Long idProyInversion) {
		super();
		this.idProyAporte = idProyAporte;
		this.idAporte = idAporte;
		this.idProyInversion = idProyInversion;
	}

	public BpProyInvAporteDTO() {
		super();
	}
    
    

}
