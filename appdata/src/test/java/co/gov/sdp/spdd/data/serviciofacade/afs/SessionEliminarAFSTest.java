package co.gov.sdp.spdd.data.serviciofacade.afs;

import static org.mockito.Mockito.doNothing;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

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

/**
 * 
 * @author Sumset
 *
 */
@RunWith(SpringRunner.class)
public class SessionEliminarAFSTest {
	@TestConfiguration
	static class SessionEliminarAFSTestContextConfiguration {
		@Bean
		public SessionEliminarAFS sessionEliminarAFS() {
			return new SessionEliminarAFS();
		}
	}

	@Autowired
	SessionEliminarAFS sessionEliminarAFS;

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
	public void eliminarComponenteGestionUsuarioTest() {
		doNothing().when(componenteGestionUsuarioServiceRepo).eliminar(1L);
		sessionEliminarAFS.eliminarComponenteGestionUsuario(1L);

	}

	@Test
	public void eliminarInformacionPresupuestalTest() {
		doNothing().when(informacionPresupuestalServiceRepo).eliminar(1L);
		sessionEliminarAFS.eliminarInformacionPresupuestal(1L);
	}

	@Test
	public void eliminarProyectoInversionUsuarioTest() {
		doNothing().when(proyectosInversionUsuarioServiceRepo).eliminar(1L);
		sessionEliminarAFS.eliminarProyectoInversionUsuario(1L);
	}

}
