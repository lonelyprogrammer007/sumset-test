package co.gov.sdp.spdd.core.bp.controller.bpiniciativa;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpIniciativaCiudadanaDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.spdd.core.bp.iservice.bpiniciativa.IBPIniciativaConsultarService;
import co.gov.sdp.spdd.core.bp.iservice.bpiniciativa.IBPIniciativaEliminarService;
import co.gov.sdp.spdd.core.bp.iservice.bpiniciativa.IBPIniciativaRegistrarService;
import co.gov.sdp.spdd.core.bp.iservice.bpiniciativa.IBpIniciativaModificarService;

@SpringBootTest(classes = { BPIniciativaController.class })
@RunWith(SpringRunner.class)
public class BPIniciativaControllerTest {

	@Autowired
	BPIniciativaController controller;

	@MockBean
	IBPIniciativaConsultarService consultar;

	@MockBean
	IBPIniciativaEliminarService eliminar;

	@MockBean
	IBPIniciativaRegistrarService registrar;

	@MockBean
	IBpIniciativaModificarService modificar;

	@MockBean
	SPDDLogger spddLogger;

	@Test
	public void testConsultarTodasLasIniCiudadana() throws SpddExceptions {
		BpIniciativaCiudadanaDTO peticion = new BpIniciativaCiudadanaDTO();
		when(consultar.obtenerTodasLaIniciativasCiudadanas(peticion)).thenReturn(new GenericoDTO());
		assertThat(controller.consultarTodasLasIniCiudadana(peticion)).isNotNull();
		when(consultar.obtenerTodasLaIniciativasCiudadanas(peticion)).thenThrow(SpddExceptions.class);
		assertThat(controller.consultarTodasLasIniCiudadana(peticion)).isNotNull();

	}

	@Test
	public void testGuardarIniciativaCiudadana() throws Exception {
		BpIniciativaCiudadanaDTO peticion = new BpIniciativaCiudadanaDTO();
		peticion.setRadicado(1L);
		peticion.setFechaRad("01/02/2020");
		peticion.setNumeroRad(11L);
		peticion.setNombre("RADICO");
		peticion.setIdLsOrigen(12L);
		peticion.setAplicaLinea(1L);
		peticion.setProblematica("problematica 1");
		peticion.setGruposEtarios("grupos 1");
		assertThat(controller.guardarIniciativaCiudadana(peticion)).isNotNull();
		peticion.setTotalPoblacion(12L);
		peticion.setAlternativaSolucion("alternativa");
		peticion.setNombreProp("sumset");
		peticion.setIdLsEstadoInicia(11L);
		peticion.setCondicionPoblacional("condicion");
		peticion.setCodigoEntidad("0143");
		when(registrar.guardarIniciativaCiudadana(peticion)).thenReturn(new BpIniciativaCiudadanaDTO());
		assertThat(controller.guardarIniciativaCiudadana(peticion)).isNotNull();
		when(registrar.guardarIniciativaCiudadana(peticion)).thenThrow(SpddExceptions.class);
		assertThat(controller.guardarIniciativaCiudadana(peticion)).isNotNull();


	}

	@Test
	public void testModificarIniciativaCiudadana() throws Exception {
		BpIniciativaCiudadanaDTO peticion = new BpIniciativaCiudadanaDTO();
		peticion.setRadicado(1L);
		peticion.setFechaRad("01/02/2020");
		peticion.setNumeroRad(11L);
		peticion.setNombre("RADICO");
		peticion.setIdLsOrigen(12L);
		peticion.setAplicaLinea(1L);
		peticion.setProblematica("problematica 1");
		peticion.setGruposEtarios("grupos 1");
		peticion.setIdIniciativa(1L);
		
		
		when(modificar.modificarIniciativaCiudadana(peticion)).thenReturn(new BpIniciativaCiudadanaDTO());
		BpIniciativaCiudadanaDTO res = controller.modificarIniciativaCiudadana(peticion);
		assertNotNull(res);
		
		peticion.setIdIniciativa(null);
		res = controller.modificarIniciativaCiudadana(peticion);
		assertNotNull(res);
		assertEquals(res.getCodigoRespuesta(), 400);
		
		peticion.setIdIniciativa(1L);
		when(modificar.modificarIniciativaCiudadana(peticion)).thenThrow(new SpddExceptions());
		res = controller.modificarIniciativaCiudadana(peticion);
		assertNotNull(res);
		assertEquals(res.getCodigoRespuesta(), 400);

	}

	@Test
	public void testConsultaDetalladaIniciativaCiudadana() throws Exception {
		Long idIniciativa = 1L;
		
		when(consultar.consultaDetallaIniciativaCiudadana(idIniciativa)).thenReturn(new BpIniciativaCiudadanaDTO());
		BpIniciativaCiudadanaDTO res = controller.consultaDetalladaIniciativaCiudadana(idIniciativa);
		assertNotNull(res);
		
		when(consultar.consultaDetallaIniciativaCiudadana(idIniciativa)).thenThrow(new SpddExceptions());
		res = controller.consultaDetalladaIniciativaCiudadana(idIniciativa);
		assertNotNull(res);
		assertEquals(res.getCodigoRespuesta(), 400);
	}

	@Test
	public void testEliminarIniciativaCiudadana() throws Exception {
		Long idIniciativa = 1L;
		
		when(eliminar.eliminarIniciativaCiudadana(idIniciativa)).thenReturn(new BpIniciativaCiudadanaDTO());
		BpIniciativaCiudadanaDTO res = controller.eliminarIniciativaCiudadana(idIniciativa);
		assertNotNull(res);
		
		when(eliminar.eliminarIniciativaCiudadana(idIniciativa)).thenThrow(new SpddExceptions());
		res = controller.eliminarIniciativaCiudadana(idIniciativa);
		assertNotNull(res);
		assertEquals(res.getCodigoRespuesta(), 400);
	}

}
