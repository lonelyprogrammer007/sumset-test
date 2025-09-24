package co.gov.sdp.spdd.data.irepositorio.bp;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.gov.sdp.spdd.data.model.bp.BpProyInvAporte;
import co.gov.sdp.spdd.data.model.bp.BpProyInvFinancia;

/**
 * Interface que contiene los metodos basicos de CrudRepository y otros para
 * hacer transacciones relacionadas con la tabla BpProyInvFinancia de la BD
 * 
 * @author Jefferson Arenas
 * @version 1.0 02/06/2020
 *  
 */
@Transactional
@Repository
public interface IBpProyInvFinanciaRepo extends CrudRepository<BpProyInvFinancia, Long>{


	/**
	 * Metodo que permite obtener los BpProyInvAporte por el identificador del proyecto
	 * @param idProyInversion Long que representa el identificador del proyecto
	 * @return una lista de tipo BpProyInvFinancia con la informacion correspondiente
	 */
	@Query("SELECT pif FROM BpProyInvFinancia pif WHERE  pif.idProyInversion.idProyInversion=:proyecto")
	public Page<BpProyInvFinancia> obtenerPorIdTodosProyInvFiancia(@Param("proyecto") Long idProyInversion, Pageable paginador);
	
	
	
	@Query("SELECT pif FROM BpProyInvFinancia pif WHERE pif.idProyInversion.idProyInversion=:idProyectoInv AND pif.idLsFuente.idArgumento=:idLsFUente")
	public BpProyInvFinancia consultarProyInvFinanciaPorIdLSFuYIdProy(@Param("idLsFUente") Long idLsFUente,@Param("idProyectoInv") Long idProyectoInv);

	@Query("SELECT pif FROM BpProyInvFinancia pif WHERE pif.idFuente=:idFUente")
	public BpProyInvFinancia consultarProyInvFinanciaPorId(@Param("idFUente") Long idFUente);

}
