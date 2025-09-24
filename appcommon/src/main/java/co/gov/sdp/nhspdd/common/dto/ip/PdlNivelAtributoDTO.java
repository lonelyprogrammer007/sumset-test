package co.gov.sdp.nhspdd.common.dto.ip;


import co.gov.sdp.nhspdd.common.dto.RespuestaDTO;
import lombok.Data;

@Data
public class PdlNivelAtributoDTO extends RespuestaDTO {
	
	    private Long idAtributo;
	    
	    private Long numero;
	 
	    private String denominacion;
	 
	    private String ponderacion;
	  
	    private String nombreGerente;

	    private String correoGerente;
	   
	    private Long idLsGeneroGerente;
	    
	    private String stringIdLsGeneroGerente;
	    
	    private Long idPdlNivel;
	    
	    private String stringIdPdlNivel;
	   
	    private Long idAtributoPadre;
	    
	    private String stringIdAtributoPadre;
	    
	    private Long proyectoEstrategico;
}
