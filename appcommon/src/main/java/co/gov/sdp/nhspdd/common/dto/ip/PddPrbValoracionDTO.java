package co.gov.sdp.nhspdd.common.dto.ip;

import co.gov.sdp.nhspdd.common.dto.RespuestaDTO;
import lombok.Data;

/**
 * Clase que persiste la tabla PDD_PRB_VALORACION de la BD
 * @author DANIEL
 * @version 1.0 19/03/2020
 */
@Data
public class PddPrbValoracionDTO extends RespuestaDTO {
    
	private Long idValoracion;
    
    private Long gravedad;
    
    private Long duracion;
    
    private Long impacto;
    
    private Long debilidad;
    
    private String balanceInicial;
    
    private String observaciones;
    
    private Long momento;
    
    private String stringMomento;
    
    private Long idLsSector;
    
    private String nombreSector;
    
    private Long idLsDimension;
    
    private String nombreDimension;
    
    //identificador de la competenecia asociada 1
    private Long idCompetenciaAsociada1;
    
    //identificador de la lista simple que contiene la informacion de la competencia asociada 1
    private Long idLsCompetencia1;
    
    private String nombreCompetencia1;
    
    
    //identificador de la competenecia asociada 2
    private Long idCompetenciaAsociada2;
    
    //identificador de la lista simple que contiene la informacion de la competencia asociada 2
    private Long idLsCompetencia2;
    
    private String nombreCompetencia2;
    
    
    private Long idProblematica;
    
    private String nombreProblematica;
    
    private Long agrava;
    
   
    
    private Long contrarresta;
    
    
}
