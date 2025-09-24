package co.gov.sdp.spdd.data.sesionfacade.impl;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpAporteCiudadanoDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvAnualizaDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvAporteDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvFinanciaDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvEtnicoDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvLocalizaDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvPoblacionDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvTipoDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyectoInversionDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.spdd.data.serviciofacade.bp.SessionConsultaBP;
import co.gov.sdp.spdd.data.serviciofacade.bp.SessionEliminarBP;
import co.gov.sdp.spdd.data.serviciofacade.bp.SessionModificarBP;
import co.gov.sdp.spdd.data.serviciofacade.bp.SessionRegistroBP;

@RunWith(SpringRunner.class)
public class SessionFacadeBPTest {

	@TestConfiguration
	static class SessionFacadeBPTestContextConfiguration {
		@Bean
		public SessionFacadeBP sessionFacadeBP() {
			return new SessionFacadeBP();
		}
	}

	@Autowired
	SessionFacadeBP sessionFacadeBP;

	@MockBean
	SessionConsultaBP sessionConsultaBP;

	@MockBean
	SessionEliminarBP sessionEliminarBP;

	@MockBean
	SessionRegistroBP sessionRegistroBP;

	@MockBean
	SessionModificarBP sessionModificarBP;

	@MockBean
	SPDDLogger spddLogger;

	@Test
	public void testConsultarBpProyectoInversionPorFiltro() {

	}

//	@Test
//	public void testConsultarBpProyInvLineaPorIdProyectoInversion() throws SpddExceptions {
//		Long idProyecto = 1L;
//		when(sessionConsultaBP.consultarBpProyInvTipoPorIdProyectoInversion(idProyecto)).thenReturn(new GenericoDTO());
//		GenericoDTO res =  sessionFacadeBP.consultarBpProyInvLineaPorIdProyectoInversion(idProyecto);
//		assertNotNull(res);
//	}

	@Test
	public void testConsultarBpProyInvTipoPorIdProyectoInversion() throws SpddExceptions {
		Long idProyecto = 1L;
		when(sessionConsultaBP.consultarBpProyInvTipoPorIdProyectoInversion(idProyecto)).thenReturn(new GenericoDTO());
		GenericoDTO res = sessionFacadeBP.consultarBpProyInvTipoPorIdProyectoInversion(idProyecto);
		assertNotNull(res);
	}

	@Test
	public void testConsultarBpProyInvTipoPorIdLsTipoYIdProyInv() throws SpddExceptions {
		Long idLsTipo = 1L;
		Long idProyInversion = 2L;
		BpProyInvTipoDTO res;
		when(sessionConsultaBP.consultarBpProyInvTipoPorIdLsTipoYIdProyInv(idLsTipo, idProyInversion))
				.thenReturn(new BpProyInvTipoDTO());
		res = sessionFacadeBP.consultarBpProyInvTipoPorIdLsTipoYIdProyInv(idLsTipo, idProyInversion);
		assertNotNull(res);
	}

	@Test
	public void testConsultarBpAporteCiudadanoTodos() throws SpddExceptions {
		when(sessionConsultaBP.consultarBpAporteCiudadanoTodos()).thenReturn(new GenericoDTO());
		GenericoDTO res = sessionFacadeBP.consultarBpAporteCiudadanoTodos();
		assertNotNull(res);
	}

	@Test
	public void testConsultarTodosBpAporteCiudadanoSinRelacionConProyectoInversion() throws SpddExceptions {
		Long idProyecto = 1L;
		when(sessionConsultaBP.consultarTodosBpAporteCiudadanoSinRelacionConProyectoInversion(idProyecto))
				.thenReturn(new GenericoDTO());
		GenericoDTO res = sessionFacadeBP.consultarTodosBpAporteCiudadanoSinRelacionConProyectoInversion(idProyecto);
		assertNotNull(res);
	}

	@Test
	public void testFiltrarIniciativaCiudadana() {

	}

