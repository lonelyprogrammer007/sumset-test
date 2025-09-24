package co.gov.sdp.nhspdd.common.dto.ip;

import co.gov.sdp.nhspdd.common.dto.RespuestaDTO;
import lombok.Data;

/**
 * Clase que persiste la tabla PDD_COMPROMISO de la BD.
 * Tener en cuenta que este DTO hace referencia a una tabl intermedia entre dos tablas (Pdd y CompromisoEstrategico) que
 * tienen una relacion de muchos a muchos, este DTO se va a utilizar para tener la informacion necesaria para crear el arbol
 * que se necesita en la pantalla de consultar compromisos.
 * @author DANIEL
 * @version 1.0 11/03/2020
 */
@Data
public class PddCompromisoDTO extends RespuestaDTO {
	
	private Long idCompromiso;
	
	private Long idEstrategico;
	
	private Long idPlanDesarrollo;
	
	
	private String nombrePlan;
	
	private Long idTematica;
	
	private String nombreTematica;
	
	//corresponde al identificador de la lista simple que contiene la informacion del compromiso estrategico
	private Long idLsEstrategico;
	
	//corresponde al campo resultado de la lista simple que contiene la informacion del compromiso estrategico
	private String nombreCompromisoEstrategico;
	
	/**
	 * Metodo constructor que permite inicializar un PddCompromisoDTO con su informacion basicos
	 * @param idCompromiso Long que representa el identificador del compromiso
	 * @param idEstrategico Long que representa el identificador del compromisoEstrategico al cual esta relacionado
	 * @param idPlanDesarrollo Long que representa el identificador del plan de desarrollo distrital al cual esta relacionado
	 */
	public PddCompromisoDTO(Long idCompromiso, Long idEstrategico, Long idPlanDesarrollo)
	{
		this.idCompromiso = idCompromiso;
		this.idEstrategico = idEstrategico;
		this.idPlanDesarrollo = idPlanDesarrollo;
	}
	
	/**
	 * Metodo constructor que permite inicalizar un PddCompromisoDTO sin parametros
	 */
	public PddCompromisoDTO()
	{	}
}
