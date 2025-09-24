package co.gov.sdp.nhspdd.common.dto.ip;

import co.gov.sdp.nhspdd.common.dto.RespuestaDTO;
import lombok.Data;

/**
 * Clase que persiste la tabla PDD_META_RESULTADO de la BD
 * 
 * @author DANIEL
 * @version 1.0 23/03/2020
 */
@Data
public class PddMetaResultadoDTO extends RespuestaDTO {

	private Long idMetaResultado;

	private String magnitud;

	private String complemento;

	private Long esFormulacion;

	private Long ponderacion;

	private Long idLsVerbo;

	private String nombreVerbo;

	private Long idLsUnidadMedida;

	private String nombreUnidadMedida;

	private Long idLsTipoAnualizacion;

	private String nombreTipoAnulacion;

	private Long idLsEstado;

	private String nombreEstedo;

	private Long idIndicador;

	private String nombreIndicador;

	private Long idProyEstrategico;

	private String nombreProyEstrategico;

	private Long idProbInd;

	// id de la problematica que esta en la realacion entre problematica-indicador
	private Long idProblematica;

	// nombre de la problematica que esta en la relacion entre
	// problematica-indicador
	private String nombreProblematica;

	// id del indicador que esta en la relacion entre problematica-indicador
	private Long idIndicadorProb;

	// nombre del indicador que esta en la relacion entre problematica-indicador
	private String nombreIndicadorProb;

	private Long idProyEstrategicoLocal;

	private String nombreProyEstrategicoLocal;

	private String fechaCreacion;
	
	private Long sumPond;
	
	private String stringFormulacion;

}