	@Test
	public void testGuardarProyectoInversionIndentificacionProyecto() throws SpddExceptions {
		BpProyectoInversionDTO bpProyectoInversionDTO = new BpProyectoInversionDTO();

		BpProyectoInversionDTO res;
		when(sessionRegistroBP.guardarProyectoInversionTABIndentificacionProyecto(bpProyectoInversionDTO))
				.thenReturn(bpProyectoInversionDTO);
		res = sessionFacadeBP.guardarProyectoInversionIndentificacionProyecto(bpProyectoInversionDTO);
		assertNotNull(res);
	}

	@Test
	public void testguardarProyInvTipo() throws SpddExceptions {
		BpProyInvTipoDTO bpProyInvTipoDTO = new BpProyInvTipoDTO();
		bpProyInvTipoDTO.setIdTipo(1L);
		bpProyInvTipoDTO.setIdLsTipo(2L);
		bpProyInvTipoDTO.setIdProyInversion(3L);

		BpProyInvTipoDTO res;

		when(sessionConsultaBP.consultarBpProyInvTipoPorIdLsTipoYIdProyInv(bpProyInvTipoDTO.getIdLsTipo(),
				bpProyInvTipoDTO.getIdProyInversion())).thenReturn(new BpProyInvTipoDTO());
		when(sessionRegistroBP.guardarProyInvTipo(bpProyInvTipoDTO)).thenReturn(bpProyInvTipoDTO);
		res = sessionFacadeBP.guardarProyInvTipo(bpProyInvTipoDTO);
		assertNotNull(res);
		assertNotNull(res.getIdTipo());

		when(sessionConsultaBP.consultarBpProyInvTipoPorIdLsTipoYIdProyInv(bpProyInvTipoDTO.getIdLsTipo(),
				bpProyInvTipoDTO.getIdProyInversion())).thenReturn(bpProyInvTipoDTO);
		res = sessionFacadeBP.guardarProyInvTipo(bpProyInvTipoDTO);
		assertNotNull(res);
		assertNull(res.getIdTipo());
	}

	@Test
	public void testGuardarBpProyInvAporte() throws SpddExceptions {
		BpProyInvAporteDTO bpProyInvAporteDTO = new BpProyInvAporteDTO();
		bpProyInvAporteDTO.setIdProyAporte(1L);
		bpProyInvAporteDTO.setIdProyInversion(1L);
		bpProyInvAporteDTO.setIdProyAporte(1L);

		when(sessionConsultaBP.consultarBpProyInvAportePorIdAporteYIdProyInversion(bpProyInvAporteDTO.getIdAporte(),
				bpProyInvAporteDTO.getIdProyInversion())).thenReturn(new BpProyInvAporteDTO());
		when(sessionRegistroBP.guardarBpProyInvAporte(bpProyInvAporteDTO)).thenReturn(bpProyInvAporteDTO);
		BpProyInvAporteDTO res = sessionFacadeBP.guardarBpProyInvAporte(bpProyInvAporteDTO);
		assertNotNull(res);
		assertNotNull(res.getIdProyAporte());

		when(sessionConsultaBP.consultarBpProyInvAportePorIdAporteYIdProyInversion(bpProyInvAporteDTO.getIdAporte(),
				bpProyInvAporteDTO.getIdProyInversion())).thenReturn(bpProyInvAporteDTO);
		res = sessionFacadeBP.guardarBpProyInvAporte(bpProyInvAporteDTO);
		assertNotNull(res);
		assertNull(res.getIdProyAporte());
	}

	@Test
	public void testEliminarBpProyInvTiposDeIdProyectoInversion() throws SpddExceptions {
		sessionFacadeBP.eliminarBpProyInvTiposDeIdProyectoInversion(1L);
	}

	@Test
	public void testModificarProyectoInversionProyecto() throws SpddExceptions {
		BpProyectoInversionDTO bpProyectoInversionDTO = new BpProyectoInversionDTO();

		BpProyectoInversionDTO res;
		when(sessionRegistroBP.guardarProyectoInversionTABIndentificacionProyecto(bpProyectoInversionDTO))
				.thenReturn(bpProyectoInversionDTO);
		res = sessionFacadeBP.modificarProyectoInversionProyecto(bpProyectoInversionDTO);
		assertNotNull(res);
	}

