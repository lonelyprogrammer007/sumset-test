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
public class EntidadSeguridadDTO {
	private String codigoEntidad;

	private String codigoSector;

	private SectorSeguridadDTO sector;

	private String descripcion;

	private String estado;

	private String nombre;

	private Integer delegar;

	private String dominioCorreo;

	private String sigla;

	
	private String codigoDistrital;
}
