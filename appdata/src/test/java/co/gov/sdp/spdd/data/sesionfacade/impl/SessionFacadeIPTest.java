package co.gov.sdp.spdd.data.sesionfacade.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.dto.PddDTO;
import co.gov.sdp.nhspdd.common.dto.PddNivelAtributoDTO;
import co.gov.sdp.nhspdd.common.dto.PddNivelDTO;
import co.gov.sdp.nhspdd.common.dto.PotInstrumentoDTO;
import co.gov.sdp.nhspdd.common.dto.PotObraDTO;
import co.gov.sdp.nhspdd.common.dto.ip.ArbolCompromisoDTO;
import co.gov.sdp.nhspdd.common.dto.ip.CompromisoEstrategicoDTO;
import co.gov.sdp.nhspdd.common.dto.ip.HisVPddCompromisoDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddCompetenciaAsociadaDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddCompromisoDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddCompromisoEspecificoDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddIndicadorDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddMetaDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddMetaResultadoDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddObraConcretaDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddPrbIndicadorDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddPrbPoblacionDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddPrbValoracionDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddProblematicaDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddRangoPonderacionDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PdlDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PotDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PotObraEntidadDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.spdd.data.model.ip.PotObraEntidad;
import co.gov.sdp.spdd.data.serviciofacade.afs.SessionConsultaAFS;
import co.gov.sdp.spdd.data.serviciofacade.ip.SessionConsultaIP;
import co.gov.sdp.spdd.data.serviciofacade.ip.SessionEliminarIP;
import co.gov.sdp.spdd.data.serviciofacade.ip.SessionModificarIP;
import co.gov.sdp.spdd.data.serviciofacade.ip.SessionRegistroIP;

@RunWith(SpringRunner.class)
public class SessionFacadeIPTest {

	@TestConfiguration
	static class SessionFacadeIPTestContextConfiguration {
		@Bean
		public SessionFacadeIP sessionFacadeIP() {
			return new SessionFacadeIP();
		}
	}

	@Autowired
	SessionFacadeIP sessionFacadeIP;

	@MockBean
	SPDDLogger logger;

	@MockBean
	SessionConsultaIP sessionConsultaIP;

	/**
	 * Inyeccion del servicio Eliminar del modulo IP
	 */
	@MockBean
	SessionEliminarIP sessionEliminarIP;

	/**
	 * Inyeccion del servicio Registrar del modulo IP
	 */
	@MockBean
	SessionRegistroIP sessionRegistroIP;

	/**
	 * Inyeccion del servicio Modificar del modulo IP
	 */
	@MockBean
	SessionModificarIP sessionModificarIP;
	
	@MockBean
	SessionConsultaAFS sessionConsultarAFS;

	@Test
	public void testConsultarCompromisoPorId() throws SpddExceptions {
		when(sessionConsultaIP.consultarCompromisoPorId(1L)).thenReturn(new PddCompromisoDTO());
		assertThat(sessionFacadeIP.consultarCompromisoPorId(1L)).isNotNull();
	}

	@Test
	public void testConsultarCompromisoPorFiltro() throws SpddExceptions {
		when(sessionConsultaIP.consultarCompromisoPorFiltro(new HisVPddCompromisoDTO())).thenReturn(new GenericoDTO());
		assertThat(sessionFacadeIP.consultarCompromisoPorFiltro(new HisVPddCompromisoDTO())).isNotNull();
	}

	@Test
	public void testConsultarCompromisoEstrategicoPorFiltro() throws SpddExceptions {
		when(sessionConsultaIP.consultarCompromisoEstrategicoPorFiltro(new CompromisoEstrategicoDTO())).thenReturn(new GenericoDTO());
		assertThat(sessionFacadeIP.consultarCompromisoEstrategicoPorFiltro(new CompromisoEstrategicoDTO())).isNotNull();
	}

	@Test
	public void testConsultarCompromisoEstrategicoPorID() throws SpddExceptions {
		when(sessionConsultaIP.consultarCompromisoEstrategicoPorId(1L)).thenReturn(new CompromisoEstrategicoDTO());
		assertThat(sessionFacadeIP.consultarCompromisoEstrategicoPorID(1L)).isNotNull();
	}

	@Test
	public void testConsultarCompromisoEstrategicoPorIdTematicaYIdCompromisoEstrategico() throws SpddExceptions {
		when(sessionConsultaIP.consultarCompromisoEstrategicoPorIdTematicaYIdCompromisoEstrategico(1L, 1L)).thenReturn(new CompromisoEstrategicoDTO());
		assertThat(sessionFacadeIP.consultarCompromisoEstrategicoPorIdTematicaYIdCompromisoEstrategico(1L, 1L)).isNotNull();
	}

	@Test
	public void testConsultarMetasCompromisoEstrategico() throws SpddExceptions {
		when(sessionConsultaIP.consultarMetasCompromisoEstrategico(1L)).thenReturn(new GenericoDTO());
		assertThat(sessionFacadeIP.consultarMetasCompromisoEstrategico(1L)).isNotNull();
	}

	@Test
	public void testConsultarObraConcretaPorId() throws SpddExceptions {
		when(sessionConsultaIP.consultarObraConcretaPorId(1L)).thenReturn(new PddObraConcretaDTO());
		assertThat(sessionFacadeIP.consultarObraConcretaPorId(1L)).isNotNull();
	}

