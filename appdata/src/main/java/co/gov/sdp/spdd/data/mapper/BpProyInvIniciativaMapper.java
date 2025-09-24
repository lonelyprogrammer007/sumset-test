package co.gov.sdp.spdd.data.mapper;

import java.util.List;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvIniciativaDTO;
import co.gov.sdp.spdd.data.model.bp.BpProyInvIniciativa;

/**
 * Clase que se encarga del mapeo de objetos entre BpProyInvIniciativa y BpProyInvIniciativaDTO
 * @author DANIEL
 * @version 14/05/2020
 */
@Mapper
public interface BpProyInvIniciativaMapper {
	
	@AfterMapping
	default void afterMapping(@MappingTarget BpProyInvIniciativa target,BpProyInvIniciativaDTO source)
	{
		if (source.getIdIniciativa() == null)
		{
			target.setIdIniciativa(null);
		}
		if (source.getIdProyInversion() == null)
		{
			target.setIdProyInversion(null);
		}
	}
	
	/**
     * Metodo que mapea una entidad a un dto
     *
     * @param bpProyInvIniciativa entidad que se desea mapear a dto
     * @return dto mapeado a partir de la entidad
     */
    @Mapping(source = "bpProyInvIniciativa.idIniciativaInv", target = "idIniciativaInv")
    @Mapping(source = "bpProyInvIniciativa.fecha", target = "fecha", dateFormat = "dd-MM-yyyy")
    @Mapping(source = "bpProyInvIniciativa.idIniciativa.idIniciativa", target = "idIniciativa")
    @Mapping(source = "bpProyInvIniciativa.idIniciativa.nombre", target = "stringIniciativa")
    @Mapping(source = "bpProyInvIniciativa.idProyInversion.idProyInversion", target = "idProyInversion")
    @Mapping(source = "bpProyInvIniciativa.idProyInversion.codigo", target = "codigoProyInversion")
    @Mapping(source = "bpProyInvIniciativa.idProyInversion.nombre", target = "stringProyInversion")
    @Mapping(source = "bpProyInvIniciativa.idProyInversion.fechaVersion", target = "fechaVersionProyInversion", dateFormat = "dd-MM-yyyy")
    @Mapping(source = "bpProyInvIniciativa.idProyInversion.idLsEstado.idArgumento", target = "idLsEstadoProyInversion")
    @Mapping(source = "bpProyInvIniciativa.idProyInversion.idLsEstado.resultado", target = "stringLsEstadoProyInversion")
    BpProyInvIniciativaDTO bpProyInvIniciativaEntityToDTO(BpProyInvIniciativa bpProyInvIniciativa);

    /**
     * Metodo que mapea un dto a una entidad
     *
     * @param bpProyInvIniciativaDTO dto que se desea mapear a entidad
     * @return la entidad mapeada a partir del dto
     */
    @Mapping(source = "bpProyInvIniciativaDTO.idIniciativaInv", target = "idIniciativaInv")
    @Mapping(source = "bpProyInvIniciativaDTO.idIniciativa", target = "idIniciativa.idIniciativa")
    @Mapping(source = "bpProyInvIniciativaDTO.idProyInversion", target = "idProyInversion.idProyInversion")
    @Mapping(source = "bpProyInvIniciativaDTO.fecha", target = "fecha", dateFormat = "dd-MM-yyyy")
    BpProyInvIniciativa bpProyInvIniciativaDTOToEntity(BpProyInvIniciativaDTO bpProyInvIniciativaDTO);

    /**
     *  Metodo que mapea una lista de entidades a una lista de DTO
     *
     * @param listaBpProyInvIniciativa lista entidades que se desea mapear a DTO
     * @return lista de DTO correspondiente.
     */
    List<BpProyInvIniciativaDTO> bpProyInvIniciativaEntitiesToDTO(List<BpProyInvIniciativa> listaBpProyInvIniciativa);

    /**
     * Metodo que mapea una lista de DTO a una lista de entidades
     *
     * @param listaBpProyInvIniciativaDTO lista dto que se desea mapear a entidad
     * @return lista de entidades correspondiente.
     */
    List<BpProyInvIniciativa> bpProyInvIniciativaDTOToEntities(List<BpProyInvIniciativaDTO> listaBpProyInvIniciativaDTO);

}
