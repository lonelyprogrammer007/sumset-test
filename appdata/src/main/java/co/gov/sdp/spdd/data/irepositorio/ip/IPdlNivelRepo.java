package co.gov.sdp.spdd.data.irepositorio.ip;

import java.io.Serializable;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.gov.sdp.spdd.data.model.ip.PddNivel;
import co.gov.sdp.spdd.data.model.ip.PdlNivel;

/**
 * Interface que contiene los metodos basicos de CrudRepository y otros para
 * hacer transacciones relacionadas con la tabla PdlNivel de la BD
 * 
 * @author SEBASTIAN
 * @version 1.0 22/04/2020
 */
@Transactional
@Repository
public interface IPdlNivelRepo extends CrudRepository<PdlNivel, Long>, Serializable {
	
	/**
	 * Metodo que permite consultar los niveles correspondientes a un plan de desarrollo local
	 * @param idPlanLocal Long que representa el identificador del plan de desarrollo local del cual se quieren
	 * obtener los niveles
	 * @return una lista de PdlNivel que contiene todos los niveles correspondientes al plan de desarrollo local.
	 */
	@Query("SELECT p FROM PdlNivel p WHERE p.idPlanLocal.idPlanLocal = :idPlan ORDER BY p.codNivel ASC")
	public List<PdlNivel> obtenerPdlNivelPorIdPlanLocal(@Param("idPlan") Long idPlanLocal);
	
	/**
	 * Metodo que permite consultar el nivel correspondiente a un codigo de nivel y a un plan de desarrollo local en especifico
	 * @param nivel Long que representa el nivel que se desea buscar (1,2,3...)
	 * @param idPlanLocal Long que representa el identificador del plan que se desea buscar
	 * @return un objeto de tipo PdlNivel con la informacion del nivel o null en caso contrario.
	 */
	@Query("SELECT p FROM PdlNivel p WHERE p.codNivel = :codNivel and p.idPlanLocal.idPlanLocal = :idPlan")
	public PdlNivel obtenerPdlNivelPorNivelYIdPlanLocal(@Param("codNivel") Long nivel, @Param("idPlan") Long idPlanLocal);

}