	@Test
	public void testConsultarObrasConcretasPorMetas() throws SpddExceptions {
		when(sessionConsultaIP.consultarObrasConcretasPorMetas(1L)).thenReturn(new GenericoDTO());
		assertThat(sessionFacadeIP.consultarObrasConcretasPorMetas(1L)).isNotNull();
	}

	@Test
	public void testConsultarPddCompetenciaAsociadaPorId() throws SpddExceptions {
		when(sessionConsultaIP.consultarPddCompetenciaAsociadaPorId(1L)).thenReturn(new PddCompetenciaAsociadaDTO());
		assertThat(sessionFacadeIP.consultarPddCompetenciaAsociadaPorId(1L)).isNotNull();
	}

	@Test
	public void testConsultarPddCompetenciaAsociadaPorIdSector() throws SpddExceptions {
		when(sessionConsultaIP.consultarPddCompetenciaAsociadaPorIdSector(1L)).thenReturn(new GenericoDTO());
		assertThat(sessionFacadeIP.consultarPddCompetenciaAsociadaPorIdSector(1l)).isNotNull();
	}

	@Test
	public void testConsultarPddCompetenciaAsociadaPorIdSectorYIdCompetencia() throws SpddExceptions {
		when(sessionConsultaIP.consultarPddCompetenciaAsociadaPorIdSectorYIdCompetencia(1L, 1L)).thenReturn(new PddCompetenciaAsociadaDTO());
		assertThat(sessionFacadeIP.consultarPddCompetenciaAsociadaPorIdSectorYIdCompetencia(1L, 1L)).isNotNull();
	}

	@Test
	public void testConsultarPddCompromisosPorFiltro() throws SpddExceptions {
		when(sessionConsultaIP.consultarPddCompromisosPorFiltro(new PddCompromisoDTO())).thenReturn(new GenericoDTO());
		assertThat(sessionFacadeIP.consultarPddCompromisosPorFiltro(new PddCompromisoDTO())).isNotNull();
	}

	@Test
	public void testConsultarPddCompromisoPorIdEstrategicoYIdPlanDesarrollo() throws SpddExceptions {
		when(sessionConsultaIP.consultarPddCompromisoPorIdEstrategicoYIdPlanDesarrollo(1L, 1L)).thenReturn(new PddCompromisoDTO());
		assertThat(sessionFacadeIP.consultarPddCompromisoPorIdEstrategicoYIdPlanDesarrollo(1L, 1L)).isNotNull();
	}

	@Test
	public void testConsultarPddCompromisoPorIdPlanDesarrollo() throws SpddExceptions {
		when(sessionConsultaIP.consultarPddCompromisoPorIdPlanDesarrollo(1L)).thenReturn(new GenericoDTO());
		assertThat(sessionFacadeIP.consultarPddCompromisoPorIdPlanDesarrollo(1L));
	}

	@Test
	public void testConsultarPddCompromisoEspecificoPorID() throws SpddExceptions {
		when(sessionConsultaIP.consultarPddCompromisoEspecificoPorId(1L)).thenReturn(new PddCompromisoEspecificoDTO());
		assertThat(sessionFacadeIP.consultarPddCompromisoEspecificoPorID(1L)).isNotNull();
	}

	@Test
	public void testConsultarPddCompromisosEspecificosPorIdCompromiso() throws SpddExceptions {
		when(sessionConsultaIP.consultarPddCompromisosEspecificosPorIdCompromiso(1L)).thenReturn(new ArbolCompromisoDTO());
		assertThat(sessionFacadeIP.consultarPddCompromisosEspecificosPorIdCompromiso(1L)).isNotNull();
	}

	@Test
	public void testConsultarPddCompromisoEspecificoPorIdCompromisoYDescripcion() throws SpddExceptions {
		when(sessionConsultaIP.consultarPddCompromisoEspecificoPorIdCompromisoYDescripcion(1L, "desc")).thenReturn(new PddCompromisoEspecificoDTO());
		assertThat(sessionFacadeIP.consultarPddCompromisoEspecificoPorIdCompromisoYDescripcion(1L, "desc")).isNotNull();
	}

	@Test
	public void testConsultarPddIndicadorPorId() throws SpddExceptions {
		when(sessionConsultaIP.consultarPddIndicadorPorId(1L)).thenReturn(new PddIndicadorDTO());
		assertThat(sessionFacadeIP.consultarPddIndicadorPorId(1L)).isNotNull();
	}

	@Test
	public void testConsultarPddsPorEstado() throws SpddExceptions {
		when(sessionConsultaIP.consultarPddsPorEstado(1L)).thenReturn(new GenericoDTO());
		assertThat(sessionFacadeIP.consultarPddsPorEstado(1L)).isNotNull();
	}

	@Test
	public void testConsultarPddPorFiltro() throws SpddExceptions {
		when(sessionConsultaIP.consultarPddPorFiltro(new PddDTO())).thenReturn(new GenericoDTO());
		assertThat(sessionFacadeIP.consultarPddPorFiltro(new PddDTO())).isNotNull();
	}

	@Test
	public void testConsultarPddPorID() throws SpddExceptions {
		when(sessionConsultaIP.consultarPddPorId(1L)).thenReturn(new PddDTO());
		assertThat(sessionFacadeIP.consultarPddPorID(1L)).isNotNull();
	}

