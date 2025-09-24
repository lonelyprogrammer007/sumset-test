package co.gov.sdp.spdd.data.dao.impl.bp;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import co.gov.sdp.spdd.data.irepositorio.bp.IBpProyInvAporteRepo;
import co.gov.sdp.spdd.data.model.bp.BpProyInvAporte;

@RunWith(SpringRunner.class)
public class BpProyInvAporteRepoTest {
	
	@TestConfiguration
	static class BpProyInvAporteRepoTestContextConfiguration {
		@Bean
		public BpProyInvAporteRepo bpProyInvAporteRepo() {
			return new BpProyInvAporteRepo();
		}
	}
	
	@Autowired
	BpProyInvAporteRepo bpProyInvAporteRepo;
	
	@MockBean
	IBpProyInvAporteRepo bpProyInvAporteRepository;
	
	@MockBean
	private EntityManager entityManager;

	@MockBean
	private EntityManagerFactory entityManagerFactory;

	@Test
	public void testGetRepo() {
		assertNotNull(bpProyInvAporteRepo.getRepo());
	}

	@Test
	public void testObtenerPorIdAporteYIdProyInversion() {
		Long idProyInversion = 1L;
		Long idAporte = 1L;
		when(bpProyInvAporteRepository.obtenerPorIdAporteYIdProyInversion(idAporte, idProyInversion)).thenReturn(new BpProyInvAporte());
		BpProyInvAporte res = bpProyInvAporteRepo.obtenerPorIdAporteYIdProyInversion(idAporte, idProyInversion);
		assertNotNull(res);
	}

	@Test
	public void testObtenerTodosPorIdProyInversionPaginado() throws Exception {
		Long idProyInversion = 1L;
		Pageable pageRequest = PageRequest.of(0, 10);
		
		List<BpProyInvAporte> lista = new ArrayList<BpProyInvAporte>();
		Page<BpProyInvAporte> pagina = new PageImpl<BpProyInvAporte>(lista,pageRequest,lista.size());
		
		when(bpProyInvAporteRepository.obtenerTodosPorIdProyInversionPaginado(idProyInversion, pageRequest)).thenReturn(pagina);
		Page<BpProyInvAporte> res = bpProyInvAporteRepo.obtenerTodosPorIdProyInversionPaginado(idProyInversion, pageRequest);
		assertNotNull(res);
	}

}
