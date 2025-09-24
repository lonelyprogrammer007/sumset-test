package co.gov.sdp.spdd.data.sesionfacade.interfaces;

import java.util.List;

import org.springframework.dao.DataAccessException;

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

/**
 * @author Sumset
 *
 */
public interface ISessionFacadeAFS {

	/* CONSULTAS */

	/**
	 * 
	 * @param idPotInstrumento
	 * @param idPotProyecto
	 * @return
	 * @throws SpddExceptions
	 */
	public PotProyectoInstrumentoDTO buscarPorIdLsPotObraYIdLsPotInstrumento(
			PotProyectoInstrumentoDTO potProyectoInstrumentoDTO) throws SpddExceptions;

	public GenericoDTO obtenerArchivoInfoPresupuestal(Long id);

	/**
	 * 
	 * @param idListaSimple
	 * @return
	 * @throws SpddException
	 */
	public ArgumentoListaSimpleDTO buscarIdListaSimpleArgumento(ArgumentoListaSimpleDTO peticion) throws SpddExceptions;

	/**
	 * 
	 * @param configuracionCargueArchivoDTO
	 * @return
	 * @throws SpddExceptions
	 */
	public ConfiguracionCargueArchivoDTO buscarIdLsModuloYIdLsTema(
			ConfiguracionCargueArchivoDTO configuracionCargueArchivoDTO) throws SpddExceptions;

	/**
	 * 
	 * @param territorializacionDTO
	 * @return
	 * @throws SpddExceptions
	 */
	public TerritorializacionDTO buscarPorLsVeredaYLsUpr(TerritorializacionDTO territorializacionDTO)
			throws SpddExceptions;

	/**
	 * 
	 * @param territorializacionDTO
	 * @return
	 * @throws SpddExceptions
	 */
	public TerritorializacionDTO buscarPorLsBarrioYLsUpzYLsLocalidad(TerritorializacionDTO territorializacionDTO)
			throws SpddExceptions;

	/**
	 * 
	 * @param sector
	 * @param categoria
	 * @return
	 * @throws SpddExceptions
	 */
	public EquipamientoDTO buscarSectorYCategoria(Long sector, Long categoria) throws SpddExceptions;

	/**
	 * 
	 * @param concepto
	 * @param sector
	 * @return
	 * @throws SpddExceptions
	 */
	public LineaDeInversionDTO buscarLineaInversionPorConceptoYSector(String concepto, Long sector)
			throws SpddExceptions;

	/**
	 * 
	 * @param unidadMedida
	 * @param verbo
	 * @return
	 * @throws SpddExceptions
	 */
	public EstructuraMetaDTO buscarUnidadMedidaYVerbo(Long unidadMedida, Long verbo) throws SpddExceptions;

	/**
	 * 
	 * @param codigo
	 * @param nombre
	 * @return
	 * @throws SpddExceptions
	 */
	public ComponenteGastoDTO buscarCodigoYNombre(Long codigo, String nombre) throws SpddExceptions;

	/**
	 * 
	 * @param id
	 * @return
	 * @throws SpddExceptions
	 */
	public ArchivoProcesadoDTO consultarArchivoProcesadoPorId(Long id) throws SpddExceptions;

	/**
	 * 
	 * @param archivoProcesadoDTO
	 * @param inicio
	 * @param limite
	 * @return
	 * @throws SpddExceptions
	 */
	public GenericoDTO consultarArchivoProcesadoPorFiltro(ArchivoProcesadoDTO archivoProcesadoDTO)
			throws SpddExceptions, DataAccessException;

	/**
	 * 
	 * @param id
	 * @return
	 * @throws SpddExceptions
	 */
	public ConfiguracionCargueArchivoDTO consultarConfigCargueArchivoProcesado(Long id) throws SpddExceptions;

	/**
	 * 
	 * @param id
	 * @return
	 * @throws SpddExceptions
	 */
	public ArgumentoListaSimpleDTO consultarArgumentoListaSimplePorId(Long id) throws SpddExceptions;

	/**
	 * Metodo que permite la comunicacion entre appdata y appcore para consultar o
	 * buscar los ArgumentosListaSimple que representa las tematicas que estan
	 * relacionadas con un plan de desarrollo
	 * 
	 * @param idPlan Long que representa el identificador del plan de desarrollo del
	 *               cual se necesitan las tematicas
	 * @return una lista de ArgumentoListaSimple que representa la lista de
	 *         tematicas correspondiente al plan de desarrollo
	 * @throws SpddExceptions
	 */
	public GenericoDTO consultarArgumentoPorIdPdd(Long idPlan) throws SpddExceptions;

