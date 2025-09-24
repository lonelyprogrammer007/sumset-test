package co.gov.sdp.nhspdd.common.dto.bp;



import java.io.Serializable;

import co.gov.sdp.nhspdd.common.dto.RespuestaDTO;

import lombok.Data;

/**
 * Clase que representa el DTO de la entidad BpProyInvFinancia
 * @author Jefferson Arenas
 * @version 1.0 02/06/2020
 */
@Data
public class BpProyInvAnualizaDTO extends RespuestaDTO implements Serializable{
	
	 	private Long idPryAnualiza;
	    
	    private Long vigencia;
	    
	    private Double monto;
	    
	    private Long idFuente;
	    
	    private String stringFuenteLsFuente;

}
