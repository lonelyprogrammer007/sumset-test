package co.gov.sdp.nhspdd.common.dto;


import java.util.List;

import lombok.Data;

/**
 * Retorna un token valido despues de autenticado
 *
 * @author mtovar
 *
 */
@Data
public class DatosToken extends GenericoSeguridadDTO {

    private String nombreUsuario;
    private String token;
    private String codigoEntidad;
    private PermisoRolEventoDto permisos;
    private List<RolDetalle> RolDetalles;

    
   
}
