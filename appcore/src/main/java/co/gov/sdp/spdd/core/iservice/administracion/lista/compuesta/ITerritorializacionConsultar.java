package co.gov.sdp.spdd.core.iservice.administracion.lista.compuesta;

import com.fasterxml.jackson.core.JsonProcessingException;

import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.dto.TerritorializacionDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;

/**
 * Interface que maneja los metodos asociados con consultar de
 * territorializacion
 *
 * @author Bryan Munoz
 *
 */
public interface ITerritorializacionConsultar {

	/**
	 * Metodo que obtiene todas las territorializaciones
	 *
	 * @return un objeto tipo genericoDTO con la lista de territorializaciones tipo
	 *         dto
	 */
	public GenericoDTO obtenerTodos() throws SpddExceptions;

	/**
	 * 
	 * @param peticion
	 * @return
	 * @throws SpddExceptions
	 * @throws JsonProcessingException 
	 */
	public GenericoDTO obtenerPaginado(TerritorializacionDTO peticion) throws SpddExceptions, JsonProcessingException;

	/**
	 * 
	 * @param localidad
	 * @return
	 * @throws SpddExceptions
	 */
	public GenericoDTO obtenerTerPorLocalidad() throws SpddExceptions;
	
	public GenericoDTO obtenerTerritoriosPorIdLocalidad(Long idLocalidad) throws SpddExceptions;

}
