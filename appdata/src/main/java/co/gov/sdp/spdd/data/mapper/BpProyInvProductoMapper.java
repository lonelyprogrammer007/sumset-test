package co.gov.sdp.spdd.data.mapper;

import java.io.Serializable;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvEspecifDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvProductoDTO;
import co.gov.sdp.spdd.data.model.bp.BpProyInvEspecif;
import co.gov.sdp.spdd.data.model.bp.BpProyInvProducto;

/**
 * Clase que se encarga del mapeo de objetos entre BpProyInvProducto y BpProyInvProductoDTO
 * @author SEBASTIAN
 * @version 28/05/2020
 */
@Mapper
public interface BpProyInvProductoMapper extends Serializable {

   /**
     * Metodo que mapea una entidad a un dto
     * @param bpProyInvProducto entidad que se desea mapear a dto
     * @return dto mapeado a partir de la entidad
     */
	@Mapping(source = "bpProyInvProducto.idProducto", target = "idProducto")
	@Mapping(source = "bpProyInvProducto.magnitud", target = "magnitud")
	@Mapping(source = "bpProyInvProducto.idLsDenominacion.idArgumento", target = "idLsDenominacion")
	@Mapping(source = "bpProyInvProducto.idProyMetaPlan.idProyMetaPlan", target = "idProyMetaPlan")
	BpProyInvProductoDTO bpProyInvProductoEntityToDTO(BpProyInvProducto bpProyInvProducto);
	
   /**
     * Metodo que mapea un dto a una entidad
     * @param bpProyInvProductoDTO dto que se desea mapear a entidad
     * @return la entidad mapeada a partir del dto
     */
	@Mapping(source = "bpProyInvProductoDTO.idProducto", target = "idProducto")
    @Mapping(source = "bpProyInvProductoDTO.magnitud", target = "magnitud")
    @Mapping(source = "bpProyInvProductoDTO.idLsDenominacion", target = "idLsDenominacion.idArgumento")
    @Mapping(source = "bpProyInvProductoDTO.idProyMetaPlan", target = "idProyMetaPlan.idProyMetaPlan")
	BpProyInvProducto bpProyInvProductoDTOToEntity(BpProyInvProductoDTO bpProyInvProductoDTO);
	
   /**
     * Metodo que mapea una lista de entidades a una lista de DTO
     * @param listaBpProyInvProducto lista entidades que se desea mapear a DTO
     * @return lista de DTO correspondiente.
     */
    List<BpProyInvProductoDTO> bpProyInvProductoEntitiesToDTO(List<BpProyInvProducto> listaBpProyInvProducto);

    /**
     * Metodo que mapea una lista de DTO a una lista de entidades
     * @param listaBpProyInvProductoDTO lista dto que se desea mapear a entidad
     * @return lista de entidades correspondiente.
     */
    List<BpProyInvProducto> bpProyInvProductoDTOToEntities(List<BpProyInvProductoDTO> listaBpProyInvProductoDTO);


}
