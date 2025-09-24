package co.gov.sdp.spdd.data.irepositorio.afs;

import java.io.Serializable;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.gov.sdp.spdd.data.model.afs.Entidad;

/**
 * Interfaz repositorio para manejar la conexion a la base de datos de la
 * entidad Entidad
 *
 * @author Raul Londoño Murillo
 *
 */
@Transactional
@Repository
public interface IEntidadRepo extends CrudRepository<Entidad, String>, Serializable  {
	

}
