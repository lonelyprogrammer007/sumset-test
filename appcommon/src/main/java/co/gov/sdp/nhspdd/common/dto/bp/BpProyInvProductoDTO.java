package co.gov.sdp.nhspdd.common.dto.bp;

import java.io.Serializable;

import co.gov.sdp.nhspdd.common.dto.RespuestaDTO;
import lombok.Data;

/**
 * Clase que representa el DTO de la entidad BpProyInvProductoDTO
 * @author SEBASTIAN
 * @version 1.0 28/05/2020
 */
@Data
public class BpProyInvProductoDTO extends RespuestaDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long idProducto;
	    
	private Long magnitud;
	    
	private Long idLsDenominacion;
	    
	private Long idProyMetaPlan;

}
