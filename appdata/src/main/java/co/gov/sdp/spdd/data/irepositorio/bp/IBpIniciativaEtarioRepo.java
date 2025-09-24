package co.gov.sdp.spdd.data.irepositorio.bp;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import co.gov.sdp.spdd.data.model.bp.BpIniciativaEtario;

public interface IBpIniciativaEtarioRepo extends CrudRepository<BpIniciativaEtario, Long> {

	@Query("SELECT e FROM BpIniciativaEtario e WHERE e.idIniciativa.idIniciativa= :idIniciativa")
	public List<BpIniciativaEtario> obtenerGrupoEtarioPorIniciativa(@Param("idIniciativa") Long idIniciativa);
}
