package co.gov.sdp.spdd.data.dao.interfaces.afs;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import co.gov.sdp.nhspdd.common.dto.ArgumentoListaSimpleDTO;
import co.gov.sdp.nhspdd.common.dto.FiltroDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.spdd.data.model.afs.ArgumentoListaSimple;
import co.gov.sdp.spdd.data.operacionesbasicas.interfaces.IOperacionesBasicasFacade;

/**
 * @author SumSet 2019
 *
 */
public interface IArgumentoListaSimpleServiceRepo extends IOperacionesBasicasFacade<ArgumentoListaSimple, Long>, Serializable  {
	
	/**
	 * 
	 * @param id
	 * @return
	 * @throws SpddExceptions
	 */
	public List<ArgumentoListaSimple> obtenerArgumentoPorLista(Long id) throws SpddExceptions;
	
	/**
	 * 
	 * @param nombre
	 * @return
	 * @throws SpddExceptions
	 */
	public List<ArgumentoListaSimple> obtenerArgumentoPorNombre(String nombre) throws SpddExceptions;
	
	/**
	 * 
	 * @param argumento
	 * @param inicio
	 * @param limite
	 * @return
	 * @throws SpddExceptions
	 */
	public FiltroDTO filtrarPorCampo(ArgumentoListaSimpleDTO argumento, Long inicio, Integer limite)throws SpddExceptions;
	
	/**
	 * 
	 * @param idListaSimple
	 * @return
	 * @throws SpddExceptions
	 */
	public ArgumentoListaSimple buscarIdListaSimple(Long idListaSimple, String argumento) throws SpddExceptions;

	/**
	 * Metodo que permite consultar o buscar los ArgumentosListaSimple que representa las tematicas que estan relacionadas con un plan de desarrollo
	 * @param idPlan Long que representa el identificador del plan de desarrollo del cual se necesitan las tematicas
	 * @return una lista de ArgumentoListaSimple que representa la lista de tematicas correspondiente al plan de desarrollo
	 * @throws SpddExceptions
	 */
	public List<ArgumentoListaSimple> obtenerArgumentoPorIdPdd(Long idPlan) throws SpddExceptions;
	
	/**
	 * 
	 * @param idArgumento
	 * @return
	 * @throws SpddExceptions
	 */
	public ArgumentoListaSimple obtenerPorId(Long idArgumento) throws SpddExceptions;
	
	/**
	 * Metodo que sirve para obtener el argumento de lista simple por el campo de resultado
	 * @param resultado string que corresponde al valor por el cual se piensa buscar
	 * @return un objeto de tipo ArgumentoistaSimple con la informacion correspondiente
	 */
	public ArgumentoListaSimple obtenerPorResultadoYNombreListaSimple(String resultado, String nombreListaSimple) throws SpddExceptions;
}
