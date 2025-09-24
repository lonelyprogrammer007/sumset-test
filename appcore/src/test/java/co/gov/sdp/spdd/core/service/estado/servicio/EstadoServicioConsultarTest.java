package co.gov.sdp.spdd.core.service.estado.servicio;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import co.gov.sdp.nhspdd.common.dto.EstadoServicioDTO;
import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.spdd.core.iservice.estado.servicio.IEstadoServicioConsultar;
import co.gov.sdp.spdd.data.dao.interfaces.afs.IEstadoServicioServiceRepo;
import co.gov.sdp.spdd.data.serviciofacade.afs.SessionConsultaAFS;
import co.gov.sdp.spdd.data.sesionfacade.impl.SessionFacadeAFS;
import co.gov.sdp.spdd.data.sesionfacade.interfaces.ISessionFacadeAFS;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {SessionFacadeAFS.class,IEstadoServicioConsultar.class,EstadoServicioConsultar.class})
public class EstadoServicioConsultarTest {

    @Autowired
    IEstadoServicioConsultar consultar;

	@MockBean
	ISessionFacadeAFS sessionFacadeAFS;
	
	
	@MockBean
	IEstadoServicioServiceRepo serviceRepo;

	@MockBean
	SessionConsultaAFS consultaAFS;
	
	@MockBean
	SPDDLogger logger;
	

	@Before
	public final void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testObtenerTodos() throws SpddExceptions {

		EstadoServicioDTO peticion = new EstadoServicioDTO();
		when(sessionFacadeAFS.consultarEstadoServicioPorFiltro(new EstadoServicioDTO())).thenReturn(new GenericoDTO());
		if (consultar.obtenerTodos(peticion) != null) {
			GenericoDTO lista = consultar.obtenerTodos(peticion);
			assertThat(lista.getLstObjectsDTO()).isNotNull();
		}
	}

}
