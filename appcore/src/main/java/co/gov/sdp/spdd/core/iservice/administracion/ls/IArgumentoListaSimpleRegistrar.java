package co.gov.sdp.spdd.core.iservice.administracion.ls;

import com.fasterxml.jackson.core.JsonProcessingException;

import co.gov.sdp.nhspdd.common.dto.ArgumentoListaSimpleDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;

/**
 * Interface que tiene los metodos asociados con el registro de argumento lista
 * simple
 *
 * @author Bryan Munoz
 *
 */
public interface IArgumentoListaSimpleRegistrar {

	/**
	 * Metodo que registra un argumento lista simple en la bd
	 *
	 * @param argumentoListaSimpleDTO objeto con datos para crear la entidad
	 * @return un codigo y mensaje de respuesta
	 * @throws JsonProcessingException 
	 */
	public ArgumentoListaSimpleDTO registrarArgumentoListaSimple(ArgumentoListaSimpleDTO argumentoListaSimpleDTO)
			throws SpddExceptions, JsonProcessingException;
}
