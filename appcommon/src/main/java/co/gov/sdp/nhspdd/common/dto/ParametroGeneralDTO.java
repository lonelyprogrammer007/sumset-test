package co.gov.sdp.nhspdd.common.dto;

import lombok.Data;

/**
 * The persistent class for the PARAMETRO_GENERAL database table.
 *
 */
@Data
public class ParametroGeneralDTO extends RespuestaDTO {

    private String codigoParametro;

    private String argumento;

    private String descripcion;

    private String fechaCreacion;

    private String fechaModificacion;

    private String nombre;

    private String usuarioCreacion;

    private String usuarioModificacion;

}
