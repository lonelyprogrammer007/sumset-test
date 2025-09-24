package co.gov.sdp.spdd.data.mapper;

import java.io.Serializable;
import java.util.List;

import org.mapstruct.BeforeMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import co.gov.sdp.nhspdd.common.dto.ArgumentoListaSimpleDTO;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.spdd.data.model.afs.ArgumentoListaSimple;

/**
 * Interface mapper de la clase Argumento Lista Simple
 *
 * @author Bryan Munoz
 *
 */
@Mapper(uses = {ListaSimpleMapper.class})
public interface ArgumentoListaSimpleMapper extends Serializable {
	
	/**
	 * 
	 * @param target
	 * @param source
	 */
	@BeforeMapping
	default void beforeMapping(@MappingTarget ArgumentoListaSimpleDTO target,
			ArgumentoListaSimple source) {
		if (source.getEstado() == 0) {
			target.setStringEstado(NHSPDDConstantes.ESTADO_STRING_INACTIVO);
		} else  {
			target.setStringEstado(NHSPDDConstantes.ESTADO_STRING_ACTIVO);
		}
	}

    /**
     * Metodo que mapea una entidad a un dto
     *
     * @param argumentoListaSimple entidad que se desea mapear a dto
     * @return dto mapeado a partir de la entidad
     */
    @Mapping(source = "argumentoListaSimple.idArgumento", target = "idArgumento")
    @Mapping(source = "argumentoListaSimple.argumento", target = NHSPDDConstantes.ARGUMENTO)
    @Mapping(source = "argumentoListaSimple.estado", target = "estado")
    @Mapping(source = "argumentoListaSimple.resultado", target = "resultado")
    @Mapping(source = "argumentoListaSimple.idListaSimple.idListaSimple", target = "idListaSimple")
    ArgumentoListaSimpleDTO argumentoListaSimpleEntityToDTO(ArgumentoListaSimple argumentoListaSimple);

    /**
     * Metodo que mapea un dto a una entidad
     *
     * @param argumentoListaSimpleDTO dto que se desea mapear a entidad
     * @return la entidad mapeada a partir del dto
     */
    @Mapping(source = "argumentoListaSimpleDTO.idArgumento", target = "idArgumento")
    @Mapping(source = "argumentoListaSimpleDTO.argumento", target = NHSPDDConstantes.ARGUMENTO)
    @Mapping(source = "argumentoListaSimpleDTO.estado", target = "estado")
    @Mapping(source = "argumentoListaSimpleDTO.resultado", target = "resultado")
    @Mapping(source = "argumentoListaSimpleDTO.idListaSimple", target = "idListaSimple.idListaSimple")
    ArgumentoListaSimple argumentoListaSimpleDTOToEntity(ArgumentoListaSimpleDTO argumentoListaSimpleDTO);

    /**
     * Metodo que mapea una lista tipo argumentosimple a una lista tipo dto
     *
     * @param argumentosListaSimple lista de argumentosimple que se desea mapear
     * a dto
     * @return la lista dto mapeada a partir de la lista
     */
    List<ArgumentoListaSimpleDTO> argumentoListaSimpleEntitiesToDTO(List<ArgumentoListaSimple> argumentosListaSimple);

    /**
     * Metodo que mapea una lista argumentolistasimpleDTO a una lista
     * argumentolistasimple
     *
     * @param argumentosListaSimpleDTO lista dto que se desea mapear a entidad
     * argumentolistasimple
     * @return lista tipo argumentolistasimple a partir del dto
     */
    List<ArgumentoListaSimple> argumentoListaSimpleDTOToEntities(List<ArgumentoListaSimpleDTO> argumentosListaSimpleDTO);
}
