package co.gov.sdp.spdd.core.controller.presupuesto;

import static org.assertj.core.api.Assertions.assertThat;
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
import co.gov.sdp.nhspdd.common.dto.InformacionPresupuestalDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.spdd.core.icontroller.presupuesto.IInformacionPresupuestalPresupuestoController;
import co.gov.sdp.spdd.core.iservice.presupuesto.IInformacionPresupuestalPresupuestoConsultar;
import co.gov.sdp.spdd.core.iservice.presupuesto.IInformacionPresupuestalPresupuestoEliminar;
import co.gov.sdp.spdd.core.iservice.presupuesto.IInformacionPresupuestalPresupuestoModificar;
import co.gov.sdp.spdd.core.iservice.presupuesto.IInformacionPresupuestalPresupuestoRegistrar;

@SpringBootTest(classes = { InformacionPresupuestalPresupuestoController.class })
@RunWith(SpringRunner.class)
class InformacionPresupuestalPresupuestoControllerTest {
	
	@Autowired
	IInformacionPresupuestalPresupuestoController info;
	
	// Servicio de consulta para informacion presupuestal
	@MockBean
	IInformacionPresupuestalPresupuestoConsultar consultar;

	// Servicio de eliminacion para informacion presupuestal
	@MockBean
	IInformacionPresupuestalPresupuestoEliminar eliminar;

	// Servicio de modificacion para informacion presupuestal
	@MockBean
	IInformacionPresupuestalPresupuestoModificar modificar;

	// Servicio de registro para informacion presupuestal
	@MockBean
	IInformacionPresupuestalPresupuestoRegistrar registrar;

	/**
	 * Logger
	 */
	@MockBean
	SPDDLogger spddLogger;

