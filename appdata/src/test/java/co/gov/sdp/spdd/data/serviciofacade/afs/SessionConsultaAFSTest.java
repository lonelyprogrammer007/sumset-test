package co.gov.sdp.spdd.data.serviciofacade.afs;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
import co.gov.sdp.nhspdd.common.dto.FiltroDTO;
import co.gov.sdp.nhspdd.common.dto.FuncionarioClaveEntidadDTO;
import co.gov.sdp.nhspdd.common.dto.InformacionPresupuestalDTO;
import co.gov.sdp.nhspdd.common.dto.LineaDeInversionDTO;
import co.gov.sdp.nhspdd.common.dto.ListaCompuestaDTO;
import co.gov.sdp.nhspdd.common.dto.ListaSimpleDTO;
import co.gov.sdp.nhspdd.common.dto.ParametroGeneralDTO;
import co.gov.sdp.nhspdd.common.dto.PddDTO;
import co.gov.sdp.nhspdd.common.dto.PddNivelAtributoDTO;
import co.gov.sdp.nhspdd.common.dto.PotInstrumentoDTO;
import co.gov.sdp.nhspdd.common.dto.PotObraDTO;
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
import co.gov.sdp.spdd.data.model.afs.ConfigCargueArchivo;
import co.gov.sdp.spdd.data.model.afs.ConfiguracionNotificacion;
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
import co.gov.sdp.spdd.data.model.afs.Territorializacion;
import co.gov.sdp.spdd.data.model.afs.UsuarioEntidad;
import co.gov.sdp.spdd.data.model.ip.Pdd;
import co.gov.sdp.spdd.data.model.ip.PddNivelAtributo;
import co.gov.sdp.spdd.data.model.ip.PotInstrumento;
import co.gov.sdp.spdd.data.model.ip.PotObra;
import co.gov.sdp.spdd.data.model.ip.PotProyectoInstrumento;

/**
 * 
 * @author Sumset
 *
 */
@RunWith(SpringRunner.class)
public class SessionConsultaAFSTest {

	@TestConfiguration
	static class SessionConsultaAFSTestContextConfiguration {
		@Bean
		public SessionConsultaAFS sessionConsultaAFS() {
			return new SessionConsultaAFS();
		}
	}

	@Autowired
	SessionConsultaAFS sessionConsultaAFS;

	/**
	 * 
	 */
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
	public void testConsultarArchivoProcesadoPorId() {
		Long id = 1L;
		ArchivoProcesado archivoProcesado = new ArchivoProcesado();
		archivoProcesado.setIdArchivo(id);
		ArchivoProcesadoDTO respuesta = new ArchivoProcesadoDTO();
		respuesta.setIdArchivo(id);
		when(archivoProcesadoServiceRepo.obtener(id)).thenReturn(archivoProcesado);
		when(archivoProcesadoMapper.ArchivoProcesadoEntityToDTO(archivoProcesado)).thenReturn(respuesta);
		ArchivoProcesadoDTO archivoProcesadoDTO = sessionConsultaAFS.consultarArchivoProcesadoPorId(id);
		assertThat(archivoProcesadoDTO.getIdArchivo()).isEqualTo(respuesta.getIdArchivo());
	}

	@Test
	public void testConsultarConfigCargueArchivoProcesado() {
		Long id = 1L;
		Integer id2 = 1;
		ConfigCargueArchivo configCargueArchivo = new ConfigCargueArchivo();
		configCargueArchivo.setIdConfigCargue(id);
		ConfiguracionCargueArchivoDTO respuesta = new ConfiguracionCargueArchivoDTO();
		respuesta.setIdConfigCargue(id2);
		when(configCargueArchivoServiceRepo.obtenerConfigCarguePorArchivoProcesado(id)).thenReturn(configCargueArchivo);
		when(configCargueArchivoMapper.configuracionCargueEntityToDTO(configCargueArchivo)).thenReturn(respuesta);
		ConfiguracionCargueArchivoDTO configuracionCargueArchivoDTO = sessionConsultaAFS
				.consultarConfigCargueArchivoProcesado(id);
		assertThat(configuracionCargueArchivoDTO.getIdConfigCargue()).isEqualTo(respuesta.getIdConfigCargue());
	}

	@Test
	public void testConsultarArchivoProcesadofiltrarPorCampo() throws SpddExceptions {
		Integer pagina = 5;
		Integer tamanioPagina = 10;
		ArchivoProcesadoDTO archivoProcesadoDTO = new ArchivoProcesadoDTO();
		archivoProcesadoDTO.setPagina(pagina);
		archivoProcesadoDTO.setTamanioPagina(tamanioPagina);
		Pageable pageRequest = PageRequest.of(archivoProcesadoDTO.getPagina(), archivoProcesadoDTO.getTamanioPagina());
		FiltroDTO filtro = new FiltroDTO();
		filtro.setContador(100L);
		List<ArchivoProcesado> lista = new ArrayList<>();
		filtro.setResultadoConsulta(lista);
		when(archivoProcesadoServiceRepo.filtrarPorCampo(archivoProcesadoDTO, pageRequest.getOffset(),
				pageRequest.getPageSize())).thenReturn(filtro);
		assertNotNull(sessionConsultaAFS.consultarArchivoProcesadofiltrarPorCampo(archivoProcesadoDTO));

	}

	@Test
	public void testConsultarArgumentoListaSimplePorId() {
		Long id = 1L;
		ArgumentoListaSimple argumentoListaSimple = new ArgumentoListaSimple();
		ListaSimple lista = new ListaSimple();
		lista.setIdListaSimple(id);
		argumentoListaSimple.setIdListaSimple(lista);
		ArgumentoListaSimpleDTO respuesta = new ArgumentoListaSimpleDTO();
		respuesta.setIdListaSimple(id);
		when(argumentoListaSimpleServiceRepo.obtener(id)).thenReturn(argumentoListaSimple);
		when(argumentoListaSimpleMapper.argumentoListaSimpleEntityToDTO(argumentoListaSimple)).thenReturn(respuesta);
		ArgumentoListaSimpleDTO argumentoListaSimpleDTO = sessionConsultaAFS.consultarArgumentoListaSimplePorId(id);
		assertThat(argumentoListaSimpleDTO.getIdListaSimple()).isEqualTo(respuesta.getIdListaSimple());
	}

	@Test
	public void testConsultarArgumentoPorIdPdd() throws SpddExceptions {
		Long id = 1L;
		ArgumentoListaSimple argumentoListaSimple = new ArgumentoListaSimple();
		ListaSimple lista = new ListaSimple();
		lista.setIdListaSimple(id);
		argumentoListaSimple.setIdListaSimple(lista);
		ArgumentoListaSimpleDTO respuesta = new ArgumentoListaSimpleDTO();
		respuesta.setIdListaSimple(id);
		List<ArgumentoListaSimple> lstArgumentoListaSimple = new ArrayList<ArgumentoListaSimple>();
		lstArgumentoListaSimple.add(argumentoListaSimple);
		when(argumentoListaSimpleServiceRepo.obtenerArgumentoPorIdPdd(id)).thenReturn(lstArgumentoListaSimple);
		assertNotNull(sessionConsultaAFS.consultarArgumentoPorIdPdd(id));
	}

	@Test
	public void testConsultarBancoDeProyectoporId() {
		Long id = 1L;
		BancoDeProyectos bancoDeProyectos = new BancoDeProyectos();
		bancoDeProyectos.setIdBancoProyecto(id);
		BancoDeProyectoDTO respuesta = new BancoDeProyectoDTO();
		respuesta.setIdBancoProyecto(id);
		when(bancoDeProyectoServiceRepo.obtener(id)).thenReturn(bancoDeProyectos);
		when(bancoDeProyectoMapper.bancoDeProyectoEntityToDTO(bancoDeProyectos)).thenReturn(respuesta);
		BancoDeProyectoDTO bancoDeProyectoDTO = sessionConsultaAFS.consultarBancoDeProyectoporId(id);
		assertThat(bancoDeProyectoDTO.getIdBancoProyecto()).isEqualTo(respuesta.getIdBancoProyecto());
	}

	@Test
	public void testConsultarBuzonNotificacionesPorId() {
		Long id = 1L;
		BuzonNotificaciones buzonNotificaciones = new BuzonNotificaciones();
		buzonNotificaciones.setIdNotificacion(id);
		BuzonNotificacionesDTO respuesta = new BuzonNotificacionesDTO();
		respuesta.setIdNotificacion(id);
		when(buzonNotificacioneServiceRepo.obtener(id)).thenReturn(buzonNotificaciones);
		when(buzonNotificacionesMapper.buzonNotificacionesEntityToDTO(buzonNotificaciones)).thenReturn(respuesta);
		BuzonNotificacionesDTO buzonNotificacionesDTO = sessionConsultaAFS.consultarBuzonNotificacionesPorId(id);
		assertThat(buzonNotificacionesDTO.getIdNotificacion()).isEqualTo(respuesta.getIdNotificacion());
	}

	@Test
	public void testConsultarBuzonNotificacionesPorFiltro() throws SpddExceptions {
		Integer pagina = 5;
		Integer tamanioPagina = 10;
		BuzonNotificacionesDTO buzonNotificacionesDTO = new BuzonNotificacionesDTO();
		buzonNotificacionesDTO.setPagina(pagina);
		buzonNotificacionesDTO.setTamanioPagina(tamanioPagina);
		Pageable pageRequest = PageRequest.of(buzonNotificacionesDTO.getPagina(),
				buzonNotificacionesDTO.getTamanioPagina());
		FiltroDTO filtro = new FiltroDTO();
		List<BuzonNotificaciones> lista = new ArrayList<>();
		filtro.setResultadoConsulta(lista);
		filtro.setContador(200L);
		when(buzonNotificacioneServiceRepo.filtrarPorCampo(buzonNotificacionesDTO, pageRequest.getOffset(),
				pageRequest.getPageSize())).thenReturn(filtro);
		assertNotNull(sessionConsultaAFS.consultarBuzonNotificacionesPorFiltro(buzonNotificacionesDTO));
	}

	@Test
	public void testConsultarComponenteGastoPorId() {
		Long id = 1L;
		ComponenteGasto componenteGasto = new ComponenteGasto();
		componenteGasto.setIdComponenteGasto(id);
		ComponenteGastoDTO respuesta = new ComponenteGastoDTO();
		respuesta.setIdComponenteGasto(id);
		when(componenteGastoServiceRepo.obtener(id)).thenReturn(componenteGasto);
		when(componenteGastoMapper.componenteGastoEntityToDTO(componenteGasto)).thenReturn(respuesta);
		ComponenteGastoDTO componenteGastoDTO = sessionConsultaAFS.consultarComponenteGastoPorId(id);
		assertThat(componenteGastoDTO.getIdComponenteGasto()).isEqualTo(respuesta.getIdComponenteGasto());
	}

	@Test
	public void testConsultarComponenteGestionUsuarioPorId() {
		Long id = 1L;
		ComponenteGestionUsuario componenteGestionUsuario = new ComponenteGestionUsuario();
		componenteGestionUsuario.setIdGestionUsuario(id);
		ComponenteGestionUsuarioDTO respuesta = new ComponenteGestionUsuarioDTO();
		respuesta.setIdGestionUsuario(id);
		when(componenteGestionUsuarioServiceRepo.obtener(id)).thenReturn(componenteGestionUsuario);
		when(componenteGestionUsuarioMapper.componenteGestionUsuarioEntityToDTO(componenteGestionUsuario))
				.thenReturn(respuesta);
		ComponenteGestionUsuarioDTO componenteGestionUsuarioDTO = sessionConsultaAFS
				.consultarComponenteGestionUsuarioPorId(id);
		assertThat(componenteGestionUsuarioDTO.getIdGestionUsuario()).isEqualTo(respuesta.getIdGestionUsuario());
	}

	@Test
	public void testConsultarConsecutivoPorId() {
		Long id = 1L;
		Consecutivo consecutivo = new Consecutivo();
		consecutivo.setIdConsecutivo(id);
		ConsecutivoDTO respuesta = new ConsecutivoDTO();
		respuesta.setIdConsecutivo(id);
		when(consecutivoServiceRepo.obtener(id)).thenReturn(consecutivo);
		when(consecutivoMapper.consecutivoEntityToDTO(consecutivo)).thenReturn(respuesta);
		ConsecutivoDTO consecutivoDTO = new ConsecutivoDTO();
		consecutivoDTO = sessionConsultaAFS.consultarConsecutivoPorId(id);
		assertThat(consecutivoDTO.getIdConsecutivo()).isEqualTo(respuesta.getIdConsecutivo());
	}

