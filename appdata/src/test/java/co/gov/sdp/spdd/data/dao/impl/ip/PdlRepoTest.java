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
import org.springframework.test.context.junit4.SpringRunner;

import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.spdd.data.irepositorio.ip.IPdlRepo;
import co.gov.sdp.spdd.data.model.ip.Pdl;

/**
 * 
 * @author SEBASTIAN
 *
 */
@RunWith(SpringRunner.class)
public class PdlRepoTest {
	
	@TestConfiguration
	static class PdlRepoTestContextConfiguration {
		@Bean
		public PdlRepo pdlRepo() {
			return new PdlRepo();
		}
	}
	
	@Autowired
	private PdlRepo pdlRepo;
	
	@MockBean
	private EntityManager entityManager;

	@MockBean
	private EntityManagerFactory entityManagerFactory;
	
	@MockBean
	private IPdlRepo pdlRepository;
	
	@Test
	public void testGetRepo() {
		assertNotNull(pdlRepo.getRepo());
		
	}

	@Test
	public void testFiltrarPorCampo() {
		
	}

	@Test
	public void testObtenerPdlsPorEstado() throws SpddExceptions {
		Long idEstado = 1L;
		when(pdlRepository.obtenerPorEstado(idEstado)).thenReturn(new ArrayList<Pdl>());
		List<Pdl> res = pdlRepo.obtenerPdlsPorEstado(idEstado);
		assertNotNull(res);
	}

	@Test
		public void testObtenerPorLsEstadoPlanYCodigoEntidad() {
			String codigoEntidad = "0141";
			when(pdlRepository.obtenerPorLsEstadoPlanYCodigoEntidad(NHSPDDConstantes.PDD_ESTADO_VIGENTE, codigoEntidad)).thenReturn(new ArrayList<Pdl>());
			List<Pdl> res = pdlRepository.obtenerPorLsEstadoPlanYCodigoEntidad(NHSPDDConstantes.PDD_ESTADO_VIGENTE, codigoEntidad);
			assertNotNull(res);
		}

}
