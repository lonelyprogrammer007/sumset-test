package co.gov.sdp.spdd.data.dao.impl.ip;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import co.gov.sdp.spdd.data.irepositorio.ip.IPdlNivelAtributoRepo;
import co.gov.sdp.spdd.data.model.ip.PdlNivelAtributo;

/**
 * 
 * @author SEBASTIAN
 *
 */
@RunWith(SpringRunner.class)
public class PdlNivelAtributoRepoTest {

	@TestConfiguration
	static class PdlNivelAtributoRepoTestContextConfiguration {
		@Bean
		public PdlNivelAtributoRepo pdlNivelAtributoRepo() {
			return new PdlNivelAtributoRepo();
		}
	}
	
	@Autowired
	private PdlNivelAtributoRepo pdlNivelAtributoRepo;

	@MockBean
	private IPdlNivelAtributoRepo pdlNivelAtributoRepository;
	
	@Test
	public void testGetRepo() {
		assertNotNull(pdlNivelAtributoRepo.getRepo());
	}

	@Test
	public void testObtenerTodosPorIdPdlNivelAtributoPaginado() {
		
	}

	@Test
	public void testObtenerTodosPorIdAtributoPadrePaginado() {
		
	}

	@Test
	public void testObtenerPorDenominacionYIdPdlNivel() {
		String denominacion = "denominacion";
		Long idPdlNivel = 1L;
		when(pdlNivelAtributoRepository.obtenerPorDenominacionYIdPdlNivel(denominacion, idPdlNivel)).thenReturn(new PdlNivelAtributo());
		PdlNivelAtributo res = pdlNivelAtributoRepo.obtenerPorDenominacionYIdPdlNivel(denominacion, idPdlNivel);
		assertNotNull(res);
	}

}
