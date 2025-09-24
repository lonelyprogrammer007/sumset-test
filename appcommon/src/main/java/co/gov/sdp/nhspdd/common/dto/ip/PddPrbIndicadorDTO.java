package co.gov.sdp.nhspdd.common.dto.ip;

import co.gov.sdp.nhspdd.common.dto.RespuestaDTO;
import lombok.Data;

@Data
public class PddPrbIndicadorDTO extends RespuestaDTO {
	
	private Long idProbInd;
	
	private Long idIndicador;
	
	private Long idProblematica;
	
	private String nombre;
	
	private String tipo;
	
	private String lineaBaseDesc;
	
	private String fuente;
	
	private String yearLineaBase;
	
	private String informacionSoporte;
	
	private Long lineaBase;

}
