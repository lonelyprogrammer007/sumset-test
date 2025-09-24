package co.gov.sdp.spdd.data.irepositorio.ip;

import java.io.Serializable;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.gov.sdp.spdd.data.model.ip.PddNivel;

/**
 * Interface que contiene los metodos basicos de CrudRepository y otros para
 * hacer transacciones relacionadas con la tabla PddNivel de la BD
 * 
 * @author DANIEL
 * @version 1.0 03/03/2020
 */
@Transactional
@Repository
public interface IPddNivelRepo extends CrudRepository<PddNivel, Long>, Serializable {
	
	/**
	 * Metodo que permite consultar los niveles correspondientes a un plan de desarrollo
	 * @param idPlanDesarrollo Long que representa el identificador del plan de desarrollo del cual se quieren
	 * obtener los niveles
	 * @return una lista de PddNivel que contiene todos los niveles correspondientes al plan de desarrollo.
	 */
	@Query("SELECT p FROM PddNivel p WHERE p.idPlanDesarrollo.idPlanDesarrollo = :idPlan ORDER BY p.codNivel ASC")
	public List<PddNivel> obtenerPddNivelPorIdPlanDesarrollo(@Param("idPlan") Long idPlanDesarrollo);
	
	/**
	 * Metodo que permite consultar el nivel correspondiente a un codigo de nivel y a un plan de desarrollo en especifico
	 * @param nivel Long que representa el nivel que se desea buscar (1,2,3...)
	 * @param idPlanDesarrollo Long que representa el identificador del plan que se desea buscar
	 * @return un objeto de tipo PddNivel con la informacion del nivel o null en caso contrario.
	 */
	@Query("SELECT p FROM PddNivel p WHERE p.codNivel = :codNivel and p.idPlanDesarrollo.idPlanDesarrollo = :idPlan")
	public PddNivel obtenerPddNivelPorNivelYIdPlanDesarrollo(@Param("codNivel") Long nivel, @Param("idPlan") Long idPlanDesarrollo);
	
	/**
	 * Metodo que permite consultar el nivel correspondiente a un codigo de nivel y a un plan de desarrollo en especifico que esten desbalanceados
	 * @param nivel Long que representa el nivel que se desea buscar (1,2,3...)
	 * @param idPlanDesarrollo Long que representa el identificador del plan que se desea buscar
	 * @return un objeto de tipo PddNivel con la informacion del nivel o null en caso contrario.
	 */
	@Query("SELECT p FROM PddNivel p WHERE p.codNivel = :codNivel AND p.idPlanDesarrollo.idPlanDesarrollo = :idPlan AND p.sumPond <> 100")
	public List<PddNivel> obtenerPddNivelDesbalanceadosPorNivelYIdPlanDesarrollo(@Param("codNivel") Long nivel, @Param("idPlan") Long idPlanDesarrollo);


}
