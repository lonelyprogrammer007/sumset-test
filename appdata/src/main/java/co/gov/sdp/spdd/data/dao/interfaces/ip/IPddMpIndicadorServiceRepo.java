package co.gov.sdp.spdd.data.dao.interfaces.ip;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import co.gov.sdp.nhspdd.common.dto.ip.PddMpIndicadorDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.spdd.data.model.ip.PddMpIndicador;
import co.gov.sdp.spdd.data.operacionesbasicas.interfaces.IOperacionesBasicasFacade;

/**
 * 
 * @author Bryan Muñoz
 *
 */
public interface IPddMpIndicadorServiceRepo extends IOperacionesBasicasFacade<PddMpIndicador, Long> {

	/**
	 * 
	 * @param idMetaProducto
	 * @return
	 * @throws SpddExceptions
	 */
	public Page<PddMpIndicador> obtenerMpIndicadorPorMetaProducto(Long idMetaProducto, Pageable pageable)
			throws SpddExceptions;

	/**
	 * 
	 * @param idMetaProducto
	 * @param idIndicador
	 * @return
	 * @throws SpddExceptions
	 */
	public PddMpIndicador validarMetaProductoIndicador(Long idMetaProducto, Long idIndicador) throws SpddExceptions;

	/**
	 * Metodo que permite obtener todos los PddMpIndicador desbalanceados
	 * 
	 * @return una lista de PddMpIndicador con la informacion correspondientes.
	 */
	public List<PddMpIndicador> obtenerTodosDesbalanceados(Long idPlanDesarrollo);

	/**
	 * 
	 * @param idMetaProducto
	 * @return
	 */
	public Long sumarPonderacionesMpIndicador(Long idMetaProducto);
	
	/**
	 * 
	 * @return
	 */
	public List<PddMpIndicador> obtenerTodosOrdenadosASC();
}