	@Test
	public void testConsultarArchivoInfoPrespuestal() throws SpddExceptions {
		Long id = 1L;
		InformacionPresupuestal informacionPresupuestal = new InformacionPresupuestal();
		informacionPresupuestal.setIdInfoPresupuestal(id);
		ArchivoProcesado archivoProcesado = new ArchivoProcesado();
		archivoProcesado.setIdArchivo(id);
		informacionPresupuestal.setIdArchivo(archivoProcesado);
		InformacionPresupuestalDTO respuesta = new InformacionPresupuestalDTO();
		respuesta.setIdInfoPresupuestal(id);
		respuesta.setIdArchivo(id);
		List<InformacionPresupuestal> lstInformacionPresupuestal = new ArrayList<>();
		lstInformacionPresupuestal.add(informacionPresupuestal);
		when(informacionPresupuestalServiceRepo.obtenerPorArchivo(id)).thenReturn(lstInformacionPresupuestal);
		assertNotNull(sessionConsultaAFS.consultarArchivoInfoPrespuestal(id));
	}

	@Test
	public void testConsultarConsecutivoPorFiltro() throws SpddExceptions {
		Integer pagina = 5;
		Integer tamanioPagina = 10;
		ConsecutivoDTO consecutivoDTO = new ConsecutivoDTO();
		consecutivoDTO.setPagina(pagina);
		consecutivoDTO.setTamanioPagina(tamanioPagina);
		Pageable pageRequest = PageRequest.of(consecutivoDTO.getPagina(), consecutivoDTO.getTamanioPagina());
		FiltroDTO filtro = new FiltroDTO();
		List<Consecutivo> lista = new ArrayList<>();
		filtro.setResultadoConsulta(lista);
		filtro.setContador(200L);
		when(consecutivoServiceRepo.filtrarPorCampo(consecutivoDTO, pageRequest.getOffset(), pageRequest.getPageSize()))
				.thenReturn(filtro);
		assertNotNull(sessionConsultaAFS.consultarConsecutivoPorFiltro(consecutivoDTO));
	}

	@Test
	public void testConsultarEntidad() {
		String id = "1";
		Entidad entidad = new Entidad();
		entidad.setCodigoEntidad(id);
		EntidadDTO respuesta = new EntidadDTO();
		respuesta.setCodigoEntidad(id);
		when(entidadServiceRepo.obtener(id)).thenReturn(entidad);
		when(entidadMapper.entidadEntityToDTO(entidad)).thenReturn(respuesta);
		EntidadDTO entidadDTO = sessionConsultaAFS.consultarEntidad(id);
		assertThat(entidadDTO.getCodigoEntidad()).isEqualTo(respuesta.getCodigoEntidad());
	}

	@Test
	public void testConsultarEquipamiento() {
		Long id = 1L;
		Equipamento equipamento = new Equipamento();
		equipamento.setIdEquipamento(id);
		EquipamientoDTO respuesta = new EquipamientoDTO();
		respuesta.setIdEquipamento(id);
		when(equipamientoServiceRepo.obtener(id)).thenReturn(equipamento);
		when(equipamientoMapper.equipamientoEntityToDTO(equipamento)).thenReturn(respuesta);
		EquipamientoDTO equipamientoDTO = sessionConsultaAFS.consultarEquipamiento(id);
		assertThat(equipamientoDTO.getIdEquipamento()).isEqualTo(respuesta.getIdEquipamento());
	}

	@Test
	public void testConsultarEstadoServicio() {
		Long id = 1L;
		EstadoServicio estadoServicio = new EstadoServicio();
		estadoServicio.setIdEstadoServicio(id);
		EstadoServicioDTO respuesta = new EstadoServicioDTO();
		respuesta.setIdEstadoServicio(id);
		when(estadoServicioServiceRepo.obtener(id)).thenReturn(estadoServicio);
		when(estadoServicioMapper.estadoServicioEntityToDTO(estadoServicio)).thenReturn(respuesta);
		EstadoServicioDTO estadoServicioDTO = sessionConsultaAFS.consultarEstadoServicio(id);
		assertThat(estadoServicioDTO.getIdEstadoServicio()).isEqualTo(respuesta.getIdEstadoServicio());
	}

	@Test
	public void testConsultarEstructuraMeta() {
		Long id = 1L;
		EstructuraMeta estructuraMeta = new EstructuraMeta();
		estructuraMeta.setIdEstructuraMetas(id);
		EstructuraMetaDTO respuesta = new EstructuraMetaDTO();
		respuesta.setIdEstructuraMetas(id);
		when(estructuraMetaServiceRepo.obtener(id)).thenReturn(estructuraMeta);
		when(estructuraMetaMapper.estructuraMetaEntityToDTO(estructuraMeta)).thenReturn(respuesta);
		EstructuraMetaDTO estructuraMetaDTO = sessionConsultaAFS.consultarEstructuraMeta(id);
		assertThat(estructuraMetaDTO.getIdEstructuraMetas()).isEqualTo(respuesta.getIdEstructuraMetas());
	}

	@Test
	public void testConsultarFuncionarioClaveEntidad() {
		Long id = 1L;
		FuncionarioClaveEntidad funcionarioClaveEntidad = new FuncionarioClaveEntidad();
		funcionarioClaveEntidad.setIdFuncionarioEntidad(id);
		FuncionarioClaveEntidadDTO respuesta = new FuncionarioClaveEntidadDTO();
		respuesta.setIdFuncionarioEntidad(id);
		when(funcionarioClaveEntidadServiceRepo.obtener(id)).thenReturn(funcionarioClaveEntidad);
		when(funcionarioClaveEntidadMapper.funcionarioClaveEntidadToDTO(funcionarioClaveEntidad)).thenReturn(respuesta);
		FuncionarioClaveEntidadDTO funcionarioClaveEntidadDTO = sessionConsultaAFS.consultarFuncionarioClaveEntidad(id);
		assertThat(funcionarioClaveEntidadDTO.getIdFuncionarioEntidad()).isEqualTo(respuesta.getIdFuncionarioEntidad());
	}

	@Test
	public void testConsultarConfiguracionNotificacionPorFiltro() throws SpddExceptions {
		Integer pagina = 5;
		Integer tamanioPagina = 10;
		ConfiguracionNotificacionDTO configuracionNotificacionDTO = new ConfiguracionNotificacionDTO();
		configuracionNotificacionDTO.setPagina(pagina);
		configuracionNotificacionDTO.setTamanioPagina(tamanioPagina);
		Pageable pageRequest = PageRequest.of(configuracionNotificacionDTO.getPagina(),
				configuracionNotificacionDTO.getTamanioPagina());
		FiltroDTO filtro = new FiltroDTO();
		List<ConfiguracionNotificacion> lista = new ArrayList<>();
		filtro.setResultadoConsulta(lista);
		filtro.setContador(200L);
		when(configuracionNotificacionServiceRepo.filtrarPorCampo(configuracionNotificacionDTO, pageRequest.getOffset(),
				pageRequest.getPageSize())).thenReturn(filtro);
		assertNotNull(sessionConsultaAFS.consultarConfiguracionNotificacionPorFiltro(configuracionNotificacionDTO));
	}

	@Test
	public void testInformacionPresupuestalPorId() throws SpddExceptions {
		Long id = 1L;
		InformacionPresupuestal informacionPresupuestal = new InformacionPresupuestal();
		informacionPresupuestal.setIdInfoPresupuestal(id);
		InformacionPresupuestalDTO respuesta = new InformacionPresupuestalDTO();
		respuesta.setIdInfoPresupuestal(id);
		when(informacionPresupuestalServiceRepo.obtener(id)).thenReturn(informacionPresupuestal);
		when(informacionPresupuestalMapper.informacionPresupuestalEntityToDTO(informacionPresupuestal))
				.thenReturn(respuesta);
		InformacionPresupuestalDTO informacionPresupuestalDTO = sessionConsultaAFS
				.consultarInformacionPresupuestalPorId(id);
		assertThat(informacionPresupuestalDTO.getIdInfoPresupuestal()).isEqualTo(respuesta.getIdInfoPresupuestal());
	}

	@Test
	public void testConsultarInformacionPresupuestalPorFiltro() throws SpddExceptions {
		Integer pagina = 5;
		Integer tamanioPagina = 10;
		InformacionPresupuestalDTO informacionPresupuestalDTO = new InformacionPresupuestalDTO();
		informacionPresupuestalDTO.setPagina(pagina);
		informacionPresupuestalDTO.setTamanioPagina(tamanioPagina);
		Pageable pageRequest = PageRequest.of(informacionPresupuestalDTO.getPagina(),
				informacionPresupuestalDTO.getTamanioPagina());
		FiltroDTO filtro = new FiltroDTO();
		List<InformacionPresupuestal> lista = new ArrayList<>();
		filtro.setResultadoConsulta(lista);
		filtro.setContador(200L);
		when(informacionPresupuestalServiceRepo.filtrarPorCampo(informacionPresupuestalDTO, pageRequest.getOffset(),
				pageRequest.getPageSize())).thenReturn(filtro);
		assertNotNull(sessionConsultaAFS.consultarInformacionPresupuestalPorFiltro(informacionPresupuestalDTO));
	}

	@Test
	public void testConsultarLineaDeInversion() {
		Long id = 1L;
		LineaDeInversion lineaDeInversion = new LineaDeInversion();
		lineaDeInversion.setIdLineaInversion(id);
		LineaDeInversionDTO respuesta = new LineaDeInversionDTO();
		respuesta.setIdLineaInversion(id);
		when(lineaDeInversionServiceRepo.obtener(id)).thenReturn(lineaDeInversion);
		when(lineaDeInversionMapper.lineaDeInversionEntityToDTO(lineaDeInversion)).thenReturn(respuesta);
		LineaDeInversionDTO lineaDeInversionDTO = sessionConsultaAFS.consultarLineaDeInversion(id);
		assertThat(lineaDeInversionDTO.getIdLineaInversion()).isEqualTo(respuesta.getIdLineaInversion());
	}

	@Test
	public void testConsultarLineaDeInversionPorFiltro() throws SpddExceptions {
		Integer pagina = 5;
		Integer tamanioPagina = 10;
		LineaDeInversionDTO lineaInversionDTO = new LineaDeInversionDTO();
		lineaInversionDTO.setPagina(pagina);
		lineaInversionDTO.setTamanioPagina(tamanioPagina);
		Pageable pageRequest = PageRequest.of(lineaInversionDTO.getPagina(), lineaInversionDTO.getTamanioPagina());
		FiltroDTO filtro = new FiltroDTO();
		List<LineaDeInversion> lista = new ArrayList<>();
		filtro.setResultadoConsulta(lista);
		filtro.setContador(200L);
		when(lineaDeInversionServiceRepo.filtrarPorCampo(lineaInversionDTO, pageRequest.getOffset(),
				pageRequest.getPageSize())).thenReturn(filtro);
		assertNotNull(sessionConsultaAFS.consultarLineaDeInversionPorFiltro(lineaInversionDTO));
	}

	@Test
	public void testConsultarListaCompuestaPorId() {
		Long id = 1L;
		ListaCompuesta listaCompuesta = new ListaCompuesta();
		listaCompuesta.setIdListaCompuesta(id);
		ListaCompuestaDTO respuesta = new ListaCompuestaDTO();
		respuesta.setIdListaCompuesta(id);
		when(listaCompuestaServiceRepo.obtener(id)).thenReturn(listaCompuesta);
		when(listaCompuestaMapper.listaCompuestaEntityToDTO(listaCompuesta)).thenReturn(respuesta);
		ListaCompuestaDTO listaCompuestaDTO = sessionConsultaAFS.consultarListaCompuestaPorId(id);
		assertThat(listaCompuestaDTO.getIdListaCompuesta()).isEqualTo(respuesta.getIdListaCompuesta());
	}

