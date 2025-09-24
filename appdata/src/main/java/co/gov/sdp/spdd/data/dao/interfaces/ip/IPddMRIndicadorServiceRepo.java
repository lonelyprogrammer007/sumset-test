package co.gov.sdp.spdd.data.dao.interfaces.ip;

import java.util.List;

import org.springframework.data.repository.query.Param;

import co.gov.sdp.nhspdd.common.dto.FiltroDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.spdd.data.model.ip.PddMRIndicador;
import co.gov.sdp.spdd.data.operacionesbasicas.interfaces.IOperacionesBasicasFacade;

/**
 * 
 * @author Bryan Muñoz
 *
 */
public interface IPddMRIndicadorServiceRepo extends IOperacionesBasicasFacade<PddMRIndicador, Long> {

	/**
	 * Metodo que obtiene todos los indicadores de un meta resultado
	 * 
	 * @param idMetaResultado id por el que se desea buscar el meta resultado
	 * @param inicio          pagina en la que inicia
	 * @param limite          limite que se muestra por consulta
	 * @return un objeto tipo filtroDTO
	 * @throws SpddExceptions
	 */
	public FiltroDTO obtenerTodosIndicadoresPorMetaResultado(Long idMetaResultado, Long inicio, Integer limite)
			throws SpddExceptions;

	/**
	 * 
	 * @param idIndicador
	 * @param idMetaResultado
	 * @return
	 * @throws SpddExceptions
	 */
	public PddMRIndicador validarIndicadorMetaResultado(Long idIndicador, Long idMetaResultado) throws SpddExceptions;
	
	/**
	 * 
	 * @param idMetaResultado
	 * @return
	 */
	public List<PddMRIndicador> obtenerTodosPorIdMetaResultado(Long idMetaResultado);
	
	/**
	 * 
	 * @return
	 */
	public List<PddMRIndicador> obtenerTodosOrdenadosASC();


}
