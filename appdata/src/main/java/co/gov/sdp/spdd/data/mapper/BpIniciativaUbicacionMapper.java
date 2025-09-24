package co.gov.sdp.spdd.data.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import co.gov.sdp.nhspdd.common.dto.bp.BpIniciativaUbicacionDTO;
import co.gov.sdp.spdd.data.model.bp.BpIniciativaUbicacion;

@Mapper
public interface BpIniciativaUbicacionMapper {

	@Mapping(source = "iniciativaUbicacion.idUbicacion", target = "idUbicacion")
	@Mapping(source = "iniciativaUbicacion.idTerritorializacion.idTerritorializacion", target = "idTerritorializacion")
	@Mapping(source = "iniciativaUbicacion.idIniciativa.idIniciativa", target = "idIniciativa")
	@Mapping(source = "iniciativaUbicacion.idTerritorializacion.idLsUpz.resultado", target = "nombreUpz")
	@Mapping(source = "iniciativaUbicacion.idTerritorializacion.idLsUpr.resultado", target = "nombreUpr")
	@Mapping(source = "iniciativaUbicacion.idTerritorializacion.idLsUpz.idArgumento", target = "idLsUpz")
	@Mapping(source = "iniciativaUbicacion.idTerritorializacion.idLsUpr.idArgumento", target = "idLsUpr")
	@Mapping(source = "iniciativaUbicacion.idTerritorializacion.idLsVereda.resultado", target = "nombreVereda")
	@Mapping(source = "iniciativaUbicacion.idTerritorializacion.idLsBarrio.resultado", target = "nombreBarrio")
	@Mapping(source = "iniciativaUbicacion.idTerritorializacion.idLsVereda.idArgumento", target = "idLsVereda")
	@Mapping(source = "iniciativaUbicacion.idTerritorializacion.idLsBarrio.idArgumento", target = "idLsBarrio")
	BpIniciativaUbicacionDTO iniciativaUbicacionEntityToDTO(BpIniciativaUbicacion iniciativaUbicacion);

	@Mapping(source = "iniciativaUbicacionDTO.idUbicacion", target = "idUbicacion")
	@Mapping(source = "iniciativaUbicacionDTO.idTerritorializacion", target = "idTerritorializacion.idTerritorializacion")
	@Mapping(source = "iniciativaUbicacionDTO.idIniciativa", target = "idIniciativa.idIniciativa")
	BpIniciativaUbicacion iniciativaUbicacionDTOToEntity(BpIniciativaUbicacionDTO iniciativaUbicacionDTO);

	List<BpIniciativaUbicacionDTO> inciativaUbicacionEntitiesToDTO(List<BpIniciativaUbicacion> listaUbicacion);

	List<BpIniciativaUbicacion> iniciativaUbicacionDTOToEntities(List<BpIniciativaUbicacionDTO> listaUbicacionDTO);
}
