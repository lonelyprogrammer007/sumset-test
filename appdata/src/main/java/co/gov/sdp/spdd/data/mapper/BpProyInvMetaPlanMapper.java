package co.gov.sdp.spdd.data.mapper;

import java.io.Serializable;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvMetaPlanDTO;
import co.gov.sdp.spdd.data.model.bp.BpProyInvMetaPlan;

/**
 * Clase que se encarga del mapeo de objetos entre BpProyInvMetaPlan y BpProyInvMetaPlanDTO
 * @author SEBASTIAN
 * @version 28/05/2020
 */
@Mapper
public interface BpProyInvMetaPlanMapper extends Serializable {

   /**
     * Metodo que mapea una entidad a un dto
     * @param bpProyInvMetaPlan entidad que se desea mapear a dto
     * @return dto mapeado a partir de la entidad
     */
	@Mapping(source = "bpProyInvMetaPlan.idProyMetaPlan", target = "idProyMetaPlan")
	@Mapping(source = "bpProyInvMetaPlan.idProyObjEspecif.idProyObjEspecif", target = "idProyObjEspecif")
	@Mapping(source = "bpProyInvMetaPlan.idMetaProducto.idMetaProducto", target = "idMetaProducto")
	BpProyInvMetaPlanDTO bpProyInvMetaPlanEntityToDTO(BpProyInvMetaPlan bpProyInvMetaPlan);
	
   /**
     * Metodo que mapea un dto a una entidad
     * @param bpProyInvMetaPlanDTO dto que se desea mapear a entidad
     * @return la entidad mapeada a partir del dto
     */
	@Mapping(source = "bpProyInvMetaPlanDTO.idProyMetaPlan", target = "idProyObjEspecif")
	@Mapping(source = "bpProyInvMetaPlanDTO.idProyObjEspecif", target = "idProyObjEspecif.idProyObjEspecif")
	@Mapping(source = "bpProyInvMetaPlanDTO.idMetaProducto", target = "idMetaProducto.idMetaProducto")
	BpProyInvMetaPlan bpProyInvMetaPlanDTOToEntity(BpProyInvMetaPlanDTO bpProyInvMetaPlanDTO);
	
   /**
     * Metodo que mapea una lista de entidades a una lista de DTO
     * @param listaBpProyInvMetaPlan lista entidades que se desea mapear a DTO
     * @return lista de DTO correspondiente.
     */
    List<BpProyInvMetaPlanDTO> bpProyInvMetaPlanEntitiesToDTO(List<BpProyInvMetaPlan> listaBpProyInvMetaPlan);

    /**
     * Metodo que mapea una lista de DTO a una lista de entidades
     * @param listaBpProyInvMetaPlanDTO lista dto que se desea mapear a entidad
     * @return lista de entidades correspondiente.
     */
    List<BpProyInvMetaPlan> bpProyInvMetaPlanDTOToEntities(List<BpProyInvMetaPlanDTO> listaBpProyInvMetaPlanDTO);

}
