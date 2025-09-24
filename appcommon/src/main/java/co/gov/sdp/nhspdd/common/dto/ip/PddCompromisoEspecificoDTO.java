package co.gov.sdp.nhspdd.common.dto.ip;

import co.gov.sdp.nhspdd.common.dto.RespuestaDTO;
import lombok.Data;

/**
 * Clase que persiste la tabla PDD_COMPROMISO_ESPECIFICO de la BD
 * @author DANIEL
 * @version 1.0 10/03/2020
 */
@Data
public class PddCompromisoEspecificoDTO extends RespuestaDTO {
	
	private Long idEspecifico;
	
	private String descripcion;
	
	private Long idCompromiso;
	
	private Long idCompromisoEstrategico;
	
	private Long idLsCompromisoEstrategico;
	
	private String nombreCompromisoEstrategico;
	
	private Long idTematica;
	
	private String nombreTematica;
	
	/**
	 * Metodo Constructor que permite inicializar un PddCompromisoEspecificoDTO con su informacion basica
	 * @param idEspecifico Long que representa el identificador del compromiso especifico
	 * @param idCompromiso Long que representa el identificador del pddCompromiso al cual esta relacionado
	 * @param descripcion String que representa la descripcion del compromiso especifico
	 */
	public PddCompromisoEspecificoDTO(Long idEspecifico, Long idCompromiso, String descripcion)
	{
		this.idEspecifico = idEspecifico;
		this.idCompromiso = idCompromiso;
		this.descripcion = descripcion;
	}
	
	/**
	 * Metodo constructor para inicilizar un PddCompromisoEspecificoDTO sin parametros
	 */
	public PddCompromisoEspecificoDTO()
	{	}
}
