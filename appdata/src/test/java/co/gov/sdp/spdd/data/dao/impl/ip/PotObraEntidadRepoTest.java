package co.gov.sdp.spdd.data.dao.impl.ip;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.doAnswer;
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

import co.gov.sdp.spdd.data.irepositorio.ip.IPotObraEntidadRepo;
import co.gov.sdp.spdd.data.model.ip.PotObraEntidad;

@RunWith(SpringRunner.class)
public class PotObraEntidadRepoTest {
	
	@TestConfiguration
	static class PotObraEntidadRepoTestContextConfiguration {
		@Bean
		public PotObraEntidadRepo potObraEntidadRepo() {
			return new PotObraEntidadRepo();
		}
	}
	
	@Autowired
	private PotObraEntidadRepo potObraEntidadRepo;
	
	@MockBean
	private IPotObraEntidadRepo potObraEntidadRepository;
	
	

	@Test
	public void testGetRepo() throws Exception {
		assertNotNull(potObraEntidadRepo.getRepo());
	}

	@Test
	public void testObtenerTodosPorIdPotObra() throws Exception {
		Long idPotObra = 1L;
		
		List<PotObraEntidad> listaPotObraEntidad = new ArrayList<PotObraEntidad>();
		when(potObraEntidadRepository.obtenerTodosPorIdPotObra(idPotObra)).thenReturn(listaPotObraEntidad);
		List<PotObraEntidad> res = potObraEntidadRepo.obtenerTodosPorIdPotObra(idPotObra);
		assertNotNull(res);
	}

	@Test
	public void testEliminarTodos() throws Exception {
		PotObraEntidad potObraEntidad = new PotObraEntidad();
		List<PotObraEntidad> listaPotObraEntidad = new ArrayList<PotObraEntidad>();
		listaPotObraEntidad.add(potObraEntidad);
		
		doAnswer((argumentos) -> {
	        listaPotObraEntidad.clear();
	        return null;
	    }).when(potObraEntidadRepository).deleteAll(listaPotObraEntidad);
		potObraEntidadRepo.eliminarTodos(listaPotObraEntidad);
		assertThat(listaPotObraEntidad.isEmpty()).isTrue();
	}

	@Test
	public void testObtenerPotCodigoEntidadYIdPotObra() throws Exception {
		String codigoEntidad = "123";
		Long idPotObra = 1L;
		when(potObraEntidadRepository.obtenerPotCodigoEntidadYIdPotObra(codigoEntidad, idPotObra)).thenReturn(new PotObraEntidad());
		PotObraEntidad res= potObraEntidadRepo.obtenerPotCodigoEntidadYIdPotObra(codigoEntidad, idPotObra);
		assertNotNull(res);
	}

}
