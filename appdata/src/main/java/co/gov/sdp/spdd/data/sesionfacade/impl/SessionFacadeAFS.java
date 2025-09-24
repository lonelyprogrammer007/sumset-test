package co.gov.sdp.spdd.data.sesionfacade.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

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
import co.gov.sdp.spdd.data.model.afs.ProyectoInversion;
import co.gov.sdp.spdd.data.model.afs.Territorializacion;
import co.gov.sdp.spdd.data.serviciofacade.afs.SessionConsultaAFS;
import co.gov.sdp.spdd.data.serviciofacade.afs.SessionEliminarAFS;
import co.gov.sdp.spdd.data.serviciofacade.afs.SessionModificarAFS;
import co.gov.sdp.spdd.data.serviciofacade.afs.SessionRegistroAFS;
import co.gov.sdp.spdd.data.sesionfacade.interfaces.ISessionFacadeAFS;

/**
 * @author Sumset
 *
 */
@Service
public class SessionFacadeAFS implements ISessionFacadeAFS {

	/**
	 * Atributo de serialización.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@Autowired
	SessionConsultaAFS sessionConsultaAFS;

	/**
	 * 
	 */
	@Autowired
	SessionEliminarAFS sessionEliminarAFS;

	/**
	 * 
	 */
	@Autowired
	SessionRegistroAFS sessionRegistroAFS;

	/**
	 * 
	 */
	@Autowired
	SessionModificarAFS sessionModificarAFS;

	@Override
	public ArgumentoListaSimpleDTO buscarIdListaSimpleArgumento(ArgumentoListaSimpleDTO peticion)
			throws SpddExceptions {
		return sessionConsultaAFS.buscarIdListaSimpleArgumento(peticion);
	}

	@Override
	public PotProyectoInstrumentoDTO buscarPorIdLsPotObraYIdLsPotInstrumento(
			PotProyectoInstrumentoDTO potProyectoInstrumentoDTO) throws SpddExceptions {
		return sessionConsultaAFS.buscarPorIdLsPotObraYIdLsPotInstrumento(potProyectoInstrumentoDTO);
	}

	@Override
	public ConfiguracionCargueArchivoDTO buscarIdLsModuloYIdLsTema(
			ConfiguracionCargueArchivoDTO configuracionCargueArchivoDTO) throws SpddExceptions {
		return sessionConsultaAFS.buscarIdLsModuloYIdLsTema(configuracionCargueArchivoDTO);
	}

	@Override
	public TerritorializacionDTO buscarPorLsVeredaYLsUpr(TerritorializacionDTO territorializacionDTO)
			throws SpddExceptions {
		return sessionConsultaAFS.buscarPorLsVeredaYLsUpr(territorializacionDTO);
	}

	@Override
	public TerritorializacionDTO buscarPorLsBarrioYLsUpzYLsLocalidad(TerritorializacionDTO territorializacionDTO)
			throws SpddExceptions {
		return sessionConsultaAFS.buscarPorLsBarrioYLsUpzYLsLocalidad(territorializacionDTO);
	}

	@Override
	public EquipamientoDTO buscarSectorYCategoria(Long sector, Long categoria) throws SpddExceptions {
		return sessionConsultaAFS.buscarSectorYCategoria(sector, categoria);
	}

	@Override
	public LineaDeInversionDTO buscarLineaInversionPorConceptoYSector(String concepto, Long sector)
			throws SpddExceptions {
		return sessionConsultaAFS.buscarLineaInversionPorConceptoYSector(concepto, sector);
	}

	@Override
	public ComponenteGastoDTO buscarCodigoYNombre(Long codigo, String nombre) throws SpddExceptions {
		return sessionConsultaAFS.buscarPorCodigoYNombre(codigo, nombre);
	}

	@Override
	public EstructuraMetaDTO buscarUnidadMedidaYVerbo(Long unidadMedida, Long verbo) throws SpddExceptions {
		return sessionConsultaAFS.buscarUnidadMedidaYVerbo(unidadMedida, verbo);
	}

	@Override
	public ArchivoProcesadoDTO consultarArchivoProcesadoPorId(Long id) throws SpddExceptions {
		return sessionConsultaAFS.consultarArchivoProcesadoPorId(id);
	}

