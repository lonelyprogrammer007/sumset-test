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

import co.gov.sdp.nhspdd.common.dto.PotProyectoInstrumentoDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.spdd.core.iservice.administracion.lista.compuesta.IPotProyectoInstrumentoModificar;
import co.gov.sdp.spdd.data.dao.interfaces.ip.IPotProyectoInstrumentoServiceRepo;
import co.gov.sdp.spdd.data.mapper.PotProyectoInstrumentoMapper;
import co.gov.sdp.spdd.data.sesionfacade.impl.SessionFacadeAFS;
import co.gov.sdp.spdd.data.sesionfacade.interfaces.ISessionFacadeAFS;
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { SessionFacadeAFS.class, PotProyectoInstrumentoModificar.class, IPotProyectoInstrumentoModificar.class})
class PotProyectoInstrumentoModificarTest {
	
	@Autowired
	IPotProyectoInstrumentoModificar modificar;
	
	@MockBean
	IPotProyectoInstrumentoServiceRepo potProyectoInstrumentoServiceRepo;

	/**
	 * Objeto que permite mapear de dto a entidad y viceversa
	 */
	@MockBean
	PotProyectoInstrumentoMapper potProyectoInstrumentoMapper;

	/**
	 * 
	 */
	@MockBean
	ISessionFacadeAFS sessionFacadeAFS;

	
	@Test
	void testModificarProyectoInstrumento() throws SpddExceptions {
		PotProyectoInstrumentoDTO entidad = new PotProyectoInstrumentoDTO();
		entidad.setIdProyectoInstrumento(1L);;
		entidad.setIdPotProyecto(2L);
		entidad.setIdPotInstrumento(2L);
		PotProyectoInstrumentoDTO auxiliar = new PotProyectoInstrumentoDTO();
		auxiliar.setIdProyectoInstrumento(1L);
		auxiliar.setIdPotProyecto(2L);
		auxiliar.setIdPotInstrumento(2L);
		PotProyectoInstrumentoDTO res;
		
		when(sessionFacadeAFS.consultarPotProyectoInstrumentoPorId(entidad.getIdProyectoInstrumento())).thenReturn(entidad);
		when(sessionFacadeAFS.buscarPorIdLsPotObraYIdLsPotInstrumento(entidad)).thenReturn(auxiliar);
		when(sessionFacadeAFS.guardarPotProyectoInstrumento(auxiliar)).thenReturn(new PotProyectoInstrumentoDTO());
		when(sessionFacadeAFS.modificarProyectoInstrumento(auxiliar)).thenReturn(auxiliar);
		res = modificar.modificarProyectoInstrumento(auxiliar);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(200L);
		
		entidad.setIdProyectoInstrumento(2L);
		when(sessionFacadeAFS.consultarPotProyectoInstrumentoPorId(auxiliar.getIdProyectoInstrumento())).thenReturn(auxiliar);
		when(sessionFacadeAFS.buscarPorIdLsPotObraYIdLsPotInstrumento(auxiliar)).thenReturn(entidad);
		when(sessionFacadeAFS.guardarPotProyectoInstrumento(auxiliar)).thenReturn(auxiliar);
		res = modificar.modificarProyectoInstrumento(auxiliar);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(200L);
		
		entidad.setIdProyectoInstrumento(2L);
		when(sessionFacadeAFS.consultarPotProyectoInstrumentoPorId(auxiliar.getIdProyectoInstrumento())).thenReturn(auxiliar);
		when(sessionFacadeAFS.buscarPorIdLsPotObraYIdLsPotInstrumento(auxiliar)).thenReturn(entidad);
		when(sessionFacadeAFS.guardarPotProyectoInstrumento(auxiliar)).thenReturn(new PotProyectoInstrumentoDTO());
		
		res = modificar.modificarProyectoInstrumento(auxiliar);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
		
		when(sessionFacadeAFS.consultarPotProyectoInstrumentoPorId(auxiliar.getIdProyectoInstrumento())).thenReturn(null);
		when(sessionFacadeAFS.buscarPorIdLsPotObraYIdLsPotInstrumento(auxiliar)).thenReturn(entidad);
		when(sessionFacadeAFS.guardarPotProyectoInstrumento(auxiliar)).thenReturn(new PotProyectoInstrumentoDTO());
		
		res = modificar.modificarProyectoInstrumento(auxiliar);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
	}

}
