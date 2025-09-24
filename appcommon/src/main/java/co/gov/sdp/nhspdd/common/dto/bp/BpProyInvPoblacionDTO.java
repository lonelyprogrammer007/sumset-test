package co.gov.sdp.nhspdd.common.dto.bp;

import java.io.Serializable;

import co.gov.sdp.nhspdd.common.dto.RespuestaDTO;

import lombok.Data;

/**
 * Clase que representa el DTO de la entidad BpProyInvPoblacionDTO
 * @author LUIS FIGUEROA
 * @version 1.0 02/06/2020
 */
@Data
public class BpProyInvPoblacionDTO extends RespuestaDTO implements Serializable{
	private Long idPoblacion;
    
    private Long numero;
    
    private String descripcion;
   
    private Long idLsEtario;
    
    private String concatenadosIdsLsEtario;
    
    private Boolean validacionUnicidad;
    
    private String stringLsEtario;
   
    private Long idProyInversion;
    
    private String stringProyInversion;
}
