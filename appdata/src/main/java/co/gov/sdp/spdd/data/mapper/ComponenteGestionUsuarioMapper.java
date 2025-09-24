package co.gov.sdp.spdd.data.mapper;

import java.io.Serializable;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import co.gov.sdp.nhspdd.common.dto.ComponenteGestionUsuarioDTO;
import co.gov.sdp.spdd.data.model.afs.ComponenteGestionUsuario;

@Mapper
public interface ComponenteGestionUsuarioMapper extends Serializable {

    @Mapping(source = "componenteGestionUsuario.idGestionUsuario", target = "idGestionUsuario")
    @Mapping(source = "componenteGestionUsuario.codigoUsuario", target = "codigoUsuario")
    @Mapping(source = "componenteGestionUsuario.idComponenteGestion.idAtributo", target = "idComponenteGestion")
    @Mapping(source = "componenteGestionUsuario.idComponenteGestion.denominacion", target = "denominacion")
    ComponenteGestionUsuarioDTO componenteGestionUsuarioEntityToDTO(ComponenteGestionUsuario componenteGestionUsuario);

    @Mapping(source = "componenteGestionUsuarioDTO.idGestionUsuario", target = "idGestionUsuario")
    @Mapping(source = "componenteGestionUsuarioDTO.codigoUsuario", target = "codigoUsuario")
    @Mapping(source = "componenteGestionUsuarioDTO.idComponenteGestion", target = "idComponenteGestion.idAtributo")
    ComponenteGestionUsuario componenteGestionUsuarioDTOToEntity(
            ComponenteGestionUsuarioDTO componenteGestionUsuarioDTO);

    List<ComponenteGestionUsuario> componenteGestionUsuarioDTOToEntities(
            List<ComponenteGestionUsuarioDTO> componenteGestionUsuariosDTO);

    List<ComponenteGestionUsuarioDTO> componenteGestionUsuarioEntitiesToDTO(
            List<ComponenteGestionUsuario> componenteGestionUsuarios);
}
