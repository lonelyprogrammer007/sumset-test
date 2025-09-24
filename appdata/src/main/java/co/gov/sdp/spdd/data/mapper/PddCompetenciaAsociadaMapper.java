package co.gov.sdp.spdd.data.mapper;

import java.io.Serializable;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import co.gov.sdp.nhspdd.common.dto.ip.PddCompetenciaAsociadaDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddCompromisoEspecificoDTO;
import co.gov.sdp.spdd.data.model.ip.PddCompetenciaAsociada;
import co.gov.sdp.spdd.data.model.ip.PddCompromisoEspecifico;

/**
 * Interface que contiene los mapeadores de entity a DTO y viceversa para la entidad PddCompetenciaSimple
 * @author DANIEL
 * @version 1.0 10/03/2020
 */
@Mapper(uses = {ArgumentoListaSimpleMapper.class})
public interface PddCompetenciaAsociadaMapper extends Serializable {
	
	/**
     * Metodo que mapea una entidad a un dto
     * @param pddCompetencia entidad que se desea mapear a dto
     * @return dto mapeado a partir de la entidad
     */
    @Mapping(source = "pddCompetencia.idCompetencia", target = "idCompetencia")
    @Mapping(source = "pddCompetencia.idLsCompetencia.idArgumento", target = "idLsCompetencia")
    @Mapping(source = "pddCompetencia.idLsSector.idArgumento", target = "idLsSector")
    @Mapping(source = "pddCompetencia.idLsCompetencia.resultado", target = "nombreCompetencia")
    @Mapping(source = "pddCompetencia.idLsSector.resultado", target = "nombreSector")
    PddCompetenciaAsociadaDTO pddCompetenciaAsociadaEntityToDTO(PddCompetenciaAsociada pddCompetencia);

    /**
     * Metodo que mapea un dto a una entidad
     * @param PddCompromisoEspecificoDTO dto que se desea mapear a entidad
     * @return la entidad mapeada a partir del dto
     */
    @Mapping(source = "pddCompetenciaDTO.idCompetencia", target = "idCompetencia")
    @Mapping(source = "pddCompetenciaDTO.idLsCompetencia", target = "idLsCompetencia.idArgumento")
    @Mapping(source = "pddCompetenciaDTO.idLsSector", target = "idLsSector.idArgumento")
    PddCompetenciaAsociada pddCompetenciaAsociadaDTOToEntity(PddCompetenciaAsociadaDTO pddCompetenciaDTO);

    /**
     * Metodo que mapea una lista tipo PddCompetenciaAsociada a una lista tipo dto
     * @param pddCompetencias lista de compromisos especificos que se desea mapear a dto
     * @return la lista dto mapeada a partir de la lista
     */
    List<PddCompetenciaAsociadaDTO> pddCompetenciasAsociadasEntitiesToDTO(List<PddCompetenciaAsociada> pddCompetencias);

    /**
     * Metodo que mapea una lista PddCompetenciaAsociadaDTO a una lista PddCompetenciaAsociada
     * @param pddCompetenciasDTO lista dto que se desea mapear a entidad
     * @return lista tipo PddCompetenciaAsociada a partir del dto
     */
    List<PddCompetenciaAsociada> pddCompetenciasAsociadasDTOToEntities(List<PddCompetenciaAsociadaDTO> pddCompetenciasDTO);


}