	@Override
	public ConfiguracionCargueArchivoDTO consultarConfigCargueArchivoProcesado(Long id) throws SpddExceptions {
		return sessionConsultaAFS.consultarConfigCargueArchivoProcesado(id);
	}

	@Override
	public GenericoDTO consultarArchivoProcesadoPorFiltro(ArchivoProcesadoDTO peticion) throws SpddExceptions {
		return sessionConsultaAFS.consultarArchivoProcesadofiltrarPorCampo(peticion);
	}

	@Override
	public ArgumentoListaSimpleDTO consultarArgumentoListaSimplePorId(Long id) throws SpddExceptions {
		return sessionConsultaAFS.consultarArgumentoListaSimplePorId(id);
	}

	@Override
	public GenericoDTO consultarArgumentoPorIdPdd(Long idPlan) throws SpddExceptions {
		return sessionConsultaAFS.consultarArgumentoPorIdPdd(idPlan);
	}

	@Override
	public BancoDeProyectoDTO consultarBancoDeProyectoporId(Long id) throws SpddExceptions {
		return sessionConsultaAFS.consultarBancoDeProyectoporId(id);
	}

	@Override
	public BuzonNotificacionesDTO consultarBuzonNotificacionesPorId(Long id) throws SpddExceptions {
		return sessionConsultaAFS.consultarBuzonNotificacionesPorId(id);
	}

	@Override
	public GenericoDTO consultarBuzonNotificacionesPorFiltro(BuzonNotificacionesDTO peticion) throws SpddExceptions {
		return sessionConsultaAFS.consultarBuzonNotificacionesPorFiltro(peticion);
	}

	@Override
	public ComponenteGastoDTO consultarComponenteGastoPorId(Long id) throws SpddExceptions {
		return sessionConsultaAFS.consultarComponenteGastoPorId(id);
	}

	@Override
	public ComponenteGestionUsuarioDTO consultarComponenteGestionUsuarioPorId(Long id) throws SpddExceptions {
		return sessionConsultaAFS.consultarComponenteGestionUsuarioPorId(id);
	}

	@Override
	public ConsecutivoDTO consultarConsecutivoPorId(Long id) throws SpddExceptions {
		return sessionConsultaAFS.consultarConsecutivoPorId(id);
	}

	@Override
	public GenericoDTO consultarConsecutivoPorFiltro(ConsecutivoDTO peticion) throws SpddExceptions {
		return sessionConsultaAFS.consultarConsecutivoPorFiltro(peticion);
	}

	@Override
	public EntidadDTO consultarEntidad(String id) throws SpddExceptions {
		return sessionConsultaAFS.consultarEntidad(id);
	}

	@Override
	public EquipamientoDTO consultarEquipamiento(Long id) throws SpddExceptions {
		return sessionConsultaAFS.consultarEquipamiento(id);
	}

	@Override
	public GenericoDTO consultarEquipamientoPorFiltro(EquipamientoDTO peticion) throws SpddExceptions {
		return sessionConsultaAFS.consultarEquipamientoPorFiltro(peticion);
	}

	@Override
	public EstadoServicioDTO consultarEstadoServicio(Long id) throws SpddExceptions {
		return sessionConsultaAFS.consultarEstadoServicio(id);
	}

	@Override
	public GenericoDTO consultarEstadoServicioPorFiltro(EstadoServicioDTO peticion) throws SpddExceptions {
		return sessionConsultaAFS.consultarEstadoServicioPorFiltro(peticion);
	}

	@Override
	public EstructuraMetaDTO consultarEstructuraMeta(Long id) throws SpddExceptions {
		return sessionConsultaAFS.consultarEstructuraMeta(id);
	}

	@Override
	public GenericoDTO consultarEstructuraMetaPorFiltro(EstructuraMetaDTO peticion) throws SpddExceptions {
		return sessionConsultaAFS.consultarEstructuraMetaPorFiltro(peticion);
	}

	@Override
	public FuncionarioClaveEntidadDTO consultarFuncionarioClaveEntidad(Long id) throws SpddExceptions {
		return sessionConsultaAFS.consultarFuncionarioClaveEntidad(id);
	}

