package co.gov.sdp.spdd.data.mapper;

import java.io.Serializable;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import co.gov.sdp.nhspdd.common.dto.ip.PdlNivelDTO;
import co.gov.sdp.spdd.data.model.ip.PdlNivel;

@Mapper
public interface PdlNivelMapper extends Serializable {
	
	/**
	 * Metodo que permite pasar de una entidad a DTO
	 * @param pdlNivel entidad de tipo PdlNivel de la que se quiere extraer la informacion para el DTO
	 * @return un objeto PdlNivelDTO con la informacion correspondiente
	 */
	@Mapping(source = "pdlNivel.idPdlNivel", target = "idPdlNivel")
	@Mapping(source = "pdlNivel.codNivel", target = "codNivel")
	@Mapping(source = "pdlNivel.descripcion", target = "descripcion")
	@Mapping(source = "pdlNivel.idPlanLocal.idPlanLocal", target = "idPlanLocal")
	@Mapping(source = "pdlNivel.idPlanLocal.nombrePlan", target = "nombrePlan")	
	PdlNivelDTO pdlNivelEntityToDTO(PdlNivel pdlNivel);
	
	/**
	 * Metodo que permite pasar de una DTO a entidad
	 * @param pdlNivelDTO objeto de tipo PdlNivelDTO del que se quiere extraer la informacion para el DTO
	 * @return un objeto PdlNivel con la informacion correspondiente
	 */
	@Mapping(source = "pdlNivelDTO.idPdlNivel", target = "idPdlNivel")
	@Mapping(source = "pdlNivelDTO.codNivel", target = "codNivel")
	@Mapping(source = "pdlNivelDTO.descripcion", target = "descripcion")
	@Mapping(source = "pdlNivelDTO.idPlanLocal", target = "idPlanLocal.idPlanLocal")
	PdlNivel pdlNivelDTOToEntity(PdlNivelDTO pdlNivelDTO);

	/**
	 * Metodo que pasa una lista tipo pdl a una lista dto
	 *
	 * @param pdl lista que se desea convertir
	 * @return una lista dto a partir de la lista tipo pdl
	 */
	List<PdlNivelDTO> pdlNivelEntitiesToDTO(List<PdlNivel> pdlNivel);

	/**
	 * Metodo que pasa una lista tipo dto a una lista pdl
	 *
	 * @param pdlDTO lista a convertir
	 * @return una lista tipo pdl a partir de una lista dto
	 */
	List<PdlNivel> pdlNivelDTOToEntities(List<PdlNivelDTO> pdlNivelDTO);
}
