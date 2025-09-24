package co.gov.sdp.nhspdd.common.dto;

import lombok.Data;

@Data
public class UsuariosDTO extends RespuestaDTO {

    private String nombreUsuario;
    
    private Character estado;
    
    private String codigoEntidad;
    
    private Character tipoUsuario;
    
    private String fechaCaducida;
    
    private String fechaUltimoIngreso;
    
    private String nombre;
    
    private String correo;
    
    private String telefono;
    
    private Long cambiarContrasenia;
    
    private Long numeroIntentos;
    
    private String tipo;
    
    private String contraseniaNueva;
    
    private String contraseniaActual;
    
    private String aplicacion;
    
    private String clave;
    
    private String identificacion;
    
    private String nombreRoles;
    
    private String entidad;
    
    
    /**
     * 
     */
    public UsuariosDTO() {
        super();
    }

}
