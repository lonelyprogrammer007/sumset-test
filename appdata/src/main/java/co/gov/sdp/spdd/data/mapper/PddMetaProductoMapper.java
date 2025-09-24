package co.gov.sdp.spdd.data.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import co.gov.sdp.nhspdd.common.dto.ip.PddMetaProductoDTO;
import co.gov.sdp.spdd.data.model.ip.PddMetaProducto;

@Mapper
public interface PddMetaProductoMapper {

	@Mapping(source = "pddMetaProducto.idMetaProducto", target = "idMetaProducto")
	@Mapping(source = "pddMetaProducto.magnitud", target = "magnitud")
	@Mapping(source = "pddMetaProducto.complemento", target = "complemento")
	@Mapping(source = "pddMetaProducto.metaPdd", target = "metaPdd")
	@Mapping(source = "pddMetaProducto.observaciones", target = "observaciones")
	@Mapping(source = "pddMetaProducto.esFormulacion", target = "esFormulacion")
	@Mapping(source = "pddMetaProducto.bloqueaPpi", target = "bloqueaPpi")
	@Mapping(source = "pddMetaProducto.ponderacion", target = "ponderacion")
	@Mapping(source = "pddMetaProducto.sumPond", target = "sumPond")
	@Mapping(source = "pddMetaProducto.idLsVerbo.idArgumento", target = "idLsVerbo")
	@Mapping(source = "pddMetaProducto.idLsUnidadMedida.idArgumento", target = "idLsUnidadMedida")
	@Mapping(source = "pddMetaProducto.idLsTipoAnualizacion.idArgumento", target = "idLsTipoAnualizacion")
	@Mapping(source = "pddMetaProducto.idLsEstado.idArgumento", target = "idLsEstado")
	@Mapping(source = "pddMetaProducto.idMetaResultado.idMetaResultado", target = "idMetaResultado")
	@Mapping(source = "pddMetaProducto.fechaCreacion", target = "fechaCreacion", dateFormat = "yyyy-MM-dd")
	@Mapping(source = "pddMetaProducto.idLsVerbo.resultado", target = "nombreVerbo")
	@Mapping(source = "pddMetaProducto.idLsUnidadMedida.resultado", target = "nombreUnidadMedida")
	@Mapping(source = "pddMetaProducto.idLsEstado.resultado", target = "nombreEstado")
	@Mapping(source = "pddMetaProducto.idLsTipoAnualizacion.resultado", target = "nombreIdTipoAnualizacion")
	@Mapping(source = "pddMetaProducto.idMetaResultado.idIndicador.nombre", target = "nombreMetaResultado")
	PddMetaProductoDTO metaProductoEntityToDTO(PddMetaProducto pddMetaProducto);

	@Mapping(source = "pddMetaProductoDTO.idMetaProducto", target = "idMetaProducto")
	@Mapping(source = "pddMetaProductoDTO.magnitud", target = "magnitud")
	@Mapping(source = "pddMetaProductoDTO.complemento", target = "complemento")
	@Mapping(source = "pddMetaProductoDTO.metaPdd", target = "metaPdd")
	@Mapping(source = "pddMetaProductoDTO.observaciones", target = "observaciones")
	@Mapping(source = "pddMetaProductoDTO.esFormulacion", target = "esFormulacion")
	@Mapping(source = "pddMetaProductoDTO.bloqueaPpi", target = "bloqueaPpi")
	@Mapping(source = "pddMetaProductoDTO.ponderacion", target = "ponderacion")
	@Mapping(source = "pddMetaProductoDTO.sumPond", target = "sumPond")
	@Mapping(source = "pddMetaProductoDTO.idLsVerbo", target = "idLsVerbo.idArgumento")
	@Mapping(source = "pddMetaProductoDTO.idLsUnidadMedida", target = "idLsUnidadMedida.idArgumento")
	@Mapping(source = "pddMetaProductoDTO.idLsTipoAnualizacion", target = "idLsTipoAnualizacion.idArgumento")
	@Mapping(source = "pddMetaProductoDTO.idLsEstado", target = "idLsEstado.idArgumento")
	@Mapping(source = "pddMetaProductoDTO.idMetaResultado", target = "idMetaResultado.idMetaResultado")
	@Mapping(source = "pddMetaProductoDTO.fechaCreacion", target = "fechaCreacion", dateFormat = "yyyy-MM-dd")
	PddMetaProducto metaProductoDTOToEntity(PddMetaProductoDTO pddMetaProductoDTO);

	List<PddMetaProductoDTO> metaEntitiesToDTO(List<PddMetaProducto> listaMetaProducto);

	List<PddMetaProducto> metaDTOToEntities(List<PddMetaProductoDTO> listaMetaProductoDTO);

}
