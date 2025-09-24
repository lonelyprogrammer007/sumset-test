package co.gov.sdp.spdd.data.dao.impl.afs;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

import co.gov.sdp.spdd.data.irepositorio.afs.IArchivoProcesadoRepo;
import co.gov.sdp.spdd.data.model.afs.ArchivoProcesado;

/**
 * 
 * @author Sumset
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { ArchivoProcesadoRepo.class})
public class ArchivoProcesadoRepoTest {

	@Autowired
	ArchivoProcesadoRepo archivoProcesadoRepo;

	@MockBean
	EntityManagerFactory em;

	@MockBean
	private IArchivoProcesadoRepo archivoProcesadoRepository;

	private EntityManager entityManager;

	@MockBean
	Root<ArchivoProcesado> archivoProcesado;

	@MockBean
	CriteriaBuilder cb;

	@MockBean
	CriteriaQuery<ArchivoProcesado> cq;

	@MockBean
	Path<String> path;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		entityManager = mock(EntityManager.class);
		ReflectionTestUtils.setField(archivoProcesadoRepo, "em", entityManager);

	}

	@Test
	public void testGetRepo() {
		assertNotNull(archivoProcesadoRepo.getRepo());
	}

//	@Test
//	public void testFiltrarPorCampo() throws SpddExceptions {
//
//		ArchivoProcesadoDTO archivoProcesadoDTO = new ArchivoProcesadoDTO();
////		when(em.createEntityManager()).thenReturn(entityManager);
////		when(em.getCriteriaBuilder()).thenReturn(cb);
////
//		Long inicio = 1L;
//		Integer limite = 10;
//
////		when(cb.createQuery(ArchivoProcesado.class)).thenReturn(cq);
//		archivoProcesadoDTO.setIdArchivo(1L);
//		archivoProcesadoDTO.setColumnaOrdenar("idArchivo");
//		archivoProcesadoDTO.setTipoOrden("asc");
////		when(cq.from(ArchivoProcesado.class)).thenReturn(archivoProcesado);
//
////		when(cq.orderBy(cb.asc(archivoProcesado.get(archivoProcesadoDTO.getColumnaOrdenar())))).thenReturn(cq);
//        when(em.createEntityManager()).thenReturn(entityManager);
//		when(entityManager.createQuery(cq).setFirstResult(inicio.intValue()).setMaxResults(limite.intValue())
//				.getResultList()).thenReturn(new ArrayList<>());
//		FiltroDTO lista = archivoProcesadoRepo.filtrarPorCampo(archivoProcesadoDTO, inicio, limite);
//		assertNotNull(lista);
//	}

}
