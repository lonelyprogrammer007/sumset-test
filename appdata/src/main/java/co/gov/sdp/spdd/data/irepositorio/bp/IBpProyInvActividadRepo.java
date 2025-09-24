package co.gov.sdp.spdd.data.irepositorio.bp;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.gov.sdp.spdd.data.model.bp.BpProyInvActividad;
import co.gov.sdp.spdd.data.model.bp.BpProyInvMetaPlan;

/**
 * Interface que contiene los metodos basicos de CrudRepository y otros para
 * hacer transacciones relacionadas con la tabla BpProyInvActividad de la BD
 * 
 * @author SEBASTIAN
 * @version 1.0 29/05/2020
 */
@Transactional
@Repository
public interface IBpProyInvActividadRepo extends CrudRepository<BpProyInvActividad, Long> {

	/**
	 * Metodo que permite la meta por objetivo especifico el identificador del proyecto de inversion pasado como parametro
	 * @param IdProducto Long que representa el identificador del proyecto de inversion por el cual se va a filtra
	 * @return un objeto BpProyInvActividad
	 */
	@Query("SELECT pi FROM BpProyInvActividad pi WHERE pi.idProducto.idProducto = :idProyecto")
	public BpProyInvActividad obtenerPorIdProducto(@Param("idProyecto") Long IdProducto);
}
