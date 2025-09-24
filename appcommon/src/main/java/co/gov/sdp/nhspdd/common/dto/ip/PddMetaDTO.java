package co.gov.sdp.nhspdd.common.dto.ip;

import co.gov.sdp.nhspdd.common.dto.RespuestaDTO;
import lombok.Data;

/**
 * The persistent class for the SPDD_PDD_META database table.
 *
 */
@Data
public class PddMetaDTO extends RespuestaDTO {

	private Long idMeta;
	
	private String meta;
	
	private Long idTipoMetaLs;
	
	private String nombreidTipoMetaLs;
	
	private Long magnitud;
	
	private Long idEspecifico;
	
	private String stringEspecifico;

}
