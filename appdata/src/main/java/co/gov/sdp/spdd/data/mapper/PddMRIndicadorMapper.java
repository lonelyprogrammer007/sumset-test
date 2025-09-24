package co.gov.sdp.spdd.data.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import co.gov.sdp.nhspdd.common.dto.ip.PddMRIndicadorDTO;
import co.gov.sdp.spdd.data.model.ip.PddMRIndicador;

/**
 * 
 * @author Bryan Muñoz
 *
 */
@Mapper
public interface PddMRIndicadorMapper {

	@Mapping(source = "pddMRIndicador.idIndProxy", target = "idIndProxy")
	@Mapping(source = "pddMRIndicador.idIndicador.idIndicador", target = "idIndicador")
	@Mapping(source = "pddMRIndicador.idMetaResultado.idMetaResultado", target = "idMetaResultado")
	@Mapping(source = "pddMRIndicador.idMetaResultado.idIndicador.idIndicador", target = "indicadorMetaResultado")
	@Mapping(source = "pddMRIndicador.idIndicador.magnitud", target = "magnitud")
	@Mapping(source = "pddMRIndicador.idIndicador.nombre", target = "denominacion")
	@Mapping(source = "pddMRIndicador.idIndicador.periodicidad", target = "periodicidad")
	@Mapping(source = "pddMRIndicador.idIndicador.fuente", target = "fuente")
	@Mapping(source = "pddMRIndicador.idIndicador.fuenteExterna", target = "fuenteExterna")
	@Mapping(source = "pddMRIndicador.idIndicador.idLsEstado.resultado", target = "estado")
	@Mapping(source = "pddMRIndicador.idIndicador.lineaBase", target = "lineaBase")
	@Mapping(source = "pddMRIndicador.idIndicador.magnitudLb", target = "magnitudLb")
	@Mapping(source = "pddMRIndicador.idIndicador.lineaBaseDesc", target = "lineaBaseDesc")
	@Mapping(source = "pddMRIndicador.idIndicador.idArchivo.idArchivo", target="idArchivo")
	@Mapping(source = "pddMRIndicador.idIndicador.fechaCreacion", target = "fechaCreacion", dateFormat = "yyyy-MM-dd")
	PddMRIndicadorDTO mRIndicadorEntityToDTO(PddMRIndicador pddMRIndicador);

	@Mapping(source = "pddMRIndicadorDTO.idIndProxy", target = "idIndProxy")
	@Mapping(source = "pddMRIndicadorDTO.idIndicador", target = "idIndicador.idIndicador")
	@Mapping(source = "pddMRIndicadorDTO.idMetaResultado", target = "idMetaResultado.idMetaResultado")
	PddMRIndicador mRIndicadorDTOToEntity(PddMRIndicadorDTO pddMRIndicadorDTO);

	/**
	 * 
	 * @param listaIndicador
	 * @return
	 */
	List<PddMRIndicadorDTO> mRIndicadorEntitiesToDTO(List<PddMRIndicador> listaIndicador);

	/**
	 * 
	 * @param listaIndicadorDTO
	 * @return
	 */
	List<PddMRIndicador> mRIndicadorDTOToEntities(List<PddMRIndicadorDTO> listaIndicadorDTO);

}
