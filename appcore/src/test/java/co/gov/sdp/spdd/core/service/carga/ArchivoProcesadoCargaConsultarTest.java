package co.gov.sdp.spdd.core.service.carga;

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
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.junit4.SpringRunner;

import co.gov.sdp.nhspdd.common.dto.ArchivoProcesadoDTO;
import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.spdd.core.iservice.carga.IArchivoProcesadoCargaConsultar;
import co.gov.sdp.spdd.data.dao.interfaces.afs.IArchivoProcesadoServiceRepo;
import co.gov.sdp.spdd.data.dao.interfaces.afs.IEntidadServiceRepo;
import co.gov.sdp.spdd.data.mapper.ArchivoProcesadoMapper;
import co.gov.sdp.spdd.data.model.afs.ArchivoProcesado;
import co.gov.sdp.spdd.data.sesionfacade.impl.SessionFacadeAFS;
import co.gov.sdp.spdd.data.sesionfacade.interfaces.ISessionFacadeAFS;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { SessionFacadeAFS.class, IArchivoProcesadoCargaConsultar.class,ArchivoProcesadoCargaConsultar.class})
public class ArchivoProcesadoCargaConsultarTest {
	
	@Autowired
	IArchivoProcesadoCargaConsultar registrar;
	
	@MockBean
	IArchivoProcesadoServiceRepo archivoProcesadoServiceRepo;

	@MockBean
	ArchivoProcesadoMapper archivoProcesadoMapper;

	@MockBean
	IEntidadServiceRepo entidadServiceRepo;

	@MockBean
	ISessionFacadeAFS sessionFacadeAFS;

	@Test
	public void testObtenerArchivosProcesadosTodos() throws SpddExceptions {
		List<ArchivoProcesado> lista = new ArrayList<>();
		List<ArchivoProcesadoDTO> listaRespuesta = new ArrayList<>();
		
		GenericoDTO res;
		
		when(archivoProcesadoServiceRepo.obtenerTodos()).thenReturn(lista);
		when(archivoProcesadoMapper.ArchivoProcesadoEntitiesToDTO(lista)).thenReturn(listaRespuesta);

		res = registrar.obtenerArchivosProcesadosTodos();
		assertNotNull(res);
        assertThat(res.getCodigoRespuesta()).isEqualTo(200L);
	}

	@Test
	public void testObtenerPaginado() throws DataAccessException, SpddExceptions {
		ArchivoProcesadoDTO peticion = new ArchivoProcesadoDTO();
		
		GenericoDTO res;
		
		when(sessionFacadeAFS.consultarArchivoProcesadoPorFiltro(peticion)).thenReturn(new GenericoDTO());
		res = registrar.obtenerPaginado(peticion);
		assertNotNull(res);
        assertThat(res.getCodigoRespuesta()).isEqualTo(200L);
	}

}
