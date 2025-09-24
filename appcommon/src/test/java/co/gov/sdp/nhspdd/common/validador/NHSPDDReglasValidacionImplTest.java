package co.gov.sdp.nhspdd.common.validador;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import co.gov.sdp.nhspdd.common.dto.ArchivoProcesadoDTO;
import co.gov.sdp.nhspdd.common.dto.ArgumentoListaSimpleDTO;
import co.gov.sdp.nhspdd.common.dto.BuzonNotificacionesDTO;
import co.gov.sdp.nhspdd.common.dto.CampoValidacionDTO;
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
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;

public class NHSPDDReglasValidacionImplTest {

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
	private PddCompetenciaAsociadaDTO ppCompetenciaAsociadaDTO;
	private PddPrbValoracionDTO pddPrbValoracionDTO;
	private PddCompromisoEspecificoDTO pddCompromisoEspecificoDTO;
	private CompromisoEstrategicoDTO compromisoEstrategicoDTO;
	private PddCompromisoDTO pddCompromisoDTO;
	private PddDTO pddDTO;
	private PddMetaDTO pddMetaDTO;
	private PddProblematicaDTO pddProblematicaDTO;
	private PddObraConcretaDTO pddObraConcretaDTO;
	private PddPrbPoblacionDTO pddPrbPoblacionDTO;
	private PddPrbIndicadorDTO pddPrbIndicadorDTO;
	private PddMetaResultadoDTO pddMetaResultadoDTO;
	private PddIndicadorDTO pddIndicadorDTO;
	private BpIniciativaCiudadanaDTO bpIniciativaCiudadanaDTO;
	private BpProyectoInversionDTO bpProyectoInversionDTO;
	private BpProyInvAporteDTO bpProyInvAporteDTO;
	private PdlDTO pdlDTO;
	private PdlNivelDTO pdlNivelDTO;
	private PdlNivelAtributoDTO pdlNivelAtributoDTO;
	private Map<String, Boolean> camposAValidar;
	private PotDTO potDTO;
	private PotObraDTO potObraDTO;
	private PotInstrumentoDTO potInstrumentoDTO;

	@Before
	public void setUp() throws Exception {
		usuarioDTO = new UsuariosDTO();
		componenteGestionUsuarioDTO = new ComponenteGestionUsuarioDTO();
		listaSimpleDTO = new ListaSimpleDTO();
		proyectoInversionDTO = new ProyectoInversionDTO();
		proyectosInversionUsuarioDTO = new ProyectosInversionUsuarioDTO();
		entidadDTO = new EntidadDTO();
		entidadDTO.setCodigoEntidad(null);
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
		pddPrbValoracionDTO = new PddPrbValoracionDTO();
		ppCompetenciaAsociadaDTO = new PddCompetenciaAsociadaDTO();
		compromisoEstrategicoDTO = new CompromisoEstrategicoDTO();
		pddCompromisoDTO = new PddCompromisoDTO();
		camposAValidar = new HashMap<>();
		pddDTO = new PddDTO();
		pddMetaDTO = new PddMetaDTO();
		pddObraConcretaDTO = new PddObraConcretaDTO();
		pddProblematicaDTO = new PddProblematicaDTO();
		pddPrbPoblacionDTO = new PddPrbPoblacionDTO();
		pddPrbIndicadorDTO = new PddPrbIndicadorDTO();
		pddMetaResultadoDTO = new PddMetaResultadoDTO();
		pddIndicadorDTO = new PddIndicadorDTO();
		bpIniciativaCiudadanaDTO = new BpIniciativaCiudadanaDTO();
		bpProyectoInversionDTO = new BpProyectoInversionDTO();
		bpProyInvAporteDTO = new BpProyInvAporteDTO();
		potDTO = new PotDTO();
		pdlDTO = new PdlDTO();
		pdlNivelDTO = new PdlNivelDTO();
		pdlNivelAtributoDTO = new PdlNivelAtributoDTO();
		potObraDTO = new PotObraDTO();
		potInstrumentoDTO = new PotInstrumentoDTO();
		
		
	}

