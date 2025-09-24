package co.gov.sdp.spdd.data.mapper;

import java.util.List;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;

import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import co.gov.sdp.nhspdd.common.dto.ip.PotNivelDTO;
import co.gov.sdp.spdd.data.model.ip.PotNivel;
@Mapper
public interface PotNivelMapper {
	
	@AfterMapping
    default void afterMapping(@MappingTarget PotNivel target, PotNivelDTO source)
    {
        if (source.getIdNivelPadre() == null)
            target.setIdNivelPadre(null);
        if (source.getIdRamaPot() == null)
            target.setIdRamaPot(null);
       
    }
	/**
	 * metodo que mapea de una entidad a un DTO
	 * @param potNivel entidad que se desea mapear a un DTO
	 * @return dto mapeado a partir de la entidad
	 */
	@Mapping(source = "potNivel.idNivelPot",target = "idNivelPot")
	@Mapping(source = "potNivel.numeroNivel",target = "numeroNivel")
	@Mapping(source = "potNivel.descripcion",target ="descripcion")
	@Mapping(source = "potNivel.esObra",target ="esObra")
	@Mapping(source = "potNivel.cerrado",target ="cerrado")
	@Mapping(source = "potNivel.idNivelPadre.idNivelPot",target = "idNivelPadre")
	@Mapping(source = "potNivel.idRamaPot.idRamaPot", target = "idRamaPot")
	PotNivelDTO PotNivelEntityToDTO(PotNivel potNivel);
	/**
	 * metodo que mapea de un Dto a una entidad
	 * @param potNivelDTO Dto que se desea a mapear a un DTO
	 * @return entidad mapeada a partir del DTO
	 */
	@Mapping(source = "potNivelDTO.idNivelPot",target = "idNivelPot")
	@Mapping(source = "potNivelDTO.numeroNivel",target = "numeroNivel")
	@Mapping(source = "potNivelDTO.esObra",target = "esObra")
	@Mapping(source = "potNivelDTO.cerrado",target = "cerrado")
	@Mapping(source = "potNivelDTO.descripcion",target = "descripcion")
	@Mapping(source ="potNivelDTO.idNivelPadre",target = "idNivelPadre.idNivelPot")
	@Mapping(source = "potNivelDTO.idRamaPot",target = "idRamaPot.idRamaPot")
	PotNivel PotNivelDTOToEntity(PotNivelDTO potNivelDTO);
	
	/**
	 * Metodo que mapea una lista de entidades a una lista de tipo dto
	 * @param listaPotNivel lista de potNivel que se desea mapear a una lista de dto
	 * @return lista de DTO mapeada a partir de la lista de PotNivel
	 */
	List<PotNivelDTO> potNivelEntitiesToDTO(List<PotNivel> listaPotNivel);
	
	/**
	 * Metodo que mapea una lista de tipo DTO a una lista de entidades
	 * @param listaPotNivelDTO lista de PotNivelDTO que se desea mapear a una lista de entidades PotNivel
	 * @return lista de Entidades mapeada a partir de la lista PotNivelDTO
	 */
	List<PotNivel> potNivelDTOToEntities(List<PotNivelDTO> listaPotNivelDTO);
}
