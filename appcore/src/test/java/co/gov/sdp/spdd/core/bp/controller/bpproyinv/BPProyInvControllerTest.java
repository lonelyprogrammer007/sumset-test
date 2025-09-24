package co.gov.sdp.spdd.core.bp.controller.bpproyinv;

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
import co.gov.sdp.nhspdd.common.dto.bp.BpAporteCiudadanoDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpIniciativaCiudadanaDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvAporteDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvFinanciaDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvEtnicoDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvLocalizaYTerritorizacionDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvPoblacionDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvIniciativaDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyectoInversionDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.nhspdd.common.util.NHSPDDMensajes;
import co.gov.sdp.nhspdd.common.util.PaqueteMensajeEnum;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.spdd.core.bp.icontroller.bpproyinv.IBPProyInvController;
import co.gov.sdp.spdd.core.bp.iservice.bpproyinv.IBPProyInvConsultarService;
import co.gov.sdp.spdd.core.bp.iservice.bpproyinv.IBPProyInvEliminarService;
import co.gov.sdp.spdd.core.bp.iservice.bpproyinv.IBPProyInvModificarService;
import co.gov.sdp.spdd.core.bp.iservice.bpproyinv.IBPProyInvRegistrarService;

@SpringBootTest(classes = { BPProyInvController.class })
@RunWith(SpringRunner.class)
class BPProyInvControllerTest {
	
	@Autowired
	IBPProyInvController bPProyInvController;
	
	@MockBean
	IBPProyInvRegistrarService bPProyInvRegistrarService;

	@MockBean
	IBPProyInvConsultarService bPProyInvConsultarService;
	
	@MockBean
	IBPProyInvModificarService bPProyInvModificarService;

	@MockBean
	IBPProyInvEliminarService bPProyInvEliminarService;
	
	@MockBean
	SPDDLogger spddLogger;

