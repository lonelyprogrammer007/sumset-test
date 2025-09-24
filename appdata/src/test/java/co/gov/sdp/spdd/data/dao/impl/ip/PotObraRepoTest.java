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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import co.gov.sdp.spdd.data.irepositorio.ip.IPotObraRepo;
import co.gov.sdp.spdd.data.model.ip.PotObra;

/**
 * 
 * @author Sumset
 *
 */
@RunWith(SpringRunner.class)
public class PotObraRepoTest {

	@TestConfiguration
	static class PotObraRepoTestContextConfiguration {
		@Bean
		public PotObraRepo PotObraRepo() {
			return new PotObraRepo();
		}
	}
	
	@Autowired
	private PotObraRepo potObraRepo;

	@MockBean
	private IPotObraRepo potObraRepository;

	@MockBean
	private EntityManager entityManager;

	@MockBean
	private EntityManagerFactory entityManagerFactory;
	
	@Test
	public void testGetRepo() {
		assertNotNull(potObraRepo.getRepo());
	}

	@Test
	public void testObtenerTodosPaginadoPorIdNivelPot() throws Exception {
		Long idNivelPot = 1L;
		Integer pagina = 0;
		Integer tamanioPagina = Integer.MAX_VALUE;
		Pageable paginador = PageRequest.of(pagina, tamanioPagina, Sort.by("codigo").ascending());
	
		PotObra potObra = new PotObra();
		List<PotObra> listaPotObra = new ArrayList<PotObra>();
		listaPotObra.add(potObra);
		Page<PotObra> pagePotObra = new PageImpl<PotObra>(listaPotObra, paginador, listaPotObra.size());

		
		when(potObraRepository.obtenerTodosPorIdNivelPot(idNivelPot, paginador)).thenReturn(pagePotObra);
		Page<PotObra> res = potObraRepo.obtenerTodosPaginadoPorIdNivelPot(idNivelPot, paginador);
		assertNotNull(res);
	}

	@Test
	public void testObtenerPorCodigoYIdNivelPot() throws Exception {
		Long codigo = 1L;
		Long idNivelPot = 1L;
		when(potObraRepository.obtenerPorCodigoYIdNivelPot(codigo, idNivelPot)).thenReturn(new PotObra());
		PotObra res= potObraRepo.obtenerPorCodigoYIdNivelPot(codigo, idNivelPot);
		assertNotNull(res);
	}

}