	@Test
	public void testConsultarListaCompuestaPorFiltro() throws SpddExceptions {
		Integer pagina = 5;
		Integer tamanioPagina = 10;
		ListaCompuestaDTO listaCompuestaDTO = new ListaCompuestaDTO();
		listaCompuestaDTO.setPagina(pagina);
		listaCompuestaDTO.setTamanioPagina(tamanioPagina);
		Pageable pageRequest = PageRequest.of(listaCompuestaDTO.getPagina(), listaCompuestaDTO.getTamanioPagina());
		FiltroDTO filtro = new FiltroDTO();
		List<ListaCompuesta> lista = new ArrayList<>();
		filtro.setResultadoConsulta(lista);
		filtro.setContador(200L);
		when(listaCompuestaServiceRepo.filtrarPorCampo(listaCompuestaDTO, pageRequest.getOffset(),
				pageRequest.getPageSize())).thenReturn(filtro);
		assertNotNull(sessionConsultaAFS.consultarListaCompuestaPorFiltro(listaCompuestaDTO));
	}

	@Test
	public void testConsultarListaSimplePorID() {
		Long id = 1L;
		ListaSimple listaSimple = new ListaSimple();
		listaSimple.setIdListaSimple(id);
		ListaSimpleDTO respuesta = new ListaSimpleDTO();
		respuesta.setIdListaSimple(id);
		when(listaSimpleServiceRepo.obtener(id)).thenReturn(listaSimple);
		when(listaSimpleMapper.listaSimpleEntityToDTO(listaSimple)).thenReturn(respuesta);
		ListaSimpleDTO listaSimpleDTO = sessionConsultaAFS.consultarListaSimplePorID(id);
		assertThat(listaSimpleDTO.getIdListaSimple()).isEqualTo(respuesta.getIdListaSimple());
	}

	@Test
	public void testConsultarParametroGeneralPorId() {
		String id = "1";
		ParametroGeneral parametroGeneral = new ParametroGeneral();
		parametroGeneral.setCodigoParametro(id);
		ParametroGeneralDTO respuesta = new ParametroGeneralDTO();
		respuesta.setCodigoParametro(id);
		when(parametroGeneralServiceRepo.obtener(id)).thenReturn(parametroGeneral);
		when(parametroGeneralMapper.parametroEntityToDTO(parametroGeneral)).thenReturn(respuesta);
		ParametroGeneralDTO parametroGeneralDTO = sessionConsultaAFS.consultarParametroGeneralPorId(id);
		assertThat(parametroGeneralDTO.getCodigoParametro()).isEqualTo(respuesta.getCodigoParametro());
	}

	@Test
	public void testConsultarParametroGeneralPorFiltro() throws SpddExceptions {
		Integer pagina = 5;
		Integer tamanioPagina = 10;
		ParametroGeneralDTO parametroGeneralDTO = new ParametroGeneralDTO();
		parametroGeneralDTO.setPagina(pagina);
		parametroGeneralDTO.setTamanioPagina(tamanioPagina);
		Pageable pageRequest = PageRequest.of(parametroGeneralDTO.getPagina(), parametroGeneralDTO.getTamanioPagina());
		FiltroDTO filtro = new FiltroDTO();
		List<ParametroGeneral> lista = new ArrayList<>();
		filtro.setResultadoConsulta(lista);
		filtro.setContador(200L);
		when(parametroGeneralServiceRepo.filtrarPorCampo(parametroGeneralDTO, pageRequest.getOffset(),
				pageRequest.getPageSize())).thenReturn(filtro);
		assertNotNull(sessionConsultaAFS.consultarParametroGeneralPorFiltro(parametroGeneralDTO));
	}

	@Test
	public void testConsultarPotProyectoInstrumentoPorId() {
		Long id = 1L;
		PotProyectoInstrumento potProyectoInstrumento = new PotProyectoInstrumento();
		potProyectoInstrumento.setIdProyectoInstrumento(id);
		PotProyectoInstrumentoDTO respuesta = new PotProyectoInstrumentoDTO();
		respuesta.setIdProyectoInstrumento(id);
		when(potProyectoInstrumentoServiceRepo.obtener(id)).thenReturn(potProyectoInstrumento);
		when(potProyectoInstrumentoMapper.potProyectoInstrumentoEntityToDTO(potProyectoInstrumento))
				.thenReturn(respuesta);
		PotProyectoInstrumentoDTO potProyectoInstrumentoDTO = sessionConsultaAFS
				.consultarPotProyectoInstrumentoPorId(id);
		assertThat(potProyectoInstrumentoDTO.getIdProyectoInstrumento())
				.isEqualTo(respuesta.getIdProyectoInstrumento());
	}

	@Test
	public void testConsultarPotProyectoInstrumentoPorFiltro() throws SpddExceptions {
		Integer pagina = 5;
		Integer tamanioPagina = 10;
		PotProyectoInstrumentoDTO potProyectoInstrumentoDTO = new PotProyectoInstrumentoDTO();
		potProyectoInstrumentoDTO.setPagina(pagina);
		potProyectoInstrumentoDTO.setTamanioPagina(tamanioPagina);
		Pageable pageRequest = PageRequest.of(potProyectoInstrumentoDTO.getPagina(),
				potProyectoInstrumentoDTO.getTamanioPagina());
		FiltroDTO filtro = new FiltroDTO();
		List<PotProyectoInstrumento> lista = new ArrayList<>();
		filtro.setResultadoConsulta(lista);
		filtro.setContador(200L);
		when(potProyectoInstrumentoServiceRepo.filtrarPorCampo(potProyectoInstrumentoDTO, pageRequest.getOffset(),
				pageRequest.getPageSize())).thenReturn(filtro);
		assertNotNull(sessionConsultaAFS.consultarPotProyectoInstrumentoPorFiltro(potProyectoInstrumentoDTO));
	}

	@Test
	public void testConsultarPotInstrumento() throws SpddExceptions {
		Long id = 1L;
		PotInstrumento potInstrumento = new PotInstrumento();
		potInstrumento.setIdInstrumentoPot(id);
		List<PotInstrumento> lstPotInstrumento = new ArrayList<>();
		lstPotInstrumento.add(potInstrumento);
		PotInstrumentoDTO respuesta = new PotInstrumentoDTO();
		respuesta.setIdInstrumentoPot(id);
		List<PotInstrumentoDTO> lstPotInstrumentoDTO = new ArrayList<>();
		lstPotInstrumentoDTO.add(respuesta);
		when(potInstrumentoServiceRepo.obtenerTodos()).thenReturn(lstPotInstrumento);
		when(potInstrumentoMapper.potInstrumentoEntitiesToDTO(lstPotInstrumento)).thenReturn(lstPotInstrumentoDTO);
		assertNotNull(sessionConsultaAFS.consultarPotInstrumento());
	}

	@Test
	public void testConsultarPotObra() {
		Long id = 1L;
		PotObra potObra = new PotObra();
		potObra.setCodigo(id);
		List<PotObra> lstPotObra = new ArrayList<>();
		lstPotObra.add(potObra);
		PotObraDTO potObraDTO = new PotObraDTO();
		potObraDTO.setCodigoPotObra(id);
		List<PotObraDTO> lstPotObraDTO = new ArrayList<>();
		lstPotObraDTO.add(potObraDTO);
		when(potObraServiceRepo.obtenerTodos()).thenReturn(lstPotObra);
		when(potObraMapper.potObraEntitiesToDTO(lstPotObra)).thenReturn(lstPotObraDTO);
		assertThat(sessionConsultaAFS.consultarPotObra()).isNotNull();
	}

	@Test
	public void testConsultarPdd() throws SpddExceptions {
		Long id = 1L;
		Pdd pdd = new Pdd();
		pdd.setIdPlanDesarrollo(id);
		List<Pdd> lstPdd = new ArrayList<>();
		lstPdd.add(pdd);
		PddDTO respuesta = new PddDTO();
		respuesta.setIdPlanDesarrollo(id);
		List<PddDTO> lstPddDTO = new ArrayList<>();
		lstPddDTO.add(respuesta);
		when(pddServiceRepo.obtenerTodosPdd()).thenReturn(lstPdd);
		when(pddMapper.pddEntitiesToDTO(lstPdd)).thenReturn(lstPddDTO);
		assertNotNull(sessionConsultaAFS.consultarPdd());

	}

	@Test
	public void testConsultarProyectoInversionPorId() {
		Long id = 1L;
		ProyectoInversion proyectoInversion = new ProyectoInversion();
		proyectoInversion.setIdProyectoInversion(id);
		ProyectoInversionDTO respuesta = new ProyectoInversionDTO();
		respuesta.setIdProyectoInversion(id);
		when(proyectoInversionServiceRepo.obtener(id)).thenReturn(proyectoInversion);
		when(proyectoInversionMapper.proyectoInversionEntityToDTO(proyectoInversion)).thenReturn(respuesta);
		ProyectoInversionDTO proyectoInversionDTO = sessionConsultaAFS.consultarProyectoInversionPorId(id);
		assertThat(proyectoInversionDTO.getIdProyectoInversion()).isEqualTo(respuesta.getIdProyectoInversion());
	}

	@Test
	public void testConsultarProyectosInversionUsuarioPorId() {
		String usuario = "sumset";
		ProyectosInversionUsuario proyectosInversionUsuario = new ProyectosInversionUsuario();
		proyectosInversionUsuario.setCodigoUsuario(usuario);
		List<ProyectosInversionUsuario> lstProyectosInversionUsuarios = new ArrayList<>();
		lstProyectosInversionUsuarios.add(proyectosInversionUsuario);
		ProyectosInversionUsuarioDTO respuesta = new ProyectosInversionUsuarioDTO();
		respuesta.setCodigoUsuario(usuario);
		List<ProyectosInversionUsuarioDTO> lstProyectosInversionUsuarioDTO = new ArrayList<>();
		lstProyectosInversionUsuarioDTO.add(respuesta);
		when(proyectosInversionUsuarioServiceRepo.obtenerPorUsuario(usuario)).thenReturn(lstProyectosInversionUsuarios);
		when(proyectosInversionUsuarioMapper.proyectosInversionUsuarioEntitiesToDTO(lstProyectosInversionUsuarios))
				.thenReturn(lstProyectosInversionUsuarioDTO);
		assertNotNull(sessionConsultaAFS.consultarProyectosInversionUsuarioPorId(usuario));
	}

	@Test
	public void testConsultarTerritorializacionPorId() {
		Long id = 1L;
		Territorializacion territorializacion = new Territorializacion();
		territorializacion.setIdTerritorializacion(id);
		TerritorializacionDTO respuesta = new TerritorializacionDTO();
		respuesta.setIdTerritorializacion(id);
		when(territorializacionServiceRepo.obtener(id)).thenReturn(territorializacion);
		when(territorializacionMapper.territorializacionEntityToDTO(territorializacion)).thenReturn(respuesta);
		TerritorializacionDTO territorializacionDTO = sessionConsultaAFS.consultarTerritorializacionPorId(id);
		assertThat(territorializacionDTO.getIdTerritorializacion()).isEqualTo(respuesta.getIdTerritorializacion());
	}

	@Test
	public void testConsultarTerritorializacionPorFiltro() throws SpddExceptions {
		Integer pagina = 5;
		Integer tamanioPagina = 10;
		TerritorializacionDTO territorializacionDTO = new TerritorializacionDTO();
		territorializacionDTO.setPagina(pagina);
		territorializacionDTO.setTamanioPagina(tamanioPagina);
		Pageable pageRequest = PageRequest.of(territorializacionDTO.getPagina(),
				territorializacionDTO.getTamanioPagina());
		FiltroDTO filtro = new FiltroDTO();
		List<Territorializacion> lista = new ArrayList<>();
		filtro.setResultadoConsulta(lista);
		filtro.setContador(200L);
		when(territorializacionServiceRepo.filtrarPorCampo(territorializacionDTO, pageRequest.getOffset(),
				pageRequest.getPageSize())).thenReturn(filtro);
		assertNotNull(sessionConsultaAFS.consultarTerritorializacionPorFiltro(territorializacionDTO));
	}

	@Test
	public void testConsultarUsuarioEntidadPorId() {
		Long id = 1L;
		UsuarioEntidad usuarioEntidad = new UsuarioEntidad();
		usuarioEntidad.setIdUsuarioEntidad(id);
		UsuarioEntidadDTO respuesta = new UsuarioEntidadDTO();
		respuesta.setIdUsuarioEntidad(id);
		when(usuarioEntidadServiceRepo.obtener(id)).thenReturn(usuarioEntidad);
		when(usuarioEntidadMapper.usuarioEntidadEntityToDTO(usuarioEntidad)).thenReturn(respuesta);
		UsuarioEntidadDTO usuarioEntidadDTO = sessionConsultaAFS.consultarUsuarioEntidadPorId(id);
		assertThat(usuarioEntidadDTO.getIdUsuarioEntidad()).isEqualTo(respuesta.getIdUsuarioEntidad());
	}