	@Test
	public void testConsultarPddMetaPorId() throws SpddExceptions {
		when(sessionConsultaIP.consultarPddMetaPorId(1L)).thenReturn(new PddMetaDTO());
		assertThat(sessionFacadeIP.consultarPddMetaPorId(1L)).isNotNull();
	}

	@Test
	public void testConsultarPddMetaResultadoPorId() throws SpddExceptions {
		when(sessionConsultaIP.consultarPddMetaResultadoPorId(1L)).thenReturn(new PddMetaResultadoDTO());
		assertThat(sessionFacadeIP.consultarPddMetaResultadoPorId(1L)).isNotNull();
	}

	@Test
	public void testConsultarPddNivelPorID() throws SpddExceptions {
		when(sessionConsultaIP.consultarPddNivelPorId(1L)).thenReturn(new PddNivelDTO());
		assertThat(sessionFacadeIP.consultarPddNivelPorID(1L)).isNotNull();
	}

	@Test
	public void testConsultarPddNivelPorIdPlanDesarrollo() throws SpddExceptions {
		when(sessionConsultaIP.consultarPddNivelesPorIdPlanDesarrollo(1L)).thenReturn(new GenericoDTO());
		assertThat(sessionFacadeIP.consultarPddNivelPorIdPlanDesarrollo(1L)).isNotNull();
	}

	@Test
	public void testConsultarPddPrbValoracionPorId() throws SpddExceptions {
		when(sessionConsultaIP.consultarPddPrbValoracionPorId(1L)).thenReturn(new PddPrbValoracionDTO());
		assertThat(sessionFacadeIP.consultarPddPrbValoracionPorId(1L)).isNotNull();
	}

	@Test
	public void testConsultarPddPrbValoracionPorIdProblematicaYMomento() throws SpddExceptions {
		when(sessionConsultaIP.consultarPddPrbValoracionPorIdProblematicaYMomento(1L, 1L)).thenReturn(new PddPrbValoracionDTO());
		assertThat(sessionFacadeIP.consultarPddPrbValoracionPorIdProblematicaYMomento(1L, 1L)).isNotNull();
	}

	@Test
	public void testConsultarPddProblematicaPorId() throws SpddExceptions {
		when(sessionConsultaIP.consultarPddProblematicaPorId(1L)).thenReturn(new PddProblematicaDTO());
		assertThat(sessionFacadeIP.consultarPddProblematicaPorId(1L)).isNotNull();
	}

	@Test
	public void testConsultarPddProblematicaPorCompromiso() throws SpddExceptions {
		when(sessionConsultaIP.consultarPddProblematicaPorCompromiso(new PddProblematicaDTO())).thenReturn(new GenericoDTO());
		assertThat(sessionFacadeIP.consultarPddProblematicaPorCompromiso(new PddProblematicaDTO())).isNotNull();
	}

	@Test
	public void testConsultarPlanDesarrolloLocal() throws SpddExceptions {
		when(sessionConsultaIP.consultarPlanDesarrolloLocalPorId(1L)).thenReturn(new PdlDTO());
		assertThat(sessionFacadeIP.consultarPlanDesarrolloLocalPorId(1L)).isNotNull();
	}

	@Test
	public void testConsultarPrbPoblacionPorId() throws SpddExceptions {
		when(sessionConsultaIP.consultarPrbPolacionPorId(1L)).thenReturn(new PddPrbPoblacionDTO());
		assertThat(sessionFacadeIP.consultarPrbPoblacionPorId(1L)).isNotNull();
	}

	@Test
	public void testConsultarPrbIndicadorPorId() throws SpddExceptions {
		when(sessionConsultaIP.consultarPrbPolacionPorId(1L)).thenReturn(new PddPrbPoblacionDTO());
		assertThat(sessionFacadeIP.consultarPrbPoblacionPorId(1L)).isNotNull();
	}

	@Test
	public void testConsultarPddIndicadorTodos() throws SpddExceptions {
		when(sessionConsultaIP.consultarTodosPddIndicador()).thenReturn(new GenericoDTO());
		assertThat(sessionFacadeIP.consultarPddIndicadorTodos()).isNotNull();
	}

	@Test
	public void testConsultarPrbIndicadorPorProblematica() throws SpddExceptions {
		when(sessionConsultaIP.consultarPddPrbIndicadorPorProblematica(1L)).thenReturn(new GenericoDTO());
		assertThat(sessionFacadeIP.consultarPrbIndicadorPorProblematica(1L));
	}

	@Test
	public void testEliminarPrbIndicador() throws SpddExceptions {
		Long idPrbIndicador = 1L;
		PddPrbIndicadorDTO prbIndicadorDTO = new PddPrbIndicadorDTO(); 
		prbIndicadorDTO.setIdIndicador(idPrbIndicador);
		List<PddPrbIndicadorDTO> listaDTO = new ArrayList<PddPrbIndicadorDTO>();
		listaDTO.add(prbIndicadorDTO);
		doAnswer((argumentos) -> {
	        listaDTO.remove(0);
	        return null;
	    }).when(sessionEliminarIP).eliminarPrbIndicador(1L);
		sessionFacadeIP.eliminarPrbIndicador(idPrbIndicador);
		assertEquals(0, listaDTO.size());
	}

