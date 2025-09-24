package co.gov.sdp.spdd.data.serviciofacade.ip;

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
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import co.gov.sdp.nhspdd.common.dto.FiltroDTO;
import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.dto.PddDTO;
import co.gov.sdp.nhspdd.common.dto.PddNivelAtributoDTO;
import co.gov.sdp.nhspdd.common.dto.PddNivelDTO;
import co.gov.sdp.nhspdd.common.dto.PotInstrumentoDTO;
import co.gov.sdp.nhspdd.common.dto.PotObraDTO;
import co.gov.sdp.nhspdd.common.dto.ip.CompromisoEstrategicoDTO;
import co.gov.sdp.nhspdd.common.dto.ip.HisVPddCompromisoDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddCompetenciaAsociadaDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddCompromisoDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddCompromisoEspecificoDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddIndicadorDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddMetaDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddMetaResultadoDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddMpIndicadorDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddObraConcretaDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddPrbIndicadorDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddPrbPoblacionDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddPrbValoracionDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddProblematicaDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddRangoPonderacionDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PdlDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PdlNivelAtributoDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PdlNivelDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PotDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PotObraEntidadDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.spdd.data.model.afs.ArchivoProcesado;
import co.gov.sdp.spdd.data.model.ip.CompromisoEstrategico;
import co.gov.sdp.spdd.data.model.ip.Pdd;
import co.gov.sdp.spdd.data.model.ip.PddCompetenciaAsociada;
import co.gov.sdp.spdd.data.model.ip.PddCompromiso;
import co.gov.sdp.spdd.data.model.ip.PddCompromisoEspecifico;
import co.gov.sdp.spdd.data.model.ip.PddIndicador;
import co.gov.sdp.spdd.data.model.ip.PddMeta;
import co.gov.sdp.spdd.data.model.ip.PddMetaResultado;
import co.gov.sdp.spdd.data.model.ip.PddMpIndicador;
import co.gov.sdp.spdd.data.model.ip.PddNivel;
import co.gov.sdp.spdd.data.model.ip.PddNivelAtributo;
import co.gov.sdp.spdd.data.model.ip.PddObraConcreta;
import co.gov.sdp.spdd.data.model.ip.PddPrbIndicador;
import co.gov.sdp.spdd.data.model.ip.PddPrbPoblacion;
import co.gov.sdp.spdd.data.model.ip.PddPrbValoracion;
import co.gov.sdp.spdd.data.model.ip.PddProblematica;
import co.gov.sdp.spdd.data.model.ip.PddRangoPonderacion;
import co.gov.sdp.spdd.data.model.ip.Pdl;
import co.gov.sdp.spdd.data.model.ip.PdlNivel;
import co.gov.sdp.spdd.data.model.ip.PdlNivelAtributo;
import co.gov.sdp.spdd.data.model.ip.Pot;
import co.gov.sdp.spdd.data.model.ip.PotInstrumento;
import co.gov.sdp.spdd.data.model.ip.PotObra;
import co.gov.sdp.spdd.data.model.ip.PotObraEntidad;

@RunWith(SpringRunner.class)
public class SessionConsultaIPTest extends SessionIPTest {

	@TestConfiguration
	static class SessionConsultaIPTestContextConfiguration {
		@Bean
		public SessionConsultaIP sessionConsultaIP() {
			return new SessionConsultaIP();
		}
	}

	@Autowired
	SessionConsultaIP sessionConsultaIP;	

	@Test
	public void testSessionConsultaIP() {
	}

	@Test
	public void testConsultarCompromisoPorFiltro() throws SpddExceptions {
		Integer pagina = 5;
		Integer tamanioPagina = 10;
		HisVPddCompromisoDTO peticion = new HisVPddCompromisoDTO();
		peticion.setPagina(pagina);
		peticion.setTamanioPagina(tamanioPagina);
		Pageable pageRequest = PageRequest.of(peticion.getPagina(), peticion.getTamanioPagina());
		FiltroDTO filtro = new FiltroDTO();
		filtro.setContador(100L);
		List<HisVPddCompromisoDTO> lista = new ArrayList<>();
		filtro.setResultadoConsulta(lista);
		when(hisVPddCompromisoServiceRepo.filtrarPorCampo(peticion, pageRequest.getOffset(), pageRequest.getPageSize())).thenReturn(filtro);
		assertNotNull(sessionConsultaIP.consultarCompromisoPorFiltro(peticion));
	}

	@Test
	public void testConsultarPddMetaResultadoPorProyecto() throws SpddExceptions {
		Integer pagina = 5;
		Integer tamanioPagina = 10;
		PddMetaResultadoDTO peticion = new PddMetaResultadoDTO();
		peticion.setPagina(pagina);
		peticion.setTamanioPagina(tamanioPagina);
		Pageable pageRequest = PageRequest.of(peticion.getPagina(), peticion.getTamanioPagina());
		FiltroDTO filtro = new FiltroDTO();
		filtro.setContador(100L);
		List<ArchivoProcesado> lista = new ArrayList<>();
		filtro.setResultadoConsulta(lista);
		when(pddMetaResultadoServiceRepo.consultarPddPorProyectoEstrategico(peticion, pageRequest.getOffset(),
				pageRequest.getPageSize())).thenReturn(filtro);
		assertNotNull(sessionConsultaIP.consultarPddMetaResultadoPorProyecto(peticion));

	}