	@Test
	public void testConsultarUsuarioEntidadPorFiltro() throws SpddExceptions {
		Integer pagina = 5;
		Integer tamanioPagina = 10;
		UsuarioEntidadDTO usuarioEntidadDTO = new UsuarioEntidadDTO();
		usuarioEntidadDTO.setPagina(pagina);
		usuarioEntidadDTO.setTamanioPagina(tamanioPagina);
		Pageable pageRequest = PageRequest.of(usuarioEntidadDTO.getPagina(), usuarioEntidadDTO.getTamanioPagina());
		FiltroDTO filtro = new FiltroDTO();
		List<UsuarioEntidad> lista = new ArrayList<>();
		filtro.setResultadoConsulta(lista);
		filtro.setContador(200L);
		when(usuarioEntidadServiceRepo.filtrarPorCampo(usuarioEntidadDTO, pageRequest.getOffset(),
				pageRequest.getPageSize())).thenReturn(filtro);
		assertThat(sessionConsultaAFS.consultarUsuarioEntidadPorFiltro(usuarioEntidadDTO));
	}

	@Test
	public void testConsultarArchivoProcesadoTodos() throws SpddExceptions {
		Long id = 1L;
		ArchivoProcesado archivoProcesado = new ArchivoProcesado();
		archivoProcesado.setIdArchivo(id);
		List<ArchivoProcesado> lstArchivoProcesado = new ArrayList<>();
		lstArchivoProcesado.add(archivoProcesado);
		ArchivoProcesadoDTO archivoProcesadoDTO = new ArchivoProcesadoDTO();
		archivoProcesadoDTO.setIdArchivo(id);
		List<ArchivoProcesadoDTO> lstArchivoProcesadoDTO = new ArrayList<>();
		lstArchivoProcesadoDTO.add(archivoProcesadoDTO);
		when(archivoProcesadoServiceRepo.obtenerTodos()).thenReturn(lstArchivoProcesado);
		when(archivoProcesadoMapper.ArchivoProcesadoEntitiesToDTO(lstArchivoProcesado))
				.thenReturn(lstArchivoProcesadoDTO);
		assertNotNull(sessionConsultaAFS.consultarArchivoProcesadoTodos());
	}

	@Test
	public void testConsultarArgumentoListaSimpleTodos() throws SpddExceptions {
		Long id = 1L;
		ArgumentoListaSimple argumentoListaSimple = new ArgumentoListaSimple();
		argumentoListaSimple.setIdArgumento(id);
		List<ArgumentoListaSimple> lstArgumentoListaSimple = new ArrayList<>();
		lstArgumentoListaSimple.add(argumentoListaSimple);
		ArgumentoListaSimpleDTO respuesta = new ArgumentoListaSimpleDTO();
		respuesta.setIdArgumento(id);
		List<ArgumentoListaSimpleDTO> lstArgumentoListaSimpleDTO = new ArrayList<>();
		lstArgumentoListaSimpleDTO.add(respuesta);
		when(argumentoListaSimpleServiceRepo.obtenerTodos()).thenReturn(lstArgumentoListaSimple);
		when(argumentoListaSimpleMapper.argumentoListaSimpleEntitiesToDTO(lstArgumentoListaSimple))
				.thenReturn(lstArgumentoListaSimpleDTO);
		assertNotNull(sessionConsultaAFS.consultarArgumentoListaSimpleTodos());
	}

	@Test
	public void testConsultarArgumentosPorFiltro() throws SpddExceptions {
		Integer pagina = 5;
		Integer tamanioPagina = 10;
		ArgumentoListaSimpleDTO argumentoListaSimpleDTO = new ArgumentoListaSimpleDTO();
		argumentoListaSimpleDTO.setPagina(pagina);
		argumentoListaSimpleDTO.setTamanioPagina(tamanioPagina);
		Pageable pageRequest = PageRequest.of(argumentoListaSimpleDTO.getPagina(),
				argumentoListaSimpleDTO.getTamanioPagina());
		FiltroDTO filtro = new FiltroDTO();
		List<ArgumentoListaSimple> lista = new ArrayList<>();
		filtro.setResultadoConsulta(lista);
		filtro.setContador(200L);
		when(argumentoListaSimpleServiceRepo.filtrarPorCampo(argumentoListaSimpleDTO, pageRequest.getOffset(),
				pageRequest.getPageSize())).thenReturn(filtro);
		assertNotNull(sessionConsultaAFS.consultarArgumentosPorFiltro(argumentoListaSimpleDTO));
	}

	@Test
	public void testConsultarBancoDeProyectosTodos() throws SpddExceptions {
		Long id = 1L;
		BancoDeProyectos bancoDeProyectos = new BancoDeProyectos();
		bancoDeProyectos.setIdBancoProyecto(id);
		List<BancoDeProyectos> lstBancoDeProyectos = new ArrayList<>();
		lstBancoDeProyectos.add(bancoDeProyectos);
		BancoDeProyectoDTO bancoDeProyectoDTO = new BancoDeProyectoDTO();
		bancoDeProyectoDTO.setIdBancoProyecto(id);
		List<BancoDeProyectoDTO> lstBancoDeProyectoDTO = new ArrayList<>();
		lstBancoDeProyectoDTO.add(bancoDeProyectoDTO);
		when(bancoDeProyectoServiceRepo.obtenerTodos()).thenReturn(lstBancoDeProyectos);
		when(bancoDeProyectoMapper.bancoDeProyectoEntitiesToDTO(lstBancoDeProyectos)).thenReturn(lstBancoDeProyectoDTO);
		assertNotNull(sessionConsultaAFS.consultarBancoDeProyectosTodos());

	}

	@Test
	public void testConsultarBuzonNotificacionesTodos() throws SpddExceptions {
		Long id = 1L;
		BuzonNotificaciones buzonNotificaciones = new BuzonNotificaciones();
		buzonNotificaciones.setIdNotificacion(id);
		List<BuzonNotificaciones> lstBuzonNotificaciones = new ArrayList<>();
		lstBuzonNotificaciones.add(buzonNotificaciones);
		BuzonNotificacionesDTO buzonNotificacionesDTO = new BuzonNotificacionesDTO();
		buzonNotificacionesDTO.setIdNotificacion(id);
		List<BuzonNotificacionesDTO> lstBuzonNotificacionesDTO = new ArrayList<>();
		lstBuzonNotificacionesDTO.add(buzonNotificacionesDTO);
		when(buzonNotificacioneServiceRepo.obtenerTodos()).thenReturn(lstBuzonNotificaciones);
		when(buzonNotificacionesMapper.buzonNotificacionesEntitiesToDTO(lstBuzonNotificaciones))
				.thenReturn(lstBuzonNotificacionesDTO);
		assertNotNull(sessionConsultaAFS.consultarBuzonNotificacionesTodos());
	}

	@Test
	public void testConsultarComponenteGastoTodos() throws SpddExceptions {
		Long id = 1L;
		ComponenteGasto componenteGasto = new ComponenteGasto();
		componenteGasto.setCodigoComponente(id);
		List<ComponenteGasto> lstComponenteGasto = new ArrayList<>();
		lstComponenteGasto.add(componenteGasto);
		ComponenteGastoDTO componenteGastoDTO = new ComponenteGastoDTO();
		componenteGastoDTO.setCodigoComponente(id);
		List<ComponenteGastoDTO> lstComponenteGastoDTO = new ArrayList<>();
		lstComponenteGastoDTO.add(componenteGastoDTO);
		when(componenteGastoServiceRepo.obtener(id)).thenReturn(componenteGasto);
		when(componenteGastoMapper.componenteGastoEntitiesToDTO(lstComponenteGasto)).thenReturn(lstComponenteGastoDTO);
		assertNotNull(sessionConsultaAFS.consultarComponenteGastoTodos());
	}

	@Test
	public void testConsultarPorFiltroComponenteGasto() throws SpddExceptions {
		Integer pagina = 5;
		Integer tamanioPagina = 10;
		ComponenteGastoDTO componenteGastoDTO = new ComponenteGastoDTO();
		componenteGastoDTO.setPagina(pagina);
		componenteGastoDTO.setTamanioPagina(tamanioPagina);
		Pageable pageRequest = PageRequest.of(componenteGastoDTO.getPagina(), componenteGastoDTO.getTamanioPagina());
		FiltroDTO filtro = new FiltroDTO();
		List<ComponenteGasto> lista = new ArrayList<>();
		filtro.setResultadoConsulta(lista);
		filtro.setContador(200L);
		when(componenteGastoServiceRepo.filtrarPorCampo(componenteGastoDTO, pageRequest.getOffset(),
				pageRequest.getPageSize())).thenReturn(filtro);
		assertNotNull(sessionConsultaAFS.consultarPorFiltroComponenteGasto(componenteGastoDTO));
	}

	@Test
	public void testConsultarComponenteGestionUsuarioTodos() throws SpddExceptions {
		Long id = 1L;
		ComponenteGestionUsuario componenteGestionUsuario = new ComponenteGestionUsuario();
		componenteGestionUsuario.setIdGestionUsuario(id);
		List<ComponenteGestionUsuario> lstComponenteGestionUsuario = new ArrayList<>();
		lstComponenteGestionUsuario.add(componenteGestionUsuario);
		ComponenteGestionUsuarioDTO componenteGestionUsuarioDTO = new ComponenteGestionUsuarioDTO();
		componenteGestionUsuarioDTO.setIdComponenteGestion(id);
		List<ComponenteGestionUsuarioDTO> lstComponenteGestionUsuarioDTO = new ArrayList<>();
		lstComponenteGestionUsuarioDTO.add(componenteGestionUsuarioDTO);
		when(componenteGestionUsuarioServiceRepo.obtenerTodos()).thenReturn(lstComponenteGestionUsuario);
		when(componenteGestionUsuarioMapper.componenteGestionUsuarioEntitiesToDTO(lstComponenteGestionUsuario))
				.thenReturn(lstComponenteGestionUsuarioDTO);
		assertNotNull(sessionConsultaAFS.consultarComponenteGestionUsuarioTodos());
	}

	@Test
	public void testConsultarConsecutivoTodos() throws SpddExceptions {
		Long id = 1L;
		Consecutivo consecutivo = new Consecutivo();
		consecutivo.setIdConsecutivo(id);
		List<Consecutivo> lstConsecutivo = new ArrayList<>();
		lstConsecutivo.add(consecutivo);
		ConsecutivoDTO consecutivoDTO = new ConsecutivoDTO();
		consecutivoDTO.setIdConsecutivo(id);
		List<ConsecutivoDTO> lstConsecutivoDTO = new ArrayList<>();
		lstConsecutivoDTO.add(consecutivoDTO);
		when(consecutivoServiceRepo.obtenerTodos()).thenReturn(lstConsecutivo);
		when(consecutivoMapper.consecutivoEntitiesToDTO(lstConsecutivo)).thenReturn(lstConsecutivoDTO);
		assertNotNull(sessionConsultaAFS.consultarConsecutivoTodos());
	}

	@Test
	public void testConsultarEntidadTodos() throws SpddExceptions {
		String id = "1";
		Entidad entidad = new Entidad();
		entidad.setCodigoEntidad(id);
		List<Entidad> lstEntidad = new ArrayList<>();
		lstEntidad.add(entidad);
		EntidadDTO entidadDTO = new EntidadDTO();
		entidadDTO.setCodigoEntidad(id);
		List<EntidadDTO> lstEntidadDTO = new ArrayList<>();
		lstEntidadDTO.add(entidadDTO);
		when(entidadServiceRepo.obtenerTodos()).thenReturn(lstEntidad);
		when(entidadMapper.entidadEntitiesToDTO(lstEntidad)).thenReturn(lstEntidadDTO);
		assertNotNull(sessionConsultaAFS.consultarEntidadTodos());
	}

