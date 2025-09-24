package co.gov.sdp.spdd.data.mapper;

import java.io.Serializable;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import co.gov.sdp.nhspdd.common.dto.bp.BpAporteCiudadanoDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvLineaDTO;
import co.gov.sdp.spdd.data.model.bp.BpAporteCiudadano;
import co.gov.sdp.spdd.data.model.bp.BpProyInvLinea;

/**
 * Clase que se encarga del mapeo de objetos entre BpAporteCiudadano y BpAporteCiudadanoDTO
 * @author DANIEL
 * @version 16/04/2020
 */
@Mapper
public interface BpAporteCiudadanoMapper extends Serializable {
	
	/**
     * Metodo que mapea una entidad a un dto
     *
     * @param bpAporteCiudadano entidad que se desea mapear a dto
     * @return dto mapeado a partir de la entidad
     */
    @Mapping(source = "bpAporteCiudadano.idAporte", target = "idAporte")
    @Mapping(source = "bpAporteCiudadano.consecutivo", target = "consecutivo")
    @Mapping(source = "bpAporteCiudadano.accion", target = "accion")
    @Mapping(source = "bpAporteCiudadano.fuente", target = "fuente")
    @Mapping(source = "bpAporteCiudadano.idArchivo.idArchivo", target = "idArchivo")
    @Mapping(source = "bpAporteCiudadano.idArchivo.nombreArchivo", target = "stringArchivo")
    @Mapping(source = "bpAporteCiudadano.idNivelAtributoPdd.idAtributo", target = "idNivelAtributoPdd")
    @Mapping(source = "bpAporteCiudadano.idNivelAtributoPdd.denominacion", target = "stringNivelAtributoPdd")
    @Mapping(source = "bpAporteCiudadano.idNivelAtributoPdd.idPddNivel.idPddNivel", target = "idPddNivel")
    @Mapping(source = "bpAporteCiudadano.idNivelAtributoPdd.idPddNivel.descripcion", target = "stringPddNivel")
    @Mapping(source = "bpAporteCiudadano.idNivelAtributoPdd.idPddNivel.codNivel", target = "numeroPddNivel")
    BpAporteCiudadanoDTO bpAporteCiudadanoEntityToDTO(BpAporteCiudadano bpAporteCiudadano);

    /**
     * Metodo que mapea un dto a una entidad
     *
     * @param bpAporteCiudadanoDTO dto que se desea mapear a entidad
     * @return la entidad mapeada a partir del dto
     */
    @Mapping(source = "bpAporteCiudadanoDTO.idAporte", target = "idAporte")
    @Mapping(source = "bpAporteCiudadanoDTO.consecutivo", target = "consecutivo")
    @Mapping(source = "bpAporteCiudadanoDTO.accion", target = "accion")
    @Mapping(source = "bpAporteCiudadanoDTO.fuente", target = "fuente")
    @Mapping(source = "bpAporteCiudadanoDTO.idArchivo", target = "idArchivo.idArchivo")
    @Mapping(source = "bpAporteCiudadanoDTO.idNivelAtributoPdd", target = "idNivelAtributoPdd.idAtributo")
    BpAporteCiudadano bpAporteCiudadanoDTOToEntity(BpAporteCiudadanoDTO bpAporteCiudadanoDTO);

    /**
     *  Metodo que mapea una lista de entidades a una lista de DTO
     *
     * @param bpAportesCiudadano lista entidades que se desea mapear a DTO
     * @return lista de DTO correspondiente.
     */
    List<BpAporteCiudadanoDTO> bpAportesCiudadanoEntitiesToDTO(List<BpAporteCiudadano> bpAportesCiudadano);

    /**
     * Metodo que mapea una lista de DTO a una lista de entidades
     *
     * @param bpAportesCiudadanoDTO lista dto que se desea mapear a entidad
     * @return lista de entidades correspondiente.
     */
    List<BpAporteCiudadano> bpAportesCiudadanoDTOToEntities(List<BpAporteCiudadanoDTO> bpAportesCiudadanoDTO);
}
