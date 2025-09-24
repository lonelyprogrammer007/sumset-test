package co.gov.sdp.nhspdd.common.dto;

import lombok.Data;

/**
 * The persistent class for the PROYECTO_INVERSION database table.
 *
 */
@Data
public class ProyectoInversionDTO extends RespuestaDTO {

    private Long idProyectoInversion;
    private String nombre;

}