	@Test
	public void testConsultarCompromisoEstrategicoPorFiltro() throws SpddExceptions {
		Integer pagina = 5;
		Integer tamanioPagina = 10;
		CompromisoEstrategicoDTO peticion = new CompromisoEstrategicoDTO();
		peticion.setPagina(pagina);
		peticion.setTamanioPagina(tamanioPagina);
		Pageable pageRequest = PageRequest.of(peticion.getPagina(), peticion.getTamanioPagina());
		FiltroDTO filtro = new FiltroDTO();
		filtro.setContador(100L);
		List<CompromisoEstrategico> lista = new ArrayList<>();
		filtro.setResultadoConsulta(lista);
		when(compromisoEstrategicoServiceRepo.filtrarPorCampo(peticion, pageRequest.getOffset(),
				pageRequest.getPageSize())).thenReturn(filtro);
		assertNotNull(sessionConsultaIP.consultarCompromisoEstrategicoPorFiltro(peticion));
	}

	@Test
	public void testConsultarCompromisoEstrategicoPorId() throws SpddExceptions {
		when(compromisoEstrategicoServiceRepo.obtener(1L)).thenReturn(new CompromisoEstrategico());
		when(compromisoEstrategicoMapper.compromisoEstrategicoEntityToDTO(new CompromisoEstrategico())).thenReturn(new CompromisoEstrategicoDTO());
		assertNotNull(sessionConsultaIP.consultarCompromisoEstrategicoPorId(1L));
	}

	@Test
	public void testConsultarCompromisoEstrategicoPorIdTematicaYIdCompromisoEstrategico() throws SpddExceptions {
		when(compromisoEstrategicoServiceRepo.buscarPorIdTematicaYIdCompromisoEstrategico(1L, 1L)).thenReturn(new CompromisoEstrategico());
		when(compromisoEstrategicoMapper.compromisoEstrategicoEntityToDTO(new CompromisoEstrategico())).thenReturn(new CompromisoEstrategicoDTO());
		assertNotNull(sessionConsultaIP.consultarCompromisoEstrategicoPorIdTematicaYIdCompromisoEstrategico(1L, 1L));
	}

	@Test
	public void testConsultarMetasCompromisoEstrategico() throws SpddExceptions {
		when(pddMetaServiceRepo.consultarMetasCompromistoEstrategico(1L)).thenReturn(new ArrayList<PddMeta>());
		when(pddMetaMapper.pddMetaEntitiesToDTO(new ArrayList<PddMeta>())).thenReturn(new ArrayList<PddMetaDTO>());
		assertNotNull(sessionConsultaIP.consultarMetasCompromisoEstrategico(1L));
	}

	@Test
	public void testConsultarObrasConcretasPorMetas() throws SpddExceptions {
		when(pddObraConcretaServiceRepo.buscarPorMeta(1L)).thenReturn(new ArrayList<PddObraConcreta>());
		when(pddObraConcretamapper.pddObraConcretaEntitiesToDTO(new ArrayList<PddObraConcreta>())).thenReturn(new ArrayList<PddObraConcretaDTO>());
		assertNotNull(sessionConsultaIP.consultarObrasConcretasPorMetas(1L));
	}

	@Test
	public void testConsultarPddPorFiltro() throws SpddExceptions {
		Integer pagina = 5;
		Integer tamanioPagina = 10;
		PddDTO peticion = new PddDTO();
		peticion.setPagina(pagina);
		peticion.setTamanioPagina(tamanioPagina);
		Pageable pageRequest = PageRequest.of(peticion.getPagina(), peticion.getTamanioPagina());
		FiltroDTO filtro = new FiltroDTO();
		filtro.setContador(100L);
		List<Pdd> lista = new ArrayList<>();
		filtro.setResultadoConsulta(lista);
		when(pddServiceRepo.filtrarPorCampo(peticion, pageRequest.getOffset(), pageRequest.getPageSize())).thenReturn(filtro);
		assertNotNull(sessionConsultaIP.consultarPddPorFiltro(peticion));
	}

	@Test
	public void testConsultarPddPorId() throws SpddExceptions {
		when(pddServiceRepo.obtener(1L)).thenReturn(new Pdd());
		when(pddMapper.pddEntityToDTO(new Pdd())).thenReturn(new PddDTO());
		assertNull(sessionConsultaIP.consultarPddPorId(1L));
	}

	@Test
	public void testConsultarPddsPorEstado() throws SpddExceptions {
		when(pddServiceRepo.obtenerPddsPorEstado(1L)).thenReturn(new ArrayList<Pdd>());
		when(pddMapper.pddEntitiesToDTO(new ArrayList<Pdd>())).thenReturn(new ArrayList<PddDTO>());
	}

	@Test
	public void testConsultarPddNivelPorId() throws SpddExceptions {
		when(pddNivelServiceRepo.obtener(1L)).thenReturn(new PddNivel());
		when(pddNivelMapper.pddNivelEntityToDTO(new PddNivel())).thenReturn(new PddNivelDTO());
		assertNotNull(sessionConsultaIP.consultarPddNivelesPorIdPlanDesarrollo(1L));
	}