	@Override
	public GenericoDTO consultarFuncionarioClaveEntidadPorFiltro(FuncionarioClaveEntidadDTO peticion)
			throws SpddExceptions {
		return sessionConsultaAFS.consultarFuncionarioClaveEntidadPorFiltro(peticion);
	}

	@Override
	public InformacionPresupuestalDTO consultarInformacionPresupuestalPorId(Long id) throws SpddExceptions {
		return sessionConsultaAFS.consultarInformacionPresupuestalPorId(id);
	}

	@Override
	public GenericoDTO consultarInformacionPresupuestalPorFiltro(InformacionPresupuestalDTO peticion)
			throws SpddExceptions {
		return sessionConsultaAFS.consultarInformacionPresupuestalPorFiltro(peticion);
	}

	@Override
	public LineaDeInversionDTO consultarLineaDeInversionPorId(Long id) throws SpddExceptions {
		return sessionConsultaAFS.consultarLineaDeInversion(id);
	}

	@Override
	public GenericoDTO consultarLineaDeInversionPorFiltro(LineaDeInversionDTO peticion) throws SpddExceptions {
		return sessionConsultaAFS.consultarLineaDeInversionPorFiltro(peticion);
	}

	@Override
	public ListaCompuestaDTO consultarListaCompuestaPorId(Long id) throws SpddExceptions {
		return sessionConsultaAFS.consultarListaCompuestaPorId(id);
	}

	@Override
	public GenericoDTO consultarListaCompuestaPorFiltro(ListaCompuestaDTO peticion) throws SpddExceptions {
		return sessionConsultaAFS.consultarListaCompuestaPorFiltro(peticion);
	}

	@Override
	public ListaSimpleDTO consultarListaSimplePorID(Long id) throws SpddExceptions {
		return sessionConsultaAFS.consultarListaSimplePorID(id);
	}

	@Override
	public ParametroGeneralDTO consultarParametroGeneralPorId(String id) throws SpddExceptions {
		return sessionConsultaAFS.consultarParametroGeneralPorId(id);
	}

	@Override
	public GenericoDTO consultarParametroGeneralPorFiltro(ParametroGeneralDTO peticion) throws SpddExceptions {
		return sessionConsultaAFS.consultarParametroGeneralPorFiltro(peticion);
	}

	@Override
	public PotProyectoInstrumentoDTO consultarPotProyectoInstrumentoPorId(Long id) throws SpddExceptions {
		return sessionConsultaAFS.consultarPotProyectoInstrumentoPorId(id);
	}

	@Override
	public GenericoDTO consultarPotProyectoInstrumentoPorFiltro(PotProyectoInstrumentoDTO peticion)
			throws SpddExceptions {
		return sessionConsultaAFS.consultarPotProyectoInstrumentoPorFiltro(peticion);
	}

	@Override
	public GenericoDTO consultarPotProyectoInstrumentoTodos() throws SpddExceptions {
		return sessionConsultaAFS.consultarPotProyectoInstrumentoTodos();
	}

	@Override
	public GenericoDTO consultarPotInstrumento() throws SpddExceptions {
		return sessionConsultaAFS.consultarPotInstrumento();
	}

	@Override
	public GenericoDTO consultarPotObra() throws SpddExceptions {
		return sessionConsultaAFS.consultarPotObra();
	}

	@Override
	public GenericoDTO consultarPdd() throws SpddExceptions {
		return sessionConsultaAFS.consultarPdd();
	}

	@Override
	public ProyectoInversionDTO consultarProyectoInversionPorId(Long id) throws SpddExceptions {
		return sessionConsultaAFS.consultarProyectoInversionPorId(id);
	}

	@Override
	public List<ProyectosInversionUsuarioDTO> consultarProyectosInversionUsuarioPorId(String usuario)
			throws SpddExceptions {
		return sessionConsultaAFS.consultarProyectosInversionUsuarioPorId(usuario);
	}

	@Override
	public TerritorializacionDTO consultarTerritorializacionPorId(Long id) throws SpddExceptions {
		return sessionConsultaAFS.consultarTerritorializacionPorId(id);
	}

