package co.gov.sdp.spdd.core.iservice.administracion.ls;

import co.gov.sdp.nhspdd.common.dto.ConsecutivoDTO;
import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;

/**
 * Interface que tiene los metodos de consulta asociado con consecutivo
 *
 * @author Bryan Munoz
 *
 */
public interface IConsecutivoConsultar {

	/**
	 * Metodo que obtiene todos los consecutivos
	 *
	 * @return un objeto tipo genericoDTO que tiene la lista, un codigo y mensaje de
	 *         respuesta
	 */
	public GenericoDTO obtenerTodos() throws SpddExceptions;

	/**
	 * 
	 * @param peticion
	 * @return
	 * @throws SpddExceptions
	 */
	public GenericoDTO obtenerPaginado(ConsecutivoDTO peticion) throws SpddExceptions;

	/**
	 * 
	 * @param peticion
	 * @return
	 * @throws SpddExceptions
	 */
	public ConsecutivoDTO obtenerConsecutivoPorPlanYEntidad(ConsecutivoDTO peticion) throws SpddExceptions;

}
