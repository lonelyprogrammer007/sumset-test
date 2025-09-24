package co.gov.sdp.nhspdd.common.dto.bp;

import java.io.Serializable;

import co.gov.sdp.nhspdd.common.dto.RespuestaDTO;
import lombok.Data;

@Data
public class BpProyInvEspecifDTO extends RespuestaDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Long idProyObjEspecif;
	    
	private String especifico;
	    
	private Long idProyInversion;

}
