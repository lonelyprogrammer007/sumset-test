package co.gov.sdp.nhspdd.common.dto;

import lombok.Data;

/**
 * Clase que recibe las credenciales de acceso para autenticación
 *
 * @author mtovar
 *
 */
@Data
public class DatosLoginDTO {

    private String nombreUsuario;
    private String contrasenia;
    private String aplicacion;
    private String tipoUsuario;

 
}
