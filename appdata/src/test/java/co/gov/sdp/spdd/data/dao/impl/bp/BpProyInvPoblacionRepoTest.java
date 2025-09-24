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

import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvPoblacionDTO;
import co.gov.sdp.spdd.data.irepositorio.bp.IBpProyInvPoblacionRepo;
import co.gov.sdp.spdd.data.model.bp.BpProyInvPoblacion;

@RunWith(SpringRunner.class)
public class BpProyInvPoblacionRepoTest {
	
	@TestConfiguration
	static class BpProyInvPoblacionRepoTestContextConfiguration{
		@Bean
		public BpProyInvPoblacionRepo bpProyInvPoblacionRepo() {
			return new BpProyInvPoblacionRepo();
		}
	}
	
	@Autowired 
	BpProyInvPoblacionRepo bpProyInvPoblacionRepo;
	
	@MockBean
	IBpProyInvPoblacionRepo bpProyInvPoblacionRepository;
	
	@Test
	public void testGetRepo() {
		assertNotNull(bpProyInvPoblacionRepo.getRepo());
	}

	@Test
	public void testObtenerTodosProyInvPoblacionAsociadosAProyectoInversion() throws Exception {

		BpProyInvPoblacionDTO peticion = new BpProyInvPoblacionDTO();
		peticion.setTamanioPagina(5);
		peticion.setPagina(0);
		
		Pageable pageRequest = PageRequest.of(peticion.getPagina(), peticion.getTamanioPagina());
		
		List<BpProyInvPoblacion> listaInicializacionPageList = new ArrayList<>();
		
		Page<BpProyInvPoblacion> pagina = new PageImpl<BpProyInvPoblacion>(listaInicializacionPageList,pageRequest,listaInicializacionPageList.size());
		
		when(bpProyInvPoblacionRepository.obtenerTodosPorIdProyectoInversion(peticion.getIdProyInversion(),pageRequest)).thenReturn(pagina);
		
		Page<BpProyInvPoblacion> res = bpProyInvPoblacionRepo.obtenerTodosGruposEtarios(peticion.getIdProyInversion(), pageRequest);
		assertNotNull(res);
	}

}
