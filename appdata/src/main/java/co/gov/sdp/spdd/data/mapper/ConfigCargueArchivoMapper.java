package co.gov.sdp.spdd.data.mapper;

import java.io.Serializable;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import co.gov.sdp.nhspdd.common.dto.ConfiguracionCargueArchivoDTO;
import co.gov.sdp.spdd.data.model.afs.ConfigCargueArchivo;

@Mapper
public interface ConfigCargueArchivoMapper extends Serializable {
	
	@Mapping(source = "configCargueArchivo.idConfigCargue", target = "idConfigCargue")
    @Mapping(source = "configCargueArchivo.configuracion", target = "configuracion")
    @Mapping(source = "configCargueArchivo.idLsTema.idArgumento", target = "idLsTema")
	@Mapping(source = "configCargueArchivo.idLsTema.argumento", target = "nombreTema")
    @Mapping(source = "configCargueArchivo.idLsModulo.idArgumento", target = "idLsModulo")
	ConfiguracionCargueArchivoDTO configuracionCargueEntityToDTO(ConfigCargueArchivo configCargueArchivo);

    @Mapping(source = "configuracionCargueArchivoDTO.idConfigCargue", target = "idConfigCargue")
    @Mapping(source = "configuracionCargueArchivoDTO.configuracion", target = "configuracion")
    @Mapping(source = "configuracionCargueArchivoDTO.idLsTema", target = "idLsTema.idArgumento")
    @Mapping(source = "configuracionCargueArchivoDTO.idLsModulo", target = "idLsModulo.idArgumento")
    ConfigCargueArchivo configCargueArchivoDTOToEntity(ConfiguracionCargueArchivoDTO configuracionCargueArchivoDTO);

    List<ConfigCargueArchivo> configCargueArchivoDTOToEntities(List<ConfiguracionCargueArchivoDTO> configuracionCargueArchivoDTO);

    List<ConfiguracionCargueArchivoDTO> configCargueArchivoEntitiesToDTO(List<ConfigCargueArchivo> configCargueArchivo);

}