	/**
	 * 
	 * @param id
	 * @return
	 * @throws SpddExceptions
	 */
	public BancoDeProyectoDTO consultarBancoDeProyectoporId(Long id) throws SpddExceptions;

	/**
	 * 
	 * @param id
	 * @return
	 * @throws SpddExceptions
	 */
	public BuzonNotificacionesDTO consultarBuzonNotificacionesPorId(Long id) throws SpddExceptions;

	/**
	 * 
	 * @param id
	 * @return
	 * @throws SpddExceptions
	 */
	public GenericoDTO consultarBuzonNotificacionesPorFiltro(BuzonNotificacionesDTO peticion) throws SpddExceptions;

	/**
	 * 
	 * @param id
	 * @return
	 * @throws SpddExceptions
	 */
	public ComponenteGastoDTO consultarComponenteGastoPorId(Long id) throws SpddExceptions;

	/**
	 * 
	 * @param id
	 * @return
	 * @throws SpddExceptions
	 */
	public ComponenteGestionUsuarioDTO consultarComponenteGestionUsuarioPorId(Long id) throws SpddExceptions;

	/**
	 * 
	 * @param id
	 * @return
	 * @throws SpddExceptions
	 */
	public ConsecutivoDTO consultarConsecutivoPorId(Long id) throws SpddExceptions;

	/**
	 * 
	 * @param peticion
	 * @return
	 * @throws SpddExceptions
	 */
	public GenericoDTO consultarConsecutivoPorFiltro(ConsecutivoDTO peticion) throws SpddExceptions;

	/**
	 * 
	 * @param id
	 * @return
	 * @throws SpddExceptions
	 */
	public EntidadDTO consultarEntidad(String id) throws SpddExceptions;

	/**
	 * 
	 * @param id
	 * @return
	 * @throws SpddExceptions
	 */
	public EquipamientoDTO consultarEquipamiento(Long id) throws SpddExceptions;

	/**
	 * 
	 * @param equipamientoDTO
	 * @return
	 * @throws SpddExceptions
	 */
	public GenericoDTO consultarEquipamientoPorFiltro(EquipamientoDTO equipamientoDTO) throws SpddExceptions;

	/**
	 * 
	 * @param id
	 * @return
	 * @throws SpddExceptions
	 */
	public EstadoServicioDTO consultarEstadoServicio(Long id) throws SpddExceptions;

	/**
	 * 
	 * @return
	 * @throws SpddExceptions
	 */
	public GenericoDTO consultarEstadoServicioPorFiltro(EstadoServicioDTO peticion) throws SpddExceptions;

	/**
	 * 
	 * @param id
	 * @return
	 * @throws SpddExceptions
	 */
	public EstructuraMetaDTO consultarEstructuraMeta(Long id) throws SpddExceptions;

	/**
	 * 
	 * @param estructuraMetaDTO
	 * @return
	 * @throws SpddExceptions
	 */
	public GenericoDTO consultarEstructuraMetaPorFiltro(EstructuraMetaDTO estructuraMetaDTO) throws SpddExceptions;

	/**
	 * 
	 * @param id
	 * @return
	 * @throws SpddExceptions
	 */
	public FuncionarioClaveEntidadDTO consultarFuncionarioClaveEntidad(Long id) throws SpddExceptions;

	/**
	 * 
	 * @param peticion
	 * @return
	 * @throws SpddExceptions
	 */
	public GenericoDTO consultarFuncionarioClaveEntidadPorFiltro(FuncionarioClaveEntidadDTO peticion)
			throws SpddExceptions;

	/**
	 * 
	 * @param id
	 * @return
	 * @throws SpddExceptions
	 */
	public InformacionPresupuestalDTO consultarInformacionPresupuestalPorId(Long id) throws SpddExceptions;

	/**
	 * 
	 * @param id
	 * @return
	 * @throws SpddExceptions
	 */
	public GenericoDTO consultarInformacionPresupuestalPorFiltro(InformacionPresupuestalDTO peticion)
			throws SpddExceptions;

