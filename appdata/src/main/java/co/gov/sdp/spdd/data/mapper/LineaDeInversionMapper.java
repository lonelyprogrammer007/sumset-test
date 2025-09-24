package co.gov.sdp.spdd.data.mapper;

import java.io.Serializable;
import java.util.List;

import org.mapstruct.BeforeMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import co.gov.sdp.nhspdd.common.dto.LineaDeInversionDTO;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.spdd.data.model.afs.LineaDeInversion;

/**
 * Interface que maneja los metodos de mapeo de clas linea de inversion
 *
 * @author Bryan Munoz
 *
 */
@Mapper(uses = {ArgumentoListaSimpleMapper.class})
public interface LineaDeInversionMapper extends Serializable {

	/**
	 * 
	 * @param target
	 * @param source
	 */
	@BeforeMapping
	default void beforeMapping(@MappingTarget LineaDeInversionDTO target,
			LineaDeInversion source) {
		if (source.getEstado() == 0) {
			target.setStringEstado(NHSPDDConstantes.ESTADO_STRING_INACTIVO);
		} else  {
			target.setStringEstado(NHSPDDConstantes.ESTADO_STRING_ACTIVO);
		}
	}
	
    /**
     * Metodoq ue mapea una entidad a un dto
     *
     * @param lineDeInversion objeto tipo entidad a mapear a dto
     * @return objeto dto a apartir de la entidad
     */
    @Mapping(source = "lineaDeInversion.idLineaInversion", target = "idLineaInversion")
    @Mapping(source = "lineaDeInversion.concepto", target = "concepto")
    @Mapping(source = "lineaDeInversion.establecido", target = "establecido")
    @Mapping(source = "lineaDeInversion.descripcion", target = "descripcion")
    @Mapping(source = "lineaDeInversion.fecha", target = "fecha", dateFormat = "dd/MM/yyyy")
    @Mapping(source = "lineaDeInversion.idLsSector.idArgumento", target = "idLsSector")
    @Mapping(source = "lineaDeInversion.idLsSector.resultado", target = "nombreSector")
    @Mapping(source = "lineaDeInversion.estado", target = "estado")
    LineaDeInversionDTO lineaDeInversionEntityToDTO(LineaDeInversion lineaDeInversion);

    /**
     * Metodo que mapea un dto a una entidad
     *
     * @param lineDeInversionDTO objeto tipo dto que se mapea a entidad
     * @return un objeto tipo entidad a partir del dto
     */
    @Mapping(source = "lineaDeInversionDTO.idLineaInversion", target = "idLineaInversion")
    @Mapping(source = "lineaDeInversionDTO.concepto", target = "concepto")
    @Mapping(source = "lineaDeInversionDTO.establecido", target = "establecido")
    @Mapping(source = "lineaDeInversionDTO.descripcion", target = "descripcion")
    @Mapping(source = "lineaDeInversionDTO.fecha", target = "fecha" , dateFormat = "dd/MM/yyyy")
    @Mapping(source = "lineaDeInversionDTO.idLsSector", target = "idLsSector.idArgumento")
    @Mapping(source = "lineaDeInversionDTO.estado", target = "estado")
    LineaDeInversion lineaDeInversionDTOToEntity(LineaDeInversionDTO lineaDeInversionDTO);

    /**
     * Metodo que mapea una lista tipo LineaDeInversion a dto
     *
     * @param lineasDeInversion entidad que se quiere mapear a dto
     * @return una lista dto a partir de la lista LineaDeInversion
     */
    List<LineaDeInversionDTO> lineaDeInversionEntitiesTODTO(List<LineaDeInversion> lineasDeInversion);

    /**
     * Metodo que mapea una lista dto a una lista tipo entidad LineaDeInvesion
     *
     * @param lineasDeInversionDTO lista dto que se desea mapear a entidad
     * @return lista entidad tipo LineaDeInversion a partir del dto.
     */
    List<LineaDeInversion> lineaDeInversionDTOToEntities(List<LineaDeInversionDTO> lineasDeInversionDTO);

}
