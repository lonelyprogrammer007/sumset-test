package co.gov.sdp.spdd.core.iservice.administracion;

import co.gov.sdp.nhspdd.common.dto.ProyectosInversionUsuarioDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;

/**
 * Interfaz para implementar todos las funcionalidades de eliminacion para el
 * modulo de administracion
 *
 * @author Raul Londono Murillo
 *
 */
public interface IProyectoInversionAdmnistracionEliminar {

	/**
	 * Metodo para remover proyectos de inversion de un usuario
	 *
	 * @param peticion Objeto DTO con los datos para remover el proyecto de
	 *                 inversion de un usuario que lo tiene asignado
	 * @return Objeto DTO informando la remocion exitosa del proyecto de inversion
	 *         de los asignados al usuario, sino un objeto vacio con el mensaje de
	 *         error correspondiente
	 */
	public ProyectosInversionUsuarioDTO removerProyectoInversionUsuario(ProyectosInversionUsuarioDTO peticion)
			throws SpddExceptions;
}
