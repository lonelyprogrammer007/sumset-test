package co.gov.sdp.spdd.data.irepositorio.bp;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.spdd.data.model.bp.BpProyInvEtnico;
import co.gov.sdp.spdd.data.model.bp.BpProyInvPoblacion;

/**
 * Interface que contiene los metodos basicos de PagingAndSortingRepository y
 * otros para hacer transacciones relacionadas con la tabla BpProyInvEtnicode la
 * BD
 * 
 * @author LUIS FIGUEROA
 * @version 1.0 02/06/2020
 */
@Transactional
@Repository
public interface IBpProyInvEtnicoRepo extends PagingAndSortingRepository<BpProyInvEtnico, Long> {

	/**
	 * Metodo que permite obtener la lista de entidades del tipo "BpProyInvEtnico"
	 * asociados a una entidad del tipo "BpProyectoInversion" y a un
	 * "BpProyInvPoblacion"
	 * 
	 * @param idProyectoInversion Long que representa el identificador del proyecto
	 *                            de inversion asociado
	 * @param idPoblacion         Long que representa el identificador del proyecto
	 *                            de inversio poblacion asociado
	 * @return Una lista de BpProyInvEtnico con todos los registros correspondientes
	 */
	@Query("SELECT pi FROM BpProyInvEtnico pi WHERE pi.idPoblacion.idPoblacion=:idPoblacion")
	public Page<BpProyInvEtnico> obtenerTodosPorIdProyectoInversion(@Param("idPoblacion") Long idPoblacion,
			Pageable paginador) throws SpddExceptions;

	@Query("SELECT pi FROM BpProyInvEtnico pi WHERE pi.idLsEtnico.idArgumento=:idLsEtnico AND pi.idPoblacion.idPoblacion=:idPoblacion")
	public BpProyInvEtnico obtenerPorIdLsEtnicoYIdPoblacion(@Param("idLsEtnico") Long idLsEtnico,
			@Param("idPoblacion") Long idPoblacion);
}
