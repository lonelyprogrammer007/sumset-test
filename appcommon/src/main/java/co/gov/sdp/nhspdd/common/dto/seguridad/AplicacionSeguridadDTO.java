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
public class AplicacionSeguridadDTO {
	private String codigoAplicacion;

    private String estado;

    private String nombre;

    private String codigoEntidad;

    private String urlAplicacion;
}
