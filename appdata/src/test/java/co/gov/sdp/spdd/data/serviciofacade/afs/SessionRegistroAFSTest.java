package co.gov.sdp.spdd.data.serviciofacade.afs;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import co.gov.sdp.nhspdd.common.dto.ArchivoProcesadoDTO;
import co.gov.sdp.nhspdd.common.dto.ArgumentoListaSimpleDTO;
import co.gov.sdp.nhspdd.common.dto.BancoDeProyectoDTO;
import co.gov.sdp.nhspdd.common.dto.BuzonNotificacionesDTO;
import co.gov.sdp.nhspdd.common.dto.ComponenteGastoDTO;
import co.gov.sdp.nhspdd.common.dto.ComponenteGestionUsuarioDTO;
import co.gov.sdp.nhspdd.common.dto.ConsecutivoDTO;
import co.gov.sdp.nhspdd.common.dto.EntidadDTO;
import co.gov.sdp.nhspdd.common.dto.EquipamientoDTO;
import co.gov.sdp.nhspdd.common.dto.EstadoServicioDTO;
import co.gov.sdp.nhspdd.common.dto.EstructuraMetaDTO;
import co.gov.sdp.nhspdd.common.dto.FuncionarioClaveEntidadDTO;
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
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.spdd.data.dao.interfaces.afs.IArchivoProcesadoServiceRepo;
import co.gov.sdp.spdd.data.dao.interfaces.afs.IArgumentoListaSimpleServiceRepo;
import co.gov.sdp.spdd.data.dao.interfaces.afs.IBancoDeProyectoServiceRepo;
import co.gov.sdp.spdd.data.dao.interfaces.afs.IBuzonNotificacioneServiceRepo;
import co.gov.sdp.spdd.data.dao.interfaces.afs.IComponenteGastoServiceRepo;
import co.gov.sdp.spdd.data.dao.interfaces.afs.IComponenteGestionUsuarioServiceRepo;
import co.gov.sdp.spdd.data.dao.interfaces.afs.IConfigCargueArchivoServiceRepo;
import co.gov.sdp.spdd.data.dao.interfaces.afs.IConfiguracionNotificacionServiceRepo;
import co.gov.sdp.spdd.data.dao.interfaces.afs.IConsecutivoServiceRepo;
import co.gov.sdp.spdd.data.dao.interfaces.afs.IEntidadServiceRepo;
import co.gov.sdp.spdd.data.dao.interfaces.afs.IEquipamientoServiceRepo;
import co.gov.sdp.spdd.data.dao.interfaces.afs.IEstadoServicioServiceRepo;
import co.gov.sdp.spdd.data.dao.interfaces.afs.IEstructuraMetaServiceRepo;
import co.gov.sdp.spdd.data.dao.interfaces.afs.IFuncionarioClaveEntidadServiceRepo;
import co.gov.sdp.spdd.data.dao.interfaces.afs.IInformacionPresupuestalServiceRepo;
import co.gov.sdp.spdd.data.dao.interfaces.afs.ILineaDeInversionServiceRepo;
import co.gov.sdp.spdd.data.dao.interfaces.afs.IListaCompuestaServiceRepo;
import co.gov.sdp.spdd.data.dao.interfaces.afs.IListaSimpleServiceRepo;
import co.gov.sdp.spdd.data.dao.interfaces.afs.IParametroGeneralServiceRepo;
import co.gov.sdp.spdd.data.dao.interfaces.afs.IProyectoInversionServiceRepo;
import co.gov.sdp.spdd.data.dao.interfaces.afs.IProyectosInversionUsuarioServiceRepo;
import co.gov.sdp.spdd.data.dao.interfaces.afs.ITerritorializacionServiceRepo;
import co.gov.sdp.spdd.data.dao.interfaces.afs.IUsuarioEntidadServiceRepo;
import co.gov.sdp.spdd.data.dao.interfaces.ip.IPddNivelAtributoServiceRepo;
import co.gov.sdp.spdd.data.dao.interfaces.ip.IPddServiceRepo;
import co.gov.sdp.spdd.data.dao.interfaces.ip.IPotInstrumentoServiceRepo;
import co.gov.sdp.spdd.data.dao.interfaces.ip.IPotObraServiceRepo;
import co.gov.sdp.spdd.data.dao.interfaces.ip.IPotProyectoInstrumentoServiceRepo;
import co.gov.sdp.spdd.data.mapper.ArchivoProcesadoMapper;
import co.gov.sdp.spdd.data.mapper.ArgumentoListaSimpleMapper;
import co.gov.sdp.spdd.data.mapper.BancoDeProyectoMapper;
import co.gov.sdp.spdd.data.mapper.BuzonNotificacionesMapper;
import co.gov.sdp.spdd.data.mapper.ComponenteGastoMapper;
import co.gov.sdp.spdd.data.mapper.ComponenteGestionUsuarioMapper;
import co.gov.sdp.spdd.data.mapper.ConfigCargueArchivoMapper;
import co.gov.sdp.spdd.data.mapper.ConfiguracionNotificacionMapper;
import co.gov.sdp.spdd.data.mapper.ConsecutivoMapper;
import co.gov.sdp.spdd.data.mapper.EntidadMapper;
import co.gov.sdp.spdd.data.mapper.EquipamientoMapper;
import co.gov.sdp.spdd.data.mapper.EstadoServicioMapper;
import co.gov.sdp.spdd.data.mapper.EstructuraMetaMapper;
import co.gov.sdp.spdd.data.mapper.FuncionarioClaveEntidadMapper;
import co.gov.sdp.spdd.data.mapper.InformacionPresupuestalMapper;
import co.gov.sdp.spdd.data.mapper.LineaDeInversionMapper;
import co.gov.sdp.spdd.data.mapper.ListaCompuestaMapper;
import co.gov.sdp.spdd.data.mapper.ListaSimpleMapper;
import co.gov.sdp.spdd.data.mapper.ParametroGeneralMapper;
import co.gov.sdp.spdd.data.mapper.PddMapper;
import co.gov.sdp.spdd.data.mapper.PddNivelAtributoMapper;
import co.gov.sdp.spdd.data.mapper.PotInstrumentoMapper;
import co.gov.sdp.spdd.data.mapper.PotObraMapper;
import co.gov.sdp.spdd.data.mapper.PotProyectoInstrumentoMapper;
import co.gov.sdp.spdd.data.mapper.ProyectoInversionMapper;
import co.gov.sdp.spdd.data.mapper.ProyectosInversionUsuarioMapper;
import co.gov.sdp.spdd.data.mapper.TerritorializacionMapper;
import co.gov.sdp.spdd.data.mapper.UsuarioEntidadMapper;
import co.gov.sdp.spdd.data.model.afs.ArchivoProcesado;
import co.gov.sdp.spdd.data.model.afs.ArgumentoListaSimple;
import co.gov.sdp.spdd.data.model.afs.BancoDeProyectos;
import co.gov.sdp.spdd.data.model.afs.BuzonNotificaciones;
import co.gov.sdp.spdd.data.model.afs.ComponenteGasto;
import co.gov.sdp.spdd.data.model.afs.ComponenteGestionUsuario;
import co.gov.sdp.spdd.data.model.afs.Consecutivo;
import co.gov.sdp.spdd.data.model.afs.Entidad;
import co.gov.sdp.spdd.data.model.afs.Equipamento;
import co.gov.sdp.spdd.data.model.afs.EstadoServicio;
import co.gov.sdp.spdd.data.model.afs.EstructuraMeta;
import co.gov.sdp.spdd.data.model.afs.FuncionarioClaveEntidad;
import co.gov.sdp.spdd.data.model.afs.InformacionPresupuestal;
import co.gov.sdp.spdd.data.model.afs.LineaDeInversion;
import co.gov.sdp.spdd.data.model.afs.ListaCompuesta;
import co.gov.sdp.spdd.data.model.afs.ListaSimple;
import co.gov.sdp.spdd.data.model.afs.ParametroGeneral;
import co.gov.sdp.spdd.data.model.afs.ProyectoInversion;
import co.gov.sdp.spdd.data.model.afs.ProyectosInversionUsuario;
import co.gov.sdp.spdd.data.model.afs.UsuarioEntidad;
import co.gov.sdp.spdd.data.model.ip.PotProyectoInstrumento;

