package co.gov.sdp.spdd.data.dao.interfaces.afs;

import java.io.Serializable;

import co.gov.sdp.spdd.data.model.afs.Entidad;
import co.gov.sdp.spdd.data.operacionesbasicas.interfaces.IOperacionesBasicasFacade;

/**
 * Interfaz de servicio repositorio para manejar las consultas basicas a la base
 * de datos de la entidad Entidad
 *
 * @author Raul Londoño Murillo
 *
 */
public interface IEntidadServiceRepo extends IOperacionesBasicasFacade<Entidad, String>, Serializable  {

}