	@Test
	public void testConsultarEquipamientoTodos() throws SpddExceptions {
		Long id = 1L;
		Equipamento equipamento = new Equipamento();
		equipamento.setIdEquipamento(id);
		List<Equipamento> lstEquipamiento = new ArrayList<>();
		lstEquipamiento.add(equipamento);
		EquipamientoDTO equipamientoDTO = new EquipamientoDTO();
		equipamientoDTO.setIdEquipamento(id);
		List<EquipamientoDTO> lstEquipamientoDTO = new ArrayList<>();
		lstEquipamientoDTO.add(equipamientoDTO);
		when(equipamientoServiceRepo.obtenerTodos()).thenReturn(lstEquipamiento);
		when(equipamientoMapper.equipamientoEntitiesToDTO(lstEquipamiento)).thenReturn(lstEquipamientoDTO);
		assertNotNull(sessionConsultaAFS.consultarEquipamientoTodos());
	}

	@Test
	public void testConsultarEquipamientoPorFiltro() throws SpddExceptions {
		Integer pagina = 5;
		Integer tamanioPagina = 10;
		EquipamientoDTO equipamientoDTO = new EquipamientoDTO();
		equipamientoDTO.setPagina(pagina);
		equipamientoDTO.setTamanioPagina(tamanioPagina);
		Pageable pageRequest = PageRequest.of(equipamientoDTO.getPagina(), equipamientoDTO.getTamanioPagina());
		FiltroDTO filtro = new FiltroDTO();
		List<Equipamento> lista = new ArrayList<>();
		filtro.setResultadoConsulta(lista);
		filtro.setContador(200L);
		when(equipamientoServiceRepo.filtrarPorCampo(equipamientoDTO, pageRequest.getOffset(),
				pageRequest.getPageSize())).thenReturn(filtro);
		assertNotNull(sessionConsultaAFS.consultarEquipamientoPorFiltro(equipamientoDTO));
	}

	@Test
	public void testConsultarEstadoServicioTodos() throws SpddExceptions {
		Long id = 1L;
		EstadoServicio estadoServicio = new EstadoServicio();
		estadoServicio.setIdEstadoServicio(id);
		List<EstadoServicio> lstEstadoServicio = new ArrayList<>();
		lstEstadoServicio.add(estadoServicio);
		EstadoServicioDTO estadoServicioDTO = new EstadoServicioDTO();
		estadoServicioDTO.setIdEstadoServicio(id);
		List<EstadoServicioDTO> lstEstadoServicioDTO = new ArrayList<>();
		lstEstadoServicioDTO.add(estadoServicioDTO);
		when(estadoServicioServiceRepo.obtenerTodos()).thenReturn(lstEstadoServicio);
		when(estadoServicioMapper.estadoServicioEntitiesToDTO(lstEstadoServicio)).thenReturn(lstEstadoServicioDTO);
		assertNotNull(sessionConsultaAFS.consultarEstadoServicioTodos());
	}

	@Test
	public void testCconsultarEstadoServicioPorFiltro() throws SpddExceptions {
		Integer pagina = 5;
		Integer tamanioPagina = 10;
		EstadoServicioDTO estadoServicioDTO = new EstadoServicioDTO();
		estadoServicioDTO.setPagina(pagina);
		estadoServicioDTO.setTamanioPagina(tamanioPagina);
		Pageable pageRequest = PageRequest.of(estadoServicioDTO.getPagina(), estadoServicioDTO.getTamanioPagina());
		FiltroDTO filtro = new FiltroDTO();
		List<EstadoServicio> lista = new ArrayList<>();
		filtro.setResultadoConsulta(lista);
		filtro.setContador(200L);
		when(estadoServicioServiceRepo.filtrarPorCampo(estadoServicioDTO, pageRequest.getOffset(),
				pageRequest.getPageSize())).thenReturn(filtro);
		assertNotNull(sessionConsultaAFS.consultarEstadoServicioPorFiltro(estadoServicioDTO));
	}

	@Test
	public void testConsultarEstructuraMetaTodos() throws SpddExceptions {
		Long id = 1L;
		EstructuraMeta estructuraMeta = new EstructuraMeta();
		estructuraMeta.setIdEstructuraMetas(id);
		List<EstructuraMeta> lstEstructuraMeta = new ArrayList<>();
		lstEstructuraMeta.add(estructuraMeta);
		EstructuraMetaDTO estructuraMetaDTO = new EstructuraMetaDTO();
		estructuraMetaDTO.setIdEstructuraMetas(id);
		List<EstructuraMetaDTO> lstEstructuraMetaDTO = new ArrayList<>();
		lstEstructuraMetaDTO.add(estructuraMetaDTO);
		when(estructuraMetaServiceRepo.obtenerTodos()).thenReturn(lstEstructuraMeta);
		when(estructuraMetaMapper.estructuraMetaEntitiesToDTO(lstEstructuraMeta)).thenReturn(lstEstructuraMetaDTO);
		assertNotNull(sessionConsultaAFS.consultarEstructuraMetaTodos());
	}

	@Test
	public void testConsultarEstructuraMetaPorFiltro() throws SpddExceptions {
		Integer pagina = 5;
		Integer tamanioPagina = 10;
		EstructuraMetaDTO estructuraMetaDTO = new EstructuraMetaDTO();
		estructuraMetaDTO.setPagina(pagina);
		estructuraMetaDTO.setTamanioPagina(tamanioPagina);
		Pageable pageRequest = PageRequest.of(estructuraMetaDTO.getPagina(), estructuraMetaDTO.getTamanioPagina());
		FiltroDTO filtro = new FiltroDTO();
		List<EstructuraMeta> lista = new ArrayList<>();
		filtro.setResultadoConsulta(lista);
		filtro.setContador(200L);
		when(estructuraMetaServiceRepo.filtrarPorCampo(estructuraMetaDTO, pageRequest.getOffset(),
				pageRequest.getPageSize())).thenReturn(filtro);
		assertNotNull(sessionConsultaAFS.consultarEstructuraMetaPorFiltro(estructuraMetaDTO));
	}

	@Test
	public void testConsultarFuncionarioClaveEntidadTodos() throws SpddExceptions {
		Long id = 1L;
		FuncionarioClaveEntidad funcionarioClaveEntidad = new FuncionarioClaveEntidad();
		funcionarioClaveEntidad.setIdFuncionarioEntidad(id);
		List<FuncionarioClaveEntidad> lstFuncionarioClaveEntidad = new ArrayList<>();
		lstFuncionarioClaveEntidad.add(funcionarioClaveEntidad);
		FuncionarioClaveEntidadDTO funcionarioClaveEntidadDTO = new FuncionarioClaveEntidadDTO();
		funcionarioClaveEntidadDTO.setIdFuncionarioEntidad(id);
		List<FuncionarioClaveEntidadDTO> lstFuncionarioClaveEntidadDTO = new ArrayList<>();
		lstFuncionarioClaveEntidadDTO.add(funcionarioClaveEntidadDTO);
		when(funcionarioClaveEntidadServiceRepo.obtenerTodos()).thenReturn(lstFuncionarioClaveEntidad);
		when(funcionarioClaveEntidadMapper.funcionarioClaveEntidadEntitiesToDTO(lstFuncionarioClaveEntidad))
				.thenReturn(lstFuncionarioClaveEntidadDTO);
		assertNotNull(sessionConsultaAFS.consultarFuncionarioClaveEntidadTodos());
	}

	@Test
	public void testConsultarFuncionarioClaveEntidadPorFiltro() throws SpddExceptions {
		Integer pagina = 5;
		Integer tamanioPagina = 10;
		FuncionarioClaveEntidadDTO funcionarioClaveEntidadDTO = new FuncionarioClaveEntidadDTO();
		funcionarioClaveEntidadDTO.setPagina(pagina);
		funcionarioClaveEntidadDTO.setTamanioPagina(tamanioPagina);
		Pageable pageRequest = PageRequest.of(funcionarioClaveEntidadDTO.getPagina(),
				funcionarioClaveEntidadDTO.getTamanioPagina());
		FiltroDTO filtro = new FiltroDTO();
		List<FuncionarioClaveEntidad> lista = new ArrayList<>();
		filtro.setResultadoConsulta(lista);
		filtro.setContador(200L);
		when(funcionarioClaveEntidadServiceRepo.filtrarPorCampo(funcionarioClaveEntidadDTO, pageRequest.getOffset(),
				pageRequest.getPageSize())).thenReturn(filtro);
		assertNotNull(sessionConsultaAFS.consultarFuncionarioClaveEntidadPorFiltro(funcionarioClaveEntidadDTO));
	}

	@Test
	public void testConsultarInformacionPresupuestalTodos() throws SpddExceptions {
		Long id = 1L;
		InformacionPresupuestal informacionPresupuestal = new InformacionPresupuestal();
		informacionPresupuestal.setIdInfoPresupuestal(id);
		List<InformacionPresupuestal> lstInformacionPresupuestal = new ArrayList<>();
		lstInformacionPresupuestal.add(informacionPresupuestal);
		InformacionPresupuestalDTO informacionPresupuestalDTO = new InformacionPresupuestalDTO();
		informacionPresupuestalDTO.setIdArchivo(id);
		List<InformacionPresupuestalDTO> lstInformacionPresupuestalDTO = new ArrayList<>();
		lstInformacionPresupuestalDTO.add(informacionPresupuestalDTO);
		when(informacionPresupuestalServiceRepo.obtenerTodos()).thenReturn(lstInformacionPresupuestal);
		when(informacionPresupuestalMapper.informacionPresupuestalEntitiesToDTO(lstInformacionPresupuestal))
				.thenReturn(lstInformacionPresupuestalDTO);
		assertNotNull(sessionConsultaAFS.consultarInformacionPresupuestalTodos());
	}

	@Test
	public void testConsultarLineaDeInversionTodos() throws SpddExceptions {
		Long id = 1L;
		LineaDeInversion lineaDeInversion = new LineaDeInversion();
		lineaDeInversion.setIdLineaInversion(id);
		List<LineaDeInversion> lstLineaDeInversion = new ArrayList<>();
		lstLineaDeInversion.add(lineaDeInversion);
		LineaDeInversionDTO lineaDeInversionDTO = new LineaDeInversionDTO();
		lineaDeInversionDTO.setIdLineaInversion(id);
		List<LineaDeInversionDTO> lstLineaDeInversionDTO = new ArrayList<>();
		lstLineaDeInversionDTO.add(lineaDeInversionDTO);
		when(lineaDeInversionServiceRepo.obtenerTodos()).thenReturn(lstLineaDeInversion);
		when(lineaDeInversionMapper.lineaDeInversionEntitiesTODTO(lstLineaDeInversion))
				.thenReturn(lstLineaDeInversionDTO);
		assertNotNull(sessionConsultaAFS.consultarLineaDeInversionTodos());
	}

	@Test
	public void testConsultarListaCompuestaTodos() throws SpddExceptions {
		Long id = 1L;
		ListaCompuesta listaCompuesta = new ListaCompuesta();
		listaCompuesta.setIdListaCompuesta(id);
		List<ListaCompuesta> lstListaCompuesta = new ArrayList<>();
		lstListaCompuesta.add(listaCompuesta);
		ListaCompuestaDTO listaCompuestaDTO = new ListaCompuestaDTO();
		listaCompuestaDTO.setIdListaCompuesta(id);
		List<ListaCompuestaDTO> lstCompuestaDTO = new ArrayList<>();
		lstCompuestaDTO.add(listaCompuestaDTO);
		when(listaCompuestaServiceRepo.obtenerTodos()).thenReturn(lstListaCompuesta);
		when(listaCompuestaMapper.listaCompuestaEntitiesToDTO(lstListaCompuesta)).thenReturn(lstCompuestaDTO);
		assertNotNull(sessionConsultaAFS.consultarListaCompuestaTodos());
	}

	@Test
	public void testConsultarListaSimpleTodos() throws SpddExceptions {
		Long id = 1L;
		ListaSimple listaSimple = new ListaSimple();
		listaSimple.setIdListaSimple(id);
		List<ListaSimple> lstListaSimple = new ArrayList<>();
		lstListaSimple.add(listaSimple);
		ListaSimpleDTO listaSimpleDTO = new ListaSimpleDTO();
		listaSimpleDTO.setIdListaSimple(id);
		List<ListaSimpleDTO> lstListaSimpleDTO = new ArrayList<>();
		lstListaSimpleDTO.add(listaSimpleDTO);
		when(listaSimpleServiceRepo.obtenerTodos()).thenReturn(lstListaSimple);
		when(listaSimpleMapper.listaSimpleEntitiesToDTO(lstListaSimple)).thenReturn(lstListaSimpleDTO);
		assertNotNull(sessionConsultaAFS.consultarListaSimpleTodos());
	}

