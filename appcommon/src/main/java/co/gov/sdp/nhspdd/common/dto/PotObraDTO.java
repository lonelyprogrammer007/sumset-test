package co.gov.sdp.nhspdd.common.dto;

import lombok.Data;

@Data
public class PotObraDTO extends RespuestaDTO {

	private Long idObraProyPot;

	private Long codigoPotObra;

	private String obra;

	private Long idLsPlazo;

	private String stringLsPlazo;

	private Long idNivelPot;

	private String stringNivelPot;

	private String codigoEntidadConcatenados;

	private Long idPot;

	private String nombreEntidadConcatenados;

}
