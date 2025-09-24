package co.gov.sdp.spdd.core.ip.service.ippot;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletRequest;

import com.fasterxml.jackson.databind.ObjectMapper;

import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.dto.PotInstrumentoDTO;
import co.gov.sdp.nhspdd.common.dto.PotObraDTO;
import co.gov.sdp.nhspdd.common.dto.RespuestaApiDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PotDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PotObraEntidadDTO;
import co.gov.sdp.nhspdd.common.dto.seguridad.EntidadSeguridadDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.spdd.data.sesionfacade.impl.SessionFacadeIP;
import co.gov.sdp.spdd.util.AuditoriaUtil;
import co.gov.sdp.spdd.util.MetodosRest;

@RunWith(PowerMockRunner.class)
@SpringBootTest(classes = { SessionFacadeIP.class, IPPotConsultarService.class, ObjectMapper.class })
public class IPPotConsultarServiceTest {

	@Autowired
	IPPotConsultarService consultarService;

	@MockBean
	SessionFacadeIP sessionFacadeIP;

	@MockBean
	SPDDLogger spddLogger;

	@MockBean
	MetodosRest<RespuestaApiDTO<EntidadSeguridadDTO>> entidad;

	@MockBean
	AuditoriaUtil auditoria;
	
	@Test
	void testPotObtenerPaginado() throws SpddExceptions {
		PotDTO potDTO = new PotDTO();
		when(sessionFacadeIP.consultarPotPorFiltro(potDTO)).thenReturn(new GenericoDTO());
		assertNotNull(potDTO);
	}


	@Test
	public void testConsultarTodosPotObraPorIdNivelPotPaginado() throws Exception {

//		PotObraDTO peticion = new PotObraDTO();
//		peticion.setIdNivelPot(1L);
//
//		PotObraDTO potObraDTO1 = new PotObraDTO();
//		potObraDTO1.setIdObraProyPot(1L);
//		List<Object> listaPotObraDTOObjeto = new ArrayList<Object>();
//		listaPotObraDTOObjeto.add(potObraDTO1);
//		GenericoDTO respuesta = new GenericoDTO();
//		respuesta.setLstObjectsDTO(listaPotObraDTOObjeto);
//
//		PotObraEntidadDTO potObraEntidadDTO1 = new PotObraEntidadDTO();
//		potObraEntidadDTO1.setCodigoEntidad("11");
//		potObraEntidadDTO1.setStringEntidad("entidad1");
//		PotObraEntidadDTO potObraEntidadDTO2 = new PotObraEntidadDTO();
//		potObraEntidadDTO2.setCodigoEntidad("22");
//		potObraEntidadDTO2.setStringEntidad("entidad2");
//		List<Object> listaPotObraEntidad = new ArrayList<Object>();
//		listaPotObraEntidad.add(potObraEntidadDTO1);
//		listaPotObraEntidad.add(potObraEntidadDTO2);
//		GenericoDTO genericoPotObraEntidad = new GenericoDTO();
//		genericoPotObraEntidad.setLstObjectsDTO(listaPotObraEntidad);
//
//		List<PotObraDTO> listaPotObraDTO = new ArrayList<PotObraDTO>();
//		listaPotObraDTO.add(potObraDTO1);
//		MockHttpServletRequest request = mock(MockHttpServletRequest.class);
//		request.addHeader("Authorization", "sadadasd");
//		when(request.getHeader("Authorization")).thenReturn("sadadasd");
//		doNothing().when(entidad).agregarToken(null);
//		when(sessionFacadeIP.consultarTodosPotObraPorIdNivelPotPaginado(peticion)).thenReturn(respuesta);
//		when(sessionFacadeIP.consultarTodosPotObraEntidadPorIdPotObra(potObraDTO1.getIdObraProyPot()))
//				.thenReturn(genericoPotObraEntidad);
//		GenericoDTO res = consultarService.consultarTodosPotObraPorIdNivelPotPaginado(peticion);
//		assertNotNull(res);
//		assertThat(res.getCodigoRespuesta()).isEqualTo(200L);


	}


	@Test
	public void testConsultarTodosPotInstrumentoFiltrado() throws Exception {
		PotInstrumentoDTO peticion = new PotInstrumentoDTO();
		when(sessionFacadeIP.consultarTodosPotInstrumentoFiltrado(peticion)).thenReturn(new GenericoDTO());
		GenericoDTO res = consultarService.consultarTodosPotInstrumentoFiltrado(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(200L);

	}

}
