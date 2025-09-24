package co.gov.sdp.spdd.data.mapper;

import java.util.List;

import org.mapstruct.AfterMapping;
import org.mapstruct.BeforeMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import co.gov.sdp.nhspdd.common.dto.ip.PddMpIndicadorDTO;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.spdd.data.model.ip.PddMpIndicador;

@Mapper
public interface PddMpIndicadorMapper {

	@BeforeMapping
	default void beforeMapping(@MappingTarget PddMpIndicadorDTO target, PddMpIndicador source) {

		if (source.getEsPdd() == 0) {
			target.setStringEsPdd(NHSPDDConstantes.MAPPER_RESPUESTA_NO);
		} else {
			target.setStringEsPdd(NHSPDDConstantes.MAPPER_RESPUESTA_SI);

		}
		if (source.getIdIndicador().getFuenteExterna() == 0) {
			target.setFuenteExterna(NHSPDDConstantes.MAPPER_RESPUESTA_NO);

		} else {
			target.setFuenteExterna(NHSPDDConstantes.MAPPER_RESPUESTA_SI);

		}

		if (source.getIdIndicador().getLineaBase() == 0) {
			target.setLineaBase(NHSPDDConstantes.MAPPER_RESPUESTA_NO);

		} else {
			target.setLineaBase(NHSPDDConstantes.MAPPER_RESPUESTA_SI);

		}
	}

	@Mapping(source = "pddMpIndicador.idMetaProdInd", target = "idMetaProdInd")
	@Mapping(source = "pddMpIndicador.esPdd", target = "esPdd")
	@Mapping(source = "pddMpIndicador.sumPond", target = "sumPond")
	@Mapping(source = "pddMpIndicador.idIndicador.idIndicador", target = "idIndicador")
	@Mapping(source = "pddMpIndicador.idIndicador.ponderacion", target = "ponderacion")
	@Mapping(source = "pddMpIndicador.idIndicador.nombre", target = "nombreIndicador")
	@Mapping(source = "pddMpIndicador.idIndicador.magnitud", target = "magnitud")
	@Mapping(source = "pddMpIndicador.idIndicador.fuente", target = "fuente")
	@Mapping(source = "pddMpIndicador.idIndicador.lineaBaseDesc", target = "lineaBaseDesc")
	@Mapping(source = "pddMpIndicador.idIndicador.magnitudLb", target = "magnitudLb")
	@Mapping(source = "pddMpIndicador.idIndicador.fechaCreacion", target = "fechaCreacion")
	@Mapping(source = "pddMpIndicador.idIndicador.idLsAgregacion.resultado", target = "nombreAgregacion")
	@Mapping(source = "pddMpIndicador.idIndicador.idLsEstado.resultado", target = "nombreEstado")
	@Mapping(source = "pddMpIndicador.idIndicador.idLsAgregacion.idArgumento", target = "idLsAgregacion")
	@Mapping(source = "pddMpIndicador.idIndicador.idLsEstado.idArgumento", target = "idLsEstado")
	@Mapping(source = "pddMpIndicador.idIndicador.idArchivo.idArchivo", target = "idArchivo")
	@Mapping(source = "pddMpIndicador.idIndicador.fuenteExterna", target = "longFuenteExterna")
	@Mapping(source = "pddMpIndicador.idIndicador.lineaBase", target = "longLineaBase")
	@Mapping(source = "pddMpIndicador.idIndicador.periodicidad", target = "periodicidad")
	@Mapping(source = "pddMpIndicador.idMetaProducto.idMetaProducto", target = "idMetaProducto")
	PddMpIndicadorDTO mpIndicadorEntityToDTO(PddMpIndicador pddMpIndicador);

	@Mapping(source = "pddMpIndicadorDTO.idMetaProdInd", target = "idMetaProdInd")
	@Mapping(source = "pddMpIndicadorDTO.esPdd", target = "esPdd")
	@Mapping(source = "pddMpIndicadorDTO.idIndicador", target = "idIndicador.idIndicador")
	@Mapping(source = "pddMpIndicadorDTO.idMetaProducto", target = "idMetaProducto.idMetaProducto")
	@Mapping(source = "pddMpIndicadorDTO.longFuenteExterna", target = "idIndicador.fuenteExterna")
	@Mapping(source = "pddMpIndicadorDTO.longLineaBase", target = "idIndicador.lineaBase")
	PddMpIndicador mpIndicadorDTOToEntity(PddMpIndicadorDTO pddMpIndicadorDTO);

	List<PddMpIndicadorDTO> mpIndicadorEntitiesToDTO(List<PddMpIndicador> listaMpIndicador);

	List<PddMpIndicador> mpIndicadorDTOToEntities(List<PddMpIndicadorDTO> listaMpIndicadorDTO);

}