/**
 * 
 * @author Sumset
 *
 */
@RunWith(SpringRunner.class)
public class SessionRegistroAFSTest {

	@TestConfiguration
	static class SessionRegistoaAFSTestContextConfiguration {
		@Bean
		public SessionRegistroAFS sessionRegistroAFS() {
			return new SessionRegistroAFS();
		}
	}

	@Autowired
	SessionRegistroAFS sessionRegistroAFS;

	@MockBean
	SPDDLogger spddLogger;

	/**
	 * Atributo que representa objeto de acceso a datos por medio del cual se
	 * realiza las tareas de la capa de persistencia.
	 */
	@MockBean
	IArchivoProcesadoServiceRepo archivoProcesadoServiceRepo;

	/**
	 * 
	 */
	@MockBean
	IConfigCargueArchivoServiceRepo configCargueArchivoServiceRepo;

	/**
	 * Atributo que representa objeto de acceso a datos por medio del cual se
	 * realiza las tareas de la capa de persistencia.
	 */
	@MockBean
	IArgumentoListaSimpleServiceRepo argumentoListaSimpleServiceRepo;

	/**
	 * Atributo que representa objeto de acceso a datos por medio del cual se
	 * realiza las tareas de la capa de persistencia.
	 */
	@MockBean
	IBancoDeProyectoServiceRepo bancoDeProyectoServiceRepo;

