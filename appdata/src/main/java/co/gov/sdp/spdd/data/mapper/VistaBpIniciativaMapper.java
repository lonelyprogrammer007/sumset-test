package co.gov.sdp.spdd.data.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import co.gov.sdp.nhspdd.common.dto.bp.BpIniciativaCiudadanaDTO;
import co.gov.sdp.spdd.data.model.view.VistaBpIniciativaCiudadana;

@Mapper(uses = { PddMapper.class })
public interface VistaBpIniciativaMapper {

	@Mapping(source = "vistaBpIniciativaCiudadana.idIniciativa", target = "idIniciativa")
	@Mapping(source = "vistaBpIniciativaCiudadana.codigoEntidad.codigoEntidad", target = "codigoEntidad")
	@Mapping(source = "vistaBpIniciativaCiudadana.codigo", target = "codigoIn")
	@Mapping(source = "vistaBpIniciativaCiudadana.idPlanDesarrollo.idPlanDesarrollo", target = "idPlanDesarrollo")
	@Mapping(source = "vistaBpIniciativaCiudadana.idPlanDesarrollo.nombrePlan", target = "nombrePlanDesarrollo")
	@Mapping(source = "vistaBpIniciativaCiudadana.fechaCodigo", target = "fechaCodigo", dateFormat = "yyyy-mm-dd")
	@Mapping(source = "vistaBpIniciativaCiudadana.numeroRad", target = "numeroRad")
	@Mapping(source = "vistaBpIniciativaCiudadana.fechaRad", target = "fechaRad", dateFormat = "yyyy-mm-dd")
	@Mapping(source = "vistaBpIniciativaCiudadana.problematica", target = "problematica")
	@Mapping(source = "vistaBpIniciativaCiudadana.codigoPi", target = "codigoPi")
	@Mapping(source = "vistaBpIniciativaCiudadana.alternativaSolucion", target = "alternativaSolucion")
	@Mapping(source = "vistaBpIniciativaCiudadana.idLcLineaInv.idLineaInversion", target = "idLcLineaInv")
	@Mapping(source = "vistaBpIniciativaCiudadana.idLcLineaInv.idLsSector.resultado", target = "nombreSector")
	@Mapping(source = "vistaBpIniciativaCiudadana.idLcLineaInv.descripcion", target = "nombreLineaInversion")
	@Mapping(source = "vistaBpIniciativaCiudadana.idLsEstadoInicia.idArgumento", target = "idLsEstadoInicia")
	@Mapping(source = "vistaBpIniciativaCiudadana.idLsOrigen.idArgumento", target = "idLsOrigen")
	@Mapping(source = "vistaBpIniciativaCiudadana.idLsOrigen.resultado", target = "nombreOrigen")
	@Mapping(source = "vistaBpIniciativaCiudadana.idLsEstadoInicia.resultado", target = "nombreEstado")
	@Mapping(source = "vistaBpIniciativaCiudadana.idProyInversion.idProyInversion", target = "idProyInversion")
	@Mapping(source = "vistaBpIniciativaCiudadana.idProyInversion.nombre", target = "stringProyInversion")
	@Mapping(source = "vistaBpIniciativaCiudadana.idProyInversion.codigo", target = "codigoProyectoInversion")
	BpIniciativaCiudadanaDTO vistaIniciativaEntityToDTO(VistaBpIniciativaCiudadana vistaBpIniciativaCiudadana);

	List<BpIniciativaCiudadanaDTO> vistaIniciativaEntitiesToDTO(List<VistaBpIniciativaCiudadana> lista);
}
