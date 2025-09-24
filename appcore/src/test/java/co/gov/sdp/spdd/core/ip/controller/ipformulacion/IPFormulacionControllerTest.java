package co.gov.sdp.spdd.core.ip.controller.ipformulacion;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.JsonProcessingException;

import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.dto.PddDTO;
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
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.spdd.core.ip.iservice.ipformulacion.IIPFormulacionConsultarService;
import co.gov.sdp.spdd.core.ip.iservice.ipformulacion.IIPFormulacionEliminarService;
import co.gov.sdp.spdd.core.ip.iservice.ipformulacion.IIPFormulacionModificarService;
import co.gov.sdp.spdd.core.ip.iservice.ipformulacion.IIPFormulacionRegistrarService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { IPFormulacionController.class })
class IPFormulacionControllerTest {
	
	@Autowired
	IPFormulacionController controller;
	
	/**
	 * Inyeccion del Service de registro
	 */
	@MockBean
	IIPFormulacionRegistrarService ipFormulacionRegistrarService;

	/**
	 * Inyeccion del Service de consulta
	 */
	@MockBean
	IIPFormulacionConsultarService ipFormulacionConsultarService;

	/**
	 * Inyeccion del Service de modificar
	 */
	@MockBean
	IIPFormulacionModificarService ipFormulacionModificarService;

	/**
	 * Inyeccion del Service de eliminar
	 */
	@MockBean
	IIPFormulacionEliminarService ipFormulacionEliminarService;

	/**
	 * Logger
	 */
	@MockBean
	SPDDLogger spddLogger;

	@Test
	void testObtenerHisVPddCompromisoTodos() throws SpddExceptions {
		HisVPddCompromisoDTO peticion = new HisVPddCompromisoDTO();
		when(ipFormulacionConsultarService.obtenerPaginado(peticion)).thenReturn(new GenericoDTO());
		assertNotNull(controller.obtenerHisVPddCompromisoTodos(peticion));
		when(ipFormulacionConsultarService.obtenerPaginado(peticion)).thenThrow(SpddExceptions.class);
		assertNotNull(controller.obtenerHisVPddCompromisoTodos(peticion));
		
	}

	@Test
	void testRegistrarModificarCompetenciaAsociada() throws SpddExceptions {
		PddCompetenciaAsociadaDTO peticion = new PddCompetenciaAsociadaDTO();
		when(ipFormulacionRegistrarService.registrarPddCompetenciaAsociada(peticion)).thenReturn(new PddCompetenciaAsociadaDTO());
		assertNotNull(controller.registrarModificarCompetenciaAsociada(peticion));
		when(ipFormulacionRegistrarService.registrarPddCompetenciaAsociada(peticion)).thenThrow(SpddExceptions.class);
		assertNotNull(controller.registrarModificarCompetenciaAsociada(peticion));
		when(ipFormulacionModificarService.modificarPddCompetenciaAsociada(peticion)).thenReturn(new PddCompetenciaAsociadaDTO());
		assertNotNull(controller.registrarModificarCompetenciaAsociada(peticion));
		when(ipFormulacionModificarService.modificarPddCompetenciaAsociada(peticion)).thenThrow(SpddExceptions.class);
		assertNotNull(controller.registrarModificarCompetenciaAsociada(peticion));
		
	}

	@Test
	void testObtenerCompromisoEstrategicoTodos() throws SpddExceptions {
		CompromisoEstrategicoDTO peticion = new CompromisoEstrategicoDTO();
		when(ipFormulacionConsultarService.compromisoEstrategicoObtenerPaginado(peticion)).thenReturn(new GenericoDTO());
		assertNotNull(controller.obtenerCompromisoEstrategicoTodos(peticion));
		when(ipFormulacionConsultarService.compromisoEstrategicoObtenerPaginado(peticion)).thenThrow(SpddExceptions.class);
		assertNotNull(controller.obtenerCompromisoEstrategicoTodos(peticion));
	}

	@Test
	void testConsultarMetasCompromistoEstrategico() throws SpddExceptions {
		when(ipFormulacionConsultarService.consultarMetasCompromistoEstrategico(1L)).thenReturn(new GenericoDTO());
		assertNotNull(controller.consultarMetasCompromistoEstrategico(1L));
		when(ipFormulacionConsultarService.consultarMetasCompromistoEstrategico(1L)).thenThrow(SpddExceptions.class);
		assertNotNull(controller.consultarMetasCompromistoEstrategico(1L));
	}