	/**
	 * Atributo que representa objeto de acceso a datos por medio del cual se
	 * realiza las tareas de la capa de persistencia.
	 */
	@MockBean
	IBuzonNotificacioneServiceRepo buzonNotificacioneServiceRepo;

	/**
	 * Atributo que representa objeto de acceso a datos por medio del cual se
	 * realiza las tareas de la capa de persistencia.
	 */
	@MockBean
	IComponenteGastoServiceRepo componenteGastoServiceRepo;

	/**
	 * Atributo que representa objeto de acceso a datos por medio del cual se
	 * realiza las tareas de la capa de persistencia.
	 */
	@MockBean
	IComponenteGestionUsuarioServiceRepo componenteGestionUsuarioServiceRepo;

	/**
	 * Atributo que representa objeto de acceso a datos por medio del cual se
	 * realiza las tareas de la capa de persistencia.
	 */
	@MockBean
	IConsecutivoServiceRepo consecutivoServiceRepo;

	/**
	 * Atributo que representa objeto de acceso a datos por medio del cual se
	 * realiza las tareas de la capa de persistencia.
	 */
	@MockBean
	IEntidadServiceRepo entidadServiceRepo;

	/**
	 * Atributo que representa objeto de acceso a datos por medio del cual se
	 * realiza las tareas de la capa de persistencia.
	 */
	@MockBean
	IEquipamientoServiceRepo equipamientoServiceRepo;

	/**
	 * Atributo que representa objeto de acceso a datos por medio del cual se
	 * realiza las tareas de la capa de persistencia.
	 */
	@MockBean
	IEstadoServicioServiceRepo estadoServicioServiceRepo;

	/**
	 * Atributo que representa objeto de acceso a datos por medio del cual se
	 * realiza las tareas de la capa de persistencia.
	 */
	@MockBean
	IEstructuraMetaServiceRepo estructuraMetaServiceRepo;

	/**
	 * Atributo que representa objeto de acceso a datos por medio del cual se
	 * realiza las tareas de la capa de persistencia.
	 */
	@MockBean
	IFuncionarioClaveEntidadServiceRepo funcionarioClaveEntidadServiceRepo;

	/**
	 * Atributo que representa objeto de acceso a datos por medio del cual se
	 * realiza las tareas de la capa de persistencia.
	 */
	@MockBean
	IInformacionPresupuestalServiceRepo informacionPresupuestalServiceRepo;

	/**
	 * Atributo que representa objeto de acceso a datos por medio del cual se
	 * realiza las tareas de la capa de persistencia.
	 */
	@MockBean
	ILineaDeInversionServiceRepo lineaDeInversionServiceRepo;

	/**
	 * Atributo que representa objeto de acceso a datos por medio del cual se
	 * realiza las tareas de la capa de persistencia.
	 */
	@MockBean
	IListaCompuestaServiceRepo listaCompuestaServiceRepo;

	/**
	 * Atributo que representa objeto de acceso a datos por medio del cual se
	 * realiza las tareas de la capa de persistencia.
	 */
	@MockBean
	IListaSimpleServiceRepo listaSimpleServiceRepo;

	/**
	 * Atributo que representa objeto de acceso a datos por medio del cual se
	 * realiza las tareas de la capa de persistencia.
	 */
	@MockBean
	IParametroGeneralServiceRepo parametroGeneralServiceRepo;