	@Test
	public void testConsultarPddNivelesPorIdPlanDesarrollo() throws SpddExceptions {
		when(pddNivelServiceRepo.obtenerPorIdPlanDesarrollo(1L)).thenReturn(new ArrayList<PddNivel>());
		when(pddNivelMapper.pddNivelEntitiesToDTO(new ArrayList<PddNivel>())).thenReturn(new ArrayList<PddNivelDTO>());
		assertNotNull(sessionConsultaIP.consultarPddNivelesPorIdPlanDesarrollo(1L));
	}

	@Test
	public void testConsultarPddNivelPorNivelYIdPlanDesarrollo() throws SpddExceptions {
		when(pddNivelServiceRepo.obtenerPorNivelYIdPlanDesarrollo(1L, 1L)).thenReturn(new PddNivel());
		when(pddNivelMapper.pddNivelEntityToDTO(new PddNivel())).thenReturn(new PddNivelDTO());
		assertNotNull(sessionConsultaIP.consultarPddNivelPorNivelYIdPlanDesarrollo(1L,1L));
	}

	@Test
	public void testConsultarPddCompetenciaAsociadaPorId() throws SpddExceptions {
		when(pddCompetenciaAsociadaServiceRepo.obtener(1L)).thenReturn(new PddCompetenciaAsociada());
		when(pddCompetenciaAsociadaMapper.pddCompetenciaAsociadaEntityToDTO(new PddCompetenciaAsociada())).thenReturn(new PddCompetenciaAsociadaDTO());
		assertNotNull(sessionConsultaIP.consultarPddCompetenciaAsociadaPorId(1L));
	}

	@Test
	public void testConsultarPddCompetenciaAsociadaPorIdSector() throws SpddExceptions {
		when(pddCompetenciaAsociadaServiceRepo.obtenerPorIdSector(1L)).thenReturn(new ArrayList<PddCompetenciaAsociada>());
		when(pddCompetenciaAsociadaMapper.pddCompetenciasAsociadasEntitiesToDTO(new ArrayList<PddCompetenciaAsociada>())).thenReturn(new ArrayList<PddCompetenciaAsociadaDTO>());
		assertNotNull(sessionConsultaIP.consultarPddCompetenciaAsociadaPorIdSector(1L));
	}

	@Test
	public void testConsultarPddCompetenciaAsociadaPorIdSectorYIdCompetencia() throws SpddExceptions {
		when(pddCompetenciaAsociadaServiceRepo.obtenerPorIdSectorYIdCompetencia(1L, 1L)).thenReturn(new PddCompetenciaAsociada());
		when(pddCompetenciaAsociadaMapper.pddCompetenciaAsociadaEntityToDTO(new PddCompetenciaAsociada())).thenReturn(new PddCompetenciaAsociadaDTO());
		assertNotNull(sessionConsultaIP.consultarPddCompetenciaAsociadaPorIdSectorYIdCompetencia(1L, 1L));
	}

	@Test
	public void testConsultarPddCompromisoEspecificoPorId() throws SpddExceptions {
		when(pddCompromisoEspecificoServiceRepo.obtener(1L)).thenReturn(new PddCompromisoEspecifico());
		when(pddCompromisoEspecifocMapper.pddCompromisoEspecificoEntityToDTO(new PddCompromisoEspecifico())).thenReturn(new PddCompromisoEspecificoDTO());
		assertNotNull(sessionConsultaIP.consultarPddCompromisoEspecificoPorId(1L));
	}

	@Test
	public void testConsultarPddCompromisoEspecificoPorIdCompromisoYDescripcion() throws SpddExceptions {
		when(pddCompromisoEspecificoServiceRepo.obtenerPorIdCompromiso(1L)).thenReturn(new ArrayList<PddCompromisoEspecifico>());
		when(pddCompromisoEspecifocMapper.pddCompromisoEspecificoEntitiesToDTO(new ArrayList<PddCompromisoEspecifico>())).thenReturn(new ArrayList<PddCompromisoEspecificoDTO>());
		assertNotNull(sessionConsultaIP.consultarPddCompromisosEspecificosPorIdCompromiso(1L));
	}

	@Test
	public void testConsultarPddCompromisosEspecificosPorIdCompromiso() throws SpddExceptions {
		when(pddCompromisoEspecificoServiceRepo.obtenerPorIdCompromisoYDescripcion(1L, "d")).thenReturn(new PddCompromisoEspecifico());
		when(pddCompromisoEspecifocMapper.pddCompromisoEspecificoEntityToDTO(new PddCompromisoEspecifico())).thenReturn(new PddCompromisoEspecificoDTO());
		assertNotNull(sessionConsultaIP.consultarPddCompromisoEspecificoPorIdCompromisoYDescripcion(1L, "d"));
	}

	@Test
	public void testConsultarPdlPorFiltro() throws SpddExceptions {
		Integer pagina = 5;
		Integer tamanioPagina = 10;
		PdlDTO peticion = new PdlDTO();
		peticion.setPagina(pagina);
		peticion.setTamanioPagina(tamanioPagina);
		Pageable pageRequest = PageRequest.of(peticion.getPagina(), peticion.getTamanioPagina());
		FiltroDTO filtro = new FiltroDTO();
		filtro.setContador(100L);
		List<Pdl> lista = new ArrayList<>();
		filtro.setResultadoConsulta(lista);
		when(pdlServiceRepo.filtrarPorCampo(peticion, pageRequest.getOffset(), pageRequest.getPageSize())).thenReturn(filtro);
		assertNotNull(sessionConsultaIP.consultarPdlPorFiltro(peticion));
	}

