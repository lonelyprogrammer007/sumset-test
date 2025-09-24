package co.gov.sdp.spdd.data.dao.impl.bp;

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

import co.gov.sdp.spdd.data.irepositorio.bp.IBpAporteCiudadanoRepo;
import co.gov.sdp.spdd.data.model.bp.BpAporteCiudadano;

@RunWith(SpringRunner.class)
public class BpAporteCiudadanoRepoTest {
	
	@TestConfiguration
	static class BpAporteCiudadanoRepoTestContextConfiguration {
		@Bean
		public BpAporteCiudadanoRepo bpAporteCiudadanoRepo() {
			return new BpAporteCiudadanoRepo();
		}
	}
	
	@Autowired
	BpAporteCiudadanoRepo bpAporteCiudadanoRepo;
	
	@MockBean
	IBpAporteCiudadanoRepo bpAporteCiudadanoRepository;
	
	@MockBean
	EntityManager entityManager;

	@MockBean
	EntityManagerFactory entityManagerFactory;

	@Test
	public void testGetRepo() {
		assertNotNull(bpAporteCiudadanoRepo.getRepo());
	}

	@Test
	public void testObtenerTodosSinRelacionConProyectoInversion() {
		Long idProyecto = 1L;
		List<BpAporteCiudadano> lista = new ArrayList<>();
		when(bpAporteCiudadanoRepository.obtenerTodosSinRelacionConProyectoInversion(idProyecto)).thenReturn(lista);
		List<BpAporteCiudadano> res = bpAporteCiudadanoRepo.obtenerTodosSinRelacionConProyectoInversion(idProyecto);
		assertNotNull(res);
	}

}
