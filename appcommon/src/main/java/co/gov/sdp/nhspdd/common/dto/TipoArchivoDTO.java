package co.gov.sdp.nhspdd.common.dto;

import lombok.Data;

/**
 * The persistent class for the TIPO_ARCHIVO database table.
 *
 */
@Data
public class TipoArchivoDTO extends RespuestaDTO {

    private Long idTipoArchivo;

    private String modulo;

    private String tema;

}