	@Test
	public void testConsultarListaSimplePorFiltro() throws SpddExceptions {
		Integer pagina = 5;
		Integer tamanioPagina = 10;
		ListaSimpleDTO listaSimpleDTO = new ListaSimpleDTO();
		listaSimpleDTO.setPagina(pagina);
		listaSimpleDTO.setTamanioPagina(tamanioPagina);
		Pageable pageRequest = PageRequest.of(listaSimpleDTO.getPagina(), listaSimpleDTO.getTamanioPagina());
		FiltroDTO filtro = new FiltroDTO();
		List<ListaSimple> lista = new ArrayList<>();
		filtro.setResultadoConsulta(lista);
		filtro.setContador(200L);
		when(listaSimpleServiceRepo.filtrarPorCampo(listaSimpleDTO, pageRequest.getOffset(), pageRequest.getPageSize()))
				.thenReturn(filtro);
		assertNotNull(sessionConsultaAFS.consultarListaSimplePorFiltro(listaSimpleDTO));
	}

	@Test
	public void testConsultarParametroGeneralTodos() throws SpddExceptions {
		String id = "1";
		ParametroGeneral parametroGeneral = new ParametroGeneral();
		parametroGeneral.setCodigoParametro(id);
		List<ParametroGeneral> lstParametroGeneral = new ArrayList<>();
		lstParametroGeneral.add(parametroGeneral);
		ParametroGeneralDTO parametroGeneralDTO = new ParametroGeneralDTO();
		parametroGeneralDTO.setCodigoParametro(id);
		List<ParametroGeneralDTO> lstParametroGeneralDTO = new ArrayList<>();
		lstParametroGeneralDTO.add(parametroGeneralDTO);
		when(parametroGeneralServiceRepo.obtenerTodos()).thenReturn(lstParametroGeneral);
		when(parametroGeneralMapper.parametroGeneralEntitiesToDTO(lstParametroGeneral))
				.thenReturn(lstParametroGeneralDTO);
		assertNotNull(sessionConsultaAFS.consultarParametroGeneralTodos());
	}

	@Test
	public void testConsultarPotProyectoInstrumentoTodos() throws SpddExceptions {
		Long id = 1L;
		PotProyectoInstrumento potProyectoInstrumento = new PotProyectoInstrumento();
		potProyectoInstrumento.setIdProyectoInstrumento(id);
		List<PotProyectoInstrumento> lstPotProyectoInstrumento = new ArrayList<>();
		lstPotProyectoInstrumento.add(potProyectoInstrumento);
		PotProyectoInstrumentoDTO potProyectoInstrumentoDTO = new PotProyectoInstrumentoDTO();
		potProyectoInstrumentoDTO.setIdPotProyecto(id);
		List<PotProyectoInstrumentoDTO> lstPotProyectoInstrumentoDTO = new ArrayList<>();
		lstPotProyectoInstrumentoDTO.add(potProyectoInstrumentoDTO);
		when(potProyectoInstrumentoServiceRepo.obtenerTodos()).thenReturn(lstPotProyectoInstrumento);
		when(potProyectoInstrumentoMapper.potProyectoInstrumentoEntitiesToDTO(lstPotProyectoInstrumento))
				.thenReturn(lstPotProyectoInstrumentoDTO);
		assertNotNull(sessionConsultaAFS.consultarPotProyectoInstrumentoTodos());
	}

	@Test
	public void testConsultarProyectoInversionTodos() throws SpddExceptions {
		Long id = 1L;
		ProyectoInversion proyectoInversion = new ProyectoInversion();
		proyectoInversion.setIdProyectoInversion(id);
		List<ProyectoInversion> lstProyectoInversion = new ArrayList<>();
		lstProyectoInversion.add(proyectoInversion);
		ProyectoInversionDTO proyectoInversionDTO = new ProyectoInversionDTO();
		proyectoInversionDTO.setIdProyectoInversion(id);
		List<ProyectoInversionDTO> lstProyectoInversionDTO = new ArrayList<>();
		lstProyectoInversionDTO.add(proyectoInversionDTO);
		when(proyectoInversionServiceRepo.obtenerTodos()).thenReturn(lstProyectoInversion);
		when(proyectoInversionMapper.proyectoInversionEntitiesToDTO(lstProyectoInversion))
				.thenReturn(lstProyectoInversionDTO);
		assertNotNull(sessionConsultaAFS.consultarProyectoInversionTodos());
	}

	@Test
	public void testConsultarProyectoInversionUsuarioTodos() throws SpddExceptions {
		Long id = 1L;
		ProyectosInversionUsuario proyectosInversionUsuario = new ProyectosInversionUsuario();
		proyectosInversionUsuario.setIdProyectoUsuario(id);
		List<ProyectosInversionUsuario> lstProyectosInversionUsuario = new ArrayList<>();
		lstProyectosInversionUsuario.add(proyectosInversionUsuario);
		ProyectosInversionUsuarioDTO proyectosInversionUsuarioDTO = new ProyectosInversionUsuarioDTO();
		proyectosInversionUsuarioDTO.setIdProyectoInversion(id);
		List<ProyectosInversionUsuarioDTO> lstProyectosInversionUsuarioDTO = new ArrayList<>();
		lstProyectosInversionUsuarioDTO.add(proyectosInversionUsuarioDTO);
		when(proyectosInversionUsuarioServiceRepo.obtenerTodos()).thenReturn(lstProyectosInversionUsuario);
		when(proyectosInversionUsuarioMapper.proyectosInversionUsuarioEntitiesToDTO(lstProyectosInversionUsuario))
				.thenReturn(lstProyectosInversionUsuarioDTO);
		assertNotNull(sessionConsultaAFS.consultarProyectoInversionUsuarioTodos());
	}

	@Test
	public void testConsultarTerritorializacionTodos() throws SpddExceptions {
		Long id = 1L;
		Territorializacion territorializacion = new Territorializacion();
		territorializacion.setIdTerritorializacion(id);
		List<Territorializacion> lstTerritorializacion = new ArrayList<>();
		lstTerritorializacion.add(territorializacion);
		TerritorializacionDTO territorializacionDTO = new TerritorializacionDTO();
		territorializacionDTO.setIdTerritorializacion(id);
		List<TerritorializacionDTO> lstTerritorializacionDTO = new ArrayList<>();
		lstTerritorializacionDTO.add(territorializacionDTO);
		when(territorializacionServiceRepo.obtenerTodos()).thenReturn(lstTerritorializacion);
		when(territorializacionMapper.territorializacionEntitiesToDTO(lstTerritorializacion))
				.thenReturn(lstTerritorializacionDTO);
		assertNotNull(sessionConsultaAFS.consultarTerritorializacionTodos());
	}

	@Test
	public void testConsultarUsuarioEntidadTodos() throws SpddExceptions {
		Long id = 1L;
		UsuarioEntidad usuarioEntidad = new UsuarioEntidad();
		usuarioEntidad.setIdUsuarioEntidad(id);
		List<UsuarioEntidad> lstUsuarioEntidad = new ArrayList<>();
		lstUsuarioEntidad.add(usuarioEntidad);
		UsuarioEntidadDTO usuarioEntidadDTO = new UsuarioEntidadDTO();
		usuarioEntidadDTO.setIdUsuarioEntidad(id);
		List<UsuarioEntidadDTO> lstUsuarioEntidadDTO = new ArrayList<>();
		lstUsuarioEntidadDTO.add(usuarioEntidadDTO);
		when(usuarioEntidadServiceRepo.obtenerTodos()).thenReturn(lstUsuarioEntidad);
		when(usuarioEntidadMapper.usuarioEntidadDTOToEntities(lstUsuarioEntidadDTO)).thenReturn(lstUsuarioEntidad);
		assertNotNull(sessionConsultaAFS.consultarUsuarioEntidadTodos());
	}

	@Test
	public void testConsultarFuncionariosUsuariosClavePorFiltro() throws SpddExceptions {
		Integer pagina = 5;
		Integer tamanioPagina = 10;
		FuncionarioClaveEntidadDTO funcionarioClaveEntidadDTO = new FuncionarioClaveEntidadDTO();
		funcionarioClaveEntidadDTO.setPagina(pagina);
		funcionarioClaveEntidadDTO.setTamanioPagina(tamanioPagina);
		Pageable pageRequest = PageRequest.of(funcionarioClaveEntidadDTO.getPagina(),
				funcionarioClaveEntidadDTO.getTamanioPagina());
		FiltroDTO filtro = new FiltroDTO();
		List<FuncionarioClaveEntidad> lista = new ArrayList<>();
		filtro.setResultadoConsulta(lista);
		filtro.setContador(200L);
		when(funcionarioClaveEntidadServiceRepo.filtrarPorCampo(funcionarioClaveEntidadDTO, pageRequest.getOffset(),
				pageRequest.getPageSize())).thenReturn(filtro);
		assertNotNull(sessionConsultaAFS.consultarUsuariosClavePorFiltro(funcionarioClaveEntidadDTO));
	}

	@Test
	public void testNotificacionesPorUsuario() throws SpddExceptions {
		Long notificaciones = 1L;
		Long id = 1L;
		String codigo = "1";
		Integer estado = 0;
		BuzonNotificacionesDTO buzonNotificacionesDTO = new BuzonNotificacionesDTO();
		buzonNotificacionesDTO.setIdNotificacion(id);
		buzonNotificacionesDTO.setCodigoUsuarioDestino(codigo);
		buzonNotificacionesDTO.setEstado(estado);
		when(buzonNotificacioneServiceRepo.notificacionesPorUsuario(buzonNotificacionesDTO.getCodigoUsuarioDestino(),
				buzonNotificacionesDTO.getEstado())).thenReturn(notificaciones);
		assertNotNull(sessionConsultaAFS.notificacionesPorUsuario(buzonNotificacionesDTO));
	}

	@Test
	public void testObtenerArgumentoPorLista() throws SpddExceptions {
		Long id = 1L;
		ListaSimple listaSimple = new ListaSimple();
		listaSimple.setIdListaSimple(id);
		ArgumentoListaSimple argumentoListaSimple = new ArgumentoListaSimple();
		argumentoListaSimple.setIdListaSimple(listaSimple);
		List<ArgumentoListaSimple> lstArgumentoListaSimple = new ArrayList<>();
		lstArgumentoListaSimple.add(argumentoListaSimple);
		ArgumentoListaSimpleDTO argumentoListaSimpleDTO = new ArgumentoListaSimpleDTO();
		argumentoListaSimpleDTO.setIdListaSimple(id);
		List<ArgumentoListaSimpleDTO> lstArgumentoListaSimpleDTO = new ArrayList<>();
		lstArgumentoListaSimpleDTO.add(argumentoListaSimpleDTO);
		when(argumentoListaSimpleServiceRepo.obtenerArgumentoPorLista(id)).thenReturn(lstArgumentoListaSimple);
		when(argumentoListaSimpleMapper.argumentoListaSimpleEntitiesToDTO(lstArgumentoListaSimple))
				.thenReturn(lstArgumentoListaSimpleDTO);
		assertNotNull(sessionConsultaAFS.obtenerArgumentoPorLista(id));
	}

	@Test
	public void testObtenerArgumentoPorNombre() throws SpddExceptions {
		String nombre = "argumento";
		Long id = 1L;
		ListaSimple listaSimple = new ListaSimple();
		listaSimple.setIdListaSimple(id);
		listaSimple.setNombre(nombre);
		ArgumentoListaSimple argumentoListaSimple = new ArgumentoListaSimple();
		argumentoListaSimple.setIdListaSimple(listaSimple);
		List<ArgumentoListaSimple> lstArgumentoListaSimple = new ArrayList<>();
		lstArgumentoListaSimple.add(argumentoListaSimple);
		ArgumentoListaSimpleDTO argumentoListaSimpleDTO = new ArgumentoListaSimpleDTO();
		argumentoListaSimpleDTO.setIdListaSimple(id);
		List<ArgumentoListaSimpleDTO> lstArgumentoListaSimpleDTO = new ArrayList<>();
		lstArgumentoListaSimpleDTO.add(argumentoListaSimpleDTO);
		when(argumentoListaSimpleServiceRepo.obtenerArgumentoPorNombre(nombre)).thenReturn(lstArgumentoListaSimple);
		when(argumentoListaSimpleMapper.argumentoListaSimpleEntitiesToDTO(lstArgumentoListaSimple))
				.thenReturn(lstArgumentoListaSimpleDTO);
		assertNotNull(sessionConsultaAFS.obtenerArgumentoPorNombre(nombre));
	}

