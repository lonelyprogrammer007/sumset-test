/**
 * 
 */
package co.gov.sdp.spdd.core.iservice.buzon.notificacion;

import com.fasterxml.jackson.core.JsonProcessingException;

import co.gov.sdp.nhspdd.common.dto.BuzonNotificacionesDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;

/**
 * @author Sumset
 *
 */
public interface IBuzonNotificacionRegistrar {

	/**
	 * 
	 * @param buzonNotificacionesDTO
	 * @return
	 * @throws SpddExceptions
	 * @throws JsonProcessingException 
	 */
	public BuzonNotificacionesDTO registarBuzonNotificacion(BuzonNotificacionesDTO buzonNotificacionesDTO)
			throws SpddExceptions, JsonProcessingException;
}
