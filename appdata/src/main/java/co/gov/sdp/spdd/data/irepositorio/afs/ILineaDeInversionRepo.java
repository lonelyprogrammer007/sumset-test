package co.gov.sdp.spdd.data.irepositorio.afs;

import java.io.Serializable;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.spdd.data.model.afs.LineaDeInversion;

/**
 * 
 * @author sumset
 *
 */
@Transactional
@Repository
public interface ILineaDeInversionRepo extends CrudRepository<LineaDeInversion, Long>, Serializable {

	/**
	 * 
	 * @param concepto
	 * @param idLsSector
	 * @return
	 * @throws SpddExceptions
	 */
	@Query("SELECT li FROM LineaDeInversion li WHERE lower(li.concepto)= lower(:concepto) AND li.idLsSector.idArgumento= :sector")
	public LineaDeInversion obtenerConceptoYIdLsSector(@Param("concepto") String concepto, @Param("sector") Long sector)
			throws SpddExceptions;

	@Query("SELECT li FROM LineaDeInversion li WHERE lower(li.idLsSector.resultado) = :sector")
	public List<LineaDeInversion> obtenerLineaPorSector(@Param("sector") String sector) throws SpddExceptions;

}
