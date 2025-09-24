package co.gov.sdp.nhspdd.common.dto.ip;

import co.gov.sdp.nhspdd.common.dto.RespuestaDTO;
import lombok.Data;
/**
 * 
 * @author SumSet 2019
 *
 */
@Data
public class PddObraConcretaDTO extends RespuestaDTO {

	private Long idConcreta;
	private String obraConcreta;
	private Long idMeta;
	private String meta;
	private String tipoMeta;
}
