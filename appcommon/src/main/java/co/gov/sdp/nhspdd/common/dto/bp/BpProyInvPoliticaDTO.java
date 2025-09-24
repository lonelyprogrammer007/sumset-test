package co.gov.sdp.nhspdd.common.dto.bp;


import co.gov.sdp.nhspdd.common.dto.RespuestaDTO;
import lombok.Data;

@Data
public class BpProyInvPoliticaDTO extends RespuestaDTO {
	
	private Long idProyPolitica;
	
    private Long idPolPub;
    
    private String stringPolPub;
    
    private Long idProyInversion;
    
    private String stringProyInversion;
    
    private String idsPolPubConcatenados;
   
}
