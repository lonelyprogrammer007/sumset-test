package co.gov.sdp.spdd.data.dao.impl.afs;

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

import co.gov.sdp.spdd.data.irepositorio.afs.IInformacionPresupuestalRepo;
import co.gov.sdp.spdd.data.model.afs.InformacionPresupuestal;

/**
 * 
 * @author Sumset
 *
 */
@RunWith(SpringRunner.class)
public class InformacionPresupuestalRepoTest {

	@TestConfiguration
	static class InformacionPresupuestalRepoTestContextConfiguration {
		@Bean
		public InformacionPresupuestalRepo InformacionPresupuestalRepo() {
			return new InformacionPresupuestalRepo();
		}
	}
	
	@Autowired
	private InformacionPresupuestalRepo informacionPresupuestalRepo;

	@MockBean
	private IInformacionPresupuestalRepo informacionPresupuestalRepository;

	@MockBean
	private EntityManager entityManager;

	@MockBean
	private EntityManagerFactory entityManagerFactory;
	
	@Test
	public void testGetRepo() {
		assertNotNull(informacionPresupuestalRepo.getRepo());
	}

	@Test
	public void testFiltrarPorCampo() {
	}

	@Test
	public void testObtenerInformacionPresupuestalPorPlanDesarrollo() {
	}

	@Test
	public void testObtenerInformacionPresupuestalPorEntidad() {
	}

	@Test
	public void testObtenerPorArchivo() {
	}

	@Test
	public void testObtenerPorCodigoEntidadYMesYYear() throws Exception {
		when(informacionPresupuestalRepository.obtenerPorCodigoEntidadYMesYYear("codigo", 1L, 2020L)).thenReturn(new InformacionPresupuestal());
		InformacionPresupuestal res = informacionPresupuestalRepo.obtenerPorCodigoEntidadYMesYYear("codigo", 1L, 2020L);
		assertNotNull(res);
	}

}
