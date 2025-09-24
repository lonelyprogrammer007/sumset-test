/**
 * 
 */
package co.gov.sdp.spdd.data.serviciofacade.afs;

import java.io.Serializable;
import java.util.Date;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

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
 * @author Sumset
 *
 */
@Component
public class SessionRegistroAFS extends SessionAFS implements Serializable {

	/**
	 * Atributo de serialización.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Método que permite construir un objeto de tipo
	 */
	public SessionRegistroAFS() {
		super();
	}

	/**
	 * 
	 * @param archivoProcesadoDTO
	 * @return
	 * @throws SpddExceptions
	 */
	public ArchivoProcesadoDTO guardarArchivoProcesado(ArchivoProcesadoDTO archivoProcesadoDTO) {
		ArchivoProcesado archivoProcesado = archivoProcesadoMapper.ArchivoProcesadoDTOToEntity(archivoProcesadoDTO);
		archivoProcesado.setFechaCargue(new Date());
		return archivoProcesadoMapper
				.ArchivoProcesadoEntityToDTO(archivoProcesadoServiceRepo.guardar(archivoProcesado));
	}

	/**
	 * 
	 * @param argumentoListaSimple
	 * @return
	 * @throws SpddExceptions
	 */
	public ArgumentoListaSimpleDTO guardarArgumentoListaSimple(ArgumentoListaSimpleDTO argumentoListaSimpleDTO) {

		ArgumentoListaSimple argumentoListaSimple = argumentoListaSimpleMapper
				.argumentoListaSimpleDTOToEntity(argumentoListaSimpleDTO);

		return argumentoListaSimpleMapper
				.argumentoListaSimpleEntityToDTO(argumentoListaSimpleServiceRepo.guardar(argumentoListaSimple));
	}

	/**
	 * 
	 * @param bancoDeProyectoDTO
	 * @return
	 * @throws SpddExceptions
	 */
	public BancoDeProyectoDTO guardarBancoDeProyecto(BancoDeProyectoDTO bancoDeProyectoDTO) {
		BancoDeProyectos bancoDeProyectos = bancoDeProyectoMapper.bancoDeProyectoDTOToEntity(bancoDeProyectoDTO);
		return bancoDeProyectoMapper.bancoDeProyectoEntityToDTO(bancoDeProyectoServiceRepo.guardar(bancoDeProyectos));
	}

	/**
	 * 
	 * @param buzonNotificacionesDTO
	 * @return
	 * @throws SpddExceptions
	 */
	public BuzonNotificacionesDTO guardarBuzonNotificaciones(BuzonNotificacionesDTO buzonNotificacionesDTO) {
		BuzonNotificaciones buzonNotificaciones = buzonNotificacionesMapper
				.buzonNotificacionesDTOToEntity(buzonNotificacionesDTO);

//            buzonNotificaciones.setFechaEscritura(new Date());
		return buzonNotificacionesMapper
				.buzonNotificacionesEntityToDTO(buzonNotificacioneServiceRepo.guardar(buzonNotificaciones));
	}

	/**
	 * 
	 * @param componenteGastoDTO
	 * @return
	 * @throws SpddExceptions
	 */
	public ComponenteGastoDTO guardarComponenteGasto(ComponenteGastoDTO componenteGastoDTO) {
		ComponenteGasto componenteGasto = componenteGastoMapper.componenteGastoDTOToEntity(componenteGastoDTO);
		return componenteGastoMapper.componenteGastoEntityToDTO(componenteGastoServiceRepo.guardar(componenteGasto));
	}

	/**
	 * 
	 * @param componenteGestionUsuarioDTO
	 * @return
	 * @throws SpddExceptions
	 */
	public ComponenteGestionUsuarioDTO guardarComponenteGestionusuario(
			ComponenteGestionUsuarioDTO componenteGestionUsuarioDTO) {
		ComponenteGestionUsuario componenteGestionUsuario = componenteGestionUsuarioMapper
				.componenteGestionUsuarioDTOToEntity(componenteGestionUsuarioDTO);
		return componenteGestionUsuarioMapper.componenteGestionUsuarioEntityToDTO(
				componenteGestionUsuarioServiceRepo.guardar(componenteGestionUsuario));
	}

