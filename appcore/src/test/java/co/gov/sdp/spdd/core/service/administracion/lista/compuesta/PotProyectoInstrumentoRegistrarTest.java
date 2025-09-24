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

import co.gov.sdp.nhspdd.common.dto.PotProyectoInstrumentoDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.spdd.core.iservice.administracion.lista.compuesta.IPotProyectoInstrumentoRegistrar;
import co.gov.sdp.spdd.data.sesionfacade.impl.SessionFacadeAFS;
import co.gov.sdp.spdd.data.sesionfacade.interfaces.ISessionFacadeAFS;
import co.gov.sdp.spdd.util.AuditoriaUtil;
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { SessionFacadeAFS.class, PotProyectoInstrumentoRegistrar.class, IPotProyectoInstrumentoRegistrar.class})
class PotProyectoInstrumentoRegistrarTest {
	
	@Autowired
	IPotProyectoInstrumentoRegistrar registrar;
	
	@MockBean
	ISessionFacadeAFS sessionFacadeAFS;
	
	@MockBean
	AuditoriaUtil auditoria;
	
	@Test
	void testRegistrarProyectoInstrumento() throws SpddExceptions, JsonProcessingException {
		PotProyectoInstrumentoDTO entidad = new PotProyectoInstrumentoDTO();
		PotProyectoInstrumentoDTO res;
		entidad.setIdProyectoInstrumento(1L);
		entidad.setIdPotInstrumento(1L);
		entidad.setIdPotInstrumento(1L);
		when(sessionFacadeAFS.buscarPorIdLsPotObraYIdLsPotInstrumento(entidad)).thenReturn(entidad);
		res = registrar.registrarProyectoInstrumento(entidad);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
		
		
		when(sessionFacadeAFS.buscarPorIdLsPotObraYIdLsPotInstrumento(entidad)).thenReturn(entidad);
		entidad.setIdProyectoInstrumento(null);
		when(sessionFacadeAFS.guardarPotProyectoInstrumento(entidad)).thenReturn(new PotProyectoInstrumentoDTO());
		res = registrar.registrarProyectoInstrumento(entidad);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(200L);
		
	}

}
