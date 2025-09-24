package co.gov.sdp.nhspdd.common.dto;

import lombok.Data;

/**
 * The persistent class for the SPDD_FUNCIONARIO_CLAVE_ENTIDAD database table.
 * 
 */
@Data
public class FuncionarioClaveEntidadDTO extends RespuestaDTO {
	
	/**
	 * Atributo que representa el codigo del id funcionario entidad
	 */
	private Long idFuncionarioEntidad;
	
	/**
	 * Atributo que representa el codigo entidad
	 */
	private String codigoEntidad;
	
	/**
	 * Atributo que representa el nombre
	 */
	private String nombre;
	
	/**
	 * Atributo que representa id ls funcion
	 */
	private Long idLsFuncion;
	
	/**
	 * 
	 */
	private String nombreIdLsFuncion;
	
	/**
	 * Atributo que representa id ls genero
	 */
	private Long idLsGenero;
	
	/**
	 * 
	 */
	private String nombreIdLsGenero;
	
	/**
	 * Atributo que representa el cargo
	 */
	private String cargo;
	
	/**
	 * Atributo que representa el correo
	 */
	private String correo;
	
}