	/**
	 * 
	 * @param id
	 * @return
	 * @throws SpddExceptions
	 */
	public LineaDeInversionDTO consultarLineaDeInversionPorId(Long id) throws SpddExceptions;

	/**
	 * 
	 * @param peticion
	 * @return
	 * @throws SpddExceptions
	 */
	public GenericoDTO consultarLineaDeInversionPorFiltro(LineaDeInversionDTO peticion) throws SpddExceptions;

	/**
	 * 
	 * @param id
	 * @return
	 * @throws SpddExceptions
	 */
	public ListaCompuestaDTO consultarListaCompuestaPorId(Long id) throws SpddExceptions;

	/**
	 * 
	 * @param peticion
	 * @return
	 * @throws SpddExceptions
	 */
	public GenericoDTO consultarListaCompuestaPorFiltro(ListaCompuestaDTO peticion) throws SpddExceptions;

	/**
	 * 
	 * @param id
	 * @return
	 * @throws SpddExceptions
	 */
	public ListaSimpleDTO consultarListaSimplePorID(Long id) throws SpddExceptions;

	/**
	 * 
	 * @param id
	 * @return
	 * @throws SpddExceptions
	 */
	public ParametroGeneralDTO consultarParametroGeneralPorId(String id) throws SpddExceptions;

	/**
	 * 
	 * @param id
	 * @return
	 * @throws SpddExceptions
	 */
	public PotProyectoInstrumentoDTO consultarPotProyectoInstrumentoPorId(Long id) throws SpddExceptions;

	/**
	 * 
	 * @return
	 * @throws SpddExceptions
	 */
	public GenericoDTO consultarPotProyectoInstrumentoPorFiltro(PotProyectoInstrumentoDTO peticion)
			throws SpddExceptions;

	/**
	 * 
	 * @return
	 * @throws SpddExceptions
	 */
	public GenericoDTO consultarPotInstrumento() throws SpddExceptions;

	/**
	 * 
	 * @return
	 * @throws SpddExceptions
	 */
	public GenericoDTO consultarPotObra() throws SpddExceptions;

	/**
	 * 
	 * @return
	 * @throws SpddExceptions
	 */
	public GenericoDTO consultarPdd() throws SpddExceptions;

	/**
	 * 
	 * @param id
	 * @return
	 * @throws SpddExceptions
	 */
	public ProyectoInversionDTO consultarProyectoInversionPorId(Long id) throws SpddExceptions;

	/**
	 * 
	 * @param id
	 * @return
	 * @throws SpddExceptions
	 */
	public List<ProyectosInversionUsuarioDTO> consultarProyectosInversionUsuarioPorId(String usuario)
			throws SpddExceptions;

	/**
	 * 
	 * @param id
	 * @return
	 * @throws SpddExceptions
	 */
	public TerritorializacionDTO consultarTerritorializacionPorId(Long id) throws SpddExceptions;

	/**
	 * 
	 * @param territorializacionDTO
	 * @return
	 * @throws SpddExceptions
	 */
	public TerritorializacionDTO consultarTerritorializacionPorLsVeredaYLsUpr(
			TerritorializacionDTO territorializacionDTO) throws SpddExceptions;

	/**
	 * 
	 * @param territorializacionDTO
	 * @return
	 * @throws SpddExceptions
	 */
	public TerritorializacionDTO consultarTerritorializacionPorLsBarrioYLsUpzYLsLocalidad(
			TerritorializacionDTO territorializacionDTO) throws SpddExceptions;

	/**
	 * 
	 * @param id
	 * @return
	 * @throws SpddExceptions
	 */
	public UsuarioEntidadDTO consultarUsuarioEntidadPorId(Long id) throws SpddExceptions;

	/**
	 * 
	 * @return
	 * @throws SpddExceptions
	 */
	public GenericoDTO consultarArchivoProcesadoTodos() throws SpddExceptions;

	/**
	 * 
	 * @return
	 * @throws SpddExceptions
	 */
	public GenericoDTO consultarArgumentoListaSimpleTodos() throws SpddExceptions;

	/**
	 * 
	 * @param peticion
	 * @return
	 * @throws SpddExceptions
	 */
	public GenericoDTO consultarArgumentoListaSimplePorFiltro(ArgumentoListaSimpleDTO peticion) throws SpddExceptions;

