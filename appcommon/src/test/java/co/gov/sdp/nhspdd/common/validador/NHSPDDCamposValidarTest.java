package co.gov.sdp.nhspdd.common.validador;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

import org.junit.Test;

public class NHSPDDCamposValidarTest {

	@Test
	public void testConstructorIsPrivate()
			throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
		Constructor<NHSPDDCamposValidar> constructor = NHSPDDCamposValidar.class.getDeclaredConstructor();
		assertTrue(Modifier.isPrivate(constructor.getModifiers()));
		constructor.setAccessible(true);
		constructor.newInstance();
	}

	@Test
	public void testCambiarClaveObtenerCamposValidacion() {
		assertNotNull(NHSPDDCamposValidar.cambiarClaveObtenerCamposValidacion());

	}

	@Test
	public void testAsignarComponenteUsuario() {
		assertNotNull(NHSPDDCamposValidar.asignarComponenteUsuario());
	}

	@Test
	public void testRemoverComponenteUsuario() {
		assertNotNull(NHSPDDCamposValidar.removerComponenteUsuario());
	}

	@Test
	public void testIniciarSesionObtenerCamposValidacion() {
		assertNotNull(NHSPDDCamposValidar.iniciarSesionObtenerCamposValidacion());
	}

	@Test
	public void testRestablcerCorreoCamposValidacion() {
		assertNotNull(NHSPDDCamposValidar.restablcerCorreoCamposValidacion());
	}

	@Test
	public void testCrearProyectoInversionCamposValidacion() {
		assertNotNull(NHSPDDCamposValidar.crearProyectoInversionCamposValidacion());
	}

	@Test
	public void testAsignarProyectoInversionUsuarioCamposValidacion() {
		assertNotNull(NHSPDDCamposValidar.asignarProyectoInversionUsuarioCamposValidacion());
	}

	@Test
	public void testRemoverProyectoInversionCamposValidacion() {
		assertNotNull(NHSPDDCamposValidar.removerProyectoInversionCamposValidacion());
	}

	@Test
	public void testObtenerUsuarioComponente() {
		assertNotNull(NHSPDDCamposValidar.obtenerUsuarioComponente());
	}

	@Test
	public void testCrearEntidadCamposValidacion() {
		assertNotNull(NHSPDDCamposValidar.crearEntidadCamposValidacion());
	}

	@Test
	public void testAsignarEntidadUsuarioCamposValidacion() {
		assertNotNull(NHSPDDCamposValidar.asignarEntidadUsuarioCamposValidacion());
	}

	@Test
	public void testRemoverUsuarioEntidadCamposValidacion() {
		assertNotNull(NHSPDDCamposValidar.removerUsuarioEntidadCamposValidacion());
	}

	@Test
	public void testCrearListaSimple() {
		assertNotNull(NHSPDDCamposValidar.crearListaSimple());
	}

	@Test
	public void testModificarListaSimple() {
		assertNotNull(NHSPDDCamposValidar.modificarListaSimple());
	}

	@Test
	public void testRegistrarArgumentoListaSimple() {
		assertNotNull(NHSPDDCamposValidar.registrarArgumentoListaSimple());
	}

	@Test
	public void testModificarArgumentoListaSimple() {
		assertNotNull(NHSPDDCamposValidar.modificarArgumentoListaSimple());
	}

	@Test
	public void testCrearComponenteGasto() {
		assertNotNull(NHSPDDCamposValidar.crearComponenteGasto());
	}

	@Test
	public void testModificarComponenteGasto() {
		assertNotNull(NHSPDDCamposValidar.modificarComponenteGasto());
	}

	@Test
	public void testCambiarEstadoComponenteGasto() {
		assertNotNull(NHSPDDCamposValidar.cambiarEstadoComponenteGasto());
	}

	@Test
	public void testRegistrarPotProyectoInstrumento() {
		assertNotNull(NHSPDDCamposValidar.registrarPotProyectoInstrumento());
	}

	@Test
	public void testModificarPotProyectoInstrumento() {
		assertNotNull(NHSPDDCamposValidar.modificarPotProyectoInstrumento());
	}

	@Test
	public void testModificarEstadoPotProyectoInstrumento() {
		assertNotNull(NHSPDDCamposValidar.modificarEstadoPotProyectoInstrumento());
	}

	@Test
	public void testRegistrarLineaInverision() {
		assertNotNull(NHSPDDCamposValidar.registrarLineaInverision());
	}

	@Test
	public void testModificarLineaInversion() {
		assertNotNull(NHSPDDCamposValidar.modificarLineaInversion());
	}

	@Test
	public void testModificarEstadoLineaInversion() {
		assertNotNull(NHSPDDCamposValidar.modificarEstadoLineaInversion());
	}

	@Test
	public void testRegistrarTerritorializacion() {
		assertNotNull(NHSPDDCamposValidar.registrarTerritorializacion());
	}

	@Test
	public void testModificarTerritorializacion() {
		assertNotNull(NHSPDDCamposValidar.modificarTerritorializacion());
	}

	@Test
	public void testModificarEstadoTerritorializacion() {
		assertNotNull(NHSPDDCamposValidar.modificarEstadoTerritorializacion());
	}

	@Test
	public void testRegistrarConsecutivo() {
		assertNotNull(NHSPDDCamposValidar.registrarConsecutivo());
	}

	@Test
	public void testCrearEstructuraMeta() {
		assertNotNull(NHSPDDCamposValidar.crearEstructuraMeta());
	}

	@Test
	public void testModificarConsecutivo() {
		assertNotNull(NHSPDDCamposValidar.modificarConsecutivo());
	}

	@Test
	public void testModificarEstadoConsecutivo() {
		assertNotNull(NHSPDDCamposValidar.modificarEstadoConsecutivo());
	}

	@Test
	public void testModificarParametroGeneral() {
		assertNotNull(NHSPDDCamposValidar.modificarParametroGeneral());
	}

	@Test
	public void testModificarEstructuraMeta() {
		assertNotNull(NHSPDDCamposValidar.modificarEstructuraMeta());
	}

	@Test
	public void testDarRepuestaBuzon() {
		assertNotNull(NHSPDDCamposValidar.darRepuestaBuzon());
	}

	@Test
	public void testCrearMensaje() {
		assertNotNull(NHSPDDCamposValidar.crearMensaje());
	}

	@Test
	public void testModificarMensaje() {
		assertNotNull(NHSPDDCamposValidar.modificarMensaje());
	}

	@Test
	public void testCambiarEstadoEstructuraMeta() {
		assertNotNull(NHSPDDCamposValidar.cambiarEstadoEstructuraMeta());
	}

	@Test
	public void testCrearEquipamiento() {
		assertNotNull(NHSPDDCamposValidar.crearEquipamiento());
	}

	@Test
	public void testModificarEquipamiento() {
		assertNotNull(NHSPDDCamposValidar.modificarEquipamiento());
	}

	@Test
	public void testCambiarEstadoEquipamiento() {
		assertNotNull(NHSPDDCamposValidar.cambiarEstadoEquipamiento());
	}

	@Test
	public void testCambiarEstadoInformacionPresupuestal() {
		assertNotNull(NHSPDDCamposValidar.cambiarEstadoInformacionPresupuestal());
	}

	@Test
	public void testCrearInformacionPresupuestal() {
		assertNotNull(NHSPDDCamposValidar.crearInformacionPresupuestal());
	}

	@Test
	public void testModificarInformacionPresupuestal() {
		assertNotNull(NHSPDDCamposValidar.modificarInformacionPresupuestal());
	}

	@Test
	public void testCargarArchivoPlano() {
		assertNotNull(NHSPDDCamposValidar.cargarArchivoPlano());
	}

	@Test
	public void testObtenerArchivoPlano() {
		assertNotNull(NHSPDDCamposValidar.obtenerArchivoPlano());
	}

	@Test
	public void testObtenerArchivoPlanoPorId() {
		assertNotNull(NHSPDDCamposValidar.obtenerArchivoPlanoPorId());
	}

	@Test
	public void testCrearHistorialAdministrativo() {
		assertNotNull(NHSPDDCamposValidar.crearHistorialAdministrativo());
	}

	@Test
	public void testCrearHistorialSectorial() {
		assertNotNull(NHSPDDCamposValidar.crearHistorialSectorial());
	}

	@Test
	public void testCrearFuncionarioClaveEntidad() {
		assertNotNull(NHSPDDCamposValidar.crearFuncionarioClaveEntidad());
	}

	@Test
	public void testConsultarUsuarios() {
		assertNotNull(NHSPDDCamposValidar.consultarUsuarios());
	}

	@Test
	public void testRegistrarBuzonNotificacion() {
		assertNotNull(NHSPDDCamposValidar.registrarBuzonNotificacion());
	}
	
	@Test
	public void testRegistrarPddCompromisoEspecifico() {
		assertNotNull(NHSPDDCamposValidar.registrarPddCompromisoEspecifico());
	}
	
	@Test
	public void testModificarPddCompromisoEspecifico() {
		assertNotNull(NHSPDDCamposValidar.modificarPddCompromisoEspecifico());
	}

	@Test
	public void testRegistrarPddPrbValoracion() {
		assertNotNull(NHSPDDCamposValidar.registrarPddPrbValoracion());
	}
	@Test
	public void testRegistrarPddCompetenciaAsociada() {
		assertNotNull(NHSPDDCamposValidar.registrarPddCompetenciaAsociada());
	}
	@Test
	public void testRegistrarCompromisoEstrategico() {
		assertNotNull(NHSPDDCamposValidar.registrarCompromisoEstrategico());
	}
	@Test
	public void testModificarCompromisoEstrategico() {
		assertNotNull(NHSPDDCamposValidar.modificarCompromisoEstrategico());
	}
	@Test
	public void testRegistrarPddCompromiso() {
	  assertNotNull(NHSPDDCamposValidar.registrarPddCompromiso());
	}	
	@Test
	public void testModificarRegistrarPdd() {
		assertNotNull(NHSPDDCamposValidar.registrarPdd());
	}
	@Test
	public void testModificarPdd() {
		assertNotNull(NHSPDDCamposValidar. modificarPdd());
	}
	@Test
	public void testRegistrarPddNivel() {
		assertNotNull(NHSPDDCamposValidar.registrarPddNivel());
	}
	
	@Test
	public void testModificarFuncionarioClaveEntidad() {
		assertNotNull(NHSPDDCamposValidar.modificarFuncionarioClaveEntidad());
	}
	@Test
	public void testRegistrarPddMeta() {
		assertNotNull(NHSPDDCamposValidar.registrarPddMeta());
	}
	@Test
	public void testRegistrarPddObraConcreta() {
		assertNotNull(NHSPDDCamposValidar.registrarPddObraConcreta());
	}
	@Test
	public void testModificarObraConcreta() {
		assertNotNull(NHSPDDCamposValidar. modificarObraConcreta());
	}
	@Test
	public void testModificarPddMeta() {
		assertNotNull(NHSPDDCamposValidar.modificarPddMeta());
	}
	@Test
	public void testRegistrarPddProblematica() {
		assertNotNull(NHSPDDCamposValidar.registrarPddProblematica() );
	}
	@Test
	public void testModificarPddProblematica() {
		assertNotNull(NHSPDDCamposValidar. modificarPddProblematica());
	}
	
	@Test
	public void testRegistrarPddPbrPoblacion() {
		assertNotNull(NHSPDDCamposValidar.registrarPddPbrPoblacion());
	}
	@Test
	public void testModificarPddPbrPoblacion() {
		assertNotNull(NHSPDDCamposValidar.modificarPddPbrPoblacion());
		
	}
	@Test
	public void testRegistrarPddPrbIndicador() {
		assertNotNull(NHSPDDCamposValidar.registrarPddPrbIndicador());
	}
	@Test
	public void testModificarPddPrbIndicador() {
		assertNotNull(NHSPDDCamposValidar.modificarPddPrbIndicador());
	}
	
	@Test
	public void testRegistrarPddMetaResultado() {
		assertNotNull(NHSPDDCamposValidar.registrarPddMetaResultado());
	}
	@Test
	public void testModificarPddMetaResultado() {
		assertNotNull(NHSPDDCamposValidar.modificarPddMetaResultado());
	}
	@Test
	public void testRegistrarPddIndicador() {
		assertNotNull(NHSPDDCamposValidar.registrarPddIndicador());
	}
	@Test
	public void testModificarPddIndicador() {
		assertNotNull(NHSPDDCamposValidar. modificarPddIndicador());
	}
	@Test
	public void testRegistrarProyectoInversion_TAB_IndentificacionProyecto() {
		assertNotNull(NHSPDDCamposValidar.registrarProyectoInversionTABIndentificacionProyecto());
	}
	@Test
	public void testModificarPddCompromiso() {
		assertNotNull(NHSPDDCamposValidar.modificarPddCompromiso());
	}
	@Test
	public void testRegistrarIniciativaCiudadana() {
		assertNotNull(NHSPDDCamposValidar.registrarIniciativaCiudadana());
	}
	
	@Test
	public void testModificarIniciativaCiudadana() {
		assertNotNull(NHSPDDCamposValidar.modificarIniciativaCiudadana());
	}
	
	@Test
	public void testRegistrarProyectoInversionTABIndentificacionProyecto()
	{
		assertNotNull(NHSPDDCamposValidar.registrarProyectoInversionTABIndentificacionProyecto());
	}
	
	@Test
	public void testRegistrarBpProyInvAporte()
	{
		assertNotNull(NHSPDDCamposValidar.registrarBpProyInvAporte());
	}
	
	@Test
	public void testRegistrarIpPot() {
		assertNotNull(NHSPDDCamposValidar.registrarIpPot());
	}
	
	@Test
	public void testModificarIpPot() {
		assertNotNull(NHSPDDCamposValidar.modificarIpPot());
	}
	

	public void testRegistrarPdl() {
		assertNotNull(NHSPDDCamposValidar.registrarPdl());
	}

	@Test
	public void testRegistrarPdlNivel() {
		assertNotNull(NHSPDDCamposValidar.registrarPdlNivel());
	}

	@Test
	public void testRegistrarPdlNivelAtributo() {
		assertNotNull(NHSPDDCamposValidar.registrarPdlNivelAtributo());
	}

	@Test
	public void testRegistrarPotObra() throws Exception {
		assertNotNull(NHSPDDCamposValidar.registrarPotObra());
	}

	@Test
	public void testModificarPotObra() throws Exception {
		assertNotNull(NHSPDDCamposValidar.modificarPotObra());
	}

	@Test
	public void testObtenerPotIntrumentoPorIdPot() throws Exception {
		assertNotNull(NHSPDDCamposValidar.obtenerPotIntrumentoPorIdPot());
	}

	@Test
	public void testRegistraPotInstrumento() throws Exception {
		assertNotNull(NHSPDDCamposValidar.registraPotInstrumento());
	}
	
}
