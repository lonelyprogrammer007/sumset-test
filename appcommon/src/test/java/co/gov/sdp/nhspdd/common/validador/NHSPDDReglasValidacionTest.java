package co.gov.sdp.nhspdd.common.validador;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import co.gov.sdp.nhspdd.common.dto.ArchivoProcesadoDTO;
import co.gov.sdp.nhspdd.common.dto.ArgumentoListaSimpleDTO;
import co.gov.sdp.nhspdd.common.dto.BuzonNotificacionesDTO;
import co.gov.sdp.nhspdd.common.dto.ComponenteGastoDTO;
import co.gov.sdp.nhspdd.common.dto.ComponenteGestionUsuarioDTO;
import co.gov.sdp.nhspdd.common.dto.ConfiguracionNotificacionDTO;
import co.gov.sdp.nhspdd.common.dto.ConsecutivoDTO;
import co.gov.sdp.nhspdd.common.dto.EntidadDTO;
import co.gov.sdp.nhspdd.common.dto.EquipamientoDTO;
import co.gov.sdp.nhspdd.common.dto.EstructuraMetaDTO;
import co.gov.sdp.nhspdd.common.dto.FuncionarioClaveEntidadDTO;
import co.gov.sdp.nhspdd.common.dto.HistorialAdministrativoDTO;
import co.gov.sdp.nhspdd.common.dto.HistorialSectorialDTO;
import co.gov.sdp.nhspdd.common.dto.InformacionPresupuestalDTO;
import co.gov.sdp.nhspdd.common.dto.LineaDeInversionDTO;
import co.gov.sdp.nhspdd.common.dto.ListaSimpleDTO;
import co.gov.sdp.nhspdd.common.dto.ParametroGeneralDTO;
import co.gov.sdp.nhspdd.common.dto.PddDTO;
import co.gov.sdp.nhspdd.common.dto.PddNivelDTO;
import co.gov.sdp.nhspdd.common.dto.PotInstrumentoDTO;
import co.gov.sdp.nhspdd.common.dto.PotObraDTO;
import co.gov.sdp.nhspdd.common.dto.PotProyectoInstrumentoDTO;
import co.gov.sdp.nhspdd.common.dto.ProyectoInversionDTO;
import co.gov.sdp.nhspdd.common.dto.ProyectosInversionUsuarioDTO;
import co.gov.sdp.nhspdd.common.dto.TerritorializacionDTO;
import co.gov.sdp.nhspdd.common.dto.UsuarioEntidadDTO;
import co.gov.sdp.nhspdd.common.dto.UsuariosDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpIniciativaCiudadanaDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvAporteDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyectoInversionDTO;
import co.gov.sdp.nhspdd.common.dto.ip.CompromisoEstrategicoDTO;
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
import co.gov.sdp.nhspdd.common.dto.ip.PdlDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PdlNivelAtributoDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PdlNivelDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PotDTO;

public class NHSPDDReglasValidacionTest {

	private UsuariosDTO usuarioDTO;
	private ComponenteGestionUsuarioDTO componenteGestionUsuarioDTO;
	private ListaSimpleDTO listaSimpleDTO;
	private ProyectoInversionDTO proyectoInversionDTO;
	private ProyectosInversionUsuarioDTO proyectosInversionUsuarioDTO;
	private EntidadDTO entidadDTO;
	private EstructuraMetaDTO estructuraMetaDTO;
	private UsuarioEntidadDTO usuarioEntidadDTO;
	private ArgumentoListaSimpleDTO argumentoListaSimpleDTO;
	private ComponenteGastoDTO componenteGastoDTO;
	private PotProyectoInstrumentoDTO potProyectoInstrumentoDTO;
	private LineaDeInversionDTO lineaDeInversionDTO;
	private TerritorializacionDTO territorializacionDTO;
	private ConsecutivoDTO consecutivoDTO;
	private ParametroGeneralDTO parametroGeneralDTO;
	private BuzonNotificacionesDTO buzonNotificacionesDTO;
	private ConfiguracionNotificacionDTO configuracionNotificacionDTO;
	private EquipamientoDTO equipamientoDTO;
	private InformacionPresupuestalDTO informacionPresupuestalDTO;
	private ArchivoProcesadoDTO archivoProcesadoDTO;
	private HistorialAdministrativoDTO historialAdministrativoDTO;
	private HistorialSectorialDTO historialSectorialDTO;
	private FuncionarioClaveEntidadDTO funcionarioClaveEntidadDTO;
	private UsuariosDTO usuariosDTO;
	private PddCompromisoEspecificoDTO pddCompromisoEspecificoDTO;
	private PddCompetenciaAsociadaDTO pddCompetenciaAsociadaDTO;
	private PddPrbValoracionDTO pddPrbValoracionDTO;
	private CompromisoEstrategicoDTO compromisoEstrategicoDTO;
	private PddCompromisoDTO pddCompromiso;
	private PddDTO pddDTO;
	private PddNivelDTO pddNivelDTO;
	private PddMetaDTO pddMetaDTO;
	private PddObraConcretaDTO pddObraConcretaDTO;
	private PddPrbPoblacionDTO pddPrbPoblacionDTO;
	private PddProblematicaDTO pddProblematicaDTO;
	private PddPrbIndicadorDTO pddPrbIndicadorDTO;
	private PddMetaResultadoDTO pddMetaResultadoDTO;
	private PddIndicadorDTO pddIndicadorDTO;
	private BpIniciativaCiudadanaDTO bpIniciativaCiudadanaDTO;
	private BpProyectoInversionDTO bpProyectoInversionDTO;
	private BpProyInvAporteDTO bpProyInvAporteDTO;
	private PotDTO potDTO;
	private PdlDTO pdlDTO;
	private PdlNivelDTO pdlNivelDTO;
	private PdlNivelAtributoDTO pdlNivelAtributoDTO;
	private PotObraDTO potObraDTO;
	private Map<String, Boolean> camposAValidar;
	private PotInstrumentoDTO potInstrumentoDTO;

