package co.gov.sdp.nhspdd.common.dto.ip;

import lombok.Data;

@Data
public class PddMpEntidadPotDTO {
	
	private Long idMpEntPot;
    
    private Long idMpEntidad;
    
    private String codigoEntidad;
    
    private Long idObraProyPot;
    
    private String stringObraProyPot;
	
}
