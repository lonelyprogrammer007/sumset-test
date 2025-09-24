package co.gov.sdp.spdd.core.service.administracion.lista.compuesta;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.JsonProcessingException;

import co.gov.sdp.nhspdd.common.dto.ComponenteGastoDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.spdd.core.iservice.administracion.lista.compuesta.IComponenteGastoAdministracionRegistrar;
import co.gov.sdp.spdd.data.sesionfacade.impl.SessionFacadeAFS;
import co.gov.sdp.spdd.data.sesionfacade.interfaces.ISessionFacadeAFS;
import co.gov.sdp.spdd.util.AuditoriaUtil;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { SessionFacadeAFS.class, IComponenteGastoAdministracionRegistrar.class, ComponenteGastoAdministracionRegistrar.class})
public class ComponenteGastoAdministracionRegistrarTest {

	@Autowired
	IComponenteGastoAdministracionRegistrar registrar;
	
	@MockBean
	ISessionFacadeAFS sessionFacadeAFS;

	@MockBean
	SPDDLogger spddLogger;
	
	@MockBean
	AuditoriaUtil auditoria;
	
	@Test
	public void testCrearComponenteGasto() throws SpddExceptions, JsonProcessingException {
		
		ComponenteGastoDTO peticion = new ComponenteGastoDTO();
		peticion.setIdComponenteGasto(1L);
		
		ComponenteGastoDTO componente = new ComponenteGastoDTO();
		componente.setIdComponenteGasto(1L);
		
		ComponenteGastoDTO res;		
		
		
		when(sessionFacadeAFS.guardarComponenteGasto(peticion)).thenReturn(componente);
		res = registrar.crearComponenteGasto(peticion);
		assertNotNull(res);
        assertThat(res.getCodigoRespuesta()).isEqualTo(200);
        
        componente.setIdComponenteGasto(null);
        res = registrar.crearComponenteGasto(peticion);
		assertNotNull(res);
        assertThat(res.getCodigoRespuesta()).isEqualTo(400);
        
	}

}
