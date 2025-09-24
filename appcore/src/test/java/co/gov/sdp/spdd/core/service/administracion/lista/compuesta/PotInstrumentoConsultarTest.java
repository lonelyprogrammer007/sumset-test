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
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.spdd.core.iservice.administracion.lista.compuesta.IPotInstrumentoConsultar;
import co.gov.sdp.spdd.data.sesionfacade.impl.SessionFacadeAFS;
import co.gov.sdp.spdd.data.sesionfacade.interfaces.ISessionFacadeAFS;
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { SessionFacadeAFS.class, PotInstrumentoConsultar.class, IPotInstrumentoConsultar.class})
class PotInstrumentoConsultarTest {
	
	@Autowired
	IPotInstrumentoConsultar consultar;
	
	@MockBean
	ISessionFacadeAFS sessionFacadeAFS;
	
	@Test
	void testObtenerPotInstrumento() throws SpddExceptions {
		GenericoDTO res;
		when(sessionFacadeAFS.consultarPotInstrumento()).thenReturn(new GenericoDTO());
		res=consultar.obtenerPotInstrumento();
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(200L);
	}

}
