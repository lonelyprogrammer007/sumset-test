package co.gov.sdp.spdd.data.dao.interfaces.afs;

import java.io.Serializable;
import java.util.List;

import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.spdd.data.model.afs.ProyectoInversion;
import co.gov.sdp.spdd.data.operacionesbasicas.interfaces.IOperacionesBasicasFacade;

/**
 * Interfaz de servicio repositorio para manejar las consultas basicas a la base
 * de datos de la entidad ProyectoInversion
 *
 * @author Raul Londono Murillo
 *
 */
public interface IProyectoInversionServiceRepo extends IOperacionesBasicasFacade<ProyectoInversion, Long>, Serializable  {

	/**
	 * 
	 * @return
	 */
	public List<ProyectoInversion> proyectoInversionObtenerDisponibles() throws SpddExceptions;
}
