package co.gov.sdp.spdd.data.irepositorio.bp;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.gov.sdp.spdd.data.model.bp.BpProyInvIniciativa;
import co.gov.sdp.spdd.data.model.bp.BpProyInvLocaliza;

/**
 * 
 * @author SEBASTIAN
 *
 */
@Transactional
@Repository
public interface IBpProyInvLocalizaRepo extends CrudRepository<BpProyInvLocaliza, Long>{

	/**
	 * Metodo que permite obtener todas las relaciones entre territorializacion y proyecto de inversion segun
	 * el identificador del proyecto de inversion pasado como parametro
	 * @param idProyecto Long que representa el identificador del proyecto de inversion por el cual se va a filtra
	 * @return una lista de BpProyInvLocaliza
	 */
	@Query("SELECT pi FROM BpProyInvLocaliza pi WHERE pi.idProyInversion.idProyInversion = :idProyecto")
	public List<BpProyInvLocaliza> obtenerTodosPorIdProyInversion(@Param("idProyecto") Long idProyecto);
}
