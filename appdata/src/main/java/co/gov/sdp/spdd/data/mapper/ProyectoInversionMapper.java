package co.gov.sdp.spdd.data.mapper;

import java.io.Serializable;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import co.gov.sdp.nhspdd.common.dto.ProyectoInversionDTO;
import co.gov.sdp.spdd.data.model.afs.ProyectoInversion;

@Mapper
public interface ProyectoInversionMapper extends Serializable {

    @Mapping(source = "idProyectoInversion", target = "idProyectoInversion")
    @Mapping(source = "nombre", target = "nombre")
    ProyectoInversionDTO proyectoInversionEntityToDTO(ProyectoInversion proyectoInversion);

    @Mapping(source = "idProyectoInversion", target = "idProyectoInversion")
    @Mapping(source = "nombre", target = "nombre")
    ProyectoInversion proyectoInversionDTOToEntity(ProyectoInversionDTO proyectoInversionDTO);

    List<ProyectoInversionDTO> proyectoInversionEntitiesToDTO(List<ProyectoInversion> ProyectosInversion);

    List<ProyectoInversion> proyectosInversionUsuarioDTOToEntities(List<ProyectoInversionDTO> proyectoInversionDTO);
}
