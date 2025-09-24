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

import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.dto.PotProyectoInstrumentoDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.spdd.core.icontroller.administracion.lista.compuesta.IPotProyectoInstrumentoController;
import co.gov.sdp.spdd.core.iservice.administracion.lista.compuesta.IPotInstrumentoConsultar;
import co.gov.sdp.spdd.core.iservice.administracion.lista.compuesta.IPotObraConsultar;
import co.gov.sdp.spdd.core.iservice.administracion.lista.compuesta.IPotProyectoInstrumentoConsultar;
import co.gov.sdp.spdd.core.iservice.administracion.lista.compuesta.IPotProyectoInstrumentoModificar;
import co.gov.sdp.spdd.core.iservice.administracion.lista.compuesta.IPotProyectoInstrumentoRegistrar;

@SpringBootTest(classes = { PotProyectoInstrumentoController.class })
@RunWith(SpringRunner.class)
class PotProyectoInstrumentoControllerTest {

	@Autowired
	IPotProyectoInstrumentoController pot;
	
	/**
	 * Objeto que tiene los metodos de consultas
	 */
	@MockBean
	IPotProyectoInstrumentoConsultar consultar;

	/**
	 * 
	 */
	@MockBean
	IPotObraConsultar potObraConsultar;

	/**
	 * 
	 */
	@MockBean
	IPotInstrumentoConsultar potInstrumentoConsultar;

	/**
	 * Objeto que tiene los metodos de registrar
	 */
	@MockBean
	IPotProyectoInstrumentoRegistrar registrar;

	/**
	 * Objeto que tiene los metodos de modificar
	 */
	@MockBean
	IPotProyectoInstrumentoModificar modificar;

	/**
	 * Logger
	 */
	@MockBean
	SPDDLogger spddLogger;

	@Test
	void testObtenerTodos() throws SpddExceptions {
		PotProyectoInstrumentoDTO peticion = new PotProyectoInstrumentoDTO();
		GenericoDTO res;
		when(consultar.obtenerPaginado(peticion)).thenReturn(new GenericoDTO());
		res = pot.obtenerTodos(peticion);
		assertNotNull(res);
		
		when(consultar.obtenerPaginado(peticion)).thenThrow(new SpddExceptions());
		res = pot.obtenerTodos(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
	}

	@Test
	void testObtenerPotObra() throws SpddExceptions {
		GenericoDTO res;
		when(potObraConsultar.obtenerPotObra()).thenReturn(new GenericoDTO());
		res = pot.obtenerPotObra();
		assertNotNull(res);
		
		when(potObraConsultar.obtenerPotObra()).thenThrow(new SpddExceptions());
		res = pot.obtenerPotObra();
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
	}

	@Test
	void testObtenerPotInstrumento() throws SpddExceptions {
		GenericoDTO res;
		when(potInstrumentoConsultar.obtenerPotInstrumento()).thenReturn(new GenericoDTO());
		res = pot.obtenerPotInstrumento();
		assertNotNull(res);
		
		when(potInstrumentoConsultar.obtenerPotInstrumento()).thenThrow(new SpddExceptions());
		res = pot.obtenerPotInstrumento();
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
	}

	@Test
	void testRegistrarProyectoInstrumento() throws SpddExceptions, JsonProcessingException {
		PotProyectoInstrumentoDTO potProyectoInstrumentoDTO = new PotProyectoInstrumentoDTO();
		potProyectoInstrumentoDTO.setIdPotProyecto(1L);
		potProyectoInstrumentoDTO.setIdPotInstrumento(1L);
		potProyectoInstrumentoDTO.setEstado(1);
		
		PotProyectoInstrumentoDTO res;
		when(registrar.registrarProyectoInstrumento(potProyectoInstrumentoDTO)).thenReturn(potProyectoInstrumentoDTO);
		res = pot.registrarProyectoInstrumento(potProyectoInstrumentoDTO);
		assertNotNull(res);
		
		when(registrar.registrarProyectoInstrumento(potProyectoInstrumentoDTO)).thenThrow(new SpddExceptions());
		res = pot.registrarProyectoInstrumento(potProyectoInstrumentoDTO);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
		
		potProyectoInstrumentoDTO.setIdPotProyecto(null);
		potProyectoInstrumentoDTO.setIdPotInstrumento(null);
		potProyectoInstrumentoDTO.setEstado(null);
		
		res = pot.registrarProyectoInstrumento(potProyectoInstrumentoDTO);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
		
	}

	@Test
	void testModificarProyectoInstrumento() throws SpddExceptions {
		PotProyectoInstrumentoDTO potProyectoInstrumentoDTO = new PotProyectoInstrumentoDTO();
		potProyectoInstrumentoDTO.setIdProyectoInstrumento(1L);
		potProyectoInstrumentoDTO.setIdPotProyecto(1L);
		potProyectoInstrumentoDTO.setIdPotInstrumento(1L);
		potProyectoInstrumentoDTO.setEstado(1);
		
		PotProyectoInstrumentoDTO res;
		when(modificar.modificarProyectoInstrumento(potProyectoInstrumentoDTO)).thenReturn(potProyectoInstrumentoDTO);
		res = pot.modificarProyectoInstrumento(potProyectoInstrumentoDTO);
		assertNotNull(res);
		
		when(modificar.modificarProyectoInstrumento(potProyectoInstrumentoDTO)).thenThrow(new SpddExceptions());
		res = pot.modificarProyectoInstrumento(potProyectoInstrumentoDTO);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
		potProyectoInstrumentoDTO.setIdProyectoInstrumento(null);
		potProyectoInstrumentoDTO.setIdPotProyecto(null);
		potProyectoInstrumentoDTO.setIdPotInstrumento(null);
		potProyectoInstrumentoDTO.setEstado(null);
		
		res = pot.modificarProyectoInstrumento(potProyectoInstrumentoDTO);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
		
	}

}
