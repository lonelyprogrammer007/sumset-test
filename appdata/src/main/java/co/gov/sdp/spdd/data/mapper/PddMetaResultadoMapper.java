package co.gov.sdp.spdd.data.mapper;

import java.io.Serializable;
import java.util.List;

import org.mapstruct.AfterMapping;
import org.mapstruct.BeforeMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import co.gov.sdp.nhspdd.common.dto.ArgumentoListaSimpleDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddMetaResultadoDTO;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.spdd.data.model.afs.ArgumentoListaSimple;
import co.gov.sdp.spdd.data.model.ip.PddMetaResultado;

/**
 * Interface que contiene los mapeadores de entity a DTO y viceversa para la
 * entidad PddMetaResultado
 * 
 * @author DANIEL
 * @version 1.0 23/03/2020
 */
@Mapper(uses = { ArgumentoListaSimpleMapper.class, PddIndicadorMapper.class })
public interface PddMetaResultadoMapper extends Serializable {

	@AfterMapping
	default void afterMapping(@MappingTarget PddMetaResultado target, PddMetaResultadoDTO source) {
		if (source.getIdLsEstado() == null)
			target.setIdLsEstado(null);
		if (source.getIdIndicador() == null)
			target.setIdIndicador(null);
		if (source.getIdProyEstrategico() == null)
			target.setIdProyEstrategico(null);
		if (source.getIdProbInd() == null)
			target.setIdProbInd(null);
		if (source.getIdProyEstrategicoLocal() == null)
			target.setIdProyEstrategicoLocal(null);
	}

	@BeforeMapping
	default void beforeMapping(@MappingTarget PddMetaResultadoDTO target, PddMetaResultado source) {
		if (source.getEsFormulacion() == 0) {
			target.setStringFormulacion("No");
		} else {
			target.setStringFormulacion("Si");
		}
	}

	@Mapping(source = "pddMetaResultado.idMetaResultado", target = "idMetaResultado")
	@Mapping(source = "pddMetaResultado.magnitud", target = "magnitud")
	@Mapping(source = "pddMetaResultado.complemento", target = "complemento")
	@Mapping(source = "pddMetaResultado.esFormulacion", target = "esFormulacion")
	@Mapping(source = "pddMetaResultado.ponderacion", target = "ponderacion")
	@Mapping(source = "pddMetaResultado.sumPond", target = "sumPond")
	@Mapping(source = "pddMetaResultado.idLsVerbo.idArgumento", target = "idLsVerbo")
	@Mapping(source = "pddMetaResultado.idLsVerbo.resultado", target = "nombreVerbo")
	@Mapping(source = "pddMetaResultado.idLsUnidadMedida.idArgumento", target = "idLsUnidadMedida")
	@Mapping(source = "pddMetaResultado.idLsUnidadMedida.resultado", target = "nombreUnidadMedida")
	@Mapping(source = "pddMetaResultado.idLsTipoAnualizacion.idArgumento", target = "idLsTipoAnualizacion")
	@Mapping(source = "pddMetaResultado.idLsTipoAnualizacion.resultado", target = "nombreTipoAnulacion")
	@Mapping(source = "pddMetaResultado.idLsEstado.idArgumento", target = "idLsEstado")
	@Mapping(source = "pddMetaResultado.idLsEstado.resultado", target = "nombreEstedo")
	@Mapping(source = "pddMetaResultado.idIndicador.idIndicador", target = "idIndicador")
	@Mapping(source = "pddMetaResultado.idIndicador.nombre", target = "nombreIndicador")
	@Mapping(source = "pddMetaResultado.idProyEstrategico.idAtributo", target = "idProyEstrategico")
	@Mapping(source = "pddMetaResultado.idProyEstrategico.denominacion", target = "nombreProyEstrategico")
	@Mapping(source = "pddMetaResultado.idProbInd.idProbInd", target = "idProbInd")
	@Mapping(source = "pddMetaResultado.idProbInd.idIndicador.idIndicador", target = "idIndicadorProb")
	@Mapping(source = "pddMetaResultado.idProbInd.idIndicador.nombre", target = "nombreIndicadorProb")
	@Mapping(source = "pddMetaResultado.idProbInd.idProblematica.idProblematica", target = "idProblematica")
	@Mapping(source = "pddMetaResultado.idProbInd.idProblematica.problematica", target = "nombreProblematica")
	@Mapping(source = "pddMetaResultado.idProyEstrategicoLocal.idAtributo", target = "idProyEstrategicoLocal")
	@Mapping(source = "pddMetaResultado.idProyEstrategicoLocal.denominacion", target = "nombreProyEstrategicoLocal")
	@Mapping(source = "pddMetaResultado.fechaCreacion", target = "fechaCreacion", dateFormat = "yyyy-MM-dd")
	PddMetaResultadoDTO pddMetaResultadoEntityToDTO(PddMetaResultado pddMetaResultado);

	@Mapping(source = "pddMetaResultadoDTO.idMetaResultado", target = "idMetaResultado")
	@Mapping(source = "pddMetaResultadoDTO.magnitud", target = "magnitud")
	@Mapping(source = "pddMetaResultadoDTO.complemento", target = "complemento")
	@Mapping(source = "pddMetaResultadoDTO.esFormulacion", target = "esFormulacion")
	@Mapping(source = "pddMetaResultadoDTO.ponderacion", target = "ponderacion")
	@Mapping(source = "pddMetaResultadoDTO.sumPond", target = "sumPond")
	@Mapping(source = "pddMetaResultadoDTO.idLsVerbo", target = "idLsVerbo.idArgumento")
	@Mapping(source = "pddMetaResultadoDTO.idLsUnidadMedida", target = "idLsUnidadMedida.idArgumento")
	@Mapping(source = "pddMetaResultadoDTO.idLsTipoAnualizacion", target = "idLsTipoAnualizacion.idArgumento")
	@Mapping(source = "pddMetaResultadoDTO.idLsEstado", target = "idLsEstado.idArgumento")
	@Mapping(source = "pddMetaResultadoDTO.idIndicador", target = "idIndicador.idIndicador")
	@Mapping(source = "pddMetaResultadoDTO.idProyEstrategico", target = "idProyEstrategico.idAtributo")
	@Mapping(source = "pddMetaResultadoDTO.idProbInd", target = "idProbInd.idProbInd")
	@Mapping(source = "pddMetaResultadoDTO.idProyEstrategicoLocal", target = "idProyEstrategicoLocal.idAtributo")
	@Mapping(source = "pddMetaResultadoDTO.fechaCreacion", target = "fechaCreacion", dateFormat = "yyyy-MM-dd")
	PddMetaResultado pddMetaResultadoDTOToEntity(PddMetaResultadoDTO pddMetaResultadoDTO);

	/**
	 * Metodo que pasa una lista tipo pddMetaResultado a una lista dto
	 *
	 * @param pddMetaResultado lista que se desea convertir
	 * @return una lista dto a partir de la lista tipo pddMetaResultado
	 */
	List<PddMetaResultadoDTO> pddMetaResultadoEntitiesToDTO(List<PddMetaResultado> pddMetaResultado);

	/**
	 * Metodo que pasa una lista tipo dto a una lista PddMetaResultado
	 *
	 * @param pddMetaResultadoDTO lista a convertir
	 * @return una lista tipo PddMetaResultado a partir de una lista dto
	 */
	List<PddMetaResultado> pddMetaResultadoDTOToEntities(List<PddMetaResultadoDTO> pddMetaResultadoDTO);

}
