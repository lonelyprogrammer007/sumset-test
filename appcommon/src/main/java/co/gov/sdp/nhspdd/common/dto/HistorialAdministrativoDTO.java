package co.gov.sdp.nhspdd.common.dto;

import lombok.Data;

/**
 * The persistent class for the SEG_ENTIDAD_HIS_ADMINISTRATIVO database table.
 *
 */
@Data
public class HistorialAdministrativoDTO extends RespuestaDTO {
	
	/**
	 * Atributo que representa el codigo del historial administrativo
	 */
	private String codigoHisAdmin;
	
	/**
	 * Atributo que representa el nombre
	 */
	private String nombre;
	
	/**
	 * Atributo que representa la sigla
	 */
	private String sigla;
	
	/**
	 * Atributo que representa la fecha de inicio
	 */
	private String fechaInicio;
	
	/**
	 * Atributo que representa la fecha final 
	 */
	private String fechaFinal;
	
	/**
	 * Atributo que representa el acto administrativo
	 */
	private String actoAdministrativo;
	
	/**
	 * Atributo que representa el codigo de la entidad
	 */
	private String codigoEntidad;

	/**
	 * Constructor
	 */
	public HistorialAdministrativoDTO() {
		super();
	}

}