	@Test
	public void testEelimanrPDDMeta() throws SpddExceptions {
		Long idPddMeta = 1L;
		PddMetaDTO pddMetaDTO = new PddMetaDTO();
		pddMetaDTO.setIdMeta(idPddMeta);
		List<PddMetaDTO> listaDTO = new ArrayList<PddMetaDTO>();
		listaDTO.add(pddMetaDTO);
		doAnswer((argumentos) -> {
	        listaDTO.remove(0);
	        return null;
	    }).when(sessionEliminarIP).eliminarPddMeta(1L);
		sessionFacadeIP.elimanrPDDMeta(idPddMeta);
		assertEquals(0, listaDTO.size());
	}

	@Test
	public void testConsultarPddMetaResultadoPorIDProblematicaIndicador() throws SpddExceptions {
		when(sessionConsultaIP.consultarPddMetaResultadoPorIDProblematicaIndicador(1L)).thenReturn(new GenericoDTO());
		assertNotNull(sessionFacadeIP.consultarPddMetaResultadoPorIDProblematicaIndicador(1L));
	}

	@Test
	public void testEliminarPddObraConcreta() throws SpddExceptions {
		Long idPddObraConcreta = 1L;
		PddObraConcretaDTO pddObraConcretaDTO = new PddObraConcretaDTO();
		pddObraConcretaDTO.setIdConcreta(idPddObraConcreta);
		List<PddObraConcretaDTO> listaDTO = new ArrayList<PddObraConcretaDTO>();
		listaDTO.add(pddObraConcretaDTO);
		doAnswer((argumentos) -> {
	        listaDTO.remove(0);
	        return null;
	    }).when(sessionEliminarIP).eliminarPddObraConcreta(1L);
		sessionFacadeIP.eliminarPddObraConcreta(idPddObraConcreta);
		assertEquals(0, listaDTO.size());
	}

	@Test
	public void testEliminarPddCompromisoEspecifico() throws SpddExceptions {
		Long idPddCompromisoEspecifico = 1L;
		PddCompromisoEspecificoDTO pddCompromisoEspecificoDTO = new PddCompromisoEspecificoDTO();
		pddCompromisoEspecificoDTO.setIdEspecifico(idPddCompromisoEspecifico);
		List<PddCompromisoEspecificoDTO> listaDTO = new ArrayList<PddCompromisoEspecificoDTO>();
		listaDTO.add(pddCompromisoEspecificoDTO);
		doAnswer((argumentos) -> {
	        listaDTO.remove(0);
	        return null;
	    }).when(sessionEliminarIP).eliminarPddCompromisoEspecifico(1L);
		sessionFacadeIP.eliminarPddCompromisoEspecifico(idPddCompromisoEspecifico);
		assertEquals(0, listaDTO.size());
	}

	@Test
	public void testEliminarPrbPolacion() throws SpddExceptions {
		Long idPrbPoblacion = 1L;
		PddPrbPoblacionDTO prbPoblacionDTO = new PddPrbPoblacionDTO();
		prbPoblacionDTO.setIdPoblacion(idPrbPoblacion);
		List<PddPrbPoblacionDTO> listaDTO = new ArrayList<PddPrbPoblacionDTO>();
		listaDTO.add(prbPoblacionDTO);
		doAnswer((argumentos) -> {
	        listaDTO.remove(0);
	        return null;
	    }).when(sessionEliminarIP).eliminarPrbPoblacion(1L);
		sessionFacadeIP.eliminarPrbPolacion(idPrbPoblacion);
		assertEquals(0, listaDTO.size());
	}

	@Test
	public void testGuardarCompromisoEstrategico() throws SpddExceptions {
//		CompromisoEstrategicoDTO peticion = new CompromisoEstrategicoDTO();
//		when(sessionConsultaIP.consultarCompromisoEstrategicoPorIdTematicaYIdCompromisoEstrategico(1L,1L)).thenReturn(peticion);
//		when(sessionRegistroIP.guardarCompromisoEstrategico(peticion)).thenReturn(peticion);
//		assertThat(sessionFacadeIP.guardarCompromisoEstrategico(peticion)).isNotNull();
	}

	@Test
	public void testGuardarPdd() throws SpddExceptions {
//		PddDTO peticion = new PddDTO();
//		when(sessionRegistroIP.guardarPdd(peticion)).thenReturn(peticion);
//		assertThat(sessionFacadeIP.guardarPdd(peticion)).isNotNull();
	}

	@Test
	public void testGuardarPddCompetenciaAsociada() throws SpddExceptions {
//		PddCompetenciaAsociadaDTO peticion = new PddCompetenciaAsociadaDTO();
//		when(sessionRegistroIP.guardarPddCompetenciaAsociada(peticion)).thenReturn(peticion);
//		assertThat(sessionFacadeIP.guardarPddCompetenciaAsociada(peticion)).isNotNull();
	}

	@Test
	public void testGuardarPddCompromiso() throws SpddExceptions {
//		PddCompromisoDTO peticion = new PddCompromisoDTO();
//		when(sessionRegistroIP.guardadPddCompromiso(peticion)).thenReturn(peticion);
//		assertThat(sessionFacadeIP.guardarPddCompromiso(peticion)).isNotNull();
	}

	@Test
	public void testGuardarPddMeta() throws SpddExceptions {
//		PddMetaDTO peticion = new PddMetaDTO();
//		when(sessionRegistroIP.guardarPddMeta(peticion)).thenReturn(peticion);
//		assertThat(sessionFacadeIP.guardarPddMeta(peticion)).isNotNull();
	}

