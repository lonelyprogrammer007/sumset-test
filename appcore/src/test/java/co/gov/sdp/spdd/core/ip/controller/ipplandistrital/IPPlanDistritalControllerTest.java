package co.gov.sdp.spdd.core.ip.controller.ipplandistrital;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
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
import co.gov.sdp.nhspdd.common.dto.PddNivelAtributoDTO;
import co.gov.sdp.nhspdd.common.dto.PddNivelDTO;
import co.gov.sdp.nhspdd.common.dto.ip.ArbolCompromisoDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddIndicadorDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddMRIndicadorDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddMetaProductoDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddMetaResultadoDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddMpIndicadorDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddMpIndicadorEntidadDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddRangoPonderacionDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PdlDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PdlNivelAtributoDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.nhspdd.common.util.NHSPDDMensajes;
import co.gov.sdp.nhspdd.common.util.PaqueteMensajeEnum;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.spdd.core.ip.iservice.ipplandistrital.IIPPlanDistritalConsultarService;
import co.gov.sdp.spdd.core.ip.iservice.ipplandistrital.IIPPlanDistritalEliminarService;
import co.gov.sdp.spdd.core.ip.iservice.ipplandistrital.IIPPlanDistritalModificarService;
import co.gov.sdp.spdd.core.ip.iservice.ipplandistrital.IIPPlanDistritalRegistrarService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { IPPlanDistritalController.class })
public class IPPlanDistritalControllerTest {

	@Autowired
	IPPlanDistritalController controller;
	/**
	 * Inyeccion del Service de registro
	 */
	@MockBean
	IIPPlanDistritalRegistrarService ipPlanDistritalRegistrarService;

	/**
	 * Inyeccion del Service de consulta
	 */
	@MockBean
	IIPPlanDistritalConsultarService ipPlanDistritalConsultarService;

	/**
	 * Inyeccion del Service de modificar
	 */
	@MockBean
	IIPPlanDistritalModificarService ipPlanDistritalModificarService;

	/**
	 * Inyeccion del Service de eliminar
	 */
	@MockBean
	IIPPlanDistritalEliminarService ipPlanDistritalEliminarService;

	/**
	 * Logger
	 */
	@MockBean
	SPDDLogger spddLogger;

	@Test
	public void testConsultarTodosPddPorFiltro() throws SpddExceptions {
		PddDTO peticion = new PddDTO();
		
		when(ipPlanDistritalConsultarService.pddObtenerPaginado(peticion)).thenReturn(new GenericoDTO());
		GenericoDTO res= controller.consultarTodosPddPorFiltro(peticion);
		assertNotNull(res);
		
		when(ipPlanDistritalConsultarService.pddObtenerPaginado(peticion)).thenThrow(new SpddExceptions());
		res= controller.consultarTodosPddPorFiltro(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400);
	}

	@Test
	public void testRegistrarPdd() throws JsonProcessingException, SpddExceptions {
		PddDTO peticion = new PddDTO();
		peticion.setNombrePlan("plan");
		peticion.setSiglaPlan("pdd");
		peticion.setYearFinal("2020");
		peticion.setYearInicio("2019");
		peticion.setProgramaGobierno("programa");
		
		when(ipPlanDistritalRegistrarService.registrarPdd(peticion)).thenReturn(new PddDTO());
		PddDTO res = controller.registrarPdd(peticion);
		assertNotNull(res);
		
		peticion.setProgramaGobierno(null);
		res = controller.registrarPdd(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400);
		
		peticion.setProgramaGobierno("programa");
		when(ipPlanDistritalRegistrarService.registrarPdd(peticion)).thenThrow(new SpddExceptions());
		res = controller.registrarPdd(peticion);
		assertNotNull(res);
	}

	@Test
	public void testModificarPdd() throws JsonProcessingException, SpddExceptions {
		PddDTO peticion = new PddDTO();
		peticion.setNombrePlan("plan");
		peticion.setSiglaPlan("pdd");
		peticion.setYearFinal("2020");
		peticion.setYearInicio("2019");
		peticion.setProgramaGobierno("programa");
		peticion.setIdPlanDesarrollo(1L);
		
		when(ipPlanDistritalModificarService.modificarPdd(peticion)).thenReturn(new PddDTO());
		PddDTO res = controller.modificarPdd(peticion);
		assertNotNull(res);
		
		peticion.setProgramaGobierno(null);
		res = controller.modificarPdd(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400);
		
		peticion.setProgramaGobierno("programa");
		when(ipPlanDistritalModificarService.modificarPdd(peticion)).thenThrow(new SpddExceptions());
		res = controller.modificarPdd(peticion);
		assertNotNull(res);
	}

