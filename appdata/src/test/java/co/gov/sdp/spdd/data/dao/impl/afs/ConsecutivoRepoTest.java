package co.gov.sdp.spdd.data.dao.impl.afs;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.jayway.jsonpath.internal.Path;

import co.gov.sdp.nhspdd.common.dto.ConsecutivoDTO;
import co.gov.sdp.nhspdd.common.dto.FiltroDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.spdd.data.irepositorio.afs.IConsecutivoRepo;
import co.gov.sdp.spdd.data.model.afs.Consecutivo;

/**
 * 
 * @author Sumset
 *
 */
@RunWith(SpringRunner.class)
public class ConsecutivoRepoTest {
	
	@TestConfiguration
	static class ConsecutivoRepoTestContextConfiguration {
		@Bean
		public ConsecutivoRepo ConsecutivoRepo() {
			return new ConsecutivoRepo();
		}
	}
	
	@Autowired
	private ConsecutivoRepo consecutivoRepo;

	@MockBean
	private IConsecutivoRepo consecutivoRepository;

	@MockBean
	private EntityManager entityManager;

	@MockBean
	private EntityManagerFactory entityManagerFactory;
	
	@Mock
    Path path;
	
	@MockBean
	CriteriaBuilder cb;
	
	@InjectMocks
	private ConsecutivoDTO consecutivo;
	
	@MockBean
	CriteriaQuery<Consecutivo>  cq;
	
	@MockBean
	Root<Consecutivo>  rootAccountEntity;


	@Test
	public void testGetRepo() {
		assertNotNull(consecutivoRepo.getRepo());
	}
	
	
 
	@Test
	public void testFiltrarPorCampo() throws SpddExceptions {
		ConsecutivoDTO peticion = new ConsecutivoDTO();
		Consecutivo consecutivo = new Consecutivo();
		
		peticion.setDescripcion("like100Steps");
		peticion.setNombre("NOMBRE");
		peticion.setSecuencia(1L);
		peticion.setVigencia("dos");
		Predicate predicate;
		FiltroDTO res = null;
		Long inicio = 1L;
		int limite = 10;
		when(entityManagerFactory.createEntityManager()).thenReturn(entityManager); 
		when(entityManager.getCriteriaBuilder()).thenReturn(cb);
		when(cb.createQuery(Consecutivo.class)).thenReturn(cq);
		when(cq.from(Consecutivo.class)).thenReturn(rootAccountEntity);
	//	when(rootAccountEntity.get(peticion.getDescripcion())).thenReturn((javax.persistence.criteria.Path<Object>) path);
		//res = consecutivoRepo.filtrarPorCampo(peticion,inicio, limite);        
		
		Predicate like100Steps = cb.like(rootAccountEntity.get("descripcion"), "%100 Steps");
		System.out.println(like100Steps);
		cq.where(like100Steps);
		TypedQuery<Consecutivo> query = entityManager.createQuery(cq.select(rootAccountEntity));	
        System.out.println(query);
//		System.out.println(res);
//		assertNotNull(res);
		
		
	}

}
