package co.gov.sdp.spdd.data.mapper;

import java.io.Serializable;
import java.util.List;

import org.mapstruct.BeforeMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import co.gov.sdp.nhspdd.common.dto.BuzonNotificacionesDTO;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.spdd.data.model.afs.BuzonNotificaciones;

/**
 * Interface que maneja la conversion de dto a entidad y viceversa de buzon
 * notificaciones
 *
 * @author Bryan Munoz
 *
 */
@Mapper(uses = { ConfiguracionNotificacionMapper.class })
public interface BuzonNotificacionesMapper extends Serializable {

	/**
	 * 
	 * @param target
	 * @param source
	 */
	@BeforeMapping
	default void beforeMapping(@MappingTarget BuzonNotificacionesDTO target, BuzonNotificaciones source) {
		if (source.getEstado() == 0) {
			target.setStringEstado("No atendido");
		} else {
			target.setStringEstado("Atendido");
		}
	}

	/**
	 * Metodo que convierte una entidad a un dto
	 *
	 * @param buzonNotificaciones entidad a convertir
	 * @return un objeto tipo dto a partir de la entidad
	 */
	@Mapping(source = "buzonNotificaciones.idNotificacion", target = "idNotificacion")
	@Mapping(source = "buzonNotificaciones.estado", target = "estado")
	@Mapping(target = "stringEstado", ignore = true)
	@Mapping(source = "buzonNotificaciones.fechaEscritura", target = "fechaEscritura", dateFormat = "dd/MM/yyyy")
	@Mapping(source = "buzonNotificaciones.fechaLectura", target = "fechaLectura", dateFormat = "dd/MM/yyyy")
	@Mapping(source = "buzonNotificaciones.fechaRespuesta", target = "fechaRespuesta", dateFormat = "dd/MM/yyyy")
	@Mapping(source = "buzonNotificaciones.mensaje", target = "mensaje")
	@Mapping(source = "buzonNotificaciones.respuesta", target = "respuesta")
	@Mapping(source = "buzonNotificaciones.tipoMensaje", target = "tipoMensaje")
	@Mapping(source = "buzonNotificaciones.codigoUsuarioDestino", target = "codigoUsuarioDestino")
	@Mapping(source = "buzonNotificaciones.codigoUsuarioOrigina", target = "codigoUsuarioOrigina")
	@Mapping(source = "buzonNotificaciones.idConfigNotificacion.operacionOrigen", target = "nombreOperacionOrigen")
	@Mapping(source = "buzonNotificaciones.idConfigNotificacion.idConfigNotificacion", target = "idConfigNotificacion")
	@Mapping(source = "buzonNotificaciones.asunto", target = "asunto")

	BuzonNotificacionesDTO buzonNotificacionesEntityToDTO(BuzonNotificaciones buzonNotificaciones);

	/**
	 * Metodo que convierte un dto a una entidad
	 *
	 * @param buzonNotificacionesDTO objeto dto a convertir
	 * @return un objeto tipo entidad de buzonNotificaciones
	 */
	@Mapping(source = "buzonNotificacionesDTO.idNotificacion", target = "idNotificacion")
	@Mapping(source = "buzonNotificacionesDTO.estado", target = "estado")
	@Mapping(source = "buzonNotificacionesDTO.fechaEscritura", target = "fechaEscritura", dateFormat = "dd/MM/yyyy")
	@Mapping(source = "buzonNotificacionesDTO.fechaLectura", target = "fechaLectura", dateFormat = "dd/MM/yyyy")
	@Mapping(source = "buzonNotificacionesDTO.fechaRespuesta", target = "fechaRespuesta", dateFormat = "dd/MM/yyyy")
	@Mapping(source = "buzonNotificacionesDTO.idConfigNotificacion", target = "idConfigNotificacion.idConfigNotificacion")
	@Mapping(source = "buzonNotificacionesDTO.mensaje", target = "mensaje")
	@Mapping(source = "buzonNotificacionesDTO.respuesta", target = "respuesta")
	@Mapping(source = "buzonNotificacionesDTO.tipoMensaje", target = "tipoMensaje")
	@Mapping(source = "buzonNotificacionesDTO.codigoUsuarioDestino", target = "codigoUsuarioDestino")
	@Mapping(source = "buzonNotificacionesDTO.codigoUsuarioOrigina", target = "codigoUsuarioOrigina")
	@Mapping(source = "buzonNotificacionesDTO.asunto", target = "asunto")
	BuzonNotificaciones buzonNotificacionesDTOToEntity(BuzonNotificacionesDTO buzonNotificacionesDTO);

	//

	/**
	 * Metodo que convierte una lista tipo buzonNotificaciones a una tipo dto
	 *
	 * @param buzonNotificaciones lista a convertir a lista dto
	 * @return lista tipo dto a partir de la lista tipo buzonNotificaciones
	 */
	List<BuzonNotificacionesDTO> buzonNotificacionesEntitiesToDTO(List<BuzonNotificaciones> buzonNotificaciones);

	/**
	 * Metodo que convierte una lista tipo dto a una lista buzonNotifiaciones
	 *
	 * @param buzonNotificacionesDTO lista tipo dto a convertir a una lista tipo
	 *                               buzonNotificaciones
	 * @return una lista buzonNotificaciones a partir de una lista
	 *         buzonNotificaciones
	 */
	List<BuzonNotificaciones> buzonNotificacionesDTOToEntities(List<BuzonNotificacionesDTO> buzonNotificacionesDTO);

}