	@Override
	public GenericoDTO consultarTerritorializacionPorFiltro(TerritorializacionDTO peticion) throws SpddExceptions {
		return sessionConsultaAFS.consultarTerritorializacionPorFiltro(peticion);
	}

	@Override
	public UsuarioEntidadDTO consultarUsuarioEntidadPorId(Long id) throws SpddExceptions {
		return sessionConsultaAFS.consultarUsuarioEntidadPorId(id);
	}

	@Override
	public GenericoDTO consultarUsuarioEntidadPorFiltro(UsuarioEntidadDTO peticion) throws SpddExceptions {
		return sessionConsultaAFS.consultarUsuarioEntidadPorFiltro(peticion);
	}

	@Override
	public GenericoDTO consultarArchivoProcesadoTodos() throws SpddExceptions {
		return sessionConsultaAFS.consultarArchivoProcesadoTodos();
	}

	@Override
	public GenericoDTO consultarArgumentoListaSimpleTodos() throws SpddExceptions {
		return sessionConsultaAFS.consultarArchivoProcesadoTodos();
	}

	@Override
	public GenericoDTO consultarArgumentoListaSimplePorFiltro(ArgumentoListaSimpleDTO peticion) throws SpddExceptions {
		return sessionConsultaAFS.consultarArgumentosPorFiltro(peticion);
	}

	@Override
	public GenericoDTO consultarBancoDeProyectosTodos() throws SpddExceptions {
		return sessionConsultaAFS.consultarBancoDeProyectosTodos();
	}

	@Override
	public GenericoDTO consultarBuzonNotificacionesTodos() throws SpddExceptions {
		return sessionConsultaAFS.consultarBuzonNotificacionesTodos();
	}

	@Override
	public GenericoDTO consultarComponenteGastoTodos() throws SpddExceptions {
		return sessionConsultaAFS.consultarComponenteGastoTodos();
	}

	@Override
	public GenericoDTO consultarComponenteGastoPorFiltro(ComponenteGastoDTO peticion) throws SpddExceptions {

		return sessionConsultaAFS.consultarPorFiltroComponenteGasto(peticion);
	}

	@Override
	public GenericoDTO consultarComponenteGestionUsuarioTodos() throws SpddExceptions {
		return sessionConsultaAFS.consultarComponenteGestionUsuarioTodos();
	}

	@Override
	public GenericoDTO consultarConsecutivoTodos() throws SpddExceptions {
		return sessionConsultaAFS.consultarConsecutivoTodos();
	}

	@Override
	public GenericoDTO consultarEntidadTodos() throws SpddExceptions {
		return sessionConsultaAFS.consultarEntidadTodos();
	}

	@Override
	public GenericoDTO consultarEquipamientoTodos() throws SpddExceptions {
		return sessionConsultaAFS.consultarEquipamientoTodos();
	}

	@Override
	public GenericoDTO consultarEstadoServicioTodos() throws SpddExceptions {
		return sessionConsultaAFS.consultarEstadoServicioTodos();
	}

	@Override
	public GenericoDTO consultarEstructuraMetaTodos() throws SpddExceptions {
		return sessionConsultaAFS.consultarEstructuraMetaTodos();
	}

	@Override
	public GenericoDTO consultarFuncionarioClaveEntidadTodos() throws SpddExceptions {
		return sessionConsultaAFS.consultarFuncionarioClaveEntidadTodos();
	}

	@Override
	public GenericoDTO consultarInformacionPresupuestalTodos() throws SpddExceptions {
		return sessionConsultaAFS.consultarInformacionPresupuestalTodos();
	}

	@Override
	public GenericoDTO consultarLineaDeInversionTodos() throws SpddExceptions {
		return sessionConsultaAFS.consultarLineaDeInversionTodos();
	}

	@Override
	public GenericoDTO consultarListaCompuestaTodos() throws SpddExceptions {
		return sessionConsultaAFS.consultarListaCompuestaTodos();
	}

	@Override
	public GenericoDTO consultarListaSimpleTodos() throws SpddExceptions {
		return sessionConsultaAFS.consultarListaSimpleTodos();
	}