	@Test
	public void testConsultarBpProyectoInversionTodos() throws Exception {
		when(sessionConsultaBP.consultarBpProyectoInversionTodos()).thenReturn(new GenericoDTO());
		GenericoDTO res = sessionFacadeBP.consultarBpProyectoInversionTodos();
		assertNotNull(res);
	}

	@Test
	public void testConsultarProyInvPorId() throws Exception {
		Long idProyecto = 1L;

		when(sessionConsultaBP.consultarProyInvPorId(idProyecto)).thenReturn(new BpProyectoInversionDTO());
		BpProyectoInversionDTO res = sessionFacadeBP.consultarProyInvPorId(idProyecto);
		assertNotNull(res);
	}

	@Test
	public void testConsultarTodosBpProyInvAportePorIdProyInversionPaginado() throws Exception {
		BpProyInvAporteDTO bpProyInvAporteDTO = new BpProyInvAporteDTO();

		when(sessionConsultaBP.consultarTodosBpProyInvAportePorIdProyInversionPaginado(bpProyInvAporteDTO))
				.thenReturn(new GenericoDTO());
		GenericoDTO res = sessionFacadeBP.consultarTodosBpProyInvAportePorIdProyInversionPaginado(bpProyInvAporteDTO);
		assertNotNull(res);
	}

	@Test
	public void testConsultarBpAporteCiudadanoPorId() throws Exception {
		Long idAporte = 1L;

		when(sessionConsultaBP.consultarBpAporteCiudadanoPorId(idAporte)).thenReturn(new BpAporteCiudadanoDTO());
		BpAporteCiudadanoDTO res = sessionFacadeBP.consultarBpAporteCiudadanoPorId(idAporte);
		assertNotNull(res);
	}

	@Test
	public void testGuardarBPProyectoInvLocaliza() throws SpddExceptions {
		BpProyInvLocalizaDTO bpProyInvLocalizaDTO = new BpProyInvLocalizaDTO();
		when(sessionRegistroBP.guardarBPProyectoInvLocaliza(bpProyInvLocalizaDTO)).thenReturn(bpProyInvLocalizaDTO);
		BpProyInvLocalizaDTO res = sessionFacadeBP.guardarBPProyectoInvLocaliza(bpProyInvLocalizaDTO);
		assertNotNull(res);
	}

	@Test
	public void testColsultarTodosBpAporteCiudadanoCargadosPorArchivosConRelacionConProyectoInversion()
			throws Exception {
		Long idProyecto = 1L;

		when(sessionConsultaBP
				.obtenerTodosBpAporteCiudadanoCargadosPorArchivosConRelacionConProyectoInversion(idProyecto))
						.thenReturn(new GenericoDTO());
		GenericoDTO res = sessionFacadeBP
				.colsultarTodosBpAporteCiudadanoCargadosPorArchivosConRelacionConProyectoInversion(idProyecto);
		assertNotNull(res);
	}

	@Test
		public void testConsultarTodosBpProyInvLineaPorIdProyectoInversion() throws Exception {
		}

	@Test
	public void testGuardarProyInvTipo() throws Exception {
	}

	@Test
	public void testGuardarBPAporteCiudadano() throws Exception {
	}

	@Test
	public void testGuardarIniciativaCiudadana() throws Exception {
	}

	@Test
	public void testEliminarGruposEtarios() throws Exception {
	}

	@Test
	public void testEliminarIniciativaCiudadana() throws Exception {
	}

	@Test
	public void testEliminarUbicaciones() throws Exception {
	}

	@Test
	public void testGuardarGruposEtarios() throws Exception {
	}

	@Test
	public void testGuardarUbicacionIniciativaCiudadana() throws Exception {
	}

	@Test
	public void testObtenerCondicionesPorIniciativa() throws Exception {
	}

