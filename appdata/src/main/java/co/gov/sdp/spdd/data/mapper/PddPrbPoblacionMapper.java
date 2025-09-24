package co.gov.sdp.spdd.data.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import co.gov.sdp.nhspdd.common.dto.ip.PddPrbPoblacionDTO;
import co.gov.sdp.spdd.data.model.ip.PddPrbPoblacion;

/**
 * 
 * @author Bryan Muñoz
 *
 */
@Mapper
public interface PddPrbPoblacionMapper {

	@Mapping(source = "pddPrbPoblacion.idPoblacion", target = "idPoblacion")
	@Mapping(source = "pddPrbPoblacion.descripcion", target = "descripcion")
	@Mapping(source = "pddPrbPoblacion.idProblematica.idProblematica", target = "idProblematica")
	PddPrbPoblacionDTO poblacionEntityToDTO(PddPrbPoblacion pddPrbPoblacion);

	@Mapping(source = "pddPrbPoblacionDTO.idPoblacion", target = "idPoblacion")
	@Mapping(source = "pddPrbPoblacionDTO.descripcion", target = "descripcion")
	@Mapping(source = "pddPrbPoblacionDTO.idProblematica", target = "idProblematica.idProblematica")
	PddPrbPoblacion poblacionDTOToEntity(PddPrbPoblacionDTO pddPrbPoblacionDTO);

	List<PddPrbPoblacionDTO> poblacionEntitiesToDTO(List<PddPrbPoblacion> listPoblacion);

	List<PddPrbPoblacion> poblacionDTOToEntities(List<PddPrbPoblacionDTO> listPoblacionDTO);
}
