package co.gov.sdp.spdd.core.iservice.administracion.lista.compuesta;

import com.fasterxml.jackson.core.JsonProcessingException;

import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.dto.LineaDeInversionDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;

/**
 * Interface que maneja todos los metodos de consulta asociados con linea de
 * inversion
 *
 * @author Bryan Munoz
 *
 */
public interface ILineaDeInversionConsultar {

	/**
	 * Metodo que obtiene todos los lineas inversion registrados en la bd
	 *
	 * @return una lista tipo genericodto con las lineas de inversion
	 */
	public GenericoDTO obtenerTodos() throws SpddExceptions;

	/**
	 * 
	 * @param peticion
	 * @return
	 * @throws SpddExceptions
	 * @throws JsonProcessingException 
	 */
	public GenericoDTO obtenerPaginado(LineaDeInversionDTO peticion) throws SpddExceptions, JsonProcessingException;

	public GenericoDTO obtenerPorSector(String sector) throws SpddExceptions;
}
