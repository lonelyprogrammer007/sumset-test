package co.gov.sdp.spdd.data.irepositorio.bp;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.gov.sdp.spdd.data.model.bp.BpProyInvAnualiza;
import co.gov.sdp.spdd.data.model.bp.BpProyInvFinancia;

/**
 * Interface que contiene los metodos basicos de CrudRepository y otros para
 * hacer transacciones relacionadas con la tabla BpProyInvAnualiza de la BD
 * 
 * @author Jefferson Arenas
 * @version 1.0 02/06/2020
 *  
 */
@Transactional
@Repository
public interface IBpProyInvAnualizaRepo extends CrudRepository<BpProyInvAnualiza, Long>{

	/**
	 * Metodo que permite obtener los BpProyInvAnualiza por el indentificador del aporte y el identificador del proyecto
	 * @param idFuente Long que representa el identificador de la fuente de inversion
	 * @return una lista de tipo BpProyInvAnualiza con la informacion correspondiente
	 */
	@Query("SELECT pia FROM BpProyInvAnualiza pia WHERE  pia.idFuente.idFuente=:fuente")
	public Page<BpProyInvAnualiza> obtenerPorIdTodosProyInvAnualiza(@Param("fuente") Long idFuente, Pageable paginador);
	
	@Query("SELECT pia FROM BpProyInvAnualiza pia WHERE  pia.idFuente.idFuente=:fuente")
	public List<BpProyInvAnualiza> obtenerPorIdTodosProyInvAnualizacion (@Param("fuente") Long idFuente);
	

	
}
