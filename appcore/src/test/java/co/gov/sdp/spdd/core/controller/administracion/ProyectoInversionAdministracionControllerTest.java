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

import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.dto.ProyectoInversionDTO;
import co.gov.sdp.nhspdd.common.dto.ProyectosInversionUsuarioDTO;
import co.gov.sdp.nhspdd.common.dto.UsuariosDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.spdd.core.icontroller.administracion.IProyectoInversionAdministracionController;
import co.gov.sdp.spdd.core.iservice.administracion.IProyectoInversionAdministracionRegistrar;
import co.gov.sdp.spdd.core.iservice.administracion.IProyectoInversionAdmnistracionConsultar;
import co.gov.sdp.spdd.core.iservice.administracion.IProyectoInversionAdmnistracionEliminar;

@SpringBootTest(classes = {ProyectoInversionAdministracionController.class})
@RunWith(SpringRunner.class)
class ProyectoInversionAdministracionControllerTest {
	
	@Autowired
	IProyectoInversionAdministracionController proyecto;
	
	// Servicio de consulta para proyecto de inversion
	@MockBean
	IProyectoInversionAdmnistracionConsultar consultar;

	// Servicio de eliminacion para proyecto de inversion
	@MockBean
	IProyectoInversionAdmnistracionEliminar eliminar;

	// Servicio de registro para proyecto de inversion
	@MockBean
	IProyectoInversionAdministracionRegistrar registrar;

	/**
	 * 
	 */
	@MockBean
	SPDDLogger spddLogger;

	@Test
	void testCrearProyectoInversion() throws SpddExceptions, JsonProcessingException {
		ProyectoInversionDTO peticion = new ProyectoInversionDTO();
		peticion.setIdProyectoInversion(1L);
		peticion.setNombre("proyecto1");
		ProyectoInversionDTO res;
		
		when(registrar.crearProyectoInversion(peticion)).thenReturn(peticion);
		res = proyecto.crearProyectoInversion(peticion);
		assertNotNull(res);
		
		when(registrar.crearProyectoInversion(peticion)).thenThrow(new SpddExceptions());
		res = proyecto.crearProyectoInversion(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
		
		peticion.setNombre(null);
		res = proyecto.crearProyectoInversion(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
		
		
	}

	@Test
	void testAsignarProyectoInversion() throws SpddExceptions {
		ProyectosInversionUsuarioDTO res;
		ProyectosInversionUsuarioDTO peticion = new ProyectosInversionUsuarioDTO();
		when(registrar.asignarProyectosInversionUsuario(peticion)).thenReturn(peticion);
		res = proyecto.asignarProyectoInversion(peticion);
		assertNotNull(res);
		
		when(registrar.asignarProyectosInversionUsuario(peticion)).thenThrow(new SpddExceptions());
		res = proyecto.asignarProyectoInversion(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
	}

	@Test
	void testRemoverAsignacionProyectoInversion() throws SpddExceptions {
		ProyectosInversionUsuarioDTO peticion = new ProyectosInversionUsuarioDTO();
		ProyectosInversionUsuarioDTO res;
		peticion.setIdProyectoUsuario(1L);
		peticion.setIdProyectoInversion(1L);
		peticion.setCodigoUsuario("sumset1");
		when(eliminar.removerProyectoInversionUsuario(peticion)).thenReturn(peticion);
		res = proyecto.removerAsignacionProyectoInversion(peticion);
		assertNotNull(res);
		
		peticion.setIdProyectoUsuario(null);
		peticion.setIdProyectoInversion(null);
		peticion.setCodigoUsuario(null);
		res = proyecto.removerAsignacionProyectoInversion(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
		
		when(eliminar.removerProyectoInversionUsuario(peticion)).thenThrow(new SpddExceptions());
		res = proyecto.removerAsignacionProyectoInversion(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
		
	}

	@Test
	void testObtenerProyectosInversionAsignados() throws SpddExceptions {
		UsuariosDTO peticion = new UsuariosDTO();
		GenericoDTO res;
		
		when(consultar.obtenerProyectoInversionAsignados(peticion)).thenReturn(new GenericoDTO());
		res = proyecto.obtenerProyectosInversionAsignados(peticion);
		assertNotNull(res);
		
		when(consultar.obtenerProyectoInversionAsignados(peticion)).thenThrow(new SpddExceptions());
		res = proyecto.obtenerProyectosInversionAsignados(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
	}

	@Test
	void testObtenerProyectosInversionDisponibles() throws SpddExceptions {
		GenericoDTO res;
		when(consultar.obtenerProyectoInversionDisponibles()).thenReturn(new GenericoDTO());
		res = proyecto.obtenerProyectosInversionDisponibles();
		assertNotNull(res);
		
		when(consultar.obtenerProyectoInversionDisponibles()).thenThrow(new SpddExceptions());
		res = proyecto.obtenerProyectosInversionDisponibles();
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
	}

	@Test
	void testObtenerProyectosInversionTodos() throws SpddExceptions {
		GenericoDTO res;
		when(consultar.obtenerProyectoInversionTodos()).thenReturn(new GenericoDTO());
		res = proyecto.obtenerProyectosInversionTodos();
		assertNotNull(res);
		
		when(consultar.obtenerProyectoInversionTodos()).thenThrow(new SpddExceptions());
		res = proyecto.obtenerProyectosInversionTodos();
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
	}

}
