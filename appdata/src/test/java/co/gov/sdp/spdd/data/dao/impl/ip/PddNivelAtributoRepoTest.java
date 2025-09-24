package co.gov.sdp.spdd.data.dao.impl.ip;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import co.gov.sdp.spdd.data.irepositorio.ip.IPddNivelAtributoRepo;
import co.gov.sdp.spdd.data.model.ip.PddNivelAtributo;

/**
 * 
 * @author DANIEL
 *
 */
@RunWith(SpringRunner.class)
public class PddNivelAtributoRepoTest {
	
	@TestConfiguration
	static class PddNivelAtributoRepoTestContextConfiguration {
		@Bean
		public PddNivelAtributoRepo pddNivelAtributoRepo() {
			return new PddNivelAtributoRepo();
		}
	}
	
	@Autowired
	private PddNivelAtributoRepo pddNivelAtributoRepo;

	@MockBean
	private IPddNivelAtributoRepo pddNivelAtributoRepository;

	@Test
	public void testGetRepo() {
		assertNotNull(pddNivelAtributoRepo.getRepo());
	}
	
	@Test
	public void testPddNivelAtributoObtenerLibres() {
		when(pddNivelAtributoRepository.pddNivelAtributoObtenerLibres()).thenReturn(new ArrayList<PddNivelAtributo>());
		List<PddNivelAtributo> res = pddNivelAtributoRepo.pddNivelAtributoObtenerLibres();
		assertNotNull(res);		
	}

	@Test
	public void testObtenerPorNumeroYIdPddNivel() {
		Long numero = 1L;
		Long idPddNivel = 1L;
		Long idAtributoPadre=2L;
		when(pddNivelAtributoRepository.obtenerPorNumeroYIdPddNivel(numero, idPddNivel,idAtributoPadre)).thenReturn(new PddNivelAtributo());
		PddNivelAtributo res = pddNivelAtributoRepo.obtenerPorNumeroYIdPddNivel(numero, idPddNivel,idAtributoPadre);
		assertNotNull(res);	
	}

	@Test
	public void testObtenerTodosPorIdPddNivel() {
		/*
		Long idPddNivel = 1L;
		when(pddNivelAtributoRepository.obtenerTodosPorIdPddNivel(idPddNivel)).thenReturn(new ArrayList<PddNivelAtributo>());
		List<PddNivelAtributo> res = pddNivelAtributoRepo.obtenerTodosPorIdPddNivel(idPddNivel);
		assertNotNull(res);
		*/
	}

	@Test
	public void testObtenerPorDenominacionYIdPddNivel() {
		String denominacion = "denominacion";
		Long idPddNivel = 1L;
		when(pddNivelAtributoRepository.obtenerPorDenominacionYIdPddNivel(denominacion, idPddNivel)).thenReturn(new PddNivelAtributo());
		PddNivelAtributo res = pddNivelAtributoRepo.obtenerPorDenominacionYIdPddNivel(denominacion, idPddNivel);
		assertNotNull(res);
	}

}
