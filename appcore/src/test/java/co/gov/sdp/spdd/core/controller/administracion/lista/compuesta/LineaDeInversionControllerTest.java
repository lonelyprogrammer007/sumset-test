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
import co.gov.sdp.nhspdd.common.dto.LineaDeInversionDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.spdd.core.icontroller.administracion.lista.compuesta.ILineaDeInversionController;
import co.gov.sdp.spdd.core.iservice.administracion.lista.compuesta.ILineaDeInversionConsultar;
import co.gov.sdp.spdd.core.iservice.administracion.lista.compuesta.ILineaDeInversionModificar;
import co.gov.sdp.spdd.core.iservice.administracion.lista.compuesta.ILineaDeInversionRegistrar;

@SpringBootTest(classes = { LineaDeInversionController.class })
@RunWith(SpringRunner.class)
class LineaDeInversionControllerTest {

	@Autowired
	ILineaDeInversionController lineaInversion;

	// Objeto que permite realizar consultas de lineas de inversion
	@MockBean
	ILineaDeInversionConsultar consultar;

	// Objeto que permite realizar registros de lineas de inversion
	@MockBean
	ILineaDeInversionRegistrar registrar;

	// Objeto que permite realizar modificaciones de lineas de inversion
	@MockBean
	ILineaDeInversionModificar modificar;

	/**
	 * Logger
	 */
	@MockBean
	SPDDLogger spddLogger;

	@Test
	void testRegistrarLineaDeInversion() throws SpddExceptions, JsonProcessingException {
		LineaDeInversionDTO lineaDeInversionDTO = new LineaDeInversionDTO();
		lineaDeInversionDTO.setConcepto("concepto1");
		lineaDeInversionDTO.setEstablecido("establecido1");
		lineaDeInversionDTO.setDescripcion("Descripcion1");
		lineaDeInversionDTO.setFecha("2003-12-10");
		lineaDeInversionDTO.setEstado(1);
		LineaDeInversionDTO res;
		when(registrar.registrarLineaDeInversion(lineaDeInversionDTO)).thenReturn(lineaDeInversionDTO);
		res = lineaInversion.registrarLineaDeInversion(lineaDeInversionDTO);
		assertNotNull(res);
		
		when(registrar.registrarLineaDeInversion(lineaDeInversionDTO)).thenThrow(new SpddExceptions());
		res = lineaInversion.registrarLineaDeInversion(lineaDeInversionDTO);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
		lineaDeInversionDTO.setIdLineaInversion(null);
		lineaDeInversionDTO.setConcepto(null);
		lineaDeInversionDTO.setEstablecido(null);
		lineaDeInversionDTO.setDescripcion(null);
		lineaDeInversionDTO.setFecha(null);
		lineaDeInversionDTO.setEstado(null);
		
		res = lineaInversion.registrarLineaDeInversion(lineaDeInversionDTO);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
	}

	@Test
	void testObtenerTodos() throws SpddExceptions, JsonProcessingException {
		LineaDeInversionDTO peticion = new LineaDeInversionDTO();
		GenericoDTO res;
		when(consultar.obtenerPaginado(peticion)).thenReturn(new GenericoDTO());
		res = lineaInversion.obtenerTodos(peticion);
		assertNotNull(res);
		
		
		when(consultar.obtenerPaginado(peticion)).thenThrow(new SpddExceptions());
		res = lineaInversion.obtenerTodos(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
	}

	@Test
	void testModificarLineaDeInversion() throws SpddExceptions {
		LineaDeInversionDTO lineaDeInversionDTO = new LineaDeInversionDTO();
		lineaDeInversionDTO.setIdLineaInversion(1L);
		lineaDeInversionDTO.setConcepto("concepto1");
		lineaDeInversionDTO.setEstablecido("establecido1");
		lineaDeInversionDTO.setDescripcion("Descripcion1");
		lineaDeInversionDTO.setFecha("2003-12-10");
		lineaDeInversionDTO.setEstado(1);
		LineaDeInversionDTO res;
		when(modificar.modificarLineaInversion(lineaDeInversionDTO)).thenReturn(lineaDeInversionDTO);
		res = lineaInversion.modificarLineaDeInversion(lineaDeInversionDTO);
		assertNotNull(res);
		
		when(modificar.modificarLineaInversion(lineaDeInversionDTO)).thenThrow(new SpddExceptions());
		res = lineaInversion.modificarLineaDeInversion(lineaDeInversionDTO);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
		lineaDeInversionDTO.setIdLineaInversion(null);
		lineaDeInversionDTO.setConcepto(null);
		lineaDeInversionDTO.setEstablecido(null);
		lineaDeInversionDTO.setDescripcion(null);
		lineaDeInversionDTO.setFecha(null);
		lineaDeInversionDTO.setEstado(null);
		
		res = lineaInversion.modificarLineaDeInversion(lineaDeInversionDTO);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
	}

	@Test
	void testModificarEstadoLineaDeInversion() throws SpddExceptions {
		Long id = 1L;
		LineaDeInversionDTO respuesta;
		when(modificar.modificarEstadoLineaInversion(id)).thenReturn(new LineaDeInversionDTO());
		respuesta = lineaInversion.modificarEstadoLineaDeInversion(id);
		assertNotNull(respuesta);
		
		
		when(modificar.modificarEstadoLineaInversion(id)).thenThrow(new SpddExceptions());
		respuesta = lineaInversion.modificarEstadoLineaDeInversion(id);
		assertNotNull(respuesta);
		assertThat(respuesta.getCodigoRespuesta()).isEqualTo(400L);
	}

}
