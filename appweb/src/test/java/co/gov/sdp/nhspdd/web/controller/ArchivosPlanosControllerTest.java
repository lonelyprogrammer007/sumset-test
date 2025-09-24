package co.gov.sdp.nhspdd.web.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.multipart.MultipartFile;

import co.gov.sdp.nhspdd.common.dto.CargaAchivoDTO;
import co.gov.sdp.nhspdd.common.dto.PermisoDTO;
import co.gov.sdp.nhspdd.common.dto.PermisoRolEventoDto;
import co.gov.sdp.nhspdd.common.dto.seguridad.UsuariosSeguridadDTO;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.spdd.web.AppwebApplication;

@WebMvcTest
@ContextConfiguration(classes = { AppwebApplication.class })

@TestPropertySource(locations = "classpath:application.properties")
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class ArchivosPlanosControllerTest {

    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
	PermisoRolEventoDto respuestaAutenticacion;
    
    @MockBean
    private HttpHeaders headers;
    
    @Test
    public void testConsultarArchivosPlanos() throws Exception {
    	
		
		
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
		permiso.setUrl("/consultar-archivos");
		permisos.add(permiso);
		respuestaAutenticacion.setListaPermisos(permisos);
		sessionAttributes.put("tokenSesionSeguridad", "token1");
		sessionAttributes.put("usuarioSesion", "sumset");
		sessionAttributes.put("codigoEntidadUsuario", "Entidad 1");
		sessionAttributes.put("respuestaAutenticacion", respuestaAutenticacion);
		sessionAttributes.put("nombreUsuarioSesion", "susmset1");
        mockMvc.perform(get("/consultar-archivos").sessionAttrs(sessionAttributes))
                .andExpect(view().name("afs/archivos-planos/consultar-archivos"));
        
        permiso.setUrl("/consultar");
		permisos.add(permiso);
		respuestaAutenticacion.setListaPermisos(permisos);
		mockMvc.perform(get("/consultar-archivos").sessionAttrs(sessionAttributes))
        .andExpect(view().name("redirect:/home"));
    }

    

    
}