	@Override
	public GenericoDTO consultarListaSimplePorFiltro(ListaSimpleDTO peticion) throws SpddExceptions {
		return sessionConsultaAFS.consultarListaSimplePorFiltro(peticion);
	}

	@Override
	public GenericoDTO consultarParametroGeneralTodos() throws SpddExceptions {
		return sessionConsultaAFS.consultarParametroGeneralTodos();
	}

	@Override
	public GenericoDTO consultarProyectoInversionTodos() throws SpddExceptions {
		return sessionConsultaAFS.consultarProyectoInversionTodos();
	}

	@Override
	public GenericoDTO consultarProyectoInversionUsuarioTodos() throws SpddExceptions {
		return sessionConsultaAFS.consultarProyectoInversionUsuarioTodos();
	}

	@Override
	public GenericoDTO consultarTerritorializacionTodos() throws SpddExceptions {
		return sessionConsultaAFS.consultarTerritorializacionTodos();
	}

	@Override
	public TerritorializacionDTO consultarTerritorializacionPorLsVeredaYLsUpr(
			TerritorializacionDTO territorializacionDTO) throws SpddExceptions {
		return sessionConsultaAFS.buscarPorLsVeredaYLsUpr(territorializacionDTO);
	}

	@Override
	public TerritorializacionDTO consultarTerritorializacionPorLsBarrioYLsUpzYLsLocalidad(
			TerritorializacionDTO territorializacionDTO) throws SpddExceptions {
		return sessionConsultaAFS.buscarPorLsBarrioYLsUpzYLsLocalidad(territorializacionDTO);
	}

	@Override
	public GenericoDTO consultarUsuarioEntidadTodos() throws SpddExceptions {
		return sessionConsultaAFS.consultarUsuarioEntidadTodos();
	}

	@Override
	public GenericoDTO consultarUsuariosClavePorFiltro(FuncionarioClaveEntidadDTO peticion) throws SpddExceptions {
		return sessionConsultaAFS.consultarUsuariosClavePorFiltro(peticion);
	}

	@Override
	public GenericoDTO consultarConfiguracionNotificacionPorFiltro(ConfiguracionNotificacionDTO peticion)
			throws SpddExceptions {
		return sessionConsultaAFS.consultarConfiguracionNotificacionPorFiltro(peticion);
	}

	@Override
	public GenericoDTO obtenerArgumentoPorLista(Long id) throws SpddExceptions {
		return sessionConsultaAFS.obtenerArgumentoPorLista(id);
	}

	@Override
	public GenericoDTO obtenerArgumentoPorNombre(String nombre) throws SpddExceptions {
		return sessionConsultaAFS.obtenerArgumentoPorNombre(nombre);
	}

	@Override
	public GenericoDTO obtenerBuzonPorUsuario(String usuario) throws SpddExceptions {
		return sessionConsultaAFS.obtenerBuzonPorUsuario(usuario);
	}

	@Override
	public GenericoDTO obtenerFuncionarioPorEntidad(String codigoEntidad) throws SpddExceptions {
		return sessionConsultaAFS.obtenerFuncionarioPorEntidad(codigoEntidad);
	}

	@Override
	public Long notificacionesPorUsuario(BuzonNotificacionesDTO buzonNotificacionesDTO) throws SpddExceptions {
		return sessionConsultaAFS.notificacionesPorUsuario(buzonNotificacionesDTO);
	}

	@Override
	public List<ProyectoInversion> proyectoInversionObtenerDisponibles() throws SpddExceptions {
		return sessionConsultaAFS.proyectoInversionObtenerDisponibles();
	}

	@Override
	public GenericoDTO pddNivelAtributoObtenerLibres() throws SpddExceptions {
		return sessionConsultaAFS.pddNivelAtributoObtenerLibres();
	}

	@Override
	public ArchivoProcesadoDTO guardarArchivoProcesado(ArchivoProcesadoDTO archivoProcesadoDTO) throws SpddExceptions {
		return sessionRegistroAFS.guardarArchivoProcesado(archivoProcesadoDTO);
	}