	@Test
	public void testConsultarBpProyectoInversionPorFiltro() throws SpddExceptions {
		
		BpProyectoInversionDTO peticion =  new BpProyectoInversionDTO();
		
		GenericoDTO res;
		
		when(bPProyInvConsultarService.consultarBpProyectoInversionPorFiltro(peticion)).thenReturn(new GenericoDTO());
		res = bPProyInvController.consultarBpProyectoInversionPorFiltro(peticion);
		assertNotNull(res);
		
		when(bPProyInvConsultarService.consultarBpProyectoInversionPorFiltro(peticion)).thenThrow(new SpddExceptions());
		res = bPProyInvController.consultarBpProyectoInversionPorFiltro(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
		
	}
	
	@Test
	public void testConsultarProyectoInversionTodos() throws SpddExceptions
	{
		BpProyectoInversionDTO peticion = new BpProyectoInversionDTO();
		
		when(bPProyInvConsultarService.consultarProyectoInversionTodos(peticion)).thenReturn(new GenericoDTO());
		GenericoDTO res = bPProyInvController.consultarProyectoInversionTodos(peticion);
		assertNotNull(res);
		
		when(bPProyInvConsultarService.consultarProyectoInversionTodos(peticion)).thenThrow(new SpddExceptions());
		res = bPProyInvController.consultarProyectoInversionTodos(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
		
	}
	
	@Test
	public void testConsultarTodosBpAporteCiudadanoSinRelacionConProyectoInversion() throws SpddExceptions
	{
		Long idProyecto = 1L;
		
		when(bPProyInvConsultarService.consultarTodosBpAporteCiudadanoSinRelacionConProyectoInversion(idProyecto)).thenReturn(new GenericoDTO());
		GenericoDTO res = bPProyInvController.consultarTodosBpAporteCiudadanoSinRelacionConProyectoInversion(idProyecto);
		assertNotNull(res);
		
		when(bPProyInvConsultarService.consultarTodosBpAporteCiudadanoSinRelacionConProyectoInversion(idProyecto)).thenThrow(new SpddExceptions());
		res = bPProyInvController.consultarTodosBpAporteCiudadanoSinRelacionConProyectoInversion(idProyecto);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
	}

	@Test
	public void testRegistrarProyectoInversionTABIndentificacionProyecto() throws SpddExceptions, JsonProcessingException {
		
		BpProyectoInversionDTO peticion =  new BpProyectoInversionDTO();
		peticion.setCodigoProyecto(123L);
		peticion.setNombreProyecto("proyectoprueba");
		peticion.setCodigoBpin("1001");
		peticion.setNombreBpin("pruebabpin");
		peticion.setIdLsEtapa(1L);
		peticion.setIdLsEstado(2L);
		peticion.setBloqueo(1L);
		peticion.setCodigoEntidad("101");
		peticion.setIdsTipoProy("1,2");
		
		BpProyectoInversionDTO res;
		
		when(bPProyInvRegistrarService.registrarProyectoInversionIndentificacionProyecto(peticion)).thenReturn(new BpProyectoInversionDTO());
		res = bPProyInvController.registrarProyectoInversionTABIndentificacionProyecto(peticion);
		assertNotNull(res);
		
		when(bPProyInvRegistrarService.registrarProyectoInversionIndentificacionProyecto(peticion)).thenThrow(new SpddExceptions());
		res = bPProyInvController.registrarProyectoInversionTABIndentificacionProyecto(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
		assertEquals(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ERROR_VALIDACION,
				PaqueteMensajeEnum.ERRORES, null), res.getMsgRespuesta());
		
		peticion.setCodigoProyecto(null);
		res = bPProyInvController.registrarProyectoInversionTABIndentificacionProyecto(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
		assertEquals(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ERROR_OBLIGATORIEDAD,
						PaqueteMensajeEnum.ERRORES, peticion.getLenguaje()), res.getMsgRespuesta());
	}
	
	@Test
	public void testRegistrarVariosBpAportesCiudadanosCargadosPorArchivoConBpProyectoInversion() throws JsonProcessingException, SpddExceptions
	{
		BpProyInvAporteDTO peticion = new BpProyInvAporteDTO();
		peticion.setIdProyInversion(1L);;
		peticion.setIdsAportes("1");
		
		when(bPProyInvRegistrarService.registrarBpProyInvAporte(peticion)).thenReturn(new BpProyInvAporteDTO());
		BpProyInvAporteDTO res = bPProyInvController.registrarVariosBpAportesCiudadanosCargadosPorArchivoConBpProyectoInversion(peticion);
		assertNotNull(res);
		
		when(bPProyInvRegistrarService.registrarBpProyInvAporte(peticion)).thenThrow(new SpddExceptions());
		res = bPProyInvController.registrarVariosBpAportesCiudadanosCargadosPorArchivoConBpProyectoInversion(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
		assertEquals(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ERROR_VALIDACION,
				PaqueteMensajeEnum.ERRORES, null), res.getMsgRespuesta());
		
		peticion.setIdProyInversion(null);
		res = bPProyInvController.registrarVariosBpAportesCiudadanosCargadosPorArchivoConBpProyectoInversion(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
		assertEquals(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ERROR_OBLIGATORIEDAD,
						PaqueteMensajeEnum.ERRORES, peticion.getLenguaje()), res.getMsgRespuesta());
	}

	@Test
	public void testModificarBpProyectoInversion() throws SpddExceptions {
		BpProyectoInversionDTO peticion =  new BpProyectoInversionDTO();
		peticion.setCodigoProyecto(123L);
		peticion.setNombreProyecto("proyectoprueba");
		peticion.setCodigoBpin("1001");
		peticion.setNombreBpin("pruebabpin");
		peticion.setIdLsEtapa(1L);
		peticion.setIdLsEstado(2L);
		peticion.setBloqueo(1L);
		peticion.setCodigoEntidad("101");
		peticion.setIdsTipoProy("1,2");
		
		BpProyectoInversionDTO res;
		
		when(bPProyInvModificarService.modificarProyectoInversion(peticion)).thenReturn(new BpProyectoInversionDTO());
		res = bPProyInvController.modificarBpProyectoInversion(peticion);
		assertNotNull(res);
		
		when(bPProyInvModificarService.modificarProyectoInversion(peticion)).thenThrow(new SpddExceptions());
		res = bPProyInvController.modificarBpProyectoInversion(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
	}

	@Test
	public void testConsultarBpProyectoInversionPorId() throws Exception {
		Long idProyecto = 1L;
		
		when(bPProyInvConsultarService.consultarBpProyectoInversionPorId(idProyecto)).thenReturn(new BpProyectoInversionDTO());
		BpProyectoInversionDTO res = bPProyInvController.consultarBpProyectoInversionPorId(idProyecto);
		assertNotNull(res);
		
		when(bPProyInvConsultarService.consultarBpProyectoInversionPorId(idProyecto)).thenThrow(new SpddExceptions());
		res = bPProyInvController.consultarBpProyectoInversionPorId(idProyecto);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
	}

	@Test
	public void testRegistrarBpAporteCiudadano() throws Exception {
		BpAporteCiudadanoDTO peticion = new BpAporteCiudadanoDTO();
		peticion.setConsecutivo(1L);
		peticion.setFuente("fuente");
		peticion.setAccion("accion");
		peticion.setIdArchivo(null);
		
		when(bPProyInvRegistrarService.registrarBPAporteCiudadano(peticion)).thenReturn(new BpAporteCiudadanoDTO());
		BpAporteCiudadanoDTO res = bPProyInvController.registrarBpAporteCiudadano(peticion);
		assertNotNull(res);
		
		peticion.setConsecutivo(null);
		when(bPProyInvRegistrarService.registrarBPAporteCiudadano(peticion)).thenReturn(new BpAporteCiudadanoDTO());
		res = bPProyInvController.registrarBpAporteCiudadano(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
		assertEquals(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ERROR_OBLIGATORIEDAD,
				PaqueteMensajeEnum.ERRORES, peticion.getLenguaje()), res.getMsgRespuesta());
		
		peticion.setConsecutivo(1L);
		when(bPProyInvRegistrarService.registrarBPAporteCiudadano(peticion)).thenThrow(new SpddExceptions());
		res = bPProyInvController.registrarBpAporteCiudadano(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
	}

	@Test
	public void testConsultarTodosBpProyInvAportePorIdProyInversionPaginado() throws Exception {
		BpProyInvAporteDTO peticion = new BpProyInvAporteDTO();
		
		when(bPProyInvConsultarService.consultarTodosBpProyInvAportePorIdProyInversionPaginado(peticion)).thenReturn(new GenericoDTO());
		GenericoDTO res = bPProyInvController.consultarTodosBpProyInvAportePorIdProyInversionPaginado(peticion);
		assertNotNull(res);
		
		when(bPProyInvConsultarService.consultarTodosBpProyInvAportePorIdProyInversionPaginado(peticion)).thenThrow(new SpddExceptions());
		res = bPProyInvController.consultarTodosBpProyInvAportePorIdProyInversionPaginado(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);

	}

	@Test
	public void testConsultarBpAporteCiudadanoPorId() throws Exception {
		Long idAporte = 1L;
		
		when(bPProyInvConsultarService.consultarBpAporteCiudadanoPorId(idAporte)).thenReturn(new BpAporteCiudadanoDTO());
		BpAporteCiudadanoDTO res = bPProyInvController.consultarBpAporteCiudadanoPorId(idAporte);
		assertNotNull(res);
		
		when(bPProyInvConsultarService.consultarBpAporteCiudadanoPorId(idAporte)).thenThrow(new SpddExceptions());
		res = bPProyInvController.consultarBpAporteCiudadanoPorId(idAporte);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
	}

	@Test
	public void testEliminarBpProyInvAporte() throws Exception {
		Long idProyAporte = 1L;
		
		when(bPProyInvEliminarService.eliminarBpProyInvAporte(idProyAporte)).thenReturn(new BpProyInvAporteDTO());
		BpProyInvAporteDTO res = bPProyInvController.eliminarBpProyInvAporte(idProyAporte);
		assertNotNull(res);
		
		when(bPProyInvEliminarService.eliminarBpProyInvAporte(idProyAporte)).thenThrow(new SpddExceptions());
		res = bPProyInvController.eliminarBpProyInvAporte(idProyAporte);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
	}

	@Test
	public void testModificarBpAporteCiudadano() throws Exception {
		BpAporteCiudadanoDTO peticion = new BpAporteCiudadanoDTO();
		peticion.setConsecutivo(1L);
		peticion.setFuente("fuente");
		peticion.setAccion("accion");
		peticion.setIdArchivo(null);
		peticion.setIdAporte(1L);
		
		when(bPProyInvModificarService.modificarBpAporteCiudadano(peticion)).thenReturn(new BpAporteCiudadanoDTO());
		BpAporteCiudadanoDTO res = bPProyInvController.modificarBpAporteCiudadano(peticion);
		assertNotNull(res);
		
		peticion.setIdAporte(null);
		when(bPProyInvModificarService.modificarBpAporteCiudadano(peticion)).thenReturn(new BpAporteCiudadanoDTO());
		res = bPProyInvController.modificarBpAporteCiudadano(peticion);
		assertNotNull(res);
		assertEquals(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ERROR_OBLIGATORIEDAD,
				PaqueteMensajeEnum.ERRORES, peticion.getLenguaje()), res.getMsgRespuesta());
		
		when(bPProyInvModificarService.modificarBpAporteCiudadano(peticion)).thenThrow(new SpddExceptions());
		res = bPProyInvController.modificarBpAporteCiudadano(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
	}

	@Test
	public void testColsultarTodosBpAporteCiudadanoCargadosPorArchivosConRelacionConProyectoInversion() throws Exception {
		Long idProyecto = 1L;
		
		when(bPProyInvConsultarService.colsultarTodosBpAporteCiudadanoCargadosPorArchivosConRelacionConProyectoInversion(idProyecto)).thenReturn(new GenericoDTO());
		GenericoDTO res = bPProyInvController.colsultarTodosBpAporteCiudadanoCargadosPorArchivosConRelacionConProyectoInversion(idProyecto);
		assertNotNull(res);
		
		when(bPProyInvConsultarService.colsultarTodosBpAporteCiudadanoCargadosPorArchivosConRelacionConProyectoInversion(idProyecto)).thenThrow(new SpddExceptions());
		res = bPProyInvController.colsultarTodosBpAporteCiudadanoCargadosPorArchivosConRelacionConProyectoInversion(idProyecto);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
		
	}

	@Test
	public void testConsultarTodosBpAportesCiudadanosCargadosPorArchivos() throws Exception {
		when(bPProyInvConsultarService.consultarTodosBpAportesCiudadanosCargadosPorArchivos()).thenReturn(new GenericoDTO());
		GenericoDTO res = bPProyInvController.consultarTodosBpAportesCiudadanosCargadosPorArchivos();
		assertNotNull(res);
		
		when(bPProyInvConsultarService.consultarTodosBpAportesCiudadanosCargadosPorArchivos()).thenThrow(new SpddExceptions());
		res = bPProyInvController.consultarTodosBpAportesCiudadanosCargadosPorArchivos();
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
	}

	@Test
	public void testConsultarTodosBpIniciativaCiudadanaViablesFiltradas() throws Exception {
		BpIniciativaCiudadanaDTO peticion = new BpIniciativaCiudadanaDTO();
		
		when(bPProyInvConsultarService.consultarTodosBpIniciativaCiudadanaViablesFiltradas(peticion)).thenReturn(new GenericoDTO());
		GenericoDTO res = bPProyInvController.consultarTodosBpIniciativaCiudadanaViablesFiltradas(peticion);
		assertNotNull(res);
		
		when(bPProyInvConsultarService.consultarTodosBpIniciativaCiudadanaViablesFiltradas(peticion)).thenThrow(new SpddExceptions());
		res = bPProyInvController.consultarTodosBpIniciativaCiudadanaViablesFiltradas(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
	}

	@Test
	public void testConsultarTodosBpIniciativaCiudadanaRelacionadasConProyectoInversion() throws Exception {
		Long idProyecto = 1L;
		
		when(bPProyInvConsultarService.consultarTodosBpIniciativaCiudadanaRelacionadasConProyectoInversion(idProyecto)).thenReturn(new GenericoDTO());
		GenericoDTO res = bPProyInvController.consultarTodosBpIniciativaCiudadanaRelacionadasConProyectoInversion(idProyecto);
		assertNotNull(res);
		
		when(bPProyInvConsultarService.consultarTodosBpIniciativaCiudadanaRelacionadasConProyectoInversion(idProyecto)).thenThrow(new SpddExceptions());
		res = bPProyInvController.consultarTodosBpIniciativaCiudadanaRelacionadasConProyectoInversion(idProyecto);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
	}

	@Test
	public void testRegistrarVariasBpIniciativasCiudadanasViablesConBpProyectoInversion() throws Exception {
		BpProyInvIniciativaDTO peticion = new BpProyInvIniciativaDTO();
		peticion.setIdProyInversion(1L);
		
		when(bPProyInvRegistrarService.registrarBpProyInvIniciativa(peticion)).thenReturn(new BpProyInvIniciativaDTO());
		BpProyInvIniciativaDTO res = bPProyInvController.registrarVariasBpIniciativasCiudadanasViablesConBpProyectoInversion(peticion);
		assertNotNull(res);
		
		peticion.setIdProyInversion(null);
		res = bPProyInvController.registrarVariasBpIniciativasCiudadanasViablesConBpProyectoInversion(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
		
		peticion.setIdProyInversion(1L);
		when(bPProyInvRegistrarService.registrarBpProyInvIniciativa(peticion)).thenThrow(new SpddExceptions());
		res = bPProyInvController.registrarVariasBpIniciativasCiudadanasViablesConBpProyectoInversion(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
	}

	@Test
	public void testEliminarBpProyectoInversion() throws Exception {
		Long idProyectoInversion = 1L;
		
		when(bPProyInvEliminarService.eliminarBpProyectoInversion(idProyectoInversion)).thenReturn(new BpProyectoInversionDTO());
		BpProyectoInversionDTO res = bPProyInvController.eliminarBpProyectoInversion(idProyectoInversion);
		assertNotNull(res);
		
		when(bPProyInvEliminarService.eliminarBpProyectoInversion(idProyectoInversion)).thenThrow(new SpddExceptions());
		res = bPProyInvController.eliminarBpProyectoInversion(idProyectoInversion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
	}
	
	@Test
	public void testRegistrarBpProyectoInversionLocaliza() throws SpddExceptions {
		when(bPProyInvRegistrarService.registrarBPProyectoInvLocaliza(new BpProyInvLocalizaYTerritorizacionDTO())).thenReturn(new BpProyectoInversionDTO());
		BpProyectoInversionDTO res = bPProyInvController.registrarBpProyectoInversionLocaliza(new BpProyInvLocalizaYTerritorizacionDTO());
		assertNotNull(res);
	}

	@Test
	public void testRegistrarBpProyInvEspecif() throws Exception {
	}

	@Test
	public void testRegistrarBpProyInvMetaPlan() throws Exception {
	}

	@Test
	public void testRegistrarBpProyInvProducto() throws Exception {
	}

	@Test
	public void testRegistrarBpProyInvActividad() throws Exception {
	}

	@Test
	public void testConsultarTodosProyInvFianciaPorIdProyInversionPaginado() throws Exception {
		
		BpProyInvFinanciaDTO peticion = new BpProyInvFinanciaDTO();
		
		when(bPProyInvConsultarService.consultarTodosProyInvFianciaPorIdProyInversionPaginado(peticion)).thenReturn(new GenericoDTO());
		GenericoDTO res = bPProyInvController.consultarTodosProyInvFianciaPorIdProyInversionPaginado(peticion);
		assertNotNull(res);
		
		when(bPProyInvConsultarService.consultarTodosProyInvFianciaPorIdProyInversionPaginado(peticion)).thenThrow(new SpddExceptions());
		res = bPProyInvController.consultarTodosProyInvFianciaPorIdProyInversionPaginado(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
		
	}

	@Test
	public void testRegistrarBpProyInvFinancia() throws Exception {
		BpProyInvFinanciaDTO peticion = new BpProyInvFinanciaDTO();
		//peticion.setIdProyInversion(1L);
		
		
		
		when(bPProyInvRegistrarService.registrarBpProyInvFinancia(peticion)).thenReturn(new BpProyInvFinanciaDTO());
		BpProyInvFinanciaDTO res =bPProyInvController.registrarBpProyInvFinancia(peticion);
		assertNotNull(res);
		
		
		when(bPProyInvRegistrarService.registrarBpProyInvFinancia(peticion)).thenThrow(new SpddExceptions());
		res =bPProyInvController.registrarBpProyInvFinancia(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
		
	}

	public void testConsultarTodosProyInvPoblacionAsociadosAProyectoInversion() throws Exception {
		
		BpProyInvPoblacionDTO peticion = new BpProyInvPoblacionDTO();
		
		GenericoDTO res;

		when(bPProyInvConsultarService.consultarGruposEtarios(peticion)).thenReturn(new GenericoDTO());
		res = bPProyInvController.consultarGruposEtarios(peticion);
		assertNotNull(res);
		
		when(bPProyInvConsultarService.consultarGruposEtarios(peticion)).thenThrow(new SpddExceptions());
		res = bPProyInvController.consultarGruposEtarios(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
	}

	@Test
	public void testConsultarTodosProyInvEtnicoAsociadosAProyectoInversion() throws Exception {
		
		BpProyInvEtnicoDTO peticion = new BpProyInvEtnicoDTO();
		
		GenericoDTO res;

		when(bPProyInvConsultarService.consultarTodosProyInvEtnicoAsociadosAProyectoInversion(peticion)).thenReturn(new GenericoDTO());
		res = bPProyInvController.consultarTodosProyInvEtnicoAsociadosAProyectoInversion(peticion);
		assertNotNull(res);
		
		when(bPProyInvConsultarService.consultarTodosProyInvEtnicoAsociadosAProyectoInversion(peticion)).thenThrow(new SpddExceptions());
		res = bPProyInvController.consultarTodosProyInvEtnicoAsociadosAProyectoInversion(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
		
	}
	
	@Test
	public void testRegistrarVariosBpProyInvPoblacionAsociadoAProyInversion() throws Exception {
		
		BpProyInvPoblacionDTO peticion = new BpProyInvPoblacionDTO();
		when(bPProyInvRegistrarService.registrarBpProyInvPoblacionAsociadoaProyInv(peticion)).thenReturn(new BpProyInvPoblacionDTO());
		
		
		BpProyInvPoblacionDTO res = bPProyInvController.registrarBpProyInvPoblacionAsociadoAProyInversion(new BpProyInvPoblacionDTO());
		assertNotNull(res);
		
	}

<<<<<<< HEAD
	@Test
	public void testRegistrarBpProyInvEtnico() throws Exception {
		throw new RuntimeException("not yet implemented");
	}
=======
>>>>>>> developer
	
}
