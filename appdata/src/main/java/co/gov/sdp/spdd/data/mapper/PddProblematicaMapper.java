/**
 * 
 */
package co.gov.sdp.spdd.data.mapper;

import java.io.Serializable;
import java.util.List;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import co.gov.sdp.nhspdd.common.dto.ip.PddProblematicaDTO;
import co.gov.sdp.spdd.data.model.ip.PddProblematica;

/**
 * @author Jose Alvaro
 *
 */
@Mapper
public interface PddProblematicaMapper extends Serializable {
	
	@AfterMapping
    default void afterMapping(@MappingTarget PddProblematica target,PddProblematicaDTO source)
    {
        if (source.getIdLsLocalizacion() == null)
            target.setIdLsLocalizacion(null);
        if (source.getIdLsSublocalizacion() == null)
            target.setIdLsSublocalizacion(null);
        if (source.getIdLzUpzUpr() == null)
            target.setIdLzUpzUpr(null);
    }


	
	/**
	 * Metodo que convierte una entidad a un dto
	 * 
	 * @param pddProblematica
	 * @return pddProblematicaDTO
	 */
	@Mapping(source = "pddProblematica.idProblematica", target = "idProblematica")
	@Mapping(source = "pddProblematica.problematica", target = "problematica")
	@Mapping(source = "pddProblematica.descripcion", target = "descripcion")
	@Mapping(source = "pddProblematica.causas", target = "causas")
	@Mapping(source = "pddProblematica.consecuencias", target = "consecuencias")
	@Mapping(source = "pddProblematica.objetivo", target = "objetivo")
	@Mapping(source = "pddProblematica.idLsLocalizacion.idArgumento", target = "idLsLocalizacion")
	@Mapping(source = "pddProblematica.idLsLocalizacion.resultado", target = "localizacion")
	@Mapping(source = "pddProblematica.idLsSublocalizacion.idArgumento", target = "idLsSublocalizacion")
	@Mapping(source = "pddProblematica.idLsSublocalizacion.resultado", target = "subLocalizacion")
	@Mapping(source = "pddProblematica.idLzUpzUpr.idArgumento", target = "idLzUpzUpr")
	@Mapping(source = "pddProblematica.idLzUpzUpr.resultado", target = "upzUpr")
	@Mapping(source = "pddProblematica.idCompromiso.idCompromiso", target = "idCompromiso")
	PddProblematicaDTO pddProblematicaEntityToDTO(PddProblematica pddProblematica);
	
	/**
	 * Metodo que convierte un dto a una entidad
	 * 
	 * @param pddProblematicaDTO
	 * @return pddProblematica
	 */
	@Mapping(source = "pddProblematicaDTO.idProblematica", target = "idProblematica")
	@Mapping(source = "pddProblematicaDTO.problematica", target = "problematica")
	@Mapping(source = "pddProblematicaDTO.descripcion", target = "descripcion")
	@Mapping(source = "pddProblematicaDTO.causas", target = "causas")
	@Mapping(source = "pddProblematicaDTO.consecuencias", target = "consecuencias")
	@Mapping(source = "pddProblematicaDTO.objetivo", target = "objetivo")
	@Mapping(source = "pddProblematicaDTO.idLsLocalizacion", target = "idLsLocalizacion.idArgumento")
	@Mapping(source = "pddProblematicaDTO.idLsSublocalizacion", target = "idLsSublocalizacion.idArgumento")
	@Mapping(source = "pddProblematicaDTO.idLzUpzUpr", target = "idLzUpzUpr.idArgumento")
	@Mapping(source = "pddProblematicaDTO.idCompromiso", target = "idCompromiso.idCompromiso")
	PddProblematica pddProblematicaDTOToEntity(PddProblematicaDTO pddProblematicaDTO);
	
	/**
	 * 
	 * @param pddProblematicas
	 * @return
	 */
	List<PddProblematicaDTO> pddProblematicasEntitiesToDTO(List<PddProblematica> pddProblematicas);
	
	/**
	 * 
	 * @param pddProblematicaDTOs
	 * @return
	 */
	List<PddProblematica> pddProblematicaDTOToEntities(List<PddProblematicaDTO>pddProblematicasDTO);

}
