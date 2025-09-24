package co.gov.sdp.spdd.data.mapper;

import java.io.Serializable;
import java.util.List;

import org.mapstruct.BeforeMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import co.gov.sdp.nhspdd.common.dto.EstructuraMetaDTO;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.spdd.data.model.afs.EstructuraMeta;

/**
 * Interface mapper de la clase Estructura Meta
 *
 * @author Raul Londono Murillo
 *
 */
@Mapper(uses = { ArgumentoListaSimpleMapper.class })
public interface EstructuraMetaMapper extends Serializable {

	/**
	 * 
	 * @param target
	 * @param source
	 */
	@BeforeMapping
	default void beforeMapping(@MappingTarget EstructuraMetaDTO target, EstructuraMeta source) {
		if (source.getEstado() == 0) {
			target.setStringEstado(NHSPDDConstantes.ESTADO_STRING_INACTIVO);
		} else {
			target.setStringEstado(NHSPDDConstantes.ESTADO_STRING_ACTIVO);
		}

		if (source.getTipo() == 0) {
			target.setStringTipo(NHSPDDConstantes.TIPO_PI);
		} else {
			target.setStringTipo(NHSPDDConstantes.TIPO_PD);
		}
	}

	/**
	 * Metodo que mapea una entidad a un dto
	 *
	 * @param estructuraMeta entidad que se desea mapear a dto
	 * @return dto mapeado a partir de la entidad
	 */
	@Mapping(source = "estructuraMeta.idEstructuraMetas", target = "idEstructuraMetas")
	@Mapping(source = "estructuraMeta.estado", target = "estado")
	@Mapping(source = "estructuraMeta.idLsUnidadMedida.idArgumento", target = "idLsUnidadMedida")
	@Mapping(source = "estructuraMeta.idLsVerbo.idArgumento", target = "idLsVerbo")
	@Mapping(source = "estructuraMeta.idLsUnidadMedida.resultado", target = "nombreUnidadMedida")
	@Mapping(source = "estructuraMeta.idLsVerbo.resultado", target = "nombreVerbo")
	@Mapping(source = "estructuraMeta.tipo", target = "tipo")
	EstructuraMetaDTO estructuraMetaEntityToDTO(EstructuraMeta estructuraMeta);

	/**
	 * Metodo que mapea un dto a una entidad
	 *
	 * @param estructuraMetaDTO dto que se desea mapear a entidad
	 * @return la entidad mapeada a partir del dto
	 */
	@Mapping(source = "estructuraMetaDTO.idEstructuraMetas", target = "idEstructuraMetas")
	@Mapping(source = "estructuraMetaDTO.estado", target = "estado")
	@Mapping(source = "estructuraMetaDTO.idLsUnidadMedida", target = "idLsUnidadMedida.idArgumento")
	@Mapping(source = "estructuraMetaDTO.idLsVerbo", target = "idLsVerbo.idArgumento")
	@Mapping(source = "estructuraMetaDTO.tipo", target = "tipo")
	EstructuraMeta estructuraMetaDTOToEntity(EstructuraMetaDTO estructuraMetaDTO);

	/**
	 * Metodo que mapea una lista tipo estructuraMeta a una lista tipo dto
	 *
	 * @param estructurasMeta lista de estructurasMeta que se desea mapear a dto
	 * @return la lista dto mapeada a partir de la lista
	 */
	List<EstructuraMetaDTO> estructuraMetaEntitiesToDTO(List<EstructuraMeta> estructurasMeta);

	/**
	 * Metodo que mapea una lista estructurasMetaDTO a una lista estructurasMeta
	 *
	 * @param estructurasMetaDTO lista dto que se desea mapear a entidad
	 *                           estructurasMeta
	 * @return lista tipo estructurasMeta a partir del dto
	 */
	List<EstructuraMeta> estructuraMetaDTOToEntities(List<EstructuraMetaDTO> estructurasMetaDTO);
}
