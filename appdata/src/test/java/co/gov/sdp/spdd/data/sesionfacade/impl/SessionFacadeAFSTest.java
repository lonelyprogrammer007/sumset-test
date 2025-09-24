package co.gov.sdp.spdd.data.sesionfacade.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.junit4.SpringRunner;

import co.gov.sdp.nhspdd.common.dto.ArchivoProcesadoDTO;
import co.gov.sdp.nhspdd.common.dto.ArgumentoListaSimpleDTO;
import co.gov.sdp.nhspdd.common.dto.BancoDeProyectoDTO;
import co.gov.sdp.nhspdd.common.dto.BuzonNotificacionesDTO;
import co.gov.sdp.nhspdd.common.dto.ComponenteGastoDTO;
import co.gov.sdp.nhspdd.common.dto.ComponenteGestionUsuarioDTO;
import co.gov.sdp.nhspdd.common.dto.ConfiguracionCargueArchivoDTO;
import co.gov.sdp.nhspdd.common.dto.ConfiguracionNotificacionDTO;
import co.gov.sdp.nhspdd.common.dto.ConsecutivoDTO;
import co.gov.sdp.nhspdd.common.dto.EntidadDTO;
import co.gov.sdp.nhspdd.common.dto.EquipamientoDTO;
import co.gov.sdp.nhspdd.common.dto.EstadoServicioDTO;
import co.gov.sdp.nhspdd.common.dto.EstructuraMetaDTO;
import co.gov.sdp.nhspdd.common.dto.FuncionarioClaveEntidadDTO;
import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.dto.InformacionPresupuestalDTO;
import co.gov.sdp.nhspdd.common.dto.LineaDeInversionDTO;
import co.gov.sdp.nhspdd.common.dto.ListaCompuestaDTO;
import co.gov.sdp.nhspdd.common.dto.ListaSimpleDTO;
import co.gov.sdp.nhspdd.common.dto.ParametroGeneralDTO;
import co.gov.sdp.nhspdd.common.dto.PotProyectoInstrumentoDTO;
import co.gov.sdp.nhspdd.common.dto.ProyectoInversionDTO;
import co.gov.sdp.nhspdd.common.dto.ProyectosInversionUsuarioDTO;
import co.gov.sdp.nhspdd.common.dto.TerritorializacionDTO;
import co.gov.sdp.nhspdd.common.dto.UsuarioEntidadDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.spdd.data.serviciofacade.afs.SessionConsultaAFS;
import co.gov.sdp.spdd.data.serviciofacade.afs.SessionEliminarAFS;
import co.gov.sdp.spdd.data.serviciofacade.afs.SessionModificarAFS;
import co.gov.sdp.spdd.data.serviciofacade.afs.SessionRegistroAFS;

/**
 * 
 * @author Sumset
 *
 */
@RunWith(SpringRunner.class)
public class SessionFacadeAFSTest {

	@TestConfiguration
	static class SessionFacadeAFSTestContextConfiguration {
		@Bean
		public SessionFacadeAFS sessionFacadeAFS() {
			return new SessionFacadeAFS();
		}
	}

	@Autowired
	SessionFacadeAFS sessionFacadeAFS;
	
	@MockBean
	SessionConsultaAFS sessionConsultaAFS;

	/**
	 * 
	 */
	@MockBean
	SessionEliminarAFS sessionEliminarAFS;

	/**
	 * 
	 */
	@MockBean
	SessionRegistroAFS sessionRegistroAFS;

	/**
	 * 
	 */
	@MockBean
	SessionModificarAFS sessionModificarAFS;
	
	

	@Test
	public void buscarIdListaSimpleArgumentoTest() throws SpddExceptions {
		ArgumentoListaSimpleDTO peticion = new ArgumentoListaSimpleDTO();
		when(sessionConsultaAFS.buscarIdListaSimpleArgumento(peticion)).thenReturn(new ArgumentoListaSimpleDTO());
		assertThat(sessionFacadeAFS.buscarIdListaSimpleArgumento(peticion)).isNotNull();
	}

	@Test
	public void buscarPorIdLsPotObraYIdLsPotInstrumentoTest() throws SpddExceptions {
		PotProyectoInstrumentoDTO peticion = new PotProyectoInstrumentoDTO();
		when(sessionConsultaAFS.buscarPorIdLsPotObraYIdLsPotInstrumento(peticion))
				.thenReturn(new PotProyectoInstrumentoDTO());
		assertThat(sessionFacadeAFS.buscarPorIdLsPotObraYIdLsPotInstrumento(peticion)).isNotNull();
	}

	@Test
	public void buscarIdLsModuloYIdLsTemaTest() throws SpddExceptions {
		ConfiguracionCargueArchivoDTO peticion = new ConfiguracionCargueArchivoDTO();
		when(sessionConsultaAFS.buscarIdLsModuloYIdLsTema(peticion)).thenReturn(new ConfiguracionCargueArchivoDTO());
		assertThat(sessionFacadeAFS.buscarIdLsModuloYIdLsTema(peticion)).isNotNull();
	}

	@Test
	public void buscarPorLsVeredaYLsUprTest() throws SpddExceptions {
		TerritorializacionDTO territorializacionDTO = new TerritorializacionDTO();
		when(sessionConsultaAFS.buscarPorLsVeredaYLsUpr(territorializacionDTO)).thenReturn(new TerritorializacionDTO());
		assertThat(sessionFacadeAFS.buscarPorLsVeredaYLsUpr(territorializacionDTO)).isNotNull();
	}

	@Test
	public void buscarPorLsBarrioYLsUpzYLsLocalidadTest() throws SpddExceptions {
		TerritorializacionDTO territorializacionDTO = new TerritorializacionDTO();
		when(sessionConsultaAFS.buscarPorLsBarrioYLsUpzYLsLocalidad(territorializacionDTO))
				.thenReturn(new TerritorializacionDTO());
		assertThat(sessionFacadeAFS.buscarPorLsBarrioYLsUpzYLsLocalidad(territorializacionDTO)).isNotNull();
	}

	@Test
	public void buscarSectorYCategoriaTest() throws SpddExceptions {
		when(sessionConsultaAFS.buscarSectorYCategoria(1L, 1L)).thenReturn(new EquipamientoDTO());
		assertThat(sessionFacadeAFS.buscarSectorYCategoria(1L, 1L)).isNotNull();
	}

