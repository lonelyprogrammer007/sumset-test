package co.gov.sdp.spdd.data.mapper;

import java.io.Serializable;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import co.gov.sdp.nhspdd.common.dto.ListaSimpleDTO;
import co.gov.sdp.spdd.data.model.afs.ListaSimple;



/**
 * Interface mapper de la entidad Lista Simple
 *
 * @author BryanMunoz
 *
 */
@Mapper
public interface ListaSimpleMapper extends Serializable {

    /**
     * Mapeo que convierte una entidad a dto
     *
     * @param listaSimple emtidad que se va mapear a dto
     * @return dto mapeado a partir de la entidad
     */
    @Mapping(source = "listaSimple.idListaSimple", target = "idListaSimple")
    @Mapping(source = "listaSimple.nombre", target = "nombre")
    @Mapping(source = "listaSimple.descripcion", target = "descripcion")
    ListaSimpleDTO listaSimpleEntityToDTO(ListaSimple listaSimple);

    /**
     * Mapeo que convierte un dto a una entidad
     *
     * @param listaSimpleDTO dto que se desea convertir en entidad
     * @return entidad a partir del dto
     */
    @Mapping(source = "listaSimpleDTO.idListaSimple", target = "idListaSimple")
    @Mapping(source = "listaSimpleDTO.nombre", target = "nombre")
    @Mapping(source = "listaSimpleDTO.descripcion", target = "descripcion")
    ListaSimple listaSimpleDTOToEntity(ListaSimpleDTO listaSimpleDTO);

    /**
     * Mapeo de una lista de entidades a una lista dto
     *
     * @param listasSimples lista de entidades a convertir a lista dto
     * @return lista dto a partir de la lista de entidades
     */
    List<ListaSimpleDTO> listaSimpleEntitiesToDTO(List<ListaSimple> listasSimples);

    /**
     * Mapeo de una lista dto a una lista de entidades
     *
     * @param listasSimplesDTO lista dto a convertir a lista de entidades
     * @return lista de entidades a partir de la lista dto
     */
    List<ListaSimple> listaSimpleDTOToEntities(List<ListaSimpleDTO> listasSimplesDTO);

}
