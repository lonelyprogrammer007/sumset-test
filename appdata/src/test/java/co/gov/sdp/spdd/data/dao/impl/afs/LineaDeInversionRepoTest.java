package co.gov.sdp.spdd.data.dao.impl.afs;

import static org.junit.Assert.assertNotNull;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.query.criteria.internal.CriteriaBuilderImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.spdd.data.irepositorio.afs.ILineaDeInversionRepo;
import co.gov.sdp.spdd.data.model.afs.LineaDeInversion;

/**
 * 
 * @author Sumset
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { LineaDeInversionRepo.class })
public class LineaDeInversionRepoTest {

	@Autowired
	private LineaDeInversionRepo lineaDeInversionRepo;

	@MockBean
	private ILineaDeInversionRepo lineaDeInversionRepository;

	@MockBean
	EntityManagerFactory em;
	
	@MockBean
	EntityManager entityManager;

	@MockBean
	CriteriaBuilderImpl cb;

	@MockBean
	CriteriaQuery<LineaDeInversion> cq;

	@MockBean
	Root<LineaDeInversion> lineaDeInversion;

	@Test
	public void testGetRepo() {
		assertNotNull(lineaDeInversionRepo.getRepo());
	}

	@Test
	public void testFiltrarPorCampo() throws SpddExceptions {
//		FiltroDTO res;
//		LineaDeInversion entidad = new LineaDeInversion();
//		entidad.setIdLineaInversion(1L);
//		LineaDeInversionDTO lineaDeInversionDTO = new LineaDeInversionDTO();
//		
//	
//		CriteriaBuilder cb = em.getCriteriaBuilder();
//		
//		
//		System.out.println(em);
//		Root<LineaDeInversion> lineas = Mockito.mock(Root.class);
//		CriteriaQuery<LineaDeInversion> cq = cb.createQuery(LineaDeInversion.class);
//		
//		
//		Long inicio = 1L;
//		Integer limite = 10;
//		lineaDeInversionDTO.setIdLineaInversion(1L);
//		lineaDeInversionDTO.setConcepto("concepto1");
//		lineaDeInversionDTO.setEstablecido("establecido1");
//		lineaDeInversionDTO.setDescripcion("descripcion1");
//		lineaDeInversionDTO.setEstado(1);
//		lineaDeInversionDTO.setFecha("13-04-2020");
//		lineaDeInversionDTO.setNombreSector("sector1");
//		lineaDeInversionDTO.setTipoOrden("asc");
//		Predicate predicate = Mockito.mock(Predicate.class);
//		CriteriaBuilder criteriaBuilder = Mockito.mock(CriteriaBuilder.class);
//		
////		when(criteriaBuilder.like(lineas.get("idLineaInversion"), "%" + lineaDeInversionDTO.getIdLineaInversion())).thenReturn(predicate);
//		res = lineaDeInversionRepo.filtrarPorCampo(lineaDeInversionDTO, inicio, limite);
//		assertNotNull(res);
	}

	@Test
	public void testBuscarConceptoYSector() {
	}

}
