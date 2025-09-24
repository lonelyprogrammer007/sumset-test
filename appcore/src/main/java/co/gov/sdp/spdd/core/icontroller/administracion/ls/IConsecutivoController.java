package co.gov.sdp.spdd.core.icontroller.administracion.ls;

import com.fasterxml.jackson.core.JsonProcessingException;

import co.gov.sdp.nhspdd.common.dto.ConsecutivoDTO;
import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;

/**
 * Interface que manejo los metodos del controlador de Consecutivo
 *
 * @author Bryan Munoz
 *
 */
public interface IConsecutivoController {

	/**
	 * Metodo el cual permite modificar un consecutivo
	 *
	 * @param peticion viene con los campos a modificar
	 * @return un objeto tipo dto con un codigo y mensaje de respuesta
	 * @throws JsonProcessingException
	 */
	public ConsecutivoDTO editatConsecutivo(ConsecutivoDTO peticion) throws SpddExceptions, JsonProcessingException;

	/**
	 * Metodo el cual permite obtener todos los consecutivos
	 *
	 * @return un lista tipo genericoDTO que tiene los consecutivos un codigo y
	 *         mensaje de respuesta
	 */
	public GenericoDTO obtenerTodos(ConsecutivoDTO peticion);

	/**
	 * 
	 * @param consecutivo
	 * @return
	 */
	public ConsecutivoDTO obtenerConsecutivoPorPlanYEntidad(ConsecutivoDTO consecutivo);
}
