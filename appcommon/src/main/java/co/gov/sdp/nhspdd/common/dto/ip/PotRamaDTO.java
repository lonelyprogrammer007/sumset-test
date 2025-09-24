package co.gov.sdp.nhspdd.common.dto.ip;

import co.gov.sdp.nhspdd.common.dto.RespuestaDTO;
import lombok.Data;

@Data
public class PotRamaDTO extends RespuestaDTO {

	private Long idPotRama;
	
	private Long numeroRama;
	
	private Long cerrada;

	
	private Long idPot;
	
}