	@Override
	public ArgumentoListaSimpleDTO guardarArgumentoListaSimple(ArgumentoListaSimpleDTO argumentoListaSimpleDTO)
			throws SpddExceptions {
		return sessionRegistroAFS.guardarArgumentoListaSimple(argumentoListaSimpleDTO);
	}

	@Override
	public BancoDeProyectoDTO guardarBancoDeProyecto(BancoDeProyectoDTO bancoDeProyectoDTO) throws SpddExceptions {
		return sessionRegistroAFS.guardarBancoDeProyecto(bancoDeProyectoDTO);
	}

	@Override
	public BuzonNotificacionesDTO guardarBuzonNotificaciones(BuzonNotificacionesDTO buzonNotificacionesDTO)
			throws SpddExceptions {
		return sessionRegistroAFS.guardarBuzonNotificaciones(buzonNotificacionesDTO);
	}

	@Override
	public ComponenteGastoDTO guardarComponenteGasto(ComponenteGastoDTO componenteGastoDTO) throws SpddExceptions {
		ComponenteGastoDTO respuesta = sessionConsultaAFS.buscarPorCodigoYNombre(
				componenteGastoDTO.getCodigoComponente(), componenteGastoDTO.getNombreComponente());
		return respuesta.getIdComponenteGasto() == null ? sessionRegistroAFS.guardarComponenteGasto(componenteGastoDTO)
				: new ComponenteGastoDTO();
	}

	@Override
	public ComponenteGastoDTO modificarComponenteGasto(ComponenteGastoDTO componenteGastoDTO) throws SpddExceptions {
		return sessionRegistroAFS.guardarComponenteGasto(componenteGastoDTO);
	}

	@Override
	public ComponenteGestionUsuarioDTO guardarComponenteGestionusuario(
			ComponenteGestionUsuarioDTO componenteGestionUsuarioDTO) throws SpddExceptions {
		return sessionRegistroAFS.guardarComponenteGestionusuario(componenteGestionUsuarioDTO);
	}

	@Override
	public ConsecutivoDTO guardarConsecutivo(ConsecutivoDTO consecutivoDTO) throws SpddExceptions {
		return sessionRegistroAFS.guardarConsecutivo(consecutivoDTO);
	}

	@Override
	public EntidadDTO guardarEntidad(EntidadDTO entidadDTO) throws SpddExceptions {
		return sessionRegistroAFS.guardarEntidad(entidadDTO);
	}

	@Override
	public EquipamientoDTO guardarEquipamiento(EquipamientoDTO equipamientoDTO) throws SpddExceptions {

		EquipamientoDTO respuesta = sessionConsultaAFS.buscarSectorYCategoria(equipamientoDTO.getIdLsSector(),
				equipamientoDTO.getIdLsCategoria());
		return respuesta.getIdEquipamento() == null ? sessionRegistroAFS.guardarEquipamiento(equipamientoDTO)
				: new EquipamientoDTO();

	}

	@Override
	public EquipamientoDTO modificarEquipamiento(EquipamientoDTO equipamientoDTO) throws SpddExceptions {
		return sessionRegistroAFS.guardarEquipamiento(equipamientoDTO);
	}

	@Override
	public EstadoServicioDTO guardarEstadoServicio(EstadoServicioDTO estadoServicioDTO) throws SpddExceptions {
		return sessionRegistroAFS.guardarEstadoServicio(estadoServicioDTO);
	}

	@Override
	public EstructuraMetaDTO guardarEstructuraMeta(EstructuraMetaDTO estructuraMetaDTO) throws SpddExceptions {
		EstructuraMetaDTO respuesta = sessionConsultaAFS
				.buscarUnidadMedidaYVerbo(estructuraMetaDTO.getIdLsUnidadMedida(), estructuraMetaDTO.getIdLsVerbo());

		return respuesta.getIdEstructuraMetas() == null ? sessionRegistroAFS.guardarEstructuraMeta(estructuraMetaDTO)
				: new EstructuraMetaDTO();

	}

	@Override
	public EstructuraMetaDTO modificarEstructuraMeta(EstructuraMetaDTO estructuraMetaDTO) throws SpddExceptions {
		return sessionRegistroAFS.guardarEstructuraMeta(estructuraMetaDTO);
	}

