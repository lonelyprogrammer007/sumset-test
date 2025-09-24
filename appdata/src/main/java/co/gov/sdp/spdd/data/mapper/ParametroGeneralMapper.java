package co.gov.sdp.spdd.data.mapper;

import java.io.Serializable;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import co.gov.sdp.nhspdd.common.dto.ParametroGeneralDTO;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.spdd.data.model.afs.ParametroGeneral;

/**
 * Interface que permite mapear un dto a una entidad y viceversa
 *
 * @author Bryan Munoz
 *
 */
@Mapper
public interface ParametroGeneralMapper extends Serializable {

    /**
     * Metodo que convierte una entidad a un dto
     *
     * @param parametroGeneral entidad a convertir a dto
     * @return un objeto tipo dto
     */
    @Mapping(source = "parametroGeneral.codigoParametro", target = "codigoParametro")
    @Mapping(source = "parametroGeneral.nombre", target = "nombre")
    @Mapping(source = "parametroGeneral.descripcion", target = "descripcion")
    @Mapping(source = "parametroGeneral.argumento", target = NHSPDDConstantes.ARGUMENTO)
    @Mapping(source = "parametroGeneral.fechaCreacion", target = "fechaCreacion", dateFormat = "yyyy-MM-dd")
    @Mapping(source = "parametroGeneral.fechaModificacion", target = "fechaModificacion", dateFormat = "yyyy-MM-dd")
    ParametroGeneralDTO parametroEntityToDTO(ParametroGeneral parametroGeneral);

    /**
     * Metodo que convierte un dto a una entidad
     *
     * @param parametroGeneralDTO dto a convertir
     * @return un objeto entidad a partir de un dto
     */
    @Mapping(source = "parametroGeneralDTO.codigoParametro", target = "codigoParametro")
    @Mapping(source = "parametroGeneralDTO.nombre", target = "nombre")
    @Mapping(source = "parametroGeneralDTO.descripcion", target = "descripcion")
    @Mapping(source = "parametroGeneralDTO.argumento", target = NHSPDDConstantes.ARGUMENTO)
    @Mapping(source = "parametroGeneralDTO.fechaCreacion", target = "fechaCreacion", dateFormat = "yyyy-MM-dd")
    @Mapping(source = "parametroGeneralDTO.fechaModificacion", target = "fechaModificacion", dateFormat = "yyyy-MM-dd")
    ParametroGeneral parametroDTOToEntity(ParametroGeneralDTO parametroGeneralDTO);

    /**
     * Metodo que pasa una lista tipo parametro general a una lista dto
     *
     * @param parametrosGenerales lista que se desea convertir
     * @return una lista dto a partir de la lista tipo parametro general
     */
    List<ParametroGeneralDTO> parametroGeneralEntitiesToDTO(List<ParametroGeneral> parametrosGenerales);

    /**
     * Metodo que pasa una lista tipo dto a una lista parametro general
     *
     * @param parametrosGeneralesDTO lista a convertir
     * @return una lista tipo parametro general a partir de una lista dto
     */
    List<ParametroGeneral> parametroGeneralDTOToEntities(List<ParametroGeneralDTO> parametrosGeneralesDTO);
}
