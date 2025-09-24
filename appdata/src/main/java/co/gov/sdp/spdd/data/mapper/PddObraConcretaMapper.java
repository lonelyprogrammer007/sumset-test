package co.gov.sdp.spdd.data.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import co.gov.sdp.nhspdd.common.dto.ip.PddObraConcretaDTO;
import co.gov.sdp.spdd.data.model.ip.PddObraConcreta;

@Mapper(uses = {PddMetaMapper.class})
public interface PddObraConcretaMapper {

	@Mapping(source = "pddObraConcreta.idConcreta", target = "idConcreta")
	@Mapping(source = "pddObraConcreta.obraConcreta", target = "obraConcreta")
	@Mapping(source = "pddObraConcreta.idMeta.idMeta", target = "idMeta")
	@Mapping(source = "pddObraConcreta.idMeta.idTipoMetaLs.resultado", target = "tipoMeta")
	@Mapping(source = "pddObraConcreta.idMeta.meta", target = "meta")
	PddObraConcretaDTO pddObraConcretaEntityToDTO(PddObraConcreta pddObraConcreta);

	@Mapping(source = "pddObraConcretaDTO.idConcreta", target = "idConcreta")
	@Mapping(source = "pddObraConcretaDTO.obraConcreta", target = "obraConcreta")
	@Mapping(source = "pddObraConcretaDTO.idMeta", target = "idMeta.idMeta")
	PddObraConcreta pddObraConcretaDTOToEntity(PddObraConcretaDTO pddObraConcretaDTO);

	List<PddObraConcretaDTO> pddObraConcretaEntitiesToDTO(List<PddObraConcreta> listPddObraConcreta);

	List<PddObraConcreta> pddObraConcretaDTOToEntities(List<PddObraConcretaDTO> listPddObraConcretaDTO);

}
