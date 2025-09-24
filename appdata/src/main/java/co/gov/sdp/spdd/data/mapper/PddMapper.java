package co.gov.sdp.spdd.data.mapper;

import java.io.Serializable;
import java.util.List;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import co.gov.sdp.nhspdd.common.dto.PddDTO;
import co.gov.sdp.spdd.data.model.ip.Pdd;

@Mapper(uses = {ArgumentoListaSimpleMapper.class})
public interface PddMapper extends Serializable {
	
	@AfterMapping
	default void afterMapping(@MappingTarget Pdd target,PddDTO source)
	{
		if (source.getIdLsAdoptado() == null)
			target.setIdLsAdoptado(null);
		if (source.getIdLsNiveles() == null)
			target.setIdLsNiveles(null);
		if (source.getIdLsEstadoPlan() == null)
			target.setIdLsEstadoPlan(null);
		if (source.getIdLsAvanceSgr() == null)
			target.setIdLsAvanceSgr(null);
	}
	
	@Mapping(source = "pdd.idPlanDesarrollo", target = "idPlanDesarrollo")
	@Mapping(source = "pdd.nombrePlan", target = "nombrePlan")
	@Mapping(source = "pdd.siglaPlan", target = "siglaPlan")
	@Mapping(source = "pdd.actoAdministrativo", target = "actoAdministrativo")
	@Mapping(source = "pdd.fechaActo", target = "fechaActo",dateFormat = "yyyy-MM-dd")
	@Mapping(source = "pdd.yearInicio", target = "yearInicio")
	@Mapping(source = "pdd.nombreAlcalde", target = "nombreAlcalde")
	@Mapping(source = "pdd.correoAlcalde", target = "correoAlcalde")
	@Mapping(source = "pdd.programaGobierno", target = "programaGobierno")
	@Mapping(source = "pdd.yearFinal", target = "yearFinal")
	@Mapping(source = "pdd.idLsAdoptado.idArgumento", target = "idLsAdoptado")
	@Mapping(source = "pdd.idLsAdoptado.resultado", target = "stringAdoptado")
	@Mapping(source = "pdd.idLsAvanceSgr.idArgumento", target = "idLsAvanceSgr")
	@Mapping(source = "pdd.idLsAvanceSgr.resultado", target = "stringAvanceSgr")
	@Mapping(source = "pdd.idLsEstadoPlan.idArgumento", target = "idLsEstadoPlan")
	@Mapping(source = "pdd.idLsEstadoPlan.resultado", target = "estadoPlan")
	@Mapping(source = "pdd.idLsNiveles.idArgumento", target = "idLsNiveles")
	@Mapping(source = "pdd.idLsNiveles.resultado", target = "stringNiveles")
	@Mapping(source = "pdd.version", target = "version")
	@Mapping(source = "pdd.vigencia", target = "vigencia")
	PddDTO pddEntityToDTO(Pdd pdd);

	@Mapping(source = "pddDTO.idPlanDesarrollo", target = "idPlanDesarrollo")
	@Mapping(source = "pddDTO.nombrePlan", target = "nombrePlan")
	@Mapping(source = "pddDTO.siglaPlan", target = "siglaPlan")
	@Mapping(source = "pddDTO.actoAdministrativo", target = "actoAdministrativo")
	@Mapping(source = "pddDTO.fechaActo", target = "fechaActo",dateFormat = "yyyy-MM-dd")
	@Mapping(source = "pddDTO.yearInicio", target = "yearInicio")
	@Mapping(source = "pddDTO.nombreAlcalde", target = "nombreAlcalde")
	@Mapping(source = "pddDTO.correoAlcalde", target = "correoAlcalde")
	@Mapping(source = "pddDTO.programaGobierno", target = "programaGobierno")
	@Mapping(source = "pddDTO.yearFinal", target = "yearFinal")
	@Mapping(source = "pddDTO.idLsAdoptado", target = "idLsAdoptado.idArgumento")
	@Mapping(source = "pddDTO.idLsAvanceSgr", target = "idLsAvanceSgr.idArgumento")
	@Mapping(source = "pddDTO.idLsEstadoPlan", target = "idLsEstadoPlan.idArgumento")
	@Mapping(source = "pddDTO.idLsNiveles", target = "idLsNiveles.idArgumento")
	@Mapping(source = "pddDTO.version", target = "version")	
	Pdd pddDTOToEntity(PddDTO pddDTO);

	/**
	 * Metodo que pasa una lista tipo pdd a una lista dto
	 *
	 * @param pdd lista que se desea convertir
	 * @return una lista dto a partir de la lista tipo pdd
	 */
	List<PddDTO> pddEntitiesToDTO(List<Pdd> pdd);

	/**
	 * Metodo que pasa una lista tipo dto a una lista pdd
	 *
	 * @param pddDTO lista a convertir
	 * @return una lista tipo pdd a partir de una lista dto
	 */
	List<Pdd> pddDTOToEntities(List<PddDTO> pddDTO);

}
