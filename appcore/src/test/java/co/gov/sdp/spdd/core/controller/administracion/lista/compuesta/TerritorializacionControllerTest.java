package co.gov.sdp.spdd.core.controller.administracion.lista.compuesta;

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
import co.gov.sdp.nhspdd.common.dto.TerritorializacionDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.spdd.core.icontroller.administracion.lista.compuesta.ITerritorializacionController;
import co.gov.sdp.spdd.core.iservice.administracion.lista.compuesta.ITerritorializacionConsultar;
import co.gov.sdp.spdd.core.iservice.administracion.lista.compuesta.ITerritorializacionModificar;
import co.gov.sdp.spdd.core.iservice.administracion.lista.compuesta.ITerritorializacionRegistrar;

@SpringBootTest(classes = { TerritorializacionController.class })
@RunWith(SpringRunner.class)
class TerritorializacionControllerTest {
	
	@Autowired
	ITerritorializacionController terri;
	// Servicio para realizar consultas
	@MockBean
	ITerritorializacionConsultar consultar;

	// Servicio para realizar modificaciones
	@MockBean
	ITerritorializacionModificar modificar;

	// Servicio para realizar registros
	@MockBean
	ITerritorializacionRegistrar registrar;

	/**
	 * Logger
	 */
	@MockBean
	SPDDLogger spddLogger;

	@Test
	void testRegistrarTerritorializacion() throws SpddExceptions, JsonProcessingException {
		TerritorializacionDTO territorializacionDTO = new TerritorializacionDTO();
		TerritorializacionDTO res;
		territorializacionDTO.setEstado(1);
		territorializacionDTO.setIdLsLocalidad(1L);
		when(registrar.registrarTerritorializacion(territorializacionDTO)).thenReturn(territorializacionDTO);
		res = terri.registrarTerritorializacion(territorializacionDTO);
		assertNotNull(res);
		
		when(registrar.registrarTerritorializacion(territorializacionDTO)).thenThrow(new SpddExceptions());
		res = terri.registrarTerritorializacion(territorializacionDTO);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
		
		territorializacionDTO.setEstado(null);
		territorializacionDTO.setIdLsLocalidad(null);
	
		res = terri.registrarTerritorializacion(territorializacionDTO);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
	}

	@Test
	void testModificarTerritorializacion() throws SpddExceptions {
		TerritorializacionDTO territorializacionDTO = new TerritorializacionDTO();
		TerritorializacionDTO res;
		territorializacionDTO.setEstado(1);
		territorializacionDTO.setIdLsLocalidad(1L);
		territorializacionDTO.setIdTerritorializacion(1L);
		when(modificar.modificarTerritorializacion(territorializacionDTO)).thenReturn(territorializacionDTO);
		res = terri.modificarTerritorializacion(territorializacionDTO);
		assertNotNull(res);
		
		when(modificar.modificarTerritorializacion(territorializacionDTO)).thenThrow(new SpddExceptions());
		res = terri.modificarTerritorializacion(territorializacionDTO);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
		
		territorializacionDTO.setEstado(null);
		territorializacionDTO.setIdLsLocalidad(null);
	
		res = terri.modificarTerritorializacion(territorializacionDTO);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
	}

	@Test
	void testModificarEstadoTerritorializacion() throws SpddExceptions {
		TerritorializacionDTO res;
		Long id = 1L;
		when(modificar.modificarEstadoTerritorializacion(id)).thenReturn(new TerritorializacionDTO());
		res = terri.modificarEstadoTerritorializacion(id);
		assertNotNull(res);
		
		when(modificar.modificarEstadoTerritorializacion(id)).thenThrow(new SpddExceptions());
		res = terri.modificarEstadoTerritorializacion(id);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
	}

	@Test
	void testObtenerTodos() throws SpddExceptions, JsonProcessingException {
		TerritorializacionDTO peticion = new TerritorializacionDTO();
		GenericoDTO res;
		when(consultar.obtenerPaginado(peticion)).thenReturn(new GenericoDTO());
		res = terri.obtenerTodos(peticion);
		assertNotNull(res);
		
		when(consultar.obtenerPaginado(peticion)).thenThrow(new SpddExceptions());
		res = terri.obtenerTodos(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
	}

	@Test
	void testObtenerTerritorializacionTodos() throws SpddExceptions {
		GenericoDTO res;
		when(consultar.obtenerTodos()).thenReturn(new GenericoDTO());
		res = terri.obtenerTerritorializacionTodos();
		assertNotNull(res);
		
		when(consultar.obtenerTodos()).thenThrow(new SpddExceptions());
		res = terri.obtenerTerritorializacionTodos();
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
	}

}
