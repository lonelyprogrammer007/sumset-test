package co.gov.sdp.spdd.data.serviciofacade.bp;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import co.gov.sdp.nhspdd.common.dto.FiltroDTO;
import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpAporteCiudadanoDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpIniciativaCiudadanaDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvAnualizaDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvFinanciaDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvEtnicoDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvPoblacionDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvTipoDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyectoInversionDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.spdd.data.model.bp.BpAporteCiudadano;
import co.gov.sdp.spdd.data.model.bp.BpProyInvAnualiza;
import co.gov.sdp.spdd.data.model.bp.BpProyInvFinancia;
import co.gov.sdp.spdd.data.model.bp.BpProyInvEtnico;
import co.gov.sdp.spdd.data.model.bp.BpProyInvPoblacion;
import co.gov.sdp.spdd.data.model.bp.BpProyInvTipo;
import co.gov.sdp.spdd.data.model.bp.BpProyectoInversion;
import co.gov.sdp.spdd.data.model.view.VistaBpProyectoInversion;

@RunWith(SpringRunner.class)
public class SessionConsultaBPTest extends SessionBPTest {

	@TestConfiguration
	static class SessionConsultaBPTestContextConfiguration {
		@Bean
		public SessionConsultaBP sessionConsultaBP() {
			return new SessionConsultaBP();
		}
	}

	@Autowired
	SessionConsultaBP sessionConsultaBP;

	@Test
	public void testSessionConsultaBP() {

	}

	@Test
	public void testConsultarBpProyectoInversionPorFiltro() throws SpddExceptions {
		BpProyectoInversionDTO peticion = new BpProyectoInversionDTO();
		peticion.setTamanioPagina(5);
		peticion.setPagina(0);
		
		Pageable pageRequest = PageRequest.of(peticion.getPagina(), peticion.getTamanioPagina());
		
		FiltroDTO filtro = new FiltroDTO();
		List<BpProyectoInversion> lista = new ArrayList<BpProyectoInversion>();
		filtro.setResultadoConsulta(lista);
		filtro.setContador(100L);
		
		when(bpProyectoInversionServiceRepo.filtrarPorCampo(peticion, pageRequest.getOffset(), pageRequest.getPageSize())).thenReturn(filtro);
		GenericoDTO res = sessionConsultaBP.consultarBpProyectoInversionPorFiltro(peticion);
		assertNotNull(res);
	}

	@Test
	public void testConsultarBpProyInvLineaPorIdProyectoInversion() {

	}

	@Test
	public void testConsultarBpProyInvTipoPorIdProyectoInversion() throws SpddExceptions {
		Long id = 1L;
		
		List<BpProyInvTipo> listaBpProyInvTipo = new ArrayList<>();
		
		BpProyInvTipoDTO bpProyInvTipoDTO = new BpProyInvTipoDTO();
		bpProyInvTipoDTO.setIdTipo(id);
		List<BpProyInvTipoDTO> listaBpProyInvTipoDTO = new ArrayList<>();
		listaBpProyInvTipoDTO.add(bpProyInvTipoDTO);
		
		GenericoDTO res;
		
		when(bpProyInvTipoServiceRepo.obtenerPorIdProyectoInversion(id)).thenReturn(listaBpProyInvTipo);
		when(bpProyInvTipoMapper.bpProyInvTiposEntitiesToDTO(listaBpProyInvTipo)).thenReturn(listaBpProyInvTipoDTO);
		res = sessionConsultaBP.consultarBpProyInvTipoPorIdProyectoInversion(id);
		assertNotNull(res);
		assertThat(res.getLstObjectsDTO().isEmpty()).isEqualTo(false);
	}
	
	@Test
	public void testFiltroInciativaCiudadana() throws SpddExceptions {
		Integer pagina = 5;
		Integer tamanioPagina = 10;
		BpIniciativaCiudadanaDTO peticion = new BpIniciativaCiudadanaDTO();
		peticion.setPagina(pagina);
		peticion.setTamanioPagina(tamanioPagina);
		Pageable pageRequest = PageRequest.of(peticion.getPagina(), peticion.getTamanioPagina());
		FiltroDTO filtro = new FiltroDTO();
		filtro.setContador(100L);
		List<BpIniciativaCiudadanaDTO> lista = new ArrayList<>();
		filtro.setResultadoConsulta(lista);
		when(iniciativaCiudadanaServiceRepo.obtenerTodosIniciativaPorFiltro(peticion, pageRequest.getOffset(),
				pageRequest.getPageSize())).thenReturn(filtro);
		assertThat(sessionConsultaBP.filtroInciativaCiudadana(peticion)).isNotNull();
	}
	
