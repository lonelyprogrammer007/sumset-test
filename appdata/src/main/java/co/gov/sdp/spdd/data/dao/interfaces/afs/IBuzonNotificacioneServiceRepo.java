package co.gov.sdp.spdd.data.dao.interfaces.afs;

import java.io.Serializable;
import java.util.List;

import co.gov.sdp.nhspdd.common.dto.BuzonNotificacionesDTO;
import co.gov.sdp.nhspdd.common.dto.FiltroDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.spdd.data.model.afs.BuzonNotificaciones;
import co.gov.sdp.spdd.data.operacionesbasicas.interfaces.IOperacionesBasicasFacade;

/**
 * 
 * @author Sumset
 *
 */
public interface IBuzonNotificacioneServiceRepo
		extends IOperacionesBasicasFacade<BuzonNotificaciones, Long>, Serializable {

	/**
	 * 
	 * @param usuario
	 * @return
	 * @throws SpddExceptions
	 */
	public List<BuzonNotificaciones> obtenerBuzonPorUsuario(String usuario) throws SpddExceptions;

	/**
	 * 
	 * @param usuario
	 * @param estado
	 * @return
	 * @throws SpddExceptions
	 */
	public Long notificacionesPorUsuario(String usuario, int estado) throws SpddExceptions;

	/**
	 * 
	 * @param buzonNotificacionesDTO
	 * @param inicio
	 * @param limite
	 * @return
	 * @throws SpddExceptions
	 */
	public FiltroDTO filtrarPorCampo(BuzonNotificacionesDTO buzonNotificacionesDTO, Long inicio, Integer limite)
			throws SpddExceptions;

	/**
	 * 
	 * @param usuario
	 * @return
	 * @throws SpddExceptions
	 */
	public List<BuzonNotificaciones> leerInformativos(String usuario) throws SpddExceptions;
}