	/**
	 * Atributo que representa objeto de acceso a datos por medio del cual se
	 * realiza las tareas de la capa de persistencia.
	 */
	@MockBean
	IPotProyectoInstrumentoServiceRepo potProyectoInstrumentoServiceRepo;

	/**
	 * 
	 */
	@MockBean
	IPotInstrumentoServiceRepo potInstrumentoServiceRepo;

	/**
	 * 
	 */
	@MockBean
	IPotObraServiceRepo potObraServiceRepo;

	/**
	 * Atributo que representa objeto de acceso a datos por medio del cual se
	 * realiza las tareas de la capa de persistencia.
	 */
	@MockBean
	IProyectoInversionServiceRepo proyectoInversionServiceRepo;

	/**
	 * Atributo que representa objeto de acceso a datos por medio del cual se
	 * realiza las tareas de la capa de persistencia.
	 */
	@MockBean
	IProyectosInversionUsuarioServiceRepo proyectosInversionUsuarioServiceRepo;

	/**
	 * Atributo que representa objeto de acceso a datos por medio del cual se
	 * realiza las tareas de la capa de persistencia.
	 */
	@MockBean
	ITerritorializacionServiceRepo territorializacionServiceRepo;

	/**
	 * Atributo que representa objeto de acceso a datos por medio del cual se
	 * realiza las tareas de la capa de persistencia.
	 */
	@MockBean
	IUsuarioEntidadServiceRepo usuarioEntidadServiceRepo;

	/**
	 * 
	 */
	@MockBean
	IPddNivelAtributoServiceRepo pddNivelAtributoServiceRepo;

	/**
	 * 
	 */
	@MockBean
	IConfiguracionNotificacionServiceRepo configuracionNotificacionServiceRepo;

	/**
	 * 
	 */
	@MockBean
	IPddServiceRepo pddServiceRepo;

	/**
	 * Atributo que representa objeto de mapeo entre entidades y dtos y viceversa.
	 */
	@MockBean
	ArchivoProcesadoMapper archivoProcesadoMapper;

	/**
	 * 
	 */
	@MockBean
	ConfigCargueArchivoMapper configCargueArchivoMapper;

	/**
	 * Atributo que representa objeto de mapeo entre entidades y dtos y viceversa.
	 */
	@MockBean
	ArgumentoListaSimpleMapper argumentoListaSimpleMapper;

	/**
	 * Atributo que representa objeto de mapeo entre entidades y dtos y viceversa.
	 */
	@MockBean
	BancoDeProyectoMapper bancoDeProyectoMapper;

	/**
	 * Atributo que representa objeto de mapeo entre entidades y dtos y viceversa.
	 */
	@MockBean
	BuzonNotificacionesMapper buzonNotificacionesMapper;

	/**
	 * Atributo que representa objeto de mapeo entre entidades y dtos y viceversa.
	 */
	@MockBean
	ComponenteGastoMapper componenteGastoMapper;

	/**
	 * Atributo que representa objeto de mapeo entre entidades y dtos y viceversa.
	 */
	@MockBean
	ComponenteGestionUsuarioMapper componenteGestionUsuarioMapper;

	/**
	 * Atributo que representa objeto de mapeo entre entidades y dtos y viceversa.
	 */
	@MockBean
	ConsecutivoMapper consecutivoMapper;

	/**
	 * Atributo que representa objeto de mapeo entre entidades y dtos y viceversa.
	 */
	@MockBean
	EntidadMapper entidadMapper;

	/**
	 * Atributo que representa objeto de mapeo entre entidades y dtos y viceversa.
	 */
	@MockBean
	EquipamientoMapper equipamientoMapper;

	/**
	 * Atributo que representa objeto de mapeo entre entidades y dtos y viceversa.
	 */
	@MockBean
	EstadoServicioMapper estadoServicioMapper;

	/**
	 * Atributo que representa objeto de mapeo entre entidades y dtos y viceversa.
	 */
	@MockBean
	EstructuraMetaMapper estructuraMetaMapper;

	/**
	 * Atributo que representa objeto de mapeo entre entidades y dtos y viceversa.
	 */
	@MockBean
	FuncionarioClaveEntidadMapper funcionarioClaveEntidadMapper;

	/**
	 * Atributo que representa objeto de mapeo entre entidades y dtos y viceversa.
	 */
	@MockBean
	InformacionPresupuestalMapper informacionPresupuestalMapper;

