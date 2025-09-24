package co.gov.sdp.spdd.data.mapper;

import java.io.Serializable;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvEspecifDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvFinanciaDTO;
import co.gov.sdp.spdd.data.model.bp.BpProyInvEspecif;
import co.gov.sdp.spdd.data.model.bp.BpProyInvFinancia;

/**
 * Clase que se encarga del mapeo de objetos entre BpProyInvFinancia y BpProyInvFinanciaDTO
 * @author Jefferson Arenas Castaño
 * @version 02/06/2020
 */
@Mapper
public interface BpProyInvFinanciaMapper extends Serializable {
	/**
     * Metodo que mapea una entidad a un dto
     * @param bpProyInvFinancia entidad que se desea mapear a dto
     * @return dto mapeado a partir de la entidad
     */
	@Mapping(source = "bpProyInvFinancia.idFuente", target = "idFuente")
	@Mapping(source = "bpProyInvFinancia.idLsFuente.idArgumento", target = "idLsFuente")
	@Mapping(source = "bpProyInvFinancia.idProyInversion.idProyInversion", target = "idProyInversion")
	@Mapping(source = "bpProyInvFinancia.idLsFuente.resultado", target = "stringLsFuente")
	@Mapping(source = "bpProyInvFinancia.idProyInversion.nombre", target = "stringProyInversion")
	BpProyInvFinanciaDTO bpProyInvFinanciaEntityToDTO(BpProyInvFinancia bpProyInvFinancia);
	
   /**
     * Metodo que mapea un dto a una entidad
     * @param BpProyInvFinanciaDTO dto que se desea mapear a entidad
     * @return la entidad mapeada a partir del dto
     */
	@Mapping(source = "bpProyInvFinanciaDTO.idFuente", target = "idFuente")
	@Mapping(source = "bpProyInvFinanciaDTO.idLsFuente", target = "idLsFuente.idArgumento")
	@Mapping(source = "bpProyInvFinanciaDTO.idProyInversion", target = "idProyInversion.idProyInversion")
	BpProyInvFinancia bpProyInvFinanciaDTOToEntity(BpProyInvFinanciaDTO bpProyInvFinanciaDTO);
	
   /**
     * Metodo que mapea una lista de entidades a una lista de DTO
     * @param listaBpProyInvFinancia lista entidades que se desea mapear a DTO
     * @return lista de DTO correspondiente.
     */
    List<BpProyInvFinanciaDTO> bpProyInvFinanciaEntitiesToDTO(List<BpProyInvFinancia> listaBpProyInvFinancia);

    /**
     * Metodo que mapea una lista de DTO a una lista de entidades
     * @param listaBpProyInvFinanciaDTO lista dto que se desea mapear a entidad
     * @return lista de entidades correspondiente.
     */
    List<BpProyInvFinancia> bpProyInvFinanciaDTOToEntities(List<BpProyInvFinanciaDTO> listaBpProyInvFinanciaDTO);

}
