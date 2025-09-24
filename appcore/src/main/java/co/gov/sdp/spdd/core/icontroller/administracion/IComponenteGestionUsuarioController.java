package co.gov.sdp.spdd.core.icontroller.administracion;

import com.fasterxml.jackson.core.JsonProcessingException;

import co.gov.sdp.nhspdd.common.dto.ComponenteGestionUsuarioDTO;
import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.dto.UsuariosDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;

/**
 * Interfaz de componentes de gestion que implementa los metodos del controlador
 *
 * @author Bryan Munoz
 *
 */
public interface IComponenteGestionUsuarioController {

	/**
	 * Metodo que asigna un componente al usuario
	 *
	 * @param componenteGestionUsuarioDTO tipo componenteGestionUsuarioDTO el cual
	 *                                    trae los datos para asignar un componente
	 * @return un objeto tipo componenteGestionUsuarioDTO con un resultado de
	 *         respuesta y un mensaje de exito o fracaso
	 * @throws SpddExceptions
	 */
	public ComponenteGestionUsuarioDTO asignarComponente(ComponenteGestionUsuarioDTO componenteGestionUsuarioDTO)
			throws SpddExceptions, JsonProcessingException;

	/**
	 * Metodo que remueve un componente de usuario
	 *
	 * @param componenteGestionUsuarioDTO tipo objeto componenteGestionUsuarioDTO
	 *                                    donde se traen los datos para remover un
	 *                                    componente asignado
	 * @return un objeto tipo dto que muestra el eliminado
	 * @throws SpddExceptions
	 */
	public ComponenteGestionUsuarioDTO removerComponenteUsuario(ComponenteGestionUsuarioDTO componenteGestionUsuarioDTO)
			throws SpddExceptions;

	/**
	 * Este metodo obtiene una lista de componentes por usuario
	 *
	 * @param usuariosDTO tipo dto que tiene los datos del usuario
	 * @return una lista de componentes asignados por usuario
	 * @throws SpddExceptions
	 */
	public GenericoDTO obtenerComponenteGestionUsuario(UsuariosDTO usuariosDTO) throws SpddExceptions;

	/**
	 * Este metodo obtiene todos los componentes
	 *
	 * @return una lista de componentes de gestion
	 * @throws SpddExceptions
	 */
	public GenericoDTO obtenerListaComponentesUsuario() throws SpddExceptions;

	/**
	 * Este metodo obtiene todos los componentes libres
	 *
	 * @return una lista con los componentes libres
	 * @throws SpddExceptions
	 */
	public GenericoDTO obtenerComponenteLibre() throws SpddExceptions;
}