	@Test
	public void testGuardarIniciativaCondicion() throws Exception {
	}

	@Test
	public void testEliminarTodasCondicionIniciativa() throws Exception {
	}

	@Test
	public void testConsultarBpIniciativaCiudadanaPorId() throws Exception {
	}

	@Test
	public void testConsultarGruposEtariosPorIniciativa() throws Exception {
	}

	@Test
	public void testConsultarUbicacionesGruposEtariosPorIniciativa() throws Exception {
	}

	@Test
	public void testEliminarBpProyInvAporte() throws Exception {
	}

	@Test
	public void testEliminarBpAporteCiudadano() throws Exception {
	}

	@Test
	public void testConsultarProyInvAportePorId() throws Exception {
	}

	@Test
	public void testModificarBpAporteCiudadano() throws Exception {
	}

	@Test
	public void testConsultarBpAporteCiudadanoPorAccionYFuenteYIdNivelPdd() throws Exception {
	}

	@Test
	public void testEliminarTodosBpProyInvAporteCargadosArchivoPorIdProyecto() throws Exception {
	}

	@Test
	public void testConsultarTodosBpAportesCiudadanosCargadosPorArchivos() throws Exception {
	}

	@Test
	public void testConsultarTodosBpIniciativaCiudadanaRelacionadasConProyectoInversion() throws Exception {
	}

	@Test
	public void testConsultarTodosBpProyInvIniciativaPorIdProyInversion() throws Exception {
	}

	@Test
	public void testEliminarTodosBpProyInvIniciativaPorIdProyInversion() throws Exception {
	}

	@Test
	public void testGuardarBpProyInvIniciativa() throws Exception {
	}

	@Test
	public void testEliminarTodosBpProyInvLocalizaPorIdProyecto() throws Exception {
	}

	@Test
	public void testConsultarBpProyInvEspecifPorId() throws Exception {
	}

	@Test
	public void testGuardarBpProyInvEspecif() throws Exception {
	}

	@Test
	public void testGuardarBpProyInvMetaPlan() throws Exception {
	}

	@Test
	public void testGuardarBpProyInvProducto() throws Exception {
	}

	@Test
	public void testConsultarBpProyInvActividadPorId() throws Exception {
	}

	@Test
	public void testGuardarBpProyInvActividad() throws Exception {
	}

	@Test
	public void testConsultarBpProyInvMetaPlanPorIdProyInvEspecif() throws Exception {
	}


	@Test
	public void testConsultarTodosProyInvAnualizaPorIdFuentePaginado() throws Exception {

		BpProyInvAnualizaDTO bpProyInvAnualizaDTO = new BpProyInvAnualizaDTO();
		when(sessionConsultaBP.consultarTodosProyInvAnualizaPorIdFuentePaginado(bpProyInvAnualizaDTO))
				.thenReturn(new GenericoDTO());
		GenericoDTO res = sessionFacadeBP.consultarTodosProyInvAnualizaPorIdFuentePaginado(bpProyInvAnualizaDTO);
		assertNotNull(res);

	}

	@Test
	public void testConsultarTodosProyInvFianciaPorIdProyInversionPaginado() throws Exception {
		BpProyInvFinanciaDTO bpProyInvFinanciaDTO = new BpProyInvFinanciaDTO();

		when(sessionConsultaBP.consultarTodosProyInvFianciaPorIdProyInversionPaginado(bpProyInvFinanciaDTO))
				.thenReturn(new GenericoDTO());
		GenericoDTO res = sessionFacadeBP.consultarTodosProyInvFianciaPorIdProyInversionPaginado(bpProyInvFinanciaDTO);
		assertNotNull(res);
	}

