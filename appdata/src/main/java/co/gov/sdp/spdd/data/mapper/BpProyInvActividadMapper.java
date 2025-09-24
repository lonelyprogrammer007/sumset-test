package co.gov.sdp.spdd.data.mapper;

import java.io.Serializable;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvActividadDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvEspecifDTO;
import co.gov.sdp.spdd.data.model.bp.BpProyInvActividad;
import co.gov.sdp.spdd.data.model.bp.BpProyInvEspecif;

/**
 * Clase que se encarga del mapeo de objetos entre BpProyInvActividad y BpProyInvActividadDTO
 * @author SEBASTIAN
 * @version 29/05/2020
 */
@Mapper
public interface BpProyInvActividadMapper extends Serializable {

   /**
     * Metodo que mapea una entidad a un dto
     * @param bpProyInvActividad entidad que se desea mapear a dto
     * @return dto mapeado a partir de la entidad
     */
	@Mapping(source = "bpProyInvActividad.idActividad", target = "idActividad")
	@Mapping(source = "bpProyInvActividad.consecutivo", target = "consecutivo")
	@Mapping(source = "bpProyInvActividad.magnitud", target = "magnitud")
	@Mapping(source = "bpProyInvActividad.descripcion", target = "descripcion")
	@Mapping(source = "bpProyInvActividad.lineaBase", target = "lineaBase")
	@Mapping(source = "bpProyInvActividad.justificacion", target = "justificacion")
	@Mapping(source = "bpProyInvActividad.idLsProceso.idArgumento", target = "idLsProceso")
	@Mapping(source = "bpProyInvActividad.idLsEstado.idArgumento", target = "idLsEstado")
	@Mapping(source = "bpProyInvActividad.idLsUnidadMedida.idArgumento", target = "idLsUnidadMedida")
	@Mapping(source = "bpProyInvActividad.idProducto.idProducto", target = "idProducto")
	BpProyInvActividadDTO bpProyInvActividadEntityToDTO(BpProyInvActividad bpProyInvActividad);
	
   /**
     * Metodo que mapea un dto a una entidad
     * @param bpProyInvActividadDTO dto que se desea mapear a entidad
     * @return la entidad mapeada a partir del dto
     */
	@Mapping(source = "bpProyInvActividadDTO.idActividad", target = "idActividad")
	@Mapping(source = "bpProyInvActividadDTO.consecutivo", target = "consecutivo")
	@Mapping(source = "bpProyInvActividadDTO.magnitud", target = "magnitud")
	@Mapping(source = "bpProyInvActividadDTO.descripcion", target = "descripcion")
	@Mapping(source = "bpProyInvActividadDTO.lineaBase", target = "lineaBase")
	@Mapping(source = "bpProyInvActividadDTO.justificacion", target = "justificacion")
	@Mapping(source = "bpProyInvActividadDTO.idLsProceso", target = "idLsProceso.idArgumento")
	@Mapping(source = "bpProyInvActividadDTO.idLsEstado", target = "idLsEstado.idArgumento")
	@Mapping(source = "bpProyInvActividadDTO.idLsUnidadMedida", target = "idLsUnidadMedida.idArgumento")
	@Mapping(source = "bpProyInvActividadDTO.idProducto", target = "idProducto.idProducto")
	BpProyInvActividad bpProyInvActividadDTOToEntity(BpProyInvActividadDTO bpProyInvActividadDTO);
	
   /**
     * Metodo que mapea una lista de entidades a una lista de DTO
     * @param listaBpProyInvActividad lista entidades que se desea mapear a DTO
     * @return lista de DTO correspondiente.
     */
    List<BpProyInvActividadDTO> bpProyInvActividadEntitiesToDTO(List<BpProyInvActividad> listaBpProyInvActividad);

    /**
     * Metodo que mapea una lista de DTO a una lista de entidades
     * @param listaBpProyInvActividadDTO lista dto que se desea mapear a entidad
     * @return lista de entidades correspondiente.
     */
    List<BpProyInvActividad> bpProyInvActividadDTOToEntities(List<BpProyInvActividadDTO> listaBpProyInvActividadDTO);
}