	@Test
	public void testObtenerBuzonPorUsuario() throws SpddExceptions {
		String usuario = "sumset";
		Long id = 1L;
		BuzonNotificaciones buzonNotificaciones = new BuzonNotificaciones();
		buzonNotificaciones.setIdNotificacion(id);
		List<BuzonNotificaciones> lstBuzonNotificaciones = new ArrayList<>();
		lstBuzonNotificaciones.add(buzonNotificaciones);
		BuzonNotificacionesDTO buzonNotificacionesDTO = new BuzonNotificacionesDTO();
		buzonNotificacionesDTO.setIdNotificacion(id);
		List<BuzonNotificacionesDTO> lstBuzonNotificacionesDTO = new ArrayList<>();
		lstBuzonNotificacionesDTO.add(buzonNotificacionesDTO);
		when(buzonNotificacioneServiceRepo.obtenerBuzonPorUsuario(usuario)).thenReturn(lstBuzonNotificaciones);
		when(buzonNotificacionesMapper.buzonNotificacionesEntitiesToDTO(lstBuzonNotificaciones))
				.thenReturn(lstBuzonNotificacionesDTO);
		assertNotNull(sessionConsultaAFS.obtenerBuzonPorUsuario(usuario));
	}

	@Test
	public void testObtenerFuncionarioPorEntidad() throws SpddExceptions {
		String codigoEntidad = "0144";
		Long id = 1L;
		Entidad entidad = new Entidad();
		entidad.setCodigoEntidad(codigoEntidad);
		FuncionarioClaveEntidad funcionarioClaveEntidad = new FuncionarioClaveEntidad();
		funcionarioClaveEntidad.setIdFuncionarioEntidad(id);
		funcionarioClaveEntidad.setCodigoEntidad(entidad);
		List<FuncionarioClaveEntidad> lstFuncionarioClaveEntidad = new ArrayList<>();
		lstFuncionarioClaveEntidad.add(funcionarioClaveEntidad);
		FuncionarioClaveEntidadDTO funcionarioClaveEntidadDTO = new FuncionarioClaveEntidadDTO();
		funcionarioClaveEntidadDTO.setIdFuncionarioEntidad(id);
		funcionarioClaveEntidadDTO.setCodigoEntidad(codigoEntidad);
		List<FuncionarioClaveEntidadDTO> lstFuncionarioClaveEntidadDTO = new ArrayList<>();
		lstFuncionarioClaveEntidadDTO.add(funcionarioClaveEntidadDTO);
		when(funcionarioClaveEntidadServiceRepo.obtenerFuncionarioPorEntidad(codigoEntidad))
				.thenReturn(lstFuncionarioClaveEntidad);
		when(funcionarioClaveEntidadMapper.funcionarioClaveEntidadEntitiesToDTO(lstFuncionarioClaveEntidad))
				.thenReturn(lstFuncionarioClaveEntidadDTO);
		assertNotNull(sessionConsultaAFS.obtenerFuncionarioPorEntidad(codigoEntidad));
	}

	@Test
	public void testPddNivelAtributoObtenerLibres() {
		Long id = 1L;
		PddNivelAtributo pddNivelAtributo = new PddNivelAtributo();
		pddNivelAtributo.setIdAtributo(id);
		List<PddNivelAtributo> lstPddNivelAtributo = new ArrayList<>();
		lstPddNivelAtributo.add(pddNivelAtributo);
		PddNivelAtributoDTO pddNivelAtributoDTO = new PddNivelAtributoDTO();
		pddNivelAtributoDTO.setIdAtributo(id);
		List<PddNivelAtributoDTO> lstNivelAtributoDTO = new ArrayList<>();
		lstNivelAtributoDTO.add(pddNivelAtributoDTO);
		when(pddNivelAtributoServiceRepo.pddNivelAtributoObtenerLibres()).thenReturn(lstPddNivelAtributo);
		when(pddNivelAtributoMapper.nivelAtributoEntitiesToDTO(lstPddNivelAtributo)).thenReturn(lstNivelAtributoDTO);
		assertNotNull(sessionConsultaAFS.pddNivelAtributoObtenerLibres());
	}

	@Test
	public void testProyectoInversionObtenerDisponibles() throws SpddExceptions {
		Long id = 1L;
		ProyectoInversion proyectoInversion = new ProyectoInversion();
		proyectoInversion.setIdProyectoInversion(id);
		List<ProyectoInversion> lstProyectoInversion = new ArrayList<>();
		lstProyectoInversion.add(proyectoInversion);
		ProyectoInversionDTO proyectoInversionDTO = new ProyectoInversionDTO();
		proyectoInversionDTO.setIdProyectoInversion(id);
		List<ProyectoInversionDTO> lstProyectoInversionDTO = new ArrayList<>();
		lstProyectoInversionDTO.add(proyectoInversionDTO);
		when(proyectoInversionServiceRepo.proyectoInversionObtenerDisponibles()).thenReturn(lstProyectoInversion);
		when(proyectoInversionMapper.proyectoInversionEntitiesToDTO(lstProyectoInversion))
				.thenReturn(lstProyectoInversionDTO);
		assertNotNull(sessionConsultaAFS.proyectoInversionObtenerDisponibles());
	}

	@Test
	public void testBuscarPorLsBarrioYLsUpzYLsLocalidad() throws SpddExceptions {
		Long idTerritorializacion = 1L;
		Long idBarrio = 2L;
		Long idUpz = 3L;
		Long idLocalidad = 4L;
		ArgumentoListaSimple barrio = new ArgumentoListaSimple();
		barrio.setIdArgumento(idBarrio);
		ArgumentoListaSimple upz = new ArgumentoListaSimple();
		upz.setIdArgumento(idUpz);
		ArgumentoListaSimple localidad = new ArgumentoListaSimple();
		localidad.setIdArgumento(idLocalidad);
		Territorializacion territorializacion = new Territorializacion();
		territorializacion.setIdTerritorializacion(idTerritorializacion);
		territorializacion.setIdLsBarrio(barrio);
		territorializacion.setIdLsUpz(upz);
		territorializacion.setIdLsLocalidad(localidad);
		TerritorializacionDTO territorializacionDTO = new TerritorializacionDTO();
		territorializacionDTO.setIdTerritorializacion(idTerritorializacion);
		territorializacionDTO.setIdLsBarrio(idBarrio);
		territorializacionDTO.setIdLsUpz(idUpz);
		territorializacionDTO.setIdLsLocalidad(idLocalidad);
		when(territorializacionServiceRepo.buscarPorLsBarrioYLsUpzYLsLocalidad(territorializacionDTO.getIdLsBarrio(),
				territorializacionDTO.getIdLsUpz(), territorializacionDTO.getIdLsLocalidad()))
						.thenReturn(territorializacion);
		when(territorializacionMapper.territorializacionEntityToDTO(territorializacion))
				.thenReturn(territorializacionDTO);
		assertNotNull(sessionConsultaAFS.buscarPorLsBarrioYLsUpzYLsLocalidad(territorializacionDTO));
		when(territorializacionServiceRepo.buscarPorLsBarrioYLsUpzYLsLocalidad(territorializacionDTO.getIdLsBarrio(),
				territorializacionDTO.getIdLsUpz(), territorializacionDTO.getIdLsLocalidad())).thenReturn(null);
		when(territorializacionMapper.territorializacionEntityToDTO(territorializacion)).thenReturn(null);
		assertNotNull(sessionConsultaAFS.buscarPorLsBarrioYLsUpzYLsLocalidad(territorializacionDTO));
	}

	@Test
	public void testBuscarPorIdLsPotObraYIdLsPotInstrumento() throws SpddExceptions {
		Long id = 1L;
		Long idPotObra = 2L;
		Long idPotInstrumento = 3L;
		PotObra potObra = new PotObra();
		potObra.setIdObraProyPot(idPotObra);
		PotInstrumento potInstrumento = new PotInstrumento();
		potInstrumento.setIdInstrumentoPot(idPotInstrumento);
		PotProyectoInstrumento potProyectoInstrumento = new PotProyectoInstrumento();
		potProyectoInstrumento.setIdProyectoInstrumento(id);
		potProyectoInstrumento.setIdPotProyecto(potObra);
		potProyectoInstrumento.setIdPotInstrumento(potInstrumento);
		PotProyectoInstrumentoDTO potProyectoInstrumentoDTO = new PotProyectoInstrumentoDTO();
		potProyectoInstrumentoDTO.setIdProyectoInstrumento(id);
		potProyectoInstrumentoDTO.setIdPotInstrumento(idPotInstrumento);
		potProyectoInstrumentoDTO.setIdPotProyecto(idPotObra);
		when(potProyectoInstrumentoServiceRepo.buscarPorIdLsPotObraAndIdLsPotInstrumento(
				potProyectoInstrumentoDTO.getIdPotInstrumento(), potProyectoInstrumentoDTO.getIdPotProyecto()))
						.thenReturn(potProyectoInstrumento);
		when(potProyectoInstrumentoMapper.potProyectoInstrumentoEntityToDTO(potProyectoInstrumento))
				.thenReturn(potProyectoInstrumentoDTO);
		assertNotNull(sessionConsultaAFS.buscarPorIdLsPotObraYIdLsPotInstrumento(potProyectoInstrumentoDTO));
		when(potProyectoInstrumentoServiceRepo.buscarPorIdLsPotObraAndIdLsPotInstrumento(
				potProyectoInstrumentoDTO.getIdPotInstrumento(), potProyectoInstrumentoDTO.getIdPotProyecto()))
						.thenReturn(null);
		when(potProyectoInstrumentoMapper.potProyectoInstrumentoEntityToDTO(potProyectoInstrumento)).thenReturn(null);
		assertNotNull(sessionConsultaAFS.buscarPorIdLsPotObraYIdLsPotInstrumento(potProyectoInstrumentoDTO));
	}

	@Test
	public void testBuscarPorLsVeredaYLsUpr() throws SpddExceptions {
		Long idTerritorializacion = 1L;
		Long idVereda = 2L;
		Long idUpr = 3L;
		Long idLocalidad = 4L;
		ArgumentoListaSimple vereda = new ArgumentoListaSimple();
		vereda.setIdArgumento(idVereda);
		ArgumentoListaSimple upr = new ArgumentoListaSimple();
		upr.setIdArgumento(idUpr);
		ArgumentoListaSimple localidad = new ArgumentoListaSimple();
		localidad.setIdArgumento(idLocalidad);
		Territorializacion territorializacion = new Territorializacion();
		territorializacion.setIdTerritorializacion(idTerritorializacion);
		territorializacion.setIdLsBarrio(vereda);
		territorializacion.setIdLsUpz(upr);
		territorializacion.setIdLsLocalidad(localidad);
		TerritorializacionDTO territorializacionDTO = new TerritorializacionDTO();
		territorializacionDTO.setIdTerritorializacion(idTerritorializacion);
		territorializacionDTO.setIdLsVereda(idVereda);
		territorializacionDTO.setIdLsUpr(idUpr);
		territorializacionDTO.setIdLsLocalidad(idLocalidad);
		when(territorializacionServiceRepo.buscarPorLsVeredaYLsUpr(territorializacionDTO.getIdLsVereda(),
				territorializacionDTO.getIdLsUpr(), territorializacionDTO.getIdLsLocalidad()))
						.thenReturn(territorializacion);
		when(territorializacionMapper.territorializacionEntityToDTO(territorializacion))
				.thenReturn(territorializacionDTO);
		assertNotNull(sessionConsultaAFS.buscarPorLsVeredaYLsUpr(territorializacionDTO));
		when(territorializacionServiceRepo.buscarPorLsVeredaYLsUpr(territorializacionDTO.getIdLsVereda(),
				territorializacionDTO.getIdLsUpr(), territorializacionDTO.getIdLsLocalidad())).thenReturn(null);
		when(territorializacionMapper.territorializacionEntityToDTO(territorializacion)).thenReturn(null);
		assertNotNull(sessionConsultaAFS.buscarPorLsVeredaYLsUpr(territorializacionDTO));
	}

