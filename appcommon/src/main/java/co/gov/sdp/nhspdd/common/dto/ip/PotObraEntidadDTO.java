package co.gov.sdp.nhspdd.common.dto.ip;

import co.gov.sdp.nhspdd.common.dto.RespuestaDTO;
import lombok.Data;

@Data
public class PotObraEntidadDTO extends RespuestaDTO {
	
	private Long idObraEntidad;
    
    private String codigoEntidad;
    
    private String stringEntidad;
    
    private Long idObraProyPot;
    
    private String stringObraProyPot;

	public PotObraEntidadDTO(Long idObraEntidad, String codigoEntidad, Long idObraProyPot) {
		super();
		this.idObraEntidad = idObraEntidad;
		this.codigoEntidad = codigoEntidad;
		this.idObraProyPot = idObraProyPot;
	}
	
	public PotObraEntidadDTO() {}
    
    
}
