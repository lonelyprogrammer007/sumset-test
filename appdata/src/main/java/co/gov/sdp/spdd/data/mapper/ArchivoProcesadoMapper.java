package co.gov.sdp.spdd.data.mapper;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.mapstruct.BeforeMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import co.gov.sdp.nhspdd.common.dto.ArchivoProcesadoDTO;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.spdd.data.model.afs.ArchivoProcesado;

/**
 * Interface mapper de la clase Archivos Procesados
 *
 * @author Bryan Munoz
 *
 */
@Mapper
public interface ArchivoProcesadoMapper extends Serializable {

	@BeforeMapping
	default void beforeMapping(@MappingTarget ArchivoProcesado target, ArchivoProcesadoDTO source) {
		SimpleDateFormat sdf = new SimpleDateFormat(NHSPDDConstantes.FORMAT_DATE_SHORT_WITH_SLASH_DMY);
		try {
			if (source.getFechaCargue()==null) {
				target.setFechaCargue(new Date());
			} else {
				target.setFechaCargue(sdf.parse(source.getFechaCargue()));
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	@BeforeMapping
	default void beforeMapping(@MappingTarget ArchivoProcesadoDTO target, ArchivoProcesado source) {
		SimpleDateFormat sdf = new SimpleDateFormat(NHSPDDConstantes.FORMAT_DATE_SHORT_WITH_SLASH_DMY);
		if (source.getFechaCargue() != null) {
			target.setFechaCargue(sdf.format(source.getFechaCargue()));
		} else {
			target.setFechaCargue(sdf.format(new Date()));
		}		
	}

	/**
	 * Metodo que mapea una entidad a un dto
	 *
	 * @param ArchivoProcesado entidad que se desea mapear a dto
	 * @return dto mapeado a partir de la entidad
	 */
	@Mapping(source = "archivoProcesado.idArchivo", target = "idArchivo")
	@Mapping(source = "archivoProcesado.nombreArchivo", target = "nombreArchivo")
	@Mapping(source = "archivoProcesado.estado", target = "estado")
	@Mapping(source = "archivoProcesado.detalle", target = "detalle")
	//@Mapping(source = "archivoProcesado.fechaCargue", target = "fechaCargue", dateFormat = NHSPDDConstantes.FORMAT_DATE_SHORT_WITH_SLASH_DMY)
	@Mapping(target = "fechaCargue", ignore = true)
	@Mapping(source = "archivoProcesado.codigoUsuario", target = "codigoUsuario")
	@Mapping(source = "archivoProcesado.idConfigCargue.idConfigCargue", target = "idConfigCargue")
	@Mapping(source = "archivoProcesado.idConfigCargue.idLsTema.resultado", target = "nombreTema")
	@Mapping(source = "archivoProcesado.idConfigCargue.idLsModulo.resultado", target = "nombreModulo")
	ArchivoProcesadoDTO ArchivoProcesadoEntityToDTO(ArchivoProcesado archivoProcesado);

	/**
	 * Metodo que mapea un dto a una entidad
	 *
	 * @param ArchivoProcesadoDTO dto que se desea mapear a entidad
	 * @return la entidad mapeada a partir del dto
	 */
	@Mapping(source = "archivoProcesadoDTO.idArchivo", target = "idArchivo")
	@Mapping(source = "archivoProcesadoDTO.estado", target = "estado")
	@Mapping(source = "archivoProcesadoDTO.nombreArchivo", target = "nombreArchivo")
	@Mapping(source = "archivoProcesadoDTO.detalle", target = "detalle")
	// @Mapping(source = "archivoProcesadoDTO.fechaCargue", target = "fechaCargue")
	@Mapping(target = "fechaCargue", ignore = true)
	@Mapping(source = "archivoProcesadoDTO.codigoUsuario", target = "codigoUsuario")
	@Mapping(source = "archivoProcesadoDTO.idConfigCargue", target = "idConfigCargue.idConfigCargue")
	ArchivoProcesado ArchivoProcesadoDTOToEntity(ArchivoProcesadoDTO archivoProcesadoDTO);

	/**
	 * Metodo que mapea una lista tipo archivosProcesados a una lista tipo dto
	 *
	 * @param archivosProcesados lista de archivosProcesados que se desea mapear a
	 *                           dto
	 * @return la lista dto mapeada a partir de la lista
	 */
	List<ArchivoProcesadoDTO> ArchivoProcesadoEntitiesToDTO(List<ArchivoProcesado> archivosProcesados);

	/**
	 * Metodo que mapea una lista archivosProcesadosDTO a una lista ArchivoProcesado
	 *
	 * @param archivosProcesadosDTO lista dto que se desea mapear a entidad
	 *                              ArchivoProcesado
	 * @return lista tipo ArchivoProcesado a partir del dto
	 */
	List<ArchivoProcesado> ArchivoProcesadoDTOToEntities(List<ArchivoProcesadoDTO> archivosProcesadosDTO);
}
