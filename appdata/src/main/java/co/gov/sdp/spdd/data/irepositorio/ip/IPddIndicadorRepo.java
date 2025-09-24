package co.gov.sdp.spdd.data.irepositorio.ip;

import java.util.List;

import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.spdd.data.model.ip.PddIndicador;

/**
 * 
 * @author Bryan Muñoz
 *
 */
@Repository
public interface IPddIndicadorRepo extends CrudRepository<PddIndicador, Long> {

	@Query("SELECT pi FROM PddIndicador pi WHERE pi.nombre= :nombre AND pi.magnitud= :magnitud AND pi.periodicidad= :periodicidad "
			+ "AND pi.fuenteExterna= :fuenteExterna AND pi.fuente= :fuente AND pi.lineaBase= :lineaBase AND pi.magnitudLb= :magnitudLb AND pi.lineaBaseDesc= :lineaBaseDesc")
	List<PddIndicador> obtenerPddIndicadorMetaResultado(@Param("nombre") String denominacion,
			@Param("magnitud") Long magnitud, @Param("periodicidad") String periodicidad,
			@Param("fuenteExterna") Long fuenteExterna, @Param("fuente") String fuente,
			@Param("lineaBase") Long lineaBase, @Param("magnitudLb") Long magnitudLb,
			@Param("lineaBaseDesc") String lineaBaseDesc) throws SpddExceptions;

	@Query("SELECT pi FROM PddIndicador pi WHERE pi.idArchivo.idArchivo= -1")
	List<PddIndicador> obtenerIndicadorDeArchivos();
}
