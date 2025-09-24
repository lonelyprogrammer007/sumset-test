package co.gov.sdp.spdd.core.controller.administracion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.dto.UsuariosDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.spdd.core.icontroller.administracion.IUsuarioAdministracionController;
import co.gov.sdp.spdd.core.iservice.administracion.IUsuarioAdmnistracionConsultar;

/**
 * Clase que implementa los metodos para el rest controller de administracion
 * usuarios
 *
 * @author Bryan Munoz
 *
 */
@RestController
public class UsuarioAdministracionController implements IUsuarioAdministracionController {

	/**
	 * 
	 */
	@Autowired
	IUsuarioAdmnistracionConsultar consultar;

	/**
	 * 
	 */
	@PostMapping(NHSPDDConstantes.CORE_CONTROLLER_ADMINISTRACION_POST_CONSULTAR_USUARIOS)
	@Override
	public GenericoDTO consultarUsuario(@RequestBody UsuariosDTO peticion)
			throws SpddExceptions, JsonProcessingException {
		return consultar.consultarUsuario(peticion);
	}

}