	@Test
	void testConsultarPddCompetenciaAsociadaPorIdSector() throws SpddExceptions {
		when(ipFormulacionConsultarService.consultarPddCompetenciaAsociadaPorIdSector(1L)).thenReturn(new GenericoDTO());
		assertNotNull(controller.consultarPddCompetenciaAsociadaPorIdSector(1L));
		when(ipFormulacionConsultarService.consultarPddCompetenciaAsociadaPorIdSector(1L)).thenThrow(SpddExceptions.class);
		assertNotNull(controller.consultarPddCompetenciaAsociadaPorIdSector(1L));
	}

	@Test
	void testRegistrarCompromisoEstrategico() throws SpddExceptions {
		CompromisoEstrategicoDTO peticion = new CompromisoEstrategicoDTO();
		when(ipFormulacionRegistrarService.registrarCompromisoEstrategico(peticion)).thenReturn(new CompromisoEstrategicoDTO());
		assertNotNull(controller.registrarCompromisoEstrategico(peticion));
		when(ipFormulacionRegistrarService.registrarCompromisoEstrategico(peticion)).thenThrow(SpddExceptions.class);
		assertNotNull(controller.registrarCompromisoEstrategico(peticion));
	}

	@Test
	void testModificarCompromisoEstrategico() throws SpddExceptions {
		CompromisoEstrategicoDTO peticion = new CompromisoEstrategicoDTO();
		when(ipFormulacionModificarService.modificarCompromisoEstrategico(peticion)).thenReturn(new CompromisoEstrategicoDTO());
		assertNotNull(controller.modificarCompromisoEstrategico(peticion));
		when(ipFormulacionModificarService.modificarCompromisoEstrategico(peticion)).thenThrow(SpddExceptions.class);
		assertNotNull(controller.modificarCompromisoEstrategico(peticion));
	}

	@Test
	void testConsultarPddCompromisoEspecificoPorIdCompromiso() throws SpddExceptions {
		when(ipFormulacionConsultarService.consultarPddCompromisoEspecificoPorIdCompromiso(1L)).thenReturn(new ArbolCompromisoDTO());
		assertNotNull(controller.consultarPddCompromisoEspecificoPorIdCompromiso(1L));
		when(ipFormulacionConsultarService.consultarPddCompromisoEspecificoPorIdCompromiso(1L)).thenThrow(SpddExceptions.class);
		assertNotNull(controller.consultarPddCompromisoEspecificoPorIdCompromiso(1L));
	}

	@Test
	void testRegistrarPddCompromisoEspecifico() throws SpddExceptions {
		PddCompromisoEspecificoDTO peticion = new PddCompromisoEspecificoDTO();
		when(ipFormulacionRegistrarService.registrarPddCompromisoEspecifico(peticion)).thenReturn(new PddCompromisoEspecificoDTO());
		assertNotNull(controller.registrarPddCompromisoEspecifico(peticion));
		when(ipFormulacionRegistrarService.registrarPddCompromisoEspecifico(peticion)).thenThrow(SpddExceptions.class);
		assertNotNull(controller.registrarPddCompromisoEspecifico(peticion));
	}

	@Test
	void testModificarPddCompromisoEspecifico() throws SpddExceptions {
		PddCompromisoEspecificoDTO peticion = new PddCompromisoEspecificoDTO();
		when(ipFormulacionModificarService.modificarPddCompromisoEspecifico(peticion)).thenReturn(new PddCompromisoEspecificoDTO());
		assertNotNull(controller.modificarPddCompromisoEspecifico(peticion));
		when(ipFormulacionModificarService.modificarPddCompromisoEspecifico(peticion)).thenThrow(SpddExceptions.class);
		assertNotNull(controller.modificarPddCompromisoEspecifico(peticion));
	}

	@Test
	void testEliminarPddCompromisoEspecifico() throws SpddExceptions {
		when(ipFormulacionEliminarService.eliminarPddCompromisoEspecifico(1L)).thenReturn(new PddCompromisoEspecificoDTO());
		assertNotNull(controller.eliminarPddCompromisoEspecifico(1L));
		when(ipFormulacionEliminarService.eliminarPddCompromisoEspecifico(1L)).thenThrow(SpddExceptions.class);
		assertNotNull(controller.eliminarPddCompromisoEspecifico(1L));
	}

