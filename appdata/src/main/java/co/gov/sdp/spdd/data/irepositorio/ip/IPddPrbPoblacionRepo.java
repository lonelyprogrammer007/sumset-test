package co.gov.sdp.spdd.data.irepositorio.ip;


import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import co.gov.sdp.spdd.data.model.ip.PddPrbPoblacion;

/**
 * 
 * @author Bryan Muñoz
 *
 */
@Repository
public interface IPddPrbPoblacionRepo extends CrudRepository<PddPrbPoblacion, Long>, Serializable {

	@Query("SELECT pp FROM PddPrbPoblacion pp WHERE pp.descripcion= :descripcion AND pp.idProblematica.idProblematica= :id")
	PddPrbPoblacion buscarPorDescripcionYProblematica(@Param("descripcion") String descripcion, @Param("id") Long id);
	
	@Query("Select pp from PddPrbPoblacion pp where pp.idProblematica.idProblematica= :id")
	List<PddPrbPoblacion> obtenerPddPrbPoblacionPorIdProblematica(@Param("id") Long id);
}
