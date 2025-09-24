package co.gov.sdp.spdd.core.iservice.administracion.lista.compuesta;

import com.fasterxml.jackson.core.JsonProcessingException;

import co.gov.sdp.nhspdd.common.dto.ComponenteGastoDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;

/**
 * Interfaz para implementar todos las funcionalidades de registro para el
 * modulo de administracion
 *
 * @author Raul Londono Murillo
 *
 */
public interface IComponenteGastoAdministracionRegistrar {

	/**
	 * Metodo para crear componente de gasto
	 *
	 * @param peticion Objeto DTO con los datos para crear el componente de gasto
	 * @return Objeto DTO informando la creacion exitosa del componente de gasto,
	 *         sino un objeto vacio con el mensaje de error correspondiente
	 */
	public ComponenteGastoDTO crearComponenteGasto(ComponenteGastoDTO peticion) throws SpddExceptions, JsonProcessingException;

}
