package co.gov.sdp.spdd.data.irepositorio.afs;

import java.io.Serializable;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.spdd.data.model.afs.BuzonNotificaciones;

/**
 * Interface repositorio de la clase buzonNotificaciones
 *
 * @author Bryan Munoz
 *
 */
@Transactional
@Repository
public interface IBuzonNotificacionesRepo extends CrudRepository<BuzonNotificaciones, Long>, Serializable {

	/**
	 * 
	 * @param usuario
	 * @return
	 * @throws SpddExceptions
	 */
	@Query("SELECT c FROM BuzonNotificaciones c where c.codigoUsuarioDestino = :usuario")
	public List<BuzonNotificaciones> obtenerPorUsuario(@Param("usuario") String usuario) throws SpddExceptions;

	/**
	 * 
	 * @param usuario
	 * @param estado
	 * @return
	 * @throws SpddExceptions
	 */
	@Query("SELECT count(c) FROM BuzonNotificaciones c where c.codigoUsuarioDestino = :usuario AND c.estado = :estado")
	public Long notificacionesPorUsuario(@Param("usuario") String usuario, @Param("estado") int estado)
			throws SpddExceptions;

	/**
	 * 
	 * @param usuario
	 * @return
	 */
	@Query("SELECT c FROM BuzonNotificaciones c where c.codigoUsuarioDestino = :usuario AND c.estado = 0 AND c.idConfigNotificacion= -1")
	public List<BuzonNotificaciones> leerNotificacionesInformativas(@Param("usuario") String usuario);
}
