package co.gov.sdp.spdd.data.mapper;

import java.io.Serializable;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import co.gov.sdp.nhspdd.common.dto.EstadoServicioDTO;
import co.gov.sdp.spdd.data.model.afs.EstadoServicio;

/**
 * Interface que permite convertir un dto a una entidad y viceversa
 *
 * @author Bryan Munoz
 *
 */
@Mapper
public interface EstadoServicioMapper extends Serializable {

	/**
	 * Metodo que convierte un
	 *
	 * @param estadoServicio
	 * @return
	 */
	@Mapping(source = "estadoServicio.idEstadoServicio", target = "idEstadoServicio")
	@Mapping(source = "estadoServicio.fechaRespuesta", target = "fechaRespuesta", dateFormat = "dd/MM/yyyy")
	@Mapping(source = "estadoServicio.fechaSolicitud", target = "fechaSolicitud", dateFormat = "dd/MM/yyyy")
	@Mapping(source = "estadoServicio.nombreServicio", target = "nombreServicio", dateFormat = "dd/MM/yyyy HH:mm:ss")
	@Mapping(source = "estadoServicio.tarea", target = "tarea")
	EstadoServicioDTO estadoServicioEntityToDTO(EstadoServicio estadoServicio);

	/**
	 *
	 * @param estadoServicioDTO
	 * @return
	 */
	@Mapping(source = "estadoServicioDTO.idEstadoServicio", target = "idEstadoServicio")
	@Mapping(source = "estadoServicioDTO.fechaRespuesta", target = "fechaRespuesta", dateFormat = "dd/MM/yyyy HH:mm:ss")
	@Mapping(source = "estadoServicioDTO.fechaSolicitud", target = "fechaSolicitud", dateFormat = "dd/MM/yyyy HH:mm:ss")
	@Mapping(source = "estadoServicioDTO.nombreServicio", target = "nombreServicio", dateFormat = "dd/MM/yyyy HH:mm:ss")
	@Mapping(source = "estadoServicioDTO.tarea", target = "tarea")
	EstadoServicio estadoServicioDTOToEntity(EstadoServicioDTO estadoServicioDTO);

	/**
	 *
	 * @param estadosServicio
	 * @return
	 */
	List<EstadoServicioDTO> estadoServicioEntitiesToDTO(List<EstadoServicio> estadosServicio);

	/**
	 *
	 * @param estadosServicio
	 * @return
	 */
	List<EstadoServicio> estadoServicioDTOToEntities(List<EstadoServicioDTO> estadosServicio);
}
