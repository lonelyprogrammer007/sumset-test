package co.gov.sdp.nhspdd.common.dto.ip;

import co.gov.sdp.nhspdd.common.dto.RespuestaDTO;
import lombok.Data;

@Data
public class PddMpEntidadDTO extends RespuestaDTO {

	private Long idMpEntidad;
	private Long magnitud;
	private Long estado;
	private String codigoEntidad;
	private Long idMetaProducto;
	private String stringEstado;

}
