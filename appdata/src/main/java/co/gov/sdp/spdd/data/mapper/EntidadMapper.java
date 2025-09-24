package co.gov.sdp.spdd.data.mapper;

import java.io.Serializable;
import java.util.List;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import co.gov.sdp.nhspdd.common.dto.EntidadDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddPrbValoracionDTO;
import co.gov.sdp.spdd.data.model.afs.Entidad;
import co.gov.sdp.spdd.data.model.ip.PddPrbValoracion;

@Mapper(uses = {BancoDeProyectoMapper.class})
public interface EntidadMapper extends Serializable {
	
	@AfterMapping
	default void afterMapping(@MappingTarget Entidad target,EntidadDTO source)
	{
		if (source.getIdBancoProyecto() == null)
			target.setIdBancoProyecto(null);
		if (source.getIdLsAsociacion() == null)
			target.setIdLsAsociacion(null);
		if (source.getIdLsClasificacion() == null)
			target.setIdLsClasificacion(null);
	}
	
    @Mapping(source = "entidad.codigoEntidad", target = "codigoEntidad")
    @Mapping(source = "entidad.gestionProyectos", target = "gestionProyectos")
    @Mapping(source = "entidad.gestionUsuarios", target = "gestionUsuarios")
    @Mapping(source = "entidad.idLsClasificacion.idArgumento", target = "idLsClasificacion")
    @Mapping(source = "entidad.idLsClasificacion.resultado", target = "stringClasificacion")
    @Mapping(source = "entidad.idLsAsociacion.idArgumento", target = "idLsAsociacion")
    @Mapping(source = "entidad.idLsAsociacion.resultado", target = "stringAsociacion")
    @Mapping(source = "entidad.idBancoProyecto.idBancoProyecto", target = "idBancoProyecto")
    @Mapping(source = "entidad.idBancoProyecto.nombre", target = "stringBancoProyecto")
    EntidadDTO entidadEntityToDTO(Entidad entidad);

    @Mapping(source = "entidadDTO.codigoEntidad", target = "codigoEntidad")
    @Mapping(source = "entidadDTO.gestionProyectos", target = "gestionProyectos")
    @Mapping(source = "entidadDTO.gestionUsuarios", target = "gestionUsuarios")
    @Mapping(source = "entidadDTO.idLsClasificacion", target = "idLsClasificacion.idArgumento")
    @Mapping(source = "entidadDTO.idLsAsociacion", target = "idLsAsociacion.idArgumento")
    @Mapping(source = "entidadDTO.idBancoProyecto", target = "idBancoProyecto.idBancoProyecto")
    Entidad entidadDTOToEntity(EntidadDTO entidadDTO);

    List<EntidadDTO> entidadEntitiesToDTO(List<Entidad> entidad);

    List<Entidad> entidadDTOToEntities(List<EntidadDTO> entidadDTO);

}
