package co.gov.sdp.spdd.core.bp.service.bpiniciativa;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import co.gov.sdp.nhspdd.common.dto.bp.BpIniciativaCiudadanaDTO;
import co.gov.sdp.spdd.core.bp.iservice.bpiniciativa.IBPIniciativaEliminarService;
import co.gov.sdp.spdd.data.sesionfacade.impl.SessionFacadeAFS;
import co.gov.sdp.spdd.data.sesionfacade.impl.SessionFacadeBP;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { SessionFacadeBP.class, IBPIniciativaEliminarService.class,
		BPIniciativaEliminarService.class })
public class BPIniciativaEliminarServiceTest {

	@Autowired
	IBPIniciativaEliminarService eliminar;

	@MockBean
	SessionFacadeBP sessionFacadeBP;

	@MockBean
	SessionFacadeAFS sessionAFS;

	@Test
	public void testEliminarIniciativaCiudadana() throws Exception {
		doNothing().when(sessionFacadeBP).eliminarUbicaciones(1L);
		doNothing().when(sessionFacadeBP).eliminarGruposEtarios(1L);
		doNothing().when(sessionFacadeBP).eliminarTodasCondicionIniciativa(1L);
		doNothing().when(sessionFacadeBP).eliminarIniciativaCiudadana(1L);
		BpIniciativaCiudadanaDTO res = eliminar.eliminarIniciativaCiudadana(1L);
		assertThat(res).isNotNull();
		assertEquals(res.getCodigoRespuesta(), 400);
		
		when(sessionFacadeBP.consultarBpIniciativaCiudadanaPorId(1L)).thenReturn(new BpIniciativaCiudadanaDTO());
		res = eliminar.eliminarIniciativaCiudadana(1L);
		assertThat(res).isNotNull();
		assertEquals(res.getCodigoRespuesta(), 200);
	}

}