	/**
	 * 
	 * @return
	 * @throws SpddExceptions
	 */
	public GenericoDTO consultarBancoDeProyectosTodos() throws SpddExceptions;

	/**
	 * 
	 * @return
	 * @throws SpddExceptions
	 */
	public GenericoDTO consultarBuzonNotificacionesTodos() throws SpddExceptions;

	/**
	 * 
	 * @return
	 * @throws SpddExceptions
	 */
	public GenericoDTO consultarComponenteGastoTodos() throws SpddExceptions;

	/**
	 * 
	 * @param peticion
	 * @return
	 * @throws SpddExceptions
	 */
	public GenericoDTO consultarComponenteGastoPorFiltro(ComponenteGastoDTO peticion) throws SpddExceptions;

	/**
	 * 
	 * @return
	 * @throws SpddExceptions
	 */
	public GenericoDTO consultarComponenteGestionUsuarioTodos() throws SpddExceptions;

	/**
	 * 
	 * @return
	 * @throws SpddExceptions
	 */
	public GenericoDTO consultarConsecutivoTodos() throws SpddExceptions;

	/**
	 * 
	 * @return
	 * @throws SpddExceptions
	 */
	public GenericoDTO consultarEntidadTodos() throws SpddExceptions;

	/**
	 * 
	 * @return
	 * @throws SpddExceptions
	 */
	public GenericoDTO consultarEquipamientoTodos() throws SpddExceptions;

	/**
	 * 
	 * @return
	 * @throws SpddExceptions
	 */
	public GenericoDTO consultarEstadoServicioTodos() throws SpddExceptions;

	/**
	 * 
	 * @return
	 * @throws SpddExceptions
	 */
	public GenericoDTO consultarEstructuraMetaTodos() throws SpddExceptions;

	/**
	 * 
	 * @return
	 * @throws SpddExceptions
	 */
	public GenericoDTO consultarFuncionarioClaveEntidadTodos() throws SpddExceptions;

	/**
	 * 
	 * @return
	 * @throws SpddExceptions
	 */
	public GenericoDTO consultarInformacionPresupuestalTodos() throws SpddExceptions;

	/**
	 * 
	 * @return
	 * @throws SpddExceptions
	 */
	public GenericoDTO consultarLineaDeInversionTodos() throws SpddExceptions;

	/**
	 * 
	 * @return
	 * @throws SpddExceptions
	 */
	public GenericoDTO consultarListaCompuestaTodos() throws SpddExceptions;

	/**
	 * 
	 * @return
	 * @throws SpddExceptions
	 */
	public GenericoDTO consultarListaSimpleTodos() throws SpddExceptions;

	/**
	 * 
	 * @param peticion
	 * @return
	 * @throws SpddExceptions
	 */
	public GenericoDTO consultarListaSimplePorFiltro(ListaSimpleDTO peticion) throws SpddExceptions;

	/**
	 * 
	 * @return
	 * @throws SpddExceptions
	 */
	public GenericoDTO consultarParametroGeneralTodos() throws SpddExceptions;

	/**
	 * 
	 * @param peticion
	 * @return
	 * @throws SpddExceptions
	 */
	public GenericoDTO consultarParametroGeneralPorFiltro(ParametroGeneralDTO peticion) throws SpddExceptions;

	/**
	 * 
	 * @return
	 * @throws SpddExceptions
	 */
	public GenericoDTO consultarPotProyectoInstrumentoTodos() throws SpddExceptions;

	/**
	 * 
	 * @return
	 * @throws SpddExceptions
	 */
	public GenericoDTO consultarProyectoInversionTodos() throws SpddExceptions;

	/**
	 * 
	 * @return
	 * @throws SpddExceptions
	 */
	public GenericoDTO consultarProyectoInversionUsuarioTodos() throws SpddExceptions;

	/**
	 * 
	 * @return
	 * @throws SpddExceptions
	 */
	public GenericoDTO consultarTerritorializacionTodos() throws SpddExceptions;

	/**
	 * 
	 * @param peticion
	 * @return
	 * @throws SpddExceptions
	 */
	public GenericoDTO consultarTerritorializacionPorFiltro(TerritorializacionDTO peticion) throws SpddExceptions;

	/**
	 * 
	 * @return
	 * @throws SpddExceptions
	 */
	public GenericoDTO consultarUsuarioEntidadTodos() throws SpddExceptions;

