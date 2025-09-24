package co.gov.sdp.spdd.data.mapper;

import java.io.Serializable;
import java.util.List;

import org.mapstruct.BeforeMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import co.gov.sdp.nhspdd.common.dto.EquipamientoDTO;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.spdd.data.model.afs.Equipamento;

/**
 * Interface mapper de la clase Equipamiento
 *
 * @author Raul Londono Murillo
 *
 */
@Mapper(uses = {ArgumentoListaSimpleMapper.class})
public interface EquipamientoMapper extends Serializable {

	/**
	 * 
	 * @param target
	 * @param source
	 */
	@BeforeMapping
	default void beforeMapping(@MappingTarget EquipamientoDTO target,
			Equipamento source) {
		if (source.getEstado() == 0) {
			target.setStringEstado(NHSPDDConstantes.ESTADO_STRING_INACTIVO);
		} else  {
			target.setStringEstado(NHSPDDConstantes.ESTADO_STRING_ACTIVO);
		}
	}
	
    /**
     * Metodo que mapea una entidad a un dto
     *
     * @param equipamiento entidad que se desea mapear a dto
     * @return dto mapeado a partir de la entidad
     */
    @Mapping(source = "equipamiento.idEquipamento", target = "idEquipamento")
    @Mapping(source = "equipamiento.nombre", target = "nombre")
    @Mapping(source = "equipamiento.descripcion", target = "descripcion")
    @Mapping(source = "equipamiento.estado", target = "estado")
    @Mapping(source = "equipamiento.idLsCategoria.idArgumento", target = "idLsCategoria")
    @Mapping(source = "equipamiento.idLsSector.idArgumento", target = "idLsSector")
    @Mapping(source = "equipamiento.idLsCategoria.resultado", target = "nombreCategoria")
    @Mapping(source = "equipamiento.idLsSector.resultado", target = "nombreSector")
    EquipamientoDTO equipamientoEntityToDTO(Equipamento equipamiento);

    /**
     * Metodo que mapea un dto a una entidad
     *
     * @param equipamientoDTO dto que se desea mapear a entidad
     * @return la entidad mapeada a partir del dto
     */
    @Mapping(source = "equipamientoDTO.idEquipamento", target = "idEquipamento")
    @Mapping(source = "equipamientoDTO.nombre", target = "nombre")
    @Mapping(source = "equipamientoDTO.descripcion", target = "descripcion")
    @Mapping(source = "equipamientoDTO.estado", target = "estado")
    @Mapping(source = "equipamientoDTO.idLsCategoria", target = "idLsCategoria.idArgumento")
    @Mapping(source = "equipamientoDTO.idLsSector", target = "idLsSector.idArgumento")
    Equipamento equipamientoDTOToEntity(EquipamientoDTO equipamientoDTO);

    /**
     * Metodo que mapea una lista tipo equipamiento a una lista tipo dto
     *
     * @param equipamiento lista de equipamiento que se desea mapear a dto
     * @return la lista dto mapeada a partir de la lista
     */
    List<EquipamientoDTO> equipamientoEntitiesToDTO(List<Equipamento> equipamiento);

    /**
     * Metodo que mapea una lista equipamientoDTO a una lista equipamiento
     *
     * @param equipamientoDTO lista dto que se desea mapear a entidad
     * equipamiento
     * @return lista tipo equipamiento a partir del dto
     */
    List<Equipamento> equipamientoDTOToEntities(List<EquipamientoDTO> equipamientoDTO);
}
