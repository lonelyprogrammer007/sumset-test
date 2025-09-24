/**
 * 
 */
package co.gov.sdp.nhspdd.common.dto.seguridad;

import java.util.Date;

import lombok.Data;

/**
 * @author Sumset
 *
 */
@Data
public class RolSeguridadDTO {
	private String codigoRol;

    private String entidad;

    private String estado;

    private Date fechaCreacion;

    private Date fechaModificacion;

    private String nombre;

    private String usuarioCreacion;

    private String usuarioModificacion;

    private AplicacionSeguridadDTO segAplicacion;
}
