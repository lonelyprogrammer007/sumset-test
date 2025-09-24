package co.gov.sdp.spdd.core.iservice.administracion.ls;

import com.fasterxml.jackson.core.JsonProcessingException;

import co.gov.sdp.nhspdd.common.dto.ParametroGeneralDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;

/**
 * Interface que tiene los metodos modificar asociados a un parametro general
 *
 * @author Bryan Munoz
 *
 */
public interface IParametroGeneralModificar {

	/**
	 * Metodo que modifica un parametro
	 *
	 * @param parametroGeneralDTO campos a modificar de un parametro general
	 * @return un objeto tipo dto con un codigo y mensaje de respuesta
	 * @throws JsonProcessingException 
	 */
	public ParametroGeneralDTO editarParametro(ParametroGeneralDTO peticion) throws SpddExceptions, JsonProcessingException;
}
