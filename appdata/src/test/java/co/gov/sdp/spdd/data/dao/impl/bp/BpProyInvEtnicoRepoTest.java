package co.gov.sdp.spdd.data.dao.impl.bp;

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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvEtnicoDTO;
import co.gov.sdp.spdd.data.irepositorio.bp.IBpProyInvEtnicoRepo;
import co.gov.sdp.spdd.data.model.bp.BpProyInvEtnico;

@RunWith(SpringRunner.class)
public class BpProyInvEtnicoRepoTest {
	
	@TestConfiguration
	static class BpProyInvEtnicoRepoTestContextConfiguration{
		@Bean
		public BpProyInvEtnicoRepo bpProyInvEtnicoRepo() {
			return new BpProyInvEtnicoRepo();
		}
	}
	
	@Autowired 
	BpProyInvEtnicoRepo bpProyInvEtnicoRepo;
	
	@MockBean
	IBpProyInvEtnicoRepo bpProyInvEtnicoRepository;
	
	@Test
	public void testGetRepo() {
		assertNotNull(bpProyInvEtnicoRepo.getRepo());
	}

	@Test
	public void testObtenerTodosProyInvEtnicoAsociadosAProyectoInversion() throws Exception {
//		BpProyInvEtnicoDTO peticion = new BpProyInvEtnicoDTO();
//		peticion.setTamanioPagina(5);
//		peticion.setPagina(0);
//		
//		Pageable pageRequest = PageRequest.of(peticion.getPagina(), peticion.getTamanioPagina());
//		
//		List<BpProyInvEtnico> listaInicializacionPageList = new ArrayList<>();
//		
//		Page<BpProyInvEtnico> pagina = new PageImpl<BpProyInvEtnico>(listaInicializacionPageList,pageRequest,listaInicializacionPageList.size());
//		
//		when(bpProyInvEtnicoRepository.obtenerTodosPorIdProyectoInversion(peticion.getIdProyInversion(),pageRequest)).thenReturn(pagina);
//		
//		Page<BpProyInvEtnico> res = bpProyInvEtnicoRepo.obtenerTodosProyInvEtnicoAsociadosAProyectoInversion(peticion.getIdProyInversion(), pageRequest);
//		assertNotNull(res);
	}

}
