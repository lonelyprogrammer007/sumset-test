package co.gov.sdp.spdd.data.dao.interfaces.afs;

import java.io.Serializable;
import java.util.List;

import co.gov.sdp.spdd.data.model.afs.ProyectosInversionUsuario;
import co.gov.sdp.spdd.data.operacionesbasicas.interfaces.IOperacionesBasicasFacade;

/**
 * Interfaz de servicio repositorio para manejar las consultas basicas a la base
 * de datos de la entidad ProyectosInversionUsuario
 *
 * @author Raul Londoño Murillo
 *
 */
public interface IProyectosInversionUsuarioServiceRepo
        extends IOperacionesBasicasFacade<ProyectosInversionUsuario, Long>, Serializable  {

	/**
	 * 
	 * @param usuario
	 * @return
	 */
	public List<ProyectosInversionUsuario> obtenerPorUsuario(String usuario);

}