	@Override
	public FuncionarioClaveEntidadDTO guardarFuncionarioClaveEntidad(
			FuncionarioClaveEntidadDTO funcionarioClaveEntidadDTO) throws SpddExceptions {
		return sessionRegistroAFS.guardarFuncionarioClaveEntidad(funcionarioClaveEntidadDTO);
	}

	@Override
	public InformacionPresupuestalDTO guardarInformacionPresupuestal(
			InformacionPresupuestalDTO informacionPresupuestalDTO) throws SpddExceptions {
		return sessionRegistroAFS.guardarInformacionPresupuestal(informacionPresupuestalDTO);
	}

	@Override
	public LineaDeInversionDTO guardarLineaDeInversion(LineaDeInversionDTO lineaDeInversionDTO) throws SpddExceptions {

		LineaDeInversionDTO respuesta;
		respuesta = sessionConsultaAFS.buscarLineaInversionPorConceptoYSector(lineaDeInversionDTO.getConcepto(),
				lineaDeInversionDTO.getIdLsSector());

		return respuesta.getIdLineaInversion() == null ? sessionRegistroAFS.guardarLineaDeInversion(lineaDeInversionDTO)
				: new LineaDeInversionDTO();

	}

	@Override
	public LineaDeInversionDTO modificarLineaDeInversion(LineaDeInversionDTO lineaDeInversionDTO)
			throws SpddExceptions {
		return sessionRegistroAFS.guardarLineaDeInversion(lineaDeInversionDTO);
	}

	@Override
	public ListaCompuestaDTO guardarListaCompuesta(ListaCompuestaDTO listaCompuestaDTO) throws SpddExceptions {
		return sessionRegistroAFS.guardarListaCompuesta(listaCompuestaDTO);
	}

	@Override
	public ListaSimpleDTO guardarListaSimple(ListaSimpleDTO listaSimpleDTO) throws SpddExceptions {
		return sessionRegistroAFS.guardarListaSimple(listaSimpleDTO);
	}

	@Override
	public ParametroGeneralDTO guardarParametroGeneral(ParametroGeneralDTO parametroGeneralDTO) throws SpddExceptions {
		return sessionRegistroAFS.guardarParametroGeneral(parametroGeneralDTO);
	}

	@Override
	public PotProyectoInstrumentoDTO guardarPotProyectoInstrumento(PotProyectoInstrumentoDTO potProyectoInstrumentoDTO)
			throws SpddExceptions {
		PotProyectoInstrumentoDTO respuesta = sessionConsultaAFS
				.buscarPorIdLsPotObraYIdLsPotInstrumento(potProyectoInstrumentoDTO);

		return respuesta.getIdProyectoInstrumento() == null
				? sessionRegistroAFS.guardarPotProyectoInstrumento(potProyectoInstrumentoDTO)
				: new PotProyectoInstrumentoDTO();
	}

	@Override
	public ProyectoInversionDTO guardarProyectoInversion(ProyectoInversionDTO proyectoInversionDTO)
			throws SpddExceptions {
		return sessionRegistroAFS.guardarProyectoInversion(proyectoInversionDTO);
	}

	@Override
	public ProyectosInversionUsuarioDTO guardarProyectosInversionUsuario(
			ProyectosInversionUsuarioDTO proyectosInversionUsuarioDTO) throws SpddExceptions {
		return sessionRegistroAFS.guardarProyectosInversionUsuario(proyectosInversionUsuarioDTO);
	}

	@Override
	public TerritorializacionDTO guardarTerritorializacion(TerritorializacionDTO territorializacionDTO)
			throws SpddExceptions {

		TerritorializacionDTO respuesta;
		if (territorializacionDTO.getIdLsUpr() != null) {
			respuesta = sessionConsultaAFS.buscarPorLsVeredaYLsUpr(territorializacionDTO);
			territorializacionDTO.setIdLsBarrio(-1L);
			territorializacionDTO.setIdLsUpz(-1L);
		} else {
			respuesta = sessionConsultaAFS.buscarPorLsBarrioYLsUpzYLsLocalidad(territorializacionDTO);
			territorializacionDTO.setIdLsUpr(-1L);
			territorializacionDTO.setIdLsVereda(-1L);

		}

		return respuesta.getIdTerritorializacion() == null
				? sessionRegistroAFS.guardarTerritorializacion(territorializacionDTO)
				: new TerritorializacionDTO();
	}

