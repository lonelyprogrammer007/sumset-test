package co.gov.sdp.spdd.data.irepositorio.afs;

import java.io.Serializable;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.gov.sdp.spdd.data.model.afs.EstructuraMeta;

/**
 * 
 * @author sumset
 *
 */
@Transactional
@Repository
public interface IEstructuraMetaRepo extends CrudRepository<EstructuraMeta, Long>, Serializable  {
	
	@Query("SELECT em FROM EstructuraMeta em WHERE em.idLsUnidadMedida.idArgumento= :unidadMedida AND em.idLsVerbo.idArgumento= :verbo")
	public EstructuraMeta validarUnidadMedidaYVerbo(@Param("unidadMedida")Long unidadMedida, @Param("verbo") Long verbo);

}