	@Test
	void testConsultarPddCompromisosPorFiltro() throws SpddExceptions {
		PddCompromisoDTO peticion = new PddCompromisoDTO();
		when(ipFormulacionConsultarService.consultarPddCompromisosPorFiltro(peticion)).thenReturn(new GenericoDTO());
		assertNotNull(controller.consultarPddCompromisosPorFiltro(peticion));
		when(ipFormulacionConsultarService.consultarPddCompromisosPorFiltro(peticion)).thenThrow(SpddExceptions.class);
		assertNotNull(controller.consultarPddCompromisosPorFiltro(peticion));
	}

	@Test
	void testConsultarPddCompromisoPorIdPlanDesarrollo() throws SpddExceptions {
		when(ipFormulacionConsultarService.consultarPddCompromisoPorIdPlanDesarrollo(1L)).thenReturn(new GenericoDTO());
		assertNotNull(controller.consultarPddCompromisoPorIdPlanDesarrollo(1L));
		when(ipFormulacionConsultarService.consultarPddCompromisoPorIdPlanDesarrollo(1L)).thenThrow(SpddExceptions.class);
		assertNotNull(controller.consultarPddCompromisoPorIdPlanDesarrollo(1L));
	}

	@Test
	void testRegistrarPddCompromiso() throws SpddExceptions {
		PddCompromisoDTO peticion = new PddCompromisoDTO();
		when(ipFormulacionRegistrarService.registrarPddCompromiso(peticion)).thenReturn(new PddCompromisoDTO());
		assertNotNull(controller.registrarPddCompromiso(peticion));
		when(ipFormulacionRegistrarService.registrarPddCompromiso(peticion)).thenThrow(SpddExceptions.class);
		assertNotNull(controller.registrarPddCompromiso(peticion));
	}

	@Test
	void testModificarPddCompromiso() throws SpddExceptions {
		PddCompromisoDTO peticion = new PddCompromisoDTO();
		when(ipFormulacionModificarService.modificarPddCompromiso(peticion)).thenReturn(peticion);
		assertNotNull(controller.modificarPddCompromiso(peticion));
		when(ipFormulacionModificarService.modificarPddCompromiso(peticion)).thenThrow(SpddExceptions.class);
		assertNotNull(controller.modificarPddCompromiso(peticion));
	}

	@Test
	void testConsultarPddPrbValoracionPorIdProblematicaYMomento() throws SpddExceptions {
		PddPrbValoracionDTO peticion = new PddPrbValoracionDTO();
		when(ipFormulacionConsultarService.consultarPddPrbValoracionPorIdProblematicaYMomento(peticion)).thenReturn(new PddPrbValoracionDTO());
		assertNotNull(controller.consultarPddPrbValoracionPorIdProblematicaYMomento(peticion));
		when(ipFormulacionConsultarService.consultarPddPrbValoracionPorIdProblematicaYMomento(peticion)).thenThrow(SpddExceptions.class);
		assertNotNull(controller.consultarPddPrbValoracionPorIdProblematicaYMomento(peticion));
	}

	@Test
	void testRegistrarMetaPorCompromiso() throws SpddExceptions, JsonProcessingException {
		PddMetaDTO peticion = new PddMetaDTO();
		when(ipFormulacionRegistrarService.registrarMetaPorCompromiso(peticion)).thenReturn(new PddMetaDTO());
		assertNotNull(controller.registrarMetaPorCompromiso(peticion));
		when(ipFormulacionRegistrarService.registrarMetaPorCompromiso(peticion)).thenThrow(SpddExceptions.class);
		assertNotNull(controller.registrarMetaPorCompromiso(peticion));
	}

	@Test
	void testRegistrarObraConcretaPorMeta() throws SpddExceptions {
		PddObraConcretaDTO peticion = new PddObraConcretaDTO();
		when(ipFormulacionRegistrarService.registrarObraConcretaPorMeta(peticion)).thenReturn(new PddObraConcretaDTO());
		assertNotNull(controller.registrarObraConcretaPorMeta(peticion));
		when(ipFormulacionRegistrarService.registrarObraConcretaPorMeta(peticion)).thenThrow(SpddExceptions.class);
		assertNotNull(controller.registrarObraConcretaPorMeta(peticion));
	}

	@Test
	void testEliminarPddObraConcreta() throws SpddExceptions {
		when(ipFormulacionEliminarService.eliminarPddObraConcreta(1L)).thenReturn(new PddObraConcretaDTO());
		assertNotNull(controller.eliminarPddObraConcreta(1L));
		when(ipFormulacionEliminarService.eliminarPddObraConcreta(1L)).thenThrow(SpddExceptions.class);
		assertNotNull(controller.eliminarPddObraConcreta(1L));
		
	}

