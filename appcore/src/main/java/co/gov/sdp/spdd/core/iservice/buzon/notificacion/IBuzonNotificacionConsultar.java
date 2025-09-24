package co.gov.sdp.spdd.core.iservice.buzon.notificacion;

import co.gov.sdp.nhspdd.common.dto.BuzonNotificacionesDTO;
import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;

/**
 * Interface que maneja los metodos de consultar asociados con
 * buzonNotificaciones
 *
 * @author Bryan Munoz
 *
 */
public interface IBuzonNotificacionConsultar {

    /**
     * Metodo que obtiene todos los mensajes por admin
     *
     * @return una lista dto con un codigo y mensaje de respuesta
     */
    public GenericoDTO obtenerTodosAdmin() throws SpddExceptions;

    /**
     * Metodo que obtiene los mensajes de cada uno de los usuarios de la
     * plataforma
     *
     * @return una lista dto con un codigo y mensaje de respuesta
     */
    public GenericoDTO obtenerTodosPorUsuario(String usuario) throws SpddExceptions;
    
    /**
     * 
     * @param buzonNotificacionesDTO
     * @return
     * @throws SpddExceptions
     */
    public Long notificacionesPorUsuario(BuzonNotificacionesDTO buzonNotificacionesDTO) throws SpddExceptions;

    /**
     * 
     * @param peticion
     * @return
     * @throws SpddExceptions
     */
	public GenericoDTO obtenerPaginado(BuzonNotificacionesDTO peticion) throws SpddExceptions;

}
