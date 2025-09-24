package co.gov.sdp.spdd.data.irepositorio.bp;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import co.gov.sdp.spdd.data.model.bp.BpIniciativaUbicacion;

/**
 * 
 * @author Bryan Muñoz
 *
 */
public interface IBpIniciativaUbicacionRepo extends CrudRepository<BpIniciativaUbicacion, Long> {

	@Query("SELECT u FROM BpIniciativaUbicacion u WHERE u.idIniciativa.idIniciativa= :idIniciativa")
	List<BpIniciativaUbicacion> obtenerUbicacionesPorIniciativas(@Param("idIniciativa") Long idIniciativa);
}
