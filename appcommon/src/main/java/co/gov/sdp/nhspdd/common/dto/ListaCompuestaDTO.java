package co.gov.sdp.nhspdd.common.dto;

import lombok.Data;

/**
 * The persistent class for the LISTA_COMPUESTA database table.
 *
 */
@Data
public class ListaCompuestaDTO extends RespuestaDTO {

    private Long idListaCompuesta;

    private String descripcion;

    private String nombre;

    private String tabla;

}