	@Test
	public void testConsultarTodosPlanDesarrolloLocal() throws SpddExceptions {
		Pdl resultado = new Pdl();
		List<Pdl> lstResultado = new ArrayList<>();
		lstResultado.add(resultado);
		when(pdlServiceRepo.obtenerTodos()).thenReturn(lstResultado);
		when(pdlMapper.pdlEntitiesToDTO(lstResultado)).thenReturn(new ArrayList<PdlDTO>());
		assertNotNull(sessionConsultaIP.consultarTodosPlanDesarrolloLocal());
	}

	@Test
	public void testConsultarPlanDesarrolloLocalPorId() throws SpddExceptions {
		Pdl resultado = new Pdl();
		when(pdlServiceRepo.obtener(1L)).thenReturn(resultado);
		when(pdlMapper.pdlEntityToDTO(resultado)).thenReturn(new PdlDTO());
		assertNotNull(sessionConsultaIP.consultarPlanDesarrolloLocalPorId(1L));
	}

	@Test
	public void testConsultarPdlsPorEstado() throws SpddExceptions {
		Pdl resultado = new Pdl();
		List<Pdl> lstResultado = new ArrayList<>();
		lstResultado.add(resultado);
		when(pdlServiceRepo.obtenerPdlsPorEstado(1L)).thenReturn(lstResultado);
		when(pdlMapper.pdlEntitiesToDTO(lstResultado)).thenReturn(new ArrayList<PdlDTO>());
		assertNotNull(sessionConsultaIP.consultarPdlsPorEstado(1L));
	}

	@Test
	public void testConsultarPdlNivelPorId() throws SpddExceptions {
		PdlNivel resultado = new PdlNivel();
		when(pdlNivelServiceRepo.obtener(1L)).thenReturn(resultado);
		when(pdlNivelMapper.pdlNivelEntityToDTO(resultado)).thenReturn(new PdlNivelDTO());
		assertNotNull(sessionConsultaIP.consultarPdlNivelPorId(1L));
	}

	@Test
	public void testConsultarPdlNivelesPorIdPlanLocal() throws SpddExceptions {
		PdlNivel resultado = new PdlNivel();
		List<PdlNivel> lstResultado = new ArrayList<>();
		lstResultado.add(resultado);
		when(pdlNivelServiceRepo.obtenerPdlNivelPorIdPlanLocal(1L)).thenReturn(lstResultado);
		when(pdlNivelMapper.pdlNivelEntitiesToDTO(lstResultado)).thenReturn(new ArrayList<PdlNivelDTO>());
		assertNotNull(sessionConsultaIP.consultarPdlNivelesPorIdPlanLocal(1L));
	}

	@Test
	public void testConsultarPdlNivelPorNivelYIdPlanLocal() throws SpddExceptions {
		PdlNivel resultado = new PdlNivel();
		when(pdlNivelServiceRepo.obtenerPdlNivelPorNivelYIdPlanLocal(1L, 1L)).thenReturn(resultado);
		when(pdlNivelMapper.pdlNivelEntityToDTO(resultado)).thenReturn(new PdlNivelDTO());
		assertNotNull(sessionConsultaIP.consultarPdlNivelPorNivelYIdPlanLocal(1L, 1L));
	}

	@Test
	public void testConsultarPddCompromisosPorFiltro() throws SpddExceptions {
		Integer pagina = 5;
		Integer tamanioPagina = 10;
		PddCompromisoDTO peticion = new PddCompromisoDTO();
		peticion.setPagina(pagina);
		peticion.setTamanioPagina(tamanioPagina);
		FiltroDTO filtro = new FiltroDTO();
		filtro.setContador(100L);
		List<PddCompromiso> lista = new ArrayList<>();
		filtro.setResultadoConsulta(lista);
		when(pddCompromisoServiceRepo.filtrarPorCampo(peticion)).thenReturn(filtro);
		when(pddCompromisoMapper.pddCompromisosEntitiesToDTO(new ArrayList<PddCompromiso>())).thenReturn(new ArrayList<PddCompromisoDTO>());
		assertNotNull(sessionConsultaIP.consultarPddCompromisosPorFiltro(peticion));
	}

	@Test
	public void testConsultarPddCompromisoPorIdPlanDesarrollo() throws SpddExceptions {
		when(pddCompromisoServiceRepo.obtenerTodosPorIdPlanDesarrollo(1L)).thenReturn(new ArrayList<PddCompromiso>());
		when(pddCompromisoMapper.pddCompromisosEntitiesToDTO(new ArrayList<PddCompromiso>())).thenReturn(new ArrayList<PddCompromisoDTO>());
		assertNotNull(sessionConsultaIP.consultarPddCompromisoPorIdPlanDesarrollo(1L));
	}

	@Test
	public void testConsultarPddCompromisoPorIdEstrategicoYIdPlanDesarrollo() throws SpddExceptions {
		when(pddCompromisoServiceRepo.obtenerPorIdEstrategicoYIdPlanDesarrollo(1L,1L)).thenReturn(new PddCompromiso());
		when(pddCompromisoMapper.pddCompromisoEntityToDTO(new PddCompromiso())).thenReturn(new PddCompromisoDTO());
	}

	@Test
	public void testConsultarPddIndicadorPorId() throws SpddExceptions{
		when(pddIndicadorServiceRepo.obtener(1L)).thenReturn(new PddIndicador());
		when(pddIndicadorMapper.pddIndicadorEntityToDTO(new PddIndicador())).thenReturn(new PddIndicadorDTO());
		assertNotNull(sessionConsultaIP.consultarPddIndicadorPorId(1L));
	}

