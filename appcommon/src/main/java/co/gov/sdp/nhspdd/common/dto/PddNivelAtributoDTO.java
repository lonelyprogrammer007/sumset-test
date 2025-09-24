package co.gov.sdp.nhspdd.common.dto;


import lombok.Data;

@Data
public class PddNivelAtributoDTO extends RespuestaDTO {
	
	    private Long idAtributo;
	    
	    private Long numero;
	 
	    private String denominacion;
	 
	    private String ponderacion;
	  
	    private String nombreGerente;

	    private String correoGerente;
	   
	    private Long proyectoEstrategico;
	    
	    private Long sumPond;
	   
	    private Long idLsGeneroGerente;
	    
	    private String stringIdLsGeneroGerente;
	    
	    private Long idPddNivel;
	    
	    private  Long codigoPddNivel;
	    
	    private String stringIdPddNivel;
	   
	    private Long idAtributoPadre;
	    
	    private String stringIdAtributoPadre;
	    
	    private String sinNiveles;
}
