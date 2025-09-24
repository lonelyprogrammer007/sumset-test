package co.gov.sdp.spdd.data.mapper;

import java.io.Serializable;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import co.gov.sdp.nhspdd.common.dto.bp.BpAporteCiudadanoDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvAporteDTO;
import co.gov.sdp.spdd.data.model.bp.BpAporteCiudadano;
import co.gov.sdp.spdd.data.model.bp.BpProyInvAporte;

/**
 * Clase que se encarga del mapeo de objetos entre BpProyInvAporte y BpProyInvAporteDTO
 * @author DANIEL
 * @version 16/04/2020
 */
@Mapper
public interface BpProyInvAporteMapper extends Serializable {
	
	/**
     * Metodo que mapea una entidad a un dto
     *
     * @param bpProyInvAporte entidad que se desea mapear a dto
     * @return dto mapeado a partir de la entidad
     */
    @Mapping(source = "bpProyInvAporte.idProyAporte", target = "idProyAporte")
    @Mapping(source = "bpProyInvAporte.idAporte.idAporte", target = "idAporte")
    @Mapping(source = "bpProyInvAporte.idAporte.accion", target = "stringAporte")
    @Mapping(source = "bpProyInvAporte.idProyInversion.idProyInversion", target = "idProyInversion")
    @Mapping(source = "bpProyInvAporte.idProyInversion.nombre", target = "stringProyInversion")
    BpProyInvAporteDTO bpProyInvAporteEntityToDTO(BpProyInvAporte bpProyInvAporte);

    /**
     * Metodo que mapea un dto a una entidad
     *
     * @param bpProyInvAporteDTO dto que se desea mapear a entidad
     * @return la entidad mapeada a partir del dto
     */
    @Mapping(source = "bpProyInvAporteDTO.idProyAporte", target = "idProyAporte")
    @Mapping(source = "bpProyInvAporteDTO.idAporte", target = "idAporte.idAporte")
    @Mapping(source = "bpProyInvAporteDTO.idProyInversion", target = "idProyInversion.idProyInversion")
    BpProyInvAporte bpProyInvAporteDTOToEntity(BpProyInvAporteDTO bpProyInvAporteDTO);

    /**
     *  Metodo que mapea una lista de entidades a una lista de DTO
     *
     * @param bpProyInvAportes lista entidades que se desea mapear a DTO
     * @return lista de DTO correspondiente.
     */
    List<BpProyInvAporteDTO> bpProyInvAportesEntitiesToDTO(List<BpProyInvAporte> bpProyInvAportes);

    /**
     * Metodo que mapea una lista de DTO a una lista de entidades
     *
     * @param bpProyInvAportesDTO lista dto que se desea mapear a entidad
     * @return lista de entidades correspondiente.
     */
    List<BpProyInvAporte> bpProyInvAporteDTOToEntities(List<BpProyInvAporteDTO> bpProyInvAportesDTO);


}
