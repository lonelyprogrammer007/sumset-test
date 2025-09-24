package co.gov.sdp.spdd.core.bp.service.bpproyinv;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import co.gov.sdp.nhspdd.common.dto.ArgumentoListaSimpleDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpAporteCiudadanoDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvAporteDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyectoInversionDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.spdd.core.bp.iservice.bpproyinv.IBPProyInvEliminarService;
import co.gov.sdp.spdd.data.sesionfacade.impl.SessionFacadeBP;
import co.gov.sdp.spdd.data.sesionfacade.interfaces.ISessionFacadeAFS;
import co.gov.sdp.spdd.data.sesionfacade.interfaces.ISessionFacadeBP;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { SessionFacadeBP.class, IBPProyInvEliminarService.class, BPProyInvEliminarService.class })
class BPProyInvEliminarServiceTest {
	
	@Autowired
	private BPProyInvEliminarService bpProyInvEliminarService;
	
	@MockBean
	ISessionFacadeBP sessionFacadeBP;
	
	@MockBean
	ISessionFacadeAFS sessionFacadeAFS;

	@Test
	void testEliminarBpProyInvAporte() throws SpddExceptions {
		Long idProyAporte = 1L;
		
		BpProyInvAporteDTO respuesta = new BpProyInvAporteDTO();
		respuesta.setIdAporte(1L);
		
		BpAporteCiudadanoDTO bpAporteAux = new BpAporteCiudadanoDTO();
		bpAporteAux.setIdArchivo(-1L);
		
		when(sessionFacadeBP.consultarProyInvAportePorId(idProyAporte)).thenReturn(respuesta);
		when(sessionFacadeBP.consultarBpAporteCiudadanoPorId(respuesta.getIdAporte())).thenReturn(bpAporteAux);
		BpProyInvAporteDTO res = bpProyInvEliminarService.eliminarBpProyInvAporte(idProyAporte);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(200L);
		
		when(sessionFacadeBP.consultarProyInvAportePorId(idProyAporte)).thenReturn(null);
		res = bpProyInvEliminarService.eliminarBpProyInvAporte(idProyAporte);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400);
		
	}

	@Test
	void testEliminarBpProyectoInversion() throws SpddExceptions {
		Long idProyectoInversion = 1L;
		
		BpProyectoInversionDTO bpProyectoInversionDTO = new BpProyectoInversionDTO();
		bpProyectoInversionDTO.setIdProyInversion(1L);
		bpProyectoInversionDTO.setIdLsEstado(1L);
		
		ArgumentoListaSimpleDTO argumentoEstadoSinInscripcion = new ArgumentoListaSimpleDTO();
		argumentoEstadoSinInscripcion.setIdArgumento(1L);
		
		ArgumentoListaSimpleDTO argumentoEstadoEliminado = new ArgumentoListaSimpleDTO();
		argumentoEstadoEliminado.setIdArgumento(1L);
		
		when(sessionFacadeBP.consultarProyInvPorId(idProyectoInversion)).thenReturn(bpProyectoInversionDTO);
		when(sessionFacadeAFS.consultarArgumentoListaSimplePorResultadoYNombreListaSimple(NHSPDDConstantes.BP_PROYECTO_INV_ESTADO_SIN_INSCRIPCION, NHSPDDConstantes.LS_ESTADOS_PROYECTO_INVERSION)).thenReturn(argumentoEstadoSinInscripcion);
		when(sessionFacadeAFS.consultarArgumentoListaSimplePorResultadoYNombreListaSimple(NHSPDDConstantes.BP_PROYECTO_INV_ESTADO_ELIMINADO, NHSPDDConstantes.LS_ESTADOS_PROYECTO_INVERSION)).thenReturn(argumentoEstadoEliminado);
		when(sessionFacadeBP.modificarProyectoInversionProyecto(bpProyectoInversionDTO)).thenReturn(new BpProyectoInversionDTO());
		BpProyectoInversionDTO res = bpProyInvEliminarService.eliminarBpProyectoInversion(idProyectoInversion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(200L);
		
		bpProyectoInversionDTO.setIdLsEstado(2L);
		res = bpProyInvEliminarService.eliminarBpProyectoInversion(idProyectoInversion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400);
		
		when(sessionFacadeBP.consultarProyInvPorId(idProyectoInversion)).thenReturn(null);
		res = bpProyInvEliminarService.eliminarBpProyectoInversion(idProyectoInversion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400);
	}

}
