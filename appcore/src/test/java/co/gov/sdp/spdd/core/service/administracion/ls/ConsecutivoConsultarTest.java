package co.gov.sdp.spdd.core.service.administracion.ls;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import co.gov.sdp.nhspdd.common.dto.ConsecutivoDTO;
import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.spdd.core.iservice.administracion.ls.IConsecutivoConsultar;
import co.gov.sdp.spdd.data.dao.interfaces.afs.IConsecutivoServiceRepo;
import co.gov.sdp.spdd.data.mapper.ConsecutivoMapper;
import co.gov.sdp.spdd.data.model.afs.Consecutivo;
import co.gov.sdp.spdd.data.sesionfacade.impl.SessionFacadeAFS;
import co.gov.sdp.spdd.data.sesionfacade.interfaces.ISessionFacadeAFS;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { SessionFacadeAFS.class,IConsecutivoConsultar.class, ConsecutivoConsultar.class})
public class ConsecutivoConsultarTest {
	
	@Autowired
	IConsecutivoConsultar consultar;
	
	@MockBean
	IConsecutivoServiceRepo consecutivoServiceRepo;

	@MockBean
	ConsecutivoMapper consecutivoMapper;
	
	@MockBean
	ISessionFacadeAFS sessionFacadeAFS;

	@MockBean
	SPDDLogger spddLogger;

	@Test
	public void testObtenerTodos() throws SpddExceptions {
		
		Long id = 1L;
		Consecutivo consecutivo = new Consecutivo();
		consecutivo.setIdConsecutivo(id);
		List<Consecutivo> listaConsecutivos = new ArrayList<>();
		listaConsecutivos.add(consecutivo);
		
		ConsecutivoDTO consecutivoDTO = new ConsecutivoDTO();
		consecutivoDTO.setIdConsecutivo(id);
		List<ConsecutivoDTO> listaConsecutivosDTO = new ArrayList<>();
		listaConsecutivosDTO.add(consecutivoDTO);
		
		GenericoDTO res;
		
		when(consecutivoServiceRepo.obtenerTodos()).thenReturn(listaConsecutivos);
		when(consecutivoMapper.consecutivoEntitiesToDTO(listaConsecutivos)).thenReturn(listaConsecutivosDTO);
		res = consultar.obtenerTodos();
		assertNotNull(res);
        assertThat(res.getCodigoRespuesta()).isEqualTo(200L);
        assertThat(res.getLstObjectsDTO().isEmpty()).isEqualTo(false);
	}

	@Test
	public void testObtenerPaginado() throws SpddExceptions {
		Long id = 1L;
		ConsecutivoDTO peticion = new ConsecutivoDTO();
		peticion.setIdConsecutivo(id);
		
		GenericoDTO res;
		
		when(sessionFacadeAFS.consultarConsecutivoPorFiltro(peticion)).thenReturn(new GenericoDTO());
		res = consultar.obtenerPaginado(peticion);
		assertNotNull(res);
        assertThat(res.getCodigoRespuesta()).isEqualTo(200L);
		
		
	}

}
