package co.gov.sdp.nhspdd.common.dto.bp;

import co.gov.sdp.nhspdd.common.dto.RespuestaDTO;
import lombok.Data;

@Data
public class BpProyInvPmrDTO extends RespuestaDTO {

	private Long idProyPmr;
    
    private Long idIndMr;
    
    private Long idIndMrIdIndicador;
    
    private String idIndMrStringIndicador;
    
    private Long idIndMrIdMetaResultadol;
    
    private Long idProyInversion;
    
    private String stringProyInversion;
}
