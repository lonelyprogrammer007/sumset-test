package co.gov.sdp.spdd.data.mapper;

import java.io.Serializable;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvEspecifDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvIniciativaDTO;
import co.gov.sdp.spdd.data.model.bp.BpProyInvEspecif;
import co.gov.sdp.spdd.data.model.bp.BpProyInvIniciativa;

/**
 * Clase que se encarga del mapeo de objetos entre BpProyInvEspecif y BpProyInvEspecifDTO
 * @author SEBASTIAN
 * @version 28/05/2020
 */
@Mapper
public interface BpProyInvEspecifMapper extends Serializable {

   /**
     * Metodo que mapea una entidad a un dto
     * @param bpProyInvEspecif entidad que se desea mapear a dto
     * @return dto mapeado a partir de la entidad
     */
	@Mapping(source = "bpProyInvEspecif.idProyObjEspecif", target = "idProyObjEspecif")
	@Mapping(source = "bpProyInvEspecif.especifico", target = "especifico")
	@Mapping(source = "bpProyInvEspecif.idProyInversion.idProyInversion", target = "idProyInversion")
	BpProyInvEspecifDTO bpProyInvEspecifEntityToDTO(BpProyInvEspecif bpProyInvEspecif);
	
   /**
     * Metodo que mapea un dto a una entidad
     * @param bpProyInvEspecifDTO dto que se desea mapear a entidad
     * @return la entidad mapeada a partir del dto
     */
	@Mapping(source = "bpProyInvEspecifDTO.idProyObjEspecif", target = "idProyObjEspecif")
	@Mapping(source = "bpProyInvEspecifDTO.especifico", target = "especifico")
	@Mapping(source = "bpProyInvEspecifDTO.idProyInversion", target = "idProyInversion.idProyInversion")
	BpProyInvEspecif bpProyInvEspecifDTOToEntity(BpProyInvEspecifDTO bpProyInvEspecifDTO);
	
   /**
     * Metodo que mapea una lista de entidades a una lista de DTO
     * @param listaBpProyInvEspecif lista entidades que se desea mapear a DTO
     * @return lista de DTO correspondiente.
     */
    List<BpProyInvEspecifDTO> bpProyInvEspecifEntitiesToDTO(List<BpProyInvEspecif> listaBpProyInvEspecif);

    /**
     * Metodo que mapea una lista de DTO a una lista de entidades
     * @param listaBpProyInvEspecifDTO lista dto que se desea mapear a entidad
     * @return lista de entidades correspondiente.
     */
    List<BpProyInvEspecif> bpProyInvEspecifDTOToEntities(List<BpProyInvEspecifDTO> listaBpProyInvEspecifDTO);
}
