package co.gov.sdp.spdd.core.ip.service.ipformulacion;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.spdd.core.ip.iservice.ipformulacion.IIPFormulacionModificarService;
import co.gov.sdp.spdd.data.sesionfacade.impl.SessionFacadeIP;
import co.gov.sdp.spdd.util.AuditoriaUtil;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { IPFormulacionModificarService.class, IIPFormulacionModificarService.class,
		SessionFacadeIP.class })
public class IPFormulacionModificarServiceTest {

	@Autowired
	IPFormulacionModificarService modificar;

	@MockBean
	SessionFacadeIP sessionFacadeIP;

	@MockBean
	SPDDLogger spddLogger;

	@MockBean
	AuditoriaUtil auditoria;

	@Test
	public void testModificarCompromisoEstrategico() {
	}

	@Test
	public void testModificarPddCompetenciaAsociada() {
	}

	@Test
	public void testModificarPddCompromisoEspecifico() {
	}

	@Test
	public void testModificarPddMeta() {
	}

	@Test
	public void testModificarPddPrbValoracion() {
	}

	@Test
	public void testModificarObraConcreta() {
	}

	@Test
	public void testModificarPrbPoblacion() {
	}

	@Test
	public void testModificarPrbIndicador() {
	}

	@Test
	public void testModificarPddMetaResultado() {
	}

	@Test
	public void testModificarPddIndicador() {
	}

}
