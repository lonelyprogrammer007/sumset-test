package co.gov.sdp.spdd.core.icontroller.administracion;

import com.fasterxml.jackson.core.JsonProcessingException;

import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.dto.ProyectoInversionDTO;
import co.gov.sdp.nhspdd.common.dto.ProyectosInversionUsuarioDTO;
import co.gov.sdp.nhspdd.common.dto.UsuariosDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;

/**
 * Interface para definir los endpoints de los proyectos de inversion para el
 * modulo de administracion
 *
 * @author Raul Londono Murillo
 *
 */
public interface IProyectoInversionAdministracionController {

	/**
	 * Metodo para asignar un proyecto de inversion a un usuario
	 *
	 * @param peticion Objeto DTO que contiene la informacion del usuario a
	 *                 asignarle el proyecto de inversion y el proyecto de inversion
	 *                 a asignar
	 * @return Objeto DTO que contiene la informacion del proyecto de inversion y el
	 *         usuario asignado si la peticion se realiza satisfactoriamente, sino,
	 *         se enviara el objeto vacio con el respectivo mensaje de error
	 */
	public ProyectosInversionUsuarioDTO asignarProyectoInversion(ProyectosInversionUsuarioDTO peticion)
			throws SpddExceptions;

	/**
	 * Metodo para crear un proyecto de inversion
	 *
	 * @param peticion Objeto DTO que contiene la informacion del proyecto de
	 *                 inversion
	 * @return Objeto DTO que contiene la informacion del proyecto de inversion
	 *         creado si la peticion se realiza satisfactoriamente, sino, se enviara
	 *         el objeto vacio con el respectivo mensaje de error
	 * @throws JsonProcessingException 
	 */
	public ProyectoInversionDTO crearProyectoInversion(ProyectoInversionDTO peticion) throws SpddExceptions, JsonProcessingException;

	/**
	 * Metodo para remover una signacion de un proyecto de inversion para un usuario
	 *
	 * @param peticion Objeto DTO que contiene la informacion del usuario al cual
	 *                 removerle la asignacion el proyecto de inversion y el
	 *                 proyecto de inversion a remover
	 * @return Objeto DTO que contiene la informacion del usuario y el proyecto de
	 *         inversion removido si la peticion se realiza satisfactoriamente,
	 *         sino, se enviara el objeto vacio con el respectivo mensaje de error
	 */
	public ProyectosInversionUsuarioDTO removerAsignacionProyectoInversion(ProyectosInversionUsuarioDTO peticion)
			throws SpddExceptions;

	/**
	 * Metodo que trae la lista completa de proyectos de inversion que el usuario
	 * tiene asignado
	 *
	 * @param usuariosDTO usuario para consultar
	 * @return Objeto DTO generico que contiene la lista de los proyectos de
	 *         inversion que el usuario tiene asignado
	 */
	public GenericoDTO obtenerProyectosInversionAsignados(UsuariosDTO peticion) throws SpddExceptions;

	/**
	 * Metodo que trae la lista de los proyectos de inversion que el usuario puede
	 * ver
	 *
	 * @return Objeto DTO generico que contiene la lista de los proyectos de
	 *         inversion que el usuario puede ver
	 */
	public GenericoDTO obtenerProyectosInversionDisponibles() throws SpddExceptions;

	/**
	 * Metodo que trae la lista completa de proyectos de inversion que el usuario
	 * puede ver
	 *
	 * @return Objeto DTO generico que contiene la lista de los proyectos de
	 *         inversion que el usuario puede ver
	 */
	public GenericoDTO obtenerProyectosInversionTodos() throws SpddExceptions;
}
