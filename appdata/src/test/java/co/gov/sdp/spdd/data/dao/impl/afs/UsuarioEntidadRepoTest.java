package co.gov.sdp.spdd.data.dao.impl.afs;

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

import co.gov.sdp.spdd.data.irepositorio.afs.IUsuarioEntidadRepo;
import co.gov.sdp.spdd.data.model.afs.UsuarioEntidad;

/**
 * 
 * @author Sumset
 *
 */
@RunWith(SpringRunner.class)
public class UsuarioEntidadRepoTest {
	
	@TestConfiguration
	static class UsuarioEntidadRepoTestContextConfiguration {
		@Bean
		public UsuarioEntidadRepo UsuarioEntidadRepo() {
			return new UsuarioEntidadRepo();
		}
	}
	
	@Autowired
	private UsuarioEntidadRepo usuarioEntidadRepo;

	@MockBean
	private IUsuarioEntidadRepo usuarioEntidadRepository;

	@MockBean
	private EntityManager entityManager;

	@MockBean
	private EntityManagerFactory entityManagerFactory;

	@Test
	public void testGetRepo() {
		assertNotNull(usuarioEntidadRepo.getRepo());
	}

//	@Test
//	public void testFiltrarPorCampo() throws SpddExceptions {
//		UsuarioEntidadDTO usuarioEntidadDTO = new UsuarioEntidadDTO();
//		usuarioEntidadDTO.setIdUsuarioEntidad(157L);
//		usuarioEntidadDTO.setCodigoUsuario("sumset");
//		usuarioEntidadDTO.setCodigoEntidad("0144");
//		Long inicio = 5L;
//		Integer limite = 10;
//		
//		CriteriaQuery<UsuarioEntidad> cq = mock(CriteriaQuery.class);
//		
//		when(entityManager.createQuery(cq).setFirstResult(inicio.intValue()).setMaxResults(limite.intValue()).getResultList()).thenReturn(new ArrayList<UsuarioEntidad>());
//		
//		assertNotNull(usuarioEntidadRepo.filtrarPorCampo(usuarioEntidadDTO, inicio, limite));
//	}

	@Test
	public void testObtenerPorUsuario() {
		when(usuarioEntidadRepository.obtenerPorUsuario("prueba@sumset.com")).thenReturn(new ArrayList<>());
		List<UsuarioEntidad> list = usuarioEntidadRepo.obtenerPorUsuario("prueba@sumset.com");
		assertNotNull(list);
	}

}
