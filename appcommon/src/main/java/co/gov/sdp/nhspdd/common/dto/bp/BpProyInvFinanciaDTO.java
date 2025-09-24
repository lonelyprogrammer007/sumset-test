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
public class BpProyInvFinanciaDTO extends RespuestaDTO implements Serializable{
	
		
	 	private Long idFuente;
	    
	    private Long idLsFuente;
	    
	    private String idsLsfuentes;
	 
	    private Long idProyInversion;
	    
	    private String stringLsFuente;
	    
	    private String stringProyInversion;
	    
	    private Double montoAnio1;
	    private Long vigencia1;
	    
	    private Double montoAnio2;
	    private Long vigencia2;
	    
	    private Double montoAnio3;
	    private Long vigencia3;
	    
	    private Double montoAnio4;
	    private Long vigencia4;
	    
	    private Double montoAnio5;
	    private Long vigencia5;
	    
	    

}
