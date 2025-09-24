package co.gov.sdp.spdd.data.mapper;

import java.util.List;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import co.gov.sdp.nhspdd.common.dto.ip.PddIndicadorDTO;
import co.gov.sdp.spdd.data.model.ip.PddIndicador;

/**
 * Mapper de la clase PddIndicador
 * 
 * @author Bryan Muñoz
 *
 */
@Mapper
public interface PddIndicadorMapper {

	@AfterMapping
	default void afterMapping(@MappingTarget PddIndicador target, PddIndicadorDTO source) {
		if (source.getIdLsEstado() == null)
			target.setIdLsEstado(null);
		if (source.getIdArchivo() == null)
			target.setIdArchivo(null);
		if (source.getIdLsAgregacion() == null) {
			target.setIdLsAgregacion(null);
		}
	}

	@Mapping(source = "pddIndicador.idIndicador", target = "idIndicador")
	@Mapping(source = "pddIndicador.nombre", target = "nombre")
	@Mapping(source = "pddIndicador.tipo", target = "tipo")
	@Mapping(source = "pddIndicador.lineaBaseDesc", target = "lineaBaseDesc")
	@Mapping(source = "pddIndicador.fuente", target = "fuente")
	@Mapping(source = "pddIndicador.yearLineaBase", target = "yearLineaBase")
	@Mapping(source = "pddIndicador.informacionSoporte", target = "informacionSoporte")
	@Mapping(source = "pddIndicador.lineaBase", target = "lineaBase")
	@Mapping(source = "pddIndicador.magnitudLb", target = "magnitudLb")
	@Mapping(source = "pddIndicador.magnitud", target = "magnitud")
	@Mapping(source = "pddIndicador.periodicidad", target = "periodicidad")
	@Mapping(source = "pddIndicador.fuenteExterna", target = "fuenteExterna")
	@Mapping(source = "pddIndicador.idArchivo.idArchivo", target = "idArchivo")
	@Mapping(source = "pddIndicador.idLsEstado.idArgumento", target = "idLsEstado")
	@Mapping(source = "pddIndicador.idLsEstado.resultado", target = "stringEstado")
	@Mapping(source = "pddIndicador.idLsAgregacion.idArgumento", target = "idLsAgregacion")
	@Mapping(source = "pddIndicador.idLsAgregacion.resultado", target = "nombreAgregacion")
	@Mapping(source = "pddIndicador.ponderacion", target = "ponderacion")
	@Mapping(source = "pddIndicador.fechaCreacion", target = "fechaCreacion", dateFormat = "yyyy-MM-dd")
	PddIndicadorDTO pddIndicadorEntityToDTO(PddIndicador pddIndicador);

	@Mapping(source = "pddIndicadorDTO.idIndicador", target = "idIndicador")
	@Mapping(source = "pddIndicadorDTO.nombre", target = "nombre")
	@Mapping(source = "pddIndicadorDTO.tipo", target = "tipo")
	@Mapping(source = "pddIndicadorDTO.lineaBaseDesc", target = "lineaBaseDesc")
	@Mapping(source = "pddIndicadorDTO.fuente", target = "fuente")
	@Mapping(source = "pddIndicadorDTO.yearLineaBase", target = "yearLineaBase")
	@Mapping(source = "pddIndicadorDTO.informacionSoporte", target = "informacionSoporte")
	@Mapping(source = "pddIndicadorDTO.lineaBase", target = "lineaBase")
	@Mapping(source = "pddIndicadorDTO.magnitudLb", target = "magnitudLb")
	@Mapping(source = "pddIndicadorDTO.magnitud", target = "magnitud")
	@Mapping(source = "pddIndicadorDTO.periodicidad", target = "periodicidad")
	@Mapping(source = "pddIndicadorDTO.fuenteExterna", target = "fuenteExterna")
	@Mapping(source = "pddIndicadorDTO.idArchivo", target = "idArchivo.idArchivo")
	@Mapping(source = "pddIndicadorDTO.idLsEstado", target = "idLsEstado.idArgumento")
	@Mapping(source = "pddIndicadorDTO.ponderacion", target = "ponderacion")
	@Mapping(source = "pddIndicadorDTO.idLsAgregacion", target = "idLsAgregacion.idArgumento")
	@Mapping(source = "pddIndicadorDTO.fechaCreacion", target = "fechaCreacion", dateFormat = "yyyy-MM-dd")
	PddIndicador pddIndicadorDTOToEntity(PddIndicadorDTO pddIndicadorDTO);

	List<PddIndicadorDTO> pddIndicadorEntitiesToDTO(List<PddIndicador> listaIndicador);

	List<PddIndicador> pddIndicadorDTOToEntities(List<PddIndicadorDTO> listaIndicador);

}
