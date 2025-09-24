package co.gov.sdp.spdd.core.service.administracion;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.dto.RespuestaApiDTO;
import co.gov.sdp.nhspdd.common.dto.UsuarioEntidadDTO;
import co.gov.sdp.nhspdd.common.dto.UsuariosDTO;
import co.gov.sdp.nhspdd.common.dto.seguridad.UsuariosSeguridadDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.spdd.core.iservice.administracion.IUsuarioAdmnistracionConsultar;
import co.gov.sdp.spdd.data.sesionfacade.interfaces.ISessionFacadeAFS;
import co.gov.sdp.spdd.util.MetodosRest;
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { ISessionFacadeAFS.class, UsuarioAdministracionConsultar.class,
		IUsuarioAdmnistracionConsultar.class })
class UsuarioAdministracionConsultarTest {
	
	@Autowired
	IUsuarioAdmnistracionConsultar consultar;
	
	@MockBean
	MetodosRest<RespuestaApiDTO<UsuariosSeguridadDTO>> metodosRest;

	/**
	 * 
	 */
	@MockBean
	ObjectMapper mapper;

	/**
	 * 
	 */
	@MockBean
	ISessionFacadeAFS sessionFacadeAFS;
	
	@Test
	void testConsultarUsuario() throws JsonProcessingException, SpddExceptions {
		UsuariosDTO peticion = new UsuariosDTO();
		peticion.setToken("token1");
		RespuestaApiDTO<UsuariosSeguridadDTO> lista = new RespuestaApiDTO<>();
		List<UsuariosSeguridadDTO> usuarios = new ArrayList<>();
		lista.setObjetos(usuarios);
		when(metodosRest.getHeaders()).thenReturn(new HttpHeaders());
		when(metodosRest.get(
				NHSPDDConstantes.CONTEXTO_SEGURIDAD_API + NHSPDDConstantes.PATH_SEG_API
						+ NHSPDDConstantes.PATH_SEG_API_REST_USUARIOS + "/consultarTodosLosUsuarios?aplicacion=SPDD",
				new ParameterizedTypeReference<RespuestaApiDTO<UsuariosSeguridadDTO>>() {
				})).thenReturn(lista);
		GenericoDTO res = consultar.consultarUsuario(peticion);
	
		assertNotNull(res);
	}

	@Test
	void testObtenerPaginado() throws SpddExceptions {
		UsuarioEntidadDTO peticion = new UsuarioEntidadDTO();
		when(sessionFacadeAFS.consultarUsuarioEntidadPorFiltro(peticion)).thenReturn(new GenericoDTO());
		GenericoDTO res = consultar.obtenerPaginado(peticion);
		assertNotNull(res);
	}

}