	@Test
	public void testBuscarSectorYCategoria() throws SpddExceptions {
		Long id = 1L;
		Long idSector = 2L;
		Long idCategoria = 3L;
		ArgumentoListaSimple sector = new ArgumentoListaSimple();
		sector.setIdArgumento(idSector);
		ArgumentoListaSimple categoria = new ArgumentoListaSimple();
		categoria.setIdArgumento(idCategoria);
		Equipamento equipamento = new Equipamento();
		equipamento.setIdEquipamento(id);
		equipamento.setIdLsSector(sector);
		equipamento.setIdLsCategoria(categoria);
		EquipamientoDTO equipamientoDTO = new EquipamientoDTO();
		equipamientoDTO.setIdEquipamento(id);
		equipamientoDTO.setIdLsSector(idSector);
		equipamientoDTO.setIdLsCategoria(idCategoria);
		when(equipamientoServiceRepo.validarSectorYCategoria(idSector, idCategoria)).thenReturn(equipamento);
		when(equipamientoMapper.equipamientoEntityToDTO(equipamento)).thenReturn(equipamientoDTO);
		assertNotNull(sessionConsultaAFS.buscarSectorYCategoria(idSector, idCategoria));
		when(equipamientoServiceRepo.validarSectorYCategoria(idSector, idCategoria)).thenReturn(null);
		when(equipamientoMapper.equipamientoEntityToDTO(equipamento)).thenReturn(null);
		assertNotNull(sessionConsultaAFS.buscarSectorYCategoria(idSector, idCategoria));
	}

	@Test
	public void testBuscarLineaInversionPorConceptoYSector() throws SpddExceptions {
		String concepto = "concepto";
		Long id = 1L;
		Long idSector = 2L;
		ArgumentoListaSimple sector = new ArgumentoListaSimple();
		sector.setIdArgumento(idSector);
		LineaDeInversion lineaDeInversion = new LineaDeInversion();
		lineaDeInversion.setIdLineaInversion(id);
		lineaDeInversion.setIdLsSector(sector);
		LineaDeInversionDTO lineaDeInversionDTO = new LineaDeInversionDTO();
		lineaDeInversionDTO.setIdLineaInversion(id);
		lineaDeInversionDTO.setIdLsSector(idSector);
		when(lineaDeInversionServiceRepo.buscarConceptoYSector(concepto, idSector)).thenReturn(lineaDeInversion);
		when(lineaDeInversionMapper.lineaDeInversionEntityToDTO(lineaDeInversion)).thenReturn(lineaDeInversionDTO);
		assertNotNull(sessionConsultaAFS.buscarLineaInversionPorConceptoYSector(concepto, idSector));
		when(lineaDeInversionServiceRepo.buscarConceptoYSector(concepto, idSector)).thenReturn(null);
		when(lineaDeInversionMapper.lineaDeInversionEntityToDTO(lineaDeInversion)).thenReturn(null);
		assertNotNull(sessionConsultaAFS.buscarLineaInversionPorConceptoYSector(concepto, idSector));
	}

	@Test
	public void testBuscarPorCodigoYNombre() {
		String nombre = "nombre";
		Long id = 1L;
		Long codigo = 2L;
		ComponenteGasto componenteGasto = new ComponenteGasto();
		componenteGasto.setIdComponenteGasto(id);
		componenteGasto.setCodigoComponente(codigo);
		componenteGasto.setNombreComponente(nombre);
		ComponenteGastoDTO componenteGastoDTO = new ComponenteGastoDTO();
		componenteGastoDTO.setIdComponenteGasto(id);
		componenteGastoDTO.setCodigoComponente(codigo);
		componenteGastoDTO.setNombreComponente(nombre);
		when(componenteGastoServiceRepo.buscarPorCodigoYNombre(codigo, nombre)).thenReturn(componenteGasto);
		when(componenteGastoMapper.componenteGastoEntityToDTO(componenteGasto)).thenReturn(componenteGastoDTO);
		assertNotNull(sessionConsultaAFS.buscarPorCodigoYNombre(codigo, nombre));
		when(componenteGastoServiceRepo.buscarPorCodigoYNombre(codigo, nombre)).thenReturn(null);
		when(componenteGastoMapper.componenteGastoEntityToDTO(componenteGasto)).thenReturn(null);
		assertNotNull(sessionConsultaAFS.buscarPorCodigoYNombre(codigo, nombre));
	}

	@Test
	public void testBuscarIdLsModuloYIdLsTema() {
		Long id = 1L;
		Integer ide = 4;
		Long idModulo = 2L;
		Long idTema = 3L;
		ArgumentoListaSimple modulo = new ArgumentoListaSimple();
		modulo.setIdArgumento(idModulo);
		ArgumentoListaSimple tema = new ArgumentoListaSimple();
		modulo.setIdArgumento(idTema);
		ConfigCargueArchivo configCargueArchivo = new ConfigCargueArchivo();
		configCargueArchivo.setIdConfigCargue(id);
		configCargueArchivo.setIdLsModulo(modulo);
		configCargueArchivo.setIdLsTema(tema);
		ConfiguracionCargueArchivoDTO configuracionCargueArchivoDTO = new ConfiguracionCargueArchivoDTO();
		configuracionCargueArchivoDTO.setIdConfigCargue(ide);
		configuracionCargueArchivoDTO.setIdLsModulo(idModulo);
		configuracionCargueArchivoDTO.setIdLsTema(idTema);
		when(configCargueArchivoServiceRepo.buscarPorIdLsModuloYIdLsTema(configuracionCargueArchivoDTO.getIdLsModulo(),
				configuracionCargueArchivoDTO.getIdLsTema())).thenReturn(configCargueArchivo);
		when(configCargueArchivoMapper.configuracionCargueEntityToDTO(configCargueArchivo))
				.thenReturn(configuracionCargueArchivoDTO);
		assertNotNull(sessionConsultaAFS.buscarIdLsModuloYIdLsTema(configuracionCargueArchivoDTO));
		when(configCargueArchivoServiceRepo.buscarPorIdLsModuloYIdLsTema(configuracionCargueArchivoDTO.getIdLsModulo(),
				configuracionCargueArchivoDTO.getIdLsTema())).thenReturn(null);
		when(configCargueArchivoMapper.configuracionCargueEntityToDTO(configCargueArchivo)).thenReturn(null);
		assertNotNull(sessionConsultaAFS.buscarIdLsModuloYIdLsTema(configuracionCargueArchivoDTO));
	}

	@Test
	public void testBuscarIdListaSimpleArgumento() throws SpddExceptions {
		Long id = 1L;
		ListaSimple listaSimple = new ListaSimple();
		ArgumentoListaSimpleDTO res;
		listaSimple.setIdListaSimple(id);
		ArgumentoListaSimpleDTO peticion = new ArgumentoListaSimpleDTO();
		ArgumentoListaSimple respuesta = new ArgumentoListaSimple();
		ArgumentoListaSimpleDTO auxiliar = new ArgumentoListaSimpleDTO();
		when(argumentoListaSimpleServiceRepo.buscarIdListaSimple(peticion.getIdListaSimple(), peticion.getArgumento()))
				.thenReturn(null);
		res = sessionConsultaAFS.buscarIdListaSimpleArgumento(peticion);
		assertNotNull(res);
		when(argumentoListaSimpleServiceRepo.buscarIdListaSimple(peticion.getIdListaSimple(), peticion.getArgumento()))
				.thenReturn(respuesta);
		when(argumentoListaSimpleMapper.argumentoListaSimpleEntityToDTO(respuesta)).thenReturn(auxiliar);
		res = sessionConsultaAFS.buscarIdListaSimpleArgumento(peticion);
		assertNotNull(res);
	}

	@Test
	public void testBuscarUnidadMedidaYVerbo() throws SpddExceptions {
		Long id = 3L;
		Long idUnidadMedida = 1L;
		Long idVerbo = 2L;
		ArgumentoListaSimple unidadMedida = new ArgumentoListaSimple();
		unidadMedida.setIdArgumento(idUnidadMedida);
		ArgumentoListaSimple verbo = new ArgumentoListaSimple();
		verbo.setIdArgumento(idVerbo);
		EstructuraMeta estructuraMeta = new EstructuraMeta();
		estructuraMeta.setIdEstructuraMetas(id);
		estructuraMeta.setIdLsUnidadMedida(unidadMedida);
		estructuraMeta.setIdLsVerbo(verbo);
		EstructuraMetaDTO estructuraMetaDTO = new EstructuraMetaDTO();
		estructuraMetaDTO.setIdEstructuraMetas(id);
		estructuraMetaDTO.setIdLsUnidadMedida(idUnidadMedida);
		estructuraMetaDTO.setIdLsVerbo(idVerbo);
		when(estructuraMetaServiceRepo.validarUnidadMedidaYVerbo(idUnidadMedida, idVerbo)).thenReturn(estructuraMeta);
		when(estructuraMetaMapper.estructuraMetaEntityToDTO(estructuraMeta)).thenReturn(estructuraMetaDTO);
		assertNotNull(sessionConsultaAFS.buscarUnidadMedidaYVerbo(idUnidadMedida, idVerbo));
		when(estructuraMetaServiceRepo.validarUnidadMedidaYVerbo(idUnidadMedida, idVerbo)).thenReturn(null);
		when(estructuraMetaMapper.estructuraMetaEntityToDTO(estructuraMeta)).thenReturn(null);
		assertNotNull(sessionConsultaAFS.buscarUnidadMedidaYVerbo(idUnidadMedida, idVerbo));
	}

	@Test
	public void testBuscarSql() throws SpddExceptions {
		Long id = 1L;
		ConfigCargueArchivo configCargueArchivo = new ConfigCargueArchivo();
		configCargueArchivo.setIdConfigCargue(id);
		when(configCargueArchivoServiceRepo.buscarSql("select * from configCargueArchivo")).thenReturn(1);
		assertNotNull(sessionConsultaAFS.buscarSql("select * from configCargueArchivo"));
	}

	@Test
	public void testValidarFuncionarioPorEntidadYFuncion() throws Exception {
		FuncionarioClaveEntidad funcionario = new FuncionarioClaveEntidad();
		when(funcionarioClaveEntidadServiceRepo.validarFuncionarioPorIdLsFuncionYEntidad("01", 12L))
				.thenReturn(funcionario);
		when(funcionarioClaveEntidadMapper.funcionarioClaveEntidadToDTO(funcionario))
				.thenReturn(new FuncionarioClaveEntidadDTO());
		assertNotNull(sessionConsultaAFS.validarFuncionarioPorEntidadYFuncion("01", 12L));
	}

	@Test
	public void testBuscarPorSector() throws Exception {
		List<LineaDeInversion> linea = new ArrayList<>();
		when(lineaDeInversionServiceRepo.buscarPorSector("ls")).thenReturn(linea);
		when(lineaDeInversionMapper.lineaDeInversionEntitiesTODTO(linea)).thenReturn(new ArrayList<>());
		assertNotNull(sessionConsultaAFS.buscarPorSector("ls"));

	}

	@Test
	public void testBuscarTerrPorLocalida() throws Exception {
		List<Territorializacion> lista = new ArrayList<>();
		when(territorializacionServiceRepo.buscarPorLocalidad("ls")).thenReturn(lista);
		when(territorializacionMapper.territorializacionEntitiesToDTO(lista)).thenReturn(new ArrayList<>());
		assertNotNull(sessionConsultaAFS.buscarTerrPorLocalida());
	}

	@Test
		public void testConsultarArgumentoListaSimplePorResultadoYNombreListaSimple() throws Exception {
			ArgumentoListaSimple argumetno = new ArgumentoListaSimple();
			when(argumentoListaSimpleServiceRepo.obtenerPorResultadoYNombreListaSimple("ls","nombrels")).thenReturn(argumetno);
			when(argumentoListaSimpleMapper.argumentoListaSimpleEntityToDTO(argumetno))
					.thenReturn(new ArgumentoListaSimpleDTO());
			assertNotNull(sessionConsultaAFS.consultarArgumentoListaSimplePorResultadoYNombreListaSimple("ls","nombrels"));
		}
}