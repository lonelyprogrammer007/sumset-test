package co.gov.sdp.nhspdd.common.dto.ip;

import co.gov.sdp.nhspdd.common.dto.RespuestaDTO;
import lombok.Data;

@Data
public class PddMpAnualizarDTO extends RespuestaDTO {
	private Long idAnualizar;

	private String vigencia;

	private Long magnitud;

	private Long recursos;

	private Long idMetaProducto;
}
