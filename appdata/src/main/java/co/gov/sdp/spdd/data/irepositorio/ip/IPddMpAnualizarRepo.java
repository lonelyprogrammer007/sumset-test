package co.gov.sdp.spdd.data.irepositorio.ip;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import co.gov.sdp.spdd.data.model.ip.PddMpAnualizar;

/**
 * 
 * @author Bryan Muñoz
 *
 */
public interface IPddMpAnualizarRepo extends CrudRepository<PddMpAnualizar, Long> {

	@Query("SELECT ma FROM PddMpAnualizar ma WHERE ma.idMetaProducto.idMetaProducto= :id")
	List<PddMpAnualizar> obtenerMpAnualizarPorMetaProducto(@Param("id") Long idMetaProducto);
}
