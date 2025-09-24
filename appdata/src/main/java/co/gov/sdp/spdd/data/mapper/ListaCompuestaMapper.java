package co.gov.sdp.spdd.data.mapper;

import java.io.Serializable;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import co.gov.sdp.nhspdd.common.dto.ListaCompuestaDTO;
import co.gov.sdp.spdd.data.model.afs.ListaCompuesta;

/**
 * Interface mapper de la clase Lista Compuesta
 *
 * @author Bryan Munoz
 *
 */
@Mapper()
public interface ListaCompuestaMapper extends Serializable {

    /**
     * Metodo que mapea una entidad a un dto
     *
     * @param listaCompuesta entidad que se desea mapear a dto
     * @return dto mapeado a partir de la entidad
     */
    @Mapping(source = "listaCompuesta.idListaCompuesta", target = "idListaCompuesta")
    @Mapping(source = "listaCompuesta.descripcion", target = "descripcion")
    @Mapping(source = "listaCompuesta.nombre", target = "nombre")
    @Mapping(source = "listaCompuesta.tabla", target = "tabla")
    ListaCompuestaDTO listaCompuestaEntityToDTO(ListaCompuesta listaCompuesta);

    /**
     * Metodo que mapea un dto a una entidad
     *
     * @param listaCompuestaDTO dto que se desea mapear a entidad
     * @return la entidad mapeada a partir del dto
     */
    @Mapping(source = "listaCompuestaDTO.idListaCompuesta", target = "idListaCompuesta")
    @Mapping(source = "listaCompuestaDTO.descripcion", target = "descripcion")
    @Mapping(source = "listaCompuestaDTO.nombre", target = "nombre")
    @Mapping(source = "listaCompuestaDTO.tabla", target = "tabla")
    ListaCompuesta listaCompuestaDTOToEntity(ListaCompuestaDTO listaCompuestaDTO);

    /**
     * Metodo que mapea una lista tipo listaCompuesta a una lista tipo dto
     *
     * @param listaCompuesta lista de listas compuestas que se desea mapear a
     * dto
     * @return la lista dto mapeada a partir de la lista
     */
    List<ListaCompuestaDTO> listaCompuestaEntitiesToDTO(List<ListaCompuesta> listaCompuesta);

    /**
     * Metodo que mapea una lista listaCompuestaDTO a una lista listaCompuesta
     *
     * @param listaCompuestaDTO lista dto que se desea mapear a entidad
     * listaCompuesta
     * @return lista tipo listaCompuesta a partir del dto
     */
    List<ListaCompuesta> listaCompuestaDTOToEntities(List<ListaCompuestaDTO> listaCompuestaDTO);
}