	@Test
	public void buscarLineaInversionPorConceptoYSectorTest() throws SpddExceptions {
		when(sessionConsultaAFS.buscarLineaInversionPorConceptoYSector("concepto", 1L))
				.thenReturn(new LineaDeInversionDTO());
		assertThat(sessionFacadeAFS.buscarLineaInversionPorConceptoYSector("concepto", 1L)).isNotNull();
	}

	@Test
	public void buscarCodigoYNombre() throws SpddExceptions {
		when(sessionConsultaAFS.buscarPorCodigoYNombre(1L, "nombre")).thenReturn(new ComponenteGastoDTO());
		assertThat(sessionFacadeAFS.buscarCodigoYNombre(1L, "nombre")).isNotNull();
	}

	@Test
	public void buscarUnidadMedidaYVerboTest() throws SpddExceptions {
		when(sessionConsultaAFS.buscarUnidadMedidaYVerbo(1L, 1L)).thenReturn(new EstructuraMetaDTO());
		assertThat(sessionFacadeAFS.buscarUnidadMedidaYVerbo(1L, 1L)).isNotNull();
	}

	@Test
	public void consultarArchivoProcesadoPorIdTest() throws SpddExceptions {
		when(sessionConsultaAFS.consultarArchivoProcesadoPorId(1L)).thenReturn(new ArchivoProcesadoDTO());
		assertThat(sessionFacadeAFS.consultarArchivoProcesadoPorId(1L)).isNotNull();
	}

	@Test
	public void consultarConfigCargueArchivoProcesadoTest() throws SpddExceptions {
		when(sessionConsultaAFS.consultarConfigCargueArchivoProcesado(1L))
				.thenReturn(new ConfiguracionCargueArchivoDTO());
		assertThat(sessionFacadeAFS.consultarConfigCargueArchivoProcesado(1L)).isNotNull();
	}

	@Test
	public void consultarComponenteGestionUsuarioTodosTest() throws SpddExceptions {
		when(sessionConsultaAFS.consultarComponenteGestionUsuarioTodos()).thenReturn(new GenericoDTO());
		assertThat(sessionFacadeAFS.consultarComponenteGestionUsuarioTodos()).isNotNull();
	}

	@Test
	public void consultarArchivoProcesadoPorFiltroTest() throws SpddExceptions {
		ArchivoProcesadoDTO peticion = new ArchivoProcesadoDTO();
		when(sessionConsultaAFS.consultarArchivoProcesadofiltrarPorCampo(peticion)).thenReturn(new GenericoDTO());
		assertThat(sessionFacadeAFS.consultarArchivoProcesadoPorFiltro(peticion)).isNotNull();
	}

	@Test
	public void consultarArgumentoListaSimplePorIdTest() throws SpddExceptions {
		when(sessionConsultaAFS.consultarArgumentoListaSimplePorId(1L)).thenReturn(new ArgumentoListaSimpleDTO());
		assertThat(sessionFacadeAFS.consultarArgumentoListaSimplePorId(1L)).isNotNull();
	}

	@Test
	public void consultarArgumentoPorIdPdd() throws SpddExceptions {
		when(sessionConsultaAFS.consultarArgumentoPorIdPdd(1L)).thenReturn(new GenericoDTO());
		assertThat(sessionFacadeAFS.consultarArgumentoPorIdPdd(1L)).isNotNull();
	}

	@Test
	public void consultarBancoDeProyectoporIdTest() throws SpddExceptions {
		when(sessionConsultaAFS.consultarBancoDeProyectoporId(1L)).thenReturn(new BancoDeProyectoDTO());
		assertThat(sessionFacadeAFS.consultarBancoDeProyectoporId(1L)).isNotNull();
	}

	@Test
	public void consultarBuzonNotificacionesPorIdTest() throws SpddExceptions {
		when(sessionConsultaAFS.consultarBuzonNotificacionesPorId(1L)).thenReturn(new BuzonNotificacionesDTO());
		assertThat(sessionFacadeAFS.consultarBuzonNotificacionesPorId(1L)).isNotNull();

	}

	@Test
	public void consultarBuzonNotificacionesPorFiltroTest() throws SpddExceptions {
		BuzonNotificacionesDTO peticion = new BuzonNotificacionesDTO();
		when(sessionConsultaAFS.consultarBuzonNotificacionesPorFiltro(peticion)).thenReturn(new GenericoDTO());
		assertThat(sessionFacadeAFS.consultarBuzonNotificacionesPorFiltro(peticion)).isNotNull();
	}

	@Test
	public void consultarComponenteGastoPorIdTest() throws SpddExceptions {
		when(sessionConsultaAFS.consultarComponenteGastoPorId(1L)).thenReturn(new ComponenteGastoDTO());
		assertThat(sessionFacadeAFS.consultarComponenteGastoPorId(1L)).isNotNull();
	}

	@Test
	public void consultarComponenteGestionUsuarioPorIdTest() throws SpddExceptions {
		when(sessionConsultaAFS.consultarComponenteGestionUsuarioPorId(1L))
				.thenReturn(new ComponenteGestionUsuarioDTO());
		assertThat(sessionFacadeAFS.consultarComponenteGestionUsuarioPorId(1L)).isNotNull();
	}

	@Test
	public void consultarConsecutivoPorIdTest() throws SpddExceptions {
		when(sessionConsultaAFS.consultarConsecutivoPorId(1L)).thenReturn(new ConsecutivoDTO());
		assertThat(sessionFacadeAFS.consultarConsecutivoPorId(1L)).isNotNull();
	}

	@Test
	public void consultarConsecutivoPorFiltroTest() throws SpddExceptions {
		ConsecutivoDTO peticion = new ConsecutivoDTO();
		when(sessionConsultaAFS.consultarConsecutivoPorFiltro(peticion)).thenReturn(new GenericoDTO());
		assertThat(sessionFacadeAFS.consultarConsecutivoPorFiltro(peticion)).isNotNull();
	}

	@Test
	public void consultarEntidadTest() throws SpddExceptions {
		when(sessionConsultaAFS.consultarEntidad("0141")).thenReturn(new EntidadDTO());
		assertThat(sessionFacadeAFS.consultarEntidad("0141")).isNotNull();
	}

	@Test
	public void consultarEquipamientoTest() throws SpddExceptions {
		when(sessionConsultaAFS.consultarEquipamiento(1L)).thenReturn(new EquipamientoDTO());
		assertThat(sessionFacadeAFS.consultarEquipamiento(1L)).isNotNull();
	}