	@Test
	public void testRegistrarBpProyInvFinancia() throws Exception {

		BpProyInvFinanciaDTO peticion = new BpProyInvFinanciaDTO();
		peticion.setIdLsFuente(2L);
		peticion.setIdProyInversion(2l);

		when(sessionConsultaBP.consultarProyInvFinanciaPorIdLSFuYIdProy(peticion.getIdLsFuente(),
				peticion.getIdProyInversion())).thenReturn(new BpProyInvFinanciaDTO());
		when(sessionRegistroBP.registrarBpProyInvFinancia(peticion)).thenReturn(new BpProyInvFinanciaDTO());
		BpProyInvFinanciaDTO res = sessionFacadeBP.registrarBpProyInvFinancia(peticion);
		assertNotNull(res);

		BpProyInvFinanciaDTO auxBpProyInvFinanciaDTO = new BpProyInvFinanciaDTO();
		auxBpProyInvFinanciaDTO.setIdFuente(2l);

		when(sessionConsultaBP.consultarProyInvFinanciaPorIdLSFuYIdProy(peticion.getIdLsFuente(),
				peticion.getIdProyInversion())).thenReturn(auxBpProyInvFinanciaDTO);
		when(sessionRegistroBP.registrarBpProyInvFinancia(peticion)).thenReturn(new BpProyInvFinanciaDTO());
		res = sessionFacadeBP.registrarBpProyInvFinancia(peticion);
		assertNotNull(res);
	}

	@Test
	public void testConsultarProyInvFinanciaPorIdLSFuYIdProy() throws Exception {
		Long idLsFUente=1l, idProyectoInv=2l;
		
		BpProyInvFinanciaDTO auxBpProyInvFinanciaDTO = new BpProyInvFinanciaDTO();
		
		when(sessionConsultaBP.consultarProyInvFinanciaPorIdLSFuYIdProy(idLsFUente,idProyectoInv)).thenReturn(auxBpProyInvFinanciaDTO);
		BpProyInvFinanciaDTO res = sessionFacadeBP.consultarProyInvFinanciaPorIdLSFuYIdProy(idLsFUente,idProyectoInv);
		assertNotNull(res);
		
		auxBpProyInvFinanciaDTO.setIdFuente(1l);
		
		when(sessionConsultaBP.consultarProyInvFinanciaPorIdLSFuYIdProy(idLsFUente,idProyectoInv)).thenReturn(auxBpProyInvFinanciaDTO);
		res = sessionFacadeBP.consultarProyInvFinanciaPorIdLSFuYIdProy(idLsFUente,idProyectoInv);
		assertNotNull(res);
		
		
		
	}

	@Test
	public void testRegistrarBpProyInvAnualiza() throws Exception {
		
		BpProyInvAnualizaDTO peticion = new BpProyInvAnualizaDTO();
		when(sessionRegistroBP.registrarBpProyInvAnualiza(peticion)).thenReturn(new BpProyInvAnualizaDTO());
		BpProyInvAnualizaDTO res = sessionFacadeBP.registrarBpProyInvAnualiza(peticion);
		assertNotNull(res);
		
	}


	@Test
	public void testConsultarTodosProyInvPoblacionAsociadosAProyectoInversion() throws Exception {
		
		BpProyInvPoblacionDTO bpProyInvPoblacionDTO = new BpProyInvPoblacionDTO();
		GenericoDTO genericDTO = new GenericoDTO();
		when(sessionConsultaBP.consultarGruposEtarios(bpProyInvPoblacionDTO)).thenReturn(genericDTO);
		
		GenericoDTO res = sessionFacadeBP.consultarGruposEtarios(bpProyInvPoblacionDTO);
		assertNotNull(res);
		
	}


	@Test
	public void testConsultarTodosProyInvEtnicoAsociadosAProyectoInversion() throws Exception {
		
		BpProyInvEtnicoDTO bpProyInvEtnicoDTO = new BpProyInvEtnicoDTO();
		GenericoDTO genericDTO = new GenericoDTO();
		when(sessionConsultaBP.consultarTodosProyInvEtnicoAsociadosAProyectoInversion(bpProyInvEtnicoDTO)).thenReturn(genericDTO);
		
		GenericoDTO res = sessionFacadeBP.consultarTodosProyInvEtnicoAsociadosAProyectoInversion(bpProyInvEtnicoDTO);
		assertNotNull(res);
	}
	


}
