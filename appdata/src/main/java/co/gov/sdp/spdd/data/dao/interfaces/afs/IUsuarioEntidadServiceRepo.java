package co.gov.sdp.spdd.data.dao.interfaces.afs;

import java.io.Serializable;
import java.util.List;

import co.gov.sdp.nhspdd.common.dto.FiltroDTO;
import co.gov.sdp.nhspdd.common.dto.UsuarioEntidadDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.spdd.data.model.afs.UsuarioEntidad;
import co.gov.sdp.spdd.data.operacionesbasicas.interfaces.IOperacionesBasicasFacade;

/**
 * Interfaz de servicio repositorio para manejar las consultas basicas a la base
 * de datos de la entidad UsuarioEntidad
 *
 * @author Raul Londoño Murillo
 *
 */
public interface IUsuarioEntidadServiceRepo extends IOperacionesBasicasFacade<UsuarioEntidad, Long>, Serializable  {
	
	/**
	 * 
	 * @param usuarioEntidadDTO
	 * @param inicio
	 * @param limite
	 * @return
	 * @throws SpddExceptions
	 */
	public FiltroDTO filtrarPorCampo(UsuarioEntidadDTO usuarioEntidadDTO, Long inicio, Integer limite)
			throws SpddExceptions;
	
	/**
	 * 
	 * @param usuario
	 * @return
	 */
	public List<UsuarioEntidad> obtenerPorUsuario(String usuario);

}