	@Test
	public void testConsultarBpProyInvTipoPorIdLsTipoYIdProyInv() throws SpddExceptions
	{
		Long idLsTipo = 1L;
		Long idProyInversion = 2L;
		
		BpProyInvTipo bpProyInvTipo = new BpProyInvTipo();
		
		BpProyInvTipoDTO bpProyInvTipoDTO = new BpProyInvTipoDTO();
		bpProyInvTipoDTO.setIdTipo(5L);
		
		BpProyInvTipoDTO res;
		
		when(bpProyInvTipoServiceRepo.obtenerPorIdLsTipoYIdProyInversion(idLsTipo, idProyInversion)).thenReturn(bpProyInvTipo);
		when(bpProyInvTipoMapper.bpProyInvTipoEntityToDTO(bpProyInvTipo)).thenReturn(bpProyInvTipoDTO);
		res =  sessionConsultaBP.consultarBpProyInvTipoPorIdLsTipoYIdProyInv(idLsTipo, idProyInversion);
		assertNotNull(res);
		assertNotNull(res.getIdTipo());
		
		when(bpProyInvTipoServiceRepo.obtenerPorIdLsTipoYIdProyInversion(idLsTipo, idProyInversion)).thenReturn(null);
		res =  sessionConsultaBP.consultarBpProyInvTipoPorIdLsTipoYIdProyInv(idLsTipo, idProyInversion);
		assertNotNull(res);
		assertNull(res.getIdTipo());
	}
	
	@Test
	public void testconsultarBpAporteCiudadanoTodos() throws SpddExceptions
	{
		List<BpAporteCiudadano> lista = new ArrayList<>();
		List<BpAporteCiudadanoDTO> listaDTO = new ArrayList<>();
		when(bpAporteCiudadanoServiceRepo.obtenerTodos()).thenReturn(lista);
		when(bpAporteCiudadanoMapper.bpAportesCiudadanoEntitiesToDTO(lista)).thenReturn(listaDTO);
		GenericoDTO res = sessionConsultaBP.consultarBpAporteCiudadanoTodos();
		assertNotNull(res);
	}
	

	@Test
	public void testConsultarBpProyectoInversionTodos() throws Exception {
		Long idProyecto = 1L;
		List<VistaBpProyectoInversion> listaProyectosInversion = new ArrayList<>();
		
		BpProyectoInversionDTO bpProyectoInversionDTO = new BpProyectoInversionDTO();
		bpProyectoInversionDTO.setIdProyInversion(idProyecto);
		List<BpProyectoInversionDTO> listaProyectosInversionDTO =  new ArrayList<>();
		listaProyectosInversionDTO.add(bpProyectoInversionDTO);
		
		GenericoDTO res;
		
		when(vistaBpProyectoInversionServiceRepo.obtenerTodos()).thenReturn(listaProyectosInversion);
		when(vistaBpProyectoInversionMapper.bpProyectosInversionEntitiesToDTO(listaProyectosInversion)).thenReturn(listaProyectosInversionDTO);
		res = sessionConsultaBP.consultarBpProyectoInversionTodos();
		assertNotNull(res);
		assertThat(res.getLstObjectsDTO().isEmpty()).isEqualTo(false);
	}

	@Test
	public void testConsultarTodosBpAporteCiudadanoSinRelacionConProyectoInversion() throws Exception {
		Long idProyecto = 1L;
		List<BpAporteCiudadano> lista = new ArrayList<>();
		List<BpAporteCiudadanoDTO> listaDTO = new ArrayList<>();
		when(bpAporteCiudadanoServiceRepo.obtenerTodosSinRelacionConProyectoInversion(idProyecto)).thenReturn(lista);
		when(bpAporteCiudadanoMapper.bpAportesCiudadanoEntitiesToDTO(lista)).thenReturn(listaDTO);
		GenericoDTO res = sessionConsultaBP.consultarTodosBpAporteCiudadanoSinRelacionConProyectoInversion(idProyecto);
		assertNotNull(res);
	}

	@Test
	public void testConsultarProyInvPorId() throws Exception {
		Long idProyecto = 1L;
		
		BpProyectoInversion bpProyectoInversion = new BpProyectoInversion();
		when(bpProyectoInversionServiceRepo.obtener(idProyecto)).thenReturn(bpProyectoInversion);
		when(bpProyectoInversionMapper.bpProyectoInversionEntityToDTO(bpProyectoInversion)).thenReturn(new BpProyectoInversionDTO());
		BpProyectoInversionDTO res = sessionConsultaBP.consultarProyInvPorId(idProyecto);
		assertNotNull(res);
	}

	@Test
	public void testConsultarBpAporteCiudadanoPorId() throws Exception {
		Long idAporte = 1L;
		BpAporteCiudadano bpAporteCiudadano = new BpAporteCiudadano();
		when(bpAporteCiudadanoServiceRepo.obtener(idAporte)).thenReturn(bpAporteCiudadano);
		when(bpAporteCiudadanoMapper.bpAporteCiudadanoEntityToDTO(bpAporteCiudadano)).thenReturn(new BpAporteCiudadanoDTO());
		BpAporteCiudadanoDTO res = sessionConsultaBP.consultarBpAporteCiudadanoPorId(idAporte);
		assertNotNull(res);
	}

