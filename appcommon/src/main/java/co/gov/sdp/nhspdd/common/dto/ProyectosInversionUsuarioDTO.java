package co.gov.sdp.nhspdd.common.dto;

import lombok.Data;

/**
 * The persistent class for the PROYECTOS_INVERSION_USUARIO database table.
 *
 */
@Data
public class ProyectosInversionUsuarioDTO extends RespuestaDTO {

    private Long idProyectoUsuario;

    private Long idProyectoInversion;

    private String codigoUsuario;
    
    private String nombre;
    
    private String stringConcatenado;

}
