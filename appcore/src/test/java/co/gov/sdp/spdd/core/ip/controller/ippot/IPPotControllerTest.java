package co.gov.sdp.spdd.core.ip.controller.ippot;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.dto.PotInstrumentoDTO;
import co.gov.sdp.nhspdd.common.dto.PotObraDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PotDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.spdd.core.ip.iservice.ippot.IIPPotIEliminarService;
import co.gov.sdp.spdd.core.ip.service.ippot.IPPotConsultarService;
import co.gov.sdp.spdd.core.ip.service.ippot.IPPotModificarService;
import co.gov.sdp.spdd.core.ip.service.ippot.IPPotRegistrarService;
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { IPPotController.class })
class IPPotControllerTest {

	@Autowired
	IPPotController controller;
	
	@MockBean
	IPPotConsultarService consultarService;
	
	@MockBean
	IPPotModificarService modificarService;
	
	@MockBean
	IPPotRegistrarService registrarService;
	
	@MockBean
	IIPPotIEliminarService eliminarService;
	
	@MockBean
	SPDDLogger spddLogger;
	
	@Test
	void testConsultarTodosPotPorFiltro() throws SpddExceptions {
		GenericoDTO respuesta;
		PotDTO potDTO = new PotDTO();
		when(consultarService.potObtenerPaginado(potDTO)).thenReturn(new GenericoDTO());
		respuesta = controller.consultarTodosPotPorFiltro(potDTO);
		assertNotNull(respuesta);
		
		when(consultarService.potObtenerPaginado(potDTO)).thenThrow(new SpddExceptions());
		respuesta = controller.consultarTodosPotPorFiltro(potDTO);
		assertNotNull(respuesta);
		assertThat(respuesta.getCodigoRespuesta()).isEqualTo(400L);
	}

	@Test
	void testRegistrarPot() throws SpddExceptions {
		PotDTO peticion = new PotDTO();
		PotDTO res;
		when(registrarService.registrarPot(peticion)).thenReturn(peticion);
		res = controller.registrarPot(peticion);
		assertNotNull(res);
		
		when(registrarService.registrarPot(peticion)).thenThrow(new SpddExceptions());
		res = controller.registrarPot(peticion);
		assertNotNull(res);
	}

	@Test
	void testModificarPot() throws SpddExceptions {
		PotDTO peticion = new PotDTO();
		PotDTO res;
		when(modificarService.modificarPot(peticion)).thenReturn(peticion);
		res = controller.modificarPot(peticion);
		assertNotNull(res);
		
		when(modificarService.modificarPot(peticion)).thenThrow(new SpddExceptions());
		res = controller.modificarPot(peticion);
		assertNotNull(res);
	}

