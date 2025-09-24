package co.gov.sdp.spdd.core.service.administracion;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.spdd.core.iservice.administracion.IBancoDeProyectoConsultar;
import co.gov.sdp.spdd.data.dao.interfaces.afs.IEstadoServicioServiceRepo;
import co.gov.sdp.spdd.data.serviciofacade.afs.SessionConsultaAFS;
import co.gov.sdp.spdd.data.sesionfacade.impl.SessionFacadeAFS;
import co.gov.sdp.spdd.data.sesionfacade.interfaces.ISessionFacadeAFS;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { SessionFacadeAFS.class, BancoDeProyectosConsultar.class, IBancoDeProyectoConsultar.class })
public class BancoDeProyectosConsultarTest {

	@Autowired
	IBancoDeProyectoConsultar consultar;

	@MockBean
	ISessionFacadeAFS sessionFacadeAFS;

	@MockBean
	IEstadoServicioServiceRepo serviceRepo;

	@MockBean
	SessionConsultaAFS consultaAFS;

	@MockBean
	SPDDLogger logger;

	@Test
	public void testObtenerTodos() throws SpddExceptions {

		when(sessionFacadeAFS.consultarBancoDeProyectosTodos()).thenReturn(new GenericoDTO());
		if (consultar.obtenerTodos() != null) {
			GenericoDTO lista = consultar.obtenerTodos();
			assertThat(lista.getLstObjectsDTO()).isNotNull();
		}
	}

}
