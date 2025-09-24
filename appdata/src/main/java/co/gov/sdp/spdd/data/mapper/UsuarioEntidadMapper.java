package co.gov.sdp.spdd.data.mapper;

import java.io.Serializable;
import java.util.List;

import org.mapstruct.BeforeMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import co.gov.sdp.nhspdd.common.dto.UsuarioEntidadDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddPrbValoracionDTO;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.spdd.data.model.afs.UsuarioEntidad;
import co.gov.sdp.spdd.data.model.ip.PddPrbValoracion;

@Mapper(uses = {EntidadMapper.class})
public interface UsuarioEntidadMapper extends Serializable {
	
	/**
	 * Permite realizar la asignacion de valores a variables segun alguna condicion.
	 * @param target objeto al cual se le va asignar el valor
	 * @param source objeto que se tiene como referencia para hacer las condiciones
	 */
	@BeforeMapping
	default void beforeMapping(@MappingTarget UsuarioEntidadDTO target,UsuarioEntidad source) {
		if (source.getModificarDatos() == 1) 
			target.setStringModificar(NHSPDDConstantes.ESTADO_STRING_ACTIVO);
		else
			target.setStringModificar(NHSPDDConstantes.ESTADO_STRING_INACTIVO);

	}

    @Mapping(source = "usuarioEntidad.idUsuarioEntidad", target = "idUsuarioEntidad")
    @Mapping(source = "usuarioEntidad.codigoUsuario", target = "codigoUsuario")
    @Mapping(source = "usuarioEntidad.codigoEntidad.codigoEntidad", target = "codigoEntidad")
    @Mapping(source = "usuarioEntidad.modificarDatos", target = "modificarDatos")
    @Mapping(source = "usuarioEntidad.codigoEntidad.idBancoProyecto.idBancoProyecto", target = "idBancoProyecto")
    @Mapping(source = "usuarioEntidad.codigoEntidad.idBancoProyecto.nombre", target = "stringBancoProyecto")
    @Mapping(target = "stringModificar", ignore = true)
    UsuarioEntidadDTO usuarioEntidadEntityToDTO(UsuarioEntidad usuarioEntidad);

    @Mapping(source = "usuarioEntidadDTO.idUsuarioEntidad", target = "idUsuarioEntidad")
    @Mapping(source = "usuarioEntidadDTO.codigoEntidad", target = "codigoEntidad.codigoEntidad")
    @Mapping(source = "usuarioEntidadDTO.codigoUsuario", target = "codigoUsuario")
    @Mapping(source = "usuarioEntidadDTO.modificarDatos", target = "modificarDatos")
    UsuarioEntidad usuarioEntidadDTOToEntity(UsuarioEntidadDTO usuarioEntidadDTO);

    List<UsuarioEntidad> usuarioEntidadDTOToEntities(List<UsuarioEntidadDTO> usuarioEntidadDTO);

    List<UsuarioEntidadDTO> usuarioEntidadDTOEntitiesToDTO(List<UsuarioEntidad> usuarioEntidad);
}
