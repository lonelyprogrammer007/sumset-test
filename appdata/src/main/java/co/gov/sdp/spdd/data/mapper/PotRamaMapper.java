package co.gov.sdp.spdd.data.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import co.gov.sdp.nhspdd.common.dto.ip.PotRamaDTO;
import co.gov.sdp.spdd.data.model.ip.PotRama;

/**
 * Interface mapper de la clase PotRama
 * @author nicolas zuluaga
 *
 */

@Mapper
public interface PotRamaMapper {

	/**
	 * Metodo que mapea de una entidad a un dto
	 * @param potRama entidad que se desea mapear a un dto
	 * @return dto mapeado a partir de la entidad
	 */
	@Mapping(source = "potRama.idRamaPot",target = "idPotRama")
	@Mapping(source = "potRama.numeroRama",target = "numeroRama")
	@Mapping(source = "potRama.cerrada",target = "cerrada")
	@Mapping(source = "potRama.idPot.idPot",target = "idPot")
	PotRamaDTO potRamaEntityToDTO(PotRama potRama);
	
	/**
	 * Metodo que mapea de un dto a una entidad
	 * @param potRamaDTO dto que se desea mapear a una entidad
	 * @return entidad mapeada a partir del dto
	 */
	@Mapping(source = "potRamaDTO.idPotRama",target = "idRamaPot")
	@Mapping(source = "potRamaDTO.numeroRama",target = "numeroRama")
	@Mapping(source = "potRamaDTO.cerrada",target = "cerrada")
	@Mapping(source = "potRamaDTO.idPot",target = "idPot.idPot")
	PotRama potRamaDTOToEntity(PotRamaDTO potRamaDTO);
	
	/**
	 * Metodo que mapea una lista tipo PotRama a una lista tipo dto
	 * @param listaPotRama lista de PotRama que se desea mapear a una lista de dto
	 * @return lista de dto mapeada a partir de la lista PotRama
	 */
	List<PotRamaDTO> potRamaEntitiesToDTO(List<PotRama> listaPotRama);
	/**
	 * Metodo que mapea una lista tipo dto a una lista tipo PotRama
	 * @param listaPotRamaDTO lista de tipo dto que desea ser mapeada a una lista PotRama
	 * @return lista de tipo PotRama mapeada a partir de la lista dto
	 */
	List<PotRama> potRamaDTOToEntities(List<PotRamaDTO> listaPotRamaDTO);
	
}
