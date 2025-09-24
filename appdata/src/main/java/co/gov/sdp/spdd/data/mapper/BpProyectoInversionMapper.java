package co.gov.sdp.spdd.data.mapper;

import java.io.Serializable;
import java.util.List;

import org.mapstruct.AfterMapping;
import org.mapstruct.BeforeMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import co.gov.sdp.nhspdd.common.dto.bp.BpProyectoInversionDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddPrbValoracionDTO;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.spdd.data.model.bp.BpProyectoInversion;
import co.gov.sdp.spdd.data.model.ip.PddPrbValoracion;

@Mapper
public interface BpProyectoInversionMapper extends Serializable {
	
	/**
	 * Permite realizar la asignacion de valores a variables segun alguna condicion.
	 * @param target objeto al cual se le va asignar el valor
	 * @param source objeto que se tiene como referencia para hacer las condiciones
	 */
	@BeforeMapping
	default void beforeMapping(@MappingTarget BpProyectoInversionDTO target,BpProyectoInversion source) {
	}
	
	@AfterMapping
	default void afterMapping(@MappingTarget BpProyectoInversion target,BpProyectoInversionDTO source)
	{
		if(source.getIdLsUnidad() == null)
		{
			target.setIdLsUnidad(null);
		}
		if(source.getIdAtributoPdd() == null)
		{
			target.setIdAtributoPdd(null);
		}
		if(source.getIdAtributoPdl() == null)
		{
			target.setIdAtributoPdl(null);
		}
		if(source.getIdPlanDesarrolloDistrital() == null)
		{
			target.setIdPlanDesarrollo(null);
		}
		if(source.getIdLsSectorAl() == null)
		{
			target.setIdLsSectorAl(null);
		}		
	}
	