	@Test
	public void testGuardarPddMetaResultado() throws SpddExceptions {
		PddMetaResultadoDTO peticion = new PddMetaResultadoDTO();
		when(sessionRegistroIP.guardarPddMetaResultado(peticion)).thenReturn(peticion);
		assertThat(sessionFacadeIP.guardarPddMetaResultado(peticion)).isNotNull();
	}

	@Test
	public void testGuardarPddObraConcreta() throws SpddExceptions {
//		PddObraConcretaDTO peticion = new PddObraConcretaDTO();
//		when(sessionRegistroIP.guardarObraConcreta(peticion)).thenReturn(peticion);
//		assertThat(sessionFacadeIP.guardarPddObraConcreta(peticion)).isNotNull();
	}

	@Test
	public void testGuardarPddNivel() throws SpddExceptions {
//		PddNivelDTO peticion = new PddNivelDTO();
//		when(sessionRegistroIP.guardarPddNivel(peticion)).thenReturn(peticion);
//		assertThat(sessionFacadeIP.guardarPddNivel(peticion)).isNotNull();
	}

	@Test
	public void testGuardarPddProblematica() throws SpddExceptions {
		PddProblematicaDTO peticion = new PddProblematicaDTO();
		when(sessionRegistroIP.guardarPddProblematica(peticion)).thenReturn(peticion);
		assertThat(sessionFacadeIP.guardarPddProblematica(peticion)).isNotNull();
	}

	@Test
	public void testGuardarPrbPoblacion() throws SpddExceptions {
//		PddPrbPoblacionDTO peticion = new PddPrbPoblacionDTO();
//		when(sessionRegistroIP.guardarPrbPoblacion(peticion)).thenReturn(peticion);
//		assertThat(sessionFacadeIP.guardarPrbPoblacion(peticion)).isNotNull();
	}

	@Test
	public void testModificarPddNivel() throws SpddExceptions {
//		PddNivelDTO peticion = new PddNivelDTO();
//		when(sessionRegistroIP.guardarPddNivel(peticion)).thenReturn(peticion);
//		assertThat(sessionFacadeIP.modificarPddNivel(peticion)).isNotNull();
	}

	@Test
	public void testGuardarPddCompromisoEspecifico() throws SpddExceptions {
//		PddCompromisoEspecificoDTO peticion = new PddCompromisoEspecificoDTO();
//		when(sessionRegistroIP.guardarPddCompromisoEspecifico(peticion)).thenReturn(peticion);
//		assertThat(sessionFacadeIP.guardarPddCompromisoEspecifico(peticion)).isNotNull();
	}

	@Test
	public void testGuardarPddPrbValoracion() throws SpddExceptions {
//		PddPrbValoracionDTO peticion = new PddPrbValoracionDTO();
//		when(sessionRegistroIP.guardarPddPrbValoracion(peticion)).thenReturn(peticion);
//		assertThat(sessionFacadeIP.guardarPddPrbValoracion(peticion)).isNotNull();
	}

	@Test
	public void testGuardarPrbIndicador() throws SpddExceptions {
//		PddPrbIndicadorDTO peticion = new PddPrbIndicadorDTO();
//		when(sessionRegistroIP.guardarPddPrbIndicador(peticion)).thenReturn(peticion);
//		assertThat(sessionFacadeIP.guardarPrbIndicador(peticion)).isNotNull();
	}

	@Test
	public void testModificarCompromisoEstrategico() throws SpddExceptions {
		CompromisoEstrategicoDTO peticion = new CompromisoEstrategicoDTO();
		when(sessionRegistroIP.guardarCompromisoEstrategico(peticion)).thenReturn(peticion);
		assertThat(sessionFacadeIP.modificarCompromisoEstrategico(peticion)).isNotNull();
	}

	@Test
	public void testModificarMetaDeCompromiso() throws SpddExceptions {
		PddMetaDTO peticion = new PddMetaDTO();
		when(sessionRegistroIP.guardarPddMeta(peticion)).thenReturn(peticion);
		assertThat(sessionFacadeIP.modificarMetaDeCompromiso(peticion)).isNotNull();
	}

	@Test
	public void testModificarObraConcreta() throws SpddExceptions {
		PddObraConcretaDTO peticion = new PddObraConcretaDTO();
		when(sessionRegistroIP.guardarObraConcreta(peticion)).thenReturn(peticion);
		assertThat(sessionFacadeIP.modificarObraConcreta(peticion)).isNotNull();
	}

	@Test
	public void testModificarPdd() throws SpddExceptions {
		PddDTO peticion = new PddDTO();
		when(sessionRegistroIP.guardarPdd(peticion)).thenReturn(peticion);
		assertThat(sessionFacadeIP.modificarPdd(peticion)).isNotNull();
	}

	@Test
	public void testModificarPddCompetenciaAsociada() throws SpddExceptions {
		PddCompetenciaAsociadaDTO peticion = new PddCompetenciaAsociadaDTO();
		when(sessionRegistroIP.guardarPddCompetenciaAsociada(peticion)).thenReturn(peticion);
		assertThat(sessionFacadeIP.modificarPddCompetenciaAsociada(peticion)).isNotNull();
	}

