package co.gov.sdp.spdd.data.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import co.gov.sdp.nhspdd.common.dto.ip.PddMpAnualizarDTO;
import co.gov.sdp.spdd.data.model.ip.PddMpAnualizar;

@Mapper
public interface PddMpAnualizarMapper {

	@Mapping(source = "pddMpAnualizar.idAnualizar", target = "idAnualizar")
	@Mapping(source = "pddMpAnualizar.vigencia", target = "vigencia")
	@Mapping(source = "pddMpAnualizar.magnitud", target = "magnitud")
	@Mapping(source = "pddMpAnualizar.recursos", target = "recursos")
	@Mapping(source = "pddMpAnualizar.idMetaProducto.idMetaProducto", target = "idMetaProducto")
	PddMpAnualizarDTO pddAnualizarEntityToDTO(PddMpAnualizar pddMpAnualizar);

	@Mapping(source = "pddMpAnualizarDTO.idAnualizar", target = "idAnualizar")
	@Mapping(source = "pddMpAnualizarDTO.vigencia", target = "vigencia")
	@Mapping(source = "pddMpAnualizarDTO.magnitud", target = "magnitud")
	@Mapping(source = "pddMpAnualizarDTO.recursos", target = "recursos")
	@Mapping(source = "pddMpAnualizarDTO.idMetaProducto", target = "idMetaProducto.idMetaProducto")
	PddMpAnualizar pddAnualizarDTOToEntity(PddMpAnualizarDTO pddMpAnualizarDTO);
	
	List<PddMpAnualizarDTO> pddAnualizarEntitiesToDTO(List<PddMpAnualizar> listaAnualizar);
	
	List<PddMpAnualizar> pddAnualizarDTOToEntities(List<PddMpAnualizarDTO> listaAnulizarDTO);

}
