package co.gov.sdp.spdd.core.icontroller.administracion.lista.compuesta;

import com.fasterxml.jackson.core.JsonProcessingException;

import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.dto.LineaDeInversionDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;

/**
 * Interface que maneja los metodos que utilizara el controlador de linea de
 * inversion
 *
 * @author Bryan Munoz
 *
 */
public interface ILineaDeInversionController {

	/**
	 * Metodo que permite registrar una linea de inversion
	 *
	 * @param lineaDeInversionDTO campos para lograr el registro en la bd
	 * @return un objeto tipo dto con un codigo y mensaje de respuesta
	 */
	public LineaDeInversionDTO registrarLineaDeInversion(LineaDeInversionDTO lineaDeInversionDTO) throws SpddExceptions, JsonProcessingException;

	/**
	 * Metodo que obtiene todas las lineas de inversion
	 *
	 * @return una lista tipo genericoDTO que contiene las lineas de inversion
	 * @throws JsonProcessingException 
	 */
	public GenericoDTO obtenerTodos(LineaDeInversionDTO peticion) throws SpddExceptions, JsonProcessingException;

	/**
	 * Metodo que permite modificar una linea de inversion
	 *
	 * @param lineaDeInversionDTO campos necesarios para modificar la linea de
	 *                            inversion
	 * @return un objeto tipo dto con un codigo y mensaje de respuesta
	 */
	public LineaDeInversionDTO modificarLineaDeInversion(LineaDeInversionDTO lineaDeInversionDTO) throws SpddExceptions;

	/**
	 * Metodo que permite modificar un estado de linea de inversion
	 *
	 * @param id tipo Long del objeto que se desea activar o desactivar
	 * @return un objeto tipo dto con un codigo y mensaje de respuesta
	 */
	public LineaDeInversionDTO modificarEstadoLineaDeInversion(Long id) throws SpddExceptions;

	/**
	 * 
	 * @param sector
	 * @return
	 * @throws SpddExceptions
	 */
	public GenericoDTO obtenerLineaPorSector(String sector) throws SpddExceptions;

}
