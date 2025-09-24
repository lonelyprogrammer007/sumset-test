package co.gov.sdp.nhspdd.common.dto;

import lombok.Data;

@Data
public class PotInstrumentoDTO extends RespuestaDTO {

	private Long idInstrumentoPot;

	private Long codigoPotInstrumento;

	private Long idLsDenominacion;
	
	private String stringLsDenominacion;

	private Long idPot;
	
	private Long idLsNivelInst;
	
	private String stringLsNivelInst;

}