	/**
	 * 
	 * @param peticion
	 * @return
	 * @throws SpddExceptions
	 */
	public GenericoDTO consultarUsuarioEntidadPorFiltro(UsuarioEntidadDTO peticion) throws SpddExceptions;

	/**
	 * 
	 * @param peticion
	 * @return
	 * @throws SpddExceptions
	 */
	public GenericoDTO consultarUsuariosClavePorFiltro(FuncionarioClaveEntidadDTO peticion) throws SpddExceptions;

	/**
	 * 
	 * @param peticion
	 * @return
	 * @throws SpddExceptions
	 */
	public GenericoDTO consultarConfiguracionNotificacionPorFiltro(ConfiguracionNotificacionDTO peticion)
			throws SpddExceptions;

	/* Consultas personalizadas */

	/**
	 * 
	 * @param buzonNotificacionesDTO
	 * @return
	 * @throws SpddExceptions
	 */
	public Long notificacionesPorUsuario(BuzonNotificacionesDTO buzonNotificacionesDTO) throws SpddExceptions;

	/**
	 * 
	 * @param id
	 * @return
	 * @throws SpddExceptions
	 */
	public GenericoDTO obtenerArgumentoPorLista(Long id) throws SpddExceptions;

	/**
	 * 
	 * @param nombre
	 * @return
	 * @throws SpddExceptions
	 */
	public GenericoDTO obtenerArgumentoPorNombre(String nombre) throws SpddExceptions;

	/**
	 * 
	 * @param codigoEntidad
	 * @return
	 * @throws SpddExceptions
	 */
	public GenericoDTO obtenerFuncionarioPorEntidad(String codigoEntidad) throws SpddExceptions;

	/* CONSULTAS PERSONALIZADAS */

	/**
	 * 
	 * @param usuario
	 * @return
	 * @throws SpddExceptions
	 */
	public GenericoDTO obtenerBuzonPorUsuario(String usuario) throws SpddExceptions;

	/**
	 * 
	 * @return
	 * @throws SpddExceptions
	 */
	public List<ProyectoInversion> proyectoInversionObtenerDisponibles() throws SpddExceptions;

	/**
	 * 
	 * @return
	 * @throws SpddExceptions
	 */
	public GenericoDTO pddNivelAtributoObtenerLibres() throws SpddExceptions;

	/* REGISTRO */

	/**
	 * 
	 * @param archivoProcesadoDTO
	 * @return
	 * @throws SpddExceptions
	 */
	public ArchivoProcesadoDTO guardarArchivoProcesado(ArchivoProcesadoDTO archivoProcesadoDTO) throws SpddExceptions;

	/**
	 * 
	 * @param argumentoListaSimple
	 * @return
	 * @throws SpddExceptions
	 */
	public ArgumentoListaSimpleDTO guardarArgumentoListaSimple(ArgumentoListaSimpleDTO argumentoListaSimpleDTO)
			throws SpddExceptions;

	/**
	 * 
	 * @param bancoDeProyectoDTO
	 * @return
	 * @throws SpddExceptions
	 */
	public BancoDeProyectoDTO guardarBancoDeProyecto(BancoDeProyectoDTO bancoDeProyectoDTO) throws SpddExceptions;

	/**
	 * 
	 * @param buzonNotificacionesDTO
	 * @return
	 * @throws SpddExceptions
	 */
	public BuzonNotificacionesDTO guardarBuzonNotificaciones(BuzonNotificacionesDTO buzonNotificacionesDTO)
			throws SpddExceptions;

	/**
	 * 
	 * @param componenteGastoDTO
	 * @return
	 * @throws SpddExceptions
	 */
	public ComponenteGastoDTO guardarComponenteGasto(ComponenteGastoDTO componenteGastoDTO) throws SpddExceptions;

	/**
	 * 
	 * @param componenteGestionUsuarioDTO
	 * @return
	 * @throws SpddExceptions
	 */
	public ComponenteGestionUsuarioDTO guardarComponenteGestionusuario(
			ComponenteGestionUsuarioDTO componenteGestionUsuarioDTO) throws SpddExceptions;

	/**
	 * 
	 * @param consecutivoDTO
	 * @return
	 * @throws SpddExceptions
	 */
	public ConsecutivoDTO guardarConsecutivo(ConsecutivoDTO consecutivoDTO) throws SpddExceptions;

