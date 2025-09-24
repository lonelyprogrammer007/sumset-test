package co.gov.sdp.spdd.data.dao.interfaces.ip;

import java.util.List;

import org.springframework.data.repository.query.Param;

import co.gov.sdp.nhspdd.common.dto.FiltroDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.spdd.data.model.ip.PddMetaProducto;
import co.gov.sdp.spdd.data.operacionesbasicas.interfaces.IOperacionesBasicasFacade;

/**
 * 
 * @author Bryan Muñoz
 *
 */
public interface IPddMetaProductoServiceRepo extends IOperacionesBasicasFacade<PddMetaProducto, Long> {

	/**
	 * Obtener meta producto
	 * 
	 * @param idMetaResultado
	 * @return
	 * @throws SpddExceptions
	 */
	public List<PddMetaProducto> obtenerPorMetaResultado(Long idMetaResultado) throws SpddExceptions;

	/**
	 * 
	 * @param idMetaResultado
	 * @param inicio
	 * @param limite
	 * @return
	 * @throws SpddExceptions
	 */
	public FiltroDTO obtenerTodosMetaProductos(Long idMetaResultado, Long inicio, Integer limite) throws SpddExceptions;

	/**
	 * Metodo que permite consultar todos los PddMetaProducto que estan
	 * desbalanceados
	 * 
	 * @return una lista de tipo PddMetaProducto con la informacion
	 *         correspondientes.
	 */
	List<PddMetaProducto> obtenerTodosDesbalanceados(Long idPlanDesarrollo);

	public Long sumarPonderacionesMetaProducto(Long idMetaResultado) throws SpddExceptions;
	
	/**
	 * 
	 * @param idMetaResultado
	 * @return
	 */
	public List<PddMetaProducto> obtenerTodosPorIdMetaResultado(Long idMetaResultado);
	
	/**
	 * 
	 * @return
	 */
	public List<PddMetaProducto> obtenerTodosOrdenadosASC();
}
