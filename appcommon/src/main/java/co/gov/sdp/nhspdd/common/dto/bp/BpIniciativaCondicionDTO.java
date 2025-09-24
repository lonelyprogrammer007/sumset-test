package co.gov.sdp.nhspdd.common.dto.bp;

import co.gov.sdp.nhspdd.common.dto.RespuestaDTO;
import lombok.Data;

@Data
public class BpIniciativaCondicionDTO extends RespuestaDTO {

	private Long idCondicion;
	private Long idLsCondicion;
	private Long idIniciativa;
	private String nombreCondicion;
}
