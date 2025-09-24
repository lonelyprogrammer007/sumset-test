package co.gov.sdp.spdd.data.mapper;

import java.io.Serializable;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import co.gov.sdp.nhspdd.common.dto.ip.PdlDTO;
import co.gov.sdp.spdd.data.model.ip.Pdl;

@Mapper
public interface PdlMapper extends Serializable {

	@Mapping(source = "pdl.idPlanLocal", target = "idPlanLocal")
	@Mapping(source = "pdl.nombrePlan", target = "nombrePlan")
	@Mapping(source = "pdl.urlPlan", target = "urlPlan")
	@Mapping(source = "pdl.actoAdministrativo", target = "actoAdministrativo")
	@Mapping(source = "pdl.fechaActo", target = "fechaActo", dateFormat = "yyyy-MM-dd")
	@Mapping(source = "pdl.yearInicio", target = "yearInicio")
	@Mapping(source = "pdl.nombreAlcaldeLocal", target = "nombreAlcaldeLocal")
	@Mapping(source = "pdl.correoAlcaldeLocal", target = "correoAlcaldeLocal")
	@Mapping(source = "pdl.yearFinal", target = "yearFinal")
	@Mapping(source = "pdl.idLsAdoptado.idArgumento", target = "idLsAdoptado")
	@Mapping(source = "pdl.idLsEstadoPlan.idArgumento", target = "idLsEstadoPlan")
	@Mapping(source = "pdl.idLsEstadoPlan.resultado", target = "estadoPlan")
	@Mapping(source = "pdl.idPlanDesarrollo.idPlanDesarrollo", target = "idPlanDesarrollo")
	@Mapping(source = "pdl.codigoEntidad.codigoEntidad", target = "codigoEntidad")
	@Mapping(source = "pdl.vigencia", target = "vigencia")
	PdlDTO pdlEntityToDTO(Pdl pdl);

	@Mapping(source = "pdlDTO.idPlanLocal", target = "idPlanLocal")
	@Mapping(source = "pdlDTO.nombrePlan", target = "nombrePlan")
	@Mapping(source = "pdlDTO.urlPlan", target = "urlPlan")
	@Mapping(source = "pdlDTO.actoAdministrativo", target = "actoAdministrativo")
	@Mapping(source = "pdlDTO.fechaActo", target = "fechaActo", dateFormat = "yyyy-MM-dd")
	@Mapping(source = "pdlDTO.yearInicio", target = "yearInicio")
	@Mapping(source = "pdlDTO.nombreAlcaldeLocal", target = "nombreAlcaldeLocal")
	@Mapping(source = "pdlDTO.correoAlcaldeLocal", target = "correoAlcaldeLocal")
	@Mapping(source = "pdlDTO.yearFinal", target = "yearFinal")
	@Mapping(source = "pdlDTO.idLsAdoptado", target = "idLsAdoptado.idArgumento")
	@Mapping(source = "pdlDTO.idLsEstadoPlan", target = "idLsEstadoPlan.idArgumento")
	@Mapping(source = "pdlDTO.idPlanDesarrollo", target = "idPlanDesarrollo.idPlanDesarrollo")
	@Mapping(source = "pdlDTO.codigoEntidad", target = "codigoEntidad.codigoEntidad")
	Pdl pdlDTOToEntity(PdlDTO pdlDTO);


	/**
	 * Metodo que pasa una lista tipo pdl a una lista dto
	 *
	 * @param pdl lista que se desea convertir
	 * @return una lista dto a partir de la lista tipo pdl
	 */
	List<PdlDTO> pdlEntitiesToDTO(List<Pdl> pdl);

	/**
	 * Metodo que pasa una lista tipo dto a una lista pdl
	 *
	 * @param pdlDTO lista a convertir
	 * @return una lista tipo pdl a partir de una lista dto
	 */
	List<Pdl> pdlDTOToEntities(List<PdlDTO> pdlDTO);

}