	/**
	 * Atributo que representa objeto de mapeo entre entidades y dtos y viceversa.
	 */
	@MockBean
	LineaDeInversionMapper lineaDeInversionMapper;

	/**
	 * Atributo que representa objeto de mapeo entre entidades y dtos y viceversa.
	 */
	@MockBean
	ListaCompuestaMapper listaCompuestaMapper;

	/**
	 * Atributo que representa objeto de mapeo entre entidades y dtos y viceversa.
	 */
	@MockBean
	ListaSimpleMapper listaSimpleMapper;

	/**
	 * Atributo que representa objeto de mapeo entre entidades y dtos y viceversa.
	 */
	@MockBean
	ParametroGeneralMapper parametroGeneralMapper;

	/**
	 * Atributo que representa objeto de mapeo entre entidades y dtos y viceversa.
	 */
	@MockBean
	ProyectoInversionMapper proyectoInversionMapper;

	/**
	 * Atributo que representa objeto de mapeo entre entidades y dtos y viceversa.
	 */
	@MockBean
	ProyectosInversionUsuarioMapper proyectosInversionUsuarioMapper;

	/**
	 * Atributo que representa objeto de mapeo entre entidades y dtos y viceversa.
	 */
	@MockBean
	PotProyectoInstrumentoMapper potProyectoInstrumentoMapper;

	/**
	 * 
	 */
	@MockBean
	PotInstrumentoMapper potInstrumentoMapper;

	/**
	 * 
	 */
	@MockBean
	PotObraMapper potObraMapper;

	/**
	 * Atributo que representa objeto de mapeo entre entidades y dtos y viceversa.
	 */
	@MockBean
	TerritorializacionMapper territorializacionMapper;

	/**
	 * Atributo que representa objeto de mapeo entre entidades y dtos y viceversa.
	 */
	@MockBean
	UsuarioEntidadMapper usuarioEntidadMapper;

	/**
	 * 
	 */
	@MockBean
	PddNivelAtributoMapper pddNivelAtributoMapper;

	/**
	 * 
	 */
	@MockBean
	ConfiguracionNotificacionMapper configuracionNotificacionMapper;

	/**
	 * 
	 */
	@MockBean
	PddMapper pddMapper;

	@Test
	public void guardarArchivoProcesadoTest() {
		ArchivoProcesadoDTO archivo = new ArchivoProcesadoDTO();
		ArchivoProcesado value = new ArchivoProcesado();
		when(archivoProcesadoMapper.ArchivoProcesadoDTOToEntity(archivo)).thenReturn(value);
		when(archivoProcesadoMapper.ArchivoProcesadoEntityToDTO(archivoProcesadoServiceRepo.guardar(value)))
				.thenReturn(archivo);
		assertThat(sessionRegistroAFS.guardarArchivoProcesado(archivo)).isNotNull();

	}

	@Test
	public void guardarArgumentoListaSimpleTest() {
		ArgumentoListaSimple entidad = new ArgumentoListaSimple();
		ArgumentoListaSimpleDTO argumetno = new ArgumentoListaSimpleDTO();
		when(argumentoListaSimpleMapper.argumentoListaSimpleDTOToEntity(argumetno)).thenReturn(entidad);
		when(argumentoListaSimpleMapper
				.argumentoListaSimpleEntityToDTO(argumentoListaSimpleServiceRepo.guardar(entidad)))
						.thenReturn(argumetno);
		assertThat(sessionRegistroAFS.guardarArgumentoListaSimple(argumetno)).isNotNull();
	}

	@Test
	public void guardarBancoDeProyectoTest() {
		BancoDeProyectoDTO bancoDTO = new BancoDeProyectoDTO();
		BancoDeProyectos entidad = new BancoDeProyectos();
		when(bancoDeProyectoMapper.bancoDeProyectoDTOToEntity(bancoDTO)).thenReturn(entidad);
		when(bancoDeProyectoMapper.bancoDeProyectoEntityToDTO(bancoDeProyectoServiceRepo.guardar(entidad)))
				.thenReturn(bancoDTO);
		assertThat(sessionRegistroAFS.guardarBancoDeProyecto(bancoDTO)).isNotNull();

	}