	@Test
	public void consultarEquipamientoPorFiltroTest() throws SpddExceptions {
		EquipamientoDTO peticion = new EquipamientoDTO();
		when(sessionConsultaAFS.consultarEquipamientoPorFiltro(peticion)).thenReturn(new GenericoDTO());
		assertThat(sessionFacadeAFS.consultarEquipamientoPorFiltro(peticion)).isNotNull();
	}

	@Test
	public void consultarEstadoServicioTest() throws SpddExceptions {
		when(sessionConsultaAFS.consultarEstadoServicio(1L)).thenReturn(new EstadoServicioDTO());
		assertThat(sessionFacadeAFS.consultarEstadoServicio(1L)).isNotNull();

	}

	@Test
	public void consultarEstadoServicioPorFiltroTest() throws SpddExceptions {
		EstadoServicioDTO peticion = new EstadoServicioDTO();
		when(sessionConsultaAFS.consultarEstadoServicioPorFiltro(peticion)).thenReturn(new GenericoDTO());
		assertThat(sessionFacadeAFS.consultarEstadoServicioPorFiltro(peticion)).isNotNull();
	}

	@Test
	public void consultarEstructuraMetaTest() throws SpddExceptions {
		when(sessionConsultaAFS.consultarEstructuraMeta(1L)).thenReturn(new EstructuraMetaDTO());
		assertThat(sessionFacadeAFS.consultarEstructuraMeta(1L)).isNotNull();
	}

	@Test
	public void consultarEstructuraMetaPorFiltroTest() throws SpddExceptions {
		EstructuraMetaDTO peticion = new EstructuraMetaDTO();
		when(sessionConsultaAFS.consultarEstructuraMetaPorFiltro(peticion)).thenReturn(new GenericoDTO());
		assertThat(sessionFacadeAFS.consultarEstructuraMetaPorFiltro(peticion)).isNotNull();
	}

	@Test
	public void consultarFuncionarioClaveEntidadTest() throws SpddExceptions {
		when(sessionConsultaAFS.consultarFuncionarioClaveEntidad(1L)).thenReturn(new FuncionarioClaveEntidadDTO());
		assertThat(sessionFacadeAFS.consultarFuncionarioClaveEntidad(1L)).isNotNull();
	}

	@Test
	public void consultarFuncionarioClaveEntidadPorFiltroTest() throws SpddExceptions {
		FuncionarioClaveEntidadDTO peticion = new FuncionarioClaveEntidadDTO();
		when(sessionConsultaAFS.consultarFuncionarioClaveEntidadPorFiltro(peticion)).thenReturn(new GenericoDTO());
		assertThat(sessionFacadeAFS.consultarFuncionarioClaveEntidadPorFiltro(peticion)).isNotNull();
	}

	@Test
	public void consultarInformacionPresupuestalPorIdTest() throws SpddExceptions {
		when(sessionConsultaAFS.consultarInformacionPresupuestalPorId(1L)).thenReturn(new InformacionPresupuestalDTO());
		assertThat(sessionFacadeAFS.consultarInformacionPresupuestalPorId(1l)).isNotNull();
	}

	@Test
	public void consultarInformacionPresupuestalPorFiltroTest() throws SpddExceptions {
		InformacionPresupuestalDTO peticion = new InformacionPresupuestalDTO();
		when(sessionConsultaAFS.consultarInformacionPresupuestalPorFiltro(peticion)).thenReturn(new GenericoDTO());
		assertThat(sessionFacadeAFS.consultarInformacionPresupuestalPorFiltro(peticion)).isNotNull();
	}

	@Test
	public void consultarLineaDeInversionPorIdTest() throws SpddExceptions {
		when(sessionConsultaAFS.consultarLineaDeInversion(1L)).thenReturn(new LineaDeInversionDTO());
		assertThat(sessionFacadeAFS.consultarLineaDeInversionPorId(1L)).isNotNull();
	}

	@Test
	public void consultarLineaDeInversionPorFiltroTest() throws SpddExceptions {
		LineaDeInversionDTO peticion = new LineaDeInversionDTO();
		when(sessionConsultaAFS.consultarLineaDeInversionPorFiltro(peticion)).thenReturn(new GenericoDTO());
		assertThat(sessionFacadeAFS.consultarLineaDeInversionPorFiltro(peticion)).isNotNull();
	}

	@Test
	public void consultarListaCompuestaPorIdTest() throws SpddExceptions {
		when(sessionConsultaAFS.consultarListaCompuestaPorId(1L)).thenReturn(new ListaCompuestaDTO());
		assertThat(sessionFacadeAFS.consultarListaCompuestaPorId(1L)).isNotNull();
	}

	@Test
	public void consultarListaCompuestaPorFiltroTest() throws SpddExceptions {
		ListaCompuestaDTO peticion = new ListaCompuestaDTO();
		when(sessionConsultaAFS.consultarListaCompuestaPorFiltro(peticion)).thenReturn(new GenericoDTO());
		assertThat(sessionFacadeAFS.consultarListaCompuestaPorFiltro(peticion)).isNotNull();
	}

	@Test
	public void consultarListaSimplePorIDTest() throws SpddExceptions {
		when(sessionConsultaAFS.consultarListaSimplePorID(1L)).thenReturn(new ListaSimpleDTO());
		assertThat(sessionFacadeAFS.consultarListaSimplePorID(1L)).isNotNull();
	}

	@Test
	public void consultarParametroGeneralPorIdTest() throws SpddExceptions {
		when(sessionConsultaAFS.consultarParametroGeneralPorId("1")).thenReturn(new ParametroGeneralDTO());
		assertThat(sessionFacadeAFS.consultarParametroGeneralPorId("1")).isNotNull();
	}

	@Test
	public void consultarParametroGeneralPorFiltro() throws SpddExceptions {
		ParametroGeneralDTO peticion = new ParametroGeneralDTO();
		when(sessionConsultaAFS.consultarParametroGeneralPorFiltro(peticion)).thenReturn(new GenericoDTO());
		assertThat(sessionFacadeAFS.consultarParametroGeneralPorFiltro(peticion)).isNotNull();
	}

	@Test
	public void consultarPotProyectoInstrumentoPorIdTest() throws SpddExceptions {
		when(sessionConsultaAFS.consultarPotProyectoInstrumentoPorId(1L)).thenReturn(new PotProyectoInstrumentoDTO());
		assertThat(sessionFacadeAFS.consultarPotProyectoInstrumentoPorId(1L)).isNotNull();
	}

