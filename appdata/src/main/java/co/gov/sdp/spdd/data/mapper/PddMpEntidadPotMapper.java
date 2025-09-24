package co.gov.sdp.spdd.data.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import co.gov.sdp.nhspdd.common.dto.PotObraDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddMpEntidadPotDTO;
import co.gov.sdp.spdd.data.model.ip.PddMpEntidadPot;
import co.gov.sdp.spdd.data.model.ip.PotObra;

@Mapper
public interface PddMpEntidadPotMapper {
	
	/**
     * Metodo que mapea una entidad a un dto
     *
     * @param pddMpEntidadPot entidad que se desea mapear a dto
     * @return dto mapeado a partir de la entidad
     */
    @Mapping(source = "pddMpEntidadPot.idMpEntPot", target = "idMpEntPot")
    @Mapping(source = "pddMpEntidadPot.idMpEntidad.idMpEntidad", target = "idMpEntidad")
    @Mapping(source = "pddMpEntidadPot.idMpEntidad.codigoEntidad.codigoEntidad", target = "codigoEntidad")
    @Mapping(source = "pddMpEntidadPot.idObraProyPot.idObraProyPot", target = "idObraProyPot")
    @Mapping(source = "pddMpEntidadPot.idObraProyPot.obra", target = "stringObraProyPot")
    PddMpEntidadPotDTO pddMpEntidadPotEntityToDTO(PddMpEntidadPot pddMpEntidadPot);

    /**
     * Metodo que mapea un dto a una entidad
     *
     * @param pddMpEntidadPotDTO dto que se desea mapear a entidad
     * @return la entidad mapeada a partir del dto
     */
    @Mapping(source = "pddMpEntidadPotDTO.idMpEntPot", target = "idMpEntPot")
    @Mapping(source = "pddMpEntidadPotDTO.idMpEntidad", target = "idMpEntidad.idMpEntidad")
    @Mapping(source = "pddMpEntidadPotDTO.idObraProyPot", target = "idObraProyPot.idObraProyPot")
    PddMpEntidadPot pddMpEntidadPotDTOToEntity(PddMpEntidadPotDTO pddMpEntidadPotDTO);

    /**
     * Metodo que mapea una lista tipo potObra a una lista tipo dto
     *
     * @param listPddMpEntidadPot lista de PddMpEntidadPot que se desea mapear
     * a dto
     * @return la lista dto mapeada a partir de la lista
     */
    List<PddMpEntidadPotDTO> pddMpEntidadPotEntitiesToDTO(List<PddMpEntidadPot> listPddMpEntidadPot);

    /**
     * Metodo que mapea una lista PddMpEntidadPotDTO a una lista DTO
     *
     * @param listPddMpEntidadPotDTO lista dto que se desea mapear.
     * @return lista tipo PddMpEntidadPot a partir del dto
     */
    List<PddMpEntidadPot> pddMpEntidadPotDTOToEntities(List<PddMpEntidadPotDTO> listPddMpEntidadPotDTO);

}
