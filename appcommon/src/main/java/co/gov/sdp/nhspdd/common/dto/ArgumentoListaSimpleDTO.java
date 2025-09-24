package co.gov.sdp.nhspdd.common.dto;

import lombok.Data;

/**
 * The persistent class for the ARGUMENTO_LISTA_SIMPLE database table.
 *
 */
@Data
public class ArgumentoListaSimpleDTO extends RespuestaDTO {

    private Long idArgumento;

    private String argumento;

    private Integer estado;

    private String resultado;

    private Long idListaSimple;
    
    private String stringEstado;

}
