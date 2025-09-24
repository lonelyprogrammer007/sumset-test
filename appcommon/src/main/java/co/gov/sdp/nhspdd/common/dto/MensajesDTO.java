/**
 * 
 */
package co.gov.sdp.nhspdd.common.dto;

import lombok.Data;

/**
 * @author Sumset
 *
 */
@Data
public class MensajesDTO {
	
	private String usuario;
    
	private Long notificaciones;
    
	/**
	 * @param usuario
	 * @param notificaciones
	 */
	public MensajesDTO(String usuario, Long notificaciones) {
		super();
		this.usuario = usuario;
		this.notificaciones = notificaciones;
	}

	/**
	 * 
	 */
	public MensajesDTO() {
		super();
	}
}
