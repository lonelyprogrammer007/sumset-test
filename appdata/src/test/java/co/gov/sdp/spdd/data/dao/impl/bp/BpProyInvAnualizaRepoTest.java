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

import co.gov.sdp.spdd.data.irepositorio.bp.IBpProyInvAnualizaRepo;
import co.gov.sdp.spdd.data.model.bp.BpProyInvAnualiza;


@RunWith(SpringRunner.class)
public class BpProyInvAnualizaRepoTest {
	
	@TestConfiguration
	static class BpProyInvAnualizaRepoTestContextConfiguration {
		@Bean
		public BpProyInvAporteRepo bpProyInvAporteRepo() {
			return new BpProyInvAporteRepo();
		}
	}
	
	@Autowired 
	BpProyInvAnualizaRepo bpProyInvAporteRepo;
	
	@MockBean
    IBpProyInvAnualizaRepo iBpProyInvAnualizaRepo;
	
	@MockBean
	private EntityManager entityManager;

	@MockBean
	private EntityManagerFactory entityManagerFactory;
	
	@Test
	public void testGetRepo() throws Exception {
		assertNotNull(bpProyInvAporteRepo.getRepo());
	}
	
	@Test
	public void testObtenerPorIdTodosProyInvAnualiza() throws Exception {
		
		Long idFuente = 1L;
		Pageable pageRequest = PageRequest.of(0, 10);
		
		List<BpProyInvAnualiza> bpProyInvAnualiza = new ArrayList<>();
		
		when(iBpProyInvAnualizaRepo.obtenerPorIdTodosProyInvAnualiza(idFuente,pageRequest)).thenReturn(new PageImpl<>(bpProyInvAnualiza));
		
		Page<BpProyInvAnualiza>  res=  bpProyInvAporteRepo.obtenerPorIdTodosProyInvAnualiza(idFuente, pageRequest);
		
		assertNotNull(res);
	}


	
	
	

}
