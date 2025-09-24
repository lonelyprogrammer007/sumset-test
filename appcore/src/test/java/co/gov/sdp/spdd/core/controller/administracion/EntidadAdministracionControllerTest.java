package co.gov.sdp.spdd.core.controller.administracion;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.JsonProcessingException;

import co.gov.sdp.nhspdd.common.dto.EntidadDTO;
import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.dto.UsuarioEntidadDTO;
import co.gov.sdp.nhspdd.common.dto.UsuariosDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.spdd.core.icontroller.administracion.IEntidadAdministracionController;
import co.gov.sdp.spdd.core.iservice.administracion.IBancoDeProyectoConsultar;
import co.gov.sdp.spdd.core.iservice.administracion.IEntidadAdministracionConsultar;
import co.gov.sdp.spdd.core.iservice.administracion.IEntidadAdministracionEliminar;
import co.gov.sdp.spdd.core.iservice.administracion.IEntidadAdministracionRegistrar;
@SpringBootTest(classes = EntidadAdministracionController.class)
@RunWith(SpringRunner.class)
class EntidadAdministracionControllerTest {
	
	@Autowired
	IEntidadAdministracionController entidad;
	
	@MockBean
	IEntidadAdministracionConsultar consultar;

	@MockBean
	IBancoDeProyectoConsultar consultarBanco;
	// Servicio de eliminacion para entidad
	@MockBean
	IEntidadAdministracionEliminar eliminar;

	// Servicio de registro para entidad
	@MockBean
	IEntidadAdministracionRegistrar registrar;

	@MockBean
	SPDDLogger spddLogger;

	
	@Test
	void testAsignarEntidad() throws SpddExceptions, JsonProcessingException {
		UsuarioEntidadDTO res;
		UsuarioEntidadDTO peticion = new UsuarioEntidadDTO();
		when(registrar.asignarUsuarioEntidad(peticion)).thenReturn(peticion);
		res = entidad.asignarEntidad(peticion);
		assertNotNull(res);
	}

	@Test
	void testCrearEntidad() throws SpddExceptions {
		EntidadDTO res;
		EntidadDTO peticion = new EntidadDTO();
		peticion.setCodigoEntidad("entidad1");
		peticion.setGestionProyectos(1);
		peticion.setGestionUsuarios(1);
		peticion.setIdLsClasificacion(1L);
		peticion.setIdLsAsociacion(1L);
		peticion.setIdBancoProyecto(1L);
		
		when(registrar.crearEntidad(peticion)).thenReturn(peticion);
		res = entidad.crearEntidad(peticion);
		assertNotNull(res);
		
		when(registrar.crearEntidad(peticion)).thenThrow(new SpddExceptions());
		res = entidad.crearEntidad(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
		
		peticion.setIdLsAsociacion(null);
		peticion.setIdBancoProyecto(null);
		res = entidad.crearEntidad(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
	}

	@Test
	void testRemoverAsignacionEntidad() throws SpddExceptions, JsonProcessingException {
		UsuarioEntidadDTO peticion = new UsuarioEntidadDTO();
		UsuarioEntidadDTO res;
		peticion.setIdUsuarioEntidad(1L);
		peticion.setCodigoEntidad("entidad1");
		peticion.setCodigoUsuario("sumset1");
		
		when(eliminar.removerUsuarioEntidad(peticion)).thenReturn(peticion);
		res = entidad.removerAsignacionEntidad(peticion);
		assertNotNull(res);
		
		when(eliminar.removerUsuarioEntidad(peticion)).thenThrow(new SpddExceptions());
		res = entidad.removerAsignacionEntidad(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
		
		peticion.setIdUsuarioEntidad(null);
		peticion.setCodigoEntidad(null);
		res = entidad.removerAsignacionEntidad(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
		
	}

	@Test
	void testObtenerEntidadesAsignados() throws SpddExceptions {
		UsuariosDTO peticion = new UsuariosDTO();
		GenericoDTO res;
		when(consultar.obtenerEntidadAsignadas(peticion)).thenReturn(new GenericoDTO());
		res = entidad.obtenerEntidadesAsignados(peticion);
		assertNotNull(res);
		
		when(consultar.obtenerEntidadAsignadas(peticion)).thenThrow(new SpddExceptions());
		res = entidad.obtenerEntidadesAsignados(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
	}

	@Test
	void testObtenerEntidadesDisponibles() throws SpddExceptions {
		GenericoDTO res;
		when(consultar.obtenerEntidadTodos()).thenReturn(new GenericoDTO());
		res = entidad.obtenerEntidadesDisponibles();
		assertNotNull(res);
		
		when(consultar.obtenerEntidadTodos()).thenThrow(new SpddExceptions());
		res = entidad.obtenerEntidadesDisponibles();
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
	}

	@Test
	void testObtenerEntidadesTodas() throws SpddExceptions {
		GenericoDTO res;
		when(consultar.obtenerEntidadTodos()).thenReturn(new GenericoDTO());
		res = entidad.obtenerEntidadesTodas();
		assertNotNull(res);
		
		when(consultar.obtenerEntidadTodos()).thenThrow(new SpddExceptions());
		res = entidad.obtenerEntidadesTodas();
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
	}

	@Test
	void testObtenerTodosBancoProyectos() throws SpddExceptions {
		GenericoDTO res;
		when(consultarBanco.obtenerTodos()).thenReturn(new GenericoDTO());
		res = entidad.obtenerTodosBancoProyectos();
		assertNotNull(res);
		
		when(consultarBanco.obtenerTodos()).thenThrow(new SpddExceptions());
		res = entidad.obtenerTodosBancoProyectos();
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
	}

	@Test
	void testObtenerPorId() throws SpddExceptions {
		String codigo = "entidad 1";
		EntidadDTO res;
		when(consultar.obtenerPorId(codigo)).thenReturn(new EntidadDTO());
		res = entidad.obtenerPorId(codigo);
		assertNotNull(res);
		
		when(consultar.obtenerPorId(codigo)).thenThrow(new SpddExceptions());
		res = entidad.obtenerPorId(codigo);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
	}

}
