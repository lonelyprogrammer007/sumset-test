package co.gov.sdp.spdd.data.dao.impl.bp;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import co.gov.sdp.spdd.data.irepositorio.bp.IBpProyInvTipoRepo;
import co.gov.sdp.spdd.data.model.bp.BpProyInvTipo;

@RunWith(SpringRunner.class)
public class BpProyInvTipoRepoTest {
	
	@TestConfiguration
	static class BpProyInvTipoRepoTestContextConfiguration {
		@Bean
		public BpProyInvTipoRepo bpProyInvTipoRepo() {
			return new BpProyInvTipoRepo();
		}
	}
	
	@Autowired
	BpProyInvTipoRepo bpProyInvTipoRepo;
	
	@MockBean
	IBpProyInvTipoRepo bpProyInvTipoRepository;

	@Test
	public void testGetRepo() {
		assertNotNull(bpProyInvTipoRepo.getRepo());
	}

	@Test
	public void testObtenerPorIdProyectoInversion() {
		Long idProyecto = 1L;
		List<BpProyInvTipo> lista = new ArrayList<>();
		when(bpProyInvTipoRepository.obtenerPorIdProyectoInversion(idProyecto)).thenReturn(lista);
		List<BpProyInvTipo> res =  bpProyInvTipoRepo.obtenerPorIdProyectoInversion(idProyecto);
		assertNotNull(res);
	}

	@Test
	public void testObtenerPorIdLsTipoYIdProyInversion() {
		Long idLsTipo = 1L;
		Long idProyInversion = 2L;
		when(bpProyInvTipoRepository.obtenerPorIdLsTipoYIdProyInversion(idLsTipo, idProyInversion)).thenReturn(new BpProyInvTipo());
		BpProyInvTipo res = bpProyInvTipoRepo.obtenerPorIdLsTipoYIdProyInversion(idLsTipo, idProyInversion);
		assertNotNull(res);
	}

}
