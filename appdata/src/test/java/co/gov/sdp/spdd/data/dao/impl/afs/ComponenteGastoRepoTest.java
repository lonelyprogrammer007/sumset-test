package co.gov.sdp.spdd.data.dao.impl.afs;

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

import co.gov.sdp.spdd.data.irepositorio.afs.IComponenteGastoRepo;

/**
 * 
 * @author Sumset
 *
 */
@RunWith(SpringRunner.class)
public class ComponenteGastoRepoTest {
	
	@TestConfiguration
	static class ComponenteGastoRepoTestContextConfiguration {
		@Bean
		public ComponenteGastoRepo componenteGastoRepo() {
			return new ComponenteGastoRepo();
		}
	}
	
	@Autowired
	private ComponenteGastoRepo componenteGastoRepo;

	@MockBean
	private IComponenteGastoRepo componenteGastoRepository;

	@MockBean
	private EntityManager entityManager;

	@MockBean
	private EntityManagerFactory entityManagerFactory;

	@Test
	public void testGetRepo() {
		assertNotNull(componenteGastoRepo.getRepo());
	}

	@Test
	public void testFiltrarPorCampo() {
	}

	@Test
	public void testBuscarPorCodigoYNombre() {
	}

}
