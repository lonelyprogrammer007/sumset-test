package co.gov.sdp.spdd.data.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import co.gov.sdp.nhspdd.common.dto.bp.BpIniciativaCondicionDTO;
import co.gov.sdp.spdd.data.model.bp.BpIniciativaCondicion;

@Mapper
public interface BpIniciativaCondicionMapper {

	@Mapping(source = "bpIniciativaCondicion.idCondicion", target = "idCondicion")
	@Mapping(source = "bpIniciativaCondicion.idLsCondicion.idArgumento", target = "idLsCondicion")
	@Mapping(source = "bpIniciativaCondicion.idIniciativa.idIniciativa", target = "idIniciativa")
	@Mapping(source = "bpIniciativaCondicion.idLsCondicion.resultado", target = "nombreCondicion")
	BpIniciativaCondicionDTO iniciativaCondicionEntityToDTO(BpIniciativaCondicion bpIniciativaCondicion);

	@Mapping(source = "bpIniciativaCondicionDTO.idCondicion", target = "idCondicion")
	@Mapping(source = "bpIniciativaCondicionDTO.idLsCondicion", target = "idLsCondicion.idArgumento")
	@Mapping(source = "bpIniciativaCondicionDTO.idIniciativa", target = "idIniciativa.idIniciativa")
	BpIniciativaCondicion iniciativaCondicionDTOToEntity(BpIniciativaCondicionDTO bpIniciativaCondicionDTO);

	List<BpIniciativaCondicionDTO> iniciativaCondicionEntitiesToDTO(List<BpIniciativaCondicion> listaCondicion);

	List<BpIniciativaCondicion> iniciativaCondicionDTOToEntities(List<BpIniciativaCondicionDTO> listaCondicionDTO);
}
