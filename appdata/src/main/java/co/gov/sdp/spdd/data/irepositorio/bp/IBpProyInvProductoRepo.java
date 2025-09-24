package co.gov.sdp.spdd.data.irepositorio.bp;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.gov.sdp.spdd.data.model.bp.BpProyInvMetaPlan;
import co.gov.sdp.spdd.data.model.bp.BpProyInvProducto;

/**
 * Interface que contiene los metodos basicos de CrudRepository y otros para
 * hacer transacciones relacionadas con la tabla BpProyInvProducto de la BD
 * 
 * @author SEBASTIAN
 * @version 1.0 28/05/2020
 */
@Transactional
@Repository
public interface IBpProyInvProductoRepo extends CrudRepository<BpProyInvProducto, Long> {

	/**
	 * Metodo que permite la meta por objetivo especifico el identificador del proyecto de inversion pasado como parametro
	 * @param idProyMetaPlan Long que representa el identificador del proyecto de inversion por el cual se va a filtra
	 * @return un objeto BpProyInvProducto
	 */
	@Query("SELECT pi FROM BpProyInvMetaPlan pi WHERE pi.idProyMetaPlan.idProyMetaPlan = :idProyecto")
	public BpProyInvProducto obtenerPorIdProyInvMetaPlan(@Param("idProyecto") Long idProyMetaPlan);
	
}
