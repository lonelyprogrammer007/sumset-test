package co.gov.sdp.spdd.data.irepositorio.ip;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.spdd.data.model.ip.PddObraConcreta;
/**
 * 
 * @author SumSet 2019
 *
 */
@Repository
public interface IObraConcretaRepo extends CrudRepository<PddObraConcreta, Long> {

	@Query("SELECT c FROM PddObraConcreta c WHERE c.idMeta.idMeta= :id")
	List<PddObraConcreta> findIdMeta(@Param("id")Long idMeta);
	
	
	@Query("SELECT oc FROM PddObraConcreta oc WHERE oc.idMeta.idMeta= :idMeta AND oc.obraConcreta= :obraConcreta")
	public PddObraConcreta validarIdMetaYObraConcreta(@Param("idMeta")Long idMeta,@Param("obraConcreta") String obraConcreta) throws SpddExceptions;
}
