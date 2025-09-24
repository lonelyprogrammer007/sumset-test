package co.gov.sdp.spdd.data.irepositorio.afs;

import java.io.Serializable;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.spdd.data.model.afs.Territorializacion;

/**
 * @author SumSet 2019
 *
 */
@Transactional
@Repository
public interface ITerritorializacionRepo extends CrudRepository<Territorializacion, Long>, Serializable {

	/**
	 * 
	 * @param idLsBarrio
	 * @param idLsUpz
	 * @param idLsLocalidad
	 * @return
	 */
	@Query("SELECT c FROM Territorializacion c WHERE c.idLsLocalidad.idArgumento= :localidad AND c.idLsBarrio.idArgumento= :barrio AND c.idLsUpz.idArgumento = :upz")
	public Territorializacion findByIdLsBarrioAndIdLsUpzAndIdLsLocalidad(@Param("barrio") Long idLsBarrio,
			@Param("upz") Long idLsUpz, @Param("localidad") Long idLsLocalidad);

	/**
	 * 
	 * @param idLsVereda
	 * @param idLsUpr
	 * @param idLsLocalidad
	 * @return
	 */
	@Query("SELECT c FROM Territorializacion c WHERE c.idLsVereda.idArgumento= :vereda AND c.idLsUpr.idArgumento = :upr AND c.idLsLocalidad.idArgumento= :localidad")
	public Territorializacion findByIdLsVeredaAndIdLsUpr(@Param("vereda") Long idLsVereda, @Param("upr") Long idLsUpr,
			@Param("localidad") Long idLsLocalidad);

	/**
	 * 
	 * @param localidad
	 * @return
	 * @throws SpddExceptions
	 */
	@Query("SELECT DISTINCT t FROM Territorializacion t WHERE t.idLsLocalidad.resultado= :localidad")
	public List<Territorializacion> obtenerPorLocalidad(@Param("localidad") String localidad) throws SpddExceptions;
	
	@Query("Select t from Territorializacion t where t.idLsLocalidad.idArgumento= :idLocalidad")
	public List<Territorializacion> obtenerPorIdLocalidad(@Param("idLocalidad") Long idLocalidad);
	
	
	
}
