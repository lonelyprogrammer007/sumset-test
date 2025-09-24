package co.gov.sdp.spdd.core.ip.service.ippot;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import co.gov.sdp.nhspdd.common.dto.PotInstrumentoDTO;
import co.gov.sdp.nhspdd.common.dto.PotObraDTO;
import co.gov.sdp.spdd.core.ip.iservice.ippot.IIPPotIEliminarService;
import co.gov.sdp.spdd.data.sesionfacade.impl.SessionFacadeIP;
import co.gov.sdp.spdd.data.sesionfacade.interfaces.ISessionFacadeIP;
import co.gov.sdp.spdd.util.AuditoriaUtil;

@SpringBootTest(classes = { SessionFacadeIP.class, IPPotEliminarService.class,
		IIPPotIEliminarService.class })
public class IPPotEliminarServiceTest {
	
	@MockBean
	private AuditoriaUtil auditoria;

	@MockBean
	private ISessionFacadeIP sessionFacadeIP;
	
	@Autowired
	private IPPotEliminarService iPPotEliminarService;

	@Test
	public void testEliminarPotRama() throws Exception {
	}

	@Test
	public void testEliminarNivelDTO() throws Exception {
	}

	@Test
	public void testEliminarPotObra() throws Exception {
		Long idPotObra = 1L;
		
		PotObraDTO respuesta = new PotObraDTO();
		respuesta.setIdObraProyPot(1L);
		
		when(sessionFacadeIP.consultarPotObraPorId(idPotObra)).thenReturn(respuesta);
		doNothing().when(sessionFacadeIP).eliminarTodosPotObraEntidadPorIdPotObra(idPotObra);
		doNothing().when(sessionFacadeIP).eliminarPotObra(idPotObra);
		PotObraDTO res = iPPotEliminarService.eliminarPotObra(idPotObra);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(200);
		
		when(sessionFacadeIP.consultarPotObraPorId(idPotObra)).thenReturn(null);
		res = iPPotEliminarService.eliminarPotObra(idPotObra);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400);
	}

	@Test
	public void testEliminarPotInstrumento() throws Exception {
		Long idPotInstrumento = 1L;
		
		PotInstrumentoDTO respuesta = new PotInstrumentoDTO();
		respuesta.setIdInstrumentoPot(1L);
		
		when(sessionFacadeIP.consultarPotInstrumentoPorId(idPotInstrumento)).thenReturn(respuesta);
		doNothing().when(sessionFacadeIP).eliminarPotInstrumento(idPotInstrumento);
		PotInstrumentoDTO res = iPPotEliminarService.eliminarPotInstrumento(idPotInstrumento);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(200);
		
		when(sessionFacadeIP.consultarPotInstrumentoPorId(idPotInstrumento)).thenReturn(null);
		res = iPPotEliminarService.eliminarPotInstrumento(idPotInstrumento);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400);
	}

}
