package co.gov.sdp.spdd.data.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import co.gov.sdp.nhspdd.common.dto.bp.BpIniciativaCiudadanaDTO;
import co.gov.sdp.spdd.data.model.bp.BpIniciativaCiudadana;

/**
 * Clase que mapea una bpIniciativaCiudadana de dto a entidad y viceversa
 * 
 * @author Bryan Muñoz
 *
 */
@Mapper
public interface BpIniciativaCiudadanaMapper {

	@Mapping(source = "bpIniciativaCiudadana.idIniciativa", target = "idIniciativa")
	@Mapping(source = "bpIniciativaCiudadana.codigo", target = "codigoIn")
	@Mapping(source = "bpIniciativaCiudadana.fechaCodigo", target = "fechaCodigo", dateFormat = "yyyy-mm-dd")
	@Mapping(source = "bpIniciativaCiudadana.radicado", target = "radicado")
	@Mapping(source = "bpIniciativaCiudadana.numeroRad", target = "numeroRad")
	@Mapping(source = "bpIniciativaCiudadana.fechaRad", target = "fechaRad", dateFormat = "yyyy-mm-dd")
	@Mapping(source = "bpIniciativaCiudadana.nombre", target = "nombre")
	@Mapping(source = "bpIniciativaCiudadana.aplicaLinea", target = "aplicaLinea")
	@Mapping(source = "bpIniciativaCiudadana.idPlanDesarrollo.idPlanDesarrollo", target = "idPlanDesarrollo")
	@Mapping(source = "bpIniciativaCiudadana.problematica", target = "problematica")
	@Mapping(source = "bpIniciativaCiudadana.totalPoblacion", target = "totalPoblacion")
	@Mapping(source = "bpIniciativaCiudadana.alternativaSolucion", target = "alternativaSolucion")
	@Mapping(source = "bpIniciativaCiudadana.nombreProp", target = "nombreProp")
	@Mapping(source = "bpIniciativaCiudadana.telefonoProp", target = "telefonoProp")
	@Mapping(source = "bpIniciativaCiudadana.identificacionProp", target = "identificacionProp")
	@Mapping(source = "bpIniciativaCiudadana.emailProp", target = "emailProp")
	@Mapping(source = "bpIniciativaCiudadana.observacion", target = "observacion")
	@Mapping(source = "bpIniciativaCiudadana.idLsOrigen.idArgumento", target = "idLsOrigen")
	@Mapping(source = "bpIniciativaCiudadana.idLsOrigen.resultado", target = "nombreOrigen")
	@Mapping(source = "bpIniciativaCiudadana.idLsEstadoInicia.idArgumento", target = "idLsEstadoInicia")
	@Mapping(source = "bpIniciativaCiudadana.idLsEstadoInicia.resultado", target = "nombreEstado")
	@Mapping(source = "bpIniciativaCiudadana.idLcLineaInv.idLineaInversion", target = "idLcLineaInv")
	@Mapping(source = "bpIniciativaCiudadana.codigoEntidad.codigoEntidad", target = "codigoEntidad")
	BpIniciativaCiudadanaDTO bpIniciativaCiudadanaEntityToDTO(BpIniciativaCiudadana bpIniciativaCiudadana);

	@Mapping(source = "bpIniciativaCiudadanaDTO.idIniciativa", target = "idIniciativa")
	@Mapping(source = "bpIniciativaCiudadanaDTO.codigoIn", target = "codigo")
	@Mapping(source = "bpIniciativaCiudadanaDTO.fechaCodigo", target = "fechaCodigo", dateFormat = "yyyy-mm-dd")
	@Mapping(source = "bpIniciativaCiudadanaDTO.radicado", target = "radicado")
	@Mapping(source = "bpIniciativaCiudadanaDTO.numeroRad", target = "numeroRad")
	@Mapping(source = "bpIniciativaCiudadanaDTO.fechaRad", target = "fechaRad", dateFormat = "yyyy-mm-dd")
	@Mapping(source = "bpIniciativaCiudadanaDTO.nombre", target = "nombre")
	@Mapping(source = "bpIniciativaCiudadanaDTO.aplicaLinea", target = "aplicaLinea")
	@Mapping(source = "bpIniciativaCiudadanaDTO.idPlanDesarrollo", target = "idPlanDesarrollo.idPlanDesarrollo")
	@Mapping(source = "bpIniciativaCiudadanaDTO.problematica", target = "problematica")
	@Mapping(source = "bpIniciativaCiudadanaDTO.totalPoblacion", target = "totalPoblacion")
	@Mapping(source = "bpIniciativaCiudadanaDTO.alternativaSolucion", target = "alternativaSolucion")
	@Mapping(source = "bpIniciativaCiudadanaDTO.nombreProp", target = "nombreProp")
	@Mapping(source = "bpIniciativaCiudadanaDTO.telefonoProp", target = "telefonoProp")
	@Mapping(source = "bpIniciativaCiudadanaDTO.identificacionProp", target = "identificacionProp")
	@Mapping(source = "bpIniciativaCiudadanaDTO.emailProp", target = "emailProp")
	@Mapping(source = "bpIniciativaCiudadanaDTO.observacion", target = "observacion")
	@Mapping(source = "bpIniciativaCiudadanaDTO.idLsOrigen", target = "idLsOrigen.idArgumento")
	@Mapping(source = "bpIniciativaCiudadanaDTO.idLsEstadoInicia", target = "idLsEstadoInicia.idArgumento")
	@Mapping(source = "bpIniciativaCiudadanaDTO.idLcLineaInv", target = "idLcLineaInv.idLineaInversion")
	@Mapping(source = "bpIniciativaCiudadanaDTO.codigoEntidad", target = "codigoEntidad.codigoEntidad")
	BpIniciativaCiudadana bpIniciativaCiudadanaDTOToEntity(BpIniciativaCiudadanaDTO bpIniciativaCiudadanaDTO);

	List<BpIniciativaCiudadanaDTO> bpIniciativaCiudadanaEntitiesToDTO(
			List<BpIniciativaCiudadana> listaBpIniciativaCiudadana);

	List<BpIniciativaCiudadana> bpIniciativaCiudadanaDTOToEntities(
			List<BpIniciativaCiudadanaDTO> listaBpIniciativaCiudadana);
}
