package co.gov.sdp.spdd.data.mapper;

import java.io.Serializable;
import java.util.List;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import co.gov.sdp.nhspdd.common.dto.PddNivelAtributoDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PdlNivelAtributoDTO;
import co.gov.sdp.spdd.data.model.ip.PddNivelAtributo;
import co.gov.sdp.spdd.data.model.ip.PdlNivelAtributo;

@Mapper
public interface PdlNivelAtributoMapper extends Serializable {
	
	@AfterMapping
	default void afterMapping(@MappingTarget PdlNivelAtributo target, PdlNivelAtributoDTO source)
	{
		if (source.getIdAtributoPadre() == null)
			target.setIdAtributoPadre(null);
		if (source.getIdLsGeneroGerente() == null)
			target.setIdLsGeneroGerente(null);
		if (source.getIdPdlNivel() == null)
			target.setIdPdlNivel(null);
	}

	@Mapping(source = "pdlNivelAtributo.idAtributo", target = "idAtributo")
	@Mapping(source = "pdlNivelAtributo.numero", target = "numero")
	@Mapping(source = "pdlNivelAtributo.denominacion", target = "denominacion")
	@Mapping(source = "pdlNivelAtributo.ponderacion", target = "ponderacion")
	@Mapping(source = "pdlNivelAtributo.nombreGerente", target = "nombreGerente")
	@Mapping(source = "pdlNivelAtributo.correoGerente", target = "correoGerente")
	@Mapping(source = "pdlNivelAtributo.proyectoEstrategico", target = "proyectoEstrategico")
	@Mapping(source = "pdlNivelAtributo.idLsGeneroGerente.idArgumento", target = "idLsGeneroGerente")
	@Mapping(source = "pdlNivelAtributo.idLsGeneroGerente.resultado", target = "stringIdLsGeneroGerente")
	@Mapping(source = "pdlNivelAtributo.idPdlNivel.idPdlNivel", target = "idPdlNivel")
	@Mapping(source = "pdlNivelAtributo.idPdlNivel.descripcion", target = "stringIdPdlNivel")
	@Mapping(source = "pdlNivelAtributo.idAtributoPadre.idAtributo", target = "idAtributoPadre")
	@Mapping(source = "pdlNivelAtributo.idAtributoPadre.denominacion", target = "stringIdAtributoPadre")
	PdlNivelAtributoDTO pdlNivelAtributoEntityToDTO(PdlNivelAtributo pdlNivelAtributo);

	@Mapping(source = "pdlNivelAtributoDTO.idAtributo", target = "idAtributo")
	@Mapping(source = "pdlNivelAtributoDTO.numero", target = "numero")
	@Mapping(source = "pdlNivelAtributoDTO.denominacion", target = "denominacion")
	@Mapping(source = "pdlNivelAtributoDTO.ponderacion", target = "ponderacion")
	@Mapping(source = "pdlNivelAtributoDTO.nombreGerente", target = "nombreGerente")
	@Mapping(source = "pdlNivelAtributoDTO.correoGerente", target = "correoGerente")
	@Mapping(source = "pdlNivelAtributoDTO.proyectoEstrategico", target = "proyectoEstrategico")
	@Mapping(source = "pdlNivelAtributoDTO.idLsGeneroGerente", target = "idLsGeneroGerente.idArgumento")
	@Mapping(source = "pdlNivelAtributoDTO.idPdlNivel", target = "idPdlNivel.idPdlNivel")
	@Mapping(source = "pdlNivelAtributoDTO.idAtributoPadre", target = "idAtributoPadre.idAtributo")
	PdlNivelAtributo pdlNivelAtributoDTOToEntity(PdlNivelAtributoDTO pdlNivelAtributoDTO);

	/**
	 * Metodo que pasa una lista tipo nivel atributo a una lista dto
	 *
	 * @param nivelesAtributo lista que se desea convertir
	 * @return una lista dto a partir de la lista tipo nivel atributo
	 */
	List<PdlNivelAtributoDTO> nivelAtributoEntitiesToDTO(List<PdlNivelAtributo> nivelesAtributo);

	/**
	 * Metodo que pasa una lista tipo dto a una lista nivel atributo
	 *
	 * @param nivelesAtributoDTO lista a convertir
	 * @return una lista tipo nivel atributo a partir de una lista dto
	 */
	List<PdlNivelAtributo> nivelAtributoDTOToEntities(List<PdlNivelAtributoDTO> nivelesAtributoDTO);

}
