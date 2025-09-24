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

import co.gov.sdp.nhspdd.common.dto.bp.BpAporteCiudadanoDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvTipoDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyectoInversionDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.spdd.core.bp.iservice.bpproyinv.IBPProyInvModificarService;
import co.gov.sdp.spdd.data.sesionfacade.impl.SessionFacadeBP;
import co.gov.sdp.spdd.data.sesionfacade.interfaces.ISessionFacadeBP;
import co.gov.sdp.spdd.util.AuditoriaUtil;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { SessionFacadeBP.class, IBPProyInvModificarService.class, BPProyInvModificarService.class })
class BPProyInvModificarServiceTest {

	@Autowired
	private BPProyInvModificarService bPProyInvModificarService;

	@MockBean
	private ISessionFacadeBP sessionFacadeBP;

	@MockBean
	AuditoriaUtil util;

	@Test
	public void testModificarProyectoInversion() throws SpddExceptions {
		BpProyectoInversionDTO peticion = new BpProyectoInversionDTO();
		peticion.setIdProyInversion(1L);
		peticion.setIdsTipoProy("1");

		BpProyectoInversionDTO respuesta = new BpProyectoInversionDTO();
		respuesta.setIdProyInversion(1L);

		BpProyInvTipoDTO bpProyInvTipoDTO = new BpProyInvTipoDTO();
		bpProyInvTipoDTO.setIdTipo(1L);

		when(sessionFacadeBP.consultarProyInvPorId(peticion.getIdProyInversion())).thenReturn(respuesta);
		when(sessionFacadeBP.modificarProyectoInversionProyecto(peticion)).thenReturn(respuesta);
		BpProyectoInversionDTO res = bPProyInvModificarService.modificarProyectoInversion(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(200L);

		when(sessionFacadeBP.consultarProyInvPorId(peticion.getIdProyInversion())).thenReturn(null);
		res = bPProyInvModificarService.modificarProyectoInversion(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
	}

	@Test
	public void testModificarBpAporteCiudadano() throws Exception {
		BpAporteCiudadanoDTO peticion = new BpAporteCiudadanoDTO();
		peticion.setIdNivelAtributoPddOpcion3(1L);
		peticion.setIdAporte(1L);
		peticion.setAccion("accion");
		peticion.setFuente("fuente");
		peticion.setIdNivelAtributoPdd(1L);
		
		
		BpAporteCiudadanoDTO respuesta = new BpAporteCiudadanoDTO();
		
		BpAporteCiudadanoDTO bpAporteCiudadanoDTO = new BpAporteCiudadanoDTO();
		bpAporteCiudadanoDTO.setIdAporte(1L);
		
		BpAporteCiudadanoDTO bpAporteCiudadanoDTOAux = new BpAporteCiudadanoDTO();
		bpAporteCiudadanoDTOAux.setIdAporte(1L);
		
		when(sessionFacadeBP.consultarBpAporteCiudadanoPorId(peticion.getIdAporte())).thenReturn(bpAporteCiudadanoDTO);
		when(sessionFacadeBP.consultarBpAporteCiudadanoPorAccionYFuenteYIdNivelPdd(peticion.getAccion(), peticion.getFuente(), peticion.getIdNivelAtributoPdd())).thenReturn(bpAporteCiudadanoDTOAux);
		when(sessionFacadeBP.modificarBpAporteCiudadano(peticion)).thenReturn(respuesta);
		BpAporteCiudadanoDTO res = bPProyInvModificarService.modificarBpAporteCiudadano(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(200);
		
		bpAporteCiudadanoDTOAux.setIdAporte(2L);
		respuesta.setIdAporte(1L);
		when(sessionFacadeBP.guardarBPAporteCiudadano(peticion)).thenReturn(respuesta);
		res = bPProyInvModificarService.modificarBpAporteCiudadano(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(200);
		
		respuesta.setIdAporte(null);
		res = bPProyInvModificarService.modificarBpAporteCiudadano(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400);
		
		peticion.setIdNivelAtributoPddOpcion3(null);		
		when(sessionFacadeBP.consultarBpAporteCiudadanoPorId(peticion.getIdAporte())).thenReturn(null);
		res = bPProyInvModificarService.modificarBpAporteCiudadano(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400);
	}

}