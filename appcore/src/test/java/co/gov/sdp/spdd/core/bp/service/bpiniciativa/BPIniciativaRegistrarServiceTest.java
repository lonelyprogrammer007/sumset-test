package co.gov.sdp.spdd.core.bp.service.bpiniciativa;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.databind.ObjectMapper;

import co.gov.sdp.spdd.core.bp.iservice.bpiniciativa.IBPIniciativaRegistrarService;
import co.gov.sdp.spdd.data.sesionfacade.impl.SessionFacadeBP;
import co.gov.sdp.spdd.data.sesionfacade.interfaces.ISessionFacadeAFS;
import co.gov.sdp.spdd.data.sesionfacade.interfaces.ISessionFacadeBP;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { SessionFacadeBP.class, IBPIniciativaRegistrarService.class,
		BPIniciativaRegistrarService.class })
class BPIniciativaRegistrarServiceTest {
	
	@Autowired
	IBPIniciativaRegistrarService registrar;
	
	@MockBean
	ISessionFacadeBP sessionFacadeBP;

	@MockBean
	ISessionFacadeAFS sessionFacadeAFS;

	@MockBean
	ObjectMapper mapper;

	@Test
	void testGuardarIniciativaCiudadana() {
		/**
		 *  por el momento No se puede hacer por el type reference
		 */
	}

}
