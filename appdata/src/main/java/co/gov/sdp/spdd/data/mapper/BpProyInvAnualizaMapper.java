package co.gov.sdp.spdd.data.mapper;

import java.io.Serializable;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvAnualizaDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvFinanciaDTO;
import co.gov.sdp.spdd.data.model.bp.BpProyInvAnualiza;
import co.gov.sdp.spdd.data.model.bp.BpProyInvFinancia;

/**
 * Clase que se encarga del mapeo de objetos entre BpProyInvAnualiza y BpProyInvAnualizaDTO
 * @author Jefferson Arenas Castaño
 * @version 02/06/2020
 */
@Mapper
public interface BpProyInvAnualizaMapper extends Serializable {
	
	/**
     * Metodo que mapea una entidad a un dto
     * @param bpProyInvAnualiza entidad que se desea mapear a dto
     * @return dto mapeado a partir de la entidad
     */
	@Mapping(source = "bpProyInvAnualiza.idPryAnualiza", target = "idPryAnualiza")
	@Mapping(source = "bpProyInvAnualiza.vigencia", target = "vigencia")
	@Mapping(source = "bpProyInvAnualiza.monto", target = "monto")
	@Mapping(source = "bpProyInvAnualiza.idFuente.idFuente", target = "idFuente")
	@Mapping(source = "bpProyInvAnualiza.idFuente.idLsFuente.argumento", target = "stringFuenteLsFuente")
	BpProyInvAnualizaDTO bpProyInvAnualizEntityToDTO(BpProyInvAnualiza bpProyInvAnualiza);
	
   /**
     * Metodo que mapea un dto a una entidad
     * @param BpProyInvAnualizaDTO dto que se desea mapear a entidad
     * @return la entidad mapeada a partir del dto
     */
	@Mapping(source = "bpProyInvAnualizaDTO.idPryAnualiza", target = "idPryAnualiza")
	@Mapping(source = "bpProyInvAnualizaDTO.vigencia", target = "vigencia")
	@Mapping(source = "bpProyInvAnualizaDTO.monto", target = "monto")
	@Mapping(source = "bpProyInvAnualizaDTO.idFuente", target = "idFuente.idFuente")
	BpProyInvAnualiza bpProyInvAnualizaDTOToEntity(BpProyInvAnualizaDTO bpProyInvAnualizaDTO);
	
   /**
     * Metodo que mapea una lista de entidades a una lista de DTO
     * @param listaBpProyInvFinancia lista entidades que se desea mapear a DTO
     * @return lista de DTO correspondiente.
     */
    List<BpProyInvAnualizaDTO> bpProyInvAnualizaEntitiesToDTO(List<BpProyInvAnualiza> listaBpProyInvFinancia);

    /**
     * Metodo que mapea una lista de DTO a una lista de entidades
     * @param listaBpProyInvFinanciaDTO lista dto que se desea mapear a entidad
     * @return lista de entidades correspondiente.
     */
    List<BpProyInvAnualiza> bpProyInvAnualizafDTOToEntities(List<BpProyInvAnualizaDTO> listaBpProyInvFinanciaDTO);

	
	
}
