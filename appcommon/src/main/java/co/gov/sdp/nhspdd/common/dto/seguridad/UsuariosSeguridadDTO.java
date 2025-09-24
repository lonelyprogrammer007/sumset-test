/**
 * 
 */
package co.gov.sdp.nhspdd.common.dto.seguridad;

import java.sql.Date;
import java.sql.Timestamp;

import co.gov.sdp.nhspdd.common.dto.RespuestaDTO;
import lombok.Data;

/**
 * @author Sumset
 *
 */
@Data
public class UsuariosSeguridadDTO extends RespuestaDTO {
	
	private String codigoUsuario;
    private String usuario;
    private Long estado;
    private String identificacion;
    private Date fechaCaducida;
    private Timestamp fechaUltimoIngreso;
    private EntidadSeguridadDTO segEntidad;
    private TipoUsuarioSeguridadDTO segTipoUsuario;
    private String aplicacion;
    private String nombre;
    private String correoElectronico;
    private String telefono;
    private Integer cambiarContrasenia;
    private RolSeguridadDTO segRol;
    private String memberOf;
    private int numeroIntentos;
    private String usuarioSesion;
    private String justificacion;
    private java.util.Date fechaCreacion;
    private String fechaCreacionFormato;
    //private String fechaCreacion;
}