	@Override
	public TerritorializacionDTO modificarTerritorializacion(TerritorializacionDTO territorializacionDTO)
			throws SpddExceptions {
		return sessionRegistroAFS.guardarTerritorializacion(territorializacionDTO);
	}

	@Override
	public UsuarioEntidadDTO guardarUsuarioEntidad(UsuarioEntidadDTO usuarioEntidadDTO) throws SpddExceptions {
		return sessionRegistroAFS.guardarUsuarioEntidad(usuarioEntidadDTO);
	}

	@Override
	public void eliminarComponenteGestionUsuario(Long id) throws SpddExceptions {
		sessionEliminarAFS.eliminarComponenteGestionUsuario(id);
	}

	@Override
	public void eliminarInformacionPresupuestal(Long id) throws SpddExceptions {
		sessionEliminarAFS.eliminarInformacionPresupuestal(id);
	}

	@Override
	public void eliminarProyectoInversionUsuario(Long id) throws SpddExceptions {
		sessionEliminarAFS.eliminarProyectoInversionUsuario(id);
	}

	@Override
	public PotProyectoInstrumentoDTO modificarProyectoInstrumento(PotProyectoInstrumentoDTO peticion)
			throws SpddExceptions {
		return sessionModificarAFS.modificarProyectoInstrumento(peticion);
	}

	@Override
	public int guardarSql(String strQuery) throws SpddExceptions, DataAccessException {
		return sessionRegistroAFS.guardarSql(strQuery);
	}

	@Override
	public int buscarSql(String strQuery) throws SpddExceptions, DataAccessException {
		return sessionConsultaAFS.buscarSql(strQuery);
	}

	@Override
	public GenericoDTO obtenerArchivoInfoPresupuestal(Long id) {

		return sessionConsultaAFS.consultarArchivoInfoPrespuestal(id);
	}

	@Override
	public GenericoDTO buscarPorSectorLinea(String sector) throws SpddExceptions {
		return sessionConsultaAFS.buscarPorSector(sector);
	}

	@Override
	public GenericoDTO buscarPorLocalidadTerritorializacion() throws SpddExceptions {
		return sessionConsultaAFS.buscarTerrPorLocalida();
	}

	@Override
	public ArgumentoListaSimpleDTO consultarArgumentoListaSimplePorResultadoYNombreListaSimple(String resultado, String nombreListaSimple)
			throws SpddExceptions {
		return sessionConsultaAFS.consultarArgumentoListaSimplePorResultadoYNombreListaSimple(resultado, nombreListaSimple);
	}

	@Override
	public FuncionarioClaveEntidadDTO validarFuncionarioPorEntidadYFuncion(String codigoEntidad, Long idLsFuncion)
			throws SpddExceptions {
		return sessionConsultaAFS.validarFuncionarioPorEntidadYFuncion(codigoEntidad, idLsFuncion);
	}

	@Override
	public List<BuzonNotificacionesDTO> leerInformativos(String usuario) throws SpddExceptions {
		return sessionConsultaAFS.leerInformativos(usuario);
	}

	@Override
	public ConsecutivoDTO obtenerConsecutivosPorPlanYEntidad(Long idPlan, String entidad, String nombre)
			throws SpddExceptions {
		return sessionConsultaAFS.obtenerConsecutivosPorPlanYEntidad(idPlan, entidad, nombre);
	}

	@Override
	public GenericoDTO obtenerTerritoriosPorIdLocalidad(Long idLocalidad) throws SpddExceptions {
		// TODO Auto-generated method stub
		return sessionConsultaAFS.obtenerTerritoriosPorIdLocalidad(idLocalidad);
	}

	@Override
	public GenericoDTO obtenerLineasInversion() throws SpddExceptions {
		// TODO Auto-generated method stub
		return sessionConsultaAFS.obtenerLineasDeInversion();
	}

}
