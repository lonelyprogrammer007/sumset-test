package co.gov.sdp.spdd.data.mapper;

import java.io.Serializable;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvEtnicoDTO;
import co.gov.sdp.spdd.data.model.bp.BpProyInvEtnico;



/**
 * Interface mapper de la clase BpProyInvEtnico
 *
 * @author LUIS FIGUEROA
 * @version 1.0 02/06/2020
 */
@Mapper
public interface BpProyInvEtnicoMapper extends Serializable {
	/**
	 * Metodo que mapea una entidad a un dto
	 * 
	 * @param bpProyInvEtnico entidad que se desea mapear a dto
	 * @return dto mapeado a partir de la entidad
	 */
	@Mapping(source = "bpProyInvEtnico.idEtnico", target = "idEtnico")
	@Mapping(source = "bpProyInvEtnico.numero", target = "numero")
	@Mapping(source = "bpProyInvEtnico.descripcion", target = "descripcion")
	@Mapping(source = "bpProyInvEtnico.idLsEtnico.idArgumento", target = "idLsEtnico")
	@Mapping(source = "bpProyInvEtnico.idLsEtnico.resultado", target = "stringLsEtnico")
//	@Mapping(source = "bpProyInvEtnico.idProyInversion.idProyInversion", target = "idProyInversion")
//	@Mapping(source = "bpProyInvEtnico.idProyInversion.nombre", target = "stringProyInversion")
	@Mapping(source = "bpProyInvEtnico.idPoblacion.idPoblacion", target = "idPoblacion")
	@Mapping(source = "bpProyInvEtnico.idPoblacion.numero", target = "numeroPersonasIdPoblacion")
	BpProyInvEtnicoDTO bpProyInvEtnicoEntityToDTO(BpProyInvEtnico bpProyInvEtnico);

	/**
	 * Metodo que mapea un dto a una entidad
	 * 
	 * @param bpProyInvEtnicoDTO dto que se desea mapear a entidad
	 * @return la entidad mapeada a partir del dto
	 */
	@Mapping(source = "bpProyInvEtnicoDTO.idEtnico", target = "idEtnico")
	@Mapping(source = "bpProyInvEtnicoDTO.numero", target = "numero")
	@Mapping(source = "bpProyInvEtnicoDTO.descripcion", target = "descripcion")
	@Mapping(source = "bpProyInvEtnicoDTO.idLsEtnico", target = "idLsEtnico.idArgumento")
//	@Mapping(source = "bpProyInvEtnicoDTO.idProyInversion", target = "idProyInversion.idProyInversion")
	@Mapping(source = "bpProyInvEtnicoDTO.idPoblacion", target = "idPoblacion.idPoblacion")
	@Mapping(source = "bpProyInvEtnicoDTO.numeroPersonasIdPoblacion", target = "idPoblacion.numero")
	BpProyInvEtnico bpProyInvEtnicoDTOToEntity(BpProyInvEtnicoDTO bpProyInvEtnicoDTO);

	/**
	 * Metodo que mapea una lista de entidades a una lista de DTO
	 * 
	 * @param listaBpProyInvEtnico lista entidades que se desea mapear a DTO
	 * @return lista de DTO correspondiente.
	 */
	List<BpProyInvEtnicoDTO> bpProyInvEtnicoEntitiesToDTO(List<BpProyInvEtnico> listaBpProyInvEtnico);

	/**
	 * Metodo que mapea una lista de DTO a una lista de entidades
	 * 
	 * @param listaBpProyInvEtnicoDTO lista dto que se desea mapear a entidad
	 * @return lista de entidades correspondiente.
	 */
	List<BpProyInvEtnico> bpProyInvEtnicoDTOToEntities(List<BpProyInvEtnicoDTO> listaBpProyInvEtnicoDTO);
}
