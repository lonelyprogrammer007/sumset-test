package co.gov.sdp.nhspdd.common.dto.bp;

import co.gov.sdp.nhspdd.common.dto.RespuestaDTO;
import lombok.Data;

@Data
public class BpIniciativaUbicacionDTO extends RespuestaDTO {

	private Long idUbicacion;

	private Long idTerritorializacion;

	private Long idIniciativa;
	
	private String nombreUpz;
	
	private String nombreUpr;
	
	private String nombreVereda;
	
	private String nombreBarrio;
	
	private Long idLsBarrio;
	
	private Long idLsVereda;
	
	private Long idLsUpz;
	
	private Long idLsUpr;
	
	
}