	@Test
	public void testModificarPddCompromisoEspecifico() throws SpddExceptions {
		PddCompromisoEspecificoDTO peticion = new PddCompromisoEspecificoDTO();
		when(sessionRegistroIP.guardarPddCompromisoEspecifico(peticion)).thenReturn(peticion);
		assertThat(sessionFacadeIP.modificarPddCompromisoEspecifico(peticion)).isNotNull();
	}

	@Test
	public void testModificarPddMetaResultado() throws SpddExceptions {
		PddMetaResultadoDTO peticion = new PddMetaResultadoDTO();
		when(sessionRegistroIP.guardarPddMetaResultado(peticion)).thenReturn(peticion);
		assertThat(sessionFacadeIP.modificarPddMetaResultado(peticion)).isNotNull();
	}

	@Test
	public void testModificarPddPrbValoracion() throws SpddExceptions {
		PddPrbValoracionDTO peticion = new PddPrbValoracionDTO();
		when(sessionRegistroIP.guardarPddPrbValoracion(peticion)).thenReturn(peticion);
		assertThat(sessionFacadeIP.modificarPddPrbValoracion(peticion)).isNotNull();
	}

	@Test
	public void testModificarPrbPoblacion() {
		PddPrbPoblacionDTO peticion = new PddPrbPoblacionDTO();
		when(sessionRegistroIP.guardarPrbPoblacion(peticion)).thenReturn(peticion);
		assertThat(sessionFacadeIP.modificarPrbPoblacion(peticion)).isNotNull();
	}

	@Test
	public void testModificarPrbIndicador() throws SpddExceptions {
		PddPrbIndicadorDTO peticion = new PddPrbIndicadorDTO();
		when(sessionRegistroIP.guardarPddPrbIndicador(peticion)).thenReturn(peticion);
		assertThat(sessionFacadeIP.modificarPrbIndicador(peticion)).isNotNull();
	}

	@Test
	public void testValidarMeta() throws SpddExceptions {
//		when(sessionConsultaIP.validarMeta("meta", 1L, 1L)).thenReturn(new PddMetaDTO());
//		assertNotNull(sessionFacadeIP.validarMeta(new PddMetaDTO()));
	}

	@Test
	public void testValidarPddObraConcreta() throws SpddExceptions {
//		when(sessionConsultaIP.validarObra(1L, "obraConcreta")).thenReturn(new PddObraConcretaDTO());
//		assertThat(sessionFacadeIP.validarPddObraConcreta(new PddObraConcretaDTO())).isNotNull();
	}

	@Test
	public void testValidarPrbPoblacion() throws SpddExceptions {
//		when(sessionConsultaIP.validarPrbPoblacion("desc", 1l)).thenReturn(new PddPrbPoblacionDTO());
//		assertThat(sessionFacadeIP.validarPrbPoblacion(new PddPrbPoblacionDTO())).isNotNull();
	}

	@Test
	public void testValidarPddPrbIndicador() throws SpddExceptions {
		when(sessionConsultaIP.validarPrbIndicador(1L, 1L)).thenReturn(new PddPrbIndicadorDTO());
		assertThat(sessionFacadeIP.validarPddPrbIndicador(1L, 1L)).isNotNull();
	}

	@Test
	public void testGuardarPddIndicador() throws SpddExceptions {
		PddIndicadorDTO peticion = new PddIndicadorDTO();
		when(sessionRegistroIP.guardarPddIndicador(peticion)).thenReturn(peticion);
		assertThat(sessionFacadeIP.guardarPddIndicador(peticion)).isNotNull();
	}

	@Test
	public void testModificarPddIndicador() throws SpddExceptions {
		PddIndicadorDTO peticion = new PddIndicadorDTO();
		when(sessionRegistroIP.guardarPddIndicador(peticion)).thenReturn(peticion);
		assertThat(sessionFacadeIP.modificarPddIndicador(peticion)).isNotNull();
	}

	@Test
	public void testConsultarPddMetaResultado() throws SpddExceptions {
		PddMetaResultadoDTO peticion = new PddMetaResultadoDTO();
		when(sessionConsultaIP.consultarPddMetaResultadoPorProyecto(peticion)).thenReturn(new GenericoDTO());
		assertThat(sessionFacadeIP.consultarPddMetaResultado(peticion)).isNotNull();
	}
	
	@Test
	public void testguardarPot() throws SpddExceptions {
		PotDTO peticion = new PotDTO();
		when(sessionRegistroIP.guardarPot(peticion)).thenReturn(peticion);
		assertThat(sessionFacadeIP.guardarPot(peticion)).isNotNull();
	}
	
	@Test
	public void testObtenerPotCodigo() throws SpddExceptions {
		String codigoPot = "Pot1";
		PotDTO peticion = new PotDTO();
		when(sessionConsultaIP.obtenerPorCodigo(codigoPot)).thenReturn(peticion);
		assertThat(sessionFacadeIP.obtenerPotCodigo(codigoPot)).isNotNull();
	}

	@Test
	public void testConsultarTodosPotObraPorIdNivelPotPaginado() throws Exception {
		PotObraDTO peticion = new PotObraDTO();
		when(sessionConsultaIP.consultarTodosPotObraPorIdNivelPotPaginado(peticion)).thenReturn(new GenericoDTO());
		assertThat(sessionFacadeIP.consultarTodosPotObraPorIdNivelPotPaginado(peticion)).isNotNull();
	}
	

