package co.gov.sdp.spdd.data.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import co.gov.sdp.nhspdd.common.dto.ip.PddPrbIndicadorDTO;
import co.gov.sdp.spdd.data.model.ip.PddPrbIndicador;

/**
 * Mapper de clse PddPrbIndicador
 * @author Bryan Muñoz
 *
 */
@Mapper
public interface PddPrbIndicadorMapper {

	@Mapping(source = "pddPrbIndicador.idProbInd", target = "idProbInd")
	@Mapping(source = "pddPrbIndicador.idIndicador.idIndicador", target = "idIndicador")
	@Mapping(source = "pddPrbIndicador.idProblematica.idProblematica", target = "idProblematica")
	@Mapping(source = "pddPrbIndicador.idIndicador.nombre", target = "nombre")
	@Mapping(source = "pddPrbIndicador.idIndicador.tipo", target = "tipo")
	@Mapping(source = "pddPrbIndicador.idIndicador.lineaBaseDesc", target = "lineaBaseDesc")
	@Mapping(source = "pddPrbIndicador.idIndicador.fuente", target = "fuente")
	@Mapping(source = "pddPrbIndicador.idIndicador.yearLineaBase", target = "yearLineaBase")
	@Mapping(source = "pddPrbIndicador.idIndicador.informacionSoporte", target = "informacionSoporte")
	PddPrbIndicadorDTO prbIndicadorEntityToDTO(PddPrbIndicador pddPrbIndicador);

	@Mapping(source = "pddPrbIndicadorDTO.idProbInd", target = "idProbInd")
	@Mapping(source = "pddPrbIndicadorDTO.idIndicador", target = "idIndicador.idIndicador")
	@Mapping(source = "pddPrbIndicadorDTO.idProblematica", target = "idProblematica.idProblematica")
	PddPrbIndicador prbIndicadorDTOToEntity(PddPrbIndicadorDTO pddPrbIndicadorDTO);
	
	List<PddPrbIndicadorDTO> prbIndicadorEntititesToDTO(List<PddPrbIndicador> listPrbIndicador);
	
	List<PddPrbIndicador> prbIndicadorDTOToEntitites(List<PddPrbIndicadorDTO> listPrbIndicadorDTO);

	
}
