package co.gov.sdp.spdd.core.ip.service.ipformulacion;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.spdd.core.ip.iservice.ipformulacion.IIPFormulacionEliminarService;
import co.gov.sdp.spdd.data.sesionfacade.impl.SessionFacadeIP;
import co.gov.sdp.spdd.util.AuditoriaUtil;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { IPFormulacionEliminarService.class, IIPFormulacionEliminarService.class, SessionFacadeIP.class })
class IPFormulacionEliminarServiceTest {

	@Autowired
	IPFormulacionEliminarService eliminar;

	@MockBean
	SessionFacadeIP sessionFacadeIP;

	@MockBean
	SPDDLogger spddLogger;

	@MockBean
	AuditoriaUtil auditoria;
	
	@Test
	void testEliminarPddMeta() {
	}

	@Test
	void testEliminarPddObraConcreta() {
	}

	@Test
	void testEliminarPddCompromisoEspecifico() {
	}

	@Test
	void testEliminarPrbPoblacion() {
	}

	@Test
	void testEliminarPrbIndicador() {
	}

}
