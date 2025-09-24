package co.gov.sdp.spdd.data.irepositorio.afs;

import java.io.Serializable;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.gov.sdp.spdd.data.model.afs.Consecutivo;

/**
 * 
 * @author sumset
 *
 */
@Transactional
@Repository
public interface IConsecutivoRepo extends CrudRepository<Consecutivo, Long>, Serializable {

	@Query("SELECT c From Consecutivo c WHERE c.idPlanDesarrollo.idPlanDesarrollo= :idPlan AND c.codigoEntidad.codigoEntidad= :ent AND c.nombre= :nombre")
	public List<Consecutivo> obtenerConsecutivo(@Param("idPlan") Long idPlan, @Param("ent") String entidad,
			@Param("nombre") String nombre);
}
