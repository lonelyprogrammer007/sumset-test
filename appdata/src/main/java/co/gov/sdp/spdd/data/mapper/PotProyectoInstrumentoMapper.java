package co.gov.sdp.spdd.data.mapper;

import java.io.Serializable;
import java.util.List;

import org.mapstruct.BeforeMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import co.gov.sdp.nhspdd.common.dto.PotProyectoInstrumentoDTO;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.spdd.data.model.ip.PotProyectoInstrumento;

/**
 * Clase mapper de potProyectoInstrumento donde se convierte una entidad a dto y
 * vicerversa
 *
 * @author Bryan Munoz
 *
 */
@Mapper
public interface PotProyectoInstrumentoMapper extends Serializable {

	/**
	 * 
	 * @param target
	 * @param source
	 */
	@BeforeMapping
	default void beforeMapping(@MappingTarget PotProyectoInstrumentoDTO target, PotProyectoInstrumento source) {
		if (source.getEstado() == 0) {
			target.setStringEstado(NHSPDDConstantes.ESTADO_STRING_INACTIVO);
		} else {
			target.setStringEstado(NHSPDDConstantes.ESTADO_STRING_ACTIVO);
		}
	}

	/**
	 * Metodo que mapea un entidad a un dto
	 *
	 * @param potProyectoInstrumento entidad que se desea mapear a dto
	 * @return un dto mapeado a partir de una entidad
	 */
	@Mapping(source = "potProyectoInstrumento.idProyectoInstrumento", target = "idProyectoInstrumento")
	@Mapping(source = "potProyectoInstrumento.estado", target = "estado")
	@Mapping(source = "potProyectoInstrumento.idPotInstrumento.idInstrumentoPot", target = "idPotInstrumento")
	@Mapping(source = "potProyectoInstrumento.idPotProyecto.idObraProyPot", target = "idPotProyecto")
	@Mapping(source = "potProyectoInstrumento.idPotInstrumento.codigo", target = "nombreInstrumento")
	@Mapping(source = "potProyectoInstrumento.idPotProyecto.codigo", target = "nombrePotProyecto")
	PotProyectoInstrumentoDTO potProyectoInstrumentoEntityToDTO(PotProyectoInstrumento potProyectoInstrumento);

	/**
	 * Metodo que mapea un dto a una entidad
	 *
	 * @param potProyectoInstrumentoDTO dto que se desea mapear a entidad
	 * @return una entidad mapeada a partir de un dto
	 */
	@Mapping(source = "potProyectoInstrumentoDTO.idProyectoInstrumento", target = "idProyectoInstrumento")
	@Mapping(source = "potProyectoInstrumentoDTO.estado", target = "estado")
	@Mapping(source = "potProyectoInstrumentoDTO.idPotInstrumento", target = "idPotInstrumento.idInstrumentoPot")
	@Mapping(source = "potProyectoInstrumentoDTO.idPotProyecto", target = "idPotProyecto.idObraProyPot")
	@Mapping(source = "potProyectoInstrumentoDTO.nombreInstrumento", target = "idPotInstrumento.codigo")
	@Mapping(source = "potProyectoInstrumentoDTO.nombrePotProyecto", target = "idPotProyecto.codigo")
	PotProyectoInstrumento potProyectoInstrumentoDTOToEntity(PotProyectoInstrumentoDTO potProyectoInstrumentoDTO);

	/**
	 * Metodo que mapea una lista de entidades potProyectoInstrumento a dto
	 *
	 * @param potProyectoInstrumentos lista que se desea mapear a dto
	 * @return una lista dto mapeada a partir de la lista potProyectoInstrumento
	 */
	List<PotProyectoInstrumentoDTO> potProyectoInstrumentoEntitiesToDTO(
			List<PotProyectoInstrumento> potProyectoInstrumentos);

	/**
	 * Metodo que mapea una lista dto potProyectoInstrumento a entidad
	 *
	 * @param potProyectoInstrumentosDTO lista que se desea mapear a entidad
	 * @return una lista entidad mapeada a partir de la lista dto
	 */
	List<PotProyectoInstrumento> potProyectoInstrumentoDTOToEntities(
			List<PotProyectoInstrumentoDTO> potProyectoInstrumentosDTO);
}
