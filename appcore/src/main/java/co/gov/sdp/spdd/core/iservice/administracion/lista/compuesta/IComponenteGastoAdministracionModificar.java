package co.gov.sdp.spdd.core.iservice.administracion.lista.compuesta;

import co.gov.sdp.nhspdd.common.dto.ComponenteGastoDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;

/**
 * Interfaz para implementar todos las funcionalidades de modificicacion para el
 * modulo de administracion
 *
 * @author Raul Londono Murillo
 *
 */
public interface IComponenteGastoAdministracionModificar {

	

	/**
	 * Metodo para modificar componente de gasto
	 *
	 * @param peticion Objeto DTO con los datos para modificar el componente de
	 *                 gasto
	 * @return Objeto DTO informando la modificacion exitosa del componente de
	 *         gasto, sino un objeto vacio con el mensaje de error correspondiente
	 */
	public ComponenteGastoDTO modificarComponenteGasto(ComponenteGastoDTO peticion) throws SpddExceptions;
}
