package co.gov.sdp.spdd.core.icontroller.administracion.ls;

import org.springframework.web.bind.annotation.RequestBody;

import com.fasterxml.jackson.core.JsonProcessingException;

import co.gov.sdp.nhspdd.common.dto.ArgumentoListaSimpleDTO;
import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;

/**
 * Interface que tiene todos los metodos que maneja el controlador
 *
 * @author Bryan Munoz
 *
 */
public interface IArgumentoListaSimpleController {

	/**
	 * Metodo que registra un argumento lista simple en la bd
	 *
	 * @param argumentoListaSimpleDTO objeto el cual se convertira en entidad para
	 *                                registrar
	 * @return un objeto con un codigo y mensaje de respuesta
	 * @throws JsonProcessingException 
	 */
	public ArgumentoListaSimpleDTO registrarArgumentoListaSimple(ArgumentoListaSimpleDTO argumentoListaSimpleDTO)
			throws SpddExceptions, JsonProcessingException;

	/**
	 * Metodo que permite modificar un argumento lista simple
	 *
	 * @param argumentoListaSimpleDTO objeto el cual se convertira en entidad para
	 *                                editar
	 * @return un objeto con un codigo y mensaje de respuesta
	 * @throws JsonProcessingException 
	 */
	public ArgumentoListaSimpleDTO modificarArgumentoListaSimple(ArgumentoListaSimpleDTO argumentoListaSimpleDTO)
			throws SpddExceptions, JsonProcessingException;

	/**
	 * Metodo que obtiene todos a partir de un id de lista simple
	 *
	 * @param listaSimple objeto el cual trae un id para obtener los argumentos de
	 *                    listta
	 * @return la lista de argumentos dependiendo del id de la lista simple
	 */
	public GenericoDTO obtenerTodosPorFiltro(@RequestBody ArgumentoListaSimpleDTO peticion) throws SpddExceptions;

	/**
	 * Metodo que obtiene los argumentos por un nombre de una lista simple
	 *
	 * @param nombre tipo String hace referencia al nombre de una lista simple
	 * @return una lista,codgio y mensaje de respuesta
	 */
	public GenericoDTO obtenerArgumentoPorNombre(String nombre) throws SpddExceptions;

	
	/**
	 * Metodo que permite consultar o buscar los ArgumentosListaSimple que representa las tematicas que estan relacionadas con un plan de desarrollo
	 * @param idPlan Long que representa el identificador del plan de desarrollo del cual se necesitan las tematicas
	 * @return una lista de ArgumentoListaSimple que representa la lista de tematicas correspondiente al plan de desarrollo
	 * @throws SpddExceptions
	 */
	public GenericoDTO consultarArgumentoPorIdPlanDesarrollo(Long idPlan) throws SpddExceptions;
}
