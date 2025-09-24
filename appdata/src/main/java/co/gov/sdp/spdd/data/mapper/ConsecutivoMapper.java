package co.gov.sdp.spdd.data.mapper;

import java.io.Serializable;
import java.util.List;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import co.gov.sdp.nhspdd.common.dto.ConsecutivoDTO;
import co.gov.sdp.spdd.data.model.afs.Consecutivo;

/**
 * Interface que mapea un dto a una entidad y viceversa
 *
 * @author Bryan Munoz
 *
 */
@Mapper
public interface ConsecutivoMapper extends Serializable {
	
	@AfterMapping
	default void afterMapping(@MappingTarget Consecutivo target,ConsecutivoDTO source)
	{
		if (source.getCodigoEntidad() == null)
			target.setCodigoEntidad(null);		
	}

    @Mapping(source = "consecutivo.idConsecutivo", target = "idConsecutivo")
    @Mapping(source = "consecutivo.nombre", target = "nombre")
    @Mapping(source = "consecutivo.secuencia", target = "secuencia")
    @Mapping(source = "consecutivo.idPlanDesarrollo.idPlanDesarrollo", target = "idPlanDesarrollo")
    @Mapping(source = "consecutivo.idPlanDesarrollo.nombrePlan", target = "stringPlanDesarrollo")
    @Mapping(source = "consecutivo.descripcion", target = "descripcion")
    @Mapping(source = "consecutivo.vigencia", target = "vigencia")
    @Mapping(source = "consecutivo.codigoEntidad.codigoEntidad", target = "codigoEntidad")
    ConsecutivoDTO consecutivoEntityToDTO(Consecutivo consecutivo);

    @Mapping(source = "consecutivoDTO.idConsecutivo", target = "idConsecutivo")
    @Mapping(source = "consecutivoDTO.nombre", target = "nombre")
    @Mapping(source = "consecutivoDTO.secuencia", target = "secuencia")
    @Mapping(source = "consecutivoDTO.idPlanDesarrollo", target = "idPlanDesarrollo.idPlanDesarrollo")
    @Mapping(source = "consecutivoDTO.descripcion", target = "descripcion")
    @Mapping(source = "consecutivoDTO.vigencia", target = "vigencia")
    @Mapping(source = "consecutivoDTO.codigoEntidad", target = "codigoEntidad.codigoEntidad")
    Consecutivo consecutivoDTOTOEntity(ConsecutivoDTO consecutivoDTO);

    List<ConsecutivoDTO> consecutivoEntitiesToDTO(List<Consecutivo> consecutivos);

    List<Consecutivo> consecutivoDTOToEntities(List<ConsecutivoDTO> consecutivosDTO);
}
