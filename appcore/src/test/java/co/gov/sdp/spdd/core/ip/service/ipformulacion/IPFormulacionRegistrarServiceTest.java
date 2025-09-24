package co.gov.sdp.spdd.core.ip.service.ipformulacion;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import co.gov.sdp.nhspdd.common.dto.ip.PddMetaResultadoDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.spdd.core.ip.iservice.ipformulacion.IIPFormulacionRegistrarService;
import co.gov.sdp.spdd.data.sesionfacade.impl.SessionFacadeAFS;
import co.gov.sdp.spdd.data.sesionfacade.impl.SessionFacadeIP;
import co.gov.sdp.spdd.util.AuditoriaUtil;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { IIPFormulacionRegistrarService.class, IPFormulacionRegistrarService.class,
		SessionFacadeIP.class, SessionFacadeAFS.class })
public class IPFormulacionRegistrarServiceTest {

	@Autowired
	IPFormulacionRegistrarService registrar;

	@MockBean
	SessionFacadeIP sessionFacadeIP;

	@MockBean
	SessionFacadeAFS sessionFacadeAFS;
	
	@MockBean
	SPDDLogger spddLogger;

	@MockBean
	AuditoriaUtil auditoriaUtil;

	@Test
	public void testRegistrarCompromisoEstrategico() {
	}

	@Test
	public void testRegistrarPddCompetenciaAsociada() {
	}

	@Test
	public void testRegistrarPddCompromisoEspecifico() {
	}

	@Test
	public void testRegistrarPddCompromiso() {
	}

	@Test
	public void testRegistrarPddPrbValoracion() {
	}

	@Test
	public void testRegistrarMetaPorCompromiso() {
	}

	@Test
	public void testRegistrarObraConcretaPorMeta() {
	}

	@Test
	public void testRegistrarProblematica() {
	}

	@Test
	public void testRegistrarPrbPoblacion() {
	}

	@Test
	public void testRegistrarPrbIndicador() {
	}

	@Test
	public void testRegistrarPddMetaResultado() throws SpddExceptions {
		PddMetaResultadoDTO peticion = new PddMetaResultadoDTO();
		when(sessionFacadeIP.guardarPddMetaResultado(peticion)).thenReturn(new PddMetaResultadoDTO());
		assertThat(registrar.registrarPddMetaResultado(peticion).getCodigoRespuesta()).isEqualTo(400);
		peticion.setIdMetaResultado(1L);
		when(sessionFacadeIP.guardarPddMetaResultado(peticion)).thenReturn(peticion);
		assertThat(registrar.registrarPddMetaResultado(peticion).getCodigoRespuesta()).isEqualTo(200);

	}

	@Test
	public void testRegistrarPddIndicadorYPddPrbIndicador() {
	}

}
