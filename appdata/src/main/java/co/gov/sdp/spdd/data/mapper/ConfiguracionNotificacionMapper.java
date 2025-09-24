package co.gov.sdp.spdd.data.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import co.gov.sdp.nhspdd.common.dto.ConfiguracionNotificacionDTO;
import co.gov.sdp.spdd.data.model.afs.ConfiguracionNotificacion;

/**
 * Interface que convierte un dto a una entidad y viceversa de configuracion
 * notificacion
 *
 * @author Bryan Munoz
 *
 */
@Mapper
public interface ConfiguracionNotificacionMapper {

    /**
     * Metodo que convierte una entidad a un dto
     *
     * @param configuracionNotificacion entidad a convertir
     * @return un objeto tipo dto
     */
    @Mapping(source = "configuracionNotificacion.idConfigNotificacion", target = "idConfigNotificacion")
    @Mapping(source = "configuracionNotificacion.asunto", target = "asunto")
    @Mapping(source = "configuracionNotificacion.mensaje", target = "mensaje")
    @Mapping(source = "configuracionNotificacion.operacionOrigen", target = "operacionOrigen")
    @Mapping(source = "configuracionNotificacion.requiereAccion", target = "requiereAccion")
    @Mapping(source = "configuracionNotificacion.enviarCorreo", target = "enviarCorreo")
    @Mapping(source = "configuracionNotificacion.moduloOrigen", target = "moduloOrigen")
    @Mapping(source = "configuracionNotificacion.estado", target = "estado")
    ConfiguracionNotificacionDTO configuracionNotificacionEntityToDTO(
            ConfiguracionNotificacion configuracionNotificacion);

    /**
     * Metodo que convierte un dto a una entidad
     *
     * @param configuracionNotificacionDTO dto a convertir
     * @return un objeto entidad tipo configuracionNotificacion
     */
    @Mapping(source = "configuracionNotificacionDTO.idConfigNotificacion", target = "idConfigNotificacion")
    @Mapping(source = "configuracionNotificacionDTO.asunto", target = "asunto")
    @Mapping(source = "configuracionNotificacionDTO.mensaje", target = "mensaje")
    @Mapping(source = "configuracionNotificacionDTO.operacionOrigen", target = "operacionOrigen")
    @Mapping(source = "configuracionNotificacionDTO.requiereAccion", target = "requiereAccion")
    @Mapping(source = "configuracionNotificacionDTO.enviarCorreo", target = "enviarCorreo")
    @Mapping(source = "configuracionNotificacionDTO.moduloOrigen", target = "moduloOrigen")
    @Mapping(source = "configuracionNotificacionDTO.estado", target = "estado")
    ConfiguracionNotificacion configuracionNotificacionDTOToEntity(
            ConfiguracionNotificacionDTO configuracionNotificacionDTO);

    /**
     * Metodo que convierte una lista configuracionNotificaciones a una lista
     * dto
     *
     * @param configuracionNotificaciones lista a convertir
     * @return una lista tipo dto a partir de la lista configuracion
     * notificaciones
     */
    List<ConfiguracionNotificacionDTO> configuracionNotificacionEntitiesToDTO(
            List<ConfiguracionNotificacion> configuracionNotificaciones);

    /**
     * Metodo que convierte una lista dto a una lista
     * configuracionNotificaciones
     *
     * @param configuracionNotificacionesDTO lista tipo dto a convertir
     * @return una lista tipo configuracion notificaciones a partir de la lista
     * dto
     */
    List<ConfiguracionNotificacion> configuracionNotificacionDTOToEntities(
            List<ConfiguracionNotificacionDTO> configuracionNotificacionesDTO);
}