	@Test
	public void consultarPotProyectoInstrumentoPorFiltroTest() throws SpddExceptions {
		PotProyectoInstrumentoDTO peticion = new PotProyectoInstrumentoDTO();
		when(sessionConsultaAFS.consultarPotProyectoInstrumentoPorFiltro(peticion)).thenReturn(new GenericoDTO());
		assertThat(sessionFacadeAFS.consultarPotProyectoInstrumentoPorFiltro(peticion)).isNotNull();
	}

	@Test
	public void consultarPotProyectoInstrumentoTodosTest() throws SpddExceptions {
		when(sessionConsultaAFS.consultarPotProyectoInstrumentoTodos()).thenReturn(new GenericoDTO());
		assertThat(sessionFacadeAFS.consultarPotProyectoInstrumentoTodos()).isNotNull();
	}

	@Test
	public void consultarPotInstrumentoTest() throws SpddExceptions {
		when(sessionConsultaAFS.consultarPotInstrumento()).thenReturn(new GenericoDTO());
		assertThat(sessionFacadeAFS.consultarPotInstrumento()).isNotNull();
	}

	@Test
	public void consultarPotObraTest() throws SpddExceptions {
		when(sessionConsultaAFS.consultarPotObra()).thenReturn(new GenericoDTO());
		assertThat(sessionFacadeAFS.consultarPotObra()).isNotNull();
	}

	@Test
	public void consultarPddTest() throws SpddExceptions {
		when(sessionConsultaAFS.consultarPdd()).thenReturn(new GenericoDTO());
		assertThat(sessionFacadeAFS.consultarPdd()).isNotNull();
	}

	@Test
	public void consultarProyectoInversionPorIdTest() throws SpddExceptions {
		when(sessionConsultaAFS.consultarProyectoInversionPorId(1L)).thenReturn(new ProyectoInversionDTO());
		assertThat(sessionFacadeAFS.consultarProyectoInversionPorId(1L)).isNotNull();
	}

	@Test
	public void consultarProyectosInversionUsuarioPorIdTest() throws SpddExceptions {
		when(sessionConsultaAFS.consultarProyectosInversionUsuarioPorId("sumset")).thenReturn(new ArrayList<>());
		assertThat(sessionFacadeAFS.consultarProyectosInversionUsuarioPorId("sumset")).isNotNull();
	}

	@Test
	public void consultarTerritorializacionPorIdTest() throws SpddExceptions {
		when(sessionConsultaAFS.consultarTerritorializacionPorId(1L)).thenReturn(new TerritorializacionDTO());
		assertThat(sessionFacadeAFS.consultarTerritorializacionPorId(1L)).isNotNull();
	}

	@Test
	public void consultarTerritorializacionPorFiltroTest() throws SpddExceptions {
		TerritorializacionDTO peticion = new TerritorializacionDTO();
		when(sessionConsultaAFS.consultarTerritorializacionPorFiltro(peticion)).thenReturn(new GenericoDTO());
		assertThat(sessionFacadeAFS.consultarTerritorializacionPorFiltro(peticion)).isNotNull();
	}

	@Test
	public void consultarUsuarioEntidadPorIdTest() throws SpddExceptions {
		when(sessionConsultaAFS.consultarUsuarioEntidadPorId(1L)).thenReturn(new UsuarioEntidadDTO());
		assertThat(sessionFacadeAFS.consultarUsuarioEntidadPorId(1L)).isNotNull();
	}

	@Test
	public void consultarUsuarioEntidadPorFiltroTest() throws SpddExceptions {
		UsuarioEntidadDTO peticion = new UsuarioEntidadDTO();
		when(sessionConsultaAFS.consultarUsuarioEntidadPorFiltro(peticion)).thenReturn(new GenericoDTO());
		assertThat(sessionFacadeAFS.consultarUsuarioEntidadPorFiltro(peticion)).isNotNull();
	}

	@Test
	public void consultarArchivoProcesadoTodosTest() throws SpddExceptions {
		when(sessionConsultaAFS.consultarArchivoProcesadoTodos()).thenReturn(new GenericoDTO());
		assertThat(sessionFacadeAFS.consultarArchivoProcesadoTodos()).isNotNull();
	}

	@Test
	public void consultarArgumentoListaSimpleTodosTest() throws SpddExceptions {
		when(sessionConsultaAFS.consultarArchivoProcesadoTodos()).thenReturn(new GenericoDTO());
		assertThat(sessionFacadeAFS.consultarArgumentoListaSimpleTodos()).isNotNull();
	}

	@Test
	public void consultarArgumentoListaSimplePorFiltroTest() throws SpddExceptions {
		ArgumentoListaSimpleDTO peticion = new ArgumentoListaSimpleDTO();
		when(sessionConsultaAFS.consultarArgumentosPorFiltro(peticion)).thenReturn(new GenericoDTO());
		assertThat(sessionFacadeAFS.consultarArgumentoListaSimplePorFiltro(peticion)).isNotNull();
	}

	@Test
	public void consultarBancoDeProyectosTodosTest() throws SpddExceptions {
		when(sessionConsultaAFS.consultarBancoDeProyectosTodos()).thenReturn(new GenericoDTO());
		assertThat(sessionFacadeAFS.consultarBancoDeProyectosTodos()).isNotNull();
	}

	@Test
	public void consultarBuzonNotificacionesTodosTest() throws SpddExceptions {
		when(sessionConsultaAFS.consultarBuzonNotificacionesTodos()).thenReturn(new GenericoDTO());
		assertThat(sessionFacadeAFS.consultarBuzonNotificacionesTodos()).isNotNull();
	}

	@Test
	public void consultarComponenteGastoTodosTest() throws SpddExceptions {
		when(sessionConsultaAFS.consultarComponenteGastoTodos()).thenReturn(new GenericoDTO());
		assertThat(sessionFacadeAFS.consultarComponenteGastoTodos()).isNotNull();
	}

	@Test
	public void consultarComponenteGastoPorFiltroTest() throws SpddExceptions {
		ComponenteGastoDTO peticion = new ComponenteGastoDTO();
		when(sessionConsultaAFS.consultarPorFiltroComponenteGasto(peticion)).thenReturn(new GenericoDTO());
		assertThat(sessionFacadeAFS.consultarComponenteGastoPorFiltro(peticion)).isNotNull();
	}

	@Test
	public void consultarComponenteGestionUsuarioTodos() throws SpddExceptions {
		when(sessionConsultaAFS.consultarComponenteGastoTodos()).thenReturn(new GenericoDTO());
		assertThat(sessionFacadeAFS.consultarComponenteGastoTodos()).isNotNull();
	}

