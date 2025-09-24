package co.gov.sdp.nhspdd.common.dto;

import lombok.Data;

/**
 * The persistent class for the SEG_ENTIDAD_HIS_SECTORIAL database table.
 *
 */
@Data
public class HistorialSectorialDTO extends RespuestaDTO {

	private String codigoHisSectorial;

	private String nombre;

	private String actoAdministrativo;

	private String activo;

	private String fechaInicio;

	private String fechaFin;

	private String codigoEntidad;
	
	//nombre del sector
	private String descripcion;
	
	private String codigoDistrital;

}