	@Test
	public void testConsultarPddPrbValoracionPorId() throws SpddExceptions {
		when(pddPrbValoracionServiceRepo.obtener(1L)).thenReturn(new PddPrbValoracion());
		when(pddPrbValoracionMapper.pddPrbValoracionEntityToDTO(new PddPrbValoracion())).thenReturn(new PddPrbValoracionDTO());
		assertNotNull(sessionConsultaIP.consultarPddPrbValoracionPorId(1L));
	}

	@Test
	public void testConsultarPddPrbValoracionPorIdProblematicaYMomento() throws SpddExceptions {
		when(pddPrbValoracionServiceRepo.obtenerPorIdProblematicaYMomento(1L, 1L)).thenReturn(new PddPrbValoracion());
		when(pddPrbValoracionMapper.pddPrbValoracionEntityToDTO(new PddPrbValoracion())).thenReturn(new PddPrbValoracionDTO());
		assertNotNull(sessionConsultaIP.consultarPddPrbValoracionPorIdProblematicaYMomento(1L, 1L));
	}

	@Test
	public void testConsultarPddProblematicaPorId() throws SpddExceptions {
		when(pddProblematicaServiceRepo.obtener(1L)).thenReturn(new PddProblematica());
		when(pddProblematicaMapper.pddProblematicaEntityToDTO(new PddProblematica())).thenReturn(new PddProblematicaDTO());
		assertNotNull(sessionConsultaIP.consultarPddProblematicaPorId(1L));
	}

	@Test
	public void testConsultarPddMetaPorId() throws SpddExceptions {
		when(pddMetaServiceRepo.obtener(1L)).thenReturn(new PddMeta());
		when(pddMetaMapper.pddMetaEntityToDTO(new PddMeta())).thenReturn(new PddMetaDTO());
		assertNotNull(sessionConsultaIP.consultarPddMetaPorId(1L));
	}

	@Test
	public void testConsultarPddMetaResultadoPorId() throws SpddExceptions {
		PddMetaResultado resultado = new PddMetaResultado();
		when(pddMetaResultadoServiceRepo.obtener(1L)).thenReturn(resultado);
		when(pddMetaResultadoMapper.pddMetaResultadoEntityToDTO(resultado)).thenReturn(new PddMetaResultadoDTO());
		assertNotNull(sessionConsultaIP.consultarPddMetaResultadoPorId(1L));

	}

	@Test
	public void testConsultarObraConcretaPorId() throws SpddExceptions {
		when(pddObraConcretaServiceRepo.obtener(1L)).thenReturn(new PddObraConcreta());
		when(pddObraConcretamapper.pddObraConcretaEntityToDTO(new PddObraConcreta())).thenReturn(new PddObraConcretaDTO());
		assertNotNull(sessionConsultaIP.consultarObraConcretaPorId(1L));
	}

	@Test
	public void testConsultarPrbPolacionPorId() throws SpddExceptions {
		when(poblacionServiceRepo.obtener(1L)).thenReturn(new PddPrbPoblacion());
		when(poblacionMapper.poblacionEntityToDTO(new PddPrbPoblacion())).thenReturn(new PddPrbPoblacionDTO());
		assertNotNull(sessionConsultaIP.consultarPrbPolacionPorId(1L));
	}

	@Test
	public void testConsultarPddPrbIndicadorPorProblematica() throws SpddExceptions {
		when(pddPrbIndicadorServiceRepo.obtenerPrbIndicadorPorProblematica(1L)).thenReturn(new ArrayList<PddPrbIndicador>());
		when(pddPrbIndicadorMapper.prbIndicadorEntititesToDTO(new ArrayList<PddPrbIndicador>())).thenReturn(new ArrayList<PddPrbIndicadorDTO>());
		assertNotNull(sessionConsultaIP.consultarPddPrbIndicadorPorProblematica(1L));
	}

	@Test
	public void testConsultarPddPrbIndicadorPorId() throws SpddExceptions {
		when(pddPrbIndicadorServiceRepo.obtener(1L)).thenReturn(new PddPrbIndicador());
		when(pddPrbIndicadorMapper.prbIndicadorEntityToDTO(new PddPrbIndicador())).thenReturn(new PddPrbIndicadorDTO());
		assertNotNull(sessionConsultaIP.consultarPddPrbIndicadorPorId(1L));
	}

	@Test
	public void testConsultarTodosPddIndicador() throws SpddExceptions {
		when(pddIndicadorServiceRepo.obtenerTodos()).thenReturn(new ArrayList<PddIndicador>());
		when(pddIndicadorMapper.pddIndicadorEntitiesToDTO(new ArrayList<PddIndicador>())).thenReturn(new ArrayList<PddIndicadorDTO>());
		assertNotNull(sessionConsultaIP.consultarTodosPddIndicador());
	}

