package co.gov.sdp.spdd.data.irepositorio.afs;

import java.io.Serializable;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.gov.sdp.spdd.data.model.afs.EstadoServicio;

/**
 * 
 * @author sumset
 *
 */
@Transactional
@Repository
public interface IEstadoServicioRepo extends CrudRepository<EstadoServicio, Long>, Serializable  {

}
