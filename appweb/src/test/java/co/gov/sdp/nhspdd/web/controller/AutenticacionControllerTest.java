package co.gov.sdp.nhspdd.web.controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
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

import co.gov.sdp.nhspdd.common.dto.DatosToken;
import co.gov.sdp.nhspdd.common.dto.PermisoDTO;
import co.gov.sdp.nhspdd.common.dto.PermisoRolEventoDto;
import co.gov.sdp.nhspdd.common.dto.RespuestaApiDTO;
import co.gov.sdp.nhspdd.common.dto.UsuariosDTO;
import co.gov.sdp.nhspdd.common.payload.DatosLogin;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.spdd.web.AppwebApplication;
import co.gov.sdp.spdd.web.util.MetodosRest;

@WebMvcTest
@ContextConfiguration(classes = {AppwebApplication.class})
@TestPropertySource(locations="classpath:application.properties")
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class AutenticacionControllerTest {

    @Autowired
    private MockMvc mockMvc;
    
	
	@Autowired
	ObjectMapper mapper;

    @Test
    public void testIndex() throws Exception {
        mockMvc.perform(get("/")).andExpect(status().isOk())
                .andExpect(view().name("index"));
    }

    @Test
    public void testRestablecer() throws Exception {
        mockMvc.perform(get(NHSPDDConstantes.WEB_CONTROLLER_GET_RESTABLECER_CONTRASENA)).andExpect(status().isOk())
                .andExpect(view().name("afs/autenticacion/restablecer-contrasena"));

    }

    @Test
    public void testHome() throws Exception,NullPointerException {
    	PermisoRolEventoDto respuestaAutenticacion = new PermisoRolEventoDto();
    	respuestaAutenticacion.setCodigoAplicacion("1");
    	respuestaAutenticacion.setCodigoRol("1");
    	respuestaAutenticacion.setCodigoUsuario("4");
    	respuestaAutenticacion.setCorreo("sumset@sumset.com");
    	respuestaAutenticacion.setNombreAplicacion("APP1");
    	respuestaAutenticacion.setNombreRol("Rol1");
    	respuestaAutenticacion.setIdentificacion("12345");
    	Map<String, Object> sessionAttributes = new HashMap<>();
    	
    		
        	sessionAttributes.put("tokenSesionSeguridad", "token1");
        	sessionAttributes.put("usuarioSesion", "sumset");
        	sessionAttributes.put("codigoEntidadUsuario", "Entidad 1");
    		sessionAttributes.put("respuestaAutenticacion", respuestaAutenticacion);
    		sessionAttributes.put("nombreUsuarioSesion", "susmset1" );
    		MetodosRest<UsuariosDTO> apiUsuarios= mock(MetodosRest.class);
    	
    	
        mockMvc.perform(get(NHSPDDConstantes.WEB_CONTROLLER_GET_HOME).sessionAttrs(sessionAttributes)).andExpect(status().isOk())
                .andExpect(view().name(NHSPDDConstantes.WEB_CONTROLLER_RETURN_HOME));

    }
    
    @Test
    public void testInicio() throws Exception {
    	DatosLogin datos = new DatosLogin();
    	datos.setNombreUsuario("sumset");
    	datos.setContrasenia("123sumset");
    	datos.setTipoUsuario("admin");
    	datos.setAplicacion("1");
    	datos.setTipoUsuario("E");
    	String parametros = mapper.writeValueAsString(datos);
    	RespuestaApiDTO<DatosToken> respuesta = new RespuestaApiDTO<>();
    	respuesta.setExito(false);
    	MetodosRest<RespuestaApiDTO<DatosToken>> api = mock(MetodosRest.class);
    	when(api.post(parametros,
				new ParameterizedTypeReference<RespuestaApiDTO<DatosToken>>() {
				}, "/api/autenticacion/autenticar", NHSPDDConstantes.TIPO_SEGURIDAD)).thenReturn(respuesta);
    	mockMvc.perform(post("/iniciar-sesion").content(parametros)).andExpect(forwardedUrl(null));
    	
    	respuesta.setExito(true);
    	List<DatosToken> datosToken = new ArrayList<DatosToken>();
    	DatosToken datoToken = new DatosToken();
    	datoToken.setCodigoEntidad("entidad 1");
    	datoToken.setNombreUsuario("sumset");
    	datoToken.setToken("token1");
    	PermisoRolEventoDto permisos = new PermisoRolEventoDto();
    	permisos.setCodigoAplicacion("1");
    	permisos.setCodigoRol("1");
    	permisos.setCodigoUsuario("12");
    	permisos.setCorreo("sumset@sumset.com");
    	permisos.setIdentificacion("12344");
    	permisos.setNombreAplicacion("app1");
    	permisos.setUsuario("sumset");
    	permisos.setNombreRol("rol1");
    	permisos.setNombreUsuario("sumset1");
    	permisos.setUsuario("sumset1");
    	List<PermisoDTO> permisosRol = new ArrayList<PermisoDTO>();
       	PermisoDTO permiso = new PermisoDTO();
    	permiso.setCodigo(1);
    	permiso.setCodigoAplicacion("1");
    	permiso.setToken("token1");
    	permiso.setNombre("permiso1");
    	permiso.setCodigoPermiso("1");
    	permiso.setEstado('1');
    	permiso.setPadre("padre1");
    	permiso.setUrl("/borrar");
    	permisosRol.add(permiso);
    	permisos.setListaPermisos(permisosRol);
    	datoToken.setPermisos(permisos);
    	datosToken.add(datoToken);
    	respuesta.setObjetos(datosToken);
    	
    	
    	List<DatosToken> dt = respuesta.getObjetos();
    	
    	
    	dt.get(0).setPermisos(permisos);
    	
    	when(api.post(parametros,
				new ParameterizedTypeReference<RespuestaApiDTO<DatosToken>>() {
				}, "/api/autenticacion/autenticar", NHSPDDConstantes.TIPO_SEGURIDAD)).thenReturn(respuesta);
    	
    	mockMvc.perform(post("/iniciar-sesion").content(parametros)).andExpect(forwardedUrl(null));
    }
}
