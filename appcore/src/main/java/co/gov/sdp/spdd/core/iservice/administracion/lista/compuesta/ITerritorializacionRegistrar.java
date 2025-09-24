package co.gov.sdp.spdd.core.iservice.administracion.lista.compuesta;

import com.fasterxml.jackson.core.JsonProcessingException;

import co.gov.sdp.nhspdd.common.dto.TerritorializacionDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;

/**
 * Interface que maneja los meotodos de registrar asociados a territorializacion
 *
 * @author Bryan Munoz
 *
 */
public interface ITerritorializacionRegistrar {

	/**
	 * Metodo que permite registrar una territorializacion en la bd
	 *
	 * @param territorializacionDTO campos a ingresar en la bd
	 * @return un objeto tipo dto con un codigo y mensaje de respuesta
	 */
	public TerritorializacionDTO registrarTerritorializacion(TerritorializacionDTO territorializacionDTO)
			throws SpddExceptions, JsonProcessingException;
}
