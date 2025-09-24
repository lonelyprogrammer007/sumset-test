package co.gov.sdp.spdd.data.mapper;

import java.io.Serializable;
import java.util.List;

import org.mapstruct.BeforeMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import co.gov.sdp.nhspdd.common.dto.ip.CompromisoEstrategicoDTO;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.spdd.data.model.ip.CompromisoEstrategico;

/**
 * Interface que contiene los mapeadores de entity a DTO y viceversa para la entidad CompromisoEstrategico
 * @author DANIEL
 * @version 1.0 25/02/2020
 */
@Mapper(uses = {ArgumentoListaSimpleMapper.class})
public interface CompromisoEstrategicoMapper extends Serializable {
	
	/**
	 * Permite realizar la asignacion de valores a variables segun alguna condicion.
	 * En este caso se asigna un valor a la variable stringEstado dependiendo del valor que tenga
	 * la variable estado.
	 * @param target objeto al cual se le va asignar el valor
	 * @param source objeto que se tiene como referencia para hacer las condiciones
	 */
	@BeforeMapping
	default void beforeMapping(@MappingTarget CompromisoEstrategicoDTO target,CompromisoEstrategico source) {
		if (source.getEstado() == 0) {
			target.setStringEstado(NHSPDDConstantes.ESTADO_STRING_INACTIVO);
		} else  {
			target.setStringEstado(NHSPDDConstantes.ESTADO_STRING_ACTIVO);
		}
	}
	
    /**
     * Metodo que mapea una entidad a un dto
     *
     * @param CompromisoEstrategico entidad que se desea mapear a dto
     * @return dto mapeado a partir de la entidad
     */
    @Mapping(source = "compromisoEstrategico.idCompromiso", target = "idCompromiso")
    @Mapping(source = "compromisoEstrategico.estado", target = "estado")
    @Mapping(source = "compromisoEstrategico.idLsEstrategico.idArgumento", target = "idCompromisoEstrategico")
    @Mapping(source = "compromisoEstrategico.idLsEstrategico.resultado", target = "nombreCompromisoEstrategico")
    @Mapping(source = "compromisoEstrategico.idLsTematica.idArgumento", target = "idTematica")
    @Mapping(source = "compromisoEstrategico.idLsTematica.resultado", target = "nombreTematica")
    @Mapping(target = "stringEstado", ignore = true)
    CompromisoEstrategicoDTO compromisoEstrategicoEntityToDTO(CompromisoEstrategico compromisoEstrategico);

    /**
     * Metodo que mapea un dto a una entidad
     *
     * @param CompromisoEstrategicoDTO dto que se desea mapear a entidad
     * @return la entidad mapeada a partir del dto
     */
    @Mapping(source = "compromisoEstrategicoDTO.idCompromiso", target = "idCompromiso")
    @Mapping(source = "compromisoEstrategicoDTO.estado", target = "estado")
    @Mapping(source = "compromisoEstrategicoDTO.idCompromisoEstrategico", target = "idLsEstrategico.idArgumento")
    @Mapping(source = "compromisoEstrategicoDTO.nombreCompromisoEstrategico", target = "idLsEstrategico.argumento")
    @Mapping(source = "compromisoEstrategicoDTO.idTematica", target = "idLsTematica.idArgumento")
    @Mapping(source = "compromisoEstrategicoDTO.nombreTematica", target = "idLsTematica.argumento")    
    CompromisoEstrategico compromisoEstrategicoDTOToEntity(CompromisoEstrategicoDTO compromisoEstrategicoDTO);

    /**
     * Metodo que mapea una lista tipo CompromisoEstrategico a una lista tipo dto
     *
     * @param CompromisoEstrategico lista de archivosProcesados que se desea mapear
     * a dto
     * @return la lista dto mapeada a partir de la lista
     */
    List<CompromisoEstrategicoDTO> compromisoEstrategicoEntitiesToDTO(List<CompromisoEstrategico> compromisosEstrategicos);

    /**
     * Metodo que mapea una lista CompromisoEstrategicoDTO a una lista
     * ArchivoProcesado
     *
     * @param CompromisoEstrategicoDTO lista dto que se desea mapear a entidad
     * ArchivoProcesado
     * @return lista tipo ArchivoProcesado a partir del dto
     */
    List<CompromisoEstrategico> compromisoEstrategicoDTOToEntities(List<CompromisoEstrategicoDTO> compromisoEstrategicoDTO);


}
