package co.gov.sdp.spdd.core.service.presupuesto;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.databind.ObjectMapper;

import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.dto.InformacionPresupuestalDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.spdd.core.iservice.presupuesto.IInformacionPresupuestalPresupuestoConsultar;
import co.gov.sdp.spdd.data.dao.interfaces.afs.IInformacionPresupuestalServiceRepo;
import co.gov.sdp.spdd.data.mapper.InformacionPresupuestalMapper;
import co.gov.sdp.spdd.data.model.afs.InformacionPresupuestal;
import co.gov.sdp.spdd.data.sesionfacade.impl.SessionFacadeAFS;
import co.gov.sdp.spdd.data.sesionfacade.interfaces.ISessionFacadeAFS;
import co.gov.sdp.spdd.data.sesionfacade.interfaces.ISessionFacadeIP;
import co.gov.sdp.spdd.util.AuditoriaUtil;
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { SessionFacadeAFS.class, InformacionPresupuestalPresupuestoConsultar.class, IInformacionPresupuestalPresupuestoConsultar.class})
class InformacionPresupuestalPresupuestoConsultarTest {

	@Autowired
	IInformacionPresupuestalPresupuestoConsultar consultar;
	
	@MockBean
	IInformacionPresupuestalServiceRepo informacionPresupuestalServiceRepo;

	// Mapper de informacion presupuestal
	@MockBean
	InformacionPresupuestalMapper informacionPresupuestalMapper;
	
	@MockBean
	ObjectMapper objectMapper;
	
	@MockBean
	ISessionFacadeIP sessionFacadeIP;

	@MockBean
	ISessionFacadeAFS sessionFacadeAFS;
	
	@MockBean
	AuditoriaUtil auditoria;

	
	@Test
	void testObtenerInformacionPresupuestalTodos() throws SpddExceptions {
		List<InformacionPresupuestal> lista = new ArrayList<InformacionPresupuestal>();
		when(informacionPresupuestalServiceRepo.obtenerTodos()).thenReturn(lista);
		when(informacionPresupuestalMapper.informacionPresupuestalEntitiesToDTO(lista)).thenReturn(new ArrayList<InformacionPresupuestalDTO>());
		GenericoDTO res = consultar.obtenerInformacionPresupuestalTodos();
		assertNotNull(res);
	}

	@Test
	void testObtenerPaginado() throws SpddExceptions {
		/* Por el momento no se puede hacer por el TypeReference
		InformacionPresupuestalDTO peticion = new InformacionPresupuestalDTO();
		peticion.setIdInfoPresupuestal(1L);
		when(sessionFacadeAFS.consultarInformacionPresupuestalPorFiltro(peticion)).thenReturn(new GenericoDTO());
		GenericoDTO res = consultar.obtenerPaginado(peticion);
		assertNotNull(res);
		*/
	}

	@Test
	void testObtenerInformacionPresupuestalPorPlanDesarrollo() throws SpddExceptions {
		Long id = 1L;
		InformacionPresupuestal info = new InformacionPresupuestal();
		info.setIdInfoPresupuestal(1L);
		List<InformacionPresupuestal> lista = new ArrayList<InformacionPresupuestal>();
		when(informacionPresupuestalServiceRepo
				.obtenerInformacionPresupuestalPorPlanDesarrollo(id)).thenReturn(lista);
		when(informacionPresupuestalMapper
				.informacionPresupuestalEntitiesToDTO(lista)).thenReturn(new ArrayList<InformacionPresupuestalDTO>());
		GenericoDTO res = consultar.obtenerInformacionPresupuestalPorPlanDesarrollo(id);
		assertNotNull(res);
		
	}

	@Test
	void testObtenerInformacionPresupuestalPorEntidad() throws SpddExceptions {
		Long id = 1L;
		InformacionPresupuestal info = new InformacionPresupuestal();
		info.setIdInfoPresupuestal(1L);
		List<InformacionPresupuestal> lista = new ArrayList<InformacionPresupuestal>();
		when(informacionPresupuestalServiceRepo
				.obtenerInformacionPresupuestalPorPlanDesarrollo(id)).thenReturn(lista);
		when(informacionPresupuestalMapper
				.informacionPresupuestalEntitiesToDTO(lista)).thenReturn(new ArrayList<InformacionPresupuestalDTO>());
		GenericoDTO res = consultar.obtenerInformacionPresupuestalPorEntidad(id);
		assertNotNull(res);
	}

	@Test
	void testObtenerPorArchivoInfo() {
		Long id = 1L;
		InformacionPresupuestal info = new InformacionPresupuestal();
		info.setIdInfoPresupuestal(1L);
		
		when(sessionFacadeAFS.obtenerArchivoInfoPresupuestal(id)).thenReturn(new GenericoDTO());
		GenericoDTO res = consultar.obtenerPorArchivoInfo(id);
		assertNotNull(res);
	}

}
