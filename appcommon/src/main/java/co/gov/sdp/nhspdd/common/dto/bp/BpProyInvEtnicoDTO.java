package co.gov.sdp.nhspdd.common.dto.bp;

import java.io.Serializable;

import co.gov.sdp.nhspdd.common.dto.RespuestaDTO;
import lombok.Data;

/**
 * Clase que representa el DTO de la entidad BpProyInvEtnicoDTO
 * 
 * @author LUIS FIGUEROA
 * @version 1.0 02/06/2020
 */
@Data
public class BpProyInvEtnicoDTO extends RespuestaDTO implements Serializable {
	private Long idEtnico;

	private Long numero;

	private String descripcion;

	private Long idLsEtnico;
	
	private String concatenadosIdsLsEtnico;
	
	private Boolean validacionUnicidad;

	private String stringLsEtnico;

//	private Long idProyInversion;
//
//	private String stringProyInversion;
	
	private Long idPoblacion;
	
	private Long numeroPersonasIdPoblacion;

}
