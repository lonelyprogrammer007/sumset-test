package co.gov.sdp.spdd.data.irepositorio.ip;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.spdd.data.model.ip.PddMpIndicadorEntidad;

/**
 * 
 * @author Bryan Muñoz
 *
 */
public interface IPddMpIndicadorEntidadRepo extends PagingAndSortingRepository<PddMpIndicadorEntidad, Long> {

	@Query("SELECT ie FROM PddMpIndicadorEntidad ie WHERE ie.idMetaProdInd.idMetaProdInd= :idMetaProdInd")
	Page<PddMpIndicadorEntidad> obtenerEntidadesPorIndicadorProducto(@Param("idMetaProdInd") Long idMetaProdInd,
			Pageable page);

	@Query("SELECT ie FROM PddMpIndicadorEntidad ie WHERE ie.codigoEntidad.codigoEntidad= :codigoEntidad AND ie.idMetaProdInd.idMetaProdInd= :idMeta")
	PddMpIndicadorEntidad validarIndicadorEntidadPorEntidadEIndicadorMetaProducto(
			@Param("codigoEntidad") String codigoEntidad, @Param("idMeta") Long idMetaProdInd);

	@Query("SELECT SUM(ie.ponderacion) FROM PddMpIndicadorEntidad ie WHERE ie.idMetaProdInd.idMetaProdInd = :id")
	public Long sumarPonderacionesEntidadesIndicadorMp(@Param("id") Long idMetaProdInd) throws SpddExceptions;
}
