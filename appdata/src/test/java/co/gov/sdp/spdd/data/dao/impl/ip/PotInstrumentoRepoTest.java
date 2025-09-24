package co.gov.sdp.spdd.data.dao.impl.ip;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import co.gov.sdp.spdd.data.irepositorio.ip.IPotInstrumentoRepo;
import co.gov.sdp.spdd.data.model.ip.PotInstrumento;

/**
 * 
 * @author Sumset
 *
 */
@RunWith(SpringRunner.class)
public class PotInstrumentoRepoTest {
	
	@TestConfiguration
	static class PotInstrumentoRepoTestContextConfiguration {
		@Bean
		public PotInstrumentoRepo PotInstrumentoRepo() {
			return new PotInstrumentoRepo();
		}
	}
	
	@Autowired
	private PotInstrumentoRepo potInstrumentoRepo;

	@MockBean
	private IPotInstrumentoRepo potInstrumentoRepository;

	@MockBean
	private EntityManager entityManager;

	@MockBean
	private EntityManagerFactory entityManagerFactory;

	@Test
	public void testGetRepo() {
		assertNotNull(potInstrumentoRepo.getRepo());
	}

	@Test
	public void testObtenerPorCodigoYIdPot() throws Exception {
		Long codigo = 1L;
		Long idPot = 1L;
		when(potInstrumentoRepository.obtenerPorCodigoYIdPot(codigo, idPot)).thenReturn(new PotInstrumento());
		PotInstrumento res= potInstrumentoRepo.obtenerPorCodigoYIdPot(codigo, idPot);
		assertNotNull(res);
	}

}