	@Test
	void testEliminarPddMeta() throws SpddExceptions {
		when(ipFormulacionEliminarService.eliminarPddMeta(1L)).thenReturn(new PddMetaDTO());
		assertNotNull(controller.eliminarPddMeta(1L));
		when(ipFormulacionEliminarService.eliminarPddMeta(1L)).thenThrow(SpddExceptions.class);
		assertNotNull(controller.eliminarPddMeta(1L));
	}

	@Test
	void testModificarObraConcreta() throws SpddExceptions {
		PddObraConcretaDTO peticion = new PddObraConcretaDTO();
		when(ipFormulacionModificarService.modificarObraConcreta(peticion)).thenReturn(new PddObraConcretaDTO());
		assertNotNull(controller.modificarObraConcreta(peticion));
		when(ipFormulacionModificarService.modificarObraConcreta(peticion)).thenThrow(SpddExceptions.class);
		assertNotNull(controller.modificarObraConcreta(peticion));
	}

	@Test
	void testConsultarObrasPorMeta() throws SpddExceptions {
		when(ipFormulacionConsultarService.consultarObrasConcretasPorMetas(1L)).thenReturn(new GenericoDTO());
		assertNotNull(controller.consultarObrasPorMeta(1L));
		when(ipFormulacionConsultarService.consultarObrasConcretasPorMetas(1L)).thenThrow(SpddExceptions.class);
		assertNotNull(controller.consultarObrasPorMeta(1L));
	}

	@Test
	void testModificarPddMeta() throws SpddExceptions {
		PddMetaDTO peticion = new PddMetaDTO();
		when(ipFormulacionModificarService.modificarPddMeta(peticion)).thenReturn(new PddMetaDTO());
		assertNotNull(controller.modificarPddMeta(peticion));
		when(ipFormulacionModificarService.modificarPddMeta(peticion)).thenThrow(SpddExceptions.class);
		assertNotNull(controller.modificarPddMeta(peticion));
	}

	@Test
	void testRegistrarModificarPddPrbValoracion() throws SpddExceptions {
		PddPrbValoracionDTO peticion = new PddPrbValoracionDTO();
		when(ipFormulacionRegistrarService.registrarPddPrbValoracion(peticion)).thenReturn(null);
		assertNotNull(controller.registrarModificarPddPrbValoracion(peticion));
		when(ipFormulacionModificarService.modificarPddPrbValoracion(peticion)).thenReturn(new PddPrbValoracionDTO());
		assertNotNull(controller.registrarModificarPddPrbValoracion(peticion));
		when(ipFormulacionModificarService.modificarPddPrbValoracion(peticion)).thenThrow(SpddExceptions.class);
		assertNotNull(controller.registrarModificarPddPrbValoracion(peticion));
	}

	@Test
	void testEliminarPrbPoblacion() throws SpddExceptions {
		when(ipFormulacionEliminarService.eliminarPrbPoblacion(1L)).thenReturn(new PddPrbPoblacionDTO());
		assertNotNull(controller.eliminarPrbPoblacion(1L));
		when(ipFormulacionEliminarService.eliminarPrbPoblacion(1L)).thenThrow(SpddExceptions.class);
		assertNotNull(controller.eliminarPrbPoblacion(1L));
	}

	@Test
	void testRegistrarPrbPoblacion() throws SpddExceptions {
		PddPrbPoblacionDTO peticion = new PddPrbPoblacionDTO();
		when(ipFormulacionRegistrarService.registrarPrbPoblacion(peticion)).thenReturn(peticion);
		assertNotNull(controller.registrarPrbPoblacion(peticion));
		when(ipFormulacionRegistrarService.registrarPrbPoblacion(peticion)).thenThrow(SpddExceptions.class);
		assertNotNull(controller.registrarPrbPoblacion(peticion));
	}

	@Test
	void testColsultarPddMetaResultadoPorIDProblematicaIndicador() throws SpddExceptions {
		when(ipFormulacionConsultarService.consultarPddMetaResultadoPorIDProblematicaIndicador(1L)).thenReturn(new GenericoDTO());
		assertNotNull(controller.colsultarPddMetaResultadoPorIDProblematicaIndicador(1L));
		when(ipFormulacionConsultarService.consultarPddMetaResultadoPorIDProblematicaIndicador(1L)).thenThrow(SpddExceptions.class);
		assertNotNull(controller.colsultarPddMetaResultadoPorIDProblematicaIndicador(1L));
	}

