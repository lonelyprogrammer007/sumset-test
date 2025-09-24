package co.gov.sdp.nhspdd.web.controller;


import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import co.gov.sdp.nhspdd.common.dto.ComponenteGestionUsuarioDTO;
import co.gov.sdp.nhspdd.common.dto.EntidadDTO;
import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.dto.PermisoDTO;
import co.gov.sdp.nhspdd.common.dto.PermisoRolEventoDto;
import co.gov.sdp.nhspdd.common.dto.ProyectosInversionUsuarioDTO;
import co.gov.sdp.nhspdd.common.dto.RespuestaApiDTO;
import co.gov.sdp.nhspdd.common.dto.UsuarioEntidadDTO;
import co.gov.sdp.nhspdd.common.dto.UsuariosDTO;
import co.gov.sdp.nhspdd.common.dto.seguridad.EntidadSeguridadDTO;
import co.gov.sdp.nhspdd.common.dto.seguridad.RolSeguridadDTO;
import co.gov.sdp.nhspdd.common.dto.seguridad.UsuariosSeguridadDTO;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.spdd.web.AppwebApplication;
import co.gov.sdp.spdd.web.util.MetodosRest;

@WebMvcTest
@ContextConfiguration(classes = { AppwebApplication.class })
@TestPropertySource(locations = "classpath:application.properties")
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)