	/**
     * Metodo que mapea una entidad a un dto
     *
     * @param bpProyectoInversion entidad que se desea mapear a dto
     * @return dto mapeado a partir de la entidad
     */
    @Mapping(source = "bpProyectoInversion.idProyInversion", target = "idProyInversion")
    @Mapping(source = "bpProyectoInversion.codigo", target = "codigoProyecto")
    @Mapping(source = "bpProyectoInversion.nombre", target = "nombreProyecto")
    @Mapping(source = "bpProyectoInversion.codigoBpin", target = "codigoBpin")
    @Mapping(source = "bpProyectoInversion.codigoEntidad.codigoEntidad", target = "codigoEntidad")
    @Mapping(source = "bpProyectoInversion.codigoEntidad.idLsClasificacion.idArgumento", target = "idLsClasificacionEntidad")
    @Mapping(source = "bpProyectoInversion.codigoEntidad.idLsClasificacion.resultado", target = "stringEntidad")
    @Mapping(source = "bpProyectoInversion.codigoEntidad.idBancoProyecto.idBancoProyecto", target = "idBancoProyecto")
    @Mapping(source = "bpProyectoInversion.codigoEntidad.idBancoProyecto.nombre", target = "stringBancoProyecto")
    @Mapping(source = "bpProyectoInversion.nombreBpin", target = "nombreBpin")
    @Mapping(source = "bpProyectoInversion.idLsEtapa.idArgumento", target = "idLsEtapa")
	@Mapping(source = "bpProyectoInversion.idLsEtapa.resultado", target = "stringLsEtapa")
    @Mapping(source = "bpProyectoInversion.nombrePoai", target = "nombrePoai")
    @Mapping(source = "bpProyectoInversion.idAtributoPdd.idAtributo", target = "idAtributoPdd")
    @Mapping(source = "bpProyectoInversion.idAtributoPdd.denominacion", target = "stringAtributoPdd")    
    @Mapping(source = "bpProyectoInversion.idAtributoPdl.idAtributo", target = "idAtributoPdl")
    @Mapping(source = "bpProyectoInversion.idAtributoPdl.denominacion", target = "stringAtributoPdl") 
    @Mapping(source = "bpProyectoInversion.idAtributoPdl.idPdlNivel.idPlanLocal.idPlanLocal", target = "idPlanDesarrolloLocal")
    @Mapping(source = "bpProyectoInversion.idAtributoPdl.idPdlNivel.idPlanLocal.nombrePlan", target = "stringPlanDesarrolloLocal")
    @Mapping(source = "bpProyectoInversion.idPlanDesarrollo.idPlanDesarrollo", target = "idPlanDesarrolloDistrital")
    @Mapping(source = "bpProyectoInversion.idPlanDesarrollo.nombrePlan", target = "stringPlanDesarrolloDistrital")
    @Mapping(source = "bpProyectoInversion.idLsSectorAl.idArgumento", target = "idLsSectorAl")
    @Mapping(source = "bpProyectoInversion.idLsSectorAl.resultado", target = "stringLsSectorAl")
	@Mapping(source = "bpProyectoInversion.antecedente", target = "antecedente")
    @Mapping(source = "bpProyectoInversion.situacion", target = "situacion")
    @Mapping(source = "bpProyectoInversion.descripcionUniverso", target = "descripcionUniverso")
    @Mapping(source = "bpProyectoInversion.cuantificacion", target = "cuantificacion")
    @Mapping(source = "bpProyectoInversion.idLsUnidad.idArgumento", target = "idLsUnidad")
	@Mapping(source = "bpProyectoInversion.idLsUnidad.resultado", target = "stringLsUnidad")
    @Mapping(source = "bpProyectoInversion.descripcion", target = "descripcion")
    @Mapping(source = "bpProyectoInversion.objetivo", target = "objetivo")
    @Mapping(source = "bpProyectoInversion.observacion", target = "observacion")
    @Mapping(source = "bpProyectoInversion.nombreGerente", target = "nombreGerente")
    @Mapping(source = "bpProyectoInversion.correo", target = "correo")
    @Mapping(source = "bpProyectoInversion.telefono", target = "telefono")
    @Mapping(source = "bpProyectoInversion.cargo", target = "cargo")
    @Mapping(source = "bpProyectoInversion.area", target = "area")
    @Mapping(source = "bpProyectoInversion.fechaResponsable", target = "fechaResponsable",dateFormat = "dd-MM-yyyy")
    @Mapping(source = "bpProyectoInversion.obseracionReg", target = "obseracionReg")
    @Mapping(source = "bpProyectoInversion.version", target = "version")
    @Mapping(source = "bpProyectoInversion.aporteCiudadano", target = "aporteCiudadano")
    @Mapping(source = "bpProyectoInversion.bloqueo", target = "bloqueo")
    @Mapping(source = "bpProyectoInversion.idLsEstado.idArgumento", target = "idLsEstado")
    @Mapping(source = "bpProyectoInversion.idLsEstado.resultado", target = "stringLsEstado")
    @Mapping(source = "bpProyectoInversion.fechaEstado", target = "fechaEstado",dateFormat = "dd-MM-yyyy")
    @Mapping(source = "bpProyectoInversion.fechaVersion", target = "fechaVersion",dateFormat = "dd-MM-yyyy")
    @Mapping(source = "bpProyectoInversion.idArchUcm", target = "idArchUcm")
    @Mapping(source = "bpProyectoInversion.peso", target = "peso")
    @Mapping(source = "bpProyectoInversion.enfoqueGenero", target = "enfoqueGenero")
	@Mapping(target = "stringstipoProy", ignore = true)
    @Mapping(target = "stringSector", ignore = true)
    BpProyectoInversionDTO bpProyectoInversionEntityToDTO(BpProyectoInversion bpProyectoInversion);

