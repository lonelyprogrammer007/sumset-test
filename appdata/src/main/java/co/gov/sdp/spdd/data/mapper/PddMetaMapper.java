package co.gov.sdp.spdd.data.mapper;

import java.io.Serializable;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import co.gov.sdp.nhspdd.common.dto.ip.PddMetaDTO;
import co.gov.sdp.spdd.data.model.ip.PddMeta;

@Mapper
public interface PddMetaMapper extends Serializable {

	@Mapping(source = "pddMeta.idMeta", target = "idMeta")
	@Mapping(source = "pddMeta.meta", target = "meta")
	@Mapping(source = "pddMeta.idTipoMetaLs.idArgumento", target = "idTipoMetaLs")
	@Mapping(source = "pddMeta.idTipoMetaLs.resultado", target = "nombreidTipoMetaLs")
	@Mapping(source = "pddMeta.magnitud", target = "magnitud")
	@Mapping(source = "pddMeta.idEspecifico.idEspecifico", target = "idEspecifico")
	PddMetaDTO pddMetaEntityToDTO(PddMeta pddMeta);

	@Mapping(source = "pddMetaDTO.idMeta", target = "idMeta")
	@Mapping(source = "pddMetaDTO.meta", target = "meta")
	@Mapping(source = "pddMetaDTO.idTipoMetaLs", target = "idTipoMetaLs.idArgumento")
	@Mapping(source = "pddMetaDTO.magnitud", target = "magnitud")
	@Mapping(source = "pddMetaDTO.idEspecifico", target = "idEspecifico.idEspecifico")
	PddMeta pddMetaDTOToEntity(PddMetaDTO pddMetaDTO);

	/**
	 * Metodo que pasa una lista tipo pddMeta a una lista dto
	 *
	 * @param pddMeta lista que se desea convertir
	 * @return una lista dto a partir de la lista tipo pddMeta
	 */
	List<PddMetaDTO> pddMetaEntitiesToDTO(List<PddMeta> pddMeta);

	/**
	 * Metodo que pasa una lista tipo dto a una lista pddMeta
	 *
	 * @param pddMetaDTO lista a convertir
	 * @return una lista tipo pddMeta a partir de una lista dto
	 */
	List<PddMeta> pddMetaDTOToEntities(List<PddMetaDTO> pddMetaDTO);

}
