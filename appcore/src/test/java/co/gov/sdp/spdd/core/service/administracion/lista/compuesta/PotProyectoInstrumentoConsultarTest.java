package co.gov.sdp.spdd.core.service.administracion.lista.compuesta;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.dto.PotProyectoInstrumentoDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.spdd.core.iservice.administracion.lista.compuesta.IPotProyectoInstrumentoConsultar;
import co.gov.sdp.spdd.data.sesionfacade.impl.SessionFacadeAFS;
import co.gov.sdp.spdd.data.sesionfacade.interfaces.ISessionFacadeAFS;
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { SessionFacadeAFS.class, PotProyectoInstrumentoConsultar.class, IPotProyectoInstrumentoConsultar.class})
class PotProyectoInstrumentoConsultarTest {
	
	@Autowired
	IPotProyectoInstrumentoConsultar consultar;
	
	@MockBean
	ISessionFacadeAFS sessionFacadeAFS;
	
	/**
	 * Logger
	 */
	@MockBean
	SPDDLogger spddLogger;
	
	@Test
	void testObtenerTodosProyectoInstrumento() throws SpddExceptions {
		GenericoDTO res;
		when(sessionFacadeAFS.consultarPotProyectoInstrumentoTodos()).thenReturn(new GenericoDTO());
		res = consultar.obtenerTodosProyectoInstrumento();
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(200L);
	}

	@Test
	void testObtenerPaginado() throws SpddExceptions {
		GenericoDTO res;
		PotProyectoInstrumentoDTO peticion = new PotProyectoInstrumentoDTO();
		peticion.setIdPotInstrumento(1L);
		when(sessionFacadeAFS.consultarPotProyectoInstrumentoPorFiltro(peticion)).thenReturn(new GenericoDTO());
		res = consultar.obtenerPaginado(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(200L);
	}

}