	@Test
	public void consultarConsecutivoTodosTest() throws SpddExceptions {
		when(sessionConsultaAFS.consultarConsecutivoTodos()).thenReturn(new GenericoDTO());
		assertThat(sessionFacadeAFS.consultarConsecutivoTodos()).isNotNull();
	}

	@Test
	public void consultarEntidadTodosTest() throws SpddExceptions {
		when(sessionConsultaAFS.consultarEntidadTodos()).thenReturn(new GenericoDTO());
		assertThat(sessionFacadeAFS.consultarEntidadTodos()).isNotNull();
	}

	@Test
	public void consultarEquipamientoTodosTest() throws SpddExceptions {
		when(sessionConsultaAFS.consultarEquipamientoTodos()).thenReturn(new GenericoDTO());
		assertThat(sessionFacadeAFS.consultarEquipamientoTodos()).isNotNull();
	}

	@Test
	public void consultarEstadoServicioTodosTest() throws SpddExceptions {
		when(sessionConsultaAFS.consultarEstadoServicioTodos()).thenReturn(new GenericoDTO());
		assertThat(sessionFacadeAFS.consultarEstadoServicioTodos()).isNotNull();
	}

	@Test
	public void consultarEstructuraMetaTodos() throws SpddExceptions {
		when(sessionConsultaAFS.consultarEstructuraMetaTodos()).thenReturn(new GenericoDTO());
		assertThat(sessionFacadeAFS.consultarEstructuraMetaTodos()).isNotNull();
	}

	@Test
	public void consultarFuncionarioClaveEntidadTodosTest() throws SpddExceptions {
		when(sessionConsultaAFS.consultarFuncionarioClaveEntidadTodos()).thenReturn(new GenericoDTO());
		assertThat(sessionFacadeAFS.consultarFuncionarioClaveEntidadTodos()).isNotNull();
	}

	@Test
	public void consultarInformacionPresupuestalTodosTest() throws SpddExceptions {
		when(sessionConsultaAFS.consultarInformacionPresupuestalTodos()).thenReturn(new GenericoDTO());
		assertThat(sessionFacadeAFS.consultarInformacionPresupuestalTodos()).isNotNull();
	}

	@Test
	public void consultarLineaDeInversionTodosTest() throws SpddExceptions {
		when(sessionConsultaAFS.consultarLineaDeInversionTodos()).thenReturn(new GenericoDTO());
		assertThat(sessionFacadeAFS.consultarLineaDeInversionTodos()).isNotNull();
	}

	@Test
	public void consultarListaCompuestaTodosTest() throws SpddExceptions {
		when(sessionConsultaAFS.consultarListaCompuestaTodos()).thenReturn(new GenericoDTO());
		assertThat(sessionFacadeAFS.consultarListaCompuestaTodos()).isNotNull();
	}

	@Test
	public void consultarListaSimpleTodosTest() throws SpddExceptions {
		when(sessionConsultaAFS.consultarListaSimpleTodos()).thenReturn(new GenericoDTO());
		assertThat(sessionFacadeAFS.consultarListaSimpleTodos()).isNotNull();
	}

	@Test
	public void consultarListaSimplePorFiltroTest() throws SpddExceptions {
		ListaSimpleDTO peticion = new ListaSimpleDTO();
		when(sessionConsultaAFS.consultarListaSimplePorFiltro(peticion)).thenReturn(new GenericoDTO());
		assertThat(sessionFacadeAFS.consultarListaSimplePorFiltro(peticion)).isNotNull();
	}

	@Test
	public void consultarParametroGeneralTodosTest() throws SpddExceptions {
		when(sessionConsultaAFS.consultarParametroGeneralTodos()).thenReturn(new GenericoDTO());
		assertThat(sessionFacadeAFS.consultarParametroGeneralTodos()).isNotNull();
	}

	@Test
	public void consultarProyectoInversionTodosTest() throws SpddExceptions {
		when(sessionConsultaAFS.consultarProyectoInversionTodos()).thenReturn(new GenericoDTO());
		assertThat(sessionFacadeAFS.consultarProyectoInversionTodos()).isNotNull();

	}

	@Test
	public void consultarProyectoInversionUsuarioTodosTest() throws SpddExceptions {
		when(sessionConsultaAFS.consultarProyectoInversionUsuarioTodos()).thenReturn(new GenericoDTO());
		assertThat(sessionFacadeAFS.consultarProyectoInversionUsuarioTodos()).isNotNull();
	}

	@Test
	public void consultarTerritorializacionTodosTest() throws SpddExceptions {
		when(sessionConsultaAFS.consultarTerritorializacionTodos()).thenReturn(new GenericoDTO());
		assertThat(sessionFacadeAFS.consultarTerritorializacionTodos()).isNotNull();
	}

	@Test
	public void consultarTerritorializacionPorLsVeredaYLsUprTest() throws SpddExceptions {
		TerritorializacionDTO territorializacionDTO = new TerritorializacionDTO();
		when(sessionConsultaAFS.buscarPorLsVeredaYLsUpr(territorializacionDTO)).thenReturn(new TerritorializacionDTO());
		assertThat(sessionFacadeAFS.consultarTerritorializacionPorLsVeredaYLsUpr(territorializacionDTO)).isNotNull();
	}

	@Test
	public void consultarTerritorializacionPorLsBarrioYLsUpzYLsLocalidadTest() throws SpddExceptions {
		TerritorializacionDTO territorializacionDTO = new TerritorializacionDTO();
		when(sessionConsultaAFS.buscarPorLsBarrioYLsUpzYLsLocalidad(territorializacionDTO))
				.thenReturn(new TerritorializacionDTO());
		assertThat(sessionFacadeAFS.consultarTerritorializacionPorLsBarrioYLsUpzYLsLocalidad(territorializacionDTO))
				.isNotNull();
	}

	@Test
	public void consultarUsuarioEntidadTodosTest() throws SpddExceptions {
		when(sessionConsultaAFS.consultarUsuarioEntidadTodos()).thenReturn(new GenericoDTO());
		assertThat(sessionFacadeAFS.consultarUsuarioEntidadTodos()).isNotNull();
	}

	@Test
	public void consultarUsuariosClavePorFiltroTest() throws SpddExceptions {
		FuncionarioClaveEntidadDTO peticion = new FuncionarioClaveEntidadDTO();
		when(sessionConsultaAFS.consultarUsuariosClavePorFiltro(peticion)).thenReturn(new GenericoDTO());
		assertThat(sessionFacadeAFS.consultarUsuariosClavePorFiltro(peticion)).isNotNull();
	}