	@Test
	public void guardarBuzonNotificacionesTest() {
		BuzonNotificaciones entidad = new BuzonNotificaciones();
		BuzonNotificacionesDTO buzonDTO = new BuzonNotificacionesDTO();
		when(buzonNotificacionesMapper.buzonNotificacionesDTOToEntity(buzonDTO)).thenReturn(entidad);
		when(buzonNotificacionesMapper.buzonNotificacionesEntityToDTO(buzonNotificacioneServiceRepo.guardar(entidad)))
				.thenReturn(buzonDTO);
		assertThat(sessionRegistroAFS.guardarBuzonNotificaciones(buzonDTO)).isNotNull();
	}

	@Test
	public void guardarComponenteGastoTest() {
		ComponenteGastoDTO componenteGastoDTO = new ComponenteGastoDTO();
		ComponenteGasto entidad = new ComponenteGasto();
		when(componenteGastoMapper.componenteGastoDTOToEntity(componenteGastoDTO)).thenReturn(entidad);
		when(componenteGastoMapper.componenteGastoEntityToDTO(componenteGastoServiceRepo.guardar(entidad)))
				.thenReturn(componenteGastoDTO);
		assertThat(sessionRegistroAFS.guardarComponenteGasto(componenteGastoDTO)).isNotNull();
	}

	@Test
	public void guardarComponenteGestionusuarioTest() {
		ComponenteGestionUsuario entidad = new ComponenteGestionUsuario();
		ComponenteGestionUsuarioDTO componenteGestionUsuarioDTO = new ComponenteGestionUsuarioDTO();
		when(componenteGestionUsuarioMapper.componenteGestionUsuarioDTOToEntity(componenteGestionUsuarioDTO))
				.thenReturn(entidad);
		when(componenteGestionUsuarioMapper
				.componenteGestionUsuarioEntityToDTO(componenteGestionUsuarioServiceRepo.guardar(entidad)))
						.thenReturn(componenteGestionUsuarioDTO);
		assertThat(sessionRegistroAFS.guardarComponenteGestionusuario(componenteGestionUsuarioDTO)).isNotNull();
	}

	@Test
	public void guardarConsecutivoTest() {
		ConsecutivoDTO consecutivoDTO = new ConsecutivoDTO();
		Consecutivo consecutivo = new Consecutivo();
		when(consecutivoMapper.consecutivoDTOTOEntity(consecutivoDTO)).thenReturn(consecutivo);
		when(consecutivoMapper.consecutivoEntityToDTO(consecutivoServiceRepo.guardar(consecutivo)))
				.thenReturn(consecutivoDTO);
		assertThat(sessionRegistroAFS.guardarConsecutivo(consecutivoDTO)).isNotNull();
	}

	@Test
	public void guardarEntidadTest() {
		Entidad entidad = new Entidad();
		EntidadDTO entidadDTO = new EntidadDTO();
		when(entidadMapper.entidadDTOToEntity(entidadDTO)).thenReturn(entidad);
		when(entidadMapper.entidadEntityToDTO(entidadServiceRepo.guardar(entidad))).thenReturn(entidadDTO);
		assertThat(sessionRegistroAFS.guardarEntidad(entidadDTO)).isNotNull();
	}

	@Test
	public void guardarEquipamientoTest() {
		EquipamientoDTO equipamientoDTO = new EquipamientoDTO();
		Equipamento equipamento = new Equipamento();
		when(equipamientoMapper.equipamientoDTOToEntity(equipamientoDTO)).thenReturn(equipamento);
		when(equipamientoMapper.equipamientoEntityToDTO(equipamientoServiceRepo.guardar(equipamento)))
				.thenReturn(equipamientoDTO);
		assertThat(sessionRegistroAFS.guardarEquipamiento(equipamientoDTO)).isNotNull();
	}

	@Test
	public void guardarEstadoServicioTest() {
		EstadoServicio estadoServicio = new EstadoServicio();
		EstadoServicioDTO estadoServicioDTO = new EstadoServicioDTO();
		when(estadoServicioMapper.estadoServicioDTOToEntity(estadoServicioDTO)).thenReturn(estadoServicio);
		when(estadoServicioMapper.estadoServicioEntityToDTO(estadoServicioServiceRepo.guardar(estadoServicio)))
				.thenReturn(estadoServicioDTO);
		assertThat(sessionRegistroAFS.guardarEstadoServicio(estadoServicioDTO)).isNotNull();
	}

