package co.gov.sdp.spdd.core.iservice.administracion;

import com.fasterxml.jackson.core.JsonProcessingException;

import co.gov.sdp.nhspdd.common.dto.UsuarioEntidadDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;

/**
 * Interfaz para implementar todos las funcionalidades de eliminacion para el
 * modulo de administracion
 *
 * @author Raul Londono Murillo
 *
 */
public interface IEntidadAdministracionEliminar {

	/**
	 * Metodo para remover entidades de un usuario
	 *
	 * @param peticion Objeto DTO con los datos para remover la entidad de un
	 *                 usuario que lo tiene asignado
	 * @return Objeto DTO informando la remocion exitosa de la entidad de los
	 *         asignados al usuario, sino un objeto vacio con el mensaje de error
	 *         correspondiente
	 */
	public UsuarioEntidadDTO removerUsuarioEntidad(UsuarioEntidadDTO peticion) throws SpddExceptions, JsonProcessingException;
}
