package co.gov.sdp.nhspdd.common.dto;


import lombok.Data;

@Data
public class PddNivelDTO extends RespuestaDTO {
	
    private Long idPddNivel;

    private Long codNivel;

    private String descripcion;

    private Long obligatorioPdl;
    
 
    private Long idPlanDesarrollo;
    
    private String nombrePlan;
    
    private Long sumPond;
    
    private String sinNiveles;
    
    //Atributos para la concatenacion de la informacion de los niveles
    private String idPddNivelConcatenados;
    
    private String codNivelConcatenados;
    
    private String descripcionConcatenados;
    
    private String obligatorioPdlConcatenados;

	public PddNivelDTO(Long idPddNivel, Long codNivel, String descripcion, Long obligatorioPdl, Long idPlanDesarrollo) {
		super();
		this.idPddNivel = idPddNivel;
		this.codNivel = codNivel;
		this.descripcion = descripcion;
		this.obligatorioPdl = obligatorioPdl;
		this.idPlanDesarrollo = idPlanDesarrollo;
	}
    
	public PddNivelDTO()
	{}
    
}
