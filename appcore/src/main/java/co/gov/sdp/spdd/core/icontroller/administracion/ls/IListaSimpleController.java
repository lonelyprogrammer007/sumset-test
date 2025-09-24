package co.gov.sdp.spdd.core.icontroller.administracion.ls;

import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.dto.ListaSimpleDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;

/**
 * Interface que tiene todos los metodos a implementar del controlador
 *
 * @author BryanMunoz
 *
 */
public interface IListaSimpleController {

	/**
	 * Metodo que crea una lista simple
	 *
	 * @param listaSimpleDTO objeto dto a crear en la bd
	 * @return un objeto con un mensaje y un codigo respuesta
	 */
	public ListaSimpleDTO crearListSimple(ListaSimpleDTO listaSimpleDTO) throws SpddExceptions;

	/**
	 * Metodo que modifica una lista simple de la bd
	 *
	 * @param listaSimpleDTO objetoa modificar
	 * @return un objeto con un mensaje y un codigo de respuesta
	 */
	public ListaSimpleDTO modificarListaSimple(ListaSimpleDTO listaSimpleDTO) throws SpddExceptions;

	/**
	 * Metodo que trae todas las listas registradas
	 *
	 * @param peticion son los datos a filtrar
	 * @return un tipo ListaSimple en una lista
	 */
	public GenericoDTO paginado(ListaSimpleDTO peticion);
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public ListaSimpleDTO obtenerPorId(Long id);

}
