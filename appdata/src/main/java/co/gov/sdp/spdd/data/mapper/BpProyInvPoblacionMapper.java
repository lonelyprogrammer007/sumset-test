package co.gov.sdp.spdd.data.mapper;

import java.io.Serializable;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvPoblacionDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvProductoDTO;
import co.gov.sdp.spdd.data.model.bp.BpProyInvPoblacion;
import co.gov.sdp.spdd.data.model.bp.BpProyInvProducto;

/**
 * Interface mapper de la clase BpProyInvPoblacion
 *
 * @author LUIS FIGUEROA
 * @version 1.0 02/06/2020
 */
@Mapper
public interface BpProyInvPoblacionMapper extends Serializable {

	/**
     * Metodo que mapea una entidad a un dto
     * @param bpProyInvPoblacion entidad que se desea mapear a dto
     * @return dto mapeado a partir de la entidad
     */
	@Mapping(source = "bpProyInvPoblacion.idPoblacion", target = "idPoblacion")
	@Mapping(source = "bpProyInvPoblacion.numero", target = "numero")
	@Mapping(source = "bpProyInvPoblacion.descripcion", target = "descripcion")
	@Mapping(source = "bpProyInvPoblacion.idLsEtario.idArgumento", target = "idLsEtario")
	@Mapping(source = "bpProyInvPoblacion.idLsEtario.resultado", target = "stringLsEtario")
	@Mapping(source = "bpProyInvPoblacion.idProyInversion.idProyInversion", target = "idProyInversion")
	@Mapping(source = "bpProyInvPoblacion.idProyInversion.nombre", target = "stringProyInversion")
	BpProyInvPoblacionDTO bpProyInvPoblacionEntityToDTO(BpProyInvPoblacion bpProyInvPoblacion);
	
   /**
     * Metodo que mapea un dto a una entidad
     * @param bpProyInvPoblacionDTO dto que se desea mapear a entidad
     * @return la entidad mapeada a partir del dto
     */
	@Mapping(source = "bpProyInvPoblacionDTO.idPoblacion", target = "idPoblacion")
	@Mapping(source = "bpProyInvPoblacionDTO.numero", target = "numero")
	@Mapping(source = "bpProyInvPoblacionDTO.descripcion", target = "descripcion")
	@Mapping(source = "bpProyInvPoblacionDTO.idLsEtario", target = "idLsEtario.idArgumento")
	@Mapping(source = "bpProyInvPoblacionDTO.idProyInversion", target = "idProyInversion.idProyInversion")
	BpProyInvPoblacion bpProyInvPoblacionDTOToEntity(BpProyInvPoblacionDTO bpProyInvPoblacionDTO);
	
   /**
     * Metodo que mapea una lista de entidades a una lista de DTO
     * @param listaBpProyInvPoblacion lista entidades que se desea mapear a DTO
     * @return lista de DTO correspondiente.
     */
    List<BpProyInvPoblacionDTO> bpProyInvPoblacionEntitiesToDTO(List<BpProyInvPoblacion> listaBpProyInvPoblacion);

    /**
     * Metodo que mapea una lista de DTO a una lista de entidades
     * @param listaBpProyInvPoblacionDTO lista dto que se desea mapear a entidad
     * @return lista de entidades correspondiente.
     */
    List<BpProyInvPoblacion> bpProyInvPoblacionDTOToEntities(List<BpProyInvPoblacionDTO> listaBpProyInvPoblacionDTO);
}
