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

import co.gov.sdp.nhspdd.common.dto.FiltroDTO;
import co.gov.sdp.nhspdd.common.dto.ParametroGeneralDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.spdd.data.irepositorio.afs.IParametroGeneralRepo;

/**
 * 
 * @author Sumset
 *
 */
@RunWith(SpringRunner.class)
public class ParametroGeneralRepoTest {

	@TestConfiguration
	static class ParametroGeneralRepoTestContextConfiguration {
		@Bean
		public ParametroGeneralRepo ParametroGeneralRepo() {
			return new ParametroGeneralRepo();
		}
	}
	
	@Autowired
	private ParametroGeneralRepo parametroGeneralRepo;

	@MockBean
	private IParametroGeneralRepo parametroGeneralRepository;

	@MockBean
	private EntityManager entityManager;

	@MockBean
	private EntityManagerFactory entityManagerFactory;
	
	@Test
	public void testGetRepo() {
		assertNotNull(parametroGeneralRepo.getRepo());
	}

	@Test
	public void testFiltrarPorCampo() throws SpddExceptions {
		ParametroGeneralDTO peticion = new ParametroGeneralDTO();
		FiltroDTO res;
		peticion.setArgumento("alex");
		//res = parametroGeneralRepo.filtrarPorCampo(peticion, 1L, 5);
	
		
		
		
	}

}