	/**
	 * 
	 * @param consecutivoDTO
	 * @return
	 * @throws SpddExceptions
	 */
	public ConsecutivoDTO guardarConsecutivo(ConsecutivoDTO consecutivoDTO) {
		Consecutivo consecutivo = consecutivoMapper.consecutivoDTOTOEntity(consecutivoDTO);
		return consecutivoMapper.consecutivoEntityToDTO(consecutivoServiceRepo.guardar(consecutivo));
	}

	/**
	 * 
	 * @param entidadDTO
	 * @return
	 * @throws SpddExceptions
	 */
	public EntidadDTO guardarEntidad(EntidadDTO entidadDTO) {
		Entidad entidad = entidadMapper.entidadDTOToEntity(entidadDTO);
		return entidadMapper.entidadEntityToDTO(entidadServiceRepo.guardar(entidad));
	}

	/**
	 * 
	 * @param equipamientoDTO
	 * @return
	 * @throws SpddExceptions
	 */
	public EquipamientoDTO guardarEquipamiento(EquipamientoDTO equipamientoDTO) {
		Equipamento equipamento = equipamientoMapper.equipamientoDTOToEntity(equipamientoDTO);
		return equipamientoMapper.equipamientoEntityToDTO(equipamientoServiceRepo.guardar(equipamento));
	}

	/**
	 * 
	 * @param estadoServicioDTO
	 * @return
	 * @throws SpddExceptions
	 */
	public EstadoServicioDTO guardarEstadoServicio(EstadoServicioDTO estadoServicioDTO) {
		EstadoServicio estadoServicio = estadoServicioMapper.estadoServicioDTOToEntity(estadoServicioDTO);
		return estadoServicioMapper.estadoServicioEntityToDTO(estadoServicioServiceRepo.guardar(estadoServicio));
	}

	/**
	 * 
	 * @param estructuraMetaDTO
	 * @return
	 * @throws SpddExceptions
	 */
	public EstructuraMetaDTO guardarEstructuraMeta(EstructuraMetaDTO estructuraMetaDTO) {
		EstructuraMeta estructuraMeta = estructuraMetaMapper.estructuraMetaDTOToEntity(estructuraMetaDTO);
		return estructuraMetaMapper.estructuraMetaEntityToDTO(estructuraMetaServiceRepo.guardar(estructuraMeta));
	}

	/**
	 * 
	 * @param funcionarioClaveEntidadDTO
	 * @return
	 * @throws SpddExceptions
	 */
	public FuncionarioClaveEntidadDTO guardarFuncionarioClaveEntidad(
			FuncionarioClaveEntidadDTO funcionarioClaveEntidadDTO) {
		FuncionarioClaveEntidad funcionarioClaveEntidad = funcionarioClaveEntidadMapper
				.funcionarioClaveEntidadDTOToEntity(funcionarioClaveEntidadDTO);
		return funcionarioClaveEntidadMapper
				.funcionarioClaveEntidadToDTO(funcionarioClaveEntidadServiceRepo.guardar(funcionarioClaveEntidad));
	}

	/**
	 * 
	 * @param informacionPresupuestalDTO
	 * @return
	 * @throws SpddExceptions
	 */
	public InformacionPresupuestalDTO guardarInformacionPresupuestal(
			InformacionPresupuestalDTO informacionPresupuestalDTO) {
		InformacionPresupuestal informacionPresupuestal = informacionPresupuestalMapper
				.informacionPresupuestalDTOToEntity(informacionPresupuestalDTO);
		return informacionPresupuestalMapper.informacionPresupuestalEntityToDTO(
				informacionPresupuestalServiceRepo.guardar(informacionPresupuestal));
	}

	/**
	 * 
	 * @param lineaDeInversionDTO
	 * @return
	 * @throws SpddExceptions
	 */
	public LineaDeInversionDTO guardarLineaDeInversion(LineaDeInversionDTO lineaDeInversionDTO) {
		LineaDeInversion lineaDeInversion = lineaDeInversionMapper.lineaDeInversionDTOToEntity(lineaDeInversionDTO);
		return lineaDeInversionMapper
				.lineaDeInversionEntityToDTO(lineaDeInversionServiceRepo.guardar(lineaDeInversion));
	}

	/**
	 * 
	 * @param listaCompuestaDTO
	 * @return
	 * @throws SpddExceptions
	 */
	public ListaCompuestaDTO guardarListaCompuesta(ListaCompuestaDTO listaCompuestaDTO) {
		ListaCompuesta listaCompuesta = listaCompuestaMapper.listaCompuestaDTOToEntity(listaCompuestaDTO);
		return listaCompuestaMapper.listaCompuestaEntityToDTO(listaCompuestaServiceRepo.guardar(listaCompuesta));
	}

