
package co.gov.sdp.nhspdd.common.dto.ip;

import co.gov.sdp.nhspdd.common.dto.RespuestaDTO;
import lombok.Data;

/**
 * Clase que persiste la tabla COMPROMISO_ESTRATEGICO de la BD
 * @author DANIEL
 * @version 1.0 25/02/2020
 */
@Data
public class CompromisoEstrategicoDTO extends RespuestaDTO {
	
	private Long idCompromiso;
	
	private Long idTematica;
	
	private String nombreTematica;
	
	private Long idCompromisoEstrategico;
	
	private String nombreCompromisoEstrategico;	
	
	private Integer estado;		

	private String stringEstado;
	
	/**
	 * Metodo constructor que permite la inicializacion de un CompromisoEstrategico con los datos basicos
	 * @param idCompromiso Long que representa el identificador del compromiso estrategico
	 * @param idTematica Long que representa el identificador del argumento de lista simple que contiene la informacion de la tematica
	 * @param idCompromisoEstrategico Long que representa el identificador del argumento de lista simple que contiene la informacion del compromiso estrategico
	 * @param estado Integer que representa el estado del compromiso estrategico (1-Activo,0-Inactivo)
	 */
	public CompromisoEstrategicoDTO(Long idCompromiso, Long idTematica, Long idCompromisoEstrategico, Integer estado)
	{
		this.idCompromiso = idCompromiso;
		this.idTematica = idTematica;
		this.idCompromisoEstrategico = idCompromisoEstrategico;
		this.estado = estado;
	}
	
	/**
	 * Metodo constructor que permite la inicializacion de un compromiso sin ningun parametro.
	 */
	public CompromisoEstrategicoDTO()
	{
	}


}
