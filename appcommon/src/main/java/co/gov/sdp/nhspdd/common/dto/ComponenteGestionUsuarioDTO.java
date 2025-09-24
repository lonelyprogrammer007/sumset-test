package co.gov.sdp.nhspdd.common.dto;

import lombok.Data;

/**
 * The persistent class for the COMPONENTE_GESTION_USUARIO database table.
 *
 */
@Data
public class ComponenteGestionUsuarioDTO extends RespuestaDTO {

    private Long idGestionUsuario;

    private String codigoUsuario;

    private Long idComponenteGestion;
    
    private String denominacion;

    private String stringConcatenado;
}