	@Test
	void testCrearInformacionPresupuestal() throws SpddExceptions, JsonProcessingException {
		InformacionPresupuestalDTO peticion = new InformacionPresupuestalDTO();
		peticion.setApropiacionDefinitiva(1D);
		peticion.setApropiacionReserva(1D);
		peticion.setCodigoClasificacionPresupuestal("clasi1");
		peticion.setMes(1L);
		peticion.setYear(2014L);
		peticion.setCodigoDistrital("1234");
		peticion.setGirosVigencia(1D);
		peticion.setCodigoProyecto(1L);
		peticion.setRecursosSuspendidos(1D);
		peticion.setCodigoInterno(1L);
		peticion.setConstitucionReserva(200D);
		peticion.setNombreProyecto("proyecto11");
		peticion.setEjecucionGiroReservas(100D);
		peticion.setIdPlanDesarrollo(100L);
		InformacionPresupuestalDTO res;
		when(registrar.crearInformacionPresupuestal(peticion)).thenReturn(peticion);
		res = info.crearInformacionPresupuestal(peticion);
		assertNotNull(res);
		
		
		when(registrar.crearInformacionPresupuestal(peticion)).thenThrow(new SpddExceptions());
		res = info.crearInformacionPresupuestal(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
		
		peticion.setCodigoInterno(null);
		peticion.setConstitucionReserva(null);
		peticion.setNombreProyecto(null);
		peticion.setEjecucionGiroReservas(null);
		peticion.setIdPlanDesarrollo(null);
		
		res = info.crearInformacionPresupuestal(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
		
	}

	@Test
	void testModificarInformacionPresupuestal() throws SpddExceptions {
		InformacionPresupuestalDTO peticion = new InformacionPresupuestalDTO();
		peticion.setIdInfoPresupuestal(1L);
		peticion.setApropiacionDefinitiva(1D);
		peticion.setApropiacionReserva(1D);
		peticion.setCodigoClasificacionPresupuestal("clasi1");
		peticion.setMes(1L);
		peticion.setYear(2014L);
		peticion.setCodigoDistrital("1234");
		peticion.setGirosVigencia(1D);
		peticion.setCodigoProyecto(1L);
		peticion.setRecursosSuspendidos(1D);
		peticion.setCodigoInterno(1L);
		peticion.setConstitucionReserva(200D);
		peticion.setNombreProyecto("proyecto11");
		peticion.setEjecucionGiroReservas(100D);
		peticion.setIdPlanDesarrollo(100L);
		InformacionPresupuestalDTO res;
		when(modificar.modificarInformacionPresupuestal(peticion)).thenReturn(peticion);
		res = info.modificarInformacionPresupuestal(peticion);
		assertNotNull(res);
		
		
		when(modificar.modificarInformacionPresupuestal(peticion)).thenThrow(new SpddExceptions());
		res = info.modificarInformacionPresupuestal(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
		peticion.setIdInfoPresupuestal(null);
		peticion.setCodigoInterno(null);
		peticion.setConstitucionReserva(null);
		peticion.setNombreProyecto(null);
		peticion.setEjecucionGiroReservas(null);
		peticion.setIdPlanDesarrollo(null);
		
		res = info.modificarInformacionPresupuestal(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
	}

	@Test
	void testObtenerInformacionPresupuestalTodos() throws SpddExceptions {
//		GenericoDTO res;
//		when(consultar.obtenerInformacionPresupuestalTodos()).thenReturn(new GenericoDTO());
//		res = info.obtenerInformacionPresupuestalTodos();
//		assertNotNull(res);
//		
//		when(consultar.obtenerInformacionPresupuestalTodos()).thenThrow(new SpddExceptions());
//		res = info.obtenerInformacionPresupuestalTodos();
//		assertNotNull(res);
//		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
	}

	@Test
	void testObtenerTodos() throws SpddExceptions, JsonProcessingException {
		InformacionPresupuestalDTO peticion = new InformacionPresupuestalDTO();
		GenericoDTO res;
		when(consultar.obtenerPaginado(peticion)).thenReturn(new GenericoDTO());
		res = info.obtenerTodos(peticion);
		assertNotNull(res);
		
		when(consultar.obtenerPaginado(peticion)).thenThrow(new SpddExceptions());
		res = info.obtenerTodos(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
	}

	@Test
	void testObtenerInformacionPresupuestalPorPlanDesarrollo() throws SpddExceptions {
		Long codigo = 1L;
		GenericoDTO res;
		when(consultar.obtenerInformacionPresupuestalPorPlanDesarrollo(codigo)).thenReturn(new GenericoDTO());
		res = info.obtenerInformacionPresupuestalPorPlanDesarrollo(codigo);
		assertNotNull(res);
		
		when(consultar.obtenerInformacionPresupuestalPorPlanDesarrollo(codigo)).thenThrow(new SpddExceptions());
		res = info.obtenerInformacionPresupuestalPorPlanDesarrollo(codigo);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
	}

	@Test
	void testObtenerInformacionPresupuestalPorEntidad() throws SpddExceptions {
		Long codigo = 1L;
		GenericoDTO res;
		when(consultar.obtenerInformacionPresupuestalPorEntidad(codigo)).thenReturn(new GenericoDTO());
		res = info.obtenerInformacionPresupuestalPorEntidad(codigo);
		
		assertNotNull(res);
		
		when(consultar.obtenerInformacionPresupuestalPorEntidad(codigo)).thenThrow(new SpddExceptions());
		res = info.obtenerInformacionPresupuestalPorEntidad(codigo);
		
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
	}

	@Test
	void testObtenerPorArchivoInfo() {
		Long id = 1L;
		GenericoDTO res;
		when(consultar.obtenerPorArchivoInfo(id)).thenReturn(new GenericoDTO());
		res = info.obtenerPorArchivoInfo(id);
		assertNotNull(res);
	}

	@Test
	void testEliminarInformacionPresupuestal() throws SpddExceptions {
		Long idInformacionPresupuestal = 1L;
		InformacionPresupuestalDTO res;
		when(eliminar.eliminarInformacionPresupuestal(idInformacionPresupuestal)).thenReturn(new InformacionPresupuestalDTO());
		res = info.eliminarInformacionPresupuestal(idInformacionPresupuestal);
		assertNotNull(res);
		
		when(eliminar.eliminarInformacionPresupuestal(idInformacionPresupuestal)).thenThrow(new SpddExceptions());
		res = info.eliminarInformacionPresupuestal(idInformacionPresupuestal);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
		
	}

}
