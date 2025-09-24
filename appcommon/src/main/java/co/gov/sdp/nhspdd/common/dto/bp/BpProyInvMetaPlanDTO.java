package co.gov.sdp.nhspdd.common.dto.bp;

import java.io.Serializable;

import co.gov.sdp.nhspdd.common.dto.RespuestaDTO;
import lombok.Data;

/**
 * Clase que representa el DTO de la entidad BpProyInvMetaPlanDTO
 * @author SEBASTIAN
 * @version 1.0 28/05/2020
 */
@Data
public class BpProyInvMetaPlanDTO extends RespuestaDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long idProyMetaPlan;
	
	private Long idProyObjEspecif;
	
	private Long idMetaProducto;

	public BpProyInvMetaPlanDTO() {
		super();
	}

	public BpProyInvMetaPlanDTO(Long idProyMetaPlan, Long idProyObjEspecif, Long idMetaProducto) {
		super();
		this.idProyMetaPlan = idProyMetaPlan;
		this.idProyObjEspecif = idProyObjEspecif;
		this.idMetaProducto = idMetaProducto;
	}
	
}
