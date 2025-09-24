package co.gov.sdp.spdd.core.iservice.administracion.ls;

import com.fasterxml.jackson.core.JsonProcessingException;

import co.gov.sdp.nhspdd.common.dto.ConsecutivoDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;

/**
 * Interface que maneja los metodos de modificar asociados a consecutivo
 *
 * @author Bryan Munoz
 *
 */
public interface IConsecutivoModificar {

	/**
	 * Metodo que permite modificar un consecutivo
	 *
	 * @param peticion objeto tipo dto que tiene los campos a modificar
	 * @return un objeto tipo dto con un codigo y mensaje de respuesta
	 * @throws JsonProcessingException 
	 */
	public ConsecutivoDTO modificarConsecutivo(ConsecutivoDTO peticion) throws SpddExceptions, JsonProcessingException;

}
