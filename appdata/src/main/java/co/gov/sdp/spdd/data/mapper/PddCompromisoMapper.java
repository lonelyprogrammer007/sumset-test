package co.gov.sdp.spdd.data.mapper;

import java.io.Serializable;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import co.gov.sdp.nhspdd.common.dto.ip.PddCompromisoDTO;
import co.gov.sdp.spdd.data.model.ip.PddCompromiso;

/**
 * Clase que representa el Mapper entre la entidad BpProyectoInversion y el DTO BpProyectoInversionDTO
 * @author DANIEL
 * @version 1.0 31/03/2020
 *
 */
@Mapper(uses = {CompromisoEstrategicoMapper.class,PddMapper.class,ArgumentoListaSimpleMapper.class})
public interface PddCompromisoMapper extends Serializable {
	
    /**
     * Metodo que mapea una entidad a un dto
     *
     * @param pddCompromiso entidad que se desea mapear a dto
     * @return dto mapeado a partir de la entidad
     */
    @Mapping(source = "pddCompromiso.idCompromiso", target = "idCompromiso")
    @Mapping(source = "pddCompromiso.idEstrategico.idCompromiso", target = "idEstrategico")
    @Mapping(source = "pddCompromiso.idPlanDesarrollo.idPlanDesarrollo", target = "idPlanDesarrollo")
    @Mapping(source = "pddCompromiso.idPlanDesarrollo.nombrePlan", target = "nombrePlan")
    @Mapping(source = "pddCompromiso.idEstrategico.idLsTematica.idArgumento", target = "idTematica")
    @Mapping(source = "pddCompromiso.idEstrategico.idLsTematica.resultado", target = "nombreTematica")
    @Mapping(source = "pddCompromiso.idEstrategico.idLsEstrategico.idArgumento", target = "idLsEstrategico")
    @Mapping(source = "pddCompromiso.idEstrategico.idLsEstrategico.resultado", target = "nombreCompromisoEstrategico")
    PddCompromisoDTO pddCompromisoEntityToDTO(PddCompromiso pddCompromiso);

    /**
     * Metodo que mapea un dto a una entidad
     *
     * @param PddCompromisoDTO dto que se desea mapear a entidad
     * @return la entidad mapeada a partir del dto
     */
    @Mapping(source = "pddCompromisoDTO.idCompromiso", target = "idCompromiso")
    @Mapping(source = "pddCompromisoDTO.idEstrategico", target = "idEstrategico.idCompromiso")
    @Mapping(source = "pddCompromisoDTO.idPlanDesarrollo", target = "idPlanDesarrollo.idPlanDesarrollo")    
    PddCompromiso pddCompromisoDTOToEntity(PddCompromisoDTO pddCompromisoDTO);

    /**
     * Metodo que mapea una lista tipo PddCompromiso a una lista tipo dto
     *
     * @param pddCompromisos lista de pdd compromisos que se desea mapear a dto
     * @return la lista dto mapeada a partir de la lista
     */
    List<PddCompromisoDTO> pddCompromisosEntitiesToDTO(List<PddCompromiso> pddCompromisos);

    /**
     * Metodo que mapea una lista PddCompromisoDTO a una lista
     *
     * @param pddCompromisoDTO lista dto que se desea mapear a entidad
     * @return lista tipo ArchivoProcesado a partir del dto
     */
    List<PddCompromiso> pddCompromisosDTOToEntities(List<PddCompromisoDTO> pddCompromisoDTO);


}
