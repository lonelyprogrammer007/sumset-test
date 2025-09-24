package co.gov.sdp.nhspdd.common.dto.ip;

import co.gov.sdp.nhspdd.common.dto.RespuestaDTO;
import lombok.Data;

@Data
public class PdlNivelDTO extends RespuestaDTO {

	private Long idPdlNivel;
	
	private Long codNivel;
	
	private String descripcion;
	
	private Long idPlanLocal;
	
	private String nombrePlan;

	public PdlNivelDTO(Long idPdlNivel, Long codNivel, String descripcion, Long idPlanLocal) {
		super();
		this.idPdlNivel = idPdlNivel;
		this.codNivel = codNivel;
		this.descripcion = descripcion;
		this.idPlanLocal = idPlanLocal;
	}
	
	public PdlNivelDTO()
	{}
	
}
