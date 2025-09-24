package co.gov.sdp.spdd.data.irepositorio.bp;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.gov.sdp.spdd.data.model.bp.BpProyInvEspecif;
import co.gov.sdp.spdd.data.model.bp.BpProyInvLocaliza;
import co.gov.sdp.spdd.data.model.bp.BpProyInvMetaPlan;

/**
 * Interface que contiene los metodos basicos de CrudRepository y otros para
 * hacer transacciones relacionadas con la tabla BpProyInvMetaPlan de la BD
 * 
 * @author SEBASTIAN
 * @version 1.0 28/05/2020
 */
@Transactional
@Repository
public interface IBpProyInvMetaPlanRepo extends CrudRepository<BpProyInvMetaPlan, Long> {

	/**
	 * Metodo que permite la meta por objetivo especifico el identificador del proyecto de inversion pasado como parametro
	 * @param idProyObjEspecif Long que representa el identificador del proyecto de inversion por el cual se va a filtra
	 * @return un objeto BpProyInvMetaPlan
	 */
	@Query("SELECT pi FROM BpProyInvMetaPlan pi WHERE pi.idProyObjEspecif.idProyObjEspecif = :idProyecto")
	public BpProyInvMetaPlan obtenerPorIdProyInvEspecif(@Param("idProyecto") Long idProyObjEspecif);
}