	@Test
	public void guardarEstructuraMetaTest() {
		EstructuraMeta estructuraMeta = new EstructuraMeta();
		EstructuraMetaDTO estructuraMetaDTO = new EstructuraMetaDTO();
		when(estructuraMetaMapper.estructuraMetaDTOToEntity(estructuraMetaDTO)).thenReturn(estructuraMeta);
		when(estructuraMetaMapper.estructuraMetaEntityToDTO(estructuraMetaServiceRepo.guardar(estructuraMeta)))
				.thenReturn(estructuraMetaDTO);
		assertThat(sessionRegistroAFS.guardarEstructuraMeta(estructuraMetaDTO)).isNotNull();

	}

	@Test
	public void guardarFuncionarioClaveEntidadTest() {
		FuncionarioClaveEntidadDTO funcionarioClaveEntidadDTO = new FuncionarioClaveEntidadDTO();
		FuncionarioClaveEntidad funcionarioClaveEntidad = new FuncionarioClaveEntidad();
		when(funcionarioClaveEntidadMapper.funcionarioClaveEntidadDTOToEntity(funcionarioClaveEntidadDTO))
				.thenReturn(funcionarioClaveEntidad);
		when(funcionarioClaveEntidadMapper
				.funcionarioClaveEntidadToDTO(funcionarioClaveEntidadServiceRepo.guardar(funcionarioClaveEntidad)))
						.thenReturn(funcionarioClaveEntidadDTO);
		assertThat(sessionRegistroAFS.guardarFuncionarioClaveEntidad(funcionarioClaveEntidadDTO)).isNotNull();
	}

	@Test
	public void guardarInformacionPresupuestalTest() {
		InformacionPresupuestal informacionPresupuestal = new InformacionPresupuestal();
		InformacionPresupuestalDTO informacionPresupuestalDTO = new InformacionPresupuestalDTO();
		when(informacionPresupuestalMapper.informacionPresupuestalDTOToEntity(informacionPresupuestalDTO))
				.thenReturn(informacionPresupuestal);
		when(informacionPresupuestalMapper.informacionPresupuestalEntityToDTO(
				informacionPresupuestalServiceRepo.guardar(informacionPresupuestal)))
						.thenReturn(informacionPresupuestalDTO);
		assertThat(sessionRegistroAFS.guardarInformacionPresupuestal(informacionPresupuestalDTO)).isNotNull();
	}

	@Test
	public void guardarLineaDeInversionTest() {
		LineaDeInversion lineaDeInversion = new LineaDeInversion();
		LineaDeInversionDTO lineaDeInversionDTO = new LineaDeInversionDTO();
		when(lineaDeInversionMapper.lineaDeInversionDTOToEntity(lineaDeInversionDTO)).thenReturn(lineaDeInversion);
		when(lineaDeInversionMapper.lineaDeInversionEntityToDTO(lineaDeInversionServiceRepo.guardar(lineaDeInversion)))
				.thenReturn(lineaDeInversionDTO);
		assertThat(sessionRegistroAFS.guardarLineaDeInversion(lineaDeInversionDTO)).isNotNull();
	}

	@Test
	public void guardarListaCompuestaTest() {
		ListaCompuestaDTO listaCompuestaDTO = new ListaCompuestaDTO();
		ListaCompuesta listaCompuesta = new ListaCompuesta();
		when(listaCompuestaMapper.listaCompuestaDTOToEntity(listaCompuestaDTO)).thenReturn(listaCompuesta);
		when(listaCompuestaMapper.listaCompuestaEntityToDTO(listaCompuestaServiceRepo.guardar(listaCompuesta)))
				.thenReturn(listaCompuestaDTO);
		assertThat(sessionRegistroAFS.guardarListaCompuesta(listaCompuestaDTO)).isNotNull();
	}

	@Test
	public void guardarListaSimpleTest() {
		ListaSimple listaSimple = new ListaSimple();
		ListaSimpleDTO listaSimpleDTO = new ListaSimpleDTO();
		when(listaSimpleMapper.listaSimpleDTOToEntity(listaSimpleDTO)).thenReturn(listaSimple);
		when(listaSimpleMapper.listaSimpleEntityToDTO(listaSimpleServiceRepo.guardar(listaSimple)))
				.thenReturn(listaSimpleDTO);
		assertThat(sessionRegistroAFS.guardarListaSimple(listaSimpleDTO)).isNotNull();
	}

