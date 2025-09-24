package co.gov.sdp.spdd.data.mapper;

import java.io.Serializable;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvLineaDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvLocalizaDTO;
import co.gov.sdp.spdd.data.model.bp.BpProyInvLinea;
import co.gov.sdp.spdd.data.model.bp.BpProyInvLocaliza;

@Mapper
public interface BpProyInvLocalizaMapper extends Serializable {

	/**
     * Metodo que mapea una entidad a un dto
     * @param bpProyInvLocaliza entidad que se desea mapear a dto
     * @return dto mapeado a partir de la entidad
     */
    @Mapping(source = "bpProyInvLocaliza.idProyLocaliza", target = "idProyLocaliza")
    @Mapping(source = "bpProyInvLocaliza.idProyInversion.idProyInversion", target = "idProyInversion")
    @Mapping(source = "bpProyInvLocaliza.idTerritorializacion.idTerritorializacion", target = "idTerritorializacion")
	BpProyInvLocalizaDTO BpProyInvLocalizaEntityToDTO(BpProyInvLocaliza bpProyInvLocaliza);
    
    /**
     * Metodo que mapea un dto a una entidad
     * @param bpProyInvLocalizaDTO dto que se desea mapear a entidad
     * @return la entidad mapeada a partir del dto
     */
    @Mapping(source = "bpProyInvLocalizaDTO.idProyLocaliza", target = "idProyLocaliza")
    @Mapping(source = "bpProyInvLocalizaDTO.idProyInversion", target = "idProyInversion.idProyInversion")
    @Mapping(source = "bpProyInvLocalizaDTO.idTerritorializacion", target = "idTerritorializacion.idTerritorializacion")
    BpProyInvLocaliza BpProyInvLocalizaDTOToEntity(BpProyInvLocalizaDTO bpProyInvLocalizaDTO);
    
    /**
     * Metodo que mapea una lista tipo BpProyInvLocalizaDTO a una lista tipo dto
     * @param bpProyInvLocaliza lista BpProyInvLocaliza de  que se desea mapear a dto
     * @return la lista dto mapeada a partir de la lista
     */
    List<BpProyInvLocalizaDTO> bpProyInvLocalizaEntitiesToDTO(List<BpProyInvLocaliza> bpProyInvLocaliza);

    /**
     * Metodo que mapea una lista BpProyInvLocaliza a una lista
     * @param bpProyInvLocalizaDTO lista dto que se desea mapear a entidad
     * @return lista tipo BpProyInvLocaliza a partir del dto
     */
    List<BpProyInvLocaliza> bpProyInvLocalizaDTOToEntities(List<BpProyInvLocalizaDTO> bpProyInvLocalizaDTO);
}
