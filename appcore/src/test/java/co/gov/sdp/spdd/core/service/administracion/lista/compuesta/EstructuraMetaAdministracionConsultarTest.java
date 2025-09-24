package co.gov.sdp.spdd.core.service.administracion.lista.compuesta;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.JsonProcessingException;

import co.gov.sdp.nhspdd.common.dto.EstructuraMetaDTO;
import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.spdd.core.iservice.administracion.lista.compuesta.IEstructuraMetaAdministracionConsultar;
import co.gov.sdp.spdd.data.dao.interfaces.afs.IEstructuraMetaServiceRepo;
import co.gov.sdp.spdd.data.mapper.EstructuraMetaMapper;
import co.gov.sdp.spdd.data.model.afs.EstructuraMeta;
import co.gov.sdp.spdd.data.sesionfacade.impl.SessionFacadeAFS;
import co.gov.sdp.spdd.data.sesionfacade.interfaces.ISessionFacadeAFS;
import co.gov.sdp.spdd.util.AuditoriaUtil;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { SessionFacadeAFS.class, IEstructuraMetaAdministracionConsultar.class,
		EstructuraMetaAdministracionConsultar.class })
class EstructuraMetaAdministracionConsultarTest {

	@Autowired
	IEstructuraMetaAdministracionConsultar consultar;

	@MockBean
	IEstructuraMetaServiceRepo estructuraMetaServiceRepo;

	@MockBean
	EstructuraMetaMapper estructuraMetaMapper;

	@MockBean
	ISessionFacadeAFS sessionFacadeAFS;

	@MockBean
	SPDDLogger spddLogger;

	@MockBean
	AuditoriaUtil auditoria;

	@Test
	void testObtenerEstructuraMetaTodos() throws SpddExceptions {

		List<EstructuraMeta> lista = new ArrayList<>();
		when(estructuraMetaServiceRepo.obtenerTodos()).thenReturn(lista);
		when(estructuraMetaMapper.estructuraMetaEntitiesToDTO(lista)).thenReturn(new ArrayList<EstructuraMetaDTO>());
		GenericoDTO res = consultar.obtenerEstructuraMetaTodos();
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(200);
	}

	@Test
	void testObtenerPaginado() throws SpddExceptions, JsonProcessingException {

		Long id = 1L;
		EstructuraMetaDTO peticion = new EstructuraMetaDTO();
		peticion.setIdEstructuraMetas(id);
		when(sessionFacadeAFS.consultarEstructuraMetaPorFiltro(peticion)).thenReturn(new GenericoDTO());
		GenericoDTO res = consultar.obtenerPaginado(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(200L);
	}

}
