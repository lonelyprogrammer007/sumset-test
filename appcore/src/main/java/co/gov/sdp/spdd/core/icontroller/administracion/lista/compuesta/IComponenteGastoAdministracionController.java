package co.gov.sdp.spdd.core.icontroller.administracion.lista.compuesta;

import com.fasterxml.jackson.core.JsonProcessingException;

import co.gov.sdp.nhspdd.common.dto.ComponenteGastoDTO;
import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;

/**
 * Interface para definir los endpoints de los componentes de gasto para el
 * modulo de administracion
 *
 * @author Raul Londono Murillo
 *
 */
public interface IComponenteGastoAdministracionController {

	/**
	 * Metodo para crear un componente de gasto
	 *
	 * @param peticion Objeto DTO que contiene la informacion del componente de
	 *                 gasto
	 * @return Objeto DTO que contiene la informacion del componente de gasto creado
	 *         si la peticion se realiza satisfactoriamente, sino, se enviara el
	 *         objeto vacio con el respectivo mensaje de error
	 */
	public ComponenteGastoDTO crearComponenteGasto(ComponenteGastoDTO peticion) throws SpddExceptions, JsonProcessingException;


	/**
	 * Metodo para modificar componente de gasto
	 *
	 * @param peticion Objeto DTO que contiene la informacion del componente de
	 *                 gasto a modificar
	 * @return Objeto DTO que contiene la informacion del compentente de gasto si la
	 *         peticion se realiza satisfactoriamente, sino, se enviara el objeto
	 *         vacio con el respectivo mensaje de error
	 */
	public ComponenteGastoDTO modificarComponenteGasto(ComponenteGastoDTO peticion) throws SpddExceptions;

	/**
	 * Metodo que trae la lista completa de componentes de gasto que el usuario
	 * puede ver
	 *
	 * @return Objeto DTO generico que contiene la lista de componentes de gasto que
	 *         el usuario puede ver
	 */
	public GenericoDTO obtenerComponenteGastoTodos(ComponenteGastoDTO peticion) throws SpddExceptions;
}
