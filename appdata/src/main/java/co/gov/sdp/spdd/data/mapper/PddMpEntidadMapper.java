package co.gov.sdp.spdd.data.mapper;

import java.util.List;

import org.mapstruct.BeforeMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import co.gov.sdp.nhspdd.common.dto.ip.PddMpEntidadDTO;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.spdd.data.model.ip.PddMpEntidad;

@Mapper
public interface PddMpEntidadMapper {

	@BeforeMapping
	default void beforeMapping(@MappingTarget PddMpEntidadDTO target, PddMpEntidad source) {
		if (source.getEstado() == 0) {
			target.setStringEstado(NHSPDDConstantes.ESTADO_STRING_INACTIVO);
		} else {
			target.setStringEstado(NHSPDDConstantes.ESTADO_STRING_ACTIVO);
		}
	}

	@Mapping(source = "pddMpEntidad.idMpEntidad", target = "idMpEntidad")
	@Mapping(source = "pddMpEntidad.magnitud", target = "magnitud")
	@Mapping(source = "pddMpEntidad.estado", target = "estado")
	@Mapping(source = "pddMpEntidad.codigoEntidad.codigoEntidad", target = "codigoEntidad")
	@Mapping(source = "pddMpEntidad.idMetaProducto.idMetaProducto", target = "idMetaProducto")
	PddMpEntidadDTO mPEntidadEntityToDTO(PddMpEntidad pddMpEntidad);

	@Mapping(source = "pddMpEntidadDTO.idMpEntidad", target = "idMpEntidad")
	@Mapping(source = "pddMpEntidadDTO.magnitud", target = "magnitud")
	@Mapping(source = "pddMpEntidadDTO.estado", target = "estado")
	@Mapping(source = "pddMpEntidadDTO.codigoEntidad", target = "codigoEntidad.codigoEntidad")
	@Mapping(source = "pddMpEntidadDTO.idMetaProducto", target = "idMetaProducto.idMetaProducto")
	PddMpEntidad mPEntidadDTOToEntity(PddMpEntidadDTO pddMpEntidadDTO);

	List<PddMpEntidadDTO> mPEntitiesToDTO(List<PddMpEntidad> listaEntidades);

	List<PddMpEntidad> mPDTOToEntities(List<PddMpEntidadDTO> listaEntidadesDTO);

}