    /**
     * Metodo que mapea un dto a una entidad
     *
     * @param bpProyectoInversionDTO dto que se desea mapear a entidad
     * @return la entidad mapeada a partir del dto
     */
    @Mapping(source = "bpProyectoInversionDTO.idProyInversion", target = "idProyInversion")
    @Mapping(source = "bpProyectoInversionDTO.codigoProyecto", target = "codigo")
    @Mapping(source = "bpProyectoInversionDTO.nombreProyecto", target = "nombre")
    @Mapping(source = "bpProyectoInversionDTO.codigoBpin", target = "codigoBpin")
    @Mapping(source = "bpProyectoInversionDTO.codigoEntidad", target = "codigoEntidad.codigoEntidad")
    @Mapping(source = "bpProyectoInversionDTO.nombreBpin", target = "nombreBpin")
    @Mapping(source = "bpProyectoInversionDTO.idLsEtapa", target = "idLsEtapa.idArgumento")
    @Mapping(source = "bpProyectoInversionDTO.nombrePoai", target = "nombrePoai")
    @Mapping(source = "bpProyectoInversionDTO.idAtributoPdd", target = "idAtributoPdd.idAtributo")
	@Mapping(source = "bpProyectoInversionDTO.idAtributoPdl", target = "idAtributoPdl.idAtributo")
    @Mapping(source = "bpProyectoInversionDTO.antecedente", target = "antecedente")
    @Mapping(source = "bpProyectoInversionDTO.situacion", target = "situacion")
    @Mapping(source = "bpProyectoInversionDTO.descripcionUniverso", target = "descripcionUniverso")
    @Mapping(source = "bpProyectoInversionDTO.cuantificacion", target = "cuantificacion")
    @Mapping(source = "bpProyectoInversionDTO.idLsUnidad", target = "idLsUnidad.idArgumento")
    @Mapping(source = "bpProyectoInversionDTO.descripcion", target = "descripcion")
    @Mapping(source = "bpProyectoInversionDTO.objetivo", target = "objetivo")
    @Mapping(source = "bpProyectoInversionDTO.observacion", target = "observacion")
    @Mapping(source = "bpProyectoInversionDTO.nombreGerente", target = "nombreGerente")
    @Mapping(source = "bpProyectoInversionDTO.correo", target = "correo")
    @Mapping(source = "bpProyectoInversionDTO.telefono", target = "telefono")
    @Mapping(source = "bpProyectoInversionDTO.cargo", target = "cargo")
    @Mapping(source = "bpProyectoInversionDTO.area", target = "area")
    @Mapping(source = "bpProyectoInversionDTO.fechaResponsable", target = "fechaResponsable",dateFormat = "yyyy-MM-dd")
    @Mapping(source = "bpProyectoInversionDTO.obseracionReg", target = "obseracionReg")
    @Mapping(source = "bpProyectoInversionDTO.version", target = "version")
    @Mapping(source = "bpProyectoInversionDTO.aporteCiudadano", target = "aporteCiudadano")
    @Mapping(source = "bpProyectoInversionDTO.bloqueo", target = "bloqueo")
    @Mapping(source = "bpProyectoInversionDTO.idLsEstado", target = "idLsEstado.idArgumento")
    @Mapping(source = "bpProyectoInversionDTO.fechaEstado", target = "fechaEstado",dateFormat = "yyyy-MM-dd")
    @Mapping(source = "bpProyectoInversionDTO.fechaVersion", target = "fechaVersion",dateFormat = "yyyy-MM-dd")
    @Mapping(source = "bpProyectoInversionDTO.idArchUcm", target = "idArchUcm")
    @Mapping(source = "bpProyectoInversionDTO.peso", target = "peso")
    @Mapping(source = "bpProyectoInversionDTO.enfoqueGenero", target = "enfoqueGenero")
    @Mapping(source = "bpProyectoInversionDTO.idPlanDesarrolloDistrital", target = "idPlanDesarrollo.idPlanDesarrollo")
    @Mapping(source = "bpProyectoInversionDTO.idLsSectorAl", target = "idLsSectorAl.idArgumento")
    BpProyectoInversion bpProyectoInversionDTOToEntity(BpProyectoInversionDTO bpProyectoInversionDTO);

    /**
     * Metodo que mapea una lista tipo PddCompromiso a una lista tipo dto
     *
     * @param bpProyectosInversion lista de pdd compromisos que se desea mapear a dto
     * @return la lista dto mapeada a partir de la lista
     */
    List<BpProyectoInversionDTO> bpProyectosInversionEntitiesToDTO(List<BpProyectoInversion> bpProyectosInversion);

    /**
     * Metodo que mapea una lista BpProyectoInversion a una lista
     *
     * @param bpProyectosInversionDTO lista dto que se desea mapear a entidad
     * @return lista tipo BpProyectoInversion a partir del dto
     */
    List<BpProyectoInversion> bpProyectosInversionDTOToEntities(List<BpProyectoInversionDTO> bpProyectosInversionDTO);

}
