package co.gov.sdp.nhspdd.common.dto.bp;

import co.gov.sdp.nhspdd.common.dto.RespuestaDTO;
import lombok.Data;

@Data
public class BpIniciativaEtarioDTO extends RespuestaDTO {

	private Long idEtario;
	private Long idLsEtario;
	private Long idIniciativa;
	private String nombreEtario;

}
