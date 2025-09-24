package co.gov.sdp.spdd.core.ip.service.ipplandistrital;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.databind.ObjectMapper;

import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.dto.PddDTO;
import co.gov.sdp.nhspdd.common.dto.PddNivelAtributoDTO;
import co.gov.sdp.nhspdd.common.dto.RespuestaApiDTO;
import co.gov.sdp.nhspdd.common.dto.ip.ArbolCompromisoDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddMRIndicadorDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddMetaProductoDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddMetaResultadoDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PdlDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PdlNivelAtributoDTO;
import co.gov.sdp.nhspdd.common.dto.seguridad.EntidadSeguridadDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.spdd.core.ip.iservice.ipplandistrital.IIPPlanDistritalConsultarService;
import co.gov.sdp.spdd.data.sesionfacade.impl.SessionFacadeIP;
import co.gov.sdp.spdd.util.MetodosRest;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { SessionFacadeIP.class, IPPlanDistritalConsultarService.class,
		IIPPlanDistritalConsultarService.class })
public class IPPlanDistritalConsultarServiceTest {

	@Autowired
	IPPlanDistritalConsultarService iPPlanDistritalConsultar;

	@MockBean
	SessionFacadeIP sessionFacadeIP;

	@MockBean
	SPDDLogger spddLogger;
	
	@MockBean
	ObjectMapper mapper;
	
//	@MockBean 
//	HttpServletRequest request;
	
	@MockBean
	MetodosRest<RespuestaApiDTO<EntidadSeguridadDTO>> entidad;
	 

