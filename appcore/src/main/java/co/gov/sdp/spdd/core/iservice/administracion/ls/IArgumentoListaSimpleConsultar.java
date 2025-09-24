package co.gov.sdp.spdd.core.iservice.administracion.ls;

import co.gov.sdp.nhspdd.common.dto.ArgumentoListaSimpleDTO;
import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;

/**
 * Interface que tiene los metodos asociados cons consultar un argumento lista
 * simple
 *
 * @author Bryan Munoz
 *
 */
public interface IArgumentoListaSimpleConsultar {

	/**
	 * Metodo que obtiene todos los argumentos lista simple
	 *
	 * @return una lista de argumentos dependiendo de una lista simple
	 */
	public GenericoDTO obtenerTodos(Long id) throws SpddExceptions;

	/**
	 * Metodo que obtiene los argumentos por el nombre de una lista simple
	 *
	 * @param nombre el nombre por el que se desea buscar la lista
	 * @return una lista,codigo y mensaje de respuesta
	 */
	public GenericoDTO obtenerPorNombre(String nombre) throws SpddExceptions;
	/**
	 * 
	 * @param peticion
	 * @return
	 * @throws SpddExceptions
	 */
	public GenericoDTO obtenerPorFiltro(ArgumentoListaSimpleDTO peticion) throws SpddExceptions;
	
	/**
	 * Metodo que permite la comunicacion entre appdata y appcore para consultar o buscar los ArgumentosListaSimple que representa las tematicas que estan relacionadas con un plan de desarrollo
	 * @param idPlan Long que representa el identificador del plan de desarrollo del cual se necesitan las tematicas
	 * @return una lista de ArgumentoListaSimple que representa la lista de tematicas correspondiente al plan de desarrollo
	 * @throws SpddExceptions
	 */
	public GenericoDTO consultarArgumentoPorIdPlanDesarrollo(Long idPlan) throws SpddExceptions;

}
