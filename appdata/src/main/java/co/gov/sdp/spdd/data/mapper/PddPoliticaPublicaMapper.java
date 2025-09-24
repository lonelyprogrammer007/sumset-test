package co.gov.sdp.spdd.data.mapper;

import java.io.Serializable;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvPoliticaDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddPoliticaPublicaDTO;
import co.gov.sdp.spdd.data.model.bp.BpProyInvPolitica;
import co.gov.sdp.spdd.data.model.ip.PddPoliticaPublica;

/**
 * Clase que se encarga del mapeo de objetos entre PddPoliticaPublica y PddPoliticaPublicaDTO
 * @author DANIEL
 * @version 1.0 08/06/2020
 *
 */
@Mapper
public interface PddPoliticaPublicaMapper extends Serializable {
	
	/**
     * Metodo que mapea una entidad a un dto
     * @param pddPoliticaPublica entidad que se desea mapear a dto
     * @return dto mapeado a partir de la entidad
     */
	@Mapping(source = "pddPoliticaPublica.idPolPub", target = "idPolPub")
	@Mapping(source = "pddPoliticaPublica.codPolitica", target = "codPolitica")
	@Mapping(source = "pddPoliticaPublica.politica", target = "politica")
	@Mapping(source = "pddPoliticaPublica.idPlanDesarrollo.idPlanDesarrollo", target = "idPlanDesarrollo")
	@Mapping(source = "pddPoliticaPublica.idPlanDesarrollo.nombrePlan", target = "stringPlanDesarrollo")
	PddPoliticaPublicaDTO pddPoliticaPublicaEntityToDTO(PddPoliticaPublica pddPoliticaPublica);
	
   /**
     * Metodo que mapea un dto a una entidad
     * @param pddPoliticaPublicaDTO dto que se desea mapear a entidad
     * @return la entidad mapeada a partir del dto
     */
	@Mapping(source = "pddPoliticaPublicaDTO.idPolPub", target = "idPolPub")
	@Mapping(source = "pddPoliticaPublicaDTO.codPolitica", target = "codPolitica")
	@Mapping(source = "pddPoliticaPublicaDTO.politica", target = "politica")
	@Mapping(source = "pddPoliticaPublicaDTO.idPlanDesarrollo", target = "idPlanDesarrollo.idPlanDesarrollo")
	PddPoliticaPublica pddPoliticaPublicaDTOToEntity(PddPoliticaPublicaDTO pddPoliticaPublicaDTO);
	
   /**
     * Metodo que mapea una lista de entidades a una lista de DTO
     * @param listaPddPoliticaPublica lista entidades que se desea mapear a DTO
     * @return lista de DTO correspondiente.
     */
    List<PddPoliticaPublicaDTO> pddPoliticaPublicaEntitiesToDTO(List<PddPoliticaPublica> listaPddPoliticaPublica);

    /**
     * Metodo que mapea una lista de DTO a una lista de entidades
     * @param listaPddPoliticaPublicaDTO lista dto que se desea mapear a entidad
     * @return lista de entidades correspondiente.
     */
    List<PddPoliticaPublica> pddPoliticaPublicaDTOToEntities(List<PddPoliticaPublicaDTO> listaPddPoliticaPublicaDTO);


}
