package co.gov.sdp.spdd.core.service.carga;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import co.gov.sdp.nhspdd.common.dto.ConfiguracionCargueArchivoDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.spdd.core.iservice.carga.IConfiguracionCargueArchivoConsultar;
import co.gov.sdp.spdd.data.sesionfacade.impl.SessionFacadeAFS;
import co.gov.sdp.spdd.data.sesionfacade.interfaces.ISessionFacadeAFS;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {SessionFacadeAFS.class, IConfiguracionCargueArchivoConsultar.class,ConfiguracionCargueArchivoConsultar.class})
class ConfiguracionCargueArchivoConsultarTest {

	@Autowired
	IConfiguracionCargueArchivoConsultar consultar;
	
	@MockBean
	ISessionFacadeAFS sessionFacadeAFS;
	
	@Test
	void testConsultarConfigCargueArchivoProcesado() throws SpddExceptions {
		Long id = 1L;
		ConfiguracionCargueArchivoDTO res;
		when(sessionFacadeAFS.consultarConfigCargueArchivoProcesado(id)).thenReturn(new ConfiguracionCargueArchivoDTO());
		res = consultar.consultarConfigCargueArchivoProcesado(id);
		assertNotNull(res);
	}

}
