package co.gov.sdp.spdd.core.controller.administracion;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.JsonProcessingException;

import co.gov.sdp.nhspdd.common.dto.ComponenteGestionUsuarioDTO;
import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.dto.UsuariosDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.spdd.core.icontroller.administracion.IComponenteGestionUsuarioController;
import co.gov.sdp.spdd.core.iservice.administracion.IComponenteGestionUsuarioConsultar;
import co.gov.sdp.spdd.core.iservice.administracion.IComponenteGestionUsuarioEliminar;
import co.gov.sdp.spdd.core.iservice.administracion.IComponenteGestionUsuarioRegistrar;
import co.gov.sdp.spdd.core.iservice.administracion.IPddNivelAtributoConsultar;

@SpringBootTest(classes = {ComponenteGestionUsuarioController.class})
@RunWith(SpringRunner.class)
class ComponenteGestionUsuarioControllerTest {

	@Autowired
	IComponenteGestionUsuarioController componente;

	@MockBean
	IComponenteGestionUsuarioRegistrar registrar;

	/**
	 * Objeto que maneja los metodos de eliminar de componenteGestionUsuario
	 */
	@MockBean
	IComponenteGestionUsuarioEliminar eliminar;

	/**
	 * Objeto que maneja los metodos de consulta de componenteGestionUsuario
	 */
	@MockBean
	IComponenteGestionUsuarioConsultar consultar;

	/**
	 * Objeto que maneja los metodos de consulta de pddNivelAtributoConsultar
	 */
	@MockBean
	IPddNivelAtributoConsultar consultarNivelAtributo;

	/**
	 * Logger
	 */
	@MockBean
	SPDDLogger spddLogger;

	@Test
	void testAsignarComponente() throws SpddExceptions, JsonProcessingException {
		ComponenteGestionUsuarioDTO componenteGestionUsuarioDTO = new ComponenteGestionUsuarioDTO();
		componenteGestionUsuarioDTO.setIdGestionUsuario(1L);
		componenteGestionUsuarioDTO.setCodigoUsuario("sumset1");
		componenteGestionUsuarioDTO.setIdComponenteGestion(1L);

		ComponenteGestionUsuarioDTO res;
		when(registrar.crearGestionComponenteUsuario(componenteGestionUsuarioDTO))
				.thenReturn(componenteGestionUsuarioDTO);
		res = componente.asignarComponente(componenteGestionUsuarioDTO);
		assertNotNull(res);

		
		when(registrar.crearGestionComponenteUsuario(componenteGestionUsuarioDTO)).thenThrow(new SpddExceptions());
		res = componente.asignarComponente(componenteGestionUsuarioDTO);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);

		componenteGestionUsuarioDTO.setIdGestionUsuario(1L);
		componenteGestionUsuarioDTO.setCodigoUsuario(null);
		componenteGestionUsuarioDTO.setIdComponenteGestion(null);
		res = componente.asignarComponente(componenteGestionUsuarioDTO);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
	}

	@Test
	void testRemoverComponenteUsuario() throws SpddExceptions {
		ComponenteGestionUsuarioDTO componenteGestionUsuarioDTO = new ComponenteGestionUsuarioDTO();
		componenteGestionUsuarioDTO.setIdGestionUsuario(1L);
		componenteGestionUsuarioDTO.setCodigoUsuario("sumset1");
		componenteGestionUsuarioDTO.setIdComponenteGestion(1L);
		
		ComponenteGestionUsuarioDTO res;
		when(eliminar.eliminarComponenteUsuario(componenteGestionUsuarioDTO)).thenReturn(componenteGestionUsuarioDTO);
		res = componente.removerComponenteUsuario(componenteGestionUsuarioDTO);
		assertNotNull(res);
		
		when(eliminar.eliminarComponenteUsuario(componenteGestionUsuarioDTO)).thenThrow(new SpddExceptions());
		res = componente.removerComponenteUsuario(componenteGestionUsuarioDTO);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
		
		componenteGestionUsuarioDTO.setIdGestionUsuario(null);
		componenteGestionUsuarioDTO.setCodigoUsuario(null);
		componenteGestionUsuarioDTO.setIdComponenteGestion(null);
		res = componente.removerComponenteUsuario(componenteGestionUsuarioDTO);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
	}

	@Test
	void testObtenerComponenteGestionUsuario() throws SpddExceptions {
		UsuariosDTO usuariosDTO = new UsuariosDTO();
		usuariosDTO.setCorreo("sumset1@sumset.com");
		usuariosDTO.setNombreUsuario("sumset1");
		usuariosDTO.setClave("clave1");
		usuariosDTO.setContraseniaActual("passActual");
		usuariosDTO.setContraseniaNueva("passNuevo");
		usuariosDTO.setTipo("Tipo1");
		GenericoDTO res;
		
		when(consultar.obtenerPorUsuario(usuariosDTO)).thenReturn(new GenericoDTO());
		res =componente.obtenerComponenteGestionUsuario(usuariosDTO);
		assertNotNull(res);
		
		when(consultar.obtenerPorUsuario(usuariosDTO)).thenThrow(new SpddExceptions());
		res =componente.obtenerComponenteGestionUsuario(usuariosDTO);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
		
		usuariosDTO.setCorreo(null);
		usuariosDTO.setNombreUsuario(null);
		res =componente.obtenerComponenteGestionUsuario(usuariosDTO);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
	}

	@Test
	void testObtenerListaComponentesUsuario() throws SpddExceptions {
		GenericoDTO res;
		GenericoDTO respuesta = new GenericoDTO();
		respuesta.setMsgRespuesta("Exito");
		respuesta.setLenguaje("Es");
		when(consultar.obtenerTodos()).thenReturn(respuesta);
		res= componente.obtenerListaComponentesUsuario();
		assertNotNull(res);
		
		when(consultar.obtenerTodos()).thenThrow(new SpddExceptions());
		res= componente.obtenerListaComponentesUsuario();
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
	}

	@Test
	void testObtenerComponenteLibre() throws SpddExceptions {
		GenericoDTO res;
		GenericoDTO respuesta = new GenericoDTO();
		respuesta.setMsgRespuesta("Exito");
		respuesta.setLenguaje("Es");
		when(consultarNivelAtributo.pddNivelAtributoObtenerLibres()).thenReturn(respuesta);
		res= componente.obtenerComponenteLibre();
		assertNotNull(res);
		
		when(consultarNivelAtributo.pddNivelAtributoObtenerLibres()).thenThrow(new SpddExceptions());
		res= componente.obtenerComponenteLibre();
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
	}

}
