package co.gov.sdp.spdd.data.mapper;

import java.io.Serializable;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import co.gov.sdp.nhspdd.common.dto.ProyectosInversionUsuarioDTO;
import co.gov.sdp.spdd.data.model.afs.ProyectosInversionUsuario;

@Mapper(uses = {ProyectoInversionMapper.class})
public interface ProyectosInversionUsuarioMapper extends Serializable {

    @Mapping(source = "proyectosInversionUsuario.idProyectoUsuario", target = "idProyectoUsuario")
    @Mapping(source = "proyectosInversionUsuario.idProyectoInversion.idProyectoInversion", target = "idProyectoInversion")
    @Mapping(source = "proyectosInversionUsuario.codigoUsuario", target = "codigoUsuario")
    @Mapping(source = "proyectosInversionUsuario.idProyectoInversion.nombre", target = "nombre")
    ProyectosInversionUsuarioDTO proyectosInversionUsuarioEntityToDTO(
            ProyectosInversionUsuario proyectosInversionUsuario);

    @Mapping(source = "proyectosInversionUsuarioDTO.idProyectoUsuario", target = "idProyectoUsuario")
    @Mapping(source = "proyectosInversionUsuarioDTO.idProyectoInversion", target = "idProyectoInversion.idProyectoInversion")
    @Mapping(source = "proyectosInversionUsuarioDTO.codigoUsuario", target = "codigoUsuario")
    ProyectosInversionUsuario proyectosInversionUsuarioDTOToEntity(
            ProyectosInversionUsuarioDTO proyectosInversionUsuarioDTO);

    List<ProyectosInversionUsuarioDTO> proyectosInversionUsuarioEntitiesToDTO(
            List<ProyectosInversionUsuario> ProyectosInversionUsuario);

    List<ProyectosInversionUsuario> proyectosInversionUsuarioDTOToEntities(
            List<ProyectosInversionUsuarioDTO> proyectosInversionUsuarioDTO);

}
