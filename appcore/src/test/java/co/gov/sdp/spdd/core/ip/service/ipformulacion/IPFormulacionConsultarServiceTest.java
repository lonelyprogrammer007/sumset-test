package co.gov.sdp.spdd.core.ip.service.ipformulacion;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.spdd.core.ip.iservice.ipformulacion.IIPFormulacionConsultarService;
import co.gov.sdp.spdd.data.sesionfacade.impl.SessionFacadeIP;
import co.gov.sdp.spdd.util.AuditoriaUtil;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { IPFormulacionConsultarService.class, IIPFormulacionConsultarService.class, SessionFacadeIP.class })
class IPFormulacionConsultarServiceTest {
	
	@Autowired
	IPFormulacionConsultarService consultar;

	@MockBean
	SessionFacadeIP sessionFacadeIP;

	@MockBean
	SPDDLogger spddLogger;

	@MockBean
	AuditoriaUtil auditoria;

	@Test
	void testObtenerPaginado() {
	}

	@Test
	void testCompromisoEstrategicoObtenerPaginado() {
	}

	@Test
	void testConsultarMetasCompromistoEstrategico() {
	}

	@Test
	void testConsultarPddMetaResultadoPorIDProblematicaIndicador() {
	}

	@Test
	void testConsultarPddCompetenciaAsociadaPorIdSector() {
	}

	@Test
	void testConsultarPddCompromisosPorFiltro() {
	}

	@Test
	void testConsultarPddCompromisoPorIdPlanDesarrollo() {
	}

	@Test
	void testConsultarPddCompromisoEspecificoPorIdCompromiso() {
	}

	@Test
	void testConsultarPddPrbValoracionPorIdProblematicaYMomento() {
	}

	@Test
	void testConsultarObrasConcretasPorMetas() {
	}

	@Test
	void testConsultarPrbIndicadorPorProblematica() {
	}

	@Test
	void testConsultarPddProblematicaPorId() {
	}

	@Test
	void testConsultarPddIndicadorTodos() {
	}

	@Test
	void testConsultarPddProblematicaPorCompromiso() {
	}

	@Test
	void testConsultarPddIndicadorPorId() {
	}

	@Test
	void testConsultarPddCompromisoPorId() {
	}

	@Test
	void testConsultarPddVigente() {
	}

}
