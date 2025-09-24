package co.gov.sdp.spdd.data.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import co.gov.sdp.nhspdd.common.dto.bp.BpProyectoInversionDTO;
import co.gov.sdp.spdd.data.model.bp.BpProyectoInversion;
import co.gov.sdp.spdd.data.model.view.VistaBpProyectoInversion;

@Mapper(uses = { ArgumentoListaSimpleMapper.class })
public interface VistaBpProyectoInversionMapper {

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
    @Mapping(source = "bpProyectoInversion.idLsTipo.idArgumento", target = "idLsTipo")
    @Mapping(source = "bpProyectoInversion.idLsTipo.resultado", target = "stringLsTipo")
    BpProyectoInversionDTO bpProyectoInversionEntityToDTO(VistaBpProyectoInversion bpProyectoInversion);

    /**
     * Metodo que mapea una lista tipo PddCompromiso a una lista tipo dto
     *
     * @param bpProyectosInversion lista de pdd compromisos que se desea mapear a dto
     * @return la lista dto mapeada a partir de la lista
     */
    List<BpProyectoInversionDTO> bpProyectosInversionEntitiesToDTO(List<VistaBpProyectoInversion> bpProyectosInversion);


    
}