	@Test
	public void consultarConfiguracionNotificacionPorFiltroTest() throws SpddExceptions {
		ConfiguracionNotificacionDTO peticion = new ConfiguracionNotificacionDTO();
		when(sessionConsultaAFS.consultarConfiguracionNotificacionPorFiltro(peticion)).thenReturn(new GenericoDTO());
		assertThat(sessionFacadeAFS.consultarConfiguracionNotificacionPorFiltro(peticion)).isNotNull();
	}

	@Test
	public void obtenerArgumentoPorListaTest() throws SpddExceptions {
		when(sessionConsultaAFS.obtenerArgumentoPorLista(1L)).thenReturn(new GenericoDTO());
		assertThat(sessionFacadeAFS.obtenerArgumentoPorLista(1L)).isNotNull();
	}

	@Test
	public void obtenerArgumentoPorNombreTest() throws SpddExceptions {
		when(sessionConsultaAFS.obtenerArgumentoPorNombre("sumset")).thenReturn(new GenericoDTO());
		assertThat(sessionFacadeAFS.obtenerArgumentoPorNombre("sumset")).isNotNull();
	}

	@Test
	public void obtenerBuzonPorUsuarioTest() throws SpddExceptions {
		when(sessionConsultaAFS.obtenerBuzonPorUsuario("sumset")).thenReturn(new GenericoDTO());
		assertThat(sessionFacadeAFS.obtenerBuzonPorUsuario("sumset")).isNotNull();
	}

	@Test
	public void obtenerFuncionarioPorEntidadTest() throws SpddExceptions {
		when(sessionConsultaAFS.obtenerFuncionarioPorEntidad("0141")).thenReturn(new GenericoDTO());
		assertThat(sessionFacadeAFS.obtenerFuncionarioPorEntidad("0141")).isNotNull();
	}

	@Test
	public void notificacionesPorUsuario() throws SpddExceptions {
		BuzonNotificacionesDTO buzonNotificacionesDTO = new BuzonNotificacionesDTO();
		when(sessionConsultaAFS.notificacionesPorUsuario(buzonNotificacionesDTO)).thenReturn(1L);
		assertThat(sessionFacadeAFS.notificacionesPorUsuario(buzonNotificacionesDTO)).isEqualTo(1L);
	}

	@Test
	public void proyectoInversionObtenerDisponiblesTest() throws SpddExceptions {
		when(sessionConsultaAFS.proyectoInversionObtenerDisponibles()).thenReturn(new ArrayList<>());
		assertThat(sessionFacadeAFS.proyectoInversionObtenerDisponibles()).isNotNull();
	}

	@Test
	public void pddNivelAtributoObtenerLibresTest() throws SpddExceptions {
		when(sessionConsultaAFS.pddNivelAtributoObtenerLibres()).thenReturn(new GenericoDTO());
		assertThat(sessionFacadeAFS.pddNivelAtributoObtenerLibres()).isNotNull();
	}

	@Test
	public void guardarArchivoProcesadoTest() throws SpddExceptions {
		ArchivoProcesadoDTO archivoProcesadoDTO = new ArchivoProcesadoDTO();
		when(sessionRegistroAFS.guardarArchivoProcesado(archivoProcesadoDTO)).thenReturn(archivoProcesadoDTO);
		assertThat(sessionFacadeAFS.guardarArchivoProcesado(archivoProcesadoDTO)).isNotNull();
		;
	}

	@Test
	public void guardarArgumentoListaSimpleTest() throws SpddExceptions {
		ArgumentoListaSimpleDTO argumentoListaSimpleDTO = new ArgumentoListaSimpleDTO();
		when(sessionRegistroAFS.guardarArgumentoListaSimple(argumentoListaSimpleDTO))
				.thenReturn(argumentoListaSimpleDTO);
		assertThat(sessionFacadeAFS.guardarArgumentoListaSimple(argumentoListaSimpleDTO)).isNotNull();
	}

	@Test
	public void guardarBancoDeProyectoTest() throws SpddExceptions {
		BancoDeProyectoDTO bancoDeProyectoDTO = new BancoDeProyectoDTO();
		when(sessionRegistroAFS.guardarBancoDeProyecto(bancoDeProyectoDTO)).thenReturn(bancoDeProyectoDTO);
		assertThat(sessionFacadeAFS.guardarBancoDeProyecto(bancoDeProyectoDTO)).isNotNull();
	}

	@Test
	public void guardarBuzonNotificacionesTest() throws SpddExceptions {
		BuzonNotificacionesDTO buzonNotificacionesDTO = new BuzonNotificacionesDTO();
		when(sessionRegistroAFS.guardarBuzonNotificaciones(buzonNotificacionesDTO)).thenReturn(buzonNotificacionesDTO);
		assertThat(sessionFacadeAFS.guardarBuzonNotificaciones(buzonNotificacionesDTO)).isNotNull();
	}

	@Test
	public void guardarComponenteGastoTest() throws SpddExceptions {
		ComponenteGastoDTO componenteGastoDTO = new ComponenteGastoDTO();
		componenteGastoDTO.setIdComponenteGasto(1L);
		when(sessionConsultaAFS.buscarPorCodigoYNombre(componenteGastoDTO.getCodigoComponente(),
				componenteGastoDTO.getNombreComponente())).thenReturn(componenteGastoDTO);
		when(sessionRegistroAFS.guardarComponenteGasto(componenteGastoDTO)).thenReturn(componenteGastoDTO);
		assertThat(sessionFacadeAFS.guardarComponenteGasto(componenteGastoDTO)).isNotNull();
		componenteGastoDTO.setIdComponenteGasto(null);
		assertThat(sessionFacadeAFS.guardarComponenteGasto(componenteGastoDTO)).isNotNull();

	}

	@Test
	public void modificarComponenteGastoTest() throws SpddExceptions {
		ComponenteGastoDTO componenteGastoDTO = new ComponenteGastoDTO();
		when(sessionRegistroAFS.guardarComponenteGasto(componenteGastoDTO)).thenReturn(componenteGastoDTO);
		assertThat(sessionFacadeAFS.modificarComponenteGasto(componenteGastoDTO)).isNotNull();
	}

