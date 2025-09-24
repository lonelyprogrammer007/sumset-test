package co.gov.sdp.spdd.core.iservice.administracion;

import com.fasterxml.jackson.core.JsonProcessingException;

import co.gov.sdp.nhspdd.common.dto.ProyectoInversionDTO;
import co.gov.sdp.nhspdd.common.dto.ProyectosInversionUsuarioDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;

/**
 * Interfaz para implementar todos las funcionalidades de registro para el
 * modulo de administracion
 *
 * @author Raul Londono Murillo
 *
 */
public interface IProyectoInversionAdministracionRegistrar {

	/**
	 * Metodo para crear proyectos de inversion
	 *
	 * @param peticion Objeto DTO con los datos para crear el proyecto de inversion
	 * @return Objeto DTO informando la creacion exitosa del proyecto de inversion,
	 *         sino un objeto vacio con el mensaje de error correspondiente
	 * @throws JsonProcessingException 
	 */
	public ProyectoInversionDTO crearProyectoInversion(ProyectoInversionDTO peticion) throws SpddExceptions, JsonProcessingException;

	/**
	 * Metodo para asignar proyectos de inversion a un usuario
	 *
	 * @param peticion Objeto DTO con los datos para asignar el proyecto de
	 *                 inversion al usuario correspondiente
	 * @return Objeto DTO informando la asignacion exitosa del proyecto de inversion
	 *         al usuario, sino un objeto vacio con el mensaje de error
	 *         correspondiente
	 */
	public ProyectosInversionUsuarioDTO asignarProyectosInversionUsuario(ProyectosInversionUsuarioDTO peticion)
			throws SpddExceptions;
}
