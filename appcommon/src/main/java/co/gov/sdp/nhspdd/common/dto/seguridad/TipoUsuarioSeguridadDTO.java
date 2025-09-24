/**
 * 
 */
package co.gov.sdp.nhspdd.common.dto.seguridad;

import lombok.Data;

/**
 * @author Sumset
 *
 */
@Data
public class TipoUsuarioSeguridadDTO {
	private long codigoTipoUsuario;

	private String estado;

	private String nombre;
}