	@Test
	public void guardarComponenteGestionusuarioTest() throws SpddExceptions {
		ComponenteGestionUsuarioDTO componenteGestionUsuarioDTO = new ComponenteGestionUsuarioDTO();
		when(sessionRegistroAFS.guardarComponenteGestionusuario(componenteGestionUsuarioDTO))
				.thenReturn(componenteGestionUsuarioDTO);
		assertThat(sessionFacadeAFS.guardarComponenteGestionusuario(componenteGestionUsuarioDTO)).isNotNull();
	}

	@Test
	public void guardarConsecutivoTest() throws SpddExceptions {
		ConsecutivoDTO consecutivoDTO = new ConsecutivoDTO();
		when(sessionRegistroAFS.guardarConsecutivo(consecutivoDTO)).thenReturn(new ConsecutivoDTO());
		assertThat(sessionFacadeAFS.guardarConsecutivo(consecutivoDTO)).isNotNull();
	}

	@Test
	public void guardarEntidadTest() throws SpddExceptions {
		EntidadDTO entidadDTO = new EntidadDTO();
		when(sessionRegistroAFS.guardarEntidad(entidadDTO)).thenReturn(entidadDTO);
		assertThat(sessionFacadeAFS.guardarEntidad(entidadDTO)).isNotNull();
	}

	@Test
	public void guardarEquipamientoTest() throws SpddExceptions {
		EquipamientoDTO equipamientoDTO = new EquipamientoDTO();
		equipamientoDTO.setIdEquipamento(1L);
		when(sessionConsultaAFS.buscarSectorYCategoria(equipamientoDTO.getIdLsSector(),
				equipamientoDTO.getIdLsCategoria())).thenReturn(equipamientoDTO);
		when(sessionRegistroAFS.guardarEquipamiento(equipamientoDTO)).thenReturn(equipamientoDTO);
		assertThat(sessionFacadeAFS.guardarEquipamiento(equipamientoDTO)).isNotNull();
		equipamientoDTO.setIdEquipamento(null);
		assertThat(sessionFacadeAFS.guardarEquipamiento(equipamientoDTO)).isNotNull();
	}

	@Test
	public void modificarEquipamientoTest() throws SpddExceptions {
		EquipamientoDTO equipamientoDTO = new EquipamientoDTO();
		equipamientoDTO.setIdEquipamento(1L);
		when(sessionRegistroAFS.guardarEquipamiento(equipamientoDTO)).thenReturn(equipamientoDTO);
		assertThat(sessionFacadeAFS.modificarEquipamiento(equipamientoDTO)).isNotNull();
	}

	@Test
	public void guardarEstadoServicioTest() throws SpddExceptions {
		EstadoServicioDTO estadoServicioDTO = new EstadoServicioDTO();
		when(sessionRegistroAFS.guardarEstadoServicio(estadoServicioDTO)).thenReturn(estadoServicioDTO);
		assertThat(sessionFacadeAFS.guardarEstadoServicio(estadoServicioDTO)).isNotNull();
	}

	@Test
	public void guardarEstructuraMetaTest() throws SpddExceptions {
		EstructuraMetaDTO estructuraMetaDTO = new EstructuraMetaDTO();
		estructuraMetaDTO.setIdEstructuraMetas(1L);
		when(sessionConsultaAFS.buscarUnidadMedidaYVerbo(estructuraMetaDTO.getIdLsUnidadMedida(),
				estructuraMetaDTO.getIdLsVerbo())).thenReturn(estructuraMetaDTO);
		when(sessionRegistroAFS.guardarEstructuraMeta(estructuraMetaDTO)).thenReturn(estructuraMetaDTO);
		assertThat(sessionFacadeAFS.guardarEstructuraMeta(estructuraMetaDTO)).isNotNull();
		estructuraMetaDTO.setIdEstructuraMetas(null);
		assertThat(sessionFacadeAFS.guardarEstructuraMeta(estructuraMetaDTO)).isNotNull();
	}

	@Test
	public void modificarEstructuraMetaTest() throws SpddExceptions {
		EstructuraMetaDTO estructuraMetaDTO = new EstructuraMetaDTO();
		when(sessionRegistroAFS.guardarEstructuraMeta(estructuraMetaDTO)).thenReturn(estructuraMetaDTO);
		assertThat(sessionFacadeAFS.modificarEstructuraMeta(estructuraMetaDTO)).isNotNull();
	}

	@Test
	public void guardarFuncionarioClaveEntidadTest() throws SpddExceptions {
		FuncionarioClaveEntidadDTO funcionarioClaveEntidadDTO = new FuncionarioClaveEntidadDTO();
		when(sessionRegistroAFS.guardarFuncionarioClaveEntidad(funcionarioClaveEntidadDTO))
				.thenReturn(funcionarioClaveEntidadDTO);
		assertThat(sessionFacadeAFS.guardarFuncionarioClaveEntidad(funcionarioClaveEntidadDTO)).isNotNull();
	}

	@Test
	public void guardarInformacionPresupuestalTest() throws SpddExceptions {
		InformacionPresupuestalDTO informacionPresupuestalDTO = new InformacionPresupuestalDTO();
		when(sessionRegistroAFS.guardarInformacionPresupuestal(informacionPresupuestalDTO))
				.thenReturn(informacionPresupuestalDTO);
		assertThat(sessionFacadeAFS.guardarInformacionPresupuestal(informacionPresupuestalDTO)).isNotNull();
	}

	@Test
	public void guardarLineaDeInversionTest() throws SpddExceptions {
		LineaDeInversionDTO lineaDeInversionDTO = new LineaDeInversionDTO();
		lineaDeInversionDTO.setIdLineaInversion(1L);
		when(sessionConsultaAFS.buscarLineaInversionPorConceptoYSector(lineaDeInversionDTO.getConcepto(),
				lineaDeInversionDTO.getIdLsSector())).thenReturn(lineaDeInversionDTO);
		when(sessionRegistroAFS.guardarLineaDeInversion(lineaDeInversionDTO)).thenReturn(lineaDeInversionDTO);
		assertThat(sessionFacadeAFS.guardarLineaDeInversion(lineaDeInversionDTO)).isNotNull();
		lineaDeInversionDTO.setIdLineaInversion(null);
		assertThat(sessionFacadeAFS.guardarLineaDeInversion(lineaDeInversionDTO)).isNotNull();
	}

	@Test
	public void modificarLineaDeInversionTest() throws SpddExceptions {
		LineaDeInversionDTO lineaDeInversionDTO = new LineaDeInversionDTO();
		when(sessionRegistroAFS.guardarLineaDeInversion(lineaDeInversionDTO)).thenReturn(lineaDeInversionDTO);
		assertThat(sessionFacadeAFS.modificarLineaDeInversion(lineaDeInversionDTO)).isNotNull();
	}

