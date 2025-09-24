package co.gov.sdp.spdd.data.irepositorio.ip;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.spdd.data.model.ip.PddMetaProducto;

/**
 * 
 * @author Bryan Muñoz
 *
 */
public interface IPddMetaProductoRepo extends CrudRepository<PddMetaProducto, Long> {

	@Query("SELECT pm FROM PddMetaProducto pm WHERE pm.idMetaResultado.idMetaResultado= :id ORDER BY pm.idMetaProducto")
	List<PddMetaProducto> obtenerPorMetaResultado(@Param("id") Long idMetaResultado);

	/**
	 * Metodo que permite consultar todos los PddMetaProducto que estan
	 * desbalanceados
	 * 
	 * @return una lista de tipo PddMetaProducto con la informacion
	 *         correspondientes.
	 */
	@Query("SELECT pm FROM PddMetaProducto pm WHERE pm.sumPond > 0 AND pm.sumPond <> 100 AND pm.idMetaResultado.idProyEstrategico.idPddNivel.idPlanDesarrollo.idPlanDesarrollo = :pdd")
	List<PddMetaProducto> obtenerTodosDesbalanceados(@Param("pdd") Long idPlanDesarrollo);

	@Query("SELECT SUM(mp.ponderacion) FROM PddMetaProducto mp WHERE mp.idMetaResultado.idMetaResultado= :idMetaResultado")
	public Long sumarPonderacionesMetaProducto(@Param("idMetaResultado") Long idMetaResultado) throws SpddExceptions;
	
	@Query("SELECT pm FROM PddMetaProducto pm ORDER BY pm.idMetaProducto")
	public List<PddMetaProducto> obtenerTodosOrdenadosASC();
}
