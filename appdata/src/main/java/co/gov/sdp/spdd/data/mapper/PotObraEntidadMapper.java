package co.gov.sdp.spdd.data.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import co.gov.sdp.nhspdd.common.dto.ip.PotDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PotObraEntidadDTO;
import co.gov.sdp.spdd.data.model.ip.Pot;
import co.gov.sdp.spdd.data.model.ip.PotObraEntidad;

@Mapper
public interface PotObraEntidadMapper {
	/**
	 * Metodo que mapea una entidad a un dto
	 * @param potObraEntidads entidad que se desea Mapear a un dto
	 * @return dto mapeado a partir de la entidad
	 */
	@Mapping(source = "potObraEntidad.idObraEntidad",target = "idObraEntidad")
	@Mapping(source = "potObraEntidad.codigoEntidad.codigoEntidad",target = "codigoEntidad")
	@Mapping(source = "potObraEntidad.idObraProyPot.idObraProyPot",target = "idObraProyPot")
	@Mapping(source = "potObraEntidad.idObraProyPot.obra",target = "stringObraProyPot")
	PotObraEntidadDTO potObraEntidadEntityToDTO(PotObraEntidad potObraEntidad);
	
	/**
	 * Metodo que mapea un dto a una entidad
	 * @param potObraEntidadDTO dto que se desea mapear a una entidad
	 * @return la entidad mapeada a partir del dto
	 */
	@Mapping(source = "potObraEntidadDTO.idObraEntidad",target = "idObraEntidad")
	@Mapping(source = "potObraEntidadDTO.codigoEntidad",target = "codigoEntidad.codigoEntidad")
	@Mapping(source = "potObraEntidadDTO.idObraProyPot",target = "idObraProyPot.idObraProyPot")
	PotObraEntidad potObraEntidadDTOToEntity(PotObraEntidadDTO potObraEntidadDTO);
	
	
	/**
	 * Metodo que mapea una lista tipo Pot a una lista tipo dto
	 * @param listaPotObraEntidad lista de PotObraEntidad que se desea mapear a una lista de dto
	 * @return lista de dto mapeada a partir de la lista PotObraEntidad
	 */
	List<PotObraEntidadDTO> potObraEntidadEntitiesToDTO(List<PotObraEntidad> listaPotObraEntidad);
	
	
	/**
	 * Metodo que mapea una tipo dto a una lista tipo Pot
	 * @param listaPotObraEntidadDTO lista de tipo dto que desea ser mapeada a una lista Pot
	 * @return lista de tipo PotObraEntidad mapeada a partir de la lista dto
	 */
	List<PotObraEntidad> potObraEntidadDTOToEntities(List<PotObraEntidadDTO> listaPotObraEntidadDTO);
}
