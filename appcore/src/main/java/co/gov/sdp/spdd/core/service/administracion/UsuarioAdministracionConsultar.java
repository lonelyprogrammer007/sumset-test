package co.gov.sdp.spdd.core.service.administracion;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.dto.RespuestaApiDTO;
import co.gov.sdp.nhspdd.common.dto.UsuarioEntidadDTO;
import co.gov.sdp.nhspdd.common.dto.UsuariosDTO;
import co.gov.sdp.nhspdd.common.dto.seguridad.UsuariosSeguridadDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.nhspdd.common.util.NHSPDDMensajes;
import co.gov.sdp.nhspdd.common.util.PaqueteMensajeEnum;
import co.gov.sdp.spdd.core.iservice.administracion.IUsuarioAdmnistracionConsultar;
import co.gov.sdp.spdd.data.sesionfacade.interfaces.ISessionFacadeAFS;
import co.gov.sdp.spdd.util.MetodosRest;

/**
 * Implementacion de las funcionalidades de consulta para el modulo de
 * administracion
 *
 * @author Bryan Munoz
 *
 */
@Service
public class UsuarioAdministracionConsultar implements IUsuarioAdmnistracionConsultar {

	/**
	 * 
	 */
	@Autowired
	MetodosRest<RespuestaApiDTO<UsuariosSeguridadDTO>> metodosRest;

	/**
	 * 
	 */
	@Autowired
	ObjectMapper mapper;

	/**
	 * 
	 */
	@Autowired
	ISessionFacadeAFS sessionFacadeAFS;

	@Autowired
	HttpServletRequest request;

	/**
	 * @throws JsonProcessingException
	 * 
	 */
	@Override
	public GenericoDTO consultarUsuario(UsuariosDTO peticion) throws SpddExceptions, JsonProcessingException {

		GenericoDTO respuesta = new GenericoDTO();
		metodosRest.agregarToken(peticion.getToken());
		RespuestaApiDTO<UsuariosSeguridadDTO> lista = metodosRest.get(
				NHSPDDConstantes.CONTEXTO_SEGURIDAD_API + NHSPDDConstantes.PATH_SEG_API
						+ NHSPDDConstantes.PATH_SEG_API_REST_USUARIOS + "/consultarTodosLosUsuarios?aplicacion=SPDD",
				new ParameterizedTypeReference<RespuestaApiDTO<UsuariosSeguridadDTO>>() {
				});

		respuesta.setLstObjectsDTO(new ArrayList<>(lista.getObjetos()));

		return respuesta;
	}

	/**
	 * 
	 */
	@Override
	public GenericoDTO obtenerPaginado(UsuarioEntidadDTO peticion) throws SpddExceptions {
		GenericoDTO respuesta = sessionFacadeAFS.consultarUsuarioEntidadPorFiltro(peticion);
		respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
		respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(
				NHSPDDConstantes.MENSAJE_OBTENER_TODOS_USUARIO_ADMINISTRACION, PaqueteMensajeEnum.MENSAJES, null));

		return respuesta;
	}

}
