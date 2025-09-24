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

import com.fasterxml.jackson.core.JsonProcessingException;

import co.gov.sdp.nhspdd.common.dto.TerritorializacionDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.spdd.core.iservice.administracion.lista.compuesta.ITerritorializacionRegistrar;
import co.gov.sdp.spdd.data.sesionfacade.impl.SessionFacadeAFS;
import co.gov.sdp.spdd.data.sesionfacade.interfaces.ISessionFacadeAFS;
import co.gov.sdp.spdd.util.AuditoriaUtil;
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { SessionFacadeAFS.class, TerritorializacionRegistrar.class, ITerritorializacionRegistrar.class})
class TerritorializacionRegistrarTest {
	
	@Autowired
	ITerritorializacionRegistrar registrar;
	
	@MockBean
	SPDDLogger spddLogger;
	
	@MockBean
	AuditoriaUtil auditoria;

	/**
	 * 
	 */
	@MockBean
	ISessionFacadeAFS sessionFacadeAFS;
	
	@Test
	void testRegistrarTerritorializacion() throws SpddExceptions, JsonProcessingException {
		TerritorializacionDTO peticion= new TerritorializacionDTO();
		peticion.setIdTerritorializacion(1L);
		TerritorializacionDTO res;
		
		when(sessionFacadeAFS.guardarTerritorializacion(peticion)).thenReturn(peticion);
		
		res = registrar.registrarTerritorializacion(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(200L);
		
		when(sessionFacadeAFS.guardarTerritorializacion(peticion)).thenReturn(new TerritorializacionDTO());
		
		res = registrar.registrarTerritorializacion(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
	}

}