	@Test
	public void testPddObtenerPaginado() throws SpddExceptions {
		PddDTO peticion= new PddDTO();
		when(sessionFacadeIP.consultarPddPorFiltro(peticion)).thenReturn(new GenericoDTO());
		GenericoDTO res = iPPlanDistritalConsultar.pddObtenerPaginado(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(200);
	}

	@Test
	public void testConsultarPddPorId() throws SpddExceptions {
		Long id = 1L;
		when(sessionFacadeIP.consultarPddPorID(id)).thenReturn(new PddDTO());
		PddDTO res = iPPlanDistritalConsultar.consultarPddPorId(id);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(200);
	}

	@Test
	public void testConsultarPddNivelPorIdPlanDesarrolloDistrital() throws SpddExceptions {
		Long idPlan = 1L;
		when(sessionFacadeIP.consultarPddNivelPorIdPlanDesarrollo(idPlan)).thenReturn(new GenericoDTO());
		GenericoDTO res = iPPlanDistritalConsultar.consultarTodosPddNivelPorIdPlanDesarrolloDistrital(idPlan);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(200);
	}

	@Test
	void testPdlObtenerPaginado() throws SpddExceptions {
		PdlDTO peticion = new PdlDTO();
		when(sessionFacadeIP.consultarPdlPorFiltro(peticion)).thenReturn(new GenericoDTO());
		assertThat(iPPlanDistritalConsultar.pdlObtenerPaginado(peticion)).isNotNull();
	}

	@Test
	void testConsultarTodosPlanDesarrolloLocal() throws SpddExceptions {
		when(sessionFacadeIP.consultarTodosPlanDesarrolloLocal()).thenReturn(new GenericoDTO());
		assertThat(iPPlanDistritalConsultar.consultarTodosPlanDesarrolloLocal()).isNotNull();
	}

	@Test
	void testConsultarPlanDesarrolloLocalPorId() throws SpddExceptions {
		when(sessionFacadeIP.consultarPlanDesarrolloLocalPorId(1L)).thenReturn(new PdlDTO());
		assertThat(iPPlanDistritalConsultar.consultarPlanDesarrolloLocalPorId(1L)).isNotNull();
	}

	@Test
	public void testConsultarPddMetaResultadoProyectoEstrategico() throws SpddExceptions {
		PddMetaResultadoDTO peticion = new PddMetaResultadoDTO();
		when(sessionFacadeIP.consultarPddMetaResultado(peticion)).thenReturn(new GenericoDTO());
		assertThat(iPPlanDistritalConsultar.consultarPddMetaResultadoProyectoEstrategico(peticion)).isNotNull();
	}

	@Test
	public void testConsultarTodosRangoPonderacion() throws SpddExceptions {
		when(sessionFacadeIP.obtenerTodosRangoPonderacion()).thenReturn(new GenericoDTO());
		assertThat(iPPlanDistritalConsultar.consultarTodosRangoPonderacion()).isNotNull();
	}

	@Test
	public void testConsultarPdlNivelPorIdPlanLocal() throws SpddExceptions {
		Long idPlanLocal = 1L;
		when(sessionFacadeIP.consultarPdlNivelPorIdPlanLocal(idPlanLocal)).thenReturn(new GenericoDTO());
		GenericoDTO res = iPPlanDistritalConsultar.consultarPdlNivelPorIdPlanLocal(idPlanLocal);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(200);
	}

	@Test
	public void testConsultarTodosPddNivelAtributoPorIdPddNivelPaginado() throws SpddExceptions {
		PddNivelAtributoDTO peticion = new PddNivelAtributoDTO();
		
		when(sessionFacadeIP.consultarTodosPddNivelAtributoPorIdPddNivelPaginado(peticion)).thenReturn(new GenericoDTO());
		GenericoDTO res = iPPlanDistritalConsultar.consultarTodosPddNivelAtributoPorIdPddNivelPaginado(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(200);
	}

	@Test
	public void testConsultarTodosPddNivelAtributoPorIdAtributoPadrePaginado() throws SpddExceptions {
		PddNivelAtributoDTO peticion =  new PddNivelAtributoDTO();
		when(sessionFacadeIP.consultarTodosPddNivelAtributoPorIdAtributoPadrePaginado(peticion)).thenReturn(new GenericoDTO());
		GenericoDTO res = iPPlanDistritalConsultar.consultarTodosPddNivelAtributoPorIdAtributoPadrePaginado(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(200);
	}

	@Test
	public void testConsultarPddIndicadorMetaResultado() throws SpddExceptions {
		PddMRIndicadorDTO peticion = new PddMRIndicadorDTO();
		when(sessionFacadeIP.consultarIndicadoresMetaResultado(peticion)).thenReturn(new GenericoDTO());
		GenericoDTO res = iPPlanDistritalConsultar.consultarPddIndicadorMetaResultado(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(200);
	}

	@Test
	public void testConsultarMetaProductoPorMR() throws SpddExceptions {
		when(sessionFacadeIP.consultarMetaProductoPorMR(new PddMetaProductoDTO())).thenReturn(new GenericoDTO());
		assertThat(iPPlanDistritalConsultar.consultarMetaProductoPorMR(new PddMetaProductoDTO())).isNotNull();
	}

	@Test
	public void testConsultarTodosPdlNivelAtributoPorIdPdlNivelPaginado() throws SpddExceptions {
		PdlNivelAtributoDTO peticion = new PdlNivelAtributoDTO();
		
		when(sessionFacadeIP.consultarTodosPdlNivelAtributoPorIdPdlNivelPaginado(peticion)).thenReturn(new GenericoDTO());
		GenericoDTO res = iPPlanDistritalConsultar.consultarTodosPdlNivelAtributoPorIdPdlNivelPaginado(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(200);
	}

	@Test
	public void testConsultarTodosPdlNivelAtributoPorIdAtributoPadre() throws SpddExceptions {
		PdlNivelAtributoDTO peticion = new PdlNivelAtributoDTO();
		
		when(sessionFacadeIP.consultarTodosPdlNivelAtributoPorIdAtributoPadre(peticion)).thenReturn(new GenericoDTO());
		GenericoDTO res = iPPlanDistritalConsultar.consultarTodosPdlNivelAtributoPorIdAtributoPadre(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(200);
		
	}

	@Test
	public void testConsultarNivelesComponentesDesbalanceados() throws SpddExceptions {
		Long idPlanDesarrollo = 1L;
		
		when(sessionFacadeIP.consultarNivelesComponentesDesbalanceados(idPlanDesarrollo)).thenReturn(new ArbolCompromisoDTO());
		ArbolCompromisoDTO res = iPPlanDistritalConsultar.consultarNivelesComponentesDesbalanceados(idPlanDesarrollo);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(200);
	}
	
	@Test
	public void testConsultarRangoPonderacionPorIdPdd() throws SpddExceptions {
		when(sessionFacadeIP.obtenerPddRangoPonderacionPorIdPdd(1L)).thenReturn(new GenericoDTO());
		assertThat(iPPlanDistritalConsultar.consultarRangoPonderacionPorIdPdd(1L)).isNotNull();
	}
	
}