public class AdministracionUsuariosControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	MetodosRest<GenericoDTO> dto;


	@MockBean
	MetodosRest<GenericoDTO> proyectoRest;



	@Autowired
	ObjectMapper mapper;

	@Autowired
	private MetodosRest<EntidadDTO> entidadRest;
	
	@MockBean
	private MetodosRest<RespuestaApiDTO<EntidadSeguridadDTO>> api;
	
	

	@MockBean
	private MetodosRest<UsuarioEntidadDTO> usuarioRest;

	TestRestTemplate rest;

	@MockBean
	PermisoRolEventoDto respuestaAutenticacion;

	@Before
	public final void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}


	@Test
	public void testGestionUsuarios() throws Exception {

		UsuariosSeguridadDTO user = new UsuariosSeguridadDTO();
		user.setCodigoUsuario("sumset1");
		EntidadSeguridadDTO entidadSeg = new EntidadSeguridadDTO();
		EntidadDTO entidad = new EntidadDTO();
		entidadSeg.setCodigoDistrital("1234");

		user.setSegEntidad(entidadSeg);

		when(entidadRest.get("/administracion/obtener_por_id/" + user.getSegEntidad().getCodigoDistrital(),
				new ParameterizedTypeReference<EntidadDTO>() {
				}, NHSPDDConstantes.TIPO_CORE)).thenReturn(entidad);

		mockMvc.perform(post("/gestion-usuario").param("segEntidad.codigoDistrital", entidadSeg.getCodigoDistrital()))
				.andExpect(view().name("redirect:/editar-gestion"));
	}

	@Test
	public void testEditarGestionUsuarios() throws Exception {
		UsuariosSeguridadDTO usuarioSesion = new UsuariosSeguridadDTO();
		usuarioSesion.setUsuario("sumset1");
		usuarioSesion.setCorreoElectronico("susmet@sumset");
		RolSeguridadDTO segRol = new RolSeguridadDTO();
		segRol.setCodigoRol("Rol1");
		usuarioSesion.setSegRol(segRol);
		EntidadSeguridadDTO segEntidad = new EntidadSeguridadDTO();
		segEntidad.setCodigoDistrital("1234");
		segEntidad.setCodigoEntidad("Entidad 1");
		
		usuarioSesion.setSegEntidad(segEntidad);
		
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
		permiso.setUrl("/admin-usuario");
		permisos.add(permiso);
		respuestaAutenticacion.setListaPermisos(permisos);
		sessionAttributes.put("tokenSesionSeguridad", "token1");
		sessionAttributes.put("usuarioSesion", "sumset");
		sessionAttributes.put("codigoEntidadUsuario", "Entidad 1");
		sessionAttributes.put("respuestaAutenticacion", respuestaAutenticacion);
		sessionAttributes.put("nombreUsuarioSesion", "susmset1");
		RespuestaApiDTO<EntidadSeguridadDTO> res = new RespuestaApiDTO<EntidadSeguridadDTO>();
		when(api.get("/api/entidad/consultartodos",
				new ParameterizedTypeReference<RespuestaApiDTO<EntidadSeguridadDTO>>() {
				}, NHSPDDConstantes.TIPO_SEGURIDAD)).thenReturn(res);
		UsuariosDTO usuario = new UsuariosDTO();
		usuario.setNombreUsuario(usuarioSesion.getUsuario());
	
		GenericoDTO res1 = new GenericoDTO();
		when(dto.get(
				NHSPDDConstantes.CORE_CONTROLLER_ADMINISTRACION_GET_OBTENER_PROYECTOS_INVERSION_DISPONIBLES,
				new ParameterizedTypeReference<GenericoDTO>() {
				}, NHSPDDConstantes.TIPO_CORE)).thenReturn(res1);
		GenericoDTO res2 = new GenericoDTO();
		when(dto.get(NHSPDDConstantes.CORE_CONTROLLER_ADMINISTRACION_OBTENER_COMPONENTE_LIBRE,
				new ParameterizedTypeReference<GenericoDTO>() {
				}, NHSPDDConstantes.TIPO_CORE)).thenReturn(res2);
		String parametros = mapper.writeValueAsString(usuario);
		GenericoDTO entidad = new GenericoDTO();
		when(dto.post(parametros, new ParameterizedTypeReference<GenericoDTO>() {
		}, NHSPDDConstantes.CORE_CONTROLLER_ADMINISTRACION_GET_OBTENER_ENTIDADES_ASIGNADOS, NHSPDDConstantes.TIPO_CORE)).thenReturn(entidad);
		GenericoDTO res3 = new GenericoDTO();
		when(dto.post(parametros, new ParameterizedTypeReference<GenericoDTO>() {
		}, NHSPDDConstantes.CORE_CONTROLLER_ADMINISTRACION_OBTENER_COMPONENTES_USUARIO, NHSPDDConstantes.TIPO_CORE)).thenReturn(res3);
		GenericoDTO res4 = new GenericoDTO();
		when(dto.post(parametros, new ParameterizedTypeReference<GenericoDTO>() {
		}, NHSPDDConstantes.CORE_CONTROLLER_ADMINISTRACION_GET_OBTENER_PROYECTOS_INVERSION_ASIGNADOS,
				NHSPDDConstantes.TIPO_CORE)).thenReturn(res4);
		sessionAttributes.put("usuario", usuarioSesion);
		mockMvc.perform(get("/editar-gestion").sessionAttrs(sessionAttributes))
				.andExpect(view().name("afs/admin-usuarios/gestion-usuarios"));


		when(dto.get(NHSPDDConstantes.CORE_CONTROLLER_ADMINISTRACION_GET_OBTENER_PROYECTOS_INVERSION_DISPONIBLES,
				new ParameterizedTypeReference<GenericoDTO>() {
				}, NHSPDDConstantes.TIPO_CORE)).thenReturn(new GenericoDTO());
		when(dto.get(NHSPDDConstantes.CORE_CONTROLLER_ADMINISTRACION_OBTENER_COMPONENTE_LIBRE,
				new ParameterizedTypeReference<GenericoDTO>() {
				}, NHSPDDConstantes.TIPO_CORE)).thenReturn(new GenericoDTO());

		mockMvc.perform(get("/gestion-usuario")).andExpect(status().isOk())
				.andExpect(view().name("admin-usuarios/gestion-usuarios"));

	}

	@Test
	public void testAdministracionUsuarios() throws Exception {


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
		permiso.setUrl("/admin-usuario");
		permisos.add(permiso);
		respuestaAutenticacion.setListaPermisos(permisos);
		sessionAttributes.put("tokenSesionSeguridad", "token1");
		sessionAttributes.put("usuarioSesion", "sumset");
		sessionAttributes.put("codigoEntidadUsuario", "Entidad 1");
		sessionAttributes.put("respuestaAutenticacion", respuestaAutenticacion);
		sessionAttributes.put("nombreUsuarioSesion", "susmset1");


		UsuariosDTO usuario = new UsuariosDTO();
		usuario.setToken("token1");
		usuario.setNombreUsuario("sumset");
		String parametro = mapper.writeValueAsString(usuario);

		GenericoDTO res = new GenericoDTO();

		Mockito.when(dto.getHeaders()).thenReturn(new HttpHeaders());
		when(dto.post(parametro, new ParameterizedTypeReference<GenericoDTO>() {
		}, NHSPDDConstantes.CORE_CONTROLLER_ADMINISTRACION_POST_CONSULTAR_USUARIOS, NHSPDDConstantes.TIPO_CORE))
				.thenReturn(res);

		mockMvc.perform(get("/admin-usuario").sessionAttrs(sessionAttributes))
				.andExpect(view().name("afs/admin-usuarios/adm-usuarios"));

		permiso.setCodigo(1);
		permiso.setCodigoAplicacion("1");
		permiso.setCodigoPermiso("1");
		permiso.setCodigoRespuesta(1);
		permiso.setEstado('1');
		permiso.setUrl("/a");
		permisos.add(permiso);
		respuestaAutenticacion.setListaPermisos(permisos);
		mockMvc.perform(get("/admin-usuario").sessionAttrs(sessionAttributes)).andExpect(view().name("redirect:/home"));


		Mockito.when(dto.getHeaders()).thenReturn(new HttpHeaders());

		Mockito.when(dto.post(parametro, new ParameterizedTypeReference<GenericoDTO>() {
		}, NHSPDDConstantes.CORE_CONTROLLER_ADMINISTRACION_POST_CONSULTAR_USUARIOS, NHSPDDConstantes.TIPO_CORE))
				.thenReturn(res);

		mockMvc.perform(get("/admin-usuario").header("Authorization", NHSPDDConstantes.TOKEN_PREFIX + "token1")
				.sessionAttrs(sessionAttributes)).andExpect(view().name("afs/admin-usuarios/adm-usuarios"));

	}

	@Test
	public void testAsignarProyectoInversion() throws Exception {

//		Map<String, Object> sessionAttributes = new HashMap<>();
//
//		ProyectosInversionUsuarioDTO inversion = new ProyectosInversionUsuarioDTO();
//
//
//		sessionAttributes.put("tokenSesionSeguridad", "token1");
//		sessionAttributes.put("usuarioSesion", "sumset");
//		sessionAttributes.put("codigoEntidadUsuario", "Entidad 1");
//		sessionAttributes.put("usuario", new UsuariosSeguridadDTO());
//		sessionAttributes.put("nombreUsuarioSesion", "susmset1");
//
//		inversion.setCodigoUsuario((String) sessionAttributes.get("usuarioSesion"));
//		inversion.setIdProyectoInversion(1L);
//		String parametro = mapper.writeValueAsString(inversion);
//		
//		when(proyectoRest.post(parametro, new ParameterizedTypeReference<ProyectosInversionUsuarioDTO>() {
//		}, NHSPDDConstantes.CORE_CONTROLLER_ADMINISTRACION_POST_ASIGNAR_PROYECTO_INVERSION, NHSPDDConstantes.TIPO_CORE))
//				.thenReturn(inversion);
//		mockMvc.perform(post("/asignarProyectos").sessionAttrs(sessionAttributes))
//				.andExpect(view().name("redirect:/editar-gestion"));
	}

	@Test
	public void testAsignarComponentesGestion() throws Exception {
//		ComponenteGestionUsuarioDTO gestion = new ComponenteGestionUsuarioDTO();
//		gestion.setIdComponenteGestion(1L);
//		String parametro = mapper.writeValueAsString(gestion);
//
//		when(componenteRest.post(parametro, new ParameterizedTypeReference<ComponenteGestionUsuarioDTO>() {
//		}, NHSPDDConstantes.CORE_CONTROLLER_POST_ASIGNAR_COMPONENTE_GESTION_USUARIO, NHSPDDConstantes.TIPO_CORE))
//				.thenReturn(gestion);
//
//		mockMvc.perform(post("/asignarComponentes")).andExpect(view().name("redirect:/editar-gestion"));
	}

	@Test
	public void testAsignarEntidades() throws Exception {
		

//		UsuarioEntidadDTO user = new UsuarioEntidadDTO();
//		user.setCodigoEntidad("asasd");
//		String parametro = mapper.writeValueAsString(user);
//		UsuarioEntidadDTO entidadUsuario = new UsuarioEntidadDTO();
//		when(usuarioRest.post(parametro, new ParameterizedTypeReference<UsuarioEntidadDTO>() {
//		}, NHSPDDConstantes.CORE_CONTROLLER_ADMINISTRACION_POST_ASIGNAR_ENTIDAD, NHSPDDConstantes.TIPO_CORE))
//				.thenReturn(entidadUsuario);
//		mockMvc.perform(post("/asignarEntidades").content(parametro))
//				.andExpect(view().name("redirect:/editar-gestion"));
//
//		mockMvc.perform(post("/asignarProyectos").sessionAttrs(sessionAttributes)).andExpect(status().isOk())
//				.andExpect(view().name("admin-usuarios/adm-usuarios"));

	}

}
