package co.gov.sdp.nhspdd.common.dto.ip;

import co.gov.sdp.nhspdd.common.dto.RespuestaDTO;
import lombok.Data;

@Data
public class PddPoliticaPublicaDTO extends RespuestaDTO {
	
	private Long idPolPub;
    
    private String codPolitica;
    
    private String politica;
    
    private Long idPlanDesarrollo;
    
    private String stringPlanDesarrollo;
}
