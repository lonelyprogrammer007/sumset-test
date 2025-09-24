package co.gov.sdp.spdd.data.mapper;

import java.io.Serializable;
import java.util.List;

import org.mapstruct.BeforeMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import co.gov.sdp.nhspdd.common.dto.ComponenteGastoDTO;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.spdd.data.model.afs.ComponenteGasto;

/**
 * Interface mapper de la clase ComponenteGasto
 *
 * @author Bryan Munoz
 *
 */
@Mapper(uses = {ArgumentoListaSimpleMapper.class})
public interface ComponenteGastoMapper extends Serializable {
	
	/**
	 * 
	 * @param target
	 * @param source
	 */
	@BeforeMapping
	default void beforeMapping(@MappingTarget ComponenteGastoDTO target,
			ComponenteGasto source) {
		if (source.getEstado() == 0) {
			target.setStringEstado(NHSPDDConstantes.ESTADO_STRING_INACTIVO);
		} else  {
			target.setStringEstado(NHSPDDConstantes.ESTADO_STRING_ACTIVO);
		}
	}

    /**
     * Metodo que mapea una entidad a un dto
     *
     * @param componenteGasto entidad que se desea mapear a dto
     * @return dto mapeado a partir de la entidad
     */
    @Mapping(source = "componenteGasto.idComponenteGasto", target = "idComponenteGasto")
    @Mapping(source = "componenteGasto.codigoComponente", target = "codigoComponente")
    @Mapping(source = "componenteGasto.estado", target = "estado")
    @Mapping(source = "componenteGasto.nombreComponente", target = "nombreComponente")
    @Mapping(source = "componenteGasto.idLsTipoProyecto.idArgumento", target = "idLsTipoProyecto")
    @Mapping(source = "componenteGasto.idLsTipoProyecto.resultado", target = "tipoProyecto")
    ComponenteGastoDTO componenteGastoEntityToDTO(ComponenteGasto componenteGasto);

    /**
     * Metodo que mapea un dto a una entidad
     *
     * @param componenteGastoDTO dto que se desea mapear a entidad
     * @return la entidad mapeada a partir del dto
     */
    @Mapping(source = "componenteGastoDTO.idComponenteGasto", target = "idComponenteGasto")
    @Mapping(source = "componenteGastoDTO.codigoComponente", target = "codigoComponente")
    @Mapping(source = "componenteGastoDTO.estado", target = "estado")
    @Mapping(source = "componenteGastoDTO.nombreComponente", target = "nombreComponente")
    @Mapping(source = "componenteGastoDTO.idLsTipoProyecto", target = "idLsTipoProyecto.idArgumento")
    ComponenteGasto componenteGastoDTOToEntity(ComponenteGastoDTO componenteGastoDTO);

    /**
     * Metodo que mapea una lista tipo ComponenteGasto a una lista tipo dto
     *
     * @param componenteGasto lista de componenteGasto que se desea mapear a dto
     * @return la lista dto mapeada a partir de la lista
     */
    List<ComponenteGastoDTO> componenteGastoEntitiesToDTO(List<ComponenteGasto> componenteGastoDTO);

    /**
     * Metodo que mapea una lista componenteGastoDTO a una lista componenteGasto
     *
     * @param ComponenteGastoDTO lista dto que se desea mapear a entidad
     * componenteGasto
     * @return lista tipo componenteGasto a partir del dto
     */
    List<ComponenteGasto> componenteGastoDTOToEntities(List<ComponenteGastoDTO> componenteGastoDTO);
}