	/**
	 * 
	 * @param entidadDTO
	 * @return
	 * @throws SpddExceptions
	 */
	public EntidadDTO guardarEntidad(EntidadDTO entidadDTO) throws SpddExceptions;

	/**
	 * 
	 * @param equipamientoDTO
	 * @return
	 * @throws SpddExceptions
	 */
	public EquipamientoDTO guardarEquipamiento(EquipamientoDTO equipamientoDTO) throws SpddExceptions;

	/**
	 * 
	 * @param estadoServicioDTO
	 * @return
	 * @throws SpddExceptions
	 */
	public EstadoServicioDTO guardarEstadoServicio(EstadoServicioDTO estadoServicioDTO) throws SpddExceptions;

	/**
	 * 
	 * @param estructuraMetaDTO
	 * @return
	 * @throws SpddExceptions
	 */
	public EstructuraMetaDTO guardarEstructuraMeta(EstructuraMetaDTO estructuraMetaDTO) throws SpddExceptions;

	/**
	 * 
	 * @param funcionarioClaveEntidadDTO
	 * @return
	 * @throws SpddExceptions
	 */
	public FuncionarioClaveEntidadDTO guardarFuncionarioClaveEntidad(
			FuncionarioClaveEntidadDTO funcionarioClaveEntidadDTO) throws SpddExceptions;

	/**
	 * 
	 * @param informacionPresupuestalDTO
	 * @return
	 * @throws SpddExceptions
	 */
	public InformacionPresupuestalDTO guardarInformacionPresupuestal(
			InformacionPresupuestalDTO informacionPresupuestalDTO) throws SpddExceptions;

	/**
	 * 
	 * @param lineaDeInversionDTO
	 * @return
	 * @throws SpddExceptions
	 */
	public LineaDeInversionDTO guardarLineaDeInversion(LineaDeInversionDTO lineaDeInversionDTO) throws SpddExceptions;

	/**
	 * 
	 * @param listaCompuestaDTO
	 * @return
	 * @throws SpddExceptions
	 */
	public ListaCompuestaDTO guardarListaCompuesta(ListaCompuestaDTO listaCompuestaDTO) throws SpddExceptions;

	/**
	 * 
	 * @param listaSimpleDTO
	 * @return
	 * @throws SpddExceptions
	 */
	public ListaSimpleDTO guardarListaSimple(ListaSimpleDTO listaSimpleDTO) throws SpddExceptions;

	/**
	 * 
	 * @param parametroGeneralDTO
	 * @return
	 * @throws SpddExceptions
	 */
	public ParametroGeneralDTO guardarParametroGeneral(ParametroGeneralDTO parametroGeneralDTO) throws SpddExceptions;

	/**
	 * 
	 * @param potProyectoInstrumentoDTO
	 * @return
	 * @throws SpddExceptions
	 */
	public PotProyectoInstrumentoDTO guardarPotProyectoInstrumento(PotProyectoInstrumentoDTO potProyectoInstrumentoDTO)
			throws SpddExceptions;

	/**
	 * 
	 * @param proyectoInversionDTO
	 * @return
	 * @throws SpddExceptions
	 */
	public ProyectoInversionDTO guardarProyectoInversion(ProyectoInversionDTO proyectoInversionDTO)
			throws SpddExceptions;

	/**
	 * 
	 * @param proyectosInversionUsuarioDTO
	 * @return
	 * @throws SpddExceptions
	 */
	public ProyectosInversionUsuarioDTO guardarProyectosInversionUsuario(
			ProyectosInversionUsuarioDTO proyectosInversionUsuarioDTO) throws SpddExceptions;

	/**
	 * 
	 * @param territorializacionDTO
	 * @return
	 * @throws SpddExceptions
	 */
	public TerritorializacionDTO guardarTerritorializacion(TerritorializacionDTO territorializacionDTO)
			throws SpddExceptions;

	/**
	 * 
	 * @param usuarioEntidadDTO
	 * @return
	 * @throws SpddExceptions
	 */
	public UsuarioEntidadDTO guardarUsuarioEntidad(UsuarioEntidadDTO usuarioEntidadDTO) throws SpddExceptions;

	/* ELIMINAR */

