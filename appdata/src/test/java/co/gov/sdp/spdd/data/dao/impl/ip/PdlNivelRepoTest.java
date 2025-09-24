package co.gov.sdp.spdd.data.dao.impl.ip;

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
import org.springframework.test.context.junit4.SpringRunner;

import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.spdd.data.irepositorio.ip.IPdlNivelRepo;
import co.gov.sdp.spdd.data.model.ip.PdlNivel;

/**
 * 
 * @author SEBASTIAN
 *
 */
@RunWith(SpringRunner.class)
public class PdlNivelRepoTest {
	
	@TestConfiguration
	static class PdlNivelRepoTestContextConfiguration {
		@Bean
		public PdlNivelRepo pdlNivelRepo() {
			return new PdlNivelRepo();
		}
	}

	@Autowired
	private PdlNivelRepo pdlNivelRepo;
	
	@MockBean
	private EntityManager entityManager;

	@MockBean
	private EntityManagerFactory entityManagerFactory;
	
	@MockBean
	private IPdlNivelRepo pdlNivelRepository;
	
	@Test
	public void testGetRepo() {
		assertNotNull(pdlNivelRepo.getRepo());
	}

	@Test
	public void testObtenerPdlNivelPorIdPlanLocal() throws SpddExceptions {
		Long idPlanlocal = 1L;
		when(pdlNivelRepository.obtenerPdlNivelPorIdPlanLocal(idPlanlocal)).thenReturn(new ArrayList<PdlNivel>());
		List<PdlNivel> res = pdlNivelRepo.obtenerPdlNivelPorIdPlanLocal(idPlanlocal);
		assertNotNull(res);
	}

	@Test
	public void testObtenerPdlNivelPorNivelYIdPlanLocal() throws SpddExceptions {
		Long idPlanlocal = 1L;
		Long idPldNivel = 1L;
		when(pdlNivelRepository.obtenerPdlNivelPorNivelYIdPlanLocal(idPldNivel, idPlanlocal)).thenReturn(new PdlNivel());
		PdlNivel res = pdlNivelRepo.obtenerPdlNivelPorNivelYIdPlanLocal(idPldNivel, idPlanlocal);
		assertNotNull(res);
	}

}
