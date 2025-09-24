package co.gov.sdp.spdd.data.mapper;

import java.io.Serializable;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import co.gov.sdp.nhspdd.common.dto.ip.HisVPddCompromisoDTO;
import co.gov.sdp.spdd.data.model.ip.HisVPddCompromiso;

/**
 * Interface que contiene los mapeadores de entity a DTO y viceversa para la entidad HisVPddCompromiso
 * @author DANIEL
 * @version 1.0 24/02/2020
 */
@Mapper
public interface HisVPddCompromisoMapper extends Serializable {

	/**
	 * Metodo que mapea una entidad a un dto
	 *
	 * @param hisVPddCompromiso entidad que se desea mapear a dto
	 * @return dto mapeado a partir de la entidad
	 */
	@Mapping(source = "hisVPddCompromiso.idCompromiso", target = "idCompromiso")
	@Mapping(source = "hisVPddCompromiso.idEstrategicoLc", target = "idEstrategicoLc")
	@Mapping(source = "hisVPddCompromiso.idHisPlanDesarrollo.idPlanDesarrollo", target = "idHisPlanDesarrollo")
	HisVPddCompromisoDTO hisVPddCompromisoEntityToDTO(HisVPddCompromiso hisVPddCompromiso);

	/**
	 * Metodo que mapea un dto a una entidad
	 *
	 * @param hisVPddCompromisoDTO dto que se desea mapear a entidad
	 * @return la entidad mapeada a partir del dto
	 */
	@Mapping(source = "hisVPddCompromisoDTO.idCompromiso", target = "idCompromiso")
	@Mapping(source = "hisVPddCompromisoDTO.idEstrategicoLc", target = "idEstrategicoLc")
	@Mapping(source = "hisVPddCompromisoDTO.idHisPlanDesarrollo", target = "idHisPlanDesarrollo.idPlanDesarrollo")
	HisVPddCompromiso hisVPddCompromisoDTOToEntity(HisVPddCompromisoDTO hisVPddCompromisoDTO);

	/**
	 * Metodo que mapea una lista tipo hisVPddCompromiso a una lista tipo dto
	 *
	 * @param hisVPddCompromiso lista de HisVPddCompromiso que se desea mapear a dto
	 * @return la lista dto mapeada a partir de la lista
	 */
	List<HisVPddCompromisoDTO> hisVPddCompromisoEntitiesToDTO(List<HisVPddCompromiso> hisVPddCompromiso);

	/**
	 * Metodo que mapea una lista hisVPddCompromisoDTO a una lista hisVPddCompromiso
	 *
	 * @param HisVPddCompromisoDTO lista dto que se desea mapear a entidad
	 *                             hisVPddCompromiso
	 * @return lista tipo HisVPddCompromiso a partir del dto
	 */
	List<HisVPddCompromiso> hisVPddCompromisoDTOToEntities(List<HisVPddCompromisoDTO> hisVPddCompromisoDTO);

}
