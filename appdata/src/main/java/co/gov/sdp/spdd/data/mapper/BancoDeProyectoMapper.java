package co.gov.sdp.spdd.data.mapper;

import java.io.Serializable;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import co.gov.sdp.nhspdd.common.dto.BancoDeProyectoDTO;
import co.gov.sdp.spdd.data.model.afs.BancoDeProyectos;

@Mapper
public interface BancoDeProyectoMapper extends Serializable {

	/**
	 * 
	 * @param bancoDeProyecto
	 * @return
	 */
	@Mapping(source = "bancoDeProyecto.idBancoProyecto", target = "idBancoProyecto")
	@Mapping(source = "bancoDeProyecto.nombre", target = "nombre")
	BancoDeProyectoDTO bancoDeProyectoEntityToDTO(BancoDeProyectos bancoDeProyecto);

	/**
	 * 
	 * @param bancoDeProyectoDTO
	 * @return
	 */
	@Mapping(source = "bancoDeProyectoDTO.idBancoProyecto", target = "idBancoProyecto")
	@Mapping(source = "bancoDeProyectoDTO.nombre", target = "nombre")
	BancoDeProyectos bancoDeProyectoDTOToEntity(BancoDeProyectoDTO bancoDeProyectoDTO);

	/**
	 * 
	 * @param bancoDeProyectoDTO
	 * @return
	 */
	List<BancoDeProyectos> bancoDeProyectoDTOToEntities(List<BancoDeProyectoDTO> bancoDeProyectoDTO);

	/**
	 * 
	 * @param BancoDeProyecto
	 * @return
	 */
	List<BancoDeProyectoDTO> bancoDeProyectoEntitiesToDTO(List<BancoDeProyectos> BancoDeProyecto);

}
