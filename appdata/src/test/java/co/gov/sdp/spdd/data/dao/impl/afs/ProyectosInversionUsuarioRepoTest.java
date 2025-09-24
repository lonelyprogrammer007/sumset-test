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

import co.gov.sdp.spdd.data.irepositorio.afs.IProyectosInversionUsuarioRepo;

/**
 * 
 * @author Sumset
 *
 */
@RunWith(SpringRunner.class)
public class ProyectosInversionUsuarioRepoTest {

	@TestConfiguration
	static class ProyectosInversionUsuarioRepoTestContextConfiguration {
		@Bean
		public ProyectosInversionUsuarioRepo ProyectosInversionUsuarioRepo() {
			return new ProyectosInversionUsuarioRepo();
		}
	}
	
	@Autowired
	private ProyectosInversionUsuarioRepo proyectosInversionUsuarioRepo;

	@MockBean
	private IProyectosInversionUsuarioRepo ProyectosInversionUsuarioRepository;

	@MockBean
	private EntityManager entityManager;

	@MockBean
	private EntityManagerFactory entityManagerFactory;
	
	@Test
	public void testGetRepo() {
		assertNotNull(proyectosInversionUsuarioRepo.getRepo());
	}

	@Test
	public void testObtenerPorUsuario() {
	}

}