	@Test
	public void testConsultarPddProblematicaPorCompromiso() throws SpddExceptions {
		Integer pagina = 5;
		Integer tamanioPagina = 10;
		PddProblematicaDTO peticion = new PddProblematicaDTO();
		peticion.setPagina(pagina);
		peticion.setTamanioPagina(tamanioPagina);
		FiltroDTO filtro = new FiltroDTO();
		filtro.setContador(100L);
		List<PddProblematica> lista = new ArrayList<>();
		filtro.setResultadoConsulta(lista);
		when(pddProblematicaServiceRepo.filtrarPorCampo(peticion)).thenReturn(filtro);
		when(pddProblematicaMapper.pddProblematicasEntitiesToDTO(new ArrayList<PddProblematica>())).thenReturn(new ArrayList<PddProblematicaDTO>());
		assertNotNull(sessionConsultaIP.consultarPddProblematicaPorCompromiso(peticion));
	}

	@Test
	public void testValidarMeta() throws SpddExceptions {
		when(pddMetaServiceRepo.validarMetaTipoYEspecifico("m", 1L, 1L)).thenReturn(new PddMeta());
		when(pddMetaMapper.pddMetaEntityToDTO(new PddMeta())).thenReturn(new PddMetaDTO());
		assertNotNull(sessionConsultaIP.validarMeta("m", 1L, 1L));
	}

	@Test
	public void testValidarObra() throws SpddExceptions {
		when(pddObraConcretaServiceRepo.validarCampos(1L, "o")).thenReturn(new PddObraConcreta());
		when(pddObraConcretamapper.pddObraConcretaEntityToDTO(new PddObraConcreta())).thenReturn(new PddObraConcretaDTO());
		assertNotNull(sessionConsultaIP.validarObra(1L, "o"));
	}

	@Test
	public void testValidarPrbPoblacion() throws SpddExceptions {
		when(poblacionServiceRepo.validarReglasPoblacion("d", 1L)).thenReturn(new PddPrbPoblacion());
		when(poblacionMapper.poblacionEntityToDTO(new PddPrbPoblacion())).thenReturn(new PddPrbPoblacionDTO());
		assertNotNull(sessionConsultaIP.validarPrbPoblacion("d", 1L));
	}

	@Test
	public void testConsultarPddMetaResultadoPorIDProblematicaIndicador() throws SpddExceptions {
		when(pddMetaResultadoServiceRepo.consultarPorIDProblematicaIndicador(1L)).thenReturn(new ArrayList<PddMetaResultado>());
		when(pddMetaResultadoMapper.pddMetaResultadoEntitiesToDTO(new ArrayList<PddMetaResultado>())).thenReturn(new ArrayList<PddMetaResultadoDTO>());
		assertNotNull(sessionConsultaIP.consultarPddMetaResultadoPorIDProblematicaIndicador(1L));
	}

	@Test
	public void testConsultarMpAnualizarPorIdMetaResultado() throws SpddExceptions {
	}

	@Test
	public void testValidarPrbIndicador() throws SpddExceptions {
		when(pddPrbIndicadorServiceRepo.consultarPorIndicidicadorYProblematica(1L, 1L)).thenReturn(new PddPrbIndicador());
		when(pddPrbIndicadorMapper.prbIndicadorEntityToDTO(new PddPrbIndicador())).thenReturn(new PddPrbIndicadorDTO());
		assertNotNull(sessionConsultaIP.validarPrbIndicador(1L, 1L));
	}

	@Test
	public void testConsultarCompromisoPorId() throws SpddExceptions {
		when(pddCompromisoServiceRepo.obtener(1L)).thenReturn(new PddCompromiso());
		when(pddCompromisoMapper.pddCompromisoEntityToDTO(new PddCompromiso())).thenReturn(new PddCompromisoDTO());
		assertNotNull(sessionConsultaIP.consultarCompromisoPorId(1L));
	}

	@Test
	public void testValidarProblematicaPorLlaveDeNegocio() throws SpddExceptions {
		PddProblematicaDTO peticion = new PddProblematicaDTO();
		peticion.setIdCompromiso(1L);
		peticion.setProblematica("pro");
		when(pddProblematicaServiceRepo.validarLlaveDeNegocio(peticion.getIdCompromiso(), peticion.getProblematica())).thenReturn(new PddProblematica());
		when(pddProblematicaMapper.pddProblematicaEntityToDTO(new PddProblematica())).thenReturn(peticion);
		assertNotNull(sessionConsultaIP.validarProblematicaPorLlaveDeNegocio(peticion));
	}
	
	@Test
	public void testConsultarPotPorFiltro() throws SpddExceptions {
		Integer pagina = 5;
		Integer tamanioPagina = 10;
		PotDTO peticion = new PotDTO();
		peticion.setPagina(pagina);
		peticion.setTamanioPagina(tamanioPagina);
		Pageable pageRequest = PageRequest.of(peticion.getPagina(), peticion.getTamanioPagina());
		FiltroDTO filtro = new FiltroDTO();
		filtro.setContador(100L);
		List<Pot> lista = new ArrayList<>();
		filtro.setResultadoConsulta(lista);
		when(potServiceRepo.filtrarPorCampo(peticion, pageRequest.getOffset(), pageRequest.getPageSize())).thenReturn(filtro);
		assertNotNull(sessionConsultaIP.consultarPotPorFiltro(peticion));
	}

