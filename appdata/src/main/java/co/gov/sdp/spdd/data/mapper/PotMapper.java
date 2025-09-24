package co.gov.sdp.spdd.data.mapper;

import java.io.Serializable;

import java.util.List;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


import co.gov.sdp.nhspdd.common.dto.ip.PotDTO;

import co.gov.sdp.spdd.data.model.ip.Pot;


/**
 * Interface mapper de la clase Pot
 * @author nicolas zuluaga
 *
 */

@Mapper
public interface PotMapper  {

	
	/**
	 * Metodo que mapea una entidad a un dto
	 * @param pot entidad que se desea Mapear a un dto
	 * @return dto mapeado a partir de la entidad
	 */
	@Mapping(source = "pot.idPot",target = "idPot")
	@Mapping(source = "pot.codigoPot",target = "codigoPot")
	@Mapping(source = "pot.actoAdministrativo",target = "actoAdministrativo")
	@Mapping(source = "pot.yearInicio",target = "yearInicio")
	@Mapping(source = "pot.yearFin",target = "yearFin")
	@Mapping(source = "pot.idLsAdoptado.idArgumento",target = "idLsAdoptado")
	@Mapping(source = "pot.fecha",target = "fecha",dateFormat = "yyyy-MM-dd")
	@Mapping(source = "pot.url",target = "url")

	@Mapping(source = "pot.idLsAdoptado.resultado",target = "nombreAdoptado")
	PotDTO potEntityToDTO(Pot pot);
	
	/**
	 * Metodo que mapea un dto a una entidad
	 * @param potDTO dto que se desea mapear a una entidad
	 * @return la entidad mapeada a partir del dto
	 */
	@Mapping(source = "potDTO.idPot",target = "idPot")
	@Mapping(source = "potDTO.codigoPot",target = "codigoPot")
	@Mapping(source = "potDTO.actoAdministrativo",target = "actoAdministrativo")
	@Mapping(source = "potDTO.yearInicio",target = "yearInicio")
	@Mapping(source = "potDTO.yearFin",target = "yearFin")
	@Mapping(source = "potDTO.idLsAdoptado",target = "idLsAdoptado.idArgumento")
	@Mapping(source = "potDTO.fecha",target = "fecha", dateFormat = "yyyy-MM-dd")
	@Mapping(source = "potDTO.url",target = "url")

	Pot potDTOToEntity(PotDTO potDTO);
	
	
	/**
	 * Metodo que mapea una lista tipo Pot a una lista tipo dto
	 * @param listaPot lista de Pot que se desea mapear a una lista de dto
	 * @return lista de dto mapeada a partir de la lista Pot
	 */
	List<PotDTO> potEntitiesToDTO(List<Pot> listaPot);
	
	
	/**
	 * Metodo que mapea una tipo dto a una lista tipo Pot
	 * @param listaPotDTO lista de tipo dto que desea ser mapeada a una lista Pot
	 * @return lista de tipo Pot mapeada a partir de la lista dto
	 */
	List<Pot> potDTOToEntities(List<PotDTO> listaPotDTO);
}
