package co.gov.sdp.spdd.data.serviciofacade.afs;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

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
import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
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
import co.gov.sdp.spdd.data.model.afs.ArchivoProcesado;
import co.gov.sdp.spdd.data.model.afs.ArgumentoListaSimple;
import co.gov.sdp.spdd.data.model.afs.BuzonNotificaciones;
import co.gov.sdp.spdd.data.model.afs.ComponenteGasto;
import co.gov.sdp.spdd.data.model.afs.ConfigCargueArchivo;
import co.gov.sdp.spdd.data.model.afs.ConfiguracionNotificacion;
import co.gov.sdp.spdd.data.model.afs.Consecutivo;
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
import co.gov.sdp.spdd.data.model.afs.Territorializacion;
import co.gov.sdp.spdd.data.model.afs.UsuarioEntidad;
import co.gov.sdp.spdd.data.model.ip.PddNivelAtributo;
import co.gov.sdp.spdd.data.model.ip.PotProyectoInstrumento;

/**
 * @author Sumset
 *
 */
@Component
public class SessionConsultaAFS extends SessionAFS implements Serializable {

	/**
	 * Atributo de serialización.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Método que permite construir un objeto de tipo
	 */
	public SessionConsultaAFS() {
		super();
	}

	/**
	 * 
	 * @param id
	 * @return
	 * @throws SpddExceptions
	 */
	public ArchivoProcesadoDTO consultarArchivoProcesadoPorId(Long id) {
		return archivoProcesadoMapper.ArchivoProcesadoEntityToDTO(archivoProcesadoServiceRepo.obtener(id));
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	public ConfiguracionCargueArchivoDTO consultarConfigCargueArchivoProcesado(Long id) {
		ConfigCargueArchivo auxiliar = configCargueArchivoServiceRepo.obtenerConfigCarguePorArchivoProcesado(id);
		return configCargueArchivoMapper.configuracionCargueEntityToDTO(auxiliar);

	}

	/**
	 * 
	 * @param peticion
	 * @param inicio
	 * @param limite
	 * @return
	 * @throws SpddExceptions
	 */
	public GenericoDTO consultarArchivoProcesadofiltrarPorCampo(ArchivoProcesadoDTO peticion) throws SpddExceptions {
		GenericoDTO respuesta = new GenericoDTO();
		Pageable pageRequest = PageRequest.of(peticion.getPagina(), peticion.getTamanioPagina());
		FiltroDTO filtro = archivoProcesadoServiceRepo.filtrarPorCampo(peticion, pageRequest.getOffset(),
				pageRequest.getPageSize());
		Page<ArchivoProcesado> listaPage = new PageImpl<>((List<ArchivoProcesado>) filtro.getResultadoConsulta(),
				pageRequest, filtro.getContador());
		respuesta.setLstObjectsDTO(
				new ArrayList<>(archivoProcesadoMapper.ArchivoProcesadoEntitiesToDTO(listaPage.getContent())));
		respuesta.setTotal(listaPage.getTotalElements());
		return respuesta;
	}

	/**
	 * 
	 * @param id
	 * @return
	 * @throws SpddExceptions
	 */
	public ArgumentoListaSimpleDTO consultarArgumentoListaSimplePorId(Long id) {
		return argumentoListaSimpleMapper.argumentoListaSimpleEntityToDTO(argumentoListaSimpleServiceRepo.obtener(id));
	}

	/**
	 * Metodo que permite consultar o buscar los ArgumentosListaSimple que
	 * representa las tematicas que estan relacionadas con un plan de desarrollo
	 * 
	 * @param idPlan Long que representa el identificador del plan de desarrollo del
	 *               cual se necesitan las tematicas
	 * @return una lista de ArgumentoListaSimple que representa la lista de
	 *         tematicas correspondiente al plan de desarrollo
	 * @throws SpddExceptions
	 */
	public GenericoDTO consultarArgumentoPorIdPdd(Long idPlan) throws SpddExceptions {
		GenericoDTO genericoDTO = new GenericoDTO();
		List<ArgumentoListaSimpleDTO> tematicasDTO = argumentoListaSimpleMapper
				.argumentoListaSimpleEntitiesToDTO(argumentoListaSimpleServiceRepo.obtenerArgumentoPorIdPdd(idPlan));
		genericoDTO.setLstObjectsDTO(new ArrayList<>(tematicasDTO));
		return genericoDTO;
	}

	/**
	 * 
	 * @param id
	 * @return
	 * @throws SpddExceptions
	 */
	public BancoDeProyectoDTO consultarBancoDeProyectoporId(Long id) {
		return bancoDeProyectoMapper.bancoDeProyectoEntityToDTO(bancoDeProyectoServiceRepo.obtener(id));
	}

	/**
	 * 
	 * @param id
	 * @return
	 * @throws SpddExceptions
	 */
	public BuzonNotificacionesDTO consultarBuzonNotificacionesPorId(Long id) {
		return buzonNotificacionesMapper.buzonNotificacionesEntityToDTO(buzonNotificacioneServiceRepo.obtener(id));
	}

	/**
	 * 
	 * @param peticion
	 * @return
	 * @throws SpddExceptions
	 */
	public GenericoDTO consultarBuzonNotificacionesPorFiltro(BuzonNotificacionesDTO peticion) throws SpddExceptions {
		GenericoDTO respuesta = new GenericoDTO();
		Pageable pageRequest = PageRequest.of(peticion.getPagina(), peticion.getTamanioPagina());
		FiltroDTO filtro = buzonNotificacioneServiceRepo.filtrarPorCampo(peticion, pageRequest.getOffset(),
				pageRequest.getPageSize());
		Page<BuzonNotificaciones> listaPage = new PageImpl<>((List<BuzonNotificaciones>) filtro.getResultadoConsulta(),
				pageRequest, filtro.getContador());
		respuesta.setLstObjectsDTO(
				new ArrayList<>(buzonNotificacionesMapper.buzonNotificacionesEntitiesToDTO(listaPage.getContent())));
		respuesta.setTotal(listaPage.getTotalElements());
		return respuesta;
	}

	/**
	 * 
	 * @param id
	 * @return
	 * @throws SpddExceptions
	 */
	public ComponenteGastoDTO consultarComponenteGastoPorId(Long id) {
		return componenteGastoMapper.componenteGastoEntityToDTO(componenteGastoServiceRepo.obtener(id));
	}

	/**
	 * 
	 * @param id
	 * @return
	 * @throws SpddExceptions
	 */
	public ComponenteGestionUsuarioDTO consultarComponenteGestionUsuarioPorId(Long id) {
		return componenteGestionUsuarioMapper
				.componenteGestionUsuarioEntityToDTO(componenteGestionUsuarioServiceRepo.obtener(id));
	}

	/**
	 * 
	 * @param id
	 * @return
	 * @throws SpddExceptions
	 */
	public ConsecutivoDTO consultarConsecutivoPorId(Long id) {
		return consecutivoMapper.consecutivoEntityToDTO(consecutivoServiceRepo.obtener(id));
	}

	/**
	 * 
	 * @param archivo
	 * @return
	 */
	public GenericoDTO consultarArchivoInfoPrespuestal(Long archivo) {
		List<InformacionPresupuestalDTO> list = informacionPresupuestalMapper
				.informacionPresupuestalEntitiesToDTO(informacionPresupuestalServiceRepo.obtenerPorArchivo(archivo));
		GenericoDTO respuesta = new GenericoDTO();
		respuesta.setLstObjectsDTO(new ArrayList<Object>(list));
		return respuesta;
	}

	/**
	 * 
	 * @param peticion
	 * @return
	 * @throws SpddExceptions
	 */
	public GenericoDTO consultarConsecutivoPorFiltro(ConsecutivoDTO peticion) throws SpddExceptions {
		GenericoDTO respuesta = new GenericoDTO();
		Pageable pageRequest = PageRequest.of(peticion.getPagina(), peticion.getTamanioPagina());
		FiltroDTO filtro = consecutivoServiceRepo.filtrarPorCampo(peticion, pageRequest.getOffset(),
				pageRequest.getPageSize());
		Page<Consecutivo> listaPage = new PageImpl<>((List<Consecutivo>) filtro.getResultadoConsulta(), pageRequest,
				filtro.getContador());
		respuesta.setLstObjectsDTO(new ArrayList<>(consecutivoMapper.consecutivoEntitiesToDTO(listaPage.getContent())));
		respuesta.setTotal(listaPage.getTotalElements());
		return respuesta;
	}

	/**
	 * 
	 * @param id
	 * @return
	 * @throws SpddExceptions
	 */
	public EntidadDTO consultarEntidad(String id) {
		return entidadMapper.entidadEntityToDTO(entidadServiceRepo.obtener(id));
	}

	/**
	 * 
	 * @param id
	 * @return
	 * @throws SpddExceptions
	 */
	public EquipamientoDTO consultarEquipamiento(Long id) {
		return equipamientoMapper.equipamientoEntityToDTO(equipamientoServiceRepo.obtener(id));
	}

	/**
	 * 
	 * @param id
	 * @return
	 * @throws SpddExceptions
	 */
	public EstadoServicioDTO consultarEstadoServicio(Long id) {
		return estadoServicioMapper.estadoServicioEntityToDTO(estadoServicioServiceRepo.obtener(id));
	}

	/**
	 * 
	 * @param id
	 * @return
	 * @throws SpddExceptions
	 */
	public EstructuraMetaDTO consultarEstructuraMeta(Long id) {
		return estructuraMetaMapper.estructuraMetaEntityToDTO(estructuraMetaServiceRepo.obtener(id));
	}

	/**
	 * 
	 * @param id
	 * @return
	 * @throws SpddExceptions
	 */
	public FuncionarioClaveEntidadDTO consultarFuncionarioClaveEntidad(Long id) {
		return funcionarioClaveEntidadMapper
				.funcionarioClaveEntidadToDTO(funcionarioClaveEntidadServiceRepo.obtener(id));
	}

	/**
	 * 
	 * @param peticion
	 * @return
	 * @throws SpddExceptions
	 */
	public GenericoDTO consultarConfiguracionNotificacionPorFiltro(ConfiguracionNotificacionDTO peticion)
			throws SpddExceptions {
		GenericoDTO respuesta = new GenericoDTO();
		Pageable pageRequest = PageRequest.of(peticion.getPagina(), peticion.getTamanioPagina());
		FiltroDTO filtro = configuracionNotificacionServiceRepo.filtrarPorCampo(peticion, pageRequest.getOffset(),
				pageRequest.getPageSize());
		Page<ConfiguracionNotificacion> listaPage = new PageImpl<>(
				(List<ConfiguracionNotificacion>) filtro.getResultadoConsulta(), pageRequest, filtro.getContador());
		respuesta.setLstObjectsDTO(new ArrayList<>(
				configuracionNotificacionMapper.configuracionNotificacionEntitiesToDTO(listaPage.getContent())));
		respuesta.setTotal(listaPage.getTotalElements());
		return respuesta;
	}

	/**
	 * 
	 * @param id
	 * @return
	 * @throws SpddExceptions
	 */
	public InformacionPresupuestalDTO consultarInformacionPresupuestalPorId(Long id) throws SpddExceptions {
		return informacionPresupuestalMapper
				.informacionPresupuestalEntityToDTO(informacionPresupuestalServiceRepo.obtener(id));
	}

	/**
	 * 
	 * @param peticion
	 * @return
	 * @throws SpddExceptions
	 */
	public GenericoDTO consultarInformacionPresupuestalPorFiltro(InformacionPresupuestalDTO peticion)
			throws SpddExceptions {
		GenericoDTO respuesta = new GenericoDTO();
		Pageable pageRequest = PageRequest.of(peticion.getPagina(), peticion.getTamanioPagina());
		FiltroDTO filtro = informacionPresupuestalServiceRepo.filtrarPorCampo(peticion, pageRequest.getOffset(),
				pageRequest.getPageSize());
		Page<InformacionPresupuestal> listaPage = new PageImpl<>(
				(List<InformacionPresupuestal>) filtro.getResultadoConsulta(), pageRequest, filtro.getContador());
		respuesta.setLstObjectsDTO(new ArrayList<>(
				informacionPresupuestalMapper.informacionPresupuestalEntitiesToDTO(listaPage.getContent())));
		respuesta.setTotal(listaPage.getTotalElements());
		return respuesta;

	}

	/**
	 * 
	 * @param id
	 * @return
	 * @throws SpddExceptions
	 */
	public LineaDeInversionDTO consultarLineaDeInversion(Long id) {
		return lineaDeInversionMapper.lineaDeInversionEntityToDTO(lineaDeInversionServiceRepo.obtener(id));
	}

	/**
	 * 
	 * @param peticion
	 * @return
	 * @throws SpddExceptions
	 */
	public GenericoDTO consultarLineaDeInversionPorFiltro(LineaDeInversionDTO peticion) throws SpddExceptions {
		GenericoDTO respuesta = new GenericoDTO();
		Pageable pageRequest = PageRequest.of(peticion.getPagina(), peticion.getTamanioPagina());
		FiltroDTO filtro = lineaDeInversionServiceRepo.filtrarPorCampo(peticion, pageRequest.getOffset(),
				pageRequest.getPageSize());
		Page<LineaDeInversion> listaPage = new PageImpl<>((List<LineaDeInversion>) filtro.getResultadoConsulta(),
				pageRequest, filtro.getContador());
		respuesta.setLstObjectsDTO(
				new ArrayList<>(lineaDeInversionMapper.lineaDeInversionEntitiesTODTO(listaPage.getContent())));
		respuesta.setTotal(listaPage.getTotalElements());
		return respuesta;
	}

	/**
	 * 
	 * @param id
	 * @return
	 * @throws SpddExceptions
	 */
	public ListaCompuestaDTO consultarListaCompuestaPorId(Long id) {
		return listaCompuestaMapper.listaCompuestaEntityToDTO(listaCompuestaServiceRepo.obtener(id));
	}

	/**
	 * 
	 * @param peticion
	 * @return
	 * @throws SpddExceptions
	 */
	public GenericoDTO consultarListaCompuestaPorFiltro(ListaCompuestaDTO peticion) throws SpddExceptions {
		GenericoDTO respuesta = new GenericoDTO();
		Pageable pageRequest = PageRequest.of(peticion.getPagina(), peticion.getTamanioPagina());
		FiltroDTO filtro = listaCompuestaServiceRepo.filtrarPorCampo(peticion, pageRequest.getOffset(),
				pageRequest.getPageSize());
		Page<ListaCompuesta> listaPage = new PageImpl<>((List<ListaCompuesta>) filtro.getResultadoConsulta(),
				pageRequest, filtro.getContador());
		respuesta.setLstObjectsDTO(
				new ArrayList<>(listaCompuestaMapper.listaCompuestaEntitiesToDTO(listaPage.getContent())));
		respuesta.setTotal(listaPage.getTotalElements());
		return respuesta;
	}

	/**
	 * 
	 * @param id
	 * @return
	 * @throws SpddExceptions
	 */
	public ListaSimpleDTO consultarListaSimplePorID(Long id) {
		return listaSimpleMapper.listaSimpleEntityToDTO(listaSimpleServiceRepo.obtener(id));
	}

	/**
	 * 
	 * @param id
	 * @return
	 * @throws SpddExceptions
	 */
	public ParametroGeneralDTO consultarParametroGeneralPorId(String id) {
		return parametroGeneralMapper.parametroEntityToDTO(parametroGeneralServiceRepo.obtener(id));
	}

	/**
	 * 
	 * @param peticion
	 * @return
	 * @throws SpddExceptions
	 */
	public GenericoDTO consultarParametroGeneralPorFiltro(ParametroGeneralDTO peticion) throws SpddExceptions {
		GenericoDTO respuesta = new GenericoDTO();
		Pageable pageRequest = PageRequest.of(peticion.getPagina(), peticion.getTamanioPagina());
		FiltroDTO filtro = parametroGeneralServiceRepo.filtrarPorCampo(peticion, pageRequest.getOffset(),
				pageRequest.getPageSize());
		Page<ParametroGeneral> listaPage = new PageImpl<>((List<ParametroGeneral>) filtro.getResultadoConsulta(),
				pageRequest, filtro.getContador());
		respuesta.setLstObjectsDTO(
				new ArrayList<>(parametroGeneralMapper.parametroGeneralEntitiesToDTO(listaPage.getContent())));
		respuesta.setTotal(listaPage.getTotalElements());
		return respuesta;
	}

	/**
	 * 
	 * @param id
	 * @return
	 * @throws SpddExceptions
	 */
	public PotProyectoInstrumentoDTO consultarPotProyectoInstrumentoPorId(Long id) {
		return potProyectoInstrumentoMapper
				.potProyectoInstrumentoEntityToDTO(potProyectoInstrumentoServiceRepo.obtener(id));
	}

	/**
	 * 
	 * @param peticion
	 * @return
	 * @throws SpddExceptions
	 */
	public GenericoDTO consultarPotProyectoInstrumentoPorFiltro(PotProyectoInstrumentoDTO peticion)
			throws SpddExceptions {
		GenericoDTO respuesta = new GenericoDTO();
		Pageable pageRequest = PageRequest.of(peticion.getPagina(), peticion.getTamanioPagina());
		FiltroDTO filtro = potProyectoInstrumentoServiceRepo.filtrarPorCampo(peticion, pageRequest.getOffset(),
				pageRequest.getPageSize());
		Page<PotProyectoInstrumento> listaPage = new PageImpl<>(
				(List<PotProyectoInstrumento>) filtro.getResultadoConsulta(), pageRequest, filtro.getContador());
		respuesta.setLstObjectsDTO(new ArrayList<>(
				potProyectoInstrumentoMapper.potProyectoInstrumentoEntitiesToDTO(listaPage.getContent())));
		respuesta.setTotal(listaPage.getTotalElements());
		return respuesta;
	}

	/**
	 * 
	 * @return
	 */
	public GenericoDTO consultarPotInstrumento() {
		GenericoDTO respuesta = new GenericoDTO();
		List<PotInstrumentoDTO> lista = potInstrumentoMapper
				.potInstrumentoEntitiesToDTO(potInstrumentoServiceRepo.obtenerTodos());
		respuesta.setLstObjectsDTO(new ArrayList<>(lista));
		return respuesta;
	}

	/**
	 * 
	 * @return
	 */
	public GenericoDTO consultarPotObra() {
		GenericoDTO respuesta = new GenericoDTO();
		List<PotObraDTO> lista = potObraMapper.potObraEntitiesToDTO(potObraServiceRepo.obtenerTodos());
		respuesta.setLstObjectsDTO(new ArrayList<>(lista));
		return respuesta;
	}

	/**
	 * 
	 * @return
	 * @throws SpddExceptions
	 */
	public GenericoDTO consultarPdd() throws SpddExceptions {
		GenericoDTO respuesta = new GenericoDTO();
		List<PddDTO> lista = pddMapper.pddEntitiesToDTO(pddServiceRepo.obtenerTodosPdd());
		respuesta.setLstObjectsDTO(new ArrayList<>(lista));
		return respuesta;
	}

	/**
	 * 
	 * @param id
	 * @return
	 * @throws SpddExceptions
	 */
	public ProyectoInversionDTO consultarProyectoInversionPorId(Long id) {
		return proyectoInversionMapper.proyectoInversionEntityToDTO(proyectoInversionServiceRepo.obtener(id));
	}

	/**
	 * 
	 * @param id
	 * @return
	 * @throws SpddExceptions
	 */
	public List<ProyectosInversionUsuarioDTO> consultarProyectosInversionUsuarioPorId(String usuario) {
		return proyectosInversionUsuarioMapper.proyectosInversionUsuarioEntitiesToDTO(
				proyectosInversionUsuarioServiceRepo.obtenerPorUsuario(usuario));
	}

	/**
	 * 
	 * @param id
	 * @return
	 * @throws SpddExceptions
	 */
	public TerritorializacionDTO consultarTerritorializacionPorId(Long id) {
		return territorializacionMapper.territorializacionEntityToDTO(territorializacionServiceRepo.obtener(id));
	}

	/**
	 * 
	 * @param peticion
	 * @return
	 * @throws SpddExceptions
	 */
	public GenericoDTO consultarTerritorializacionPorFiltro(TerritorializacionDTO peticion) throws SpddExceptions {
		GenericoDTO respuesta = new GenericoDTO();
		Pageable pageRequest = PageRequest.of(peticion.getPagina(), peticion.getTamanioPagina());
		FiltroDTO filtro = territorializacionServiceRepo.filtrarPorCampo(peticion, pageRequest.getOffset(),
				pageRequest.getPageSize());
		Page<Territorializacion> listaPage = new PageImpl<>((List<Territorializacion>) filtro.getResultadoConsulta(),
				pageRequest, filtro.getContador());
		respuesta.setLstObjectsDTO(
				new ArrayList<>(territorializacionMapper.territorializacionEntitiesToDTO(listaPage.getContent())));
		respuesta.setTotal(listaPage.getTotalElements());
		return respuesta;
	}

	/**
	 * 
	 * @param id
	 * @return
	 * @throws SpddExceptions
	 */
	public UsuarioEntidadDTO consultarUsuarioEntidadPorId(Long id) {
		return usuarioEntidadMapper.usuarioEntidadEntityToDTO(usuarioEntidadServiceRepo.obtener(id));
	}

	/**
	 * 
	 * @param peticion
	 * @return
	 * @throws SpddExceptions
	 */
	public GenericoDTO consultarUsuarioEntidadPorFiltro(UsuarioEntidadDTO peticion) throws SpddExceptions {
		GenericoDTO respuesta = new GenericoDTO();
		Pageable pageRequest = PageRequest.of(peticion.getPagina(), peticion.getTamanioPagina());
		FiltroDTO filtro = usuarioEntidadServiceRepo.filtrarPorCampo(peticion, pageRequest.getOffset(),
				pageRequest.getPageSize());
		Page<UsuarioEntidad> listaPage = new PageImpl<>((List<UsuarioEntidad>) filtro.getResultadoConsulta(),
				pageRequest, filtro.getContador());
		respuesta.setLstObjectsDTO(
				new ArrayList<>(usuarioEntidadMapper.usuarioEntidadDTOEntitiesToDTO(listaPage.getContent())));
		respuesta.setTotal(listaPage.getTotalElements());
		return respuesta;
	}

	/**
	 * 
	 * @return
	 * @throws SpddExceptions
	 */
	public GenericoDTO consultarArchivoProcesadoTodos() throws SpddExceptions {
		GenericoDTO genericoDTO = new GenericoDTO();
		List<ArchivoProcesadoDTO> listaDTO = archivoProcesadoMapper
				.ArchivoProcesadoEntitiesToDTO(archivoProcesadoServiceRepo.obtenerTodos());
		genericoDTO.setLstObjectsDTO(new ArrayList<>(listaDTO));
		return genericoDTO;
	}

	/**
	 * 
	 * @return
	 * @throws SpddExceptions
	 */
	public GenericoDTO consultarArgumentoListaSimpleTodos() throws SpddExceptions {
		GenericoDTO genericoDTO = new GenericoDTO();
		List<ArgumentoListaSimpleDTO> listaDTO = argumentoListaSimpleMapper
				.argumentoListaSimpleEntitiesToDTO(argumentoListaSimpleServiceRepo.obtenerTodos());
		genericoDTO.setLstObjectsDTO(new ArrayList<>(listaDTO));
		return genericoDTO;
	}

	/**
	 * 
	 * @param peticion
	 * @return
	 * @throws SpddExceptions
	 */
	@SuppressWarnings("unchecked")
	public GenericoDTO consultarArgumentosPorFiltro(ArgumentoListaSimpleDTO peticion) throws SpddExceptions {
		GenericoDTO respuesta = new GenericoDTO();
		Pageable pageRequest = PageRequest.of(peticion.getPagina(), peticion.getTamanioPagina(),
				Sort.by("idArgumento"));
		FiltroDTO filtro = argumentoListaSimpleServiceRepo.filtrarPorCampo(peticion, pageRequest.getOffset(),
				pageRequest.getPageSize());
		Page<ArgumentoListaSimple> listaPage = new PageImpl<>(
				(List<ArgumentoListaSimple>) filtro.getResultadoConsulta(), pageRequest, filtro.getContador());
		respuesta.setLstObjectsDTO(
				new ArrayList<>(argumentoListaSimpleMapper.argumentoListaSimpleEntitiesToDTO(listaPage.getContent())));
		respuesta.setTotal(listaPage.getTotalElements());
		return respuesta;
	}

	/**
	 * 
	 * @return
	 * @throws SpddExceptions
	 */
	public GenericoDTO consultarBancoDeProyectosTodos() throws SpddExceptions {
		GenericoDTO genericoDTO = new GenericoDTO();
		List<BancoDeProyectoDTO> listaDTO = bancoDeProyectoMapper
				.bancoDeProyectoEntitiesToDTO(bancoDeProyectoServiceRepo.obtenerTodos());
		genericoDTO.setLstObjectsDTO(new ArrayList<>(listaDTO));
		return genericoDTO;
	}

	/**
	 * 
	 * @return
	 * @throws SpddExceptions
	 */
	public GenericoDTO consultarBuzonNotificacionesTodos() throws SpddExceptions {
		GenericoDTO genericoDTO = new GenericoDTO();
		List<BuzonNotificacionesDTO> listaDTO = buzonNotificacionesMapper
				.buzonNotificacionesEntitiesToDTO(buzonNotificacioneServiceRepo.obtenerTodos());
		genericoDTO.setLstObjectsDTO(new ArrayList<>(listaDTO));
		return genericoDTO;
	}

	/**
	 * 
	 * @return
	 * @throws SpddExceptions
	 */
	public GenericoDTO consultarComponenteGastoTodos() throws SpddExceptions {
		GenericoDTO genericoDTO = new GenericoDTO();
		List<ComponenteGastoDTO> listaDTO = componenteGastoMapper
				.componenteGastoEntitiesToDTO(componenteGastoServiceRepo.obtenerTodos());
		genericoDTO.setLstObjectsDTO(new ArrayList<>(listaDTO));
		return genericoDTO;
	}

	/**
	 * 
	 * @param peticion
	 * @return
	 * @throws SpddExceptions
	 */
	public GenericoDTO consultarPorFiltroComponenteGasto(ComponenteGastoDTO peticion) throws SpddExceptions {
		GenericoDTO respuesta = new GenericoDTO();
		Pageable pageRequest = PageRequest.of(peticion.getPagina(), peticion.getTamanioPagina());
		FiltroDTO filtro = componenteGastoServiceRepo.filtrarPorCampo(peticion, pageRequest.getOffset(),
				pageRequest.getPageSize());
		Page<ComponenteGasto> listaPage = new PageImpl<>((List<ComponenteGasto>) filtro.getResultadoConsulta(),
				pageRequest, filtro.getContador());
		respuesta.setLstObjectsDTO(
				new ArrayList<>(componenteGastoMapper.componenteGastoEntitiesToDTO(listaPage.getContent())));
		respuesta.setTotal(listaPage.getTotalElements());
		return respuesta;

	}

	/**
	 * 
	 * @return
	 * @throws SpddExceptions
	 */
	public GenericoDTO consultarComponenteGestionUsuarioTodos() throws SpddExceptions {
		GenericoDTO genericoDTO = new GenericoDTO();
		List<ComponenteGestionUsuarioDTO> listaDTO = componenteGestionUsuarioMapper
				.componenteGestionUsuarioEntitiesToDTO(componenteGestionUsuarioServiceRepo.obtenerTodos());
		genericoDTO.setLstObjectsDTO(new ArrayList<>(listaDTO));
		return genericoDTO;
	}

	/**
	 * 
	 * @return
	 * @throws SpddExceptions
	 */
	public GenericoDTO consultarConsecutivoTodos() throws SpddExceptions {
		GenericoDTO genericoDTO = new GenericoDTO();
		List<ConsecutivoDTO> listaDTO = consecutivoMapper
				.consecutivoEntitiesToDTO(consecutivoServiceRepo.obtenerTodos());
		genericoDTO.setLstObjectsDTO(new ArrayList<>(listaDTO));
		return genericoDTO;
	}

	/**
	 * 
	 * @return
	 * @throws SpddExceptions
	 */
	public GenericoDTO consultarEntidadTodos() throws SpddExceptions {
		GenericoDTO genericoDTO = new GenericoDTO();
		List<EntidadDTO> listaDTO = entidadMapper.entidadEntitiesToDTO(entidadServiceRepo.obtenerTodos());
		genericoDTO.setLstObjectsDTO(new ArrayList<>(listaDTO));
		return genericoDTO;
	}

	/**
	 * 
	 * @return
	 * @throws SpddExceptions
	 */
	public GenericoDTO consultarEquipamientoTodos() throws SpddExceptions {
		GenericoDTO genericoDTO = new GenericoDTO();
		List<EquipamientoDTO> listaDTO = equipamientoMapper
				.equipamientoEntitiesToDTO(equipamientoServiceRepo.obtenerTodos());
		genericoDTO.setLstObjectsDTO(new ArrayList<>(listaDTO));
		return genericoDTO;
	}

	/**
	 * 
	 * @param peticion
	 * @return
	 * @throws SpddExceptions
	 */
	public GenericoDTO consultarEquipamientoPorFiltro(EquipamientoDTO peticion) throws SpddExceptions {
		GenericoDTO respuesta = new GenericoDTO();
		Pageable pageRequest = PageRequest.of(peticion.getPagina(), peticion.getTamanioPagina());
		FiltroDTO filtro = equipamientoServiceRepo.filtrarPorCampo(peticion, pageRequest.getOffset(),
				pageRequest.getPageSize());
		Page<Equipamento> listaPage = new PageImpl<>((List<Equipamento>) filtro.getResultadoConsulta(), pageRequest,
				filtro.getContador());
		respuesta.setLstObjectsDTO(
				new ArrayList<>(equipamientoMapper.equipamientoEntitiesToDTO(listaPage.getContent())));
		respuesta.setTotal(listaPage.getTotalElements());
		return respuesta;
	}

	/**
	 * 
	 * @return
	 * @throws SpddExceptions
	 */
	public GenericoDTO consultarEstadoServicioTodos() throws SpddExceptions {
		GenericoDTO genericoDTO = new GenericoDTO();
		List<EstadoServicioDTO> listaDTO = estadoServicioMapper
				.estadoServicioEntitiesToDTO(estadoServicioServiceRepo.obtenerTodos());
		genericoDTO.setLstObjectsDTO(new ArrayList<>(listaDTO));
		return genericoDTO;
	}

	/**
	 * 
	 * @param peticion
	 * @return
	 * @throws SpddExceptions
	 */
	public GenericoDTO consultarEstadoServicioPorFiltro(EstadoServicioDTO peticion) throws SpddExceptions {
		GenericoDTO respuesta = new GenericoDTO();
		Pageable pageRequest = PageRequest.of(peticion.getPagina(), peticion.getTamanioPagina());
		FiltroDTO filtro = estadoServicioServiceRepo.filtrarPorCampo(peticion, pageRequest.getOffset(),
				pageRequest.getPageSize());
		Page<EstadoServicio> listaPage = new PageImpl<>((List<EstadoServicio>) filtro.getResultadoConsulta(),
				pageRequest, filtro.getContador());
		respuesta.setLstObjectsDTO(
				new ArrayList<>(estadoServicioMapper.estadoServicioEntitiesToDTO(listaPage.getContent())));
		respuesta.setTotal(listaPage.getTotalElements());
		return respuesta;
	}

	/**
	 * 
	 * @return
	 * @throws SpddExceptions
	 */
	public GenericoDTO consultarEstructuraMetaTodos() throws SpddExceptions {
		GenericoDTO genericoDTO = new GenericoDTO();
		List<EstructuraMetaDTO> listaDTO = estructuraMetaMapper
				.estructuraMetaEntitiesToDTO(estructuraMetaServiceRepo.obtenerTodos());
		genericoDTO.setLstObjectsDTO(new ArrayList<>(listaDTO));
		return genericoDTO;
	}

	/**
	 * 
	 * @param peticion
	 * @return
	 * @throws SpddExceptions
	 */
	public GenericoDTO consultarEstructuraMetaPorFiltro(EstructuraMetaDTO peticion) throws SpddExceptions {
		GenericoDTO respuesta = new GenericoDTO();
		Pageable pageRequest = PageRequest.of(peticion.getPagina(), peticion.getTamanioPagina());
		FiltroDTO filtro = estructuraMetaServiceRepo.filtrarPorCampo(peticion, pageRequest.getOffset(),
				pageRequest.getPageSize());
		Page<EstructuraMeta> listaPage = new PageImpl<>((List<EstructuraMeta>) filtro.getResultadoConsulta(),
				pageRequest, filtro.getContador());
		respuesta.setLstObjectsDTO(
				new ArrayList<>(estructuraMetaMapper.estructuraMetaEntitiesToDTO(listaPage.getContent())));
		respuesta.setTotal(listaPage.getTotalElements());
		return respuesta;
	}

	/**
	 * 
	 * @return
	 * @throws SpddExceptions
	 */
	public GenericoDTO consultarFuncionarioClaveEntidadTodos() throws SpddExceptions {
		GenericoDTO genericoDTO = new GenericoDTO();
		List<FuncionarioClaveEntidadDTO> listaDTO = funcionarioClaveEntidadMapper
				.funcionarioClaveEntidadEntitiesToDTO(funcionarioClaveEntidadServiceRepo.obtenerTodos());
		genericoDTO.setLstObjectsDTO(new ArrayList<>(listaDTO));
		return genericoDTO;
	}

	/**
	 * 
	 * @param peticion
	 * @return
	 * @throws SpddExceptions
	 */
	public GenericoDTO consultarFuncionarioClaveEntidadPorFiltro(FuncionarioClaveEntidadDTO peticion)
			throws SpddExceptions {
		GenericoDTO respuesta = new GenericoDTO();
		Pageable pageRequest = PageRequest.of(peticion.getPagina(), peticion.getTamanioPagina());
		FiltroDTO filtro = funcionarioClaveEntidadServiceRepo.filtrarPorCampo(peticion, pageRequest.getOffset(),
				pageRequest.getPageSize());
		Page<FuncionarioClaveEntidad> listaPage = new PageImpl<>(
				(List<FuncionarioClaveEntidad>) filtro.getResultadoConsulta(), pageRequest, filtro.getContador());
		respuesta.setLstObjectsDTO(new ArrayList<>(
				funcionarioClaveEntidadMapper.funcionarioClaveEntidadEntitiesToDTO(listaPage.getContent())));
		respuesta.setTotal(listaPage.getTotalElements());
		return respuesta;
	}

	/**
	 * 
	 * @return
	 * @throws SpddExceptions
	 */
	public GenericoDTO consultarInformacionPresupuestalTodos() throws SpddExceptions {
		GenericoDTO genericoDTO = new GenericoDTO();
		List<InformacionPresupuestalDTO> listaDTO = informacionPresupuestalMapper
				.informacionPresupuestalEntitiesToDTO(informacionPresupuestalServiceRepo.obtenerTodos());
		genericoDTO.setLstObjectsDTO(new ArrayList<>(listaDTO));
		return genericoDTO;
	}

	/**
	 * 
	 * @return
	 * @throws SpddExceptions
	 */
	public GenericoDTO consultarLineaDeInversionTodos() throws SpddExceptions {
		GenericoDTO genericoDTO = new GenericoDTO();
		List<LineaDeInversionDTO> listaDTO = lineaDeInversionMapper
				.lineaDeInversionEntitiesTODTO(lineaDeInversionServiceRepo.obtenerTodos());
		genericoDTO.setLstObjectsDTO(new ArrayList<>(listaDTO));
		return genericoDTO;
	}

	/**
	 * 
	 * @return
	 * @throws SpddExceptions
	 */
	public GenericoDTO consultarListaCompuestaTodos() throws SpddExceptions {
		GenericoDTO genericoDTO = new GenericoDTO();
		List<ListaCompuestaDTO> listaDTO = listaCompuestaMapper
				.listaCompuestaEntitiesToDTO(listaCompuestaServiceRepo.obtenerTodos());
		genericoDTO.setLstObjectsDTO(new ArrayList<>(listaDTO));
		return genericoDTO;
	}

	/**
	 * 
	 * @return
	 * @throws SpddExceptions
	 */
	public GenericoDTO consultarListaSimpleTodos() throws SpddExceptions {
		GenericoDTO genericoDTO = new GenericoDTO();
		List<ListaSimpleDTO> listaDTO = listaSimpleMapper
				.listaSimpleEntitiesToDTO(listaSimpleServiceRepo.obtenerTodos());
		genericoDTO.setLstObjectsDTO(new ArrayList<>(listaDTO));
		return genericoDTO;
	}

	/**
	 * 
	 * @param peticion
	 * @return
	 * @throws SpddExceptions
	 */
	public GenericoDTO consultarListaSimplePorFiltro(ListaSimpleDTO peticion) throws SpddExceptions {
		GenericoDTO respuesta = new GenericoDTO();

		Pageable pageRequest = PageRequest.of(peticion.getPagina(), peticion.getTamanioPagina());
		FiltroDTO filtro = listaSimpleServiceRepo.filtrarPorCampo(peticion, pageRequest.getOffset(),
				pageRequest.getPageSize());
		Page<ListaSimple> listaPage = new PageImpl<>((List<ListaSimple>) filtro.getResultadoConsulta(), pageRequest,
				filtro.getContador());
		respuesta.setLstObjectsDTO(new ArrayList<>(listaSimpleMapper.listaSimpleEntitiesToDTO(listaPage.getContent())));
		respuesta.setTotal(listaPage.getTotalElements());

		return respuesta;
	}

	/**
	 * 
	 * @return
	 * @throws SpddExceptions
	 */
	public GenericoDTO consultarParametroGeneralTodos() throws SpddExceptions {
		GenericoDTO genericoDTO = new GenericoDTO();
		List<ParametroGeneralDTO> listaDTO = parametroGeneralMapper
				.parametroGeneralEntitiesToDTO(parametroGeneralServiceRepo.obtenerTodos());
		genericoDTO.setLstObjectsDTO(new ArrayList<>(listaDTO));
		return genericoDTO;
	}

	/**
	 * 
	 * @return
	 * @throws SpddExceptions
	 */
	public GenericoDTO consultarPotProyectoInstrumentoTodos() throws SpddExceptions {
		GenericoDTO genericoDTO = new GenericoDTO();
		List<PotProyectoInstrumentoDTO> listaDTO = potProyectoInstrumentoMapper
				.potProyectoInstrumentoEntitiesToDTO(potProyectoInstrumentoServiceRepo.obtenerTodos());
		genericoDTO.setLstObjectsDTO(new ArrayList<>(listaDTO));
		return genericoDTO;
	}

	/**
	 * 
	 * @return
	 * @throws SpddExceptions
	 */
	public GenericoDTO consultarProyectoInversionTodos() throws SpddExceptions {
		GenericoDTO genericoDTO = new GenericoDTO();
		List<ProyectoInversionDTO> listaDTO = proyectoInversionMapper
				.proyectoInversionEntitiesToDTO(proyectoInversionServiceRepo.obtenerTodos());
		genericoDTO.setLstObjectsDTO(new ArrayList<>(listaDTO));
		return genericoDTO;
	}

	/**
	 * 
	 * @return
	 * @throws SpddExceptions
	 */
	public GenericoDTO consultarProyectoInversionUsuarioTodos() throws SpddExceptions {
		GenericoDTO genericoDTO = new GenericoDTO();
		List<ProyectosInversionUsuarioDTO> listaDTO = proyectosInversionUsuarioMapper
				.proyectosInversionUsuarioEntitiesToDTO(proyectosInversionUsuarioServiceRepo.obtenerTodos());
		genericoDTO.setLstObjectsDTO(new ArrayList<>(listaDTO));
		return genericoDTO;
	}

	/**
	 * 
	 * @return
	 * @throws SpddExceptions
	 */
	public GenericoDTO consultarTerritorializacionTodos() throws SpddExceptions {
		GenericoDTO genericoDTO = new GenericoDTO();
		List<TerritorializacionDTO> listaDTO = territorializacionMapper
				.territorializacionEntitiesToDTO(territorializacionServiceRepo.obtenerTodos());
		genericoDTO.setLstObjectsDTO(new ArrayList<>(listaDTO));
		return genericoDTO;
	}

	/**
	 * 
	 * @return
	 * @throws SpddExceptions
	 */
	public GenericoDTO consultarUsuarioEntidadTodos() throws SpddExceptions {
		GenericoDTO genericoDTO = new GenericoDTO();
		List<UsuarioEntidadDTO> listaDTO = usuarioEntidadMapper
				.usuarioEntidadDTOEntitiesToDTO(usuarioEntidadServiceRepo.obtenerTodos());
		genericoDTO.setLstObjectsDTO(new ArrayList<>(listaDTO));
		return genericoDTO;
	}

	/**
	 * 
	 * @param peticion
	 * @return
	 * @throws SpddExceptions
	 */
	public GenericoDTO consultarUsuariosClavePorFiltro(FuncionarioClaveEntidadDTO peticion) throws SpddExceptions {
		GenericoDTO respuesta = new GenericoDTO();
		Pageable pageRequest = PageRequest.of(peticion.getPagina(), peticion.getTamanioPagina());
		FiltroDTO filtro = funcionarioClaveEntidadServiceRepo.filtrarPorCampo(peticion, pageRequest.getOffset(),
				pageRequest.getPageSize());
		Page<FuncionarioClaveEntidad> listaPage = new PageImpl<>(
				(List<FuncionarioClaveEntidad>) filtro.getResultadoConsulta(), pageRequest, filtro.getContador());
		respuesta.setLstObjectsDTO(new ArrayList<>(
				funcionarioClaveEntidadMapper.funcionarioClaveEntidadEntitiesToDTO(listaPage.getContent())));
		respuesta.setTotal(listaPage.getTotalElements());
		return respuesta;
	}

	/* Consultas personalizadas */

	/**
	 * Método que retorna el número de notificaciones no leidas por usuario.
	 * 
	 * @param buzonNotificacionesDTO
	 * @return
	 * @throws SpddExceptions
	 */
	public Long notificacionesPorUsuario(BuzonNotificacionesDTO buzonNotificacionesDTO) throws SpddExceptions {
		return buzonNotificacioneServiceRepo.notificacionesPorUsuario(buzonNotificacionesDTO.getCodigoUsuarioDestino(),
				buzonNotificacionesDTO.getEstado());
	}

	/**
	 * 
	 * @param id
	 * @return
	 * @throws SpddExceptions
	 */
	public GenericoDTO obtenerArgumentoPorLista(Long id) throws SpddExceptions {
		GenericoDTO genericoDTO = new GenericoDTO();
		List<ArgumentoListaSimpleDTO> listaDTO = argumentoListaSimpleMapper
				.argumentoListaSimpleEntitiesToDTO(argumentoListaSimpleServiceRepo.obtenerArgumentoPorLista(id));
		genericoDTO.setLstObjectsDTO(new ArrayList<>(listaDTO));
		return genericoDTO;
	}

	/**
	 * 
	 * @param nombre
	 * @return
	 * @throws SpddExceptions
	 */
	public GenericoDTO obtenerArgumentoPorNombre(String nombre) throws SpddExceptions {
		GenericoDTO genericoDTO = new GenericoDTO();
		List<ArgumentoListaSimpleDTO> listaDTO = argumentoListaSimpleMapper
				.argumentoListaSimpleEntitiesToDTO(argumentoListaSimpleServiceRepo.obtenerArgumentoPorNombre(nombre));
		genericoDTO.setLstObjectsDTO(new ArrayList<>(listaDTO));
		return genericoDTO;
	}

	/**
	 * Método que retorna las notificaciones por usuario.
	 * 
	 * @param usuario
	 * @return
	 * @throws SpddExceptions
	 */
	public GenericoDTO obtenerBuzonPorUsuario(String usuario) throws SpddExceptions {
		GenericoDTO genericoDTO = new GenericoDTO();
		List<BuzonNotificaciones> entidad = buzonNotificacioneServiceRepo.obtenerBuzonPorUsuario(usuario);
		List<BuzonNotificacionesDTO> listaRespuesta = buzonNotificacionesMapper
				.buzonNotificacionesEntitiesToDTO(entidad);
		genericoDTO.setLstObjectsDTO(new ArrayList<>(listaRespuesta));
		return genericoDTO;
	}

	/**
	 * 
	 * @param codigoEntidad
	 * @return
	 * @throws SpddExceptions
	 */
	public GenericoDTO obtenerFuncionarioPorEntidad(String codigoEntidad) throws SpddExceptions {
		GenericoDTO respuesta = new GenericoDTO();
		List<FuncionarioClaveEntidadDTO> lista = funcionarioClaveEntidadMapper.funcionarioClaveEntidadEntitiesToDTO(
				funcionarioClaveEntidadServiceRepo.obtenerFuncionarioPorEntidad(codigoEntidad));
		respuesta.setLstObjectsDTO(new ArrayList<>(lista));
		return respuesta;
	}

	/**
	 * 
	 * @return
	 */
	public GenericoDTO pddNivelAtributoObtenerLibres() {
		GenericoDTO respuesta = new GenericoDTO();
		List<PddNivelAtributo> lista = pddNivelAtributoServiceRepo.pddNivelAtributoObtenerLibres();
		List<PddNivelAtributoDTO> listaRespuesta = pddNivelAtributoMapper.nivelAtributoEntitiesToDTO(lista);
		respuesta.setLstObjectsDTO(new ArrayList<>(listaRespuesta));
		return respuesta;
	}

	/**
	 * 
	 * @return
	 * @throws SpddExceptions
	 */
	public List<ProyectoInversion> proyectoInversionObtenerDisponibles() throws SpddExceptions {
		List<ProyectoInversion> lista = proyectoInversionServiceRepo.proyectoInversionObtenerDisponibles();
		return lista;
	}

	/**
	 * 
	 * @param territorializacionDTO
	 * @return
	 * @throws SpddExceptions
	 */
	public TerritorializacionDTO buscarPorLsBarrioYLsUpzYLsLocalidad(TerritorializacionDTO territorializacionDTO)
			throws SpddExceptions {

		Territorializacion territorializacion = territorializacionServiceRepo.buscarPorLsBarrioYLsUpzYLsLocalidad(
				territorializacionDTO.getIdLsBarrio(), territorializacionDTO.getIdLsUpz(),
				territorializacionDTO.getIdLsLocalidad());
		if (territorializacion != null) {
			return territorializacionMapper.territorializacionEntityToDTO(territorializacion);
		}
		return new TerritorializacionDTO();

	}

	/**
	 * 
	 * @param potProyectoInstrumentoDTO
	 * @return
	 * @throws SpddExceptions
	 */
	public PotProyectoInstrumentoDTO buscarPorIdLsPotObraYIdLsPotInstrumento(
			PotProyectoInstrumentoDTO potProyectoInstrumentoDTO) throws SpddExceptions {
		PotProyectoInstrumento potProyectoInstrumento = potProyectoInstrumentoServiceRepo
				.buscarPorIdLsPotObraAndIdLsPotInstrumento(potProyectoInstrumentoDTO.getIdPotInstrumento(),
						potProyectoInstrumentoDTO.getIdPotProyecto());
		if (potProyectoInstrumento != null) {
			return potProyectoInstrumentoMapper.potProyectoInstrumentoEntityToDTO(potProyectoInstrumento);
		}
		return new PotProyectoInstrumentoDTO();

	}

	/**
	 * 
	 * @param territorializacionDTO
	 * @return
	 * @throws SpddExceptions
	 */
	public TerritorializacionDTO buscarPorLsVeredaYLsUpr(TerritorializacionDTO territorializacionDTO)
			throws SpddExceptions {

		Territorializacion territorializacion = territorializacionServiceRepo.buscarPorLsVeredaYLsUpr(
				territorializacionDTO.getIdLsVereda(), territorializacionDTO.getIdLsUpr(),
				territorializacionDTO.getIdLsLocalidad());
		if (territorializacion != null) {
			return territorializacionMapper.territorializacionEntityToDTO(territorializacion);
		}
		return new TerritorializacionDTO();
	}

	/**
	 * 
	 * @param sector
	 * @param categoria
	 * @return
	 */
	public EquipamientoDTO buscarSectorYCategoria(Long sector, Long categoria) throws SpddExceptions {
		Equipamento equip = equipamientoServiceRepo.validarSectorYCategoria(sector, categoria);
		if (equip != null) {
			return equipamientoMapper.equipamientoEntityToDTO(equip);
		}
		return new EquipamientoDTO();
	}

	/**
	 * 
	 * @param concepto
	 * @param sector
	 * @return
	 * @throws SpddExceptions
	 */
	public LineaDeInversionDTO buscarLineaInversionPorConceptoYSector(String concepto, Long sector)
			throws SpddExceptions {
		LineaDeInversion lineaDeInversion = lineaDeInversionServiceRepo.buscarConceptoYSector(concepto, sector);
		if (lineaDeInversion != null) {
			return lineaDeInversionMapper.lineaDeInversionEntityToDTO(lineaDeInversion);
		}
		return new LineaDeInversionDTO();
	}

	/**
	 * 
	 * @param codigo
	 * @param nombre
	 * @return
	 */
	public ComponenteGastoDTO buscarPorCodigoYNombre(Long codigo, String nombre) {
		ComponenteGasto componente = componenteGastoServiceRepo.buscarPorCodigoYNombre(codigo, nombre);
		if (componente != null) {
			return componenteGastoMapper.componenteGastoEntityToDTO(componente);
		}
		return new ComponenteGastoDTO();
	}

	/**
	 * 
	 * @param configuracionCargueArchivoDTO
	 * @return
	 */
	public ConfiguracionCargueArchivoDTO buscarIdLsModuloYIdLsTema(
			ConfiguracionCargueArchivoDTO configuracionCargueArchivoDTO) {
		ConfiguracionCargueArchivoDTO cargueArchivoDTO = new ConfiguracionCargueArchivoDTO();

		ConfigCargueArchivo configCargueArchivo = configCargueArchivoServiceRepo.buscarPorIdLsModuloYIdLsTema(
				configuracionCargueArchivoDTO.getIdLsModulo(), configuracionCargueArchivoDTO.getIdLsTema());

		if (configCargueArchivo != null) {
			cargueArchivoDTO = configCargueArchivoMapper.configuracionCargueEntityToDTO(configCargueArchivo);
		}
		return cargueArchivoDTO;
	}

	/**
	 * 
	 * @param peticion
	 * @return
	 * @throws SpddExceptions
	 */
	public ArgumentoListaSimpleDTO buscarIdListaSimpleArgumento(ArgumentoListaSimpleDTO peticion)
			throws SpddExceptions {
		ArgumentoListaSimple argumentoListaSimple = argumentoListaSimpleServiceRepo
				.buscarIdListaSimple(peticion.getIdListaSimple(), peticion.getArgumento());
		if (argumentoListaSimple != null) {
			return argumentoListaSimpleMapper.argumentoListaSimpleEntityToDTO(argumentoListaSimple);
		}
		return new ArgumentoListaSimpleDTO();
	}

	/**
	 * 
	 * @param unidadMedida
	 * @param verbo
	 * @return
	 * @throws SpddExceptions
	 */
	public EstructuraMetaDTO buscarUnidadMedidaYVerbo(Long unidadMedida, Long verbo) throws SpddExceptions {
		EstructuraMeta estructuraMeta = estructuraMetaServiceRepo.validarUnidadMedidaYVerbo(unidadMedida, verbo);
		if (estructuraMeta != null) {
			return estructuraMetaMapper.estructuraMetaEntityToDTO(estructuraMeta);
		}
		return new EstructuraMetaDTO();
	}

	/**
	 * 
	 * @param strQuery
	 * @return
	 * @throws SpddExceptions
	 * @throws DataAccessException
	 */
	public int buscarSql(String strQuery) throws SpddExceptions, DataAccessException {
		return configCargueArchivoServiceRepo.buscarSql(strQuery);
	}

	/**
	 * 
	 * @param sector
	 * @return
	 * @throws SpddExceptions
	 */
	public GenericoDTO buscarPorSector(String sector) throws SpddExceptions {

		GenericoDTO respuesta = new GenericoDTO();
		respuesta.setLstObjectsDTO(new ArrayList<>(lineaDeInversionMapper
				.lineaDeInversionEntitiesTODTO(lineaDeInversionServiceRepo.buscarPorSector(sector))));
		return respuesta;
	}

	/**
	 * 
	 * @param localidad
	 * @return
	 * @throws SpddExceptions
	 */
	public GenericoDTO buscarTerrPorLocalida() throws SpddExceptions {
		GenericoDTO respuesta = new GenericoDTO();
		//List<TerritorializacionDTO> listaTerritorializacionDTOs = territorializacionMapper.territorializacionEntitiesToDTO(territorializacionServiceRepo.buscarPorLocalidad(localidad));
		List<TerritorializacionDTO> listaTerritorializacionDTOs = territorializacionMapper.territorializacionEntitiesToDTO(territorializacionServiceRepo.obtenerTodos());

		TerritorializacionDTO flagUpz = new TerritorializacionDTO();
		TerritorializacionDTO flagUpr = new TerritorializacionDTO();
		flagUpz.setNombreUpz("Todas las Upz");
		flagUpz.setIdLsUpz(-2L);
		flagUpz.setIdLsUpr(-1L);
		flagUpr.setNombreUpr("Todas las Upr");
		flagUpr.setIdLsUpr(-2L);
		flagUpr.setIdLsUpz(-1L);
		listaTerritorializacionDTOs.add(flagUpz);
		listaTerritorializacionDTOs.add(flagUpr);
		respuesta.setLstObjectsDTO(new ArrayList<>(listaTerritorializacionDTOs));
		return respuesta;
	}

	/**
	 * Metodo que sirve para obtener el argumento de lista simple por el campo de
	 * resultado
	 * 
	 * @param resultado string que corresponde al valor por el cual se piensa buscar
	 * @return un objeto de tipo ArgumentoistaSimple con la informacion
	 *         correspondiente
	 */
	public ArgumentoListaSimpleDTO consultarArgumentoListaSimplePorResultadoYNombreListaSimple(String resultado, String nombreListaSimple)
			throws SpddExceptions {
		ArgumentoListaSimple argumentoListaSimple = argumentoListaSimpleServiceRepo.obtenerPorResultadoYNombreListaSimple(resultado,
				nombreListaSimple);
		return argumentoListaSimple != null
				? argumentoListaSimpleMapper.argumentoListaSimpleEntityToDTO(argumentoListaSimple)
				: new ArgumentoListaSimpleDTO();
	}

	/**
	 * 
	 * @param codigoEntidad
	 * @param idLsFuncion
	 * @return
	 * @throws SpddExceptions
	 */
	public FuncionarioClaveEntidadDTO validarFuncionarioPorEntidadYFuncion(String codigoEntidad, Long idLsFuncion)
			throws SpddExceptions {
		return funcionarioClaveEntidadMapper.funcionarioClaveEntidadToDTO(funcionarioClaveEntidadServiceRepo
				.validarFuncionarioPorIdLsFuncionYEntidad(codigoEntidad, idLsFuncion));
	}

	public List<BuzonNotificacionesDTO> leerInformativos(String usuario) throws SpddExceptions {
		return buzonNotificacionesMapper
				.buzonNotificacionesEntitiesToDTO(buzonNotificacioneServiceRepo.leerInformativos(usuario));
	}

	public ConsecutivoDTO obtenerConsecutivosPorPlanYEntidad(Long idPlan, String entidad, String nombre)
			throws SpddExceptions {
		return consecutivoMapper
				.consecutivoEntityToDTO(consecutivoServiceRepo.obtenerConsecutivos(idPlan, entidad, nombre));
	}
	
	public GenericoDTO obtenerTerritoriosPorIdLocalidad(Long idLocalidad) throws SpddExceptions {
		GenericoDTO respuesta = new GenericoDTO();
		//List<TerritorializacionDTO> listaTerritorializacionDTOs = territorializacionMapper.territorializacionEntitiesToDTO(territorializacionServiceRepo.buscarPorLocalidad(localidad));
		List<TerritorializacionDTO> listaTerritorializacionDTOs = territorializacionMapper.territorializacionEntitiesToDTO(territorializacionServiceRepo.obtenerPorIdLocalidad(idLocalidad));

		TerritorializacionDTO flagUpz = new TerritorializacionDTO();
		TerritorializacionDTO flagUpr = new TerritorializacionDTO();
		flagUpz.setNombreUpz("Todas las Upz");
		flagUpz.setIdLsUpz(-2L);
		flagUpz.setIdLsUpr(-1L);
		flagUpr.setNombreUpr("Todas las Upr");
		flagUpr.setIdLsUpr(-2L);
		flagUpr.setIdLsUpz(-1L);
		listaTerritorializacionDTOs.add(flagUpz);
		listaTerritorializacionDTOs.add(flagUpr);
		respuesta.setLstObjectsDTO(new ArrayList<>(listaTerritorializacionDTOs));
		return respuesta;
	}
	
	public GenericoDTO obtenerLineasDeInversion() throws SpddExceptions{
		GenericoDTO respuesta = new GenericoDTO();
		List<LineaDeInversionDTO> lineas = lineaDeInversionMapper.lineaDeInversionEntitiesTODTO(lineaDeInversionServiceRepo.obtenerTodos());
		respuesta.setLstObjectsDTO(new ArrayList<>(lineas));
		return respuesta;
	}
}