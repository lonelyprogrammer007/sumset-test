package co.gov.sdp.spdd.core.bp.service.bpproyinv;

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

import co.gov.sdp.nhspdd.common.dto.ArgumentoListaSimpleDTO;
import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.dto.PddNivelAtributoDTO;
import co.gov.sdp.nhspdd.common.dto.RespuestaApiDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpAporteCiudadanoDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpIniciativaCiudadanaDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvAporteDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvFinanciaDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvEtnicoDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvPoblacionDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyectoInversionDTO;
import co.gov.sdp.nhspdd.common.dto.seguridad.EntidadSeguridadDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.spdd.core.bp.iservice.bpproyinv.IBPProyInvConsultarService;
import co.gov.sdp.spdd.data.sesionfacade.impl.SessionFacadeBP;
import co.gov.sdp.spdd.data.sesionfacade.interfaces.ISessionFacadeAFS;
import co.gov.sdp.spdd.data.sesionfacade.interfaces.ISessionFacadeBP;
import co.gov.sdp.spdd.data.sesionfacade.interfaces.ISessionFacadeIP;
import co.gov.sdp.spdd.util.MetodosRest;



@RunWith(SpringRunner.class)
@SpringBootTest(classes = { SessionFacadeBP.class,IBPProyInvConsultarService.class, BPProyInvConsultarService.class})
class BPProyInvConsultarServiceTest {
	
	
	@Autowired
	BPProyInvConsultarService bPProyInvConsultarService;
	
	@MockBean
	ISessionFacadeBP sessionFacadeBP;
	
	@MockBean
	ISessionFacadeIP sessionFacadeIP;
	
	@MockBean
	ISessionFacadeAFS sessionFacadeAFS;

	@MockBean
	SPDDLogger spddLogger;
	
	@MockBean
	ObjectMapper objectMapper;
	
	@MockBean
	MetodosRest<RespuestaApiDTO<EntidadSeguridadDTO>> entidad;

	@Test
	void testConsultarProyectoInversionTodos() throws SpddExceptions {
		/*
		HttpServletRequest request = mock(HttpServletRequest.class);
		
		BpProyectoInversionDTO peticion = new BpProyectoInversionDTO();
		
		BpProyectoInversionDTO bpProyectoInversionDTO = new BpProyectoInversionDTO();
		bpProyectoInversionDTO.setCodigoEntidad("0141");
		List<BpProyectoInversionDTO> lista = new ArrayList<BpProyectoInversionDTO>();
		lista.add(bpProyectoInversionDTO);
		GenericoDTO generico = new GenericoDTO();
		generico.setLstObjectsDTO(new ArrayList<Object>(lista));
		
		when(sessionFacadeBP.consultarBpProyectoInversionTodos()).thenReturn(new GenericoDTO());
		when(request.getHeader("Authorization")).thenReturn("daniel");
		doNothing().when(entidad).agregarToken(null);
		GenericoDTO res = bPProyInvConsultarService.consultarProyectoInversionTodos(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(200L);
		*/
	}
	
	@Test
	void testConsultarTodosBpAporteCiudadanoSinRelacionConProyectoInversion() throws SpddExceptions
	{
		Long idProyecto = 1L;
		when(sessionFacadeBP.consultarTodosBpAporteCiudadanoSinRelacionConProyectoInversion(idProyecto)).thenReturn(new GenericoDTO());
		GenericoDTO res = bPProyInvConsultarService.consultarTodosBpAporteCiudadanoSinRelacionConProyectoInversion(idProyecto);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(200L);
	}

	@Test
	public void testConsultarBpProyectoInversionPorId() throws Exception {
		Long idProyecto = 1L;
		
		when(sessionFacadeBP.consultarProyInvPorId(idProyecto)).thenReturn(new BpProyectoInversionDTO());
		BpProyectoInversionDTO res = bPProyInvConsultarService.consultarBpProyectoInversionPorId(idProyecto);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(200L);
	}

