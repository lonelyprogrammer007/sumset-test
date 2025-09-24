package co.gov.sdp.spdd.data.mapper;

import java.io.Serializable;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvPoliticaDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvProductoDTO;
import co.gov.sdp.spdd.data.model.bp.BpProyInvPolitica;
import co.gov.sdp.spdd.data.model.bp.BpProyInvProducto;

/**
 * Clase que se encarga del mapeo de objetos entre BpProyInvPolitica y BpProyInvPoliticaDTO
 * @author DANIEL
 * @version 1.0 08/06/2020
 *
 */
@Mapper
public interface BpProyInvPoliticaMapper extends Serializable {
	
	/**
     * Metodo que mapea una entidad a un dto
     * @param bpProyInvPolitica entidad que se desea mapear a dto
     * @return dto mapeado a partir de la entidad
     */
	@Mapping(source = "bpProyInvPolitica.idProyPolitica", target = "idProyPolitica")
	@Mapping(source = "bpProyInvPolitica.idPolPub.idPolPub", target = "idPolPub")
	@Mapping(source = "bpProyInvPolitica.idPolPub.politica", target = "stringPolPub")
	@Mapping(source = "bpProyInvPolitica.idProyInversion.idProyInversion", target = "idProyInversion")
	@Mapping(source = "bpProyInvPolitica.idProyInversion.nombre", target = "stringProyInversion")
	BpProyInvPoliticaDTO bpProyInvPoliticaEntityToDTO(BpProyInvPolitica bpProyInvPolitica);
	
   /**
     * Metodo que mapea un dto a una entidad
     * @param bpProyInvPoliticaDTO dto que se desea mapear a entidad
     * @return la entidad mapeada a partir del dto
     */
	@Mapping(source = "bpProyInvPoliticaDTO.idProyPolitica", target = "idProyPolitica")
	@Mapping(source = "bpProyInvPoliticaDTO.idPolPub", target = "idPolPub.idPolPub")
	@Mapping(source = "bpProyInvPoliticaDTO.idProyInversion", target = "idProyInversion.idProyInversion")
	BpProyInvPolitica bpProyInvPoliticaDTOToEntity(BpProyInvPoliticaDTO bpProyInvPoliticaDTO);
	
   /**
     * Metodo que mapea una lista de entidades a una lista de DTO
     * @param listaBpProyInvPolitica lista entidades que se desea mapear a DTO
     * @return lista de DTO correspondiente.
     */
    List<BpProyInvPoliticaDTO> bpProyInvPoliticaEntitiesToDTO(List<BpProyInvPolitica> listaBpProyInvPolitica);

    /**
     * Metodo que mapea una lista de DTO a una lista de entidades
     * @param listaBpProyInvPoliticaDTO lista dto que se desea mapear a entidad
     * @return lista de entidades correspondiente.
     */
    List<BpProyInvPolitica> bpProyInvPoliticaDTOToEntities(List<BpProyInvPoliticaDTO> listaBpProyInvPoliticaDTO);


}
