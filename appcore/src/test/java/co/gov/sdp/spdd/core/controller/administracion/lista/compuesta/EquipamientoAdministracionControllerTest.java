package co.gov.sdp.spdd.core.controller.administracion.lista.compuesta;

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

import co.gov.sdp.nhspdd.common.dto.EquipamientoDTO;
import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.spdd.core.icontroller.administracion.lista.compuesta.IEquipamientoAdministracionController;
import co.gov.sdp.spdd.core.iservice.administracion.lista.compuesta.IEquipamientoAdministracionConsultar;
import co.gov.sdp.spdd.core.iservice.administracion.lista.compuesta.IEquipamientoAdministracionModificar;
import co.gov.sdp.spdd.core.iservice.administracion.lista.compuesta.IEquipamientoAdministracionRegistrar;

@SpringBootTest(classes = { EquipamientoAdministracionController.class })
@RunWith(SpringRunner.class)
class EquipamientoAdministracionControllerTest {
	
	@Autowired
	IEquipamientoAdministracionController equipamiento;
	
	// Servicio de consulta para equipamiento
	@MockBean
	IEquipamientoAdministracionConsultar consultar;


	// Servicio de modificacion para equipamiento
	@MockBean
	IEquipamientoAdministracionModificar modificar;

	// Servicio de registro para equipamiento
	@MockBean
	IEquipamientoAdministracionRegistrar registrar;

	/**
	 * Logger
	 */
	@MockBean
	SPDDLogger spddLogger;

	@Test
	void testCrearEquipamiento() throws SpddExceptions, JsonProcessingException {
		EquipamientoDTO peticion = new EquipamientoDTO();
		peticion.setDescripcion("descripcion1");
		peticion.setNombre("nombre1");
		peticion.setIdLsCategoria(1L);
		peticion.setIdLsSector(1L);
		EquipamientoDTO res;
		when(registrar.crearEquipamiento(peticion)).thenReturn(peticion);
		res = equipamiento.crearEquipamiento(peticion);
		assertNotNull(res);
		
		
		
		peticion.setDescripcion(null);
		peticion.setNombre(null);
		peticion.setIdLsCategoria(1L);
		peticion.setIdLsSector(1L);
		
		res = equipamiento.crearEquipamiento(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
	}

	@Test
	void testModificarEquipamiento() throws SpddExceptions {
		EquipamientoDTO peticion = new EquipamientoDTO();
		peticion.setDescripcion("descripcion1");
		peticion.setNombre("nombre1");
		peticion.setIdLsCategoria(1L);
		peticion.setIdLsSector(1L);
		peticion.setIdEquipamento(1L);
		peticion.setEstado(1);
		EquipamientoDTO res;
		when(modificar.modificarEquipamiento(peticion)).thenReturn(peticion);
		res = equipamiento.modificarEquipamiento(peticion);
		assertNotNull(res);
		
		
		
		peticion.setDescripcion(null);
		peticion.setNombre(null);
		peticion.setIdLsCategoria(1L);
		peticion.setIdLsSector(1L);
		
		res = equipamiento.modificarEquipamiento(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
	}

	@Test
	void testObtenerEquipamientoTodos() throws SpddExceptions, JsonProcessingException {
		EquipamientoDTO peticion = new EquipamientoDTO();
		GenericoDTO res;
		when(consultar.obtenerPaginado(peticion)).thenReturn(new GenericoDTO());
		res = equipamiento.obtenerEquipamientoTodos(peticion);
		assertNotNull(res);
		
		when(consultar.obtenerPaginado(peticion)).thenThrow(new SpddExceptions());
		res = equipamiento.obtenerEquipamientoTodos(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
	}

}