	@Test
	public void testObtenerTodosBpAporteCiudadanoCargadosPorArchivosConRelacionConProyectoInversion() throws Exception {
		/*
		Long idProyecto = 1L;
		
		List<BpAporteCiudadano> listaBpAporteCiudadano = new ArrayList<BpAporteCiudadano>();
		List<BpAporteCiudadanoDTO> listaBpAporteCiudadanoDTO = new ArrayList<BpAporteCiudadanoDTO>();
		when(bpAporteCiudadanoServiceRepo.obtenerTodosCargadosPorArchivosConRelacionConProyectoInversion(idProyecto)).thenReturn(listaBpAporteCiudadano);
		when(bpAporteCiudadanoMapper.bpAportesCiudadanoEntitiesToDTO(listaBpAporteCiudadano)).thenReturn(listaBpAporteCiudadanoDTO);
		GenericoDTO res = sessionConsultaBP.obtenerTodosBpAporteCiudadanoCargadosPorArchivosConRelacionConProyectoInversion(idProyecto);
		assertNotNull(res);
		*/
	}

	@Test
	public void testConsultarBpAporteCiudadanoTodos() throws Exception {
	}

	@Test
	public void testConsultarBpProyInvAportePorIdAporteYIdProyInversion() throws Exception {
	}

	@Test
	public void testObtenerUbicacionPorIniciativa() throws Exception {
	}

	@Test
	public void testObtenerGruposEtariosPorIniciativa() throws Exception {
	}

	@Test
	public void testConsultarTodosBpProyInvAportePorIdProyInversionPaginado() throws Exception {
	}

	@Test
	public void testConsultarProyInvAportePorId() throws Exception {
	}

	@Test
	public void testConsultarBpAporteCiudadanoPorAccionYFuenteYIdNivelPdd() throws Exception {
	}

	@Test
	public void testConsultarTodosBpAportesCiudadanosCargadosPorArchivos() throws Exception {
	}

	@Test
	public void testObtenerCondicionesPorIniciativa() throws Exception {
	}

	@Test
	public void testConsultarBpIniciativaCiudadanaPorId() throws Exception {
	}

	@Test
	public void testConsultarTodosBpIniciativaCiudadanaRelacionadasConProyectoInversion() throws Exception {
	}

	@Test
	public void testConsultarTodosBpProyInvIniciativaPorIdProyInversion() throws Exception {
	}

	@Test
	public void testConsultarBpProyInvIniciativaPorIdIniciativaYIdProyInversion() throws Exception {
	}

	@Test
	public void testConsultarBpProyInvEspecifPorId() throws Exception {
	}

	@Test
	public void testConsultarBpProyInvProductoPorId() throws Exception {
	}

	@Test
	public void testConsultarBpProyInvActividadPorId() throws Exception {
	}

	@Test
	public void testObtenerPorIdProyInvEspecif() throws Exception {
	}

	@Test
	public void testConsultarTodosProyInvAnualizaPorIdFuentePaginado() throws Exception {
		
		BpProyInvAnualizaDTO bpProyInvAnualizaDTO = new BpProyInvAnualizaDTO();
		bpProyInvAnualizaDTO.setTamanioPagina(5);
		bpProyInvAnualizaDTO.setPagina(0);
		bpProyInvAnualizaDTO.setIdFuente(2L);
		
		Pageable pageRequest = PageRequest.of(bpProyInvAnualizaDTO.getPagina(), bpProyInvAnualizaDTO.getTamanioPagina(),Sort.by("vigencia").ascending());
		
		
		List<BpProyInvAnualiza> listaBpProyInvAnualiza = new ArrayList<>();
		List<BpProyInvAnualizaDTO> listaBpProyInvAnualizaDTO = new ArrayList<>();
		
		when(bpProyInvAnualizaServiceRepo.obtenerPorIdTodosProyInvAnualiza(bpProyInvAnualizaDTO.getIdFuente(),pageRequest)).thenReturn(new PageImpl<>(listaBpProyInvAnualiza));
		when(bpProyInvAnualizaMapper.bpProyInvAnualizaEntitiesToDTO(new PageImpl<>(listaBpProyInvAnualiza).getContent())).thenReturn(listaBpProyInvAnualizaDTO);
		
		GenericoDTO res = sessionConsultaBP.consultarTodosProyInvAnualizaPorIdFuentePaginado(bpProyInvAnualizaDTO);
		
		assertNotNull(res);
		
		
		
		
	}

