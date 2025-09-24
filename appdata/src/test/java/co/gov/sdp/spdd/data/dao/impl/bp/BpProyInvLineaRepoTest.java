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

import co.gov.sdp.spdd.data.irepositorio.bp.IBpProyInvLineaRepo;
import co.gov.sdp.spdd.data.model.bp.BpProyInvLinea;

@RunWith(SpringRunner.class)
public class BpProyInvLineaRepoTest {
	
	@TestConfiguration
	static class BpProyInvLineaRepoTestContextConfiguration {
		@Bean
		public BpProyInvLineaRepo bpProyInvLineaRepo() {
			return new BpProyInvLineaRepo();
		}
	}
	
	@Autowired
	private BpProyInvLineaRepo bpProyInvLineaRepo;

	@MockBean
	private IBpProyInvLineaRepo bpProyInvLineaRepository;
	

	@Test
	public void testGetRepo() {
		assertNotNull(bpProyInvLineaRepo.getRepo());
	}

	@Test
	public void testObtenerPorIdProyectoInversion() {
		Long idProyecto = 1L;
		List<BpProyInvLinea> lista = new ArrayList<>();
		when(bpProyInvLineaRepository.obtenerPorIdProyectoInversion(idProyecto)).thenReturn(lista);
		List<BpProyInvLinea> res = bpProyInvLineaRepo.obtenerPorIdProyectoInversion(idProyecto);
		assertNotNull(res);
	}

}
