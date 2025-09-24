package co.gov.sdp.spdd.data.serviciofacade.bp;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvAnualizaDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvAporteDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvFinanciaDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvLocalizaDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvTipoDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyectoInversionDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.spdd.data.model.bp.BpProyInvAnualiza;
import co.gov.sdp.spdd.data.model.bp.BpProyInvAporte;
import co.gov.sdp.spdd.data.model.bp.BpProyInvFinancia;
import co.gov.sdp.spdd.data.model.bp.BpProyInvLocaliza;
import co.gov.sdp.spdd.data.model.bp.BpProyInvTipo;
import co.gov.sdp.spdd.data.model.bp.BpProyectoInversion;

@RunWith(SpringRunner.class)
public class SessionRegistroBPTest extends SessionBPTest {
	
	@TestConfiguration
	static class SessionRegistroBPTestContextConfiguration {
		@Bean
		public SessionRegistroBP sessionRegistroBP() {
			return new SessionRegistroBP();
		}
	}

	@Autowired
	SessionRegistroBP sessionRegistroBP;
	
	@Test
	public void testGuardarProyectoInversionTABIndentificacionProyecto() throws SpddExceptions {
		
		BpProyectoInversionDTO bpProyectoInversionDTO = new BpProyectoInversionDTO();
		
		BpProyectoInversion bpProyectoInversion = new BpProyectoInversion();
		
		BpProyectoInversionDTO res;
		
		when(bpProyectoInversionMapper.bpProyectoInversionDTOToEntity(bpProyectoInversionDTO)).thenReturn(bpProyectoInversion);
		when(bpProyectoInversionServiceRepo.guardar(bpProyectoInversion)).thenReturn(bpProyectoInversion);
		when(bpProyectoInversionMapper.bpProyectoInversionEntityToDTO(bpProyectoInversion)).thenReturn(bpProyectoInversionDTO);
		res = sessionRegistroBP.guardarProyectoInversionTABIndentificacionProyecto(bpProyectoInversionDTO);
		assertNotNull(res);
	}
	
	@Test
	public void testGuardarProyInvTipo() throws SpddExceptions {
		
		BpProyInvTipoDTO bpProyInvTipoDTO = new BpProyInvTipoDTO();
		
		BpProyInvTipo bpProyInvTipo = new BpProyInvTipo();
		
		BpProyInvTipoDTO res;
		
		when(bpProyInvTipoMapper.bpProyInvTipoDTOToEntity(bpProyInvTipoDTO)).thenReturn(bpProyInvTipo);
		when(bpProyInvTipoServiceRepo.guardar(bpProyInvTipo)).thenReturn(bpProyInvTipo);
		when(bpProyInvTipoMapper.bpProyInvTipoEntityToDTO(bpProyInvTipo)).thenReturn(bpProyInvTipoDTO);
		res = sessionRegistroBP.guardarProyInvTipo(bpProyInvTipoDTO);
		assertNotNull(res);
	}
	
	@Test
	public void testGuardarBpProyInvAporte() throws SpddExceptions
	{
		BpProyInvAporteDTO bpProyInvAporteDTO = new BpProyInvAporteDTO();
		
		BpProyInvAporte bpProyInvAporte = new BpProyInvAporte();
		
		when(bpProyInvAporteMapper.bpProyInvAporteDTOToEntity(bpProyInvAporteDTO)).thenReturn(bpProyInvAporte);
		when(bpProyInvAporteServiceRepo.guardar(bpProyInvAporte)).thenReturn(bpProyInvAporte);
		when(bpProyInvAporteMapper.bpProyInvAporteEntityToDTO(bpProyInvAporte)).thenReturn(bpProyInvAporteDTO);
		BpProyInvAporteDTO res = sessionRegistroBP.guardarBpProyInvAporte(bpProyInvAporteDTO);
		assertNotNull(res);
	}


	@Test
	public void testGuardarBPProyectoInvLocaliza() throws SpddExceptions {
		BpProyInvLocalizaDTO bPProyInvLocalizaDTO = new BpProyInvLocalizaDTO();
		BpProyInvLocaliza bpProyInvLocaliza = new BpProyInvLocaliza();
		when(bpProyInvLocalizaMapper.BpProyInvLocalizaDTOToEntity(bPProyInvLocalizaDTO)).thenReturn(bpProyInvLocaliza);
		when(bpProyInvLocalizaServiceRepo.guardar(bpProyInvLocaliza)).thenReturn(bpProyInvLocaliza);
		when(bpProyInvLocalizaMapper.BpProyInvLocalizaEntityToDTO(bpProyInvLocaliza)).thenReturn(bPProyInvLocalizaDTO);
		BpProyInvLocalizaDTO res = sessionRegistroBP.guardarBPProyectoInvLocaliza(bPProyInvLocalizaDTO);
		assertNotNull(res);
	}

	@Test
	public void testGuardarGrupoEtario() throws Exception {
	}

	@Test
	public void testGuardarUbicacion() throws Exception {
	}

	@Test
	public void testGuardarCondicionIniciativa() throws Exception {
	}

	@Test
	public void testGuardarIniciativaCiudadana() throws Exception {
	}

	@Test
	public void testGuardarBPAporteCiudadano() throws Exception {
	}

	@Test
	public void testGuardarBpProyInvIniciativa() throws Exception {
	}

	@Test
	public void testGuardarBpProyInvEspecif() throws Exception {
	}

	@Test
	public void testGuardarBpProyInvMetaPlan() throws Exception {
	}

	@Test
	public void testGuardarBpProyInvProducto() throws Exception {
	}

	@Test
	public void testGuardarBpProyInvActividad() throws Exception {
	}

	@Test
	public void testRegistrarBpProyInvFinancia() throws Exception {
		
		BpProyInvFinanciaDTO peticion = new BpProyInvFinanciaDTO();
		BpProyInvFinancia obj = new BpProyInvFinancia();
		
		when(bpProyInvFinanciaMapper.bpProyInvFinanciaEntityToDTO(obj)).thenReturn(peticion);
		when(bpProyInvFinanciaServiceRepo.guardar(obj)).thenReturn(obj);
		when(bpProyInvFinanciaMapper.bpProyInvFinanciaDTOToEntity(peticion)).thenReturn(obj);
		BpProyInvFinanciaDTO res = sessionRegistroBP.registrarBpProyInvFinancia(peticion);
		assertNotNull(res);
		
	}

	@Test
	public void testRegistrarBpProyInvAnualiza() throws Exception {
		BpProyInvAnualizaDTO peticion = new BpProyInvAnualizaDTO();
		BpProyInvAnualiza obj = new BpProyInvAnualiza();
		
		when(bpProyInvAnualizaMapper.bpProyInvAnualizEntityToDTO(obj)).thenReturn(peticion);
		when(bpProyInvAnualizaServiceRepo.guardar(obj)).thenReturn(obj);
		when(bpProyInvAnualizaMapper.bpProyInvAnualizaDTOToEntity(peticion)).thenReturn(obj);
		BpProyInvAnualizaDTO res = sessionRegistroBP.registrarBpProyInvAnualiza(peticion);
		assertNotNull(res);
		
		
	}

}
