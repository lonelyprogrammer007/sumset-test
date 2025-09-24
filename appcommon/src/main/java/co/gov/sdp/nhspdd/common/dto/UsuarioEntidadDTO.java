package co.gov.sdp.nhspdd.common.dto;

import lombok.Data;

/**
 * The persistent class for the USUARIO_ENTIDAD database table.
 *
 */
@Data
public class UsuarioEntidadDTO extends RespuestaDTO {

    private Long idUsuarioEntidad;

    private String codigoUsuario;

    private String codigoEntidad;
    
    private String stringConcatenado;
    
    private Long modificarDatos;
    
    private String stringModificar;
    
    
    private Long idBancoProyecto;
    
    private String stringBancoProyecto;

}
