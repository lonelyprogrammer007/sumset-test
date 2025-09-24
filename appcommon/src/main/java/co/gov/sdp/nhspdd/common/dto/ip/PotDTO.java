package co.gov.sdp.nhspdd.common.dto.ip;

import co.gov.sdp.nhspdd.common.dto.RespuestaDTO;
import lombok.Data;

@Data
public class PotDTO extends RespuestaDTO {

	private Long idPot;

	private String codigoPot;

	private String actoAdministrativo;

	private String yearInicio;

	private String yearFin;

	private String estado;

	private Long idLsAdoptado;

	private String fecha;

	private String url;

	private String nombreAdoptado;
}