	@Test
	public void testConstructorIsPrivate()
			throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
		Constructor<NHSPDDReglasValidacionImpl> constructor = NHSPDDReglasValidacionImpl.class.getDeclaredConstructor();
		assertTrue(Modifier.isPrivate(constructor.getModifiers()));
		constructor.setAccessible(true);
		constructor.newInstance();
	}

	@Test
	public void testObtenerReglasEntidad() {
		camposAValidar = NHSPDDCamposValidar.crearEntidadCamposValidacion();
		camposAValidar.put(NHSPDDConstantes.ENTIDAD_GESTION_PROYECTOS, true);
		camposAValidar.put(NHSPDDConstantes.ENTIDAD_GESTION_USUARIOS, true);
		assertNotNull(entidadDTO);
		List<CampoValidacionDTO> resultado = NHSPDDReglasValidacionImpl.obtenerReglasEntidad(entidadDTO,
				camposAValidar);

		assertNotNull(resultado);
	}

	@Test
	public void testObtenerReglasProyectoInversion() {
		camposAValidar = NHSPDDCamposValidar.crearProyectoInversionCamposValidacion();
		camposAValidar.put(NHSPDDConstantes.PROYECTO_INVERSION_ID_PROYECTO_INVERSION, true);
		List<CampoValidacionDTO> actualList = NHSPDDReglasValidacionImpl
				.obtenerReglasProyectoInversion(proyectoInversionDTO, camposAValidar);
		assertNotNull(actualList);
	}

	@Test
	public void testObtenerReglasComponenteGestionUsuario() {
		camposAValidar = NHSPDDCamposValidar.asignarComponenteUsuario();
		camposAValidar.put(NHSPDDConstantes.COMPONENTE_GESTION_USUARIO_ID_GESTION_USUARIO, true);
		List<CampoValidacionDTO> actualList = NHSPDDReglasValidacionImpl
				.obtenerReglasComponenteGestionUsuario(componenteGestionUsuarioDTO, camposAValidar);
		assertNotNull(actualList);
	}

	@Test
	public void testObtenerReglasProyectosInversionUsuario() {
		camposAValidar = NHSPDDCamposValidar.asignarProyectoInversionUsuarioCamposValidacion();
		camposAValidar.put(NHSPDDConstantes.PROYECTOS_INVERSION_USUARIO_ID_PROYECTO_USUARIO, true);
		List<CampoValidacionDTO> actualList = NHSPDDReglasValidacionImpl
				.obtenerReglasProyectosInversionUsuario(proyectosInversionUsuarioDTO, camposAValidar);
		assertNotNull(actualList);
	}

	@Test
	public void testObtenerReglasUsuario() {
		camposAValidar.put(NHSPDDConstantes.USUARIO_ID, true);
		camposAValidar.put(NHSPDDConstantes.USUARIO_CLAVE, true);
		camposAValidar.put(NHSPDDConstantes.USUARIO_CLAVE_ANTERIOR, true);
		camposAValidar.put(NHSPDDConstantes.USUARIO_CLAVE_NUEVA, true);
		camposAValidar.put(NHSPDDConstantes.USUARIO_CORREO, true);
		camposAValidar.put(NHSPDDConstantes.USUARIO_IDENTIFICACION, true);
		camposAValidar.put(NHSPDDConstantes.USUARIO_TIPO, true);
		camposAValidar.put(NHSPDDConstantes.USUARIO_USUARIO, true);
		List<CampoValidacionDTO> actualList = NHSPDDReglasValidacionImpl.obtenerReglasUsuario(usuarioDTO,
				camposAValidar);
		assertNotNull(actualList);
	}

	@Test
	public void testObtenerReglasUsuarioEntidad() {
		camposAValidar.put(NHSPDDConstantes.USUARIO_ENTIDAD_ID_USUARIO_ENTIDAD, true);
		camposAValidar.put(NHSPDDConstantes.USUARIO_ENTIDAD_CODIGO_ENTIDAD, true);
		camposAValidar.put(NHSPDDConstantes.USUARIO_ENTIDAD_USUARIO, true);
		List<CampoValidacionDTO> actualList = NHSPDDReglasValidacionImpl.obtenerReglasUsuarioEntidad(usuarioEntidadDTO,
				camposAValidar);
		assertNotNull(actualList);
	}

	@Test
	public void testObtenerReglasListaSimple() {
		camposAValidar = NHSPDDCamposValidar.crearListaSimple();
		camposAValidar.put(NHSPDDConstantes.LISTA_SIMPLE_ID_LISTA_SIMPLE, true);
		List<CampoValidacionDTO> actualList = NHSPDDReglasValidacionImpl.obtenerReglasListaSimple(listaSimpleDTO,
				camposAValidar);
		assertNotNull(actualList);
	}

	@Test
	public void testObtenerReglasArgumentoListaSimple() {
		camposAValidar = NHSPDDCamposValidar.registrarArgumentoListaSimple();
		camposAValidar.put(NHSPDDConstantes.ARGUMENTO_LISTA_SIMPLE_ID_ARGUMENTO, true);
		List<CampoValidacionDTO> actualList = NHSPDDReglasValidacionImpl
				.obtenerReglasArgumentoListaSimple(argumentoListaSimpleDTO, camposAValidar);
		assertNotNull(actualList);
	}

	@Test
	public void testObtenerReglasPotProyectoInstrumento() {
		camposAValidar = NHSPDDCamposValidar.registrarPotProyectoInstrumento();
		camposAValidar.put(NHSPDDConstantes.POT_PROYECTO_INSTRUMENTO_ID_PROYECTO_INSTRUMENTO, true);
		List<CampoValidacionDTO> actualList = NHSPDDReglasValidacionImpl
				.obtenerReglasPotProyectoInstrumento(potProyectoInstrumentoDTO, camposAValidar);
		assertNotNull(actualList);
	}

	@Test
	public void testObtenerReglasComponenteGasto() {
		camposAValidar = NHSPDDCamposValidar.crearComponenteGasto();
		camposAValidar.put(NHSPDDConstantes.COMPONENTE_GASTO_ID_COMPONENTE_GASTO, true);
		List<CampoValidacionDTO> actualList = NHSPDDReglasValidacionImpl
				.obtenerReglasComponenteGasto(componenteGastoDTO, camposAValidar);
		assertNotNull(actualList);
	}

	@Test
	public void testObtenerReglasLineaInversion() {
		camposAValidar = NHSPDDCamposValidar.registrarLineaInverision();
		camposAValidar.put(NHSPDDConstantes.LINEA_INVERSION_ID_LS_SECTOR, true);
		camposAValidar.put(NHSPDDConstantes.LINEA_INVERSION_ID_LINEA_INVERSION, true);
		List<CampoValidacionDTO> actualList = NHSPDDReglasValidacionImpl
				.obtenerReglasLineaInversion(lineaDeInversionDTO, camposAValidar);
		assertNotNull(actualList);
	}

	@Test
	public void testObtenerReglasTerritorializacion() {
		camposAValidar = NHSPDDCamposValidar.registrarTerritorializacion();
		camposAValidar.put(NHSPDDConstantes.TERRITORIALIZACION_ID_TERRITORIALIZACION, true);
		camposAValidar.put(NHSPDDConstantes.TERRITORIALIZACION_ID_LS_BARRIO, true);
		camposAValidar.put(NHSPDDConstantes.TERRITORIALIZACION_ID_LS_VEREDA, true);
		camposAValidar.put(NHSPDDConstantes.TERRITORIALIZACION_ID_LS_UPR, true);
		camposAValidar.put(NHSPDDConstantes.TERRITORIALIZACION_ID_LS_UPZ, true);
		List<CampoValidacionDTO> actualList = NHSPDDReglasValidacionImpl
				.obtenerReglasTerritorializacion(territorializacionDTO, camposAValidar);
		assertNotNull(actualList);
	}

	@Test
	public void testObtenerReglasConsecutivo() {
		camposAValidar = NHSPDDCamposValidar.registrarConsecutivo();
		camposAValidar.put(NHSPDDConstantes.CONSECUTIVO_ID_CONSECUTIVO, true);
		List<CampoValidacionDTO> actualList = NHSPDDReglasValidacionImpl.obtenerReglasConsecutivo(consecutivoDTO,
				camposAValidar);
		assertNotNull(actualList);
	}

	@Test
	public void testObtenerReglasParametroGeneral() {
		camposAValidar.put(NHSPDDConstantes.PARAMETRO_GENERAL_CODIGO_PARAMETRO, true);
		camposAValidar.put(NHSPDDConstantes.PARAMETRO_GENERAL_ARGUMENTO, true);
		camposAValidar.put(NHSPDDConstantes.PARAMETRO_GENERAL_DESCRIPCION, true);
		camposAValidar.put(NHSPDDConstantes.PARAMETRO_GENERAL_FECHA_CREACION, true);
		camposAValidar.put(NHSPDDConstantes.PARAMETRO_GENERAL_FECHA_MODIFICACION, true);
		camposAValidar.put(NHSPDDConstantes.PARAMETRO_GENERAL_NOMBRE, true);
		camposAValidar.put(NHSPDDConstantes.PARAMETRO_GENERAL_USUARIO_CREACION, true);
		camposAValidar.put(NHSPDDConstantes.PARAMETRO_GENERAL_USUARIO_MODIFICACION, true);
		List<CampoValidacionDTO> actualList = NHSPDDReglasValidacionImpl
				.obtenerReglasParametroGeneral(parametroGeneralDTO, camposAValidar);
		assertNotNull(actualList);
	}

	@Test
	public void testObtenerReglasEstructuraMeta() {
		camposAValidar = NHSPDDCamposValidar.crearEstructuraMeta();
		camposAValidar.put(NHSPDDConstantes.ESTRUCTURA_METAS_ID_ESTRUCTURA_METAS, true);
		List<CampoValidacionDTO> actualList = NHSPDDReglasValidacionImpl.obtenerReglasEstructuraMeta(estructuraMetaDTO,
				camposAValidar);
		assertNotNull(actualList);
	}

	@Test
	public void testObtenerReglasBuzonNotificacion() {
		camposAValidar.put(NHSPDDConstantes.BUZON_NOTIFICACIONES_ID_NOTIFICACION, true);
		camposAValidar.put(NHSPDDConstantes.BUZON_NOTIFICACIONES_ESTADO, true);
		camposAValidar.put(NHSPDDConstantes.BUZON_NOTIFICACIONES_FECHA_ESCRITURA, true);
		camposAValidar.put(NHSPDDConstantes.BUZON_NOTIFICACIONES_FECHA_RESPUESTA, true);
		camposAValidar.put(NHSPDDConstantes.BUZON_NOTIFICACIONES_FECHA_LECTURA, true);
		camposAValidar.put(NHSPDDConstantes.BUZON_NOTIFICACIONES_ID_CONFIG_NOTIFICACION, true);
		camposAValidar.put(NHSPDDConstantes.BUZON_NOTIFICACIONES_MENSAJE, true);
		camposAValidar.put(NHSPDDConstantes.BUZON_NOTIFICACIONES_RESPUESTA, true);
		camposAValidar.put(NHSPDDConstantes.BUZON_NOTIFICACIONES_TIPO_MENSAJE, true);
		camposAValidar.put(NHSPDDConstantes.BUZON_NOTIFICACIONES_USUARIO_DESTINO, true);
		camposAValidar.put(NHSPDDConstantes.BUZON_NOTIFICACIONES_USUARIO_ORIGINA, true);
		List<CampoValidacionDTO> actualList = NHSPDDReglasValidacionImpl
				.obtenerReglasBuzonNotificacion(buzonNotificacionesDTO, camposAValidar);
		assertNotNull(actualList);
	}

	@Test
	public void testObtenerReglasConfiguracionNotificacion() {
		camposAValidar.put(NHSPDDConstantes.CONFIGURACION_NOTIFICACION_ID_CONFIG_NOTIFICACION, true);
		camposAValidar.put(NHSPDDConstantes.CONFIGURACION_NOTIFICACION_ASUNTO, true);
		camposAValidar.put(NHSPDDConstantes.CONFIGURACION_NOTIFICACION_MENSAJE, true);
		camposAValidar.put(NHSPDDConstantes.CONFIGURACION_NOTIFICACION_OPERACION_ORIGEN, true);
		camposAValidar.put(NHSPDDConstantes.CONFIGURACION_NOTIFICACION_REQUIERE_ACCION, true);
		camposAValidar.put(NHSPDDConstantes.CONFIGURACION_NOTIFICACION_ROL, true);
		camposAValidar.put(NHSPDDConstantes.CONFIGURACION_NOTIFICACION_USUARIOS, true);
		List<CampoValidacionDTO> actualList = NHSPDDReglasValidacionImpl
				.obtenerReglasConfiguracionNotificacion(configuracionNotificacionDTO, camposAValidar);
		assertNotNull(actualList);
	}

	@Test
	public void testObtenerReglasEquipamiento() {
		camposAValidar = NHSPDDCamposValidar.crearEquipamiento();
		camposAValidar.put(NHSPDDConstantes.EQUIPAMIENTO_ID_EQUIPAMIENTO, true);
		List<CampoValidacionDTO> actualList = NHSPDDReglasValidacionImpl.obtenerReglasEquipamiento(equipamientoDTO,
				camposAValidar);
		assertNotNull(actualList);
	}

	@Test
	public void testObtenerReglasInformacionPresupuestal() {
		camposAValidar = NHSPDDCamposValidar.crearInformacionPresupuestal();
		camposAValidar.put(NHSPDDConstantes.INFORMACION_PRESUPUESTAL_ID_INFO_PRESUPUESTAL, true);
		List<CampoValidacionDTO> actualList = NHSPDDReglasValidacionImpl
				.obtenerReglasInformacionPresupuestal(informacionPresupuestalDTO, camposAValidar);
		assertNotNull(actualList);
	}

	@Test
	public void testObtenerArchivoProcesado() {
		camposAValidar = NHSPDDCamposValidar.cargarArchivoPlano();
		camposAValidar.put(NHSPDDConstantes.ARCHIVO_PROCESADO_ID_ARCHIVO, true);
		camposAValidar.put(NHSPDDConstantes.ARCHIVO_PROCESADO_DETALLE, true);
		camposAValidar.put(NHSPDDConstantes.ARCHIVO_PROCESADO_ESTADO, true);
		camposAValidar.put(NHSPDDConstantes.ARCHIVO_PROCESADO_FECHA_CARGUE, true);
		camposAValidar.put(NHSPDDConstantes.ARCHIVO_PROCESADO_ID_CONFIG_CARGUE, true);
		List<CampoValidacionDTO> actualList = NHSPDDReglasValidacionImpl.obtenerArchivoProcesado(archivoProcesadoDTO,
				camposAValidar);
		assertNotNull(actualList);
	}

	@Test
	public void testObtenerReglasHistorialAdministrativo() {
		camposAValidar = NHSPDDCamposValidar.crearHistorialAdministrativo();
		camposAValidar.put(NHSPDDConstantes.HISTORIAL_ADMINISTRATIVO_CODIGO_HIS_ADMIN, true);
		camposAValidar.put(NHSPDDConstantes.HISTORIAL_ADMINISTRATIVO_NOMBRE, true);
		camposAValidar.put(NHSPDDConstantes.HISTORIAL_ADMINISTRATIVO_SIGLA, true);
		camposAValidar.put(NHSPDDConstantes.HISTORIAL_ADMINISTRATIVO_FECHA_INICIO, true);
		List<CampoValidacionDTO> actualList = NHSPDDReglasValidacionImpl
				.obtenerReglasHistorialAdministrativo(historialAdministrativoDTO, camposAValidar);
		assertNotNull(actualList);
	}

	@Test
	public void testObtenerReglasHistorialSectorial() {
		camposAValidar = NHSPDDCamposValidar.crearHistorialSectorial();
		camposAValidar.put(NHSPDDConstantes.HISTORIAL_SECTORIAL_CODIGO_HIS_SECTORIAL, true);
		camposAValidar.put(NHSPDDConstantes.HISTORIAL_SECTORIAL_NOMBRE, true);
		camposAValidar.put(NHSPDDConstantes.HISTORIAL_SECTORIAL_ACTIVO, true);
		camposAValidar.put(NHSPDDConstantes.HISTORIAL_SECTORIAL_FECHA_INICIO, true);
		List<CampoValidacionDTO> actualList = NHSPDDReglasValidacionImpl
				.obtenerReglasHistorialSectorial(historialSectorialDTO, camposAValidar);
		assertNotNull(actualList);
	}

	@Test
	public void testObtenerReglasFuncionarioClaveEntidad() {
		camposAValidar = NHSPDDCamposValidar.cargarArchivoPlano();
		camposAValidar.put(NHSPDDConstantes.FUNCIONARIO_CLAVE_ENTIDAD_ID_FUNCIONARIO_CLAVE_ENTIDAD, true);
		camposAValidar.put(NHSPDDConstantes.FUNCIONARIO_CLAVE_ENTIDAD_CODIDO_ENTIDAD, true);
		camposAValidar.put(NHSPDDConstantes.FUNCIONARIO_CLAVE_ENTIDAD_NOMBRE, true);
		camposAValidar.put(NHSPDDConstantes.FUNCIONARIO_CLAVE_ENTIDAD_ID_LS_FUNCION, true);
		camposAValidar.put(NHSPDDConstantes.FUNCIONARIO_CLAVE_ENTIDAD_ID_LS_GENERO, true);
		camposAValidar.put(NHSPDDConstantes.FUNCIONARIO_CLAVE_ENTIDAD_CARGO, true);
		camposAValidar.put(NHSPDDConstantes.FUNCIONARIO_CLAVE_ENTIDAD_CORREO, true);
		List<CampoValidacionDTO> actualList = NHSPDDReglasValidacionImpl
				.obtenerReglasCrearFuncionarioClaveEntidad(funcionarioClaveEntidadDTO, camposAValidar);
		assertNotNull(actualList);
	}

	@Test
	public void testObtenerReglasConsultarUsuario() {
		camposAValidar = NHSPDDCamposValidar.cargarArchivoPlano();
		camposAValidar.put(NHSPDDConstantes.SEG_USUARIO_USUARIO, true);
		camposAValidar.put(NHSPDDConstantes.SEG_USUARIO_CORREO_ELECTRONICO, true);
		camposAValidar.put(NHSPDDConstantes.SEG_USUARIO_NOMBRE, true);
		camposAValidar.put(NHSPDDConstantes.SEG_USUARIO_CODIGO_TIPO_USUARIO, true);
		camposAValidar.put(NHSPDDConstantes.SEG_USUARIO_CODIGO_ENTIDAD, true);
		camposAValidar.put(NHSPDDConstantes.SEG_USUARIO_ESTADO, true);
		List<CampoValidacionDTO> actualList = NHSPDDReglasValidacionImpl.obtenerReglasConsultarUsuario(usuarioDTO,
				camposAValidar);
		assertNotNull(actualList);
	}

	@Test
	public void testvalidarCampoDe() {
		camposAValidar = new HashMap<>();
		camposAValidar.put(NHSPDDConstantes.SEG_USUARIO_USUARIO, true);
		assertFalse(NHSPDDReglasValidacionImpl.validarCampo(camposAValidar,
				NHSPDDConstantes.SEG_USUARIO_CORREO_ELECTRONICO, "", 0));
		camposAValidar.put(NHSPDDConstantes.SEG_USUARIO_CORREO_ELECTRONICO, true);
		assertFalse(NHSPDDReglasValidacionImpl.validarCampo(camposAValidar,
				NHSPDDConstantes.SEG_USUARIO_CORREO_ELECTRONICO, "", 0));
		assertFalse(NHSPDDReglasValidacionImpl.validarCampo(camposAValidar,
				NHSPDDConstantes.SEG_USUARIO_CORREO_ELECTRONICO, "Prueba", 1));
		assertTrue(NHSPDDReglasValidacionImpl.validarCampo(camposAValidar,
				NHSPDDConstantes.SEG_USUARIO_CORREO_ELECTRONICO, "", 1));
	}

	@Test
	public void testvalidarCampoDeTexto() {

		List<String> mensajes = new ArrayList<>();
		mensajes.add(NHSPDDConstantes.MENSAJE_VALIDACION_MENSAJE_VACIO);
		mensajes.add(NHSPDDConstantes.MENSAJE_VALIDACION_MENSAJE_TAMANO_INVALIDO);

		assertNotNull(NHSPDDReglasValidacionImpl.validarCampoDeTexto(mensajes, null, "", 1, 2));
		assertNotNull(NHSPDDReglasValidacionImpl.validarCampoDeTexto(mensajes, null, "te", 3, 4));
		assertNotNull(NHSPDDReglasValidacionImpl.validarCampoDeTexto(mensajes, null, "test", 1, 2));
		assertNotNull(NHSPDDReglasValidacionImpl.validarCampoDeTexto(mensajes, null, "test", 1, 6));
	}

	@Test
	public void testObtenerReglasPddCompromisoEspecifico() {
		camposAValidar = NHSPDDCamposValidar.registrarPddCompromisoEspecifico();
		camposAValidar.put(NHSPDDConstantes.PDD_COMPROMISO_ESPEDIFICO_ID_COMPROMISO, true);
		camposAValidar.put(NHSPDDConstantes.PDD_COMPROMISO_ESPEDIFICO_DESCRIPCION, true);
		camposAValidar.put(NHSPDDConstantes.PDD_COMPROMISO_ESPEDIFICO_ID_ESPECIFICO, true);

		List<CampoValidacionDTO> actualList = NHSPDDReglasValidacionImpl
				.obtenerReglasPddCompromisoEspecifico(pddCompromisoEspecificoDTO, camposAValidar);
		assertNotNull(actualList);
	}

	@Test
	public void testObtenerReglasPddPrbValoracion() {
		camposAValidar = NHSPDDCamposValidar.registrarPddPrbValoracion();
		camposAValidar.put(NHSPDDConstantes.PDD_PRB_VALORACION_OBSERVACIONES, true);
		camposAValidar.put(NHSPDDConstantes.PDD_PRB_VALORACION_ID_LS_COMPETENCIA_1, true);
		camposAValidar.put(NHSPDDConstantes.PDD_PRB_VALORACION_ID_LS_COMPETENCIA_2, true);
		camposAValidar.put(NHSPDDConstantes.PDD_PRB_VALORACION_ID_PROBLEMATICA, true);

		List<CampoValidacionDTO> actualList = NHSPDDReglasValidacionImpl
				.obtenerReglasPddPrbValoracion(pddPrbValoracionDTO, camposAValidar);
		assertNotNull(actualList);
	}

	@Test
	public void testObtenerReglasPddCompetenciaAsociada() {
		camposAValidar = NHSPDDCamposValidar.registrarPddCompetenciaAsociada();
		camposAValidar.put(NHSPDDConstantes.PDD_COMPETENCIA_ASOCIADA_ID_COMPETENCIA, true);
		List<CampoValidacionDTO> actualList = NHSPDDReglasValidacionImpl
				.obtenerReglasPddCompetenciaAsociada(ppCompetenciaAsociadaDTO, camposAValidar);
		assertNotNull(actualList);
	}

	@Test
	public void testObtenerReglasCompromisoEstrategico() {
		camposAValidar = NHSPDDCamposValidar.registrarCompromisoEstrategico();
		camposAValidar.put(NHSPDDConstantes.COMPROMISO_ESTRATEGICO_ID_COMPROMISO, true);
		List<CampoValidacionDTO> actualList = NHSPDDReglasValidacionImpl
				.obtenerReglasCompromisoEstrategico(compromisoEstrategicoDTO, camposAValidar);
		assertNotNull(actualList);
	}

	@Test
	public void testObtenerReglasPddCompromiso() {
		camposAValidar = NHSPDDCamposValidar.registrarPddCompromiso();
		camposAValidar.put(NHSPDDConstantes.PDD_COMPROMISO_ID_ESTRATEGICO, true);
		camposAValidar.put(NHSPDDConstantes.PDD_COMPROMISO_ID_COMPROMISO, true);
		List<CampoValidacionDTO> actualList = NHSPDDReglasValidacionImpl.obtenerReglasPddCompromiso(pddCompromisoDTO,
				camposAValidar);
		assertNotNull(actualList);
	}

	@Test
	public void testObtenerReglasPdd() {
		camposAValidar = NHSPDDCamposValidar.registrarPdd();
		List<CampoValidacionDTO> actualList = NHSPDDReglasValidacionImpl.obtenerReglasPdd(pddDTO, camposAValidar);
		assertNotNull(actualList);
	}

	@Test
	public void testObtenerReglasPddNivel() {
		camposAValidar = NHSPDDCamposValidar.registrarPddNivel();
		camposAValidar.put(NHSPDDConstantes.PDD_NIVEL_ID_PDD_NIVEL, true);
		List<CampoValidacionDTO> actualList = NHSPDDReglasValidacionImpl.obtenerReglasPdd(pddDTO, camposAValidar);
		assertNotNull(actualList);
	}

	@Test
	public void testObtenerReglasPddMeta() {
		camposAValidar = NHSPDDCamposValidar.registrarPddMeta();
		camposAValidar.put(NHSPDDConstantes.PDD_META_ID_META, true);
		List<CampoValidacionDTO> actualList = NHSPDDReglasValidacionImpl.obtenerReglasPddMeta(pddMetaDTO,
				camposAValidar);
		assertNotNull(actualList);
	}

	@Test
	public void testObtenerReglasPddObraConcreta() {
		camposAValidar = NHSPDDCamposValidar.registrarPddObraConcreta();
		camposAValidar.put(NHSPDDConstantes.PDD_OBRA_CONCRETA_ID_CONCRETA, true);
		List<CampoValidacionDTO> actualList = NHSPDDReglasValidacionImpl
				.obtenerReglasPddObraConcreta(pddObraConcretaDTO, camposAValidar);
		assertNotNull(actualList);
	}

	@Test
	public void testObtenerReglasPddProblematica() {
		camposAValidar = NHSPDDCamposValidar.registrarPddProblematica();
		camposAValidar.put(NHSPDDConstantes.PDD_PROBLEMATICA_ID_PROBLEMATICA, true);
		List<CampoValidacionDTO> actualList = NHSPDDReglasValidacionImpl
				.obtenerReglasPddProblematica(pddProblematicaDTO, camposAValidar);
		assertNotNull(actualList);
	}

	@Test
	public void testObtenerReglasPddPbrPoblacion() {
		camposAValidar = NHSPDDCamposValidar.registrarPddPbrPoblacion();
		camposAValidar.put(NHSPDDConstantes.PDD_PBR_POBLACION_ID_POBLACION, true);
		List<CampoValidacionDTO> actualList = NHSPDDReglasValidacionImpl
				.obtenerReglasPddPbrPoblacion(pddPrbPoblacionDTO, camposAValidar);
		assertNotNull(actualList);

	}

	@Test
	public void testObtenerReglasPddPrbIndicador() {
		camposAValidar = NHSPDDCamposValidar.registrarPddPrbIndicador();
		camposAValidar.put(NHSPDDConstantes.PDD_PRB_INDICADOR_ID_INDICADOR, true);
		List<CampoValidacionDTO> actualList = NHSPDDReglasValidacionImpl
				.obtenerReglasPddPrbIndicador(pddPrbIndicadorDTO, camposAValidar);
		assertNotNull(actualList);
	}

	@Test
	public void testObtenerReglasPddMetaResultado() {
		camposAValidar = NHSPDDCamposValidar.registrarPddMetaResultado();
		camposAValidar.put(NHSPDDConstantes.PDD_META_RESULTADO_ID_META_RESULTADO, true);
		List<CampoValidacionDTO> actualList = NHSPDDReglasValidacionImpl
				.obtenerReglasPddMetaResultado(pddMetaResultadoDTO, camposAValidar);
		assertNotNull(actualList);
	}

	@Test
	public void testObtenerReglasPddIndicadorYPrbIndicador() {
		camposAValidar = NHSPDDCamposValidar.registrarPddIndicador();
		camposAValidar.put(NHSPDDConstantes.PDD_INDICADOR_ID_INDICADOR, true);
		List<CampoValidacionDTO> actualList = NHSPDDReglasValidacionImpl.obtenerReglasPddIndicador(pddIndicadorDTO,
				camposAValidar);
		assertNotNull(actualList);

	}
	
	@Test
	public void testObtenerReglasIniciativaCiudadana() {
		camposAValidar = NHSPDDCamposValidar.registrarIniciativaCiudadana();
		camposAValidar.put(NHSPDDConstantes.BP_INICIATIVA_CIUDADANA_RADICADO, true);
		camposAValidar.put(NHSPDDConstantes.BP_INICIATIVA_CIUDADANA_FECHA_RADICADO, true);
		camposAValidar.put(NHSPDDConstantes.BP_INICIATIVA_CIUDADANA_RADICADO, true);
		camposAValidar.put(NHSPDDConstantes.BP_INICIATIVA_CIUDADANA_NUMERO_RADICADO, true);
		camposAValidar.put(NHSPDDConstantes.BP_INICIATIVA_CIUDADANA_NOMBRE, true);
		camposAValidar.put(NHSPDDConstantes.BP_INICIATIVA_CIUDADANA_ID_LS_ORIGEN, true);
		camposAValidar.put(NHSPDDConstantes.BP_INICIATIVA_CIUDADANA_APLICA_LINEA, true);
		camposAValidar.put(NHSPDDConstantes.BP_INICIATIVA_CIUDADANA_PROBLEMATICA, true);
		camposAValidar.put(NHSPDDConstantes.BP_INICIATIVA_CIUDADANA_ID_LC_TERRITORIALIZACION_UPZ, true);
		camposAValidar.put(NHSPDDConstantes.BP_INICIATIVA_CIUDADANA_ID_LC_TERRITORIALIZACION_UPR, true);
		camposAValidar.put(NHSPDDConstantes.BP_INICIATIVA_CIUDADANA_GRUPOS_ETARIOS, true);
		camposAValidar.put(NHSPDDConstantes.BP_INICIATIVA_CIUDADANA_TOTAL_POBLACION, true);
		camposAValidar.put(NHSPDDConstantes.BP_INICIATIVA_CIUDADANA_ALTERNATIVA_SOLUCION, true);
		camposAValidar.put(NHSPDDConstantes.BP_INICIATIVA_CIUDADANA_NOMBRE_PROP, true);
		camposAValidar.put(NHSPDDConstantes.BP_INICIATIVA_CIUDADANA_ID_LS_ESTADO_INICIA, true);
		camposAValidar.put(NHSPDDConstantes.BP_INICIATIVA_CIUDADANA_CONDICION_POBLACIONAL, true);
		camposAValidar.put(NHSPDDConstantes.BP_INICIATIVA_CIUDADANA_CODIGO_ENTIDAD, true);
		List<CampoValidacionDTO> actualList = NHSPDDReglasValidacionImpl.obtenerReglasIniciativaCiudadana(bpIniciativaCiudadanaDTO, 
				camposAValidar);
		assertNotNull(actualList);
	}
	
	@Test
	public void testObtenerProyectoInversionTABIndentificacionProyecto()
	{
		camposAValidar = NHSPDDCamposValidar.registrarProyectoInversionTABIndentificacionProyecto();
		camposAValidar.put(NHSPDDConstantes.BP_PROYECTO_INVERSION_ID_PROYECTO_INVERSION, true);
		List<CampoValidacionDTO> actualList = NHSPDDReglasValidacionImpl.obtenerReglasProyectoInversionTABIndentificacionProyecto(bpProyectoInversionDTO, 
				camposAValidar);
		assertNotNull(actualList);
	}
	
	@Test
	public void testObtenerReglasBpProyInvAporte()
	{
		camposAValidar = NHSPDDCamposValidar.registrarBpProyInvAporte();
		camposAValidar.put(NHSPDDConstantes.BP_PROY_INV_APORTE_ID_APORTE, true);
		camposAValidar.put(NHSPDDConstantes.BP_PROY_INV_APORTE_ID_PROY_INV_APORTE, true);
		List<CampoValidacionDTO> actualList = NHSPDDReglasValidacionImpl.obtenerReglasBpProyInvAporte(bpProyInvAporteDTO, camposAValidar);
		assertNotNull(actualList);
	}
	
	@Test
	public void testObtenerReglasIpPot() {
		camposAValidar.put(NHSPDDConstantes.IP_POT_ID_POT, true);
		camposAValidar.put(NHSPDDConstantes.IP_POT_CODIGO_POT, true);
		camposAValidar.put(NHSPDDConstantes.IP_POT_ACTO_ADMINISTRATIVO, true);
		camposAValidar.put(NHSPDDConstantes.IP_POT_FECHA, true);
		camposAValidar.put(NHSPDDConstantes.IP_POT_ID_LS_ADOPTADO, true);
		camposAValidar.put(NHSPDDConstantes.IP_POT_YEAR_FIN, true);
		camposAValidar.put(NHSPDDConstantes.IP_POT_YEAR_INICIO, true);
		camposAValidar.put(NHSPDDConstantes.IP_POT_NOMBRE, true);
		camposAValidar.put(NHSPDDConstantes.IP_POT_ESTADO, true);
		
		List<CampoValidacionDTO> actualList = NHSPDDReglasValidacionImpl.obtenerReglasIpPot(potDTO, camposAValidar);
		assertNotNull(actualList);
	}
	public void testObtenerReglasPdl() {
		camposAValidar = NHSPDDCamposValidar.registrarPdl();
		camposAValidar.put(NHSPDDConstantes.PDL_ID_PLAN_LOCAL, true);
		camposAValidar.put(NHSPDDConstantes.PDL_NOMBRE_PLAN, true);
		camposAValidar.put(NHSPDDConstantes.PDL_IDLSADOPTADO, true);
		camposAValidar.put(NHSPDDConstantes.PDL_ACTO_ADMINISTRATIVO, true);
		camposAValidar.put(NHSPDDConstantes.PDL_FECHA_ACTO, true);
		camposAValidar.put(NHSPDDConstantes.PDL_YEAR_INICIO, true);
		camposAValidar.put(NHSPDDConstantes.PDL_YEAR_FINAL, true);
		camposAValidar.put(NHSPDDConstantes.PDL_ID_PLAN_DESARROLLO, true);
		List<CampoValidacionDTO> actualList = NHSPDDReglasValidacionImpl.obtenerReglasPdl(pdlDTO, camposAValidar);

		assertNotNull(actualList);
	}

	@Test
	public void testObtenerReglasPdlNivel() {
		camposAValidar = NHSPDDCamposValidar.registrarPdlNivel();
		camposAValidar.put(NHSPDDConstantes.PDL_NIVEL_ID_PLAN_LOCAL, true);
		List<CampoValidacionDTO> actualList = NHSPDDReglasValidacionImpl.obtenerReglasPdlNivel(pdlNivelDTO, camposAValidar);
		assertNotNull(actualList);
	}

	@Test
	public void testObtenerReglasPdlNivelAtributo() {
		camposAValidar = NHSPDDCamposValidar.registrarPdlNivelAtributo();
		camposAValidar.put(NHSPDDConstantes.PDL_NIVEL_ATRIBUTO_NUMERO, true);
		camposAValidar.put(NHSPDDConstantes.PDL_NIVEL_ATRIBUTO_DENOMINACION, true);
		camposAValidar.put(NHSPDDConstantes.PDL_NIVEL_ATRIBUTO_NOMBRE_GERENTE, true);
		camposAValidar.put(NHSPDDConstantes.PDL_NIVEL_ATRIBUTO_CORREO_GERENTE, true);
		camposAValidar.put(NHSPDDConstantes.PDL_NIVEL_ATRIBUTO_ID_LS_GENERO_GERENTE, true);
		List<CampoValidacionDTO> actualList = NHSPDDReglasValidacionImpl.obtenerReglasPdlNivelAtributo(pdlNivelAtributoDTO, camposAValidar);
		assertNotNull(actualList);
	}

	@Test
	public void testObtenerReglasPotObra() throws Exception {
		camposAValidar = NHSPDDCamposValidar.registrarPotObra();
		List<CampoValidacionDTO> actualList = NHSPDDReglasValidacionImpl.obtenerReglasPotObra(potObraDTO, camposAValidar);
		assertNotNull(actualList);
	}

	@Test
	public void testObtenerReglasPotInstrumento() throws Exception {
		camposAValidar = NHSPDDCamposValidar.registrarPotObra();
		List<CampoValidacionDTO> actualList = NHSPDDReglasValidacionImpl.obtenerReglasPotInstrumento(potInstrumentoDTO, camposAValidar);
		assertNotNull(actualList);
	}
	
}
