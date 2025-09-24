package co.gov.sdp.spdd.data.irepositorio.bp;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.gov.sdp.spdd.data.model.bp.BpProyInvIniciativa;

/**
 * Interface que contiene los metodos basicos de CrudRepository y otros para
 * hacer transacciones relacionadas con la tabla BpProyInvIniciativa de la BD
 * 
 * @author DANIEL
 * @version 1.0 14/05/2020
 */
@Transactional
@Repository
public interface IBpProyInvIniciativaRepo extends CrudRepository<BpProyInvIniciativa, Long> {
	
	/**
	 * Metodo que permite obtener todas las relaciones entre iniciativa ciudadana y proyecto de inversion segun
	 * el identificador del proyecto de inversion pasado como parametro
	 * @param idProyecto Long que representa el identificador del proyecto de inversion por el cual se va a filtra
	 * @return una lista de BpProyInvIniciativa
	 */
	@Query("SELECT pii FROM BpProyInvIniciativa pii WHERE pii.idProyInversion.idProyInversion = :idProyecto")
	public List<BpProyInvIniciativa> obtenerTodosPorIdProyInversion(@Param("idProyecto") Long idProyecto);
	
	/**
	 * Metodo que permite obtener todas una relacione entre iniciativa ciudadana y proyecto de inversion segun
	 * el identificador del proyecto de inversion y el identificador de la iniciativa pasadas como parametro
	 * @param idProyecto Long que representa el identificador del proyecto de inversion por el cual se va a filtra
	 * @param idInciativa Long que representa el identificador de la iniciativa ciudadana por el cual se va a filtar
	 * @return un objeto de tipo sBpProyInvIniciativa
	 */
	@Query("SELECT pii FROM BpProyInvIniciativa pii WHERE pii.idIniciativa.idIniciativa = :iniciativa AND pii.idProyInversion.idProyInversion = :proyecto")
	public BpProyInvIniciativa obtenerPorIdIniciativaYIdProyInversion(@Param("iniciativa") Long idIniciativa, @Param("proyecto") Long idProyecto);

	/**
	 * Metodo que permite obtener todas las realaciones filtradas por iniciativa ciudadana
	 * @param idIniciativa Long que representa el identificador de la iniciativa por la cual se quiere filtrar
	 * @return una lista de BpProyInvIniciativa con la informacion correspondiente
	 */
	@Query("SELECT pii FROM BpProyInvIniciativa pii WHERE pii.idIniciativa.idIniciativa = :iniciativa")
	public List<BpProyInvIniciativa> obtenerTodosPorIdIniciativa(@Param("iniciativa") Long idIniciativa);

}
