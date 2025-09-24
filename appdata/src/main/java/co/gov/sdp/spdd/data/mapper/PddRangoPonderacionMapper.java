package co.gov.sdp.spdd.data.mapper;

import java.io.Serializable;
import java.util.Base64;
import java.util.List;

import org.mapstruct.BeforeMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import co.gov.sdp.nhspdd.common.dto.ArgumentoListaSimpleDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddRangoPonderacionDTO;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.spdd.data.model.afs.ArgumentoListaSimple;
import co.gov.sdp.spdd.data.model.ip.PddRangoPonderacion;

@Mapper
public interface PddRangoPonderacionMapper extends Serializable {

	
//	@BeforeMapping
//    default void beforeMapping(@MappingTarget PddRangoPonderacionDTO target,
//            PddRangoPonderacion source) {
//        if (source.getLogo() != null) {
//        	target.setImagen(Base64.getEncoder().encodeToString(source.getLogo()));
//        } 
//    }
	/**
	 * Metodo que convierte una entidad a un dto
	 * @param ppdRangoPonderacion
	 * @return
	 */
	@Mapping(source = "ppdRangoPonderacion.idRango", target = "idRango")
	@Mapping(source = "ppdRangoPonderacion.logo", target = "logo")
	@Mapping(source = "ppdRangoPonderacion.logo", target = "imagen")
	@Mapping(source = "ppdRangoPonderacion.rango", target = "rango")
	@Mapping(source = "ppdRangoPonderacion.descripcion", target = "descripcion")
	@Mapping(source = "ppdRangoPonderacion.idPlanDesarrollo.idPlanDesarrollo", target = "idPlanDesarrollo")
	PddRangoPonderacionDTO ppdRangoPonderacionEntityToDTO(PddRangoPonderacion ppdRangoPonderacion);
	

	/**
	 * Metodo que convierte un dto a una entidad
	 * @param pddRangoPonderacionDTO
	 * @return
	 */
	@Mapping(source = "pddRangoPonderacionDTO.idRango", target = "idRango")
	@Mapping(source = "pddRangoPonderacionDTO.logo", target = "logo")
	@Mapping(source = "pddRangoPonderacionDTO.rango", target = "rango")
	@Mapping(source = "pddRangoPonderacionDTO.descripcion", target = "descripcion")
	@Mapping(source = "pddRangoPonderacionDTO.idPlanDesarrollo", target = "idPlanDesarrollo.idPlanDesarrollo")
	PddRangoPonderacion ppdRangoPonderacionDTOToEntity(PddRangoPonderacionDTO pddRangoPonderacionDTO);
	
	/**
	 * 
	 * @param ppdRangoPonderacion
	 * @return
	 */
	List<PddRangoPonderacionDTO> pddRangoPonderacionEntitiesToDTO(List<PddRangoPonderacion> ppdRangoPonderacion);
	
	/**
	 * 
	 * @param pddRangoPonderacionDTO
	 * @return
	 */
	List<PddRangoPonderacion> ppdRangoPonderacionDTOToEntities(List<PddRangoPonderacionDTO> pddRangoPonderacionDTO);
	
}
