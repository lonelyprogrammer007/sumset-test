package co.gov.sdp.spdd.data.dao.impl.ip;

import static org.junit.Assert.assertNotNull;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import co.gov.sdp.spdd.data.irepositorio.ip.IPotProyectoInstrumentoRepo;

/**
 * 
 * @author Sumset
 *
 */
@RunWith(SpringRunner.class)
public class PotProyectoInstrumentoRepoTest {

	@TestConfiguration
	static class PotProyectoInstrumentoRepoTestContextConfiguration {
		@Bean
		public PotProyectoInstrumentoRepo PotProyectoInstrumentoRepo() {
			return new PotProyectoInstrumentoRepo();
		}
	}
	
	@Autowired
	private PotProyectoInstrumentoRepo potProyectoInstrumentoRepo;

	@MockBean
	private IPotProyectoInstrumentoRepo potProyectoInstrumentoRepository;

	@MockBean
	private EntityManager entityManager;

	@MockBean
	private EntityManagerFactory entityManagerFactory;
	
	@Test
	public void testGetRepo() {
		assertNotNull(potProyectoInstrumentoRepo.getRepo());
	}

	@Test
	public void testFiltrarPorCampo() {
	}

	@Test
	public void testBuscarPorIdLsPotObraAndIdLsPotInstrumento() {
	}

}