	/**
	 * 
	 * @param id
	 * @throws SpddExceptions
	 */
	public void eliminarComponenteGestionUsuario(Long id) throws SpddExceptions;

	/**
	 * 
	 * @param id
	 * @throws SpddExceptions
	 */
	public void eliminarInformacionPresupuestal(Long id) throws SpddExceptions;

	/**
	 * 
	 * @param id
	 * @throws SpddExceptions
	 */
	public void eliminarProyectoInversionUsuario(Long id) throws SpddExceptions;

	/**
	 * 
	 * @param potProyectoInstrumentoDTO
	 * @return
	 * @throws SpddExceptions
	 */
	public PotProyectoInstrumentoDTO modificarProyectoInstrumento(PotProyectoInstrumentoDTO potProyectoInstrumentoDTO)
			throws SpddExceptions;

	/**
	 * 
	 * @param territorializacionDTO
	 * @return
	 * @throws SpddExceptions
	 */
	public TerritorializacionDTO modificarTerritorializacion(TerritorializacionDTO territorializacionDTO)
			throws SpddExceptions;

	/**
	 * 
	 * @param equipamientoDTO
	 * @return
	 * @throws SpddExceptions
	 */
	public EquipamientoDTO modificarEquipamiento(EquipamientoDTO equipamientoDTO) throws SpddExceptions;

	/**
	 * 
	 * @param componenteGastoDTO
	 * @return
	 * @throws SpddExceptions
	 */
	public ComponenteGastoDTO modificarComponenteGasto(ComponenteGastoDTO componenteGastoDTO) throws SpddExceptions;

	/**
	 * 
	 * @param strQuery
	 * @return
	 * @throws SpddExceptions
	 * @throws DataAccessException
	 */
	public int guardarSql(String strQuery) throws SpddExceptions, DataAccessException;

	/**
	 * 
	 * @param strQuery
	 * @return
	 * @throws SpddExceptions
	 * @throws DataAccessException
	 */
	public int buscarSql(String strQuery) throws SpddExceptions, DataAccessException;

	/**
	 * 
	 * @param lineaDeInversionDTO
	 * @return
	 * @throws SpddExceptions
	 */
	public LineaDeInversionDTO modificarLineaDeInversion(LineaDeInversionDTO lineaDeInversionDTO) throws SpddExceptions;

	/**
	 * 
	 * @param estructuraMetaDTO
	 * @return
	 * @throws SpddExceptions
	 */
	public EstructuraMetaDTO modificarEstructuraMeta(EstructuraMetaDTO estructuraMetaDTO) throws SpddExceptions;

	/**
	 * 
	 * @param sector
	 * @return
	 * @throws SpddExceptions
	 */
	public GenericoDTO buscarPorSectorLinea(String sector) throws SpddExceptions;

	/**
	 * 
	 * @param localidad
	 * @return
	 * @throws SpddExceptions
	 */
	public GenericoDTO buscarPorLocalidadTerritorializacion() throws SpddExceptions;

	/**
	 * Metodo que permite la comunicacion entre appdata y appcore para obtener el
	 * argumento de lista simple por el campo de resultado
	 * 
	 * @param resultado string que corresponde al valor por el cual se piensa buscar
	 * @return un objeto de tipo ArgumentoistaSimple con la informacion
	 *         correspondiente
	 */
	public ArgumentoListaSimpleDTO consultarArgumentoListaSimplePorResultadoYNombreListaSimple(String resultado, String nombreListaSimple)
			throws SpddExceptions;

	/**
	 * 
	 * @param codigoEntidad
	 * @param idLsFuncion
	 * @return
	 * @throws SpddExceptions
	 */
	public FuncionarioClaveEntidadDTO validarFuncionarioPorEntidadYFuncion(String codigoEntidad, Long idLsFuncion)
			throws SpddExceptions;

	/**
	 * 
	 * @param usuario
	 * @return
	 * @throws SpddExceptions
	 */
	public List<BuzonNotificacionesDTO> leerInformativos(String usuario) throws SpddExceptions;

	public ConsecutivoDTO obtenerConsecutivosPorPlanYEntidad(Long idPlan, String entidad, String nombre)
			throws SpddExceptions;
	
	public GenericoDTO obtenerTerritoriosPorIdLocalidad(Long idLocalidad) throws SpddExceptions;
	
	public GenericoDTO obtenerLineasInversion() throws SpddExceptions;
}
