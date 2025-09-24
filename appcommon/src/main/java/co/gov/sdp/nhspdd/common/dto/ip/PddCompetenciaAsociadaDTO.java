package co.gov.sdp.nhspdd.common.dto.ip;

import co.gov.sdp.nhspdd.common.dto.RespuestaDTO;
import lombok.Data;

/**
 * Clase que persiste la tabla PDD_COMPETENCIA_ASOCIADA de la BD.
 * @author DANIEL
 * @version 1.0 19/03/2020
 */
@Data
public class PddCompetenciaAsociadaDTO extends RespuestaDTO {
	
	private Long idCompetencia;
	
    private Long idLsCompetencia;
    
    private String nombreCompetencia;
    
    private Long idLsSector;
    
    private String nombreSector;
	
}
