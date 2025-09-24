package co.gov.sdp.spdd.data.irepositorio.ip;

import java.io.Serializable;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.spdd.data.model.ip.PddCompromiso;

/**
 * Interface que contiene los metodos basicos de CrudRepository y otros para
 * hacer transacciones relacionadas con la tabla PDDCOMPROMISO de la BD
 * 
 * @author DANIEL
 * @version 1.0 25/02/2020
 */
@Transactional
@Repository
public interface IPddCompromisoRepo extends CrudRepository<PddCompromiso, Long>, Serializable {
	
	/**
	 * Metodo que permite consultar los pddCompromiso relacionados a un plan de desarrollo
	 * @param idPlan Long que representa el identificador del plan de desarrollo por el cual se va a buscar
	 * @return una lista de tipo PddCompromiso correspondiente a la busqueda o null
	 */
	@Query("SELECT pc FROM PddCompromiso pc WHERE pc.idPlanDesarrollo.idPlanDesarrollo= :idPlanDesarrollo")
	public List<PddCompromiso> obtenerPorIdPlanDesarrollo(@Param("idPlanDesarrollo") Long idPlan);
	
	/**
	 * Metodo que permite consultar un PddCompromiso en la BD relacionado a un compromisoEstrategico y a un plan de desarrollo
	 * @param idEstrategico Long que represnta el identificador del compromisoEstrategico por el cual se va a buscar
	 * @param idPlan Long que representa el identiricador del plan de desarrollo por el cual se va a buscar
	 * @return un objeto de tipo PddCompromiso correspondiente a la informacion o null
	 */
	@Query("SELECT pc FROM PddCompromiso pc WHERE pc.idEstrategico.idCompromiso = :idEstrategico AND pc.idPlanDesarrollo.idPlanDesarrollo= :idPlanDesarrollo")
	public PddCompromiso obtenerPorIdEstrategicoYIdPlanDesarrollo(@Param("idEstrategico") Long idEstrategico, @Param("idPlanDesarrollo") Long idPlan);
	
	/**
	 * Metodo que retorna compromiso por Id
	 * 
	 * @param idCompromiso
	 * @return
	 * @throws SpddExceptions
	 */
	@Query("SELECT pc FROM PddCompromiso pc WHERE pc.idCompromiso = :idCompromiso")
	public PddCompromiso obtenerPorId(@Param("idCompromiso")Long idCompromiso) throws SpddExceptions;
	
}