	@Before
	public void setUp() throws Exception {
		usuarioDTO = new UsuariosDTO();
		componenteGestionUsuarioDTO = new ComponenteGestionUsuarioDTO();
		listaSimpleDTO = new ListaSimpleDTO();
		proyectoInversionDTO = new ProyectoInversionDTO();
		proyectosInversionUsuarioDTO = new ProyectosInversionUsuarioDTO();
		entidadDTO = new EntidadDTO();
		estructuraMetaDTO = new EstructuraMetaDTO();
		usuarioEntidadDTO = new UsuarioEntidadDTO();
		argumentoListaSimpleDTO = new ArgumentoListaSimpleDTO();
		componenteGastoDTO = new ComponenteGastoDTO();
		potProyectoInstrumentoDTO = new PotProyectoInstrumentoDTO();
		lineaDeInversionDTO = new LineaDeInversionDTO();
		territorializacionDTO = new TerritorializacionDTO();
		consecutivoDTO = new ConsecutivoDTO();
		parametroGeneralDTO = new ParametroGeneralDTO();
		buzonNotificacionesDTO = new BuzonNotificacionesDTO();
		configuracionNotificacionDTO = new ConfiguracionNotificacionDTO();
		equipamientoDTO = new EquipamientoDTO();
		informacionPresupuestalDTO = new InformacionPresupuestalDTO();
		archivoProcesadoDTO = new ArchivoProcesadoDTO();
		historialAdministrativoDTO = new HistorialAdministrativoDTO();
		historialSectorialDTO = new HistorialSectorialDTO();
		funcionarioClaveEntidadDTO = new FuncionarioClaveEntidadDTO();
		usuariosDTO = new UsuariosDTO();
		pddCompromisoEspecificoDTO = new PddCompromisoEspecificoDTO();
		camposAValidar = new HashMap<>();
		pddCompetenciaAsociadaDTO = new PddCompetenciaAsociadaDTO();
		pddPrbValoracionDTO = new PddPrbValoracionDTO();
		compromisoEstrategicoDTO = new CompromisoEstrategicoDTO();
		pddCompromiso = new PddCompromisoDTO();
		pddDTO = new PddDTO();
		pddMetaDTO = new PddMetaDTO();
		pddObraConcretaDTO = new PddObraConcretaDTO();
		pddPrbPoblacionDTO = new PddPrbPoblacionDTO();
		pddProblematicaDTO = new PddProblematicaDTO();
		pddPrbIndicadorDTO = new PddPrbIndicadorDTO();
		pddMetaResultadoDTO = new PddMetaResultadoDTO();
		pddIndicadorDTO=new PddIndicadorDTO();
		bpIniciativaCiudadanaDTO = new BpIniciativaCiudadanaDTO();
		bpProyectoInversionDTO =  new BpProyectoInversionDTO();
		pddNivelDTO=new PddNivelDTO();
		bpProyInvAporteDTO = new BpProyInvAporteDTO();
		potDTO = new PotDTO();
		pdlDTO = new PdlDTO();
		potObraDTO = new PotObraDTO();
		potInstrumentoDTO = new PotInstrumentoDTO();
		pdlNivelDTO = new PdlNivelDTO();
		pdlNivelAtributoDTO = new PdlNivelAtributoDTO();

	}