	@Test
	public void testConsultarTodosPotObraPorIdNivelPotPaginado() throws SpddExceptions {
		/*
		Integer pagina = 0;
		Integer tamanioPagina = Integer.MAX_VALUE;
		PotObraDTO potObraDTO = new PotObraDTO();
		Long idNivelPot = 1L;
		potObraDTO.setIdNivelPot(idNivelPot);
		Pageable pageRequest = PageRequest.of(pagina, tamanioPagina, Sort.by("codigo").ascending());
		
		PotObra potObra = new PotObra();
		List<PotObra> listaPotObra = new ArrayList<PotObra>();
		listaPotObra.add(potObra);
		Page<PotObra> pagePotObra = new PageImpl<PotObra>(listaPotObra, pageRequest, listaPotObra.size());
		
		PotObraDTO potObraDTO1 = new PotObraDTO();
		List<PotObraDTO> listaPotObraDTO = new ArrayList<PotObraDTO>();
		listaPotObraDTO.add(potObraDTO1);
		
		when(potObraServiceRepo.obtenerTodosPaginadoPorIdNivelPot(potObraDTO.getIdNivelPot(), pageRequest)).thenReturn(pagePotObra);
		when(potObraMapper.potObraEntitiesToDTO(pagePotObra.getContent())).thenReturn(listaPotObraDTO);
		GenericoDTO res = sessionConsultaIP.consultarTodosPotObraPorIdNivelPotPaginado(potObraDTO);
		assertNotNull(res);
		*/
	}

	@Test
	public void testConsultarPotObraPorCodigoYIdNivelPot() throws SpddExceptions {
		Long codigo = 1L;
		Long idNivelPot = 1L;
		PotObra potObra = new PotObra();
		PotObraDTO potObraDTO = new PotObraDTO();
		potObraDTO.setIdObraProyPot(1L);
		when(potObraServiceRepo.obtenerPorCodigoYIdNivelPot(codigo, idNivelPot)).thenReturn(potObra);
		when(potObraMapper.potObraEntityToDTO(potObra)).thenReturn(potObraDTO);
		PotObraDTO res = sessionConsultaIP.consultarPotObraPorCodigoYIdNivelPot(codigo, idNivelPot);
		assertNotNull(res);
		assertNotNull(res.getIdObraProyPot());
		when(potObraServiceRepo.obtenerPorCodigoYIdNivelPot(codigo, idNivelPot)).thenReturn(null);
		res = sessionConsultaIP.consultarPotObraPorCodigoYIdNivelPot(codigo, idNivelPot);
		assertNotNull(res);
		assertNull(res.getIdObraProyPot());
		
	}

	@Test
	public void testConsultarPotObraPorId() throws SpddExceptions {
		Long idPotObra = 1L;
		PotObra potObra = new PotObra();
		when(potObraServiceRepo.obtener(idPotObra)).thenReturn(potObra);
		when(potObraMapper.potObraEntityToDTO(potObra)).thenReturn(new PotObraDTO());
		PotObraDTO res = sessionConsultaIP.consultarPotObraPorId(idPotObra);
		assertNotNull(res);
	}

	@Test
	public void testObtenerPotObraEntidadPorCodigoEntidadYIdPotObra() throws SpddExceptions {
		String codigoEntidad = "123";
		Long idPotObra = 1L;
		PotObraEntidad potObraEntidad = new PotObraEntidad();
		PotObraEntidadDTO potObraEntidadDTO= new PotObraEntidadDTO();
		potObraEntidadDTO.setIdObraEntidad(1L);
		when(potObraEntidadServiceRepo.obtenerPotCodigoEntidadYIdPotObra(codigoEntidad,idPotObra)).thenReturn(potObraEntidad);
		when(potObraEntidadMapper.potObraEntidadEntityToDTO(potObraEntidad)).thenReturn(potObraEntidadDTO);
		PotObraEntidadDTO res = sessionConsultaIP.obtenerPotObraEntidadPorCodigoEntidadYIdPotObra(codigoEntidad, idPotObra);
		assertNotNull(res);
		assertNotNull(res.getIdObraEntidad());
		when(potObraEntidadServiceRepo.obtenerPotCodigoEntidadYIdPotObra(codigoEntidad,idPotObra)).thenReturn(null);
		res = sessionConsultaIP.obtenerPotObraEntidadPorCodigoEntidadYIdPotObra(codigoEntidad, idPotObra);
		assertNotNull(res);
		assertNull(res.getIdObraEntidad());
		
	}

	@Test
	public void testConsultarPotInstrumentoPorId() throws SpddExceptions {
		Long idPotInstrumento = 1L;
		PotInstrumento potInstrumento = new PotInstrumento();
		when(potInstrumentoServiceRepo.obtener(idPotInstrumento)).thenReturn(potInstrumento);
		when(potInstrumentoMapper.potInstrumentoEntityToDTO(potInstrumento)).thenReturn(new PotInstrumentoDTO());
		PotInstrumentoDTO res= sessionConsultaIP.consultarPotInstrumentoPorId(idPotInstrumento);
		assertNotNull(res);
	}

	@Test
	public void testConsultarPotInstrumentoPorCodigoYIdPot() throws SpddExceptions {
		Long codigo = 1L;;
		Long idPot = 1L;
		PotInstrumento potInstrumento = new PotInstrumento();
		PotInstrumentoDTO potInstrumentoDTO = new PotInstrumentoDTO();
		potInstrumentoDTO.setIdInstrumentoPot(1L);
		when(potInstrumentoServiceRepo.obtenerPorCodigoYIdPot(codigo, idPot)).thenReturn(potInstrumento);
		when(potInstrumentoMapper.potInstrumentoEntityToDTO(potInstrumento)).thenReturn(potInstrumentoDTO);
		PotInstrumentoDTO res = sessionConsultaIP.consultarPotInstrumentoPorCodigoYIdPot(codigo, idPot);
		assertNotNull(res);
		assertNotNull(res.getIdInstrumentoPot());
		when(potInstrumentoServiceRepo.obtenerPorCodigoYIdPot(codigo, idPot)).thenReturn(null);
		res = sessionConsultaIP.consultarPotInstrumentoPorCodigoYIdPot(codigo, idPot);
		assertNotNull(res);
		assertNull(res.getIdInstrumentoPot());
	}

