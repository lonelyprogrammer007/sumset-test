package co.gov.sdp.spdd.core.iservice.buzon.notificacion;

import co.gov.sdp.nhspdd.common.dto.BuzonNotificacionesDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;

/**
 * Interface que tiene los metodos asociados con modificar de
 * BuzonNotificaciones
 *
 * @author Bryan Munoz
 *
 */
public interface IBuzonNotificacionModificar {

	/**
	 * Metodo que permite dar la respuesta y cambiar el estado de un mensaje de
	 * pendiente a atendido.
	 *
	 * @param peticion campos para validar respuesta y cambio de estado
	 * @return un objeto dto con un codigo y mensaje de respuesta
	 */
	public BuzonNotificacionesDTO darRespuesta(BuzonNotificacionesDTO peticion) throws SpddExceptions;

	public BuzonNotificacionesDTO leerInformativos(String usuario) throws SpddExceptions;
}