	@Test
	public void testConsultarTodosPddNivelPorIdPlanDesarrolloDistrital() throws SpddExceptions {
		Long idPlan = 1L;
		
		when(ipPlanDistritalConsultarService.consultarTodosPddNivelPorIdPlanDesarrolloDistrital(idPlan)).thenReturn(new GenericoDTO());
		GenericoDTO res = controller.consultarTodosPddNivelPorIdPlanDesarrolloDistrital(idPlan);
		assertNotNull(res);
		
		when(ipPlanDistritalConsultarService.consultarTodosPddNivelPorIdPlanDesarrolloDistrital(idPlan)).thenThrow(new SpddExceptions());
		res = controller.consultarTodosPddNivelPorIdPlanDesarrolloDistrital(idPlan);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400);
	}

	@Test
	public void testRegistrarModificarPddNivel() {
	}

	@Test
	public void testRegistrarPddNivel() throws JsonProcessingException, SpddExceptions {
		PddNivelDTO peticion = new PddNivelDTO();
		peticion.setIdPlanDesarrollo(1L);
		peticion.setCodNivelConcatenados("1");
		peticion.setDescripcionConcatenados("des");
		peticion.setObligatorioPdlConcatenados("0");
		peticion.setIdPddNivelConcatenados("1");
		
		when(ipPlanDistritalRegistrarService.registrarPddNivel(peticion)).thenReturn(new GenericoDTO());
		GenericoDTO res = controller.registrarPddNivel(peticion);
		assertNotNull(res);
		
		peticion.setIdPlanDesarrollo(null);
		res = controller.registrarPddNivel(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400);
		
		peticion.setIdPlanDesarrollo(1L);
		when(ipPlanDistritalRegistrarService.registrarPddNivel(peticion)).thenThrow(new SpddExceptions());
		res = controller.registrarPddNivel(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400);
	}

	@Test
	public void testModificarPddNivel() {
	}

	@Test
	public void testConsultarPddMetaResultadoPorProyecto() throws SpddExceptions {
		PddMetaResultadoDTO peticion = new PddMetaResultadoDTO();
		when(ipPlanDistritalConsultarService.consultarPddMetaResultadoProyectoEstrategico(peticion))
				.thenReturn(new GenericoDTO());
		assertNotNull(controller.consultarPddMetaResultadoPorProyecto(peticion));

		when(ipPlanDistritalConsultarService.consultarPddMetaResultadoProyectoEstrategico(peticion))
				.thenThrow(SpddExceptions.class);
		assertNotNull(controller.consultarPddMetaResultadoPorProyecto(peticion));

	}

	@Test
	public void TestRegistrarPddNivelAtributo() {

	}

	@Test
	public void testEliminarMetaResultadoPorId() throws SpddExceptions {
		when(ipPlanDistritalEliminarService.eliminarMetaResultado(1L)).thenReturn(new PddMetaResultadoDTO());
		assertNotNull(controller.eliminarMetaResultadoPorId(1L));
		when(ipPlanDistritalEliminarService.eliminarMetaResultado(1L)).thenThrow(SpddExceptions.class);
		assertNotNull(controller.eliminarMetaResultadoPorId(1L));

	}

	@Test
	void testConsultarTodosPdlPorFiltro() throws SpddExceptions {
		PdlDTO peticion = new PdlDTO();
		when(ipPlanDistritalConsultarService.pdlObtenerPaginado(peticion)).thenReturn(new GenericoDTO());
		assertNotNull(controller.consultarTodosPdlPorFiltro(peticion));
		when(ipPlanDistritalConsultarService.pdlObtenerPaginado(peticion)).thenThrow(SpddExceptions.class);
		assertNotNull(controller.consultarTodosPdlPorFiltro(peticion));
	}

	@Test
	void testConsultarTodosPlanDesarrolloLocal() throws SpddExceptions {
		when(ipPlanDistritalConsultarService.consultarTodosPlanDesarrolloLocal()).thenReturn(new GenericoDTO());
		assertNotNull(controller.consultarTodosPlanDesarrolloLocal());
		when(ipPlanDistritalConsultarService.consultarTodosPlanDesarrolloLocal()).thenThrow(SpddExceptions.class);
		assertNotNull(controller.consultarTodosPlanDesarrolloLocal());
	}

	@Test
	void testConsultarPlanDesarrolloLocalPorId() throws SpddExceptions {
		when(ipPlanDistritalConsultarService.consultarPlanDesarrolloLocalPorId(1L)).thenReturn(new PdlDTO());
		assertNotNull(controller.consultarPlanDesarrolloLocalPorId(1L));
		when(ipPlanDistritalConsultarService.consultarPlanDesarrolloLocalPorId(1L)).thenThrow(SpddExceptions.class);
		assertNotNull(controller.consultarPlanDesarrolloLocalPorId(1L));
	}

	@Test
	void testRegistrarPdl() throws JsonProcessingException, SpddExceptions {
		PdlDTO peticion = new PdlDTO();
		peticion.setIdPlanLocal(1L);
		peticion.setNombrePlan("Plan local");
		peticion.setUrlPlan("www.planlocal.com");
		peticion.setIdLsAdoptado(2L);
		peticion.setActoAdministrativo("acto1");
		peticion.setFechaActo("27/04/2020");
		peticion.setYearInicio("2020");
		peticion.setIdLsEstadoPlan(3L);
		peticion.setNombreAlcaldeLocal("alcaldeLocal@gmail.com");
		peticion.setCorreoAlcaldeLocal("correo@gmail.com");
		peticion.setYearFinal("2021");
		peticion.setIdPlanDesarrollo(4L);
		peticion.setCodigoEntidad("Secretaria de movilidad");

		when(ipPlanDistritalRegistrarService.registrarPdl(peticion)).thenReturn(new PdlDTO());
		PdlDTO res = controller.registrarPdl(peticion);
		assertNotNull(res);
		
		peticion.setIdLsAdoptado(null);
		res = controller.registrarPdl(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
		
		peticion.setIdLsAdoptado(2L);
		when(ipPlanDistritalRegistrarService.registrarPdl(peticion)).thenThrow(new SpddExceptions());
		res = controller.registrarPdl(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
		assertEquals(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ERROR_VALIDACION,
				PaqueteMensajeEnum.ERRORES, null), res.getMsgRespuesta());
	}

	@Test
	void testModificarPdl() throws JsonProcessingException, SpddExceptions {
		PdlDTO peticion = new PdlDTO();
		peticion.setIdPlanLocal(1L);
		peticion.setNombrePlan("Plan local");
		peticion.setUrlPlan("www.planlocal.com");
		peticion.setIdLsAdoptado(2L);
		peticion.setActoAdministrativo("acto1");
		peticion.setFechaActo("27/04/2020");
		peticion.setYearInicio("2020");
		peticion.setIdLsEstadoPlan(3L);
		peticion.setNombreAlcaldeLocal("alcaldeLocal@gmail.com");
		peticion.setCorreoAlcaldeLocal("correo@gmail.com");
		peticion.setYearFinal("2021");
		peticion.setIdPlanDesarrollo(4L);
		peticion.setCodigoEntidad("Secretaria de movilidad");

		when(ipPlanDistritalModificarService.modificarPdl(peticion)).thenReturn(new PdlDTO());
		PdlDTO res = controller.modificarPdl(peticion);
		assertNotNull(res);
		
		peticion.setIdLsAdoptado(null);
		res = controller.modificarPdl(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
		
		peticion.setIdLsAdoptado(2L);
		when(ipPlanDistritalModificarService.modificarPdl(peticion)).thenThrow(new SpddExceptions());
		res = controller.modificarPdl(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
		assertEquals(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ERROR_VALIDACION,
				PaqueteMensajeEnum.ERRORES, null), res.getMsgRespuesta());
	}

	@Test
	public void testConsultarMetaProductoPorMR() throws SpddExceptions {
		PddMetaProductoDTO peticion = new PddMetaProductoDTO();
		when(ipPlanDistritalConsultarService.consultarMetaProductoPorMR(peticion)).thenReturn(new GenericoDTO());
		assertNotNull(controller.consultarMetaProductoPorMR(peticion));
		when(ipPlanDistritalConsultarService.consultarMetaProductoPorMR(peticion)).thenThrow(SpddExceptions.class);
		assertThat(controller.consultarMetaProductoPorMR(peticion).getCodigoRespuesta()).isEqualTo(400);
	}

	@Test
	public void testEliminarMetaProducto() throws SpddExceptions {
		when(ipPlanDistritalEliminarService.eliminarMetaProducto(1L)).thenReturn(new PddMetaProductoDTO());
		assertNotNull(controller.eliminarMetaProducto(1L));
		when(ipPlanDistritalEliminarService.eliminarMetaProducto(1L)).thenThrow(SpddExceptions.class);
		assertThat(controller.eliminarMetaProducto(1L).getCodigoRespuesta()).isEqualTo(400);

	}

	@Test
	public void testModificarMetaProducto() throws SpddExceptions {
		PddMetaProductoDTO peticion = new PddMetaProductoDTO();
		assertThat(controller.modificarMetaProducto(peticion).getCodigoRespuesta()).isEqualTo(400);
		peticion.setIdMetaResultado(1L);
		peticion.setIdMetaProducto(1L);
		peticion.setIdLsVerbo(1L);
		peticion.setMagnitud(12321L);
		peticion.setIdLsUnidadMedida(1L);
		peticion.setComplemento("a");
		peticion.setIdLsTipoAnualizacion(1L);
		peticion.setIdLsEstado(1L);
		peticion.setPonderacion(1L);
		peticion.setObservaciones("observacion");
		peticion.setCodigoRespuesta(200);
		when(ipPlanDistritalModificarService.modificarMetaProducto(peticion)).thenReturn(peticion);
		assertThat(controller.modificarMetaProducto(peticion).getCodigoRespuesta()).isEqualTo(200);
		when(ipPlanDistritalModificarService.modificarMetaProducto(peticion)).thenThrow(SpddExceptions.class);
		assertThat(controller.modificarMetaProducto(peticion).getCodigoRespuesta()).isEqualTo(400);

	}

	@Test
	public void testRegistrarMetaProducto() throws SpddExceptions {
		PddMetaProductoDTO peticion = new PddMetaProductoDTO();
		assertThat(controller.registrarMetaProducto(peticion).getCodigoRespuesta()).isEqualTo(400);
		peticion.setIdMetaResultado(1L);
		peticion.setIdMetaProducto(1L);
		peticion.setIdLsVerbo(1L);
		peticion.setMagnitud(12321L);
		peticion.setIdLsUnidadMedida(1L);
		peticion.setComplemento("a");
		peticion.setIdLsTipoAnualizacion(1L);
		peticion.setIdLsEstado(1L);
		peticion.setPonderacion(1L);
		peticion.setObservaciones("observacion");
		peticion.setCodigoRespuesta(200);
		when(ipPlanDistritalRegistrarService.registrarMetaProducto(peticion)).thenReturn(peticion);
		assertThat(controller.registrarMetaProducto(peticion).getCodigoRespuesta()).isEqualTo(200);
		when(ipPlanDistritalRegistrarService.registrarMetaProducto(peticion)).thenThrow(SpddExceptions.class);
		assertThat(controller.registrarMetaProducto(peticion).getCodigoRespuesta()).isEqualTo(400);
	}

	@Test
	public void testConsultarPdlNivelPorIdPlanLocal() throws SpddExceptions {
		when(ipPlanDistritalConsultarService.consultarPdlNivelPorIdPlanLocal(1L)).thenReturn(new GenericoDTO());
		assertNotNull(controller.consultarPdlNivelPorIdPlanLocal(1L));
		when(ipPlanDistritalConsultarService.consultarPdlNivelPorIdPlanLocal(1L)).thenThrow(SpddExceptions.class);
		assertThat(controller.consultarPdlNivelPorIdPlanLocal(1L).getCodigoRespuesta()).isEqualTo(400);
	}

	@Test
	public void testRegistrarPdlNivelAtributo() throws SpddExceptions {
		PdlNivelAtributoDTO peticion = new PdlNivelAtributoDTO();
		peticion.setIdAtributo(1L);
		peticion.setNumero(2L);
		peticion.setDenominacion("denominacion");
		peticion.setPonderacion("ponderacion");
		peticion.setNombreGerente("nombreGerente");
		peticion.setCorreoGerente("correoGerente");
		peticion.setIdLsGeneroGerente(3L);
		peticion.setStringIdLsGeneroGerente("stringIdLsGeneroGerente");
		peticion.setIdPdlNivel(4L);
		peticion.setStringIdPdlNivel("stringIdPdlNivel");
		peticion.setIdAtributoPadre(5L);
		peticion.setStringIdAtributoPadre("stringIdAtributoPadre");
		peticion.setProyectoEstrategico(6L);
		
		when(ipPlanDistritalRegistrarService.registrarPdlNivelAtributo(peticion)).thenReturn(new PdlNivelAtributoDTO());
		PdlNivelAtributoDTO res = controller.registrarPdlNivelAtributo(peticion);
		assertNotNull(res);
		
		peticion.setNumero(null);
		res = controller.registrarPdlNivelAtributo(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
		
		peticion.setNumero(2L);
		when(ipPlanDistritalRegistrarService.registrarPdlNivelAtributo(peticion)).thenThrow(new SpddExceptions());
		res = controller.registrarPdlNivelAtributo(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
		assertEquals(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ERROR_VALIDACION,
				PaqueteMensajeEnum.ERRORES, null), res.getMsgRespuesta());
	}

	@Test
	public void testConsultarTodosPddRangoPonderacion() throws SpddExceptions {
		when(ipPlanDistritalConsultarService.consultarTodosRangoPonderacion()).thenReturn(new GenericoDTO());
		assertNotNull(controller.consultarTodosPddRangoPonderacion());
		when(ipPlanDistritalConsultarService.consultarTodosRangoPonderacion()).thenThrow(SpddExceptions.class);
		assertThat(controller.consultarTodosPddRangoPonderacion().getCodigoRespuesta()).isEqualTo(400);
	}

	@Test
	public void testEliminarPddRangoPonderacion() throws SpddExceptions {
		when(ipPlanDistritalEliminarService.eliminarPddRangoPonderacion(1L)).thenReturn(new PddRangoPonderacionDTO());
		assertNotNull(controller.eliminarPddRangoPonderacion(1L));
		when(ipPlanDistritalEliminarService.eliminarPddRangoPonderacion(1L)).thenThrow(SpddExceptions.class);
		assertThat(controller.eliminarPddRangoPonderacion(1L).getCodigoRespuesta()).isEqualTo(400);
	}

	@Test
	public void testRegistrarPddRangoPonderacion() throws SpddExceptions {
//		PddRangoPonderacionDTO peticion = new PddRangoPonderacionDTO();
//		peticion.setRango(">90%");
//		when(ipPlanDistritalRegistrarService.registrarPddRangoPonderacion(peticion)).thenReturn(new PddRangoPonderacionDTO());
//		PddRangoPonderacionDTO res = controller.registrarPddRangoPonderacion(peticion);
//		assertNotNull(res);
//		when(ipPlanDistritalRegistrarService.registrarPddRangoPonderacion(peticion)).thenThrow(new SpddExceptions());
//		res = controller.registrarPddRangoPonderacion(peticion);
//		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
	}

	@Test
	public void testModificarPddRangoPonderacion() throws SpddExceptions {
//		PddRangoPonderacionDTO peticion = new PddRangoPonderacionDTO();
//		peticion.setRango(">90%");
//		when(ipPlanDistritalModificarService.modificarPddRangoPonderacion(peticion)).thenReturn(new PddRangoPonderacionDTO());
//		PddRangoPonderacionDTO res = controller.modificarPddRangoPonderacion(peticion);
//		assertNotNull(res);
//		
//		when(ipPlanDistritalModificarService.modificarPddRangoPonderacion(peticion)).thenThrow(new SpddExceptions());
//		res = controller.modificarPddRangoPonderacion(peticion);
//		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
	}

	@Test
	public void testRegistrarPddNivelAtributo() throws Exception {
		PddNivelAtributoDTO peticion = new PddNivelAtributoDTO();
		peticion.setNumero(1L);
		peticion.setDenominacion("deno");
		peticion.setPonderacion("10");
		
		when(ipPlanDistritalRegistrarService.registrarPddNivelAtributo(peticion)).thenReturn(new PddNivelAtributoDTO());
		PddNivelAtributoDTO res = controller.registrarPddNivelAtributo(peticion);
		assertNotNull(res);
		
		peticion.setNumero(null);
		res = controller.registrarPddNivelAtributo(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
		
		peticion.setNumero(1L);
		when(ipPlanDistritalRegistrarService.registrarPddNivelAtributo(peticion)).thenThrow(new SpddExceptions());
		res = controller.registrarPddNivelAtributo(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
	}

	@Test
	public void testConsultarTodosPddNivelAtributoPorIdPddNivelPaginado() throws Exception {
		PddNivelAtributoDTO peticion = new PddNivelAtributoDTO();
		
		when(ipPlanDistritalConsultarService.consultarTodosPddNivelAtributoPorIdPddNivelPaginado(peticion)).thenReturn(new GenericoDTO());
		GenericoDTO res = controller.consultarTodosPddNivelAtributoPorIdPddNivelPaginado(peticion);
		assertNotNull(res);
		
		when(ipPlanDistritalConsultarService.consultarTodosPddNivelAtributoPorIdPddNivelPaginado(peticion)).thenThrow(new SpddExceptions());
		res = controller.consultarTodosPddNivelAtributoPorIdPddNivelPaginado(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);		
	}

	@Test
	public void testConsultarTodosPddNivelAtributoPorIdAtributoPadrePaginado() throws Exception {
		PddNivelAtributoDTO peticion = new PddNivelAtributoDTO();
		
		when(ipPlanDistritalConsultarService.consultarTodosPddNivelAtributoPorIdAtributoPadrePaginado(peticion)).thenReturn(new GenericoDTO());
		GenericoDTO res = controller.consultarTodosPddNivelAtributoPorIdAtributoPadrePaginado(peticion);
		assertNotNull(res);
		
		when(ipPlanDistritalConsultarService.consultarTodosPddNivelAtributoPorIdAtributoPadrePaginado(peticion)).thenThrow(new SpddExceptions());
		res = controller.consultarTodosPddNivelAtributoPorIdAtributoPadrePaginado(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);		

	}

	@Test
	public void testRegistrarIndicadorMetaResultado() throws Exception {
		PddIndicadorDTO peticion =  new PddIndicadorDTO();
		peticion.setNombre("nombre");
		peticion.setLineaBaseDesc("linea");
		peticion.setFuente("fuente");
		
		when(ipPlanDistritalRegistrarService.registrarIndicadorMetaResultado(peticion)).thenReturn(new PddMRIndicadorDTO());
		PddMRIndicadorDTO res = controller.registrarIndicadorMetaResultado(peticion);
		assertNotNull(res);
		
		peticion.setFuente(null);
		res = controller.registrarIndicadorMetaResultado(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);	
		
		peticion.setNombre("nombre");
		when(ipPlanDistritalRegistrarService.registrarIndicadorMetaResultado(peticion)).thenThrow(new SpddExceptions());
		res = controller.registrarIndicadorMetaResultado(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
	}

	@Test
	public void testEliminarMetaResultadoIndicador() throws Exception {
		Long id = 1L;
		
		when(ipPlanDistritalEliminarService.eliminarIndicadorMetaResultado(id)).thenReturn(new PddMRIndicadorDTO());
		PddMRIndicadorDTO res = controller.eliminarMetaResultadoIndicador(id);
		assertNotNull(res);
		
		when(ipPlanDistritalEliminarService.eliminarIndicadorMetaResultado(id)).thenThrow(new SpddExceptions());
		res = controller.eliminarMetaResultadoIndicador(id);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
	}

	@Test
	public void testConsultarIndicadorMetaResultado() throws Exception {
		PddMRIndicadorDTO peticion = new PddMRIndicadorDTO();
		
		when(ipPlanDistritalConsultarService.consultarPddIndicadorMetaResultado(peticion)).thenReturn(new GenericoDTO());
		GenericoDTO res = controller.consultarIndicadorMetaResultado(peticion);
		assertNotNull(res);
		
		when(ipPlanDistritalConsultarService.consultarPddIndicadorMetaResultado(peticion)).thenThrow(new SpddExceptions());
		res = controller.consultarIndicadorMetaResultado(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
	}

	@Test
	public void testConsultarIndicadoresMetaProducto() throws Exception {
		PddMpIndicadorDTO peticion = new PddMpIndicadorDTO();
		
		when(ipPlanDistritalConsultarService.consultarIndicadoresMetaProducto(peticion)).thenReturn(new GenericoDTO());
		GenericoDTO res = controller.consultarIndicadoresMetaProducto(peticion);
		assertNotNull(res);
		
		when(ipPlanDistritalConsultarService.consultarIndicadoresMetaProducto(peticion)).thenThrow(new SpddExceptions());
		res = controller.consultarIndicadoresMetaProducto(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
	}

	@Test
	public void testRegistrarIndicadorMetaProducto() throws Exception {
		PddIndicadorDTO peticion = new PddIndicadorDTO();
		peticion.setNombre("nombre");
		peticion.setLineaBaseDesc("linea");
		peticion.setFuente("fuente");
		
		when(ipPlanDistritalRegistrarService.registrarMetaProductoIndicador(peticion)).thenReturn(new PddMpIndicadorDTO());
		PddMpIndicadorDTO res = controller.registrarIndicadorMetaProducto(peticion);
		assertNotNull(res);
		
		peticion.setNombre(null);
		res = controller.registrarIndicadorMetaProducto(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
		
		peticion.setNombre("nombre");
		when(ipPlanDistritalRegistrarService.registrarMetaProductoIndicador(peticion)).thenThrow(new SpddExceptions());
		res = controller.registrarIndicadorMetaProducto(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400);
		
	}

	@Test
	public void testEliminarIndicadorMp() throws Exception {
		Long idIndicadorMp = 1L;
		
		when(ipPlanDistritalEliminarService.eliminarMpIndicador(idIndicadorMp)).thenReturn(new PddMpIndicadorDTO());
		PddMpIndicadorDTO res = controller.eliminarIndicadorMp(idIndicadorMp);
		assertNotNull(res);
		
		when(ipPlanDistritalEliminarService.eliminarMpIndicador(idIndicadorMp)).thenThrow(new SpddExceptions());
		res = controller.eliminarIndicadorMp(idIndicadorMp);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400);		
	}

	@Test
	public void testModificarPddNivelAtributo() throws Exception {
		PddNivelAtributoDTO peticion = new PddNivelAtributoDTO();
		peticion.setNumero(1L);
		peticion.setDenominacion("deno");
		peticion.setPonderacion("10");
		peticion.setIdAtributo(1L);
		
		when(ipPlanDistritalModificarService.modificarPddNivelAtributo(peticion)).thenReturn(new PddNivelAtributoDTO());
		PddNivelAtributoDTO res = controller.modificarPddNivelAtributo(peticion);
		assertNotNull(res);
		
		peticion.setNumero(null);
		res = controller.modificarPddNivelAtributo(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400);
		
		peticion.setNumero(1L);
		when(ipPlanDistritalModificarService.modificarPddNivelAtributo(peticion)).thenThrow(new SpddExceptions());
		res = controller.modificarPddNivelAtributo(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400);
	}

	@Test
	public void testEliminarPddNivelAtributo() throws Exception {
		Long idAtributo = 1L;
		
		when(ipPlanDistritalEliminarService.eliminarPddNivelAtributo(idAtributo)).thenReturn(new PddNivelAtributoDTO());
		PddNivelAtributoDTO res = controller.eliminarPddNivelAtributo(idAtributo);
		assertNotNull(res);
		
		when(ipPlanDistritalEliminarService.eliminarPddNivelAtributo(idAtributo)).thenThrow(new SpddExceptions());
		res = controller.eliminarPddNivelAtributo(idAtributo);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400);
	}

	@Test
	public void testCopiarEstructuraPddToPdl() throws Exception {
		PdlDTO peticion =  new PdlDTO();
		peticion.setIdPlanDesarrollo(1L);
		peticion.setCodigoEntidad("0142");
		
		when(ipPlanDistritalRegistrarService.copiarEstructuraPddToPdl(peticion)).thenReturn(new GenericoDTO());
		GenericoDTO res = controller.copiarEstructuraPddToPdl(peticion);
		assertNotNull(res);
		
		peticion.setIdPlanDesarrollo(null);
		res = controller.copiarEstructuraPddToPdl(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400);
		
		peticion.setIdPlanDesarrollo(1L);
		when(ipPlanDistritalRegistrarService.copiarEstructuraPddToPdl(peticion)).thenThrow(new SpddExceptions());
		res = controller.copiarEstructuraPddToPdl(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400);
	}

	@Test
	public void testConsultarTodosPdlNivelAtributoPorIdPdlNivelPaginado() throws Exception {
		PdlNivelAtributoDTO peticion = new PdlNivelAtributoDTO();
		
		when(ipPlanDistritalConsultarService.consultarTodosPdlNivelAtributoPorIdPdlNivelPaginado(peticion)).thenReturn(new GenericoDTO());
		GenericoDTO res = controller.consultarTodosPdlNivelAtributoPorIdPdlNivelPaginado(peticion);
		assertNotNull(res);
		
		when(ipPlanDistritalConsultarService.consultarTodosPdlNivelAtributoPorIdPdlNivelPaginado(peticion)).thenThrow(new SpddExceptions());
		res = controller.consultarTodosPdlNivelAtributoPorIdPdlNivelPaginado(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400);
	}

	@Test
	public void testConsultarTodosPdlNivelAtributoPorIdAtributoPadre() throws Exception {
		PdlNivelAtributoDTO peticion = new PdlNivelAtributoDTO();
		
		when(ipPlanDistritalConsultarService.consultarTodosPdlNivelAtributoPorIdAtributoPadre(peticion)).thenReturn(new GenericoDTO());
		GenericoDTO res = controller.consultarTodosPdlNivelAtributoPorIdAtributoPadre(peticion);
		assertNotNull(res);
		
		when(ipPlanDistritalConsultarService.consultarTodosPdlNivelAtributoPorIdAtributoPadre(peticion)).thenThrow(new SpddExceptions());
		res = controller.consultarTodosPdlNivelAtributoPorIdAtributoPadre(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400);
	}

	@Test
	public void testConsultarTodosMpEntidades() throws Exception {
		PddMpIndicadorEntidadDTO peticion =  new PddMpIndicadorEntidadDTO();
		
		when(ipPlanDistritalConsultarService.consultarTodosMpEntidad(peticion)).thenReturn(new GenericoDTO());
		GenericoDTO res = controller.consultarTodosMpEntidades(peticion);
		assertNotNull(res);
		
		when(ipPlanDistritalConsultarService.consultarTodosMpEntidad(peticion)).thenThrow(new SpddExceptions());
		res = controller.consultarTodosMpEntidades(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400);
	}

	@Test
	public void testConsultarNivelesComponentesDesbalanceados() throws Exception {
		Long idPlanDesarrollo = 1L;
		
		when(ipPlanDistritalConsultarService.consultarNivelesComponentesDesbalanceados(idPlanDesarrollo)).thenReturn(new ArbolCompromisoDTO());
		ArbolCompromisoDTO res = controller.consultarNivelesComponentesDesbalanceados(idPlanDesarrollo);
		assertNotNull(res);
		
		when(ipPlanDistritalConsultarService.consultarNivelesComponentesDesbalanceados(idPlanDesarrollo)).thenThrow(new SpddExceptions());
		res = controller.consultarNivelesComponentesDesbalanceados(idPlanDesarrollo);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400);
	}

	@Test
	public void testRegistrarMpIndicadorEntidad() throws Exception {
		PddMpIndicadorEntidadDTO peticion = new PddMpIndicadorEntidadDTO();
		peticion.setCodigoEntidad("0141");
		peticion.setIdMetaProdInd(1L);
		peticion.setMagnitud(1L);
		peticion.setPonderacion(10L);
		
		when(ipPlanDistritalRegistrarService.registrarMpEntidad(peticion)).thenReturn(new PddMpIndicadorEntidadDTO());
		PddMpIndicadorEntidadDTO res = controller.registrarMpIndicadorEntidad(peticion);
		assertNotNull(res);
		
		peticion.setPonderacion(null);
		res = controller.registrarMpIndicadorEntidad(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400);
		
		peticion.setPonderacion(10L);
		when(ipPlanDistritalRegistrarService.registrarMpEntidad(peticion)).thenThrow(new SpddExceptions());
		res = controller.registrarMpIndicadorEntidad(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400);
	}

	@Test
	public void testModificarMpIndicadorEntidad() throws Exception {
		PddMpIndicadorEntidadDTO peticion = new PddMpIndicadorEntidadDTO();
		peticion.setCodigoEntidad("0141");
		peticion.setIdMetaProdInd(1L);
		peticion.setMagnitud(1L);
		peticion.setPonderacion(10L);
		peticion.setIdMpIndEntidad(1L);
		
		when(ipPlanDistritalModificarService.modificarMpEntidad(peticion)).thenReturn(new PddMpIndicadorEntidadDTO());
		PddMpIndicadorEntidadDTO res = controller.modificarMpIndicadorEntidad(peticion);
		assertNotNull(res);
		
		peticion.setIdMpIndEntidad(null);
		res = controller.modificarMpIndicadorEntidad(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400);
		
		peticion.setIdMpIndEntidad(1L);
		when(ipPlanDistritalModificarService.modificarMpEntidad(peticion)).thenThrow(new SpddExceptions());
		res = controller.modificarMpIndicadorEntidad(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400);
	}

	@Test
	public void testEliminarMpIndicadorEntidad() throws Exception {
		Long idMpEntidad = 1L;
		
		when(ipPlanDistritalEliminarService.eliminarMpEntidad(idMpEntidad)).thenReturn(new PddMpIndicadorEntidadDTO());
		PddMpIndicadorEntidadDTO res = controller.eliminarMpIndicadorEntidad(idMpEntidad);
		assertNotNull(res);
		
		when(ipPlanDistritalEliminarService.eliminarMpEntidad(idMpEntidad)).thenThrow(new SpddExceptions());
		res = controller.eliminarMpIndicadorEntidad(idMpEntidad);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400);
	}
	
	
	
	
}
