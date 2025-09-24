package co.gov.sdp.spdd.data.irepositorio.bp;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import co.gov.sdp.spdd.data.model.bp.BpIniciativaCondicion;

/**
 * 
 * @author Bryan Muñoz
 *
 */
public interface IBpIniciativaCondicionRepo extends CrudRepository<BpIniciativaCondicion, Long> {

	@Query("SELECT ic FROM BpIniciativaCondicion ic WHERE ic.idIniciativa.idIniciativa= :idIniciativa")
	List<BpIniciativaCondicion> obtenerCondicionPorIniciativa(@Param("idIniciativa") Long idIniciativa);
}
