package co.gov.sdp.nhspdd.common.dto.ip;

import co.gov.sdp.nhspdd.common.dto.RespuestaDTO;
import lombok.Data;

@Data
public class PddPrbPoblacionDTO extends RespuestaDTO {

	private Long idPoblacion;
	private String descripcion;
	private Long idProblematica;

}
