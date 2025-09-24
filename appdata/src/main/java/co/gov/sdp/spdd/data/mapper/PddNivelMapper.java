package co.gov.sdp.spdd.data.mapper;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import co.gov.sdp.nhspdd.common.dto.PddNivelDTO;
import co.gov.sdp.spdd.data.model.ip.PddNivel;

@Mapper(uses = {PddMapper.class})
public interface PddNivelMapper extends Serializable {

	/**
	 * Metodo que permite pasar de una entidad a DTO
	 * @param pddNivel entidad de tipo PddNivel de la que se quiere extraer la informacion para el DTO
	 * @return un objeto PddNivelDTO con la informacion correspondiente
	 */
	@Mapping(source = "pddNivel.idPddNivel", target = "idPddNivel")
	@Mapping(source = "pddNivel.codNivel", target = "codNivel")
	@Mapping(source = "pddNivel.sumPond", target = "sumPond")
	@Mapping(source = "pddNivel.descripcion", target = "descripcion")
	@Mapping(source = "pddNivel.idPlanDesarrollo.idPlanDesarrollo", target = "idPlanDesarrollo")
	@Mapping(source = "pddNivel.idPlanDesarrollo.nombrePlan", target = "nombrePlan")
	
	PddNivelDTO pddNivelEntityToDTO(PddNivel pddNivel);
	
	/**
	 * Metodo que permite pasar de una DTO a entidad
	 * @param pddNivelDTO objeto de tipo PddNivelDTO del que se quiere extraer la informacion para el DTO
	 * @return un objeto PddNivel con la informacion correspondiente
	 */
	@Mapping(source = "pddNivelDTO.idPddNivel", target = "idPddNivel")
	@Mapping(source = "pddNivelDTO.codNivel", target = "codNivel")
	@Mapping(source = "pddNivelDTO.sumPond", target = "sumPond")
	@Mapping(source = "pddNivelDTO.descripcion", target = "descripcion")
	@Mapping(source = "pddNivelDTO.obligatorioPdl", target = "obligatorioPdl")
	@Mapping(source = "pddNivelDTO.idPlanDesarrollo", target = "idPlanDesarrollo.idPlanDesarrollo")
	PddNivel pddNivelDTOToEntity(PddNivelDTO pddNivelDTO);

	/**
	 * Metodo que pasa una lista tipo pdd a una lista dto
	 *
	 * @param pdd lista que se desea convertir
	 * @return una lista dto a partir de la lista tipo pdd
	 */
	List<PddNivelDTO> pddNivelEntitiesToDTO(List<PddNivel> pddNivel);

	/**
	 * Metodo que pasa una lista tipo dto a una lista pdd
	 *
	 * @param pddDTO lista a convertir
	 * @return una lista tipo pdd a partir de una lista dto
	 */
	List<PddNivel> pddNivelDTOToEntities(List<PddNivelDTO> pddNivelDTO);

}
