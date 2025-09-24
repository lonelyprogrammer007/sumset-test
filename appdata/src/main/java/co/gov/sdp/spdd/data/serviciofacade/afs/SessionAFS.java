package co.gov.sdp.spdd.data.serviciofacade.afs;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

@Component
public class SessionAFS implements Serializable {

	/**
	 * 
	 */
	@Autowired
	SPDDLogger spddLogger;

	/**
	 * Atributo que representa objeto de acceso a datos por medio del cual se
	 * realiza las tareas de la capa de persistencia.
	 */
	@Autowired
	IArchivoProcesadoServiceRepo archivoProcesadoServiceRepo;
	
	/**
	 * 
	 */
	@Autowired
	IConfigCargueArchivoServiceRepo configCargueArchivoServiceRepo;

	/**
	 * Atributo que representa objeto de acceso a datos por medio del cual se
	 * realiza las tareas de la capa de persistencia.
	 */
	@Autowired
	IArgumentoListaSimpleServiceRepo argumentoListaSimpleServiceRepo;

	/**
	 * Atributo que representa objeto de acceso a datos por medio del cual se
	 * realiza las tareas de la capa de persistencia.
	 */
	@Autowired
	IBancoDeProyectoServiceRepo bancoDeProyectoServiceRepo;

	/**
	 * Atributo que representa objeto de acceso a datos por medio del cual se
	 * realiza las tareas de la capa de persistencia.
	 */
	@Autowired
	IBuzonNotificacioneServiceRepo buzonNotificacioneServiceRepo;

	/**
	 * Atributo que representa objeto de acceso a datos por medio del cual se
	 * realiza las tareas de la capa de persistencia.
	 */
	@Autowired
	IComponenteGastoServiceRepo componenteGastoServiceRepo;

	/**
	 * Atributo que representa objeto de acceso a datos por medio del cual se
	 * realiza las tareas de la capa de persistencia.
	 */
	@Autowired
	IComponenteGestionUsuarioServiceRepo componenteGestionUsuarioServiceRepo;

	/**
	 * Atributo que representa objeto de acceso a datos por medio del cual se
	 * realiza las tareas de la capa de persistencia.
	 */
	@Autowired
	IConsecutivoServiceRepo consecutivoServiceRepo;

	/**
	 * Atributo que representa objeto de acceso a datos por medio del cual se
	 * realiza las tareas de la capa de persistencia.
	 */
	@Autowired
	IEntidadServiceRepo entidadServiceRepo;

	/**
	 * Atributo que representa objeto de acceso a datos por medio del cual se
	 * realiza las tareas de la capa de persistencia.
	 */
	@Autowired
	IEquipamientoServiceRepo equipamientoServiceRepo;

	/**
	 * Atributo que representa objeto de acceso a datos por medio del cual se
	 * realiza las tareas de la capa de persistencia.
	 */
	@Autowired
	IEstadoServicioServiceRepo estadoServicioServiceRepo;

	/**
	 * Atributo que representa objeto de acceso a datos por medio del cual se
	 * realiza las tareas de la capa de persistencia.
	 */
	@Autowired
	IEstructuraMetaServiceRepo estructuraMetaServiceRepo;

	/**
	 * Atributo que representa objeto de acceso a datos por medio del cual se
	 * realiza las tareas de la capa de persistencia.
	 */
	@Autowired
	IFuncionarioClaveEntidadServiceRepo funcionarioClaveEntidadServiceRepo;

	/**
	 * Atributo que representa objeto de acceso a datos por medio del cual se
	 * realiza las tareas de la capa de persistencia.
	 */
	@Autowired
	IInformacionPresupuestalServiceRepo informacionPresupuestalServiceRepo;

	/**
	 * Atributo que representa objeto de acceso a datos por medio del cual se
	 * realiza las tareas de la capa de persistencia.
	 */
	@Autowired
	ILineaDeInversionServiceRepo lineaDeInversionServiceRepo;

	/**
	 * Atributo que representa objeto de acceso a datos por medio del cual se
	 * realiza las tareas de la capa de persistencia.
	 */
	@Autowired
	IListaCompuestaServiceRepo listaCompuestaServiceRepo;

	/**
	 * Atributo que representa objeto de acceso a datos por medio del cual se
	 * realiza las tareas de la capa de persistencia.
	 */
	@Autowired
	IListaSimpleServiceRepo listaSimpleServiceRepo;

	/**
	 * Atributo que representa objeto de acceso a datos por medio del cual se
	 * realiza las tareas de la capa de persistencia.
	 */
	@Autowired
	IParametroGeneralServiceRepo parametroGeneralServiceRepo;

	/**
	 * Atributo que representa objeto de acceso a datos por medio del cual se
	 * realiza las tareas de la capa de persistencia.
	 */
	@Autowired
	IPotProyectoInstrumentoServiceRepo potProyectoInstrumentoServiceRepo;

	/**
	 * 
	 */
	@Autowired
	IPotInstrumentoServiceRepo potInstrumentoServiceRepo;

	/**
	 * 
	 */
	@Autowired
	IPotObraServiceRepo potObraServiceRepo;

	/**
	 * Atributo que representa objeto de acceso a datos por medio del cual se
	 * realiza las tareas de la capa de persistencia.
	 */
	@Autowired
	IProyectoInversionServiceRepo proyectoInversionServiceRepo;

	/**
	 * Atributo que representa objeto de acceso a datos por medio del cual se
	 * realiza las tareas de la capa de persistencia.
	 */
	@Autowired
	IProyectosInversionUsuarioServiceRepo proyectosInversionUsuarioServiceRepo;

