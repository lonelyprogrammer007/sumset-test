package co.gov.sdp.spdd.core.icontroller.administracion;

import com.fasterxml.jackson.core.JsonProcessingException;

import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.dto.UsuariosDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;

/**
 * Interfaz del controlador UsuarioAdministracionController donde se establece
 * los metodos a usar en el proyecto
 *
 * @author Bryan Munoz
 *
 */
public interface IUsuarioAdministracionController {
	
	/**
	 * 
	 * @param peticion
	 * @return
	 * @throws SpddExceptions
	 * @throws JsonProcessingException
	 */
	public GenericoDTO consultarUsuario(UsuariosDTO peticion) throws SpddExceptions, JsonProcessingException;

}
