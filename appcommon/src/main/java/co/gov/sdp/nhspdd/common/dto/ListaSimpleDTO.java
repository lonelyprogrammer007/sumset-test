package co.gov.sdp.nhspdd.common.dto;

import lombok.Data;

/**
 * The persistent class for the LISTA_SIMPLE database table.
 *
 */
@Data
public class ListaSimpleDTO extends RespuestaDTO {

    private Long idListaSimple;

    private String descripcion;

    private String nombre;

}