	@Test
	public void testConsultarTodosPotObraEntidadPorIdPotObra() throws Exception {
		Long idPotObra = 1L;
		when(sessionConsultaIP.consultarTodosPotObraEntidadPorIdPotObra(idPotObra)).thenReturn(new GenericoDTO());
		GenericoDTO res = sessionFacadeIP.consultarTodosPotObraEntidadPorIdPotObra(idPotObra);
		assertNotNull(res);
	}

	@Test
	public void testGuardarPotObra() throws Exception {
		PotObraDTO potObraDTO = new PotObraDTO();
		potObraDTO.setCodigoPotObra(1L);
		potObraDTO.setIdNivelPot(1L);
		potObraDTO.setIdObraProyPot(1L);
		
		PotObraDTO potObraDTOAux = new PotObraDTO();
		potObraDTOAux.setIdObraProyPot(1L);
		
		when(sessionConsultaIP.consultarPotObraPorCodigoYIdNivelPot(potObraDTO.getCodigoPotObra(), potObraDTO.getIdNivelPot())).thenReturn(new PotObraDTO());
		when(sessionRegistroIP.guardarPotObra(potObraDTO)).thenReturn(potObraDTO);
		PotObraDTO res = sessionFacadeIP.guardarPotObra(potObraDTO);
		assertNotNull(res);
		assertNotNull(res.getIdObraProyPot());
		
		when(sessionConsultaIP.consultarPotObraPorCodigoYIdNivelPot(potObraDTO.getCodigoPotObra(), potObraDTO.getIdNivelPot())).thenReturn(potObraDTOAux);
		res = sessionFacadeIP.guardarPotObra(potObraDTO);
		assertNotNull(res);
		assertNull(res.getIdObraProyPot());
		
	}

	@Test
	public void testGuardarPotObraEntidad() throws Exception {
		PotObraEntidadDTO potObraEntidadDTO = new PotObraEntidadDTO();
		potObraEntidadDTO.setCodigoEntidad("123");
		potObraEntidadDTO.setIdObraProyPot(1L);
		potObraEntidadDTO.setIdObraEntidad(1L);
		
		PotObraEntidadDTO potObraEntidadDTOAux = new PotObraEntidadDTO();
		
		when(sessionConsultaIP.obtenerPotObraEntidadPorCodigoEntidadYIdPotObra(potObraEntidadDTO.getCodigoEntidad(), potObraEntidadDTO.getIdObraProyPot())).thenReturn(potObraEntidadDTOAux);
		when(sessionRegistroIP.guardarPotObraEntidad(potObraEntidadDTO)).thenReturn(potObraEntidadDTO);
		PotObraEntidadDTO res = sessionFacadeIP.guardarPotObraEntidad(potObraEntidadDTO);
		assertNotNull(res);
		assertNotNull(res.getIdObraEntidad());
		
		potObraEntidadDTOAux.setIdObraEntidad(1L);
		res = sessionFacadeIP.guardarPotObraEntidad(potObraEntidadDTO);
		assertNotNull(res);
		assertNull(res.getIdObraEntidad());
	}

	@Test
	public void testConsultarPotObraPorId() throws Exception {
		Long idPotObra = 1L;
		when(sessionConsultaIP.consultarPotObraPorId(idPotObra)).thenReturn(new PotObraDTO());
		PotObraDTO res = sessionFacadeIP.consultarPotObraPorId(idPotObra);
		assertNotNull(res);
	}

	@Test
	public void testConsultarPotObraPorCodigoYIdNivelPot() throws Exception {
		Long codigo = 1L;
		Long idNivelPot= 1L;
		when(sessionConsultaIP.consultarPotObraPorCodigoYIdNivelPot(codigo, idNivelPot)).thenReturn(new PotObraDTO());
		PotObraDTO res = sessionFacadeIP.consultarPotObraPorCodigoYIdNivelPot(codigo, idNivelPot);
		assertNotNull(res);
	}

	@Test
	public void testEliminarTodosPotObraEntidadPorIdPotObra() throws Exception {
		Long idPotObra = 1L;
		
		PotObraEntidad potObraEntidad = new PotObraEntidad();
		List<PotObraEntidad> listaPotObraEntidad = new ArrayList<PotObraEntidad>();
		listaPotObraEntidad.add(potObraEntidad);
		
		doAnswer((argumentos) -> {
	        listaPotObraEntidad.clear();
	        return null;
	    }).when(sessionEliminarIP).eliminarTodosPotObraEntidadPorIdPotObra(idPotObra);
		sessionFacadeIP.eliminarTodosPotObraEntidadPorIdPotObra(idPotObra);
		assertThat(listaPotObraEntidad.isEmpty()).isTrue();
	}

	@Test
	public void testModificarPotObra() throws Exception {
		PotObraDTO potObraDTO = new PotObraDTO();
		when(sessionRegistroIP.guardarPotObra(potObraDTO)).thenReturn(new PotObraDTO());
		PotObraDTO res = sessionFacadeIP.modificarPotObra(potObraDTO);
		assertNotNull(res);
	}

	@Test
	public void testConsultarPotInstrumentoPorId() throws Exception {
		Long idPotInstrumento = 1L;
		when(sessionConsultaIP.consultarPotInstrumentoPorId(idPotInstrumento)).thenReturn(new PotInstrumentoDTO());
		PotInstrumentoDTO res = sessionFacadeIP.consultarPotInstrumentoPorId(idPotInstrumento);
		assertNotNull(res);
	}