	@Test
	public void guardarListaCompuestaTest() throws SpddExceptions {
		ListaCompuestaDTO listaCompuestaDTO = new ListaCompuestaDTO();
		when(sessionRegistroAFS.guardarListaCompuesta(listaCompuestaDTO)).thenReturn(listaCompuestaDTO);
		assertThat(sessionFacadeAFS.guardarListaCompuesta(listaCompuestaDTO)).isNotNull();
	}

	@Test
	public void guardarListaSimpleTest() throws SpddExceptions {
		ListaSimpleDTO listaSimpleDTO = new ListaSimpleDTO();
		when(sessionRegistroAFS.guardarListaSimple(listaSimpleDTO)).thenReturn(listaSimpleDTO);
		assertThat(sessionFacadeAFS.guardarListaSimple(listaSimpleDTO)).isNotNull();
	}

	@Test
	public void guardarParametroGeneralTest() throws SpddExceptions {
		ParametroGeneralDTO parametroGeneralDTO = new ParametroGeneralDTO();
		when(sessionRegistroAFS.guardarParametroGeneral(parametroGeneralDTO)).thenReturn(parametroGeneralDTO);
		assertThat(sessionFacadeAFS.guardarParametroGeneral(parametroGeneralDTO)).isNotNull();
	}

	@Test
	public void guardarPotProyectoInstrumentoTest() throws SpddExceptions {
		PotProyectoInstrumentoDTO potProyectoInstrumentoDTO = new PotProyectoInstrumentoDTO();
		potProyectoInstrumentoDTO.setIdProyectoInstrumento(1L);
		when(sessionConsultaAFS.buscarPorIdLsPotObraYIdLsPotInstrumento(potProyectoInstrumentoDTO))
				.thenReturn(potProyectoInstrumentoDTO);
		when(sessionRegistroAFS.guardarPotProyectoInstrumento(potProyectoInstrumentoDTO))
				.thenReturn(potProyectoInstrumentoDTO);
		assertThat(sessionFacadeAFS.guardarPotProyectoInstrumento(potProyectoInstrumentoDTO)).isNotNull();
		potProyectoInstrumentoDTO.setIdProyectoInstrumento(null);
		assertThat(sessionFacadeAFS.guardarPotProyectoInstrumento(potProyectoInstrumentoDTO)).isNotNull();

	}

	@Test
	public void guardarProyectoInversionTest() throws SpddExceptions {
		ProyectoInversionDTO proyectoInversionDTO = new ProyectoInversionDTO();
		when(sessionRegistroAFS.guardarProyectoInversion(proyectoInversionDTO)).thenReturn(proyectoInversionDTO);
		assertThat(sessionFacadeAFS.guardarProyectoInversion(proyectoInversionDTO)).isNotNull();
	}

	@Test
	public void guardarProyectosInversionUsuarioTest() throws SpddExceptions {
		ProyectosInversionUsuarioDTO proyectosInversionUsuarioDTO = new ProyectosInversionUsuarioDTO();
		when(sessionRegistroAFS.guardarProyectosInversionUsuario(proyectosInversionUsuarioDTO))
				.thenReturn(proyectosInversionUsuarioDTO);
		assertThat(sessionFacadeAFS.guardarProyectosInversionUsuario(proyectosInversionUsuarioDTO)).isNotNull();
	}

	@Test
	public void guardarTerritorializacionTest() throws SpddExceptions {
		TerritorializacionDTO territorializacionDTO = new TerritorializacionDTO();
		territorializacionDTO.setIdLsUpr(1L);
		when(sessionConsultaAFS.buscarPorLsVeredaYLsUpr(territorializacionDTO)).thenReturn(territorializacionDTO);
		when(sessionRegistroAFS.guardarTerritorializacion(territorializacionDTO)).thenReturn(territorializacionDTO);
		assertThat(sessionFacadeAFS.guardarTerritorializacion(territorializacionDTO)).isNotNull();
		territorializacionDTO.setIdLsUpr(null);
		territorializacionDTO.setIdTerritorializacion(1L);
		when(sessionConsultaAFS.buscarPorLsBarrioYLsUpzYLsLocalidad(territorializacionDTO))
				.thenReturn(territorializacionDTO);
		assertThat(sessionFacadeAFS.guardarTerritorializacion(territorializacionDTO)).isNotNull();

	}

	@Test
	public void modificarTerritorializacionTest() throws SpddExceptions {
		TerritorializacionDTO territorializacionDTO = new TerritorializacionDTO();
		when(sessionRegistroAFS.guardarTerritorializacion(territorializacionDTO)).thenReturn(territorializacionDTO);
		assertThat(sessionFacadeAFS.modificarTerritorializacion(territorializacionDTO)).isNotNull();
	}

	@Test
	public void guardarUsuarioEntidadTest() throws SpddExceptions {
		UsuarioEntidadDTO usuarioEntidadDTO = new UsuarioEntidadDTO();
		when(sessionRegistroAFS.guardarUsuarioEntidad(usuarioEntidadDTO)).thenReturn(usuarioEntidadDTO);
		assertThat(sessionFacadeAFS.guardarUsuarioEntidad(usuarioEntidadDTO)).isNotNull();
	}

	@Test
	public void modificarProyectoInstrumentoTest() throws SpddExceptions {
		PotProyectoInstrumentoDTO peticion = new PotProyectoInstrumentoDTO();
		when(sessionModificarAFS.modificarProyectoInstrumento(peticion)).thenReturn(peticion);
		assertThat(sessionFacadeAFS.modificarProyectoInstrumento(peticion)).isNotNull();
	}

	@Test
	public void guardarSqlTest() throws DataAccessException, SpddExceptions {
		when(sessionRegistroAFS.guardarSql("*")).thenReturn(1);
		assertThat(sessionFacadeAFS.guardarSql("*")).isEqualTo(1);
	}

	@Test
	public void buscarSql() throws DataAccessException, SpddExceptions {
		when(sessionConsultaAFS.buscarSql("*")).thenReturn(1);
		assertThat(sessionFacadeAFS.buscarSql("*")).isEqualTo(1);
	}

	@Test
	public void obtenerArchivoInfoPresupuestalTest() {
		when(sessionConsultaAFS.consultarArchivoInfoPrespuestal(1L)).thenReturn(new GenericoDTO());
		assertThat(sessionFacadeAFS.obtenerArchivoInfoPresupuestal(1L)).isNotNull();
	}

}