	@Test
	public void guardarParametroGeneralTest() {
		ParametroGeneralDTO parametroGeneralDTO = new ParametroGeneralDTO();
		ParametroGeneral parametroGeneral = new ParametroGeneral();
		when(parametroGeneralMapper.parametroDTOToEntity(parametroGeneralDTO)).thenReturn(parametroGeneral);
		when(parametroGeneralMapper.parametroEntityToDTO(parametroGeneralServiceRepo.guardar(parametroGeneral)))
				.thenReturn(parametroGeneralDTO);
		assertThat(sessionRegistroAFS.guardarParametroGeneral(parametroGeneralDTO)).isNotNull();
	}

	@Test
	public void guardarPotProyectoInstrumentoTest() {
		PotProyectoInstrumentoDTO potProyectoInstrumentoDTO = new PotProyectoInstrumentoDTO();
		PotProyectoInstrumento potProyectoInstrumento = new PotProyectoInstrumento();
		when(potProyectoInstrumentoMapper.potProyectoInstrumentoDTOToEntity(potProyectoInstrumentoDTO))
				.thenReturn(potProyectoInstrumento);
		when(potProyectoInstrumentoMapper
				.potProyectoInstrumentoEntityToDTO(potProyectoInstrumentoServiceRepo.guardar(potProyectoInstrumento)))
						.thenReturn(potProyectoInstrumentoDTO);
		assertThat(sessionRegistroAFS.guardarPotProyectoInstrumento(potProyectoInstrumentoDTO));
	}

	@Test
	public void guardarProyectoInversionTest() {
		ProyectoInversionDTO proyectoInversionDTO = new ProyectoInversionDTO();
		ProyectoInversion proyectoInversion = new ProyectoInversion();
		when(proyectoInversionMapper.proyectoInversionDTOToEntity(proyectoInversionDTO)).thenReturn(proyectoInversion);
		when(proyectoInversionMapper
				.proyectoInversionEntityToDTO(proyectoInversionServiceRepo.guardar(proyectoInversion)))
						.thenReturn(proyectoInversionDTO);
		assertThat(sessionRegistroAFS.guardarProyectoInversion(proyectoInversionDTO)).isNotNull();
	}

	@Test
	public void guardarProyectosInversionUsuarioTest() {
		ProyectosInversionUsuario proyectosInversionUsuario = new ProyectosInversionUsuario();
		ProyectosInversionUsuarioDTO proyectosInversionUsuarioDTO = new ProyectosInversionUsuarioDTO();
		when(proyectosInversionUsuarioMapper.proyectosInversionUsuarioDTOToEntity(proyectosInversionUsuarioDTO))
				.thenReturn(proyectosInversionUsuario);
		when(proyectosInversionUsuarioMapper.proyectosInversionUsuarioEntityToDTO(
				proyectosInversionUsuarioServiceRepo.guardar(proyectosInversionUsuario)))
						.thenReturn(proyectosInversionUsuarioDTO);
		assertThat(sessionRegistroAFS.guardarProyectosInversionUsuario(proyectosInversionUsuarioDTO)).isNotNull();
	}

	@Test
	public void guardarTerritorializacionTest() {

		TerritorializacionDTO territorializacionDTO = new TerritorializacionDTO();
		when(territorializacionMapper.territorializacionEntityToDTO(territorializacionServiceRepo
				.guardar(territorializacionMapper.territorializacionDTOToEntity(territorializacionDTO))))
						.thenReturn(territorializacionDTO);
		assertThat(sessionRegistroAFS.guardarTerritorializacion(territorializacionDTO)).isNotNull();
	}

	@Test
	public void guardarUsuarioEntidadTest() {
		UsuarioEntidad usuarioEntidad = new UsuarioEntidad();
		UsuarioEntidadDTO usuarioEntidadDTO = new UsuarioEntidadDTO();
		when(usuarioEntidadMapper.usuarioEntidadDTOToEntity(usuarioEntidadDTO)).thenReturn(usuarioEntidad);
		when(usuarioEntidadMapper.usuarioEntidadEntityToDTO(usuarioEntidadServiceRepo.guardar(usuarioEntidad)))
				.thenReturn(usuarioEntidadDTO);
		assertThat(sessionRegistroAFS.guardarUsuarioEntidad(usuarioEntidadDTO)).isNotNull();
	}

	@Test
	public void guardarSqlTest() throws SpddExceptions {
		String sql = "Select * FROM";
		when(configCargueArchivoServiceRepo.guardarSql(sql)).thenReturn(1);
		assertThat(sessionRegistroAFS.guardarSql(sql)).isNotNull();
	}
}
