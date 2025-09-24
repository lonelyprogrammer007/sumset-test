package co.gov.sdp.nhspdd.common.dto.ip;

import co.gov.sdp.nhspdd.common.dto.RespuestaDTO;
import lombok.Data;

@Data
public class PddMetaProductoDTO extends RespuestaDTO {

	private Long idMetaProducto;
	private Long magnitud;
	private String complemento;
	private Long metaPdd;
	private String observaciones;
	private Long esFormulacion;
	private Long bloqueaPpi;
	private Long ponderacion;
	private Long idLsVerbo;
	private Long idLsUnidadMedida;
	private Long idLsTipoAnualizacion;
	private Long idLsEstado;
	private Long idMetaResultado;
	private String fechaCreacion;
	private String nombreVerbo;
	private String nombreUnidadMedida;
	private String nombreIdTipoAnualizacion;
	private String nombreEstado;
	private String nombreMetaResultado;
	
	private Long sumPond;

}
