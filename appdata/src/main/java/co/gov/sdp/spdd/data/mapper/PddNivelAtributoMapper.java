package co.gov.sdp.spdd.data.mapper;

import java.io.Serializable;
import java.util.List;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import co.gov.sdp.nhspdd.common.dto.PddNivelAtributoDTO;
import co.gov.sdp.spdd.data.model.ip.PddNivelAtributo;

@Mapper
public interface PddNivelAtributoMapper extends Serializable {
	
	@AfterMapping
	default void afterMapping(@MappingTarget PddNivelAtributo target,PddNivelAtributoDTO source)
	{
		if (source.getIdAtributoPadre() == null)
			target.setIdAtributoPadre(null);
		if (source.getIdLsGeneroGerente() == null)
			target.setIdLsGeneroGerente(null);
		if (source.getIdPddNivel() == null)
			target.setIdPddNivel(null);
	}

	@Mapping(source = "pddNivelAtributo.idAtributo", target = "idAtributo")
	@Mapping(source = "pddNivelAtributo.numero", target = "numero")
	@Mapping(source = "pddNivelAtributo.denominacion", target = "denominacion")
	@Mapping(source = "pddNivelAtributo.ponderacion", target = "ponderacion")
	@Mapping(source = "pddNivelAtributo.nombreGerente", target = "nombreGerente")
	@Mapping(source = "pddNivelAtributo.correoGerente", target = "correoGerente")
	@Mapping(source = "pddNivelAtributo.proyectoEstrategico", target = "proyectoEstrategico")
	@Mapping(source = "pddNivelAtributo.sumPond", target = "sumPond")
	@Mapping(source = "pddNivelAtributo.idLsGeneroGerente.idArgumento", target = "idLsGeneroGerente")
	@Mapping(source = "pddNivelAtributo.idLsGeneroGerente.resultado", target = "stringIdLsGeneroGerente")
	@Mapping(source = "pddNivelAtributo.idPddNivel.idPddNivel", target = "idPddNivel")
	@Mapping(source = "pddNivelAtributo.idPddNivel.descripcion", target = "stringIdPddNivel")
	@Mapping(source = "pddNivelAtributo.idPddNivel.codNivel", target = "codigoPddNivel")
	@Mapping(source = "pddNivelAtributo.idAtributoPadre.idAtributo", target = "idAtributoPadre")
	@Mapping(source = "pddNivelAtributo.idAtributoPadre.denominacion", target = "stringIdAtributoPadre")
	PddNivelAtributoDTO pddNivelAtributoEntityToDTO(PddNivelAtributo pddNivelAtributo);

	@Mapping(source = "pddNivelAtributoDTO.idAtributo", target = "idAtributo")
	@Mapping(source = "pddNivelAtributoDTO.numero", target = "numero")
	@Mapping(source = "pddNivelAtributoDTO.denominacion", target = "denominacion")
	@Mapping(source = "pddNivelAtributoDTO.ponderacion", target = "ponderacion")
	@Mapping(source = "pddNivelAtributoDTO.nombreGerente", target = "nombreGerente")
	@Mapping(source = "pddNivelAtributoDTO.correoGerente", target = "correoGerente")
	@Mapping(source = "pddNivelAtributoDTO.proyectoEstrategico", target = "proyectoEstrategico")
	@Mapping(source = "pddNivelAtributoDTO.sumPond", target = "sumPond")
	@Mapping(source = "pddNivelAtributoDTO.idLsGeneroGerente", target = "idLsGeneroGerente.idArgumento")
	@Mapping(source = "pddNivelAtributoDTO.idPddNivel", target = "idPddNivel.idPddNivel")
	@Mapping(source = "pddNivelAtributoDTO.idAtributoPadre", target = "idAtributoPadre.idAtributo")
	PddNivelAtributo pddNivelAtributoDTOToEntity(PddNivelAtributoDTO pddNivelAtributoDTO);

	/**
	 * Metodo que pasa una lista tipo nivel atributo a una lista dto
	 *
	 * @param nivelesAtributo lista que se desea convertir
	 * @return una lista dto a partir de la lista tipo nivel atributo
	 */
	List<PddNivelAtributoDTO> nivelAtributoEntitiesToDTO(List<PddNivelAtributo> nivelesAtributo);

	/**
	 * Metodo que pasa una lista tipo dto a una lista nivel atributo
	 *
	 * @param nivelesAtributoDTO lista a convertir
	 * @return una lista tipo nivel atributo a partir de una lista dto
	 */
	List<PddNivelAtributo> nivelAtributoDTOToEntities(List<PddNivelAtributoDTO> nivelesAtributoDTO);

}
