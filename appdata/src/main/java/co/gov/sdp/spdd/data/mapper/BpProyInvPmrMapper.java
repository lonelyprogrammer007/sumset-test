package co.gov.sdp.spdd.data.mapper;

import java.io.Serializable;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvPmrDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvPoliticaDTO;
import co.gov.sdp.spdd.data.model.bp.BpProyInvPmr;
import co.gov.sdp.spdd.data.model.bp.BpProyInvPolitica;

/**
 * Clase que se encarga del mapeo de objetos entre BpProyInvPmr y BpProyInvPmrDTO
 * @author DANIEL
 * @version 1.0 08/06/2020
 *
 */
@Mapper
public interface BpProyInvPmrMapper extends Serializable {
	
	/**
     * Metodo que mapea una entidad a un dto
     * @param bpProyInvPmr entidad que se desea mapear a dto
     * @return dto mapeado a partir de la entidad
     */
	@Mapping(source = "bpProyInvPmr.idProyPmr", target = "idProyPmr")
	@Mapping(source = "bpProyInvPmr.idIndMr.idIndProxy", target = "idIndMr")
	@Mapping(source = "bpProyInvPmr.idIndMr.idIndicador.idIndicador", target = "idIndMrIdIndicador")
	@Mapping(source = "bpProyInvPmr.idIndMr.idIndicador.nombre", target = "idIndMrStringIndicador")
	@Mapping(source = "bpProyInvPmr.idIndMr.idMetaResultado.idMetaResultado", target = "idIndMrIdMetaResultadol")
	@Mapping(source = "bpProyInvPmr.idProyInversion.idProyInversion", target = "idProyInversion")
	@Mapping(source = "bpProyInvPmr.idProyInversion.nombre", target = "stringProyInversion")
	BpProyInvPmrDTO bpProyInvPmrEntityToDTO(BpProyInvPmr bpProyInvPmr);
	
   /**
     * Metodo que mapea un dto a una entidad
     * @param bpProyInvPmrDTO dto que se desea mapear a entidad
     * @return la entidad mapeada a partir del dto
     */
	@Mapping(source = "bpProyInvPmrDTO.idProyPmr", target = "idProyPmr")
	@Mapping(source = "bpProyInvPmrDTO.idIndMr", target = "idIndMr.idIndProxy")
	@Mapping(source = "bpProyInvPmrDTO.idProyInversion", target = "idProyInversion.idProyInversion")
	BpProyInvPmr bpProyInvPmrDTOToEntity(BpProyInvPmrDTO bpProyInvPmrDTO);
	
   /**
     * Metodo que mapea una lista de entidades a una lista de DTO
     * @param listaBpProyInvPmr lista entidades que se desea mapear a DTO
     * @return lista de DTO correspondiente.
     */
    List<BpProyInvPmrDTO> bpProyInvPmrEntitiesToDTO(List<BpProyInvPmr> listaBpProyInvPmr);

    /**
     * Metodo que mapea una lista de DTO a una lista de entidades
     * @param listaBpProyInvPmrDTO lista dto que se desea mapear a entidad
     * @return lista de entidades correspondiente.
     */
    List<BpProyInvPmr> bpProyInvPmrDTOToEntities(List<BpProyInvPmrDTO> listaBpProyInvPmrDTO);

}
