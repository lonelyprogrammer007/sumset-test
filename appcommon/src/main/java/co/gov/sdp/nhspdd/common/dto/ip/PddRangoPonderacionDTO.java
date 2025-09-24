package co.gov.sdp.nhspdd.common.dto.ip;

import co.gov.sdp.nhspdd.common.dto.RespuestaDTO;
import lombok.Data;

@Data
public class PddRangoPonderacionDTO extends RespuestaDTO {

	private Long idRango;

	private String logo;

	private String rango;

	private String descripcion;

	private Long idPlanDesarrollo;

	private String imagen;

}
