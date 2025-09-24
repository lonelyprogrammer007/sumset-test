package co.gov.sdp.nhspdd.common.dto.bp;

import java.io.Serializable;

import co.gov.sdp.nhspdd.common.dto.RespuestaDTO;
import lombok.Data;

/**
 * Clase que representa el DTO de la entidad BpAporteCiudadano
 * @author DANIEL
 * @version 1.0 16/04/2020
 */
@Data
public class BpAporteCiudadanoDTO extends RespuestaDTO implements Serializable {

	private Long idAporte;
	
	private Long consecutivo;
	
	private String accion;
	
	private String fuente;
	
	private Long idArchivo;
	
	private String stringArchivo;
	
	private Long idNivelAtributoPdd;
	
	private String stringNivelAtributoPdd;
	
	private Long idPddNivel;
	
	private String stringPddNivel;
	
	private Long numeroPddNivel;
	
	//se utiliza en el agregar BpAporteCiudadano por modal ya que se le tiene que asignar al proyecto inversion actual
	private Long idProyInversion;
	
	private Long idNivelAtributoPddOpcion1;
	
	private String stringNivelAtributoPddOpcion1;
	
	private Long idNivelAtributoPddOpcion2;
	
	private String stringNivelAtributoPddOpcion2;
	
	private Long idNivelAtributoPddOpcion3;
	
	private String stringNivelAtributoPddOpcion3;
	
	
}
