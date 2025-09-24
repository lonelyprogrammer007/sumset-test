package co.gov.sdp.spdd.data.mapper;

import java.io.Serializable;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import co.gov.sdp.nhspdd.common.dto.FuncionarioClaveEntidadDTO;
import co.gov.sdp.spdd.data.model.afs.FuncionarioClaveEntidad;
/**
 * Interface mapper de la clase FuncionarioClaveEntidad
 *
 * @author Johan Sebastian Giraldo
 *
 */
@Mapper(uses = { ArgumentoListaSimpleMapper.class })
public interface FuncionarioClaveEntidadMapper extends Serializable {

	/**
	 * Metodo que mapea una entidad a un dto
	 * @param funcionarioClaveEntidad entidad que se desea mapear a dto
	 * @return dto mapeado a partir de la entidad
	 */
	@Mapping(source = "funcionarioClaveEntidad.idFuncionarioEntidad", target = "idFuncionarioEntidad")
	@Mapping(source = "funcionarioClaveEntidad.codigoEntidad.codigoEntidad", target = "codigoEntidad")
	@Mapping(source = "funcionarioClaveEntidad.nombre", target = "nombre")
	@Mapping(source = "funcionarioClaveEntidad.idLsFuncion.idArgumento", target = "idLsFuncion")
	@Mapping(source = "funcionarioClaveEntidad.idLsGenero.idArgumento", target = "idLsGenero")
	@Mapping(source = "funcionarioClaveEntidad.idLsFuncion.resultado", target = "nombreIdLsFuncion")
	@Mapping(source = "funcionarioClaveEntidad.idLsGenero.resultado", target = "nombreIdLsGenero")
	@Mapping(source = "funcionarioClaveEntidad.cargo", target = "cargo")
	@Mapping(source = "funcionarioClaveEntidad.correo", target = "correo")
	FuncionarioClaveEntidadDTO funcionarioClaveEntidadToDTO(FuncionarioClaveEntidad funcionarioClaveEntidad);

	/**
	 * Metodo que mapea un dto a una entidad
	 * @param funcionarioClaveEntidadDTO dto que se desea mapear a entidad
	 * @return la entidad mapeada a partir del dto
	 */
	@Mapping(source = "funcionarioClaveEntidadDTO.idFuncionarioEntidad", target = "idFuncionarioEntidad")
	@Mapping(source = "funcionarioClaveEntidadDTO.codigoEntidad", target = "codigoEntidad.codigoEntidad")
	@Mapping(source = "funcionarioClaveEntidadDTO.nombre", target = "nombre")
	@Mapping(source = "funcionarioClaveEntidadDTO.idLsFuncion", target = "idLsFuncion.idArgumento")
	@Mapping(source = "funcionarioClaveEntidadDTO.idLsGenero", target = "idLsGenero.idArgumento")
	@Mapping(source = "funcionarioClaveEntidadDTO.cargo", target = "cargo")
	@Mapping(source = "funcionarioClaveEntidadDTO.correo", target = "correo")
	FuncionarioClaveEntidad funcionarioClaveEntidadDTOToEntity(FuncionarioClaveEntidadDTO funcionarioClaveEntidadDTO);

	/**
	 * Metodo que mapea una lista tipo funcionarioClaveEntidad a una lista tipo dto
	 * @param funcionarioClaveEntidad lista de funcionarioClaveEntidad que se desea mapear a dto
	 * @return la lista dto mapeada a partir de la lista
	 */
	List<FuncionarioClaveEntidadDTO> funcionarioClaveEntidadEntitiesToDTO(
			List<FuncionarioClaveEntidad> funcionarioClaveEntidad);

	/**
	 * Metodo que mapea una lista funcionarioClaveEntidadDTO a una lista funcionarioClave
	 * @param funcionarioClaveEntidadDTO lista dto que se desea mapear a entidad FuncionarioClaveEntidad
	 * @return lista tipo FuncionarioClaveEntidad a partir del dto
	 */
	List<FuncionarioClaveEntidad> funcionarioClaveEntidadDTOToEntities(
			List<FuncionarioClaveEntidadDTO> funcionarioClaveEntidadDTO);

}