	@Test
	void testRegistrarPddMetaResultado() throws SpddExceptions {
		PddMetaResultadoDTO peticion = new PddMetaResultadoDTO();
		when(ipFormulacionRegistrarService.registrarPddMetaResultado(peticion)).thenReturn(new PddMetaResultadoDTO());
		assertNotNull(controller.registrarPddMetaResultado(peticion));
		when(ipFormulacionRegistrarService.registrarPddMetaResultado(peticion)).thenThrow(SpddExceptions.class);
		assertNotNull(controller.registrarPddMetaResultado(peticion));
	}

	@Test
	void testModificarPddMetaResultado() throws SpddExceptions {
		PddMetaResultadoDTO peticion = new PddMetaResultadoDTO();
		when(ipFormulacionModificarService.modificarPddMetaResultado(peticion)).thenReturn(new PddMetaResultadoDTO());
		assertNotNull(controller.modificarPddMetaResultado(peticion));
		when(ipFormulacionModificarService.modificarPddMetaResultado(peticion)).thenThrow(SpddExceptions.class);
		assertNotNull(controller.modificarPddMetaResultado(peticion));
	}

	@Test
	void testModificarPrbPoblacion() throws SpddExceptions {
		PddPrbPoblacionDTO peticion = new PddPrbPoblacionDTO();
		when(ipFormulacionModificarService.modificarPrbPoblacion(peticion)).thenReturn(new PddPrbPoblacionDTO());
		assertNotNull(controller.modificarPrbPoblacion(peticion));
		when(ipFormulacionModificarService.modificarPrbPoblacion(peticion)).thenThrow(SpddExceptions.class);
		assertNotNull(controller.modificarPrbPoblacion(peticion));
		
	}

	@Test
	void testConsultarPddIndicadorTodos() throws SpddExceptions {
		when(ipFormulacionConsultarService.consultarPddIndicadorTodos()).thenReturn(new GenericoDTO());
		assertNotNull(controller.consultarPddIndicadorTodos());
		when(ipFormulacionConsultarService.consultarPddIndicadorTodos()).thenThrow(SpddExceptions.class);
		assertNotNull(controller.consultarPddIndicadorTodos());
	}

	@Test
	void testConsultarPddProblematicaPorId() throws SpddExceptions {
		when(ipFormulacionConsultarService.consultarPddProblematicaPorId(1L)).thenReturn(new PddProblematicaDTO());
		assertNotNull(controller.consultarPddProblematicaPorId(1L));
		when(ipFormulacionConsultarService.consultarPddProblematicaPorId(1L)).thenThrow(SpddExceptions.class);
		assertNotNull(controller.consultarPddProblematicaPorId(1L));
		
	}

	@Test
	void testConsultarPrbIndicadorPorProblematica() throws SpddExceptions {
		when(ipFormulacionConsultarService.consultarPrbIndicadorPorProblematica(1L)).thenReturn(new GenericoDTO());
		assertNotNull(controller.consultarPrbIndicadorPorProblematica(1L));
		when(ipFormulacionConsultarService.consultarPrbIndicadorPorProblematica(1L)).thenThrow(SpddExceptions.class);
		assertNotNull(controller.consultarPrbIndicadorPorProblematica(1L));
	}

	@Test
	void testConsultarPddProblematicaPorCompromiso() throws SpddExceptions {
		PddProblematicaDTO peticion = new PddProblematicaDTO();
		when(ipFormulacionConsultarService.consultarPddProblematicaPorCompromiso(peticion)).thenReturn(new GenericoDTO());
		assertNotNull(controller.consultarPddProblematicaPorCompromiso(peticion));
		when(ipFormulacionConsultarService.consultarPddProblematicaPorCompromiso(peticion)).thenThrow(SpddExceptions.class);
		assertNotNull(controller.consultarPddProblematicaPorCompromiso(peticion));
	}

	@Test
	void testRegistrarPddProblematica() throws SpddExceptions {
		PddProblematicaDTO peticion = new PddProblematicaDTO();
		when(ipFormulacionRegistrarService.registrarProblematica(peticion)).thenReturn(new PddProblematicaDTO());
		assertNotNull(controller.registrarPddProblematica(peticion));
		when(ipFormulacionRegistrarService.registrarProblematica(peticion)).thenThrow(SpddExceptions.class);
		assertNotNull(controller.registrarPddProblematica(peticion));
	}