	@Test
	public void testConsultarTodosProyInvFianciaPorIdProyInversionPaginado() throws Exception {
		
		BpProyInvFinanciaDTO bpProyInvFinanciaDTO = new BpProyInvFinanciaDTO();
		bpProyInvFinanciaDTO.setIdProyInversion(1L);
		bpProyInvFinanciaDTO.setTamanioPagina(5);
		bpProyInvFinanciaDTO.setPagina(0);
		
		Pageable pageRequest = PageRequest.of(bpProyInvFinanciaDTO.getPagina(), bpProyInvFinanciaDTO.getTamanioPagina());
		
		List<BpProyInvFinancia> BpProyInvFinancia = new ArrayList<>();
		List<BpProyInvFinanciaDTO> listaBpProyInvFinanciaDTO = new ArrayList<>();
		
		when(bpProyInvFinanciaServiceRepo.obtenerPorIdTodosProyInvFiancia(bpProyInvFinanciaDTO.getIdProyInversion(), pageRequest)).thenReturn(new PageImpl<>(BpProyInvFinancia));
		when(bpProyInvFinanciaMapper.bpProyInvFinanciaEntitiesToDTO(new PageImpl<>(BpProyInvFinancia).getContent())).thenReturn(listaBpProyInvFinanciaDTO);
		
		GenericoDTO res = sessionConsultaBP.consultarTodosProyInvFianciaPorIdProyInversionPaginado(bpProyInvFinanciaDTO);
		
		assertNotNull(res);
		
	}

	@Test
	public void testConsultarProyInvFinanciaPorIdLSFuYIdProy() throws Exception {
		throw new RuntimeException("not yet implemented");
	}
	
	
	public void testConsultarTodosProyInvPoblacionAsociadosAProyectoInversion() throws Exception {
		
		BpProyInvPoblacionDTO peticion = new BpProyInvPoblacionDTO();
		peticion.setTamanioPagina(5);
		peticion.setPagina(0);
		
		Pageable pageRequest = PageRequest.of(peticion.getPagina(), peticion.getTamanioPagina());
		
		List<BpProyInvPoblacion> listaInicializacionPageList = new ArrayList<>();
		
		Page<BpProyInvPoblacion> pagina = new PageImpl<BpProyInvPoblacion>(listaInicializacionPageList,pageRequest,listaInicializacionPageList.size());
		
		when(bpProyInvPoblacionServiceRepo.obtenerTodosGruposEtarios(peticion.getIdProyInversion(), pageRequest)).thenReturn(pagina);
		
		List<BpProyInvPoblacion> listaBpProyInvPoblacion = new ArrayList<>();
		List<BpProyInvPoblacionDTO> listaBpProyInvPoblacionDTO = new ArrayList<>();
		listaBpProyInvPoblacionDTO.add(peticion);
		
		when(bpProyInvPoblacionMapper.bpProyInvPoblacionEntitiesToDTO(listaBpProyInvPoblacion)).thenReturn(listaBpProyInvPoblacionDTO);
		
		GenericoDTO res = new GenericoDTO();
		
		res = sessionConsultaBP.consultarGruposEtarios(peticion);
		assertNotNull(res);
		assertThat(res.getLstObjectsDTO().isEmpty()).isEqualTo(false);
	}

	@Test
	public void testConsultarTodosProyInvEtnicoAsociadosAProyectoInversion() throws Exception {
//		BpProyInvEtnicoDTO peticion = new BpProyInvEtnicoDTO();
//		peticion.setTamanioPagina(5);
//		peticion.setPagina(0);
//		
//		Pageable pageRequest = PageRequest.of(peticion.getPagina(), peticion.getTamanioPagina());
//		
//		List<BpProyInvEtnico> listaInicializacionPageList = new ArrayList<>();
//		
//		when(bpProyInvEtnicoServiceRepo.obtenerTodosProyInvEtnicoAsociadosAProyectoInversion(peticion.getIdProyInversion(), pageRequest)).thenReturn(new PageImpl<>(listaInicializacionPageList));
//		
//		List<BpProyInvEtnico> listaBpProyInvEtnico = new ArrayList<>();
//		List<BpProyInvEtnicoDTO> listaBpProyInvEtnicoDTO = new ArrayList<>();
//		listaBpProyInvEtnicoDTO.add(peticion);
//		
//		when(bpProyInvEtnicoMapper.bpProyInvEtnicoEntitiesToDTO(listaBpProyInvEtnico)).thenReturn(listaBpProyInvEtnicoDTO);
//		
//		GenericoDTO res = new GenericoDTO();
//		
//		res = sessionConsultaBP.consultarTodosProyInvEtnicoAsociadosAProyectoInversion(peticion);
//		assertNotNull(res);
//		assertThat(res.getLstObjectsDTO().isEmpty()).isEqualTo(false);
	}

}
