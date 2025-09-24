package co.gov.sdp.spdd.core.icontroller.buzon.notificacion;

import co.gov.sdp.nhspdd.common.dto.BuzonNotificacionesDTO;
import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;

/**
 * Interface que maneja los metodos del controlador buzonNotificaciones
 *
 * @author Bryan Munoz
 *
 */
public interface IBuzonNotificacionController {

	/**
	 * Metodo que direcciona para obtener todos los mensajes con estado que requiera
	 * atencion
	 *
	 * @return una lista tipo genericoDTO con un codigo y mensaje de respuesta
	 */
	public GenericoDTO obtenerTodosAdmin(BuzonNotificacionesDTO peticion) throws SpddExceptions;

	/**
	 * Metodo que direcciona para obtener los mensajes por usuario
	 *
	 * @param usuario usuario que desea obtener mensajes
	 * @return una lista tipo GenericoDTO con un codigo y mensaje de respuesta
	 */
	public GenericoDTO obtenerPorUsuario(String usuario) throws SpddExceptions;

	/**
	 * Metodo que responde un mensaje del buzon
	 *
	 * @param peticion trae el id con el usuario para responder el mensaje
	 * @return un objeto tipo dto con un codigo y mensaje de respuesta
	 * @throws SpddExceptions
	 */
	public BuzonNotificacionesDTO darRespuesta(BuzonNotificacionesDTO peticion) throws SpddExceptions;

	/**
	 * 
	 * @param peticion
	 * @return
	 * @throws SpddExceptions
	 */
	public BuzonNotificacionesDTO registrarBuzonNotificacion(BuzonNotificacionesDTO peticion) throws SpddExceptions;

	/**
	 * 
	 * @param usuario
	 * @return
	 */
	public BuzonNotificacionesDTO leerInformativo(String usuario);

	/**
	 * 
	 * @param peticion
	 * @return
	 */
	public Long leerNotificacioenes(BuzonNotificacionesDTO peticion);
}