	/**
	 * Atributo que representa objeto de acceso a datos por medio del cual se
	 * realiza las tareas de la capa de persistencia.
	 */
	@Autowired
	ITerritorializacionServiceRepo territorializacionServiceRepo;

	/**
	 * Atributo que representa objeto de acceso a datos por medio del cual se
	 * realiza las tareas de la capa de persistencia.
	 */
	@Autowired
	IUsuarioEntidadServiceRepo usuarioEntidadServiceRepo;
	
	/**
	 * 
	 */
	@Autowired
	IPddNivelAtributoServiceRepo pddNivelAtributoServiceRepo;
	
	/**
	 * 
	 */
	@Autowired
	IConfiguracionNotificacionServiceRepo configuracionNotificacionServiceRepo;
	
	/**
	 * 
	 */
	@Autowired
	IPddServiceRepo pddServiceRepo;

	/**
	 * Atributo que representa objeto de mapeo entre entidades y dtos y viceversa.
	 */
	@Autowired
	ArchivoProcesadoMapper archivoProcesadoMapper;
	
	/**
	 * 
	 */
	@Autowired
	ConfigCargueArchivoMapper configCargueArchivoMapper;

	/**
	 * Atributo que representa objeto de mapeo entre entidades y dtos y viceversa.
	 */
	@Autowired
	ArgumentoListaSimpleMapper argumentoListaSimpleMapper;

	/**
	 * Atributo que representa objeto de mapeo entre entidades y dtos y viceversa.
	 */
	@Autowired
	BancoDeProyectoMapper bancoDeProyectoMapper;

	/**
	 * Atributo que representa objeto de mapeo entre entidades y dtos y viceversa.
	 */
	@Autowired
	BuzonNotificacionesMapper buzonNotificacionesMapper;

	/**
	 * Atributo que representa objeto de mapeo entre entidades y dtos y viceversa.
	 */
	@Autowired
	ComponenteGastoMapper componenteGastoMapper;

	/**
	 * Atributo que representa objeto de mapeo entre entidades y dtos y viceversa.
	 */
	@Autowired
	ComponenteGestionUsuarioMapper componenteGestionUsuarioMapper;

	/**
	 * Atributo que representa objeto de mapeo entre entidades y dtos y viceversa.
	 */
	@Autowired
	ConsecutivoMapper consecutivoMapper;

	/**
	 * Atributo que representa objeto de mapeo entre entidades y dtos y viceversa.
	 */
	@Autowired
	EntidadMapper entidadMapper;

	/**
	 * Atributo que representa objeto de mapeo entre entidades y dtos y viceversa.
	 */
	@Autowired
	EquipamientoMapper equipamientoMapper;

	/**
	 * Atributo que representa objeto de mapeo entre entidades y dtos y viceversa.
	 */
	@Autowired
	EstadoServicioMapper estadoServicioMapper;

	/**
	 * Atributo que representa objeto de mapeo entre entidades y dtos y viceversa.
	 */
	@Autowired
	EstructuraMetaMapper estructuraMetaMapper;

	/**
	 * Atributo que representa objeto de mapeo entre entidades y dtos y viceversa.
	 */
	@Autowired
	FuncionarioClaveEntidadMapper funcionarioClaveEntidadMapper;

	/**
	 * Atributo que representa objeto de mapeo entre entidades y dtos y viceversa.
	 */
	@Autowired
	InformacionPresupuestalMapper informacionPresupuestalMapper;

	/**
	 * Atributo que representa objeto de mapeo entre entidades y dtos y viceversa.
	 */
	@Autowired
	LineaDeInversionMapper lineaDeInversionMapper;

	/**
	 * Atributo que representa objeto de mapeo entre entidades y dtos y viceversa.
	 */
	@Autowired
	ListaCompuestaMapper listaCompuestaMapper;

	/**
	 * Atributo que representa objeto de mapeo entre entidades y dtos y viceversa.
	 */
	@Autowired
	ListaSimpleMapper listaSimpleMapper;

	/**
	 * Atributo que representa objeto de mapeo entre entidades y dtos y viceversa.
	 */
	@Autowired
	ParametroGeneralMapper parametroGeneralMapper;

	/**
	 * Atributo que representa objeto de mapeo entre entidades y dtos y viceversa.
	 */
	@Autowired
	ProyectoInversionMapper proyectoInversionMapper;

	/**
	 * Atributo que representa objeto de mapeo entre entidades y dtos y viceversa.
	 */
	@Autowired
	ProyectosInversionUsuarioMapper proyectosInversionUsuarioMapper;

	/**
	 * Atributo que representa objeto de mapeo entre entidades y dtos y viceversa.
	 */
	@Autowired
	PotProyectoInstrumentoMapper potProyectoInstrumentoMapper;

	/**
	 * 
	 */
	@Autowired
	PotInstrumentoMapper potInstrumentoMapper;

	/**
	 * 
	 */
	@Autowired
	PotObraMapper potObraMapper;

	/**
	 * Atributo que representa objeto de mapeo entre entidades y dtos y viceversa.
	 */
	@Autowired
	TerritorializacionMapper territorializacionMapper;

	/**
	 * Atributo que representa objeto de mapeo entre entidades y dtos y viceversa.
	 */
	@Autowired
	UsuarioEntidadMapper usuarioEntidadMapper;
	
	/**
	 * 
	 */
	@Autowired
	PddNivelAtributoMapper pddNivelAtributoMapper;

	/**
	 * 
	 */
	@Autowired
	ConfiguracionNotificacionMapper configuracionNotificacionMapper;
	
	/**
	 * 
	 */
	@Autowired
	PddMapper pddMapper;
}
