package co.gov.sdp.spdd.data.mapper;

import java.io.Serializable;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import co.gov.sdp.nhspdd.common.dto.ip.PddCompromisoEspecificoDTO;
import co.gov.sdp.spdd.data.model.ip.PddCompromisoEspecifico;

/**
 * Interface que contiene los mapeadores de entity a DTO y viceversa para la entidad PddCompromisoEspecifico
 * @author DANIEL
 * @version 1.0 10/03/2020
 */
@Mapper(uses = {PddCompromisoMapper.class})
public interface PddCompromisoEspecificoMapper extends Serializable {
	
    /**
     * Metodo que mapea una entidad a un dto
     *
     * @param PddCompromisoEspecifico entidad que se desea mapear a dto
     * @return dto mapeado a partir de la entidad
     */
    @Mapping(source = "pddCompromisoEspecifico.idEspecifico", target = "idEspecifico")
    @Mapping(source = "pddCompromisoEspecifico.descripcion", target = "descripcion")
    @Mapping(source = "pddCompromisoEspecifico.idCompromiso.idCompromiso", target = "idCompromiso")
    @Mapping(source = "pddCompromisoEspecifico.idCompromiso.idEstrategico.idCompromiso", target = "idCompromisoEstrategico")
    @Mapping(source = "pddCompromisoEspecifico.idCompromiso.idEstrategico.idLsEstrategico.idArgumento", target = "idLsCompromisoEstrategico")
    @Mapping(source = "pddCompromisoEspecifico.idCompromiso.idEstrategico.idLsEstrategico.resultado", target = "nombreCompromisoEstrategico")
    @Mapping(source = "pddCompromisoEspecifico.idCompromiso.idEstrategico.idLsTematica.idArgumento", target = "idTematica")
    @Mapping(source = "pddCompromisoEspecifico.idCompromiso.idEstrategico.idLsTematica.resultado", target = "nombreTematica")
    PddCompromisoEspecificoDTO pddCompromisoEspecificoEntityToDTO(PddCompromisoEspecifico pddCompromisoEspecifico);

    /**
     * Metodo que mapea un dto a una entidad
     * @param PddCompromisoEspecificoDTO dto que se desea mapear a entidad
     * @return la entidad mapeada a partir del dto
     */
    @Mapping(source = "pddCompromisoEspecificoDTO.idEspecifico", target = "idEspecifico")
    @Mapping(source = "pddCompromisoEspecificoDTO.descripcion", target = "descripcion")
    @Mapping(source = "pddCompromisoEspecificoDTO.idCompromiso", target = "idCompromiso.idCompromiso")
    PddCompromisoEspecifico pddCompromisoEspecificoDTOToEntity(PddCompromisoEspecificoDTO pddCompromisoEspecificoDTO);

    /**
     * Metodo que mapea una lista tipo PddCompromisoEspecifico a una lista tipo dto
     *
     * @param pddCompromisosEspecificos lista de compromisos especificos que se desea mapear
     * a dto
     * @return la lista dto mapeada a partir de la lista
     */
    List<PddCompromisoEspecificoDTO> pddCompromisoEspecificoEntitiesToDTO(List<PddCompromisoEspecifico> pddCompromisosEspecificos);

    /**
     * Metodo que mapea una lista PddCompromisoEspecificoDTO a una lista PddCompromisoEspecifico
     * @param pddCompromisosEspecificosDTO lista dto que se desea mapear a entidad
     * @return lista tipo PddCompromisoEspecifico a partir del dto
     */
    List<PddCompromisoEspecifico> pddCompromisosEspecificosDTOToEntities(List<PddCompromisoEspecificoDTO> pddCompromisosEspecificosDTO);


}
