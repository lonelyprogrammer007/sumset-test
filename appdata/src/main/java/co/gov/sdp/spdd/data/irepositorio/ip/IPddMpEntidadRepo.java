package co.gov.sdp.spdd.data.irepositorio.ip;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import co.gov.sdp.spdd.data.model.ip.PddMpEntidad;

/**
 * 
 * @author Bryan Muñoz
 *
 */
public interface IPddMpEntidadRepo extends CrudRepository<PddMpEntidad, Long> {

	@Query("SELECT me FROM PddMpEntidad me WHERE me.idMetaProducto.idMetaProducto= :idMetaProducto")
	List<PddMpEntidad> obtenerMpEntidadPorMetaProducto(@Param("idMetaProducto") Long idMetaProducto, Pageable pageable);

	@Query("SELECT me FROM PddMpEntidad me WHERE me.codigoEntidad.codigoEntidad= :codigoEntidad AND me.idMetaProducto.idMetaProducto= :idMetaProducto")
	PddMpEntidad validarMpEntidadPorMetaProductoYEntidad(@Param("codigoEntidad") String codigoEntidad,
			@Param("idMetaProducto") Long idMetaProducto);
}
