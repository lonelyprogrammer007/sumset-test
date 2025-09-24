package co.gov.sdp.nhspdd.common.dto.ip;

import co.gov.sdp.nhspdd.common.dto.RespuestaDTO;
import lombok.Data;

@Data
public class PotNivelDTO extends RespuestaDTO {

	private Long idNivelPot;

	private Long numeroNivel;

	private String descripcion;

	private Long idNivelPadre;

	private Long idRamaPot;

	private Long cerrado;

	private Long esObra;
}
