package co.gov.sdp.spdd.core.service.autenticacion;

import org.springframework.stereotype.Service;

import co.gov.sdp.nhspdd.common.dto.UsuariosDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.nhspdd.common.util.NHSPDDMensajes;
import co.gov.sdp.spdd.core.iservice.autenticacion.IAutenticacionConsultar;

/**
 * Implementacion de las funcionalidades de consulta para el modulo de
 * autenticacion
 *
 * @author Raul Londono Murillo
 *
 */
@Service
public class AutenticacionConsultar implements IAutenticacionConsultar {

	/**
	 * Implementacion del metodo iniciarSesion
	 *
	 * @see co.gov.sdp.spdd.core.iservice.autenticacion.IAutenticacionConsultar.iniciarSesion
	 */
	@Override
	public UsuariosDTO iniciarSesion(UsuariosDTO peticion) throws SpddExceptions {
		
		UsuariosDTO respuesta = new UsuariosDTO();
		
		respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
		respuesta.setMsgRespuesta(
				NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_INICIO_SESION_CORRECTO, peticion.getLenguaje()));
		
		return respuesta;
	}

	@Override
	public UsuariosDTO verificarCorreo(UsuariosDTO usuariosDTO) throws SpddExceptions {
		return null;
	}
}
