package co.gov.sdp.spdd.data.mapper;

import java.io.Serializable;
import java.util.List;

import org.mapstruct.AfterMapping;
import org.mapstruct.BeforeMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import co.gov.sdp.nhspdd.common.dto.InformacionPresupuestalDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddPrbValoracionDTO;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.spdd.data.model.afs.InformacionPresupuestal;
import co.gov.sdp.spdd.data.model.ip.PddPrbValoracion;

/**
 * Interface mapper de la clase Infomracion presupuestal
 *
 * @author Raul Londono Murillo
 *
 */
@Mapper(uses = { ArchivoProcesadoMapper.class, EntidadMapper.class, PddMapper.class, PddNivelAtributoMapper.class })
public interface InformacionPresupuestalMapper extends Serializable {
	
	@AfterMapping
	default void afterMapping(@MappingTarget InformacionPresupuestal target, InformacionPresupuestalDTO source)
	{
		if (source.getIdArchivo() == null)
			target.setIdArchivo(null);
		if (source.getCodigoN3() == null)
			target.setCodigoN3(null);
		if (source.getCodigoDistrital() == null)
			target.setCodigoDistrital(null);
	}

	/**
	 * Metodo que mapea una entidad a un dto
	 *
	 * @param InformacionPresupuestal entidad que se desea mapear a dto
	 * @return dto mapeado a partir de la entidad
	 */
	@Mapping(source = "informacionPresupuestal.idInfoPresupuestal", target = "idInfoPresupuestal")
	@Mapping(source = "informacionPresupuestal.year", target = "year")
	@Mapping(source = "informacionPresupuestal.mes", target = "mes")
	@Mapping(source = "informacionPresupuestal.codigoDistrital.codigoEntidad", target = "codigoDistrital")
	@Mapping(source = "informacionPresupuestal.codigoProyecto", target = "codigoProyecto")
	@Mapping(source = "informacionPresupuestal.nombreProyecto", target = "nombreProyecto")
	@Mapping(source = "informacionPresupuestal.ejecucionVigencia", target = "ejecucionVigencia")
	@Mapping(source = "informacionPresupuestal.girosVigencia", target = "girosVigencia")
	@Mapping(source = "informacionPresupuestal.recursosSuspendidos", target = "recursosSuspendidos")
	@Mapping(source = "informacionPresupuestal.constitucionReserva", target = "constitucionReserva")
	@Mapping(source = "informacionPresupuestal.apropiacionReserva", target = "apropiacionReserva")
	@Mapping(source = "informacionPresupuestal.apropiacionDefinitiva", target = "apropiacionDefinitiva")
	@Mapping(source = "informacionPresupuestal.ejecucionGiroReservas", target = "ejecucionGiroReservas")
	@Mapping(source = "informacionPresupuestal.codigoInterno", target = "codigoInterno")
	@Mapping(source = "informacionPresupuestal.origen", target = "origen")
	@Mapping(source = "informacionPresupuestal.idArchivo.idArchivo", target = "idArchivo")
	@Mapping(source = "informacionPresupuestal.idPlanDesarrollo.idPlanDesarrollo", target = "idPlanDesarrollo")
	@Mapping(source = "informacionPresupuestal.codigoClasifPresupuestal", target = "codigoClasificacionPresupuestal")
	@Mapping(source = "informacionPresupuestal.codigoN3.idAtributo", target = "codigoN3")
	InformacionPresupuestalDTO informacionPresupuestalEntityToDTO(InformacionPresupuestal informacionPresupuestal);

	/**
	 * Metodo que mapea un dto a una entidad
	 *
	 * @param InformacionPresupuestalDTO dto que se desea mapear a entidad
	 * @return la entidad mapeada a partir del dto
	 */
	@Mapping(source = "informacionPresupuestalDTO.idInfoPresupuestal", target = "idInfoPresupuestal")
	@Mapping(source = "informacionPresupuestalDTO.year", target = "year")
	@Mapping(source = "informacionPresupuestalDTO.mes", target = "mes")
	@Mapping(source = "informacionPresupuestalDTO.codigoDistrital", target = "codigoDistrital.codigoEntidad")
	@Mapping(source = "informacionPresupuestalDTO.codigoProyecto", target = "codigoProyecto")
	@Mapping(source = "informacionPresupuestalDTO.nombreProyecto", target = "nombreProyecto")
	@Mapping(source = "informacionPresupuestalDTO.ejecucionVigencia", target = "ejecucionVigencia")
	@Mapping(source = "informacionPresupuestalDTO.girosVigencia", target = "girosVigencia")
	@Mapping(source = "informacionPresupuestalDTO.recursosSuspendidos", target = "recursosSuspendidos")
	@Mapping(source = "informacionPresupuestalDTO.constitucionReserva", target = "constitucionReserva")
	@Mapping(source = "informacionPresupuestalDTO.apropiacionReserva", target = "apropiacionReserva")
	@Mapping(source = "informacionPresupuestalDTO.apropiacionDefinitiva", target = "apropiacionDefinitiva")
	@Mapping(source = "informacionPresupuestalDTO.ejecucionGiroReservas", target = "ejecucionGiroReservas")
	@Mapping(source = "informacionPresupuestalDTO.codigoInterno", target = "codigoInterno")
	@Mapping(source = "informacionPresupuestalDTO.origen", target = "origen")
	@Mapping(source = "informacionPresupuestalDTO.idArchivo", target = "idArchivo.idArchivo")
	@Mapping(source = "informacionPresupuestalDTO.idPlanDesarrollo", target = "idPlanDesarrollo.idPlanDesarrollo")
	@Mapping(source = "informacionPresupuestalDTO.codigoClasificacionPresupuestal", target = "codigoClasifPresupuestal")
	@Mapping(source = "informacionPresupuestalDTO.codigoN3", target = "codigoN3.idAtributo")
	InformacionPresupuestal informacionPresupuestalDTOToEntity(InformacionPresupuestalDTO informacionPresupuestalDTO);

	/**
	 * Metodo que mapea una lista tipo argumentosimple a una lista tipo dto
	 *
	 * @param informacionesPresupuestales lista de argumentosimple que se desea
	 *                                    mapear a dto
	 * @return la lista dto mapeada a partir de la lista
	 */
	List<InformacionPresupuestalDTO> informacionPresupuestalEntitiesToDTO(
			List<InformacionPresupuestal> informacionesPresupuestales);

	/**
	 * Metodo que mapea una lista InformacionPresupuestalDTO a una lista
	 * InformacionPresupuestal
	 *
	 * @param informacionesPresupuestalesDTO lista dto que se desea mapear a entidad
	 *                                       InformacionPresupuestal
	 * @return lista tipo InformacionPresupuestal a partir del dto
	 */
	List<InformacionPresupuestal> informacionPresupuestalDTOToEntities(
			List<InformacionPresupuestalDTO> informacionesPresupuestalesDTO);
}