	@Test
	void testGuardarPrbIndicador() throws SpddExceptions, JsonProcessingException {
		PddPrbIndicadorDTO peticion = new PddPrbIndicadorDTO();
		when(ipFormulacionRegistrarService.registrarPrbIndicador(peticion)).thenReturn(peticion);
		assertNotNull(controller.guardarPrbIndicador(peticion));
		when(ipFormulacionRegistrarService.registrarPrbIndicador(peticion)).thenThrow(SpddExceptions.class);
		assertNotNull(controller.guardarPrbIndicador(peticion));
	}

	@Test
	void testModificarPrbIndicador() throws SpddExceptions {
		PddPrbIndicadorDTO peticion = new PddPrbIndicadorDTO();
		when(ipFormulacionModificarService.modificarPrbIndicador(peticion)).thenReturn(peticion);
		assertNotNull(controller.modificarPrbIndicador(peticion));
		when(ipFormulacionModificarService.modificarPrbIndicador(peticion)).thenThrow(SpddExceptions.class);
		assertNotNull(controller.modificarPrbIndicador(peticion));
	}

	@Test
	void testEliminarPrbIndicador() throws SpddExceptions {
		when(ipFormulacionEliminarService.eliminarPrbIndicador(1L)).thenReturn(new PddPrbIndicadorDTO());
		assertNotNull(controller.eliminarPrbIndicador(1L));
		when(ipFormulacionEliminarService.eliminarPrbIndicador(1L)).thenThrow(SpddExceptions.class);
		assertNotNull(controller.eliminarPrbIndicador(1L));
	}

	@Test
	void testConsultarPddIndicadorPorId() throws SpddExceptions {
		when(ipFormulacionConsultarService.consultarPddIndicadorPorId(1L)).thenReturn(new PddIndicadorDTO());
		assertNotNull(controller.consultarPddIndicadorPorId(1L));
		when(ipFormulacionConsultarService.consultarPddIndicadorPorId(1L)).thenThrow(SpddExceptions.class);
		assertNotNull(controller.consultarPddIndicadorPorId(1L));
	}

	@Test
	void testRegistrarPddIndicadorYPddPrbIndicador() throws SpddExceptions, JsonProcessingException {
		PddPrbIndicadorDTO peticion = new PddPrbIndicadorDTO();
		when(ipFormulacionRegistrarService.registrarPddIndicadorYPddPrbIndicador(peticion)).thenReturn(new PddPrbIndicadorDTO());
		assertNotNull(controller.registrarPddIndicadorYPddPrbIndicador(peticion));
		when(ipFormulacionRegistrarService.registrarPddIndicadorYPddPrbIndicador(peticion)).thenThrow(SpddExceptions.class);
		assertNotNull(controller.registrarPddIndicadorYPddPrbIndicador(peticion));
	}

	@Test
	void testModificarPddIndicador() throws SpddExceptions, JsonProcessingException {
		PddIndicadorDTO peticion = new PddIndicadorDTO();
		when(ipFormulacionModificarService.modificarPddIndicador(peticion)).thenReturn(new PddIndicadorDTO());
		assertNotNull(controller.modificarPddIndicador(peticion));
		when(ipFormulacionModificarService.modificarPddIndicador(peticion)).thenThrow(SpddExceptions.class);
		assertNotNull(controller.modificarPddIndicador(peticion));
	}

	@Test
	void testConsultarPddCompromisoPorId() throws SpddExceptions {
		when(ipFormulacionConsultarService.consultarPddCompromisoPorId(1L)).thenReturn(new PddCompromisoDTO());
		assertNotNull(controller.consultarPddCompromisoPorId(1L));
		when(ipFormulacionConsultarService.consultarPddCompromisoPorId(1L)).thenThrow(SpddExceptions.class);
		assertNotNull(controller.consultarPddCompromisoPorId(1L));
	}

	@Test
	void testConsultarPddVigente() throws SpddExceptions {
		when(ipFormulacionConsultarService.consultarPddVigente()).thenReturn(new PddDTO());
		assertNotNull(controller.consultarPddVigente());
		when(ipFormulacionConsultarService.consultarPddVigente()).thenThrow(SpddExceptions.class);
		assertNotNull(controller.consultarPddVigente());
		
	}

}
