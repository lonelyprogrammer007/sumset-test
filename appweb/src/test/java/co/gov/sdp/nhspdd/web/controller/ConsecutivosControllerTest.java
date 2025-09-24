package co.gov.sdp.nhspdd.web.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import co.gov.sdp.nhspdd.common.dto.ConsecutivoDTO;
import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.dto.PermisoDTO;
import co.gov.sdp.nhspdd.common.dto.PermisoRolEventoDto;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.spdd.web.AppwebApplication;
import co.gov.sdp.spdd.web.util.MetodosRest;

@WebMvcTest
@ContextConfiguration(classes = { AppwebApplication.class })

@TestPropertySource(locations = "classpath:application.properties")
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class ConsecutivosControllerTest {

	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private MetodosRest<GenericoDTO> dto;
	
	@MockBean
	PermisoRolEventoDto respuestaAutenticacion;
	
	@MockBean
	private MetodosRest<ConsecutivoDTO> restconse;
	
	@MockBean
	ConsecutivoDTO conRes;
	
	@Autowired
	ObjectMapper mapper;

	@Test
	public void testConsultarConsecutivo() throws Exception {
		Map<String, Object> sessionAttributes = new HashMap<>();
		respuestaAutenticacion = new PermisoRolEventoDto();
		respuestaAutenticacion.setCodigoAplicacion("1");
		respuestaAutenticacion.setCodigoRol("1");
		respuestaAutenticacion.setCodigoUsuario("4");
		respuestaAutenticacion.setCorreo("sumset@sumset.com");
		respuestaAutenticacion.setNombreAplicacion("APP1");
		respuestaAutenticacion.setNombreRol("Rol1");
		respuestaAutenticacion.setIdentificacion("12345");
		List<PermisoDTO> permisos = new ArrayList<PermisoDTO>();
		PermisoDTO permiso = new PermisoDTO();
		permiso.setCodigo(1);
		permiso.setCodigoAplicacion("1");
		permiso.setCodigoPermiso("1");
		permiso.setCodigoRespuesta(1);
		permiso.setEstado('1');
		permiso.setUrl("/consultar-consecutivo");
		permisos.add(permiso);
		respuestaAutenticacion.setListaPermisos(permisos);
		sessionAttributes.put("tokenSesionSeguridad", "token1");
		sessionAttributes.put("usuarioSesion", "sumset");
		sessionAttributes.put("codigoEntidadUsuario", "Entidad 1");
		sessionAttributes.put("respuestaAutenticacion", respuestaAutenticacion);
		sessionAttributes.put("nombreUsuarioSesion", "susmset1");
		GenericoDTO entidades = new GenericoDTO();
		when(dto.get(
				NHSPDDConstantes.CORE_CONTROLLER_ADMINISTRACION_GET_OBTENER_ENTIDADES_TODOS,
				new ParameterizedTypeReference<GenericoDTO>() {
				}, NHSPDDConstantes.TIPO_CORE)).thenReturn(entidades);
		mockMvc.perform(get("/consultar-consecutivo").sessionAttrs(sessionAttributes))
				.andExpect(view().name("afs/parametros-consecutivos/consultar-consecutivo"));
		
		permiso.setUrl("/consultar");
		permisos.add(permiso);
		respuestaAutenticacion.setListaPermisos(permisos);
		mockMvc.perform(get("/consultar-consecutivo").sessionAttrs(sessionAttributes))
		.andExpect(view().name("redirect:/home"));
	}

	@Test
	public void testEditarConsecutivo() throws Exception {
		ConsecutivoDTO con = new ConsecutivoDTO();
		con.setIdConsecutivo(1L);
		con.setCodigoEntidad("Entidad 1");
		con.setDescripcion("Descripcion");
		con.setNombre("nombre 1");
		con.setSecuencia(1L);
		con.setVigencia("vigente");
		con.setIdPlanDesarrollo(1L);
		
		conRes=new ConsecutivoDTO();
		when(restconse.put(con, ConsecutivoDTO.class,
				NHSPDDConstantes.CORE_CONTROLLER_ADMINISTRACION_LISTA_SIMPLE_MODIFICAR_CONSECUTIVO,
				NHSPDDConstantes.TIPO_CORE)).thenReturn(conRes);
		mockMvc.perform(post("/editarConsecutivo").param("idConsecutivo", String.valueOf(con.getCodigo()))
				.param("descripcion", con.getDescripcion())
				.param("codigoEntidad", con.getCodigoEntidad())
				.param("nombre", con.getNombre())
				.param("secuencia", String.valueOf(con.getSecuencia()))
				.param("vigencia", con.getVigencia())
				.param("idPlanDesarrollo", String.valueOf(con.getIdPlanDesarrollo()))).andExpect(redirectedUrl("/consultar-consecutivo"));

	}

}
