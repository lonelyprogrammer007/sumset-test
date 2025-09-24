package co.gov.sdp.spdd.data.irepositorio.ip;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import co.gov.sdp.spdd.data.model.ip.PddMpIndicador;

/**
 * 
 * @author Bryan Muñoz
 *
 */
public interface IPddMpIndicadorRepo extends PagingAndSortingRepository<PddMpIndicador, Long> {

	@Query("SELECT mi FROM PddMpIndicador mi WHERE mi.idMetaProducto.idMetaProducto= :id")
	Page<PddMpIndicador> obtenerMpIndicadorPorMetaProducto(@Param("id") Long idMetaProducto, Pageable pageable);

	@Query("SELECT mp FROM PddMpIndicador mp WHERE mp.idMetaProducto.idMetaProducto= :id AND mp.idIndicador.idIndicador= :indicador")
	PddMpIndicador validarMpIndicador(@Param("id") Long idMetaProducto, @Param("indicador") Long indicador);

	/**
	 * Metodo que permite obtener todos los PddMpIndicador desbalanceados
	 * 
	 * @return una lista de PddMpIndicador con la informacion correspondientes.
	 */
	@Query("SELECT mpi FROM PddMpIndicador mpi WHERE mpi.sumPond > 0 AND mpi.sumPond <> 100 AND mpi.idMetaProducto.idMetaResultado.idProyEstrategico.idPddNivel.idPlanDesarrollo.idPlanDesarrollo = :pdd")
	public List<PddMpIndicador> obtenerTodosDesbalanceados(@Param("pdd") Long idPlanDesarrollo);

	@Query("SELECT SUM(mi.idIndicador.ponderacion) FROM PddMpIndicador mi WHERE mi.idMetaProducto.idMetaProducto= :id")
	public Long sumarPonderacionIndicadoreMetaProducto(@Param("id") Long idMetaProducto);
	
	@Query("SELECT mpi FROM PddMpIndicador mpi ORDER BY mpi.idMetaProdInd ASC")
	public List<PddMpIndicador> obtenerTodosOrdenadosASC();
}