	@Test
	public void testConsultarTodosPotInstrumentoFiltrado() throws SpddExceptions {
		PotInstrumentoDTO potInstrumentoDTO = new PotInstrumentoDTO();
		Long pagina = 0L;
		Integer tamanioPagina = Integer.MAX_VALUE;
		FiltroDTO filtro = new FiltroDTO();
		filtro.setContador(100L);
		List<PotInstrumento> lista = new ArrayList<>();
		filtro.setResultadoConsulta(lista);
		when(potInstrumentoServiceRepo.obtenerTodosFiltrado(potInstrumentoDTO, pagina,tamanioPagina)).thenReturn(filtro);
		GenericoDTO res = sessionConsultaIP.consultarTodosPotInstrumentoFiltrado(potInstrumentoDTO);
		assertNotNull(res);
	}

	@Test
	public void testObtenerIndicadorMetaProductoPorId() throws SpddExceptions {
		PddMpIndicador indicador = new PddMpIndicador();
		when(pddMpIndicadorServiceRepo.obtener(1L)).thenReturn(indicador);
		when(pddMpIndicadorMapper.mpIndicadorEntityToDTO(indicador)).thenReturn(new PddMpIndicadorDTO());
		assertThat(sessionConsultaIP.obtenerIndicadorMetaProductoPorId(1L)).isNotNull();
	}

	@Test
	public void testValidarMetaProductoIndicador() throws SpddExceptions {
		PddMpIndicadorDTO peticion = new PddMpIndicadorDTO();
		PddMpIndicador indicador = new PddMpIndicador();
		when(pddMpIndicadorServiceRepo.validarMetaProductoIndicador(peticion.getIdMetaProducto(), peticion.getIdIndicador())).thenReturn(indicador);
		when(pddMpIndicadorMapper.mpIndicadorEntityToDTO(indicador)).thenReturn(peticion);
		assertThat(sessionConsultaIP.validarMetaProductoIndicador(peticion)).isNotNull();

	}

	@Test
	public void testConsultarPdlNivelAtributoPorDenominacionYIdPdlNivel() throws SpddExceptions {
		when(pdlNivelAtributoServiceRepo.obtenerPorDenominacionYIdPdlNivel("den", 1L)).thenReturn(new PdlNivelAtributo());
		when(pdlNivelAtributoMapper.pdlNivelAtributoEntityToDTO(new PdlNivelAtributo())).thenReturn(new PdlNivelAtributoDTO());
		assertNotNull(sessionConsultaIP.consultarPdlNivelAtributoPorDenominacionYIdPdlNivel("den", 1L));
	}

	@Test
	public void testObtenerTodosRangoPonderacion() throws SpddExceptions {
		when(pddRangoPonderacionServiceRepo.obtenerTodos()).thenReturn(new ArrayList<PddRangoPonderacion>());
		when(pddRangoPonderacionMapper.pddRangoPonderacionEntitiesToDTO(new ArrayList<PddRangoPonderacion>())).thenReturn(new ArrayList<PddRangoPonderacionDTO>());
		assertNotNull(sessionConsultaIP.obtenerTodosRangoPonderacion());
	}

	@Test
	public void testObtenerPddRangoPonderacionPorId() throws SpddExceptions {
		when(pddRangoPonderacionServiceRepo.obtener(1L)).thenReturn(new PddRangoPonderacion());
		when(pddRangoPonderacionMapper.ppdRangoPonderacionEntityToDTO(new PddRangoPonderacion())).thenReturn(new PddRangoPonderacionDTO());
		assertNotNull(sessionConsultaIP.obtenerPddRangoPonderacionPorId(1L));
	}

	@Test
	public void testObtenerPddRangoPonderacionPorIdPdd() throws SpddExceptions {
		when(pddRangoPonderacionServiceRepo.obtenerPddRangoPonderacionPorIdPdd(1L)).thenReturn(new ArrayList<PddRangoPonderacion>());
		when(pddRangoPonderacionMapper.pddRangoPonderacionEntitiesToDTO(new ArrayList<PddRangoPonderacion>())).thenReturn(new ArrayList<PddRangoPonderacionDTO>());
		assertNotNull(sessionConsultaIP.obtenerPddRangoPonderacionPorIdPdd(1L));
	}

	@Test
	public void testConsultarPddNivelAtributoPorId() throws SpddExceptions {
		Long idAtributo = 1L;
		PddNivelAtributo pddNivelAtributo = new PddNivelAtributo();
		when(pddNivelAtributoServiceRepo.obtener(idAtributo)).thenReturn(pddNivelAtributo);
		when(pddNivelAtributoMapper.pddNivelAtributoEntityToDTO(pddNivelAtributo)).thenReturn(new PddNivelAtributoDTO());
		PddNivelAtributoDTO res = sessionConsultaIP.consultarPddNivelAtributoPorId(idAtributo);
		assertNotNull(res);
	}


}
