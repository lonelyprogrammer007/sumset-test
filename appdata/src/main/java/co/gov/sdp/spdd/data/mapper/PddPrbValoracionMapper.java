package co.gov.sdp.spdd.data.mapper;

import java.util.List;

import org.mapstruct.AfterMapping;
import org.mapstruct.BeforeMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

import co.gov.sdp.nhspdd.common.dto.ip.PddPrbValoracionDTO;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.spdd.data.model.ip.PddPrbValoracion;
/*
componentModel = "spring",
nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL,
nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL,
*/
@Mapper(uses = {ArgumentoListaSimpleMapper.class,PddCompetenciaAsociadaMapper.class,PddProblematicaMapper.class})
public interface PddPrbValoracionMapper {
	
	/**
	 * Permite realizar la asignacion de valores a variables segun alguna condicion.
	 * En este caso se asigna un valor a la variable stringMomento dependiendo del valor que tenga
	 * la variable estado.
	 * @param target objeto al cual se le va asignar el valor
	 * @param source objeto que se tiene como referencia para hacer las condiciones
	 */
	@BeforeMapping
	default void beforeMapping(@MappingTarget PddPrbValoracionDTO target,PddPrbValoracion source) {
		if (source.getMomento() == 1) 
			target.setStringMomento(NHSPDDConstantes.MOMENTO_STRING_ANTES);
		else if (source.getMomento() == 2)  
			target.setStringMomento(NHSPDDConstantes.MOMENTO_STRING_DURANTE);
		else
			target.setStringMomento(NHSPDDConstantes.MOMENTO_STRING_DESPUES);
	}
	
	@AfterMapping
	default void afterMapping(@MappingTarget PddPrbValoracion target,PddPrbValoracionDTO source)
	{
		if (source.getIdCompetenciaAsociada1() == null)
			target.setIdLsCompetencia1(null);
		if (source.getIdCompetenciaAsociada2() == null)
			target.setIdLsCompetencia2(null);
		if (source.getIdProblematica() == null)
			target.setIdProblematica(null);
		
	}
	
	/**
     * Metodo que mapea una entidad a un dto
     *
     * @param pddPrbValoracion entidad que se desea mapear a dto
     * @return dto mapeado a partir de la entidad
     */
    @Mapping(source = "pddPrbValoracion.idValoracion", target = "idValoracion")
    @Mapping(source = "pddPrbValoracion.gravedad", target = "gravedad")
    @Mapping(source = "pddPrbValoracion.duracion", target = "duracion")
    @Mapping(source = "pddPrbValoracion.impacto", target = "impacto")
    @Mapping(source = "pddPrbValoracion.debilidad", target = "debilidad")
    @Mapping(source = "pddPrbValoracion.balanceInicial", target = "balanceInicial")
    @Mapping(source = "pddPrbValoracion.observaciones", target = "observaciones")
    @Mapping(source = "pddPrbValoracion.momento", target = "momento")
    @Mapping(source = "pddPrbValoracion.idLsSector.idArgumento", target = "idLsSector")
    @Mapping(source = "pddPrbValoracion.idLsSector.resultado", target = "nombreSector")
    @Mapping(source = "pddPrbValoracion.idLsDimension.idArgumento", target = "idLsDimension")
    @Mapping(source = "pddPrbValoracion.idLsDimension.resultado", target = "nombreDimension")
    @Mapping(source = "pddPrbValoracion.idLsCompetencia1.idCompetencia", target = "idCompetenciaAsociada1")
    @Mapping(source = "pddPrbValoracion.idLsCompetencia1.idLsCompetencia.idArgumento", target = "idLsCompetencia1")
    @Mapping(source = "pddPrbValoracion.idLsCompetencia1.idLsCompetencia.resultado", target = "nombreCompetencia1")
    @Mapping(source = "pddPrbValoracion.idLsCompetencia2.idCompetencia", target = "idCompetenciaAsociada2")
    @Mapping(source = "pddPrbValoracion.idLsCompetencia2.idLsCompetencia.idArgumento", target = "idLsCompetencia2")
    @Mapping(source = "pddPrbValoracion.idLsCompetencia2.idLsCompetencia.resultado", target = "nombreCompetencia2")
    @Mapping(source = "pddPrbValoracion.idProblematica.idProblematica", target = "idProblematica")
    @Mapping(source = "pddPrbValoracion.idProblematica.problematica", target = "nombreProblematica")
    @Mapping(source = "pddPrbValoracion.agrava", target = "agrava")

    @Mapping(source = "pddPrbValoracion.contrarresta", target = "contrarresta")
  
    @Mapping(target = "stringMomento", ignore = true)
    PddPrbValoracionDTO pddPrbValoracionEntityToDTO(PddPrbValoracion pddPrbValoracion);

    /**
     * Metodo que mapea un dto a una entidad
     *
     * @param pddPrbValoracionDTO dto que se desea mapear a entidad
     * @return la entidad mapeada a partir del dto
     */
    @Mapping(source = "pddPrbValoracionDTO.idValoracion", target = "idValoracion")
    @Mapping(source = "pddPrbValoracionDTO.gravedad", target = "gravedad")
    @Mapping(source = "pddPrbValoracionDTO.duracion", target = "duracion")
    @Mapping(source = "pddPrbValoracionDTO.impacto", target = "impacto")
    @Mapping(source = "pddPrbValoracionDTO.debilidad", target = "debilidad")
    @Mapping(source = "pddPrbValoracionDTO.balanceInicial", target = "balanceInicial")
    @Mapping(source = "pddPrbValoracionDTO.observaciones", target = "observaciones")
    @Mapping(source = "pddPrbValoracionDTO.momento", target = "momento")
    @Mapping(source = "pddPrbValoracionDTO.idLsSector", target = "idLsSector.idArgumento")
    @Mapping(source = "pddPrbValoracionDTO.idLsDimension", target = "idLsDimension.idArgumento")
    @Mapping(source = "pddPrbValoracionDTO.idCompetenciaAsociada1", target = "idLsCompetencia1.idCompetencia")
    @Mapping(source = "pddPrbValoracionDTO.idCompetenciaAsociada2", target = "idLsCompetencia2.idCompetencia")
    @Mapping(source = "pddPrbValoracionDTO.idProblematica", target = "idProblematica.idProblematica")
    @Mapping(source = "pddPrbValoracionDTO.agrava", target = "agrava")
    @Mapping(source = "pddPrbValoracionDTO.contrarresta", target = "contrarresta.")
    PddPrbValoracion pddPrbValoracionDTOToEntity(PddPrbValoracionDTO pddPrbValoracionDTO);

    /**
     * Metodo que mapea una lista tipo PddPrbValoracion a una lista tipo dto
     *
     * @param pddPrbValoraciones lista de pdPrbValoracion que se desea mapear a dto
     * @return la lista dto mapeada a partir de la lista
     */
    List<PddPrbValoracionDTO> pddPrbValoracionesEntitiesToDTO(List<PddPrbValoracion> pddPrbValoraciones);

    /**
     * Metodo que mapea una lista pddPrbValoracionDTO a una lista
     *
     * @param pddPrbValoraciones lista dto que se desea mapear a entidad
     * @return lista tipo PddPrbValoracion a partir del dto
     */
    List<PddPrbValoracion> pddPrbValoracionesDTOToEntities(List<PddPrbValoracionDTO> pddPrbValoraciones);

}
