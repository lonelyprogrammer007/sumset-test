package co.gov.sdp.spdd.data.irepositorio.ip;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.gov.sdp.spdd.data.model.ip.PddPrbIndicador;

/**
 * 
 * @author Bryan Muñoz
 *
 */
@Repository
public interface IPddPrbIndicadorRepo extends CrudRepository<PddPrbIndicador, Long> {
	@Query("SELECT pi FROM PddPrbIndicador pi WHERE pi.idProblematica.idProblematica= :id")
	List<PddPrbIndicador> buscarPorProblematica(@Param("id") Long idProblematica);

	@Query("SELECT pi FROM PddPrbIndicador pi WHERE pi.idProblematica.idProblematica= :idProblematica AND idIndicador.idIndicador= :indicador")
	PddPrbIndicador buscarPorIndicadorYProblematica(@Param("idProblematica") Long idIndicador,
			@Param("indicador") Long idProblematica);
}
