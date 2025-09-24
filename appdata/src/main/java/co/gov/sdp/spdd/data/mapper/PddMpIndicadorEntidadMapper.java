package co.gov.sdp.spdd.data.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import co.gov.sdp.nhspdd.common.dto.ip.PddMpIndicadorEntidadDTO;
import co.gov.sdp.spdd.data.model.ip.PddMpIndicadorEntidad;

/**
 * 
 * @author Bryan Muñoz
 *
 */
@Mapper
public interface PddMpIndicadorEntidadMapper {

	@Mapping(source = "pddMpIndicadorEntidad.idMpIndEntidad", target = "idMpIndEntidad")
	@Mapping(source = "pddMpIndicadorEntidad.magnitud", target = "magnitud")
	@Mapping(source = "pddMpIndicadorEntidad.ponderacion", target = "ponderacion")
	@Mapping(source = "pddMpIndicadorEntidad.codigoEntidad.codigoEntidad", target = "codigoEntidad")
	@Mapping(source = "pddMpIndicadorEntidad.idLsTipoAnualizacion.idArgumento", target = "idLsTipoAnualizacion")
	@Mapping(source = "pddMpIndicadorEntidad.idLsTipoAnualizacion.resultado", target = "nombreTipoAnualizacion")
	@Mapping(source = "pddMpIndicadorEntidad.idMetaProdInd.idMetaProdInd", target = "idMetaProdInd")
	PddMpIndicadorEntidadDTO indicadorEntidadEntityToDTO(PddMpIndicadorEntidad pddMpIndicadorEntidad);

	@Mapping(source = "pddMpIndicadorEntidadDTO.idMpIndEntidad", target = "idMpIndEntidad")
	@Mapping(source = "pddMpIndicadorEntidadDTO.magnitud", target = "magnitud")
	@Mapping(source = "pddMpIndicadorEntidadDTO.ponderacion", target = "ponderacion")
	@Mapping(source = "pddMpIndicadorEntidadDTO.codigoEntidad", target = "codigoEntidad.codigoEntidad")
	@Mapping(source = "pddMpIndicadorEntidadDTO.idLsTipoAnualizacion", target = "idLsTipoAnualizacion.idArgumento")
	@Mapping(source = "pddMpIndicadorEntidadDTO.idMetaProdInd", target = "idMetaProdInd.idMetaProdInd")
	PddMpIndicadorEntidad indicadorEntidadDTOToEntity(PddMpIndicadorEntidadDTO pddMpIndicadorEntidadDTO);

	List<PddMpIndicadorEntidadDTO> indicadorEntidadEntitiesToDTO(List<PddMpIndicadorEntidad> listaIndicadoresEntidades);

	List<PddMpIndicadorEntidad> indicadorEntidadDTOToEntities(List<PddMpIndicadorEntidadDTO> listaIndicadoresDTO);
}
