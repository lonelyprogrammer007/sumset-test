package co.gov.sdp.nhspdd.common.dto;

import lombok.Data;

/**
 * The persistent class for the CONSECUTIVO database table.
 *
 */
@Data
public class ConsecutivoDTO extends RespuestaDTO {

    private Long idConsecutivo;

    private String descripcion;

    private String codigoEntidad;
    
    private String StringEntidad;

    private String nombre;

    private Long secuencia;

    private String vigencia;

    private Long idPlanDesarrollo;
    
    private String stringPlanDesarrollo;

    private String stringPlanDeDesarrollo;
}
