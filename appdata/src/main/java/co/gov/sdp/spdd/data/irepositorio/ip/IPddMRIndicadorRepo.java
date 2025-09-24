package co.gov.sdp.spdd.data.irepositorio.ip;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import co.gov.sdp.spdd.data.model.ip.PddMRIndicador;

/**
 * 
 * @author Bryan Muñoz
 *
 */
public interface IPddMRIndicadorRepo extends CrudRepository<PddMRIndicador, Long> {

	@Query("Select pi FROM PddMRIndicador pi WHERE pi.idIndicador.idIndicador= :idIndicador AND pi.idMetaResultado.idMetaResultado= :idMetaResultado ")
	PddMRIndicador validarIndicadorMetaResultado(@Param("idIndicador") Long idIndicador,
			@Param("idMetaResultado") Long idMetaResultado);
	
	@Query("Select mri FROM PddMRIndicador mri WHERE mri.idMetaResultado.idMetaResultado= :metaResultado ORDER BY mri.idIndProxy ")
	public List<PddMRIndicador> obtenerTodosPorIdMetaResultado(@Param("metaResultado") Long idMetaResultado);

	@Query("Select mri FROM PddMRIndicador mri ORDER BY mri.idIndProxy ASC")
	public List<PddMRIndicador> obtenerTodosOrdenadosASC();

}
