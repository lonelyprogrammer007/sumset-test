package co.gov.sdp.spdd.data.mapper;

import java.io.Serializable;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvTipoDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyectoInversionDTO;
import co.gov.sdp.spdd.data.model.bp.BpProyInvTipo;
import co.gov.sdp.spdd.data.model.bp.BpProyectoInversion;

/**
 * 
 * @author DANIEL
 *
 */
@Mapper
public interface BpProyInvTipoMapper extends Serializable {

	/**
     * Metodo que mapea una entidad a un dto
     *
     * @param bpProyInvTipo entidad que se desea mapear a dto
     * @return dto mapeado a partir de la entidad
     */
    @Mapping(source = "bpProyInvTipo.idTipo", target = "idTipo")
    @Mapping(source = "bpProyInvTipo.idLsTipo.idArgumento", target = "idLsTipo")
    @Mapping(source = "bpProyInvTipo.idLsTipo.resultado", target = "stringLsTipo")
    @Mapping(source = "bpProyInvTipo.idProyInversion.idProyInversion", target = "idProyInversion")
    BpProyInvTipoDTO bpProyInvTipoEntityToDTO(BpProyInvTipo bpProyInvTipo);

    /**
     * Metodo que mapea un dto a una entidad
     *
     * @param bpProyInvTipoDTO dto que se desea mapear a entidad
     * @return la entidad mapeada a partir del dto
     */
    @Mapping(source = "bpProyInvTipoDTO.idTipo", target = "idTipo")
    @Mapping(source = "bpProyInvTipoDTO.idLsTipo", target = "idLsTipo.idArgumento")
    @Mapping(source = "bpProyInvTipoDTO.idProyInversion", target = "idProyInversion.idProyInversion")
    BpProyInvTipo bpProyInvTipoDTOToEntity(BpProyInvTipoDTO bpProyInvTipoDTO);

    /**
     * Metodo que mapea una lista tipo BpProyInvTipo a una lista tipo dto
     *
     * @param bpProyInvTipos lista BpProyInvTipo de  que se desea mapear a dto
     * @return la lista dto mapeada a partir de la lista
     */
    List<BpProyInvTipoDTO> bpProyInvTiposEntitiesToDTO(List<BpProyInvTipo> bpProyInvTipos);

    /**
     * Metodo que mapea una lista BpProyInvTipo a una lista
     *
     * @param bpProyInvTiposDTO lista dto que se desea mapear a entidad
     * @return lista tipo BpProyInvTipo a partir del dto
     */
    List<BpProyInvTipo> bpProyInvTipoDTOToEntities(List<BpProyInvTipoDTO> bpProyInvTiposDTO);

	
}
