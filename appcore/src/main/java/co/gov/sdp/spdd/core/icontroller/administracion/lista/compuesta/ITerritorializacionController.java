package co.gov.sdp.spdd.core.icontroller.administracion.lista.compuesta;

import com.fasterxml.jackson.core.JsonProcessingException;

import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.dto.TerritorializacionDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;

/**
 * Interface que maneja los metodos del controlador de territorializacion
 *
 * @author Bryan Munoz
 *
 */
public interface ITerritorializacionController {

	/**
	 * Metodo que registra una territorializacion
	 *
	 * @param territorializacionDTO campos para registrar la territorializacion
	 * @return un objeto tipo dto con un codigo y mensaje de respuesta
	 */
	public TerritorializacionDTO registrarTerritorializacion(TerritorializacionDTO territorializacionDTO)
			throws SpddExceptions, JsonProcessingException;

	/**
	 * Metodo que modifica una territorializacion
	 *
	 * @param territorializacionDTO campos a modificar
	 * @return un objeto tipo dto con un codigo y mensaje de respuesta
	 */
	public TerritorializacionDTO modificarTerritorializacion(TerritorializacionDTO territorializacionDTO)
			throws SpddExceptions;

	/**
	 * Metodo que modifica el estado de una territorializacion
	 *
	 * @param id tipo Long del objeto que se desea activar o desactivar
	 * @return un objeto tipo dto con un codigo y mensaje de respuesta
	 */
	public TerritorializacionDTO modificarEstadoTerritorializacion(Long id) throws SpddExceptions;

	/**
	 * Metodoq que obtiene una lista de todas las territorializaciones en la bd
	 *
	 * @return un objeto tipo genericoDTO con las listas y un codigo y mensaje de
	 *         respuesta
	 * @throws JsonProcessingException 
	 */
	public GenericoDTO obtenerTodos(TerritorializacionDTO peticion) throws SpddExceptions, JsonProcessingException;

	/**
	 * 
	 * @return
	 * @throws SpddExceptions
	 */
	public GenericoDTO obtenerTerritorializacionTodos() throws SpddExceptions;

	/**
	 * 
	 * @return
	 * @throws SpddExceptions
	 */
	public GenericoDTO obtenerTerritorializacionPorLocalidad() throws SpddExceptions;
	
	public GenericoDTO obtenerTerritoritoriosPorIdLocalidad(Long idLocalidad);

}
