package co.gov.sdp.spdd.data.irepositorio.bp;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.gov.sdp.spdd.data.model.bp.BpProyInvTipo;

/**
 * 
 * @author DANIEL
 *
 */
@Transactional
@Repository
public interface IBpProyInvTipoRepo extends CrudRepository<BpProyInvTipo, Long> {
	
	/**
	 * Metodo que permite obtener una lista de BpProyInvTipo relacionado a un proyecto de inversion
	 * @param idProyecto Long que representa el identificador por le cual se va a buscar los BpProyInvTipo 
	 * @return una lista BpProyInvTipo con la informacion correspondiente
	 */
	@Query("SELECT pit FROM BpProyInvTipo pit WHERE pit.idProyInversion.idProyInversion=:idProyecto")
	public List<BpProyInvTipo> obtenerPorIdProyectoInversion(@Param("idProyecto") Long idProyecto);
	
	/**
	 * Metodo que permite obtener un objeto de  tipo BpProyInvTipo correspondiente al idLsTipo e idProyInversion pasados como parametros
	 * @param idLsTipo Long que representa el identificador del argumento de lista simple que reprsenta el tipo.
	 * @param idProyInversion Long que representa el identificador del proyecto de inversion
	 * @return una lista BpProyInvTipo con la informacion correspondiente
	 */
	@Query("SELECT pit FROM BpProyInvTipo pit WHERE pit.idLsTipo.idArgumento=:tipo AND pit.idProyInversion.idProyInversion=:proyecto")
	public BpProyInvTipo obtenerPorIdLsTipoYIdProyInversion(@Param("tipo") Long idLsTipo,@Param("proyecto") Long idProyInversion);
	
}