	@Test
	public void testConsultarPotInstrumentoPorCodigoYIdPot() throws Exception {
		Long codigo = 1L;;
		Long idPot = 1L;
		
		when(sessionConsultaIP.consultarPotInstrumentoPorCodigoYIdPot(codigo, idPot)).thenReturn(new PotInstrumentoDTO());
		PotInstrumentoDTO res= sessionFacadeIP.consultarPotInstrumentoPorCodigoYIdPot(codigo, idPot);
		assertNotNull(res);
	}

	@Test
	public void testModificarPotInstrumento() throws Exception {
		PotInstrumentoDTO potInstrumentoDTO= new PotInstrumentoDTO();
		
		when(sessionRegistroIP.guardarPotInstrumento(potInstrumentoDTO)).thenReturn(new PotInstrumentoDTO());
		PotInstrumentoDTO res = sessionFacadeIP.modificarPotInstrumento(potInstrumentoDTO);
		assertNotNull(res);
	}

	@Test
	public void testGuardarPotInstrumento() throws Exception {
		PotInstrumentoDTO potInstrumentoDTO = new PotInstrumentoDTO();
		potInstrumentoDTO.setCodigoPotInstrumento(1L);
		potInstrumentoDTO.setIdPot(1L);
		potInstrumentoDTO.setIdInstrumentoPot(1L);
		
		PotInstrumentoDTO potInstrumentoDTOAux = new PotInstrumentoDTO();
		
		when(sessionConsultaIP.consultarPotInstrumentoPorCodigoYIdPot(potInstrumentoDTO.getCodigoPotInstrumento(), potInstrumentoDTO.getIdPot())).thenReturn(potInstrumentoDTOAux);
		when(sessionRegistroIP.guardarPotInstrumento(potInstrumentoDTO)).thenReturn(potInstrumentoDTO);
		PotInstrumentoDTO res =  sessionFacadeIP.guardarPotInstrumento(potInstrumentoDTO);
		assertNotNull(res);
		assertNotNull(res.getIdInstrumentoPot());
		
		potInstrumentoDTOAux.setIdInstrumentoPot(1L);
		when(sessionConsultaIP.consultarPotInstrumentoPorCodigoYIdPot(potInstrumentoDTO.getCodigoPotInstrumento(), potInstrumentoDTO.getIdPot())).thenReturn(potInstrumentoDTOAux);
		res =  sessionFacadeIP.guardarPotInstrumento(potInstrumentoDTO);
		assertNotNull(res);
		assertNull(res.getIdInstrumentoPot());
	}

	@Test
	public void testConsultarTodosPotInstrumentoFiltrado() throws Exception {
		PotInstrumentoDTO potInstrumentoDTO = new PotInstrumentoDTO();
		
		when(sessionConsultaIP.consultarTodosPotInstrumentoFiltrado(potInstrumentoDTO)).thenReturn(new GenericoDTO());
		GenericoDTO res = sessionFacadeIP.consultarTodosPotInstrumentoFiltrado(potInstrumentoDTO);
		assertNotNull(res);
	}

	@Test
	public void testObtenerTodosRangoPonderacion() throws SpddExceptions {
		when(sessionConsultaIP.obtenerTodosRangoPonderacion()).thenReturn(new GenericoDTO());
		assertNotNull(sessionFacadeIP.obtenerTodosRangoPonderacion());
	}

	@Test
	public void testObtenerPddRangoPonderacionPorId() throws SpddExceptions {
		when(sessionConsultaIP.obtenerPddRangoPonderacionPorId(1L)).thenReturn(new PddRangoPonderacionDTO());
		assertNotNull(sessionFacadeIP.obtenerPddRangoPonderacionPorId(1L));
	}

	@Test
	public void testGuardarPddRangoPonderacion() throws SpddExceptions {
		when(sessionRegistroIP.guardarPddRangoPonderacion(new PddRangoPonderacionDTO())).thenReturn(new PddRangoPonderacionDTO());
		assertNotNull(sessionFacadeIP.guardarPddRangoPonderacion(new PddRangoPonderacionDTO()));
	}

	@Test
	public void testModificarPddRangoPonderacion() throws SpddExceptions {
		when(sessionRegistroIP.guardarPddRangoPonderacion(new PddRangoPonderacionDTO())).thenReturn(new PddRangoPonderacionDTO());
		assertNotNull(sessionFacadeIP.modificarPddRangoPonderacion(new PddRangoPonderacionDTO()));
	}

	@Test
	public void testObtenerPddRangoPonderacionPorIdPdd() throws SpddExceptions {
		when(sessionConsultaIP.obtenerPddRangoPonderacionPorIdPdd(1L)).thenReturn(new GenericoDTO());
		assertNotNull(sessionFacadeIP.obtenerPddRangoPonderacionPorIdPdd(1L));
	}

	@Test
	public void testConsultarPddNivelAtributoPorId() throws Exception {
		Long idAtributo = 1L;
		
		when(sessionConsultaIP.consultarPddNivelAtributoPorId(idAtributo)).thenReturn(new PddNivelAtributoDTO());
		PddNivelAtributoDTO res = sessionFacadeIP.consultarPddNivelAtributoPorId(idAtributo);
		assertNotNull(res);
	}


}