	@Test
	public void testConsultarTodosPotObraPorIdNivelPotPaginado() throws Exception {
		PotObraDTO peticion = new PotObraDTO();
		
		when(consultarService.consultarTodosPotObraPorIdNivelPotPaginado(peticion)).thenReturn(new GenericoDTO());
		GenericoDTO res =  controller.consultarTodosPotObraPorIdNivelPotPaginado(peticion);
		assertNotNull(res);
		
		when(consultarService.consultarTodosPotObraPorIdNivelPotPaginado(peticion)).thenThrow(new SpddExceptions());
		res =  controller.consultarTodosPotObraPorIdNivelPotPaginado(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
		
		
	}

	@Test
	public void testRegistrarPotObra() throws Exception {
		PotObraDTO peticion = new PotObraDTO();
		peticion.setCodigoPotObra(1L);
		peticion.setIdLsPlazo(1L);
		peticion.setIdNivelPot(1L);
		peticion.setCodigoEntidadConcatenados("1;2");
		
		when(registrarService.registrarPotObra(peticion)).thenReturn(new PotObraDTO());
		PotObraDTO res = controller.registrarPotObra(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
		
		peticion.setObra("obra1");
		res = controller.registrarPotObra(peticion);
		assertNotNull(res);
		
		when(registrarService.registrarPotObra(peticion)).thenThrow(new SpddExceptions());
		res = controller.registrarPotObra(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
	}

	@Test
	public void testModificarPotObra() throws Exception {
		PotObraDTO peticion = new PotObraDTO();
		peticion.setCodigoPotObra(1L);
		peticion.setIdLsPlazo(1L);
		peticion.setIdNivelPot(1L);
		peticion.setCodigoEntidadConcatenados("1;2");
		peticion.setObra("obra1");
		
		when(modificarService.modificarPotObra(peticion)).thenReturn(new PotObraDTO());
		PotObraDTO res = controller.modificarPotObra(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
		
		peticion.setIdObraProyPot(1L);
		res = controller.modificarPotObra(peticion);
		assertNotNull(res);
		
		when(modificarService.modificarPotObra(peticion)).thenThrow(new SpddExceptions());
		res = controller.modificarPotObra(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
	}

	@Test
	public void testEliminarPotObra() throws Exception {
		Long idPotObra = 1L;
		
		when(eliminarService.eliminarPotObra(idPotObra)).thenReturn(new PotObraDTO());
		PotObraDTO res =  controller.eliminarPotObra(idPotObra);
		assertNotNull(res);
		
		when(eliminarService.eliminarPotObra(idPotObra)).thenThrow(new SpddExceptions());
		res =  controller.eliminarPotObra(idPotObra);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
	}

	@Test
	public void testConsultarTodosPotInstrumentoPorIdPotFiltrado() throws Exception {
		PotInstrumentoDTO peticion = new PotInstrumentoDTO();
		
		when(consultarService.consultarTodosPotInstrumentoFiltrado(peticion)).thenReturn(new GenericoDTO());
		GenericoDTO res =  controller.consultarTodosPotInstrumentoPorIdPotFiltrado(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
		
		peticion.setIdPot(1L);
		when(consultarService.consultarTodosPotInstrumentoFiltrado(peticion)).thenReturn(new GenericoDTO());
		res =  controller.consultarTodosPotInstrumentoPorIdPotFiltrado(peticion);
		assertNotNull(res);
		
		when(consultarService.consultarTodosPotInstrumentoFiltrado(peticion)).thenThrow(new SpddExceptions());
		res =  controller.consultarTodosPotInstrumentoPorIdPotFiltrado(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
	}

	@Test
	public void testRegistrarPotInstrumento() throws Exception {
		PotInstrumentoDTO peticion = new PotInstrumentoDTO();
		peticion.setCodigoPotInstrumento(1L);
		peticion.setIdLsDenominacion(1L);
		
		when(registrarService.registrarPotInstrumento(peticion)).thenReturn(new PotInstrumentoDTO());
		PotInstrumentoDTO res = controller.registrarPotInstrumento(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
		
		peticion.setIdPot(1L);
		res = controller.registrarPotInstrumento(peticion);
		assertNotNull(res);
		
		when(registrarService.registrarPotInstrumento(peticion)).thenThrow(new SpddExceptions());
		res = controller.registrarPotInstrumento(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
	}

	@Test
	public void testModificarPotInstrumento() throws Exception {
		PotInstrumentoDTO peticion = new PotInstrumentoDTO();
//		peticion.setCodigoPotInstrumento(1L);
		peticion.setIdLsDenominacion(1L);
		peticion.setIdInstrumentoPot(1L);
		
		when(modificarService.modificarPotInstrumento(peticion)).thenReturn(new PotInstrumentoDTO());
		PotInstrumentoDTO res = controller.modificarPotInstrumento(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
		
		peticion.setIdPot(1L);
		res = controller.modificarPotInstrumento(peticion);
		assertNotNull(res);
		
		when(modificarService.modificarPotInstrumento(peticion)).thenThrow(new SpddExceptions());
		res = controller.modificarPotInstrumento(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
	}

	@Test
	public void testEliminarPotInstrumento() throws Exception {
		Long idPotInstrumento = 1L;
		
		when(eliminarService.eliminarPotInstrumento(idPotInstrumento)).thenReturn(new PotInstrumentoDTO());
		PotInstrumentoDTO res = controller.eliminarPotInstrumento(idPotInstrumento);
		assertNotNull(res);
		
		when(eliminarService.eliminarPotInstrumento(idPotInstrumento)).thenThrow(new SpddExceptions());
		res = controller.eliminarPotInstrumento(idPotInstrumento);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
	}

}
