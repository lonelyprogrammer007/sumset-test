package co.gov.sdp.spdd.core.service.autenticacion;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import co.gov.sdp.nhspdd.common.dto.RespuestaApiDTO;
import co.gov.sdp.nhspdd.common.dto.UsuariosDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.spdd.core.iservice.autenticacion.IAutenticacionModificar;
import co.gov.sdp.spdd.util.MetodosRest;
import co.gov.sdp.spdd.util.ValidacionMensajesError;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { IAutenticacionModificar.class, AutenticacionModificar.class})
public class AutenticacionModificarTest {
	
	@Autowired
	IAutenticacionModificar modificar;
	
	@MockBean
	MetodosRest<RespuestaApiDTO<UsuariosDTO>> metodosRest;

	@MockBean
	ValidacionMensajesError<UsuariosDTO> validacionMensajesError;

	@MockBean
	SPDDLogger spddLogger;
	
	@MockBean
	ObjectMapper mapper;

	@Test
	public void testCambiarClave() throws JsonProcessingException, SpddExceptions {
		
		UsuariosDTO peticion = new UsuariosDTO();
		UsuariosDTO respuesta = new UsuariosDTO();
		String parametro = "parametro";
		RespuestaApiDTO<UsuariosDTO> respuestaApi = new RespuestaApiDTO<>();
		respuestaApi.setExito(true);
		
		
		UsuariosDTO res;
		
		when(mapper.writeValueAsString(peticion)).thenReturn(parametro);
		when(metodosRest.post(parametro,
				new ParameterizedTypeReference<RespuestaApiDTO<UsuariosDTO>>() {
				},
				NHSPDDConstantes.CONTEXTO_SEGURIDAD_API + NHSPDDConstantes.PATH_SEG_API
						+ NHSPDDConstantes.PATH_SEG_API_REST_AUT
						+ NHSPDDConstantes.PATH_SEG_API_REST_AUT_CAMBIAR_CONTRASENIA)).thenReturn(respuestaApi);
		res = modificar.cambiarClave(peticion);
		assertNotNull(res);
        assertThat(res.getCodigoRespuesta()).isEqualTo(200L);
		
		respuestaApi.setExito(false);
		respuestaApi.setCodigo(400);
		respuestaApi.setMensaje("Error");
		when(validacionMensajesError.validarMensajesError(respuesta)).thenReturn(respuesta);
		res = modificar.cambiarClave(peticion);		
		assertNotNull(res);
        assertThat(res.getMsgRespuesta()).isEqualTo("Error");
		
		
	}

	@Test
	public void testReestablecerContrasenia() throws JsonProcessingException, SpddExceptions {
		UsuariosDTO peticion = new UsuariosDTO();
		UsuariosDTO respuesta = new UsuariosDTO();
		String parametro = "parametro";
		RespuestaApiDTO<UsuariosDTO> respuestaApi = new RespuestaApiDTO<>();
		respuestaApi.setExito(true);		
		UsuariosDTO res;
		
		when(mapper.writeValueAsString(peticion)).thenReturn(parametro);
		when(metodosRest.post(parametro,
				new ParameterizedTypeReference<RespuestaApiDTO<UsuariosDTO>>() {
				},
				NHSPDDConstantes.CONTEXTO_SEGURIDAD_API + NHSPDDConstantes.PATH_SEG_API
						+ NHSPDDConstantes.PATH_SEG_API_REST_AUT
						+ NHSPDDConstantes.PATH_SEG_API_REST_AUT_RECORDAR_CONTRASENIA)).thenReturn(respuestaApi);
		res = modificar.reestablecerContrasenia(peticion);
		assertNotNull(res);
        assertThat(res.getCodigoRespuesta()).isEqualTo(200L);
        
        respuestaApi.setExito(false);
		respuestaApi.setCodigo(400);
		respuestaApi.setMensaje("Error");
		when(validacionMensajesError.validarMensajesError(respuesta)).thenReturn(respuesta);
		res = modificar.reestablecerContrasenia(peticion);		
		assertNotNull(res);
	}

}
