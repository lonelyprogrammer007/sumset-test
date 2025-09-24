package co.gov.sdp.spdd.core.iservice.administracion.lista.compuesta;

import co.gov.sdp.nhspdd.common.dto.TerritorializacionDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;

/**
 * Interface que maneja los metodos asociados con modificar de
 * Territorializacion
 *
 * @author Bryan Munoz
 *
 */
public interface ITerritorializacionModificar {

	/**
	 * Metodo que permite modificar una territorializacion de la bd
	 *
	 * @param territorializacionDTO campos a modicar
	 * @return un objeto tipo dto con un codigo y mensaje de respuesta
	 */
	public TerritorializacionDTO modificarTerritorializacion(TerritorializacionDTO territorializacionDTO)
			throws SpddExceptions;

	/**
	 * Metodo que permite activar o desactivar un estado
	 *
	 * @param id tipo Long del objeto que se desea modificar el estado
	 * @return un objeto tipo dto con un codigo y mensaje de respuesta
	 */
	public TerritorializacionDTO modificarEstadoTerritorializacion(Long id) throws SpddExceptions;

}