	@Test
	public void testConstructorIsPrivate()
			throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
		Constructor<NHSPDDReglasValidacion> constructor = NHSPDDReglasValidacion.class.getDeclaredConstructor();
		assertTrue(Modifier.isPrivate(constructor.getModifiers()));
		constructor.setAccessible(true);
		constructor.newInstance();
	}

	@Test
	public void testValidarUsuario() {
		assertNotNull(NHSPDDReglasValidacion.validarUsuario(usuarioDTO, camposAValidar));
	}

	@Test
	public void testValidarComponenteGestionUsuario() {
		assertNotNull(
				NHSPDDReglasValidacion.validarComponenteGestionUsuario(componenteGestionUsuarioDTO, camposAValidar));
	}

	@Test
	public void testValidarListaSimple() {
		assertNotNull(NHSPDDReglasValidacion.validarListaSimple(listaSimpleDTO, camposAValidar));
	}

	@Test
	public void testValidarProyectoInversion() {
		assertNotNull(NHSPDDReglasValidacion.validarProyectoInversion(proyectoInversionDTO, camposAValidar));
	}

	@Test
	public void testValidarProyectosInversionUsuario() {
		assertNotNull(
				NHSPDDReglasValidacion.validarProyectosInversionUsuario(proyectosInversionUsuarioDTO, camposAValidar));
	}

	@Test
	public void testValidarEntidad() {
		assertNotNull(NHSPDDReglasValidacion.validarEntidad(entidadDTO, camposAValidar));
	}

	@Test
	public void testValidarEstrucuraMeta() {
		assertNotNull(NHSPDDReglasValidacion.validarEstrucuraMeta(estructuraMetaDTO, camposAValidar));
	}

	@Test
	public void testValidarUsuarioEntidad() {
		assertNotNull(NHSPDDReglasValidacion.validarUsuarioEntidad(usuarioEntidadDTO, camposAValidar));
	}

	@Test
	public void testValidarArgumentoListaSimple() {
		assertNotNull(NHSPDDReglasValidacion.validarArgumentoListaSimple(argumentoListaSimpleDTO, camposAValidar));
	}

	@Test
	public void testValidarComponenteGasto() {
		assertNotNull(NHSPDDReglasValidacion.validarComponenteGasto(componenteGastoDTO, camposAValidar));
	}

	@Test
	public void testValidarPotProyectoInstrumento() {
		assertNotNull(NHSPDDReglasValidacion.validarPotProyectoInstrumento(potProyectoInstrumentoDTO, camposAValidar));
	}

	@Test
	public void testValidarLineaInversion() {
		assertNotNull(NHSPDDReglasValidacion.validarLineaInversion(lineaDeInversionDTO, camposAValidar));
	}

	@Test
	public void testValidarTerritorializacion() {
		assertNotNull(NHSPDDReglasValidacion.validarTerritorializacion(territorializacionDTO, camposAValidar));
	}

	@Test
	public void testValidarConsecutivo() {
		assertNotNull(NHSPDDReglasValidacion.validarConsecutivo(consecutivoDTO, camposAValidar));
	}

	@Test
	public void testValidarParametroGeneral() {
		assertNotNull(NHSPDDReglasValidacion.validarParametroGeneral(parametroGeneralDTO, camposAValidar));
	}

	@Test
	public void testValidarBuzonNotificacion() {
		assertNotNull(NHSPDDReglasValidacion.validarBuzonNotificacion(buzonNotificacionesDTO, camposAValidar));
	}

	@Test
	public void testValidarConfiguracionNotificacion() {
		assertNotNull(
				NHSPDDReglasValidacion.validarConfiguracionNotificacion(configuracionNotificacionDTO, camposAValidar));
	}

	@Test
	public void testValidarEquipamiento() {
		assertNotNull(NHSPDDReglasValidacion.validarEquipamiento(equipamientoDTO, camposAValidar));
	}

	@Test
	public void testValidarInformacionPresupuestal() {
		assertNotNull(
				NHSPDDReglasValidacion.validarInformacionPresupuestal(informacionPresupuestalDTO, camposAValidar));
	}

	@Test
	public void testValidarArchivoProcesado() {
		assertNotNull(NHSPDDReglasValidacion.validarArchivoProcesado(archivoProcesadoDTO, camposAValidar));
	}

	@Test
	public void testValidarCrearHistorialAdministrativo() {
		assertNotNull(
				NHSPDDReglasValidacion.validarCrearHistorialAdministrativo(historialAdministrativoDTO, camposAValidar));
	}

	@Test
	public void testValidarCrearHistorialSectorial() {
		assertNotNull(NHSPDDReglasValidacion.validarCrearHistorialSectorial(historialSectorialDTO, camposAValidar));
	}

	@Test
	public void testValidarFuncionarioClaveEntidad() {
		assertNotNull(
				NHSPDDReglasValidacion.validarCrearFuncionarioClaveEntidad(funcionarioClaveEntidadDTO, camposAValidar));
	}

	@Test
	public void testValidarConsultarUsuario() {
		assertNotNull(NHSPDDReglasValidacion.consultarUsuario(usuariosDTO, camposAValidar));
	}

	@Test
	public void testValidarPddCompromisoEspecifico() {
		assertNotNull(
				NHSPDDReglasValidacion.validarPddCompromisoEspecifico(pddCompromisoEspecificoDTO, camposAValidar));
	}

	@Test
	public void testValidarPddCompetenciaAsociada() {
		assertNotNull(NHSPDDReglasValidacion.validarPddCompetenciaAsociada(pddCompetenciaAsociadaDTO, camposAValidar));
	}

	@Test
	public void testValidarPddPrbValoracion() {
		assertNotNull(NHSPDDReglasValidacion.validarPddPrbValoracion(pddPrbValoracionDTO, camposAValidar));
	}

	@Test
	public void testValidarCompromisoEstrategico() {
		assertNotNull(NHSPDDReglasValidacion.validarCompromisoEstrategico(compromisoEstrategicoDTO, camposAValidar));
	}

	@Test
	public void testValidarPddCompromiso() {
		assertNotNull(NHSPDDReglasValidacion.validarPddCompromiso(pddCompromiso, camposAValidar));
	}

	@Test
	public void testValidarPdd() {
		assertNotNull(NHSPDDReglasValidacion.validarPdd(pddDTO, camposAValidar));
	}

	@Test
	public void testValidarPddNivel() {
		assertNotNull(NHSPDDReglasValidacion.validarPddNivel(pddNivelDTO, camposAValidar));
	}

	@Test
	public void testValidarPddMeta() {
		assertNotNull(NHSPDDReglasValidacion.validarPddMeta(pddMetaDTO, camposAValidar));
	}

	@Test
	public void testValidarPddObraConcreta() {
		assertNotNull(NHSPDDReglasValidacion.validarPddObraConcreta(pddObraConcretaDTO, camposAValidar));

	}

	@Test
	public void testValidarPddPbrPoblacion() {
		assertNotNull(NHSPDDReglasValidacion.validarPddPbrPoblacion(pddPrbPoblacionDTO, camposAValidar));
	}

	@Test
	public void testValidarPddProblematica() {
		assertNotNull(NHSPDDReglasValidacion.validarPddProblematica(pddProblematicaDTO, camposAValidar));
	}

	@Test
	public void testValidarPddPrbIndicador() {
		assertNotNull(NHSPDDReglasValidacion.validarPddPrbIndicador(pddPrbIndicadorDTO, camposAValidar));
	}

	@Test
	public void testValidarPddMetaResultado() {
		assertNotNull(NHSPDDReglasValidacion.validarPddMetaResultado(pddMetaResultadoDTO, camposAValidar));
	}

	@Test
	public void testValidarPddIndicadorYPrbIndicador() {
		assertNotNull(NHSPDDReglasValidacion.validarPddIndicadorYPrbIndicador(pddPrbIndicadorDTO, camposAValidar));
	}
	
	@Test
	public void testValidarPddIndicador() {
		assertNotNull(NHSPDDReglasValidacion.validarPddIndicador(pddIndicadorDTO, camposAValidar));
	}
	
	@Test
	public void testValidarIniciativaCiudadana() {
		assertNotNull(NHSPDDReglasValidacion.validarIniciativaCiudadana(bpIniciativaCiudadanaDTO, camposAValidar));
	}
	
	@Test
	public void testValidarProyectoInversionTABIndentificacionProyecto() {
		assertNotNull(NHSPDDReglasValidacion.validarProyectoInversionTABIndentificacionProyecto(bpProyectoInversionDTO, camposAValidar));
	}
	
	@Test
	public void testValidarBpProyInvAporte() {
		assertNotNull(NHSPDDReglasValidacion.validarBpProyInvAporte(bpProyInvAporteDTO, camposAValidar));
	}
	
	@Test
	public void testValidarIpPot() {
		assertNotNull(NHSPDDReglasValidacion.validarIpPot(potDTO, camposAValidar));
	}
	
	@Test	
	public void testValidarPdl() {
		assertNotNull(NHSPDDReglasValidacion.validarPdl(pdlDTO, camposAValidar));

	}

	@Test
	public void testValidarPdlNivel() {
		assertNotNull(NHSPDDReglasValidacion.validarPdlNivel(pdlNivelDTO, camposAValidar));
	}

	@Test
	public void testValidarPdlNivelAtributo() {
		assertNotNull(NHSPDDReglasValidacion.validarPdlNivelAtributo(pdlNivelAtributoDTO, camposAValidar));
	}

	@Test
	public void testValidarPotObra() throws Exception {
		assertNotNull(NHSPDDReglasValidacion.validarPotObra(potObraDTO, camposAValidar));
	}

	@Test
	public void testValidarPotIntrumento() throws Exception {
		assertNotNull(NHSPDDReglasValidacion.validarPotIntrumento(potInstrumentoDTO, camposAValidar));
	}

}
