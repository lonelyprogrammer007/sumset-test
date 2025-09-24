package co.gov.sdp.spdd.data.dao.impl.afs;

import static org.junit.Assert.assertNotNull;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import co.gov.sdp.spdd.data.irepositorio.afs.IListaSimpleRepo;
import co.gov.sdp.spdd.data.model.afs.ListaSimple;


/**
 * 
 * @author Sumset
 *
 */
@RunWith(SpringRunner.class)
public class ListaSimpleRepoTest {

	@TestConfiguration
	static class ListaSimpleRepoTestContextConfiguration {
		@Bean
		public ListaSimpleRepo ListaSimpleRepo() {
			return new ListaSimpleRepo();
		}
	}
		
	@Autowired
	private ListaSimpleRepo listaSimpleRepo;

	@MockBean
	private IListaSimpleRepo listaSimpleRepository;

	@MockBean
	private EntityManager entityManager;

	@MockBean
	private EntityManagerFactory entityManagerFactory;
	
	@MockBean
	private CriteriaBuilder cb;
	
	@MockBean
	private CriteriaQuery<ListaSimple> cq;
	
	@MockBean
	private CriteriaQuery<Long> cq2;
	
	@MockBean
	private Root<ListaSimple> listaSimple;
	
	
	@Test
	public void testGetRepo() {
		assertNotNull(listaSimpleRepo.getRepo());
	}

	@Test
	public void testFiltrarPorCampo() {
		
	}

}
