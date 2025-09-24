package co.gov.sdp.spdd.util;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import co.gov.sdp.nhspdd.common.dto.RespuestaApiDTO;
import co.gov.sdp.nhspdd.common.dto.seguridad.AuditoriaDTO;
import co.gov.sdp.nhspdd.common.dto.seguridad.UsuariosSeguridadDTO;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.spdd.core.seguridad.JWTUtil;

@Component
public class AuditoriaUtil {

	@Autowired
	HttpServletRequest request;
	@Autowired
	JWTUtil jwtUtil;

	@Autowired
	MetodosRest<RespuestaApiDTO<UsuariosSeguridadDTO>> auditoriaRest;
	@Autowired
	ObjectMapper mapper;

	public void crearAuditoria(String peticionOriginal, String peticionModificada, String transaccion, String evento)
			throws JsonProcessingException {
		AuditoriaDTO auditoriaDTO = new AuditoriaDTO();
		auditoriaDTO.setValorOriginal(peticionOriginal);
		auditoriaDTO.setValorModificado(peticionModificada);
		auditoriaDTO.setAplicacion("SPDD");
		auditoriaDTO.setNombreTransaccion(transaccion);
		auditoriaDTO.setNombreEvento(evento);
		String token = request.getHeader("Authorization");
		String sub = token.substring(6, token.length());
		auditoriaRest.agregarToken(sub);
		String nombreUsuario = jwtUtil.getNombreUsuarioJWT(sub);
		auditoriaDTO.setNombreUsuario(nombreUsuario);
		String parametro = mapper.writeValueAsString(auditoriaDTO);
		auditoriaRest.post(parametro, new ParameterizedTypeReference<RespuestaApiDTO<UsuariosSeguridadDTO>>() {
		}, NHSPDDConstantes.CONTEXTO_SEGURIDAD_API + NHSPDDConstantes.PATH_SEG_API
				+ NHSPDDConstantes.PATH_SEG_API_REST_AUDITORIA + NHSPDDConstantes.PATH_SEG_API_REST_CREAR_AUDITORIA);

	}
}
