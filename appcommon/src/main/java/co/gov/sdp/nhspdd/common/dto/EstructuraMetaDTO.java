package co.gov.sdp.nhspdd.common.dto;

import lombok.Data;

/**
 * The persistent class for the ESTRUCTURA_META database table.
 *
 */
@Data
public class EstructuraMetaDTO extends RespuestaDTO {

    private Long idEstructuraMetas;

    private Integer estado;

    private Long idLsUnidadMedida;

    private Long idLsVerbo;

    private String nombreUnidadMedida;

    private String nombreVerbo;
    
    private Long tipo;
    
    private String stringEstado;
    
    private String stringTipo;

}
