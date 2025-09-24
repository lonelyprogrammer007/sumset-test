package co.gov.sdp.spdd.data.mapper;

import java.io.Serializable;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvLineaDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvTipoDTO;
import co.gov.sdp.spdd.data.model.bp.BpProyInvLinea;
import co.gov.sdp.spdd.data.model.bp.BpProyInvTipo;

/**
 * 
 * @author DANIEL
 *
 */
@Mapper
public interface BpProyInvLineaMapper extends Serializable {
	
	/**
     * Metodo que mapea una entidad a un dto
     *
     * @param bpProyInvLinea entidad que se desea mapear a dto
     * @return dto mapeado a partir de la entidad
     */
    @Mapping(source = "bpProyInvLinea.idProyLinea", target = "idProyLinea")
    @Mapping(source = "bpProyInvLinea.idProyInversion.idProyInversion", target = "idProyInversion")
    @Mapping(source = "bpProyInvLinea.idProyInversion.nombre", target = "stringProyInversion")
    @Mapping(source = "bpProyInvLinea.idLineaInversion.descripcion", target = "stringLineaInversion")
    @Mapping(source = "bpProyInvLinea.idLineaInversion.idLineaInversion", target = "idLineaInversion")
    @Mapping(source = "bpProyInvLinea.idLineaInversion.idLsSector.idArgumento", target = "idLsSectorLineaInversion")
    @Mapping(source = "bpProyInvLinea.idLineaInversion.idLsSector.resultado", target = "stringLsSectorLineaInversion")
    @Mapping(source = "bpProyInvLinea.idLineaInversion.concepto", target = "conceptoLineaInversion")
    BpProyInvLineaDTO bpProyInvLineaEntityToDTO(BpProyInvLinea bpProyInvLinea);

    /**
     * Metodo que mapea un dto a una entidad
     *
     * @param bpProyInvLineaDTO dto que se desea mapear a entidad
     * @return la entidad mapeada a partir del dto
     */
    @Mapping(source = "bpProyInvLineaDTO.idProyLinea", target = "idProyLinea")
    @Mapping(source = "bpProyInvLineaDTO.idProyInversion", target = "idProyInversion.idProyInversion")
    @Mapping(source = "bpProyInvLineaDTO.idLineaInversion", target = "idLineaInversion.idLineaInversion")
    BpProyInvLinea bpProyInvLineaDTOToEntity(BpProyInvLineaDTO bpProyInvLineaDTO);

    /**
     * Metodo que mapea una lista tipo BpProyInvLinea a una lista tipo dto
     *
     * @param bpProyInvLineas lista BpProyInvTipo de  que se desea mapear a dto
     * @return la lista dto mapeada a partir de la lista
     */
    List<BpProyInvLineaDTO> bpProyInvLineasEntitiesToDTO(List<BpProyInvLinea> bpProyInvLineas);

    /**
     * Metodo que mapea una lista BpProyInvTipo a una lista
     *
     * @param bpProyInvLineasDTO lista dto que se desea mapear a entidad
     * @return lista tipo BpProyInvLinea a partir del dto
     */
    List<BpProyInvLinea> bpProyInvLineasDTOToEntities(List<BpProyInvLineaDTO> bpProyInvLineasDTO);


}
