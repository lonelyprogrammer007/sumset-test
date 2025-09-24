package co.gov.sdp.spdd.data.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import co.gov.sdp.nhspdd.common.dto.bp.BpIniciativaEtarioDTO;
import co.gov.sdp.spdd.data.model.bp.BpIniciativaEtario;

/**
 * 
 * @author Bryan Muñoz
 *
 */
@Mapper
public interface BpIniciativaEtarioMapper {

	@Mapping(source = "etario.idEtario", target = "idEtario")
	@Mapping(source = "etario.idLsEtario.idArgumento", target = "idLsEtario")
	@Mapping(source = "etario.idIniciativa.idIniciativa", target = "idIniciativa")
	@Mapping(source = "etario.idLsEtario.resultado", target = "nombreEtario")
	BpIniciativaEtarioDTO etarioEntityToDTO(BpIniciativaEtario etario);

	@Mapping(source = "etarioDTO.idEtario", target = "idEtario")
	@Mapping(source = "etarioDTO.idLsEtario", target = "idLsEtario.idArgumento")
	@Mapping(source = "etarioDTO.idIniciativa", target = "idIniciativa.idIniciativa")
	BpIniciativaEtario etarioDTOToEntity(BpIniciativaEtarioDTO etarioDTO);

	List<BpIniciativaEtarioDTO> etarioEntitiesToDTO(List<BpIniciativaEtario> listaEtario);

	List<BpIniciativaEtario> etarioDTOToEntities(List<BpIniciativaEtarioDTO> listaEtario);
}
