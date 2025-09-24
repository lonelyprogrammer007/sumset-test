package co.gov.sdp.nhspdd.common.dto.bp;

import co.gov.sdp.nhspdd.common.dto.RespuestaDTO;
import lombok.Data;

/**
 * Clase que representa el DTO de la entidad BpProyInvLocaliza
 * @author SEBASTIAN
 * @version 1.0 15/05/2020
 */
@Data
public class BpProyInvLocalizaDTO extends RespuestaDTO {
	
	private Long idProyLocaliza;

	private Long idProyInversion;

	private Long idTerritorializacion;

	public BpProyInvLocalizaDTO() {
		super();
	}
	
	public BpProyInvLocalizaDTO(Long idProyLocaliza, Long idProyInversion, Long idTerritorializacion) {
		super();
		this.idProyLocaliza = idProyLocaliza;
		this.idProyInversion = idProyInversion;
		this.idTerritorializacion = idTerritorializacion;
	}
	

}
