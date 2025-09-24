package co.gov.sdp.spdd.data.mapper;

import java.io.Serializable;
import java.util.List;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import co.gov.sdp.nhspdd.common.dto.PotInstrumentoDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddPrbValoracionDTO;
import co.gov.sdp.spdd.data.model.ip.PddPrbValoracion;
import co.gov.sdp.spdd.data.model.ip.PotInstrumento;

@Mapper
public interface PotInstrumentoMapper extends Serializable {
	
	@AfterMapping
	default void afterMapping(@MappingTarget PotInstrumento target,PotInstrumentoDTO source)
	{
		if (source.getIdLsNivelInst() == null)
			target.setIdLsNivelInst(null);
	}
	
    /**
     * Metodo que mapea una entidad a un dto
     *
     * @param PotInstrumento entidad que se desea mapear a dto
     * @return dto mapeado a partir de la entidad
     */
    @Mapping(source = "potInstrumento.idInstrumentoPot", target = "idInstrumentoPot")
    @Mapping(source = "potInstrumento.codigo", target = "codigoPotInstrumento")
    @Mapping(source = "potInstrumento.denominacion.idArgumento", target = "idLsDenominacion")
    @Mapping(source = "potInstrumento.denominacion.resultado", target = "stringLsDenominacion")
    @Mapping(source = "potInstrumento.idPot.idPot", target = "idPot")
    @Mapping(source = "potInstrumento.idLsNivelInst.idArgumento", target = "idLsNivelInst")
    @Mapping(source = "potInstrumento.idLsNivelInst.resultado", target = "stringLsNivelInst")
    PotInstrumentoDTO potInstrumentoEntityToDTO(PotInstrumento potInstrumento);

    /**
     * Metodo que mapea un dto a una entidad
     *
     * @param PotInstrumentoDTO dto que se desea mapear a entidad
     * @return la entidad mapeada a partir del dto
     */
    @Mapping(source = "potInstrumentoDTO.idInstrumentoPot", target = "idInstrumentoPot")
    @Mapping(source = "potInstrumentoDTO.codigoPotInstrumento", target = "codigo")
    @Mapping(source = "potInstrumentoDTO.idLsDenominacion", target = "denominacion.idArgumento")
    @Mapping(source = "potInstrumentoDTO.idPot", target = "idPot.idPot")
    @Mapping(source = "potInstrumentoDTO.idLsNivelInst", target = "idLsNivelInst.idArgumento")
    PotInstrumento potInstrumentoDTOToEntity(PotInstrumentoDTO potInstrumentoDTO);

    /**
     * Metodo que mapea una lista tipo PotInstrumento a una lista tipo dto
     *
     * @param listaPotInstrumento lista de PotInstrumento que se desea mapear
     * a dto
     * @return la lista dto mapeada a partir de la lista
     */
    List<PotInstrumentoDTO> potInstrumentoEntitiesToDTO(List<PotInstrumento> listaPotInstrumento);

    /**
     * Metodo que mapea una lista PotInstrumentoDTO a una lista
     * PotInstrumento
     *
     * @param PotInstrumentoDTO lista dto que se desea mapear a entidad
     * PotInstrumento
     * @return lista tipo PotInstrumento a partir del dto
     */
    List<PotInstrumento> potInstrumentoDTOToEntities(List<PotInstrumentoDTO> listaPotInstrumentoDTO);
	
}
