package co.gov.sdp.spdd.core.iservice.administracion.ls;

import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.dto.ListaSimpleDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;

/**
 * Interface que tiene los metodos de consultar
 *
 * @author Bryan Munoz
 *
 */
public interface IAdministracionListaSimpleConsultar {

	/**
	 * Obtiene todas las listas simples
	 *
	 * @return todas las listas simples en la base de datos
	 */
	public GenericoDTO obtenerTodos() throws SpddExceptions;

	/**
	 * 
	 * @param id
	 * @return
	 * @throws SpddExceptions
	 */
	public ListaSimpleDTO obtenerPorId(Long id) throws SpddExceptions;

	/**
	 * 
	 * @param peticion
	 * @return
	 * @throws SpddExceptions
	 */
	public GenericoDTO obtenerPaginado(ListaSimpleDTO peticion) throws SpddExceptions;
	

}
