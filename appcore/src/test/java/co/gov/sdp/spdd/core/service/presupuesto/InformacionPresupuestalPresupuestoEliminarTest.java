package co.gov.sdp.spdd.core.service.presupuesto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import co.gov.sdp.nhspdd.common.dto.InformacionPresupuestalDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.spdd.core.iservice.presupuesto.IInformacionPresupuestalPresupuestoEliminar;
import co.gov.sdp.spdd.data.sesionfacade.impl.SessionFacadeAFS;
import co.gov.sdp.spdd.data.sesionfacade.interfaces.ISessionFacadeAFS;
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { SessionFacadeAFS.class, InformacionPresupuestalPresupuestoEliminar.class, IInformacionPresupuestalPresupuestoEliminar.class})
class InformacionPresupuestalPresupuestoEliminarTest {
	
	@Autowired
	IInformacionPresupuestalPresupuestoEliminar eliminar;
	/**
	 * Inyeccion del SessionFacade del modulo IP
	 */
	@MockBean
	ISessionFacadeAFS sessionFacadeAFS;

	/**
	 * Logger
	 */
	@MockBean
	SPDDLogger spddLogger;
	
	@Test
	void testEliminarInformacionPresupuestal() throws SpddExceptions {
		Long id = 1L;
		InformacionPresupuestalDTO res;
		InformacionPresupuestalDTO info = new InformacionPresupuestalDTO();
		info.setIdInfoPresupuestal(1L);
		
		when(sessionFacadeAFS.consultarInformacionPresupuestalPorId(id)).thenReturn(info);
		res = eliminar.eliminarInformacionPresupuestal(id);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(200L);
		
		when(sessionFacadeAFS.consultarInformacionPresupuestalPorId(id)).thenReturn(null);
		res = eliminar.eliminarInformacionPresupuestal(id);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
	}

}