	@Test
	public void testConsultarTodosBpProyInvAportePorIdProyInversionPaginado() throws Exception {
		BpProyInvAporteDTO peticion = new BpProyInvAporteDTO();

		when(sessionFacadeBP.consultarTodosBpProyInvAportePorIdProyInversionPaginado(peticion)).thenReturn(new GenericoDTO());
		GenericoDTO res=  bPProyInvConsultarService.consultarTodosBpProyInvAportePorIdProyInversionPaginado(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(200L);
	}

	@Test
	public void testConsultarBpAporteCiudadanoPorId() throws Exception {
		Long idAporte = 1L;
		
		BpAporteCiudadanoDTO respuesta = new BpAporteCiudadanoDTO();
		respuesta.setIdAporte(1L);
		respuesta.setNumeroPddNivel(3L);
		respuesta.setIdNivelAtributoPdd(1L);
		
		PddNivelAtributoDTO pddNivelAtributoAux = new PddNivelAtributoDTO();
		pddNivelAtributoAux.setIdAtributoPadre(1L);
		
		when(sessionFacadeBP.consultarBpAporteCiudadanoPorId(idAporte)).thenReturn(respuesta);
		when(sessionFacadeIP.consultarPddNivelAtributoPorId(pddNivelAtributoAux.getIdAtributoPadre())).thenReturn(pddNivelAtributoAux);
		BpAporteCiudadanoDTO res = bPProyInvConsultarService.consultarBpAporteCiudadanoPorId(idAporte);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(200L);
		
		respuesta.setNumeroPddNivel(2L);
		res = bPProyInvConsultarService.consultarBpAporteCiudadanoPorId(idAporte);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(200L);
		
		when(sessionFacadeBP.consultarBpAporteCiudadanoPorId(idAporte)).thenReturn(null);
		res = bPProyInvConsultarService.consultarBpAporteCiudadanoPorId(idAporte);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
	}

	@Test
	public void testColsultarTodosBpAporteCiudadanoCargadosPorArchivosConRelacionConProyectoInversion() throws Exception {
		Long idProyecto = 1L;
		
		when(sessionFacadeBP.colsultarTodosBpAporteCiudadanoCargadosPorArchivosConRelacionConProyectoInversion(idProyecto)).thenReturn(new GenericoDTO());
		GenericoDTO res = bPProyInvConsultarService.colsultarTodosBpAporteCiudadanoCargadosPorArchivosConRelacionConProyectoInversion(idProyecto);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(200L);
	}

	@Test
	public void testConsultarTodosBpAportesCiudadanosCargadosPorArchivos() throws Exception {

	}

	@Test
	public void testConsultarTodosBpIniciativaCiudadanaViablesFiltradas() throws Exception {
		BpIniciativaCiudadanaDTO peticion = new BpIniciativaCiudadanaDTO();
		
		ArgumentoListaSimpleDTO argumetoEstadoViableIniciativa = new ArgumentoListaSimpleDTO();
		argumetoEstadoViableIniciativa.setIdArgumento(1L);		
		
		when(sessionFacadeAFS.consultarArgumentoListaSimplePorResultadoYNombreListaSimple(NHSPDDConstantes.PDD_ESTADO_VIABLE, NHSPDDConstantes.LS_ESTADOS_PDD)).thenReturn(argumetoEstadoViableIniciativa);
		when(sessionFacadeBP.filtrarIniciativaCiudadana(peticion)).thenReturn(new GenericoDTO());
		GenericoDTO res = bPProyInvConsultarService.consultarTodosBpIniciativaCiudadanaViablesFiltradas(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(200L);
		
		when(sessionFacadeAFS.consultarArgumentoListaSimplePorResultadoYNombreListaSimple(NHSPDDConstantes.PDD_ESTADO_VIABLE, NHSPDDConstantes.LS_ESTADOS_PDD)).thenReturn(null);
		res = bPProyInvConsultarService.consultarTodosBpIniciativaCiudadanaViablesFiltradas(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(200L);
	}

	@Test
	public void testConsultarTodosBpIniciativaCiudadanaRelacionadasConProyectoInversion() throws Exception {
		Long idProyecto = 1L;
		
		when(sessionFacadeBP.consultarTodosBpIniciativaCiudadanaRelacionadasConProyectoInversion(idProyecto)).thenReturn(new GenericoDTO());
		GenericoDTO res = bPProyInvConsultarService.consultarTodosBpIniciativaCiudadanaRelacionadasConProyectoInversion(idProyecto);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(200L);
	}

	@Test
	public void testConsultarTodosProyInvFianciaPorIdProyInversionPaginado() throws Exception {
//		
//		BpProyInvFinanciaDTO peticion = new BpProyInvFinanciaDTO();
//		
//		peticion.setIdProyInversion(1L);
//		when(sessionFacadeBP.consultarTodosProyInvFianciaPorIdProyInversionPaginado(peticion)).thenReturn(new GenericoDTO());
//		
//		GenericoDTO res = bPProyInvConsultarService.consultarTodosProyInvFianciaPorIdProyInversionPaginado(peticion);
//
////		assertNotNull(res);
////		assertThat(res.getCodigoRespuesta()).isEqualTo(200L);
	
	}
	public void testConsultarTodosProyInvPoblacionAsociadosAProyectoInversion() throws Exception {
		
		BpProyInvPoblacionDTO peticion = new BpProyInvPoblacionDTO();
		
		when(sessionFacadeBP.consultarGruposEtarios(peticion)).thenReturn(new GenericoDTO());
		GenericoDTO res = bPProyInvConsultarService.consultarGruposEtarios(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(200L);
		
	}

	@Test
	public void testConsultarTodosProyInvEtnicoAsociadosAProyectoInversion() throws Exception {
		
		BpProyInvEtnicoDTO peticion = new BpProyInvEtnicoDTO();
		
		when(sessionFacadeBP.consultarTodosProyInvEtnicoAsociadosAProyectoInversion(peticion)).thenReturn(new GenericoDTO());
		GenericoDTO res = bPProyInvConsultarService.consultarTodosProyInvEtnicoAsociadosAProyectoInversion(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(200L);

	}

}
