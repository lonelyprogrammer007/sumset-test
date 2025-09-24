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

import co.gov.sdp.spdd.data.irepositorio.afs.IComponenteGestionUsuarioRepo;

/**
 * 
 * @author Sumset
 *
 */
@RunWith(SpringRunner.class)
public class ComponenteGestionUsuarioRepoTest {
	
	@TestConfiguration
    static class ComponenteGestionUsuarioRepoTestContextConfiguration {
        @Bean
        public ComponenteGestionUsuarioRepo componenteGestionUsuarioRepo() {
            return new ComponenteGestionUsuarioRepo();
        }
    }
	
	@Autowired
	private ComponenteGestionUsuarioRepo componenteGestionUsuarioRepo;
	
	@MockBean
	private IComponenteGestionUsuarioRepo componenteGestionUsuarioRepository;
	
    @MockBean
    private EntityManager entityManager;
    
    @MockBean
    private EntityManagerFactory entityManagerFactory;

	@Test
	public void testGetRepo() {
		assertNotNull(componenteGestionUsuarioRepo.getRepo());
	}

}
