package co.gov.sdp.nhspdd.common.dto.ip;

import co.gov.sdp.nhspdd.common.dto.RespuestaDTO;
import lombok.Data;

/**
 * 
 * @author Bryan Muñoz
 *
 */
@Data
public class PddMpIndicadorEntidadDTO extends RespuestaDTO {

	private Long idMpIndEntidad;
	private Long magnitud;
	private Long ponderacion;
	private String nombreEntidad;
	private String codigoEntidad;
	private Long idMetaProdInd;
	private Long idLsTipoAnualizacion;
	private String nombreTipoAnualizacion;

}