	/**
	 * 
	 * @param listaSimpleDTO
	 * @return
	 * @throws SpddExceptions
	 */
	public ListaSimpleDTO guardarListaSimple(ListaSimpleDTO listaSimpleDTO) {
		ListaSimple listaSimple = listaSimpleMapper.listaSimpleDTOToEntity(listaSimpleDTO);
		return listaSimpleMapper.listaSimpleEntityToDTO(listaSimpleServiceRepo.guardar(listaSimple));
	}

	/**
	 * 
	 * @param parametroGeneralDTO
	 * @return
	 * @throws SpddExceptions
	 */
	public ParametroGeneralDTO guardarParametroGeneral(ParametroGeneralDTO parametroGeneralDTO) {
		ParametroGeneral parametroGeneral = parametroGeneralMapper.parametroDTOToEntity(parametroGeneralDTO);
		return parametroGeneralMapper.parametroEntityToDTO(parametroGeneralServiceRepo.guardar(parametroGeneral));
	}

	/**
	 * 
	 * @param potProyectoInstrumentoDTO
	 * @return
	 * @throws SpddExceptions
	 */
	public PotProyectoInstrumentoDTO guardarPotProyectoInstrumento(
			PotProyectoInstrumentoDTO potProyectoInstrumentoDTO) {
		PotProyectoInstrumento potProyectoInstrumento = potProyectoInstrumentoMapper
				.potProyectoInstrumentoDTOToEntity(potProyectoInstrumentoDTO);
		return potProyectoInstrumentoMapper
				.potProyectoInstrumentoEntityToDTO(potProyectoInstrumentoServiceRepo.guardar(potProyectoInstrumento));
	}

	/**
	 * 
	 * @param proyectoInversionDTO
	 * @return
	 * @throws SpddExceptions
	 */
	public ProyectoInversionDTO guardarProyectoInversion(ProyectoInversionDTO proyectoInversionDTO) {
		ProyectoInversion proyectoInversion = proyectoInversionMapper
				.proyectoInversionDTOToEntity(proyectoInversionDTO);
		return proyectoInversionMapper
				.proyectoInversionEntityToDTO(proyectoInversionServiceRepo.guardar(proyectoInversion));

	}

	/**
	 * 
	 * @param proyectosInversionUsuarioDTO
	 * @return
	 * @throws SpddExceptions
	 */
	public ProyectosInversionUsuarioDTO guardarProyectosInversionUsuario(
			ProyectosInversionUsuarioDTO proyectosInversionUsuarioDTO) {
		ProyectosInversionUsuario proyectosInversionUsuario = proyectosInversionUsuarioMapper
				.proyectosInversionUsuarioDTOToEntity(proyectosInversionUsuarioDTO);
		return proyectosInversionUsuarioMapper.proyectosInversionUsuarioEntityToDTO(
				proyectosInversionUsuarioServiceRepo.guardar(proyectosInversionUsuario));

	}

	/**
	 * 
	 * @param territorializacionDTO
	 * @return
	 * @throws SpddExceptions
	 */
	public TerritorializacionDTO guardarTerritorializacion(TerritorializacionDTO territorializacionDTO) {

		return territorializacionMapper.territorializacionEntityToDTO(territorializacionServiceRepo
				.guardar(territorializacionMapper.territorializacionDTOToEntity(territorializacionDTO)));
	}

	/**
	 * 
	 * @param usuarioEntidadDTO
	 * @return
	 * @throws SpddExceptions
	 */
	public UsuarioEntidadDTO guardarUsuarioEntidad(UsuarioEntidadDTO usuarioEntidadDTO) {
		UsuarioEntidad usuarioEntidad = usuarioEntidadMapper.usuarioEntidadDTOToEntity(usuarioEntidadDTO);
		return usuarioEntidadMapper.usuarioEntidadEntityToDTO(usuarioEntidadServiceRepo.guardar(usuarioEntidad));
	}

	/**
	 * 
	 * @param strQuery
	 * @return
	 * @throws SpddExceptions
	 * @throws DataAccessException
	 */
	public int guardarSql(String strQuery) throws SpddExceptions, DataAccessException {
		return configCargueArchivoServiceRepo.guardarSql(strQuery);
	}
}
