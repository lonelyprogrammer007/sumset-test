/**
 * 
 */
package co.gov.sdp.spdd.data.irepositorio.ip;

import java.util.List;
import java.io.Serializable;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.spdd.data.model.ip.PddObraConcreta;
import co.gov.sdp.spdd.data.model.ip.PddProblematica;

/**
 * @author Jose Alvaro
 *
 */
@Transactional
@Repository
public interface IPddProblematicaRepo extends CrudRepository<PddProblematica, Long>, Serializable {

	/**
	 * Método para validar si la llave de negocio existe.
	 * 
	 * @param idCompromiso
	 * @param problematica
	 * @return
	 * @throws SpddExceptions
	 */
	@Query("SELECT pr FROM PddProblematica pr WHERE pr.idCompromiso.idCompromiso= :idCompromiso AND pr.problematica= :problematica")
	public PddProblematica validarIdCompromisoYProblematica(@Param("idCompromiso") Long idCompromiso,
			@Param("problematica") String problematica) throws SpddExceptions;
	
	@Query("Select pr From PddProblematica pr where pr.idCompromiso.idCompromiso= :idCompromiso")
	public List<PddProblematica> consultarPorIdCompromiso(@Param("idCompromiso") Long idCompromiso);
}
