package co.gov.sdp.spdd.data.mapper;

import java.io.Serializable;
import java.util.List;

import org.mapstruct.BeforeMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import co.gov.sdp.nhspdd.common.dto.TerritorializacionDTO;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.spdd.data.model.afs.Territorializacion;

/**
 * Interface mapper que realiza el mapeo de un dto a entidad y viceversa
 *
 * @author Bryan Munoz
 */
@Mapper(uses = {ArgumentoListaSimpleMapper.class})
public interface TerritorializacionMapper extends Serializable {

	/**
	 * 
	 * @param target
	 * @param source
	 */
	@BeforeMapping
	default void beforeMapping(@MappingTarget TerritorializacionDTO target,
			Territorializacion source) {
		if (source.getEstado() == 0) {
			target.setStringEstado(NHSPDDConstantes.ESTADO_STRING_INACTIVO);
		} else  {
			target.setStringEstado(NHSPDDConstantes.ESTADO_STRING_ACTIVO);
		}
	}
	
    /**
     * Metodo que mapea una entidad a un dto
     *
     * @param territorializacion entidad a mapear
     * @return un dto mapeado a partir de una entidad
     */
    @Mapping(source = "territorializacion.idTerritorializacion", target = "idTerritorializacion")
    @Mapping(source = "territorializacion.estado", target = "estado")
    @Mapping(source = "territorializacion.idLsBarrio.idArgumento", target = "idLsBarrio")
    @Mapping(source = "territorializacion.idLsLocalidad.idArgumento", target = "idLsLocalidad")
    @Mapping(source = "territorializacion.idLsUpr.idArgumento", target = "idLsUpr")
    @Mapping(source = "territorializacion.idLsUpz.idArgumento", target = "idLsUpz")
    @Mapping(source = "territorializacion.idLsVereda.idArgumento", target = "idLsVereda")
    @Mapping(source = "territorializacion.idLsBarrio.resultado", target = "nombreBarrio", defaultValue = "no asignado")
    @Mapping(source = "territorializacion.idLsLocalidad.resultado", target = "nombreLocalidad")
    @Mapping(source = "territorializacion.idLsUpr.resultado", target = "nombreUpr", defaultValue = "no asignado")
    @Mapping(source = "territorializacion.idLsUpz.resultado", target = "nombreUpz", defaultValue = "no asignado")
    @Mapping(source = "territorializacion.idLsVereda.resultado", target = "nombreVereda", defaultValue = "no asignado")
    TerritorializacionDTO territorializacionEntityToDTO(Territorializacion territorializacion);

    /**
     * Metodo que mapea un dto a una entidad
     *
     * @param territorializacionDTO dto a mapear
     * @return una entidad mapeada a partir de un dto
     */
    @Mapping(source = "territorializacionDTO.idTerritorializacion", target = "idTerritorializacion")
    @Mapping(source = "territorializacionDTO.estado", target = "estado")
    @Mapping(source = "territorializacionDTO.idLsBarrio", target = "idLsBarrio.idArgumento")
    @Mapping(source = "territorializacionDTO.idLsLocalidad", target = "idLsLocalidad.idArgumento")
    @Mapping(source = "territorializacionDTO.idLsUpr", target = "idLsUpr.idArgumento")
    @Mapping(source = "territorializacionDTO.idLsUpz", target = "idLsUpz.idArgumento")
    @Mapping(source = "territorializacionDTO.idLsVereda", target = "idLsVereda.idArgumento")
    Territorializacion territorializacionDTOToEntity(TerritorializacionDTO territorializacionDTO);

    /**
     * Metodo que mapea una lista tipo territorializacion a una lista
     * territorializacionesDTO
     *
     * @param territorializaciones lista a mapear
     * @return un lista tipo dto mapeada a partir de lista tipo
     * territorializacion
     */
    List<TerritorializacionDTO> territorializacionEntitiesToDTO(List<Territorializacion> territorializaciones);

    /**
     * Metodo que mapea una lista tipo territorializacionesDTO a una lista
     * territorializaciones
     *
     * @param territorializacionesDTO lista a mapear
     * @return una lista tipo territorializacion a partir de la lista
     * territorializacionDTO
     */
    List<Territorializacion> territorializacionDTOToEntities(List<TerritorializacionDTO> territorializacionesDTO);

}
