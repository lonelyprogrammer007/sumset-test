package co.gov.sdp.spdd.data.mapper;

import java.io.Serializable;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import co.gov.sdp.nhspdd.common.dto.PotObraDTO;
import co.gov.sdp.spdd.data.model.ip.PotObra;

@Mapper
public interface PotObraMapper extends Serializable {
	
	 /**
     * Metodo que mapea una entidad a un dto
     *
     * @param potObra entidad que se desea mapear a dto
     * @return dto mapeado a partir de la entidad
     */
    @Mapping(source = "potObra.idObraProyPot", target = "idObraProyPot")
    @Mapping(source = "potObra.codigo", target = "codigoPotObra")
    @Mapping(source = "potObra.obra", target = "obra")
    @Mapping(source = "potObra.idLsPlazo.idArgumento", target = "idLsPlazo")
    @Mapping(source = "potObra.idLsPlazo.resultado", target = "stringLsPlazo")
    @Mapping(source = "potObra.idNivelPot.idNivelPot", target = "idNivelPot")
    @Mapping(source = "potObra.idNivelPot.descripcion", target = "stringNivelPot")
    PotObraDTO potObraEntityToDTO(PotObra potObra);

    /**
     * Metodo que mapea un dto a una entidad
     *
     * @param potObraDTO dto que se desea mapear a entidad
     * @return la entidad mapeada a partir del dto
     */
    @Mapping(source = "potObraDTO.idObraProyPot", target = "idObraProyPot")
    @Mapping(source = "potObraDTO.codigoPotObra", target = "codigo")
    @Mapping(source = "potObraDTO.obra", target = "obra")
    @Mapping(source = "potObraDTO.idLsPlazo", target = "idLsPlazo.idArgumento")
    @Mapping(source = "potObraDTO.idNivelPot", target = "idNivelPot.idNivelPot")
    PotObra potObraDTOToEntity(PotObraDTO potObraDTO);

    /**
     * Metodo que mapea una lista tipo potObra a una lista tipo dto
     *
     * @param potObra lista de PotInstrumento que se desea mapear
     * a dto
     * @return la lista dto mapeada a partir de la lista
     */
    List<PotObraDTO> potObraEntitiesToDTO(List<PotObra> potObra);

    /**
     * Metodo que mapea una lista potObraDTO a una lista
     * PotInstrumento
     *
     * @param potObraDTO lista dto que se desea mapear a entidad
     * potObra
     * @return lista tipo PotInstrumento a partir del dto
     */
    List<PotObra> potObraDTOToEntities(List<PotObraDTO> potObraDTO);

}
